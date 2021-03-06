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

/*This file has been autogenerated by the Vapid Tool v1.0 @ 22/12/2012 22:43:14*/
package vapor.widget;

import android.widget.CalendarView;

/**
 * Fluent variadic Vapor companion to CalendarView, a widget for displaying and
 * selecting dates. The range of dates supported by this calendar is
 * configurable. A user can select a date by taping on it and can scroll and
 * fling the calendar to a desired date.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from CalendarView
 * @param <X>
 *            A Vapor type derived from VCalendarView
 */
public class VXCalendarView<T extends CalendarView, X extends VaporCalendarView<T, ?>>
		extends VaporXCalendarView<T, X, VXCalendarView<T, X>> {

	public VXCalendarView(Integer... ids) {
		super(ids);
	}

	public VXCalendarView(T... calendarViews) {
		super(calendarViews);
	}

	public VXCalendarView(X... vaporCalendarViews) {
		super(vaporCalendarViews);
	}

	public VXCalendarView(VaporXCalendarView<T, X, ?>... vaporXCalendarViews) {
		super(vaporXCalendarViews);
	}

	public VXCalendarView(Object... calendarViewItems) {
		super(calendarViewItems);
	}

}