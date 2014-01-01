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

package vapor.core.hookframework;

import java.util.ArrayList;
import java.util.List;

import vapor.core.$;
import vapor.core.VaporX;
import vapor.listeners.hookframework.$hookee;
import vapor.listeners.hookframework.$hooker;
import vapor.os.VaporBundle;

/**
 * A VaporXHook allows clients to manage arbitrary groupings of VaporHooks simultaneously
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 */
public final class VaporXHook extends VaporX<VaporHook, VaporXHook> {

	public VaporXHook(String... hookNames) {
		for (String hookName : hookNames)
			this.members.add($.hook(hookName));
	}

	public VaporXHook(VaporHook... hooks) {
		for (VaporHook hook : hooks)
			this.members.add(hook);
	}

	public VaporXHook(Object... hookItems) {
		add(hookItems);
	}

	protected VaporXHook add(Object... mixedSelectors) {

		for (Object selector : mixedSelectors) {
			try {
				// first we try to cast to VaporHook
				members.add((VaporHook) selector);
				continue;
			} catch (ClassCastException e1) {
				try {
					// then try to cast it to String
					members.add($.hook((String) selector));
					continue;
				} catch (ClassCastException e2) {
					// treat it as another VaporXHook instance
					members.addAll(((VaporXHook) selector).members());
					continue;
				}
			}

		}
		return this;

	}

	/**
	 * Returns whether this Hook will be automatically deleted when there are no
	 * longer Hookees attached
	 * 
	 * @return whether this Hook will be automatically deleted when there are no
	 *         longer Hookees attached
	 */
	public ArrayList<Boolean> autoDelete() {
		int _size = members.size();
		ArrayList<Boolean> _returns = new ArrayList<Boolean>(_size);

		for (int _index = 0; _index < _size; _index++) {
			_returns.add(members.get(_index).autoDelete());
		}
		return _returns;
	}

	/**
	 * Sets whether this Hook will be automatically deleted when there are no
	 * longer Hookees attached
	 * 
	 * NOTE: This operation may require adminAuth.
	 * 
	 * @param autoDelete
	 *            true to automatically delete this Hook when there are no
	 *            longer Hookees attached, false otherwise
	 * @return self
	 */
	public VaporXHook autoDelete(boolean autoDelete) {

		for (VaporHook hook : members)
			hook.autoDelete(autoDelete);

		return this;
	}

	/**
	 * Sets whether this Hook will be automatically deleted when there are no
	 * longer Hookees attached
	 * 
	 * @param adminAuth
	 *            authorization for this operation
	 * @param autoDelete
	 *            true to automatically delete this Hook when there are no
	 *            longer Hookees attached, false otherwise
	 * @return self
	 */
	public VaporXHook autoDelete(String adminAuth, boolean autoDelete) {
		for (VaporHook hook : members)
			hook.autoDelete(adminAuth, autoDelete);

		return this;
	}

	/**
	 * Sets the Hooker object that will be informed when the hook is fired. The
	 * Hooker is used to affect and examine the args being sent out by the VHF
	 * to the Hookees of this Hook
	 * 
	 * @param hooker
	 *            the Hooker object that will be informed when the hook is fired
	 * @return self
	 */
	public VaporXHook hooker($hooker hooker) {
		for (VaporHook hook : members)
			hook.hooker(hooker);

		return this;
	}

	/**
	 * Sets the Hooker object that will be informed when the hook is fired. The
	 * Hooker is used to affect and examine the args being sent out by the VHF
	 * to the Hookees of this Hook
	 * 
	 * @param adminAuth
	 *            the admin password that may be required to apply this change
	 * @param hooker
	 *            the Hooker object that will be informed when the hook is fired
	 * @return self
	 */
	public VaporXHook hooker(String adminAuth, $hooker hooker) {
		for (VaporHook hook : members)
			hook.hooker(adminAuth, hooker);
		return this;
	}

	/**
	 * Verify whether the given admin auth matches the stored admin auth for
	 * this Hook
	 * 
	 * @param adminAuthOffering
	 *            the password to match against
	 * @return true if the offered admin auth matches the stored admin auth for
	 *         this Hook, false otherwise
	 */
	ArrayList<Boolean> adminAccess(String adminAuthOffering) {
		int _size = members.size();
		ArrayList<Boolean> _returns = new ArrayList<Boolean>(_size);

		for (int _index = 0; _index < _size; _index++) {
			_returns.add(members.get(_index).adminAccess(adminAuthOffering));
		}
		return _returns;
	}

