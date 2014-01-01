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

import android.widget.GridView;
import android.widget.ListAdapter;

/**
 * Fluent Vapor companion to GridView, a view that shows items in
 * two-dimensional scrolling grid. The items in the grid come from the
 * ListAdapter associated with this view.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from GridView
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporGridView<T extends GridView, self extends VaporGridView<T, self>>
		extends VaporAbsListView<T, self> {

	public VaporGridView(int id) {
		super(id);
	}

	public VaporGridView(T gridView) {
		super(gridView);
	}

	/**
	 * Returns the adapter currently associated with this widget.
	 * 
	 * @return The adapter used to provide this view's content.
	 */
	public ListAdapter adapter() {
		return view.getAdapter();
	}

	@Override
	public self adapter(ListAdapter adapter) {
		view.setAdapter(adapter);
		return (self) this;
	}

	/**
	 * Get the number of columns in the grid. Returns AUTO_FIT if the Grid has
	 * never been laid out.
	 * 
	 * @return
	 */
	public int cols() {
		return view.getNumColumns();
	}

	public self cols(int numberOfColumns) {
		view.setNumColumns(numberOfColumns);
		return (self) this;
	}

	/**
	 * Return the width of a column in the grid.
	 * 
	 * This may not be valid yet if a layout is pending.
	 * 
	 * @return The column width in pixels
	 */
	public int colWidth() {
		return view.getColumnWidth();
	}

	/**
	 * Set the width of columns in the grid.
	 * 
	 * @param columnWidth
	 *            The column width, in pixels.
	 * @return this
	 */
	public self colWidth(int columnWidth) {
		view.setColumnWidth(columnWidth);
		return (self) this;
	}

	/**
	 * Describes how the child views are horizontally aligned. Defaults to
	 * Gravity.LEFT
	 * 
	 * @return the gravity that will be applied to this grid's children
	 */
	public int gravity() {
		return view.getGravity();
	}

	/**
	 * Set the gravity for this grid. Gravity describes how the child views are
	 * horizontally aligned. Defaults to Gravity.LEFT
	 * 
	 * @param gravity
	 *            the gravity to apply to this grid's children
	 * @return this
	 */
	public self gravity(int gravity) {
		view.setGravity(gravity);
		return (self) this;
	}

	/**
	 * Returns the amount of horizontal spacing currently used between each item
	 * in the grid.
	 * 
	 * @return Current horizontal spacing between each item in pixels
	 */
	public int xSpacing() {
		return view.getHorizontalSpacing();
	}

	/**
	 * Set the amount of horizontal (x) spacing to place between each item in
	 * the grid.
	 * 
	 * @param horizontalSpacing
	 *            The amount of horizontal space between items, in pixels.
	 * @return this
	 */
	public self xSpacing(int horizontalSpacing) {
		view.setHorizontalSpacing(horizontalSpacing);
		return (self) this;
	}

	/**
	 * Return the requested width of a column in the grid.
	 * 
	 * @return The requested column width in pixels
	 */
	public int reqdColWidth() {
		return view.getRequestedColumnWidth();
	}

	/**
	 * Returns the requested amount of horizontal spacing between each item in
	 * the grid.
	 * 
	 * @return The currently requested horizontal spacing between items, in
	 *         pixels
	 */
	public int reqdXSpacing() {
		return view.getRequestedHorizontalSpacing();
	}

	public self itemSelected(int position) {
		view.setSelection(position);
		return (self) this;
	}

	/**
	 * Smoothly scroll to the specified adapter position offset. The view will
	 * scroll such that the indicated position is displayed.
	 * 
	 * @param offset
	 *            The amount to offset from the adapter position to scroll to.
	 * @return this
	 */
	public self smoothByOffset(int offset) {
		view.smoothScrollByOffset(offset);
		return (self) this;
	}

	/**
	 * 
	 * @return
	 */
	public int stretchMode() {
		return view.getStretchMode();
	}

	/**
	 * Control how items are stretched to fill their space.
	 * 
	 * @param stretchMode
	 *            Either NO_STRETCH, STRETCH_SPACING, STRETCH_SPACING_UNIFORM,
	 *            or STRETCH_COLUMN_WIDTH.
	 * @return this
	 */
	public self stretchMode(int stretchMode) {
		view.setStretchMode(stretchMode);
		return (self) this;
	}

	/**
	 * Returns the amount of vertical spacing between each item in the grid.
	 * 
	 * @return The vertical spacing between items in pixels
	 */
	public int ySpacing() {
		return view.getVerticalSpacing();
	}

	/**
	 * Set the amount of vertical (y) spacing to place between each item in the
	 * grid.
	 * 
	 * @param verticalSpacing
	 *            The amount of vertical space between items, in pixels.
	 * @return this
	 */
	public self ySpacing(int verticalSpacing) {
		view.setVerticalSpacing(verticalSpacing);
		return (self) this;
	}

}
