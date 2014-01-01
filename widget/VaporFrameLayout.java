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
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;

/**
 * Fluent Vapor companion to FrameLayout, designed to block out an area on the
 * screen to display a single item. Generally, FrameLayout should be used to
 * hold a single child view, because it can be difficult to organize child views
 * in a way that's scalable to different screen sizes without the children
 * overlapping each other. You can, however, add multiple children to a
 * FrameLayout and control their position within the FrameLayout by assigning
 * gravity to each child, using the android:layout_gravity attribute.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from FrameLayout
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporFrameLayout<T extends FrameLayout, self extends VaporFrameLayout<T, self>>
		extends VaporViewGroup<T, self> {

	public VaporFrameLayout(int id) {
		super(id);
	}

	public VaporFrameLayout(T frameLayout) {
		super(frameLayout);
	}

	public FrameLayout.LayoutParams layoutParams(int width, int height) {
		return new FrameLayout.LayoutParams(width, height);
	}

	/**
	 * Returns the drawable used as the foreground of this VaporFrameLayout. The
	 * foreground drawable, if non-null, is always drawn on top of the children.
	 * 
	 * @return A Drawable or null if no foreground was set.
	 */
	public Drawable fg() {
		return view.getForeground();
	}

	/**
	 * Supply a Drawable that is to be rendered on top of all of the child views
	 * in the frame layout. Any padding in the Drawable will be taken into
	 * account by ensuring that the children are inset to be placed inside of the
	 * padding area.
	 * 
	 * @param foregroundDrawable
	 *            The Drawable to be drawn on top of the children.
	 * @return this
	 */
	public self fg(Drawable foregroundDrawable) {
		view.setForeground(foregroundDrawable);
		return (self) this;
	}

	/**
	 * Describes how the foreground is positioned.
	 * 
	 * @return foreground gravity
	 */
	public int fgGravity() {
		return view.getForegroundGravity();
	}

	/**
	 * Describes how the foreground is positioned. Defaults to START and TOP.
	 * 
	 * @param foregroundGravity
	 *            See Gravity
	 * @return this
	 */
	public self fgGravity(int foregroundGravity) {
		view.setForegroundGravity(foregroundGravity);
		return (self) this;
	}

	/**
	 * Determines whether all children, or just those in the VISIBLE or
	 * INVISIBLE state, are considered when measuring.
	 * 
	 * @return Whether all children are considered when measuring.
	 */
	public boolean measureChildren() {
		return view.getMeasureAllChildren();
	}

	/**
	 * Sets whether to consider all children, or just those in the VISIBLE or
	 * INVISIBLE state, when measuring. Defaults to false.
	 * 
	 * @param measureAllChildren
	 *            true to consider children marked GONE, false otherwise.
	 *            Default value is false.
	 * @return this
	 */
	public self measureChildren(boolean measureAllChildren) {
		view.setMeasureAllChildren(measureAllChildren);
		return (self) this;
	}

}
