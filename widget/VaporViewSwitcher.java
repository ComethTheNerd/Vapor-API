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
import android.view.View;
import android.widget.ViewSwitcher;
import android.widget.ViewSwitcher.ViewFactory;

/**
 * Fluent Vapor companion to ViewSwitcher, a ViewAnimator that switches between
 * two views, and has a factory from which these views are created. You can
 * either use the factory to create the views, or add them yourself. A
 * ViewSwitcher can only have two child views, of which only one is shown at a
 * time.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from ViewSwitcher
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporViewSwitcher<T extends ViewSwitcher, self extends VaporViewSwitcher<T, self>>
		extends VaporViewAnimator<T, self> {

	public VaporViewSwitcher(int id) {
		super(id);
	}

	public VaporViewSwitcher(T viewSwitcher) {
		super(viewSwitcher);
	}

	/**
	 * Sets the factory used to create the two views between which the
	 * ViewSwitcher will flip. Instead of using a factory, you can call
	 * child(VaporView, int, android.view.ViewGroup.LayoutParams) twice.
	 * 
	 * @param factory
	 *            the view factory used to generate the switcher's content
	 * @return self
	 */
	public self factory(ViewFactory factory) {
		view.setFactory(factory);
		return (self) this;
	}

	/**
	 * Returns the next view to be displayed.
	 * 
	 * @return the view that will be displayed after the next views flip.
	 */
	public VaporView<? extends View, ?> nextView() {
		return $.vapor(view.getNextView());
	}

	/**
	 * Reset the ViewSwitcher to hide all of the existing views and to make it
	 * think that the first time animation has not yet played.
	 * 
	 * @return self
	 */
	public self reset() {
		view.reset();
		return (self) this;
	}

}
