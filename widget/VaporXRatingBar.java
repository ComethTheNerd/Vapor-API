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

/*This file has been autogenerated by the Vapid Tool v1.0 @ 22/12/2012 22:43:13*/
package vapor.widget;

import java.util.ArrayList;

import android.widget.RatingBar;

/**
 * Fluent variadic Vapor companion to RatingBar, an extension of SeekBar and
 * ProgressBar that shows a rating in stars. The user can touch/drag or use
 * arrow keys to set the rating when using the default size RatingBar. The
 * smaller RatingBar style ( ratingBarStyleSmall) and the larger indicator-only
 * style (ratingBarStyleIndicator) do not support user interaction and should
 * only be used as indicators.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from RatingBar
 * @param <X>
 *            A Vapor type derived from VaporRatingBar
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporXRatingBar<T extends RatingBar, X extends VaporRatingBar<T, ?>, self extends VaporXRatingBar<T, X, self>>
		extends VaporXAbsSeekBar<T, X, self> {

	public VaporXRatingBar(Integer... ids) {
		super(ids);
	}

	public VaporXRatingBar(T... ratingBars) {
		super(ratingBars);
	}

	public VaporXRatingBar(X... vaporRatingBars) {
		super(vaporRatingBars);
	}

	public VaporXRatingBar(VaporXRatingBar<T, X, ?>... vaporXRatingBars) {
		super(vaporXRatingBars);
	}

	public VaporXRatingBar(Object... ratingBarItems) {
		super(ratingBarItems);
	}

	/**
	 * Sets the listener to be called when the rating changes.
	 * 
	 * @param ratingBarChangeListener
	 *            The listener.
	 * @return this
	 */
	public self change(
			vapor.listeners.widget.ratingbar.$change ratingBarChangeListener) {
		for (X vaporView : members)
			vaporView.change(ratingBarChangeListener);
		return (self) this;

	}

	/**
	 * Gets the step size of this rating bar.
	 * 
	 * @return The step size.
	 */
	public ArrayList<Float> granularity() {
		int _size = this.members.size();
		ArrayList<Float> _returns = new ArrayList<Float>(_size);
		for (int _index = 0; _index < _size; _index++)
			_returns.add(this.members.get(_index).granularity());
		return _returns;
	}

	/**
	 * Sets the step size (granularity) of this rating bar.
	 * 
	 * @param stepSize
	 *            The step size of this rating bar. For example, if half-star
	 *            granularity is wanted, this would be 0.5.
	 * @return this
	 */
	public self granularity(float stepSize) {
		for (X vaporView : members)
			vaporView.granularity(stepSize);
		return (self) this;

	}

	/**
	 * Returns whether this rating bar is only an indicator.
	 * 
	 * @return Whether this rating bar is only an indicator.
	 */
	public ArrayList<Boolean> indicator() {
		int _size = this.members.size();
		ArrayList<Boolean> _returns = new ArrayList<Boolean>(_size);
		for (int _index = 0; _index < _size; _index++)
			_returns.add(this.members.get(_index).indicator());
		return _returns;
	}

	/**
	 * Whether this rating bar should only be an indicator (thus non-changeable
	 * by the user).
	 * 
	 * @param isIndicator
	 *            Whether it should be an indicator.
	 * @return this
	 */
	public self indicator(boolean isIndicator) {
		for (X vaporView : members)
			vaporView.indicator(isIndicator);
		return (self) this;

	}

	/**
	 * Gets the current rating (number of stars filled).
	 * 
	 * @return The current rating
	 */
	public ArrayList<Float> rating() {
		int _size = this.members.size();
		ArrayList<Float> _returns = new ArrayList<Float>(_size);
		for (int _index = 0; _index < _size; _index++)
			_returns.add(this.members.get(_index).rating());
		return _returns;
	}

	/**
	 * Sets the rating (the number of stars filled).
	 * 
	 * @param rating
	 *            The rating to set.
	 * @return this
	 */
	public self rating(float rating) {
		for (X vaporView : members)
			vaporView.rating(rating);
		return (self) this;

	}

	/**
	 * Returns the number of stars shown.
	 * 
	 * @return The number of stars shown.
	 */
	public ArrayList<Integer> stars() {
		int _size = this.members.size();
		ArrayList<Integer> _returns = new ArrayList<Integer>(_size);
		for (int _index = 0; _index < _size; _index++)
			_returns.add(this.members.get(_index).stars());
		return _returns;
	}

	/**
	 * Sets the number of stars to show. In order for these to be shown
	 * properly, it is recommended the layout width of this widget be wrap
	 * content.
	 * 
	 * @param numberOfStars
	 *            The number of stars.
	 * @return this
	 */
	public self stars(int numberOfStars) {
		for (X vaporView : members)
			vaporView.stars(numberOfStars);
		return (self) this;

	}

}