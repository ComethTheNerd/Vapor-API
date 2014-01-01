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

package vapor.core.hookframework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import vapor.core.$;
import vapor.exception.VaporLicenseException;
import vapor.listeners.$$hooker;
import vapor.listeners.hookframework.$hookee;
import vapor.listeners.hookframework.$hooker;
import vapor.os.VaporBundle;
import android.util.Log;

/**
 * A VaporHook is an embellished implementation of the Observer Design Pattern, and
 * is used to inform subscribers (Hookees) when an event of interest occurs.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 */
public final class VaporHook {
	// no password
	public static final String NO_PASS = "";
	public static final String ADMIN_AUTH = "adminAuth",
			SUBSCRIBER_AUTH = "subscriberAuth", DELETEABLE = "deletable",
			AUTO_DELETE = "autoDelete";

	protected static final String __ADMIN_AUTH = NO_PASS,
			__SUBSCRIBER_AUTH = NO_PASS;

	protected final String shortName = $.shortName(this);
	protected static final boolean __DELETEABLE = true, __AUTO_DELETE = false;

	private final String hookName;// , adminAuth, subscriberAuth;
	private int callCount;

	protected enum Locker {
		callBack, delete
	};

	private $hooker hooker;
	private ArrayList<$hookee<?>> hookees, readOnlyList;
	private Stack<$hookee<?>> /* hookOutWaitingList, */hookInWaitingList;

	private Iterator<$hookee<?>> hookeeIterator;

	// the name of the operation that is locking the list
	protected Locker locker = Locker.callBack;

	protected final Iterator<$hookee<?>> lockList(Locker locker) {
		if (lockedList() && locker != this.locker)
			return null;

		this.locker = locker;
		return hookeeIterator = hookees.iterator();
	}

	private boolean abortAndDelete = false, dead = false;
	private boolean[] asyncHookOuts;

	protected final boolean lockedList() {
		return hookeeIterator != null;
	}

	protected final void unlockList(Locker locker) {
		// only the method that locked it can unlock it
		if (locker != this.locker)
			return;

		hookeeIterator = null;

		/*
		 * // if readOnlyList is not null, we have already updated a shadow list
		 * // so can just copy that if (readOnlyList != null) { hookees =
		 * readOnlyList; readOnlyList = null; return; } // otherwise, have to
		 * update with async changes else
		 */hookees = __update(hookees); // this will return readOnlyList if
										// already calculated

		// readOnlyList = null; // memory reclaim
		__autoDelete();
	}

	private final ArrayList<$hookee<?>> __update(ArrayList<$hookee<?>> list) {

		// if readOnlyList is not null, we have already updated a shadow list
		// so can just copy that
		if (readOnlyList != null) {
			return readOnlyList; // this means no hookIns or hookOuts have taken
									// place
		}

		while (!hookInWaitingList.isEmpty()) {
			$hookee<?> vh = hookInWaitingList.pop();

			if (!hookees.contains(vh))
				list.add(vh);
		}

		// we keep track of how many items we've deleted so that we can adjust
		// the list iterator index accordingly to avoid Exceptions, and pointing
		// to the wrong
		// element in the list
		int ptrAdjustment = 0;
		for (int index = 0; index < asyncHookOuts.length; index++) {
			if (asyncHookOuts[index]) {
				list.remove(index - ptrAdjustment);
				ptrAdjustment++;
			}
		}

		return list;
	}

	/*
	 * Ok, so whenever
	 */
	private final List<$hookee<?>> readOnlyList() {
		readOnlyList = __update((ArrayList<$hookee<?>>) hookees.clone());

		// return a read only version of the readOnlyList
		return Collections.unmodifiableList(readOnlyList);
	}

	private final <T> void __hookIn($hookee<T> observer) {
		if (dead) {
			Log.e(shortName + ".__hookIn(VaporHookee<T>)",
					"Cannot hookIn to a dead Hook!");
			return;
		}

		readOnlyList = null; // readOnlyList will be stale so wipe it out

		if (lockedList()) {
			hookInWaitingList.push(observer);
		} else if (!hookees.contains(observer))
			hookees.add(observer);
	}

