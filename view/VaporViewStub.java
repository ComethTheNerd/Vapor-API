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

package vapor.view;

import vapor.core.$;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;

/**
 * Fluent Vapor companion to ViewStub, an invisible, zero-sized View that can be
 * used to lazily inflate layout resources at runtime.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from ViewStub
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporViewStub<T extends ViewStub, self extends VaporViewStub<T, self>>
		extends VaporView<T, self> {

	public VaporViewStub(int id) {
		super(id);
	}

	public VaporViewStub(T viewStub) {
		super(viewStub);
	}

	/**
	 * Inflates the layout resource identified by layoutRes() and replaces this
	 * StubbedView in its parent by the inflated layout resource.
	 * 
	 * @return The inflated layout resource.
	 */
	public VaporView<? extends View, ?> inflate() {
		return $.vapor(view.inflate());
	}

	/**
	 * Specifies the inflate listener to be notified after this ViewStub
	 * successfully inflated its layout resource.
	 * 
	 * @param inflateListener
	 *            The OnInflateListener to notify of successful inflation.
	 * @return self
	 */
	public self inflate(vapor.listeners.view.viewstub.$inflate inflateListener) {
		view.setOnInflateListener(inflateListener);
		return (self) this;
	}

	/**
	 * Returns the id taken by the inflated view. If the inflated id is NO_ID,
	 * the inflated view keeps its original id.
	 * 
	 * @return A positive integer used to identify the inflated view or NO_ID if
	 *         the inflated view should keep its id.
	 */
	public int inflateId() {
		return view.getInflatedId();
	}

	/**
	 * Defines the id taken by the inflated view. If the inflated id is NO_ID,
	 * the inflated view keeps its original id.
	 * 
	 * @param id
	 *            A positive integer used to identify the inflated view or NO_ID
	 *            if the inflated view should keep its id.
	 * @return self
	 */
	public self inflateId(int id) {
		view.setInflatedId(id);
		return (self) this;
	}

	/**
	 * Get current LayoutInflater used in inflate().
	 * 
	 * @return current LayoutInflater used in inflate().
	 */
	public LayoutInflater inflater() {
		return view.getLayoutInflater();
	}

	/**
	 * Set LayoutInflater to use in inflate(), or null to use the default.
	 * 
	 * @param inflater
	 * @return self
	 */
	public self inflater(LayoutInflater inflater) {
		view.setLayoutInflater(inflater);
		return (self) this;
	}

	/**
	 * Returns the layout resource that will be used by viz(int) or inflate() to
	 * replace this StubbedView in its parent by another view.
	 * 
	 * @return The layout resource identifier used to inflate the new View.
	 */
	public int layoutRes() {
		return view.getLayoutResource();
	}

	/**
	 * Specifies the layout resource to inflate when this StubbedView becomes
	 * visible or invisible or when inflate() is invoked. The View created by
	 * inflating the layout resource is used to replace this StubbedView in its
	 * parent.
	 * 
	 * @param layoutResource
	 *            A valid layout resource identifier (different from 0.)
	 * @return self
	 */
	public self layoutRes(int layoutResource) {
		view.setLayoutResource(layoutResource);
		return (self) this;
	}

}