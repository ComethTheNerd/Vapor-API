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

/*This file has been autogenerated by the Vapid Tool v1.0 @ 22/12/2012 22:43:13*/
package vapor.widget;

import java.util.ArrayList;

import android.text.TextWatcher;
import android.widget.DialerFilter;

/**
 * Fluent variadic Vapor companion to DialerFilter
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from DialerFilter
 * @param <X>
 *            A Vapor type derived from VaporDialerFilter
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporXDialerFilter<T extends DialerFilter, X extends VaporDialerFilter<T, ?>, self extends VaporXDialerFilter<T, X, self>>
		extends VaporXRelativeLayout<T, X, self> {

	public VaporXDialerFilter(Integer... ids) {
		super(ids);
	}

	public VaporXDialerFilter(T... dialerFilters) {
		super(dialerFilters);
	}

	public VaporXDialerFilter(X... vaporDialerFilters) {
		super(vaporDialerFilters);
	}

	public VaporXDialerFilter(
			VaporXDialerFilter<T, X, ?>... vaporXDialerFilters) {
		super(vaporXDialerFilters);
	}

	public VaporXDialerFilter(Object... dialerFilterItems) {
		super(dialerFilterItems);
	}

	/**
	 * 
	 * @param text
	 * @return self
	 */
	public self append(String text) {
		for (X vaporView : members)
			vaporView.append(text);
		return (self) this;

	}

	/**
	 * Clears both the digits and the filter text.
	 * 
	 * @return self
	 */
	public self clear() {
		for (X vaporView : members)
			vaporView.clear();
		return (self) this;

	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<CharSequence> digits() {
		int _size = this.members.size();
		ArrayList<CharSequence> _returns = new ArrayList<CharSequence>(_size);
		for (int _index = 0; _index < _size; _index++)
			_returns.add(this.members.get(_index).digits());
		return _returns;
	}

	/**
	 * 
	 * @param digitsWatcher
	 * @return
	 */
	public self digits(vapor.listeners.text.$text digitsWatcher) {
		for (X vaporView : members)
			vaporView.digits(digitsWatcher);
		return (self) this;

	}

	/**
	 * 
	 * @param filterWatcher
	 * @return
	 */
	public self filter(vapor.listeners.text.$text filterWatcher) {
		for (X vaporView : members)
			vaporView.filter(filterWatcher);
		return (self) this;

	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<CharSequence> filterText() {
		int _size = this.members.size();
		ArrayList<CharSequence> _returns = new ArrayList<CharSequence>(_size);
		for (int _index = 0; _index < _size; _index++)
			_returns.add(this.members.get(_index).filterText());
		return _returns;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<CharSequence> letters() {
		int _size = this.members.size();
		ArrayList<CharSequence> _returns = new ArrayList<CharSequence>(_size);
		for (int _index = 0; _index < _size; _index++)
			_returns.add(this.members.get(_index).letters());
		return _returns;
	}

	/**
	 * 
	 * @param lettersWatcher
	 * @return
	 */
	public self letters(vapor.listeners.text.$text lettersWatcher) {
		for (X vaporView : members)
			vaporView.letters(lettersWatcher);
		return (self) this;

	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Integer> mode() {
		int _size = this.members.size();
		ArrayList<Integer> _returns = new ArrayList<Integer>(_size);
		for (int _index = 0; _index < _size; _index++)
			_returns.add(this.members.get(_index).mode());
		return _returns;
	}

	/**
	 * Change the mode of the widget.
	 * 
	 * @param newMode
	 *            The mode to switch to.
	 * @return
	 */
	public self mode(int newMode) {
		for (X vaporView : members)
			vaporView.mode(newMode);
		return (self) this;

	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Boolean> qwerty() {
		int _size = this.members.size();
		ArrayList<Boolean> _returns = new ArrayList<Boolean>(_size);
		for (int _index = 0; _index < _size; _index++)
			_returns.add(this.members.get(_index).qwerty());
		return _returns;
	}

	/**
	 * 
	 * @param filterWatcher
	 * @return
	 */
	public self remove(TextWatcher filterWatcher) {
		for (X vaporView : members)
			vaporView.remove(filterWatcher);
		return (self) this;

	}

}