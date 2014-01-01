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
import android.view.ViewGroup;
import android.widget.GridLayout;

/**
 * Fluent Vapor companion to GridLayout, a layout that places its children in a
 * rectangular grid. The grid is composed of a set of infinitely thin lines that
 * separate the viewing area into cells.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from GridLayout
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporGridLayout<T extends GridLayout, self extends VaporGridLayout<T, self>>
		extends VaporViewGroup<T, self> {

	public VaporGridLayout(int id) {
		super(id);
	}

	public VaporGridLayout(T gridLayout) {
		super(gridLayout);
	}

	public GridLayout.LayoutParams layoutParams(int width, int height) {
		return new GridLayout.LayoutParams(new ViewGroup.LayoutParams(width,
				height));
	}

	/**
	 * Returns the alignment mode.
	 * 
	 * @return the alignment mode; either ALIGN_BOUNDS or ALIGN_MARGINS
	 */
	public int alignMode() {
		return view.getAlignmentMode();
	}

	/**
	 * Sets the alignment mode to be used for all of the alignments between the
	 * children of this container.
	 * 
	 * @param alignmentMode
	 *            either ALIGN_BOUNDS or ALIGN_MARGINS
	 * @return self
	 */
	public self alignMode(int alignmentMode) {
		view.setAlignmentMode(alignmentMode);
		return (self) this;
	}

	/**
	 * Returns the current number of columns. This is either the last value that
	 * was set with colCount(int) or, if no such value was set, the maximum
	 * value of each the upper bounds defined in columnSpec.
	 * 
	 * @return the current number of columns
	 */
	public int cols() {
		return view.getColumnCount();
	}

	/**
	 * colCount is used only to generate default column/column indices when they
	 * are not specified by a component's layout parameters.
	 * 
	 * @param columnCount
	 *            the number of columns.
	 * @return self
	 */
	public self cols(int columnCount) {
		view.setColumnCount(columnCount);
		return (self) this;
	}

	/**
	 * Returns whether or not this GridLayout will allocate default margins when
	 * no corresponding layout parameters are defined.
	 * 
	 * @return true if default margins should be allocated
	 */
	public boolean defMargins() {
		return view.getUseDefaultMargins();
	}

	/**
	 * When true, GridLayout allocates default margins around children based on
	 * the child's visual characteristics. Each of the margins so defined may be
	 * independently overridden by an assignment to the appropriate layout
	 * parameter.
	 * 
	 * When false, the default value of all margins is zero.
	 * 
	 * @param useDefaultMargins
	 *            use true to make GridLayout allocate default margins
	 * @return self
	 */
	public self defMargins(boolean useDefaultMargins) {
		view.setUseDefaultMargins(useDefaultMargins);
		return (self) this;
	}

	/**
	 * Returns whether or not column boundaries are ordered by their grid
	 * indices.
	 * 
	 * @return true if column boundaries must appear in the order of their
	 *         indices, false otherwise
	 */
	public boolean keepColOrder() {
		return view.isColumnOrderPreserved();
	}

	/**
	 * When this property is true, GridLayout is forced to place the column
	 * boundaries so that their associated grid indices are in ascending order
	 * in the view.
	 * 
	 * @param columnOrderPreserved
	 *            use true to force GridLayout to respect the order of column
	 *            boundaries.
	 * @return self
	 */
	public self keepColOrder(boolean columnOrderPreserved) {
		view.setColumnOrderPreserved(columnOrderPreserved);
		return (self) this;
	}

	/**
	 * Returns whether or not row boundaries are ordered by their grid indices.
	 * 
	 * @return true if row boundaries must appear in the order of their indices,
	 *         false otherwise
	 */
	public boolean keepRowOrder() {
		return view.isRowOrderPreserved();
	}

	/**
	 * When this property is true, GridLayout is forced to place the row
	 * boundaries so that their associated grid indices are in ascending order
	 * in the view.
	 * 
	 * @param rowOrderPreserved
	 *            true to force GridLayout to respect the order of row
	 *            boundaries
	 * @return self
	 */
	public self keepRowOrder(boolean rowOrderPreserved) {
		view.setRowOrderPreserved(rowOrderPreserved);
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
	 * GridLayout uses the orientation property for two purposes: To control the
	 * 'direction' in which default row/column indices are generated when they
	 * are not specified in a component's layout parameters. To control which
	 * axis should be processed first during the layout operation: when
	 * orientation is HORIZONTAL the horizontal axis is laid out first.
	 * 
	 * @param orientation
	 *            either HORIZONTAL or VERTICAL
	 * @return self
	 */
	public self orientation(int orientation) {
		view.setOrientation(orientation);
		return (self) this;
	}

	/**
	 * Returns the current number of rows. This is either the last value that
	 * was set with setRowCount(int) or, if no such value was set, the maximum
	 * value of each the upper bounds defined in rowSpec.
	 * 
	 * @return the current number of rows
	 */
	public int rows() {
		return view.getRowCount();
	}

	/**
	 * RowCount is used only to generate default row/column indices when they
	 * are not specified by a component's layout parameters.
	 * 
	 * @param rowCount
	 *            the number of rows
	 * @return self
	 */
	public self rows(int rowCount) {
		view.setRowCount(rowCount);
		return (self) this;
	}

	/**
	 * Return a Spec, spec, where: spec.span = [start, start + 1]
	 * 
	 * @param start
	 *            the start index
	 * @return
	 */
	public static GridLayout.Spec spec(int start) {
		return GridLayout.spec(start);
	}

	/**
	 * Return a Spec, spec, where: spec.span = [start, start + size]
	 * 
	 * @param start
	 * @param size
	 * @return
	 */
	public static GridLayout.Spec spec(int start, int size) {
		return GridLayout.spec(start, size);
	}

	/**
	 * Return a Spec, spec, where: spec.span = [start, start + 1] spec.alignment
	 * = alignment
	 * 
	 * @param start
	 *            the start index
	 * @param alignment
	 *            the alignment
	 * @return
	 */
	public static GridLayout.Spec spec(int start, GridLayout.Alignment alignment) {
		return GridLayout.spec(start, alignment);
	}

	/**
	 * Return a Spec, spec, where: spec.span = [start, start + size]
	 * spec.alignment = alignment
	 * 
	 * @param start
	 *            the start
	 * @param size
	 *            the size
	 * @param alignment
	 *            the alignment
	 * @return self
	 */
	public static GridLayout.Spec spec(int start, int size,
			GridLayout.Alignment alignment) {
		return GridLayout.spec(start, size, alignment);
	}

}
