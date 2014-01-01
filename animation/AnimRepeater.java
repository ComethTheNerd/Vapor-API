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

import vapor.view.VaporView;
import android.animation.Animator;
import android.view.View;
import android.view.ViewGroup;

public class AnimRepeater implements vapor.listeners.animation.animator.$anim {

	public static final int INFINITY = -1;
	private int repeatCount, count;
	vapor.listeners.animation.animator.$anim animListener;
	private boolean terminate = false;
	private Anim anim;
	private ViewGroup.LayoutParams origLayout;
	private float origX, origY, rotX, rotY, scaleX, scaleY,alpha;
	private int viz;
	private VaporView<? extends View, ?> target;

	
	
	public AnimRepeater(Anim anim, int repeatCount,
			vapor.listeners.animation.animator.$anim animListener) {
		this.anim = anim;
		this.target = anim.subject();
		this.repeatCount = repeatCount;
		this.animListener = animListener;
	}

	public vapor.listeners.animation.animator.$anim animListener() {
		return animListener;
	}

	public void animListener(
			vapor.listeners.animation.animator.$anim animListener) {
		this.animListener = animListener;
	}

	public int repeatFor() {
		return repeatCount;
	}

	public void repeatFor(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	public int currentCount() {
		return count;
	}

	public void terminate() {
		terminate = true;
	};

	@Override
	public void onAnimationCancel(Animator animation) {
		if (animListener != null)
			animListener.onAnimationCancel(animation);
	}

	@Override
	public void onAnimationEnd(Animator animation) {
		if (animListener != null)
			animListener.onAnimationEnd(animation);

		if (!terminate) {
			// start the anim again if this is an infinite repeat, or has not
			// repeated the maximum times
			if (repeatCount == INFINITY || count < repeatCount) {
				animation.start();
				return;
			}
		}
		if (!anim.fillAfter())
			restoreOrigLayout();
		terminate = false;
		count = 0; // reset for next time

	}

	@Override
	public void onAnimationRepeat(Animator animation) {
		if (animListener != null)
			animListener.onAnimationRepeat(animation);
	}

	@Override
	public void onAnimationStart(Animator animation) {
		count++;
		if (count == 1) // first run
			saveOrigLayout();
		// if(anim.fillAfter()) anim.recordOrigLayout();
		if (animListener != null)
			animListener.onAnimationStart(animation);
	}

	private void saveOrigLayout() {
		alpha = target.alpha();
		viz = target.viz();
		origLayout = target.layoutParams();
		origX = target.transX();
		origY = target.transY();
		rotX = target.rotX();
		rotY = target.rotY();
		scaleX = target.scaleX();
		scaleY = target.scaleY();
	}

	private void restoreOrigLayout() {
		target.transX(origX);
		target.transY(origY);
		target.layoutParams(origLayout);
		target.rotX(rotX);
		target.rotY(rotY);
		target.scaleX(scaleX);
		target.scaleY(scaleY);
		target.viz(viz);
		target.alpha(alpha);
	}
}
