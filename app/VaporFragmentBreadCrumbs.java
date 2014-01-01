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

package vapor.app;

import vapor.view.VaporViewGroup;
import android.app.FragmentBreadCrumbs;
import android.app.FragmentManager.OnBackStackChangedListener;

//Checked:051220121044

/**
 * Fluent Vapor companion to FragmentBreadCrumbs, a helper class for showing
 * "bread crumbs" representing the fragment stack in an activity.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from FragmentBreadCrumbs
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporFragmentBreadCrumbs<T extends FragmentBreadCrumbs, self extends VaporFragmentBreadCrumbs<T, self>>
		extends VaporViewGroup<T, self> implements OnBackStackChangedListener {

	public VaporFragmentBreadCrumbs(int id) {
		super(id);
	}

	public VaporFragmentBreadCrumbs(T fragmentBreadCrumbs) {
		super(fragmentBreadCrumbs);
	}

	/**
	 * Attach the bread crumbs to their activity. This must be called once when
	 * creating the bread crumbs.
	 * 
	 * @param vaporActivity
	 * @return self
	 */
	public self activity(VaporActivity vaporActivity) { // should just be
														// act(..)
		view.setActivity(vaporActivity);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onBackStackChanged(), called
	 * whenever the contents of the back stack change.
	 * 
	 * @return self
	 */
	public self backStackChanged() {
		view.onBackStackChanged();
		return (self) this;
	}

	/**
	 * Sets a listener for clicks on the bread crumbs. This will be called
	 * before the default click action is performed.
	 * 
	 * @param breadCrumbClickListener
	 *            The new listener to set. Replaces any existing listener.
	 * @return self
	 */
	public self click(
			vapor.listeners.app.fragmentbreadcrumbs.$click breadCrumbClickListener) {
		view.setOnBreadCrumbClickListener(breadCrumbClickListener);
		return (self) this;
	}

	/**
	 * The maximum number of breadcrumbs to show. Older fragment headers will be
	 * hidden from view.
	 * 
	 * @param visibleCrumbs
	 *            the number of visible breadcrumbs. This should be greater than
	 *            zero.
	 * @return self
	 */
	public self maxViz(int visibleCrumbs) {
		view.setMaxVisible(visibleCrumbs);
		return (self) this;
	}

	/**
	 * Inserts an optional parent entry at the first position in the
	 * breadcrumbs. Selecting this entry will result in a call to the specified
	 * listener's click(View) method.
	 * 
	 * @param title
	 *            the title for the parent entry
	 * @param shortTitle
	 *            the short title for the parent entry
	 * @param clickListener
	 *            the vapor.listeners.view.$click listener to be called when
	 *            clicked. A null will result in no action being taken when the
	 *            parent entry is clicked.
	 * @return self
	 */
	public self parentTitle(CharSequence title, CharSequence shortTitle,
			vapor.listeners.view.$click clickListener) {
		view.setParentTitle(title, shortTitle, clickListener);
		return (self) this;
	}

	/**
	 * Set a custom title for the bread crumbs. This will be the first entry
	 * shown at the left, representing the root of the bread crumbs. If the
	 * title is null, it will not be shown.
	 * 
	 * @param title
	 *            the title for the entry
	 * @param shortTitle
	 *            the short title for the entry
	 * @return self
	 */
	public self title(CharSequence title, CharSequence shortTitle) {
		view.setTitle(title, shortTitle);
		return (self) this;
	}

	/* INTERFACE : OnBackStackChangedListener */
	/**
	 * NOTE: This method serves to satisfy the OnBackStackChangedListener
	 * interface, use the equivalent VAPOR FLUENT method backStackChanged()
	 * instead
	 */
	@Override
	public void onBackStackChanged() {
		backStackChanged();
	}
}