	private final <T> void __hookOut($hookee<T> observer) {
		if (dead) {
			Log.e(shortName + ".__hookOut(VaporHookee<T>)",
					"Cannot hookOut from a dead Hook!");
			return;
		}

		readOnlyList = null; // readOnlyList will be stale so wipe it out

		if (lockedList()) {

			int indexOf = hookees.indexOf(observer);
			if (indexOf != -1)
				asyncHookOuts[indexOf] = true;

			else
				Log.w(shortName + ".__hookOut(VaporHookee<T>)",
						"HookOut failed. Not a subscriber: "
								+ observer.toString());

		} else if (hookees.contains(observer)) {
			hookees.remove(observer);
			__autoDelete();
		}

	}

	private final void __autoDelete() {
		// if auto delete is true, the hook is deleted when subscriber
		// list is empty
		if (hookees.isEmpty() && autoDelete()) {
			if (deleteable()) {
				Log.i(shortName + ".__autoDelete()",
						"Autodeleting Hook! 0 observers left, and AUTO_DELETE is true!");
				delete(prop(ADMIN_AUTH, __ADMIN_AUTH));
			} else {
				Log.e(shortName + ".__autoDelete()",
						"Autodeleting Hook failed! Ensure that DELETEABLE is true.");
			}
		}
	}

	private VaporBundle settings;

	/*
	 * private Hashtable<Class<?>, HookRule<?>> rules;
	 * 
	 * public <T> void rule(Class<T> cl,HookRule<T> rule,String adminAuth){
	 * if(adminAccess(adminAuth)) rules.put(rule.subject(), rule); }
	 */

	public VaporHook(String hookName) {
		this(hookName, null);
		// this "" as adminAuth means that even if you set secureHooks=true, you
		// can leave passwords blank
		// and the non restricted hook will fire, even in a restricted
		// environment
	}

	public VaporHook(String hookName, VaporBundle settings) {
			this.settings = settings != null ? settings : $.Bundle();
			this.hookName = hookName;
			this.callCount = 0;
			this.hooker = new $$hooker();
			this.hookees = new ArrayList<$hookee<?>>();
			this.hookInWaitingList = new Stack<$hookee<?>>();
			// this.hookOutWaitingList = new Stack<VaporHookee<?>>();
			this.asyncHookOuts = new boolean[0];
	}

	/**
	 * Return a property from the settings for this hook, or a default value if
	 * the property is undefined in the settings or of incorrect type
	 * 
	 * @param propertyName
	 *            The property to retrieve a value for
	 * @param defaultValue
	 *            A default value that is returned if the property is undefined
	 *            in the settings or of incorrect type
	 * @return
	 */
	protected final <T> T prop(String propertyName, T defaultValue) {
		return $.prop(settings, propertyName, defaultValue);
	}

	/**
	 * Returns whether this Hook will be automatically deleted when there are no
	 * longer Hookees attached
	 * 
	 * @return whether this Hook will be automatically deleted when there are no
	 *         longer Hookees attached
	 */
	public boolean autoDelete() {
		return prop(AUTO_DELETE, __AUTO_DELETE);
	}

	/**
	 * Sets whether this Hook will be automatically deleted when there are no
	 * longer Hookees attached
	 * 
	 * NOTE: This operation may require adminAuth.
	 * 
	 * @param autoDelete
	 *            true to automatically delete this Hook when there are no
	 *            longer Hookees attached, false otherwise
	 * @return self
	 */
	public VaporHook autoDelete(boolean autoDelete) {
		return autoDelete(NO_PASS, autoDelete);
	}

	/**
	 * Sets whether this Hook will be automatically deleted when there are no
	 * longer Hookees attached
	 * 
	 * @param adminAuth
	 *            authorization for this operation
	 * @param autoDelete
	 *            true to automatically delete this Hook when there are no
	 *            longer Hookees attached, false otherwise
	 * @return self
	 */
	public VaporHook autoDelete(String adminAuth, boolean autoDelete) {
		if (adminAccess(adminAuth)) {
			settings.put(AUTO_DELETE, autoDelete);
			Log.i(shortName + ".autoDelete(String,boolean)",
					"AUTO_DELETE property updated to " + autoDelete + "!");
		} else {
			Log.w(shortName + ".autoDelete(String,boolean)",
					"AUTO_DELETE property not updated. Incorrect adminAuth supplied.");
		}
		return this;
	}

