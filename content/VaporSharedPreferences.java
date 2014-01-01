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

package vapor.content;

import java.util.Map;
import java.util.Random;
import java.util.Set;

import vapor.core.$;
import vapor.core.hookframework.VaporHook;
import vapor.exception.VaporLicenseException;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

/**
 * A Singleton fluent Vapor implementation of SharedPreferences. This class
 * unifies the process of editing, and reading from, SharedPreferences to make
 * it easier to interact with preferences saved on disk. Changes can be saved
 * automatically for you, either asynchronously or as a blocking operation.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 */
public final class VaporSharedPreferences {
	// Singleton
	private static VaporSharedPreferences instance;

	private static final int VSP_MODE = Context.MODE_PRIVATE;

	protected SharedPreferences prefs;
	// NOTE: DO NOT use directly, use through edit()
	protected SharedPreferences.Editor editor;
	private String filename = ""; // current shared preferences file name
	private boolean autoSave = true, // whether changes to preferences are
										// automatically written back
			async = true, //
			dirtyEditor = true; // flag for when we need to retrieve a new
								// editor

	private static final String COMMIT_FAILURE = "commitFailure",
			
			// MUST MATCH PASSWORD IN VAPORHOOKENGINE createSystemHooks()
			VSP_HOOK_ADMIN = "ifosifw03fcs04fonwed3";
			//"ifosifw03fcs" + new Random().nextInt(20000) + "04fon" + new Random().nextInt(20000) + "wed3";

	// Singleton
	private VaporSharedPreferences() {
		if(!$.licensed()){
			throw new VaporLicenseException();
		}else{
			prefs = $.act().getPreferences(VSP_MODE);
			dirtyEditor = true;
			/*
			 * NOW BEING CREATED IN VAPORHOOKENGINE createSystemHooks() TO ENSURE
			 * THE HOOK IS CREATED BEFORE THE CLIENT CAN CREATE IT
			 * 
			$.hook(COMMIT_FAILURE,
				$.Bundle().put(VaporHook.ADMIN_AUTH, VSP_HOOK_ADMIN) // password
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
						
			*/
		}
	}

	/**
	 * This is called internally to autoSave changes (if enabled)
	 */
	private void _autoSave() {
		if (autoSave)
			save();
	}

	private SharedPreferences.Editor edit() {
		// if we need to retrieve a new editor
		if (dirtyEditor) {
			editor = prefs.edit();
			dirtyEditor = false;
		}
		return editor;
	}
	/*
	public String file(){
		return filename;
	}*/
	
	public VaporSharedPreferences file(String filename){
		file($.act().getSharedPreferences(this.filename = filename,VSP_MODE));
		return this;
	}
	
	public VaporSharedPreferences file(SharedPreferences sharedPreferences) {
		prefs = sharedPreferences;
		dirtyEditor = true; // ensures we get a new editor to perform writes
		return this;
	}

	/**
	 * Retrieves a Singleton instance of VaporSharedPreferences based on the
	 * SharedPreferences for the current Activity context
	 * 
	 * @return a Singleton instance of VaporSharedPreferences
	 */
	public static synchronized VaporSharedPreferences prefs() {
		if (instance == null)
			instance = new VaporSharedPreferences();

		return instance;
	}


	/**
	 * Returns whether changes to the shared preferences are automatically saved
	 * by Vapor
	 * 
	 * @return true if changes to the shared preferences are automatically saved
	 *         by Vapor
	 */
	public boolean autoSave() {
		return autoSave;
	}

	/**
	 * Sets whether changes to the shared preferences are automatically saved by
	 * Vapor
	 * 
	 * @param autoSave
	 *            true if changes to the shared preferences should be
	 *            automatically saved by Vapor
	 * @return self
	 */
	public VaporSharedPreferences autoSave(boolean autoSave) {
		this.autoSave = autoSave;
		return this;
	}

	/**
	 * Returns whether writes from the underlying editor are done asynchronously
	 * 
	 * @return true if writes from the underlying editor are done
	 *         asynchronously, false otherwise
	 */
	public boolean async() {
		return async;
	}