	/**
	 * Verify whether the given subscriber auth matches the stored subscriber
	 * auth for this Hook
	 * 
	 * @param subscriberAuthOffering
	 *            the password to match against
	 * @return true if the offered subscriber auth matches the stored subscriber
	 *         auth for this Hook, false otherwise
	 */
	ArrayList<Boolean> subscriberAccess(String subscriberAuthOffering) {
		int _size = members.size();
		ArrayList<Boolean> _returns = new ArrayList<Boolean>(_size);

		for (int _index = 0; _index < _size; _index++) {
			_returns.add(members.get(_index).subscriberAccess(
					subscriberAuthOffering));
		}
		return _returns;
	}

	/**
	 * Returns how many time this Hook has been fired
	 * 
	 * @return the number of times this Hook has been fired
	 */
	public ArrayList<Integer> callCount() {
		int _size = members.size();
		ArrayList<Integer> _returns = new ArrayList<Integer>(_size);

		for (int _index = 0; _index < _size; _index++) {
			_returns.add(members.get(_index).callCount());
		}
		return _returns;
	}

	/**
	 * Returns whether this Hook can be deleted.
	 * 
	 * NOTE: This operation may require adminAuth.
	 * 
	 * @return true if the Hook can be deleted, false otherwise
	 */
	public ArrayList<Boolean> deleteable() {
		int _size = members.size();
		ArrayList<Boolean> _returns = new ArrayList<Boolean>(_size);

		for (int _index = 0; _index < _size; _index++) {
			_returns.add(members.get(_index).deleteable());
		}
		return _returns;
	}

	/**
	 * Sets whether this Hook can be deleted.
	 * 
	 * NOTE: This operation may require adminAuth.
	 * 
	 * @param canDelete
	 *            Whether the Hook can be deleted
	 * @return self
	 */
	public VaporXHook deleteable(boolean canDelete) {
		for (VaporHook hook : members)
			hook.deleteable(canDelete);

		return this;
	}

	/**
	 * Sets whether this Hook can be deleted.
	 * 
	 * @param adminAuth
	 *            the admin password for this Hook
	 * @param canDelete
	 *            Whether the Hook can be deleted
	 * @return self
	 */
	public VaporXHook deleteable(String adminAuth, boolean canDelete) {
		for (VaporHook hook : members)
			hook.deleteable(adminAuth, canDelete);
		return this;
	}

	/**
	 * Deletes this Hook.
	 * 
	 * NOTE: This operation may require adminAuth NOTE: Deleting a Hook raises
	 * the lastCall(...) notification, not call(...)
	 * 
	 * @return true if the Hook was deleted, false otherwise
	 */
	public ArrayList<Boolean> delete() {
		int _size = members.size();
		ArrayList<Boolean> _returns = new ArrayList<Boolean>(_size);

		for (int _index = 0; _index < _size; _index++) {
			_returns.add(members.get(_index).delete());
		}
		return _returns;
	}

	/**
	 * Deletes this Hook.
	 * 
	 * NOTE: Deleting a Hook raises the lastCall(...) notification, not
	 * call(...)
	 * 
	 * @param adminAuth
	 *            authorization for this operation
	 * @return true if the Hook was deleted, false otherwise
	 */
	public ArrayList<Boolean> delete(String adminAuth) {
		int _size = members.size();
		ArrayList<Boolean> _returns = new ArrayList<Boolean>(_size);

		for (int _index = 0; _index < _size; _index++) {
			_returns.add(members.get(_index).delete(adminAuth));
		}
		return _returns;
	}

	/**
	 * Deletes this Hook, and passes the given VaporBundle as args to the
	 * lastCall(...) notification sent to all Hookees prior to deletion.
	 * 
	 * NOTE: This operation may require adminAuth NOTE: Deleting a Hook raises
	 * the lastCall(...) notification, not call(...)
	 * 
	 * @param args
	 *            the args to pass to Hookees during the lastCall(...) event
	 * @return true if the Hook was deleted, false otherwise
	 */
	public ArrayList<Boolean> delete(VaporBundle args) {
		int _size = members.size();
		ArrayList<Boolean> _returns = new ArrayList<Boolean>(_size);

		for (int _index = 0; _index < _size; _index++) {
			_returns.add(members.get(_index).delete(args));
		}
		return _returns;
	}