	/**
	 * Sets the Hooker object that will be informed when the hook is fired. The
	 * Hooker is used to affect and examine the args being sent out by the VHF
	 * to the Hookees of this Hook
	 * 
	 * @param hooker
	 *            the Hooker object that will be informed when the hook is fired
	 * @return self
	 */
	public VaporHook hooker($hooker hooker) {
		return hooker(NO_PASS, hooker);
	}

	/**
	 * Sets the Hooker object that will be informed when the hook is fired. The
	 * Hooker is used to affect and examine the args being sent out by the VHF
	 * to the Hookees of this Hook
	 * 
	 * @param adminAuth
	 *            the admin password that may be required to apply this change
	 * @param hooker
	 *            the Hooker object that will be informed when the hook is fired
	 * @return self
	 */
	public VaporHook hooker(String adminAuth, $hooker hooker) {
		if (adminAccess(adminAuth)) {
			this.hooker = hooker;
			Log.i(shortName + ".hooker(String,VaporHooker)", "Hooker updated!");
		} else {
			Log.w(shortName + ".hooker(String,VaporHooker)",
					"Hooker not updated. Incorrect adminAuth supplied.");
		}
		return this;
	}

	/**
	 * Verify whether the given admin auth matches the stored admin auth for
	 * this Hook
	 * 
	 * @param adminAuthOffering
	 *            the password to match against
	 * @return true if the offered admin auth matches the stored admin auth for
	 *         this Hook, false otherwise
	 */
	boolean adminAccess(String adminAuthOffering) {
		// check to see if the offered password is correct
		// NOTE: Package access so Engine can delete Hooks
		if (adminRestricted())
			return prop(ADMIN_AUTH, __ADMIN_AUTH).equals(adminAuthOffering);
		return true;
	}

	/**
	 * Verify whether the given subscriber auth matches the stored subscriber
	 * auth for this Hook
	 * 
	 * @param subscriberAuthOffering
	 *            the password to match against
	 * @return true if the offered subscriber auth matches the stored subscriber
	 *         auth for this Hook, false otherwise
	 */
	boolean subscriberAccess(String subscriberAuthOffering) {
		if (subscriberRestricted())
			return prop(SUBSCRIBER_AUTH, __SUBSCRIBER_AUTH).equals(
					subscriberAuthOffering);
		return true;
	}

	// fire the hook
	private void callback(VaporBundle args) {
		if (dead) {
			Log.e(shortName + ".callback(VaporBundle)",
					"Cannot fire a dead Hook!");
			return;
		}

		if (lockedList()) {
			Log.e(shortName + ".callback(VaporBundle)",
					"Attempting to fire a Hook that has already been fired!");
			return;
		}
		// Concurrent...
		lockList(Locker.callBack);

		// if first call
		if (callCount == 0) {
			// first time the hook is fired we check the license
			if(!$.licensed()){
				throw new VaporLicenseException();
			}
			else{
				++callCount;
				Log.i(shortName + ".callback(VaporBundle)", "First call!");
				args = hooker.firstCall(args);
			}
		}
		else ++callCount;

		args = hooker.pre(args);

		// async dead hookees, this lets us skip hookees that are later in the
		// callback list, but can't be actually
		// removed until the list is unlocked
		asyncHookOuts = new boolean[hookees.size()];
		int index = -1;

		while (hookeeIterator.hasNext()) {
			// NOTE: we have to advance the iterator regardless of whether we
			// are going to skip the item!!
			$hookee<?> hookee = hookeeIterator.next();

			// if this hookee is to be deleted, and has effectively been
			// asynchronously hookedOut from this hook
			// we skip it
			if (asyncHookOuts[++index])
				continue;

			if (hookee == null) {
				Log.w(shortName + ".callback(VaporBundle)",
						hookName
								+ " contains null observer. Did you forget to hookOut? Automatically hooking out...");
				__hookOut(hookee);
				continue;
			}
			/*
			 NOTE:  NOT CHECKING if hookee.subject == null because it could well be if developer uses parameterless constructor
			 to instantiate the hookee! Hooking this out when subject is null would remove these valid cases where subject will be null 
			 */
			

			VaporBundle bespokeArgs = hooker.each(hookee, args);

			hookee.call(hookName, bespokeArgs);

			// the hookee may prematurely ask to delete this hook. When this
			// happens
			// during a callback the abortAndDelete flag is set
			if (abortAndDelete)
				break;
		}
		hooker.post(args);

		unlockList(Locker.callBack);

		if (abortAndDelete) {
			abortAndDelete = false;
			__delete($.Bundle());
		}
	}

