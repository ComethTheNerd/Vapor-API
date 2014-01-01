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

package vapor.os;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import vapor.core.$;
import vapor.exception.VaporLicenseException;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;

/**
 * Fluent Vapor companion to standard Android Bundle, a mapping from String
 * values to various types
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 */
public final class VaporBundle {

	private Bundle bundle;
	private HashMap<String, Object> auxStorage;

	// If no more specific overload is found, indicating the underlying bundle
	// can't handle the type
	// of T, the auxStorage is used.
	public <T> VaporBundle put(String key, T value) {
		auxStorage.put(key, value);
		return this;
	}

	public VaporBundle() {
		/*No license check here. Need functionality even if no context is set.*/
			bundle = new Bundle();
			auxStorage = new HashMap<String, Object>();
		// hashCode = bundle.hashCode();

	}

	public VaporBundle(Bundle bundle) {
		/*No license check here. Need functionality even if no context is set.*/
			// nulls are translated to empty bundles
			this.bundle = bundle != null? bundle : new Bundle();
			
			auxStorage = new HashMap<String, Object>();
			// hashCode = bundle.hashCode();
		
	}

	/**
	 * Returns the auxillary structure used to store arbitrary types that are
	 * not supported by the underlying Android bundle
	 * 
	 * @return the auxillary structure used to store arbitrary types
	 */
	public HashMap<String, Object> aux() {
		return auxStorage;
	}

	/**
	 * 
	 * @return
	 */
	public Bundle bundle() {
		return bundle;
	}

	/**
	 * Removes all elements from the mapping of this Bundle.
	 * 
	 * @return
	 */
	public VaporBundle clear() {
		bundle.clear();
		auxStorage.clear();
		return this;
	}

	/**
	 * Clones the current Bundle.
	 * 
	 */
	public VaporBundle clone() {
		VaporBundle clone = new VaporBundle((Bundle) bundle.clone());
		clone.auxStorage.putAll(this.auxStorage);

		return clone;
	}

	/**
	 * Returns true if the given key is contained in the mapping of this Bundle.
	 * 
	 * @param key
	 * @return
	 */
	public boolean contains(String key) {
		return bundle.containsKey(key) || auxStorage.containsKey(key);
	}

	/**
	 * Report the nature of this Parcelable's contents.
	 * 
	 * @return
	 */
	public int describe() {
		return bundle.describeContents();
	}

	/**
	 * Compares this instance with the specified object and indicates if they
	 * are equal.
	 * 
	 * @param vb
	 * @return
	 */
	public boolean eq(VaporBundle vb) {
		return equals(vb);
	}

	/**
	 * Returns true if the mapping of this Bundle is empty, false otherwise.
	 * 
	 * @return
	 */
	public boolean empty() {
		return bundle.isEmpty() && auxStorage.isEmpty();
	}

	/**
	 * Reports whether the bundle contains any parcelled file descriptors.
	 * 
	 * @return
	 */
	public boolean fileDesc() {
		return bundle.hasFileDescriptors();
	}

	/**
	 * Returns the entry with the given key as an object.
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		if (bundle.containsKey(key))
			return bundle.get(key);
		else
			return auxStorage.get(key);
	}

	/**
	 * Returns the entry with the given key as an instance of T.
	 * 
	 * @param key
	 * @param cls
	 *            the class of the entry with the given key
	 * @return
	 */
	public <T> T get(String key, Class<T> cls) {
		if (bundle.containsKey(key))
			return (T) bundle.get(key);
		else if (auxStorage.containsKey(key))
			return (T) auxStorage.get(key);
		return null;
	}

	/**
	 * Returns the value associated with the given key, or false if no mapping
	 * of the desired type exists for the given key.
	 * 
	 * @param key
	 */
	public boolean getBool(String key) {
		return bundle.getBoolean(key);
	}

