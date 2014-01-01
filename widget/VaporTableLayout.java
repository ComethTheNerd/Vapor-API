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

import android.widget.TableLayout;

/**
 * Fluent Vapor companion to TableLayout, a layout that arranges its children
 * into rows and columns. A TableLayout consists of a number of TableRow
 * objects, each defining a row (actually, you can have other children, which
 * will be explained below).
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from TableLayout
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporTableLayout<T extends TableLayout, self extends VaporTableLayout<T, self>>
		extends VaporLinearLayout<T, self> {

	public VaporTableLayout(int id) {
		super(id);
	}

	public VaporTableLayout(T tableLayout) {
		super(tableLayout);
	}

	@Override
	public TableLayout.LayoutParams layoutParams(int width, int height) {
		return new TableLayout.LayoutParams(width, height);
	}

	/**
	 * Returns the collapsed state of the specified column.
	 * 
	 * @param columnIndex
	 *            the index of the column
	 * @return true if the column is collapsed, false otherwise
	 */
	public boolean collapsed(int columnIndex) {
		return view.isColumnCollapsed(columnIndex);
	}

	/**
	 * Collapses or restores a given column. When collapsed, a column does not
	 * appear on screen and the extra space is reclaimed by the other columns. A
	 * column is collapsed/restored only when it belongs to a TableRow.
	 * 
	 * Calling this method requests a layout operation.
	 * 
	 * @param columnIndex
	 *            the index of the column
	 * @param isCollapsed
	 *            true if the column must be collapsed, false otherwise
	 * @return this
	 */
	public self collapsed(int columnIndex, boolean isCollapsed) {
		view.setColumnCollapsed(columnIndex, isCollapsed);
		return (self) this;
	}

	/**
	 * Indicates whether all columns are shrinkable or not.
	 * 
	 * @return true if all columns are shrinkable, false otherwise
	 */
	public boolean shrinkable() {
		return view.isShrinkAllColumns();
	}

	/**
	 * Convenience method to mark all columns as shrinkable.
	 * 
	 * @param shrinkAllColumns
	 *            true to mark all columns shrinkable
	 * @return this
	 */
	public self shrinkable(boolean shrinkAllColumns) {
		view.setShrinkAllColumns(shrinkAllColumns);
		return (self) this;
	}

	/**
	 * Returns the collapsed state of the specified column.
	 * 
	 * @param columnIndex
	 *            the index of the column
	 * @return true if the column is collapsed, false otherwise
	 */
	public boolean shrinkable(int columnIndex) {
		return view.isColumnShrinkable(columnIndex);
	}

	/**
	 * Makes the given column shrinkable or not. When a row is too wide, the
	 * table can reclaim extra space from shrinkable columns.
	 * 
	 * Calling this method requests a layout operation.
	 * 
	 * @param columnIndex
	 *            the index of the column
	 * @param isShrinkable
	 *            true if the column must be shrinkable, false otherwise.
	 *            Default is false.
	 * @return this
	 */
	public self shrinkable(int columnIndex, boolean isShrinkable) {
		view.setColumnShrinkable(columnIndex, isShrinkable);
		return (self) this;
	}

	/**
	 * Indicates whether all columns are stretchable or not.
	 * 
	 * @return true if all columns are stretchable, false otherwise
	 */
	public boolean stretchable() {
		return view.isStretchAllColumns();
	}

	/**
	 * Convenience method to mark all columns as stretchable.
	 * 
	 * @param stretchAllColumns
	 *            true to mark all columns stretchable
	 * @return this
	 */
	public self stretchable(boolean stretchAllColumns) {
		view.setStretchAllColumns(stretchAllColumns);
		return (self) this;
	}

	/**
	 * Returns whether the specified column is stretchable or not.
	 * 
	 * @param columnIndex
	 *            the index of the column
	 * @return true if the column is stretchable, false otherwise
	 */
	public boolean stretchable(int columnIndex) {
		return view.isColumnStretchable(columnIndex);
	}

	/**
	 * Makes the given column stretchable or not. When stretchable, a column
	 * takes up as much as available space as possible in its row.
	 * 
	 * Calling this method requests a layout operation.
	 * 
	 * @param columnIndex
	 *            the index of the column
	 * @param isStretchable
	 *            true if the column must be stretchable, false otherwise.
	 *            Default is false.
	 * @return this
	 */
	public self stretchable(int columnIndex, boolean isStretchable) {
		view.setColumnStretchable(columnIndex, isStretchable);
		return (self) this;
	}

}