	/**
	 * Returns how many time this Hook has been fired
	 * 
	 * @return the number of times this Hook has been fired
	 */
	public int callCount() {
		return callCount;
	}

	/**
	 * Returns whether this Hook can be deleted.
	 * 
	 * NOTE: This operation may require adminAuth.
	 * 
	 * @return true if the Hook can be deleted, false otherwise
	 */
	public boolean deleteable() {
		return prop(DELETEABLE, __DELETEABLE);
	}

	/**
	 * Sets whether this Hook can be deleted.
	 * 
	 * NOTE: This operation may require adminAuth.
	 * 
	 * @param canDelete
	 *            Whether the Hook can be deleted
	 * @return self
	 */
	public VaporHook deleteable(boolean canDelete) {
		return deleteable(NO_PASS, canDelete);
	}

	/**
	 * Sets whether this Hook can be deleted.
	 * 
	 * @param adminAuth
	 *            the admin password for this Hook
	 * @param canDelete
	 *            Whether the Hook can be deleted
	 * @return self
	 */
	public VaporHook deleteable(String adminAuth, boolean canDelete) {
		if (adminAccess(adminAuth)) {
			settings.put(DELETEABLE, canDelete);
			Log.i(shortName + ".deleteable(String,boolean)",
					"DELETEABLE property updated to " + canDelete + "!");
		} else {
			Log.w(shortName + ".deleteable(String,boolean)",
					"DELETEABLE property not updated. Incorrect adminAuth supplied.");
		}
		return this;
	}

	/**
	 * Deletes this Hook.
	 * 
	 * NOTE: This operation may require adminAuth NOTE: Deleting a Hook raises
	 * the lastCall(...) notification, not call(...)
	 * 
	 * @return true if the Hook was deleted, false otherwise
	 */
	public boolean delete() {
		return delete(NO_PASS);
	}

	/**
	 * Deletes this Hook.
	 * 
	 * NOTE: Deleting a Hook raises the lastCall(...) notification, not
	 * call(...)
	 * 
	 * @param adminAuth
	 *            authorization for this operation
	 * @return true if the Hook was deleted, false otherwise
	 */
	public boolean delete(String adminAuth) {
		return delete(adminAuth, $.Bundle());
	}

	/**
	 * Deletes this Hook, and passes the given VaporBundle as args to the
	 * lastCall(...) notification sent to all Hookees prior to deletion.
	 * 
	 * NOTE: This operation may require adminAuth NOTE: Deleting a Hook raises
	 * the lastCall(...) notification, not call(...)
	 * 
	 * @param args
	 *            the args to pass to Hookees during the lastCall(...) event
	 * @return true if the Hook was deleted, false otherwise
	 */
	public boolean delete(VaporBundle args) {
		return delete(NO_PASS, args);
	}

