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

import vapor.os.VaporBundle;
import vapor.widget.VaporTabHost;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.widget.TabHost;

/**
 * Fluent Vapor companion to FragmentTabHost, a special TabHost that allows the use of Fragment objects for its tab content.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from FragmentTabHost
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporFragmentTabHost<T extends FragmentTabHost, self extends VaporFragmentTabHost<T, self>>
extends VaporTabHost<T, self> implements vapor.listeners.widget.tabhost.$change{

	public VaporFragmentTabHost(int id) {
		super(id);
	}

	public VaporFragmentTabHost(T fragmentTabHost) {
		super(fragmentTabHost);
	}
	
	/**
	 * 
	 * @param context
	 * @param fragmentManager
	 * @return self
	 */
	public self setup(Context context, FragmentManager fragmentManager){
		view.setup(context,fragmentManager);
		return (self)this;
	}
	
	/**
	 * 
	 * @param context
	 * @param fragmetManager
	 * @param containerId
	 * @return self
	 */
	public self setup(Context context, FragmentManager fragmentManager, int containerId){
		view.setup(context,fragmentManager,containerId);
		return (self)this;
	}
	
	/**Adds a tab
	 * 
	 * @param tabSpec
	 * @param clss
	 * @param args
	 * @return self
	 */
	public self tab(TabHost.TabSpec tabSpec, Class<?> clss, VaporBundle args){
		view.addTab(tabSpec, clss, args.bundle());
		return (self)this;
	}
	
	/**Fluent equivalent Vapor method for invoking onTabChanged(String) 
	 * 
	 * @param tabId
	 * @return self
	 */
	public self tabChanged(String tabId){
		view.onTabChanged(tabId);
		return (self)this;
	}
	
	/* INTERFACE : TabHost.OnTabChangeListener */
	/**
	 * NOTE: This method serves to satisfy the TabHost.OnTabChangeListener interface, use
	 * the equivalent VAPOR FLUENT method tabChanged(String) instead
	 */
	@Override
	public void onTabChanged(String tabId) {
		tabChanged(tabId);
	}
}