	/**
	 * Sets whether writes from the underlying editor are done asynchronously
	 * (apply()) or as a blocking operation (commit())
	 * 
	 * @param asynchronousWrites
	 *            true if writes from the underlying editor should be done
	 *            asynchronously
	 * @return self
	 */
	public VaporSharedPreferences async(boolean asynchronousWrites) {
		this.async = asynchronousWrites;
		return this;
	}

	/**
	 * Write any outstanding changes to disk. Note this will be done
	 * automatically by Vapor if autoSave is enabled. To alter whether writes
	 * are done asynchronously, or as a blocking operation, use async(boolean).
	 * 
	 * @return self
	 */
	public VaporSharedPreferences save() {
		if (async) {
			editor.apply();
		} else {
			if (!editor.commit()) {
				// If the commit fails fire the hook
				$.hook(COMMIT_FAILURE).fire(VSP_HOOK_ADMIN, $.Bundle());
			}
		}

		// invalidate the editor
		dirtyEditor = true;
		// NOTE: we don't set editor to null in case
		// it is in the middle of an async write

		return this;
	}

	/**
	 * Retrieve all values from the preferences. Note that you must not modify
	 * the collection returned by this method, or alter any of its contents. The
	 * consistency of your stored data is not guaranteed if you do.
	 * 
	 * @return a map containing a list of pairs key/value representing the
	 *         preferences
	 */
	public Map<String, ?> all() {
		return prefs.getAll();
	}

	/**
	 * Registers a callback to be invoked when a change happens to a preference
	 * 
	 * @param sharedPreferencesChangeListener
	 *            The callback that will run
	 * @return self
	 */
	public VaporSharedPreferences change(
			vapor.listeners.content.sharedpreferences.$change sharedPreferencesChangeListener) {
		prefs.registerOnSharedPreferenceChangeListener(sharedPreferencesChangeListener);
		return this;
	}

	/**
	 * Unregisters a previous callback
	 * 
	 * @param sharedPreferencesChangeListener
	 *            The callback that should be unregistered
	 * @return self
	 */
	public VaporSharedPreferences remove(
			OnSharedPreferenceChangeListener sharedPreferencesChangeListener) {
		prefs.unregisterOnSharedPreferenceChangeListener(sharedPreferencesChangeListener);
		return this;
	}

	/**
	 * Mark that a preference value should be removed, which will be done in the
	 * actual preferences once save() is called. Note that when committing back
	 * to the preferences, all removals are done first, regardless of whether
	 * you called remove before or after put methods on this editor.
	 * 
	 * @param key
	 *            The name of the preference to remove
	 * @return self
	 */
	public VaporSharedPreferences remove(String key) {
		edit().remove(key);
		_autoSave();
		return this;
	}

	/**
	 * Checks whether the preferences contains a preference
	 * 
	 * @param The
	 *            name of the preference to check
	 * @return true if the preference exists in the preferences, otherwise false
	 */
	public boolean contains(String key) {
		return prefs.contains(key);
	}

	/**
	 * Mark in the editor to remove all values from the preferences. If
	 * autoSave() is true, this change will automatically be written to disk.
	 * 
	 * @return self
	 */
	public VaporSharedPreferences clear() {
		edit().clear();
		_autoSave();
		return this;
	}

	/**
	 * Retrieve a boolean value from the preferences
	 * 
	 * @param key
	 *            the name of the preference to retrieve
	 * @param defaultValue
	 *            value to return if this preference does not exist
	 * @return the preference value if it exists, or defaultValue if it does not
	 */
	public boolean getBool(String key, boolean defaultValue) {
		return prefs.getBoolean(key, defaultValue);
	}

	/**
	 * Retrieve a float value from the preferences
	 * 
	 * @param key
	 *            the name of the preference to retrieve
	 * @param defaultValue
	 *            value to return if this preference does not exist
	 * @return the preference value if it exists, or defaultValue if it does not
	 */
	public float getFloat(String key, float defaultValue) {
		return prefs.getFloat(key, defaultValue);
	}

