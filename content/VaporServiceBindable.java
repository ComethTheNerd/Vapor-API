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

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;

public interface VaporServiceBindable {

	/**
	 * Obtain a VaporServiceConnection instance for a connection to the given
	 * service class
	 * 
	 * @param serviceClass
	 *            The class for the service to bind to
	 * @return a VaporServiceConnection instance for a connection to the given
	 *         service class
	 */
	public VaporServiceConnection vsc(Class<? extends Service> serviceClass);

	/**
	 * Returns the VaporServiceConnection instance that was used to bind the
	 * Service instance of the given class
	 * 
	 * @param serviceClass
	 *            The class for the service to retrieve the binding for
	 * @return the VaporServiceConnection instance that was used to bind the
	 *         Service instance of the given class, or null if no such binding
	 *         exists
	 */
	public <T extends Service> VaporServiceConnection binding(
			Class<T> serviceClass);

	/**
	 * Returns the bound Service that is an instance of the given class
	 * 
	 * @param serviceClass
	 *            The class of the Service instance that will be retrieved
	 * @return the bound Service that is an instance of the given class, or null
	 *         if no such binding exists
	 */
	public <T extends Service> T service(Class<T> serviceClass);

	/**
	 * Connect to an application service, creating it if needed. This defines a
	 * dependency between your application and the service. The given connection
	 * will receive the service object when it is created and be told if it dies
	 * and restarts. The service will be considered required by the system only
	 * for as long as the calling context exists.
	 * 
	 * @param intent
	 *            Identifies the service to connect to. The Intent may specify
	 *            either an explicit component name, or a logical description
	 *            (action, category, etc) to match an IntentFilter published by
	 *            a service.
	 * @param connection
	 *            Receives information as the service is started and stopped.
	 *            This must be a valid ServiceConnection object; it must not be
	 *            null.
	 * @param flags
	 *            Operation options for the binding. May be 0, BIND_AUTO_CREATE,
	 *            BIND_DEBUG_UNBIND, BIND_NOT_FOREGROUND, BIND_ABOVE_CLIENT,
	 *            BIND_ALLOW_OOM_MANAGEMENT, or BIND_WAIVE_PRIORITY.
	 * @return If you have successfully bound to the service, true is returned;
	 *         false is returned if the connection is not made so you will not
	 *         receive the service object.
	 */
	public boolean bindService(Intent intent, ServiceConnection connection,
			int flags);

	/**
	 * Convenience method for unbinding from a Service that is an instance of
	 * the given class. The Service MUST have been bound by a
	 * VaporServiceConnection instance for this to work.
	 * 
	 * @param serviceClass
	 *            The class of the Service that will be unbound
	 */
	public void unbindService(Class<? extends Service> serviceClass);

	/**
	 * 
	 * @param connection
	 */
	public void unbindService(ServiceConnection connection);

	/**
	 * Unbind all currently bound services
	 * 
	 */
	public void unbindServices();
}
