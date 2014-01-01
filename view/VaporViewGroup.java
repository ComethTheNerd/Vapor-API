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

import vapor.core.$;
import android.animation.LayoutTransition;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewManager;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;

/**
 * Fluent Vapor companion to ViewGroup, a special view that can contain other
 * views (called children.) The view group is the base class for layouts and
 * views containers. This class also defines the ViewGroup.LayoutParams class
 * which serves as the base class for layouts parameters.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from ViewGroup
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public abstract class VaporViewGroup<T extends ViewGroup, self extends VaporViewGroup<T, self>>
		extends VaporView<T, self> implements ViewManager, ViewParent {

	public VaporViewGroup(int id) {
		super(id);
	}

	public VaporViewGroup(T viewGroup) {
		super(viewGroup);
	}

	/**
	 * Append a child to this ViewGroup
	 * 
	 * @param child
	 *            the child to append to this ViewGroup
	 * @return self
	 */
	public self append(VaporView<? extends View, ?> child) {
		child(child);
		return (self) this;
	}

	/**
	 * Append a child to this ViewGroup using the given LayoutParams
	 * 
	 * @param child
	 *            the child to append to this ViewGroup
	 * @param layoutParams
	 *            the layout params to apply to the new child
	 * @return self
	 */
	public self append(VaporView<? extends View, ?> child,
			LayoutParams layoutParams) {
		child(child, layoutParams);
		return (self) this;
	}

	/**
	 * Prepend a child to this ViewGroup, before all existing children
	 * 
	 * @param child
	 *            the child to prepend to this ViewGroup
	 * @return self
	 */
	public self prepend(VaporView<? extends View, ?> child) {
		child(child, 0);
		return (self) this;
	}

	/**
	 * Prepend a child to this ViewGroup using the given LayoutParams, before
	 * all existing children
	 * 
	 * @param child
	 *            the child to prepend to this ViewGroup
	 * @param layoutParams
	 *            the layout params to apply to the new child
	 * @return self
	 */
	public self prepend(VaporView<? extends View, ?> child,
			LayoutParams layoutParams) {
		child(child, 0, layoutParams);
		return (self) this;
	}

	/**
	 * Generates the layout params that a child of this ViewGroup will require
	 * in order to set its size arbitrarily
	 * 
	 * @param width
	 *            the new width of the child
	 * @param height
	 *            the new height of the child
	 * @return layout params that represent the requested size
	 */
	public ViewGroup.LayoutParams layoutParams(int width, int height) {
		return new ViewGroup.LayoutParams(width, height);
	}

	/**
	 * Start an action mode for the specified view.
	 * 
	 * @param originalView
	 *            The source view where the action mode was first invoked
	 * @param actionModeCallback
	 *            The callback that will handle lifecycle events for the action
	 *            mode
	 * @return self
	 */
	public ActionMode action(VaporView<? extends View, ?> originalView,
			ActionMode.Callback actionModeCallback) {
		return view.startActionModeForChild(originalView.view(),
				actionModeCallback);
	}

	/**
	 * Indicates whether this ViewGroup will always try to draw its children
	 * using their drawing cache. By default this property is enabled.
	 * 
	 * @return true if the animation cache is enabled, false otherwise
	 */
	public boolean alwaysDrawCached() {
		return view.isAlwaysDrawnWithCacheEnabled();
	}

	/**
	 * Indicates whether this ViewGroup will always try to draw its children
	 * using their drawing cache. This property can be set to true when the
	 * cache rendering is slightly different from the children's normal
	 * rendering. Renderings can be different, for instance, when the cache's
	 * quality is set to low. When this property is disabled, the ViewGroup will
	 * use the drawing cache of its children only when asked to. It's usually the
	 * task of subclasses to tell ViewGroup when to start using the drawing cache
	 * and when to stop using it.
	 * 
	 * @param alwaysDrawnWithCache
	 *            true to always draw with the drawing cache, false otherwise
	 * @return self
	 */
	public self alwaysDrawCached(boolean alwaysDrawnWithCache) {
		view.setAlwaysDrawnWithCacheEnabled(alwaysDrawnWithCache);
		return (self) this;
	}

	/**
	 * Indicates whether the children's drawing cache is used during a layout
	 * animation. By default, the drawing cache is enabled but this will prevent
	 * nested layout animations from working. To nest animations, you must
	 * disable the cache.
	 * 
	 * @return true if the animation cache is enabled, false otherwise
	 */
	public boolean animCached() {
		return view.isAnimationCacheEnabled();
	}

	/**
	 * Enables or disables the children's drawing cache during a layout
	 * animation. By default, the drawing cache is enabled but this will prevent
	 * nested layout animations from working. To nest animations, you must
	 * disable the cache.
	 * 
	 * @param animationCache
	 *            true to enable the animation cache, false otherwise
	 * @return self
	 */
	public self animCached(boolean animationCache) {
		view.setAnimationCacheEnabled(animationCache);
		return (self) this;
	}

	/**
	 * Returns the view at the specified position in the group.
	 * 
	 * @param index
	 *            the position at which to get the view from
	 * @return the view at the specified position or null if the position does
	 *         not exist within the group
	 */
	public VaporView<? extends View, ?> child(int index) {
		return $.vapor(view.getChildAt(index));
	}

	/**
	 * Adds a child view. If no layout parameters are already set on the child,
	 * the default parameters for this ViewGroup are set on the child.
	 * 
	 * @param child
	 *            the child view to add
	 * @return self
	 */
	public self child(VaporView<? extends View, ?> child) {
		view.addView(child.view());
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
			LayoutParams layoutParams) {
		view.addView(child.view(), index, layoutParams);
		return (self) this;
	}

	/**
	 * Adds a child view with the specified layout parameters.
	 * 
	 * @param child
	 *            the child view to add
	 * @param layoutParams
	 *            the layout parameters to set on the child
	 * @return self
	 */
	public self child(VaporView<? extends View, ?> child,
			LayoutParams layoutParams) {
		view.addView(child.view(), layoutParams);
		return (self) this;
	}

	/**
	 * Adds a child view. If no layout parameters are already set on the child,
	 * the default parameters for this ViewGroup are set on the child.
	 * 
	 * @param child
	 *            the child view to add
	 * @param index
	 *            the position at which to add the child
	 * @return self
	 */
	public self child(VaporView<? extends View, ?> child, int index) {
		view.addView(child.view(), index);
		return (self) this;
	}

	/**
	 * Adds a child view with this ViewGroup's default layout parameters and the
	 * specified width and height.
	 * 
	 * @param child
	 *            the child view to add
	 * @param width
	 * @param height
	 * @return self
	 */
	public self child(VaporView<? extends View, ?> child, int width, int height) {
		view.addView(child.view(), width, height);
		return (self) this;
	}

	/**
	 * Returns the number of children in the group.
	 * 
	 * @return a positive integer representing the number of children in the
	 *         group
	 */
	public int childCount() {
		return view.getChildCount();
	}

	/**
	 * If addStatesFromChildren() is true, refreshes this group's drawable state
	 * (to include the states from its children).
	 * 
	 * @param child
	 *            The child whose drawable state has changed.
	 * @return self
	 */
	public self childDrawStateChanged(VaporView<? extends View, ?> child) {
		view.childDrawableStateChanged(child.view());
		return (self) this;
	}

	/**
	 * Returns the position in the group of the specified child view.
	 * 
	 * @param child
	 *            the view for which to get the position
	 * @return a positive integer representing the position of the view in the
	 *         group, or -1 if the view does not exist in the group
	 */
	public int childIndex(VaporView<? extends View, ?> child) {
		return view.indexOfChild(child.view());
	}

	/**
	 * Does the hard part of measureChildren: figuring out the MeasureSpec to
	 * pass to a particular child.
	 * 
	 * @param spec
	 *            The requirements for this view
	 * @param padding
	 *            The padding of this view for the current dimension and
	 *            margins, if applicable
	 * @param childDimension
	 *            How big the child wants to be in the current dimension
	 * @return a MeasureSpec integer for the child
	 */
	public static int childMeasure(int spec, int padding, int childDimension) {
		return ViewGroup.getChildMeasureSpec(spec, padding, childDimension);
	}

	/**
	 * Return true if the pressed state should be delayed for children or
	 * descendants of this ViewGroup. Generally, this should be done for
	 * containers that can scroll, such as a List. This prevents the pressed
	 * state from appearing when the user is actually trying to scroll the
	 * content. The default implementation returns true for compatibility
	 * reasons. Subclasses that do not scroll should generally override this
	 * method and return false.
	 * 
	 * @return
	 */
	public boolean childPressedDelay() {
		return view.shouldDelayChildPressedState();
	}

	/**
	 * Compute the visible part of a rectangular region defined in terms of a
	 * child view's coordinates.
	 * 
	 * @param child
	 *            A child View, whose rectangular visible region we want to
	 *            compute
	 * @param rect
	 *            The input rectangle, defined in the child coordinate system.
	 *            Will be overwritten to contain the resulting visible
	 *            rectangle, expressed in global (root) coordinates
	 * @param offset
	 *            The input coordinates of a point, defined in the child
	 *            coordinate system. As with the r parameter, this will be
	 *            overwritten to contain the global (root) coordinates of that
	 *            point. A null value is valid (in case you are not interested
	 *            in this result)
	 * @return true if the resulting rectangle is not empty, false otherwise
	 */
	public boolean childRect(VaporView<? extends View, ?> child, Rect rect,
			Point offset) {
		return view.getChildVisibleRect(child.view(), rect, offset);
	}

	/**
	 * Returns whether this ViewGroup's drawable states also include its
	 * children's drawable states.
	 * 
	 * @return
	 */
	public boolean childStates() {
		return view.addStatesFromChildren();
	}

	/**
	 * Sets whether this ViewGroup's drawable states also include its children's
	 * drawable states. This is used, for example, to make a group appear to be
	 * focused when its child EditText or button is focused.
	 * 
	 * @param addStatesFromChildren
	 * @return self
	 */
	public self childStates(boolean addStatesFromChildren) {
		view.setAddStatesFromChildren(addStatesFromChildren);
		return (self) this;
	}

	/**
	 * Removes any pending animations for views that have been removed. Call
	 * this if you don't want animations for exiting views to stack up.
	 * 
	 * @return self
	 */
	public self clearDisappearing() {
		view.clearDisappearingChildren();
		return (self) this;
	}

	/**
	 * Called when this view wants to give up focus. If focus is cleared
	 * onFocusChanged(boolean, int, android.graphics.Rect) is called.
	 * 
	 * @return self;
	 * @Override public VaporViewGroup clearFocus(){
	 *           ((ViewGroup)view).clearFocus(); return this; }
	 */
	/**
	 * Called when a child of this parent is giving up focus
	 * 
	 * @param child
	 *            The view that is giving up focus
	 * @return self
	 */
	public self clearFocus(VaporView<? extends View, ?> child) {
		view.clearChildFocus(child.view());
		return (self) this;
	}

	/**
	 * By default, children are clipped to their bounds before drawing. This
	 * allows view groups to override this behavior for animations, etc.
	 * 
	 * @param clipChildren
	 *            true to clip children to their bounds, false otherwise
	 * @return self
	 */
	public self clipChildren(boolean clipChildren) {
		view.setClipChildren(clipChildren);
		return (self) this;
	}

	/**
	 * By default, children are clipped to the padding of the ViewGroup. This
	 * allows view groups to override this behavior
	 * 
	 * @param clipToPadding
	 *            true to clip children to the padding of the group, false
	 *            otherwise
	 * @return self
	 */
	public self clipPad(boolean clipToPadding) {
		view.setClipToPadding(clipToPadding);
		return (self) this;
	}

	/**
	 * Gets the descendant focusability of this view group.
	 * 
	 * @return one of FOCUS_BEFORE_DESCENDANTS, FOCUS_AFTER_DESCENDANTS,
	 *         FOCUS_BLOCK_DESCENDANTS.
	 */
	public int descFocusability() {
		return view.getDescendantFocusability();
	}

	/**
	 * Set the descendant focusability of this view group. This defines the
	 * relationship between this view group and its descendants when looking for
	 * a view to take focus in requestFocus(int, android.graphics.Rect).
	 * 
	 * @param descendantFocusability
	 *            one of FOCUS_BEFORE_DESCENDANTS, FOCUS_AFTER_DESCENDANTS,
	 *            FOCUS_BLOCK_DESCENDANTS.
	 * @return self
	 */
	public self descFocusability(int descendantFocusability) {
		view.setDescendantFocusability(descendantFocusability);
		return (self) this;
	}

	/**
	 * Dispatch activated to all of this View's children.
	 * 
	 * @param activated
	 *            The new activated state
	 * @return self
	 */
	public self dispatchActivated(boolean activated) {
		view.dispatchSetActivated(activated);
		return (self) this;
	}

	/**
	 * Dispatch selected to all of this View's children.
	 * 
	 * @param selected
	 *            The new selected state
	 * @return self
	 */
	public self dispatchSelected(boolean selected) {
		view.dispatchSetSelected(selected);
		return (self) this;
	}

	/**
	 * Returns an integer indicating what types of drawing caches are kept in
	 * memory.
	 * 
	 * @return one or a combination of PERSISTENT_NO_CACHE,
	 *         PERSISTENT_ANIMATION_CACHE, PERSISTENT_SCROLLING_CACHE and
	 *         PERSISTENT_ALL_CACHES
	 */
	public int persistDrawCache() {
		return view.getPersistentDrawingCache();
	}

	/**
	 * Indicates what types of drawing caches should be kept in memory after
	 * they have been created.
	 * 
	 * @boolean drawingCacheToKeep one or a combination of PERSISTENT_NO_CACHE,
	 *          PERSISTENT_ANIMATION_CACHE, PERSISTENT_SCROLLING_CACHE and
	 *          PERSISTENT_ALL_CACHES
	 * @return self
	 */
	public self persistDrawCache(int drawingCacheToKeep) {
		view.setPersistentDrawingCache(drawingCacheToKeep);
		return (self) this;
	}

	/**
	 * This method should always be called following an earlier call to
	 * startViewTransition(View).
	 * 
	 * @param view
	 *            The View object that has been removed but is being kept around
	 *            in the visible hierarchy by an earlier call to
	 *            startViewTransition(View).
	 * @return self
	 */
	public self endTransit(VaporView<? extends View, ?> view) {
		this.view.endViewTransition(view.view());
		return (self) this;
	}

	/**
	 * Find the nearest view in the specified direction that wants to take
	 * focus.
	 * 
	 * @param view
	 *            The view that currently has focus
	 * @param direction
	 *            One of FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, and FOCUS_RIGHT, or 0
	 *            for not applicable.
	 * @return
	 */
	public VaporView<? extends View, ?> focus(
			VaporView<? extends View, ?> view, int direction) {
		return $.vapor(this.view.focusSearch(view.view(), direction));
	}

	/**
	 * Returns the focused child of this view, if any. The child may have focus
	 * or contain focus.
	 * 
	 * @return the focused child or null.
	 */
	public VaporView<? extends View, ?> focusedChild() {
		return $.vapor(view.getFocusedChild());
	}

	/**
	 * Tells the parent that a new focusable view has become available. This is
	 * to handle transitions from the case where there are no focusable views to
	 * the case where the first focusable view appears.
	 * 
	 * @param view
	 *            The view that has become newly focusable
	 * @return self
	 */
	public self focusableAvailable(VaporView<? extends View, ?> view) {
		this.view.focusableViewAvailable(view.view());
		return (self) this;
	}

	/**
	 * Change the z order of the child so it's on top of all other children
	 * 
	 * @param child
	 * @return self
	 */
	public self front(VaporView<? extends View, ?> child) {
		view.bringChildToFront(child.view());
		return (self) this;
	}

	/**
	 * Register a callback to be invoked when a child is added to or removed
	 * from this view.
	 * 
	 * @param hierarchyListener
	 *            the callback to invoke on hierarchy change
	 * @return self
	 */
	public self hierarchy(
			vapor.listeners.view.viewgroup.$hierarchy hierarchyListener) {
		view.setOnHierarchyChangeListener(hierarchyListener);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking
	 * onInterceptHoverEvent(HoverEvent), used to intercept hover events before
	 * they are handled by child views.
	 * 
	 * This method is called before dispatching a hover event to a child of the
	 * view group or to the view group's own onHoverEvent(MotionEvent) to allow
	 * the view group a chance to intercept the hover event. This method can
	 * also be used to watch all pointer motions that occur within the bounds of
	 * the view group even when the pointer is hovering over a child of the view
	 * group rather than over the view group itself.
	 * 
	 * @param hoverEvent
	 *            The motion event that describes the hover.
	 * @return True if the view group would like to intercept the hover event
	 *         and prevent its children from receiving it.
	 */
	public boolean hoverIntercept(MotionEvent hoverEvent) {
		return view.onInterceptHoverEvent(hoverEvent);
	}

	/**
	 * Returns a new set of layout parameters based on the supplied attributes
	 * set.
	 * 
	 * @param attrs
	 *            the attributes to build the layout parameters from
	 * @return self
	 */
	public ViewGroup.LayoutParams layout(AttributeSet attrs) {
		return view.generateLayoutParams(attrs);
	}

	/**
	 * Returns the layout animation controller used to animate the group's
	 * children.
	 * 
	 * @return the current animation controller
	 */
	public LayoutAnimationController layoutAnim() {
		return view.getLayoutAnimation();
	}

	/**
	 * Sets the layout animation controller used to animate the group's children
	 * after the first layout.
	 * 
	 * @param layoutAnimationController
	 *            the animation controller
	 * @return self
	 */
	public self layoutAnim(LayoutAnimationController layoutAnimationController) {
		view.setLayoutAnimation(layoutAnimationController);
		return (self) this;
	}

	/**
	 * Specifies the animation listener to which layout animation events must be
	 * sent.
	 * 
	 * @param animationListener
	 *            the layout animation listener
	 * @return self
	 */
	public self layoutAnim(
			vapor.listeners.view.animation.animation.$anim animationListener) {
		view.setLayoutAnimationListener(animationListener);
		return (self) this;
	}

	/**
	 * Returns the animation listener to which layout animation events are sent.
	 * 
	 * @return an VaporViewAnimation.AnimationListener
	 */
	public Animation.AnimationListener layoutAnimListener() {
		return view.getLayoutAnimationListener();
	}

	/**
	 * Gets the LayoutTransition object for this ViewGroup.
	 * 
	 * @return LayoutTranstion The LayoutTransition object that will animated
	 *         changes in layout. A value of null means no transition will run
	 *         on layout changes.
	 */
	public LayoutTransition layoutTransit() {
		return view.getLayoutTransition();
	}

	/**
	 * Sets the LayoutTransition object for this ViewGroup. If the
	 * LayoutTransition object is not null, changes in layout which occur
	 * because of children being added to or removed from the ViewGroup will be
	 * animated according to the animations defined in that LayoutTransition
	 * object. By default, the transition object is null (so layout changes are
	 * not animated).
	 * 
	 * @param layoutTransition
	 *            The LayoutTransition object that will animated changes in
	 *            layout. A value of null means no transition will run on layout
	 *            changes.
	 * @return self
	 */
	public self layoutTransit(LayoutTransition layoutTransition) {
		view.setLayoutTransition(layoutTransition);
		return (self) this;
	}

	/**
	 * Bring up a context menu for the specified view or its ancestors.
	 * 
	 * @param originalView
	 *            The source view where the context menu was first invoked
	 * @return self
	 */
	public boolean menu(VaporView<? extends View, ?> originalView) {
		return view.showContextMenuForChild(originalView.view());
	}

	/**
	 * Returns true if MotionEvents dispatched to this ViewGroup can be split to
	 * multiple children.
	 * 
	 * @return true if MotionEvents dispatched to this ViewGroup can be split to
	 *         multiple children.
	 */
	public boolean motionSplit() {
		return view.isMotionEventSplittingEnabled();
	}

	/**
	 * Enable or disable the splitting of MotionEvents to multiple children
	 * during touch event dispatch.
	 * 
	 * When this option is enabled MotionEvents may be split and dispatched to
	 * different child views depending on where each pointer initially went
	 * down. This allows for user interactions such as scrolling two panes of
	 * content independently, chording of buttons, and performing independent
	 * gestures on different pieces of content.
	 * 
	 * @param motionEventSplitting
	 *            true to allow MotionEvents to be split and dispatched to
	 *            multiple child views. false to only allow one child view to be
	 *            the target of any MotionEvent received by this ViewGroup.
	 * @return self
	 */
	public self motionSplit(boolean motionEventSplitting) {
		view.setMotionEventSplittingEnabled(motionEventSplitting);
		return (self) this;
	}

	/**
	 * Offset a rectangle that is in a descendant's coordinate space into our
	 * coordinate space.
	 * 
	 * @param descendant
	 *            A descendant of this view
	 * @param rect
	 *            A rectangle defined in descendant's coordinate space.
	 * @return self
	 */
	public final self offsetRect(VaporView<? extends View, ?> descendant,
			Rect rect) {
		view.offsetDescendantRectToMyCoords(descendant.view(), rect);
		return (self) this;
	}

	/**
	 * Offset a rectangle that is in our coordinate space into an ancestor's
	 * coordinate space.
	 * 
	 * @param descendant
	 *            A descendant of this view
	 * @param rect
	 *            A rectangle defined in descendant's coordinate space.
	 * @return self
	 */
	public final self offsetRect2Desc(VaporView<? extends View, ?> descendant,
			Rect rect) {
		view.offsetRectIntoDescendantCoords(descendant.view(), rect);
		return (self) this;
	}

	/**
	 * Tell view hierarchy that the global view attributes need to be
	 * re-evaluated.
	 * 
	 * @param child
	 *            View whose attributes have changed.
	 * @return self
	 */
	public self recomputeAttrs(VaporView<? extends View, ?> child) {
		view.recomputeViewAttributes(child.view());
		return (self) this;
	}

	/**
	 * Removes the view at the specified position in the group.
	 * 
	 * @param index
	 *            the position in the group of the view to remove
	 * @return self
	 */
	public self remove(int index) {
		view.removeViewAt(index);
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
		this.view.removeView(view.view());
		return (self) this;
	}

	/**
	 * 
	 * @param view
	 * @param duringLayout
	 * @return self
	 */
	public self remove(VaporView<? extends View, ?> view, boolean duringLayout) {
		if (duringLayout)
			this.view.removeViewInLayout(view.view());
		else
			this.view.removeView(view.view());
		return (self) this;
	}

	/**
	 * Removes the specified range of views from the group.
	 * 
	 * @param startIndex
	 *            the first position in the group of the range of views to
	 *            remove
	 * @param count
	 *            the number of views to remove
	 * @param duringLayout
	 *            true to remove the views during view layout
	 * @return self
	 */
	public self remove(int startIndex, int count, boolean duringLayout) {
		if (duringLayout)
			view.removeViews(startIndex, count);
		else
			view.removeViewsInLayout(startIndex, count);
		return (self) this;
	}

	/**
	 * Call this method to remove all child views from the ViewGroup, optionally
	 * during layout when it must first know its size on screen before it can
	 * calculate how many child views it will render.
	 * 
	 * @param duringLayout
	 *            true to remove views during layout
	 * @return self
	 */
	public self removeAll(boolean duringLayout) {
		if (duringLayout)
			view.removeAllViewsInLayout();
		else
			view.removeAllViews();
		return (self) this;
	}

	/**
	 * Called when a child of this parent wants focus
	 * 
	 * @param child
	 *            The child of this ViewParent that wants focus. This view will
	 *            contain the focused view. It is not necessarily the view that
	 *            actually has focus.
	 * @param focused
	 *            The view that is a descendant of child that actually has focus
	 * @return self
	 */
	public self reqFocus(VaporView<? extends View, ?> child,
			VaporView<? extends View, ?> focused) {
		view.requestChildFocus(child.view(), focused.view());
		return (self) this;
	}

	/**
	 * Called when a child does not want this parent and its ancestors to
	 * intercept touch events with touchIntercept(MotionEvent).
	 * 
	 * This parent should pass this call onto its parents. This parent must obey
	 * this request for the duration of the touch (that is, only clear the flag
	 * after this parent has received an up or a cancel).
	 * 
	 * @param disallowIntercept
	 *            True if the child does not want the parent to intercept touch
	 *            events.
	 * @return self
	 */
	public self reqNoTouchIntercept(boolean disallowIntercept) {
		view.requestDisallowInterceptTouchEvent(disallowIntercept);
		return (self) this;
	}

	/**
	 * Called when a child of this group wants a particular rectangle to be
	 * positioned onto the screen.
	 * 
	 * @param child
	 *            The direct child making the request. param rectangle The
	 *            rectangle in the child's coordinates the child wishes to be on
	 *            the screen.
	 * @param immediate
	 *            True to forbid animated or delayed scrolling, false otherwise
	 * @return Whether the group scrolled to handle the operation
	 */
	public boolean reqRect(VaporView<? extends View, ?> child, Rect rectangle,
			boolean immediate) {
		return view.requestChildRectangleOnScreen(child.view(), rectangle,
				immediate);
	}

	/**
	 * Called by a child to request from its parent to send an
	 * AccessibilityEvent. The child has already populated a record for itself
	 * in the event and is delegating to its parent to send the event. The parent
	 * can optionally add a record for itself.
	 * 
	 * @param child
	 *            The child which requests sending the event.
	 * @param accessibilityEvent
	 *            The event to be sent.
	 * @return True if the event should be sent.
	 */
	public boolean reqSendAccess(VaporView<? extends View, ?> child,
			AccessibilityEvent accessibilityEvent) {
		return view.requestSendAccessibilityEvent(child.view(),
				accessibilityEvent);
	}

	/**
	 * Fluent equivalent Vapor method for invoking
	 * onRequestSendAccessibilityEvent(View,AccessibilityEvent), called when a
	 * child has requested sending an AccessibilityEvent and gives an opportunity
	 * to its parent to augment the event.
	 * 
	 * @param child
	 *            The child which requests sending the event.
	 * @param accessibilityEvent
	 *            The event to be sent.
	 * @return True if the event should be sent.
	 */
	public boolean reqingSendAccess(VaporView<? extends View, ?> child,
			AccessibilityEvent accessibilityEvent) {
		return view.onRequestSendAccessibilityEvent(child.view(),
				accessibilityEvent);
	}

	/**
	 * Called when a child wants the view hierarchy to gather and report
	 * transparent regions to the window compositor. Views that "punch" holes in
	 * the view hierarchy, such as SurfaceView can use this API to improve
	 * performance of the system. When no such a view is present in the
	 * hierarchy, this optimization in unnecessary and might slightly reduce the
	 * view hierarchy performance.
	 * 
	 * @param child
	 *            the view requesting the transparent region computation
	 * @return self
	 */
	public self reqTransparentRegion(VaporView<? extends View, ?> child) {
		view.requestTransparentRegion(child.view());
		return (self) this;
	}

	/**
	 * Schedules the layout animation to be played after the next layout pass of
	 * this view group. This can be used to restart the layout animation when
	 * the content of the view group changes or when the activity is paused and
	 * resumed.
	 * 
	 * @return self
	 */
	public self schedLayoutAnim() {
		view.scheduleLayoutAnimation();
		return (self) this;
	}

	/**
	 * Runs the layout animation. Calling this method triggers a relayout of
	 * this view group.
	 * 
	 * @return self
	 */
	public self startLayoutAnim() {
		view.startLayoutAnimation();
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking
	 * onInterceptTouchEvent(MotionEvent), used to intercept all touch screen
	 * motion events. This allows you to watch events as they are dispatched to
	 * your children, and take ownership of the current gesture at any point.
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

	/**
	 * This method tells the ViewGroup that the given View object, which should
	 * have this ViewGroup as its parent, should be kept around (re-displayed
	 * when the ViewGroup draws its children) even if it is removed from its
	 * parent. This allows animations, such as those used by Fragment and
	 * LayoutTransition to animate the removal of views. A call to this method
	 * should always be accompanied by a later call to endViewTransition(View),
	 * such as after an animation on the View has finished, so that the View
	 * finally gets removed.
	 * 
	 * @param view
	 *            The View object to be kept visible even if it gets removed
	 *            from its parent.
	 * @return self
	 */
	public self transit(VaporView<? extends View, ?> view) {
		this.view.startViewTransition(view.view());
		return (self) this;
	}

	/**
	 * This is used by the RootView to perform an optimization when the view
	 * hierarchy contains one or several SurfaceView.
	 * 
	 * @param region
	 *            The transparent region for this ViewAncestor (window).
	 * @return Returns true if the effective visibility of the view at this
	 *         point is opaque, regardless of the transparent region; returns
	 *         false if it is possible for underlying windows to be seen behind
	 *         the view.
	 */
	public boolean transparentRegion(Region region) {
		return view.gatherTransparentRegion(region);
	}

	/**
	 * 
	 * @param view
	 * @param layoutParams
	 * @return self
	 */
	public self update(VaporView<? extends View, ?> view,
			LayoutParams layoutParams) {
		this.view.updateViewLayout(view.view(), layoutParams);
		return (self) this;
	}

	/* INTERFACE : ViewManager */
	/**
	 * NOTE: This method serves to satisfy the ViewManager interface, use the
	 * equivalent VAPOR FLUENT method child(VaporView<? extends
	 * View,?>,ViewGroup.LayoutParams) instead
	 */
	public void addView(View view, ViewGroup.LayoutParams layoutParams) {
		child($.vapor(view), layoutParams);
	}

	/**
	 * NOTE: This method serves to satisfy the ViewManager interface, use the
	 * equivalent VAPOR FLUENT method child(VaporView<? extends View,?>,true)
	 * instead
	 */
	public void removeView(View view) {
		remove($.vapor(view), true);
	}

	/**
	 * NOTE: This method serves to satisfy the ViewManager interface, use the
	 * equivalent VAPOR FLUENT method update(VaporView<? extends
	 * View,?>,LayoutParams) instead
	 */
	public void updateViewLayout(View view, ViewGroup.LayoutParams layoutParams) {
		update($.vapor(view), layoutParams);
	}

	/* INTERFACE : ViewParent */
	/**
	 * NOTE: This method serves to satisfy the ViewParent interface, use the
	 * equivalent VAPOR FLUENT method front(VaporView<? extends View,?>) instead
	 */
	public void bringChildToFront(View child) {
		front($.vapor(child));
	}

	/**
	 * NOTE: This method serves to satisfy the ViewParent interface, use the
	 * equivalent VAPOR FLUENT method childDrawStateChange(VaporView<? extends
	 * View,?>) instead
	 */
	public void childDrawableStateChanged(View child) {
		childDrawStateChanged($.vapor(child));
	}

	/**
	 * NOTE: This method serves to satisfy the ViewParent interface, use the
	 * equivalent VAPOR FLUENT method clearFocus(VaporView<? extends View,?>)
	 * instead
	 */
	public void clearChildFocus(View child) {
		clearFocus($.vapor(child));
	}

	/**
	 * NOTE: This method serves to satisfy the ViewParent interface, use the
	 * equivalent VAPOR FLUENT method menu(ContextMenu) instead
	 */
	public void createContextMenu(ContextMenu menu) {
		menu(menu);
	}

	/**
	 * NOTE: This method serves to satisfy the ViewParent interface, use the
	 * equivalent VAPOR FLUENT method focus(VaporView<? extends View,?>,int)
	 * instead
	 */
	public View focusSearch(View view, int direction) {
		return focus($.vapor(view), direction).view();
	}

	/**
	 * NOTE: This method serves to satisfy the ViewParent interface, use the
	 * equivalent VAPOR FLUENT method focusableAvailable(VaporView<? extends
	 * View,?>) instead
	 */
	public void focusableViewAvailable(View view) {
		focusableAvailable($.vapor(view));
	}

	/**
	 * NOTE: This method serves to satisfy the ViewParent interface, use the
	 * equivalent VAPOR FLUENT method childRect(VaporView<? extends
	 * View,?>,Rect,Point) instead
	 */
	public boolean getChildVisibleRect(View child, Rect rectangle, Point offset) {
		return childRect($.vapor(child), rectangle, offset);
	}

	/**
	 * NOTE: This method serves to satisfy the ViewParent interface, use the
	 * equivalent VAPOR FLUENT method parent(false) instead
	 */
	public ViewParent getParent() {
		return parent(false);
	}

	/**
	 * NOTE: This method serves to satisfy the ViewParent interface, use the
	 * equivalent VAPOR FLUENT method parent(true) instead
	 */
	public ViewParent getParentForAccessibility() {
		return parent(true);
	}

	/**
	 * NOTE: This method serves to satisfy the ViewParent interface, there is no
	 * equivalent VAPOR FLUENT method as this should NOT be called or overridden
	 */
	public void invalidateChild(View child, Rect rectangle) {
		view.invalidateChild(child, rectangle);
	}

	/**
	 * NOTE: This method serves to satisfy the ViewParent interface, there is no
	 * equivalent VAPOR FLUENT method as this should NOT be called or overridden
	 */
	public ViewParent invalidateChildInParent(int[] location, Rect rectangle) {
		return view.invalidateChildInParent(location, rectangle);
	}

	/**
	 * NOTE: This method serves to satisfy the ViewParent interface, use the
	 * equivalent VAPOR FLUENT method layoutRequested() instead
	 */
	public boolean isLayoutRequested() {
		return layoutReqd();
	}

	/**
	 * NOTE: This method serves to satisfy the ViewParent interface, use the
	 * equivalent VAPOR FLUENT method recomputeAttrs(VaporView<? extends
	 * View,?>) instead
	 */
	public void recomputeViewAttributes(View child) {
		recomputeAttrs($.vapor(child));
	}

	/**
	 * NOTE: This method serves to satisfy the ViewParent interface, use the
	 * equivalent VAPOR FLUENT method reqFocus(VaporView<W,X>,VaporView<Y,Z>)
	 * instead
	 */
	public void requestChildFocus(View child, View focused) {
		reqFocus($.vapor(child), $.vapor(focused));
	}

	/**
	 * NOTE: This method serves to satisfy the ViewParent interface, use the
	 * equivalent VAPOR FLUENT method reqRect(VaporView<? extends
	 * View,?>,Rect,boolean) instead
	 */
	public boolean requestChildRectangleOnScreen(View child, Rect rectangle,
			boolean immediate) {
		return reqRect($.vapor(child), rectangle, immediate);
	}

	/**
	 * NOTE: This method serves to satisfy the ViewParent interface, use the
	 * equivalent VAPOR FLUENT method reqNoTouchIntercept(boolean) instead
	 */
	public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
		reqNoTouchIntercept(disallowIntercept);
	}

	/**
	 * NOTE: This method serves to satisfy the ViewParent interface, use the
	 * equivalent VAPOR FLUENT method reqFit() instead
	 */
	public void requestFitSystemWindows() {
		reqFit();
	}

	/**
	 * NOTE: This method serves to satisfy the ViewParent interface, use the
	 * equivalent VAPOR FLUENT method reqLayout() instead
	 */
	public void requestLayout() {
		reqLayout();
	}

	/**
	 * NOTE: This method serves to satisfy the ViewParent interface, use the
	 * equivalent VAPOR FLUENT method reqSendAccess(VaporView<? extends
	 * View,?>,AccessibilityEvent) instead
	 */
	public boolean requestSendAccessibilityEvent(View child,
			AccessibilityEvent accessibilityEvent) {
		return reqSendAccess($.vapor(child), accessibilityEvent);
	}

	/**
	 * NOTE: This method serves to satisfy the ViewParent interface, use the
	 * equivalent VAPOR FLUENT method reqTransparentRegion(VaporView<? extends
	 * View,?>) instead
	 */
	public void requestTransparentRegion(View child) {
		reqTransparentRegion($.vapor(child));
	}

	/**
	 * NOTE: This method serves to satisfy the ViewParent interface, use the
	 * equivalent VAPOR FLUENT method menu(VaporView<? extends View,?>) instead
	 */
	public boolean showContextMenuForChild(View originalView) {
		return menu($.vapor(originalView));
	}

	/**
	 * NOTE: This method serves to satisfy the ViewParent interface, use the
	 * equivalent VAPOR FLUENT method action(VaporView<? extends
	 * View,?>,ActionMode.Callback) instead
	 */
	public ActionMode startActionModeForChild(View originalView,
			ActionMode.Callback callback) {
		return action($.vapor(originalView), callback);
	}
}
