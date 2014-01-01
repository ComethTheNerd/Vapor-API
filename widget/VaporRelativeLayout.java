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

import vapor.view.VaporViewGroup;
import android.widget.RelativeLayout;

/**
 * Fluent Vapor companion to RelativeLayout, a Layout where the positions of the
 * children can be described in relation to each other or to the parent.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from RelativeLayout
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporRelativeLayout<T extends RelativeLayout, self extends VaporRelativeLayout<T, self>>
		extends VaporViewGroup<T, self> {

	public VaporRelativeLayout(int id) {
		super(id);
	}

	public VaporRelativeLayout(T relativeLayout) {
		super(relativeLayout);
	}

	@Override
	public RelativeLayout.LayoutParams layoutParams(int width, int height) {
		return new RelativeLayout.LayoutParams(width, height);
	}

	/**
	 * Describes how the child views are positioned.
	 * 
	 * @return the gravity.
	 */
	public int gravity() {
		return view.getGravity();
	}

	/**
	 * Describes how the child views are positioned. Defaults to Gravity.LEFT |
	 * Gravity.TOP.
	 * 
	 * Note that since RelativeLayout considers the positioning of each child
	 * relative to one another to be significant, setting gravity will affect
	 * the positioning of all children as a single unit within the parent. This
	 * happens after children have been relatively positioned.
	 * 
	 * @param gravity
	 *            Standard gravity constant for placing an object within a
	 *            potentially larger container.
	 * @return self
	 */
	public self gravity(int gravity) {
		view.setGravity(gravity);
		return (self) this;
	}

	/**
	 * 
	 * @param horizontalGravity
	 * @return self
	 */
	public self xGravity(int horizontalGravity) {
		view.setHorizontalGravity(horizontalGravity);
		return (self) this;
	}

	/**
	 * Defines which View is ignored when the gravity is applied. This setting
	 * has no effect if the gravity is Gravity.LEFT | Gravity.TOP.
	 * 
	 * @param viewId
	 *            The id of the View to be ignored by gravity, or 0 if no View
	 *            should be ignored.
	 * @return self
	 */
	public self ignoreGravity(int viewId) {
		view.setIgnoreGravity(viewId);
		return (self) this;
	}

	/**
	 * 
	 * @param verticalGravity
	 * @return self
	 */
	public self yGravity(int verticalGravity) {
		view.setVerticalGravity(verticalGravity);
		return (self) this;
	}
}
