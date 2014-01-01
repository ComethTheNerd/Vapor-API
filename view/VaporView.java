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

package vapor.view;

import java.util.ArrayList;

import vapor.animation.Anim;
import vapor.animation.Fade;
import vapor.animation.Shake;
import vapor.animation.Slide;
import vapor.animation.VBounce;
import vapor.animation.VFade;
import vapor.animation.VFlip;
import vapor.animation.VHide;
import vapor.animation.VPulse;
import vapor.animation.VResize;
import vapor.animation.VShow;
import vapor.animation.VSlide;
import vapor.core.$;
import vapor.exception.VaporLicenseException;
import vapor.listeners.$$layout;
import vapor.os.VaporBundle;
import android.app.ActionBar;
import android.content.ClipData;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.SparseArray;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Display;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityEventSource;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AbsListView;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

/**
 * Fluent Vapor companion to View, a class that represents the basic building
 * block for user interface components. A View occupies a rectangular area on
 * the screen and is responsible for drawing and event handling.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from View
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporView<T extends View, self extends VaporView<T, self>>
		implements Drawable.Callback, KeyEvent.Callback,
		AccessibilityEventSource {

	protected final T view;

	public static final int CONTAINER_NO_PARENT = 0, CONTAINER_ABS_LIST = 1,
			CONTAINER_ABSOLUTE = 2, CONTAINER_GALLERY = 3,
			CONTAINER_VIEW_GROUP = 4, CONTAINER_VIEW_PAGER = 5,
			CONTAINER_WINDOW_MANAGER = 6, CONTAINER_ACTION_BAR = 7,
			CONTAINER_FRAME = 8, CONTAINER_GRID = 9, CONTAINER_LINEAR = 10,
			CONTAINER_RELATIVE = 11, CONTAINER_RADIO_GROUP = 12,
			CONTAINER_TABLE = 13, CONTAINER_TABLE_ROW = 14;

	/**
	 * Returns an integer denoting the type of container in which this View is
	 * placed
	 * 
	 * @return an integer denoting the type of container in which this View is
	 *         placed
	 */
	public final int containedBy() {

		// get the parent of this view
		ViewParent parent = this.parent();

		if (parent != null) {
			Class<ViewGroup> cls = (Class<ViewGroup>) parent.getClass();
			if (cls.equals(AbsListView.class))
				return CONTAINER_ABS_LIST;
			else if (cls.equals(FrameLayout.class))
				return CONTAINER_FRAME;
			else if (cls.equals(GridLayout.class))
				return CONTAINER_GRID;
			else if (cls.equals(LinearLayout.class))
				return CONTAINER_LINEAR;
			else if (cls.equals(RelativeLayout.class))
				return CONTAINER_RELATIVE;
			else if (cls.equals(ViewGroup.class))
				return CONTAINER_VIEW_GROUP;
			else if (cls.equals(ViewPager.class))
				return CONTAINER_VIEW_PAGER;
			else if (cls.equals(WindowManager.class))
				return CONTAINER_WINDOW_MANAGER;
			else if (cls.equals(ActionBar.class))
				return CONTAINER_ACTION_BAR;
			else if (cls.equals(RadioGroup.class))
				return CONTAINER_RADIO_GROUP;
			else if (cls.equals(TableLayout.class))
				return CONTAINER_TABLE;
			else if (cls.equals(TableRow.class))
				return CONTAINER_TABLE_ROW;
			else if (cls.equals(AbsoluteLayout.class))
				return CONTAINER_ABSOLUTE;
			else if (cls.equals(Gallery.class))
				return CONTAINER_GALLERY;
		}

		return CONTAINER_NO_PARENT;

	}

	// //////
	/**
	 * Makes the VaporTextView exactly this many pixels tall. You could do the
	 * same thing by specifying this number in the LayoutParams. Note that
	 * setting this value overrides any other (minimum / maximum) number of
	 * lines or height setting.
	 * 
	 * @param pixels
	 * @return self
	 */
	public self height(int pixels) {
		// view.setHeight(pixels);
		size(layoutParams().width, pixels);
		return (self) this;
	}

	/**
	 * Sets the size of this view
	 * 
	 * @param width
	 *            The width of the view
	 * @param height
	 *            The height of the view
	 * @return self
	 */
	public <VG extends ViewGroup, VVG extends VaporViewGroup<VG, ?>> self size(
			int width, int height) {

		ViewGroup.LayoutParams layoutParams;
		switch (this.containedBy()) {
		// HERE WE TAKE CARE OF ALL CONTAINERS WE DON'T HAVE VAPOR CLASSES FOR
		case CONTAINER_GALLERY:
			layoutParams = new Gallery.LayoutParams(width, height);
			break;
		case CONTAINER_VIEW_PAGER:
			Log.w("TextView.size(int,int)",
					"Cannot set size as View has ViewPager as parent");
			return (self) this;
		case CONTAINER_WINDOW_MANAGER:
			layoutParams = new WindowManager.LayoutParams(width, height);
			break;
		case CONTAINER_ACTION_BAR:
			layoutParams = new ActionBar.LayoutParams(width, height);
			break;
		case CONTAINER_NO_PARENT:
			Log.w("TextView.size(int,int)",
					"Cannot set size as View has no parent");
			return (self) this;
			// ------------------------------------------------------------------------
		default:
			// otherwise we can get the layout params from the parent object
			layoutParams = ((VVG) $.vapor((VG) this.parent())).layoutParams(
					width, height);
			break;
		}

		/*
		 * boolean horizontallyCentered = horizCentered(), verticallyCentered =
		 * vertCentered();
		 * 
		 * if(horizontallyCentered && verticallyCentered){
		 * layoutParams(layoutParams); center(); } else{ float currentX =
		 * this.x(), currentY = this.y();
		 * 
		 * layoutParams(layoutParams);
		 * 
		 * if(horizontallyCentered) horizCenter(); else x(currentX);
		 * 
		 * if(verticallyCentered) vertCenter(); else y(currentY); }
		 */
		// BUG FIX: this method would reset the x,y position of a view to 0.
		// We fix that by restoring the current x and y value of the view, after
		// applying
		// the new size
		float currentX = this.x(), currentY = this.y();

		layoutParams(layoutParams);
		x(currentX);
		y(currentY);
		return (self) this;
	}

	/**
	 * Makes the TextView exactly this many pixels wide. You could do the same
	 * thing by specifying this number in the LayoutParams.
	 * 
	 * @param pixels
	 * @return self
	 */
	public self width(int pixels) {
		// view.setWidth(pixels);
		size(pixels, layoutParams().height);

		return (self) this;
	}

	// /////

	private Anim animation;
	
	// This is for the PSEUDO-ANIMATIONS: fadeIn and fadeOut
	private final long FADE_INOUT_DURATION = 1900L;
	
	
	
	public VaporView(int id) {
		if(!$.licensed()){
			throw new VaporLicenseException();
		}else{
			view = load(id);
		}
	}

	public VaporView(T view) {
		if(!$.licensed()){
			throw new VaporLicenseException();
		}else{
			this.view = view;
		}
	}

	// ----------ANIMATION------------

	/**
	 * Performs a bounce animation using the default bounce settings
	 * 
	 * @return self
	 */
	public self bounce() {
		bounce(null);
		return (self) this;
	}

	/**
	 * Performs a bounce animation using the supplied bounce settings
	 * 
	 * @return self
	 */
	public self bounce(VaporBundle options) {
		animation = new VBounce(this, options);
		animation.run();
		return (self) this;
	}

	/**
	 * Performs a fade animation using the default fade settings
	 * 
	 * @return self
	 */
	public self fade() {
		fade(null);
		return (self) this;
	}

	/**
	 * Performs a fade animation using the supplied fade settings
	 * 
	 * @return self
	 */
	public self fade(VaporBundle options) {
		animation = new VFade(this, options);
		animation.run();
		return (self) this;
	}

	/**
	 * Performs a flip animation using the default flip settings
	 * 
	 * @return self
	 */
	public self flip() {
		flip(null);
		return (self) this;
	}

	/**
	 * Performs a flip animation using the supplied flip settings
	 * 
	 * @return self
	 */
	public self flip(VaporBundle options) {
		animation = new VFlip(this, options);
		animation.run();
		return (self) this;
	}

	/**
	 * Performs a fade in animation using the default fade in settings
	 * 
	 * @return self
	 */
	public self fadeIn() {
		fadeIn(null);
		return (self) this;
	}
	
	/**
	 * Performs a fade in animation using the supplied fade in settings
	 * 
	 * @return self
	 */
	public self fadeIn(VaporBundle options) {
		// We use Show instead, because the will set visibility.
		// NOTE: We can't do this on a normal Fade because it might not go completely to 1.0F
		show((options != null ? options : $.Bundle()).put(Fade.DURATION, FADE_INOUT_DURATION));
		return (self) this;
	}

	/**
	 * Performs a fade out animation using the default fade out settings
	 * 
	 * @return self
	 */
	public self fadeOut() {
		fadeOut(null);
		return (self) this;
	}

	/**
	 * Performs a fade out animation using the supplied fade out settings
	 * 
	 * @return self
	 */
	public self fadeOut(VaporBundle options) {
		// We use Hide instead, because the will set visibility.
		// NOTE: We can't do this on a normal Fade because it might not go completely to 0.0F
		hide((options != null ? options : $.Bundle()).put(Fade.DURATION, FADE_INOUT_DURATION));
		return (self) this;
	}

	/**
	 * Performs a hide animation using the supplied hide settings
	 * 
	 * @return self
	 */
	public self hide() {
		hide(null);
		return (self) this;
	}

	/**
	 * Performs a hide animation using the supplied hide settings
	 * 
	 * @return self
	 */
	public self hide(VaporBundle options) {
		animation = new VHide(this, options);
		animation.run();
		return (self) this;
	}

	/**
	 * Performs a pulse animation using the default pulse settings
	 * 
	 * @return self
	 */
	public self pulse() {
		pulse(null);
		return (self) this;
	}

	/**
	 * Performs a pulse animation using the supplied pulse settings
	 * 
	 * @return self
	 */
	public self pulse(VaporBundle options) {
		animation = new VPulse(this, options);
		animation.run();
		return (self) this;
	}

	/**
	 * Performs a resize animation using the default resize settings
	 * 
	 * @return self
	 */
	public self resize() {
		resize(null);
		return (self) this;
	}

	/**
	 * Performs a resize animation using the supplied resize settings
	 * 
	 * @return self
	 */
	public self resize(VaporBundle options) {
		animation = new VResize(this, options);
		animation.run();
		return (self) this;
	}

	/**
	 * Performs a shake animation using the default shake settings
	 * 
	 * @return self
	 */
	public self shake() {
		shake(null);
		return (self) this;
	}

	/**
	 * Performs a shake animation using the supplied shake settings
	 * 
	 * @return self
	 */
	public self shake(VaporBundle options) {
		animation = new Shake(this, options);
		animation.run();
		return (self) this;
	}

	/**
	 * Performs a show animation using the default show settings
	 * 
	 * @return self
	 */
	public self show() {
		show(null);
		return (self) this;
	}

	/**
	 * Performs a show animation using the supplied show settings
	 * 
	 * @return self
	 */
	public self show(VaporBundle options) {
		animation = new VShow(this, options);
		animation.run();
		return (self) this;
	}

	/**
	 * Performs a slide animation using the default slide settings
	 * 
	 * @return self
	 */
	public self slide() {
		slide(null);
		return (self) this;
	}

	/**
	 * Performs a slide animation using the supplied slide settings
	 * 
	 * @return self
	 */
	public self slide(VaporBundle options) {
		animation = new VSlide(this, options);
		animation.run();
		return (self) this;
	}

	/**
	 * Performs a slide animation in the given direction, using the supplied
	 * slide settings
	 * 
	 * @param direction
	 *            The direction in which to slide
	 * @param options
	 *            Custom settings for the animation
	 * @return self
	 */
	protected self slide(String direction, VaporBundle options) {
		slide((options != null ? options : $.Bundle()).put(Slide.DIRECTION,
				direction));
		return (self) this;
	}

	/**
	 * Performs a slide up animation using the default slide up settings
	 * 
	 * @return self
	 */
	public self slideUp() {
		slideUp(null);
		return (self) this;
	}

	/**
	 * Performs a slide up animation using the supplied slide up settings
	 * 
	 * @return self
	 */
	public self slideUp(VaporBundle options) {
		slide(Slide.UP, options);
		return (self) this;
	}

	/**
	 * Performs a slide left animation using the default slide left settings
	 * 
	 * @return self
	 */
	public self slideLeft() {
		slideLeft(null);
		return (self) this;
	}

	/**
	 * Performs a slide left animation using the supplied slide left settings
	 * 
	 * @return self
	 */
	public self slideLeft(VaporBundle options) {
		slide(Slide.LEFT, options);
		return (self) this;
	}

	/**
	 * Performs a slide right animation using the default slide right settings
	 * 
	 * @return self
	 */
	public self slideRight() {
		slideRight(null);
		return (self) this;
	}

	/**
	 * Performs a slide right animation using the supplied slide right settings
	 * 
	 * @return self
	 */
	public self slideRight(VaporBundle options) {
		slide(Slide.RIGHT, options);
		return (self) this;
	}

	/**
	 * Performs a slide down animation using the default slide down settings
	 * 
	 * @return self
	 */
	public self slideDown() {
		slideDown(null);
		return (self) this;
	}

	/**
	 * Performs a slide down animation using the supplied slide down settings
	 * 
	 * @return self
	 */
	public self slideDown(VaporBundle options) {
		slide(Slide.DOWN, options);
		return (self) this;
	}

	// see clearAnim() for what was previous animStop()

	// //////////ANIM

	/**
	 * Look for a child view with the given id. If this view has the given id,
	 * return this view.
	 * 
	 * @param id
	 *            The id to search for.
	 * @return The view that has the given id in the hierarchy or null
	 */
	@SuppressWarnings("unchecked")
	private final T load(int id) {
		return (T) ($.act().findViewById(id));
	}// COPIED IN VaporXView

	@SuppressWarnings("unchecked")
	public static final <U extends View, V extends U> V load(
			Class<U> viewClass, int id) {
		return (V) $.act().findViewById(id);
	}

	/**
	 * Indicates whether this view is attached to a hardware accelerated window
	 * or not.
	 * 
	 * @return True if the view is attached to a window and the window is
	 *         hardware accelerated; false in any other case.
	 */
	public boolean accelerated() {
		return view.isHardwareAccelerated();
	}

	/**
	 * Performs the specified accessibility action on the view. For possible
	 * accessibility actions look at AccessibilityNodeInfo.
	 * 
	 * @param action
	 *            The action to perform.
	 * @param arguments
	 *            Optional action arguments.
	 * @return Whether the action was performed.
	 */
	public boolean access(int action, VaporBundle arguments) {
		return view.performAccessibilityAction(action, arguments.bundle());
	}

	/**
	 * Adds the children of a given View for accessibility. Since some Views are
	 * not important for accessibility the children for accessibility are not
	 * necessarily direct children of the view, rather they are the first level
	 * of descendants important for accessibility.
	 * 
	 * @param children
	 * @return The list of children for accessibility.
	 */
	public self accessChildren(
			ArrayList<VaporView<? extends View, ?>> childrenForAccessibility) {
		view.addChildrenForAccessibility($.android(childrenForAccessibility));
		return (self) this;
	}// Checked: 071220121119

	/**
	 * Start an action mode.
	 * 
	 * @param callback
	 *            Callback that will control the lifecycle of the action mode
	 * @return self
	 */
	public ActionMode action(ActionMode.Callback actionModeCallback) {
		return view.startActionMode(actionModeCallback);
	}

	/**
	 * Indicates the activation state of this view.
	 * 
	 * @return true if the view is activated, false otherwise
	 */
	public boolean activated() {
		return view.isActivated();
	}

	/**
	 * Changes the activated state of this view.
	 * 
	 * @param activated
	 *            true if the view must be activated, false otherwise
	 * @return self
	 */
	public self activated(boolean activated) {
		view.setActivated(activated);
		return (self) this;
	}

	/**
	 * Aligns the bottom edge of a View with the given View
	 * 
	 * @param alignWith
	 *            The VaporView to align bottom with
	 * @return self
	 */
	public self alignBottom(VaporView<? extends View, ?> alignWith) {
		return bottom(alignWith.bottom());
	}

	/**
	 * Aligns the left edge of a VaporView with the given VaporView
	 * 
	 * @param alignWith
	 *            The VaporView to align left with
	 * @return self
	 */
	public self alignLeft(VaporView<? extends View, ?> alignWith) {
		return left(alignWith.left());
	}

	/**
	 * Aligns the right edge of a VaporView with the given VaporView
	 * 
	 * @param alignWith
	 *            The VaporView to align right with
	 * @return self
	 */
	public self alignRight(VaporView<? extends View, ?> alignWith) {
		return right(alignWith.right());
	}

	/**
	 * Aligns the top edge of a VaporView with the given VaporView
	 * 
	 * @param alignWith
	 *            The VaporView to align top with
	 * @return self
	 */
	public self alignTop(VaporView<? extends View, ?> alignWith) {
		return top(alignWith.top());
	}

	/**
	 * The opacity of the view from 0 to 1, where 0 means the view is completely
	 * transparent and 1 means the view is completely opaque
	 * 
	 * @return The opacity of the view.
	 */
	public float alpha() {
		return view.getAlpha();
	}

	/**
	 * Sets the opacity of the view.
	 * 
	 * @param alpha
	 *            The opacity of the view.
	 * @return self
	 */
	public self alpha(float alpha) {
		view.setAlpha(alpha);
		return (self) this;
	}

	/**
	 * This method returns a ViewPropertyAnimator object, which can be used to
	 * animate specific properties on this View.
	 * 
	 * @return ViewPropertyAnimator The ViewPropertyAnimator associated with
	 *         this View.
	 */
	public ViewPropertyAnimator animate() {
		return view.animate();
	} // Checked: 071220121144

	/**
	 * Get the animation currently associated with this view.
	 * 
	 * @return The animation that is currently playing or scheduled to play for
	 *         this view.
	 */
	public Animation anim() {
		return view.getAnimation();
	}

	/**
	 * Sets the next animation to play for this view.
	 * 
	 * @param animation
	 *            The next animation, or null.
	 * @return self
	 */
	public self anim(Animation animation) {
		view.setAnimation(animation);
		return (self) this;
	}

	/**
	 * Convenience method for sending a TYPE_ANNOUNCEMENT AccessibilityEvent to
	 * make an announcement which is related to some sort of a context change
	 * for which none of the events representing UI transitions is a good fit.
	 * 
	 * @param text
	 *            The announcement text.
	 * @return self
	 */
	public self accessAnnounce(CharSequence text) {
		view.announceForAccessibility(text);
		return (self) this;
	}// Checked: 071220121146

	/**
	 * Return the offset of the widget's text baseline from the widget's top
	 * boundary.
	 * 
	 * @return the offset of the baseline within the widget's bounds or -1 if
	 *         baseline alignment is not supported
	 */
	public int baseline() {
		return view.getBaseline();
	}

	/**
	 * Gets the background drawable.
	 * 
	 * @return The drawable used as the background for this view, if any.
	 */
	public Drawable bg() {
		return view.getBackground();
	}

	/**
	 * Set the background to a given Drawable, or remove the background.
	 * 
	 * @param background
	 *            The Drawable to use as the background, or null to remove the
	 *            background
	 * @return self
	 */
	public self bg(Drawable background) {
		view.setBackground(background);
		return (self) this;
	}

	/**
	 * Set the background to a given resource.
	 * 
	 * @param resId
	 *            The identifier of the resource.
	 * @return self
	 */
	public self bg(int resId) {
		view.setBackgroundResource(resId);
		return (self) this;
	}

	/**
	 * Sets the background color for this view.
	 * 
	 * @param color
	 *            the color of the background
	 * @return self
	 */
	public self bgColor(int color) {
		view.setBackgroundColor(color);
		return (self) this;
	}

	/**
	 * Bottom position of this view relative to its parent.
	 * 
	 * @return The bottom of this view, in pixels.
	 */
	public final int bottom() {
		return view.getBottom();
	}

	/**
	 * Sets the bottom position of this view relative to its parent.
	 * 
	 * @param bottom
	 *            The bottom of this view, in pixels.
	 * @return self
	 */
	public self bottom(int bottom) {
		view.setBottom(bottom);
		return (self) this;
	}

	/**
	 * Calling this method is equivalent to calling buildDrawCache(false).
	 * 
	 * @return self
	 */
	public self buildDrawCache() {
		view.buildDrawingCache();
		return (self) this;
	}// Checked: 071220121152

	/**
	 * Forces the drawing cache to be built if the drawing cache is invalid.
	 * 
	 * @param autoScale
	 * @return self
	 */
	public self buildDrawCache(boolean autoScale) {
		view.buildDrawingCache(autoScale);
		return (self) this;
	}// Checked: 071220121155

	/**
	 * Forces this view's layer to be created and this view to be rendered into
	 * its layer.
	 * 
	 * @throws IllegalStateException
	 *             If this view is not attached to a window
	 * @return self
	 */
	public self buildLayer() {
		view.buildLayer();
		return (self) this;
	}// Checked: 071220121157

	/**
	 * Cancels a pending long press.
	 * 
	 * @return self
	 */
	public self cancelLongPress() {
		view.cancelLongPress();
		return (self) this;
	}// Checked: 071220121206

	/**
	 * Check if this view can be scrolled horizontally in a certain direction.
	 * 
	 * @param direction
	 *            Negative to check scrolling left, positive to check scrolling
	 *            right.
	 * @return true if this view can be scrolled in the specified direction,
	 *         false otherwise.
	 */
	public boolean xScrollable(int direction) {
		return view.canScrollHorizontally(direction);
	}// Checked: 071220121204

	/**
	 * Check if this view can be scrolled vertically in a certain direction.
	 * 
	 * @param direction
	 *            Negative to check scrolling up, positive to check scrolling
	 *            down.
	 * @return true if this view can be scrolled in the specified direction,
	 *         false otherwise.
	 */
	public boolean yScrollable(int direction) {
		return view.canScrollVertically(direction);
	} // Checked: 071220121206

	/**
	 * Directly call any attached OnClickListener. Unlike click(), this only
	 * calls the listener, and does not do any associated clicking actions like
	 * reporting an accessibility event.
	 * 
	 * @return True there was an assigned OnClickListener that was called, false
	 *         otherwise is returned.
	 */
	public boolean click() {
		return view.callOnClick();
	} // Checked: 071220121200

	/**
	 * Call this view's $click listener, if it is defined. Performs all normal
	 * actions associated with clicking: reporting accessibility event, playing
	 * a sound, etc.
	 * 
	 * @return True there was an assigned $click listener that was called, false
	 *         otherwise is returned.
	 */
	public boolean clickExt() {
		return view.performClick();
	}

	/**
	 * Enables or disables click events for this view.
	 * 
	 * @param clickable
	 *            true to make the view clickable, false otherwise
	 * @return self
	 */
	public self clickable(boolean clickable) {
		view.setClickable(clickable);
		return (self) this;
	}

	/**
	 * Register a callback to be invoked when this view is clicked.
	 * 
	 * @param clickListener
	 *            The callback that will run
	 * @return self
	 */
	public self click(vapor.listeners.view.$click clickListener) {
		view.setOnClickListener(clickListener);
		return (self) this;
	}

	/**
	 * Center this view both horizontally and vertically in its parent view
	 * 
	 * @return self
	 */
	public self center() {
		__center(true, true);
		return (self) this;
	}

	/**
	 * Center this view vertically in its parent view
	 * 
	 * @return self
	 */
	public self centerY() {
		__center(false, true);
		return (self) this;
	}

	/**
	 * Center this view horizontally in its parent view
	 * 
	 * @return self
	 */
	public self centerX() {
		__center(true, false);
		return (self) this;
	}

	// Helper
	private final void __center(final boolean centerX, final boolean centerY) {
		View p = (View) parent();

		if (p == null) {
			Log.w("center()", "Cannot center View with no parent");
			return;
		}

		int pH = p.getHeight();
		int pW = p.getWidth();

		// if these are both 0 we suspect the layout has not completed yet
		if (pH == 0 && pW == 0) {
			// in this case we register a callback for when the layout has
			// completed

			final VaporView<T, self> self = this;
			ViewTreeObserver vto = self.treeObserver();

			// NOTE: DO NOT USE hook.VIEW_READY for this as it won't be called
			// if
			// client is using standard Android Activities
			vto.addOnGlobalLayoutListener(new $$layout() {

				@Override
				public void onGlobalLayout() {
					View p = (View) parent();
					if (centerY)
						y((p.getHeight() / 2) - (height() / 2));
					if (centerX)
						x((p.getWidth() / 2) - (width() / 2));

					self.treeObserver().removeOnGlobalLayoutListener(this);
				}
			});
		} else {
			if (centerY)
				y((pH / 2) - (this.height() / 2));
			if (centerX)
				x((pW / 2) - (this.width() / 2));
		}

	}

	/**
	 * Cancels any animations for this view.
	 * 
	 * @return self
	 */
	public self cancelAnim() {
		view.clearAnimation();

		// Vapor Anim stop
		if (animation != null)
			animation.stop();
		// ///////////////////

		return (self) this;
	}// Checked: 071220121214

	/**
	 * Called when this view wants to give up focus.
	 * 
	 * @return self
	 */
	public self cancelFocus() {
		view.clearFocus();
		return (self) this;
	}// Checked: 071220121216

	/**
	 * Indicates whether this view reacts to click events or not.
	 * 
	 * @return true if the view is clickable, false otherwise
	 */
	public boolean clickable() {
		return view.isClickable();
	}

	/**
	 * Return whether this view has an attached OnClickListener.
	 * 
	 * @return true if there is a listener, false if there is none.
	 */
	public boolean clickListening() {
		return view.hasOnClickListeners();
	}

	/**
	 * Merge two states as returned by measuredState().
	 * 
	 * @param currentState
	 *            The current state as returned from a view or the result of
	 *            combining multiple views.
	 * @param newState
	 *            The new view state to combine.
	 * @return Returns a new integer reflecting the combination of the two
	 *         states.
	 */
	public static int combine(int currentState, int newState) {
		return View.combineMeasuredStates(currentState, newState);
	}// Checked: 071220121220

	/**
	 * Fluent equivalent Vapor method for invoking
	 * onCreateInputConnection(EditorInfo), that creates a new InputConnection
	 * for an InputMethod to interact with the view.
	 * 
	 * @param outAttrs
	 *            Fill in with attribute information about the connection.
	 * @return self
	 */
	public InputConnection conn(EditorInfo outAttrs) {
		return view.onCreateInputConnection(outAttrs);
	}

	/**
	 * Returns the context the view is running in, through which it can access
	 * the current theme, resources, etc.
	 * 
	 * @return The view's Context.
	 */
	public Context context() {
		return view.getContext();
	}

	/**
	 * Indicates whether this duplicates its drawable state from its parent.
	 * 
	 * @return True if this view's drawable state is duplicated from the parent,
	 *         false otherwise
	 */
	public boolean copyParent() {
		return view.isDuplicateParentStateEnabled();
	}

	/**
	 * Enables or disables the duplication of the parent's state into this view.
	 * 
	 * @param enabled
	 *            True to enable duplication of the parent's drawable state,
	 *            false to disable it.
	 * @return self
	 */
	public self copyParent(boolean enabled) {
		view.setDuplicateParentStateEnabled(enabled);
		return (self) this;
	}

	/**
	 * Utility to return a default size.
	 * 
	 * @param size
	 *            Default size for this view
	 * @param measureSpec
	 *            Constraints imposed by the parent
	 * @return The size this view should be.
	 */
	public static int defSize(int size, int measureSpec) {
		return View.getDefaultSize(size, measureSpec);
	}

	/**
	 * Gets the View description.
	 * 
	 * @return The content description.
	 */
	public CharSequence describe() {
		return view.getContentDescription();
	}

	/**
	 * Sets the description.
	 * 
	 * @param contentDescription
	 *            The content description.
	 * @return self
	 */
	public self describe(CharSequence contentDescription) {
		view.setContentDescription(contentDescription);
		return (self) this;
	}

	/**
	 * True if this view has changed since the last time being drawn.
	 * 
	 * @return The dirty state of this view.
	 */
	public boolean dirty() {
		return view.isDirty();
	}

	/**
	 * Dispatch a notification about a resource configuration change down the
	 * view hierarchy.
	 * 
	 * @param newConfig
	 * @return self
	 */
	public self dispatchConfig(Configuration newConfig) {
		view.dispatchConfigurationChanged(newConfig);
		return (self) this;
	}// Checked: 071220121230

	/**
	 * Detects if this View is enabled and has a drag event listener. If both
	 * are true, then it calls the drag event listener with the DragEvent it
	 * received. If the drag event listener returns true, then dispatchDrag()
	 * returns true.
	 * 
	 * @param dragEvent
	 *            the drag event
	 * @return If the drag event listener returns true, then dispatchDrag()
	 *         returns true.
	 */
	public boolean dispatchDrag(DragEvent dragEvent) {
		return view.dispatchDragEvent(dragEvent);
	}// Checked: 081220121005

	/**
	 * Dispatch a hint about whether this view is displayed.
	 * 
	 * @param displayHint
	 *            A hint about whether or not this view is displayed: VISIBLE or
	 *            INVISIBLE.
	 * @return self True if the event was handled by the view, false otherwise.
	 */
	public self dispatchHint(int displayHint) {
		view.dispatchDisplayHint(displayHint);
		return (self) this;
	}// Checked: 071220121232

	/**
	 * Dispatches a key shortcut event.
	 * 
	 * @param keyEvent
	 *            The key event to be dispatched.
	 * @return
	 */
	public boolean dispatchHotKey(KeyEvent keyEvent) {
		return view.dispatchKeyShortcutEvent(keyEvent);
	}// Checked: 081220121011

	/**
	 * Dispatch a generic motion event.
	 * 
	 * @param genericMotionEvent
	 *            The motion event to be dispatched.
	 * @return True if the event was handled by the view, false otherwise.
	 */
	public boolean dispatchMotion(MotionEvent genericMotionEvent) {
		return view.dispatchGenericMotionEvent(genericMotionEvent);
	}// Checked: 081220121005

	/**
	 * Dispatches an AccessibilityEvent to the View first and then to its
	 * children for adding their text content to the event.
	 * 
	 * @param accessibilityEvent
	 *            true populates to its children for adding their text content
	 *            to the event.
	 * @return True if the event population was completed.
	 */
	public boolean dispatchPopAccess(AccessibilityEvent accessibilityEvent) {
		return view.dispatchPopulateAccessibilityEvent(accessibilityEvent);
	} // Checked: 081220121012

	/**
	 * This method is the last chance for the focused view and its ancestors to
	 * respond to an arrow key.
	 * 
	 * @param focused
	 *            The currently focused view.
	 * @param direction
	 *            The direction focus wants to move. One of FOCUS_UP,
	 *            FOCUS_DOWN, FOCUS_LEFT, and FOCUS_RIGHT.
	 * @return True if the this view consumed this unhandled move.
	 */
	public boolean dispatchMove(VaporView<? extends View, ?> focused,
			int direction) {
		return view.dispatchUnhandledMove(focused.view(), direction);
	}// Checked:081220121015

	/**
	 * Called when the window containing this view gains or loses window focus.
	 * 
	 * @param windowHasFocus
	 *            True if the window containing this view now has focus, false
	 *            otherwise.
	 * @return self
	 */
	public self dispatchFocus(boolean windowHasFocus) {
		view.dispatchWindowFocusChanged(windowHasFocus);
		return (self) this;
	}// Checked: 081220121015

	/**
	 * Dispatch a key event to the next view on the focus path. This path runs
	 * from the top of the view tree down to the currently focused view. If this
	 * view has focus, it will dispatch to itself. Otherwise it will dispatch
	 * the next node down the focus path. This method also fires any key
	 * listeners.
	 * 
	 * @param keyEvent
	 *            The key event to be dispatched.
	 * @return True if the event was handled, false otherwise.
	 */
	public boolean dispatchKey(KeyEvent keyEvent) {
		return dispatchKey(keyEvent, false);
	}// Checked: 081220121009

	/**
	 * Dispatch a key event to the next view on the focus path, optionally
	 * before it is processed by any input method associated with the view
	 * hierarchy.
	 * 
	 * @param keyEvent
	 * @param preInputProcessed
	 *            true if it is processed by any input method associated with
	 *            the view hierarchy
	 * @return True if the event was handled, false otherwise.
	 */
	public boolean dispatchKey(KeyEvent keyEvent, boolean preInputProcessed) {
		if (preInputProcessed)
			return view.dispatchKeyEventPreIme(keyEvent);
		else
			return view.dispatchKeyEvent(keyEvent);
	} // Checked: 081220121010

	/**
	 * Pass the touch screen motion event down to the target view, or this view
	 * if it is the target.
	 * 
	 * @param touchEvent
	 *            The motion event to be dispatched.
	 * @return True if the event was handled by the view, false otherwise.
	 */
	public boolean dispatchTouch(MotionEvent touchEvent) {
		return view.dispatchTouchEvent(touchEvent);
	} // Checked:081220121013

	/**
	 * Pass a trackball motion event down to the focused view.
	 * 
	 * @param trackballEvent
	 *            The motion event to be dispatched.
	 * @return True if the event was handled by the view, false otherwise.
	 */
	public boolean dispatchTrackball(MotionEvent trackballEvent) {
		return view.dispatchTrackballEvent(trackballEvent);
	} // Checked:081220121013

	/**
	 * Dispatch callbacks to sysViz($change systemUiVisibilityChangeListener)
	 * down the view hierarchy, or optionally dispatch callbacks to
	 * winSysVizChanged(int) down the view hierarchy..
	 * 
	 * @param uiVisibility
	 * @param windowVisibility
	 * @return self
	 */
	public self dispatchSysViz(int uiVisibility, boolean windowVisibility) {
		if (windowVisibility)
			view.dispatchSystemUiVisibilityChanged(uiVisibility);
		else
			view.dispatchWindowSystemUiVisiblityChanged(uiVisibility);
		return (self) this;
	}// Checked:081220121012

	/**
	 * Dispatch a window visibility change down the view hierarchy.
	 * 
	 * @param visibility
	 *            The new visibility of the window.
	 * @return self
	 */
	public self dispatchViz(int visibility) {
		view.dispatchWindowVisibilityChanged(visibility);
		return (self) this;
	}// Checked:081220121019

	/**
	 * Gets the logical display to which the view's window has been attached.
	 * 
	 * @return The logical display, or null if the view is not currently
	 *         attached to a window.
	 */
	public Display display() { // API:17
		return view.getDisplay();
	}

	/**
	 * Starts a drag and drop operation. When your application calls this
	 * method, it passes a View.DragShadowBuilder object to the system. The
	 * system calls this object's onProvideShadowMetrics(Point, Point) to get
	 * metrics for the drag shadow, and then calls the object's
	 * onDrawShadow(Canvas) to draw the drag shadow itself.
	 * 
	 * @param clipData
	 *            A ClipData object pointing to the data to be transferred by
	 *            the drag and drop operation.
	 * @param shadowBuilder
	 *            A View.DragShadowBuilder object for building the drag shadow.
	 * @param myLocalState
	 *            An Object containing local data about the drag and drop
	 *            operation. This Object is put into every DragEvent object sent
	 *            by the system during the current drag.
	 * @param flags
	 *            Flags that control the drag and drop operation. No flags are
	 *            currently defined, so the parameter should be set to 0.
	 * @return true if the method completes successfully, or false if it fails
	 *         anywhere. Returning false means the system was unable to do a
	 *         drag, and so no drag operation is in progress.
	 */
	public final boolean drag(ClipData clipData,
			View.DragShadowBuilder shadowBuilder, Object myLocalState, int flags) {
		return view.startDrag(clipData, shadowBuilder, myLocalState, flags);
	}

	/**
	 * Register a drag event listener callback object for this View.
	 * 
	 * @param dragListener
	 *            An implementation of View.OnDragListener.
	 * @return self
	 */
	public self drag(vapor.listeners.view.$drag dragListener) {
		view.setOnDragListener(dragListener);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onDragEvent(DragEvent), that
	 * handles drag events sent by the system following a call to startDrag().
	 * 
	 * @param dragEvent
	 *            The DragEvent sent by the system.
	 * @return true if the method was successful, otherwise false.
	 */
	public boolean drag(DragEvent dragEvent) {
		return view.onDragEvent(dragEvent);
	}

	/**
	 * Manually render this view (and all of its children) to the given Canvas.
	 * 
	 * @param canvas
	 *            The Canvas to which the View is rendered.
	 * @return self
	 */
	public self draw(Canvas canvas) {
		view.draw(canvas);
		return (self) this;
	}// Checked:081220121020

	/**
	 * Calling this method is equivalent to calling drawCache(false).
	 * 
	 * @return A non-scaled bitmap representing this view or null if cache is
	 *         disabled.
	 */
	public Bitmap drawCache() {
		return view.getDrawingCache();
	}

	/**
	 * Returns the bitmap in which this view drawing is cached.
	 * 
	 * @param autoScale
	 *            Indicates whether the generated bitmap should be scaled based
	 *            on the current density of the screen when the application is
	 *            in compatibility mode.
	 * @return A bitmap representing this view or null if cache is disabled.
	 */
	public Bitmap drawCache(boolean autoScale) {
		return view.getDrawingCache(autoScale);
	}

	/**
	 * Indicates whether the drawing cache is enabled for this view.
	 * 
	 * @return true if the drawing cache is enabled
	 */
	public boolean drawCached() {
		return view.isDrawingCacheEnabled();
	}

	/**
	 * Enables or disables the drawing cache.
	 * 
	 * @param enabled
	 *            true to enable the drawing cache, false otherwise
	 * @return self
	 */
	public self drawCached(boolean enabled) {
		view.setDrawingCacheEnabled(enabled);
		return (self) this;
	}

	/**
	 * The background color to used for the drawing cache's bitmap
	 * 
	 * @return The background color to used for the drawing cache's bitmap
	 */
	public int drawCacheBgColor() {
		return view.getDrawingCacheBackgroundColor();
	}

	/**
	 * Setting a solid background color for the drawing cache's bitmaps will
	 * improve performance and memory usage.
	 * 
	 * @param color
	 *            The background color to use for the drawing cache's bitmap
	 * @return self
	 */
	public self drawCacheBgColor(int color) {
		view.setDrawingCacheBackgroundColor(color);
		return (self) this;
	}

	/**
	 * Frees the resources used by the drawing cache.
	 * 
	 * @return self
	 */
	public self destroyDrawCache() {
		view.destroyDrawingCache();
		return (self) this;
	}// Checked: 071220121227

	/**
	 * Returns the quality of the drawing cache.
	 * 
	 * @return One of DRAWING_CACHE_QUALITY_AUTO, DRAWING_CACHE_QUALITY_LOW, or
	 *         DRAWING_CACHE_QUALITY_HIGH
	 */
	public int drawCacheQual() {
		return view.getDrawingCacheQuality();
	}

	/**
	 * Set the drawing cache quality of this view.
	 * 
	 * @param quality
	 *            One of DRAWING_CACHE_QUALITY_AUTO, DRAWING_CACHE_QUALITY_LOW,
	 *            or DRAWING_CACHE_QUALITY_HIGH
	 * @return self
	 */
	public self drawCacheQual(int quality) {
		view.setDrawingCacheQuality(quality);
		return (self) this;
	}

	/**
	 * Returns the bitmap in which this view drawing is cached.
	 * 
	 * @param autoScale
	 * @return
	 */
	public Bitmap drawCacheScaled(boolean autoScale) {
		return view.getDrawingCache(autoScale);
	}

	/**
	 * Return the visible drawing bounds of your view.
	 * 
	 * @param outRect
	 *            The (scrolled) drawing bounds of the view.
	 * @return self
	 */
	public self drawRect(Rect outRect) {
		view.getDrawingRect(outRect);
		return (self) this;
	}

	/**
	 * Return an array of resource IDs of the drawable states representing the
	 * current state of the view.
	 * 
	 * @return The current drawable state
	 */
	public final int[] drawState() {
		return view.getDrawableState();
	}

	/**
	 * Return the time at which the drawing of the view hierarchy started.
	 * 
	 * @return the drawing start time in milliseconds
	 */
	public long drawTime() {
		return view.getDrawingTime();
	}

	/**
	 * Indicates whether this View is currently in edit mode.
	 * 
	 * @return True if this View is in edit mode, false otherwise.
	 */
	public boolean editing() {
		return view.isInEditMode();
	}

	/**
	 * Returns the enabled status for this view.
	 * 
	 * @return True if this view is enabled, false otherwise.
	 */
	public boolean enabled() {
		return view.isEnabled();
	}

	/**
	 * Set the enabled state of this view.
	 * 
	 * @param enabled
	 *            True if this view is enabled, false otherwise.
	 * @return self
	 */
	public self enabled(boolean enabled) {
		view.setEnabled(enabled);
		return (self) this;
	}

	/**
	 * Set the size of the faded edge used to indicate that more content in this
	 * view is available.
	 * 
	 * @param length
	 *            The size in pixels of the faded edge used to indicate that
	 *            more content in this view is visible.
	 * @return self
	 */
	public self fadeEdge(int length) {
		view.setFadingEdgeLength(length);
		return (self) this;
	}

	/**
	 * Look for a child view with the given id. If this view has the given id,
	 * return this view.
	 * 
	 * @param id
	 *            The id to search for.
	 * @return The view that has the given id in the hierarchy or null
	 */
	public final VaporView<? extends View, ?> find(int id) {
		return $.vapor(view.findViewById(id));
	}

	/**
	 * Look for a child view with the given tag. If this view has the given tag,
	 * return this view.
	 * 
	 * @param tag
	 *            The tag to search for, using "tag.equals(getTag())".
	 * @return The View that has the given tag in the hierarchy or null
	 */
	public final VaporView<? extends View, ?> find(Object tag) {
		return $.vapor(view.findViewWithTag(tag));
	}

	/**
	 * Finds the Views that contain given text.
	 * 
	 * @param outViews
	 *            The output list of matching Views.
	 * @param searched
	 *            The text to match against.
	 * @param flags
	 * @return self
	 */
	public self find(ArrayList<VaporView<? extends View, ?>> outViews,
			CharSequence searched, int flags) {
		view.findViewsWithText($.android(outViews), searched, flags);
		return (self) this;
	}

	/**
	 * Find the view in the hierarchy rooted at this view that currently has
	 * focus.
	 * 
	 * @return The view that currently has focus, or null if no focused view can
	 *         be found.
	 */
	public VaporView<? extends View, ?> findFocus() {
		return $.vapor(view.findFocus());
	}// Checked: 081220121020

	/**
	 * Fluent equivalent Vapor method for invoking onFinishTemporaryDetach(),
	 * called after onStartTemporaryDetach() when the container is done changing
	 * the view.
	 * 
	 * @return
	 */
	public self finishTempDetach() {
		view.onFinishTemporaryDetach();
		return (self) this;
	}

	/**
	 * Check for state of 'If this method returns true, the default
	 * implementation of fits(boolean) will be executed'.
	 * 
	 * @return Returns true if the default implementation of fits(boolean) will
	 *         be executed.
	 */
	public boolean fits() {
		return view.getFitsSystemWindows();
	}

	/**
	 * Sets whether or not this view should account for system screen
	 * decorations such as the status bar and inset its content; that is,
	 * controlling whether the default implementation of
	 * fitSystemWindows(boolean) will be executed.
	 * 
	 * @param fitSystemWindows
	 *            If true, then the default implementation of
	 *            fitSystemWindows(Rect) will be executed.
	 * @return self
	 */
	public self fits(boolean fitSystemWindows) {
		view.setFitsSystemWindows(fitSystemWindows);
		return (self) this;
	}

	/**
	 * Find the nearest view in the specified direction that can take focus.
	 * This does not actually give focus to that view.
	 * 
	 * @param direction
	 *            One of FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, and FOCUS_RIGHT
	 * @return The nearest focusable in the specified direction, or null if none
	 *         can be found.
	 */
	public VaporView<? extends View, ?> focusSearch(int direction) {
		return $.vapor(view.focusSearch(direction));
	}// Checked: 081220121024

	/**
	 * Register a callback to be invoked when focus of this view changed.
	 * 
	 * @param focusChangeListener
	 *            Register a callback to be invoked when focus of this view
	 *            changed.
	 * @return self
	 */
	public self focus(vapor.listeners.view.$focus focusChangeListener) {
		view.setOnFocusChangeListener(focusChangeListener);
		return (self) this;
	}

	/**
	 * Returns true if this view has focus itself
	 * 
	 * @return True if this view has focus, false otherwise.
	 */
	public boolean focused() {
		return view.isFocused();
	}

	/**
	 * Returns true if this view has focus iteself, or is the ancestor of the
	 * view that has focus.
	 * 
	 * @return True if this view has or contains focus, false otherwise.
	 */
	public boolean focusedExt() {
		return view.hasFocus();
	}

	/**
	 * Returns the focus-change callback registered for this view.
	 * 
	 * @return The callback, or null if one is not registered.
	 */
	public OnFocusChangeListener focusListener() {
		return view.getOnFocusChangeListener();
	}

	/**
	 * When a view has focus and the user navigates away from it, the next view
	 * is searched for starting from the rectangle filled in by this method.
	 * 
	 * @param rect
	 *            The rectangle to fill in, in this view's coordinates.
	 * @return self
	 */
	public self focusedRect(Rect rect) {
		view.getFocusedRect(rect);
		return (self) this;
	}

	/**
	 * Returns true if this view is focusable or if it contains a reachable View
	 * for which focusableExt() returns true.
	 * 
	 * @param includeChildren
	 *            true if should check the view's children for a focusable too
	 * @return True if the view is focusable or if the view contains a focusable
	 *         View, false otherwise.
	 */
	public boolean focusable() {
		return view.isFocusable();
	}

	/**
	 * Set whether this view can receive the focus.
	 * 
	 * @param focusable
	 * @return
	 */
	public self focusable(boolean focusable) {
		view.setFocusable(focusable);
		return (self) this;
	}

	/**
	 * Find and return all focusable views that are descendants of this view,
	 * possibly including this view if it is focusable itself.
	 * 
	 * @param direction
	 *            The direction of the focus
	 * @return A list of focusable views
	 */
	public ArrayList<VaporView<? extends View, ?>> focusables(int direction) {
		return $.vapor(view.getFocusables(direction));
	}

	/**
	 * Adds any focusable views that are descendants of this view (possibly
	 * including this view if it is focusable itself) to views.
	 * 
	 * @param views
	 *            Focusable views found so far or null if all we are interested
	 *            is the number of focusables.
	 * @param direction
	 *            The direction of the focus.
	 * @return self
	 */
	public self focusables(ArrayList<VaporView<? extends View, ?>> views,
			int direction) {
		view.addFocusables($.android(views), direction);
		return (self) this;
	}// Checked: 071220121119

	/**
	 * Adds any focusable views that are descendants of this view (possibly
	 * including this view if it is focusable itself) to views.
	 * 
	 * @param views
	 *            Focusable views found so far or null if all we are interested
	 *            is the number of focusables.
	 * @param direction
	 *            The direction of the focus.
	 * @param focusableMode
	 *            The type of focusables to be added.
	 * @return self
	 */
	public self focusables(ArrayList<VaporView<? extends View, ?>> views,
			int direction, int focusableMode) {
		view.addFocusables($.android(views), direction, focusableMode);
		return (self) this;
	} // Checked: 071220121125

	/**
	 * Returns true if this view is focusable or if it contains a reachable View
	 * for which focusableExt() returns true. A "reachable focusableExt()" is a
	 * View whose parents do not block descendants focus. Only VISIBLE views are
	 * considered focusable.
	 * 
	 * @return True if the view is focusable or if the view contains a focusable
	 *         View, false otherwise.
	 */
	public boolean focusableExt() {
		return view.hasFocusable();
	}

	/**
	 * Change the view's z order in the tree, so it's on top of other sibling
	 * views.
	 * 
	 * @return self
	 */
	public self front() {
		view.bringToFront();
		return (self) this;
	} // Checked: 071220121147

	/**
	 * Generate a value suitable for use in id(int). This value will not collide
	 * with ID values generated at build time by aapt for R.id.
	 * 
	 * @return a generated ID value
	 */
	public static int genViewId() { // API:17
		return View.generateViewId();
	} // Checked: 081220121025

	/**
	 * 
	 * @return A handler associated with the thread running the View. This
	 *         handler can be used to pump events in the UI events queue.
	 */
	public Handler handler() {
		return view.getHandler();
	}

	/**
	 * Whether this view should have haptic feedback enabled for events long
	 * presses.
	 * 
	 * @return true if this view has haptic feedback enabled for events long
	 *         presses.
	 */
	public boolean haptic() {
		return view.isHapticFeedbackEnabled();
	}

	/**
	 * Set whether this view should have haptic feedback for events such as long
	 * presses.
	 * 
	 * @param hapticFeedbackEnabled
	 * @return
	 */
	public self haptic(boolean hapticFeedbackEnabled) {
		view.setHapticFeedbackEnabled(hapticFeedbackEnabled);
		return (self) this;
	}

	/**
	 * Provide haptic feedback to the user for this view.
	 * 
	 * @param feedbackConstant
	 *            One of the constants defined in HapticFeedbackConstants
	 * @return
	 */
	public boolean haptic(int feedbackConstant) {
		return view.performHapticFeedback(feedbackConstant);
	}

	/**
	 * Like performHapticFeedback(int), with additional options.
	 * 
	 * @param feedbackConstant
	 * @param flags
	 * @return
	 */
	public boolean haptic(int feedbackConstant, int flags) {
		return view.performHapticFeedback(feedbackConstant, flags);
	}

	/**
	 * Return the height of your view.
	 * 
	 * @return The height of your view, in pixels.
	 */
	public int height() {
		return view.getHeight();
	}

	/**
	 * Hit rectangle in parent's coordinates
	 * 
	 * @param outRect
	 *            The hit rectangle of the view.
	 * @return self
	 */
	public self hitRect(Rect outRect) {
		view.getHitRect(outRect);
		return (self) this;
	}

	/**
	 * Indicate whether the horizontal edges are faded when the view is scrolled
	 * horizontally.
	 * 
	 * @return true if the horizontal edges should are faded on scroll, false
	 *         otherwise
	 */
	public boolean fadesXEdges() {
		return view.isHorizontalFadingEdgeEnabled();
	}

	/**
	 * Define whether the horizontal edges should be faded when this view is
	 * scrolled horizontally.
	 * 
	 * @param horizontalFadingEdgeEnabled
	 *            true if the horizontal edges should be faded when the view is
	 *            scrolled horizontally
	 * @return self
	 */
	public self fadesXEdges(boolean horizontalFadingEdgeEnabled) {
		view.setHorizontalFadingEdgeEnabled(horizontalFadingEdgeEnabled);
		return (self) this;
	}

	/**
	 * Returns the size of the horizontal faded edges used to indicate that more
	 * content in this view is visible.
	 * 
	 * @return The size in pixels of the horizontal faded edge or 0 if
	 *         horizontal faded edges are not enabled for this view.
	 */
	public int xFadeEdge() {
		return view.getHorizontalFadingEdgeLength();
	}

	/**
	 * Indicate whether the horizontal scrollbar should be drawn or not. The
	 * scrollbar is not drawn by default.
	 * 
	 * @return true if the horizontal scrollbar should be painted, false
	 *         otherwise
	 */
	public boolean xScrolls() {
		return view.isHorizontalScrollBarEnabled();
	}

	/**
	 * Define whether the horizontal scrollbar should be drawn or not.
	 * 
	 * @param horizontalScrollBarEnabled
	 *            true if the horizontal scrollbar should be painted
	 * @return self
	 */
	public self xScrolls(boolean horizontalScrollBarEnabled) {
		view.setHorizontalScrollBarEnabled(horizontalScrollBarEnabled);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onHoverEvent(MotionEvent),
	 * used to handle hover events.
	 * 
	 * @param hoverEvent
	 *            The motion event that describes the hover.
	 * @return True if the view handled the hover event
	 */
	public boolean hover(MotionEvent hoverEvent) {
		return view.onHoverEvent(hoverEvent);
	}

	/**
	 * Register a callback to be invoked when a hover event is sent to this
	 * view.
	 * 
	 * @param hoverListener
	 *            the hover listener to attach to this view
	 * @return self
	 */
	public self hover(vapor.listeners.view.$hover hoverListener) {
		view.setOnHoverListener(hoverListener);
		return (self) this;
	}

	/**
	 * Fluent Vapor method for invoking onHoverChanged(boolean), used to handle
	 * hover state changes.
	 * 
	 * @param hovered
	 * @return self
	 */
	public self hoverChanged(boolean hovered) {
		view.onHoverChanged(hovered);
		return (self) this;
	}

	/**
	 * Returns true if the view is currently hovered.
	 * 
	 * @return True if the view is currently hovered.
	 */
	public boolean hovered() {
		return view.isHovered();
	}

	/**
	 * Sets whether the view is currently hovered.
	 * 
	 * @param hovered
	 *            True if the view is hovered.
	 * @return self
	 */
	public self hovered(boolean hovered) {
		view.setHovered(hovered);
		return (self) this;
	}

	/**
	 * Returns this view's identifier.
	 * 
	 * @return a positive integer used to identify the view or NO_ID if the view
	 *         has no ID
	 */
	public int id() {
		return view.getId();
	}

	/**
	 * Sets the identifier for this view.
	 * 
	 * @param id
	 *            a number used to identify the view
	 * @return self
	 */
	public self id(int id) {
		view.setId(id);
		return (self) this;
	}

	/**
	 * Gets the mode for determining whether this View is important for
	 * accessibility which is if it fires accessibility events and if it is
	 * reported to accessibility services that query the screen.
	 * 
	 * @return The mode for determining whether a View is important for
	 *         accessibility.
	 */
	public int vipAccess() {
		return view.getImportantForAccessibility();
	}

	/**
	 * Sets how to determine whether this view is important for accessibility
	 * which is if it fires accessibility events and if it is reported to
	 * accessibility services that query the screen.
	 * 
	 * @param mode
	 *            How to determine whether this view is important for
	 *            accessibility.
	 * @return self
	 */
	public self vipAccess(int mode) {
		view.setImportantForAccessibility(mode);
		return (self) this;
	}

	/**
	 * Inflate a view from an XML resource.
	 * 
	 * @param context
	 *            The Context object for your activity or application.
	 * @param resource
	 *            The resource ID to inflate
	 * @param root
	 *            A view group that will be the parent. Used to properly inflate
	 *            the layout_* parameters.
	 * @return
	 */
	public static VaporView<? extends View, ?> inflate(Context context,
			int resource, VaporViewGroup<? extends ViewGroup, ?> root) {
		return $.vapor(View.inflate(context, resource, (ViewGroup) root.view()));
	}

	/**
	 * Fluent equivalent Vapor method for invoking
	 * onInitializeAccessibilityEventh(AccessibilityEvent), used to initialize
	 * an AccessibilityEvent with information about this View which is the event
	 * source. In other words, the source of an accessibility event is the view
	 * whose state change triggered firing the event.
	 * 
	 * @param accessibilityEvent
	 *            The event to initialize.
	 * @return self
	 */
	public self initAccess(AccessibilityEvent accessibilityEvent) {
		view.onInitializeAccessibilityEvent(accessibilityEvent);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking
	 * onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo), used to
	 * initialize an AccessibilityNodeInfo with information about this view.
	 * 
	 * @param accessibilityNodeInfo
	 *            The instance to initialize.
	 * @return self
	 */
	public self initAccess(AccessibilityNodeInfo accessibilityNodeInfo) {
		view.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
		return (self) this;
	}

	/**
	 * Invalidate the whole view.
	 * 
	 * @return self
	 */
	public self invalidate() {
		view.invalidate();
		return (self) this;
	}

	/**
	 * Mark the area defined by dirty as needing to be drawn.
	 * 
	 * @param dirty
	 *            the rectangle representing the bounds of the dirty region
	 * @return self
	 */
	public self invalidate(Rect dirty) {
		view.invalidate(dirty);
		return (self) this;
	}

	/**
	 * Mark the area defined by the rect (left,top,right,bottom) as needing to
	 * be drawn.
	 * 
	 * @param left
	 *            the left position of the dirty region
	 * @param top
	 *            the top position of the dirty region
	 * @param right
	 *            the right position of the dirty region
	 * @param bottom
	 *            the bottom position of the dirty region
	 * @return self
	 */
	public self invalidate(int left, int top, int right, int bottom) {
		view.invalidate(left, top, right, bottom);
		return (self) this;
	}

	/**
	 * Equivalent fluent Vapor method for invoking invalidateDrawable(Drawable),
	 * invalidates the specified Drawable.
	 * 
	 * @param drawable
	 *            the drawable to invalidate
	 * @return self
	 */
	public self invalidate(Drawable drawable) {
		view.invalidateDrawable(drawable);
		return (self) this;
	}

	/**
	 * Call Drawable.jumpToCurrentState() on all Drawable objects associated
	 * with this view.
	 * 
	 * @return self
	 */
	public self jumpState() {
		view.jumpDrawablesToCurrentState();
		return (self) this;
	}

	/**
	 * Returns whether the screen should remain on, corresponding to the current
	 * value of KEEP_SCREEN_ON.
	 * 
	 * @return Returns true if KEEP_SCREEN_ON is set.
	 */
	public boolean keepOn() {
		return view.getKeepScreenOn();
	}

	/**
	 * Controls whether the screen should remain on, modifying the value of
	 * KEEP_SCREEN_ON.
	 * 
	 * @param keepScreenOn
	 *            Supply true to set KEEP_SCREEN_ON.
	 * @return self
	 */
	public self keepOn(boolean keepScreenOn) {
		view.setKeepScreenOn(keepScreenOn);
		return (self) this;
	}

	/*
	 * public VaporView<T> key() { throw new UnsupportedOperationException(
	 * "Vapor Error: Method not implemented yet"); }
	 */
	/**
	 * Register a callback to be invoked when a hardware key is pressed in this
	 * view.
	 * 
	 * @param keyListener
	 *            the key listener to attach to this view
	 * @return self
	 */
	public self key(vapor.listeners.view.$key keyListener) {
		view.setOnKeyListener(keyListener);
		return (self) this;
	}

	/**
	 * Return the global KeyEvent.DispatcherState for this view's window.
	 * 
	 * @return the global KeyEvent.DispatcherState for this view's window.
	 */
	public KeyEvent.DispatcherState keyDispatcher() {
		return view.getKeyDispatcherState();
	}

	/**
	 * Fluent equivalent Vapor method for invoking onKeyDown(KeyEvent), called
	 * when KEYCODE_DPAD_CENTER or KEYCODE_ENTER is released, if the view is
	 * enabled and clickable.
	 * 
	 * @param keyEvent
	 *            The KeyEvent object that defines the button action.
	 * @return If you handled the event, return true. If you want to allow the
	 *         event to be handled by the next receiver, return false.
	 */
	public boolean keyDown(KeyEvent keyEvent) {
		return view.onKeyDown(keyEvent.getKeyCode(), keyEvent);
	}

	/**
	 * Fluent equivalent Vapor method for invoking onKeyLongPress(int,KeyEvent),
	 * called always returns false (doesn't handle the event).
	 * 
	 * @param keyEvent
	 *            Description of the key event.
	 * @return If you handled the event, return true. If you want to allow the
	 *         event to be handled by the next receiver, return false.
	 */
	public boolean keyLongPress(KeyEvent keyEvent) {
		return view.onKeyLongPress(keyEvent.getKeyCode(), keyEvent);
	}

	/**
	 * Fluent equivalent Vapor method for invoking
	 * onKeyMultiple(int,int,KeyEvent), always returns false (doesn't handle the
	 * event).
	 * 
	 * @param repeatCount
	 *            The number of times the action was made.
	 * @param keyEvent
	 *            The KeyEvent object that defines the button action.
	 * @return If you handled the event, return true. If you want to allow the
	 *         event to be handled by the next receiver, return false.
	 */
	public boolean keyMulti(int repeatCount, KeyEvent keyEvent) {
		return view.onKeyMultiple(keyEvent.getKeyCode(), repeatCount, keyEvent);
	}

	/**
	 * Fluent equivalent Vapor method for invoking onKeyPreIme(KeyEvent), used
	 * to handle a key event before it is processed by any input method
	 * associated with the view hierarchy.
	 * 
	 * @param keyEvent
	 *            Description of the key event.
	 * @return If you handled the event, return true. If you want to allow the
	 *         event to be handled by the next receiver, return false.
	 */
	public boolean keyPreIme(KeyEvent keyEvent) {
		return view.onKeyPreIme(keyEvent.getKeyCode(), keyEvent);
	}

	/**
	 * Fluent equivalent Vapor method for invoking onKeyShortcut(KeyEvent),
	 * called on the focused view when a key shortcut event is not handled.
	 * 
	 * @param keyEvent
	 *            Description of the key event.
	 * @return If you handled the event, return true. If you want to allow the
	 *         event to be handled by the next receiver, return false.
	 */
	public boolean keyHot(KeyEvent keyEvent) {
		return view.onKeyShortcut(keyEvent.getKeyCode(), keyEvent);
	}

	/**
	 * Fluent equivalent Vapor method for invoking onKeyUp(int,KeyEvent), called
	 * when KEYCODE_DPAD_CENTER or KEYCODE_ENTER is released.
	 * 
	 * @param keyEvent
	 *            The KeyEvent object that defines the button action.
	 * @return If you handled the event, return true. If you want to allow the
	 *         event to be handled by the next receiver, return false.
	 */
	public boolean keyUp(KeyEvent keyEvent) {
		return view.onKeyUp(keyEvent.getKeyCode(), keyEvent);
	}

	/**
	 * Gets the id of a view for which this view serves as a label for
	 * accessibility purposes.
	 * 
	 * @return The labeled view id.
	 */
	public int labelFor() { // API:17
		return view.getLabelFor();
	}

	/**
	 * Sets the id of a view for which this view serves as a label for
	 * accessibility purposes.
	 * 
	 * @param id
	 *            The labeled view id.
	 * @return self
	 */
	public self labelFor(int id) { // API:17
		view.setLabelFor(id);
		return (self) this;
	}

	/**
	 * Updates the Paint object used with the current layer (used only if the
	 * current layer type is not set to LAYER_TYPE_NONE). Changed properties of
	 * the Paint provided to layerType(int, android.graphics.Paint) will be used
	 * the next time the View is redrawn, but layerPaint(android.graphics.Paint)
	 * must be called to ensure that the view gets redrawn immediately.
	 * 
	 * @param paint
	 *            The paint used to compose the layer. This argument is optional
	 *            and can be null. It is ignored when the layer type is
	 *            LAYER_TYPE_NONE
	 * @return self
	 */
	public self layerPaint(Paint paint) { // API:17
		view.setLayerPaint(paint);
		return (self) this;
	}

	/**
	 * Indicates what type of layer is currently associated with this view.
	 * 
	 * @return LAYER_TYPE_NONE, LAYER_TYPE_SOFTWARE or LAYER_TYPE_HARDWARE
	 */
	public int layerType() {
		return view.getLayerType();
	}

	/**
	 * Specifies the type of layer backing this view.
	 * 
	 * @param layerType
	 *            The type of layer to use with this view, must be one of
	 *            LAYER_TYPE_NONE, LAYER_TYPE_SOFTWARE or LAYER_TYPE_HARDWARE
	 * @param paint
	 *            The paint used to compose the layer. This argument is optional
	 *            and can be null. It is ignored when the layer type is
	 *            LAYER_TYPE_NONE
	 * @return self
	 */
	public self layerType(int layerType, Paint paint) {
		view.setLayerType(layerType, paint);
		return (self) this;
	}

	/**
	 * Assign a size and position to a view and all of its descendants
	 * 
	 * @param left
	 *            Left position, relative to parent
	 * @param top
	 *            Top position, relative to parent
	 * @param right
	 *            Right position, relative to parent
	 * @param bottom
	 *            Bottom position, relative to parent
	 * @return self
	 */
	public self layout(int left, int top, int right, int bottom) {
		view.layout(left, top, right, bottom);
		return (self) this;
	}

	/**
	 * Add a listener that will be called when the bounds of the view change due
	 * to layout processing.
	 * 
	 * @param layoutChangeListener
	 *            The listener that will be called when layout bounds change.
	 * @return self
	 */
	public self layout(vapor.listeners.view.$layout layoutChangeListener) {
		view.addOnLayoutChangeListener(layoutChangeListener);
		return (self) this;
	}// Checked: 071220121141

	/**
	 * Returns the resolved layout direction for this view.
	 * 
	 * @return LAYOUT_DIRECTION_RTL if the layout direction is RTL or returns
	 *         LAYOUT_DIRECTION_LTR if the layout direction is not RTL. For
	 *         compatibility, this will return LAYOUT_DIRECTION_LTR if API
	 *         version is lower than JELLY_BEAN_MR1.
	 */
	public int layoutDirection() { // API:17
		return view.getLayoutDirection();
	}

	/**
	 * Set the layout direction for this view. This will propagate a reset of
	 * layout direction resolution to the view's children and resolve layout
	 * direction for this view.
	 * 
	 * @param layoutDirection
	 *            the layout direction to set. Should be one of:
	 *            LAYOUT_DIRECTION_LTR, LAYOUT_DIRECTION_RTL,
	 *            LAYOUT_DIRECTION_INHERIT, LAYOUT_DIRECTION_LOCALE. Resolution
	 *            will be done if the value is set to LAYOUT_DIRECTION_INHERIT.
	 *            The resolution proceeds up the parent chain of the view to get
	 *            the value. If there is no parent, then it will return the
	 *            default LAYOUT_DIRECTION_LTR.
	 * @return self
	 */
	public self layoutDirection(int layoutDirection) {
		view.setLayoutDirection(layoutDirection);
		return (self) this;
	}

	/*
	 * /**A Drawable can call this to get the resolved layout direction of the
	 * who.
	 * 
	 * @param who The drawable being queried.
	 * 
	 * @return
	 * 
	 * public abstract int layoutDirection(Drawable who){ return
	 * view.getResolvedLayoutDirection(who); }
	 */

	/**
	 * Forces this view to be laid out during the next layout pass.
	 * 
	 * @return self
	 */
	public self layoutForce() {
		view.forceLayout();
		return (self) this;
	}// Checked: 081220121024

	/**
	 * Indicates whether or not this view's layout will be requested during the
	 * next hierarchy layout pass.
	 * 
	 * @return true if the layout will be forced during next layout pass
	 */
	public boolean layoutReqd() {
		return view.isLayoutRequested();
	}

	/**
	 * Get the LayoutParams associated with this view.
	 * 
	 * @return The LayoutParams associated with this view, or null if no
	 *         parameters have been set yet
	 */
	public LayoutParams layoutParams() {
		return view.getLayoutParams();
	}

	/**
	 * Set the layout parameters associated with this view.
	 * 
	 * @param params
	 *            The layout parameters for this view, cannot be null
	 * @return self
	 */
	public self layoutParams(ViewGroup.LayoutParams params) {
		view.setLayoutParams(params);
		return (self) this;
	}

	/**
	 * Left position of this view relative to its parent.
	 * 
	 * @return The left edge of this view, in pixels.
	 */
	public final int left() {
		return view.getLeft();
	}

	/**
	 * Sets the left position of this view relative to its parent.
	 * 
	 * @param left
	 *            The left of this view, in pixels.
	 * @return self
	 */
	public self left(int left) {
		view.setLeft(left);
		return (self) this;
	}

	/**
	 * Call this view's OnLongClickListener, if it is defined.
	 * 
	 * @return True if one of the above receivers consumed the event, false
	 *         otherwise.
	 */
	public boolean longClick() {
		return view.performLongClick();
	}

	/**
	 * Register a callback to be invoked when this view is clicked and held.
	 * 
	 * @param longClickListener
	 *            The callback that will run
	 * @return self
	 */
	public self longClick(vapor.listeners.view.$longClick longClickListener) {
		view.setOnLongClickListener(longClickListener);
		return (self) this;
	}

	/**
	 * Indicates whether this view reacts to long click events or not.
	 * 
	 * @return true if the view is long clickable, false otherwise
	 */
	public boolean longClickable() {
		return view.isLongClickable();
	}

	/**
	 * Enables or disables long click events for this view.
	 * 
	 * @param longClickable
	 *            true to make the view long clickable, false otherwise
	 * @return self
	 */
	public self longClickable(boolean longClickable) {
		view.setLongClickable(longClickable);
		return (self) this;
	}

	/**
	 * 
	 * @param rect
	 * @return
	 */
	public final boolean localRect(Rect rect) {
		return view.getLocalVisibleRect(rect);
	}

	/**
	 * The transform matrix of this view, which is calculated based on the
	 * current rotation, scale, and pivot properties.
	 * 
	 * @return The current transform matrix for the view.
	 */
	public Matrix matrix() {
		return view.getMatrix();
	}

	/**
	 * This is called to find out how big a view should be.
	 * 
	 * @param widthMeasureSpec
	 *            Horizontal space requirements as imposed by the parent
	 * @param heightMeasureSpec
	 *            Vertical space requirements as imposed by the parent
	 * @return self
	 */
	public final self measure(int widthMeasureSpec, int heightMeasureSpec) {
		view.measure(widthMeasureSpec, heightMeasureSpec);
		return (self) this;
	}

	/**
	 * Returns the raw height component (that is the result is masked by
	 * MEASURED_SIZE_MASK).
	 * 
	 * @withState true combines measurement with state
	 * @return The raw measured height of this view (optionally combined with
	 *         state).
	 */
	public final int measuredHeight(boolean withState) {
		if (withState)
			return view.getMeasuredHeightAndState();
		else
			return view.getMeasuredHeight();
	}

	/**
	 * Like measuredHeight(boolean), but only returns the raw width component
	 * (that is the result is masked by MEASURED_SIZE_MASK).
	 * 
	 * @return
	 */
	public final int measuredHeight() {
		return measuredHeight(false);
	}

	/**
	 * Return only the state bits of getMeasuredWidthAndState() and
	 * getMeasuredHeightAndState(), combined into one integer.
	 * 
	 * @return
	 */
	public final int measuredState() {
		return view.getMeasuredState();
	}

	/**
	 * Returns the raw width component (that is the result is masked by
	 * MEASURED_SIZE_MASK).
	 * 
	 * @withState true combines measurement with state
	 * @return The raw measured width of this view (optionally combined with
	 *         state).
	 */
	public final int measuredWidth(boolean withState) {
		if (withState)
			return view.getMeasuredWidthAndState();
		else
			return view.getMeasuredWidth();
	}

	/**
	 * Like measuredWidth(boolean), but only returns the raw width component
	 * (that is the result is masked by MEASURED_SIZE_MASK).
	 * 
	 * @return The raw measured width of this view.
	 */
	public final int measuredWidth() {
		return measuredWidth(false);
	}

	/**
	 * Bring up the context menu for this view.
	 * 
	 * @return Whether a context menu was displayed.
	 */
	public boolean menu() {
		return view.showContextMenu();
	}

	/**
	 * Show the context menu for this view.
	 * 
	 * @param menu
	 *            The context menu to populate
	 * @return self
	 */
	public self menu(ContextMenu menu) {
		view.createContextMenu(menu);
		return (self) this;
	} // Checked: 071220121226

	/**
	 * Register a callback to be invoked when the context menu for this view is
	 * being built.
	 * 
	 * @param createcContextMenuListener
	 *            The callback that will run
	 * @return self
	 */
	public self menu(vapor.listeners.view.$menu createContextMenuListener) {
		view.setOnCreateContextMenuListener(createContextMenuListener);
		return (self) this;
	}

	/**
	 * Returns the minimum height of the view.
	 * 
	 * @return the minimum height the view will try to be.
	 */
	public int minHeight() {
		return view.getMinimumHeight();
	}

	/**
	 * Sets the minimum height of the view.
	 * 
	 * @param minHeight
	 *            The minimum height the view will try to be.
	 * @return self
	 */
	public self minHeight(int minHeight) {
		view.setMinimumHeight(minHeight);
		return (self) this;
	}

	/**
	 * Returns the minimum width of the view.
	 * 
	 * @return the minimum width the view will try to be.
	 */
	public int minWidth() {
		return view.getMinimumWidth();
	}

	/**
	 * Sets the minimum width of the view.
	 * 
	 * @param minWidth
	 *            The minimum width the view will try to be.
	 * @return self
	 */
	public self minWidth(int minWidth) {
		view.setMinimumWidth(minWidth);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking
	 * onGenericMotionEvent(MotionEvent), used to handle generic motion events.
	 * 
	 * @param genericMotionEvent
	 *            The generic motion event being processed.
	 * @return True if the event was handled, false otherwise.
	 */
	public boolean motion(MotionEvent genericMotionEvent) {
		return view.onGenericMotionEvent(genericMotionEvent);
	}

	/**
	 * Register a callback to be invoked when a generic motion event is sent to
	 * this view.
	 * 
	 * @param genericMotionListener
	 *            the generic motion listener to attach to this view
	 * @return self
	 */
	public self motion(vapor.listeners.view.$motion genericMotionListener) {
		view.setOnGenericMotionListener(genericMotionListener);
		return (self) this;
	}

	/**
	 * Gets the id of the view to use when the next focus is FOCUS_DOWN.
	 * 
	 * @return The next focus ID, or NO_ID if the framework should decide
	 *         automatically.
	 */
	public int nextDown() {
		return view.getNextFocusDownId();
	}

	/**
	 * Sets the id of the view to use when the next focus is FOCUS_DOWN.
	 * 
	 * @param nextFocusDownId
	 *            The next focus ID, or NO_ID if the framework should decide
	 *            automatically.
	 * @return self
	 */
	public self nextDown(int nextFocusDownId) {
		view.setNextFocusDownId(nextFocusDownId);
		return (self) this;
	}

	/**
	 * Gets the id of the view to use when the next focus is FOCUS_FORWARD.
	 * 
	 * @return The next focus ID, or NO_ID if the framework should decide
	 *         automatically.
	 */
	public int nextForward() {
		return view.getNextFocusForwardId();
	}

	/**
	 * Sets the id of the view to use when the next focus is FOCUS_FORWARD.
	 * 
	 * @param nextFocusForwardId
	 *            The next focus ID, or NO_ID if the framework should decide
	 *            automatically.
	 * @return self
	 */
	public self nextForward(int nextFocusForwardId) {
		view.setNextFocusForwardId(nextFocusForwardId);
		return (self) this;
	}

	/**
	 * Gets the id of the view to use when the next focus is FOCUS_LEFT.
	 * 
	 * @return The next focus ID, or NO_ID if the framework should decide
	 *         automatically.
	 */
	public int nextLeft() {
		return view.getNextFocusLeftId();
	}

	/**
	 * Sets the id of the view to use when the next focus is FOCUS_LEFT.
	 * 
	 * @param nextFocusLeftId
	 *            The next focus ID, or NO_ID if the framework should decide
	 *            automatically.
	 * @return self
	 */
	public self nextLeft(int nextFocusLeftId) {
		view.setNextFocusLeftId(nextFocusLeftId);
		return (self) this;
	}

	/**
	 * Gets the id of the view to use when the next focus is FOCUS_RIGHT.
	 * 
	 * @return The next focus ID, or NO_ID if the framework should decide
	 *         automatically.
	 */
	public int nextRight() {
		return view.getNextFocusRightId();
	}

	/**
	 * Sets the id of the view to use when the next focus is FOCUS_RIGHT.
	 * 
	 * @param nextFocusRightId
	 *            The next focus ID, or NO_ID if the framework should decide
	 *            automatically.
	 * @return self
	 */
	public self nextRight(int nextFocusRightId) {
		view.setNextFocusRightId(nextFocusRightId);
		return (self) this;
	}

	/**
	 * Gets the id of the view to use when the next focus is FOCUS_UP.
	 * 
	 * @return The next focus ID, or NO_ID if the framework should decide
	 *         automatically.
	 */
	public int nextUp() {
		return view.getNextFocusUpId();
	}

	/**
	 * Sets the id of the view to use when the next focus is FOCUS_UP.
	 * 
	 * @param nextFocusUpId
	 *            The next focus ID, or NO_ID if the framework should decide
	 *            automatically.
	 * @return self
	 */
	public self nextUp(int nextFocusUpId) {
		view.setNextFocusUpId(nextFocusUpId);
		return (self) this;
	}

	/**
	 * Returns an AccessibilityNodeInfo representing this view from the point of
	 * view of an AccessibilityService.
	 * 
	 * @return A populated AccessibilityNodeInfo.
	 */
	public AccessibilityNodeInfo accessNodeInfo() {
		return view.createAccessibilityNodeInfo();
	}// Checked: 071220121225

	/**
	 * Gets the provider for managing a virtual view hierarchy rooted at this
	 * View and reported to AccessibilityServices that explore the window
	 * content.
	 * 
	 * @return The provider.
	 */
	public AccessibilityNodeProvider accessNodeProvider() {
		return view.getAccessibilityNodeProvider();
	}// Checked: 081220121026

	/**
	 * Returns whether or not this View draws on its own.
	 * 
	 * @return true if this view has nothing to draw, false otherwise
	 */
	public boolean noDraw() {
		return view.willNotDraw();
	}

	/**
	 * If this view doesn't do any drawing on its own, set this flag to allow
	 * further optimizations.
	 * 
	 * @param willNotDraw
	 *            whether or not this View draw on its own
	 * @return self
	 */
	public self noDraw(boolean willNotDraw) {
		view.setWillNotDraw(willNotDraw);
		return (self) this;
	}

	/**
	 * Returns whether or not this View can cache its drawing or not.
	 * 
	 * @return true if this view does not cache its drawing, false otherwise
	 */
	public boolean noDrawCache() {
		return view.willNotCacheDrawing();
	}

	/**
	 * When a View's drawing cache is enabled, drawing is redirected to an
	 * offscreen bitmap.
	 * 
	 * @param willNotCacheDrawing
	 *            true if this view does not cache its drawing, false otherwise
	 * @return self
	 */
	public self noDrawCache(boolean willNotCacheDrawing) {
		view.setWillNotCacheDrawing(willNotCacheDrawing);
		return (self) this;
	}

	/**
	 * Gets whether the framework should discard touches when the view's window
	 * is obscured by another visible window.
	 * 
	 * @return True if touch filtering is enabled.
	 */
	public boolean obscureTouchFilter() {
		return view.getFilterTouchesWhenObscured();
	}

	/**
	 * Sets whether the framework should discard touches when the view's window
	 * is obscured by another visible window.
	 * 
	 * @param enabled
	 * @return
	 */
	public self obscureTouchFilter(boolean enabled) {
		view.setFilterTouchesWhenObscured(enabled);
		return (self) this;
	}

	/**
	 * Offset this view's location by the specified amount of pixels.
	 * 
	 * @param offset
	 *            the number of pixels to offset the view by
	 * @return self
	 */
	public self offset(int offset) {
		return offsetLR(offset).offsetTB(offset);
	}

	/**
	 * Offset this view's horizontal location by the specified amount of pixels.
	 * 
	 * @param offset
	 *            the number of pixels to offset the view by
	 * @return self
	 */
	public self offsetLR(int offset) {
		view.offsetLeftAndRight(offset);
		return (self) this;
	}

	/**
	 * Offset this view's vertical location by the specified number of pixels.
	 * 
	 * @param offset
	 *            the number of pixels to offset the view by
	 * @return self
	 */
	public self offsetTB(int offset) {
		view.offsetTopAndBottom(offset);
		return (self) this;
	}

	/**
	 * Indicates whether this View is opaque.
	 * 
	 * @return True if this View is guaranteed to be fully opaque, false
	 *         otherwise.
	 */
	public boolean opaque() {
		return view.isOpaque();
	}

	/**
	 * Returns whether this View has content which overlaps.
	 * 
	 * @return true if the content in this view might overlap, false otherwise.
	 */
	public boolean overlaps() {
		return view.hasOverlappingRendering();
	}

	/**
	 * Returns the over-scroll mode for this view.
	 * 
	 * @return self view's over-scroll mode.
	 */
	public int overScroll() {
		return view.getOverScrollMode();
	}

	/**
	 * Set the over-scroll mode for this view.
	 * 
	 * @param overScrollMode
	 *            The new over-scroll mode for this view.
	 * @return self
	 */
	public self overScroll(int overScrollMode) {
		view.setOverScrollMode(overScrollMode);
		return (self) this;
	}

	/**
	 * Sets the padding. The view may add on the space required to display the
	 * scrollbars, depending on the style and visibility of the scrollbars. So
	 * the values returned from padLeft(), padTop(), padRight() and padBottom()
	 * may be different from the values set in this call.
	 * 
	 * @param allSides
	 *            the padding in pixels to apply to top, left, bottom and right
	 *            sides
	 * @return self
	 */
	public self pad(int allSides) {
		return pad(allSides, allSides, allSides, allSides);
	}

	/**
	 * Sets the padding. The view may add on the space required to display the
	 * scrollbars, depending on the style and visibility of the scrollbars. So
	 * the values returned from padLeft(), padTop(), padRight() and padBottom()
	 * may be different from the values set in this call.
	 * 
	 * @param topBottom
	 *            the top and bottom padding in pixels
	 * @param leftRight
	 *            the left and right padding in pixels
	 * @return self
	 */
	public self pad(int topBottom, int leftRight) {
		return pad(leftRight, topBottom, leftRight, topBottom);
	}

	/**
	 * Sets the padding. The view may add on the space required to display the
	 * scrollbars, depending on the style and visibility of the scrollbars. So
	 * the values returned from padLeft(), padTop(), padRight() and padBottom()
	 * may be different from the values set in this call.
	 * 
	 * @param top
	 *            the top padding in pixels
	 * @param leftRight
	 *            the left and right padding in pixels
	 * @param bottom
	 *            the bottom padding in pixels
	 * @return self
	 */
	public self pad(int top, int leftRight, int bottom) {
		return pad(leftRight, top, leftRight, bottom);
	}

	/**
	 * Sets the padding. The view may add on the space required to display the
	 * scrollbars, depending on the style and visibility of the scrollbars. So
	 * the values returned from padLeft(), padTop(), padRight() and padBottom()
	 * may be different from the values set in this call.
	 * 
	 * @param left
	 *            the left padding in pixels
	 * @param top
	 *            the top padding in pixels
	 * @param right
	 *            the right padding in pixels
	 * @param bottom
	 *            the bottom padding in pixels
	 * @return self
	 */
	public self pad(int left, int top, int right, int bottom) {
		view.setPadding(left, top, right, bottom);
		return (self) this;
	}

	/**
	 * Returns the bottom padding of this view.
	 * 
	 * @return the bottom padding in pixels
	 */
	public int padBottom() {
		return view.getPaddingBottom();
	}

	/**
	 * 
	 * @param bottom
	 * @return
	 */
	public self padBottom(int bottom) {
		pad(padLeft(), padTop(), bottom, padRight());
		return (self) this;
	}

	/**
	 * Returns the end padding of this view depending on its resolved layout
	 * direction. If there are inset and enabled scrollbars, this value may
	 * include the space required to display the scrollbars as well.
	 * 
	 * @return the end padding in pixels
	 */
	public int padEnd() { // API:17
		return view.getPaddingEnd();
	}

	/**
	 * Returns the left padding of this view.
	 * 
	 * @return the left padding in pixels
	 */
	public int padLeft() {
		return view.getPaddingLeft();
	}

	/**
	 * 
	 * @param left
	 * @return
	 */
	public self padLeft(int left) {
		pad(left, padTop(), padBottom(), padRight());
		return (self) this;
	}

	/**
	 * Return if the padding as been set thru relative values
	 * setPaddingRelative(int, int, int, int) or thru
	 * 
	 * @return true if the padding is relative or false if it is not.
	 */
	public boolean paddedRel() { // API:17
		return view.isPaddingRelative();
	}

	/**
	 * Sets the relative padding. The view may add on the space required to
	 * display the scrollbars, depending on the style and visibility of the
	 * scrollbars. So the values returned from padStart(), padTop(), padEnd()
	 * and padBottom() may be different from the values set in this call.
	 * 
	 * @param start
	 *            the start padding in pixels
	 * @param top
	 *            the top padding in pixels
	 * @param end
	 *            the end padding in pixels
	 * @param bottom
	 *            the bottom padding in pixels
	 * @return self
	 */
	public self padRel(int start, int top, int end, int bottom) { // API:17
		view.setPaddingRelative(start, top, end, bottom);
		return (self) this;
	}

	/**
	 * Sets the relative padding. The view may add on the space required to
	 * display the scrollbars, depending on the style and visibility of the
	 * scrollbars. So the values returned from padStart(), padTop(), padEnd()
	 * and padBottom() may be different from the values set in this call.
	 * 
	 * @param allSides
	 *            the relative padding to apply to top, left, bottom and right
	 *            sides
	 * @return self
	 */
	public self padRel(int allSides) {
		return padRel(allSides, allSides, allSides, allSides);
	}

	/**
	 * Sets the relative padding. The view may add on the space required to
	 * display the scrollbars, depending on the style and visibility of the
	 * scrollbars. So the values returned from padStart(), padTop(), padEnd()
	 * and padBottom() may be different from the values set in this call.
	 * 
	 * @param topBottom
	 *            the top and bottom relative padding in pixels
	 * @param startEnd
	 *            the start and end relative padding in pixels
	 * @return self
	 */
	public self padRel(int topBottom, int startEnd) {
		return padRel(startEnd, topBottom, startEnd, topBottom);
	}

	/**
	 * Sets the relative padding. The view may add on the space required to
	 * display the scrollbars, depending on the style and visibility of the
	 * scrollbars. So the values returned from padStart(), padTop(), padEnd()
	 * and padBottom() may be different from the values set in this call.
	 * 
	 * @param top
	 *            the top relative padding in pixels
	 * @param startEnd
	 *            the start and end relative padding in pixels
	 * @param bottom
	 *            the bottom relative padding in pixels
	 * @return self
	 */
	public self padRel(int top, int startEnd, int bottom) {
		return padRel(startEnd, top, startEnd, bottom);
	}

	/**
	 * Returns the right padding of this view.
	 * 
	 * @return the right padding in pixels
	 */
	public int padRight() {
		return view.getPaddingRight();
	}

	/**
	 * 
	 * @param right
	 * @return
	 */
	public self padRight(int right) {
		pad(padLeft(), padTop(), padBottom(), right);
		return (self) this;
	}

	/**
	 * Returns the start padding of this view depending on its resolved layout
	 * direction. If there are inset and enabled scrollbars, this value may
	 * include the space required to display the scrollbars as well.
	 * 
	 * @return the start padding in pixels
	 */
	public int padStart() { // API:17
		return view.getPaddingStart();
	}

	/**
	 * Returns the top padding of this view.
	 * 
	 * @return the top padding in pixels
	 */
	public int padTop() {
		return view.getPaddingTop();
	}

	/**
	 * 
	 * @param top
	 * @return
	 */
	public self padTop(int top) {
		pad(padLeft(), top, padBottom(), padRight());
		return (self) this;
	}

	/**
	 * Gets the parent of this view. Note that the parent is a ViewParent and
	 * not necessarily a View.
	 * 
	 * @return parent of this view
	 */
	public final ViewParent parent() {
		return parent(false);
	}

	/**
	 * Gets the parent of this view.
	 * 
	 * @param forAccessibility
	 *            true to get the parent for accessibility purposes
	 * @return Parent of this view.
	 */
	public ViewParent parent(boolean forAccessibility) {
		return forAccessibility ? view.getParentForAccessibility() : view
				.getParent();
	}

	/**
	 * Indicates whether the entire hierarchy under this view will save its
	 * state when a state saving traversal occurs from its parent.
	 * 
	 * @return Returns true if the view state saving from parent is enabled,
	 *         else false.
	 */
	public boolean parentSaveable() {
		return view.isSaveFromParentEnabled();
	}

	/**
	 * Controls whether the entire hierarchy under this view will save its state
	 * when a state saving traversal occurs from its parent.
	 * 
	 * @param enabled
	 *            Set to false to disable state saving, or true (the default) to
	 *            allow it.
	 * @return self
	 */
	public self parentSaveable(boolean enabled) {
		view.setSaveFromParentEnabled(enabled);
		return (self) this;
	}

	/**
	 * The x location of the point around which the view is rotated and scaled.
	 * 
	 * @return The x location of the pivot point.
	 */
	public float pivX() {
		return view.getPivotX();
	}

	/**
	 * Sets the x location of the point around which the view is rotated and
	 * scaled.
	 * 
	 * @param pivotX
	 *            The x location of the pivot point.
	 * @return self
	 */
	public self pivX(float pivotX) {
		view.setPivotX(pivotX);
		return (self) this;
	}

	/**
	 * The y location of the point around which the view is rotated and scaled.
	 * 
	 * @return The y location of the pivot point.
	 */
	public float pivY() {
		return view.getPivotY();
	}

	/**
	 * Sets the y location of the point around which the view is rotated and
	 * scaled.
	 * 
	 * @param pivotY
	 *            The y location of the pivot point.
	 * @return self
	 */
	public self pivY(float pivotY) {
		view.setPivotY(pivotY);
		return (self) this;
	}

	/**
	 * Equivalent fluent Vapor method for invoking
	 * onPopulateAccessibilityEvent(AccessibilityEvent), called from
	 * dispatchPopulateAccessibilityEvent(AccessibilityEvent) giving a chance to
	 * this View to populate the accessibility event with its text content.
	 * 
	 * @param accessibilityEvent
	 *            The accessibility event which to populate.
	 * @return self
	 */
	public self populateAccess(AccessibilityEvent accessibilityEvent) {
		view.onPopulateAccessibilityEvent(accessibilityEvent);
		return (self) this;
	}

	/**
	 * Computes the coordinates of this view.
	 * 
	 * @param location
	 * @param global
	 *            true returns coordinates on the screen, false returns
	 *            coordinates in its window
	 * @return
	 */
	public self pos(int[] location, boolean global) {
		if (global)
			view.getLocationOnScreen(location);
		else
			view.getLocationInWindow(location);
		return (self) this;
	}

	/**
	 * Causes the Runnable to be added to the message queue.
	 * 
	 * @param action
	 *            The Runnable that will be executed.
	 * @return Returns true if the Runnable was successfully placed in to the
	 *         message queue. Returns false on failure, usually because the
	 *         looper processing the message queue is exiting.
	 */
	public boolean post(Runnable action) {
		return view.post(action);
	}

	/**
	 * Causes the Runnable to be added to the message queue, to be run after the
	 * specified amount of time elapses.
	 * 
	 * @param action
	 *            The Runnable that will be executed.
	 * @param delayMillis
	 *            The delay (in milliseconds) until the Runnable will be
	 *            executed.
	 * @return true if the Runnable was successfully placed in to the message
	 *         queue. Returns false on failure, usually because the looper
	 *         processing the message queue is exiting. Note that a result of
	 *         true does not mean the Runnable will be processed -- if the
	 *         looper is quit before the delivery time of the message occurs
	 *         then the message will be dropped.
	 */
	public boolean post(Runnable action, long delayMillis) {
		return view.postDelayed(action, delayMillis);
	}

	/**
	 * Causes the Runnable to execute on the next animation time step.
	 * 
	 * @param action
	 *            Causes the Runnable to execute on the next animation time
	 *            step. The runnable will be run on the user interface thread.
	 * @return self
	 */
	public self postAnim(Runnable action) {
		view.postOnAnimation(action);
		return (self) this;
	}

	/**
	 * Causes the Runnable to execute on the next animation time step, after the
	 * specified amount of time elapses.
	 * 
	 * @param action
	 *            The Runnable that will be executed.
	 * @param delayMillis
	 *            The delay (in milliseconds) until the Runnable will be
	 *            executed.
	 * @return self
	 */
	public self postAnim(Runnable action, long delayMillis) {
		view.postOnAnimationDelayed(action, delayMillis);
		return (self) this;
	}

	/**
	 * Cause an invalidate to happen on the next animation time step, typically
	 * the next display frame.
	 * 
	 * @return self
	 */
	public self postAnimInvalidate() {
		view.postInvalidateOnAnimation();
		return (self) this;
	}

	/**
	 * Cause an invalidate of the specified area to happen on the next animation
	 * time step, typically the next display frame.
	 * 
	 * @param left
	 *            The left coordinate of the rectangle to invalidate.
	 * @param top
	 *            The top coordinate of the rectangle to invalidate.
	 * @param right
	 *            The right coordinate of the rectangle to invalidate.
	 * @param bottom
	 *            The bottom coordinate of the rectangle to invalidate.
	 * @return self
	 */
	public self postAnimInvalidate(int left, int top, int right, int bottom) {
		view.postInvalidateOnAnimation(left, top, right, bottom);
		return (self) this;
	}

	/**
	 * Cause an invalidate to happen on a subsequent cycle through the event
	 * loop.
	 * 
	 * @return self
	 */
	public self postInvalidate() {
		view.postInvalidate();
		return (self) this;
	}

	/**
	 * Cause an invalidate to happen on a subsequent cycle through the event
	 * loop.
	 * 
	 * @param delayMilliseconds
	 *            the duration in milliseconds to delay the invalidation by
	 * @return self
	 */
	public self postInvalidate(long delayMilliseconds) {
		view.postInvalidateDelayed(delayMilliseconds);
		return (self) this;
	}

	/**
	 * Cause an invalidate of the specified area to happen on a subsequent cycle
	 * through the event loop.
	 * 
	 * @param left
	 *            The left coordinate of the rectangle to invalidate.
	 * @param top
	 *            The top coordinate of the rectangle to invalidate.
	 * @param right
	 *            The right coordinate of the rectangle to invalidate.
	 * @param bottom
	 *            The bottom coordinate of the rectangle to invalidate.
	 * @return self
	 */
	public self postInvalidate(int left, int top, int right, int bottom) {
		view.postInvalidate(left, top, right, bottom);
		return (self) this;
	}

	/**
	 * Cause an invalidate of the specified area to happen on a subsequent cycle
	 * through the event loop.
	 * 
	 * @param delayMilliseconds
	 *            the duration in milliseconds to delay the invalidation by
	 * @param left
	 *            The left coordinate of the rectangle to invalidate.
	 * @param top
	 *            The top coordinate of the rectangle to invalidate.
	 * @param right
	 *            The right coordinate of the rectangle to invalidate.
	 * @param bottom
	 *            The bottom coordinate of the rectangle to invalidate.
	 * @return self
	 */
	public self postInvalidate(long delayMilliseconds, int left, int top,
			int right, int bottom) {
		view.postInvalidateDelayed(delayMilliseconds, left, top, right, bottom);
		return (self) this;
	}

	/**
	 * Indicates whether the view is currently in pressed state.
	 * 
	 * @return true if the view is currently pressed, false otherwise
	 */
	public boolean pressed() {
		return view.isPressed();
	}

	/**
	 * Sets the pressed state for this view.
	 * 
	 * @param pressed
	 *            Pass true to set the View's internal state to "pressed", or
	 *            false to reverts the View's internal state from a previously
	 *            set "pressed" state.
	 * @return self
	 */
	public self pressed(boolean pressed) {
		view.setPressed(pressed);
		return (self) this;
	}

	/**
	 * Equivalent fluent Vapor method for invoking
	 * checkInputConnectionProxy(View), called by the InputMethodManager when a
	 * view who is not the current input connection target is trying to make a
	 * call on the manager.
	 * 
	 * @param view
	 *            The View that is making the InputMethodManager call.
	 * @return Return true to allow the call, false to reject.
	 */
	public boolean proxy(VaporView<? extends View, ?> view) {
		return this.view.checkInputConnectionProxy(view.view());
	} // Checked: 071220121209

	/**
	 * Returns the resources associated with this view.
	 * 
	 * @return Resources object.
	 */
	public Resources res() {
		return view.getResources();
	}

	/**
	 * If some part of this view is not clipped by any of its parents, then
	 * return that area in r in global (root) coordinates.
	 * 
	 * @param rect
	 *            If true is returned, r holds the global coordinates of the
	 *            visible portion of this view.
	 * @return true if rect is non-empty (i.e. part of the view is visible at
	 *         the root level.)
	 */
	public boolean rect(Rect rect) {
		return view.getGlobalVisibleRect(rect);
	}

	/**
	 * If some part of this view is not clipped by any of its parents, then
	 * return that area in r in global (root) coordinates.
	 * 
	 * @param rect
	 *            If true is returned, r holds the global coordinates of the
	 *            visible portion of this view.
	 * @param globalOffset
	 *            If true is returned, globalOffset holds the dx,dy between this
	 *            view and its root. globalOffet may be null.
	 * @return true if rect is non-empty (i.e. part of the view is visible at
	 *         the root level.)
	 */
	public boolean rect(Rect rect, Point globalOffset) {
		return view.getGlobalVisibleRect(rect, globalOffset);
	}

	/**
	 * Call this to force a view to update its drawable state.
	 * 
	 * @return self
	 */
	public self refresh() {
		view.refreshDrawableState();
		return (self) this;
	}

	/**
	 * Removes the specified Runnable from the message queue.
	 * 
	 * @param callbackAction
	 *            The Runnable to remove from the message handling queue
	 * @return true if this view could ask the Handler to remove the Runnable,
	 *         false otherwise. When the returned value is true, the Runnable
	 *         may or may not have been actually removed from the message queue
	 *         (for instance, if the Runnable was not in the queue already.)
	 */
	public boolean remove(Runnable callbackAction) {
		return view.removeCallbacks(callbackAction);
	}

	/**
	 * Remove a listener for attach state changes.
	 * 
	 * @param attachStateChangeListener
	 *            Listener to remove
	 * @return self
	 */
	public self remove(OnAttachStateChangeListener attachStateChangeListener) {
		view.removeOnAttachStateChangeListener(attachStateChangeListener);
		return (self) this;
	}

	/**
	 * Remove a listener for layout changes.
	 * 
	 * @param layoutChangeListener
	 *            The listener for layout bounds change.
	 * @return self
	 */
	public self remove(OnLayoutChangeListener layoutChangeListener) {
		view.removeOnLayoutChangeListener(layoutChangeListener);
		return (self) this;
	}

	/**
	 * Ask that a new dispatch of fitSystemWindows(Rect) be performed.
	 * 
	 * @return self
	 */
	public self reqFit() {
		view.requestFitSystemWindows();
		return (self) this;
	}

	/**
	 * Call this to try to give focus to a specific view or to one of its
	 * descendants.
	 * 
	 * @return Whether this view or one of its descendants actually took focus.
	 */
	public boolean reqFocus() {
		return view.requestFocus();
	}

	/**
	 * Call this to try to give focus to a specific view or to one of its
	 * descendants and give it a hint about what direction focus is heading.
	 * 
	 * @param direction
	 *            One of FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, and FOCUS_RIGHT
	 * @return Whether this view or one of its descendants actually took focus.
	 */
	public final boolean reqFocus(int direction) {
		return view.requestFocus(direction);
	}

	/**
	 * Call this to try to give focus to a specific view or to one of its
	 * descendants and give it hints about the direction and a specific
	 * rectangle that the focus is coming from.
	 * 
	 * @param direction
	 *            One of FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, and FOCUS_RIGHT
	 * @param previouslyFocusedRect
	 *            The rectangle (in this View's coordinate system) to give a
	 *            finer grained hint about where focus is coming from. May be
	 *            null if there is no hint.
	 * @return Whether this view or one of its descendants actually took focus.
	 */
	public final boolean reqFocus(int direction, Rect previouslyFocusedRect) {
		return view.requestFocus(direction, previouslyFocusedRect);
	}

	/**
	 * Call this to try to give focus to a specific view or to one of its
	 * descendants.
	 * 
	 * @return Whether this view or one of its descendants actually took focus.
	 */
	public final boolean reqTouchFocus() {
		return view.requestFocusFromTouch();
	}

	/**
	 * Call this when something has changed which has invalidated the layout of
	 * this view.
	 * 
	 * @return self
	 */
	public self reqLayout() {
		view.requestLayout();
		return (self) this;
	}

	/**
	 * Request that a rectangle of this view be visible on the screen, scrolling
	 * if necessary just enough.
	 * 
	 * @param rectangle
	 *            The rectangle.
	 * @return Whether any parent scrolled.
	 */
	public boolean reqRect(Rect rectangle) {
		return view.requestRectangleOnScreen(rectangle);
	}

	/**
	 * Request that a rectangle of this view be visible on the screen, scrolling
	 * if necessary just enough.
	 * 
	 * @param rectangle
	 *            The rectangle.
	 * @param immediate
	 *            True to forbid animated scrolling, false otherwise
	 * @return Whether any parent scrolled.
	 */
	public boolean reqRect(Rect rectangle, boolean immediate) {
		return view.requestRectangleOnScreen(rectangle, immediate);
	}

	/**
	 * Version of resolveSizeAndState(int, int, int) returning only the
	 * MEASURED_SIZE_MASK bits of the result.
	 * 
	 * @param size
	 *            How big the view wants to be
	 * @param measureSpec
	 *            Constraints imposed by the parent
	 * @return
	 */
	public static int resolve(int size, int measureSpec) {
		return View.resolveSize(size, measureSpec);
	}

	/**
	 * Utility to reconcile a desired size and state, with constraints imposed
	 * by a MeasureSpec.
	 * 
	 * @param size
	 *            How big the view wants to be
	 * @param measureSpec
	 *            Constraints imposed by the parent
	 * @param childMeasuredState
	 * @return Size information bit mask as defined by MEASURED_SIZE_MASK and
	 *         MEASURED_STATE_TOO_SMALL.
	 */
	public static int resolve(int size, int measureSpec, int childMeasuredState) {
		return View.resolveSizeAndState(size, measureSpec, childMeasuredState);
	}

	/**
	 * Restore this view hierarchy's frozen state from the given container.
	 * 
	 * @param frozenHierarchyContainer
	 *            The SparseArray which holds previously frozen states.
	 * @return self
	 */
	public self restore(SparseArray<Parcelable> frozenHierarchyContainer) {
		view.restoreHierarchyState(frozenHierarchyContainer);
		return (self) this;
	}

	/**
	 * Right position of this view relative to its parent.
	 * 
	 * @return The right edge of this view, in pixels.
	 */
	public final int right() {
		return view.getRight();
	}

	/**
	 * Sets the right position of this view relative to its parent.
	 * 
	 * @param right
	 *            The right of this view, in pixels.
	 * @return self
	 */
	public self right(int right) {
		view.setRight(right);
		return (self) this;
	}

	/**
	 * Finds the topmost view in the current view hierarchy.
	 * 
	 * @return the topmost view containing this view
	 */
	public View root() {
		return view.getRootView();
	}

	/**
	 * The degrees that the view is rotated around the pivot point.
	 * 
	 * @return The degrees of rotation.
	 */
	public float rot() {
		return view.getRotation();
	}

	/**
	 * Sets the degrees that the view is rotated around the pivot point.
	 * 
	 * @param rotation
	 *            The degrees of rotation.
	 * @return self
	 */
	public self rot(float rotation) {
		view.setRotation(rotation);
		return (self) this;
	}

	/**
	 * The degrees that the view is rotated around the horizontal axis through
	 * the pivot point.
	 * 
	 * @return The degrees of X rotation.
	 */
	public float rotX() {
		return view.getRotationX();
	}

	/**
	 * Sets the degrees that the view is rotated around the horizontal axis
	 * through the pivot point.
	 * 
	 * @param rotationX
	 *            The degrees of X rotation.
	 * @return self
	 */
	public self rotX(float rotationX) {
		view.setRotationX(rotationX);
		return (self) this;
	}

	/**
	 * The degrees that the view is rotated around the vertical axis through the
	 * pivot point.
	 * 
	 * @return The degrees of Y rotation.
	 */
	public float rotY() {
		return view.getRotationY();
	}

	/**
	 * Sets the degrees that the view is rotated around the vertical axis
	 * through the pivot point.
	 * 
	 * @param rotationY
	 *            The degrees of Y rotation.
	 * @return self
	 */
	public self rotY(float rotationY) {
		view.setRotationY(rotationY);
		return (self) this;
	}

	// VAPOR EQUIVALENT METHOD?
	/**
	 * Fluent equivalent Vapor method for invoking onRtlPropertiesChanged(int),
	 * called when any RTL property (layout direction or text direction or text
	 * alignment) has been changed.
	 * 
	 * @param layoutDirection
	 *            the direction of the layout
	 * @return self
	 */
	public self rtlPropChanged(int layoutDirection) { // API:17
		view.onRtlPropertiesChanged(layoutDirection);
		return (self) this;
	}

	/**
	 * Store this view hierarchy's frozen state into the given container.
	 * 
	 * @param frozenHierarchyContainer
	 *            The SparseArray in which to save the view's state.
	 * @return self
	 */
	public self save(SparseArray<Parcelable> frozenHierarchyContainer) {
		view.saveHierarchyState(frozenHierarchyContainer);
		return (self) this;
	}

	/**
	 * Indicates whether this view will save its state (that is, whether its
	 * save() method will be called).
	 * 
	 * @return Returns true if the view state saving is enabled, else false.
	 */
	public boolean saveable() {
		return view.isSaveEnabled();
	}

	/**
	 * Controls whether the saving of this view's state is enabled (that is,
	 * whether its onSaveInstanceState() method will be called).
	 * 
	 * @param enabled
	 *            Set to false to disable state saving, or true (the default) to
	 *            allow it.
	 * @return self
	 */
	public self saveable(boolean enabled) {
		view.setSaveEnabled(enabled);
		return (self) this;
	}

	/**
	 * The amount that the view is scaled in x around the pivot point, as a
	 * proportion of the view's unscaled width.
	 * 
	 * @return The scaling factor.
	 */
	public float scaleX() {
		return view.getScaleX();
	}

	/**
	 * Sets the amount that the view is scaled in x around the pivot point, as a
	 * proportion of the view's unscaled width.
	 * 
	 * @param scaleX
	 *            The scaling factor.
	 * @return self
	 */
	public self scaleX(float scaleX) {
		view.setScaleX(scaleX);
		return (self) this;
	}

	/**
	 * The amount that the view is scaled in y around the pivot point, as a
	 * proportion of the view's unscaled height.
	 * 
	 * @return The scaling factor.
	 */
	public float scaleY() {
		return view.getScaleY();
	}

	/**
	 * Sets the amount that the view is scaled in Y around the pivot point, as a
	 * proportion of the view's unscaled width.
	 * 
	 * @param scaleY
	 *            The scaling factor.
	 * @return self
	 */
	public self scaleY(float scaleY) {
		view.setScaleY(scaleY);
		return (self) this;
	}

	/**
	 * Equivalent fluent Vapor method for invoking
	 * scheduleDrawable(Drawable,Runnable,long), schedules an action on a
	 * drawable to occur at a specified time.
	 * 
	 * @param drawable
	 *            the recipient of the action
	 * @param runnable
	 *            the action to run on the drawable
	 * @param when
	 *            the time at which the action must occur. Uses the
	 * @return self
	 */
	public self sched(Drawable drawable, Runnable runnable, long when) {
		view.scheduleDrawable(drawable, runnable, when);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onScreenStateChanged(int),
	 * called whenever the state of the screen this view is attached to changes.
	 * 
	 * @param screenState
	 *            The new state of the screen. Can be either SCREEN_STATE_ON or
	 *            SCREEN_STATE_OFF
	 * @return self
	 */
	public self screenChanged(int screenState) {
		view.onScreenStateChanged(screenState);
		return (self) this;
	}

	/**
	 * Called by a parent to request that a child update its values for mScrollX
	 * and mScrollY if necessary.
	 * 
	 * @return self
	 */
	public self scroll() {
		view.computeScroll();
		return (self) this;
	}// Checked: 071220121223

	/**
	 * Indicates whether this view is one of the set of scrollable containers in
	 * its window.
	 * 
	 * @return whether this view is one of the set of scrollable containers in
	 *         its window
	 */
	public boolean scrollable() {
		return view.isScrollContainer();
	}

	/**
	 * Change whether this view is one of the set of scrollable containers in
	 * its window.
	 * 
	 * @param isScrollContainer
	 * @return self
	 */
	public self scrollable(boolean isScrollContainer) {
		view.setScrollContainer(isScrollContainer);
		return (self) this;
	}

	/**
	 * Move the scrolled position of your view. This will cause a call to
	 * onScrollChanged(int, int, int, int) and the view will be invalidated.
	 * 
	 * @param x
	 *            the amount of pixels to scroll by horizontally
	 * @param y
	 *            the amount of pixels to scroll by vertically
	 * @return self
	 */
	public self scrollBy(int x, int y) {
		view.scrollBy(x, y);
		return (self) this;
	}

	/**
	 * Returns the scrollbar fade duration.
	 * 
	 * @return the scrollbar fade duration
	 */
	public int scrollFade() {
		return view.getScrollBarFadeDuration();
	}

	/**
	 * Defines the scrollbar fade duration.
	 * 
	 * @param scrollBarFadeDuration
	 *            the scrollbar fade duration
	 * @return self
	 */
	public self scrollFade(int scrollBarFadeDuration) {
		view.setScrollBarFadeDuration(scrollBarFadeDuration);
		return (self) this;
	}

	/**
	 * Define whether scrollbars will fade when the view is not scrolling.
	 * 
	 * @param fadeScrollBars
	 *            wheter to enable fading
	 * @return self
	 */
	public self scrollsFade(boolean fadeScrollBars) {
		view.setScrollbarFadingEnabled(fadeScrollBars);
		return (self) this;
	}

	/**
	 * Returns the delay before scrollbars fade.
	 * 
	 * @return the delay before scrollbars fade
	 */
	public int scrollPreFade() {
		return view.getScrollBarDefaultDelayBeforeFade();
	}

	/**
	 * Define the delay before scrollbars fade.
	 * 
	 * @param scrollBarDefaultDelayBeforeFade
	 *            the delay before scrollbars fade
	 * @return self
	 */
	public self scrollPreFade(int scrollBarDefaultDelayBeforeFade) {
		view.setScrollBarDefaultDelayBeforeFade(scrollBarDefaultDelayBeforeFade);
		return (self) this;
	}

	/**
	 * Returns the scrollbar size.
	 * 
	 * @return the scrollbar size
	 */
	public int scrollSize() {
		return view.getScrollBarSize();
	}

	/**
	 * Define the scrollbar size.
	 * 
	 * @param scrollBarSize
	 *            the scrollbar size
	 * @return self
	 */
	public self scrollSize(int scrollBarSize) {
		view.setScrollBarSize(scrollBarSize);
		return (self) this;
	}

	/**
	 * Returns the current scrollbar style.
	 * 
	 * @return the current scrollbar style
	 */
	public int scrollStyle() {
		return view.getScrollBarStyle();
	}

	/**
	 * Specify the style of the scrollbars.
	 * 
	 * @param scrollBarStyle
	 *            the style of the scrollbars. Should be one of
	 *            SCROLLBARS_INSIDE_OVERLAY, SCROLLBARS_INSIDE_INSET,
	 *            SCROLLBARS_OUTSIDE_OVERLAY or SCROLLBARS_OUTSIDE_INSET.
	 * @return self
	 */
	public self scrollStyle(int scrollBarStyle) {
		view.setScrollBarStyle(scrollBarStyle);
		return (self) this;
	}

	/**
	 * Move the scrolled position of your view. This will cause a call to
	 * scrollChanged(int, int, int, int) and the view will be invalidated.
	 * 
	 * @param x
	 *            the amount of pixels to scroll by horizontally
	 * @param y
	 *            the amount of pixels to scroll by vertically
	 * @return self
	 */
	public self scrollTo(int x, int y) {
		view.scrollTo(x, y);
		return (self) this;
	}

	/**
	 * Return the scrolled left position of this view.
	 * 
	 * @return The left edge of the displayed part of your view, in pixels.
	 */
	public final int scrollX() {
		return view.getScrollX();
	}

	/**
	 * Set the horizontal scrolled position of your view.
	 * 
	 * @param scrollX
	 *            the x position to scroll to
	 * @return
	 */
	public self scrollX(int scrollX) {
		view.setScrollX(scrollX);
		return (self) this;
	}

	/**
	 * Return the scrolled top position of this view.
	 * 
	 * @return The top edge of the displayed part of your view, in pixels.
	 */
	public final int scrollY() {
		return view.getScrollY();
	}

	/**
	 * Set the vertical scrolled position of your view.
	 * 
	 * @param scrollY
	 *            the y position to scroll to
	 * @return self
	 */
	public self scrollY(int scrollY) {
		view.setScrollY(scrollY);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking
	 * onFilterTouchEventForSecurity(MotionEvent), used to filter the touch
	 * event to apply security policies.
	 * 
	 * @param motionEvent
	 *            The motion event to be filtered.
	 * @return True if the event should be dispatched, false if the event should
	 *         be dropped.
	 */
	public boolean secTouchFilter(MotionEvent motionEvent) {
		return view.onFilterTouchEventForSecurity(motionEvent);
	}

	/**
	 * Indicates the selection state of this view.
	 * 
	 * @return true if the view is selected, false otherwise
	 */
	public boolean selected() {
		return view.isSelected();
	}

	/**
	 * Changes the selection state of this view.
	 * 
	 * @param selected
	 *            true if the view must be selected, false otherwise
	 * @return self
	 */
	public self selected(boolean selected) {
		view.setSelected(selected);
		return (self) this;
	}

	/**
	 * Equivalent fluent Vapor method for invoking sendAccessibilityEvent(int),
	 * sends an accessibility event of the given type.
	 * 
	 * @param accessibilityEventType
	 *            The type of the event to send, as defined by several types
	 *            from AccessibilityEvent, such as TYPE_VIEW_CLICKED or
	 *            TYPE_VIEW_HOVER_ENTER.
	 * @return self
	 */
	public self sendAccess(int accessibilityEventType) {
		view.sendAccessibilityEvent(accessibilityEventType);
		return (self) this;
	}

	/**
	 * Equivalent fluent Vapor method for invoking
	 * sendAccessibilityEventUnchecked(AccessibilityEvent), this method behaves
	 * exactly as sendAccessibilityEvent(int) but takes as an argument an empty
	 * AccessibilityEvent and does not perform a check whether accessibility is
	 * enabled.
	 * 
	 * @param accessibilityEvent
	 * @return self
	 */
	public self sendUncheckedAccess(AccessibilityEvent accessibilityEvent) {
		view.sendAccessibilityEventUnchecked(accessibilityEvent);
		return (self) this;
	}

	/**
	 * Play a sound effect for this view.
	 * 
	 * @param soundConstant
	 *            One of the constants defined in SoundEffectConstants
	 * @return self
	 */
	public self sfx(int soundConstant) {
		view.playSoundEffect(soundConstant);
		return (self) this;
	}

	/**
	 * 
	 * @return whether this view should have sound effects enabled for events
	 *         such as clicking and touching.
	 */
	public boolean sfxable() {
		return view.isSoundEffectsEnabled();
	}

	/**
	 * Set whether this view should have sound effects enabled for events such
	 * as clicking and touching.
	 * 
	 * @param soundEffectsEnabled
	 *            whether sound effects are enabled for this view.
	 * @return self
	 */
	public self sfxable(boolean soundEffectsEnabled) {
		view.setSoundEffectsEnabled(soundEffectsEnabled);
		return (self) this;
	}

	/**
	 * Returns the visibility of this view and all of its ancestors
	 * 
	 * @return True if this view and all of its ancestors are VISIBLE
	 */
	public boolean showing() {
		return view.isShown();
	}

	/**
	 * Override this if your view is known to always be drawn on top of a solid
	 * color background, and needs to draw fading edges.
	 * 
	 * @return The known solid color background for this view, or 0 if the color
	 *         may vary
	 */
	public int solColor() { // NOT FINAL ON PURPOSE.
		return view.getSolidColor();
	}

	/**
	 * Start the specified animation now.
	 * 
	 * @param animation
	 *            the animation to start now
	 * @return self
	 */
	public self startAnim(Animation animation) {
		view.startAnimation(animation);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onStartTemporaryDetach(),
	 * called when a container is going to temporarily detach a child, with
	 * ViewGroup.detachViewFromParent.
	 * 
	 * @return self
	 */
	public self tempDetach() {
		view.onStartTemporaryDetach();
		return (self) this;
	}

	/**
	 * Add a listener for attach state changes.
	 * 
	 * @param attachStateListener
	 *            Listener to attach.
	 * @return self
	 */
	public self state(vapor.listeners.view.$state attachStateListener) {
		view.addOnAttachStateChangeListener(attachStateListener);
		return (self) this;
	}// Checked: 071220121139

	/**
	 * Returns the last sysViz(int) that this view has requested.
	 * 
	 * @return Bitwise-or of flags SYSTEM_UI_FLAG_LOW_PROFILE,
	 *         SYSTEM_UI_FLAG_HIDE_NAVIGATION, SYSTEM_UI_FLAG_FULLSCREEN,
	 *         SYSTEM_UI_FLAG_LAYOUT_STABLE,
	 *         SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION, and
	 *         SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN.
	 */
	public int sysViz() {
		return view.getSystemUiVisibility();
	}

	/**
	 * Request that the visibility of the status bar or other screen/window
	 * decorations be changed.
	 * 
	 * @param systemUiVisibility
	 *            Bitwise-or of flags SYSTEM_UI_FLAG_LOW_PROFILE,
	 *            SYSTEM_UI_FLAG_HIDE_NAVIGATION, SYSTEM_UI_FLAG_FULLSCREEN,
	 *            SYSTEM_UI_FLAG_LAYOUT_STABLE,
	 *            SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION, and
	 *            SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN.
	 * @return self
	 */
	public self sysViz(int systemUiVisibility) {
		view.setSystemUiVisibility(systemUiVisibility);
		return (self) this;
	}

	/**
	 * Set a listener to receive callbacks when the visibility of the system bar
	 * changes.
	 * 
	 * @param systemUiVisibilityChangeListener
	 *            The View.OnSystemUiVisibilityChangeListener to receive
	 *            callbacks.
	 * @return self
	 */
	public self sysViz(
			vapor.listeners.view.$change systemUiVisibilityChangeListener) {
		view.setOnSystemUiVisibilityChangeListener(systemUiVisibilityChangeListener);
		return (self) this;
	}

	/**
	 * Returns this view's tag.
	 * 
	 * @return the Object stored in this view as a tag
	 */
	public Object tag() {
		return view.getTag();
	}

	/**
	 * Returns the tag associated with this view and the specified key.
	 * 
	 * @param key
	 *            The key identifying the tag
	 * @return the Object stored in this view as a tag
	 */
	public Object tag(int key) {
		return view.getTag(key);
	}

	/**
	 * Sets the tag associated with this view.
	 * 
	 * @param tag
	 *            an Object to tag the view with
	 * @return self
	 */
	public self tag(Object tag) {
		view.setTag(tag);
		return (self) this;
	}

	/**
	 * Sets a tag associated with this view and a key.
	 * 
	 * @param key
	 *            The key identifying the tag
	 * @param tag
	 *            An Object to tag the view with
	 * @return self
	 */
	public self tag(int key, Object tag) {
		view.setTag(key, tag);
		return (self) this;
	}

	/**
	 * Return the resolved text alignment.
	 * 
	 * @return the resolved text alignment. Returns one of:
	 *         TEXT_ALIGNMENT_GRAVITY, TEXT_ALIGNMENT_CENTER,
	 *         TEXT_ALIGNMENT_TEXT_START, TEXT_ALIGNMENT_TEXT_END,
	 *         TEXT_ALIGNMENT_VIEW_START, TEXT_ALIGNMENT_VIEW_END
	 */
	public int textAlignment() { // API:17
		return view.getTextAlignment();
	}

	/**
	 * Set the text alignment.
	 * 
	 * @param textAlignment
	 *            The text alignment to set. Should be one of
	 *            TEXT_ALIGNMENT_INHERIT, TEXT_ALIGNMENT_GRAVITY,
	 *            TEXT_ALIGNMENT_CENTER, TEXT_ALIGNMENT_TEXT_START,
	 *            TEXT_ALIGNMENT_TEXT_END, TEXT_ALIGNMENT_VIEW_START,
	 *            TEXT_ALIGNMENT_VIEW_END Resolution will be done if the value
	 *            is set to TEXT_ALIGNMENT_INHERIT. The resolution proceeds up
	 *            the parent chain of the view to get the value. If there is no
	 *            parent, then it will return the default
	 *            TEXT_ALIGNMENT_GRAVITY.
	 * @return self
	 */
	public self textAlignment(int textAlignment) {
		view.setTextAlignment(textAlignment);
		return (self) this;
	}

	/**
	 * Return the resolved text direction.
	 * 
	 * @return the resolved text direction. Returns one of:
	 *         TEXT_DIRECTION_FIRST_STRONG TEXT_DIRECTION_ANY_RTL,
	 *         TEXT_DIRECTION_LTR, TEXT_DIRECTION_RTL, TEXT_DIRECTION_LOCALE
	 */
	public int textDirection() { // API:17
		return view.getTextDirection();
	}

	/**
	 * Set the text direction.
	 * 
	 * @param textDirection
	 *            the direction to set. Should be one of:
	 *            TEXT_DIRECTION_INHERIT, TEXT_DIRECTION_FIRST_STRONG
	 *            TEXT_DIRECTION_ANY_RTL, TEXT_DIRECTION_LTR,
	 *            TEXT_DIRECTION_RTL, TEXT_DIRECTION_LOCALE Resolution will be
	 *            done if the value is set to TEXT_DIRECTION_INHERIT. The
	 *            resolution proceeds up the parent chain of the view to get the
	 *            value. If there is no parent, then it will return the default
	 *            TEXT_DIRECTION_FIRST_STRONG.
	 * @return self
	 */
	public self textDirection(int textDirection) {
		view.setTextDirection(textDirection);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onCheckIsTextEditor(), used
	 * to check whether the called view is a text editor, in which case it would
	 * make sense to automatically display a soft input window for it.
	 * 
	 * @return Returns true if this view is a text editor, else false.
	 */
	public boolean textEditor() {
		return view.onCheckIsTextEditor();
	}

	/**
	 * Retrieve a unique token identifying the window that this view is attached
	 * to.
	 * 
	 * @param applicationWindowToken
	 *            true returns the top-level "real" window of the window that
	 *            this view is attached to.
	 * @return Returns the associated window token, either getWindowToken() or
	 *         the containing window's token.
	 */
	public IBinder token(boolean applicationWindowToken) {
		return applicationWindowToken ? view.getApplicationWindowToken() : view
				.getWindowToken();

	}

	/**
	 * Top position of this view relative to its parent.
	 * 
	 * @return The top of this view, in pixels.
	 */
	public final int top() {
		return view.getTop();
	}

	/**
	 * Sets the top position of this view relative to its parent.
	 * 
	 * @param top
	 *            The top of this view, in pixels.
	 * @return self
	 */
	public self top(int top) {
		view.setTop(top);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onTouchEvent(MotionEvent),
	 * used to handle touch screen motion events.
	 * 
	 * @param touchEvent
	 *            The motion event.
	 * @return True if the event was handled, false otherwise.
	 */
	public boolean touch(MotionEvent touchEvent) {
		return view.onTouchEvent(touchEvent);
	}

	/**
	 * Register a callback to be invoked when a touch event is sent to this
	 * view.
	 * 
	 * @param touchListener
	 *            the touch listener to attach to this view
	 * @return self
	 */
	public self touch(vapor.listeners.view.$touch touchListener) {
		view.setOnTouchListener(touchListener);
		return (self) this;
	}

	/**
	 * Find and return all touchable views that are descendants of this view,
	 * possibly including this view if it is touchable itself.
	 * 
	 * @return A list of touchable views
	 */
	public ArrayList<VaporView<? extends View, ?>> touchables() {
		return $.vapor(view.getTouchables());
	}

	/**
	 * Add any touchable views that are descendants of this view (possibly
	 * including this view if it is touchable itself) to views.
	 * 
	 * @param views
	 *            Touchable views found so far
	 * @return self
	 */
	public self touchables(ArrayList<VaporView<? extends View, ?>> views) {
		view.addTouchables($.android(views));
		return (self) this;
	}// Checked: 071220121143

	/**
	 * Returns whether the device is currently in touch mode.
	 * 
	 * @return Whether the device is in touch mode.
	 */
	public boolean touching() {
		return view.isInTouchMode();
	}

	/**
	 * Gets the TouchDelegate for this View.
	 * 
	 * @return the TouchDelegate for this View
	 */
	public TouchDelegate touchDelegate() {
		return view.getTouchDelegate();
	}

	/**
	 * Sets the TouchDelegate for this View.
	 * 
	 * @param touchDelegate
	 * @return self
	 */
	public self delegate(TouchDelegate touchDelegate) {
		view.setTouchDelegate(touchDelegate);
		return (self) this;
	}

	/**
	 * Sets a delegate for implementing accessibility support via compositon as
	 * opposed to inheritance.
	 * 
	 * @param accessibilityDelegate
	 *            The delegate instance.
	 * @return self
	 */
	public self delegate(AccessibilityDelegate accessibilityDelegate) {
		view.setAccessibilityDelegate(accessibilityDelegate);
		return (self) this;
	}

	/**
	 * When a view is focusable, it may not want to take focus when in touch
	 * mode.
	 * 
	 * @return Whether the view is focusable in touch mode.
	 */
	public boolean touchFocusable() {
		return view.isFocusableInTouchMode();
	}

	/**
	 * Set whether this view can receive focus while in touch mode.
	 * 
	 * @param focusableInTouchMode
	 *            If true, this view can receive the focus while in touch mode.
	 * @returnn this
	 */
	public self touchFocusable(boolean focusableInTouchMode) {
		view.setFocusableInTouchMode(focusableInTouchMode);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking
	 * onTrackballEvent(MotionEvent), used to handle trackball motion events.
	 * 
	 * @param trackballEvent
	 *            The motion event.
	 * @return True if the event was handled, false otherwise.
	 */
	public boolean trackball(MotionEvent trackballEvent) {
		return view.onTrackballEvent(trackballEvent);
	}

	/**
	 * Indicates whether the view is currently tracking transient state that the
	 * app should not need to concern itself with saving and restoring, but that
	 * the framework should take special note to preserve when possible.
	 * 
	 * @return true if the view has transient state
	 */
	public boolean transientState() {
		return view.hasTransientState();
	}

	/**
	 * Set whether this view is currently tracking transient state that the
	 * framework should attempt to preserve when possible.
	 * 
	 * @param hasTransientState
	 *            true if this view has transient state
	 * @return self
	 */
	public self transientState(boolean hasTransientState) {
		view.setHasTransientState(hasTransientState);
		return (self) this;
	}

	/**
	 * The horizontal location of this view relative to its left position.
	 * 
	 * @return The horizontal position of this view relative to its left
	 *         position, in pixels.
	 */
	public float transX() {
		return view.getTranslationX();
	}

	/**
	 * Sets the horizontal location of this view relative to its left position.
	 * 
	 * @param translationX
	 *            The horizontal position of this view relative to its left
	 *            position, in pixels.
	 * @return self
	 */
	public self transX(float translationX) {
		view.setTranslationX(translationX);
		return (self) this;
	}

	/**
	 * The horizontal location of this view relative to its top position.
	 * 
	 * @return The vertical position of this view relative to its top position,
	 *         in pixels.
	 */
	public float transY() {
		return view.getTranslationY();
	}

	/**
	 * Sets the vertical location of this view relative to its top position.
	 * 
	 * @param translationY
	 *            The vertical position of this view relative to its top
	 *            position, in pixels.
	 * @return self
	 */
	public self transY(float translationY) {
		view.setTranslationY(translationY);
		return (self) this;
	}

	/**
	 * Returns the ViewTreeObserver for this view's hierarchy.
	 * 
	 * @return The ViewTreeObserver for this view's hierarchy.
	 */
	public ViewTreeObserver treeObserver() {
		return view.getViewTreeObserver();
	}

	/**
	 * Equivalent fluent Vapor method for invoking unscheduleDrawable(Drawable),
	 * called to unschedule any events associated with the given Drawable.
	 * 
	 * @param drawable
	 *            The Drawable to unschedule.
	 * @return self
	 */
	public self unsched(Drawable drawable) {
		view.unscheduleDrawable(drawable);
		return (self) this;
	}

	/**
	 * Equivalent fluent Vapor method for invoking unscheduleDrawable(Drawable),
	 * called to cancel a scheduled action on a drawable.
	 * 
	 * @param drawable
	 *            the recipient of the action
	 * @param runnable
	 *            the action to cancel
	 * @return self
	 */
	public self unsched(Drawable drawable, Runnable runnable) {
		view.unscheduleDrawable(drawable, runnable);
		return (self) this;
	}

	/**
	 * Returns the width of the vertical scrollbar.
	 * 
	 * @return The width in pixels of the vertical scrollbar or 0 if there is no
	 *         vertical scrollbar.
	 */
	public int yScrollWidth() { 
		return view.getVerticalScrollbarWidth();
	}

	/**
	 * Indicate whether the vertical edges are faded when the view is scrolled
	 * horizontally.
	 * 
	 * @return true if the vertical edges should are faded on scroll, false
	 *         otherwise
	 */
	public boolean fadesYEdges() {
		return view.isVerticalFadingEdgeEnabled(); 
	}

	/**
	 * Define whether the vertical edges should be faded when this view is
	 * scrolled vertically.
	 * 
	 * @param verticalFadingEdgeEnabled
	 *            true if the vertical edges should be faded when the view is
	 *            scrolled vertically
	 * @return self
	 */
	public self fadesYEdges(boolean verticalFadingEdgeEnabled) {
		view.setVerticalFadingEdgeEnabled(verticalFadingEdgeEnabled);
		return (self) this;
	}

	/**
	 * Returns the size of the vertical faded edges used to indicate that more
	 * content in this view is visible.
	 * 
	 * @return The size in pixels of the vertical faded edge or 0 if vertical
	 *         faded edges are not enabled for this view.
	 */
	public int yFadeEdge() {
		return view.getVerticalFadingEdgeLength(); 
	}

	/**
	 * Return the underlying standard Android View
	 * 
	 * @return the underlying standard Android View
	 */
	public T view() {
		return view;
	}

	/**
	 * Returns the visibility status for this view.
	 * 
	 * @return One of VISIBLE, INVISIBLE, or GONE.
	 */
	public int viz() {
		return view.getVisibility();
	}

	/**
	 * Set the enabled state of this view.
	 * 
	 * @param visibility
	 *            One of VISIBLE, INVISIBLE, or GONE.
	 * @return self
	 */
	public self viz(int visibility) {
		view.setVisibility(visibility);
		return (self) this;
	}

	/**
	 * Indicate whether the vertical scrollbar should be drawn or not. The
	 * scrollbar is not drawn by default.
	 * 
	 * @return true if the vertical scrollbar should be painted, false otherwise
	 */
	public boolean yScrolls() {
		return view.isVerticalScrollBarEnabled(); 
	}

	/**
	 * Define whether the vertical scrollbar should be drawn or not.
	 * 
	 * @param verticalScrollBarEnabled
	 *            true if the vertical scrollbar should be painted
	 * @return self
	 */
	public self yScrolls(boolean verticalScrollBarEnabled) {
		view.setVerticalScrollBarEnabled(verticalScrollBarEnabled);
		return (self) this;
	}

	/**
	 * Returns the vertical scrollbar position
	 * 
	 * @return The position where the vertical scroll bar will show, if
	 *         applicable.
	 */
	public int yScrollPos() {
		return view.getVerticalScrollbarPosition();
	}

	/**
	 * Set the position of the vertical scroll bar.
	 * 
	 * @param position
	 *            Where the vertical scroll bar should be positioned.
	 * @return self
	 */
	public self yScrollPos(int position) {
		view.setVerticalScrollbarPosition(position);
		return (self) this;
	}

	/**
	 * return the width of the your view.
	 * 
	 * @return The width of your view, in pixels.
	 */
	public final int width() {
		return view.getWidth();
	}

	/**
	 * Returns true if this view is in a window that currently has window focus.
	 * 
	 * @return True if this view is in a window that currently has window focus.
	 */
	public boolean winFocused() {
		return view.hasWindowFocus();
	}

	/**
	 * Fluent equivalent Vapor method for invoking
	 * onWindowFocusChanged(boolean), called when the window containing this
	 * view gains or loses focus. Note that this is separate from view focus: to
	 * receive key events, both your view and its window must have focus.
	 * 
	 * @param hasWindowFocus
	 *            True if the window containing this view now has focus, false
	 *            otherwise.
	 * @return self
	 */
	public self winFocusChanged(boolean hasWindowFocus) {
		view.onWindowFocusChanged(hasWindowFocus);
		return (self) this;
	}

	/**
	 * Returns the current system UI visibility that is currently set for the
	 * entire window.
	 * 
	 * @return
	 */
	public int winSysViz() {
		return view.getWindowSystemUiVisibility();
	}

	/**
	 * Fluent equivalent Vapor method for invoking
	 * onWindowSystemUiVisibilityChanged(int)
	 * 
	 * @param visible
	 * @return
	 */
	public self winSysVizChanged(int visible) {
		view.onWindowSystemUiVisibilityChanged(visible);
		return (self) this;
	}

	/**
	 * Returns the current visibility of the window this view is attached to
	 * (either GONE, INVISIBLE, or VISIBLE).
	 * 
	 * @return Returns the current visibility of the view's window.
	 */
	public int winViz() {
		return view.getWindowVisibility();
	}

	/**
	 * Retrieve the overall visible display size in which the window this view
	 * is attached to has been positioned in.
	 * 
	 * @param outRect
	 *            Filled in with the visible display frame. If the view is not
	 *            attached to a window, this is simply the raw display size.
	 * @return self
	 */
	public self winVizFrame(Rect outRect) {
		view.getWindowVisibleDisplayFrame(outRect);
		return (self) this;
	}

	/**
	 * The visual x position of this view, in pixels.
	 * 
	 * @return The visual x position of this view, in pixels.
	 */
	public float x() {
		return view.getX();
	}

	/**
	 * Sets the visual x position of this view, in pixels.
	 * 
	 * @param x
	 *            The visual x position of this view, in pixels.
	 * @return self
	 */
	public self x(float x) {
		view.setX(x);
		return (self) this;
	}

	/**
	 * The visual y position of this view, in pixels.
	 * 
	 * @return The visual y position of this view, in pixels.
	 */
	public float y() {
		return view.getY();
	}

	/**
	 * Sets the visual y position of this view, in pixels.
	 * 
	 * @param y
	 *            The visual y position of this view, in pixels.
	 * @return self
	 */
	public self y(float y) {
		view.setY(y);
		return (self) this;
	}

	/**
	 * Gets the distance along the Z axis from the camera to this view.
	 * 
	 * @return The distance along the Z axis.
	 */
	public float z() {
		return view.getCameraDistance();
	}

	/**
	 * Sets the distance along the Z axis (orthogonal to the X/Y plane on which
	 * views are drawn) from the camera to this view.
	 * 
	 * @param z
	 *            The distance in "depth pixels", if negative the opposite value
	 *            is used
	 * @return self
	 */
	public self z(float z) {
		view.setCameraDistance(z);
		return (self) this;
	}

	/* INTERFACE : AccessibilityEventSource */
	/**
	 * NOTE: This method serves to satisfy the AccessibilityEventSource
	 * interface, use the equivalent VAPOR FLUENT method sendAccess(int) instead
	 */
	@Override
	public void sendAccessibilityEvent(int accessibilityEventType) {
		sendAccess(accessibilityEventType);
	}

	/**
	 * NOTE: This method serves to satisfy the AccessibilityEventSource
	 * interface, use the equivalent VAPOR FLUENT method
	 * sendUncheckedAccess(AccessibilityEvent) instead
	 */
	@Override
	public void sendAccessibilityEventUnchecked(
			AccessibilityEvent accessibilityEvent) {
		sendUncheckedAccess(accessibilityEvent);

	}

	/* INTERFACE : KeyEvent.CallBack */
	/**
	 * NOTE: This method serves to satisfy the KeyEvent.CallBack interface, use
	 * the equivalent VAPOR FLUENT method keyDown(KeyEvent) instead
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
		return keyDown(keyEvent);
	}

	/**
	 * NOTE: This method serves to satisfy the KeyEvent.CallBack interface, use
	 * the equivalent VAPOR FLUENT method keyLong(KeyEvent) instead
	 */
	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent keyEvent) {
		return keyLongPress(keyEvent);
	}

	/**
	 * NOTE: This method serves to satisfy the KeyEvent.CallBack interface, use
	 * the equivalent VAPOR FLUENT method keyMulti(int,KeyEvent) instead
	 */
	@Override
	public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent keyEvent) {
		return keyMulti(repeatCount, keyEvent);
	}

	/**
	 * NOTE: This method serves to satisfy the KeyEvent.CallBack interface, use
	 * the equivalent VAPOR FLUENT method keyUp(KeyEvent) instead
	 */
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent keyEvent) {
		return keyUp(keyEvent);
	}

	/* INTERFACE : Drawable.CallBack */
	/**
	 * NOTE: This method serves to satisfy the Drawable.CallBack interface, use
	 * the equivalent VAPOR FLUENT method invalidate(Drawable) instead
	 */
	@Override
	public void invalidateDrawable(Drawable drawable) {
		invalidate(drawable);
	}

	/**
	 * NOTE: This method serves to satisfy the Drawable.CallBack interface, use
	 * the equivalent VAPOR FLUENT method sched(Drawable,Runnable,long) instead
	 */
	@Override
	public void scheduleDrawable(Drawable drawable, Runnable runnable, long when) {
		sched(drawable, runnable, when);
	}

	/**
	 * NOTE: This method serves to satisfy the Drawable.CallBack interface, use
	 * the equivalent VAPOR FLUENT method unsched(Drawable,Runnable) instead
	 */
	@Override
	public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
		unsched(drawable, runnable);
	}
}
