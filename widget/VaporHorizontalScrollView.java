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

import android.view.KeyEvent;
import android.widget.HorizontalScrollView;

/**
 * Fluent Vapor companion to HorizontalScrollView, a layout container for a view
 * hierarchy that can be scrolled by the user, allowing it to be larger than the
 * physical display. A HorizontalScrollView is a FrameLayout, meaning you should
 * place one child in it containing the entire contents to scroll; this child
 * may itself be a layout manager with a complex hierarchy of objects. A child
 * that is often used is a LinearLayout in a horizontal orientation, presenting
 * a horizontal array of top-level items that the user can scroll through.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from HorizontalScrollView
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporHorizontalScrollView<T extends HorizontalScrollView, self extends VaporHorizontalScrollView<T, self>>
		extends VaporFrameLayout<T, self> {

	public VaporHorizontalScrollView(int id) {
		super(id);
	}

	public VaporHorizontalScrollView(T horizontalScrollView) {
		super(horizontalScrollView);
	}

	/**
	 * Handle scrolling in response to a left or right arrow click.
	 * 
	 * @param direction
	 *            The direction corresponding to the arrow key that was pressed
	 * @return True if we consumed the event, false otherwise
	 */
	public boolean arrowScroll(int direction) {
		return view.arrowScroll(direction);
	}

	/**
	 * Fling the scroll view
	 * 
	 * @param velocityX
	 *            The initial velocity in the X direction. Positive numbers mean
	 *            that the finger/cursor is moving down the screen, which means
	 *            we want to scroll towards the left
	 * @return self
	 */
	public self fling(int velocityX) {
		view.fling(velocityX);
		return (self) this;
	}

	/**
	 * Indicates whether this HorizontalScrollView's content is stretched to
	 * fill the viewport.
	 * 
	 * @return True if the content fills the viewport, false otherwise.
	 */
	public boolean fillsViewport() {
		return view.isFillViewport();
	}

	/**
	 * Indicates this HorizontalScrollView whether it should stretch its content
	 * width to fill the viewport or not.
	 * 
	 * @param fillsViewport
	 *            True to stretch the content's width to the viewport's
	 *            boundaries, false otherwise.
	 * @return self
	 */
	public self fillsViewport(boolean fillsViewport) {
		view.setFillViewport(fillsViewport);
		return (self) this;
	}

	/**
	 * Handles scrolling in response to a "home/end" shortcut press. This method
	 * will scroll the view to the left or right and give the focus to the
	 * leftmost/rightmost component in the new visible area. If no component is a
	 * good candidate for focus, this scrollview reclaims the focus.
	 * 
	 * @param direction
	 *            the scroll direction: FOCUS_LEFT to go the left of the view or
	 *            FOCUS_RIGHT to go the right
	 * @return true if the key event is consumed by this method, false otherwise
	 */
	public boolean fullScroll(int direction) {
		return view.fullScroll(direction);
	}

	/**
	 * You can call this function yourself to have the scroll view perform
	 * scrolling from a key event, just as if the event had been dispatched to
	 * it by the view hierarchy.
	 * 
	 * @param keyEvent
	 *            The key event to execute.
	 * @return
	 */
	public boolean key(KeyEvent keyEvent) {
		return view.executeKeyEvent(keyEvent);
	}

	/**
	 * 
	 * @return The maximum amount this scroll view will scroll in response to an
	 *         arrow event.
	 */
	public int maxScroll() {
		return view.getMaxScrollAmount();
	}

	/**
	 * Handles scrolling in response to a "page up/down" shortcut press. This
	 * method will scroll the view by one page left or right and give the focus
	 * to the leftmost/rightmost component in the new visible area. If no
	 * component is a good candidate for focus, this scrollview reclaims the
	 * focus.
	 * 
	 * @param direction
	 *            the scroll direction: FOCUS_LEFT to go one page left or
	 *            FOCUS_RIGHT to go one page right
	 * @return true if the key event is consumed by this method, false otherwise
	 */
	public boolean pgScroll(int direction) {
		return view.pageScroll(direction);
	}

	/**
	 * 
	 * @return Whether arrow scrolling will animate its transition.
	 */
	public boolean smooths() {
		return view.isSmoothScrollingEnabled();
	}

	/**
	 * Set whether arrow scrolling will animate its transition.
	 * 
	 * @param smoothScrollingEnabled
	 *            whether arrow scrolling will animate its transition
	 * @return self
	 */
	public self smooths(boolean smoothScrollingEnabled) {
		view.setSmoothScrollingEnabled(smoothScrollingEnabled);
		return (self) this;
	}

	/**
	 * Like scrollBy(int, int), but scroll smoothly instead of immediately.
	 * 
	 * @param dx
	 *            the number of pixels to scroll by on the X axis
	 * @param dy
	 *            the number of pixels to scroll by on the Y axis
	 * @return self
	 */
	public final self smoothBy(int dx, int dy) {
		view.smoothScrollBy(dx, dy);
		return (self) this;
	}

	/**
	 * Like scrollTo(int, int), but scroll smoothly instead of immediately.
	 * 
	 * @param x
	 *            the position where to scroll on the X axis
	 * @param y
	 *            the position where to scroll on the Y axis
	 * @return self
	 */
	public final self smoothTo(int x, int y) {
		view.smoothScrollTo(x, y);
		return (self) this;
	}

}
