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
import android.widget.LinearLayout;

/**
 * Fluent Vapor companion to LinearLayout, a Layout that arranges its children
 * in a single column or a single row. The direction of the row can be set by
 * calling orientation(int). You can also specify gravity, which specifies the
 * alignment of all the child elements by calling gravity(int) or specify that
 * specific children grow to fill up any remaining space in the layout by
 * setting the weight member of LinearLayout.LayoutParams. The default
 * orientation is horizontal.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from LinearLayout
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporLinearLayout<T extends LinearLayout, self extends VaporLinearLayout<T, self>>
		extends VaporViewGroup<T, self> {

	public VaporLinearLayout(int id) {
		super(id);
	}

	public VaporLinearLayout(T linearLayout) {
		super(linearLayout);
	}

	public LinearLayout.LayoutParams layoutParams(int width, int height) {
		return new LinearLayout.LayoutParams(width, height);
	}

	/**
	 * Indicates whether widgets contained within this layout are aligned on
	 * their baseline or not.
	 * 
	 * @return true when widgets are baseline-aligned, false otherwise
	 */
	public boolean baselineAligned() {
		return view.isBaselineAligned();
	}

	/**
	 * Defines whether widgets contained in this layout are baseline-aligned or
	 * not.
	 * 
	 * @param baselineAligned
	 *            true to align widgets on their baseline, false otherwise
	 * @return this
	 */
	public self baselineAligned(boolean baselineAligned) {
		view.setBaselineAligned(baselineAligned);
		return (self) this;
	}

	/**
	 * Returns the index of the child that will be used if this layout is part
	 * of a larger layout that is baseline aligned, or -1 if none has been set.
	 * 
	 * @return The index of the child that will be used if this layout is part
	 *         of a larger layout that is baseline aligned, or -1 if none has
	 *         been set.
	 */
	public int baselineAlignedChild() {
		return view.getBaselineAlignedChildIndex();
	}

	/**
	 * Set the index of the child that will be used if this layout is part of a
	 * larger layout that is baseline aligned.
	 * 
	 * @param childIndex
	 *            The index of the child that will be used if this layout is
	 *            part of a larger layout that is baseline aligned.
	 * @return this
	 */
	public self baselineAlignedChild(int childIndex) {
		view.setBaselineAlignedChildIndex(childIndex);
		return (self) this;
	}

	/**
	 * Returns the divider Drawable that will divide each item.
	 * 
	 * @return the divider Drawable that will divide each item.
	 */
	public Drawable div() {
		return view.getDividerDrawable();
	}

	/**
	 * Set a drawable to be used as a divider between items.
	 * 
	 * @param dividerDrawable
	 *            Drawable that will divide each item.
	 * @return this
	 */
	public self div(Drawable dividerDrawable) {
		view.setDividerDrawable(dividerDrawable);
		return (self) this;
	}

	/**
	 * Get the padding size used to inset dividers in pixels
	 * 
	 * @return the padding size used to inset dividers in pixels
	 */
	public int divPad() {
		return view.getDividerPadding();
	}

	/**
	 * Set padding displayed on both ends of dividers.
	 * 
	 * @param dividerPadding
	 *            Padding value in pixels that will be applied to each end
	 * @return this
	 */
	public self divPad(int dividerPadding) {
		view.setDividerPadding(dividerPadding);
		return (self) this;
	}

	/**
	 * A flag set indicating how dividers should be shown around items.
	 * 
	 * @return
	 */
	public int divPos() {
		return view.getShowDividers();
	}

	/**
	 * Set how dividers should be shown between items in this layout
	 * 
	 * @param showDividers
	 *            One or more of SHOW_DIVIDER_BEGINNING, SHOW_DIVIDER_MIDDLE, or
	 *            SHOW_DIVIDER_END, or SHOW_DIVIDER_NONE to show no dividers.
	 * @return this
	 */
	public self divPos(int showDividers) {
		view.setShowDividers(showDividers);
		return (self) this;
	}

	/**
	 * Describes how the child views are positioned. Defaults to GRAVITY_TOP. If
	 * this layout has a VERTICAL orientation, this controls where all the child
	 * views are placed if there is extra vertical space. If this layout has a
	 * HORIZONTAL orientation, this controls the alignment of the children.
	 * 
	 * @param gravity
	 *            One of the standard GRAVITY constants for placing an object
	 *            within a potentially larger container.
	 * @return this
	 */
	public self gravity(int gravity) {
		view.setGravity(gravity);
		return (self) this;
	}

	/**
	 * 
	 * @param horizontalGravity
	 * @return this
	 */
	public self xGravity(int horizontalGravity) {
		view.setHorizontalGravity(horizontalGravity);
		return (self) this;
	}

	/**
	 * When true, all children with a weight will be considered having the
	 * minimum size of the largest child. If false, all children are measured
	 * normally.
	 * 
	 * @return True to measure children with a weight using the minimum size of
	 *         the largest child, false otherwise.
	 */
	public boolean measuredWithLargestChild() {
		return view.isMeasureWithLargestChildEnabled();
	}

	/**
	 * When set to true, all children with a weight will be considered having
	 * the minimum size of the largest child. If false, all children are
	 * measured normally. Disabled by default.
	 * 
	 * @param measureWithLargestChild
	 *            True to measure children with a weight using the minimum size
	 *            of the largest child, false otherwise.
	 * @return this
	 */
	public self measuredWithLargestChild(boolean measureWithLargestChild) {
		view.setMeasureWithLargestChildEnabled(measureWithLargestChild);
		return (self) this;
	}

	/**
	 * Returns the current orientation.
	 * 
	 * @return either HORIZONTAL or VERTICAL
	 */
	public int orientation() {
		return view.getOrientation();
	}

	/**
	 * Should the layout be a column or a row.
	 * 
	 * @param orientation
	 *            Pass HORIZONTAL or VERTICAL. Default value is HORIZONTAL.
	 * @return this
	 */
	public self orientation(int orientation) {
		view.setOrientation(orientation);
		return (self) this;
	}

	/**
	 * 
	 * @param verticalGravity
	 * @return this
	 */
	public self yGravity(int verticalGravity) {
		view.setVerticalGravity(verticalGravity);
		return (self) this;
	}

	/**
	 * Returns the desired weights sum.
	 * 
	 * @return A number greater than 0.0f if the weight sum is defined, or a
	 *         number lower than or equals to 0.0f if not weight sum is to be
	 *         used.
	 */
	public float weightSum() {
		return view.getWeightSum();
	}

	/**
	 * Defines the desired weights sum. If unspecified the weights sum is
	 * computed at layout time by adding the layout_weight of each child. This
	 * can be used for instance to give a single child 50% of the total available
	 * space by giving it a layout_weight of 0.5 and setting the weightSum to
	 * 1.0.
	 * 
	 * @param weigthSum
	 *            a number greater than 0.0f, or a number lower than or equals
	 *            to 0.0f if the weight sum should be computed from the
	 *            children's layout_weight
	 * @return this
	 */
	public self weightSum(float weightSum) {
		view.setWeightSum(weightSum);
		return (self) this;
	}
}
