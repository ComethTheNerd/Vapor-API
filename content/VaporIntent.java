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

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;

import org.xmlpull.v1.XmlPullParser;

import vapor.core.$;
import vapor.exception.VaporLicenseException;
import vapor.os.VaporBundle;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;

public class VaporIntent<T extends Intent, self extends VaporIntent<T, self>> {
	protected final String shortName = $.shortName(this);
	protected T intent;

	public VaporIntent(Context packageContext, Class<?> cls) {
		if(!$.licensed()){
			throw new VaporLicenseException();
		}else{
			intent = (T) new Intent(packageContext, cls);
		}
	}

	public VaporIntent(T intent) {
		if(!$.licensed()){
			throw new VaporLicenseException();
		}else{
			this.intent = intent;
		}
	}

	/**
	 * Retrieve the general action to be performed, such as ACTION_VIEW. The
	 * action describes the general way the rest of the information in the
	 * intent should be interpreted -- most importantly, what to do with the data
	 * returned by getData().
	 * 
	 * @return The action of this intent or null if none is specified.
	 */
	public String action() {
		return intent.getAction();
	}

	/**
	 * Set the general action to be performed.
	 * 
	 * @param action
	 *            An action name, such as ACTION_VIEW. Application-specific
	 *            actions should be prefixed with the vendor's package name.
	 * @return self
	 */
	public self action(String action) {
		intent.setAction(action);
		return (self) this;
	}

	/**
	 * Retrieve the application package name this Intent is limited to. When
	 * resolving an Intent, if non-null this limits the resolution to only
	 * components in the given application package.
	 * 
	 * @return The name of the application package for the Intent.
	 */
	public String appPackage() {
		return intent.getPackage();
	}

	/**
	 * (Usually optional) Set an explicit application package name that limits
	 * the components this Intent will resolve to. If left to the default value
	 * of null, all components in all applications will considered. If non-null,
	 * the Intent can only match the components in the given application package.
	 * 
	 * @param packageName
	 *            The name of the application package to handle the intent, or
	 *            null to allow any application package.
	 * @return self
	 */
	public self appPackage(String packageName) {
		intent.setPackage(packageName);
		return (self) this;
	}

	/**
	 * Add a new category to the intent. Categories provide additional detail
	 * about the action the intent performs. When resolving an intent, only
	 * activities that provide all of the requested categories will be used.
	 * 
	 * @param category
	 *            The desired category. This can be either one of the predefined
	 *            Intent categories, or a custom category in your own namespace.
	 * @return self
	 */
	public self category(String category) {
		intent.addCategory(category);
		return (self) this;
	}

	/**
	 * Return the set of all categories in the intent. If there are no
	 * categories, returns NULL.
	 * 
	 * @return The set of categories you can examine. Do not modify!
	 */
	public Set<String> categories() {
		return intent.getCategories();
	}

	/**
	 * Check if a category exists in the intent.
	 * 
	 * @param category
	 *            the category to check
	 * @return True if the intent contains the category, else false.
	 */
	public boolean categorized(String category) {
		return intent.hasCategory(category);
	}

	/**
	 * Convenience function for creating a ACTION_CHOOSER Intent.
	 * 
	 * Builds a new ACTION_CHOOSER Intent that wraps the given target intent,
	 * also optionally supplying a title. If the target intent has specified
	 * FLAG_GRANT_READ_URI_PERMISSION or FLAG_GRANT_WRITE_URI_PERMISSION, then
	 * these flags will also be set in the returned chooser intent, with its
	 * ClipData set appropriately: either a direct reflection of getClipData()
	 * if that is non-null, or a new ClipData build from getData().
	 * 
	 * @param target
	 *            The Intent that the user will be selecting an activity to
	 *            perform.
	 * @param title
	 *            Optional title that will be displayed in the chooser.
	 * @return Return a new VaporIntent object that you can hand to
	 *         Context.startActivity() and related methods.
	 */
	public static VaporIntent<? extends Intent, ?> chooser(
			VaporIntent<? extends Intent, ?> target, CharSequence title) {
		return $.Intent(Intent.createChooser(target.intent(), title));

	}

	/**
	 * Sets the ClassLoader that will be used when unmarshalling any Parcelable
	 * values from the extras of this Intent.
	 * 
	 * @param classLoader
	 *            a ClassLoader, or null to use the default loader at the time
	 *            of unmarshalling.
	 * @return self
	 */
	public self classLoader(ClassLoader classLoader) {
		intent.setExtrasClassLoader(classLoader);
		return (self) this;
	}

