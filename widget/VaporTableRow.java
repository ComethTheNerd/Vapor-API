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

import vapor.core.$;
import vapor.view.VaporView;
import android.view.View;
import android.widget.TableRow;

/**
 * Fluent Vapor companion to TableRow, a layout that arranges its children
 * horizontally. A TableRow should always be used as a child of a TableLayout.
 * If a TableRow's parent is not a TableLayout, the TableRow will behave as an
 * horizontal LinearLayout.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from TableRow
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporTableRow<T extends TableRow, self extends VaporTableRow<T, self>>
		extends VaporLinearLayout<T, self> {

	public VaporTableRow(int id) {
		super(id);
	}

	public VaporTableRow(T tableRow) {
		super(tableRow);
	}

	@Override
	public TableRow.LayoutParams layoutParams(int width, int height) {
		return new TableRow.LayoutParams(width, height);
	}

	/**
	 * Returns the view at the specified index. This can include virtual
	 * children.
	 * 
	 * @param childIndex
	 *            the child's index
	 * @return the child at the specified index
	 */
	@Override
	public VaporView<? extends View, ?> child(int childIndex) {
		return $.vapor(view.getVirtualChildAt(childIndex)); // intentional
															// override
	}

	/**
	 * Returns the virtual number of children. This number might be different
	 * than the actual number of children if the layout can hold virtual
	 * children. Refer to TableLayout and TableRow for an example.
	 * 
	 * @return the virtual number of children
	 */
	public int virtualChildCount() {
		return view.getVirtualChildCount();
	}
}
