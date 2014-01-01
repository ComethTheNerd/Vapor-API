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

import vapor.content.VaporIntent;
import vapor.core.$;
import vapor.view.VaporView;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterViewAnimator;
import android.widget.Advanceable;

/**
 * Fluent Vapor companion to AdapterViewAnimator, the base class for a
 * AdapterView that will perform animations when switching between its views.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from AdapterViewAnimator
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public abstract class VaporAdapterViewAnimator<T extends AdapterViewAnimator, self extends VaporAdapterViewAnimator<T, self>>
		extends VaporAdapterView<Adapter, T, self> implements Advanceable {

	public VaporAdapterViewAnimator(int id) {
		super(id);
	}

	public VaporAdapterViewAnimator(T adapterViewAnimator) {
		super(adapterViewAnimator);
	}

	/**
	 * Returns the adapter currently associated with this widget.
	 * 
	 * @return The adapter used to provide this view's content.
	 */
	public Adapter adapter() {
		return view.getAdapter();
	}

	/**
	 * Called by an AppWidgetHost in order to advance the current view when it
	 * is being used within an app widget.
	 * 
	 * @return self
	 */
	public self adv() { // advance() is defined in the Advanceable interface
		view.advance();
		return (self) this;
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
		view.setAnimateFirstView(animateFirstView);
		return (self) this;
	}

	/**
	 * Returns the current animation used to animate a View that enters the
	 * screen.
	 * 
	 * @return An VaporViewAnimation or null if none is set.
	 */
	public ObjectAnimator animIn() {
		return view.getInAnimation();
	}

	/**
	 * Specifies the animation used to animate a View that enters the screen.
	 * 
	 * @param context
	 *            The application's environment.
	 * @param resourceId
	 *            The resource id of the animation.
	 * @return self
	 */
	public self animIn(Context context, int resourceId) {
		view.setInAnimation(context, resourceId);
		return (self) this;
	}

	/**
	 * Specifies the animation used to animate a View that enters the screen.
	 * 
	 * @param inAnimation
	 *            The animation started when a View enters the screen.
	 * @return self
	 */
	public self animIn(ObjectAnimator inAnimation) {
		view.setInAnimation(inAnimation);
		return (self) this;
	}

	/**
	 * Returns the current animation used to animate a View that exits the
	 * screen.
	 * 
	 * @return An VaporViewAnimation or null if none is set.
	 */
	public ObjectAnimator animOut() {
		return view.getOutAnimation();
	}

	/**
	 * Specifies the animation used to animate a View that exit the screen.
	 * 
	 * @param context
	 *            The application's environment.
	 * @param resourceId
	 *            The resource id of the animation.
	 * @return self
	 */
	public self animOut(Context context, int resourceId) {
		view.setOutAnimation(context, resourceId);
		return (self) this;
	}

	/**
	 * Specifies the animation used to animate a View that exit the screen.
	 * 
	 * @param outAnimation
	 *            The animation started when a View exit the screen.
	 * @return self
	 */
	public self animOut(ObjectAnimator outAnimation) {
		view.setOutAnimation(outAnimation);
		return (self) this;
	}

	/**
	 * Returns the View corresponding to the currently displayed child.
	 * 
	 * @return The View currently displayed.
	 */
	public VaporView<? extends View, ?> current() {
		return $.vapor(view.getCurrentView());
	}

	/**
	 * Sets which child view will be displayed.
	 * 
	 * @param whichChild
	 *            the index of the child view to display
	 * @return self
	 */
	public self current(int whichChild) {
		view.setDisplayedChild(whichChild);
		return (self) this;
	}

	/**
	 * Returns the index of the currently displayed child view.
	 * 
	 * @return index of the currently displayed child view
	 */
	public int currentIndex() {
		return view.getDisplayedChild();
	}

	/**
	 * This defers a notifyDataSetChanged on the pending RemoteViewsAdapter if
	 * it has not connected yet.
	 * 
	 * @return self
	 */
	public self deferNotify() {
		view.deferNotifyDataSetChanged();
		return (self) this;
	}

	/**
	 * Called by an AppWidgetHost to indicate that it will be automatically
	 * advancing the views of this AdapterViewAnimator by calling advance() at
	 * some point in the future.
	 * 
	 * @return self
	 */
	public self fyi() {
		view.fyiWillBeAdvancedByHostKThx();
		return (self) this;
	}

	/**
	 * Manually shows the next child.
	 * 
	 * @return self
	 */
	public self next() {
		view.showNext();
		return (self) this;
	}

	/**
	 * Manually shows the previous child.
	 * 
	 * @return self
	 */
	public self prev() {
		view.showPrevious();
		return (self) this;
	}

	/**
	 * Sets up this AdapterViewAnimator to use a remote views adapter which
	 * connects to a RemoteViewsService through the specified intent.
	 * 
	 * @param intent
	 *            the intent used to identify the RemoteViewsService for the
	 *            adapter to connect to.
	 * @return self
	 */
	public self remote(VaporIntent<? extends Intent, ?> intent) {
		view.setRemoteViewsAdapter(intent.intent());
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onRemoteAdapterConnected(),
	 * called back when the adapter connects to the RemoteViewsService.
	 * 
	 * @return
	 */
	public boolean remoteConn() {
		return view.onRemoteAdapterConnected();
	}

	/**
	 * Fluent equivalent Vapor method for invoking
	 * onRemoteAdapterDisconnected(), called back when the adapter disconnects
	 * from the RemoteViewsService.
	 * 
	 * @return self
	 */
	public self remoteDisconn() {
		view.onRemoteAdapterDisconnected();
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
	 * @return self
	 */
	public self restore(Parcelable state) {
		view.onRestoreInstanceState(state);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onSaveInstanceState(), a hook
	 * allowing a view to generate a representation of its internal state that
	 * can later be used to create a new instance with that same state.
	 * 
	 * @return Returns a Parcelable object containing the view's current dynamic
	 *         state, or null if there is nothing interesting to save. The
	 *         default implementation returns null.
	 */
	public Parcelable save() {
		return view.onSaveInstanceState();
	}

	/**
	 * Sets the currently selected item. To support accessibility subclasses
	 * that override this method must invoke the overriden super method first.
	 * 
	 * @param position
	 *            Index (starting at 0) of the data item to be selected.
	 */
	public self itemSelected(int position) {
		view.setSelection(position);
		return (self) this;
	}

	/* INTERFACE : Advanceable */
	/**
	 * NOTE: This method serves to satisfy the Advanceable interface, use the
	 * equivalent VAPOR FLUENT method adv() instead
	 */
	public void advance() {
		adv();
	}

	/**
	 * NOTE: This method serves to satisfy the Advanceable interface, use the
	 * equivalent VAPOR FLUENT method fyi() instead
	 */
	public void fyiWillBeAdvancedByHostKThx() {
		fyi();
	}

}
