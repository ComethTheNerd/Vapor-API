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

import vapor.core.$;
import vapor.listeners.widget.date.$change;
import android.widget.CalendarView;
import android.widget.DatePicker;

/**
 * Fluent Vapor companion to DatePicker, a widget for selecting a date. The date
 * can be selected by a year, month, and day spinners or a CalendarView. The set
 * of spinners and the calendar view are automatically synchronized. The client
 * can customize whether only the spinners, or only the calendar view, or both
 * to be displayed. Also the minimal and maximal date from which dates to be
 * selected can be customized.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from DatePicker
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporDatePicker<T extends DatePicker, self extends VaporDatePicker<T, self>>
		extends VaporFrameLayout<T, self> {

	public VaporDatePicker(int id) {
		super(id);
	}

	public VaporDatePicker(T datePicker) {
		super(datePicker);
	}

	/**
	 * Gets the CalendarView.
	 * 
	 * @return The calendar view.
	 */
	public VaporCalendarView<? extends CalendarView, ?> calendar() {
		return $.CalendarView(view.getCalendarView());
	}

	/**
	 * 
	 * @return The selected day of month
	 */
	public int day() {
		return view.getDayOfMonth();
	}

	/**
	 * Sets the day
	 * 
	 * @param day
	 *            the value to set the day to
	 * @return self
	 */
	public self day(int dayOfMonth) {
		update(year(), month(), dayOfMonth);
		return (self) this;
	}

	/**
	 * Initialize the state. If the provided values designate an inconsistent
	 * date the values are normalized before updating the spinners.
	 * 
	 * @param year
	 *            The initial year.
	 * @param monthOfYear
	 *            The initial month starting from zero.
	 * @param dayOfMonth
	 *            The initial day of the month.
	 * @param dateChangedListener
	 *            How user is notified date is changed by user, can be null.
	 * @return
	 */
	public self init(int year, int monthOfYear, int dayOfMonth,
			$change dateChangedListener) {
		view.init(year, monthOfYear, dayOfMonth, dateChangedListener);
		return (self) this;
	}

	/**
	 * Gets the maximal date supported by this DatePicker in milliseconds since
	 * January 1, 1970 00:00:00 in getDefault() time zone.
	 * 
	 * @return The maximal supported date.
	 */
	public long maxDate() {
		return view.getMaxDate();
	}

	/**
	 * Sets the maximal date supported by this DatePicker in milliseconds since
	 * January 1, 1970 00:00:00 in getDefault() time zone.
	 * 
	 * @param maxDate
	 *            The maximal supported date.
	 * @return self
	 */
	public self maxDate(long maxDate) {
		view.setMaxDate(maxDate);
		return (self) this;
	}

	/**
	 * Gets the minimal date supported by this DatePicker in milliseconds since
	 * January 1, 1970 00:00:00 in getDefault() time zone.
	 * 
	 * @return The minimal supported date.
	 */
	public long minDate() {
		return view.getMinDate();
	}

	/**
	 * Sets the minimal date supported by this NumberPicker in milliseconds
	 * since January 1, 1970 00:00:00 in getDefault() time zone.
	 * 
	 * @param minDate
	 *            The minimal supported date.
	 * @return self
	 */
	public self minDate(long minDate) {
		view.setMinDate(minDate);
		return (self) this;
	}

	/**
	 * 
	 * @return The selected month.
	 */
	public int month() {
		return view.getMonth();
	}

	/**
	 * Sets the month
	 * 
	 * @param month
	 *            the value to set the month to
	 * @return self
	 */
	public self month(int month) {
		update(year(), month, day());
		return (self) this;
	}

	/**
	 * Gets whether the CalendarView is shown.
	 * 
	 * @return True if the calendar view is shown.
	 */
	public boolean calendarShowing() {
		return view.getCalendarViewShown();
	}

	/**
	 * Sets whether the CalendarView is shown.
	 * 
	 * @param shown
	 *            True if the calendar view is to be shown.
	 * @return self
	 */
	public self calendarShowing(boolean shown) {
		view.setCalendarViewShown(shown);
		return (self) this;
	}

	/**
	 * Sets whether the spinners are shown.
	 * 
	 * @param shown
	 *            True if the spinners are to be shown.
	 * @return True if the spinners are shown.
	 */
	public self spinnersShowing(boolean shown) {
		view.setSpinnersShown(shown);
		return (self) this;
	}

	/**
	 * Gets whether the spinners are shown.
	 * 
	 * @return True if the spinners are shown.
	 */
	public boolean spinnersShowing() {
		return view.getSpinnersShown();
	}

	/**
	 * Updates the current date.
	 * 
	 * @param year
	 *            Updates the current date.
	 * @param month
	 *            The month which is starting from zero.
	 * @param dayOfMonth
	 *            The day of the month.
	 * @return self
	 */
	public self update(int year, int month, int dayOfMonth) {
		view.updateDate(year, month, dayOfMonth);
		return (self) this;
	}

	/**
	 * 
	 * @return The selected year.
	 */
	public int year() {
		return view.getYear();
	}

	/**
	 * Sets the year
	 * 
	 * @param year
	 *            the value to set the year to
	 * @return self
	 */
	public self year(int year) {
		update(year, month(), day());
		return (self) this;
	}
}