	/**
	 * Retrieve an int value from the preferences
	 * 
	 * @param key
	 *            the name of the preference to retrieve
	 * @param defaultValue
	 *            value to return if this preference does not exist
	 * @return the preference value if it exists, or defaultValue if it does not
	 */
	public int getInt(String key, int defaultValue) {
		return prefs.getInt(key, defaultValue);
	}

	/**
	 * Retrieve a long value from the preferences
	 * 
	 * @param key
	 *            the name of the preference to retrieve
	 * @param defaultValue
	 *            value to return if this preference does not exist
	 * @return the preference value if it exists, or defaultValue if it does not
	 */
	public float getLong(String key, long defaultValue) {
		return prefs.getLong(key, defaultValue);
	}

	/**
	 * Retrieve a String value from the preferences
	 * 
	 * @param key
	 *            the name of the preference to retrieve
	 * @param defaultValue
	 *            value to return if this preference does not exist
	 * @return the preference value if it exists, or defaultValue if it does not
	 */
	public String getString(String key, String defaultValue) {
		return prefs.getString(key, defaultValue);
	}

	/**
	 * Retrieve a set of String values from the preferences
	 * 
	 * @param key
	 *            the name of the preference to retrieve
	 * @param defaultValue
	 *            value to return if this preference does not exist
	 * @return the preference value if it exists, or defaultValue if it does not
	 */
	public Set<String> getStrings(String key, Set<String> defaultValues) {
		return prefs.getStringSet(key, defaultValues);
	}

	/**
	 * Set a boolean value in the preferences. If autoSave() is true, this
	 * change will automatically be written to disk.
	 * 
	 * @param key
	 *            the name of the preference to modify
	 * @param value
	 *            the new value for the preference
	 * @return self
	 */
	public VaporSharedPreferences put(String key, boolean value) {
		edit().putBoolean(key, value);
		_autoSave();
		return this;
	}

	/**
	 * Set a float value in the preferences. If autoSave() is true, this change
	 * will automatically be written to disk.
	 * 
	 * @param key
	 *            the name of the preference to modify
	 * @param value
	 *            the new value for the preference
	 * @return self
	 */
	public VaporSharedPreferences put(String key, float value) {
		edit().putFloat(key, value);
		_autoSave();
		return this;
	}

	/**
	 * Set an int value in the preferences. If autoSave() is true, this change
	 * will automatically be written to disk.
	 * 
	 * @param key
	 *            the name of the preference to modify
	 * @param value
	 *            the new value for the preference
	 * @return self
	 */
	public VaporSharedPreferences put(String key, int value) {
		edit().putInt(key, value);
		_autoSave();
		return this;
	}

	/**
	 * Set a long value in the preferences. If autoSave() is true, this change
	 * will automatically be written to disk.
	 * 
	 * @param key
	 *            the name of the preference to modify
	 * @param value
	 *            the new value for the preference
	 * @return self
	 */
	public VaporSharedPreferences put(String key, long value) {
		edit().putLong(key, value);
		_autoSave();
		return this;
	}

	/**
	 * Set a String value in the preferences. If autoSave() is true, this change
	 * will automatically be written to disk.
	 * 
	 * @param key
	 *            the name of the preference to modify
	 * @param value
	 *            the new value for the preference
	 * @return self
	 */
	public VaporSharedPreferences put(String key, String value) {
		edit().putString(key, value);
		_autoSave();
		return this;
	}

	/**
	 * Set a set of String values in the preferences. If autoSave() is true,
	 * this change will automatically be written to disk.
	 * 
	 * @param key
	 *            the name of the preference to modify
	 * @param value
	 *            the new value for the preference
	 * @return self
	 */
	public VaporSharedPreferences put(String key, Set<String> value) {
		edit().putStringSet(key, value);
		_autoSave();
		return this;
	}

	/**
	 * Retrieve the underlying SharedPreferences object
	 * 
	 * @return the underlying SharedPreferences object
	 */
	public SharedPreferences sharedPreferences() {
		return prefs;
	}
}
