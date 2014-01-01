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

import android.app.SearchableInfo;
import android.view.CollapsibleActionView;
import android.widget.CursorAdapter;
import android.widget.SearchView;

/**
 * Fluent Vapor companion to SearchView, a widget that provides a user interface
 * for the user to enter a search query and submit a request to a search
 * provider. Shows a list of query suggestions or results, if available, and
 * allows the user to pick a suggestion or result to launch into.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from SearchView
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporSearchView<T extends SearchView, self extends VaporSearchView<T, self>>
		extends VaporLinearLayout<T, self> implements CollapsibleActionView {

	public VaporSearchView(int id) {
		super(id);
	}

	public VaporSearchView(T searchView) {
		super(searchView);
	}

	/**
	 * Sets a listener to inform when the user closes the SearchView.
	 * 
	 * @param closeListener
	 *            the listener to call when the user closes the SearchView.
	 * @return self
	 */
	public self close(vapor.listeners.widget.searchview.$close closeListener) {
		view.setOnCloseListener(closeListener);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onActionViewCollapsed(),
	 * called when this view is collapsed as an action view.
	 * 
	 * @return self
	 */
	public self collapsed() {
		view.onActionViewCollapsed();
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onActionViewExpanded(),
	 * called when this view is expanded as an action view.
	 * 
	 * @return self
	 */
	public self expanded() {
		view.onActionViewExpanded();
		return (self) this;
	}

	/**
	 * Gets the hint text to display in the query text field.
	 * 
	 * @return the query hint text, if specified, null otherwise.
	 */
	public CharSequence hint() {
		return view.getQueryHint();
	}

	/**
	 * Sets the hint text to display in the query text field. This overrides any
	 * hint specified in the SearchableInfo.
	 * 
	 * @param hint
	 *            the hint text to display
	 * @return self
	 */
	public self hint(CharSequence hint) {
		view.setQueryHint(hint);
		return (self) this;
	}

	/**
	 * Returns the current iconified state of the VaporSearchView.
	 * 
	 * @return true if the VaporSearchView is currently iconified, false if the
	 *         search field is fully visible.
	 */
	public boolean iconified() {
		return view.isIconified();
	}

	/**
	 * Iconifies or expands the SearchView. Any query text is cleared when
	 * iconified. This is a temporary state and does not override the default
	 * iconified state set by defIconified(boolean). If the default state is
	 * iconified, then a false here will only be valid until the user closes the
	 * field. And if the default state is expanded, then a true here will only
	 * clear the text field and not close it.
	 * 
	 * @param iconify
	 *            a true value will collapse the SearchView to an icon, while a
	 *            false will expand it.
	 * @return self
	 */
	public self iconified(boolean iconify) {
		view.setIconified(iconify);
		return (self) this;
	}

	/**
	 * Returns the default iconified state of the search field.
	 * 
	 * @return
	 */
	public boolean defIconified() {
		return view.isIconfiedByDefault();
	}

	/**
	 * ets the default or resting state of the search field. If true, a single
	 * search icon is shown by default and expands to show the text field and
	 * other buttons when pressed. Also, if the default state is iconified, then
	 * it collapses to that state when the close button is pressed. Changes to
	 * this property will take effect immediately.
	 * 
	 * The default value is true.
	 * 
	 * @param iconifyByDefault
	 *            whether the search field should be iconified by default
	 * @return self
	 */
	public self defIconified(boolean iconifyByDefault) {
		view.setIconifiedByDefault(iconifyByDefault);
		return (self) this;
	}

	/**
	 * Returns the IME options set on the query text field.
	 * 
	 * @return the ime options
	 */
	public int ime() {
		return view.getImeOptions();
	}

	/**
	 * Sets the IME options on the query text field.
	 * 
	 * @param imeOptions
	 *            the options to set on the query text field
	 * @return self
	 */
	public self ime(int imeOptions) {
		view.setImeOptions(imeOptions);
		return (self) this;
	}

	/**
	 * Sets the SearchableInfo for this SearchView. Properties in the
	 * SearchableInfo are used to display labels, hints, suggestions, create
	 * intents for launching search results screens and controlling other
	 * affordances such as a voice button.
	 * 
	 * @param searchableInfo
	 *            a SearchableInfo can be retrieved from the SearchManager, for
	 *            a specific activity or a global search provider.
	 * @return self
	 */
	public self info(SearchableInfo searchableInfo) {
		view.setSearchableInfo(searchableInfo);
		return (self) this;
	}

	/**
	 * Returns the input type set on the query text field.
	 * 
	 * @return the input type
	 */
	public int inputType() {
		return view.getInputType();
	}

	/**
	 * Sets the input type on the query text field.
	 * 
	 * @param inputType
	 *            the input type to set on the query text field
	 * @return self
	 */
	public self inputType(int inputType) {
		view.setInputType(inputType);
		return (self) this;
	}

	/**
	 * Gets the specified maximum width in pixels, if set. Returns zero if no
	 * maximum width was specified.
	 * 
	 * @return the maximum width of the view
	 */
	public int maxWidth() {
		return view.getMaxWidth();
	}

	/**
	 * Makes the view at most this many pixels wide
	 * 
	 * @param maxPixels
	 * @return self
	 */
	public self maxWidth(int maxPixels) {
		view.setMaxWidth(maxPixels);
		return (self) this;
	}

	/**
	 * Returns the query string currently in the text field.
	 * 
	 * @return the query string
	 */
	public CharSequence query() {
		return view.getQuery();
	}

	/**
	 * Sets a query string in the text field and optionally submits the query as
	 * well.
	 * 
	 * @param query
	 *            the query string. This replaces any query text already present
	 *            in the text field.
	 * @param submit
	 *            whether to submit the query right now or only update the
	 *            contents of text field.
	 * @return self
	 */
	public self query(CharSequence query, boolean submit) {
		view.setQuery(query, submit);
		return (self) this;
	}

	/**
	 * Sets a listener for user actions within the SearchView.
	 * 
	 * @param queryTextListener
	 *            the listener object that receives callbacks when the user
	 *            performs actions in the SearchView such as clicking on buttons
	 *            or typing a query.
	 * @return self
	 */
	public self query(vapor.listeners.widget.searchview.$query queryTextListener) {
		view.setOnQueryTextListener(queryTextListener);
		return (self) this;
	}

	/**
	 * Sets a listener to inform when the focus of the query text field changes.
	 * 
	 * @param queryTextFocusListener
	 *            the listener to inform of focus changes.
	 * @return self
	 */
	public self queryFocus(vapor.listeners.view.$focus queryTextFocusListener) {
		view.setOnQueryTextFocusChangeListener(queryTextFocusListener);
		return (self) this;
	}

	/**
	 * Returns whether query refinement is enabled for all items or only
	 * specific ones.
	 * 
	 * @return true if enabled for all items, false otherwise.
	 */
	public boolean refineQueries() {
		return view.isQueryRefinementEnabled();
	}

	/**
	 * Specifies if a query refinement button should be displayed alongside each
	 * suggestion or if it should depend on the flags set in the individual
	 * items retrieved from the suggestions provider. Clicking on the query
	 * refinement button will replace the text in the query text field with the
	 * text from the suggestion. This flag only takes effect if a SearchableInfo
	 * has been specified with info(SearchableInfo) and not when using a custom
	 * adapter.
	 * 
	 * @param queryRefinementEnabled
	 *            true if all items should have a query refinement button, false
	 *            if only those items that have a query refinement flag set
	 *            should have the button.
	 * @return self
	 */
	public self refineQueries(boolean queryRefinementEnabled) {
		view.setQueryRefinementEnabled(queryRefinementEnabled);
		return (self) this;
	}

	/**
	 * Sets a listener to inform when the search button is pressed. This is only
	 * relevant when the text field is not visible by default. Calling
	 * iconified(false) can also cause this listener to be informed.
	 * 
	 * @param searchClickListener
	 *            the listener to inform when the search button is clicked or
	 *            the text field is programmatically de-iconified.
	 * @return self
	 */
	public self search(vapor.listeners.view.$click searchClickListener) {
		view.setOnSearchClickListener(searchClickListener);
		return (self) this;
	}

	/**
	 * Returns whether the submit button is enabled when necessary or never
	 * displayed.
	 * 
	 * @return whether the submit button is enabled automatically when necessary
	 */
	public boolean submitable() {
		return view.isSubmitButtonEnabled();
	}

	/**
	 * Enables showing a submit button when the query is non-empty. In cases
	 * where the SearchView is being used to filter the contents of the current
	 * activity and doesn't launch a separate results activity, then the submit
	 * button should be disabled.
	 * 
	 * @param submitButtonEnabled
	 *            true to show a submit button for submitting queries, false if
	 *            a submit button is not required.
	 * @return self
	 */
	public self submitable(boolean submitButtonEnabled) {
		view.setSubmitButtonEnabled(submitButtonEnabled);
		return (self) this;
	}

	/**
	 * Sets a listener to inform when a suggestion is focused or clicked.
	 * 
	 * @param suggestionListener
	 *            the listener to inform of suggestion selection events.
	 * @return self
	 */
	public self suggestion(
			vapor.listeners.widget.searchview.$suggestion suggestionListener) {
		view.setOnSuggestionListener(suggestionListener);
		return (self) this;
	}

	/**
	 * Returns the adapter used for suggestions, if any.
	 * 
	 * @return the suggestions adapter
	 */
	public CursorAdapter adapter() {
		return view.getSuggestionsAdapter();
	}

	/**
	 * You can set a custom adapter if you wish. Otherwise the default adapter
	 * is used to display the suggestions from the suggestions provider
	 * associated with the SearchableInfo.
	 * 
	 * @param suggestionsAdapter
	 * @return self
	 */
	public self adapter(CursorAdapter suggestionsAdapter) {
		view.setSuggestionsAdapter(suggestionsAdapter);
		return (self) this;
	}

	/* INTERFACE : CollapsibleActionView */
	/**
	 * NOTE: This method serves to satisfy the CollapsibleActionView interface,
	 * use the equivalent VAPOR FLUENT method collapsed() instead
	 */
	public void onActionViewCollapsed() {
		collapsed(); // using the Vapor method in case we embellish it in future
	}

	/**
	 * NOTE: This method serves to satisfy the CollapsibleActionView interface,
	 * use the equivalent VAPOR FLUENT method expanded() instead
	 */
	public void onActionViewExpanded() {
		expanded(); // using the Vapor method in case we embellish it in future
	}

}
