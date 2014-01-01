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

import vapor.core.$;
import vapor.view.VaporView;
import android.app.LocalActivityManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TabWidget;

/**
 * Fluent Vapor companion to TabHost, a container for a tabbed window view. This
 * object holds two children: a set of tab labels that the user clicks to select
 * a specific tab, and a FrameLayout object that displays the contents of that
 * page. The individual elements are typically controlled using this container
 * object, rather than setting values on the child elements themselves.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from TabHost
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporTabHost<T extends TabHost, self extends VaporTabHost<T, self>>
		extends VaporFrameLayout<T, self> implements
		vapor.listeners.view.viewtreeobserver.$mode {

	public VaporTabHost(int id) {
		super(id);

	}

	public VaporTabHost(T tabHost) {
		super(tabHost);
	}

	/**
	 * Add a tab.
	 * 
	 * @param tabSpec
	 *            Specifies how to create the indicator and content.
	 * @return self
	 */
	public self tab(TabHost.TabSpec tabSpec) {
		view.addTab(tabSpec);
		return (self) this;
	}

	/**
	 * Removes all tabs from the tab widget associated with this tab host.
	 * 
	 * @return self
	 */
	public self clear() {
		view.clearAllTabs();
		return (self) this;
	}

	/**
	 * Get the FrameLayout which holds tab content
	 * 
	 * @return
	 */
	public VFrameLayout<FrameLayout> contentView() {
		return $.FrameLayout(view.getTabContentView());
	}

	/**
	 * 
	 * @return
	 */
	public int itemSelected() {
		return view.getCurrentTab();
	}

	/**
	 * 
	 * @param tabIndex
	 * @return
	 */
	public self itemSelected(int tabIndex) {
		view.setCurrentTab(tabIndex);
		return (self) this;
	}

	/**
	 * 
	 * @param tabTag
	 * @return
	 */
	public self itemSelected(String tabTag) {
		view.setCurrentTabByTag(tabTag);
		return (self) this;
	}

	/**
	 * 
	 * @return
	 */
	public VaporView<? extends View, ?> currentTabView() {
		return $.vapor(view.getCurrentTabView());
	}

	/**
	 * 
	 * @return
	 */
	public String itemSelectedTag() {
		return view.getCurrentTabTag();
	}

	/**
	 * 
	 * @return
	 */
	public VaporView<? extends View, ?> currentView() {
		return $.vapor(view.getCurrentView());
	}

	/**
	 * Register a callback to be invoked when the selected state of any of the
	 * items in this list changes
	 * 
	 * @param tabChangedListener
	 *            The callback that will run
	 * @return self
	 */
	public self tab(vapor.listeners.widget.tabhost.$change tabChangedListener) {
		view.setOnTabChangedListener(tabChangedListener);
		return (self) this;
	}

	/**
	 * If you are using setContent(android.content.Intent), this must be called
	 * since the activityGroup is needed to launch the local activity. This is
	 * done for you if you extend TabActivity.
	 * 
	 * @param activityGroup
	 *            Used to launch activities for tab content.
	 * @return self
	 */
	public self setup(LocalActivityManager activityGroup) {
		view.setup(activityGroup);
		return (self) this;
	}

	/**
	 * Get a new TabHost.TabSpec associated with this tab host.
	 * 
	 * @param newTabSpecTag
	 *            required tag of tab.
	 * @return
	 */
	public TabHost.TabSpec spec(String newTabSpecTag) {
		return view.newTabSpec(newTabSpecTag);
	}

	/**
	 * Fluent equivalent Vapor method for invoking onTouchModeChanged(boolean),
	 * invoked when the touch mode changes.
	 * 
	 * @param isInTouchMode
	 *            True if the view hierarchy is now in touch mode, false
	 *            otherwise.
	 * @return self
	 */
	public self touchModeChanged(boolean isInTouchMode) {
		view.onTouchModeChanged(isInTouchMode);
		return (self) this;
	}

	/**
	 * 
	 * @return
	 */
	public VTabWidget<TabWidget> widget() {
		return $.TabWidget(view.getTabWidget());
	}

	/* INTERFACE : ViewTreeObserver.OnTouchModeChangeListener */
	/**
	 * NOTE: This method serves to satisfy the
	 * ViewTreeObserver.OnTouchModeChangeListener interface, use the equivalent
	 * VAPOR FLUENT method touchModeChanged(boolean) instead
	 */
	@Override
	public void onTouchModeChanged(boolean isInTouchMode) {
		touchModeChanged(isInTouchMode);
	}
}
