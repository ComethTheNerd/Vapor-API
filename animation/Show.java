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

package vapor.animation;

import vapor.listeners.animation.animator.$anim;
import vapor.os.VaporBundle;
import vapor.view.VaporView;
import android.animation.Animator;
import android.view.View;

public class Show<self extends Show<self>> extends Anim<self> {

	protected Fade fader;
	protected static final long __DURATION = 0L;

	private int lastHash = 0;

	/*
	 * final Handler handler = new Handler (); timer.scheduleAtFixedRate (new
	 * TimerTask (){ public void run (){ handler.post (new Runnable (){ public
	 * void run (){ storedInterface.A (); } }); } }, 0, speed);
	 */

	public Show(VaporView<? extends View, ?> view, VaporBundle options) {
		super(view, options);
	}

	public Show(VaporView<? extends View, ?> view) {
		super(view);
	}

	@Override
	public void run() {
		if (!options.contains(DURATION))
			options.put(DURATION, __DURATION);

		// Get the listener in the options
		$anim listener = prop(ANIM_LISTENER, __ANIM_LISTENER);
		// To avoid stacking up combined listeners from multi calls to run, we
		// check first if
		// the hashcode is not the same as the combinedListener we made last
		// time
		if (listener.hashCode() != lastHash) {

			// create new combined listener
			$anim combinedListener = combineListeners(listener);

			// set the last hash flag
			lastHash = combinedListener.hashCode();

			// put the new combined listener in the bundle
			options.put(ANIM_LISTENER, combinedListener);

			// so if next time we call run() the listener hasn't been updated
			// we won't end up stacking up combined listeners
		}

		options.put(Fade.FROM, 0F);
		options.put(Fade.TO, 1F);

		fader = new Fade(view, options);
		fader.run();
	}

	// Combines the given animationListener, with the one we require for this
	// animation
	protected final vapor.listeners.animation.animator.$anim combineListeners(
			final vapor.listeners.animation.animator.$anim with) {

		return new $anim() {

			@Override
			public void onAnimationCancel(Animator animation) {
				with.onAnimationCancel(animation);
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				with.onAnimationEnd(animation);
			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				with.onAnimationRepeat(animation);
			}

			@Override
			public void onAnimationStart(Animator animation) {
				with.onAnimationStart(animation);
				// Completely show the View
				view.viz(View.VISIBLE);
			}
		};
	}

}