	/**
	 * Deletes this Hook, and passes the given VaporBundle as args to the
	 * lastCall(...) notification sent to all Hookees prior to deletion.
	 * 
	 * NOTE: Deleting a Hook raises the lastCall(...) notification, not
	 * call(...)
	 * 
	 * @param adminAuth
	 *            authorization for this operation
	 * @param args
	 *            the args to pass to Hookees during the lastCall(...) event
	 * @return true if the Hook was deleted, false otherwise
	 */
	public ArrayList<Boolean> delete(String adminAuth, VaporBundle args) {
		int _size = members.size();
		ArrayList<Boolean> _returns = new ArrayList<Boolean>(_size);

		for (int _index = 0; _index < _size; _index++) {
			_returns.add(members.get(_index).delete(adminAuth, args));
		}
		return _returns;
	}

	/**
	 * Fires the Hook.
	 * 
	 * NOTE: This operation may require adminAuth
	 * 
	 * @return true if the Hook was fired, false otherwise
	 */
	public ArrayList<Boolean> fire() {
		int _size = members.size();
		ArrayList<Boolean> _returns = new ArrayList<Boolean>(_size);

		for (int _index = 0; _index < _size; _index++) {
			_returns.add(members.get(_index).fire());
		}
		return _returns;

	}

	/**
	 * Fires the Hook and passes the given args to all Hookees in the call(...)
	 * notification.
	 * 
	 * NOTE: A Hooker may alter these args before they are passed to Hookees
	 * NOTE: This operation may require adminAuth
	 * 
	 * @param args
	 *            the args to pass to Hookees when this Hook is fired
	 * @return true if the Hook was fired, false otherwise
	 */
	public ArrayList<Boolean> fire(VaporBundle args) {
		int _size = members.size();
		ArrayList<Boolean> _returns = new ArrayList<Boolean>(_size);

		for (int _index = 0; _index < _size; _index++) {
			_returns.add(members.get(_index).fire(args));
		}
		return _returns;
	}

	/**
	 * Fires the Hook using the given admin auth.
	 * 
	 * @param adminAuth
	 *            authorization for this operation
	 * @return true if the Hook was fired, false otherwise
	 */
	public ArrayList<Boolean> fire(String adminAuth) {
		int _size = members.size();
		ArrayList<Boolean> _returns = new ArrayList<Boolean>(_size);

		for (int _index = 0; _index < _size; _index++) {
			_returns.add(members.get(_index).fire(adminAuth));
		}
		return _returns;
	}

	/**
	 * Fires the Hook using the given admin auth, and passes the given args to
	 * all Hookees in the call(...) notification.
	 * 
	 * NOTE: A Hooker may alter these args before they are passed to Hookees
	 * 
	 * @param adminAuth
	 *            authorization for this operation
	 * @param args
	 *            the args to pass to Hookees when this Hook is fired
	 * @return true if the Hook was fired, false otherwise
	 */
	public synchronized ArrayList<Boolean> fire(String adminAuth,
			VaporBundle args) {

		int _size = members.size();
		ArrayList<Boolean> _returns = new ArrayList<Boolean>(_size);

		for (int _index = 0; _index < _size; _index++) {
			_returns.add(members.get(_index).fire(adminAuth, args));
		}
		return _returns;
	}

	/**
	 * Check whether the given Object is the $subject of a Hookee attached to
	 * this Hook
	 * 
	 * @param subscriber
	 *            the Object to check for in the Hookees attached to this hook
	 * @return true if the given Object is referenced in a Hookee attached to
	 *         this hook
	 */
	public ArrayList<Boolean> hooked(Object observer) {
		int _size = members.size();
		ArrayList<Boolean> _returns = new ArrayList<Boolean>(_size);

		for (int _index = 0; _index < _size; _index++) {
			_returns.add(members.get(_index).hooked(observer));
		}
		return _returns;
	}

	/**
	 * Check whether the given Hookee is attached to this Hook
	 * 
	 * @param observer
	 *            the Hookee to check for
	 * @return true if the given Hookee is attached to this Hook, false
	 *         otherwise
	 */
	public <T> ArrayList<Boolean> hooked($hookee<T> observer) {
		int _size = members.size();
		ArrayList<Boolean> _returns = new ArrayList<Boolean>(_size);

		for (int _index = 0; _index < _size; _index++) {
			_returns.add(members.get(_index).hooked(observer));
		}
		return _returns;
	}

