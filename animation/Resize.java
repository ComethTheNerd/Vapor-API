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
import android.animation.AnimatorSet;
import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

public class Resize<self extends Resize<self>> extends Anim<self> {

	public static final String WIDTH = "width", HEIGHT = "height";

	public Resize(VaporView<? extends View, ?> view, VaporBundle options) {
		super(view, options);
	}

	public Resize(VaporView<? extends View, ?> view) {
		super(view);
	}

	public int toHeight() {
		return (Integer) prop(HEIGHT, view.height());
	}

	public self toHeight(int height) {
		options.put(HEIGHT, height);
		return (self) this;
	}

	public int toWidth() {
		return (Integer) prop(WIDTH, view.width());
	}

	public self toWidth(int width) {
		options.put(WIDTH, width);
		return (self) this;
	}

	@Override
	public void run() {
		View v = view.view();
		int h = v.getHeight(), w = v.getWidth();

		AnimatorSet as = new AnimatorSet();
		as.playTogether(ValueAnimator.ofObject(new WidthEvaluator(v), w,
				prop(WIDTH, w)), ValueAnimator.ofObject(new HeightEvaluator(v),
				h, prop(HEIGHT, h)));

		run(as);
	}

	private class WidthEvaluator extends IntEvaluator {

		private View v;

		public WidthEvaluator(View v) {
			this.v = v;
		}

		@Override
		public Integer evaluate(float fraction, Integer startValue,
				Integer endValue) {
			int num = (Integer) super.evaluate(fraction, startValue, endValue);
			ViewGroup.LayoutParams params = v.getLayoutParams();
			params.width = num;
			v.setLayoutParams(params);
			return num;
		}
	}

	private class HeightEvaluator extends IntEvaluator {

		private View v;

		public HeightEvaluator(View v) {
			this.v = v;
		}

		@Override
		public Integer evaluate(float fraction, Integer startValue,
				Integer endValue) {
			int num = (Integer) super.evaluate(fraction, startValue, endValue);
			ViewGroup.LayoutParams params = v.getLayoutParams();
			params.height = num;
			v.setLayoutParams(params);
			return num;
		}
	}

}
