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

import vapor.view.VaporView;
import android.app.MediaRouteButton;

//Checked: 051220121048

/**
 * Fluent Vapor companion to MediaRouteButton
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from MediaRouteButton
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporMediaRouteButton<T extends MediaRouteButton, self extends VaporMediaRouteButton<T, self>>
		extends VaporView<T, self> {

	public VaporMediaRouteButton(int id) {
		super(id);
	}

	public VaporMediaRouteButton(T mediaRouteButton) {
		super(mediaRouteButton);
	}

	/**
	 * Fluent equivalent Vapor method for invoking onAttachedToWindow(), called
	 * when the view is attached to a window. At this point it has a Surface and
	 * will start drawing.
	 * 
	 * @return self
	 */
	public self attached() {
		view.onAttachedToWindow();
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onDetachedFromWindow(),
	 * called when the view is detached from a window. At this point it no
	 * longer has a surface for drawing.
	 * 
	 * @return self
	 */
	public self detached() {
		view.onDetachedFromWindow();
		return (self) this;
	}

	/**
	 * Asynchronously show the route chooser dialog. This will attach a
	 * DialogFragment to the containing Activity.
	 * 
	 * @return self
	 */
	public self dialog() {
		view.showDialog();
		return (self) this;
	}

	/**
	 * Sets the extended settings click listener
	 * 
	 * @param extendedSettingsListener
	 * @return self
	 */
	public self extSettings(vapor.listeners.view.$click extendedSettingsListener) {
		view.setExtendedSettingsClickListener(extendedSettingsListener);
		return (self) this;
	}

	/**
	 * 
	 * @return
	 */
	public int routeTypes() {
		return view.getRouteTypes();
	}

	/**
	 * 
	 * @param routeTypes
	 * @return self
	 */
	public self routeTypes(int routeTypes) {
		view.setRouteTypes(routeTypes);
		return (self) this;
	}

}
