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

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

/**
 * Fluent Vapor companion to Spinner, a view that displays one child at a time
 * and lets the user pick among them. The items in the Spinner come from the
 * Adapter associated with this view.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from Spinner
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporSpinner<T extends Spinner, self extends VaporSpinner<T, self>>
		extends VaporAbsSpinner<T, self> implements
		vapor.listeners.content.dialog.$click {

	public VaporSpinner(int id) {
		super(id);
	}

	public VaporSpinner(T spinner) {
		super(spinner);
	}

	@Override
	public self adapter(SpinnerAdapter adapter) {
		view.setAdapter(adapter);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onClick(DialogInterface,int),
	 * called when a button in the dialog is clicked.
	 * 
	 * @param dialog
	 *            The dialog that received the click.
	 * @param which
	 *            The button that was clicked (e.g. BUTTON1) or the position of
	 *            the item clicked.
	 * @return self
	 */
	public self click(DialogInterface dialog, int which) {
		view.onClick(dialog, which);
		return (self) this;
	}

	/**
	 * Get the configured horizontal offset in pixels for the spinner's popup
	 * window of choices. Only valid in MODE_DROPDOWN; other modes will return
	 * 0.
	 * 
	 * @return Horizontal offset in pixels
	 */
	public int popXOffset() {
		return view.getDropDownHorizontalOffset();
	}

	/**
	 * Set a horizontal offset in pixels for the spinner's popup window of
	 * choices. Only valid in MODE_DROPDOWN; this method is a no-op in other
	 * modes.
	 * 
	 * @param pixels
	 *            Horizontal offset in pixels
	 * @return self
	 */
	public self popXOffset(int pixels) {
		view.setDropDownHorizontalOffset(pixels);
		return (self) this;
	}

	/**
	 * Get the configured vertical offset in pixels for the spinner's popup
	 * window of choices. Only valid in MODE_DROPDOWN; other modes will return
	 * 0.
	 * 
	 * @return Vertical offset in pixels
	 */
	public int popYOffset() {
		return view.getDropDownVerticalOffset();
	}

	/**
	 * Set a vertical offset in pixels for the spinner's popup window of
	 * choices. Only valid in MODE_DROPDOWN; this method is a no-op in other
	 * modes.
	 * 
	 * @param pixels
	 *            Vertical offset in pixels
	 * @return self
	 */
	public self popYOffset(int pixels) {
		view.setDropDownVerticalOffset(pixels);
		return (self) this;
	}

	/**
	 * Get the configured width of the spinner's popup window of choices in
	 * pixels. The returned value may also be MATCH_PARENT meaning the popup
	 * window will match the width of the Spinner itself, or WRAP_CONTENT to wrap
	 * to the measured size of contained dropdown list items.
	 * 
	 * @return Width in pixels, WRAP_CONTENT, or MATCH_PARENT
	 */
	public int popWidth() {
		return view.getDropDownWidth();
	}

	/**
	 * Set the width of the spinner's popup window of choices in pixels. This
	 * value may also be set to MATCH_PARENT to match the width of the Spinner
	 * itself, or WRAP_CONTENT to wrap to the measured size of contained dropdown
	 * list items.
	 * 
	 * Only valid in MODE_DROPDOWN; this method is a no-op in other modes.
	 * 
	 * @param pixels
	 *            Width in pixels, WRAP_CONTENT, or MATCH_PARENT
	 * @return self
	 */
	public self popWidth(int pixels) {
		view.setDropDownWidth(pixels);
		return (self) this;
	}

	/**
	 * Describes how the selected item view is positioned. The default is
	 * determined by the current theme.
	 * 
	 * @return A Gravity constant for placing an object within a potentially
	 *         larger container.
	 */
	public int gravity() {
		return view.getGravity();
	}

	/**
	 * Describes how the selected item view is positioned. Currently only the
	 * horizontal component is used. The default is determined by the current
	 * theme.
	 * 
	 * @param gravity
	 *            A Gravity constant for placing an object within a potentially
	 *            larger container.
	 * @return self
	 */
	public self gravity(int gravity) {
		view.setGravity(gravity);
		return (self) this;
	}

	/**
	 * Get the background drawable for the spinner's popup window of choices.
	 * Only valid in MODE_DROPDOWN; other modes will return null.
	 * 
	 * @return Background drawable
	 */
	public Drawable popBg() {
		return view.getPopupBackground();
	}

	/**
	 * Set the background drawable for the spinner's popup window of choices.
	 * Only valid in MODE_DROPDOWN; this method is a no-op in other modes.
	 * 
	 * @param resId
	 *            Resource ID of a background drawable
	 * @return self
	 */
	public self popBg(int resId) {
		view.setPopupBackgroundResource(resId);
		return (self) this;
	}

	/**
	 * Set the background drawable for the spinner's popup window of choices.
	 * Only valid in MODE_DROPDOWN; this method is a no-op in other modes.
	 * 
	 * @param popUpDrawable
	 *            Background drawable
	 * @return self
	 */
	public self popBg(Drawable popUpDrawable) {
		view.setPopupBackgroundDrawable(popUpDrawable);
		return (self) this;
	}

	/**
	 * Returns the prompt to display when the dialog is shown
	 * 
	 * @return The prompt to display when the dialog is shown
	 */
	public CharSequence prompt() {
		return view.getPrompt();
	}

	/**
	 * Sets the prompt to display when the dialog is shown.
	 * 
	 * @param promptId
	 *            the resource ID of the prompt to display when the dialog is
	 *            shown
	 * @return self
	 */
	public self prompt(int promptId) {
		view.setPromptId(promptId);
		return (self) this;
	}

	/**
	 * Sets the prompt to display when the dialog is shown.
	 * 
	 * @param prompt
	 *            the prompt to set
	 * @return self
	 */
	public self prompt(CharSequence prompt) {
		view.setPrompt(prompt);
		return (self) this;
	}

	/* INTERFACE : DialogInterface.OnClickListener */
	/**
	 * NOTE: This method serves to satisfy the DialogInterface.OnClickListener
	 * interface, use the equivalent VAPOR FLUENT method
	 * clicked(DialogInterface,int) instead
	 */
	@Override
	public void onClick(DialogInterface dialog, int which) {
		click(dialog, which);
	}

}
