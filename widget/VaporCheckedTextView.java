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
import android.widget.Checkable;
import android.widget.CheckedTextView;

/**
 * Fluent Vapor companion to CheckedTextView, an extension to TextView that
 * supports the Checkable interface. This is useful when used in a ListView
 * where the it's setChoiceMode has been set to something other than
 * CHOICE_MODE_NONE.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from CheckedTextView
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporCheckedTextView<T extends CheckedTextView, self extends VaporCheckedTextView<T, self>>
		extends VaporTextView<T, self> implements Checkable {

	public VaporCheckedTextView(int id) {
		super(id);
	}

	public VaporCheckedTextView(T checkedTextView) {
		super(checkedTextView);
	}

	/**
	 * 
	 * @return
	 */
	public boolean checked() {
		return view.isChecked();
	}

	/**
	 * Changes the checked state of this text view.
	 * 
	 * @param checked
	 *            true to check the text, false to uncheck it
	 * @return self
	 */
	public self checked(boolean checked) {
		view.setChecked(checked);
		return (self) this;
	}

	/**
	 * Gets the checkmark drawable
	 * 
	 * @return The drawable use to represent the checkmark, if any.
	 */
	public Drawable mark() {
		return view.getCheckMarkDrawable();
	}

	/**
	 * Set the checkmark to a given Drawable, identified by its resourece id.
	 * This will be drawn when isChecked() is true.
	 * 
	 * @param resId
	 *            The Drawable to use for the checkmark.
	 * @return self
	 */
	public self mark(int resId) {
		view.setCheckMarkDrawable(resId);
		return (self) this;
	}

	/**
	 * Set the checkmark to a given Drawable. This will be drawn when
	 * isChecked() is true.
	 * 
	 * @param checkMarkDrawable
	 *            The Drawable to use for the checkmark.
	 * @return self
	 */
	public self mark(Drawable checkMarkDrawable) {
		view.setCheckMarkDrawable(checkMarkDrawable);
		return (self) this;
	}

	/**
	 * Change the checked state of the view to the inverse of its current state
	 * 
	 * @return self
	 */
	public self tog() {
		view.toggle();
		return (self) this;
	}

	/* INTERFACE : Checkable */
	/**
	 * NOTE: This method serves to satisfy the Checkable interface, use the
	 * equivalent VAPOR FLUENT method checked() instead
	 */
	@Override
	public boolean isChecked() {
		return checked();
	}

	/**
	 * NOTE: This method serves to satisfy the Checkable interface, use the
	 * equivalent VAPOR FLUENT method checked(boolean) instead
	 */
	@Override
	public void setChecked(boolean checked) {
		checked(checked);
	}

	/**
	 * NOTE: This method serves to satisfy the Checkable interface, use the
	 * equivalent VAPOR FLUENT method tog() instead
	 */
	@Override
	public void toggle() {
		tog();
	}
}
