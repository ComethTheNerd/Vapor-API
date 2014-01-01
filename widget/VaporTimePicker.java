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

import android.widget.TimePicker;

/**
 * Fluent Vapor companion to TimePicker, a view for selecting the time of day,
 * in either 24 hour or AM/PM mode. The hour, each minute digit, and AM/PM (if
 * applicable) can be controlled by vertical spinners. The hour can be entered
 * by keyboard input. Entering in two digit hours can be accomplished by hitting
 * two digits within a timeout of about a second (e.g. '1' then '2' to select
 * 12). The minutes can be entered by entering single digits. Under AM/PM mode,
 * the user can hit 'a', 'A", 'p' or 'P' to pick. For a dialog using this view,
 * see TimePickerDialog.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from TimePicker
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporTimePicker<T extends TimePicker, self extends VaporTimePicker<T, self>>
		extends VaporFrameLayout<T, self> {

	public VaporTimePicker(int id) {
		super(id);
	}

	public VaporTimePicker(T timePicker) {
		super(timePicker);
	}

	/**
	 * Set the callback that indicates the time has been adjusted by the user.
	 * 
	 * @param timeChangedListener
	 *            the callback, should not be null.
	 * @return this
	 */
	public self time(
			vapor.listeners.widget.timepicker.$change timeChangedListener) {
		view.setOnTimeChangedListener(timeChangedListener);
		return (self) this;
	}

	/**
	 * 
	 * @return true if this is in 24 hour view else false.
	 */
	public boolean formatted24h() {
		return view.is24HourView();
	}

	/**
	 * Set whether in 24 hour or AM/PM mode.
	 * 
	 * @param is24HourView
	 *            True = 24 hour mode. False = AM/PM.
	 * @return this
	 */
	public self formatted24h(boolean is24HourView) {
		view.setIs24HourView(is24HourView);
		return (self) this;
	}

	/**
	 * 
	 * @return The current hour in the range (0-23).
	 */
	public int hour() {
		return view.getCurrentHour();
	}

	/**
	 * Set the current hour.
	 * 
	 * @param currentHour
	 * @return this
	 */
	public self hour(int currentHour) {
		view.setCurrentHour(currentHour);
		return (self) this;
	}

	/**
	 * 
	 * @return The current minute.
	 */
	public int minute() {
		return view.getCurrentMinute();
	}

	/**
	 * Set the current minute (0-59).
	 * 
	 * @param currentMinute
	 * @return this
	 */
	public self minute(int currentMinute) {
		view.setCurrentMinute(currentMinute);
		return (self) this;
	}
}