	/**
	 * Return the ClipData associated with this Intent. If there is none,
	 * returns null. See setClipData(ClipData) for more information.
	 * 
	 * @return
	 */
	public ClipData clipData() {
		return intent.getClipData();
	}

	/**
	 * Set a ClipData associated with this Intent. This replaces any previously
	 * set ClipData.
	 * 
	 * @param clipData
	 *            The new clip to set. May be null to clear the current clip.
	 * @return self
	 */
	public self clipData(ClipData clipData) {
		intent.setClipData(clipData);
		return (self) this;
	}

	/**
	 * Make a clone of only the parts of the Intent that are relevant for filter
	 * matching: the action, data, type, component, and categories.
	 * 
	 * @return self
	 */
	public self cloneFilter() {
		intent.cloneFilter();
		return (self) this;
	}

	/**
	 * Retrieve the concrete component associated with the intent. When
	 * receiving an intent, this is the component that was found to best handle
	 * it (that is, yourself) and will always be non-null; in all other cases it
	 * will be null unless explicitly set.
	 * 
	 * @return The name of the application component to handle the intent.
	 */
	public ComponentName component() {
		return intent.getComponent();
	}

	/**
	 * (Usually optional) Explicitly set the component to handle the intent. If
	 * left with the default value of null, the system will determine the
	 * appropriate class to use based on the other fields (action, data, type,
	 * categories) in the Intent. If this class is defined, the specified class
	 * will always be used regardless of the other fields. You should only set
	 * this value when you know you absolutely want a specific class to be used;
	 * otherwise it is better to let the system find the appropriate class so
	 * that you will respect the installed applications and user preferences.
	 * 
	 * @param componentName
	 *            The name of the application component to handle the intent, or
	 *            null to let the system find one for you.
	 * @return self
	 */
	public self component(ComponentName componentName) {
		intent.setComponent(componentName);
		return (self) this;
	}

	/**
	 * Retrieve data this intent is operating on. This URI specifies the name of
	 * the data; often it uses the content: scheme, specifying data in a content
	 * provider. Other schemes may be handled by specific activities, such as
	 * http: by the web browser.
	 * 
	 * @return The URI of the data this intent is targeting or null.
	 */
	public Uri data() {
		return intent.getData();
	}

	/**
	 * Set the data this intent is operating on. This method automatically
	 * clears any type that was previously set by type(String) or
	 * typeAndNormalize(String).
	 * 
	 * @param data
	 *            The Uri of the data this intent is now targeting.
	 * @return self
	 */
	public self data(Uri data) {
		data(data, false);
		return (self) this;
	}

	/**
	 * Set the data this intent is operating on. This method automatically
	 * clears any type that was previously set by type(String) or
	 * typeAndNormalize(String).
	 * 
	 * @param data
	 *            The Uri of the data this intent is now targeting.
	 * @param normalize
	 *            true to normalize the data this intent is operating on.
	 * @return self
	 */
	public self data(Uri data, boolean normalize) {
		if (normalize)
			intent.setDataAndNormalize(data);
		else
			intent.setData(data);
		return (self) this;
	}

	/**
	 * (Usually optional) Set the data for the intent along with an explicit
	 * MIME data type. This method should very rarely be used -- it allows you
	 * to override the MIME type that would ordinarily be inferred from the data
	 * with your own type given here.
	 * 
	 * @param data
	 *            The Uri of the data this intent is now targeting.
	 * @param type
	 *            The MIME type of the data being handled by this intent.
	 * @return self
	 */
	public self dataAndType(Uri data, String type) {
		dataAndType(data, type, false);
		return (self) this;
	}

	/**
	 * (Usually optional) Set the data for the intent along with an explicit
	 * MIME data type. This method should very rarely be used -- it allows you
	 * to override the MIME type that would ordinarily be inferred from the data
	 * with your own type given here.
	 * 
	 * @param data
	 *            The Uri of the data this intent is now targeting.
	 * @param type
	 *            The MIME type of the data being handled by this intent.
	 * @param normalize
	 *            true to normalize the data Uri and an explicit MIME data type
	 * @return self
	 */
	public self dataAndType(Uri data, String type, boolean normalize) {
		if (normalize)
			intent.setDataAndTypeAndNormalize(data, type);
		else
			intent.setDataAndType(data, type);
		return (self) this;
	}

