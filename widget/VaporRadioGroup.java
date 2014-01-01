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

import android.widget.RadioGroup;

/**
 * Fluent Vapor companion to RadioGroup, used to create a multiple-exclusion
 * scope for a set of radio buttons. Checking one radio button that belongs to a
 * radio group unchecks any previously checked radio button within the same
 * group.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from RadioGroup
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporRadioGroup<T extends RadioGroup, self extends VaporRadioGroup<T, self>>
		extends VaporLinearLayout<T, self> {

	public VaporRadioGroup(int id) {
		super(id);
	}

	public VaporRadioGroup(T radioGroup) {
		super(radioGroup);
	}

	@Override
	public RadioGroup.LayoutParams layoutParams(int width, int height) {
		return new RadioGroup.LayoutParams(width, height);
	}

	/**
	 * Returns the identifier of the selected radio button in this group. Upon
	 * empty selection, the returned value is -1.
	 * 
	 * @return the unique id of the selected radio button in this group
	 */
	public int itemSelected() {
		return view.getCheckedRadioButtonId();
	}

	/**
	 * Sets the selection to the radio button whose identifier is passed in
	 * parameter. Using -1 as the selection identifier clears the selection;
	 * such an operation is equivalent to invoking uncheck().
	 * 
	 * @param id
	 *            the unique id of the radio button to select in this group
	 * @return this
	 */
	public self itemSelected(int id) {
		view.check(id);
		return (self) this;
	}

	/**
	 * Register a callback to be invoked when the checked radio button changes
	 * in this group.
	 * 
	 * @param checkedChangeListener
	 *            the callback to call on checked state change
	 * @return this
	 */
	public self change(
			vapor.listeners.widget.radiogroup.$checked checkedChangeListener) {
		view.setOnCheckedChangeListener(checkedChangeListener);
		return (self) this;
	}

	/**
	 * Clears the selection. When the selection is cleared, no radio button in
	 * this group is selected and getCheckedRadioButtonId() returns null.
	 * 
	 * @return
	 */
	public self clear() {
		view.clearCheck();
		return (self) this;
	}

}