	/**
	 * Deletes this Hook, and passes the given VaporBundle as args to the
	 * lastCall(...) notification sent to all Hookees prior to deletion.
	 * 
	 * NOTE: Deleting a Hook raises the lastCall(...) notification, not
	 * call(...)
	 * 
	 * @param adminAuth
	 *            authorization for this operation
	 * @param args
	 *            the args to pass to Hookees during the lastCall(...) event
	 * @return true if the Hook was deleted, false otherwise
	 */
	public boolean delete(String adminAuth, VaporBundle args) {
		// delete will NOT fire normal callback, only last calls
		if (adminAccess(adminAuth) && deleteable()) {

			return __delete(args);
		} else {
			Log.w(shortName + ".delete(String,VaporBundle)",
					"Hook not deleted. Ensure adminAuth supplied is correct, and that DELETEABLE is true.");
			return false;
		}
	}

	private final boolean __delete(VaporBundle args) {
		if (dead) {
			Log.w(shortName + ".__delete(VaporBundle)",
					"Attempting to delete a Hook that is already dead!");
			return true;
		}

		// The list will be locked if this method is invoked during callback
		if (lockedList()) {
			// in this case we set a flag which callback will use to exit early
			// and invoke delete(..) again
			// after unlocking the list
			abortAndDelete = true;
			return true;
		}

		lockList(Locker.delete);

		dead = true;

		args = hooker.lastCall(args);
		// Concurrent
		while (hookeeIterator.hasNext()) {
			$hookee<?> hookee = hookeeIterator.next();

			VaporBundle bespokeArgs = hooker.each(hookee, args);

			hookee.delete(hookName, bespokeArgs);
		}

		hookees.clear();
		hooker = null;

		// Leave list locked!

		Log.i(shortName + ".__delete(VaporBundle)", "Deleting...");
		VaporHookEngine.instance().delete(hookName);

		return true;
	}

	/**
	 * Fires the Hook.
	 * 
	 * NOTE: This operation may require adminAuth
	 * 
	 * @return true if the Hook was fired, false otherwise
	 */
	public boolean fire() {
		return fire(NO_PASS);
	}

	/**
	 * Fires the Hook and passes the given args to all Hookees in the call(...)
	 * notification.
	 * 
	 * NOTE: A Hooker may alter these args before they are passed to Hookees
	 * NOTE: This operation may require adminAuth
	 * 
	 * @param args
	 *            the args to pass to Hookees when this Hook is fired
	 * @return true if the Hook was fired, false otherwise
	 */
	public boolean fire(VaporBundle args) {
		return fire(NO_PASS, args);
	}

	/**
	 * Fires the Hook using the given admin auth.
	 * 
	 * @param adminAuth
	 *            authorization for this operation
	 * @return true if the Hook was fired, false otherwise
	 */
	public boolean fire(String adminAuth) {
		return fire(adminAuth, $.Bundle());
	}

	/**
	 * Fires the Hook using the given admin auth, and passes the given args to
	 * all Hookees in the call(...) notification.
	 * 
	 * NOTE: A Hooker may alter these args before they are passed to Hookees
	 * 
	 * @param adminAuth
	 *            authorization for this operation
	 * @param args
	 *            the args to pass to Hookees when this Hook is fired
	 * @return true if the Hook was fired, false otherwise
	 */
	public synchronized boolean fire(String adminAuth, VaporBundle args) {

		// if is restricted and password fails return
		if (!adminAccess(adminAuth)) {
			Log.w(shortName + ".fire(String,VaporBundle)",
					"Hook not fired. Incorrect adminAuth supplied.");
			return false; // adminAuth failed
		}

		Log.i(shortName + ".fire(String,VaporBundle)", "Firing " + hookName + " Hook!");
		// instruct the hook to fire
		callback(args);

		return true; // fired
	}

	/**
	 * Check whether the given Object is the $subject of a Hookee attached to
	 * this Hook
	 * 
	 * @param subscriber
	 *            the Object to check for in the Hookees attached to this hook
	 * @return true if the given Object is referenced in a Hookee attached to
	 *         this hook
	 */
	public boolean hooked(Object observer) {
		// Concurrent...
		for ($hookee<?> hookee : readOnlyList()) {

			Object o = hookee.subject();

			if (o == null)
				continue;

			if (o.equals(observer))
				return true;
		}
		return false;
	}

