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

import vapor.core.$;
import vapor.exception.VaporLicenseException;
import vapor.listeners.animation.animator.$anim;
import vapor.os.VaporBundle;
import vapor.view.VaporView;
import android.animation.Animator;
import android.view.View;

public abstract class Anim<self extends Anim<self>> {

	public static final String DURATION = "duration",
	/* ANIM = "anim", */
	REPEAT = "repeat", FILL_AFTER = "fillAfter",
			ANIM_LISTENER = "animListener";

	protected static final long __DURATION = 500L;
	protected static final int __REPEAT = 1;
	protected static final boolean __FILL_AFTER = true, __UNISON = false;
	protected static final $anim __ANIM_LISTENER = new $anim() {

		@Override
		public void onAnimationCancel(Animator arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationEnd(Animator animation) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationRepeat(Animator animation) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onAnimationStart(Animator animation) {
			// TODO Auto-generated method stub

		}
	};
	protected VaporBundle options;
	protected VaporView<? extends View, ?> view;
	private AnimRepeater repeater;
	private Animator animator;

	private static final String LOG_TAG = "VaporAnimEngine";

	public Anim(VaporView<? extends View, ?> view, VaporBundle options) {
		if(!$.licensed()){
			throw new VaporLicenseException();
		}else{
			this.view = view;
			this.options = options != null ? options : $.Bundle();
		}
	}

	public Anim(VaporView<? extends View, ?> view) {
		this(view, null);
	}

	/**Safely retrieves a property from the options associated with this animation instance. See $.prop(...) for more details
	*
	*@param propertyName the name of the property to retrieve
	*@param defaultValue the default value to return if the property is missing, or a problem occurs during retrieval
	*@return the value mapped to the given property name in the options associated with this animation instance, or the default value if the property is missing, or a problem occurs during retrieval
	*
	*/
	protected final <T> T prop(String propertyName, T defaultValue) {
		return $.prop(options, propertyName, defaultValue);
	}

	/**Prompts the animation to start immediately
	*/
	public abstract void run();

	/**A helper framework method that you should use to run your animation code. This method will take care of setting up 
	* the appropriate animation listener, the required number of repeats, etc.
	*
	*@param anim the animator instance that contains the code for the animation
	*/
	protected final void run(Animator anim) {
		// If the previous animator is still playing
		if (animator != null) {
			// check if we allow animation in unison
			if (animator.isRunning())
				stop();
		}
		animator = anim;

		animator.setDuration(prop(DURATION, __DURATION));
		// create an AnimRepeater that will control repeat count and firing the
		// listener
		repeater = new AnimRepeater(this, prop(REPEAT, __REPEAT), prop(
				ANIM_LISTENER, __ANIM_LISTENER));
		animator.addListener(repeater);
		animator.start();
	}

	/**Stops the current animation immediately
	*
	*@return self
	*/
	public final self stop() {
		if (repeater != null)
			repeater = null;// TEST THIS. //repeater.terminate();
		if (animator != null)
			animator.end();
		return (self) this;
	}

	/*NOW SET VIA THE OPTIONS VAPORBUNDLE!
	 * public vapor.listeners.animation.animator.$anim animListener(){return
	 * animListener;}
	 * 
	 * public self animListener(vapor.listeners.animation.animator.$anim
	 * animatorListener){ this.animListener = animatorListener; return
	 * (self)this; }
	 */
	 
	
	/**Returns how long each run of this animation instance will take
	*
	*@return how long each run of this animation instance will take
	*/
	public long duration() {
		return prop(DURATION, __DURATION);
	}

	/**Set how long each run of this animation instance will take
	*
	*@param duration how long each run of this animation instance will take
	*@return self
	*/
	public self duration(long duration) {
		options.put(DURATION, duration);
		return (self) this;
	}

	/**Returns whether the post-animation state of the underlying View should be preserved, or reverted to its 
	* pre-animation state, following the animation being run
	*
	*@return true if the post-animation state of the underlying View should be preserved, false if it is reverted to its 
	* pre-animation state
	*/
	public boolean fillAfter() {
		return prop(FILL_AFTER, __FILL_AFTER);
	}

	/**Sets whether the post-animation state of the underlying View should be preserved, or reverted to its 
	* pre-animation state, following the animation being run
	*
	*@param fillAfter true if the post-animation state of the underlying View should be preserved, false if it is reverted to its 
	* pre-animation state
	*@return self
	*/
	public self fillAfter(boolean fillAfter) {
		options.put(FILL_AFTER, fillAfter);
		return (self) this;
	}

	/**Returns the log tag to use for debugging
	*
	*@return the log tag to use for debugging
	*/
	protected final String logTag() {
		return LOG_TAG;
	}

	/**Returns the options for this animation instance
	*
	*@return the options for this animation instance
	*/
	public VaporBundle options() {
		return options;
	}

	/**Overwrites the existing options for this animation, with the new given options
	*
	*@param newOptions the new options to use for this animation instance
	*@return self
	*/
	public self options(VaporBundle newOptions) {
		this.options = newOptions;
		return (self) this;
	}

	/**Returns how many times the animation repeats for when it is run
	*
	*@return how many times the animation repeats for when it is run
	*/
	public int repeatFor() {
		return prop(REPEAT, __REPEAT);
	}

	/**Set how many times this animation instance will repeat for when run
	*
	*@param repeatFor the number of times to repeat the animation
	*@return self
	*/
	public self repeatFor(int repeatFor) {
		options.put(REPEAT, repeatFor);
		return (self) this;
	}

	/**Returns the view that is the subject of this animation instance
	*
	*@return the view that is the subject of this animation instance
	*/
	public VaporView<? extends View, ?> subject() {
		return view;
	}

}
