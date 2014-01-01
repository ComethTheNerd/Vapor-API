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

import android.graphics.drawable.Drawable;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Filterable;
import android.widget.ListAdapter;

/**
 * Fluent Vapor companion to AutoCompleteTextView, an editable text view that
 * shows completion suggestions automatically while the user is typing. The list
 * of suggestions is displayed in a drop down menu from which the user can
 * choose an item to replace the content of the edit box with.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from AutoCompleteTextView
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporAutoCompleteTextView<T extends AutoCompleteTextView, self extends VaporAutoCompleteTextView<T, self>>
		extends VaporEditText<T, self> implements
		vapor.listeners.widget.filter.$filter {

	public VaporAutoCompleteTextView(int id) {
		super(id);
	}

	public VaporAutoCompleteTextView(T autoCompleteTextView) {
		super(autoCompleteTextView);
	}

	/**
	 * Returns a filterable list adapter used for auto completion.
	 * 
	 * @return a data adapter used for auto completion
	 */
	public ListAdapter adapter() {
		return view.getAdapter();
	}

	/**
	 * Changes the list of data used for auto completion. The provided list must
	 * be a filterable list adapter.
	 * 
	 * @param adapter
	 *            the adapter holding the auto completion data
	 * @return self
	 */
	public <U extends ListAdapter & Filterable> self adapter(U adapter) {
		view.setAdapter(adapter);
		return (self) this;
	}

	/**
	 * Returns the id for the view that the auto-complete drop down list is
	 * anchored to.
	 * 
	 * @return the view's id, or NO_ID if none specified
	 */
	public int popAnchor() {
		return view.getDropDownAnchor();
	}

	/**
	 * Sets the view to which the auto-complete drop down list should anchor.
	 * The view corresponding to this id will not be loaded until the next time
	 * it is needed to avoid loading a view which is not yet instantiated.
	 * 
	 * @param id
	 *            the id to anchor the drop down list view to
	 * @return self
	 */
	public self popAnchor(int id) {
		view.setDropDownAnchor(id);
		return (self) this;
	}

	/**
	 * Clear the list selection. This may only be temporary, as user input will
	 * often bring it back.
	 * 
	 * @return self
	 */
	public self clearSelection() {
		view.clearListSelection();
		return (self) this;
	}

	/**
	 * Performs the text completion by converting the selected item from the
	 * drop down list into a string, replacing the text box's content with this
	 * string and finally dismissing the drop down menu.
	 * 
	 * @return self
	 */
	public self complete() {
		view.performCompletion();
		return (self) this;
	}

	/**
	 * Gets the optional hint text displayed at the bottom of the the matching
	 * list.
	 * 
	 * @return The hint text, if any
	 */
	public CharSequence completionHint() {
		return view.getCompletionHint();
	}

	/**
	 * Sets the optional hint text that is displayed at the bottom of the the
	 * matching list. This can be used as a cue to the user on how to best use
	 * the list, or to provide extra information.
	 * 
	 * @param completionHint
	 *            the text to be displayed to the user
	 * @return self
	 */
	public self completionHint(CharSequence completionHint) {
		view.setCompletionHint(completionHint);
		return (self) this;
	}

	/**
	 * Identifies whether the view is currently performing a text completion, so
	 * subclasses can decide whether to respond to text changed events.
	 * 
	 * @return
	 */
	public boolean completing() {
		return view.isPerformingCompletion();
	}

	/**
	 * Gets the background of the auto-complete drop-down list.
	 * 
	 * @return the background drawable
	 */
	public Drawable popBg() {
		return view.getDropDownBackground();
	}

	/**
	 * Sets the background of the auto-complete drop-down list.
	 * 
	 * @param resId
	 *            the id of the drawable to set as the background
	 * @return self
	 */
	public self popBg(int resId) {
		view.setDropDownBackgroundResource(resId);
		return (self) this;
	}

	/**
	 * Sets the background of the auto-complete drop-down list.
	 * 
	 * @param backgroundDrawable
	 *            the drawable to set as the background
	 * @return self
	 */
	public self popBg(Drawable backgroundDrawable) {
		view.setDropDownBackgroundDrawable(backgroundDrawable);
		return (self) this;
	}

	/**
	 * Returns the current height for the auto-complete drop down list. This can
	 * be a fixed height, or MATCH_PARENT to fill the screen, or WRAP_CONTENT to
	 * fit the height of the drop down's content.
	 * 
	 * @return the height for the drop down list
	 */
	public int popHeight() {
		return view.getDropDownHeight();
	}

	/**
	 * Sets the current height for the auto-complete drop down list. This can be
	 * a fixed height, or MATCH_PARENT to fill the screen, or WRAP_CONTENT to
	 * fit the height of the drop down's content.
	 * 
	 * @param dropDownHeight
	 *            the height to use
	 * @return self
	 */
	public self popHeight(int dropDownHeight) {
		view.setDropDownHeight(dropDownHeight);
		return (self) this;
	}

	/**
	 * Gets the horizontal offset used for the auto-complete drop-down list.
	 * 
	 * @return the horizontal offset
	 */
	public int popXOffset() {
		return view.getDropDownHorizontalOffset();
	}

	/**
	 * Sets the horizontal offset used for the auto-complete drop-down list.
	 * 
	 * @param offset
	 *            the horizontal offset
	 * @return self
	 */
	public self popXOffset(int offset) {
		view.setDropDownHorizontalOffset(offset);
		return (self) this;
	}

	/**
	 * Indicates whether the popup menu is showing.
	 * 
	 * @return true if the popup menu is showing, false otherwise
	 */
	public boolean popShowing() {
		return view.isPopupShowing();
	}

	/**
	 * Sets whether the drop down is showing on screen.
	 * 
	 * @param showDropDown
	 *            true to show the drop down, or false to dismiss it
	 * @return self
	 */
	public self popShowing(boolean showDropDown) {
		if (showDropDown)
			view.showDropDown();
		else
			view.dismissDropDown();
		return (self) this;
	}

	/**
	 * Gets the vertical offset used for the auto-complete drop-down list.
	 * 
	 * @return the vertical offset
	 */
	public int popYOffset() {
		return view.getDropDownVerticalOffset();
	}

	/**
	 * Sets the vertical offset used for the auto-complete drop-down list.
	 * 
	 * @param offset
	 *            the vertical offset
	 * @return self
	 */
	public self popYOffset(int offset) {
		view.setDropDownVerticalOffset(offset);
		return (self) this;
	}

	/**
	 * Returns the current width for the auto-complete drop down list. This can
	 * be a fixed width, or MATCH_PARENT to fill the screen, or WRAP_CONTENT to
	 * fit the width of its anchor view.
	 * 
	 * @return the width for the drop down list
	 */
	public int popWidth() {
		return view.getDropDownWidth();
	}

	/**
	 * Sets the current width for the auto-complete drop down list. This can be
	 * a fixed width, or MATCH_PARENT to fill the screen, or WRAP_CONTENT to fit
	 * the width of its anchor view.
	 * 
	 * @param width
	 *            the width to use
	 * @return self
	 */
	public self popWidth(int width) {
		view.setDropDownWidth(width);
		return (self) this;
	}

	/**
	 * Set a listener that will be invoked whenever the AutoCompleteTextView's
	 * list of completions is dismissed.
	 * 
	 * @param dismissListener
	 *            Listener to invoke when completions are dismissed
	 * @return self
	 */
	public self dismiss(
			vapor.listeners.autocompletetextview.$dismiss dismissListener) { // API:17
		view.setOnDismissListener(dismissListener);
		return (self) this;
	}

	/**
	 * Returns true if the amount of text in the field meets or exceeds the
	 * threshold() requirement. You can override this to impose a different
	 * standard for when filtering will be triggered.
	 * 
	 * @return true if the amount of text in the field meets or exceeds the
	 *         threshold() requirement
	 */
	public boolean enoughToFilter() {
		return view.enoughToFilter();
	}

	/**
	 * Fluent equivalent Vapor method for invoking onFilterComplete(int), which
	 * notifies the end of a filtering operation.
	 * 
	 * @param count
	 *            the number of values computed by the filter
	 * @return self
	 */
	public self filterComplete(int count) {
		view.onFilterComplete(count);
		return (self) this;
	}

	/**
	 * Sets the listener that will be notified when the user clicks an item in
	 * the drop down list.
	 * 
	 * @param itemClickListener
	 *            the item click listener
	 * @return self
	 */
	public self itemClick(
			vapor.listeners.widget.adapterview.$click itemClickListener) {
		view.setOnItemClickListener(itemClickListener);
		return (self) this;
	}

	/**
	 * Returns the listener that is notified whenever the user clicks an item in
	 * the drop down list.
	 * 
	 * @return the item click listener
	 */
	public AdapterView.OnItemClickListener itemClickListener() {
		return view.getOnItemClickListener();
	}

	/**
	 * Sets the listener that will be notified when the user selects an item in
	 * the drop down list.
	 * 
	 * @param itemSelectedListener
	 *            the item selected listener
	 * @return self
	 */
	public self itemSelected(
			vapor.listeners.widget.adapterview.$select itemSelectedListener) {
		view.setOnItemSelectedListener(itemSelectedListener);
		return (self) this;
	}

	/**
	 * Returns the listener that is notified whenever the user selects an item
	 * in the drop down list.
	 * 
	 * @return the item selected listener
	 */
	public AdapterView.OnItemSelectedListener itemSelectedListener() {
		return view.getOnItemSelectedListener();
	}

	/**
	 * Get the position of the dropdown view selection, if there is one. Returns
	 * ListView.INVALID_POSITION if there is no dropdown or if there is no
	 * selection.
	 * 
	 * @return the position of the current selection, if there is one, or
	 *         ListView.INVALID_POSITION if not.
	 */
	public int selectedItem() {
		return view.getListSelection();
	}

	/**
	 * Set the position of the dropdown view selection.
	 * 
	 * @param listSelectionPosition
	 *            The position to move the selector to.
	 * @return self
	 */
	public self selectedItem(int listSelectionPosition) {
		view.setListSelection(listSelectionPosition);
		return (self) this;
	}

	/**
	 * Like text(CharSequence), except that it can disable filtering.
	 * 
	 * @param text
	 * @param filter
	 *            If false, no filtering will be performed as a result of this
	 *            call.
	 * @return self
	 */
	public self text(CharSequence text, boolean filter) { // API:17
		view.setText(text, filter);
		return (self) this;
	}

	/**
	 * Returns the number of characters the user must type before the drop down
	 * list is shown.
	 * 
	 * @return the minimum number of characters to type to show the drop down
	 */
	public int threshold() {
		return view.getThreshold();
	}

	/**
	 * Specifies the minimum number of characters the user has to type in the
	 * edit box before the drop down list is shown.
	 * 
	 * @param threshold
	 *            the number of characters to type before the drop down is shown
	 * @return self
	 */
	public self threshold(int threshold) {
		view.setThreshold(threshold);
		return (self) this;
	}

	/**
	 * If a validator was set on this view and the current string is not valid,
	 * ask the validator to fix it.
	 * 
	 * @return self
	 */
	public self validate() {
		view.performValidation();
		return (self) this;
	}

	/**
	 * Returns the Validator set with validator(AutoCompleteTextView.Validator),
	 * or null if it was not set.
	 * 
	 * @return the Validator set with validator(AutoCompleteTextView.Validator),
	 *         or null if it was not set.
	 */
	public AutoCompleteTextView.Validator validator() {
		return view.getValidator();
	}

	/**
	 * Sets the validator used to perform text validation.
	 * 
	 * @param validator
	 *            The validator used to validate the text entered in this
	 *            widget.
	 * @return self
	 */
	public self validator(AutoCompleteTextView.Validator validator) {
		view.setValidator(validator);
		return (self) this;
	}

	/* INTERFACE : Filter.FilterListener */
	/**
	 * NOTE: This method serves to satisfy the Filter.FilterListener interface,
	 * use the equivalent VAPOR FLUENT method preDraw() instead
	 */
	@Override
	public void onFilterComplete(int count) {
		filterComplete(count);
	}

}
