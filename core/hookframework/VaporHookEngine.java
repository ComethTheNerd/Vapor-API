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

import java.util.Collection;
import java.util.Hashtable;

import vapor.core.$;
import vapor.os.VaporBundle;
import android.util.Log;

/**
 * A Singleton class used to provide a central component for the management of
 * all VaporHooks in the application
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 */
public final class VaporHookEngine {
	// singleton
	private static VaporHookEngine instance;

	private static final String LOG_TAG = "VaporHookEngine",
			VHE_HOOK_AUTH = "rgspvjposrjgpovms";
	public static final String VHE_EVENTS = "VHE_EVENTS", EVENT = "event",
			HOOK = "hook";
	public static final int HOOK_CREATED = 1, HOOK_DELETED = 2;

	private static VaporBundle baseEventArgs; // any general args to send when
												// events fire

	// List of Hooks
	private Hashtable<String, VaporHook> hooks;

	private String logTag() {
		return LOG_TAG;
	}

	// Singleton private constructor
	private VaporHookEngine() {
		Log.i(logTag(), "Initialized.");
		hooks = new Hashtable<String, VaporHook>();
		baseEventArgs = $.Bundle(); // put here any general args

		// Make sure we create system hooks here so there is no chance clients
		// can create them first!!
		createSystemHooks();
	}

	private final void createSystemHooks() {

		// VaporActivity
		
		// MUST MATCH THE PASSWORD STORED IN VAPOR ACTIVITY
		String actHookAdmin = "b45ighepv0hs[032dsfscqwq";

		VaporBundle settings = $.Bundle()
				.put(VaporHook.ADMIN_AUTH, actHookAdmin) // password protect
															// against admin
															// changes
															// to these hooks
				.put(VaporHook.AUTO_DELETE, false) // these hooks will not be
													// auto
													// deleted when hookeeCount
													// drops to 0
				.put(VaporHook.DELETEABLE, false) // these hooks CANNOT be
													// deleted
		;
		String CREATE = "actCreate", START = "actStart", STOP = "actStop", PAUSE = "actPause", RESUME = "actResume", DESTROY = "actDestroy", RESTART = "actRestart", CONFIG_CHANGED = "actConfigChanged", LOW_MEM = "actLowMemory", ORIENTATION = "orientation", ORIENTATION_PORTRAIT = "orientationPortrait", ORIENTATION_LANDSCAPE = "orientationLandscape", DEVICE_TABLET = "deviceTablet", DEVICE_PHONE = "devicePhone", VIEW_READY = "actViewReady", SERVICE_BIND = "actService", SERVICE_UNBIND = "actServiceUnbind";
		// this creates and stores PERSISTENT hooks
		// Life cycle events
		hook(CREATE, settings);
		hook(START, settings);
		hook(STOP, settings);
		hook(PAUSE, settings);
		hook(RESUME, settings);
		hook(DESTROY, settings);
		hook(RESTART, settings);

		// Device callbacks
		hook(DEVICE_PHONE, settings);
		hook(DEVICE_TABLET, settings);

		// App callbacks
		hook(CONFIG_CHANGED, settings);
		hook(LOW_MEM, settings);
		// Orientation callbacks
		hook(ORIENTATION, settings);
		hook(ORIENTATION_PORTRAIT, settings);
		hook(ORIENTATION_LANDSCAPE, settings);
		hook(SERVICE_BIND, settings);// callbacks for services

		
		hook(VIEW_READY, $.Bundle()
				.put(VaporHook.ADMIN_AUTH,actHookAdmin) 	// password protect
															// against admin changes
															// to these hooks
				.put(VaporHook.AUTO_DELETE, false) 			// these hooks will not be auto
															// deleted when hookeeCount
															// drops to 0
				.put(VaporHook.DELETEABLE, true) 			// this hook is deleted when fired
				);
		
		// VaporService
		// MUST MATCH THE PASSWORD STORED IN VAPOR SERVICE
		String serviceHookAdmin = "2390r0d9uxjwi12pwskomx4=f0rce-d";
		String SERVICE_EVENT = "serviceEvent";
		hook(SERVICE_EVENT, $.Bundle().put(VaporHook.ADMIN_AUTH, serviceHookAdmin));
		
		// SharedPreferences
		String vspHookAdmin = "ifosifw03fcs04fonwed3";
		String COMMIT_FAILURE = "commitFailure";
		hook(COMMIT_FAILURE,
				$.Bundle().put(VaporHook.ADMIN_AUTH, vspHookAdmin) // password
																		// protect
																		// against
																		// admin
																		// changes
																		// to
																		// these
																		// hooks
						.put(VaporHook.AUTO_DELETE, false) // these hooks will
															// not be auto
															// deleted when
															// hookeeCount drops
															// to 0
						.put(VaporHook.DELETEABLE, false));
		// $
		String $hookAdmin  = "wpficpo430c-334nr4nfcp3a22e";
		String CONTEXT_CHANGED = "contextChanged";
		
		VaporBundle settings2 = $.Bundle()
				.put(VaporHook.ADMIN_AUTH, $hookAdmin) // password protect against
															// admin changes to
															// these hooks
				.put(VaporHook.AUTO_DELETE, false) // these hooks will not be auto
													// deleted when hookeeCount
													// drops to 0
				.put(VaporHook.DELETEABLE, false) // these hooks CANNOT be deleted
		;
		
		hook(CONTEXT_CHANGED,settings2);
		
		
	}

