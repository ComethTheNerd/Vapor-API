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

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.widget.Switch;

/**
 * Fluent Vapor companion to Switch, a two-state toggle switch widget that can
 * select between two options. The user may drag the "thumb" back and forth to
 * choose the selected option, or simply tap to toggle as if it were a checkbox.
 * The text property controls the text displayed in the label for the switch,
 * whereas the off and on text controls the text on the thumb.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from Switch
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporSwitch<T extends Switch, self extends VaporSwitch<T, self>>
		extends VaporCompoundButton<T, self> {

	public VaporSwitch(int id) {
		super(id);
	}

	public VaporSwitch(T switchView) {
		super(switchView);
	}

	/**
	 * Returns the text displayed when the button is in the checked state.
	 * 
	 * @return The text displayed when the button is in the checked state.
	 */
	public CharSequence checkedText() {
		return view.getTextOn();
	}

	/**
	 * Sets the text displayed when the button is in the checked state.
	 * 
	 * @param textOn
	 * @return self
	 */
	public self checkedText(CharSequence textOn) {
		view.setTextOn(textOn);
		return (self) this;
	}

	/**
	 * Sets the typeface in which the text should be displayed on the switch.
	 * Note that not all Typeface families actually have bold and italic
	 * variants, so you may need to use font(Typeface, int) to get the appearance
	 * that you actually want.
	 * 
	 * @param typeFace
	 * @return self
	 */
	@Override
	public self font(Typeface typeFace) {
		view.setSwitchTypeface(typeFace); // intentional override
		return (self) this;
	}

	/**
	 * Sets the typeface and style in which the text should be displayed on the
	 * switch, and turns on the fake bold and italic bits in the Paint if the
	 * Typeface that you provided does not have all the bits in the style that
	 * you specified.
	 * 
	 * @param typeFace
	 * @param style
	 * @return self
	 */
	@Override
	public self font(Typeface typeFace, int style) {
		view.setSwitchTypeface(typeFace, style); // intentional override
		return (self) this;
	}

	/**
	 * Sets the switch text color, size, style, hint color, and highlight color
	 * from the specified TextAppearance resource.
	 * 
	 * @param context
	 * @param resId
	 * @return self
	 */
	@Override
	public self appearance(Context context, int resId) {
		view.setSwitchTextAppearance(context, resId); // intentional override
		return (self) this;
	}

	/**
	 * Get the minimum width of the switch in pixels. The switch's width will be
	 * the maximum of this value and its measured width as determined by the
	 * switch drawables and text used.
	 * 
	 * @return Minimum width of the switch in pixels
	 */
	public int minWidth() {
		return view.getSwitchMinWidth(); // intentional override
	}

	/**
	 * Set the minimum width of the switch in pixels. The switch's width will be
	 * the maximum of this value and its measured width as determined by the
	 * switch drawables and text used.
	 * 
	 * @param switchMinWidth
	 *            Minimum width of the switch in pixels
	 * @return self
	 */
	public self minWidth(int switchMinWidth) {
		view.setSwitchMinWidth(switchMinWidth); // intentional override
		return (self) this;
	}

	/**
	 * Get the amount of horizontal padding between the switch and the
	 * associated text.
	 * 
	 * @return Amount of padding in pixels
	 */
	public int pad() {
		return view.getSwitchPadding();
	}

	/**
	 * Set the amount of horizontal padding between the switch and the
	 * associated text.
	 * 
	 * @param switchPadding
	 *            Amount of padding in pixels
	 * @return self
	 */
	public self pad(int switchPadding) {
		view.setSwitchPadding(switchPadding); // intentional override
		return (self) this;
	}

	/**
	 * Get the drawable used for the switch "thumb" - the piece that the user
	 * can physically touch and drag along the track.
	 * 
	 * @return Thumb drawable
	 */
	public Drawable thumb() {
		return view.getThumbDrawable();
	}

	/**
	 * Set the drawable used for the switch "thumb" - the piece that the user
	 * can physically touch and drag along the track.
	 * 
	 * @param resId
	 *            Resource ID of a thumb drawable
	 * @return self
	 */
	public self thumb(int resId) {
		view.setThumbResource(resId);
		return (self) this;
	}

	/**
	 * Set the drawable used for the switch "thumb" - the piece that the user
	 * can physically touch and drag along the track.
	 * 
	 * @param thumb
	 *            Thumb drawable
	 * @return self
	 */
	public self thumb(Drawable thumb) {
		view.setThumbDrawable(thumb);
		return (self) this;
	}

	/**
	 * Get the horizontal padding around the text drawn on the switch itself.
	 * 
	 * @return Horizontal padding for switch thumb text in pixels
	 */
	public int thumbPad() {
		return view.getThumbTextPadding();
	}

	/**
	 * Set the horizontal padding around the text drawn on the switch itself.
	 * 
	 * @param pixels
	 *            Horizontal padding for switch thumb text in pixels
	 * @return
	 */
	public self thumbPad(int pixels) {
		view.setThumbTextPadding(pixels);
		return (self) this;
	}

	/**
	 * Get the drawable used for the track that the switch slides within.
	 * 
	 * @return Track drawable
	 */
	public Drawable track() {
		return view.getTrackDrawable();
	}

	/**
	 * Set the drawable used for the track that the switch slides within.
	 * 
	 * @param resId
	 *            Resource ID of a track drawable
	 * @return self
	 */
	public self track(int resId) {
		view.setTrackResource(resId);
		return (self) this;
	}

	/**
	 * Set the drawable used for the track that the switch slides within.
	 * 
	 * @param track
	 *            Track drawable
	 * @return self
	 */
	public self track(Drawable track) {
		view.setTrackDrawable(track);
		return (self) this;
	}

	/**
	 * Returns the text displayed when the button is not in the checked state.
	 * 
	 * @return The text displayed when the button is not in the checked state.
	 */
	public CharSequence uncheckedText() {
		return view.getTextOff();
	}

	/**
	 * Sets the text displayed when the button is not in the checked state.
	 * 
	 * @param textOff
	 * @return self
	 */
	public self uncheckedText(CharSequence textOff) {
		view.setTextOff(textOff);
		return (self) this;
	}
}
