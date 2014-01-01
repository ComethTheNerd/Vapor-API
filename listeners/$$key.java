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

package vapor.listeners;

import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;

public class $$key implements
		vapor.listeners.inputmethodservice.keyboardview.$key,
		vapor.listeners.text.method.$key, vapor.listeners.view.$key {

	@Override
	public void onKey(int primaryCode, int[] keyCodes) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPress(int primaryCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRelease(int primaryCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onText(CharSequence text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void swipeDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void swipeLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void swipeRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void swipeUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearMetaKeyState(View arg0, Editable arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getInputType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean onKeyDown(View arg0, Editable arg1, int arg2, KeyEvent arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onKeyOther(View arg0, Editable arg1, KeyEvent arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onKeyUp(View arg0, Editable arg1, int arg2, KeyEvent arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}
