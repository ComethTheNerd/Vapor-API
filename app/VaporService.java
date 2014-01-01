/*	Vapor API Terms and Conditions
 *
 *  - You MAY NOT
 *  	- attempt to claim ownership of, or pass off the Vapor source code and materials as your own work unless:
 *
 *  		- used as constituent component in an Android application that you intend to release and/or profit from
 *
 *		- use or redistribute the Vapor source code and materials without explicit attribution to the owning parties
 *
 *  	- advertise Vapor in a misleading, inappropriate or offensive fashion
 *
 * - Indemnity
 * 		You agree to indemnify and hold harmless the authors of the Software and any contributors for any direct, indirect, 
 * 		incidental, or consequential third-party claims, actions or suits, as well as any related expenses, liabilities, damages, 
 * 		settlements or fees arising from your use or misuse of the Software, or a violation of any terms of this license.
 *  
 *  - DISCLAIMER OF WARRANTY
 *  	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESSED OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, 
 *		WARRANTIES OF QUALITY, PERFORMANCE, NON-INFRINGEMENT, MERCHANTABILITY, OR FITNESS FOR A PARTICULAR PURPOSE.
 *  
 *  - LIMITATIONS OF LIABILITY
 *  	YOU ASSUME ALL RISK ASSOCIATED WITH THE INSTALLATION AND USE OF THE SOFTWARE. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * 		HOLDERS OF THE SOFTWARE BE LIABLE FOR CLAIMS, DAMAGES OR OTHER LIABILITY ARISING FROM, OUT OF, OR IN CONNECTION WITH THE 
 *		SOFTWARE. LICENSE HOLDERS ARE SOLELY RESPONSIBLE FOR DETERMINING THE APPROPRIATENESS OF USE AND ASSUME ALL RISKS ASSOCIATED 
 *		WITH ITS USE, INCLUDING BUT NOT LIMITED TO THE RISKS OF PROGRAM ERRORS, DAMAGE TO EQUIPMENT, LOSS OF DATA OR SOFTWARE PROGRAMS, 
 *		OR UNAVAILABILITY OR INTERRUPTION OF OPERATIONS.
 *  
 *  © Darius Hodaei. License Version 1.1. Last Updated 30/06/2013.
*/

package vapor.app;

import java.util.HashMap;
import java.util.Random;

import vapor.content.VaporIntent;
import vapor.content.VaporServiceBindable;
import vapor.content.VaporServiceConnection;
import vapor.core.$;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * VaporService is an extension of the standard Android Service class, and
 * provides an efficient model for your service by running it entirely in its
 * own thread (away from the main UI thread). The underlying thread can be
 * safely paused at any time using paused(boolean), and can be set to sleep for
 * an arbitrary interval upon each iteration using sleepFor(int). In your
 * subclass you must at least provide an implementation for runCode() containing
 * your main service code, as this method is invoked once per iteration of the
 * thread loop.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 */
