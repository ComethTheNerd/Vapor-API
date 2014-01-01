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
import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class VaporToast {

	private String text = "";
	private int[] gravity = new int[] { Gravity.TOP, 0, 0 };
	private final int GRAV = 0, X = 1, Y = 2;
	private int duration = Toast.LENGTH_SHORT;
	private VaporView<? extends View, ?> view;

	public VaporToast(String text) {
		this.text = text;
	}

	public int duration() {
		return duration;
	}

	public VaporToast duration(int duration) {
		this.duration = duration;
		return this;
	}

	public int gravity() {
		return this.gravity[GRAV];
	}

	public VaporToast gravity(int gravity) {
		this.gravity[GRAV] = gravity;
		return this;
	}

	public VaporToast gravity(int gravity, int xy) {
		this.gravity[GRAV] = gravity;
		this.gravity[X] = xy;
		this.gravity[Y] = xy;
		return this;
	}

	public VaporToast gravity(int gravity, int x, int y) {
		this.gravity[GRAV] = gravity;
		this.gravity[X] = x;
		this.gravity[Y] = y;
		return this;
	}

	public VaporToast show() {
		final Activity act = $.act();

		// Toast needs to run on UI thread, not Worker thread
		act.runOnUiThread(new Runnable() {
			public void run() {
				Toast toast = Toast.makeText(act, text, duration);
				if (view != null)
					toast.setView(view.view());
				toast.setGravity(gravity[GRAV], gravity[X], gravity[Y]);
				toast.show();
			}
		});

		return this;
	}

	public VaporToast text(String text) {
		this.text = text;
		return this;
	}

	public VaporView<? extends View, ?> view() {
		return view;
	}

	public VaporToast view(VaporView<? extends View, ?> view) {
		this.view = view;
		return this;
	}

	public int x() {
		return gravity[X];
	}

	public VaporToast x(int x) {
		gravity[X] = x;
		return this;
	}

	public int y() {
		return gravity[Y];
	}

	public VaporToast y(int y) {
		gravity[Y] = y;
		return this;
	}

}
