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

package vapor.support.v4.view;

import vapor.view.VaporViewGroup;
import android.support.v4.view.PagerTitleStrip;

// Checked: 061220122040

/**
 * Fluent Vapor companion to PagerTitleStrip, a non-interactive indicator of the
 * current, next, and previous pages of a ViewPager.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from PagerTitleStrip
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporPagerTitleStrip<T extends PagerTitleStrip, self extends VaporPagerTitleStrip<T, self>>
		extends VaporViewGroup<T, self> {

	public VaporPagerTitleStrip(int id) {
		super(id);
	}

	public VaporPagerTitleStrip(T pagerTitleStrip) {
		super(pagerTitleStrip);
	}

	/**
	 * Set the alpha value used for non-primary page titles.
	 * 
	 * @param nonPrimaryAlpha
	 *            Opacity value in the range 0-1f
	 * @return self
	 */
	public self alphaNonPrim(float nonPrimaryAlpha) {
		view.setNonPrimaryAlpha(nonPrimaryAlpha);
		return (self) this;
	}

	/**
	 * Set the color value used as the base color for all displayed page titles.
	 * Alpha will be ignored for non-primary page titles. See
	 * alphaNonPrim(float).
	 * 
	 * @param textColor
	 *            Color hex code in 0xAARRGGBB format
	 * @return self
	 */
	public self color(int textColor) {
		view.setTextColor(textColor);
		return (self) this;
	}

	/**
	 * Set the default text size to a given unit and value. See TypedValue for
	 * the possible dimension units.
	 * 
	 * Example: to set the text size to 14px, use
	 * setTextSize(TypedValue.COMPLEX_UNIT_PX, 14);
	 * 
	 * @param unit
	 *            The desired dimension unit
	 * @param textSize
	 *            The desired size in the given units
	 * @return self
	 */
	public self fontSize(int unit, float textSize) {
		view.setTextSize(unit, textSize);
		return (self) this;
	}

	/**
	 * Set the Gravity used to position text within the title strip. Only the
	 * vertical gravity component is used.
	 * 
	 * @param gravity
	 *            Gravity constant for positioning title text
	 * @return self
	 */
	public self gravity(int gravity) {
		view.setGravity(gravity);
		return (self) this;
	}

	/**
	 * Returns the required spacing between title segments in pixels
	 * 
	 * @return The required spacing between title segments in pixels
	 */
	public int spacing() {
		return view.getTextSpacing();
	}

	/**
	 * Set the required spacing between title segments.
	 * 
	 * @param textSpacing
	 *            Spacing between each title displayed in pixels
	 * @return self
	 */
	public self spacing(int textSpacing) {
		view.setTextSpacing(textSpacing);
		return (self) this;
	}

}
