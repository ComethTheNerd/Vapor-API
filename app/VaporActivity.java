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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import vapor.content.VaporServiceBindable;
import vapor.content.VaporServiceConnection;
import vapor.core.$;
import vapor.core.hookframework.VaporHook;
import vapor.listeners.$$hookee;
import vapor.listeners.$$layout;
import vapor.listeners.$$orientation;
import vapor.listeners.hookframework.$hooker;
import vapor.os.VaporBundle;
import vapor.view.VaporView;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;

/**
 * A special extension of Activity that we recommend you subclass for all
 * Activities in your App. This class makes full use of the Vapor framework to
 * provide handy functions and Hooks for the regularly used features when
 * writing Activities, eg. binding to services, device and orientation
 * detection, and notification when the geometry of the content view has been
 * fully calculated. Read the documentation at www.vapor-api.com for a full
 * walkthrough of VaporActivity.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 */
public abstract class VaporActivity extends Activity implements
		VaporServiceBindable {

	public static final String CREATE = "actCreate", START = "actStart",
			STOP = "actStop", PAUSE = "actPause", RESUME = "actResume",
			DESTROY = "actDestroy", RESTART = "actRestart",
			CONFIG_CHANGED = "actConfigChanged", LOW_MEM = "actLowMemory",
			ORIENTATION = "orientation",
			ORIENTATION_PORTRAIT = "orientationPortrait",
			ORIENTATION_LANDSCAPE = "orientationLandscape",
			DEVICE_TABLET = "deviceTablet", DEVICE_PHONE = "devicePhone",
			VIEW_READY = "actViewReady", SERVICE_BIND = "actService",
			SERVICE_UNBIND = "actServiceUnbind";

	// create the hooks for this activity
	private static void initHooks() {

		VaporBundle settings = $.Bundle()
		.put(VaporHook.ADMIN_AUTH, ACT_HOOK_ADMIN) // password protect
													// against admin changes
													// to these hooks
		.put(VaporHook.AUTO_DELETE, false) // these hooks will not be auto
											// deleted when hookeeCount
											// drops to 0
		.put(VaporHook.DELETEABLE, false) // these hooks CANNOT be deleted
		;
		
		// this creates and stores PERSISTENT hooks
		actHooks = new VaporHook[] {
				// Life cycle events
				$.hook(CREATE, settings),
				$.hook(START, settings),
				$.hook(STOP, settings),
				$.hook(PAUSE, settings),
				$.hook(RESUME, settings),
				$.hook(DESTROY, settings),
				$.hook(RESTART, settings),

				// Device callbacks
				$.hook(DEVICE_PHONE, settings),
				$.hook(DEVICE_TABLET, settings),

				// App callbacks
				$.hook(CONFIG_CHANGED, settings),
				$.hook(LOW_MEM, settings),

				// Orientation callbacks
				$.hook(ORIENTATION, settings),
				$.hook(ORIENTATION_PORTRAIT, settings),
				$.hook(ORIENTATION_LANDSCAPE, settings),
				$.hook(SERVICE_BIND, settings) // callbacks for services

		};
	}

	private final String shortName = $.shortName(this);

	private static final Random rdm = new Random();
	
	// MUST MATCH THE VAPOR HOOK ENGINE createSystemHooks() STRING
	private static final String ACT_HOOK_ADMIN = "b45ighepv0hs[032dsfscqwq";
	
	
	
			//"b45ighepv0hs[032d"+ rdm.nextInt(20000) + "sfscqwq" + rdm.nextInt(20000) + "3rdfc";

	// this is initially an empty array to avoid Null pointer in case onDestroy
	// is called before it is assigned to
	private static VaporHook[] actHooks = new VaporHook[] {};


	
	private VaporBundle baseHookArgs;
	private VaporBundle extras; // set in onCreate
	protected final VaporActivity $act = this;

	// flag for first VaporActivity created for Application
	private static boolean firstRun = true;

	private HashMap<Class<? extends Service>, VaporServiceConnection> conns;

	// Media player for the activity
	// protected final VaporMedia media = new VaporMedia();

	@Override
	protected final void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		// $act = this;
		$.act($act); // this should be done as soon as possible

		// ONLY DO THESE THINGS ONCE PER APP RUN
		/*
		 * CURRENTLY MOVED initHooks() TO VAPORHOOKENGINE TO ENSURE
		 * HOOKS CREATED BEFORE CLIENT CAN CREATE THEM
		 * 
		if (firstRun) {
			initHooks(); // do not move this down
			firstRun = false;
		}*/
		initOrientationCallback();

		// bindings to services
		if (conns == null)
			conns = new HashMap<Class<? extends Service>, VaporServiceConnection>();

		// retrieve the bundle passed with this
		if(extras == null){
			Bundle b = getIntent().getExtras();
			if (b == null)
				extras = new VaporBundle();
			else
				extras = new VaporBundle(b);
		}
		// base args sent to act hooks
		if(baseHookArgs == null) baseHookArgs = new VaporBundle().put("activityClass", this.getClass());

		// set up the document.ready callback
		initViewReady();

		// call onVaporCreate for client code
		create(savedInstanceState != null ? new VaporBundle(savedInstanceState)
				: new VaporBundle());

		$.hook(CREATE).fire(ACT_HOOK_ADMIN, baseHookArgs);

		// fire the device hook depending on whether its a phone or tablet
		deviceCheck();

	}

	@Override
	protected final void onStart() {
		super.onStart();
		start();
		$.hook(START).fire(ACT_HOOK_ADMIN, baseHookArgs);
	}

	@Override
	protected final void onRestart() {
		super.onRestart();
		restart();
		$.hook(RESTART).fire(ACT_HOOK_ADMIN, baseHookArgs);
	}

	@Override
	protected final void onResume() {
		super.onResume();
		resume();
		$.hook(RESUME).fire(ACT_HOOK_ADMIN, baseHookArgs);
	}

	@Override
	protected final void onPause() {

		super.onPause();
		pause();
		$.hook(PAUSE).fire(ACT_HOOK_ADMIN, baseHookArgs);
	}

	@Override
	protected final void onStop() {
		super.onStop();

		stop();
		$.hook(STOP).fire(ACT_HOOK_ADMIN, baseHookArgs);

	}

	@Override
	protected final void onDestroy() {
		// call to parent
		super.onDestroy();

		// call to client's onDestroy code
		destroy();
		$.hook(DESTROY).fire(ACT_HOOK_ADMIN, baseHookArgs);

		unbindServices();
		//conns = null;

		// Delete all hooks. This will send a notification to listeners
		for (VaporHook hook : actHooks)
			hook.deleteable(ACT_HOOK_ADMIN, true).delete(ACT_HOOK_ADMIN);

		/*
		// Clear dangling reference to this activity if needs be
		if ($.act() == this)
			$.act((Activity) null);
		 */
		firstRun = true;
	}

	// Clients implement these methods instead so that we can intercept OS
	// messages
	// protected abstract int layoutFile(); // subclasses should implement this
	// to return layout value
	
	
	protected void create(VaporBundle savedInstanceState) {
	}

	protected void start() {
	}

	protected void stop() {
	}

	protected void pause() {
	}

	protected void resume() {
	}

	protected void restart() {
	}

	protected void destroy() {
	}

	protected VaporBundle extras() {
		return extras;
	}

	// -----------------------------------------------------------------------------
	// -----------------------------------------------------------------------------
	// NOTE: There is no boolean fullScreen() method as this can't be deduced
	// currently

	/**
	 * Sets whether this activity is shown in fullScreen mode. In order to have
	 * the title bar hidden as well you must call fullScreen(true) before adding
	 * content
	 * 
	 * @param fullScreen
	 *            whether the activity should be in fullScreen mode
	 */
	public void fullScreen(boolean fullScreen) {
		if (fullScreen) {
			try {
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				// throws AndroidRunTimeException, must be done before adding
				// content
			} catch (AndroidRuntimeException are) {
				Log.w(shortName + ".fullScreen(boolean)",
						"To hide Title Bar call fullScreen(true) before adding content");
			}
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
			getWindow().clearFlags(
					WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		} else {
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
			getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
	}

	// -----------------------------------------------------------------------------
	// -----------------------------------------------------------------------------

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

				$.hook(SERVICE_BIND).fire(ACT_HOOK_ADMIN,
						$.Bundle().put("serviceName", name));
			}

			@Override
			public void onServiceDisconnected(ComponentName name) {
				super.onServiceDisconnected(name);
				conns.remove(serviceClass());

				$.hook(SERVICE_UNBIND).fire(ACT_HOOK_ADMIN,
						$.Bundle().put("serviceName", name));
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

	/**
	 * Unbind all services currently bound to this activity
	 * 
	 */
	public void unbindServices() {

		Log.i(shortName + ".unbindServices()", "Unbinding services bound to "
				+ shortName);
		// clean up any running services
		for (Class<? extends Service> serviceClass : conns.keySet()) {
			unbindService(serviceClass);
		}
		// conns = null; // gc the stack

	}

	/* DEVICE METHODS */
	private void deviceCheck() {
		// put screen dimensions in a bundle, passed to the callback
		VaporBundle args = $.Bundle().put("displayWidth", $.displayWidth())
				.put("displayHeight", $.displayHeight());

		// fire appropriate device callback
		switch ($.device()) {
		case $.DEVICE_PHONE:
			$.hook(DEVICE_PHONE).fire(ACT_HOOK_ADMIN, args);
			break;
		case $.DEVICE_TABLET:
			$.hook(DEVICE_TABLET).fire(ACT_HOOK_ADMIN, args);
			break;
		}

	}

	/* ORIENTATION METHODS */
	private void initOrientationCallback() {

		$$orientation orientationListener = new $$orientation(this) {

			@Override
			public void onOrientationChanged(int orientation) {
				// fire the orientation changed hook
				$.hook(ORIENTATION).fire(ACT_HOOK_ADMIN,
						$.Bundle().put("orientation", orientation));

				// fire orientation specific hook
				if (portrait())
					$.hook(ORIENTATION_PORTRAIT).fire(ACT_HOOK_ADMIN);
				else
					$.hook(ORIENTATION_LANDSCAPE).fire(ACT_HOOK_ADMIN);

			}
		};

		// start the orientation listener
		orientationListener.enable();

		// hook in to the onDestroy event
		$.hook(STOP).hookIn(new $$hookee<$$orientation>(orientationListener) {

			public void call(String hookName, VaporBundle args) {
				// disable the orientation listener in onDestroy
				$subject.disable();
				// remove observer from the onDestroy hook
				// $.hook(ACT_STOP).hookOut(this);
			}
		});
	}

	public int orientation() {
		return $.orientation(this);
	}

	public void orientation(int orientation) {
		$.orientation(this,orientation);
	}

	public final boolean portrait() {
		return orientation() == Configuration.ORIENTATION_PORTRAIT;
	}

	public final boolean landscape() {
		return orientation() == Configuration.ORIENTATION_LANDSCAPE;
	}

	/* SYSTEM HOOKS */

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		$.hook(CONFIG_CHANGED).fire(ACT_HOOK_ADMIN,
				$.Bundle().put("newConfig", newConfig));
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		$.hook(LOW_MEM).fire(ACT_HOOK_ADMIN);
	}

	/* CONTENT VIEW */
	
	/**Set the activity content from a layout resource. The resource will be inflated, adding all top-level views to the activity.
	*
	*@param layoutResId Resource ID to be inflated
	*/
	protected final void contentView(int layoutResId) {
		setContentView(layoutResId);
	}

	/**Set the activity content to an explicit view. This view is placed directly into the activity's view hierarchy. 
	* It can itself be a complex view hierarchy. When calling this method, the layout parameters of the specified view are ignored. 
	* Both the width and the height of the view are set by default to MATCH_PARENT. To use your own layout parameters, 
	* invoke cntentView(VaporView<? extends View,?>, android.view.ViewGroup.LayoutParams) instead.
	*
	*@param view The desired content to display
	*/
	protected final void contentView(VaporView<? extends View, ?> view) {
		setContentView(view.view());
	}
	
	/**Set the activity content to an explicit view. This view is placed directly into the activity's view hierarchy. 
	* It can itself be a complex view hierarchy.
	*
	*@param view The desired content to display
	*@param layoutParams Layout parameters for the view
	*/
	protected final void contentView(VaporView<? extends View, ?> view,  ViewGroup.LayoutParams layoutParams) {
		setContentView(view.view(),layoutParams);
	}

	/* VIEW READY CALLBACK */
	// equivalent to document.ready in jQuery
	private final void initViewReady() {
/*
		View root = getWindow().getDecorView().getRootView();

		if (root != null) {
			final VaporView<View, ?> vaporRoot = $.View(root);
			
			ViewTreeObserver vto = vaporRoot.treeObserver();
			vto.addOnGlobalLayoutListener(new $$layout() {

				@Override
				public void onGlobalLayout() {
					// When the layout has been calculated we fire this event to
					// show the View is ready
					$.hook(VIEW_READY).fire(ACT_HOOK_ADMIN, baseHookArgs);
					// delete this hook after we fire it, it is only fired once
					// per activity
		
					//$.hook(VIEW_READY).delete(ACT_HOOK_ADMIN);
					vaporRoot.treeObserver().removeOnGlobalLayoutListener(this);
				}
			});
		}
*/

		// Create the View ready hook
		$.hook(VIEW_READY, $.Bundle()
							.put(VaporHook.ADMIN_AUTH, ACT_HOOK_ADMIN) 	// password protect
																		// against admin changes
																		// to these hooks
							.put(VaporHook.AUTO_DELETE, false) 			// these hooks will not be auto
																		// deleted when hookeeCount
																		// drops to 0
							.put(VaporHook.DELETEABLE, true) 			// this hook is deleted when fired
		);

		View root = getWindow().getDecorView().getRootView();

		if (root != null) {
			final VaporView<View, ?> vaporRoot = $.View(root);
			ViewTreeObserver vto = vaporRoot.treeObserver();
			vto.addOnGlobalLayoutListener(new $$layout() {

				@Override
				public void onGlobalLayout() {
					// When the layout has been calculated we fire this event to
					// show the View is ready
					$.hook(VIEW_READY).fire(ACT_HOOK_ADMIN, baseHookArgs);
					// delete this hook after we fire it, it is only fired once
					// per activity
					$.hook(VIEW_READY).delete(ACT_HOOK_ADMIN);
					vaporRoot.treeObserver().removeOnGlobalLayoutListener(this);
				}
			});
		}
		
		
	}
}