	/**
	 * Register the given Hookee as a subscriber to this Hook
	 * 
	 * NOTE: This operation may require subscriberAuth
	 * 
	 * @param observer
	 *            the Hookee to register to this Hook
	 * @return self
	 */
	public final <T> VaporXHook hookIn($hookee<T> observer) {
		for (VaporHook hook : members)
			hook.hookIn(observer);

		return this;
	}

	/**
	 * Register the given Hookee as a subscriber to this Hook using the given
	 * subscriber auth
	 * 
	 * @param subscriberAuth
	 *            authorization for this operation
	 * @param observer
	 *            the Hookee to register to this Hook
	 * @return self
	 */
	public synchronized final <T> VaporXHook hookIn(String subscriberAuth,
			$hookee<T> observer) {

		for (VaporHook hook : members)
			hook.hookIn(subscriberAuth, observer);

		return this;
	}

	/**
	 * Registers the given Hookees as subscribers to this Hook
	 * 
	 * NOTE: This operation may require subscriberAuth
	 * 
	 * @param observers
	 *            the Hookees to register to this Hook
	 * @return self
	 */
	public final VaporXHook hookIn(ArrayList<$hookee<?>> observers) {
		for (VaporHook hook : members)
			hook.hookIn(observers);

		return this;
	}

	/**
	 * Registers the given Hookees as subscribers to this Hook
	 * 
	 * NOTE: This operation may require subscriberAuth
	 * 
	 * @param observers
	 *            the Hookees to register to this Hook
	 * @return self
	 */
	public final VaporXHook hookIn($hookee<?>... observers) {
		for (VaporHook hook : members)
			hook.hookIn(observers);

		return this;
	}

	/**
	 * Registers the given Hookees as subscribers to this Hook
	 * 
	 * @param subscriberAuth
	 *            authorization for this operation
	 * @param observers
	 *            the Hookees to register to this Hook
	 * @return self
	 */
	public final VaporXHook hookIn(String subscriberAuth,
			ArrayList<$hookee<?>> observers) {

		for (VaporHook hook : members)
			hook.hookIn(subscriberAuth, observers);

		return this;
	}

	/**
	 * Registers the given Hookees as subscribers to this Hook
	 * 
	 * @param subscriberAuth
	 *            authorization for this operation
	 * @param observers
	 *            the Hookees to register to this Hook
	 * @return self
	 */
	public final VaporXHook hookIn(String subscriberAuth,
			$hookee<?>... observers) {

		for (VaporHook hook : members)
			hook.hookIn(subscriberAuth, observers);

		return this;
	}

	/**
	 * Unregister the given Hookee from this Hook
	 * 
	 * NOTE: This operation may require subscriberAuth
	 * 
	 * @param observer
	 *            the Hookee to unregister from this Hook
	 * @return self
	 */
	public final <T> VaporXHook hookOut($hookee<T> observer) {
		for (VaporHook hook : members)
			hook.hookOut(observer);

		return this;
	}

	/**
	 * Unregister the given Hookee from this Hook
	 * 
	 * @param subscriberAuth
	 *            authorization for this operation
	 * @param observer
	 *            the Hookee to unregister from this Hook
	 * @return self
	 */
	public synchronized final <T> VaporXHook hookOut(String subscriberAuth,
			$hookee<T> observer) {

		for (VaporHook hook : members)
			hook.hookOut(subscriberAuth, observer);

		return this;
	}

	/**
	 * Unregisters the given Hookees from this Hook
	 * 
	 * NOTE: This operation may require subscriberAuth
	 * 
	 * @param observers
	 *            the Hookees to unregister from this Hook
	 * @return self
	 */
	public final VaporXHook hookOut(ArrayList<$hookee<?>> observers) {
		for (VaporHook hook : members)
			hook.hookOut(observers);

		return this;
	}

	/**
	 * Unregisters the given Hookees from this Hook
	 * 
	 * NOTE: This operation may require subscriberAuth
	 * 
	 * @param observers
	 *            the Hookees to unregister from this Hook
	 * @return self
	 */
	public final VaporXHook hookOut($hookee<?>... observers) {
		for (VaporHook hook : members)
			hook.hookOut(observers);

		return this;
	}

