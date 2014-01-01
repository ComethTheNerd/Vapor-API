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

package vapor.listeners.hookframework;

import vapor.os.VaporBundle;

public abstract class $hookee<T> {

	// final so that it cannot be incorrectly assigned to in client code
	protected final T $subject;

	public $hookee(T subject) {
		this.$subject = subject;
	}

	public $hookee() {
		this.$subject = null;
	}

	/**
	 * Returns the subject of this Hookee, or null if no subject was supplied to
	 * the constructor
	 * 
	 * @return the subject of this Hookee, or null if no subject was supplied to
	 *         the constructor
	 */
	public final T subject() {
		return $subject;
	}

	/**
	 * This event occurs whenever the Hook mapped to the given hookName is
	 * fired.
	 * 
	 * @param hookName
	 *            The name to which the Hook raising this event is mapped to in
	 *            the Vapor Hook Engine
	 * @param args
	 *            Data associated with this event, sent by the Hook
	 */
	public abstract void call(String hookName, VaporBundle args);

	/**
	 * This event signifies that the Hook mapped to the given hookName is about
	 * to be deleted. This means that no further events will be raised by the
	 * Hook in future.
	 * 
	 * @param hookName
	 *            The name to which the Hook raising this event is mapped to in
	 *            the Vapor Hook Engine
	 * @param args
	 *            Data associated with this event, sent by the Hook
	 */
	public abstract void delete(String hookName, VaporBundle args);

	@Override
	public String toString() {
		return "Hookee:" + this.hashCode() + ", subject: " + $subject;
	}
}