public abstract class VaporService extends Service implements
		VaporServiceBindable {
	// NOTE: ALL Services must be declared in manifest file: <service
	// android:name=".ExampleService" />

	private boolean bindable = true, // whether clients can bind to this service
			running = false, // whether the service has been started, and not
								// yet stopped (may be paused)
			stopped = false, paused = false; // whether the current thread has
												// been paused, and will be
												// resumed at some point

	private volatile boolean threadExec = false; // FLAG: The main THREAD loop
													// will execute until false

	private int sleep = 0, bindCount = 0;
	private final String shortName = $.shortName(this);

	public static final int SERVICE_NOT_STARTED = 0, SERVICE_RUNNING = 1,
			SERVICE_PAUSED = 2, SERVICE_STOPPED = 3;

	// the thread in which this service will run
	private Thread thread = null;
	private VaporBinder binder = null;

	public VaporService() {
	}

	// ------------------------------------------------
	/*
	 * The system calls this method when the service is first created, to
	 * perform one-time setup procedures (before it calls either
	 * onStartCommand() or onBind()) NOTE: If the service is already running,
	 * this method is not called.
	 */
	@Override
	public final void onCreate() {
		super.onCreate();

		// bindings to services
		if (conns == null)
			conns = new HashMap<Class<? extends Service>, VaporServiceConnection>();

		thread = new Thread(new VaporServiceRunnable());

		// set priority of thread to default
		priority(Thread.NORM_PRIORITY);

		// call client code
		create();
	}

	/*
	 * Called by system when bound to pass a Binder back. I treat Binders as
	 * throw away objects.
	 */
	@Override
	public final IBinder onBind(Intent intent) {
		// NOTE: DO NOT EDIT. Edit bind(Intent,boolean) instead
		if (bindable) {
			// Unified bind/rebind event
			__bind($.Intent(intent), false);
			return binder == null ? binder = new VaporBinder(this) : binder;
		}
		// "If you don't want to allow binding, then you should return null"
		else
			return null;

	}

	/*
	 * Called when all Activities are unbound
	 */
	public final boolean onUnbind(Intent intent) {
		// NOTE: DO NOT EDIT. Edit unbind(Intent) instead
		super.onUnbind(intent);
		Log.i(shortName + ".onUnbind(Intent)", "Unbinding VaporService");
		__unbind($.Intent(intent));
		// NOTE: ALWAYS return true so that onRebind(Intent) is definitely
		// called,
		// otherwise no bind/rebind event is called
		return true;
	}

	/*
	 * A client is binding to the service with bindService(), after onUnbind()
	 * has already been called and returned true
	 */
	public final void onRebind(Intent intent) {
		// NOTE: DO NOT EDIT. Edit bind(Intent,boolean) instead
		super.onRebind(intent);
		Log.i(shortName + ".onRebind(Intent)", "Rebinding VaporService");

		// Unified bind/rebind event
		__bind($.Intent(intent), true);
	}

	public final int onStartCommand(Intent intent, int flags, int startId) {

		super.onStartCommand(intent, flags, startId);

		running(true);

		return startCommand($.Intent(intent), flags, startId);
	}

	/*
	 * The system calls this method when the service is no longer used and is
	 * being destroyed. Your service should implement this to clean up any
	 * resources such as threads, registered listeners, receivers, etc. This is
	 * the last call the service receives.
	 */
	@Override
	public final void onDestroy() {
		super.onDestroy();
		Log.i(shortName + ".onDestroy()", "Destroying VaporService");

		running(false);
		destroy();

		unbindServices();
		//conns = null;
		thread = null;
	}

	// ------------------------------------------------
	public int bindCount() {
		return bindCount;
	}

	private final void __bind(VaporIntent<? extends Intent, ?> intent,
			boolean rebind) {
		++bindCount;
		bind(intent, rebind);
	}

	private final void __unbind(VaporIntent<? extends Intent, ?> intent) {
		--bindCount;
		unbind(intent);
		binder = null;
	}

	/**
	 * Returns whether this service is bindable and allows clients to bind to it
	 * 
	 * @return whether this service is bindable and allows clients to bind to it
	 */
	public final boolean bindable() {
		return bindable;
	}

	/**
	 * Set whether to allow clients to bind to this service
	 * 
	 * @param bindable
	 *            whether to allow clients to bind to this service
	 */
	public final void bindable(boolean bindable) {
		this.bindable = bindable;
	}

	/**
	 * Gets the priority for the underlying thread that this Service runs inside
	 * 
	 * @return the thread priority for the underlying thread
	 */
	public final int priority() {
		return thread.getPriority();
	}

	/**
	 * Sets the priority for the underlying thread that this Service runs inside
	 * 
	 * @param threadPriority
	 *            the new thread priority for the underlying thread
	 */
	public final void priority(int threadPriority) {
		thread.setPriority(threadPriority);
	}

	/**
	 * Returns how long in milliseconds the underlying thread will sleep for
	 * every iteration
	 * 
	 * @return how long in milliseconds the underlying thread will sleep for
	 *         every iteration
	 */
	public final int sleepFor() {
		return sleep;
	}

	/**
	 * Sets how long in milliseconds the underlying thread should sleep for
	 * every iteration
	 * 
	 * @param sleepFor
	 *            The amount in milliseconds that the underlying thread should
	 *            sleep for every iteration
	 */
	public final void sleepFor(int sleepFor) {
		this.sleep = sleepFor;
	}

	private final void startThread() {
		Log.i(shortName + ".startThread()", "Starting VaporService thread");

		// Nothing to do if already running
		if (running)
			return;

		thread.start();
		running = true;
		try {
			synchronized (thread) {
				thread.wait(500);
			}
			Log.i(shortName + ".startThread()",
					"Waiting for VaporService thread to finish initialising");
			synchronized (thread) {
				thread.wait(200);
			}

		} catch (final InterruptedException ex) {
			Log.e(shortName + ".startThread()",
					"Error waiting for VaporService thread initialisation: "
							+ ex.getMessage());
			ex.printStackTrace();
		}

	}

	private final void stopThread() {
		Log.i(shortName + ".stopThread()", "Stopping VaporService thread");
		try {
			threadExec = false;
			thread.join();
			running = false;
		} catch (final InterruptedException ex) {
			Log.e(shortName + ".stopThread()",
					"Error joining VaporService thread: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public final boolean paused() {
		return paused;
	}

	public final void paused(boolean paused) {
		// if no change, return early
		if (this.paused == paused)
			return;
		// change of pause state
		this.paused = paused;

		// pausing is handled in run(), the thread will wait() if this.paused ==
		// true,
		// we only handle unpausing here from outside the thread...
		if (!this.paused) {
			// notify the thread that it can continue
			synchronized (thread) {
				thread.notify(); // unpause the thread...
			}
		}

	}

	/**
	 * Returns whether this Service, and its underlying thread, are running.
	 * Note, this method will tell you whether the Service is alive still, but
	 * to query whether the service is alive and not paused use state() ==
	 * SERVICE_RUNNING
	 * 
	 * @return
	 */
	public final boolean running() {
		return running;
	}

	/**
	 * Sets whether this Service is running, and running(false) may only be
	 * called once. To temporarily pause the service use paused(true) instead as
	 * you can unpause this safely.
	 * 
	 * @param running
	 *            true if the Service should run, false to stop it
	 */
	public final void running(boolean running) {
		if (running) {
			if (this.running)
				return; // already running
			startThread();
		} else {
			stopThread();
			stopSelf();
			stopped = true;
		}
		this.running = running; // update flag
	}

	/**
	 * Returns a value denoting the current state of the Service. This takes in
	 * to account whether the Service has started, whether its paused, and
	 * whether its been stopped.
	 * 
	 * @return one of SERVICE_STOPPED, SERVICE_NOT_STARTED, SERVICE_PAUSED or
	 *         SERVICE_RUNNING
	 */
	public final int state() {
		if (stopped)
			return SERVICE_STOPPED; // need additional flag 'stopped'

		else if (!running)
			return SERVICE_NOT_STARTED; // !stopped && !running

		else if (paused)
			return SERVICE_PAUSED; // running && paused

		else
			return SERVICE_RUNNING; // running && !paused
	}

	// --------------------------------------------------------------
	// NOTE: Moved Runnable to inner class to prevent clients calling run()
	private class VaporServiceRunnable implements Runnable {
		@Override
		public final void run() {

			synchronized (thread) {
				thread.notifyAll();
			}

			Log.i(shortName + ".run()", "Run called on VaporService thread");

			threadExec = true; // flag for continuous execution
			try {
				while (threadExec) {

					if (paused) {
						synchronized (thread) {
							thread.wait();
						}
					}

					// client run code
					runCode();

					// sleep for the set interval
					Thread.sleep(sleep);

				}
			} catch (final InterruptedException ex) {
				Log.e(shortName + ".run()",
						"Error running VaporService thread: " + ex.getMessage());
				ex.printStackTrace();
			}

		}
	}

	// --------------------------------------------------------------
	public class VaporBinder extends Binder {
		private VaporService binding;

		public VaporBinder(VaporService service) {
			binding = service;
		}

		public VaporService service() {
			return binding;
		}
	}

	// --------------------------------------------------------------

	// CLIENT METHODS
	/**
	 * Called by the system every time a client explicitly starts the service by
	 * calling startService(Intent), providing the arguments it supplied and a
	 * unique integer token representing the start request. This happen after
	 * Vapor has automatically invoked the same super method. Do not call this
	 * method directly.
	 * 
	 * @param intent
	 *            The Intent supplied to startService(Intent), as given. This
	 *            may be null if the service is being restarted after its
	 *            process has gone away, and it had previously returned anything
	 *            except START_STICKY_COMPATIBILITY.
	 * @param flags
	 *            Additional data about this start request. Currently either 0,
	 *            START_FLAG_REDELIVERY, or START_FLAG_RETRY.
	 * @param startId
	 *            A unique integer representing this specific request to start.
	 *            Use with stopSelfResult(int).
	 * @return The return value indicates what semantics the system should use
	 *         for the service's current started state. It may be one of the
	 *         constants associated with the START_CONTINUATION_MASK bits.
	 */
	public int startCommand(VaporIntent<? extends Intent, ?> intent, int flags,
			int startId) {
		return START_STICKY;
	}

	/**
	 * Called by the system when the service is first created, after Vapor has
	 * automatically invoked the same super method. Do not call this method
	 * directly.
	 */
	public void create() {
	}

	/**
	 * Called when all clients have disconnected from a particular interface
	 * published by the service. Please note, unlike onUnbind(Intent) this
	 * method does not return a boolean. This is to force the onRebind(Intent)
	 * event by automatically returning a true value to the OS from
	 * onUnbind(Intent).
	 * 
	 * @param intent
	 *            The Intent that was used to bind to this service, as given to
	 *            Context.bindService. Note that any extras that were included
	 *            with the Intent at that point will not be seen here.
	 */
	public void unbind(VaporIntent<? extends Intent, ?> intent) {
	}

	/**
	 * Called when new clients have connected to the service. Unlike the
	 * standard Android framework, this event will ALWAYS be raised upon a new
	 * client binding to the service, even if all clients previously
	 * disconnected from it (a rebind).
	 * 
	 * @param intent
	 *            The Intent that was used to bind to this service, as given to
	 *            Context.bindService. Note that any extras that were included
	 *            with the Intent at that point will not be seen here.
	 * @param rebind
	 *            True if this event is the result of a rebind, ie. when all
	 *            clients have previously disconnected from this service
	 */
	public void bind(VaporIntent<? extends Intent, ?> intent, boolean rebind) {
	}

	/**
	 * Override this method to clean up any resources used by your service,
	 * called after Vapor has automatically invoked the same super method. You
	 * needn't stop the service yourself as Vapor will call stopSelf() when the
	 * service is being destroyed.
	 */
	public void destroy() {
	}

	/**
	 * This method should contain your service code, and is invoked once per
	 * iteration of the service loop. Use sleepFor(int) to force the thread to
	 * sleep for a set period of time during each iteration.
	 */
	public abstract void runCode();

	// -------------------------------------------------------------------------
	private HashMap<Class<? extends Service>, VaporServiceConnection> conns;
	private static final Random rdm = new Random();
	private static final String SRV_HOOK_ADMIN = "2390r0d9uxjwi12pwskomx4=f0rce-d";
			
			//"2390r0d9uxjwi"+ rdm.nextInt(20000) + "12pwskomx" + rdm.nextInt(20000)+ "4=f0rce-d";
	public static final String SERVICE_EVENT = "serviceEvent";

	public VaporServiceConnection vsc(Class<? extends Service> serviceClass) {
		return new VaporServiceConnection(serviceClass) {

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				super.onServiceConnected(name, service);

				// WE ONLY ALLOW A BINDING IF bindService DOES NOT FIND EXISTING
				// MAPPING
				// SO IT IS SAFE TO ASSUME HERE THAT WE WON'T LEAK A SERVICE BY
				// OVERWRITING
				conns.put(serviceClass(), this);
				$.hook(SERVICE_EVENT).fire(SRV_HOOK_ADMIN,
						$.Bundle().put("serviceName", name).put("bound", true));
			}

			@Override
			public void onServiceDisconnected(ComponentName name) {
				super.onServiceDisconnected(name);
				conns.remove(serviceClass());
				$.hook(SERVICE_EVENT)
						.fire(SRV_HOOK_ADMIN,
								$.Bundle().put("serviceName", name)
										.put("bound", false));
			}

		};

	}

	public <T extends Service> VaporServiceConnection binding(
			Class<T> serviceClass) {
		if (conns.containsKey(serviceClass))
			return conns.get(serviceClass);
		return null;
	}

	public <T extends Service> T service(Class<T> serviceClass) {
		// VSC's are only added to conns once onServiceConnected is called, so
		// this is safe
		if (conns.containsKey(serviceClass))
			return (T) conns.get(serviceClass).service();
		return null;
	}

	@Override
	public boolean bindService(Intent intent, ServiceConnection connection,
			int flags) {

		if (VaporServiceConnection.class
				.isAssignableFrom(connection.getClass())) {
			VaporServiceConnection vsc = ((VaporServiceConnection) connection);

			// We only attempt to bind if we aren't already bound to this
			// service
			if (!conns.containsKey(vsc.serviceClass())) {
				return super.bindService(intent, vsc, flags);
			} else {
				Log.w(shortName + ".bindService(Intent,ServiceConnection,int)",
						"Already bound to "
								+ vsc.serviceClass()
								+ ". To avoid service leaks you must unbind from the service first.");
			}
			return false;
		} else {
			Log.w(shortName + ".bindService(Intent,ServiceConnection,int)",
					"This service binding will NOT be managed by Vapor as 'connection' is not an instance of VaporServiceConnection. \nUse .vsc(Class<? extends Service>) to obtain a VaporServiceConnection for your Service");
			return super.bindService(intent, connection, flags);
		}

	}

	public void unbindService(Class<? extends Service> serviceClass) {
		if (conns.containsKey(serviceClass)) {
			// retrieve connection
			VaporServiceConnection vsc = conns.get(serviceClass);
			// call to unbind from parent
			super.unbindService(vsc);
			// remove mapping
			// conns.remove(serviceClass); REMOVED IN vsc INSTANCE
		} else {
			Log.e(shortName + ".unbindService(Class<? extends Service>)",
					"No binding found for the class: " + serviceClass);
		}

	}

	@Override
	public void unbindService(ServiceConnection connection) {
		if (VaporServiceConnection.class
				.isAssignableFrom(connection.getClass())) {
			// attempt cast
			VaporServiceConnection vsc = ((VaporServiceConnection) connection);

			// delegate call to new method that can accept just the service
			// class
			unbindService(vsc.serviceClass());
		} else {
			Log.i(shortName + ".unbindService(ServiceConnection)",
					"Unbinding unmanaged ServiceConnection");
			super.unbindService(connection);
		}

	}

	public void unbindServices() {

		Log.i(shortName + ".unbindServices()", "Unbinding services bound to "
				+ shortName);
		// clean up any running services
		for (Class<? extends Service> serviceClass : conns.keySet()) {
			unbindService(serviceClass);
		}
		// conns = null; // gc the stack

	}
}
