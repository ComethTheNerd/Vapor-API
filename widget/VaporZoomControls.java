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

import android.widget.ZoomControls;

/**
 * Fluent Vapor companion to ZoomControls, a class that displays a simple set of
 * controls used for zooming and provides callbacks to register for events.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from ZoomControls
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporZoomControls<T extends ZoomControls, self extends VaporZoomControls<T, self>>
		extends VaporLinearLayout<T, self> {

	public VaporZoomControls(int id) {
		super(id);
	}

	public VaporZoomControls(T zoomControls) {
		super(zoomControls);
	}

	/**
	 * 
	 * @return self
	 * 
	 *         public self hide(){ LEAVE THIS TO THE ANIMATION IN VaporView
	 *         view.hide(); return (self)this; }
	 */

	/**
	 * 
	 * @return self
	 * 
	 *         public self show(){ LEAVE THIS TO THE ANIMATION IN VaporView
	 *         view.show(); return (self)this; }
	 */

	/**
	 * 
	 * @param zoomSpeed
	 * @return this
	 */
	public self speed(long zoomSpeed) {
		view.setZoomSpeed(zoomSpeed);
		return (self) this;
	}

	/**
	 * 
	 * @param zoomInListener
	 * @return this
	 */
	public self zoomIn(vapor.listeners.view.$click zoomInListener) {
		view.setOnZoomInClickListener(zoomInListener);
		return (self) this;
	}

	/**
	 * 
	 * @param zoomOutListener
	 * @return this
	 */
	public self zoomOut(vapor.listeners.view.$click zoomOutListener) {
		view.setOnZoomOutClickListener(zoomOutListener);
		return (self) this;
	}

	/**
	 * 
	 * @param zoomInEnabled
	 * @return this
	 */
	public self zoomableIn(boolean zoomInEnabled) {
		view.setIsZoomInEnabled(zoomInEnabled);
		return (self) this;
	}

	/**
	 * 
	 * @param zoomOutEnabled
	 * @return this
	 */
	public self zoomableOut(boolean zoomOutEnabled) {
		view.setIsZoomOutEnabled(zoomOutEnabled);
		return (self) this;
	}
}
