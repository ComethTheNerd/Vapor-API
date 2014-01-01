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

import vapor.os.VaporBundle;
import vapor.view.VaporView;
import android.animation.ObjectAnimator;
import android.view.View;

public class Slide<self extends Slide<self>> extends Anim<self> {

	public static final String DIRECTION = "direction", DISTANCE = "distance",
			UP = "up", LEFT = "left", RIGHT = "right", DOWN = "down";

	protected static final float __DISTANCE = 20F;
	protected static final String __DIRECTION = UP;
	private String tempDirection = __DIRECTION;

	public Slide(VaporView<? extends View, ?> view, VaporBundle options) {
		super(view, options);
	}

	public Slide(VaporView<? extends View, ?> view) {
		super(view);
	}

	public String direction() {
		return prop(DIRECTION, __DIRECTION);
	}

	public self direction(String direction) {
		options.put(DIRECTION, direction);
		return (self) this;

	}

	public float distance() {
		return prop(DISTANCE, __DISTANCE);
	}

	public self distance(float distance) {
		options.put(DISTANCE, distance);
		return (self) this;

	}

	@Override
	public void run() {
		// assign a transient value to tempDirection, used for other calcs
		tempDirection = prop(DIRECTION, __DIRECTION);
		float currentTranslation = currentTrans();

		run(ObjectAnimator.ofFloat(view.view(), transProp(),
				currentTranslation, endPos(currentTranslation)));
	}

	// Returns the property we need to animate
	protected final String transProp() {
		if (tempDirection.equals(RIGHT) || tempDirection.equals(LEFT))
			return "translationX";
		else
			return "translationY";
	}

	// Returns the end position of the VaporView after the animation
	protected final float endPos(float currentTranslation) {
		if (tempDirection.equals(DOWN) || tempDirection.equals(RIGHT))
			return currentTranslation + prop(DISTANCE, __DISTANCE);
		else
			return currentTranslation - prop(DISTANCE, __DISTANCE);
	}

	// Returns the current translation
	protected final float currentTrans() {
		if (tempDirection.equals(DOWN) || tempDirection.equals(UP))
			return view.view().getTranslationY();
		else
			return view.view().getTranslationX();
	}

}