	/**
	 * Returns the value associated with the given key, or defaultValue if no
	 * mapping of the desired type exists for the given key.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public boolean getBool(String key, boolean defaultValue) {
		return bundle.getBoolean(key, defaultValue);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @return
	 */
	public boolean[] getBools(String key) {
		return bundle.getBooleanArray(key);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @return
	 */
	public VaporBundle getBundle(String key) {
		return new VaporBundle(bundle.getBundle(key));
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @return
	 */
	public byte getByte(String key) {
		return bundle.getByte(key);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public byte getByte(String key, byte defaultValue) {
		return bundle.getByte(key, defaultValue);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @return
	 */
	public byte[] getBytes(String key) {
		return bundle.getByteArray(key);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @return
	 */
	public char getChar(String key) {
		return bundle.getChar(key);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public char getChar(String key, char defaultValue) {
		return bundle.getChar(key, defaultValue);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @return
	 */
	public char[] getChars(String key) {
		return bundle.getCharArray(key);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @return
	 */
	public CharSequence getCharSeq(String key) {
		return bundle.getCharSequence(key);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public CharSequence getCharSeq(String key, CharSequence defaultValue) {
		return bundle.getCharSequence(key, defaultValue);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @return
	 */
	public CharSequence[] getCharSeqs(String key) {
		return bundle.getCharSequenceArray(key);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @return
	 */
	public ArrayList<CharSequence> getCharSeqList(String key) {
		return bundle.getCharSequenceArrayList(key);
	}

	/**
	 * Returns the value associated with the given key, or 0.0 if no mapping of
	 * the desired type exists for the given key.
	 * 
	 * @param key
	 * @return
	 */
	public double getDouble(String key) {
		return bundle.getDouble(key);
	}

	/**
	 * Returns the value associated with the given key, or defaultValue if no
	 * mapping of the desired type exists for the given key.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public double getDouble(String key, double defaultValue) {
		return bundle.getDouble(key, defaultValue);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @return
	 */
	public double[] getDoubles(String key) {
		return bundle.getDoubleArray(key);
	}

	/**
	 * Returns the value associated with the given key, or 0.0f if no mapping of
	 * the desired type exists for the given key.
	 * 
	 * @param key
	 * @return
	 */
	public float getFloat(String key) {
		return bundle.getFloat(key);
	}

	/**
	 * Returns the value associated with the given key, or defaultValue if no
	 * mapping of the desired type exists for the given key.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public float getFloat(String key, float defaultValue) {
		return bundle.getFloat(key, defaultValue);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @return
	 */
	public float[] getFloats(String key) {
		return bundle.getFloatArray(key);
	}

	/**
	 * Returns the value associated with the given key, or 0 if no mapping of
	 * the desired type exists for the given key.
	 * 
	 * @param key
	 * @return
	 */
	public int getInt(String key) {
		return bundle.getInt(key);
	}

	/**
	 * Returns the value associated with the given key, or defaultValue if no
	 * mapping of the desired type exists for the given key.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public int getInt(String key, int defaultValue) {
		return bundle.getInt(key, defaultValue);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @return
	 */
	public int[] getInts(String key) {
		return bundle.getIntArray(key);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @return
	 */
	public ArrayList<Integer> getIntList(String key) {
		return bundle.getIntegerArrayList(key);
	}

	/**
	 * Returns the value associated with the given key, or 0L if no mapping of
	 * the desired type exists for the given key.
	 * 
	 * @param key
	 * @return
	 */
	public long getLong(String key) {
		return bundle.getLong(key);
	}

	/**
	 * Returns the value associated with the given key, or defaultValue if no
	 * mapping of the desired type exists for the given key.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public long getLong(String key, long defaultValue) {
		return bundle.getLong(key, defaultValue);
	}

	/**
	 * Returns the value associated with the given key, or defaultValue if no
	 * mapping of the desired type exists for the given key.
	 * 
	 * @param key
	 * @return
	 */
	public long[] getLongs(String key) {
		return bundle.getLongArray(key);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @return
	 */
	public <T extends Parcelable> T getParcel(String key) {
		return bundle.getParcelable(key);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @return
	 */
	public Parcelable[] getParcels(String key) {
		return bundle.getParcelableArray(key);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @return
	 */
	public <T extends Parcelable> ArrayList<T> getParcelList(String key) {
		return bundle.getParcelableArrayList(key);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @return
	 */
	public Serializable getSerial(String key) {
		return bundle.getSerializable(key);
	}

	/**
	 * Returns the value associated with the given key, or (short) 0 if no
	 * mapping of the desired type exists for the given key.
	 * 
	 * @param key
	 * @return
	 */
	public short getShort(String key) {
		return bundle.getShort(key);
	}

	/**
	 * Returns the value associated with the given key, or defaultValue if no
	 * mapping of the desired type exists for the given key.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public short getShort(String key, short defaultValue) {
		return bundle.getShort(key, defaultValue);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @return
	 */
	public short[] getShorts(String key) {
		return bundle.getShortArray(key);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 */
	public <T extends Parcelable> SparseArray<T> getSparse(String key) {
		return bundle.getSparseParcelableArray(key);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		return bundle.getString(key);
	}

	/**
	 * Returns the value associated with the given key, or defaultValue if no
	 * mapping of the desired type exists for the given key.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String getString(String key, String defaultValue) {
		return bundle.getString(key, defaultValue);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @return
	 */
	public String[] getStrings(String key) {
		return bundle.getStringArray(key);
	}

	/**
	 * Returns the value associated with the given key, or null if no mapping of
	 * the desired type exists for the given key or a null value is explicitly
	 * associated with the key.
	 * 
	 * @param key
	 * @return
	 */
	public ArrayList<String> getStringList(String key) {
		return bundle.getStringArrayList(key);
	}

	/**
	 * Returns an integer hash code for this object.
	 * 
	 * @return
	 */
	public int hash() {
		return bundle.hashCode();
	}

	/**
	 * Returns a Set containing the Strings used as keys in this Bundle.
	 * 
	 * @return
	 */
	public Set<String> keys() {
		Set<String> keys = bundle.keySet();
		keys.addAll(auxStorage.keySet());
		return keys;
	}

	/**
	 * Return the ClassLoader currently associated with this Bundle.
	 * 
	 * @return
	 */
	public ClassLoader loader() {
		return bundle.getClassLoader();
	}

	/**
	 * Changes the ClassLoader this Bundle uses when instantiating objects.
	 * 
	 * @param loader
	 * @return
	 */
	public VaporBundle loader(ClassLoader loader) {
		bundle.setClassLoader(loader);
		return this;
	}

	/**
	 * Writes the Bundle contents to a Parcel, typically in order for it to be
	 * passed through an IBinder connection.
	 * 
	 * @param parcel
	 * @param flags
	 * @return
	 */
	public VaporBundle parcel(Parcel parcel, int flags) {
		bundle.writeToParcel(parcel, flags);
		return this;

	}

	/**
	 * Inserts all mappings from the given Bundle into this Bundle.
	 * 
	 * @param vaporMap
	 * @return
	 */
	public VaporBundle put(VaporBundle map) {
		bundle.putAll(map.bundle());
		auxStorage.putAll(map.aux());
		return this;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public VaporBundle put(String key, int value) {
		bundle.putInt(key, value);
		return this;
	}

	/**
	 * Inserts an int array value into the mapping of this Bundle, replacing any
	 * existing value for the given key.
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public VaporBundle put(String key, int[] values) {
		bundle.putIntArray(key, values);
		return this;
	}

	/**
	 * Inserts a byte value into the mapping of this Bundle, replacing any
	 * existing value for the given key.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public VaporBundle put(String key, byte value) {
		bundle.putByte(key, value);
		return this;
	}

	/**
	 * Inserts a byte array value into the mapping of this Bundle, replacing any
	 * existing value for the given key.
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public VaporBundle put(String key, byte[] values) {
		bundle.putByteArray(key, values);
		return this;
	}

	/**
	 * Inserts a Boolean value into the mapping of this Bundle, replacing any
	 * existing value for the given key.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public VaporBundle put(String key, boolean value) {
		bundle.putBoolean(key, value);
		return this;
	}

	/**
	 * Inserts a boolean array value into the mapping of this Bundle, replacing
	 * any existing value for the given key.
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public VaporBundle put(String key, boolean[] values) {
		bundle.putBooleanArray(key, values);
		return this;
	}

	/**
	 * Inserts a short value into the mapping of this Bundle, replacing any
	 * existing value for the given key.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public VaporBundle put(String key, short value) {
		bundle.putShort(key, value);
		return this;
	}

	/**
	 * Inserts a short array value into the mapping of this Bundle, replacing
	 * any existing value for the given key.
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public VaporBundle put(String key, short[] values) {
		bundle.putShortArray(key, values);
		return this;
	}

	/**
	 * Inserts a char value into the mapping of this Bundle, replacing any
	 * existing value for the given key.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public VaporBundle put(String key, char value) {
		bundle.putChar(key, value);
		return this;
	}

	/**
	 * Inserts a char array value into the mapping of this Bundle, replacing any
	 * existing value for the given key.
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public VaporBundle put(String key, char[] values) {
		bundle.putCharArray(key, values);
		return this;
	}

	/**
	 * Inserts a CharSequence value into the mapping of this Bundle, replacing
	 * any existing value for the given key.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public VaporBundle put(String key, CharSequence value) {
		bundle.putCharSequence(key, value);
		return this;
	}

	/**
	 * Inserts a CharSequence array value into the mapping of this Bundle,
	 * replacing any existing value for the given key.
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public VaporBundle put(String key, CharSequence[] values) {
		bundle.putCharSequenceArray(key, values);
		return this;
	}

	/**
	 * Inserts a double value into the mapping of this Bundle, replacing any
	 * existing value for the given key.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public VaporBundle put(String key, double value) {
		bundle.putDouble(key, value);
		return this;
	}

	/**
	 * Inserts a double array value into the mapping of this Bundle, replacing
	 * any existing value for the given key.
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public VaporBundle put(String key, double[] values) {
		bundle.putDoubleArray(key, values);
		return this;
	}

	/**
	 * Inserts a float value into the mapping of this Bundle, replacing any
	 * existing value for the given key.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public VaporBundle put(String key, float value) {
		bundle.putFloat(key, value);
		return this;
	}

	/**
	 * Inserts a float array value into the mapping of this Bundle, replacing
	 * any existing value for the given key.
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public VaporBundle put(String key, float[] values) {
		bundle.putFloatArray(key, values);
		return this;
	}

	/**
	 * Inserts a long value into the mapping of this Bundle, replacing any
	 * existing value for the given key.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public VaporBundle put(String key, long value) {
		bundle.putLong(key, value);
		return this;
	}

	/**
	 * Inserts a long array value into the mapping of this Bundle, replacing any
	 * existing value for the given key.
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public VaporBundle put(String key, long[] values) {
		bundle.putLongArray(key, values);
		return this;
	}

	/**
	 * Inserts a String value into the mapping of this Bundle, replacing any
	 * existing value for the given key.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public VaporBundle put(String key, String value) {
		bundle.putString(key, value);
		return this;
	}

	/**
	 * Inserts a String array value into the mapping of this Bundle, replacing
	 * any existing value for the given key.
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public VaporBundle put(String key, String[] values) {
		bundle.putStringArray(key, values);
		return this;
	}

	/**
	 * Inserts a Parcelable value into the mapping of this Bundle, replacing any
	 * existing value for the given key.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public VaporBundle put(String key, Parcelable value) {
		bundle.putParcelable(key, value);
		return this;
	}

	/**
	 * Inserts an array of Parcelable values into the mapping of this Bundle,
	 * replacing any existing value for the given key.
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public VaporBundle put(String key, Parcelable[] values) {
		bundle.putParcelableArray(key, values);
		return this;
	}

	/**
	 * Inserts a SparseArray of Parcelable values into the mapping of this
	 * Bundle, replacing any existing value for the given key.
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public VaporBundle put(String key, SparseArray<? extends Parcelable> values) {
		bundle.putSparseParcelableArray(key, values);
		return this;
	}

	/**
	 * Inserts a Serializable value into the mapping of this Bundle, replacing
	 * any existing value for the given key.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public VaporBundle put(String key, Serializable value) {
		bundle.putSerializable(key, value);
		return this;
	}

	/**
	 * Inserts a Bundle value into the mapping of this Bundle, replacing any
	 * existing value for the given key.
	 * 
	 * @param key
	 * @param bundle
	 * @return
	 */
	public VaporBundle put(String key, VaporBundle bundle) {
		bundle.put(key, bundle);
		return this;
	}

	/**
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public VaporBundle putCharSeqList(String key, ArrayList<CharSequence> values) {
		bundle.putCharSequenceArrayList(key, values);
		return this;
	}

	/**
	 * Inserts an ArrayList value into the mapping of this Bundle, replacing any
	 * existing value for the given key.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public VaporBundle putIntList(String key, ArrayList<Integer> value) {
		bundle.putIntegerArrayList(key, value);
		return this;
	}

	/**
	 * Inserts a List of Parcelable values into the mapping of this Bundle,
	 * replacing any existing value for the given key.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public VaporBundle putParcelList(String key, ArrayList<Parcelable> value) {
		bundle.putParcelableArrayList(key, value);
		return this;
	}

	/**
	 * Inserts an ArrayList value into the mapping of this Bundle, replacing any
	 * existing value for the given key.
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public VaporBundle putStringList(String key, ArrayList<String> values) {
		bundle.putStringArrayList(key, values);
		return this;
	}

	/**
	 * Reads the Parcel contents into this Bundle, typically in order for it to
	 * be passed through an IBinder connection.
	 * 
	 * @param parcel
	 * @return
	 */
	public VaporBundle read(Parcel parcel) {
		bundle.readFromParcel(parcel);
		return this;
	}

	/**
	 * Removes any entry with the given key from the mapping of this Bundle.
	 * 
	 * @param key
	 * @return
	 */
	public VaporBundle remove(String key) {
		if (bundle.containsKey(key))
			bundle.remove(key);
		else
			auxStorage.remove(key);
		return this;
	}

	/**
	 * Returns the number of mappings contained in this VaporBundle.
	 * 
	 * @return
	 */
	public int size() {
		return bundle.size() + auxStorage.size();
	}

	/**
	 * Returns a string containing a concise, human-readable description of this
	 * object.
	 * 
	 */
	@Override
	public String toString() {
		Set<String> keys = this.keys();

		String contents = "VaporBundle with " + keys.size() + " items:";
		for (String key : keys) {
			contents += '\n' + key + " => " + this.get(key).toString();
		}
		return contents;
	}

	@Override
	public boolean equals(Object obj) {
		VaporBundle vb2 = (VaporBundle) obj;
		return bundle.equals(vb2.bundle()) && auxStorage.equals(vb2.auxStorage);
	}

	/*
	 * private static HashMap<Integer, HashMap<String,Object>> permaObjs = new
	 * HashMap<Integer, HashMap<String, Object>>();
	 * 
	 * private static void removePermaObj(int hashCode, String key){
	 * 
	 * if(permaObjs.containsKey(hashCode)){
	 * 
	 * HashMap<String,Object> objMap = permaObjs.get(hashCode);
	 * 
	 * if(objMap.containsKey(key)){ objMap.remove(key); } } }
	 * 
	 * private static <T> void putPermaObj(int hashCode, String key, T value){
	 * 
	 * if(permaObjs.containsKey(hashCode)){ permaObjs.get(hashCode).put(key,
	 * value); } }
	 * 
	 * private static <T> T getPermaObj(int hashCode, String key){
	 * if(permaObjs.containsKey(hashCode)){
	 * 
	 * HashMap<String,Object> objMap = permaObjs.get(hashCode);
	 * 
	 * if(objMap.containsKey(key)){ return (T) objMap.get(key); } } return null;
	 * }
	 * 
	 * // put,
	 * 
	 * private final int hashCode;
	 */
}
