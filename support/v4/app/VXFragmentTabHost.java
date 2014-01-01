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

package vapor.support.v4.app;

import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.PagerTitleStrip;
import vapor.support.v4.view.VXPagerTitleStrip;
import vapor.support.v4.view.VaporPagerTitleStrip;
import vapor.support.v4.view.VaporXPagerTabStrip;
import vapor.support.v4.view.VaporXPagerTitleStrip;

/**
 * Fluent variadic Vapor companion to FragmentTabHost, a special TabHost that allows the use of Fragment objects for its tab content.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from FragmentTabHost
 * @param <X>
 *            A Vapor type derived from VaporFragmentTabHost
 */
public class VXFragmentTabHost<T extends FragmentTabHost, X extends VaporFragmentTabHost<T, ?>>
extends VaporXFragmentTabHost<T, X, VXFragmentTabHost<T, X>> {
	
	public VXFragmentTabHost(Integer... ids) {
		super(ids);
	}

	public VXFragmentTabHost(T... fragmentTabHosts) {
		super(fragmentTabHosts);
	}

	public VXFragmentTabHost(X... vaporFragmentTabHosts) {
		super(vaporFragmentTabHosts);
	}

	public VXFragmentTabHost(VaporXFragmentTabHost<T, X, ?>... vaporXFragmentTabHosts) {
		super(vaporXFragmentTabHosts);
	}

	public VXFragmentTabHost(Object... fragmentTabHostItems) {
		super(fragmentTabHostItems);
	}
}
