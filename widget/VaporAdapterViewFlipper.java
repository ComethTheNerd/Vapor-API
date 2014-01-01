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

import android.widget.Adapter;
import android.widget.AdapterViewFlipper;

/**
 * Fluent Vapor companion to AdapterViewFlipper, a view that animates between
 * two or more views that have been added to it. Only one child is shown at a
 * time. If requested, can automatically flip between each child at a regular
 * interval.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from AdapterViewFlipper
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporAdapterViewFlipper<T extends AdapterViewFlipper, self extends VaporAdapterViewFlipper<T, self>>
		extends VaporAdapterViewAnimator<T, self> {

	public VaporAdapterViewFlipper(int id) {
		super(id);
	}

	public VaporAdapterViewFlipper(T adapterViewFlipper) {
		super(adapterViewFlipper);
	}

	@Override
	public self adapter(Adapter adapter) {
		view.setAdapter(adapter);
		return (self) this;
	}

	/**
	 * Returns true if this view automatically calls startFlipping() when it
	 * becomes attached to a window.
	 * 
	 * @return true if this view automatically calls startFlipping() when it
	 *         becomes attached to a window.
	 */
	public boolean autoStarts() {
		return view.isAutoStart();
	}

	/**
	 * Set if this view automatically calls startFlipping() when it becomes
	 * attached to a window.
	 * 
	 * @param autoStart
	 * @return self
	 */
	public self autoStarts(boolean autoStart) {
		view.setAutoStart(autoStart);
		return (self) this;
	}

	/**
	 * Start a timer to cycle through child views
	 * 
	 * @param flip
	 *            true to start flipping, false to stop flipping
	 * @return self
	 */
	public self flip(boolean flip) {
		if (flip)
			view.startFlipping();
		else
			view.stopFlipping();
		return (self) this;
	}

	/**
	 * Returns true if the child views are flipping.
	 * 
	 * @return true if the child views are flipping.
	 */
	public boolean flipping() {
		return view.isFlipping();
	}

	/**
	 * Returns the flip interval, in milliseconds.
	 * 
	 * @return the flip interval in milliseconds
	 */
	public int interval() {
		return view.getFlipInterval();
	}

	/**
	 * How long to wait before flipping to the next view.
	 * 
	 * @param flipInterval
	 *            flip interval in milliseconds
	 * @return self
	 */
	public self interval(int flipInterval) {
		view.setFlipInterval(flipInterval);
		return (self) this;
	}

}
