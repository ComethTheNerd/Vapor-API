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

import vapor.view.VaporView;
import android.view.View;
import android.widget.MediaController;

/**
 * Fluent Vapor companion to MediaController, a view containing controls for a
 * MediaPlayer. Typically contains the buttons like "Play/Pause", "Rewind",
 * "Fast Forward" and a progress slider. It takes care of synchronizing the
 * controls with the state of the MediaPlayer.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from MediaController
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporMediaController<T extends MediaController, self extends VaporMediaController<T, self>>
		extends VaporFrameLayout<T, self> {

	public VaporMediaController(int id) {
		super(id);
	}

	public VaporMediaController(T mediaController) {
		super(mediaController);
	}

	/**
	 * Set the view that acts as the anchor for the control view. This can for
	 * example be a VideoView, or your Activity's main view.
	 * 
	 * @param view
	 *            The view to which to anchor the controller when it is visible.
	 * @return
	 */
	public self anchor(VaporView<? extends View, ?> view) {
		this.view.setAnchorView(view.view());
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onFinishInflate(), used to
	 * finalize inflating a view from XML. This is called as the last phase of
	 * inflation, after all child views have been added.
	 * 
	 * @return this
	 */
	public self finishInflate() {
		view.onFinishInflate();
		return (self) this;
	}

	/*
	 * Originally intended to have this method, but think that show/hide will do
	 * the same thing, as controls refers to the whole view
	 * 
	 * 
	 * public boolean controls(){ return view.isShowing(); return (self)this; }
	 * 
	 * public self controls(boolean showControls){
	 * 
	 * }
	 * 
	 * /**Remove the controller from the screen.
	 * 
	 * @return this
	 * 
	 * public self hide(){ view.hide(); return (self)this; }
	 */
	/**
	 * 
	 * @param mediaPlayer
	 * @return this
	 */
	public self player(MediaController.MediaPlayerControl mediaPlayer) {
		view.setMediaPlayer(mediaPlayer);
		return (self) this;
	}

	/**
	 * 
	 * @param prev
	 * @param next
	 * @return this
	 */
	public self prevNext(vapor.listeners.view.$click prev,
			vapor.listeners.view.$click next) {
		view.setPrevNextListeners(next, prev); // the order of method args is
												// intentionally different as it
												// makes more sense
		return (self) this;
	}

	/**
	 * Show the controller on screen. It will go away automatically after 3
	 * seconds of inactivity.
	 * 
	 * @return this
	 */
	public self tempShow() {
		view.show();
		return (self) this;
	}

	/**
	 * Show the controller on screen. It will go away automatically after
	 * 'timeout' milliseconds of inactivity.
	 * 
	 * @param timeOut
	 *            The timeout in milliseconds. Use 0 to show the controller
	 *            until hide() is called.
	 * @return this
	 */
	public self tempShow(int timeOut) {
		view.show(timeOut);
		return (self) this;
	}

	@Override
	public boolean showing() {
		return view.isShowing();// override on purpose
	}

}
