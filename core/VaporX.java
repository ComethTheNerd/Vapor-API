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

package vapor.core;

import java.util.ArrayList;

/**VaporX is a framework for encapsulating a set of related components, so as to perform the same operation on all constituent 
* members via a single method invocation. Rather than instantiate a VaporX instance directly, you should use the appropriate '$.' method.
*
* @author Darius H (darius@vapor-api.com)
* @since 1.0
*/
public abstract class VaporX<X, self extends VaporX<X, self>> {

	protected final ArrayList<X> members;
	{
		members = new ArrayList<X>();
	}

	/**
	 * Returns true iff the given member exists in the underlying collection
	 * 
	 * @param member
	 * @return true iff the given member exists in the underlying collection
	 */
	public boolean contains(X member) {

		return members.contains(member);
	}

	/**
	 * Returns the index of the given component in the underlying collection
	 * 
	 * @param member
	 *            The component to search for
	 * @return the index of the component in the underlying collection, or -1 if
	 *         not found
	 */
	public int index(X member) {
		return members.indexOf(member);
	}

	/**
	 * Returns the underlying VaporView collection
	 * 
	 * @return the underlying VaporView collection
	 */
	public ArrayList<X> members() {
		return members;
	}

	/**
	 * A VaporX implementation must support instantiation/member addition
	 * through any combination of acceptable selector types. Pass the mixed
	 * combination to this method, it should be set up to correctly resolve and
	 * deal with the mixed types. It should at the very least support other
	 * instances of this VaporX type.
	 * 
	 * @param mixedSelectors
	 *            A combination of any of the acceptable selector types for the
	 *            particular VaporX implementation
	 * @return self
	 */
	protected abstract self add(Object... mixedSelectors);

	/**
	 * Returns an aggregation of the returns values obtained from applying the
	 * given map function to all members of the underlying set. Use each($each)
	 * if no return value is required from the function
	 * 
	 * @param mapFunction
	 *            The function to run on every member of the underlying set
	 * @return an aggregation of the returns values obtained from applying the
	 *         given map function to all members of the underlying set
	 */
	public <M> ArrayList<M> map(vapor.core.$map<X, M> mapFunction) {
		int _size = this.members.size();
		ArrayList<M> _returns = new ArrayList<M>(_size);

		for (int _index = 0; _index < _size; _index++)
			// aggregate results
			_returns.add(mapFunction.map(this.members.get(_index)));

		return _returns;
	}

	/**
	 * Applies the given each function to every member of the underlying set.
	 * Use map($map) if a return value is required from the function.
	 * 
	 * @param eachFunction
	 *            The function to run on every member of the underlying set
	 * @return self
	 */
	public self each($each<X> eachFunction) {
		for (X member : members)
			eachFunction.each(member);
		return (self) this;
	}

}
