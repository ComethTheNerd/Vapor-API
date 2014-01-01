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
import android.widget.AbsSeekBar;

/**
 * Fluent Vapor companion to AbsSeekBar.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from AbsSeekBar
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public abstract class VaporAbsSeekBar<T extends AbsSeekBar, self extends VaporAbsSeekBar<T, self>>
		extends VaporProgressBar<T, self> {

	public VaporAbsSeekBar(int id) {
		super(id);
	}

	public VaporAbsSeekBar(T absSeekBar) {
		super(absSeekBar);
	}

	/**
	 * Returns the amount of progress changed via the arrow keys.
	 * 
	 * @return The amount to increment or decrement when the user presses the
	 *         arrow keys.
	 */
	public int keyIncrement() {
		return view.getKeyProgressIncrement();
	}

	/**
	 * Sets the amount of progress changed via the arrow keys.
	 * 
	 * @param increment
	 *            The amount to increment or decrement when the user presses the
	 *            arrow keys.
	 * @return self
	 */
	public self keyIncrement(int increment) {
		view.setKeyProgressIncrement(increment);
		return (self) this;
	}

	/**
	 * Return the drawable used to represent the scroll thumb - the component
	 * that the user can drag back and forth indicating the current value by its
	 * position.
	 * 
	 * @return The current thumb drawable
	 */
	public Drawable thumb() {
		return view.getThumb();
	}

	/**
	 * Sets the thumb that will be drawn at the end of the progress meter within
	 * the SeekBar.
	 * 
	 * @param thumb
	 *            Drawable representing the thumb
	 * @return self
	 */
	public self thumb(Drawable thumb) {
		view.setThumb(thumb);
		return (self) this;
	}

	/**
	 * 
	 * @return
	 */
	public int thumbOffset() {
		return view.getThumbOffset();
	}

	/**
	 * Sets the thumb offset that allows the thumb to extend out of the range of
	 * the track.
	 * 
	 * @param thumbOffset
	 *            The offset amount in pixels.
	 * @return self
	 */
	public self thumbOffset(int thumbOffset) {
		view.setThumbOffset(thumbOffset);
		return (self) this;
	}

}
