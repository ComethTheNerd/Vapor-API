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

/*This file has been autogenerated by the Vapid Tool v1.0 @ 22/12/2012 22:43:14*/
package vapor.widget;

import java.util.ArrayList;

import vapor.view.VaporView;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ViewAnimator;

/**
 * Fluent variadic Vapor companion to ViewAnimator, the base class for a
 * FrameLayout container that will perform animations when switching between its
 * views.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from ViewAnimator
 * @param <X>
 *            A Vapor type derived from VaporViewAnimator
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporXViewAnimator<T extends ViewAnimator, X extends VaporViewAnimator<T, ?>, self extends VaporXViewAnimator<T, X, self>>
		extends VaporXFrameLayout<T, X, self> {

	public VaporXViewAnimator(Integer... ids) {
		super(ids);
	}

	public VaporXViewAnimator(T... viewAnimators) {
		super(viewAnimators);
	}

	public VaporXViewAnimator(X... vaporViewAnimators) {
		super(vaporViewAnimators);
	}

	public VaporXViewAnimator(
			VaporXViewAnimator<T, X, ?>... vaporXViewAnimators) {
		super(vaporXViewAnimators);
	}

	public VaporXViewAnimator(Object... viewAnimatorItems) {
		super(viewAnimatorItems);
	}

	/**
	 * Returns whether the current View should be animated the first time the
	 * ViewAnimator is displayed.
	 * 
	 * @return true if the current View will be animated the first time it is
	 *         displayed, false otherwise.
	 */
	public ArrayList<Boolean> anim1st() {
		int _size = this.members.size();
		ArrayList<Boolean> _returns = new ArrayList<Boolean>(_size);
		for (int _index = 0; _index < _size; _index++)
			_returns.add(this.members.get(_index).anim1st());
		return _returns;
	}

	/**
	 * Indicates whether the current View should be animated the first time the
	 * ViewAnimation is displayed.
	 * 
	 * @param animateFirstView
	 *            True to animate the current View the first time it is
	 *            displayed, false otherwise.
	 * @return self
	 */
	public self anim1st(boolean animateFirstView) {
		for (X vaporView : members)
			vaporView.anim1st(animateFirstView);
		return (self) this;

	}

	/**
	 * Returns the current animation used to animate a View that enters the
	 * screen.
	 * 
	 * @return An VaporViewAnimation or null if none is set.
	 */
	public ArrayList<Animation> animIn() {
		int _size = this.members.size();
		ArrayList<Animation> _returns = new ArrayList<Animation>(_size);
		for (int _index = 0; _index < _size; _index++)
			_returns.add(this.members.get(_index).animIn());
		return _returns;
	}

	/**
	 * Specifies the animation used to animate a View that enters the screen.
	 * 
	 * @param context
	 *            The application's environment.
	 * @param resId
	 *            The resource id of the animation.
	 * @return self
	 */
	public self animIn(Context context, int resId) {
		for (X vaporView : members)
			vaporView.animIn(context, resId);
		return (self) this;

	}

	/**
	 * Specifies the animation used to animate a View that enters the screen.
	 * 
	 * @param inAnimation
	 *            The animation started when a View enters the screen.
	 * @return self
	 */
	public self animIn(Animation inAnimation) {
		for (X vaporView : members)
			vaporView.animIn(inAnimation);
		return (self) this;

	}

	/**
	 * Returns the current animation used to animate a View that exits the
	 * screen.
	 * 
	 * @return An VaporViewAnimation or null if none is set.
	 */
	public ArrayList<Animation> animOut() {
		int _size = this.members.size();
		ArrayList<Animation> _returns = new ArrayList<Animation>(_size);
		for (int _index = 0; _index < _size; _index++)
			_returns.add(this.members.get(_index).animOut());
		return _returns;
	}

	/**
	 * Specifies the animation used to animate a View that exit the screen.
	 * 
	 * @param context
	 *            The application's environment.
	 * @param resId
	 *            The resource id of the animation.
	 * @return self
	 */
	public self animOut(Context context, int resId) {
		for (X vaporView : members)
			vaporView.animOut(context, resId);
		return (self) this;

	}

	/**
	 * Specifies the animation used to animate a View that exit the screen.
	 * 
	 * @param outAnimation
	 *            The animation started when a View exit the screen.
	 * @return self
	 */
	public self animOut(Animation outAnimation) {
		for (X vaporView : members)
			vaporView.animOut(outAnimation);
		return (self) this;

	}

	/**
	 * Returns the index of the currently displayed child view.
	 * 
	 * @return
	 */
	public ArrayList<Integer> itemSelected() {
		int _size = this.members.size();
		ArrayList<Integer> _returns = new ArrayList<Integer>(_size);
		for (int _index = 0; _index < _size; _index++)
			_returns.add(this.members.get(_index).itemSelected());
		return _returns;
	}

	/**
	 * Sets which child view will be displayed.
	 * 
	 * @param whichChild
	 *            the index of the child view to display
	 * @return self
	 */
	public self itemSelected(int whichChild) {
		for (X vaporView : members)
			vaporView.itemSelected(whichChild);
		return (self) this;

	}

	/**
	 * Manually shows the next child.
	 * 
	 * @return self
	 */
	public self next() {
		for (X vaporView : members)
			vaporView.next();
		return (self) this;

	}

	/**
	 * Manually shows the previous child.
	 * 
	 * @return self
	 */
	public self prev() {
		for (X vaporView : members)
			vaporView.prev();
		return (self) this;

	}

	/**
	 * Removes the given View
	 * 
	 * @param view
	 *            The View to remove
	 * @return self
	 */
	public self remove(VaporView<? extends View, ?> view) {
		remove(view, false);
		return (self) this;
	}

	/**
	 * Removes the given number of Views, starting from the given index
	 * 
	 * @param startIndex
	 *            the index to start removing Views from
	 * @param count
	 *            the number of Views to remove
	 * @return self
	 */
	public self remove(int startIndex, int count) {
		remove(startIndex, count, false);
		return (self) this;
	}

	/**
	 * Call this method to remove all child views from the ViewGroup.
	 * 
	 * @return
	 */
	public self removeAll() {
		for (X vaporView : members)
			vaporView.removeAll();
		return (self) this;

	}

}