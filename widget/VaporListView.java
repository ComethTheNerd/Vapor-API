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

import vapor.view.VaporView;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Fluent Vapor companion to ListView, a view that shows items in a vertically
 * scrolling list. The items come from the ListAdapter associated with this
 * view.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from ListView
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporListView<T extends ListView, self extends VaporListView<T, self>>
		extends VaporAbsListView<T, self> {

	public VaporListView(int id) {
		super(id);
	}

	public VaporListView(T listView) {
		super(listView);
	}

	@Override
	public ListAdapter adapter() {
		return view.getAdapter();
	}

	@Override
	public self adapter(ListAdapter adapter) {
		view.setAdapter(adapter);
		return (self) this;
	}

	/**
	 * Returns the drawable that will be drawn between each item in the list.
	 * 
	 * @return the current drawable drawn between list elements
	 */
	public Drawable div() {
		return view.getDivider();
	}

	/**
	 * Sets the drawable that will be drawn between each item in the list. If
	 * the drawable does not have an intrinsic height, you should also call
	 * divHeight(int)
	 * 
	 * @param dividerDrawable
	 *            The drawable to use.
	 * @return self
	 */
	public self div(Drawable dividerDrawable) {
		view.setDivider(dividerDrawable);
		return (self) this;
	}

	/**
	 * Returns the height of the divider that will be drawn between each item in
	 * the list.
	 * 
	 * @return the height of the divider that will be drawn between each item in
	 *         the list.
	 */
	public int divHeight() {
		return view.getDividerHeight();
	}

	/**
	 * Sets the height of the divider that will be drawn between each item in
	 * the list. Calling this will override the intrinsic height as set by
	 * div(Drawable)
	 * 
	 * @param dividerHeight
	 *            The new height of the divider in pixels.
	 * @return self
	 */
	public self divHeight(int dividerHeight) {
		view.setDividerHeight(dividerHeight);
		return (self) this;
	}

	/**
	 * Returns whether the views created by the ListAdapter can contain
	 * focusable items.
	 * 
	 * @return Whether the views created by the ListAdapter can contain
	 *         focusable items.
	 */
	public boolean focusableItems() {
		return view.getItemsCanFocus();
	}

	/**
	 * Indicates that the views created by the ListAdapter can contain focusable
	 * items.
	 * 
	 * @param itemsCanFocus
	 *            true if items can get focus, false otherwise
	 * @return self
	 */
	public self focusableItems(boolean itemsCanFocus) {
		view.setItemsCanFocus(itemsCanFocus);
		return (self) this;
	}

	/**
	 * Add a fixed view to appear at the bottom of the list. If footer(...) is
	 * called more than once, the views will appear in the order they were
	 * added. Views added using this call can take focus if they want.
	 * 
	 * NOTE: Call this before calling adapter(ListAdapter). This is so ListView
	 * can wrap the supplied cursor with one that will also account for header
	 * and footer views.
	 * 
	 * @param view
	 *            The view to add.
	 * @return self
	 */
	public self footer(VaporView<? extends View, ?> view) {
		this.view.addFooterView(view.view());
		return (self) this;
	}

	/**
	 * Add a fixed view to appear at the bottom of the list. If footer(...) is
	 * called more than once, the views will appear in the order they were
	 * added. Views added using this call can take focus if they want.
	 * 
	 * NOTE: Call this before calling adapter(ListAdapter). This is so ListView
	 * can wrap the supplied cursor with one that will also account for header
	 * and footer views.
	 * 
	 * @param view
	 *            The view to add.
	 * @param data
	 *            Data to associate with this view
	 * @param isSelectable
	 *            true if the footer view can be selected
	 * @return self
	 */
	public self footer(VaporView<? extends View, ?> view, Object data,
			boolean isSelectable) {
		this.view.addFooterView(view.view(), data, isSelectable);
		return (self) this;
	}

	/**
	 * Returns the number of footer views in the list. Footer views are special
	 * views at the bottom of the list that should not be recycled during a
	 * layout.
	 * 
	 * @return The number of footer views, 0 in the default implementation.
	 */
	public int footerCount() {
		return view.getFooterViewsCount();
	}

	/**
	 * Enables or disables the drawing of the divider for footer views.
	 * 
	 * @param footerDividersEnabled
	 *            True to draw the footers, false otherwise.
	 * @return self
	 */
	public self footerDivs(boolean footerDividersEnabled) {
		view.setFooterDividersEnabled(footerDividersEnabled);
		return (self) this;
	}

	/**
	 * Add a fixed view to appear at the top of the list. If header(...) is
	 * called more than once, the views will appear in the order they were
	 * added. Views added using this call can take focus if they want.
	 * 
	 * NOTE: Call this before calling adapter(ListAdapter). This is so ListView
	 * can wrap the supplied cursor with one that will also account for header
	 * and footer views.
	 * 
	 * @param view
	 *            The view to add.
	 * @return self
	 */
	public self header(VaporView<? extends View, ?> view) {
		this.view.addHeaderView(view.view());
		return (self) this;
	}

	/**
	 * Add a fixed view to appear at the top of the list. If header(...) is
	 * called more than once, the views will appear in the order they were
	 * added. Views added using this call can take focus if they want.
	 * 
	 * NOTE: Call this before calling adapter(ListAdapter). This is so ListView
	 * can wrap the supplied cursor with one that will also account for header
	 * and footer views.
	 * 
	 * @param view
	 *            The view to add.
	 * @param data
	 *            Data to associate with this view
	 * @param isSelectable
	 *            whether the item is selectable
	 * @return self
	 */
	public self header(VaporView<? extends View, ?> view, Object data,
			boolean isSelectable) {
		this.view.addHeaderView(view.view(), data, isSelectable);
		return (self) this;
	}

	/**
	 * Returns the number of header views in the list. Header views are special
	 * views at the top of the list that should not be recycled during a layout.
	 * 
	 * @return The number of header views, 0 in the default implementation.
	 */
	public int headerCount() {
		return view.getHeaderViewsCount();
	}

	/**
	 * Enables or disables the drawing of the divider for header views.
	 * 
	 * @param headerDividersEnabled
	 *            True to draw the headers, false otherwise.
	 * @return self
	 */
	public self headerDivs(boolean headerDividersEnabled) {
		view.setHeaderDividersEnabled(headerDividersEnabled);
		return (self) this;
	}

	/**
	 * Returns the maximum amount a list view will scroll in response to an
	 * arrow event.
	 * 
	 * @return The maximum amount a list view will scroll in response to an
	 *         arrow event.
	 */
	public int maxScroll() {
		return view.getMaxScrollAmount();
	}

	/**
	 * Returns the drawable that will be drawn below all other list content
	 * 
	 * @return The drawable that will be drawn below all other list content
	 */
	public Drawable overScrollFooter() {
		return view.getOverscrollFooter();
	}

	/**
	 * Sets the drawable that will be drawn below all other list content. This
	 * area can become visible when the user overscrolls the list, or when the
	 * list's content does not fully fill the container area.
	 * 
	 * @param footer
	 *            The drawable to use
	 * @return self
	 */
	public self overScrollFooter(Drawable footer) {
		view.setOverscrollFooter(footer);
		return (self) this;
	}

	/**
	 * Returns the drawable that will be drawn above all other list content
	 * 
	 * @return The drawable that will be drawn above all other list content
	 */
	public Drawable overScrollHeader() {
		return view.getOverscrollHeader();
	}

	/**
	 * Sets the drawable that will be drawn above all other list content. This
	 * area can become visible when the user overscrolls the list.
	 * 
	 * @param header
	 *            The drawable to use
	 * @return self
	 */
	public self overScrollHeader(Drawable header) {
		view.setOverscrollHeader(header);
		return (self) this;
	}

	/**
	 * Removes a previously-added footer view.
	 * 
	 * @param view
	 *            The view to remove
	 * @return true if the view was removed, false if the view was not a footer
	 *         view
	 */
	public boolean removeFooter(VaporView<? extends View, ?> view) {
		return this.view.removeFooterView(view.view());
	}

	/**
	 * Removes a previously-added header view.
	 * 
	 * @param view
	 *            The view to remove
	 * @return true if the view was removed, false if the view was not a header
	 *         view
	 */
	public boolean removeHeader(VaporView<? extends View, ?> view) {
		return this.view.removeHeaderView(view.view());
	}

	@Override
	public self itemSelected(int position) {
		view.setSelection(position);
		return (self) this;
	}

	/**
	 * Set the selection to be the first list item after the header views.
	 * 
	 * @return self
	 */
	public self selectAfterHeader() {
		view.setSelectionAfterHeaderView();
		return (self) this;
	}

	/**
	 * Sets the selected item and positions the selection y pixels from the top
	 * edge of the ListView. (If in touch mode, the item will not be selected
	 * but it will still be positioned appropriately.)
	 * 
	 * @param position
	 *            Index (starting at 0) of the data item to be selected.
	 * @param y
	 *            The distance from the top edge of the ListView (plus padding)
	 *            that the item will be positioned.
	 * @return self
	 */
	public self selectFromTop(int position, int y) {
		view.setSelectionFromTop(position, y);
		return (self) this;
	}

	/**
	 * Smoothly scroll to the specified adapter position offset. The view will
	 * scroll such that the indicated position is displayed.
	 * 
	 * @param offset
	 *            The amount to offset from the adapter position to scroll to.
	 * @return self
	 */
	public self smoothByOffset(int offset) {
		view.smoothScrollByOffset(offset);
		return (self) this;
	}

}