	/**
	 * Unregisters the given Hookees from this Hook
	 * 
	 * @param subscriberAuth
	 *            authorization for this operation
	 * @param observers
	 *            the Hookees to unregister from this Hook
	 * @return self
	 */
	public final VaporXHook hookOut(String subscriberAuth,
			ArrayList<$hookee<?>> observers) {

		for (VaporHook hook : members)
			hook.hookOut(subscriberAuth, observers);

		return this;
	}

	/**
	 * Unregisters the given Hookees from this Hook
	 * 
	 * @param subscriberAuth
	 *            authorization for this operation
	 * @param observers
	 *            the Hookees to unregister from this Hook
	 * @return self
	 */
	public final VaporXHook hookOut(String subscriberAuth,
			$hookee<?>... observers) {
		for (VaporHook hook : members)

			hook.hookOut(subscriberAuth, observers);

		return this;
	}

	/**
	 * Returns the number of Hookees currently attached to this Hook. NOTE:
	 * Because hooking in and out is asynchronous, this method will return the
	 * predicted size that will be accurate as of when the hookees list is next
	 * retrieved or used.
	 * 
	 * @return the number of Hookees currently attached to this Hook
	 */
	public ArrayList<Integer> hookeeCount() {
		int _size = members.size();
		ArrayList<Integer> _returns = new ArrayList<Integer>(_size);

		for (int _index = 0; _index < _size; _index++) {
			_returns.add(members.get(_index).hookeeCount());
		}
		return _returns;
	}

	/**
	 * Returns the name of this Hook
	 * 
	 * @return the name of this Hook
	 */
	public ArrayList<String> name() {
		int _size = members.size();
		ArrayList<String> _returns = new ArrayList<String>(_size);

		for (int _index = 0; _index < _size; _index++) {
			_returns.add(members.get(_index).name());
		}
		return _returns;
	}

	/**
	 * Returns a read-only list of Hookees currently subscribed to this Hook
	 * 
	 * NOTE: This operation may require adminAuth
	 * 
	 * @return the list of Hookees currently subscribed to this Hook
	 */
	public ArrayList<List<$hookee<?>>> hookees() {
		int _size = members.size();
		ArrayList<List<$hookee<?>>> _returns = new ArrayList<List<$hookee<?>>>(
				_size);

		for (int _index = 0; _index < _size; _index++) {
			_returns.add(members.get(_index).hookees());
		}
		return _returns;
	}

	/**
	 * Returns a read-only list of Hookees currently subscribed to this Hook
	 * 
	 * @param adminAuth
	 *            authorization for this operation
	 * @return the list of Hookees currently subscribed to this Hook
	 */
	public ArrayList<List<$hookee<?>>> hookees(String adminAuth) {
		int _size = members.size();
		ArrayList<List<$hookee<?>>> _returns = new ArrayList<List<$hookee<?>>>(
				_size);

		for (int _index = 0; _index < _size; _index++) {
			_returns.add(members.get(_index).hookees(adminAuth));
		}
		return _returns;
	}

	/**
	 * Returns the settings for this Hook
	 * 
	 * @return the settings for this Hook
	 */
	ArrayList<VaporBundle> settings() {
		int _size = members.size();
		ArrayList<VaporBundle> _returns = new ArrayList<VaporBundle>(_size);

		for (int _index = 0; _index < _size; _index++) {
			_returns.add(members.get(_index).settings());
		}
		return _returns;
	}

	/**
	 * Returns whether this Hook has an admin auth password set
	 * 
	 * @return true if this Hook has an admin auth password set, false otherwise
	 */
	public ArrayList<Boolean> adminRestricted() {
		int _size = members.size();
		ArrayList<Boolean> _returns = new ArrayList<Boolean>(_size);

		for (int _index = 0; _index < _size; _index++) {
			_returns.add(members.get(_index).adminRestricted());
		}
		return _returns;
	}

	/**
	 * Returns whether this Hook has an subscriber auth password set
	 * 
	 * @return true if this Hook has an subsciber auth password set, false
	 *         otherwise
	 */
	public ArrayList<Boolean> subscriberRestricted() {
		int _size = members.size();
		ArrayList<Boolean> _returns = new ArrayList<Boolean>(_size);

		for (int _index = 0; _index < _size; _index++) {
			_returns.add(members.get(_index).subscriberRestricted());
		}
		return _returns;
	}

	public String toString() {
		String s = "";

		for (VaporHook hook : members)
			s += hook.toString() + '\n';

		return s;

	}
}
