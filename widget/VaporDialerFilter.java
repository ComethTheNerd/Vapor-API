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

import android.text.TextWatcher;
import android.widget.DialerFilter;

/**
 * Fluent Vapor companion to DialerFilter
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from DialerFilter
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporDialerFilter<T extends DialerFilter, self extends VaporDialerFilter<T, self>>
		extends VaporRelativeLayout<T, self> {

	public VaporDialerFilter(int id) {
		super(id);
	}

	public VaporDialerFilter(T dialerFilter) {
		super(dialerFilter);
	}

	/**
	 * 
	 * @param text
	 * @return self
	 */
	public self append(String text) {
		view.append(text);
		return (self) this;
	}

	/**
	 * Clears both the digits and the filter text.
	 * 
	 * @return self
	 */
	public self clear() {
		view.clearText();
		return (self) this;
	}

	/**
	 * 
	 * @return
	 */
	public CharSequence digits() {
		return view.getDigits();
	}

	/**
	 * 
	 * @param digitsWatcher
	 * @return
	 */
	public self digits(vapor.listeners.text.$text digitsWatcher) {
		view.setDigitsWatcher(digitsWatcher);
		return (self) this;
	}

	/**
	 * 
	 * @param filterWatcher
	 * @return
	 */
	public self filter(vapor.listeners.text.$text filterWatcher) {
		view.setFilterWatcher(filterWatcher);
		return (self) this;
	}

	/**
	 * 
	 * @return
	 */
	public CharSequence filterText() {
		return view.getFilterText();
	}

	/**
	 * 
	 * @return
	 */
	public CharSequence letters() {
		return view.getLetters();
	}

	/**
	 * 
	 * @param lettersWatcher
	 * @return
	 */
	public self letters(vapor.listeners.text.$text lettersWatcher) {
		view.setLettersWatcher(lettersWatcher);
		return (self) this;
	}

	/**
	 * 
	 * @return
	 */
	public int mode() {
		return view.getMode();
	}

	/**
	 * Change the mode of the widget.
	 * 
	 * @param newMode
	 *            The mode to switch to.
	 * @return
	 */
	public self mode(int newMode) {
		view.setMode(newMode);
		return (self) this;
	}

	/**
	 * 
	 * @return
	 */
	public boolean qwerty() {
		return view.isQwertyKeyboard();
	}

	/**
	 * 
	 * @param filterWatcher
	 * @return
	 */
	public self remove(TextWatcher filterWatcher) {
		view.removeFilterWatcher(filterWatcher);
		return (self) this;
	}
}
