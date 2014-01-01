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
import vapor.view.VaporViewGroup;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * Fluent Vapor companion to AdapterView, a view whose children are determined
 * by an Adapter.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <U>
 *            A standard Android type derived from Adapter
 * @param <T>
 *            A standard Android type derived from AdapterView
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public abstract class VaporAdapterView<U extends Adapter, T extends AdapterView<U>, self extends VaporAdapterView<U, T, self>>
		extends VaporViewGroup<T, self> {

	public VaporAdapterView(int id) {
		super(id);
	}

	public VaporAdapterView(T adapterView) {
		super(adapterView);
	}

	/**
	 * Returns the adapter currently associated with this widget.
	 * 
	 * @return The adapter used to provide this view's content.
	 */
	public abstract U adapter();

	/**
	 * Sets the adapter that provides the data and the views to represent the
	 * data in this widget.
	 * 
	 * @param adapter
	 *            The adapter to use to create this view's content.
	 * @return this
	 */
	public abstract self adapter(U adapter);

	/**
	 * 
	 * @return The number of items owned by the Adapter associated with this
	 *         AdapterView.
	 */
	public int count() {
		return view.getCount();
	}

	/**
	 * When the current adapter is empty, the AdapterView can display a special
	 * view call the empty view.
	 * 
	 * @return The view to show if the adapter is empty.
	 */
	public VaporView<? extends View, ?> emptyView() {
		return $.vapor(view.getEmptyView());
	}

	/**
	 * Sets the view to show if the adapter is empty
	 * 
	 * @param emptyView
	 * @return this
	 */
	public self emptyView(VaporView<? extends View, ?> emptyView) {
		view.setEmptyView(emptyView.view());
		return (self) this;
	}

	/**
	 * Returns the position within the adapter's data set for the first item
	 * displayed on screen.
	 * 
	 * @return The position within the adapter's data set
	 */
	public int firstViz() {
		return view.getFirstVisiblePosition();
	}

	/**
	 * Get the position within the adapter's data set for the view, where view
	 * is a an adapter item or a descendant of an adapter item.
	 * 
	 * @param view
	 *            an adapter item, or a descendant of an adapter item. This must
	 *            be visible in this AdapterView at the time of the call.
	 * @return the position within the adapter's data set of the view, or
	 *         INVALID_POSITION if the view does not correspond to a list item
	 *         (or it is not currently visible).
	 */
	public int index(VaporView<? extends View, ?> view) {
		return this.view.getPositionForView(view.view());
	}

	/**
	 * 
	 * @return The data corresponding to the currently selected item, or null if
	 *         there is nothing selected.
	 */
	public Object itemSelected() {
		return view.getSelectedItem();
	}

	/**
	 * Gets the data associated with the specified position in the list.
	 * 
	 * @param position
	 *            Which data to get
	 * @return The data associated with the specified position in the list
	 */
	public Object item(int position) {
		return view.getItemAtPosition(position);
	}

	/**
	 * Call the OnItemClickListener, if it is defined.
	 * 
	 * @param view
	 *            The view within the AdapterView that was clicked.
	 * @param position
	 *            The position of the view in the adapter.
	 * @param id
	 *            The position of the view in the adapter.
	 * @return True if there was an assigned OnItemClickListener that was
	 *         called, false otherwise is returned.
	 */
	public boolean itemClick(VaporView<? extends View, ?> view, int position,
			long id) {
		return this.view.performItemClick(view.view(), position, id);
	}

	/**
	 * Register a callback to be invoked when an item in this AdapterView has
	 * been clicked.
	 * 
	 * @param itemClickListener
	 *            The callback that will be invoked.
	 * @return this
	 */
	public self itemClick(
			vapor.listeners.widget.adapterview.$click itemClickListener) {
		view.setOnItemClickListener(itemClickListener);
		return (self) this;
	}

	/**
	 * 
	 * @return The id corresponding to the currently selected item, or
	 *         INVALID_ROW_ID if nothing is selected.
	 */
	public long itemId() {
		return view.getSelectedItemId();
	}

	/**
	 * Gets the id associated with the specified position in the list.
	 * 
	 * @param position
	 *            Which id to get
	 * @return The id associated with the specified position in the list
	 */
	public long itemId(int position) {
		return view.getItemIdAtPosition(position);
	}

	/**
	 * Return the position of the currently selected item within the adapter's
	 * data set
	 * 
	 * @return int Position (starting at 0), or INVALID_POSITION if there is
	 *         nothing selected.
	 */
	public int indexSelected() {
		return view.getSelectedItemPosition();
	}

	/**
	 * 
	 * @return The callback to be invoked with an item in this AdapterView has
	 *         been clicked, or null id no callback has been set.
	 */
	public final OnItemClickListener itemClickListener() {
		return view.getOnItemClickListener();
	}

	/**
	 * Register a callback to be invoked when an item in this AdapterView has
	 * been selected.
	 * 
	 * @param itemLongClickListener
	 *            The callback that will run
	 * @return this
	 */
	public self itemLongClick(
			vapor.listeners.widget.adapterview.$longClick itemLongClickListener) {
		view.setOnItemLongClickListener(itemLongClickListener);
		return (self) this;
	}

	/**
	 * 
	 * @return The callback to be invoked with an item in this AdapterView has
	 *         been clicked and held, or null id no callback as been set.
	 */
	public final OnItemLongClickListener itemLongClickListener() {
		return view.getOnItemLongClickListener();
	}

	/**
	 * Register a callback to be invoked when an item in this AdapterView has
	 * been clicked.
	 * 
	 * @param itemSelectedListener
	 *            the callback that will be invoked
	 * @return self
	 */
	public self itemSelected(
			vapor.listeners.widget.adapterview.$select itemSelectedListener) {
		view.setOnItemSelectedListener(itemSelectedListener);
		return (self) this;
	}

	/**
	 * 
	 * @return
	 */
	public final OnItemSelectedListener itemSelectedListener() {
		return view.getOnItemSelectedListener();
	}

	/**
	 * Returns the position within the adapter's data set for the last item
	 * displayed on screen.
	 * 
	 * @return The position within the adapter's data set
	 */
	public int lastVizPos() {
		return view.getLastVisiblePosition();
	}

	/**
	 * 
	 * @return The view corresponding to the currently selected item, or null if
	 *         nothing is selected
	 */
	public VaporView<? extends View, ?> selectedView() {
		return $.vapor(view.getSelectedView());
	}

	/**
	 * Sets the currently selected item. To support accessibility subclasses
	 * that override this method must invoke the overridden super method first.
	 * 
	 * @param position
	 *            Index (starting at 0) of the data item to be selected.
	 * @return this
	 */
	public abstract self itemSelected(int position);

}