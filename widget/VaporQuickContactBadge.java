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
import android.net.Uri;
import android.view.View;
import android.widget.QuickContactBadge;

/**
 * Fluent Vapor companion to QuickContactBadge, a widget used to show an image
 * with the standard QuickContact badge and on-click behavior.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from QuickContactBadge
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporQuickContactBadge<T extends QuickContactBadge, self extends VaporQuickContactBadge<T, self>>
		extends VaporImageView<T, self> implements vapor.listeners.view.$click {

	public VaporQuickContactBadge(int id) {
		super(id);
	}

	public VaporQuickContactBadge(T quickContactBadge) {
		super(quickContactBadge);
	}

	/**
	 * Fluent equivalent Vapor method for invoking onClick(View), called when a
	 * view has been clicked
	 * 
	 * @param view
	 *            The view that was clicked.
	 * @return self
	 */
	public self click(VaporView<? extends View, ?> view) {
		this.view.onClick(view.view());
		return (self) this;
	}

	/**
	 * Resets the contact photo to the default state.
	 * 
	 * @return self
	 */
	public self defImg() {
		view.setImageToDefault();
		return (self) this;
	}

	/**
	 * Assign a contact based on an email address. This should only be used when
	 * the contact's URI is not available, as an extra query will have to be
	 * performed to lookup the URI based on the email.
	 * 
	 * @param emailAddress
	 *            The email address of the contact.
	 * @param lazyLookup
	 *            If this is true, the lookup query will not be performed until
	 *            this view is clicked.
	 * @return self
	 */
	public self emailContact(String emailAddress, boolean lazyLookup) {
		view.assignContactFromEmail(emailAddress, lazyLookup);
		return (self) this;
	}

	/**
	 * Set a list of specific MIME-types to exclude and not display. For
	 * example, this can be used to hide the CONTENT_ITEM_TYPE profile icon.
	 * 
	 * @param excludeMimes
	 * @return self
	 */
	public self excludeMimes(String[] excludeMimes) {
		view.setExcludeMimes(excludeMimes);
		return (self) this;
	}

	/**
	 * Assign a contact based on a phone number. This should only be used when
	 * the contact's URI is not available, as an extra query will have to be
	 * performed to lookup the URI based on the phone number.
	 * 
	 * @param phoneNumber
	 *            The phone number of the contact.
	 * @param lazyLookup
	 *            If this is true, the lookup query will not be performed until
	 *            this view is clicked.
	 * @return self
	 */
	public self phoneContact(String phoneNumber, boolean lazyLookup) {
		view.assignContactFromPhone(phoneNumber, lazyLookup);
		return (self) this;
	}

	/**
	 * Assign the contact uri that this QuickContactBadge should be associated
	 * with. Note that this is only used for displaying the QuickContact window
	 * and won't bind the contact's photo for you. Call img(Drawable) to set the
	 * photo.
	 * 
	 * @param contactUri
	 *            Either a CONTENT_URI or CONTENT_LOOKUP_URI style URI.
	 * @return self
	 */
	public self uriContact(Uri contactUri) {
		view.assignContactUri(contactUri);
		return (self) this;
	}

	/* INTERFACE : OnClickListener */
	/**
	 * NOTE: This method serves to satisfy the OnClickListener interface, use
	 * the equivalent VAPOR FLUENT method clicked(VaporView<? extends View,?>)
	 * instead
	 */
	@Override
	public void onClick(View view) {
		click($.vapor(view));

	}

}
