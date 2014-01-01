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

import android.widget.TextClock;

/**
 * Fluent Vapor companion to TextClock, that can display the current date and/or
 * time as a formatted string.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from TextClock
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporTextClock<T extends TextClock, self extends VaporTextClock<T, self>>
		extends VaporTextView<T, self> {

	public VaporTextClock(int id) {
		super(id);
	}

	public VaporTextClock(T textClock) {
		super(textClock);
	}

	/**
	 * Returns the formatting pattern used to display the date and/or time in
	 * 12-hour mode. The formatting pattern syntax is described in DateFormat.
	 * 
	 * @return A CharSequence or null.
	 */
	public CharSequence format12h() {
		return view.getFormat12Hour();
	}

	/**
	 * Specifies the formatting pattern used to display the date and/or time in
	 * 12-hour mode. The formatting pattern syntax is described in DateFormat.
	 * If this pattern is set to null, format24h() will be used even in 12-hour
	 * mode. If both 24-hour and 12-hour formatting patterns are set to null,
	 * DEFAULT_FORMAT_24_HOUR and DEFAULT_FORMAT_12_HOUR will be used instead.
	 * 
	 * @param format
	 *            A date/time formatting pattern as described in DateFormat
	 * @return self
	 */
	public self format12h(CharSequence format) {
		view.setFormat12Hour(format);
		return (self) this;
	}

	/**
	 * Returns the formatting pattern used to display the date and/or time in
	 * 24-hour mode. The formatting pattern syntax is described in DateFormat.
	 * 
	 * @return A CharSequence or null.
	 */
	public CharSequence format24h() {
		return view.getFormat24Hour();
	}

	/**
	 * Specifies the formatting pattern used to display the date and/or time in
	 * 24-hour mode. The formatting pattern syntax is described in DateFormat.
	 * If this pattern is set to null, format12h() will be used even in 24-hour
	 * mode. If both 24-hour and 12-hour formatting patterns are set to null,
	 * DEFAULT_FORMAT_24_HOUR and DEFAULT_FORMAT_12_HOUR will be used instead.
	 * 
	 * @param format
	 *            A date/time formatting pattern as described in DateFormat
	 * @return self
	 */
	public self format24h(CharSequence format) {
		view.setFormat24Hour(format);
		return (self) this;
	}

	/**
	 * Indicates whether the system is currently using the 24-hour mode. When
	 * the system is in 24-hour mode, this view will use the pattern returned by
	 * format24h(). In 12-hour mode, the pattern returned by format12h() is used
	 * instead. If either one of the formats is null, the other format is used.
	 * If both formats are null, the default values DEFAULT_FORMAT_12_HOUR and
	 * DEFAULT_FORMAT_24_HOUR are used instead.
	 * 
	 * @return true if time should be displayed in 24-hour format, false if it
	 *         should be displayed in 12-hour format.
	 */
	public boolean formatted24h() {
		return view.is24HourModeEnabled();
	}

	/**
	 * Indicates which time zone is currently used by this view.
	 * 
	 * @return The ID of the current time zone or null if the default time zone,
	 *         as set by the user, must be used
	 */
	public String timeZone() {
		return view.getTimeZone();
	}

	/**
	 * Sets the specified time zone to use in this clock. When the time zone is
	 * set through this method, system time zone changes (when the user sets the
	 * time zone in settings for instance) will be ignored.
	 * 
	 * @param timeZone
	 *            The desired time zone's ID as specified in TimeZone or null to
	 *            user the time zone specified by the user (system time zone)
	 * @return self
	 */
	public self timeZone(String timeZone) {
		view.setTimeZone(timeZone);
		return (self) this;
	}
}
