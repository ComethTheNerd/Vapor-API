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

package vapor.app;

import java.io.FileDescriptor;
import java.io.PrintWriter;

import vapor.content.VaporIntent;
import vapor.core.$;
import vapor.exception.VaporLicenseException;
import vapor.os.VaporBundle;
import vapor.view.VaporView;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.LoaderManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;

/**
 * Fluent Vapor companion to Fragment
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from Fragment
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporFragment<T extends Fragment, self extends VaporFragment<T, self>>
		implements ComponentCallbacks2, OnCreateContextMenuListener {
	/*
	 * NOTE: Skipped from onContextItemSelected(...) to onViewStateRestored as
	 * these are callback events and names Vapor equivalent like attach(..) seem
	 * confusing Need to think about how to deal with this. Some prefix maybe?
	 */
	T fragment;

	public VaporFragment() {
		if(!$.licensed()){
			throw new VaporLicenseException();
		}else{
			fragment = (T) new Fragment();
		}
	}

	public VaporFragment(int id) {
		if(!$.licensed()){
			throw new VaporLicenseException();
		}else{
			fragment = load(id);
		}
	}

	public VaporFragment(T fragment) {
		if(!$.licensed()){
			throw new VaporLicenseException();
		}else{
			this.fragment = fragment;
		}
	}

	private T load(int id) {
		return (T) $.act().getFragmentManager().findFragmentById(id);
	}

	/**
	 * Return the Activity this fragment is currently associated with.
	 * 
	 * @return the Activity this fragment is currently associated with.
	 */
	public final Activity act() {
		return fragment.getActivity();
	}

	/**
	 * Fluent equivalent Vapor method for invoking onActivityCreated(Bundle),
	 * called when the fragment's activity has been created and this fragment's
	 * view hierarchy instantiated. It can be used to do final initialization
	 * once these pieces are in place, such as retrieving views or restoring
	 * state. It is also useful for fragments that use setRetainInstance(boolean)
	 * to retain their instance, as this callback tells the fragment when it is
	 * fully associated with the new activity instance. This is called after
	 * onCreateView(LayoutInflater, ViewGroup, Bundle) and before
	 * onViewStateRestored(Bundle).
	 * 
	 * @param savedInstanceState
	 *            If the fragment is being re-created from a previous saved
	 *            state, this is the state.
	 * @return self
	 */
	public self actCreated(VaporBundle savedInstanceState) {
		fragment.onActivityCreated(savedInstanceState.bundle());
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking
	 * onActivityResult(int,int,Intent), used to receive the result from a
	 * previous call to startActivityForResult(Intent, int). This follows the
	 * related Activity API as described there in onActivityResult(int, int,
	 * Intent).
	 * 
	 * @param requestCode
	 *            The integer request code originally supplied to
	 *            startActivityForResult(), allowing you to identify who this
	 *            result came from.
	 * @param resultCode
	 *            The integer result code returned by the child activity through
	 *            its setResult().
	 * @param data
	 *            A VaporIntent, which can return result data to the caller
	 *            (various data can be attached to Intent "extras").
	 * @return self
	 */
	public self actResult(int requestCode, int resultCode,
			VaporIntent<? extends Intent, ?> data) {
		fragment.onActivityResult(requestCode, resultCode, data.intent());
		return (self) this;
	}

	/**
	 * Return true if the fragment is currently added to its activity.
	 * 
	 * @return
	 */
	public final boolean added() {
		return fragment.isAdded();
	}

	/**
	 * Return the arguments supplied when the fragment was instantiated, if any.
	 * 
	 * @return the arguments supplied when the fragment was instantiated, if any
	 */
	public final VaporBundle args() {
		return new VaporBundle(fragment.getArguments());
	}

	/**
	 * Supply the construction arguments for this fragment. This can only be
	 * called before the fragment has been attached to its activity; that is,
	 * you should call it immediately after constructing the fragment. The
	 * arguments supplied here will be retained across fragment destroy and
	 * creation.
	 * 
	 * @param args
	 * @return self
	 */
	public self args(VaporBundle args) {
		fragment.setArguments(args.bundle());
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onAttach(Activity), called
	 * when a fragment is first attached to its activity. onCreate(Bundle) will
	 * be called after this.
	 * 
	 * @param activity
	 * @return self
	 */
	public self attach(VaporActivity activity) {
		fragment.onAttach(activity);
		return (self) this;
	}

	/**
	 * Return a private FragmentManager for placing and managing Fragments
	 * inside of this Fragment.
	 * 
	 * @return a private FragmentManager for placing and managing Fragments
	 *         inside of this Fragment
	 * @return a private FragmentManager for placing and managing Fragments
	 *         inside of this Fragment
	 */
	public final FragmentManager childFragMan() {
		return fragment.getChildFragmentManager();
	}

	/**
	 * Fluent equivalent Vapor method for invoking
	 * onConfigurationChanged(Configuration), called by the system when the
	 * device configuration changes while your component is running. Note that,
	 * unlike activities, other components are never restarted when a
	 * configuration changes: they must always deal with the results of the
	 * change, such as by re-retrieving resources.
	 * 
	 * At the time that this function has been called, your Resources object
	 * will have been updated to return resource values matching the new
	 * configuration.
	 * 
	 * @param newConfig
	 *            The new device configuration.
	 * @return self
	 */
	public self configChanged(Configuration newConfig) {
		fragment.onConfigurationChanged(newConfig);
		return (self) this;
	}

	/**
	 * Return true if the fragment has been explicitly detached from the UI.
	 * That is, FragmentTransaction.detach(Fragment) has been used on it.
	 * 
	 * @return
	 */
	public final boolean detached() {
		return fragment.isDetached();
	}

	/**
	 * Print the Fragments's state into the given stream.
	 * 
	 * @param prefix
	 *            Text to print at the front of each line.
	 * @param fileDescriptors
	 *            The raw file descriptor that the dump is being sent to.
	 * @param writer
	 *            The PrintWriter to which you should dump your state. This will
	 *            be closed for you after you return.
	 * @param args
	 *            additional arguments to the dump request.
	 * @return self
	 */
	public self dump(String prefix, FileDescriptor fileDescriptors,
			PrintWriter writer, String[] args) {
		fragment.dump(prefix, fileDescriptors, writer, args);
		return (self) this;
	}

	/**
	 * Return the FragmentManager for interacting with fragments associated with
	 * this fragment's activity. Note that this will be non-null slightly before
	 * act(), during the time from when the fragment is placed in a
	 * FragmentTransaction until it is committed and attached to its activity.
	 * 
	 * If this Fragment is a child of another Fragment, the FragmentManager
	 * returned here will be the parent's childFragMan().
	 * 
	 * @return the FragmentManager for interacting with fragments associated
	 *         with this fragment's activity
	 */
	public final FragmentManager fragMan() {
		return fragment.getFragmentManager();
	}

	/**
	 * Return the underlying standard Android Fragment
	 * 
	 * @return the underlying standard Android Fragment
	 */
	public T frag() {
		return fragment;
	}

	/**
	 * Return true if the fragment has been hidden. By default fragments are
	 * shown. You can find out about changes to this state with
	 * onHiddenChanged(boolean). Note that the hidden state is orthogonal to
	 * other states -- that is, to be visible to the user, a fragment must be
	 * both started and not hidden.
	 * 
	 * @return
	 */
	public final boolean hidden() {
		return fragment.isHidden();
	}

	/**
	 * Return the identifier this fragment is known by. This is either the
	 * android:id value supplied in a layout or the container view ID supplied
	 * when adding the fragment.
	 * 
	 * @return the identifier this fragment is known by
	 */
	public final int id() {
		return fragment.getId();
	}

	/**
	 * Like instance(Context, String, VaporBundle) but with a null argument
	 * VaporBundle.
	 * 
	 * @param context
	 *            The calling context being used to instantiate the fragment.
	 *            This is currently just used to get its ClassLoader.
	 * @param fragmentName
	 *            The class name of the fragment to instantiate.
	 * @return A new fragment instance.
	 */
	public static VaporFragment<? extends Fragment, ?> instance(
			Context context, String fragmentName) {
		return $.Fragment(Fragment.instantiate(context, fragmentName));
	}

	/**
	 * Create a new instance of a Fragment with the given class name. This is
	 * the same as calling its empty constructor.
	 * 
	 * @param context
	 *            The calling context being used to instantiate the fragment.
	 *            This is currently just used to get its ClassLoader.
	 * @param fragmentName
	 *            The class name of the fragment to instantiate.
	 * @return A new fragment instance.
	 */
	public static VaporFragment<? extends Fragment, ?> instance(
			Context context, String fragmentName, VaporBundle args) {
		return $.Fragment(Fragment.instantiate(context, fragmentName,
				args.bundle()));
	}

	/**
	 * Return true if the layout is included as part of an activity view
	 * hierarchy via the <fragment> tag. This will always be true when fragments
	 * are created through the <fragment> tag, except in the case where an old
	 * fragment is restored from a previous state and it does not appear in the
	 * layout of the current state.
	 * 
	 * @return true if the layout is included as part of an activity view
	 *         hierarchy via the <fragment> tag
	 */
	public final boolean inLayout() {
		return fragment.isInLayout();
	}

	/**
	 * Set the initial saved state that this Fragment should restore itself from
	 * when first being constructed, as returned by
	 * FragmentManager.saveFragmentInstanceState.
	 * 
	 * @param savedState
	 *            The state the fragment should be restored from.
	 * @return self
	 */
	public self initSavedState(Fragment.SavedState savedState) {
		fragment.setInitialSavedState(savedState);
		return (self) this;
	}

	/**
	 * Return the LoaderManager for this fragment, creating it if needed.
	 * 
	 * @return the LoaderManager for this fragment, creating it if needed.
	 */
	public LoaderManager loaderMan() {
		return fragment.getLoaderManager();
	}

	/**
	 * Set a hint for whether this fragment's menu should be visible. This is
	 * useful if you know that a fragment has been placed in your view hierarchy
	 * so that the user can not currently seen it, so any menu items it has
	 * should also not be shown.
	 * 
	 * @param menuVisible
	 *            The default is true, meaning the fragment's menu will be shown
	 *            as usual. If false, the user will not see the menu.
	 * @return self
	 */
	public self menuShowing(boolean menuVisible) {
		fragment.setMenuVisibility(menuVisible);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking
	 * onContextItemSelected(MenuItem), called whenever an item in a context
	 * menu is selected.
	 * 
	 * @param item
	 *            The context menu item that was selected
	 * @return Return false to allow normal context menu processing to proceed,
	 *         true to consume it here
	 */
	public boolean fragOption(MenuItem item) {
		return fragment.onContextItemSelected(item);
	}

	/**
	 * Report that this fragment would like to participate in populating the
	 * options menu by receiving a call to onCreateOptionsMenu(Menu,
	 * MenuInflater) and related methods.
	 * 
	 * @param hasOptionsMenu
	 *            If true, the fragment has menu items to contribute.
	 * @return self
	 */
	public self optionsMenu(boolean hasOptionsMenu) {
		fragment.setHasOptionsMenu(hasOptionsMenu);
		return (self) this;
	}

	/**
	 * Returns the parent Fragment containing this Fragment. If this Fragment is
	 * attached directly to an Activity, returns null.
	 * 
	 * @return
	 */
	public final VaporFragment<? extends Fragment, ?> parent() {
		Fragment frag = fragment.getParentFragment();

		return null;// frag != null? $.Fragment(frag) : null;
	}

	/**
	 * Registers a context menu to be shown for the given view (multiple views
	 * can show the context menu). This method will set the
	 * View.OnCreateContextMenuListener on the view to this fragment, so
	 * onCreateContextMenu(ContextMenu, View, ContextMenuInfo) will be called
	 * when it is time to show the context menu.
	 * 
	 * @param view
	 *            The view that should show a context menu.
	 * @return self
	 */
	public self regMenu(VaporView<? extends View, ?> view) {

		fragment.registerForContextMenu(view.view());
		return (self) this;
	}

	/**
	 * Return true if this fragment is currently being removed from its
	 * activity. This is not whether its activity is finishing, but rather
	 * whether it is in the process of being removed from its activity.
	 * 
	 * @return true if this fragment is currently being removed from its
	 *         activity
	 */
	public final boolean removing() {
		return fragment.isRemoving();
	}

	/**
	 * Return act().getResources().
	 * 
	 * @return
	 */
	public final Resources res() {
		return act().getResources();
	}

	/**
	 * Return true if the fragment is in the resumed state. This is true for the
	 * duration of onResume() and onPause() as well.
	 * 
	 * @return true if the fragment is in the resumed state
	 */
	public final boolean resumed() {
		return fragment.isResumed();
	}

	/**
	 * 
	 * @return
	 */
	public final boolean retain() {
		return fragment.getRetainInstance();
	}

	/**
	 * Control whether a fragment instance is retained across Activity
	 * re-creation (such as from a configuration change). This can only be used
	 * with fragments not in the back stack. If set, the fragment lifecycle will
	 * be slightly different when an activity is recreated:
	 * 
	 * onDestroy() will not be called (but onDetach() still will be, because the
	 * fragment is being detached from its current activity). onCreate(Bundle)
	 * will not be called since the fragment is not being re-created.
	 * onAttach(Activity) and onActivityCreated(Bundle) will still be called.
	 * 
	 * @param retainInstance
	 * @return self
	 */
	public self retain(boolean retainInstance) {
		fragment.setRetainInstance(retainInstance);
		return (self) this;
	}

	/**
	 * Return true if the fragment is currently visible to the user. This means
	 * it: (1) has been added, (2) has its view attached to the window, and (3)
	 * is not hidden.
	 * 
	 * @return true if the fragment is currently visible to the user
	 */
	public final boolean showing() {
		return fragment.isVisible();
	}

	/**
	 * Call startActivity(VaporIntent) on the fragment's containing Activity.
	 * 
	 * @param intent
	 *            The intent to start.
	 * @return self
	 */
	public self act(VaporIntent<? extends Intent, ?> intent) {
		fragment.startActivity(intent.intent());
		return (self) this;
	}

	/**
	 * Call startActivity(Intent, Bundle) on the fragment's containing Activity.
	 * 
	 * @param intent
	 *            The intent to start.
	 * @param options
	 *            Additional options for how the Activity should be started. See
	 *            Context.startActivity(Intent, Bundle) for more details.
	 * @return self
	 */
	public self act(VaporIntent<? extends Intent, ?> intent, VaporBundle options) {
		fragment.startActivity(intent.intent(), options.bundle());
		return (self) this;
	}

	/**
	 * Call startActivityForResult(Intent, int) on the fragment's containing
	 * Activity.
	 * 
	 * @param intent
	 *            The intent to start.
	 * @param requestCode
	 * @return self
	 */
	public self act(VaporIntent<? extends Intent, ?> intent, int requestCode) {
		fragment.startActivityForResult(intent.intent(), requestCode);
		return (self) this;
	}

	/**
	 * Call startActivityForResult(Intent, int, Bundle) on the fragment's
	 * containing Activity.
	 * 
	 * @param intent
	 *            The intent to start.
	 * @param requestCode
	 * @param options
	 * @return self
	 */
	public self act(VaporIntent<? extends Intent, ?> intent, int requestCode,
			VaporBundle options) {
		fragment.startActivityForResult(intent.intent(), requestCode,
				options.bundle());
		return (self) this;
	}

	/**
	 * Return a localized string from the application's package's default string
	 * table.
	 * 
	 * @param resIdResource
	 *            id for the string
	 * @return
	 */
	public final String string(int resId) {
		return fragment.getString(resId);
	}

	/**
	 * Return a localized formatted string from the application's package's
	 * default string table, substituting the format arguments as defined in
	 * Formatter and format(String, Object...).
	 * 
	 * @param resId
	 *            Resource id for the format string
	 * @param formatArgs
	 *            The format arguments that will be used for substitution.
	 * @return
	 */
	public final String string(int resId, Object... formatArgs) {
		return fragment.getString(resId, formatArgs);
	}

	/**
	 * Get the tag name of the fragment, if specified.
	 * 
	 * @return
	 */
	public final String tag() {
		return fragment.getTag();
	}

	/**
	 * Return the target fragment set by targetFrag(VaporFragment, int).
	 * 
	 * @return
	 */
	public final VaporFragment<? extends Fragment, ?> targetFrag() {

		Fragment frag = fragment.getTargetFragment();
		return frag != null ? $.Fragment(frag) : null;
	}

	/**
	 * Optional target for this fragment. This may be used, for example, if this
	 * fragment is being started by another, and when done wants to give a
	 * result back to the first. The target set here is retained across instances
	 * via FragmentManager.putFragment().
	 * 
	 * @param fragment
	 *            The fragment that is the target of this one.
	 * @param requestCode
	 *            Optional request code, for convenience if you are going to
	 *            call back with onActivityResult(int, int, VaporIntent).
	 * @return self
	 */
	public self targetFrag(VaporFragment<? extends Fragment, ?> fragment,
			int requestCode) {
		this.fragment.setTargetFragment(fragment.frag(), requestCode);
		return (self) this;
	}

	/**
	 * Return the target request code set by targetFrag(VaporFragment, int).
	 * 
	 * @return
	 */
	public final int targetReqCode() {
		return fragment.getTargetRequestCode();
	}

	/**
	 * Return a localized, styled CharSequence from the application's package's
	 * default string table.
	 * 
	 * @param resId
	 *            Resource id for the CharSequence text
	 * @return
	 */
	public final CharSequence text(int resId) {
		return fragment.getText(resId);
	}

	/**
	 * Returns the current value of the user-visible hint on this fragment.
	 * 
	 * @return The current value of the user-visible hint on this fragment.
	 */
	public boolean uiShowing() {
		return fragment.getUserVisibleHint();
	}

	/**
	 * Set a hint to the system about whether this fragment's UI is currently
	 * visible to the user. This hint defaults to true and is persistent across
	 * fragment instance state save and restore.
	 * 
	 * An app may set this to false to indicate that the fragment's UI is
	 * scrolled out of visibility or is otherwise not directly visible to the
	 * user. This may be used by the system to prioritize operations such as
	 * fragment lifecycle updates or loader ordering behavior.
	 * 
	 * @param isVisibleToUser
	 *            true if this fragment's UI is currently visible to the user
	 *            (default), false if it is not.
	 * @return self
	 */
	public self uiShowing(boolean isVisibleToUser) {
		fragment.setUserVisibleHint(isVisibleToUser);
		return (self) this;
	}

	/**
	 * Prevents a context menu to be shown for the given view. This method will
	 * remove the View.OnCreateContextMenuListener on the view.
	 * 
	 * @param view
	 *            The view that should stop showing a context menu.
	 * @return self
	 */
	public self unregMenu(VaporView<? extends View, ?> view) {
		fragment.unregisterForContextMenu(view.view());
		return (self) this;
	}

	/**
	 * Get the root view for the fragment's layout (the one returned by
	 * onCreateView(LayoutInflater, VaporViewGroup, Bundle)), if provided.
	 * 
	 * @return The fragment's root view, or null if it has no layout.
	 */
	public VaporView<? extends View, ?> root() {

		View view = fragment.getView();

		return view != null ? $.vapor(view) : null;
	}

	/* INTERFACE : ComponentCallbacks */
	/**
	 * NOTE: This method serves to satisfy the ComponentCallbacks interface, use
	 * the equivalent VAPOR FLUENT method configChanged(Configuration) instead
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfiguration) {
		configChanged(newConfiguration);
	}

	/**
	 * NOTE: This method serves to satisfy the ComponentCallbacks interface, use
	 * the equivalent VAPOR FLUENT method lowMem() instead
	 */
	@Override
	public void onLowMemory() {
		// lowMem();
	}

	/* INTERFACE : OnCreateContextMenuListener */
	/**
	 * NOTE: This method serves to satisfy the OnCreateContextMenuListener
	 * interface, use the equivalent VAPOR FLUENT method
	 * menuCreated(ContextMenu,VaporView<? extends View,?>,ContextMenuInfo)
	 * instead
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View view,
			ContextMenuInfo menuInfo) {
		// menuCreated(menu,$.vapor(view),menuInfo);
	}

	/**
	 * NOTE: This method serves to satisfy the OnCreateContextMenuListener
	 * interface, use the equivalent VAPOR FLUENT method trimMem(int) instead
	 */
	@Override
	public void onTrimMemory(int level) {
		// trimMem(level);
	}

}