	// example usage
	private final void event_CreatedHook(String hookName) {
		/*
		 * $.hook(VHE_EVENTS).fire(VHE_HOOK_AUTH, baseEventArgs.put(EVENT,
		 * HOOK_CREATED).put(HOOK, hookName));
		 */
	}

	private final void event_DeletedHook(String hookName) {
		/*
		 * $.hook(VHE_EVENTS).fire(VHE_HOOK_AUTH, baseEventArgs.put(EVENT,
		 * HOOK_DELETED).put(HOOK, hookName));
		 */
	}

	/**
	 * Returns the number of Hooks currently live in the VaporHookEngine
	 * 
	 * @return the number of Hooks currently live in the VaporHookEngine
	 */
	public int count() {
		return hooks.size();
	}

	// package access
	void delete(String hookName) {
		if (exists(hookName)) {
			hooks.remove(hookName);
			Log.i(logTag(), "Hook: " + hookName + " deleted!");
			Log.i(logTag(), "Hook count: " + count());

			// alert any interested listeners
			event_DeletedHook(hookName);

			return;
		}
		Log.w(logTag(), "Cannot delete " + hookName + ". Hook does not exist!");
	}

	/**
	 * Checks whether there is a hook mapped to the given name
	 * 
	 * @param hookName
	 * @return true if a hook mapped to the given name exists in the VHE, false
	 *         otherwise
	 */
	public boolean exists(String hookName) {
		return hooks.containsKey(hookName);
	}

	public Collection<VaporHook> all() {
		return hooks.values();
	}

	/**
	 * Retrieves the hook identified by the given name, creating it first with
	 * default settings if the hook does not already exist.
	 * 
	 * @param hookName
	 *            The name of the hook to retrieve
	 * @return The VaporHook mapped to the given name
	 */
	public VaporHook hook(String hookName) {
		// retrieve it if exists already
		if (exists(hookName))
			return hooks.get(hookName);
		// create and retrieve it if not
		else
			return addHook(hookName, new VaporHook(hookName));
	}

	/**
	 * Attempts to create and retrieve a new hook using the supplied settings,
	 * overwriting any hook that was previously mapped to the given name. If the
	 * previously mapped hook is adminRestricted, and the adminAuth in the
	 * supplied settings is invalid, this will fail and return null
	 * 
	 * @param hookName
	 *            The name of the hook to retrieve
	 * @param settings
	 *            The new settings that will be used to create the hook
	 * @return The newly created VaporHook mapped to the given name, or null if
	 *         a previously mapped hook could not be overwritten
	 */
	public VaporHook hook(String hookName, VaporBundle settings) {
		return hook(new VaporHook(hookName, settings));
	}

	/**
	 * Retrieves the hook identified by the given name, creating it first with
	 * default settings if the hook does not already exist.
	 * 
	 * @param hookName
	 *            The name of the hook to retrieve
	 * @return The VaporHook mapped to the given name
	 */