	/**
	 * The same as data(), but returns the URI as an encoded String.
	 * 
	 * @return
	 */
	public String dataString() {
		return intent.getDataString();
	}

	/**
	 * Describe the kinds of special objects contained in this Parcelable's
	 * marshalled representation.
	 * 
	 * @return a bitmask indicating the set of special object types marshalled
	 *         by the Parcelable.
	 */
	public int describe() {
		return intent.describeContents();
	}

	/**
	 * Erases all extras in the Intent.
	 * 
	 * @return self
	 */
	public self clearExtras() {
		replace((VaporBundle) null);
		return (self) this;
	}

	/**
	 * Returns true if an extra value is associated with the given name.
	 * 
	 * @param name
	 *            the extra's name
	 * @return true if the given extra is present.
	 */
	public boolean contains(String name) {
		return intent.hasExtra(name);
	}

	/**
	 * Retrieves a map of extended data from the intent
	 * 
	 * @return the map of all extras previously added with put(VaporBundle), or
	 *         null if none have been added.
	 */
	public VaporBundle extras() {
		return new VaporBundle(intent.getExtras());
	}

	/**
	 * Returns true if the Intent's extras contain a parcelled file descriptor.
	 * 
	 * @return true if the Intent contains a parcelled file descriptor.
	 */
	public boolean fileDesc() {
		return intent.hasFileDescriptors();
	}

	/**
	 * Determine if two intents are the same for the purposes of intent
	 * resolution (filtering). That is, if their action, data, type, class, and
	 * categories are the same. This does not compare any extra data included in
	 * the intents.
	 * 
	 * @param compareTo
	 *            The other Intent to compare against.
	 * @return Returns true if action, data, type, class, and categories are the
	 *         same.
	 */
	public boolean filterEq(VaporIntent compareTo) {
		return intent.filterEquals(compareTo.intent());
	}

	/**
	 * Generate hash code that matches semantics of filterEquals().
	 * 
	 * @return Returns the hash value of the action, data, type, class, and
	 *         categories.
	 */
	public int filterHash() {
		return intent.filterHashCode();
	}

	/**
	 * Retrieve any special flags associated with this intent. You will normally
	 * just set them with setFlags(int) and let the system take the appropriate
	 * action with them.
	 * 
	 * @return The currently set flags.
	 */
	public int flags() {
		return intent.getFlags();
	}