	/**
	 * Check whether the given Hookee is attached to this Hook
	 * 
	 * @param observer
	 *            the Hookee to check for
	 * @return true if the given Hookee is attached to this Hook, false
	 *         otherwise
	 */
	public <T> boolean hooked($hookee<T> observer) {
		return readOnlyList().contains(observer);
	}

	/**
	 * Register the given Hookee as a subscriber to this Hook
	 * 
	 * NOTE: This operation may require subscriberAuth
	 * 
	 * @param observer
	 *            the Hookee to register to this Hook
	 * @return self
	 */
	public final <T> VaporHook hookIn($hookee<T> observer) {
		hookIn(NO_PASS, observer);
		return this;
	}

	/**
	 * Register the given Hookee as a subscriber to this Hook using the given
	 * subscriber auth
	 * 
	 * @param subscriberAuth
	 *            authorization for this operation
	 * @param observer
	 *            the Hookee to register to this Hook
	 * @return self
	 */
	public synchronized final <T> VaporHook hookIn(String subscriberAuth,
			$hookee<T> observer) {

		// if is restricted and password fails return
		if (!subscriberAccess(subscriberAuth)) {
			Log.w(shortName + ".hookIn(String,VaporHookee<T>)",
					"HookIn failed. Incorrect subscriberAuth supplied. Hookee: "
							+ observer);
			return this;
		}

		// add observer
		// hookees.add(observer);

		// Async queue
		__hookIn(observer);
		// Log.i(logTag(), "Async HookIn requested! Hookee: " +
		// observer);

		return this;
	}

	/**
	 * Registers the given Hookees as subscribers to this Hook
	 * 
	 * NOTE: This operation may require subscriberAuth
	 * 
	 * @param observers
	 *            the Hookees to register to this Hook
	 * @return self
	 */
	public final VaporHook hookIn(ArrayList<$hookee<?>> observers) {
		hookIn(NO_PASS, observers);
		return this;
	}

	/**
	 * Registers the given Hookees as subscribers to this Hook
	 * 
	 * NOTE: This operation may require subscriberAuth
	 * 
	 * @param observers
	 *            the Hookees to register to this Hook
	 * @return self
	 */
	public final VaporHook hookIn($hookee<?>... observers) {
		hookIn(NO_PASS, observers);
		return this;
	}

	/**
	 * Registers the given Hookees as subscribers to this Hook
	 * 
	 * @param subscriberAuth
	 *            authorization for this operation
	 * @param observers
	 *            the Hookees to register to this Hook
	 * @return self
	 */
	public final VaporHook hookIn(String subscriberAuth,
			ArrayList<$hookee<?>> observers) {
		// Concurrent...
		for ($hookee<?> item : observers)
			hookIn(subscriberAuth, item);
		return this;
	}

	/**
	 * Registers the given Hookees as subscribers to this Hook
	 * 
	 * @param subscriberAuth
	 *            authorization for this operation
	 * @param observers
	 *            the Hookees to register to this Hook
	 * @return self
	 */
	public final VaporHook hookIn(String subscriberAuth,
			$hookee<?>... observers) {
		// Concurrent...
		for ($hookee<?> item : observers)
			hookIn(subscriberAuth, item);
		return this;
	}

	/**
	 * Unregister the given Hookee from this Hook
	 * 
	 * NOTE: This operation may require subscriberAuth
	 * 
	 * @param observer
	 *            the Hookee to unregister from this Hook
	 * @return self
	 */
	public final <T> VaporHook hookOut($hookee<T> observer) {
		hookOut(NO_PASS, observer);
		return this;
	}

	/**
	 * Unregister the given Hookee from this Hook
	 * 
	 * @param subscriberAuth
	 *            authorization for this operation
	 * @param observer
	 *            the Hookee to unregister from this Hook
	 * @return self
	 */
	public synchronized final <T> VaporHook hookOut(String subscriberAuth,
			$hookee<T> observer) {

		// if is restricted and password fails return
		if (!subscriberAccess(subscriberAuth)) {
			Log.e(shortName + ".hookOut(String,VaporHookee<T>)",
					"HookOut failed. Incorrect subscriberAuth supplied. Hookee: "
							+ observer);
			return this;
		}

		// Async queue
		__hookOut(observer);

		return this;
	}

