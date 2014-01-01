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

package vapor.content;

import vapor.app.VaporService;
import vapor.core.$;
import android.app.Service;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

public class VaporServiceConnection implements ServiceConnection {

	private VaporService service;
	private VaporService.VaporBinder binder;
	private ComponentName componentName;
	private Class<? extends Service> serviceClass;

	public VaporServiceConnection(Class<? extends Service> serviceClass) {
		// We pass the Service Class here so it's ready even before connection
		// callback
		this.serviceClass = serviceClass;
	}

	public void onServiceConnected(ComponentName name, IBinder service) {
		try {
			binder = (VaporService.VaporBinder) service;
			this.service = binder.service();

			componentName = name;

			Log.i("VaporServiceConnection.onServiceConnected(ComponentName,IBinder)",
					name + " connected to " + $.shortName(this.service));
		} catch (ClassCastException ex) {
			Log.e("VaporServiceConnection.onServiceConnected(ComponentName,IBinder)",
					"IBinder must be instance of VaporService.VaporBinder.");
			throw ex; // rethrow to cancel current operation
		}
	}

	public void onServiceDisconnected(ComponentName name) {
		Log.i("VaporServiceConnection.onServiceDisconnected(ComponentName)",
				name + " disconnected from " + $.shortName(this.service));
		service = null;
	}

	public ComponentName componentName() {
		return componentName;
	}

	public VaporService.VaporBinder binder() {
		return binder;
	}

	public VaporService service() {
		return service;
	}

	public boolean bound() {
		return service != null;
	}

	public Class<? extends Service> serviceClass() {
		return serviceClass;
	}
}