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

package vapor.inputmethodservice;

import vapor.core.$;
import vapor.view.VaporView;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.View;

// Checked: 051220121346

/**
 * Fluent Vapor companion to KeyboardView, a view that renders a virtual
 * Keyboard. It handles rendering of keys and detecting key presses and touch
 * movements.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from KeyboardView
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporKeyboardView<T extends KeyboardView, self extends VaporKeyboardView<T, self>>
		extends VaporView<T, self> implements vapor.listeners.view.$click {

	public VaporKeyboardView(int id) {
		super(id);
	}

	public VaporKeyboardView(T keyboardView) {
		super(keyboardView);
	}

	/**
	 * 
	 * @return
	 */
	public boolean back() {
		return view.handleBack();
	}

	/**
	 * Fluent equivalent Vapor method for invoking onClick(View)
	 * 
	 * @param view
	 * @return self
	 */
	public self click(VaporView<? extends View, ?> view) {
		this.view.onClick(view.view());
		return (self) this;
	}

	/**
	 * 
	 * @return self
	 */
	public self closing() {
		view.closing();
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onDetachedFromWindow(),
	 * called when the view is detached from a window. At this point it no
	 * longer has a surface for drawing.
	 * 
	 * @return self
	 */
	public self detached() {
		view.onDetachedFromWindow();
		return (self) this;
	}

	/**
	 * Invalidates a key so that it will be redrawn on the next repaint. Use
	 * this method if only one key is changing it's content. Any changes that
	 * affect the position or size of the key may not be honored.
	 * 
	 * @param keyIndex
	 *            the index of the key in the attached Keyboard.
	 * @return self
	 */
	public self invalidate(int keyIndex) {
		view.invalidateKey(keyIndex);
		return (self) this;
	}

	/**
	 * Requests a redraw of the entire keyboard. Calling invalidate() is not
	 * sufficient because the keyboard renders the keys to an off-screen buffer
	 * and an invalidate() only draws the cached buffer.
	 * 
	 * @return self
	 */
	public self invalidateKeys() {
		view.invalidateAllKeys();
		return (self) this;
	}

	/**
	 * Returns the current keyboard being displayed by this view.
	 * 
	 * @return the currently attached keyboard
	 */
	public Keyboard keyboard() {
		return view.getKeyboard();
	}

	/**
	 * Attaches a keyboard to this view. The keyboard can be switched at any
	 * time and the view will re-layout itself to accommodate the keyboard.
	 * 
	 * @param keyboard
	 *            the keyboard to display in this view
	 * @return self
	 */
	public self keyboard(Keyboard keyboard) {
		view.setKeyboard(keyboard);
		return (self) this;
	}

	/**
	 * 
	 * @param keyboardActionListener
	 * @return self
	 */
	public self key(
			vapor.listeners.inputmethodservice.keyboardview.$key keyboardActionListener) {
		view.setOnKeyboardActionListener(keyboardActionListener);
		return (self) this;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return self
	 */
	public self popOffset(int x, int y) {
		view.setPopupOffset(x, y);
		return (self) this;
	}

	/**
	 * 
	 * @param popUpParent
	 * @return self
	 */
	public self popParent(VaporView<? extends View, ?> popUpParent) {
		this.view.setPopupParent(popUpParent.view());
		return (self) this;
	}

	/**
	 * Returns the enabled state of the key feedback popup.
	 * 
	 * @return whether or not the key feedback popup is enabled
	 */
	public boolean previewable() {
		return view.isPreviewEnabled();
	}

	/**
	 * Enables or disables the key feedback popup. This is a popup that shows a
	 * magnified version of the depressed key. By default the preview is
	 * enabled.
	 * 
	 * @param previewEnabled
	 *            whether or not to enable the key feedback popup
	 * @return self
	 */
	public self previewable(boolean previewEnabled) {
		view.setPreviewEnabled(previewEnabled);
		return (self) this;
	}

	/**
	 * Returns true if proximity correction is enabled.
	 * 
	 * @return true if proximity correction is enabled.
	 */
	public boolean proxCorrect() {
		return view.isProximityCorrectionEnabled();
	}

	/**
	 * When enabled, calls to onKey(int, int[]) will include key codes for
	 * adjacent keys. When disabled, only the primary key code will be reported.
	 * 
	 * @param proximityCorrectionEnabled
	 *            whether or not the proximity correction is enabled
	 * @return self
	 */
	public self proxCorrect(boolean proximityCorrectionEnabled) {
		view.setProximityCorrectionEnabled(proximityCorrectionEnabled);
		return (self) this;
	}

	/**
	 * Returns the state of the shift key of the keyboard, if any.
	 * 
	 * @return true if the shift is in a pressed state, false otherwise. If
	 *         there is no shift key on the keyboard or there is no keyboard
	 *         attached, it returns false.
	 */
	public boolean shifted() {
		return view.isShifted();
	}

	/**
	 * Sets the state of the shift key of the keyboard, if any.
	 * 
	 * @param shifted
	 *            whether or not to enable the state of the shift key
	 * @return true if the shift key state changed, false if there was no change
	 */
	public boolean shifted(boolean shifted) {
		return view.setShifted(shifted);
	}

	/**
	 * Fluent equivalent Vapor method for invoking
	 * onSizeChanged(int,int,int,int), called during layout when the size of
	 * this view has changed. If you were just added to the view hierarchy,
	 * you're called with the old values of 0.
	 * 
	 * @param width
	 *            Current width of this view.
	 * @param height
	 *            Current height of this view.
	 * @param oldWidth
	 *            Old width of this view.
	 * @param oldHeight
	 *            Old height of this view.
	 * @return self
	 */
	public self sizeChanged(int width, int height, int oldWidth, int oldHeight) { // tense
																					// of
																					// this
																					// method
																					// name
		view.onSizeChanged(width, height, oldWidth, oldHeight);
		return (self) this;
	}

	/**
	 * 
	 * @param verticalOffset
	 * @return self
	 */
	public self yCorrect(int verticalOffset) {
		view.setVerticalCorrection(verticalOffset);
		return (self) this;
	}

	/* INTERFACE : ViewTreeObserver.OnTouchModeChangeListener */
	/**
	 * NOTE: This method serves to satisfy the
	 * ViewTreeObserver.OnTouchModeChangeListener interface, use the equivalent
	 * VAPOR FLUENT method touchModeChange(boolean) instead
	 */
	@Override
	public void onClick(View v) {
		click($.vapor(v));
	}

}