	/**
	 * Returns a VaporXHook instance encapsulating the hooks identified by the
	 * given hookNames, creating any first with default settings that do not
	 * already exist.
	 * 
	 * @param hookNames
	 *            The name of the hooks to retrieve
	 * @return a VaporXHook instance encapsulating the hooks identified by the
	 *         given hookNames
	 */
	public VaporXHook hook(String... hookNames) {
		return new VaporXHook(hookNames);
	}

	/**
	 * Returns a VaporXHook instance encapsulating the hooks identified by the
	 * given hookItems, creating any first with default settings that do not
	 * already exist. The items must be instances of acceptable types, namely
	 * VaporHook or String
	 * 
	 * @param hookItems
	 *            The items that pertain to hooks
	 * @return a VaporXHook instance encapsulating the hooks identified by the
	 *         given hookItems
	 */
	public VaporXHook hook(Object... hookItems) {
		return new VaporXHook(hookItems);
	}

	/**
	 * Returns a VaporXHook instance encapsulating the given hooks.
	 * 
	 * @param hooks
	 *            The hooks to encapsulate
	 * @return a VaporXHook instance encapsulating the given hooks
	 */
	public VaporXHook hook(VaporHook... hooks) {
		return new VaporXHook(hooks);
	}

	/**
	 * Attempts to create and retrieve a new hook in the VHE, overwriting any
	 * hook that was previously mapped to the hook's name. If the previously
	 * mapped hook is adminRestricted, and the adminAuth in the new hook's
	 * settings is invalid, this will fail and return null
	 * 
	 * @param newHook
	 *            The new hook
	 * @return The hook newly mapped to the given name, or null if a previously
	 *         mapped hook could not be overwritten
	 */
	private VaporHook hook(VaporHook hook) {
		// get the hook name
		String hookName = hook.name();

		// if the hook exists
		if (exists(hookName)) {
			VaporBundle settings = hook.settings();
			// check we have permission to delete it
			if (!hooks.get(hookName).delete(
					settings.getString(VaporHook.ADMIN_AUTH))) {
				// NOTE: logging will be done inside the Hook

				// not authorized to delete the hook
				return null;
			}
			// we have permission to overwrite the hook
			else
				hook = addHook(hookName, hook);
		}
		// the hook does not exist already so create it in the VHE
		else
			hook = addHook(hookName, hook);

		// return the newly created, or newly overwritten hook
		return hook;
	}

	private VaporHook addHook(String hookName, VaporHook hook) {
		hooks.put(hookName, hook);
		Log.i(logTag(), "New Hook created! Hook: " + hookName);
		Log.i(logTag(), "Hook count: " + count());

		// alert any interested listeners
		event_CreatedHook(hookName);

		return hooks.get(hookName);
	}

	/**
	 * Retrieve the Singleton instance of the VaporHookEngine
	 * 
	 * @return the Singleton instance of the VaporHookEngine
	 */
	public static synchronized VaporHookEngine instance() {
		if (instance == null) {
			instance = new VaporHookEngine();

			// create hook that will fire whenever an event of interest occurs
			// NOTE: cannot go in constructor, as will StackOverflow on
			// instance(), so
			$.hook(VHE_EVENTS,
					$.Bundle().put(VaporHook.ADMIN_AUTH, VHE_HOOK_AUTH)
							.put(VaporHook.DELETEABLE, false)
							.put(VaporHook.AUTO_DELETE, false));
		}
		return instance;
	}

	// Generates a guaranteed unique name for a hook. Use this to prevent
	// overwrites:
	// $.hook(uniqueName("myHook")) << to create a new unique hook
	/**
	 * Generates a name for a hook based on the proposed name supplied, and
	 * guaranteed to be unique at the point of invocation. This is useful to
	 * avoid the unintentional overwriting of hooks that already exist.
	 * 
	 * @param proposedHookName
	 *            the proposed name to base the uniquely generated new name on
	 * @return A unique hook name that is based on the proposed hook name
	 */
	public String uniqueName(String proposedHookName) {
		return exists(proposedHookName) ? proposedHookName + ++gen
				: proposedHookName;
	}

	// used to generate unique names
	private int gen = 0;

	public String toString() {
		String content = "VHE contains " + count() + " Hooks:";

		for (VaporHook hook : $.hooks().all()) {

			content += '\n' + hook.toString();
		}
		return content;
	}

}
