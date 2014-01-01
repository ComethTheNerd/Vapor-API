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

import android.widget.ToggleButton;

/**
 * Fluent Vapor companion to ToggleButton, that displays checked/unchecked
 * states as a button with a "light" indicator and by default accompanied with
 * the text "ON" or "OFF".
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from ToggleButton
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporToggleButton<T extends ToggleButton, self extends VaporToggleButton<T, self>>
		extends VaporCompoundButton<T, self> {

	public VaporToggleButton(int id) {
		super(id);
	}

	public VaporToggleButton(T toggleButton) {
		super(toggleButton);
	}

	/**
	 * Returns the text for when the button is in the checked state.
	 * 
	 * @return the checked text
	 */
	public CharSequence checkedText() {
		return view.getTextOn();
	}

	/**
	 * Sets the text for when the button is in the checked state.
	 * 
	 * @param textOn
	 *            the checked text
	 * @return this
	 */
	public self checkedText(CharSequence textOn) {
		view.setTextOn(textOn);
		return (self) this;
	}

	/**
	 * Returns the text for when the button is not in the checked state.
	 * 
	 * @return the unchecked text
	 */
	public CharSequence uncheckedText() {
		return view.getTextOff();
	}

	/**
	 * Sets the text for when the button is not in the checked state.
	 * 
	 * @param textOff
	 *            The unchecked text
	 * @return this
	 */
	public self uncheckedText(CharSequence textOff) {
		view.setTextOff(textOff);
		return (self) this;
	}
}
