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
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.view.animation.Interpolator;
import android.widget.ProgressBar;

/**
 * Fluent Vapor companion to ProgressBar, a visual indicator of progress in some
 * operation. Displays a bar to the user representing how far the operation has
 * progressed; the application can change the amount of progress (modifying the
 * length of the bar) as it moves forward. There is also a secondary progress
 * displayable on a progress bar which is useful for displaying intermediate
 * progress, such as the buffer level during a streaming playback progress bar.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from ProgressBar
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporProgressBar<T extends ProgressBar, self extends VaporProgressBar<T, self>>
		extends VaporView<T, self> {

	public VaporProgressBar(int id) {
		super(id);
	}

	public VaporProgressBar(T progressBar) {
		super(progressBar);
	}

	/**
	 * Increase the progress bar's progress by the specified amount.
	 * 
	 * @param increaseBy
	 *            the amount by which the progress must be increased
	 * @return this
	 */
	public synchronized final self increment(int increaseBy) {
		view.incrementProgressBy(increaseBy);
		return (self) this;
	}

	/**
	 * Increase the progress bar's secondary progress by the specified amount.
	 * 
	 * @param increaseBy
	 *            the amount by which the secondary progress must be increased
	 * @return this
	 */
	public synchronized final self increment2nd(int increaseBy) {
		view.incrementSecondaryProgressBy(increaseBy);
		return (self) this;
	}

	/**
	 * Indicate whether this progress bar is in indeterminate mode.
	 * 
	 * @return true if the progress bar is in indeterminate mode
	 */
	public synchronized boolean indeterminate() {
		return view.isIndeterminate();
	}

	/**
	 * Change the indeterminate mode for this progress bar. In indeterminate
	 * mode, the progress is ignored and the progress bar shows an infinite
	 * animation instead.
	 * 
	 * If this progress bar's style only supports indeterminate mode (such as
	 * the circular progress bars), then this will be ignored.
	 * 
	 * @param indeterminate
	 *            true to enable the indeterminate mode
	 * @return this
	 */
	public synchronized self indeterminate(boolean indeterminate) {
		view.setIndeterminate(indeterminate);
		return (self) this;
	}

	/**
	 * Get the drawable used to draw the progress bar in indeterminate mode.
	 * 
	 * @return a Drawable instance
	 */
	public Drawable indeterminateImg() {
		return view.getIndeterminateDrawable();
	}

	/**
	 * Define the drawable used to draw the progress bar in indeterminate mode.
	 * 
	 * @param drawable
	 *            the new drawable
	 * @return this
	 */
	public self indeterminateImg(Drawable drawable) {
		view.setIndeterminateDrawable(drawable);
		return (self) this;
	}

	/**
	 * Gets the acceleration curve type for the indeterminate animation.
	 * 
	 * @return the Interpolator associated to this animation
	 */
	public Interpolator interpolator() {
		return view.getInterpolator();
	}

	/**
	 * Sets the acceleration curve for the indeterminate animation. Defaults to
	 * a linear interpolation.
	 * 
	 * @param interpolator
	 *            The interpolator which defines the acceleration curve
	 * @return this
	 */
	public self interpolator(Interpolator interpolator) {
		view.setInterpolator(interpolator);
		return (self) this;
	}

	/**
	 * Sets the acceleration curve for the indeterminate animation. The
	 * interpolator is loaded as a resource from the specified context.
	 * 
	 * @param context
	 *            The application environment
	 * @param resId
	 *            The resource identifier of the interpolator to load
	 * @return this
	 */
	public self interpolator(Context context, int resId) {
		view.setInterpolator(context, resId);
		return (self) this;
	}

	/**
	 * Return the upper limit of this progress bar's range.
	 * 
	 * @return a positive integer
	 */
	public synchronized int maxVal() {
		return view.getMax();
	}

	/**
	 * Set the range of the progress bar to 0...max.
	 * 
	 * @param max
	 *            the upper range of this progress bar
	 * @return this
	 */
	public synchronized self maxVal(int max) {
		view.setMax(max);
		return (self) this;
	}

	/**
	 * Get the progress bar's current level of progress. Return 0 when the
	 * progress bar is in indeterminate mode.
	 * 
	 * @return the current progress, between 0 and max()
	 */
	public synchronized int progress() {
		return view.getProgress();
	}

	/**
	 * Set the current progress to the specified value. Does not do anything if
	 * the progress bar is in indeterminate mode.
	 * 
	 * @param progress
	 *            the new progress, between 0 and max()
	 * @return this
	 */
	public synchronized self progress(int progress) {
		view.setProgress(progress);
		return (self) this;
	}

	/**
	 * Get the progress bar's current level of secondary progress. Return 0 when
	 * the progress bar is in indeterminate mode.
	 * 
	 * @return the current secondary progress, between 0 and max()
	 */
	public synchronized int progress2nd() {
		return view.getSecondaryProgress();
	}

	/**
	 * Set the current secondary progress to the specified value. Does not do
	 * anything if the progress bar is in indeterminate mode.
	 * 
	 * @param secondaryProgress
	 *            the new secondary progress, between 0 and max()
	 * @return this
	 */
	public self progress2nd(int secondaryProgress) {
		view.setSecondaryProgress(secondaryProgress);
		return (self) this;
	}

	/**
	 * Get the drawable used to draw the progress bar in progress mode.
	 * 
	 * @return a Drawable instance
	 */
	public Drawable progressImg() {
		return view.getProgressDrawable();
	}

	/**
	 * Define the drawable used to draw the progress bar in progress mode.
	 * 
	 * @param drawable
	 *            the new drawable
	 * @return this
	 */
	public self progressImg(Drawable drawable) {
		view.setProgressDrawable(drawable);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking
	 * onRestoreInstanceState(Parcelable), a hook allowing a view to re-apply a
	 * representation of its internal state that had previously been generated by
	 * save(). This function will never be called with a null state.
	 * 
	 * @param state
	 *            The frozen state that had previously been returned by save().
	 * @return this
	 */
	public self restore(Parcelable state) {
		view.onRestoreInstanceState(state);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onSaveInstanceState(), a hook
	 * allowing a view to generate a representation of its internal state that
	 * can later be used to create a new instance with that same state. This
	 * state should only contain information that is not persistent or can not be
	 * reconstructed later.
	 * 
	 * @return Returns a Parcelable object containing the view's current dynamic
	 *         state, or null if there is nothing interesting to save. The
	 *         default implementation returns null.
	 */
	public Parcelable save() {
		return view.onSaveInstanceState();
	}

}
