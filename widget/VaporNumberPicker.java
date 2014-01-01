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

package vapor.widget;

import android.widget.NumberPicker;

/**
 * Fluent Vapor companion to NumberPicker, a widget that enables the user to
 * select a number form a predefined range. There are two flavors of this widget
 * and which one is presented to the user depends on the current theme.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from NumberPicker
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporNumberPicker<T extends NumberPicker, self extends VaporNumberPicker<T, self>>
		extends VaporLinearLayout<T, self> {

	public VaporNumberPicker(int id) {
		super(id);
	}

	public VaporNumberPicker(T numberPicker) {
		super(numberPicker);
	}

	/**
	 * Gets the values to be displayed instead of string values.
	 * 
	 * @return The displayed values.
	 */
	public String[] displayVals() {
		return view.getDisplayedValues();
	}

	/**
	 * 
	 * @param displayedValues
	 * @return self
	 */
	public self displayVals(String[] displayedValues) {
		view.setDisplayedValues(displayedValues);
		return (self) this;
	}

	/**
	 * Set the formatter to be used for formatting the current value.
	 * 
	 * Note: If you have provided alternative values for the values this
	 * formatter is never invoked.
	 * 
	 * @param formatter
	 *            The formatter object. If formatter is null, valueOf(int) will
	 *            be used.
	 * @return self
	 */
	public self formatter(NumberPicker.Formatter formatter) {
		view.setFormatter(formatter);
		return (self) this;
	}

	/**
	 * Returns the max value of the picker.
	 * 
	 * @return The max value
	 */
	public int maxVal() {
		return view.getMaxValue();
	}

	/**
	 * Sets the max value of the picker.
	 * 
	 * @param maxValue
	 *            The max value
	 * @return self
	 */
	public self maxVal(int maxValue) {
		view.setMaxValue(maxValue);
		return (self) this;
	}

	/**
	 * Returns the min value of the picker.
	 * 
	 * @return The min value
	 */
	public int minVal() {
		return view.getMinValue();
	}

	/**
	 * Sets the min value of the picker.
	 * 
	 * @param minValue
	 *            The min value
	 * @return self
	 */
	public self minVal(int minValue) {
		view.setMinValue(minValue);
		return (self) this;
	}

	/**
	 * Set listener to be notified for scroll state changes.
	 * 
	 * @param scrollListener
	 *            The listener.
	 * @return self
	 */
	public self scroll(
			vapor.listeners.widget.numberpicker.$scroll scrollListener) {
		view.setOnScrollListener(scrollListener);
		return (self) this;
	}

	/**
	 * Sets the speed at which the numbers be incremented and decremented when
	 * the up and down buttons are long pressed respectively.
	 * 
	 * The default value is 300 ms.
	 * 
	 * @param intervalMillis
	 *            The speed (in milliseconds) at which the numbers will be
	 *            incremented and decremented.
	 * @return self
	 */
	public self updateRate(long intervalMillis) {
		view.setOnLongPressUpdateInterval(intervalMillis);
		return (self) this;
	}

	/**
	 * Returns the value of the picker.
	 * 
	 * @return The value.
	 */
	public int val() {
		return view.getValue();
	}

	/**
	 * Set the current value for the number picker.
	 * 
	 * If the argument is less than the minVal() and wraps() is false the
	 * current value is set to the getMinValue() value.
	 * 
	 * If the argument is less than the minVal() and wraps() is true the current
	 * value is set to the getMaxValue() value.
	 * 
	 * If the argument is less than the maxVal() and wraps() is false the
	 * current value is set to the getMaxValue() value.
	 * 
	 * If the argument is less than the maxVal() and wraps() is true the current
	 * value is set to the getMinValue() value.
	 * 
	 * @param value
	 *            The current value.
	 * @return self
	 */
	public self val(int value) {
		view.setValue(value);
		return (self) this;
	}

	/**
	 * Sets the listener to be notified on change of the current value.
	 * 
	 * @param valueChangedListener
	 *            The listener.
	 * @return self
	 */
	public self val(
			vapor.listeners.widget.numberpicker.$change valueChangedListener) {
		view.setOnValueChangedListener(valueChangedListener);
		return (self) this;
	}

	/**
	 * Gets whether the selector wheel wraps when reaching the min/max value.
	 * 
	 * @return True if the selector wheel wraps.
	 */
	public boolean wraps() {
		return view.getWrapSelectorWheel();
	}

	/**
	 * Sets whether the selector wheel shown during flinging/scrolling should
	 * wrap around the minVal() and maxVal() values.
	 * 
	 * By default if the range (max - min) is more than the number of items
	 * shown on the selector wheel the selector wheel wrapping is enabled.
	 * 
	 * Note: If the number of items, i.e. the range ( maxVal() - minVal()) is
	 * less than the number of items shown on the selector wheel, the selector
	 * wheel will not wrap. Hence, in such a case calling this method is a NOP.
	 * 
	 * @param wrapSelectorWheel
	 *            Whether to wrap.
	 * @return self
	 */
	public self wraps(boolean wrapSelectorWheel) {
		view.setWrapSelectorWheel(wrapSelectorWheel);
		return (self) this;
	}
}
