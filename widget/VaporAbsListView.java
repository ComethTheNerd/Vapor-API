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

import java.util.List;

import vapor.content.VaporIntent;
import vapor.core.$;
import vapor.view.VaporView;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.Editable;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Filter.FilterListener;
import android.widget.ListAdapter;

/**
 * Fluent Vapor companion to AbsListView, a class that can be used to implement
 * virtualized lists of items. A list does not have a spatial definition here.
 * For instance, subclases of this class can display the content of the list in
 * a grid, in a carousel, as stack, etc.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from AbsListView
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public abstract class VaporAbsListView<T extends AbsListView, self extends VaporAbsListView<T, self>>
		extends VaporAdapterView<ListAdapter, T, self> implements
		vapor.listeners.text.$text,
		vapor.listeners.view.viewtreeobserver.$layout,
		vapor.listeners.view.viewtreeobserver.$mode, FilterListener {

	public VaporAbsListView(int id) {
		super(id);
	}

	public VaporAbsListView(T absListView) {
		super(absListView);
	}

	@Override
	public AbsListView.LayoutParams layoutParams(int width, int height) {
		return new AbsListView.LayoutParams(width, height);
	}

	/**
	 * When set to a non-zero value, the cache color hint indicates that this
	 * list is always drawn on top of a solid, single-color, opaque background.
	 * 
	 * @return The cache color hint.
	 */
	public int cacheColorHint() {
		return view.getCacheColorHint();
	}

	/**
	 * When set to a non-zero value, the cache color hint indicates that this
	 * list is always drawn on top of a solid, single-color, opaque background.
	 * 
	 * @param color
	 *            The background color.
	 * @return self
	 */
	public self cacheColorHint(int color) {
		view.setCacheColorHint(color);
		return (self) this;
	}

	/**
	 * Returns the checked state of the specified position. The result is only
	 * valid if the choice mode has been set to CHOICE_MODE_SINGLE or
	 * CHOICE_MODE_MULTIPLE.
	 * 
	 * @param itemPosition
	 *            The item whose checked state to return.
	 * @return The item's checked state or false if choice mode is invalid.
	 */
	public boolean checked(int itemPosition) {
		return view.isItemChecked(itemPosition);
	}

	/**
	 * Sets the checked state of the specified position.
	 * 
	 * @param itemPosition
	 *            The item whose checked state is to be checked.
	 * @param value
	 *            The new checked state for the item.
	 * @return self
	 */
	public self checked(int itemPosition, boolean value) {
		view.setItemChecked(itemPosition, value);
		return (self) this;
	}

	/**
	 * Returns the number of items currently selected. This will only be valid
	 * if the choice mode is not CHOICE_MODE_NONE (default).
	 * 
	 * @return The number of items currently selected.
	 */
	public int checkedCount() {
		return view.getCheckedItemCount();
	}

	/**
	 * Returns the set of checked items ids.
	 * 
	 * @return A new array which contains the id of each checked item in the
	 *         list.
	 */
	public long[] checkedIds() {
		return view.getCheckedItemIds();
	}

	/**
	 * Returns the currently checked item.
	 * 
	 * @return The position of the currently checked item or INVALID_POSITION if
	 *         nothing is selected.
	 */
	public int checkedPos() {
		return view.getCheckedItemPosition();
	}

	/**
	 * Returns the set of checked items in the list.
	 * 
	 * @return A SparseBooleanArray which will return true for each call to
	 *         get(int position) where position is a position in the list, or
	 *         null if the choice mode is set to CHOICE_MODE_NONE.
	 */
	public SparseBooleanArray checkedPosAll() {
		return view.getCheckedItemPositions();
	}

	/**
	 * Returns the current choice mode.
	 * 
	 * @return The current choice mode.
	 */
	public int choiceMode() {
		return view.getChoiceMode();
	}

	/**
	 * Defines the choice behavior for the List.
	 * 
	 * @param choiceMode
	 *            One of CHOICE_MODE_NONE, CHOICE_MODE_SINGLE, or
	 *            CHOICE_MODE_MULTIPLE.
	 * @return self
	 */
	public self choiceMode(int choiceMode) {
		view.setChoiceMode(choiceMode);
		return (self) this;
	}

	/**
	 * Clear any choices previously set.
	 * 
	 * @return self
	 */
	public self clear() {
		view.clearChoices();
		return (self) this;
	}

	/**
	 * Clear the text filter.
	 * 
	 * @return self
	 */
	public self clearFilter() {
		view.clearTextFilter();
		return (self) this;
	}

	/**
	 * This defers a notifyDataSetChanged on the pending RemoteViewsAdapter if
	 * it has not connected yet.
	 * 
	 * @return self
	 */
	public self deferNotify() {
		view.deferNotifyDataSetChanged();
		return (self) this;
	}

	/**
	 * Controls whether the selection highlight drawable should be drawn on top
	 * of the item or behind it.
	 * 
	 * @param onTop
	 *            If true, the selector will be drawn on the item it is
	 *            highlighting. The default is false.
	 * @return self
	 */
	public self selectorOnTop(boolean onTop) {
		view.setDrawSelectorOnTop(onTop);
		return (self) this;
	}

	/**
	 * Returns the current state of the fast scroll feature.
	 * 
	 * @return true if fast scroll is enabled, false otherwise.
	 */
	public boolean fastScrolls() {
		return view.isFastScrollEnabled();
	}

	/**
	 * Enables fast scrolling by letting the user quickly scroll through lists
	 * by dragging the fast scroll thumb.
	 * 
	 * @param enabled
	 *            whether or not to enable fast scrolling.
	 * @return self
	 */
	public self fastScrolls(boolean enabled) {
		view.setFastScrollEnabled(enabled);
		return (self) this;
	}

	/**
	 * Returns true if the fast scroller is set to always show on this view
	 * rather than fade out when not in use.
	 * 
	 * @return true if the fast scroller will always show.
	 */
	public boolean fastScrollPermaViz() {
		return view.isFastScrollAlwaysVisible();
	}

	/**
	 * Set whether or not the fast scroller should always be shown in place of
	 * the standard scrollbars.
	 * 
	 * @param alwaysShow
	 *            true if the fast scroller should always be displayed.
	 * @return self
	 */
	public self fastScrollPermaViz(boolean alwaysShow) {
		view.setFastScrollAlwaysVisible(alwaysShow);
		return (self) this;
	}

	/**
	 * Returns the list's text filter, if available.
	 * 
	 * @return the list's text filter or null if filtering isn't enabled.
	 */
	public CharSequence filter() {
		return view.getTextFilter();
	}

	/**
	 * Sets the initial value for the text filter.
	 * 
	 * @param filterText
	 *            The text to use for the filter.
	 * @return self
	 */
	public self filter(String filterText) {
		view.setFilterText(filterText);
		return (self) this;
	}

	/**
	 * Returns if the ListView currently has a text filter.
	 * 
	 * @return true if the ListView currently has a text filter.
	 */
	public boolean filterable() {
		return view.hasTextFilter();
	}

	/**
	 * Indicates whether type filtering is enabled for this view.
	 * 
	 * @return true if type filtering is enabled, false otherwise.
	 */
	public boolean filtered() {
		return view.isTextFilterEnabled();
	}

	/**
	 * Enables or disables the type filter window.
	 * 
	 * @param textFilterEnabled
	 *            true to enable type filtering, false otherwise
	 * @return self
	 */
	public self filtered(boolean textFilterEnabled) {
		view.setTextFilterEnabled(textFilterEnabled);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onFilterComplete(int), used
	 * to notify the end of a filtering operation.
	 * 
	 * @param count
	 *            the number of values computed by the filter.
	 * @return self
	 */
	public self filterComplete(int count) {
		view.onFilterComplete(count);
		return (self) this;
	}

	/**
	 * The amount of friction applied to flings.
	 * 
	 * @param friction
	 *            A scalar dimensionless value representing the coefficient of
	 *            friction.
	 * @return self
	 */
	public self friction(float friction) {
		view.setFriction(friction);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onGlobalLayout(), invoked
	 * when the global layout state or the visibility of views within the view
	 * tree changes.
	 * 
	 * @return self
	 */
	public self globalLayout() {
		view.onGlobalLayout();
		return (self) this;
	}

	/**
	 * Causes all the views to be rebuilt and redrawn.
	 * 
	 * @return self
	 */
	public self invalidateViews() {
		view.invalidateViews();
		return (self) this;
	}

	/**
	 * List padding is the maximum of the normal view's padding and the padding
	 * of the selector.
	 * 
	 * @return The bottom list padding.
	 */
	public int listPadBottom() {
		return view.getListPaddingBottom();
	}

	/**
	 * List padding is the maximum of the normal view's padding and the padding
	 * of the selector.
	 * 
	 * @return The left list padding.
	 */
	public int listPadLeft() {
		return view.getListPaddingLeft();
	}

	/**
	 * List padding is the maximum of the normal view's padding and the padding
	 * of the selector.
	 * 
	 * @return The right list padding.
	 */
	public int listPadRight() {
		return view.getListPaddingRight();
	}

	/**
	 * List padding is the maximum of the normal view's padding and the padding
	 * of the selector.
	 * 
	 * @return The top list padding.
	 */
	public int listPadTop() {
		return view.getListPaddingTop();
	}

	/**
	 * Set a MultiChoice listener that will manage the lifecycle of the
	 * selection ActionMode.
	 * 
	 * @param multiChoiceModeListener
	 *            Listener that will manage the selection mode.
	 * @return self
	 */
	public self multiChoice(
			vapor.listeners.widget.abslistview.$mode multiChoiceModeListener) {
		view.setMultiChoiceModeListener(multiChoiceModeListener);
		return (self) this;
	}

	/**
	 * Maps a point to a position in the list.
	 * 
	 * @param x
	 *            X in local coordinate
	 * @param y
	 *            Y in local coordinate
	 * @return The position of the item which contains the specified point, or
	 *         INVALID_POSITION if the point does not intersect an item.
	 */
	public int pointToPos(int x, int y) {
		return view.pointToPosition(x, y);
	}

	/**
	 * Maps a point to the rowId of the item which intersects that point.
	 * 
	 * @param x
	 *            X in local coordinate
	 * @param y
	 *            Y in local coordinate
	 * @return The rowId of the item which contains the specified point, or
	 *         INVALID_ROW_ID if the point does not intersect an item.
	 */
	public long pointToRowId(int x, int y) {
		return view.pointToRowId(x, y);
	}

	/**
	 * Move all views (excluding headers and footers) held by this AbsListView
	 * into the supplied List.
	 * 
	 * @param views
	 *            A list into which to put the reclaimed views.
	 * @return self
	 */
	public self reclaim(List<VaporView<? extends View, ?>> views) {
		view.reclaimViews($.android(views));
		return (self) this;
	}

	/**
	 * Sets the recycler listener to be notified whenever a View is set aside in
	 * the recycler for later reuse.
	 * 
	 * @param recyclerListener
	 *            The recycler listener to be notified of views set aside in the
	 *            recycler.
	 * @return self
	 */
	public self recycle(
			vapor.listeners.widget.abslistview.$recycle recyclerListener) {
		view.setRecyclerListener(recyclerListener);
		return (self) this;
	}

	/**
	 * Sets up this AbsListView to use a remote views adapter which connects to
	 * a RemoteViewsService through the specified intent.
	 * 
	 * @param intent
	 *            the intent used to identify the RemoteViewsService for the
	 *            adapter to connect to.
	 * @return self
	 */
	public self remote(VaporIntent<? extends Intent, ?> intent) {
		view.setRemoteViewsAdapter(intent.intent());
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onRemoteAdapterConnected(),
	 * which is called back when the adapter connects to the RemoteViewsService.
	 * 
	 * @return
	 */
	public boolean remoteConn() {
		return view.onRemoteAdapterConnected();
	}

	/**
	 * Fluent equivalent Vapor method for invoking
	 * onRemoteAdapterDisconnected(), which is called back when the adapter
	 * disconnects from the RemoteViewsService.
	 * 
	 * @return self
	 */
	public self remoteDisconn() {
		view.onRemoteAdapterDisconnected();
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking
	 * onRestoreInstanceState(Parcelable), a hook allowing a view to re-apply a
	 * representation of its internal state that had previously been generated by
	 * save().
	 * 
	 * @param state
	 *            The frozen state that had previously been returned by save().
	 * @return self
	 */
	public self restore(Parcelable state) {
		view.onRestoreInstanceState(state);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onSaveInstanceState(), a hook
	 * allowing a view to generate a representation of its internal state that
	 * can later be used to create a new instance with that same state.
	 * 
	 * @return Returns a Parcelable object containing the view's current dynamic
	 *         state, or null if there is nothing interesting to save.
	 */
	public Parcelable save() {
		return view.onSaveInstanceState();
	}

	/**
	 * Set the listener that will receive notifications every time the list
	 * scrolls.
	 * 
	 * @param scrollListener
	 *            the scroll listener.
	 * @return self
	 */
	public self scroll(vapor.listeners.widget.abslistview.$scroll scrollListener) {
		view.setOnScrollListener(scrollListener);
		return (self) this;
	}

	/**
	 * Indicates whether the children's drawing cache is used during a scroll.
	 * 
	 * @return true if the scrolling cache is enabled, false otherwise.
	 */
	public boolean scrollCached() {
		return view.isScrollingCacheEnabled();
	}

	/**
	 * Enables or disables the children's drawing cache during a scroll.
	 * 
	 * @param enabled
	 *            true to enable the scroll cache, false otherwise
	 * @return self
	 */
	public self scrollCached(boolean enabled) {
		view.setScrollingCacheEnabled(enabled);
		return (self) this;
	}

	/**
	 * 
	 * @param up
	 * @param down
	 * @return self
	 */
	public self scrollIndicators(VaporView<? extends View, ?> up,
			VaporView<? extends View, ?> down) {
		view.setScrollIndicators(up.view(), down.view());
		return (self) this;
	}

	/**
	 * Returns the selector Drawable that is used to draw the selection in the
	 * list.
	 * 
	 * @return the drawable used to display the selector.
	 */
	public Drawable selector() {
		return view.getSelector();
	}

	/**
	 * Set a Drawable that should be used to highlight the currently selected
	 * item.
	 * 
	 * @param resId
	 *            A Drawable resource to use as the selection highlight.
	 * @return self
	 */
	public self selector(int resId) {
		view.setSelector(resId);
		return (self) this;
	}

	/**
	 * 
	 * @param selector
	 * @return self
	 */
	public self selector(Drawable selector) {
		view.setSelector(selector);
		return (self) this;
	}

	/**
	 * Smoothly scroll by distance pixels over duration milliseconds.
	 * 
	 * @param distance
	 *            Distance to scroll in pixels.
	 * @param duration
	 *            Duration of the scroll animation in milliseconds.
	 * @return self
	 */
	public self smoothBy(int distance, int duration) {
		view.smoothScrollBy(distance, duration);
		return (self) this;
	}

	/**
	 * Returns the current state of the fast scroll feature.
	 * 
	 * @return True if smooth scrollbar is enabled is enabled, false otherwise.
	 */
	public boolean smoothScrolls() {
		return view.isSmoothScrollbarEnabled();
	}

	/**
	 * When smooth scrollbar is enabled, the position and size of the scrollbar
	 * thumb is computed based on the number of visible pixels in the visible
	 * items.
	 * 
	 * @param enabled
	 *            Whether or not to enable smooth scrollbar.
	 * @return self
	 */
	public self smoothScrolls(boolean enabled) {
		view.setSmoothScrollbarEnabled(enabled);
		return (self) this;
	}

	/**
	 * Smoothly scroll to the specified adapter position.
	 * 
	 * @param position
	 *            Scroll to this adapter position.
	 * @return self
	 */
	public self smoothTo(int position) {
		view.smoothScrollToPosition(position);
		return (self) this;
	}

	/**
	 * Smoothly scroll to the specified adapter position.
	 * 
	 * @param position
	 *            Scroll to this adapter position.
	 * @param boundPosition
	 *            Do not scroll if it would move this adapter position out of
	 *            view.
	 * @return self
	 */
	public self smoothTo(int position, int boundPosition) {
		view.smoothScrollToPosition(position, boundPosition);
		return (self) this;
	}

	/**
	 * Smoothly scroll to the specified adapter position.
	 * 
	 * @param position
	 *            Position to scroll to.
	 * @param offset
	 *            Desired distance in pixels of position from the top of the
	 *            view when scrolling is finished.
	 * @return self
	 */
	public self smoothToFromTop(int position, int offset) {
		view.smoothScrollToPositionFromTop(position, offset);
		return (self) this;
	}

	/**
	 * Smoothly scroll to the specified adapter position.
	 * 
	 * @param position
	 *            Position to scroll to.
	 * @param offset
	 *            Desired distance in pixels of position from the top of the
	 *            view when scrolling is finished.
	 * @param duration
	 *            Number of milliseconds to use for the scroll.
	 * @return self
	 */
	public self smoothToFromTop(int position, int offset, int duration) {
		view.smoothScrollToPositionFromTop(position, offset, duration);
		return (self) this;
	}

	/**
	 * Indicates whether the content of this view is pinned to, or stacked from,
	 * the bottom edge.
	 * 
	 * @return true if the content is stacked from the bottom edge, false
	 *         otherwise.
	 */
	public boolean stacksFromBottom() {
		return view.isStackFromBottom();
	}

	/**
	 * When stack from bottom is set to true, the list fills its content
	 * starting from the bottom of the view.
	 * 
	 * @param stackFromBottom
	 *            true to pin the view's content to the bottom edge, false to
	 *            pin the view's content to the top edge
	 * @return self
	 */
	public self stacksFromBottom(boolean stackFromBottom) {
		view.setStackFromBottom(stackFromBottom);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking
	 * onTextChanged(CharSequence,int,int,int), used by the text watcher that is
	 * associated with the text filter.
	 * 
	 * @param charSequence
	 * @param start
	 * @param before
	 * @param count
	 * @return self
	 */
	public self textChanged(CharSequence charSequence, int start, int before,
			int count) {
		view.onTextChanged(charSequence, start, before, count);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onTouchModeChanged(boolean),
	 * a callback method to be invoked when the touch mode changes.
	 * 
	 * @param isInTouchMode
	 *            True if the view hierarchy is now in touch mode, false
	 *            otherwise.
	 * @return self
	 */
	public self touchModeChanged(boolean isInTouchMode) {
		view.onTouchModeChanged(isInTouchMode);
		return (self) this;
	}

	/**
	 * Returns the current transcript mode.
	 * 
	 * @return TRANSCRIPT_MODE_DISABLED, TRANSCRIPT_MODE_NORMAL or
	 *         TRANSCRIPT_MODE_ALWAYS_SCROLL.
	 */
	public int transcriptMode() {
		return view.getTranscriptMode();
	}

	/**
	 * Puts the list or grid into transcript mode.
	 * 
	 * @param mode
	 *            the transcript mode to set
	 * @return self
	 */
	public self transcriptMode(int mode) {
		view.setTranscriptMode(mode);
		return (self) this;
	}

	/**
	 * Sets a scale factor for the fling velocity.
	 * 
	 * @param scale
	 *            The scale factor to multiply the velocity by.
	 * @return self
	 */
	public self velocityScale(float scale) {
		view.setVelocityScale(scale);
		return (self) this;
	}

	/**
	 * If your view subclass is displaying its own Drawable objects, it should
	 * override this function and return true for any Drawable it is displaying.
	 * 
	 * @param drawable
	 *            The Drawable to verify.
	 * @return If true than the Drawable is being displayed in the view; else
	 *         false and it is not allowed to animate.
	 */
	public boolean verify(Drawable drawable) {
		return view.verifyDrawable(drawable);
	}

	/* INTERFACE: TextWatcher */
	/**
	 * NOTE: This method serves to satisfy the TextWatcher interface, use the
	 * equivalent VAPOR FLUENT method postTextChange(Editable) instead
	 */
	public void afterTextChanged(Editable editable) {
		view.afterTextChanged(editable);
	}

	/**
	 * NOTE: This method serves to satisfy the TextWatcher interface, use the
	 * equivalent VAPOR FLUENT method preTextChange(CharSequence,int,int,int)
	 * instead
	 */
	public void beforeTextChanged(CharSequence text, int start, int count,
			int after) {
		view.beforeTextChanged(text, start, count, after);
	}

	/**
	 * NOTE: This method serves to satisfy the TextWatcher interface, use the
	 * equivalent VAPOR FLUENT method onTextChanged(CharSequence,int,int,int)
	 * instead
	 */
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		textChanged(s, start, before, count);
	}

	/* INTERFACE: OnGlobalLayoutListener */
	/**
	 * NOTE: This method serves to satisfy the OnGlobalLayoutListener interface,
	 * use the equivalent VAPOR FLUENT method onGlobalLayout() instead
	 */
	public void onGlobalLayout() {
		globalLayout();
	}

	/* INTERFACE: OnTouchModeChangeListener */
	/**
	 * NOTE: This method serves to satisfy the OnTouchModeChangeListener
	 * interface, use the equivalent VAPOR FLUENT method
	 * onTouchModeChanged(boolean) instead
	 */
	public void onTouchModeChanged(boolean isInTouchMode) {
		touchModeChanged(isInTouchMode);
	}

	/* INTERFACE: FilterListener */
	/**
	 * NOTE: This method serves to satisfy the FilterListener interface, use the
	 * equivalent VAPOR FLUENT method onFilterComplete(int) instead
	 */
	public void onFilterComplete(int count) {
		filterComplete(count);
	}
}