	/**
	 * Add additional flags to the intent (or with existing flags value).
	 * 
	 * @param flags
	 *            The new flags to set.
	 * @return self
	 */
	public self flags(int flags) {
		intent.addFlags(flags);
		return (self) this;
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @param defaultValue
	 *            the value to be returned if no value of the desired type is
	 *            stored with the given name.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public boolean getBool(String name, boolean defaultValue) {
		return intent.getBooleanExtra(name, defaultValue);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public boolean[] getBools(String name) {
		return intent.getBooleanArrayExtra(name);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public VaporBundle getBundle(String name) {
		return new VaporBundle(intent.getBundleExtra(name));
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @param defaultValue
	 *            the value to be returned if no value of the desired type is
	 *            stored with the given name.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public byte getByte(String name, byte defaultValue) {
		return intent.getByteExtra(name, defaultValue);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public byte[] getBytes(String name) {
		return intent.getByteArrayExtra(name);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @param defaultValue
	 *            the value to be returned if no value of the desired type is
	 *            stored with the given name.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public char getChar(String name, char defaultValue) {
		return intent.getCharExtra(name, defaultValue);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public char[] getChars(String name) {
		return intent.getCharArrayExtra(name);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public CharSequence getCharSeq(String name) {
		return intent.getCharSequenceExtra(name);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public CharSequence[] getCharSeqs(String name) {
		return intent.getCharSequenceArrayExtra(name);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public ArrayList<CharSequence> getCharSeqList(String name) {
		return intent.getCharSequenceArrayListExtra(name);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @param defaultValue
	 *            the value to be returned if no value of the desired type is
	 *            stored with the given name.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public double getDouble(String name, double defaultValue) {
		return intent.getDoubleExtra(name, defaultValue);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public double[] getDoubles(String name) {
		return intent.getDoubleArrayExtra(name);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @param defaultValue
	 *            the value to be returned if no value of the desired type is
	 *            stored with the given name.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public float getFloat(String name, float defaultValue) {
		return intent.getFloatExtra(name, defaultValue);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public float[] getFloats(String name) {
		return intent.getFloatArrayExtra(name);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @param defaultValue
	 *            the value to be returned if no value of the desired type is
	 *            stored with the given name.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public int getInt(String name, int defaultValue) {
		return intent.getIntExtra(name, defaultValue);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public int[] getInts(String name) {
		return intent.getIntArrayExtra(name);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public ArrayList<Integer> getIntList(String name) {
		return intent.getIntegerArrayListExtra(name);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @param defaultValue
	 *            the value to be returned if no value of the desired type is
	 *            stored with the given name.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public long getLong(String name, long defaultValue) {
		return intent.getLongExtra(name, defaultValue);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public long[] getLongs(String name) {
		return intent.getLongArrayExtra(name);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @param defaultValue
	 *            the value to be returned if no value of the desired type is
	 *            stored with the given name.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public <P extends Parcelable> P getParcel(String name) {
		return intent.getParcelableExtra(name);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public Parcelable[] getParcels(String name) {
		return intent.getParcelableArrayExtra(name);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public <P extends Parcelable> ArrayList<P> getParcelList(String name) {
		return intent.getParcelableArrayListExtra(name);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @param defaultValue
	 *            the value to be returned if no value of the desired type is
	 *            stored with the given name.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public Serializable getSerial(String name) {
		return intent.getSerializableExtra(name);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @param defaultValue
	 *            the value to be returned if no value of the desired type is
	 *            stored with the given name.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public short getShort(String name, short defaultValue) {
		return intent.getShortExtra(name, defaultValue);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public short[] getShorts(String name) {
		return intent.getShortArrayExtra(name);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @param defaultValue
	 *            the value to be returned if no value of the desired type is
	 *            stored with the given name.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public String getString(String name, String defaultValue) {
		return intent.getStringExtra(name);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public String[] getStrings(String name) {
		return intent.getStringArrayExtra(name);
	}

	/**
	 * Retrieve extended data from the intent.
	 * 
	 * @param name
	 *            The name of the desired item.
	 * @return the value of an item that previously added with put(...) or null
	 *         if no boolean array value was found.
	 */
	public ArrayList<String> getStringList(String name) {
		return intent.getStringArrayListExtra(name);
	}

	public T intent() {
		return intent;
	}

	/**
	 * Create an intent to launch the main (root) activity of a task. This is
	 * the Intent that is started when the application's is launched from Home.
	 * For anything else that wants to launch an application in the same way, it
	 * is important that they use an Intent structured the same way, and can use
	 * this function to ensure this is the case.
	 * 
	 * @param mainActivity
	 *            The main activity component that this Intent will launch.
	 * @return Returns a newly created Intent that can be used to launch the
	 *         activity as a main application entry.
	 */
	public static VaporIntent<? extends Intent, ?> makeMain(
			ComponentName mainActivity) {
		return $.Intent(Intent.makeMainActivity(mainActivity));
	}

	/**
	 * Make an Intent for the main activity of an application, without
	 * specifying a specific activity to run but giving a selector to find the
	 * activity. This results in a final Intent that is structured the same as
	 * when the application is launched from Home. For anything else that wants
	 * to launch an application in the same way, it is important that they use an
	 * Intent structured the same way, and can use this function to ensure this
	 * is the case.
	 * 
	 * @param selectorAction
	 *            The action name of the Intent's selector.
	 * @param selectorCategory
	 *            The name of a category to add to the Intent's selector.
	 * @return Returns a newly created Intent that can be used to launch the
	 *         activity as a main application entry.
	 */
	public static VaporIntent<? extends Intent, ?> makeSelector(
			String selectorAction, String selectorCategory) {
		return $.Intent(Intent.makeMainSelectorActivity(selectorAction,
				selectorCategory));
	}

	/**
	 * Make an Intent that can be used to re-launch an application's task in its
	 * base state. This is like makeMain(ComponentName), but also sets the flags
	 * FLAG_ACTIVITY_NEW_TASK and FLAG_ACTIVITY_CLEAR_TASK.
	 * 
	 * @param mainActivity
	 *            The activity component that is the root of the task; this is
	 *            the activity that has been published in the application's
	 *            manifest as the main launcher icon.
	 * @return Returns a newly created Intent that can be used to relaunch the
	 *         activity's task in its root state.
	 */
	public static VaporIntent<? extends Intent, ?> makeRestart(
			ComponentName mainActivity) {
		return $.Intent(Intent.makeRestartActivityTask(mainActivity));
	}

	/**
	 * Normalize a MIME data type.
	 * 
	 * A normalized MIME type has white-space trimmed, content-type parameters
	 * removed, and is lower-case. This aligns the type with Android best
	 * practices for intent filtering.
	 * 
	 * For example, "text/plain; charset=utf-8" becomes "text/plain".
	 * "text/x-vCard" becomes "text/x-vcard".
	 * 
	 * All MIME types received from outside Android (such as user input, or
	 * external sources like Bluetooth, NFC, or the Internet) should be
	 * normalized before they are used to create an Intent.
	 * 
	 * @param type
	 *            MIME data type to normalize
	 * @return normalized MIME data type, or null if the input was null
	 */
	public static String normalMime(String type) {
		return Intent.normalizeMimeType(type);
	}

	/**
	 * 
	 * @param uri
	 * @return
	 */
	public static VaporIntent<? extends Intent, ?> old(String uri) {
		try {
			return $.Intent(Intent.getIntentOld(uri));
		} catch (URISyntaxException e) {
			return null;
		}
	}

	/**
	 * Parses the "intent" element (and its children) from XML and instantiates
	 * an Intent object. The given XML parser should be located at the tag where
	 * parsing should start (often named "intent"), from which the basic action,
	 * data, type, and package and class name will be retrieved. The function
	 * will then parse in to any child elements, looking for tags to add
	 * categories and to attach extra data to the intent.
	 * 
	 * @param resources
	 *            The Resources to use when inflating resources.
	 * @param parser
	 *            The XML parser pointing at an "intent" tag.
	 * @param attrs
	 *            The AttributeSet interface for retrieving extended attribute
	 *            data at the current parser location.
	 * @return A VaporIntent object matching the XML data.
	 */
	public static VaporIntent<? extends Intent, ?> parse(Resources resources,
			XmlPullParser parser, AttributeSet attrs) {
		try {
			return $.Intent(Intent.parseIntent(resources, parser, attrs));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Create an intent from a URI. This URI may encode the action, category,
	 * and other intent fields, if it was returned by toUri(int). If the Intent
	 * was not generate by toUri(), its data will be the entire URI and its
	 * action will be ACTION_VIEW.
	 * 
	 * The URI given here must not be relative -- that is, it must include the
	 * scheme and full path.
	 * 
	 * @param uri
	 *            The URI to turn into an VaporIntent.
	 * @param flags
	 *            Additional processing flags. Either 0 or URI_INTENT_SCHEME.
	 * @return The newly created Intent object.
	 */
	public static VaporIntent<? extends Intent, ?> parse(String uri, int flags) {
		try {
			return $.Intent(Intent.parseUri(uri, flags));
		} catch (URISyntaxException e) {
			return null;
		}
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The double array data value
	 * @return self
	 */
	public self put(String name, double[] value) {
		intent.putExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The integer data value
	 * @return self
	 */
	public self put(String name, int value) {
		intent.putExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The CharSequence data value
	 * @return self
	 */
	public self put(String name, CharSequence value) {
		intent.putExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The char data value
	 * @return self
	 */
	public self put(String name, char value) {
		intent.putExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The VaporBundle data value
	 * @return self
	 */
	public self put(String name, VaporBundle value) {
		intent.putExtra(name, value.bundle());
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The Parcelable array data value
	 * @return self
	 */
	public self put(String name, Parcelable[] value) {
		intent.putExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The Serializable data value
	 * @return self
	 */
	public self put(String name, Serializable value) {
		intent.putExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The integer array data value
	 * @return self
	 */
	public self put(String name, int[] value) {
		intent.putExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The float data value
	 * @return self
	 */
	public self put(String name, float value) {
		intent.putExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The byte array data value
	 * @return self
	 */
	public self put(String name, byte[] value) {
		intent.putExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The long data value
	 * @return self
	 */
	public self put(String name, long value) {
		intent.putExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The long array data value
	 * @return self
	 */
	public self put(String name, long[] value) {
		intent.putExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The Parcelable data value
	 * @return self
	 */
	public self put(String name, Parcelable value) {
		intent.putExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The float array data value
	 * @return self
	 */
	public self put(String name, float[] value) {
		intent.putExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The String array data value
	 * @return self
	 */
	public self put(String name, String[] value) {
		intent.putExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The boolean data value
	 * @return self
	 */
	public self put(String name, boolean value) {
		intent.putExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The boolean array data value
	 * @return self
	 */
	public self put(String name, boolean[] value) {
		intent.putExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The short data value
	 * @return self
	 */
	public self put(String name, short value) {
		intent.putExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The double data value
	 * @return self
	 */
	public self put(String name, double value) {
		intent.putExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The short array data value
	 * @return self
	 */
	public self put(String name, short[] value) {
		intent.putExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The String data value
	 * @return self
	 */
	public self put(String name, String value) {
		intent.putExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The byte data value
	 * @return self
	 */
	public self put(String name, byte value) {
		intent.putExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The char array data value
	 * @return self
	 */
	public self put(String name, char[] value) {
		intent.putExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The CharSequence array data value
	 * @return self
	 */
	public self put(String name, CharSequence[] value) {
		intent.putExtra(name, value);
		return (self) this;
	}

	/**
	 * Copy all extras in 'src' in to this intent.
	 * 
	 * @param src
	 *            Contains the extras to copy.
	 * @return self
	 */
	public self put(VaporIntent<? extends Intent, ?> src) {
		intent.putExtras(src.intent());
		return (self) this;
	}

	/**
	 * Add a set of extended data to the intent. The keys must include a package
	 * prefix, for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param bundle
	 *            The Bundle of extras to add to this intent.
	 * @return this
	 */
	public self put(VaporBundle bundle) {
		int auxCount = bundle.aux().size();
		// if the VaporBundle contains arbitrary reference types
		if (auxCount > 0) {
			// warn the user that these will be lost if the VaporIntent is
			// converted to an Intent
			Log.w(shortName + ".put(VaporBundle)",
					"VaporBundle contains "
							+ auxCount
							+ " arbitrary reference types in aux(). This information will be lost if this VaporIntent is converted to a standard Android Intent. Standard Android Intents and Bundles do not support storage of arbitrary reference types.");
		}
		intent.putExtras(bundle.bundle());
		return (self) this;
	}

	/**
	 * Copy the contents of other in to this object, but only where fields are
	 * not defined by this object.
	 * 
	 * @param other
	 *            Another Intent whose values are to be used to fill in the
	 *            current one.
	 * @param flags
	 *            Options to control which fields can be filled in.
	 * @return Returns a bit mask of FILL_IN_ACTION, FILL_IN_DATA,
	 *         FILL_IN_CATEGORIES, FILL_IN_PACKAGE, FILL_IN_COMPONENT,
	 *         FILL_IN_SOURCE_BOUNDS, and FILL_IN_SELECTOR indicating which
	 *         fields were changed.
	 */
	public int putAdhoc(VaporIntent<? extends Intent, ?> other, int flags) {
		return intent.fillIn(other.intent(), flags);
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The ArrayList data value.
	 * @return self
	 */
	public self putCharSeqList(String name, ArrayList<CharSequence> value) {
		intent.putCharSequenceArrayListExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The ArrayList data value
	 * @return self
	 */
	public self putIntList(String name, ArrayList<Integer> value) {
		intent.putIntegerArrayListExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The ArrayList data value
	 * @return self
	 */
	public self putParcelList(String name, ArrayList<? extends Parcelable> value) {
		intent.putParcelableArrayListExtra(name, value);
		return (self) this;
	}

	/**
	 * Add extended data to the intent. The name must include a package prefix,
	 * for example the app com.android.contacts would use names like
	 * "com.android.contacts.ShowAll".
	 * 
	 * @param name
	 *            The name of the extra data, with package prefix.
	 * @param value
	 *            The ArrayList data value
	 * @return self
	 */
	public self putStringList(String name, ArrayList<String> value) {
		intent.putStringArrayListExtra(name, value);
		return (self) this;
	}

	/**
	 * 
	 * @param in
	 * @return self
	 */
	public self read(Parcel in) {
		intent.readFromParcel(in);
		return (self) this;
	}

	/**
	 * Remove extended data from the intent.
	 * 
	 * @param name
	 * @return self
	 */
	public self remove(String name) {
		intent.removeExtra(name);
		return (self) this;
	}

	/**
	 * Remove a category from an intent.
	 * 
	 * @param category
	 *            The category to remove.
	 * @return self
	 */
	public self removeCategory(String category) {
		intent.removeCategory(category);
		return (self) this;
	}

	/**
	 * Completely replace the extras in the Intent with the given Bundle of
	 * extras.
	 * 
	 * @param extras
	 *            The new set of extras in the Intent, or null to erase all
	 *            extras.
	 * @return self
	 */
	public self replace(VaporBundle extras) {
		intent.replaceExtras(extras.bundle());
		return (self) this;
	}

	/**
	 * Completely replace the extras in the Intent with the extras in the given
	 * Intent.
	 * 
	 * @param src
	 *            The exact extras contained in this Intent are copied into the
	 *            target intent, replacing any that were previously there.
	 * @return self
	 */
	public self replace(VaporIntent<? extends Intent, ?> src) {
		intent.replaceExtras(src.intent());
		return (self) this;
	}

	/**
	 * Return the Activity component that should be used to handle this intent.
	 * 
	 * @param packageManager
	 *            The package manager with which to resolve the VaporIntent.
	 * @return Name of the component implementing an activity that can display
	 *         the intent.
	 */
	public ComponentName resolve(PackageManager packageManager) {
		return intent.resolveActivity(packageManager);
	}

	/**
	 * Resolve the Intent into an ActivityInfo describing the activity that
	 * should execute the intent. Resolution follows the same rules as described
	 * for resolve(PackageManager), but you get back the completely information
	 * about the resolved activity instead of just its class name.
	 * 
	 * @param packageManager
	 *            The package manager with which to resolve the VaporIntent.
	 * @param flags
	 *            Addition information to retrieve as per
	 *            PackageManager.getActivityInfo().
	 * @return PackageManager.ActivityInfo
	 */
	public ActivityInfo resolve(PackageManager packageManager, int flags) {
		return intent.resolveActivityInfo(packageManager, flags);
	}

	/**
	 * Return the MIME data type of this intent. If the type field is explicitly
	 * set, that is simply returned. Otherwise, if the data is set, the type of
	 * that data is returned. If neither fields are set, a null is returned.
	 * 
	 * @param contentResolver
	 *            A ContentResolver that can be used to determine the MIME type
	 *            of the intent's data.
	 * @return The MIME type of this intent.
	 */
	public String resolve(ContentResolver contentResolver) {
		return intent.resolveType(contentResolver);
	}

	/**
	 * Return the MIME data type of this intent. If the type field is explicitly
	 * set, that is simply returned. Otherwise, if the data is set, the type of
	 * that data is returned. If neither fields are set, a null is returned.
	 * 
	 * @param context
	 * @return The MIME type of this intent.
	 */
	public String resolve(Context context) {
		return intent.resolveType(context);
	}

	/**
	 * Return the MIME data type of this intent, only if it will be needed for
	 * intent resolution. This is not generally useful for application code; it
	 * is used by the frameworks for communicating with back-end system services.
	 * 
	 * @param contentResolver
	 *            A ContentResolver that can be used to determine the MIME type
	 *            of the intent's data.
	 * @return The MIME type of this intent, or null if it is unknown or not
	 *         needed.
	 */
	public String resolveAdhoc(ContentResolver contentResolver) {
		return intent.resolveTypeIfNeeded(contentResolver);
	}

	/**
	 * Return the scheme portion of the intent's data. If the data is null or
	 * does not include a scheme, null is returned. Otherwise, the scheme prefix
	 * without the final ':' is returned, i.e. "http".
	 * 
	 * This is the same as calling getData().getScheme() (and checking for null
	 * data).
	 * 
	 * @return The scheme of this intent.
	 */
	public String scheme() {
		return intent.getScheme();
	}

	/**
	 * Return the specific selector associated with this Intent. If there is
	 * none, returns null. See selector(VaporIntent) for more information.
	 * 
	 * @return
	 */
	public VaporIntent<? extends Intent, ?> selector() {
		return $.Intent(intent.getSelector());
	}

	/**
	 * Set a selector for this Intent. This is a modification to the kinds of
	 * things the Intent will match. If the selector is set, it will be used
	 * when trying to find entities that can handle the Intent, instead of the
	 * main contents of the Intent. This allows you build an Intent containing a
	 * generic protocol while targeting it more specifically.
	 * 
	 * @param selector
	 *            The desired selector Intent; set to null to not use a special
	 *            selector.
	 * @return self
	 */
	public self selector(VaporIntent<? extends Intent, ?> selector) {
		intent.setSelector(selector.intent());
		return (self) this;
	}

	/**
	 * Get the bounds of the sender of this intent, in screen coordinates. This
	 * can be used as a hint to the receiver for animations and the like. Null
	 * means that there is no source bounds.
	 * 
	 * @return
	 */
	public Rect sourceBounds() {
		return intent.getSourceBounds();
	}

	/**
	 * Set the bounds of the sender of this intent, in screen coordinates. This
	 * can be used as a hint to the receiver for animations and the like. Null
	 * means that there is no source bounds.
	 * 
	 * @param rectangle
	 * @return self
	 */
	public self sourceBounds(Rect rectangle) {
		intent.setSourceBounds(rectangle);
		return (self) this;
	}

	/**
	 * Set special flags controlling how this intent is handled. Most values
	 * here depend on the type of component being executed by the Intent,
	 * specifically the FLAG_ACTIVITY_* flags are all for use with
	 * Context.startActivity() and the FLAG_RECEIVER_* flags are all for use with
	 * Context.sendBroadcast().
	 * 
	 * @param flags
	 *            The desired flags.
	 * @return self
	 */
	public self specialFlags(int flags) {
		intent.setFlags(flags);
		return (self) this;
	}

	/**
	 * Convert this Intent into a String holding a URI representation of it. The
	 * returned URI string has been properly URI encoded, so it can be used with
	 * Uri.parse(String). The URI contains the Intent's data as the base URI,
	 * with an additional fragment describing the action, categories, type,
	 * flags, package, component, and extras.
	 * 
	 * @param flags
	 *            Additional operating flags. Either 0 or URI_INTENT_SCHEME.
	 * @return Returns a URI encoding URI string describing the entire contents
	 *         of the Intent.
	 */
	public String toUri(int flags) {
		return intent.toUri(flags);
	}

	/**
	 * Retrieve any explicit MIME type included in the intent. This is usually
	 * null, as the type is determined by the intent data.
	 * 
	 * @return If a type was manually set, it is returned; else null is
	 *         returned.
	 */
	public String type() {
		return intent.getType();
	}

	/**
	 * Set an explicit MIME data type.
	 * 
	 * This is used to create intents that only specify a type and not data, for
	 * example to indicate the type of data to return.
	 * 
	 * This method automatically clears any data that was previously set (for
	 * example by data(Uri)).
	 * 
	 * @param type
	 *            The MIME type of the data being handled by this intent.
	 * @return self
	 */
	public self type(String type) {
		type(type, false);
		return (self) this;
	}

	/**
	 * Set an explicit MIME data type, optionally normalized.
	 * 
	 * This is used to create intents that only specify a type and not data, for
	 * example to indicate the type of data to return.
	 * 
	 * This method automatically clears any data that was previously set (for
	 * example by data(Uri)).
	 * 
	 * @param type
	 *            The MIME type of the data being handled by this intent.
	 * @param normalize
	 *            Normalize the MIME data type.
	 * @return self
	 */
	public self type(String type, boolean normalize) {
		if (normalize)
			intent.setTypeAndNormalize(type);
		else
			intent.setType(type);
		return (self) this;
	}

	/**
	 * Convenience for calling component(ComponentName) with the name returned
	 * by a Class object.
	 * 
	 * @param packageContext
	 *            A Context of the application package implementing this class.
	 * @param cls
	 *            The class name to set, equivalent to update(context,
	 *            cls.getName()).
	 * @return self
	 */
	public self update(Context packageContext, Class<?> cls) {
		intent.setClass(packageContext, cls);
		return (self) this;
	}

	/**
	 * Convenience for calling component(ComponentName) with an explicit class
	 * name.
	 * 
	 * @param packageContext
	 *            A Context of the application package implementing this class.
	 * @param className
	 *            The name of a class inside of the application package that
	 *            will be used as the component for this Intent.
	 * @return self
	 */
	public self update(Context packageContext, String className) {
		intent.setClassName(packageContext, className);
		return (self) this;
	}

	/**
	 * Convenience for calling component(ComponentName) with an explicit
	 * application package name and class name.
	 * 
	 * @param packageName
	 *            The name of the package implementing the desired component.
	 * @param className
	 *            The name of a class inside of the application package that
	 *            will be used as the component for this Intent.
	 * @return self
	 */
	public self update(String packageName, String className) {
		intent.setClassName(packageName, className);
		return (self) this;
	}

	/**
	 * Flatten this object in to a Parcel.
	 * 
	 * @param out
	 *            The Parcel in which the object should be written.
	 * @param flags
	 *            Additional flags about how the object should be written. May
	 *            be 0 or PARCELABLE_WRITE_RETURN_VALUE.
	 * @return self
	 */
	public self write(Parcel out, int flags) {
		intent.writeToParcel(out, flags);
		return (self) this;
	}

}
