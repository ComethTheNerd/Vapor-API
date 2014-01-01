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

package vapor.support.v4.view;

import vapor.view.VaporView;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fluent Vapor companion to ViewPager, a layout manager that allows the user to
 * flip left and right through pages of data.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from ViewPager
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporViewPager<T extends ViewPager, self extends VaporViewPager<T, self>>
		extends VaporView<T, self> {

	public VaporViewPager(int id) {
		super(id);
	}

	public VaporViewPager(T viewPager) {
		super(viewPager);
	}

	/**
	 * Retrieve the current adapter supplying pages.
	 * 
	 * @return The currently registered PagerAdapter
	 */
	public PagerAdapter adapter() {
		return view.getAdapter();
	}

	/**
	 * Set a PagerAdapter that will supply views for this pager as needed.
	 * 
	 * @param pagerAdapter
	 * @return self
	 */
	public self adapter(PagerAdapter pagerAdapter) {
		view.setAdapter(pagerAdapter);
		return (self) this;
	}

	/**
	 * 
	 * @param direction
	 * @return self
	 */
	public boolean arrowScroll(int direction) {
		return view.arrowScroll(direction);
	}

	/**
	 * Start a fake drag of the pager.
	 * 
	 * A fake drag can be useful if you want to synchronize the motion of the
	 * ViewPager with the touch scrolling of another view, while still letting
	 * the ViewPager control the snapping motion and fling behavior. (e.g.
	 * parallax-scrolling tabs.) Call fakeBy(float) to simulate the actual drag
	 * motion. Call endFake() to complete the fake drag and fling as necessary.
	 * 
	 * During a fake drag the ViewPager will ignore all touch events. If a real
	 * drag is already in progress, this method will return false.
	 * 
	 * @return true if the fake drag began successfully, false if it could not
	 *         be started.
	 */
	public boolean beginFake() {
		return view.beginFakeDrag();
	}

	/**
	 * Set a listener that will be invoked whenever the page changes or is
	 * incrementally scrolled. See
	 * ViewPager.OnPageChangeListener/vapor.listeners
	 * .support.v4.view.viewpager.$change/ vapor.listeners.$$change
	 * 
	 * @param pageChangeListener
	 *            Listener to set
	 * @return self
	 */
	public self change(
			vapor.listeners.support.v4.view.viewpager.$change pageChangeListener) {// ViewPager.OnPageChangeListener
		view.setOnPageChangeListener(pageChangeListener);
		return (self) this;
	}

	/**
	 * Adds a child view with the specified layout parameters.
	 * 
	 * @param child
	 *            the child view to add
	 * @param index
	 *            the position at which to add the child
	 * @param layoutParams
	 *            the layout parameters to set on the child
	 * @return self
	 */
	public self child(VaporView<? extends View, ?> child, int index,
			ViewGroup.LayoutParams layoutParams) {
		view.addView(child.view(), index, layoutParams);
		return (self) this;
	}

	/**
	 * Fake drag by an offset in pixels. You must have called beginFakeDrag()
	 * first/Implicitly calls beginFake() prior for your convenience.
	 * 
	 * @param xOffset
	 *            Offset in pixels to drag by.
	 * @return self
	 */
	public self fakeBy(float xOffset) {

		// if(!faking) beginFake(); <---- IMPLICIT?? this returns a boolean
		// tho...

		view.fakeDragBy(xOffset);

		// endFake(); <--- IMPLICIT??
		return (self) this;
	}

	/**
	 * End a fake drag of the pager.
	 * 
	 * @return self
	 */
	public self endFake() {
		view.endFakeDrag();
		return (self) this;
	}

	/**
	 * Returns true if a fake drag is in progress.
	 * 
	 * @return true if currently in a fake drag, false otherwise.
	 */
	public boolean faking() {
		return view.isFakeDragging();
	}

	/**
	 * 
	 * @return
	 */
	public int item() {
		return view.getCurrentItem();
	}

	/**
	 * Set the currently selected page. If the ViewPager has already been
	 * through its first layout with its current adapter there will be a smooth
	 * animated transition between the current item and the specified item.
	 * 
	 * @param index
	 *            Item index to select
	 * @return self
	 */
	public self item(int index) {
		view.setCurrentItem(index);
		return (self) this;
	}

	/**
	 * Set the currently selected page.
	 * 
	 * @param index
	 *            Item index to select
	 * @param smoothScroll
	 *            True to smoothly scroll to the new item, false to transition
	 *            immediately
	 * @return self
	 */
	public self item(int index, boolean smoothScroll) {
		view.setCurrentItem(index, smoothScroll);
		return (self) this;
	}

	/**
	 * You can call this function yourself to have the scroll view perform
	 * scrolling from a key event, just as if the event had been dispatched to
	 * it by the view hierarchy.
	 * 
	 * @param keyEvent
	 *            The key event to execute.
	 * @return Return true if the event was handled, else false.
	 */
	public boolean key(KeyEvent keyEvent) {
		return view.executeKeyEvent(keyEvent);
	}

	/**
	 * Returns a new set of layout parameters based on the supplied attributes
	 * set.
	 * 
	 * @param attrs
	 *            the attributes to build the layout parameters from
	 * @return an instance of ViewGroup.LayoutParams or one of its descendants
	 */
	public ViewGroup.LayoutParams layoutParams(AttributeSet attrs) {
		return view.generateLayoutParams(attrs);
	}

	/**
	 * Returns the number of pages that will be retained to either side of the
	 * current page in the view hierarchy in an idle state. Defaults to 1.
	 * 
	 * @return How many pages will be kept offscreen on either side
	 */
	public int offScreenLimit() {
		return view.getOffscreenPageLimit();
	}

	/**
	 * Set the number of pages that should be retained to either side of the
	 * current page in the view hierarchy in an idle state. Pages beyond this
	 * limit will be recreated from the adapter when needed.
	 * 
	 * This is offered as an optimization. If you know in advance the number of
	 * pages you will need to support or have lazy-loading mechanisms in place
	 * on your pages, tweaking this setting can have benefits in perceived
	 * smoothness of paging animations and interaction. If you have a small
	 * number of pages (3-4) that you can keep active all at once, less time
	 * will be spent in layout for newly created view subtrees as the user pages
	 * back and forth.
	 * 
	 * You should keep this limit low, especially if your pages have complex
	 * layouts. This setting defaults to 1.
	 * 
	 * @param offScreenPageLimit
	 *            How many pages will be kept offscreen in an idle state.
	 * @return self
	 */
	public self offScreenLimit(int offScreenPageLimit) {
		view.setOffscreenPageLimit(offScreenPageLimit);
		return (self) this;
	}

	/**
	 * Return the margin between pages.
	 * 
	 * @return The size of the margin in pixels
	 */
	public int pageMargin() {
		return view.getPageMargin();
	}

	/**
	 * Set the margin between pages.
	 * 
	 * @param marginPixels
	 *            Distance between adjacent pages in pixels
	 * @return self
	 */
	public self pageMargin(int marginPixels) {
		view.setPageMargin(marginPixels);
		return (self) this;
	}

	/**
	 * Set a drawable that will be used to fill the margin between pages.
	 * 
	 * @param resId
	 *            Resource ID of a drawable to display between pages
	 * @return self
	 */
	public self pageMarginImg(int resId) {
		view.setPageMarginDrawable(resId);
		return (self) this;
	}

	/**
	 * Set a drawable that will be used to fill the margin between pages.
	 * 
	 * @param drawable
	 *            Drawable to display between pages
	 * @return self
	 */
	public self pageMarginImg(Drawable drawable) {
		view.setPageMarginDrawable(drawable);
		return (self) this;
	}

	/**
	 * Equivalent fluent Vapor method for invoking
	 * onRestoreInstanceState(Parcelable), a hook allowing a view to re-apply a
	 * representation of its internal state that had previously been generated by
	 * save().
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
	 * Equivalent fluent Vapor method for invoking onSaveInstanceState(), a hook
	 * allowing a view to generate a representation of its internal state that
	 * can later be used to create a new instance with that same state.
	 * 
	 * @return Returns a Parcelable object containing the view's current dynamic
	 *         state, or null if there is nothing interesting to save.
	 */
	public Parcelable save() {
		return view.onSaveInstanceState();
	}

	/**
	 * Equivalent fluent Vapor method for invoking
	 * onInterceptTouchEvent(MotionEvent), and intercept all touch screen motion
	 * events. This allows you to watch events as they are dispatched to your
	 * children, and take ownership of the current gesture at any point.
	 * 
	 * @param touchEvent
	 *            The motion event being dispatched down the hierarchy.
	 * @return Return true to steal motion events from the children and have
	 *         them dispatched to this ViewGroup through onTouchEvent(). The
	 *         current target will receive an ACTION_CANCEL event, and no
	 *         further messages will be delivered here.
	 */
	public boolean touchIntercept(MotionEvent touchEvent) {
		return view.onInterceptTouchEvent(touchEvent);
	}
}
