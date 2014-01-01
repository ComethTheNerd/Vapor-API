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

import android.support.v4.view.PagerTabStrip;

// Checked: 061220122038

/**
 * Fluent Vapor companion to PagerTabStrip, an interactive indicator of the
 * current, next, and previous pages of a ViewPager.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from PagerTabStrip
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporPagerTabStrip<T extends PagerTabStrip, self extends VaporPagerTabStrip<T, self>>
		extends VaporPagerTitleStrip<T, self> {

	public VaporPagerTabStrip(int id) {
		super(id);
	}

	public VaporPagerTabStrip(T pagerTabStrip) {
		super(pagerTabStrip);
	}

	/**
	 * Returns the current tab indicator color as an 0xRRGGBB value.
	 * 
	 * @return The current tab indicator color as an 0xRRGGBB value.
	 */
	public int indicatorColor() {
		return view.getTabIndicatorColor();
	}

	/**
	 * Set the color of the tab indicator bar.
	 * 
	 * @param indicatorColor
	 *            Color to set as an 0xRRGGBB value. The high byte (alpha) is
	 *            ignored.
	 * @return self
	 */
	public self indicatorColor(int indicatorColor) {
		view.setTabIndicatorColor(indicatorColor);
		return (self) this;
	}

	/**
	 * Set the color of the tab indicator bar from a color resource.
	 * 
	 * @param resId
	 *            Resource ID of a color resource to load
	 * @return self
	 */
	public self indicatorColorRes(int resId) {
		view.setTabIndicatorColorResource(resId);
		return (self) this;
	}

	/**
	 * Return whether or not this tab strip will draw a full-width underline.
	 * This defaults to true if no background is set.
	 * 
	 * @return true if this tab strip will draw a full-width underline in the
	 *         current tab indicator color.
	 */
	public boolean underlined() {
		return view.getDrawFullUnderline();
	}

	/**
	 * Set whether this tab strip should draw a full-width underline in the
	 * current tab indicator color.
	 * 
	 * @param drawFullUnderline
	 *            true to draw a full-width underline, false otherwise
	 * @return self
	 */
	public self underlined(boolean drawFullUnderline) {
		view.setDrawFullUnderline(drawFullUnderline);
		return (self) this;
	}

}