	/**
	 * Unregisters the given Hookees from this Hook
	 * 
	 * NOTE: This operation may require subscriberAuth
	 * 
	 * @param observers
	 *            the Hookees to unregister from this Hook
	 * @return self
	 */
	public final VaporHook hookOut(ArrayList<$hookee<?>> observers) {
		hookOut(NO_PASS, observers);
		return this;
	}

	/**
	 * Unregisters the given Hookees from this Hook
	 * 
	 * NOTE: This operation may require subscriberAuth
	 * 
	 * @param observers
	 *            the Hookees to unregister from this Hook
	 * @return self
	 */
	public final VaporHook hookOut($hookee<?>... observers) {
		hookOut(NO_PASS, observers);
		return this;
	}

	/**
	 * Unregisters the given Hookees from this Hook
	 * 
	 * @param subscriberAuth
	 *            authorization for this operation
	 * @param observers
	 *            the Hookees to unregister from this Hook
	 * @return self
	 */
	public final VaporHook hookOut(String subscriberAuth,
			ArrayList<$hookee<?>> observers) {
		// Concurrent...
		for ($hookee<?> item : observers)
			hookOut(subscriberAuth, item);
		return this;
	}

	/**
	 * Unregisters the given Hookees from this Hook
	 * 
	 * @param subscriberAuth
	 *            authorization for this operation
	 * @param observers
	 *            the Hookees to unregister from this Hook
	 * @return self
	 */
	public final VaporHook hookOut(String subscriberAuth,
			$hookee<?>... observers) {
		// Concurrent...
		for ($hookee<?> item : observers)
			hookOut(subscriberAuth, item);
		return this;
	}

	/**
	 * Returns the number of Hookees currently attached to this Hook. NOTE:
	 * Because hooking in and out is asynchronous, this method will return the
	 * predicted size that will be accurate as of when the hookees list is next
	 * retrieved or used.
	 * 
	 * @return the number of Hookees currently attached to this Hook
	 */
	public int hookeeCount() {
		return readOnlyList().size();
	}

	/**
	 * Returns the name of this Hook
	 * 
	 * @return the name of this Hook
	 */
	public String name() {
		return hookName;
	}

	/**
	 * Returns a read-only list of Hookees currently subscribed to this Hook
	 * 
	 * NOTE: This operation may require adminAuth
	 * 
	 * @return the list of Hookees currently subscribed to this Hook
	 */
	public List<$hookee<?>> hookees() {
		return hookees(NO_PASS);
	}

	/**
	 * Returns a read-only list of Hookees currently subscribed to this Hook
	 * 
	 * @param adminAuth
	 *            authorization for this operation
	 * @return the list of Hookees currently subscribed to this Hook
	 */
	public List<$hookee<?>> hookees(String adminAuth) {
		if (adminAccess(adminAuth))
			return readOnlyList();

		Log.e(shortName + ".hookees(String)",
				"Hookees list denied. Incorrected adminAuth supplied.");
		return null;
	}

	/**
	 * Returns the settings for this Hook
	 * 
	 * @return the settings for this Hook
	 */
	VaporBundle settings() {
		return settings;
	}

	/**
	 * Returns whether this Hook has an admin auth password set
	 * 
	 * @return true if this Hook has an admin auth password set, false otherwise
	 */
	public boolean adminRestricted() {
		return !prop(ADMIN_AUTH, __ADMIN_AUTH).equals(NO_PASS);
	}

	/**
	 * Returns whether this Hook has an subscriber auth password set
	 * 
	 * @return true if this Hook has an subsciber auth password set, false
	 *         otherwise
	 */
	public boolean subscriberRestricted() {
		return !prop(SUBSCRIBER_AUTH, __SUBSCRIBER_AUTH).equals(NO_PASS);
	}

	public String toString() {
		String s = hookName + " has " + hookeeCount() + " observers.";
		// Concurrent...
		for ($hookee<?> h : readOnlyList()) {
			s += '\n' + h.subject().toString();
		}

		return s;

	}
}
