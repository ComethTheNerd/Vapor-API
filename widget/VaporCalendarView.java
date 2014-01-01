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

import android.graphics.drawable.Drawable;
import android.widget.CalendarView;

/**
 * Fluent Vapor companion to CalendarView, a widget for displaying and selecting
 * dates. The range of dates supported by this calendar is configurable. A user
 * can select a date by taping on it and can scroll and fling the calendar to a
 * desired date.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from CalendarView
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporCalendarView<T extends CalendarView, self extends VaporCalendarView<T, self>>
		extends VaporFrameLayout<T, self> {

	public VaporCalendarView(int id) {
		super(id);
	}

	public VaporCalendarView(T calendarView) {
		super(calendarView);
	}

	/**
	 * Gets the text appearance for the calendar dates.
	 * 
	 * @return The text appearance resource id.
	 */
	public int appearance() {
		return view.getDateTextAppearance();
	}

	/**
	 * Sets the text appearance for the calendar dates.
	 * 
	 * @param resId
	 *            The text appearance resource id.
	 * @return self
	 */
	public self appearance(int resId) {
		view.setDateTextAppearance(resId);
		return (self) this;
	}

	/**
	 * Gets the selected date in milliseconds since January 1, 1970 00:00:00 in
	 * getDefault() time zone.
	 * 
	 * @return The selected date.
	 */
	public long date() {
		return view.getDate();
	}

	/**
	 * Sets the listener to be notified upon selected date change.
	 * 
	 * @param dateChangeListener
	 *            The listener to be notified.
	 * @return self
	 */
	public self date(vapor.listeners.widget.calendar.$change dateChangeListener) {
		// should this be $checked?
		view.setOnDateChangeListener(dateChangeListener);
		return (self) this;
	}

	/**
	 * Sets the selected date in milliseconds since January 1, 1970 00:00:00 in
	 * getDefault() time zone.
	 * 
	 * @param date
	 *            The selected date.
	 * @return self
	 */
	public self date(long date) {
		view.setDate(date);
		return (self) this;
	}

	/**
	 * Sets the selected date in milliseconds since January 1, 1970 00:00:00 in
	 * getDefault() time zone.
	 * 
	 * @param date
	 *            The date.
	 * @param animate
	 *            Whether to animate the scroll to the current date.
	 * @param center
	 *            Whether to center the current date even if it is already
	 *            visible.
	 * @return self
	 */
	public self date(long date, boolean animate, boolean center) {
		view.setDate(date, animate, center);
		return (self) this;
	}

	/**
	 * Gets the first day of week.
	 * 
	 * @return The first day of the week conforming to the CalendarView APIs.
	 */
	public int firstWeekDay() {
		return view.getFirstDayOfWeek();
	}

	/**
	 * Sets the first day of week.
	 * 
	 * @param firstDayOfWeek
	 *            The first day of the week conforming to the CalendarView APIs.
	 * @return self
	 */
	public self firstWeekDay(int firstDayOfWeek) {
		view.setFirstDayOfWeek(firstDayOfWeek);
		return (self) this;
	}

	/**
	 * Gets the maximal date supported by this CalendarView in milliseconds
	 * since January 1, 1970 00:00:00 in getDefault() time zone.
	 * 
	 * @return The maximal supported date.
	 */
	public long maxDate() {
		return view.getMaxDate();
	}

	/**
	 * Sets the maximal date supported by this CalendarView in milliseconds
	 * since January 1, 1970 00:00:00 in getDefault() time zone.
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
	 * Gets the minimal date supported by this CalendarView in milliseconds
	 * since January 1, 1970 00:00:00 in getDefault() time zone.
	 * 
	 * @return The minimal supported date.
	 */
	public long minDate() {
		return view.getMinDate();
	}

	/**
	 * Sets the minimal date supported by this CalendarView in milliseconds
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
	 * Gets the color for the dates in the focused month.
	 * 
	 * @param focused
	 *            true to return the color for a focused month
	 * @return The focused month date color.
	 */
	public int monthColor(boolean focused) {
		if (focused)
			return view.getFocusedMonthDateColor();
		else
			return view.getUnfocusedMonthDateColor();
	}

	/**
	 * Sets the color for the dates of the focused month.
	 * 
	 * @param color
	 *            The focused month date color.
	 * @param focused
	 *            true if this applies to a focused month
	 * @return self
	 */
	public self monthColor(int color, boolean focused) {
		if (focused)
			view.setFocusedMonthDateColor(color);
		else
			view.setUnfocusedMonthDateColor(color);
		return (self) this;
	}

	/**
	 * Gets the drawable for the vertical bar shown at the beginning and at the
	 * end of the selected date.
	 * 
	 * @return The vertical bar drawable.
	 */
	public Drawable selectedDateBar() {
		return view.getSelectedDateVerticalBar();
	}

	/**
	 * Sets the drawable for the vertical bar shown at the beginning and at the
	 * end of the selected date.
	 * 
	 * @param resId
	 *            The vertical bar drawable resource id.
	 * @return self
	 */
	public self selectedDateBar(int resId) {
		view.setSelectedDateVerticalBar(resId);
		return (self) this;
	}

	/**
	 * Sets the drawable for the vertical bar shown at the beginning and at the
	 * end of the selected date.
	 * 
	 * @param drawable
	 *            The vertical bar drawable.
	 * @return self
	 */
	public self selectedDateBar(Drawable drawable) {
		view.setSelectedDateVerticalBar(drawable);
		return (self) this;
	}

	/**
	 * Gets the background color for the selected week.
	 * 
	 * @return The week background color.
	 */
	public int selectedWeekBgColor() {
		return view.getSelectedWeekBackgroundColor();
	}

	/**
	 * Sets the background color for the selected week.
	 * 
	 * @param color
	 *            The week background color.
	 * @return self
	 */
	public self selectedWeekBgColor(int color) {
		view.setSelectedWeekBackgroundColor(color);
		return (self) this;
	}

	/**
	 * Sets whether to show the week number.
	 * 
	 * @param showWeekNumber
	 *            True to show the week number.
	 * @return self
	 */
	public self weekNums(boolean showWeekNumber) {
		view.setShowWeekNumber(showWeekNumber);
		return (self) this;
	}

	/**
	 * Gets whether to show the week number.
	 * 
	 * @return True if showing the week number.
	 */
	public boolean weekNums() {
		return view.getShowWeekNumber();
	}

	/**
	 * Gets the number of weeks to be shown.
	 * 
	 * @return The shown week count.
	 */
	public int weeks() {
		return view.getShownWeekCount();
	}

	/**
	 * Sets the number of weeks to be shown.
	 * 
	 * @param shownWeekCount
	 *            The shown week count.
	 * @return self
	 */
	public self weeks(int shownWeekCount) {
		view.setShownWeekCount(shownWeekCount);
		return (self) this;
	}

	/**
	 * Gets the text appearance for the week day abbreviation of the calendar
	 * header.
	 * 
	 * @return The text appearance resource id.
	 */
	public int weekDayAppearance() {
		return view.getWeekDayTextAppearance();
	}

	/**
	 * Sets the text appearance for the week day abbreviation of the calendar
	 * header.
	 * 
	 * @param resId
	 *            The text appearance resource id.
	 * @return self
	 */
	public self weekDayAppearance(int resId) {
		view.setWeekDayTextAppearance(resId);
		return (self) this;
	}

	/**
	 * Gets the color for the week numbers.
	 * 
	 * @return The week number color.
	 */
	public int weekNumColor() {
		return view.getWeekNumberColor();
	}

	/**
	 * Sets the color for the week numbers.
	 * 
	 * @param color
	 *            The week number color.
	 * @return self
	 */
	public self weekNumColor(int color) {
		view.setWeekNumberColor(color);
		return (self) this;
	}

	/**
	 * Gets the color for the separator line between weeks.
	 * 
	 * @return The week separator color.
	 */
	public int weekDivColor() {
		return view.getWeekSeparatorLineColor();
	}

	/**
	 * Sets the color for the separator line between weeks.
	 * 
	 * @param color
	 *            The week separator color.
	 * @return self
	 */
	public self weekDivColor(int color) {
		view.setWeekSeparatorLineColor(color);
		return (self) this;
	}
}
