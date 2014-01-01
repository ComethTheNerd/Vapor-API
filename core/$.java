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

import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import vapor.listeners.$$hooker;
import vapor.app.VFragment;
import vapor.app.VFragmentBreadCrumbs;
import vapor.app.VMediaRouteButton;
import vapor.app.VXFragment;
import vapor.app.VXFragmentBreadCrumbs;
import vapor.app.VXMediaRouteButton;
import vapor.app.VaporActivity;
import vapor.app.VaporFragment;
import vapor.app.VaporFragmentBreadCrumbs;
import vapor.app.VaporMediaRouteButton;
import vapor.app.VaporService;
import vapor.appwidget.VAppWidgetHostView;
import vapor.appwidget.VXAppWidgetHostView;
import vapor.appwidget.VaporAppWidgetHostView;
import vapor.content.VIntent;
import vapor.content.VaporSharedPreferences;
import vapor.core.hookframework.VaporHook;
import vapor.core.hookframework.VaporHookEngine;
import vapor.core.hookframework.VaporXHook;
import vapor.gesture.VGestureOverlayView;
import vapor.gesture.VXGestureOverlayView;
import vapor.gesture.VaporGestureOverlayView;
import vapor.inputmethodservice.VExtractEditText;
import vapor.inputmethodservice.VKeyboardView;
import vapor.inputmethodservice.VXExtractEditText;
import vapor.inputmethodservice.VXKeyboardView;
import vapor.inputmethodservice.VaporExtractEditText;
import vapor.inputmethodservice.VaporKeyboardView;
import vapor.opengl.VGLSurfaceView;
import vapor.opengl.VXGLSurfaceView;
import vapor.opengl.VaporGLSurfaceView;
import vapor.os.VaporBundle;
import vapor.support.v4.app.VFragmentTabHost;
import vapor.support.v4.app.VXFragmentTabHost;
import vapor.support.v4.app.VaporFragmentTabHost;
import vapor.support.v4.view.VPagerTabStrip;
import vapor.support.v4.view.VPagerTitleStrip;
import vapor.support.v4.view.VViewPager;
import vapor.support.v4.view.VXPagerTabStrip;
import vapor.support.v4.view.VXPagerTitleStrip;
import vapor.support.v4.view.VXViewPager;
import vapor.support.v4.view.VaporPagerTabStrip;
import vapor.support.v4.view.VaporPagerTitleStrip;
import vapor.support.v4.view.VaporViewPager;
import vapor.view.VSurfaceView;
import vapor.view.VTextureView;
import vapor.view.VView;
import vapor.view.VViewGroup;
import vapor.view.VViewStub;
import vapor.view.VXSurfaceView;
import vapor.view.VXTextureView;
import vapor.view.VXView;
import vapor.view.VXViewStub;
import vapor.view.VaporSurfaceView;
import vapor.view.VaporTextureView;
import vapor.view.VaporView;
import vapor.view.VaporViewStub;
import vapor.webkit.VWebView;
import vapor.webkit.VXWebView;
import vapor.webkit.VaporWebView;
import vapor.widget.*;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentBreadCrumbs;
import android.app.MediaRouteButton;
import android.app.Service;
import android.appwidget.AppWidgetHostView;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.gesture.GestureOverlayView;
import android.graphics.Point;
import android.inputmethodservice.ExtractEditText;
import android.inputmethodservice.KeyboardView;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.AbsSeekBar;
import android.widget.AbsSpinner;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.AdapterViewAnimator;
import android.widget.AdapterViewFlipper;
import android.widget.AnalogClock;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.DialerFilter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.MultiAutoCompleteTextView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.QuickContactBadge;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.StackView;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextClock;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;
import android.widget.VideoView;
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;
import android.widget.ZoomButton;
import android.widget.ZoomControls;

/**
 * The central component to the Vapor framework. Use the static methods in '$'
 * to access the key features of Vapor from any part of your code.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * 
 */
public final class $ {

	/**Checks whether this copy of Vapor is licensed
	 * 
	 * @return true if this copy of Vapor is licensed, false otherwise
	 */
	public static boolean licensed(){
		return Framework.licensed();
	}
	
	/**Retrieve the signature for the application
	 * 
	 * @return the signature for the application
	 */
	public static String appSignature(){
		return Framework.appSignature();
	}

	public static final String CONTEXT_CHANGED = "contextChanged";

	private static final Random rdm = new Random();
	
	// MUST MATCH PASSWORD IN VAPORHOOKENGINE createSystemHooks()
	private static final String $_HOOK_ADMIN = "wpficpo430c-334nr4nfcp3a22e";
			//"wpficpo430c-3"+ rdm.nextInt(20000) + "34nr4nfcp" + rdm.nextInt(20000) + "3a22e";
	
	/*
	 * NOW CREATED IN VAPORHOOKENGINE createSystemHooks() SO THE CLIENT
	 * CAN NOT CREATE THE HOOK BEFORE THE SYSTEM
	 * 
	private static VaporBundle settings = $.Bundle()
			.put(VaporHook.ADMIN_AUTH, $_HOOK_ADMIN) // password protect against
														// admin changes to
														// these hooks
			.put(VaporHook.AUTO_DELETE, false) // these hooks will not be auto
												// deleted when hookeeCount
												// drops to 0
			.put(VaporHook.DELETEABLE, false) // these hooks CANNOT be deleted
	;

	// Need to create this hook here to ensure it is done before any other code
	// has a chance to
	private static final VaporHook contextChangedHook = $.hook(CONTEXT_CHANGED,settings);*/
	
	private static final HashMap<Class<? extends View>, Class<? extends VaporView>> vaporMap = new HashMap<Class<? extends View>, Class<? extends VaporView>>() {
		{
			put(View.class, VView.class);
			put(AnalogClock.class, VAnalogClock.class);
			put(ImageView.class, VImageView.class);
			put(KeyboardView.class, VKeyboardView.class);
			put(MediaRouteButton.class, VMediaRouteButton.class);
			put(ProgressBar.class, VProgressBar.class);
			put(Space.class, VSpace.class);
			put(SurfaceView.class, VSurfaceView.class);
			put(TextView.class, VTextView.class);
			put(TextureView.class, VTextureView.class);
			put(ViewGroup.class, VViewGroup.class);
			put(ViewStub.class, VViewStub.class);
			put(AbsListView.class, VAbsListView.class);
			put(AbsSeekBar.class, VAbsSeekBar.class);
			put(AbsSpinner.class, VAbsSpinner.class);
			put(AbsoluteLayout.class, VAbsoluteLayout.class);
			put(AdapterView.class, VAdapterView.class);
			put(AdapterViewAnimator.class, VAdapterViewAnimator.class);
			put(AdapterViewFlipper.class, VAdapterViewFlipper.class);
			put(AppWidgetHostView.class, VAppWidgetHostView.class);
			put(AutoCompleteTextView.class, VAutoCompleteTextView.class);
			put(Button.class, VButton.class);
			put(CalendarView.class, VCalendarView.class);
			put(CheckBox.class, VCheckBox.class);
			put(CheckedTextView.class, VCheckedTextView.class);
			put(Chronometer.class, VChronometer.class);
			put(CompoundButton.class, VCompoundButton.class);
			put(DatePicker.class, VDatePicker.class);
			put(DialerFilter.class, VDialerFilter.class);
			put(EditText.class, VEditText.class);
			put(ExpandableListView.class, VExpandableListView.class);
			put(ExtractEditText.class, VExtractEditText.class);
			put(FragmentBreadCrumbs.class, VFragmentBreadCrumbs.class);
			put(FragmentTabHost.class,VFragmentTabHost.class);
			put(FrameLayout.class, VFrameLayout.class);
			put(GLSurfaceView.class, VGLSurfaceView.class);
			put(GestureOverlayView.class, VGestureOverlayView.class);
			put(GridLayout.class, VGridLayout.class);
			put(GridView.class, VGridView.class);
			put(HorizontalScrollView.class, VHorizontalScrollView.class);
			put(ImageButton.class, VImageButton.class);
			put(ImageSwitcher.class, VImageSwitcher.class);
			put(LinearLayout.class, VLinearLayout.class);
			put(ListView.class, VListView.class);
			put(MediaController.class, VMediaController.class);
			put(MultiAutoCompleteTextView.class,
					VMultiAutoCompleteTextView.class);
			put(NumberPicker.class, VNumberPicker.class);
			put(PagerTabStrip.class, VPagerTabStrip.class);
			put(PagerTitleStrip.class, VPagerTitleStrip.class);
			put(QuickContactBadge.class, VQuickContactBadge.class);
			put(RadioButton.class, VRadioButton.class);
			put(RadioGroup.class, VRadioGroup.class);
			put(RatingBar.class, VRatingBar.class);
			put(RelativeLayout.class, VRelativeLayout.class);
			put(ScrollView.class, VScrollView.class);
			put(SearchView.class, VSearchView.class);
			put(SeekBar.class, VSeekBar.class);
			put(Spinner.class, VSpinner.class);
			put(StackView.class, VStackView.class);
			put(Switch.class, VSwitch.class);
			put(TabHost.class, VTabHost.class);
			put(TabWidget.class, VTabWidget.class);
			put(TableLayout.class, VTableLayout.class);
			put(TableRow.class, VTableRow.class);
			put(TextClock.class, VTextClock.class);
			put(TextSwitcher.class, VTextSwitcher.class);
			put(TimePicker.class, VTimePicker.class);
			put(ToggleButton.class, VToggleButton.class);
			put(VideoView.class, VVideoView.class);
			put(ViewAnimator.class, VViewAnimator.class);
			put(ViewFlipper.class, VViewFlipper.class);
			put(ViewPager.class, VViewPager.class);
			put(ViewSwitcher.class, VViewSwitcher.class);
			put(WebView.class, VWebView.class);
			put(ZoomButton.class, VZoomButton.class);
			put(ZoomControls.class, VZoomControls.class);

		}
	};

	/**
	 * Convert an ArrayList of VaporViews to an ArrayList of standard Android companion View instances
	 * 
	 * @param view
	 *            the list of VaporViews to convert
	 * @return an ArrayList of View instances converted to standard Android
	 */
	public static final ArrayList<View> android(
			List<VaporView<? extends View, ?>> vaporViews) {
		ArrayList<View> c = new ArrayList<View>(vaporViews.size());
		for (VaporView<? extends View, ?> v : vaporViews) {
			c.add(v.view());
		}
		return c;
	}

	/**Convert a Vapor View type to the companion standard Android View type
	*
	*@param vaporView the Vapor View instance to convert
	*@return the instance converted to its companion standard Android View type
	*/
	public static <T extends VaporView<? extends View, ?>, AndroidType extends View> AndroidType android(
			T vaporView) {
		return (AndroidType) vaporView.view();
	}

	/**Convert a Vapor View class to the companion standard Android View class
	*
	*@param vaporViewClass the Vapor View class to convert
	*@return the companion standard Android View class
	*/
	public static Class<? extends View> android(Class<?> vaporViewClass) {

		for (HashMap.Entry<Class<? extends View>, Class<? extends VaporView>> entry : vaporMap
				.entrySet()) {
			if (entry.getValue() == vaporViewClass) {
				return entry.getKey();
			}
		}

		return null; // caller's responsibility to deal with supplying incorrect
						// arg type
	}

	/**
	 * Returns an instance of a closest-ancestor equivalent VaporView type for
	 * the given View, mirroring the View's state. Use this method when needing
	 * to convert Android View types in to VaporView types
	 * 
	 * @param view
	 *            the Android View instance to convert
	 * @return a closest-ancestor equivalent VaporView instance, or null if none
	 *         could be deduced
	 */
	public static <T extends View, VaporType extends VaporView<? extends View, ?>> VaporType vapor(
			T view) {

		VaporType vaporType = null;
		Class<T> cls = (Class<T>) view.getClass();
		Class<? extends VaporView> vaporClass = vapor(cls);
		try {
			// retrieve the constructor that takes an object of the source class
			Constructor<? extends VaporView> constructor = vaporClass
					.getConstructor(new Class[] { cls });

			vaporType = (VaporType) constructor.newInstance(view);
		} catch (Exception ex) {
			return null;
		}

		// instantiate and return the constructed object
		return vaporType;

	}

	/**
	 * Returns the closest-ancestor equivalent VaporView class for any class
	 * supplied that derives from a valid Android View class at some level in
	 * its inheritance hierarchy
	 * 
	 * @param viewClass
	 *            the Android View class to convert
	 * @return the closest-ancestor equivalent VaporView class, or null if none
	 *         could be deduced
	 */
	public static Class<? extends VaporView> vapor(Class<?> viewClass) {
		// iterate through all superclasses, starting at the given classes
		while (viewClass != null) {

			if (vaporMap.containsKey(viewClass))
				return vaporMap.get(viewClass); // return deduced equivalent
												// VaporClass

			// haven't found an equivalent Vapor class so go up the inheritance
			// tree
			viewClass = viewClass.getSuperclass();// NOTE: do not just naively
													// cast this to View class,
													// as may be Object
		}
		// eg. MyCustomButton extends MyBigButton extends Button, would
		// eventually return VButton

		return null; // caller's responsibility to deal with supplying incorrect
						// arg type
	}
	
	/**
	 * Convert an ArrayList of standard Android Views to an ArrayList of Vapor companion View instances
	 * 
	 * @param view
	 *            the list of standard Android Views to convert
	 * @return an ArrayList of View instances converted to Vapor
	 */
	public static final ArrayList<VaporView<? extends View, ?>> vapor(
			List<View> views) {
		ArrayList<VaporView<? extends View, ?>> c = new ArrayList<VaporView<? extends View, ?>>(
				views.size());
		for (View v : views) {
			c.add($.vapor(v));
		}
		return c;
	}

	/*----------------------------*/
	/**
	 * Returns a new toast, use show() to present it to the user
	 * 
	 * @param toastText
	 *            The text for the toast
	 * @return a new VaporToast instance
	 */
	public static VaporToast toast(String toastText) {
		return new VaporToast(toastText);
	}

	/**
	 * Convenience method for generating a simple tag for the given object,
	 * useful for Log messages
	 * 
	 * @param o the object to get a tag for
	 * @return the tag for the given object, or "null" if the object == null
	 */
	public static String shortName(Object o) {
		return o == null ? "null" : o.getClass().getSimpleName();
	}

	/*----------------------------*/
	// a handle to the current activity
	// a weak reference will prevent any memory leaks
	private static WeakReference<Activity> currentAct = new WeakReference<Activity>(null);
	/**
	 * Returns the current activity shown by the application
	 * 
	 * @return The current activity shown by the application
	 */
	public static Activity act() {
		// retrieve the reference to the current context
		
		if(currentAct == null){
			 Log.w("$.act()", Framework.CONTEXT_NOT_SET);
			return null;
		}
		
		Activity act = currentAct.get();
		
		if (act == null) 
			Log.w("$.act()", Framework.CONTEXT_NOT_SET);
					
		return act;
	}

	/**
	 * Set the current activity shown by the application
	 */
	public static void act(Activity activity) {
		
		Activity oldContext = currentAct.get();

		if (activity != oldContext) {
			
			currentAct = new WeakReference<Activity>(activity);
						
			// Alert observers when the context changes
			$.hook(CONTEXT_CHANGED).fire($_HOOK_ADMIN,
					$.Bundle().put("oldContext", $.shortName(oldContext))
							.put("newContext", $.shortName(currentAct)));
		}
		
	}

	public static final int NO_RESULT = -1,
			DEFAULT_BIND_MODE = Service.START_STICKY;

	/**
	 * Start the given VaporActivity for no result and with no extras
	 * 
	 * @param nextActivity the class of the next VaporActivity to start
	 */
	public static void act(Class<? extends VaporActivity> nextActivity) {
		$.act(nextActivity, $.Bundle(), NO_RESULT);
	}

	/**
	 * Start the given VaporActivity for no result and with the given extras
	 * 
	 * @param nextActivity the class of the next VaporActivity to start
	 * @param extra the given extras to start the activity with
	 */
	public static void act(Class<? extends VaporActivity> nextActivity,
			VaporBundle extras) {
		$.act(nextActivity, extras, NO_RESULT);
	}

	/**
	 * Start the given VaporActivity for the given result code and with the given extras
	 * 
	 * @param nextActivity the class of the next VaporActivity to start
	 * @param extra the given extras to start the activity with
	 * @param resultCode the result code to start the activity for
	 */
	public static void act(Class<? extends VaporActivity> nextActivity,
			VaporBundle extras, int resultCode) {

		VIntent<Intent> intent = $.Intent(new Intent($.act(), nextActivity))
				.put(extras);

		if (resultCode == NO_RESULT)
			$.act().startActivity(intent.intent());
		else
			$.act().startActivityForResult(intent.intent(), resultCode);

	}

	/**
	 * Start the given VaporService immediately
	 * 
	 * @param serviceClass the class of the VaporService to start
	 * @return the ComponentName associated to the newly started service
	 */
	public static ComponentName srv(Class<? extends VaporService> serviceClass) {

		Context appContext = $.act().getApplicationContext();
		Intent intent = $.Intent(appContext, serviceClass).intent();

		// Start at application level
		return appContext.startService(intent);
		/*
		 * If you bind from the Activity, then the new Activity instance will
		 * have a reference to the ServiceConnection which has an implicit
		 * reference to the old Activity, and the old Activity cannot be garbage
		 * collected
		 */
	}

	/**
	 * Bind the current Activity context to the service that is an instance of the given VaporService class,
	 * starting the service first if it is not already running
	 * 
	 * @param serviceClass the class of the VaporService to bind to
	 * @return true if the current Activity context was successfully bound to the service, false otherwise
	 */
	public static boolean bind(Class<? extends VaporService> serviceClass) {
		return bind(serviceClass, Context.BIND_AUTO_CREATE);
	}

	/**
	 * Bind the given Activity context to the service that is an instance of the given VaporService class,
	 * starting the service first if it is not already running
	 * 
	 * @param serviceClass the class of the VaporService to bind to
	 * @param activity the VaporActivity instance to bind to the service
	 * @return true if the given Activity context was successfully bound to the service, false otherwise
	 */
	public static boolean bind(Class<? extends VaporService> serviceClass,
			VaporActivity activity) {
		return bind(serviceClass, activity, Context.BIND_AUTO_CREATE);
	}

	/**
	 * Bind the given VaporService to the service that is an instance of the given VaporService class,
	 * starting the service first if it is not already running
	 * 
	 * @param serviceClass the class of the VaporService to bind to
	 * @param service the VaporService instance to bind to the service
	 * @return true if the given VaporService was successfully bound to the service, false otherwise
	 */
	public static boolean bind(Class<? extends VaporService> serviceClass,
			VaporService service) {
		return bind(serviceClass, service, Context.BIND_AUTO_CREATE);
	}

	/**
	 * Bind the current Activity context to the service that is an instance of the given VaporService class,
	 * using the given bind mode
	 * 
	 * @param serviceClass the class of the VaporService to bind to
	 * @param bindMode the bind mode to use when binding the given Activity context to the service
	 * @return true if the given Activity context was successfully bound to the service, false otherwise
	 */
	public static boolean bind(Class<? extends VaporService> serviceClass,
			int bindMode) {
		Activity act = $.act();
		if (VaporActivity.class.isAssignableFrom(act.getClass())) {

			return bind(serviceClass, (VaporActivity) act, bindMode);
		} else {
			Log.w("$.bind(Class<? extends VaporService>,int)",
					"This method is only applicable to contexts where $.act() is an instance of VaporActivity");
			return false;
		}

	}

	/**
	 * Bind the given Activity context to the service that is an instance of the given VaporService class,
	 * using the given bind mode
	 *
	 * @param serviceClass the class of the VaporService to bind to
	 * @param activity the VaporActivity instance to bind to the service
	 * @param bindMode the bind mode to use when binding the given Activity context to the service
	 * @return true if the given Activity context was successfully bound to the service, false otherwise
	 */
	public static boolean bind(Class<? extends VaporService> serviceClass,
			VaporActivity activity, int bindMode) {

		Intent intent = $.Intent(activity, serviceClass).intent();

		return activity.bindService(intent, activity.vsc(serviceClass),
				bindMode);

	}

	/**
	 * Bind the given VaporService to the service that is an instance of the given VaporService class,
	 * using the given bind mode
	 *
	 * @param serviceClass the class of the VaporService to bind to
	 * @param service the VaporService instance to bind to the service
	 * @param bindMode the bind mode to use when binding the given Activity context to the service
	 * @return true if the given VaporService was successfully bound to the service, false otherwise
	 */
	public static boolean bind(Class<? extends VaporService> serviceClass,
			VaporService service, int bindMode) {

		Intent intent = $.Intent(service, serviceClass).intent();

		return service.bindService(intent, service.vsc(serviceClass), bindMode);

	}

	/**Unbinds the current Activity context from the service that is an instance of the given VaporService class
	*
	*@param serviceClass the class of the VaporService to bind from
	*/
	public static void unbind(Class<? extends VaporService> serviceClass) {
		Activity act = $.act();
		if (VaporActivity.class.isAssignableFrom(act.getClass())) {
			unbind(serviceClass, (VaporActivity) act);
		} else {
			Log.w("$.unbind(Class<? extends VaporService>)",
					"This method is only applicable to contexts where $.act() is an instance of VaporActivity");
		}

	}

	/**Unbinds the given VaporActivity from the service that is an instance of the given VaporService class
	*
	* @param serviceClass the class of the VaporService to bind from
	* @param activity the VaporActivity instance to unbind from the service
	*/
	public static void unbind(Class<? extends VaporService> serviceClass,
			VaporActivity activity) {
		activity.unbindService(serviceClass);
	}

	/**Unbinds the given VaporService from the service that is an instance of the given VaporService class
	*
	* @param serviceClass the class of the VaporService to bind from
	 * @param service the VaporService instance to unbind from the service
	*/
	public static void unbind(Class<? extends VaporService> serviceClass,
			VaporService service) {
		service.unbindService(serviceClass);
	}

	/**Retrieves the Singleton instance of the VaporSharedPreferences
	*
	*@return the Singleton instance of the VaporSharedPreferences
	*/
	public static VaporSharedPreferences prefs() {
		return VaporSharedPreferences.prefs();
	}

	/**Retrieves the Singleton instance of the VaporSharedPreferences for the given file name, creating the file first 
	* if it does not already exist
	*
	*@return the Singleton instance of the VaporSharedPreferences for the given file name
	*/
	public static VaporSharedPreferences prefs(String name) {
		return prefs().file(name);
	}

	/**Retrieves the Singleton instance of the VaporSharedPreferences for the given SharedPreferences instance
	*
	*@return the Singleton instance of the VaporSharedPreferences for the given SharedPreferences instance
	*/
	public static VaporSharedPreferences prefs(
			SharedPreferences sharedPreferences) {
		return prefs().file(sharedPreferences);
	}

	/**Check whether the application has the given permission. Permissions must be declared statically 
	 * in your application manifest, and are not dynamically assignable due to user security issues
	 * 
	 * @param permission the permission to check for
	 * @return true if the application has the given permission, false otherwise
	 */
	public static boolean permits(String permission)
	{
		if(permission.indexOf('.') == -1){
			permission = "android.permission." + permission;
		}
		
	   return $.act().getBaseContext().checkCallingOrSelfPermission(permission)
	   == PackageManager.PERMISSION_GRANTED;            
	}
	
	/**Retrieve the application level meta-data from the manifest file
	 * 
	 * @return a VaporBundle containing the parsed application level meta-data from the manifest file
	 */
	public static VaporBundle meta(){
		try {
			Activity act = $.act();
			return $.Bundle(
					act.getPackageManager().getApplicationInfo(
							act.getPackageName(), PackageManager.GET_META_DATA).metaData
					);
		} catch (NameNotFoundException e) {
			return $.Bundle();
		}
	}

	/**Retrieve the given activity's meta-data from the manifest file
	 * 
	 * @param activity the activity to retrieve the meta-data for
	 * @return a VaporBundle containing the parsed activity's meta-data from the manifest file
	 */
	public static VaporBundle meta(VaporActivity activity){
		try {
			return $.Bundle(
					activity.getPackageManager().
						getActivityInfo(activity.getComponentName(), 
								PackageManager.GET_META_DATA).metaData
					);
		} catch (NameNotFoundException e) {
			return $.Bundle();
		}
	}
	
	/**Retrieve the given service's meta-data from the manifest file
	 * 
	 * @param service the service to retrieve the meta-data for
	 * @return a VaporBundle containing the parsed service's meta-data from the manifest file
	 */
	public static VaporBundle meta(VaporService service){
		try {
			ComponentName serviceComponent = new ComponentName(service, service.getClass());
			return $.Bundle(
					service.getPackageManager().getServiceInfo(serviceComponent,
							PackageManager.GET_META_DATA).metaData
					);
		} catch (NameNotFoundException e) {
			return $.Bundle();
		}
	}
	
	/**Retrieves the VaporHook instance identified by the given name, creating it first with default settings if it does not
	* already exist
	*
	*@param hookName the unique name of the VaporHook
	*@return the VaporHook instance identified by the given name
	*/
	public static VaporHook hook(String hookName) {
		return hooks().hook(hookName);
	}
	
	/**Retrieves the VaporXHook instance that encapsulates the VaporHooks identified by the given names, creating any first with default settings if it does not
	* already exist
	*
	*@param hookNames the unique names of the VaporHooks
	*@return the VaporXHook instance that encapsulates the VaporHooks identified by the given names
	*/
	public static VaporXHook hook(String... hookNames) {
		return hooks().hook(hookNames);
	}

	/**Retrieves the VaporXHook instance that encapsulates the VaporHooks deduced from the given arbitrary mix of accepted selector types, 
	* creating any first with default settings if it does not already exist
	*
	*@param hookItems an arbitrary mix of accepted selector types
	*@return the VaporXHook instance that encapsulates the VaporHooks identified by the given names
	*/
	public static VaporXHook hook(Object... hookItems) {
		return hooks().hook(hookItems);
	}

	/**Retrieves the VaporXHook instance that encapsulates the given VaporHooks
	*
	*@param hooks the VaporHooks to encapsulate
	*@return the VaporXHook instance that encapsulates the given VaporHooks
	*/
	public static VaporXHook hook(VaporHook... hooks) {
		return hooks().hook(hooks);
	}

	/**Retrieves the VaporHook instance identified by the given name, creating it first with the given settings
	*
	*@param hookName the unique name of the VaporHook
	*@param settings the settings to create the new VaporHook with
	*@return the new VaporHook instance identified by the given name
	*/
	public static VaporHook hook(String hookName, VaporBundle settings) {
		return hooks().hook(hookName, settings);
	}

	/**Retrieves a handle to the Singleton VaporHookEngine instance
	*
	*@return a handle to the Singleton VaporHookEngine instance
	*/
	public static VaporHookEngine hooks() {
		return VaporHookEngine.instance();
	}

	/*----------------------------*/
	/* ______CORE______ */
	/* BUNDLE */
	public static VaporBundle Bundle() {
		return new VaporBundle();
	}

	public static VaporBundle Bundle(Bundle bundle) {
		return new VaporBundle(bundle);
	}

	/**A safe way to retrieve the property identified by the given name, from the given Bundle, or a default value if the property does not exist, 
	* or cannot be cast to the expected type
	*
	*@param bundle The VaporBundle instance to retrieve the value from
	*@param propertyName the unique name that identifies the property within the given bundle
	*@param defaultValue the value to return in the event that the property is not found, or cannot be cast to the expected type
	*@return the value mapped to the given property name, inside the given bundle, or the default value if a problem occurred
	*/
	public static <T> T prop(VaporBundle bundle, String propertyName,
			T defaultValue) {
		try {
			if (bundle.contains(propertyName)) {
				try {
					T value = (T) bundle.get(propertyName);
					return value;
				} catch (Exception e) {
					logBadData(bundle, propertyName, defaultValue);
				}
			}
			return defaultValue;
		} catch (Exception e) {
			
			return defaultValue;
		}
	}

	private static final String LOG_TAG = "VaporBundle Content Resolver";

	private static <T> void logBadData(VaporBundle bundle, String fieldName,
			T defaultValue) {
		Log.e(LOG_TAG, "Incorrect type for for \"" + fieldName + "\" in "
				+ bundle.toString() + ". Required: "
				+ defaultValue.getClass().getName());
	}

	/* INTENT */
	public static VIntent<Intent> Intent(Context packageContext, Class<?> cls) {
		return new VIntent<Intent>(packageContext, cls);
	}

	public static <T extends Intent> VIntent<T> Intent(T intent) {
		return new VIntent<T>(intent);
	}

	public static <T extends Intent> VIntent<T> Intent(Class<T> intentClass,
			Context packageContext, Class<?> cls) {
		return new VIntent<T>(packageContext, cls);
	}

	/* GENERAL */
	public static final int ORIENTATION_PORTRAIT = Configuration.ORIENTATION_PORTRAIT,
			ORIENTATION_LANDSCAPE = Configuration.ORIENTATION_LANDSCAPE;
	
	/**Returns an int representing the orientation of the current Activity, using the give Activity context
	 * 
	 * @param activityContext the Activity context to use
	 * @return the orientation of the current Activity
	 */
	public static int orientation(Activity activityContext){
		return 	activityContext.getResources().getConfiguration().orientation;
	}
	
	/**Set the current Activity orientation, using the current Activity context
	*
	*@param activityContext the Activity context to use
	*@param orientation the new orientation to set
	*/
	public static void orientation(Activity activityContext, int orientation){
		activityContext.setRequestedOrientation(orientation);
	}
	
	/**Returns an int representing the orientation of the current Activity, using the current Activity context
	 * 
	 * @return the orientation of the current Activity
	 */
	public static int orientation() {
		return 	$.orientation($.act());
	}

	/**Set the current Activity orientation, using the current Activity context
	*
	*@param orientation the new orientation to set
	*/
	public static void orientation(int orientation) {
		$.orientation($.act(),orientation);
	}

	/**Check if the current Activity is being displayed in a portrait orientation, using the current Activity context
	*
	*@return true if the current Activity is being displayed in a portrait orientation
	*/
	public static final boolean portrait() {
		return orientation() == ORIENTATION_PORTRAIT;
	}

	/**Check if the current Activity is being displayed in a landscape orientation, using the current Activity context
	*
	*@return true if the current Activity is being displayed in a landscape orientation
	*/
	public static final boolean landscape() {
		return orientation() == Configuration.ORIENTATION_LANDSCAPE;
	}
	
	private static Integer deviceDetected = null;
	public static final int DEVICE_PHONE = 1, DEVICE_TABLET = 2;

	/**Check if the device currently running the application is a phone, using the given context
	*
	*@param activityContext the context to use
	*@return true if Vapor deduces the device currently running the application to be a phone
	*/
	public static boolean phone(Context activityContext) {
		return device(activityContext) == DEVICE_PHONE;
	}
	
	/**Check if the device currently running the application is a phone, using the current Activity context
	*
	*@return true if Vapor deduces the device currently running the application to be a phone
	*/
	public static boolean phone() {
		return phone($.act());
	}

	/**Check if the device currently running the application is a tablet, using the given context
	*
	*@param activityContext the context to use
	*@return true if Vapor deduces the device currently running the application to be a tablet
	*/
	public static boolean tablet(Context activityContext) {
		return device(activityContext) == DEVICE_TABLET;
	}

	/**Check if the device currently running the application is a tablet, using the current Activity context
	*
	*@return true if Vapor deduces the device currently running the application to be a tablet
	*/
	public static boolean tablet() {
		return tablet($.act());
	}
	
	/**Returns an integer flag denoting the type of device deduced to be  currently running the application, using the given context
	*
	*@param activityContext the context to use
	*@return an integer flag denoting the type of device deduced to be currently running the application, currently either DEVICE_PHONE or DEVICE_TABLET
	*/
	public static int device(Context activityContext) {

		// efficiency - avoid running test again if already deduced
		if (deviceDetected != null)
			return deviceDetected;

		// Verifies if the Generalized Size of the device is XLARGE to be
		// considered a Tablet
		boolean xlarge = ((activityContext.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE);

		// If XLarge, checks if the Generalized Density is at least MDPI
		// (160dpi)
		if (xlarge) {
			DisplayMetrics metrics = new DisplayMetrics();
			Activity activity = (Activity) activityContext;
			activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

			// MDPI=160, DEFAULT=160, DENSITY_HIGH=240, DENSITY_MEDIUM=160,
			// DENSITY_TV=213, DENSITY_XHIGH=320
			if (metrics.densityDpi == DisplayMetrics.DENSITY_DEFAULT
					|| metrics.densityDpi == DisplayMetrics.DENSITY_HIGH
					|| metrics.densityDpi == DisplayMetrics.DENSITY_MEDIUM
					|| metrics.densityDpi == DisplayMetrics.DENSITY_TV
					|| metrics.densityDpi == DisplayMetrics.DENSITY_XHIGH) {

				return deviceDetected = DEVICE_TABLET;
			}
		}
		return deviceDetected = DEVICE_PHONE;
	}

	/**Returns an integer flag denoting the type of device deduced to be currently running the application, using the current Activity context
	*
	*@return an integer flag denoting the type of device deduced to be currently running the application, currently either DEVICE_PHONE or DEVICE_TABLET
	*/
	public static int device() {
		return device(act());
	}


	public static final Display display(Context context) {
		WindowManager windowMan = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		return windowMan.getDefaultDisplay();
	}

	public static final Display display() {
		return display(act());
	}
	
	/**Returns the width of the display, retrieved from the given context
	*
	*@param context the context to use
	*@return the width of the display
	*/
	public static final int displayWidth(Context context) {
		Point size = new Point();
		display(context).getSize(size);
		return size.x;
	}

	/**Returns the width of the display, retrieved using the current Activity context
	*
	*@param context the context to use
	*@return the width of the display
	*/
	public static final int displayWidth() {
		return displayWidth(act());
	}

	/**Returns the height of the display, retrieved from the given context
	*
	*@param context the context to use
	*@return the height of the display
	*/
	public static final int displayHeight(Context context) {
		Point size = new Point();
		display(context).getSize(size);
		return size.y;
	}

	/**Returns the height of the display, retrieved using the current Activity context
	*
	*@param context the context to use
	*@return the height of the display
	*/
	public static final int displayHeight() {
		return displayHeight(act());
	}


	public static <T extends Fragment> VFragment<T> Fragment(Integer id) {
		T t = (T) $.act().getFragmentManager().findFragmentById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VFragment<T>(t);
	}

	public static <T extends Fragment> VFragment<T> Fragment(T fragment) {
		return new VFragment<T>(fragment);
	}

	public static <T extends Fragment> VFragment<T> Fragment(
			Class<T> fragmentClass, Integer id) {
		return new VFragment<T>(id);
	}

	public static VXFragment<Fragment, VaporFragment<Fragment, ?>> Fragment(
			Integer... ids) {
		return new VXFragment<Fragment, VaporFragment<Fragment, ?>>(ids);
	}

	public static VXFragment<Fragment, VaporFragment<Fragment, ?>> Fragment(
			Object... fragments) {
		return new VXFragment<Fragment, VaporFragment<Fragment, ?>>(fragments);
	}

	public static <T extends Fragment> VXFragment<T, VaporFragment<T, ?>> Fragment(
			Class<T> fragmentClass, Integer... ids) {
		return new VXFragment<T, VaporFragment<T, ?>>(ids);
	}

	public static <T extends FragmentBreadCrumbs> VFragmentBreadCrumbs<T> FragmentBreadCrumbs(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VFragmentBreadCrumbs<T>(t);
	}

	public static <T extends FragmentBreadCrumbs> VFragmentBreadCrumbs<T> FragmentBreadCrumbs(
			View fragmentBreadCrumbs) {
		Class<T> tClass = (Class<T>) fragmentBreadCrumbs.getClass();
		return new VFragmentBreadCrumbs<T>((T) fragmentBreadCrumbs);
	}

	public static <T extends FragmentBreadCrumbs> VFragmentBreadCrumbs<T> FragmentBreadCrumbs(
			T fragmentBreadCrumbs) {
		return new VFragmentBreadCrumbs<T>(fragmentBreadCrumbs);
	}

	public static <T extends FragmentBreadCrumbs> VFragmentBreadCrumbs<T> FragmentBreadCrumbs(
			Class<T> fragmentBreadCrumbsClass, Integer id) {
		return new VFragmentBreadCrumbs<T>(id);
	}

	public static VXFragmentBreadCrumbs<FragmentBreadCrumbs, VaporFragmentBreadCrumbs<FragmentBreadCrumbs, ?>> FragmentBreadCrumbs(
			Integer... ids) {
		return new VXFragmentBreadCrumbs<FragmentBreadCrumbs, VaporFragmentBreadCrumbs<FragmentBreadCrumbs, ?>>(
				ids);
	}

	public static VXFragmentBreadCrumbs<FragmentBreadCrumbs, VaporFragmentBreadCrumbs<FragmentBreadCrumbs, ?>> FragmentBreadCrumbs(
			Object... fragmentBreadCrumbss) {
		return new VXFragmentBreadCrumbs<FragmentBreadCrumbs, VaporFragmentBreadCrumbs<FragmentBreadCrumbs, ?>>(
				fragmentBreadCrumbss);
	}

	public static <T extends FragmentBreadCrumbs> VXFragmentBreadCrumbs<T, VaporFragmentBreadCrumbs<T, ?>> FragmentBreadCrumbs(
			Class<T> fragmentBreadCrumbsClass, Integer... ids) {
		return new VXFragmentBreadCrumbs<T, VaporFragmentBreadCrumbs<T, ?>>(ids);
	}

	public static <T extends MediaRouteButton> VMediaRouteButton<T> MediaRouteButton(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VMediaRouteButton<T>(t);
	}

	public static <T extends MediaRouteButton> VMediaRouteButton<T> MediaRouteButton(
			View mediaRouteButton) {
		Class<T> tClass = (Class<T>) mediaRouteButton.getClass();
		return new VMediaRouteButton<T>((T) mediaRouteButton);
	}

	public static <T extends MediaRouteButton> VMediaRouteButton<T> MediaRouteButton(
			T mediaRouteButton) {
		return new VMediaRouteButton<T>(mediaRouteButton);
	}

	public static <T extends MediaRouteButton> VMediaRouteButton<T> MediaRouteButton(
			Class<T> mediaRouteButtonClass, Integer id) {
		return new VMediaRouteButton<T>(id);
	}

	public static VXMediaRouteButton<MediaRouteButton, VaporMediaRouteButton<MediaRouteButton, ?>> MediaRouteButton(
			Integer... ids) {
		return new VXMediaRouteButton<MediaRouteButton, VaporMediaRouteButton<MediaRouteButton, ?>>(
				ids);
	}

	public static VXMediaRouteButton<MediaRouteButton, VaporMediaRouteButton<MediaRouteButton, ?>> MediaRouteButton(
			Object... mediaRouteButtons) {
		return new VXMediaRouteButton<MediaRouteButton, VaporMediaRouteButton<MediaRouteButton, ?>>(
				mediaRouteButtons);
	}

	public static <T extends MediaRouteButton> VXMediaRouteButton<T, VaporMediaRouteButton<T, ?>> MediaRouteButton(
			Class<T> mediaRouteButtonClass, Integer... ids) {
		return new VXMediaRouteButton<T, VaporMediaRouteButton<T, ?>>(ids);
	}

	public static <T extends AppWidgetHostView> VAppWidgetHostView<T> AppWidgetHostView(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VAppWidgetHostView<T>(t);
	}

	public static <T extends AppWidgetHostView> VAppWidgetHostView<T> AppWidgetHostView(
			View appWidgetHostView) {
		Class<T> tClass = (Class<T>) appWidgetHostView.getClass();
		return new VAppWidgetHostView<T>((T) appWidgetHostView);
	}

	public static <T extends AppWidgetHostView> VAppWidgetHostView<T> AppWidgetHostView(
			T appWidgetHostView) {
		return new VAppWidgetHostView<T>(appWidgetHostView);
	}

	public static <T extends AppWidgetHostView> VAppWidgetHostView<T> AppWidgetHostView(
			Class<T> appWidgetHostViewClass, Integer id) {
		return new VAppWidgetHostView<T>(id);
	}

	public static VXAppWidgetHostView<AppWidgetHostView, VaporAppWidgetHostView<AppWidgetHostView, ?>> AppWidgetHostView(
			Integer... ids) {
		return new VXAppWidgetHostView<AppWidgetHostView, VaporAppWidgetHostView<AppWidgetHostView, ?>>(
				ids);
	}

	public static VXAppWidgetHostView<AppWidgetHostView, VaporAppWidgetHostView<AppWidgetHostView, ?>> AppWidgetHostView(
			Object... appWidgetHostViews) {
		return new VXAppWidgetHostView<AppWidgetHostView, VaporAppWidgetHostView<AppWidgetHostView, ?>>(
				appWidgetHostViews);
	}

	public static <T extends AppWidgetHostView> VXAppWidgetHostView<T, VaporAppWidgetHostView<T, ?>> AppWidgetHostView(
			Class<T> appWidgetHostViewClass, Integer... ids) {
		return new VXAppWidgetHostView<T, VaporAppWidgetHostView<T, ?>>(ids);
	}

	public static <T extends GestureOverlayView> VGestureOverlayView<T> GestureOverlayView(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VGestureOverlayView<T>(t);
	}

	public static <T extends GestureOverlayView> VGestureOverlayView<T> GestureOverlayView(
			View gestureOverlayView) {
		Class<T> tClass = (Class<T>) gestureOverlayView.getClass();
		return new VGestureOverlayView<T>((T) gestureOverlayView);
	}

	public static <T extends GestureOverlayView> VGestureOverlayView<T> GestureOverlayView(
			T gestureOverlayView) {
		return new VGestureOverlayView<T>(gestureOverlayView);
	}

	public static <T extends GestureOverlayView> VGestureOverlayView<T> GestureOverlayView(
			Class<T> gestureOverlayViewClass, Integer id) {
		return new VGestureOverlayView<T>(id);
	}

	public static VXGestureOverlayView<GestureOverlayView, VaporGestureOverlayView<GestureOverlayView, ?>> GestureOverlayView(
			Integer... ids) {
		return new VXGestureOverlayView<GestureOverlayView, VaporGestureOverlayView<GestureOverlayView, ?>>(
				ids);
	}

	public static VXGestureOverlayView<GestureOverlayView, VaporGestureOverlayView<GestureOverlayView, ?>> GestureOverlayView(
			Object... gestureOverlayViews) {
		return new VXGestureOverlayView<GestureOverlayView, VaporGestureOverlayView<GestureOverlayView, ?>>(
				gestureOverlayViews);
	}

	public static <T extends GestureOverlayView> VXGestureOverlayView<T, VaporGestureOverlayView<T, ?>> GestureOverlayView(
			Class<T> gestureOverlayViewClass, Integer... ids) {
		return new VXGestureOverlayView<T, VaporGestureOverlayView<T, ?>>(ids);
	}

	public static <T extends ExtractEditText> VExtractEditText<T> ExtractEditText(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VExtractEditText<T>(t);
	}

	public static <T extends ExtractEditText> VExtractEditText<T> ExtractEditText(
			View extractEditText) {
		Class<T> tClass = (Class<T>) extractEditText.getClass();
		return new VExtractEditText<T>((T) extractEditText);
	}

	public static <T extends ExtractEditText> VExtractEditText<T> ExtractEditText(
			T extractEditText) {
		return new VExtractEditText<T>(extractEditText);
	}

	public static <T extends ExtractEditText> VExtractEditText<T> ExtractEditText(
			Class<T> extractEditTextClass, Integer id) {
		return new VExtractEditText<T>(id);
	}

	public static VXExtractEditText<ExtractEditText, VaporExtractEditText<ExtractEditText, ?>> ExtractEditText(
			Integer... ids) {
		return new VXExtractEditText<ExtractEditText, VaporExtractEditText<ExtractEditText, ?>>(
				ids);
	}

	public static VXExtractEditText<ExtractEditText, VaporExtractEditText<ExtractEditText, ?>> ExtractEditText(
			Object... extractEditTexts) {
		return new VXExtractEditText<ExtractEditText, VaporExtractEditText<ExtractEditText, ?>>(
				extractEditTexts);
	}

	public static <T extends ExtractEditText> VXExtractEditText<T, VaporExtractEditText<T, ?>> ExtractEditText(
			Class<T> extractEditTextClass, Integer... ids) {
		return new VXExtractEditText<T, VaporExtractEditText<T, ?>>(ids);
	}

	public static <T extends KeyboardView> VKeyboardView<T> KeyboardView(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VKeyboardView<T>(t);
	}

	public static <T extends KeyboardView> VKeyboardView<T> KeyboardView(
			View keyboardView) {
		Class<T> tClass = (Class<T>) keyboardView.getClass();
		return new VKeyboardView<T>((T) keyboardView);
	}

	public static <T extends KeyboardView> VKeyboardView<T> KeyboardView(
			T keyboardView) {
		return new VKeyboardView<T>(keyboardView);
	}

	public static <T extends KeyboardView> VKeyboardView<T> KeyboardView(
			Class<T> keyboardViewClass, Integer id) {
		return new VKeyboardView<T>(id);
	}

	public static VXKeyboardView<KeyboardView, VaporKeyboardView<KeyboardView, ?>> KeyboardView(
			Integer... ids) {
		return new VXKeyboardView<KeyboardView, VaporKeyboardView<KeyboardView, ?>>(
				ids);
	}

	public static VXKeyboardView<KeyboardView, VaporKeyboardView<KeyboardView, ?>> KeyboardView(
			Object... keyboardViews) {
		return new VXKeyboardView<KeyboardView, VaporKeyboardView<KeyboardView, ?>>(
				keyboardViews);
	}

	public static <T extends KeyboardView> VXKeyboardView<T, VaporKeyboardView<T, ?>> KeyboardView(
			Class<T> keyboardViewClass, Integer... ids) {
		return new VXKeyboardView<T, VaporKeyboardView<T, ?>>(ids);
	}

	public static <T extends GLSurfaceView> VGLSurfaceView<T> GLSurfaceView(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VGLSurfaceView<T>(t);
	}

	public static <T extends GLSurfaceView> VGLSurfaceView<T> GLSurfaceView(
			View gLSurfaceView) {
		Class<T> tClass = (Class<T>) gLSurfaceView.getClass();
		return new VGLSurfaceView<T>((T) gLSurfaceView);
	}

	public static <T extends GLSurfaceView> VGLSurfaceView<T> GLSurfaceView(
			T gLSurfaceView) {
		return new VGLSurfaceView<T>(gLSurfaceView);
	}

	public static <T extends GLSurfaceView> VGLSurfaceView<T> GLSurfaceView(
			Class<T> gLSurfaceViewClass, Integer id) {
		return new VGLSurfaceView<T>(id);
	}

	public static VXGLSurfaceView<GLSurfaceView, VaporGLSurfaceView<GLSurfaceView, ?>> GLSurfaceView(
			Integer... ids) {
		return new VXGLSurfaceView<GLSurfaceView, VaporGLSurfaceView<GLSurfaceView, ?>>(
				ids);
	}

	public static VXGLSurfaceView<GLSurfaceView, VaporGLSurfaceView<GLSurfaceView, ?>> GLSurfaceView(
			Object... gLSurfaceViews) {
		return new VXGLSurfaceView<GLSurfaceView, VaporGLSurfaceView<GLSurfaceView, ?>>(
				gLSurfaceViews);
	}

	public static <T extends GLSurfaceView> VXGLSurfaceView<T, VaporGLSurfaceView<T, ?>> GLSurfaceView(
			Class<T> gLSurfaceViewClass, Integer... ids) {
		return new VXGLSurfaceView<T, VaporGLSurfaceView<T, ?>>(ids);
	}

	public static <T extends PagerTabStrip> VPagerTabStrip<T> PagerTabStrip(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VPagerTabStrip<T>(t);
	}

	public static <T extends PagerTabStrip> VPagerTabStrip<T> PagerTabStrip(
			View pagerTabStrip) {
		Class<T> tClass = (Class<T>) pagerTabStrip.getClass();
		return new VPagerTabStrip<T>((T) pagerTabStrip);
	}

	public static <T extends PagerTabStrip> VPagerTabStrip<T> PagerTabStrip(
			T pagerTabStrip) {
		return new VPagerTabStrip<T>(pagerTabStrip);
	}

	public static <T extends PagerTabStrip> VPagerTabStrip<T> PagerTabStrip(
			Class<T> pagerTabStripClass, Integer id) {
		return new VPagerTabStrip<T>(id);
	}

	public static VXPagerTabStrip<PagerTabStrip, VaporPagerTabStrip<PagerTabStrip, ?>> PagerTabStrip(
			Integer... ids) {
		return new VXPagerTabStrip<PagerTabStrip, VaporPagerTabStrip<PagerTabStrip, ?>>(
				ids);
	}

	public static VXPagerTabStrip<PagerTabStrip, VaporPagerTabStrip<PagerTabStrip, ?>> PagerTabStrip(
			Object... pagerTabStrips) {
		return new VXPagerTabStrip<PagerTabStrip, VaporPagerTabStrip<PagerTabStrip, ?>>(
				pagerTabStrips);
	}

	public static <T extends PagerTabStrip> VXPagerTabStrip<T, VaporPagerTabStrip<T, ?>> PagerTabStrip(
			Class<T> pagerTabStripClass, Integer... ids) {
		return new VXPagerTabStrip<T, VaporPagerTabStrip<T, ?>>(ids);
	}

	public static <T extends PagerTitleStrip> VPagerTitleStrip<T> PagerTitleStrip(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VPagerTitleStrip<T>(t);
	}

	public static <T extends PagerTitleStrip> VPagerTitleStrip<T> PagerTitleStrip(
			View pagerTitleStrip) {
		Class<T> tClass = (Class<T>) pagerTitleStrip.getClass();
		return new VPagerTitleStrip<T>((T) pagerTitleStrip);
	}

	public static <T extends PagerTitleStrip> VPagerTitleStrip<T> PagerTitleStrip(
			T pagerTitleStrip) {
		return new VPagerTitleStrip<T>(pagerTitleStrip);
	}

	public static <T extends PagerTitleStrip> VPagerTitleStrip<T> PagerTitleStrip(
			Class<T> pagerTitleStripClass, Integer id) {
		return new VPagerTitleStrip<T>(id);
	}

	public static VXPagerTitleStrip<PagerTitleStrip, VaporPagerTitleStrip<PagerTitleStrip, ?>> PagerTitleStrip(
			Integer... ids) {
		return new VXPagerTitleStrip<PagerTitleStrip, VaporPagerTitleStrip<PagerTitleStrip, ?>>(
				ids);
	}

	public static VXPagerTitleStrip<PagerTitleStrip, VaporPagerTitleStrip<PagerTitleStrip, ?>> PagerTitleStrip(
			Object... pagerTitleStrips) {
		return new VXPagerTitleStrip<PagerTitleStrip, VaporPagerTitleStrip<PagerTitleStrip, ?>>(
				pagerTitleStrips);
	}

	public static <T extends PagerTitleStrip> VXPagerTitleStrip<T, VaporPagerTitleStrip<T, ?>> PagerTitleStrip(
			Class<T> pagerTitleStripClass, Integer... ids) {
		return new VXPagerTitleStrip<T, VaporPagerTitleStrip<T, ?>>(ids);
	}

	public static <T extends ViewPager> VViewPager<T> ViewPager(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VViewPager<T>(t);
	}

	public static <T extends ViewPager> VViewPager<T> ViewPager(View viewPager) {
		Class<T> tClass = (Class<T>) viewPager.getClass();
		return new VViewPager<T>((T) viewPager);
	}

	public static <T extends ViewPager> VViewPager<T> ViewPager(T viewPager) {
		return new VViewPager<T>(viewPager);
	}

	public static <T extends ViewPager> VViewPager<T> ViewPager(
			Class<T> viewPagerClass, Integer id) {
		return new VViewPager<T>(id);
	}

	public static VXViewPager<ViewPager, VaporViewPager<ViewPager, ?>> ViewPager(
			Integer... ids) {
		return new VXViewPager<ViewPager, VaporViewPager<ViewPager, ?>>(ids);
	}

	public static VXViewPager<ViewPager, VaporViewPager<ViewPager, ?>> ViewPager(
			Object... viewPagers) {
		return new VXViewPager<ViewPager, VaporViewPager<ViewPager, ?>>(
				viewPagers);
	}

	public static <T extends ViewPager> VXViewPager<T, VaporViewPager<T, ?>> ViewPager(
			Class<T> viewPagerClass, Integer... ids) {
		return new VXViewPager<T, VaporViewPager<T, ?>>(ids);
	}

	public static <T extends SurfaceView> VSurfaceView<T> SurfaceView(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VSurfaceView<T>(t);
	}

	public static <T extends SurfaceView> VSurfaceView<T> SurfaceView(
			View surfaceView) {
		Class<T> tClass = (Class<T>) surfaceView.getClass();
		return new VSurfaceView<T>((T) surfaceView);
	}

	public static <T extends SurfaceView> VSurfaceView<T> SurfaceView(
			T surfaceView) {
		return new VSurfaceView<T>(surfaceView);
	}

	public static <T extends SurfaceView> VSurfaceView<T> SurfaceView(
			Class<T> surfaceViewClass, Integer id) {
		return new VSurfaceView<T>(id);
	}

	public static VXSurfaceView<SurfaceView, VaporSurfaceView<SurfaceView, ?>> SurfaceView(
			Integer... ids) {
		return new VXSurfaceView<SurfaceView, VaporSurfaceView<SurfaceView, ?>>(
				ids);
	}

	public static VXSurfaceView<SurfaceView, VaporSurfaceView<SurfaceView, ?>> SurfaceView(
			Object... surfaceViews) {
		return new VXSurfaceView<SurfaceView, VaporSurfaceView<SurfaceView, ?>>(
				surfaceViews);
	}

	public static <T extends SurfaceView> VXSurfaceView<T, VaporSurfaceView<T, ?>> SurfaceView(
			Class<T> surfaceViewClass, Integer... ids) {
		return new VXSurfaceView<T, VaporSurfaceView<T, ?>>(ids);
	}

	public static <T extends TextureView> VTextureView<T> TextureView(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VTextureView<T>(t);
	}

	public static <T extends TextureView> VTextureView<T> TextureView(
			View textureView) {
		Class<T> tClass = (Class<T>) textureView.getClass();
		return new VTextureView<T>((T) textureView);
	}

	public static <T extends TextureView> VTextureView<T> TextureView(
			T textureView) {
		return new VTextureView<T>(textureView);
	}

	public static <T extends TextureView> VTextureView<T> TextureView(
			Class<T> textureViewClass, Integer id) {
		return new VTextureView<T>(id);
	}

	public static VXTextureView<TextureView, VaporTextureView<TextureView, ?>> TextureView(
			Integer... ids) {
		return new VXTextureView<TextureView, VaporTextureView<TextureView, ?>>(
				ids);
	}

	public static VXTextureView<TextureView, VaporTextureView<TextureView, ?>> TextureView(
			Object... textureViews) {
		return new VXTextureView<TextureView, VaporTextureView<TextureView, ?>>(
				textureViews);
	}

	public static <T extends TextureView> VXTextureView<T, VaporTextureView<T, ?>> TextureView(
			Class<T> textureViewClass, Integer... ids) {
		return new VXTextureView<T, VaporTextureView<T, ?>>(ids);
	}

	public static <T extends View> VView<T> View(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VView<T>(t);
	}

	public static <T extends View> VView<T> View(T view) {
		return new VView<T>(view);
	}

	public static <T extends View> VView<T> View(Class<T> viewClass, Integer id) {
		return new VView<T>(id);
	}

	public static VXView<View, VaporView<View, ?>> View(Integer... ids) {
		return new VXView<View, VaporView<View, ?>>(ids);
	}

	public static VXView<View, VaporView<View, ?>> View(Object... views) {
		return new VXView<View, VaporView<View, ?>>(views);
	}

	public static <T extends View> VXView<T, VaporView<T, ?>> View(
			Class<T> viewClass, Integer... ids) {
		return new VXView<T, VaporView<T, ?>>(ids);
	}

	public static <T extends ViewStub> VViewStub<T> ViewStub(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VViewStub<T>(t);
	}

	public static <T extends ViewStub> VViewStub<T> ViewStub(View viewStub) {
		Class<T> tClass = (Class<T>) viewStub.getClass();
		return new VViewStub<T>((T) viewStub);
	}

	public static <T extends ViewStub> VViewStub<T> ViewStub(T viewStub) {
		return new VViewStub<T>(viewStub);
	}

	public static <T extends ViewStub> VViewStub<T> ViewStub(
			Class<T> viewStubClass, Integer id) {
		return new VViewStub<T>(id);
	}

	public static VXViewStub<ViewStub, VaporViewStub<ViewStub, ?>> ViewStub(
			Integer... ids) {
		return new VXViewStub<ViewStub, VaporViewStub<ViewStub, ?>>(ids);
	}

	public static VXViewStub<ViewStub, VaporViewStub<ViewStub, ?>> ViewStub(
			Object... viewStubs) {
		return new VXViewStub<ViewStub, VaporViewStub<ViewStub, ?>>(viewStubs);
	}

	public static <T extends ViewStub> VXViewStub<T, VaporViewStub<T, ?>> ViewStub(
			Class<T> viewStubClass, Integer... ids) {
		return new VXViewStub<T, VaporViewStub<T, ?>>(ids);
	}

	public static <T extends WebView> VWebView<T> WebView(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VWebView<T>(t);
	}

	public static <T extends WebView> VWebView<T> WebView(View webView) {
		Class<T> tClass = (Class<T>) webView.getClass();
		return new VWebView<T>((T) webView);
	}

	public static <T extends WebView> VWebView<T> WebView(T webView) {
		return new VWebView<T>(webView);
	}

	public static <T extends WebView> VWebView<T> WebView(
			Class<T> webViewClass, Integer id) {
		return new VWebView<T>(id);
	}

	public static VXWebView<WebView, VaporWebView<WebView, ?>> WebView(
			Integer... ids) {
		return new VXWebView<WebView, VaporWebView<WebView, ?>>(ids);
	}

	public static VXWebView<WebView, VaporWebView<WebView, ?>> WebView(
			Object... webViews) {
		return new VXWebView<WebView, VaporWebView<WebView, ?>>(webViews);
	}

	public static <T extends WebView> VXWebView<T, VaporWebView<T, ?>> WebView(
			Class<T> webViewClass, Integer... ids) {
		return new VXWebView<T, VaporWebView<T, ?>>(ids);
	}

	public static <T extends AbsoluteLayout> VAbsoluteLayout<T> AbsoluteLayout(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VAbsoluteLayout<T>(t);
	}

	public static <T extends AbsoluteLayout> VAbsoluteLayout<T> AbsoluteLayout(
			View absoluteLayout) {
		Class<T> tClass = (Class<T>) absoluteLayout.getClass();
		return new VAbsoluteLayout<T>((T) absoluteLayout);
	}

	public static <T extends AbsoluteLayout> VAbsoluteLayout<T> AbsoluteLayout(
			T absoluteLayout) {
		return new VAbsoluteLayout<T>(absoluteLayout);
	}

	public static <T extends AbsoluteLayout> VAbsoluteLayout<T> AbsoluteLayout(
			Class<T> absoluteLayoutClass, Integer id) {
		return new VAbsoluteLayout<T>(id);
	}

	public static VXAbsoluteLayout<AbsoluteLayout, VaporAbsoluteLayout<AbsoluteLayout, ?>> AbsoluteLayout(
			Integer... ids) {
		return new VXAbsoluteLayout<AbsoluteLayout, VaporAbsoluteLayout<AbsoluteLayout, ?>>(
				ids);
	}

	public static VXAbsoluteLayout<AbsoluteLayout, VaporAbsoluteLayout<AbsoluteLayout, ?>> AbsoluteLayout(
			Object... absoluteLayouts) {
		return new VXAbsoluteLayout<AbsoluteLayout, VaporAbsoluteLayout<AbsoluteLayout, ?>>(
				absoluteLayouts);
	}

	public static <T extends AbsoluteLayout> VXAbsoluteLayout<T, VaporAbsoluteLayout<T, ?>> AbsoluteLayout(
			Class<T> absoluteLayoutClass, Integer... ids) {
		return new VXAbsoluteLayout<T, VaporAbsoluteLayout<T, ?>>(ids);
	}

	public static <T extends AdapterViewFlipper> VAdapterViewFlipper<T> AdapterViewFlipper(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VAdapterViewFlipper<T>(t);
	}

	public static <T extends AdapterViewFlipper> VAdapterViewFlipper<T> AdapterViewFlipper(
			View adapterViewFlipper) {
		Class<T> tClass = (Class<T>) adapterViewFlipper.getClass();
		return new VAdapterViewFlipper<T>((T) adapterViewFlipper);
	}

	public static <T extends AdapterViewFlipper> VAdapterViewFlipper<T> AdapterViewFlipper(
			T adapterViewFlipper) {
		return new VAdapterViewFlipper<T>(adapterViewFlipper);
	}

	public static <T extends AdapterViewFlipper> VAdapterViewFlipper<T> AdapterViewFlipper(
			Class<T> adapterViewFlipperClass, Integer id) {
		return new VAdapterViewFlipper<T>(id);
	}

	public static VXAdapterViewFlipper<AdapterViewFlipper, VaporAdapterViewFlipper<AdapterViewFlipper, ?>> AdapterViewFlipper(
			Integer... ids) {
		return new VXAdapterViewFlipper<AdapterViewFlipper, VaporAdapterViewFlipper<AdapterViewFlipper, ?>>(
				ids);
	}

	public static VXAdapterViewFlipper<AdapterViewFlipper, VaporAdapterViewFlipper<AdapterViewFlipper, ?>> AdapterViewFlipper(
			Object... adapterViewFlippers) {
		return new VXAdapterViewFlipper<AdapterViewFlipper, VaporAdapterViewFlipper<AdapterViewFlipper, ?>>(
				adapterViewFlippers);
	}

	public static <T extends AdapterViewFlipper> VXAdapterViewFlipper<T, VaporAdapterViewFlipper<T, ?>> AdapterViewFlipper(
			Class<T> adapterViewFlipperClass, Integer... ids) {
		return new VXAdapterViewFlipper<T, VaporAdapterViewFlipper<T, ?>>(ids);
	}

	public static <T extends AnalogClock> VAnalogClock<T> AnalogClock(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VAnalogClock<T>(t);
	}

	public static <T extends AnalogClock> VAnalogClock<T> AnalogClock(
			View analogClock) {
		Class<T> tClass = (Class<T>) analogClock.getClass();
		return new VAnalogClock<T>((T) analogClock);
	}

	public static <T extends AnalogClock> VAnalogClock<T> AnalogClock(
			T analogClock) {
		return new VAnalogClock<T>(analogClock);
	}

	public static <T extends AnalogClock> VAnalogClock<T> AnalogClock(
			Class<T> analogClockClass, Integer id) {
		return new VAnalogClock<T>(id);
	}

	public static VXAnalogClock<AnalogClock, VaporAnalogClock<AnalogClock, ?>> AnalogClock(
			Integer... ids) {
		return new VXAnalogClock<AnalogClock, VaporAnalogClock<AnalogClock, ?>>(
				ids);
	}

	public static VXAnalogClock<AnalogClock, VaporAnalogClock<AnalogClock, ?>> AnalogClock(
			Object... analogClocks) {
		return new VXAnalogClock<AnalogClock, VaporAnalogClock<AnalogClock, ?>>(
				analogClocks);
	}

	public static <T extends AnalogClock> VXAnalogClock<T, VaporAnalogClock<T, ?>> AnalogClock(
			Class<T> analogClockClass, Integer... ids) {
		return new VXAnalogClock<T, VaporAnalogClock<T, ?>>(ids);
	}

	public static <T extends AutoCompleteTextView> VAutoCompleteTextView<T> AutoCompleteTextView(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VAutoCompleteTextView<T>(t);
	}

	public static <T extends AutoCompleteTextView> VAutoCompleteTextView<T> AutoCompleteTextView(
			View autoCompleteTextView) {
		Class<T> tClass = (Class<T>) autoCompleteTextView.getClass();
		return new VAutoCompleteTextView<T>((T) autoCompleteTextView);
	}

	public static <T extends AutoCompleteTextView> VAutoCompleteTextView<T> AutoCompleteTextView(
			T autoCompleteTextView) {
		return new VAutoCompleteTextView<T>(autoCompleteTextView);
	}

	public static <T extends AutoCompleteTextView> VAutoCompleteTextView<T> AutoCompleteTextView(
			Class<T> autoCompleteTextViewClass, Integer id) {
		return new VAutoCompleteTextView<T>(id);
	}

	public static VXAutoCompleteTextView<AutoCompleteTextView, VaporAutoCompleteTextView<AutoCompleteTextView, ?>> AutoCompleteTextView(
			Integer... ids) {
		return new VXAutoCompleteTextView<AutoCompleteTextView, VaporAutoCompleteTextView<AutoCompleteTextView, ?>>(
				ids);
	}

	public static VXAutoCompleteTextView<AutoCompleteTextView, VaporAutoCompleteTextView<AutoCompleteTextView, ?>> AutoCompleteTextView(
			Object... autoCompleteTextViews) {
		return new VXAutoCompleteTextView<AutoCompleteTextView, VaporAutoCompleteTextView<AutoCompleteTextView, ?>>(
				autoCompleteTextViews);
	}

	public static <T extends AutoCompleteTextView> VXAutoCompleteTextView<T, VaporAutoCompleteTextView<T, ?>> AutoCompleteTextView(
			Class<T> autoCompleteTextViewClass, Integer... ids) {
		return new VXAutoCompleteTextView<T, VaporAutoCompleteTextView<T, ?>>(
				ids);
	}

	public static <T extends Button> VButton<T> Button(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VButton<T>(t);
	}

	public static <T extends Button> VButton<T> Button(View button) {
		Class<T> tClass = (Class<T>) button.getClass();
		return new VButton<T>((T) button);
	}

	public static <T extends Button> VButton<T> Button(T button) {
		return new VButton<T>(button);
	}

	public static <T extends Button> VButton<T> Button(Class<T> buttonClass,
			Integer id) {
		return new VButton<T>(id);
	}

	public static VXButton<Button, VaporButton<Button, ?>> Button(
			Integer... ids) {
		return new VXButton<Button, VaporButton<Button, ?>>(ids);
	}

	public static VXButton<Button, VaporButton<Button, ?>> Button(
			Object... buttons) {
		return new VXButton<Button, VaporButton<Button, ?>>(buttons);
	}

	public static <T extends Button> VXButton<T, VaporButton<T, ?>> Button(
			Class<T> buttonClass, Integer... ids) {
		return new VXButton<T, VaporButton<T, ?>>(ids);
	}

	public static <T extends CalendarView> VCalendarView<T> CalendarView(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VCalendarView<T>(t);
	}

	public static <T extends CalendarView> VCalendarView<T> CalendarView(
			View calendarView) {
		Class<T> tClass = (Class<T>) calendarView.getClass();
		return new VCalendarView<T>((T) calendarView);
	}

	public static <T extends CalendarView> VCalendarView<T> CalendarView(
			T calendarView) {
		return new VCalendarView<T>(calendarView);
	}

	public static <T extends CalendarView> VCalendarView<T> CalendarView(
			Class<T> calendarViewClass, Integer id) {
		return new VCalendarView<T>(id);
	}

	public static VXCalendarView<CalendarView, VaporCalendarView<CalendarView, ?>> CalendarView(
			Integer... ids) {
		return new VXCalendarView<CalendarView, VaporCalendarView<CalendarView, ?>>(
				ids);
	}

	public static VXCalendarView<CalendarView, VaporCalendarView<CalendarView, ?>> CalendarView(
			Object... calendarViews) {
		return new VXCalendarView<CalendarView, VaporCalendarView<CalendarView, ?>>(
				calendarViews);
	}

	public static <T extends CalendarView> VXCalendarView<T, VaporCalendarView<T, ?>> CalendarView(
			Class<T> calendarViewClass, Integer... ids) {
		return new VXCalendarView<T, VaporCalendarView<T, ?>>(ids);
	}

	public static <T extends CheckBox> VCheckBox<T> CheckBox(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VCheckBox<T>(t);
	}

	public static <T extends CheckBox> VCheckBox<T> CheckBox(View checkBox) {
		Class<T> tClass = (Class<T>) checkBox.getClass();
		return new VCheckBox<T>((T) checkBox);
	}

	public static <T extends CheckBox> VCheckBox<T> CheckBox(T checkBox) {
		return new VCheckBox<T>(checkBox);
	}

	public static <T extends CheckBox> VCheckBox<T> CheckBox(
			Class<T> checkBoxClass, Integer id) {
		return new VCheckBox<T>(id);
	}

	public static VXCheckBox<CheckBox, VaporCheckBox<CheckBox, ?>> CheckBox(
			Integer... ids) {
		return new VXCheckBox<CheckBox, VaporCheckBox<CheckBox, ?>>(ids);
	}

	public static VXCheckBox<CheckBox, VaporCheckBox<CheckBox, ?>> CheckBox(
			Object... checkBoxs) {
		return new VXCheckBox<CheckBox, VaporCheckBox<CheckBox, ?>>(checkBoxs);
	}

	public static <T extends CheckBox> VXCheckBox<T, VaporCheckBox<T, ?>> CheckBox(
			Class<T> checkBoxClass, Integer... ids) {
		return new VXCheckBox<T, VaporCheckBox<T, ?>>(ids);
	}

	public static <T extends CheckedTextView> VCheckedTextView<T> CheckedTextView(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VCheckedTextView<T>(t);
	}

	public static <T extends CheckedTextView> VCheckedTextView<T> CheckedTextView(
			View checkedTextView) {
		Class<T> tClass = (Class<T>) checkedTextView.getClass();
		return new VCheckedTextView<T>((T) checkedTextView);
	}

	public static <T extends CheckedTextView> VCheckedTextView<T> CheckedTextView(
			T checkedTextView) {
		return new VCheckedTextView<T>(checkedTextView);
	}

	public static <T extends CheckedTextView> VCheckedTextView<T> CheckedTextView(
			Class<T> checkedTextViewClass, Integer id) {
		return new VCheckedTextView<T>(id);
	}

	public static VXCheckedTextView<CheckedTextView, VaporCheckedTextView<CheckedTextView, ?>> CheckedTextView(
			Integer... ids) {
		return new VXCheckedTextView<CheckedTextView, VaporCheckedTextView<CheckedTextView, ?>>(
				ids);
	}

	public static VXCheckedTextView<CheckedTextView, VaporCheckedTextView<CheckedTextView, ?>> CheckedTextView(
			Object... checkedTextViews) {
		return new VXCheckedTextView<CheckedTextView, VaporCheckedTextView<CheckedTextView, ?>>(
				checkedTextViews);
	}

	public static <T extends CheckedTextView> VXCheckedTextView<T, VaporCheckedTextView<T, ?>> CheckedTextView(
			Class<T> checkedTextViewClass, Integer... ids) {
		return new VXCheckedTextView<T, VaporCheckedTextView<T, ?>>(ids);
	}

	public static <T extends Chronometer> VChronometer<T> Chronometer(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VChronometer<T>(t);
	}

	public static <T extends Chronometer> VChronometer<T> Chronometer(
			View chronometer) {
		Class<T> tClass = (Class<T>) chronometer.getClass();
		return new VChronometer<T>((T) chronometer);
	}

	public static <T extends Chronometer> VChronometer<T> Chronometer(
			T chronometer) {
		return new VChronometer<T>(chronometer);
	}

	public static <T extends Chronometer> VChronometer<T> Chronometer(
			Class<T> chronometerClass, Integer id) {
		return new VChronometer<T>(id);
	}

	public static VXChronometer<Chronometer, VaporChronometer<Chronometer, ?>> Chronometer(
			Integer... ids) {
		return new VXChronometer<Chronometer, VaporChronometer<Chronometer, ?>>(
				ids);
	}

	public static VXChronometer<Chronometer, VaporChronometer<Chronometer, ?>> Chronometer(
			Object... chronometers) {
		return new VXChronometer<Chronometer, VaporChronometer<Chronometer, ?>>(
				chronometers);
	}

	public static <T extends Chronometer> VXChronometer<T, VaporChronometer<T, ?>> Chronometer(
			Class<T> chronometerClass, Integer... ids) {
		return new VXChronometer<T, VaporChronometer<T, ?>>(ids);
	}

	public static <T extends DatePicker> VDatePicker<T> DatePicker(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VDatePicker<T>(t);
	}

	public static <T extends DatePicker> VDatePicker<T> DatePicker(
			View datePicker) {
		Class<T> tClass = (Class<T>) datePicker.getClass();
		return new VDatePicker<T>((T) datePicker);
	}

	public static <T extends DatePicker> VDatePicker<T> DatePicker(T datePicker) {
		return new VDatePicker<T>(datePicker);
	}

	public static <T extends DatePicker> VDatePicker<T> DatePicker(
			Class<T> datePickerClass, Integer id) {
		return new VDatePicker<T>(id);
	}

	public static VXDatePicker<DatePicker, VaporDatePicker<DatePicker, ?>> DatePicker(
			Integer... ids) {
		return new VXDatePicker<DatePicker, VaporDatePicker<DatePicker, ?>>(ids);
	}

	public static VXDatePicker<DatePicker, VaporDatePicker<DatePicker, ?>> DatePicker(
			Object... datePickers) {
		return new VXDatePicker<DatePicker, VaporDatePicker<DatePicker, ?>>(
				datePickers);
	}

	public static <T extends DatePicker> VXDatePicker<T, VaporDatePicker<T, ?>> DatePicker(
			Class<T> datePickerClass, Integer... ids) {
		return new VXDatePicker<T, VaporDatePicker<T, ?>>(ids);
	}

	public static <T extends DialerFilter> VDialerFilter<T> DialerFilter(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VDialerFilter<T>(t);
	}

	public static <T extends DialerFilter> VDialerFilter<T> DialerFilter(
			View dialerFilter) {
		Class<T> tClass = (Class<T>) dialerFilter.getClass();
		return new VDialerFilter<T>((T) dialerFilter);
	}

	public static <T extends DialerFilter> VDialerFilter<T> DialerFilter(
			T dialerFilter) {
		return new VDialerFilter<T>(dialerFilter);
	}

	public static <T extends DialerFilter> VDialerFilter<T> DialerFilter(
			Class<T> dialerFilterClass, Integer id) {
		return new VDialerFilter<T>(id);
	}

	public static VXDialerFilter<DialerFilter, VaporDialerFilter<DialerFilter, ?>> DialerFilter(
			Integer... ids) {
		return new VXDialerFilter<DialerFilter, VaporDialerFilter<DialerFilter, ?>>(
				ids);
	}

	public static VXDialerFilter<DialerFilter, VaporDialerFilter<DialerFilter, ?>> DialerFilter(
			Object... dialerFilters) {
		return new VXDialerFilter<DialerFilter, VaporDialerFilter<DialerFilter, ?>>(
				dialerFilters);
	}

	public static <T extends DialerFilter> VXDialerFilter<T, VaporDialerFilter<T, ?>> DialerFilter(
			Class<T> dialerFilterClass, Integer... ids) {
		return new VXDialerFilter<T, VaporDialerFilter<T, ?>>(ids);
	}

	public static <T extends EditText> VEditText<T> EditText(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VEditText<T>(t);
	}

	public static <T extends EditText> VEditText<T> EditText(View editText) {
		Class<T> tClass = (Class<T>) editText.getClass();
		return new VEditText<T>((T) editText);
	}

	public static <T extends EditText> VEditText<T> EditText(T editText) {
		return new VEditText<T>(editText);
	}

	public static <T extends EditText> VEditText<T> EditText(
			Class<T> editTextClass, Integer id) {
		return new VEditText<T>(id);
	}

	public static VXEditText<EditText, VaporEditText<EditText, ?>> EditText(
			Integer... ids) {
		return new VXEditText<EditText, VaporEditText<EditText, ?>>(ids);
	}

	public static VXEditText<EditText, VaporEditText<EditText, ?>> EditText(
			Object... editTexts) {
		return new VXEditText<EditText, VaporEditText<EditText, ?>>(editTexts);
	}

	public static <T extends EditText> VXEditText<T, VaporEditText<T, ?>> EditText(
			Class<T> editTextClass, Integer... ids) {
		return new VXEditText<T, VaporEditText<T, ?>>(ids);
	}

	public static <T extends ExpandableListView> VExpandableListView<T> ExpandableListView(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VExpandableListView<T>(t);
	}

	public static <T extends ExpandableListView> VExpandableListView<T> ExpandableListView(
			View expandableListView) {
		Class<T> tClass = (Class<T>) expandableListView.getClass();
		return new VExpandableListView<T>((T) expandableListView);
	}

	public static <T extends ExpandableListView> VExpandableListView<T> ExpandableListView(
			T expandableListView) {
		return new VExpandableListView<T>(expandableListView);
	}

	public static <T extends ExpandableListView> VExpandableListView<T> ExpandableListView(
			Class<T> expandableListViewClass, Integer id) {
		return new VExpandableListView<T>(id);
	}

	public static VXExpandableListView<ExpandableListView, VaporExpandableListView<ExpandableListView, ?>> ExpandableListView(
			Integer... ids) {
		return new VXExpandableListView<ExpandableListView, VaporExpandableListView<ExpandableListView, ?>>(
				ids);
	}

	public static VXExpandableListView<ExpandableListView, VaporExpandableListView<ExpandableListView, ?>> ExpandableListView(
			Object... expandableListViews) {
		return new VXExpandableListView<ExpandableListView, VaporExpandableListView<ExpandableListView, ?>>(
				expandableListViews);
	}

	public static <T extends ExpandableListView> VXExpandableListView<T, VaporExpandableListView<T, ?>> ExpandableListView(
			Class<T> expandableListViewClass, Integer... ids) {
		return new VXExpandableListView<T, VaporExpandableListView<T, ?>>(ids);
	}

	public static <T extends FrameLayout> VFrameLayout<T> FrameLayout(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VFrameLayout<T>(t);
	}

	public static <T extends FrameLayout> VFrameLayout<T> FrameLayout(
			View frameLayout) {
		Class<T> tClass = (Class<T>) frameLayout.getClass();
		return new VFrameLayout<T>((T) frameLayout);
	}

	public static <T extends FrameLayout> VFrameLayout<T> FrameLayout(
			T frameLayout) {
		return new VFrameLayout<T>(frameLayout);
	}

	public static <T extends FrameLayout> VFrameLayout<T> FrameLayout(
			Class<T> frameLayoutClass, Integer id) {
		return new VFrameLayout<T>(id);
	}

	public static VXFrameLayout<FrameLayout, VaporFrameLayout<FrameLayout, ?>> FrameLayout(
			Integer... ids) {
		return new VXFrameLayout<FrameLayout, VaporFrameLayout<FrameLayout, ?>>(
				ids);
	}

	public static VXFrameLayout<FrameLayout, VaporFrameLayout<FrameLayout, ?>> FrameLayout(
			Object... frameLayouts) {
		return new VXFrameLayout<FrameLayout, VaporFrameLayout<FrameLayout, ?>>(
				frameLayouts);
	}

	public static <T extends FrameLayout> VXFrameLayout<T, VaporFrameLayout<T, ?>> FrameLayout(
			Class<T> frameLayoutClass, Integer... ids) {
		return new VXFrameLayout<T, VaporFrameLayout<T, ?>>(ids);
	}

	public static <T extends GridLayout> VGridLayout<T> GridLayout(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VGridLayout<T>(t);
	}

	public static <T extends GridLayout> VGridLayout<T> GridLayout(
			View gridLayout) {
		Class<T> tClass = (Class<T>) gridLayout.getClass();
		return new VGridLayout<T>((T) gridLayout);
	}

	public static <T extends GridLayout> VGridLayout<T> GridLayout(T gridLayout) {
		return new VGridLayout<T>(gridLayout);
	}

	public static <T extends GridLayout> VGridLayout<T> GridLayout(
			Class<T> gridLayoutClass, Integer id) {
		return new VGridLayout<T>(id);
	}

	public static VXGridLayout<GridLayout, VaporGridLayout<GridLayout, ?>> GridLayout(
			Integer... ids) {
		return new VXGridLayout<GridLayout, VaporGridLayout<GridLayout, ?>>(ids);
	}

	public static VXGridLayout<GridLayout, VaporGridLayout<GridLayout, ?>> GridLayout(
			Object... gridLayouts) {
		return new VXGridLayout<GridLayout, VaporGridLayout<GridLayout, ?>>(
				gridLayouts);
	}

	public static <T extends GridLayout> VXGridLayout<T, VaporGridLayout<T, ?>> GridLayout(
			Class<T> gridLayoutClass, Integer... ids) {
		return new VXGridLayout<T, VaporGridLayout<T, ?>>(ids);
	}

	public static <T extends GridView> VGridView<T> GridView(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VGridView<T>(t);
	}

	public static <T extends GridView> VGridView<T> GridView(View gridView) {
		Class<T> tClass = (Class<T>) gridView.getClass();
		return new VGridView<T>((T) gridView);
	}

	public static <T extends GridView> VGridView<T> GridView(T gridView) {
		return new VGridView<T>(gridView);
	}

	public static <T extends GridView> VGridView<T> GridView(
			Class<T> gridViewClass, Integer id) {
		return new VGridView<T>(id);
	}

	public static VXGridView<GridView, VaporGridView<GridView, ?>> GridView(
			Integer... ids) {
		return new VXGridView<GridView, VaporGridView<GridView, ?>>(ids);
	}

	public static VXGridView<GridView, VaporGridView<GridView, ?>> GridView(
			Object... gridViews) {
		return new VXGridView<GridView, VaporGridView<GridView, ?>>(gridViews);
	}

	public static <T extends GridView> VXGridView<T, VaporGridView<T, ?>> GridView(
			Class<T> gridViewClass, Integer... ids) {
		return new VXGridView<T, VaporGridView<T, ?>>(ids);
	}

	public static <T extends HorizontalScrollView> VHorizontalScrollView<T> HorizontalScrollView(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VHorizontalScrollView<T>(t);
	}

	public static <T extends HorizontalScrollView> VHorizontalScrollView<T> HorizontalScrollView(
			View horizontalScrollView) {
		Class<T> tClass = (Class<T>) horizontalScrollView.getClass();
		return new VHorizontalScrollView<T>((T) horizontalScrollView);
	}

	public static <T extends HorizontalScrollView> VHorizontalScrollView<T> HorizontalScrollView(
			T horizontalScrollView) {
		return new VHorizontalScrollView<T>(horizontalScrollView);
	}

	public static <T extends HorizontalScrollView> VHorizontalScrollView<T> HorizontalScrollView(
			Class<T> horizontalScrollViewClass, Integer id) {
		return new VHorizontalScrollView<T>(id);
	}

	public static VXHorizontalScrollView<HorizontalScrollView, VaporHorizontalScrollView<HorizontalScrollView, ?>> HorizontalScrollView(
			Integer... ids) {
		return new VXHorizontalScrollView<HorizontalScrollView, VaporHorizontalScrollView<HorizontalScrollView, ?>>(
				ids);
	}

	public static VXHorizontalScrollView<HorizontalScrollView, VaporHorizontalScrollView<HorizontalScrollView, ?>> HorizontalScrollView(
			Object... horizontalScrollViews) {
		return new VXHorizontalScrollView<HorizontalScrollView, VaporHorizontalScrollView<HorizontalScrollView, ?>>(
				horizontalScrollViews);
	}

	public static <T extends HorizontalScrollView> VXHorizontalScrollView<T, VaporHorizontalScrollView<T, ?>> HorizontalScrollView(
			Class<T> horizontalScrollViewClass, Integer... ids) {
		return new VXHorizontalScrollView<T, VaporHorizontalScrollView<T, ?>>(
				ids);
	}

	public static <T extends ImageButton> VImageButton<T> ImageButton(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VImageButton<T>(t);
	}

	public static <T extends ImageButton> VImageButton<T> ImageButton(
			View imageButton) {
		Class<T> tClass = (Class<T>) imageButton.getClass();
		return new VImageButton<T>((T) imageButton);
	}

	public static <T extends ImageButton> VImageButton<T> ImageButton(
			T imageButton) {
		return new VImageButton<T>(imageButton);
	}

	public static <T extends ImageButton> VImageButton<T> ImageButton(
			Class<T> imageButtonClass, Integer id) {
		return new VImageButton<T>(id);
	}

	public static VXImageButton<ImageButton, VaporImageButton<ImageButton, ?>> ImageButton(
			Integer... ids) {
		return new VXImageButton<ImageButton, VaporImageButton<ImageButton, ?>>(
				ids);
	}

	public static VXImageButton<ImageButton, VaporImageButton<ImageButton, ?>> ImageButton(
			Object... imageButtons) {
		return new VXImageButton<ImageButton, VaporImageButton<ImageButton, ?>>(
				imageButtons);
	}

	public static <T extends ImageButton> VXImageButton<T, VaporImageButton<T, ?>> ImageButton(
			Class<T> imageButtonClass, Integer... ids) {
		return new VXImageButton<T, VaporImageButton<T, ?>>(ids);
	}

	public static <T extends ImageSwitcher> VImageSwitcher<T> ImageSwitcher(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VImageSwitcher<T>(t);
	}

	public static <T extends ImageSwitcher> VImageSwitcher<T> ImageSwitcher(
			View imageSwitcher) {
		Class<T> tClass = (Class<T>) imageSwitcher.getClass();
		return new VImageSwitcher<T>((T) imageSwitcher);
	}

	public static <T extends ImageSwitcher> VImageSwitcher<T> ImageSwitcher(
			T imageSwitcher) {
		return new VImageSwitcher<T>(imageSwitcher);
	}

	public static <T extends ImageSwitcher> VImageSwitcher<T> ImageSwitcher(
			Class<T> imageSwitcherClass, Integer id) {
		return new VImageSwitcher<T>(id);
	}

	public static VXImageSwitcher<ImageSwitcher, VaporImageSwitcher<ImageSwitcher, ?>> ImageSwitcher(
			Integer... ids) {
		return new VXImageSwitcher<ImageSwitcher, VaporImageSwitcher<ImageSwitcher, ?>>(
				ids);
	}

	public static VXImageSwitcher<ImageSwitcher, VaporImageSwitcher<ImageSwitcher, ?>> ImageSwitcher(
			Object... imageSwitchers) {
		return new VXImageSwitcher<ImageSwitcher, VaporImageSwitcher<ImageSwitcher, ?>>(
				imageSwitchers);
	}

	public static <T extends ImageSwitcher> VXImageSwitcher<T, VaporImageSwitcher<T, ?>> ImageSwitcher(
			Class<T> imageSwitcherClass, Integer... ids) {
		return new VXImageSwitcher<T, VaporImageSwitcher<T, ?>>(ids);
	}

	public static <T extends ImageView> VImageView<T> ImageView(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VImageView<T>(t);
	}

	public static <T extends ImageView> VImageView<T> ImageView(View imageView) {
		Class<T> tClass = (Class<T>) imageView.getClass();
		return new VImageView<T>((T) imageView);
	}

	public static <T extends ImageView> VImageView<T> ImageView(T imageView) {
		return new VImageView<T>(imageView);
	}

	public static <T extends ImageView> VImageView<T> ImageView(
			Class<T> imageViewClass, Integer id) {
		return new VImageView<T>(id);
	}

	public static VXImageView<ImageView, VaporImageView<ImageView, ?>> ImageView(
			Integer... ids) {
		return new VXImageView<ImageView, VaporImageView<ImageView, ?>>(ids);
	}

	public static VXImageView<ImageView, VaporImageView<ImageView, ?>> ImageView(
			Object... imageViews) {
		return new VXImageView<ImageView, VaporImageView<ImageView, ?>>(
				imageViews);
	}

	public static <T extends ImageView> VXImageView<T, VaporImageView<T, ?>> ImageView(
			Class<T> imageViewClass, Integer... ids) {
		return new VXImageView<T, VaporImageView<T, ?>>(ids);
	}

	public static <T extends LinearLayout> VLinearLayout<T> LinearLayout(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VLinearLayout<T>(t);
	}

	public static <T extends LinearLayout> VLinearLayout<T> LinearLayout(
			View linearLayout) {
		Class<T> tClass = (Class<T>) linearLayout.getClass();
		return new VLinearLayout<T>((T) linearLayout);
	}

	public static <T extends LinearLayout> VLinearLayout<T> LinearLayout(
			T linearLayout) {
		return new VLinearLayout<T>(linearLayout);
	}

	public static <T extends LinearLayout> VLinearLayout<T> LinearLayout(
			Class<T> linearLayoutClass, Integer id) {
		return new VLinearLayout<T>(id);
	}

	public static VXLinearLayout<LinearLayout, VaporLinearLayout<LinearLayout, ?>> LinearLayout(
			Integer... ids) {
		return new VXLinearLayout<LinearLayout, VaporLinearLayout<LinearLayout, ?>>(
				ids);
	}

	public static VXLinearLayout<LinearLayout, VaporLinearLayout<LinearLayout, ?>> LinearLayout(
			Object... linearLayouts) {
		return new VXLinearLayout<LinearLayout, VaporLinearLayout<LinearLayout, ?>>(
				linearLayouts);
	}

	public static <T extends LinearLayout> VXLinearLayout<T, VaporLinearLayout<T, ?>> LinearLayout(
			Class<T> linearLayoutClass, Integer... ids) {
		return new VXLinearLayout<T, VaporLinearLayout<T, ?>>(ids);
	}

	public static <T extends ListView> VListView<T> ListView(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VListView<T>(t);
	}

	public static <T extends ListView> VListView<T> ListView(View listView) {
		Class<T> tClass = (Class<T>) listView.getClass();
		return new VListView<T>((T) listView);
	}

	public static <T extends ListView> VListView<T> ListView(T listView) {
		return new VListView<T>(listView);
	}

	public static <T extends ListView> VListView<T> ListView(
			Class<T> listViewClass, Integer id) {
		return new VListView<T>(id);
	}

	public static VXListView<ListView, VaporListView<ListView, ?>> ListView(
			Integer... ids) {
		return new VXListView<ListView, VaporListView<ListView, ?>>(ids);
	}

	public static VXListView<ListView, VaporListView<ListView, ?>> ListView(
			Object... listViews) {
		return new VXListView<ListView, VaporListView<ListView, ?>>(listViews);
	}

	public static <T extends ListView> VXListView<T, VaporListView<T, ?>> ListView(
			Class<T> listViewClass, Integer... ids) {
		return new VXListView<T, VaporListView<T, ?>>(ids);
	}

	public static <T extends MediaController> VMediaController<T> MediaController(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VMediaController<T>(t);
	}

	public static <T extends MediaController> VMediaController<T> MediaController(
			View mediaController) {
		Class<T> tClass = (Class<T>) mediaController.getClass();
		return new VMediaController<T>((T) mediaController);
	}

	public static <T extends MediaController> VMediaController<T> MediaController(
			T mediaController) {
		return new VMediaController<T>(mediaController);
	}

	public static <T extends MediaController> VMediaController<T> MediaController(
			Class<T> mediaControllerClass, Integer id) {
		return new VMediaController<T>(id);
	}

	public static VXMediaController<MediaController, VaporMediaController<MediaController, ?>> MediaController(
			Integer... ids) {
		return new VXMediaController<MediaController, VaporMediaController<MediaController, ?>>(
				ids);
	}

	public static VXMediaController<MediaController, VaporMediaController<MediaController, ?>> MediaController(
			Object... mediaControllers) {
		return new VXMediaController<MediaController, VaporMediaController<MediaController, ?>>(
				mediaControllers);
	}

	public static <T extends MediaController> VXMediaController<T, VaporMediaController<T, ?>> MediaController(
			Class<T> mediaControllerClass, Integer... ids) {
		return new VXMediaController<T, VaporMediaController<T, ?>>(ids);
	}

	public static <T extends MultiAutoCompleteTextView> VMultiAutoCompleteTextView<T> MultiAutoCompleteTextView(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VMultiAutoCompleteTextView<T>(t);
	}

	public static <T extends MultiAutoCompleteTextView> VMultiAutoCompleteTextView<T> MultiAutoCompleteTextView(
			View multiAutoCompleteTextView) {
		Class<T> tClass = (Class<T>) multiAutoCompleteTextView.getClass();
		return new VMultiAutoCompleteTextView<T>((T) multiAutoCompleteTextView);
	}

	public static <T extends MultiAutoCompleteTextView> VMultiAutoCompleteTextView<T> MultiAutoCompleteTextView(
			T multiAutoCompleteTextView) {
		return new VMultiAutoCompleteTextView<T>(multiAutoCompleteTextView);
	}

	public static <T extends MultiAutoCompleteTextView> VMultiAutoCompleteTextView<T> MultiAutoCompleteTextView(
			Class<T> multiAutoCompleteTextViewClass, Integer id) {
		return new VMultiAutoCompleteTextView<T>(id);
	}

	public static VXMultiAutoCompleteTextView<MultiAutoCompleteTextView, VaporMultiAutoCompleteTextView<MultiAutoCompleteTextView, ?>> MultiAutoCompleteTextView(
			Integer... ids) {
		return new VXMultiAutoCompleteTextView<MultiAutoCompleteTextView, VaporMultiAutoCompleteTextView<MultiAutoCompleteTextView, ?>>(
				ids);
	}

	public static VXMultiAutoCompleteTextView<MultiAutoCompleteTextView, VaporMultiAutoCompleteTextView<MultiAutoCompleteTextView, ?>> MultiAutoCompleteTextView(
			Object... multiAutoCompleteTextViews) {
		return new VXMultiAutoCompleteTextView<MultiAutoCompleteTextView, VaporMultiAutoCompleteTextView<MultiAutoCompleteTextView, ?>>(
				multiAutoCompleteTextViews);
	}

	public static <T extends MultiAutoCompleteTextView> VXMultiAutoCompleteTextView<T, VaporMultiAutoCompleteTextView<T, ?>> MultiAutoCompleteTextView(
			Class<T> multiAutoCompleteTextViewClass, Integer... ids) {
		return new VXMultiAutoCompleteTextView<T, VaporMultiAutoCompleteTextView<T, ?>>(
				ids);
	}

	public static <T extends NumberPicker> VNumberPicker<T> NumberPicker(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VNumberPicker<T>(t);
	}

	public static <T extends NumberPicker> VNumberPicker<T> NumberPicker(
			View numberPicker) {
		Class<T> tClass = (Class<T>) numberPicker.getClass();
		return new VNumberPicker<T>((T) numberPicker);
	}

	public static <T extends NumberPicker> VNumberPicker<T> NumberPicker(
			T numberPicker) {
		return new VNumberPicker<T>(numberPicker);
	}

	public static <T extends NumberPicker> VNumberPicker<T> NumberPicker(
			Class<T> numberPickerClass, Integer id) {
		return new VNumberPicker<T>(id);
	}

	public static VXNumberPicker<NumberPicker, VaporNumberPicker<NumberPicker, ?>> NumberPicker(
			Integer... ids) {
		return new VXNumberPicker<NumberPicker, VaporNumberPicker<NumberPicker, ?>>(
				ids);
	}

	public static VXNumberPicker<NumberPicker, VaporNumberPicker<NumberPicker, ?>> NumberPicker(
			Object... numberPickers) {
		return new VXNumberPicker<NumberPicker, VaporNumberPicker<NumberPicker, ?>>(
				numberPickers);
	}

	public static <T extends NumberPicker> VXNumberPicker<T, VaporNumberPicker<T, ?>> NumberPicker(
			Class<T> numberPickerClass, Integer... ids) {
		return new VXNumberPicker<T, VaporNumberPicker<T, ?>>(ids);
	}

	public static <T extends ProgressBar> VProgressBar<T> ProgressBar(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VProgressBar<T>(t);
	}

	public static <T extends ProgressBar> VProgressBar<T> ProgressBar(
			View progressBar) {
		Class<T> tClass = (Class<T>) progressBar.getClass();
		return new VProgressBar<T>((T) progressBar);
	}

	public static <T extends ProgressBar> VProgressBar<T> ProgressBar(
			T progressBar) {
		return new VProgressBar<T>(progressBar);
	}

	public static <T extends ProgressBar> VProgressBar<T> ProgressBar(
			Class<T> progressBarClass, Integer id) {
		return new VProgressBar<T>(id);
	}

	public static VXProgressBar<ProgressBar, VaporProgressBar<ProgressBar, ?>> ProgressBar(
			Integer... ids) {
		return new VXProgressBar<ProgressBar, VaporProgressBar<ProgressBar, ?>>(
				ids);
	}

	public static VXProgressBar<ProgressBar, VaporProgressBar<ProgressBar, ?>> ProgressBar(
			Object... progressBars) {
		return new VXProgressBar<ProgressBar, VaporProgressBar<ProgressBar, ?>>(
				progressBars);
	}

	public static <T extends ProgressBar> VXProgressBar<T, VaporProgressBar<T, ?>> ProgressBar(
			Class<T> progressBarClass, Integer... ids) {
		return new VXProgressBar<T, VaporProgressBar<T, ?>>(ids);
	}

	public static <T extends QuickContactBadge> VQuickContactBadge<T> QuickContactBadge(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VQuickContactBadge<T>(t);
	}

	public static <T extends QuickContactBadge> VQuickContactBadge<T> QuickContactBadge(
			View quickContactBadge) {
		Class<T> tClass = (Class<T>) quickContactBadge.getClass();
		return new VQuickContactBadge<T>((T) quickContactBadge);
	}

	public static <T extends QuickContactBadge> VQuickContactBadge<T> QuickContactBadge(
			T quickContactBadge) {
		return new VQuickContactBadge<T>(quickContactBadge);
	}

	public static <T extends QuickContactBadge> VQuickContactBadge<T> QuickContactBadge(
			Class<T> quickContactBadgeClass, Integer id) {
		return new VQuickContactBadge<T>(id);
	}

	public static VXQuickContactBadge<QuickContactBadge, VaporQuickContactBadge<QuickContactBadge, ?>> QuickContactBadge(
			Integer... ids) {
		return new VXQuickContactBadge<QuickContactBadge, VaporQuickContactBadge<QuickContactBadge, ?>>(
				ids);
	}

	public static VXQuickContactBadge<QuickContactBadge, VaporQuickContactBadge<QuickContactBadge, ?>> QuickContactBadge(
			Object... quickContactBadges) {
		return new VXQuickContactBadge<QuickContactBadge, VaporQuickContactBadge<QuickContactBadge, ?>>(
				quickContactBadges);
	}

	public static <T extends QuickContactBadge> VXQuickContactBadge<T, VaporQuickContactBadge<T, ?>> QuickContactBadge(
			Class<T> quickContactBadgeClass, Integer... ids) {
		return new VXQuickContactBadge<T, VaporQuickContactBadge<T, ?>>(ids);
	}

	public static <T extends RadioButton> VRadioButton<T> RadioButton(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VRadioButton<T>(t);
	}

	public static <T extends RadioButton> VRadioButton<T> RadioButton(
			View radioButton) {
		Class<T> tClass = (Class<T>) radioButton.getClass();
		return new VRadioButton<T>((T) radioButton);
	}

	public static <T extends RadioButton> VRadioButton<T> RadioButton(
			T radioButton) {
		return new VRadioButton<T>(radioButton);
	}

	public static <T extends RadioButton> VRadioButton<T> RadioButton(
			Class<T> radioButtonClass, Integer id) {
		return new VRadioButton<T>(id);
	}

	public static VXRadioButton<RadioButton, VaporRadioButton<RadioButton, ?>> RadioButton(
			Integer... ids) {
		return new VXRadioButton<RadioButton, VaporRadioButton<RadioButton, ?>>(
				ids);
	}

	public static VXRadioButton<RadioButton, VaporRadioButton<RadioButton, ?>> RadioButton(
			Object... radioButtons) {
		return new VXRadioButton<RadioButton, VaporRadioButton<RadioButton, ?>>(
				radioButtons);
	}

	public static <T extends RadioButton> VXRadioButton<T, VaporRadioButton<T, ?>> RadioButton(
			Class<T> radioButtonClass, Integer... ids) {
		return new VXRadioButton<T, VaporRadioButton<T, ?>>(ids);
	}

	public static <T extends RadioGroup> VRadioGroup<T> RadioGroup(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VRadioGroup<T>(t);
	}

	public static <T extends RadioGroup> VRadioGroup<T> RadioGroup(
			View radioGroup) {
		Class<T> tClass = (Class<T>) radioGroup.getClass();
		return new VRadioGroup<T>((T) radioGroup);
	}

	public static <T extends RadioGroup> VRadioGroup<T> RadioGroup(T radioGroup) {
		return new VRadioGroup<T>(radioGroup);
	}

	public static <T extends RadioGroup> VRadioGroup<T> RadioGroup(
			Class<T> radioGroupClass, Integer id) {
		return new VRadioGroup<T>(id);
	}

	public static VXRadioGroup<RadioGroup, VaporRadioGroup<RadioGroup, ?>> RadioGroup(
			Integer... ids) {
		return new VXRadioGroup<RadioGroup, VaporRadioGroup<RadioGroup, ?>>(ids);
	}

	public static VXRadioGroup<RadioGroup, VaporRadioGroup<RadioGroup, ?>> RadioGroup(
			Object... radioGroups) {
		return new VXRadioGroup<RadioGroup, VaporRadioGroup<RadioGroup, ?>>(
				radioGroups);
	}

	public static <T extends RadioGroup> VXRadioGroup<T, VaporRadioGroup<T, ?>> RadioGroup(
			Class<T> radioGroupClass, Integer... ids) {
		return new VXRadioGroup<T, VaporRadioGroup<T, ?>>(ids);
	}

	public static <T extends RatingBar> VRatingBar<T> RatingBar(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VRatingBar<T>(t);
	}

	public static <T extends RatingBar> VRatingBar<T> RatingBar(View ratingBar) {
		Class<T> tClass = (Class<T>) ratingBar.getClass();
		return new VRatingBar<T>((T) ratingBar);
	}

	public static <T extends RatingBar> VRatingBar<T> RatingBar(T ratingBar) {
		return new VRatingBar<T>(ratingBar);
	}

	public static <T extends RatingBar> VRatingBar<T> RatingBar(
			Class<T> ratingBarClass, Integer id) {
		return new VRatingBar<T>(id);
	}

	public static VXRatingBar<RatingBar, VaporRatingBar<RatingBar, ?>> RatingBar(
			Integer... ids) {
		return new VXRatingBar<RatingBar, VaporRatingBar<RatingBar, ?>>(ids);
	}

	public static VXRatingBar<RatingBar, VaporRatingBar<RatingBar, ?>> RatingBar(
			Object... ratingBars) {
		return new VXRatingBar<RatingBar, VaporRatingBar<RatingBar, ?>>(
				ratingBars);
	}

	public static <T extends RatingBar> VXRatingBar<T, VaporRatingBar<T, ?>> RatingBar(
			Class<T> ratingBarClass, Integer... ids) {
		return new VXRatingBar<T, VaporRatingBar<T, ?>>(ids);
	}

	public static <T extends RelativeLayout> VRelativeLayout<T> RelativeLayout(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VRelativeLayout<T>(t);
	}

	public static <T extends RelativeLayout> VRelativeLayout<T> RelativeLayout(
			View relativeLayout) {
		Class<T> tClass = (Class<T>) relativeLayout.getClass();
		return new VRelativeLayout<T>((T) relativeLayout);
	}

	public static <T extends RelativeLayout> VRelativeLayout<T> RelativeLayout(
			T relativeLayout) {
		return new VRelativeLayout<T>(relativeLayout);
	}

	public static <T extends RelativeLayout> VRelativeLayout<T> RelativeLayout(
			Class<T> relativeLayoutClass, Integer id) {
		return new VRelativeLayout<T>(id);
	}

	public static VXRelativeLayout<RelativeLayout, VaporRelativeLayout<RelativeLayout, ?>> RelativeLayout(
			Integer... ids) {
		return new VXRelativeLayout<RelativeLayout, VaporRelativeLayout<RelativeLayout, ?>>(
				ids);
	}

	public static VXRelativeLayout<RelativeLayout, VaporRelativeLayout<RelativeLayout, ?>> RelativeLayout(
			Object... relativeLayouts) {
		return new VXRelativeLayout<RelativeLayout, VaporRelativeLayout<RelativeLayout, ?>>(
				relativeLayouts);
	}

	public static <T extends RelativeLayout> VXRelativeLayout<T, VaporRelativeLayout<T, ?>> RelativeLayout(
			Class<T> relativeLayoutClass, Integer... ids) {
		return new VXRelativeLayout<T, VaporRelativeLayout<T, ?>>(ids);
	}

	public static <T extends ScrollView> VScrollView<T> ScrollView(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VScrollView<T>(t);
	}

	public static <T extends ScrollView> VScrollView<T> ScrollView(
			View scrollView) {
		Class<T> tClass = (Class<T>) scrollView.getClass();
		return new VScrollView<T>((T) scrollView);
	}

	public static <T extends ScrollView> VScrollView<T> ScrollView(T scrollView) {
		return new VScrollView<T>(scrollView);
	}

	public static <T extends ScrollView> VScrollView<T> ScrollView(
			Class<T> scrollViewClass, Integer id) {
		return new VScrollView<T>(id);
	}

	public static VXScrollView<ScrollView, VaporScrollView<ScrollView, ?>> ScrollView(
			Integer... ids) {
		return new VXScrollView<ScrollView, VaporScrollView<ScrollView, ?>>(ids);
	}

	public static VXScrollView<ScrollView, VaporScrollView<ScrollView, ?>> ScrollView(
			Object... scrollViews) {
		return new VXScrollView<ScrollView, VaporScrollView<ScrollView, ?>>(
				scrollViews);
	}

	public static <T extends ScrollView> VXScrollView<T, VaporScrollView<T, ?>> ScrollView(
			Class<T> scrollViewClass, Integer... ids) {
		return new VXScrollView<T, VaporScrollView<T, ?>>(ids);
	}

	public static <T extends SearchView> VSearchView<T> SearchView(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VSearchView<T>(t);
	}

	public static <T extends SearchView> VSearchView<T> SearchView(
			View searchView) {
		Class<T> tClass = (Class<T>) searchView.getClass();
		return new VSearchView<T>((T) searchView);
	}

	public static <T extends SearchView> VSearchView<T> SearchView(T searchView) {
		return new VSearchView<T>(searchView);
	}

	public static <T extends SearchView> VSearchView<T> SearchView(
			Class<T> searchViewClass, Integer id) {
		return new VSearchView<T>(id);
	}

	public static VXSearchView<SearchView, VaporSearchView<SearchView, ?>> SearchView(
			Integer... ids) {
		return new VXSearchView<SearchView, VaporSearchView<SearchView, ?>>(ids);
	}

	public static VXSearchView<SearchView, VaporSearchView<SearchView, ?>> SearchView(
			Object... searchViews) {
		return new VXSearchView<SearchView, VaporSearchView<SearchView, ?>>(
				searchViews);
	}

	public static <T extends SearchView> VXSearchView<T, VaporSearchView<T, ?>> SearchView(
			Class<T> searchViewClass, Integer... ids) {
		return new VXSearchView<T, VaporSearchView<T, ?>>(ids);
	}

	public static <T extends SeekBar> VSeekBar<T> SeekBar(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VSeekBar<T>(t);
	}

	public static <T extends SeekBar> VSeekBar<T> SeekBar(View seekBar) {
		Class<T> tClass = (Class<T>) seekBar.getClass();
		return new VSeekBar<T>((T) seekBar);
	}

	public static <T extends SeekBar> VSeekBar<T> SeekBar(T seekBar) {
		return new VSeekBar<T>(seekBar);
	}

	public static <T extends SeekBar> VSeekBar<T> SeekBar(
			Class<T> seekBarClass, Integer id) {
		return new VSeekBar<T>(id);
	}

	public static VXSeekBar<SeekBar, VaporSeekBar<SeekBar, ?>> SeekBar(
			Integer... ids) {
		return new VXSeekBar<SeekBar, VaporSeekBar<SeekBar, ?>>(ids);
	}

	public static VXSeekBar<SeekBar, VaporSeekBar<SeekBar, ?>> SeekBar(
			Object... seekBars) {
		return new VXSeekBar<SeekBar, VaporSeekBar<SeekBar, ?>>(seekBars);
	}

	public static <T extends SeekBar> VXSeekBar<T, VaporSeekBar<T, ?>> SeekBar(
			Class<T> seekBarClass, Integer... ids) {
		return new VXSeekBar<T, VaporSeekBar<T, ?>>(ids);
	}

	public static <T extends Space> VSpace<T> Space(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VSpace<T>(t);
	}

	public static <T extends Space> VSpace<T> Space(View space) {
		Class<T> tClass = (Class<T>) space.getClass();
		return new VSpace<T>((T) space);
	}

	public static <T extends Space> VSpace<T> Space(T space) {
		return new VSpace<T>(space);
	}

	public static <T extends Space> VSpace<T> Space(Class<T> spaceClass,
			Integer id) {
		return new VSpace<T>(id);
	}

	public static VXSpace<Space, VaporSpace<Space, ?>> Space(Integer... ids) {
		return new VXSpace<Space, VaporSpace<Space, ?>>(ids);
	}

	public static VXSpace<Space, VaporSpace<Space, ?>> Space(Object... spaces) {
		return new VXSpace<Space, VaporSpace<Space, ?>>(spaces);
	}

	public static <T extends Space> VXSpace<T, VaporSpace<T, ?>> Space(
			Class<T> spaceClass, Integer... ids) {
		return new VXSpace<T, VaporSpace<T, ?>>(ids);
	}

	public static <T extends Spinner> VSpinner<T> Spinner(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VSpinner<T>(t);
	}

	public static <T extends Spinner> VSpinner<T> Spinner(View spinner) {
		Class<T> tClass = (Class<T>) spinner.getClass();
		return new VSpinner<T>((T) spinner);
	}

	public static <T extends Spinner> VSpinner<T> Spinner(T spinner) {
		return new VSpinner<T>(spinner);
	}

	public static <T extends Spinner> VSpinner<T> Spinner(
			Class<T> spinnerClass, Integer id) {
		return new VSpinner<T>(id);
	}

	public static VXSpinner<Spinner, VaporSpinner<Spinner, ?>> Spinner(
			Integer... ids) {
		return new VXSpinner<Spinner, VaporSpinner<Spinner, ?>>(ids);
	}

	public static VXSpinner<Spinner, VaporSpinner<Spinner, ?>> Spinner(
			Object... spinners) {
		return new VXSpinner<Spinner, VaporSpinner<Spinner, ?>>(spinners);
	}

	public static <T extends Spinner> VXSpinner<T, VaporSpinner<T, ?>> Spinner(
			Class<T> spinnerClass, Integer... ids) {
		return new VXSpinner<T, VaporSpinner<T, ?>>(ids);
	}

	public static <T extends StackView> VStackView<T> StackView(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VStackView<T>(t);
	}

	public static <T extends StackView> VStackView<T> StackView(View stackView) {
		Class<T> tClass = (Class<T>) stackView.getClass();
		return new VStackView<T>((T) stackView);
	}

	public static <T extends StackView> VStackView<T> StackView(T stackView) {
		return new VStackView<T>(stackView);
	}

	public static <T extends StackView> VStackView<T> StackView(
			Class<T> stackViewClass, Integer id) {
		return new VStackView<T>(id);
	}

	public static VXStackView<StackView, VaporStackView<StackView, ?>> StackView(
			Integer... ids) {
		return new VXStackView<StackView, VaporStackView<StackView, ?>>(ids);
	}

	public static VXStackView<StackView, VaporStackView<StackView, ?>> StackView(
			Object... stackViews) {
		return new VXStackView<StackView, VaporStackView<StackView, ?>>(
				stackViews);
	}

	public static <T extends StackView> VXStackView<T, VaporStackView<T, ?>> StackView(
			Class<T> stackViewClass, Integer... ids) {
		return new VXStackView<T, VaporStackView<T, ?>>(ids);
	}

	public static <T extends Switch> VSwitch<T> Switch(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VSwitch<T>(t);
	}

	public static <T extends Switch> VSwitch<T> Switch(View switchView) {
		Class<T> tClass = (Class<T>) switchView.getClass();
		return new VSwitch<T>((T) switchView);
	}

	public static <T extends Switch> VSwitch<T> Switch(T switchView) {
		return new VSwitch<T>(switchView);
	}

	public static <T extends Switch> VSwitch<T> Switch(Class<T> switchClass,
			Integer id) {
		return new VSwitch<T>(id);
	}

	public static VXSwitch<Switch, VaporSwitch<Switch, ?>> Switch(
			Integer... ids) {
		return new VXSwitch<Switch, VaporSwitch<Switch, ?>>(ids);
	}

	public static VXSwitch<Switch, VaporSwitch<Switch, ?>> Switch(
			Object... switchs) {
		return new VXSwitch<Switch, VaporSwitch<Switch, ?>>(switchs);
	}

	public static <T extends Switch> VXSwitch<T, VaporSwitch<T, ?>> Switch(
			Class<T> switchClass, Integer... ids) {
		return new VXSwitch<T, VaporSwitch<T, ?>>(ids);
	}

	public static <T extends TabHost> VTabHost<T> TabHost(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VTabHost<T>(t);
	}

	public static <T extends TabHost> VTabHost<T> TabHost(View tabHost) {
		Class<T> tClass = (Class<T>) tabHost.getClass();
		return new VTabHost<T>((T) tabHost);
	}

	public static <T extends TabHost> VTabHost<T> TabHost(T tabHost) {
		return new VTabHost<T>(tabHost);
	}

	public static <T extends TabHost> VTabHost<T> TabHost(
			Class<T> tabHostClass, Integer id) {
		return new VTabHost<T>(id);
	}

	public static VXTabHost<TabHost, VaporTabHost<TabHost, ?>> TabHost(
			Integer... ids) {
		return new VXTabHost<TabHost, VaporTabHost<TabHost, ?>>(ids);
	}

	public static VXTabHost<TabHost, VaporTabHost<TabHost, ?>> TabHost(
			Object... tabHosts) {
		return new VXTabHost<TabHost, VaporTabHost<TabHost, ?>>(tabHosts);
	}

	public static <T extends TabHost> VXTabHost<T, VaporTabHost<T, ?>> TabHost(
			Class<T> tabHostClass, Integer... ids) {
		return new VXTabHost<T, VaporTabHost<T, ?>>(ids);
	}

	public static <T extends TableLayout> VTableLayout<T> TableLayout(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VTableLayout<T>(t);
	}

	public static <T extends TableLayout> VTableLayout<T> TableLayout(
			View tableLayout) {
		Class<T> tClass = (Class<T>) tableLayout.getClass();
		return new VTableLayout<T>((T) tableLayout);
	}

	public static <T extends TableLayout> VTableLayout<T> TableLayout(
			T tableLayout) {
		return new VTableLayout<T>(tableLayout);
	}

	public static <T extends TableLayout> VTableLayout<T> TableLayout(
			Class<T> tableLayoutClass, Integer id) {
		return new VTableLayout<T>(id);
	}

	public static VXTableLayout<TableLayout, VaporTableLayout<TableLayout, ?>> TableLayout(
			Integer... ids) {
		return new VXTableLayout<TableLayout, VaporTableLayout<TableLayout, ?>>(
				ids);
	}

	public static VXTableLayout<TableLayout, VaporTableLayout<TableLayout, ?>> TableLayout(
			Object... tableLayouts) {
		return new VXTableLayout<TableLayout, VaporTableLayout<TableLayout, ?>>(
				tableLayouts);
	}

	public static <T extends TableLayout> VXTableLayout<T, VaporTableLayout<T, ?>> TableLayout(
			Class<T> tableLayoutClass, Integer... ids) {
		return new VXTableLayout<T, VaporTableLayout<T, ?>>(ids);
	}

	public static <T extends TableRow> VTableRow<T> TableRow(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VTableRow<T>(t);
	}

	public static <T extends TableRow> VTableRow<T> TableRow(View tableRow) {
		Class<T> tClass = (Class<T>) tableRow.getClass();
		return new VTableRow<T>((T) tableRow);
	}

	public static <T extends TableRow> VTableRow<T> TableRow(T tableRow) {
		return new VTableRow<T>(tableRow);
	}

	public static <T extends TableRow> VTableRow<T> TableRow(
			Class<T> tableRowClass, Integer id) {
		return new VTableRow<T>(id);
	}

	public static VXTableRow<TableRow, VaporTableRow<TableRow, ?>> TableRow(
			Integer... ids) {
		return new VXTableRow<TableRow, VaporTableRow<TableRow, ?>>(ids);
	}

	public static VXTableRow<TableRow, VaporTableRow<TableRow, ?>> TableRow(
			Object... tableRows) {
		return new VXTableRow<TableRow, VaporTableRow<TableRow, ?>>(tableRows);
	}

	public static <T extends TableRow> VXTableRow<T, VaporTableRow<T, ?>> TableRow(
			Class<T> tableRowClass, Integer... ids) {
		return new VXTableRow<T, VaporTableRow<T, ?>>(ids);
	}

	public static <T extends TabWidget> VTabWidget<T> TabWidget(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VTabWidget<T>(t);
	}

	public static <T extends TabWidget> VTabWidget<T> TabWidget(View tabWidget) {
		Class<T> tClass = (Class<T>) tabWidget.getClass();
		return new VTabWidget<T>((T) tabWidget);
	}

	public static <T extends TabWidget> VTabWidget<T> TabWidget(T tabWidget) {
		return new VTabWidget<T>(tabWidget);
	}

	public static <T extends TabWidget> VTabWidget<T> TabWidget(
			Class<T> tabWidgetClass, Integer id) {
		return new VTabWidget<T>(id);
	}

	public static VXTabWidget<TabWidget, VaporTabWidget<TabWidget, ?>> TabWidget(
			Integer... ids) {
		return new VXTabWidget<TabWidget, VaporTabWidget<TabWidget, ?>>(ids);
	}

	public static VXTabWidget<TabWidget, VaporTabWidget<TabWidget, ?>> TabWidget(
			Object... tabWidgets) {
		return new VXTabWidget<TabWidget, VaporTabWidget<TabWidget, ?>>(
				tabWidgets);
	}

	public static <T extends TabWidget> VXTabWidget<T, VaporTabWidget<T, ?>> TabWidget(
			Class<T> tabWidgetClass, Integer... ids) {
		return new VXTabWidget<T, VaporTabWidget<T, ?>>(ids);
	}

	public static <T extends TextClock> VTextClock<T> TextClock(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VTextClock<T>(t);
	}

	public static <T extends TextClock> VTextClock<T> TextClock(View textClock) {
		Class<T> tClass = (Class<T>) textClock.getClass();
		return new VTextClock<T>((T) textClock);
	}

	public static <T extends TextClock> VTextClock<T> TextClock(T textClock) {
		return new VTextClock<T>(textClock);
	}

	public static <T extends TextClock> VTextClock<T> TextClock(
			Class<T> textClockClass, Integer id) {
		return new VTextClock<T>(id);
	}

	public static VXTextClock<TextClock, VaporTextClock<TextClock, ?>> TextClock(
			Integer... ids) {
		return new VXTextClock<TextClock, VaporTextClock<TextClock, ?>>(ids);
	}

	public static VXTextClock<TextClock, VaporTextClock<TextClock, ?>> TextClock(
			Object... textClocks) {
		return new VXTextClock<TextClock, VaporTextClock<TextClock, ?>>(
				textClocks);
	}

	public static <T extends TextClock> VXTextClock<T, VaporTextClock<T, ?>> TextClock(
			Class<T> textClockClass, Integer... ids) {
		return new VXTextClock<T, VaporTextClock<T, ?>>(ids);
	}

	public static <T extends TextSwitcher> VTextSwitcher<T> TextSwitcher(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VTextSwitcher<T>(t);
	}

	public static <T extends TextSwitcher> VTextSwitcher<T> TextSwitcher(
			View textSwitcher) {
		Class<T> tClass = (Class<T>) textSwitcher.getClass();
		return new VTextSwitcher<T>((T) textSwitcher);
	}

	public static <T extends TextSwitcher> VTextSwitcher<T> TextSwitcher(
			T textSwitcher) {
		return new VTextSwitcher<T>(textSwitcher);
	}

	public static <T extends TextSwitcher> VTextSwitcher<T> TextSwitcher(
			Class<T> textSwitcherClass, Integer id) {
		return new VTextSwitcher<T>(id);
	}

	public static VXTextSwitcher<TextSwitcher, VaporTextSwitcher<TextSwitcher, ?>> TextSwitcher(
			Integer... ids) {
		return new VXTextSwitcher<TextSwitcher, VaporTextSwitcher<TextSwitcher, ?>>(
				ids);
	}

	public static VXTextSwitcher<TextSwitcher, VaporTextSwitcher<TextSwitcher, ?>> TextSwitcher(
			Object... textSwitchers) {
		return new VXTextSwitcher<TextSwitcher, VaporTextSwitcher<TextSwitcher, ?>>(
				textSwitchers);
	}

	public static <T extends TextSwitcher> VXTextSwitcher<T, VaporTextSwitcher<T, ?>> TextSwitcher(
			Class<T> textSwitcherClass, Integer... ids) {
		return new VXTextSwitcher<T, VaporTextSwitcher<T, ?>>(ids);
	}

	public static <T extends TextView> VTextView<T> TextView(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VTextView<T>(t);
	}

	public static <T extends TextView> VTextView<T> TextView(View textView) {
		Class<T> tClass = (Class<T>) textView.getClass();
		return new VTextView<T>((T) textView);
	}

	public static <T extends TextView> VTextView<T> TextView(T textView) {
		return new VTextView<T>(textView);
	}

	public static <T extends TextView> VTextView<T> TextView(
			Class<T> textViewClass, Integer id) {
		return new VTextView<T>(id);
	}

	public static VXTextView<TextView, VaporTextView<TextView, ?>> TextView(
			Integer... ids) {
		return new VXTextView<TextView, VaporTextView<TextView, ?>>(ids);
	}

	public static VXTextView<TextView, VaporTextView<TextView, ?>> TextView(
			Object... textViews) {
		return new VXTextView<TextView, VaporTextView<TextView, ?>>(textViews);
	}

	public static <T extends TextView> VXTextView<T, VaporTextView<T, ?>> TextView(
			Class<T> textViewClass, Integer... ids) {
		return new VXTextView<T, VaporTextView<T, ?>>(ids);
	}

	public static <T extends TimePicker> VTimePicker<T> TimePicker(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VTimePicker<T>(t);
	}

	public static <T extends TimePicker> VTimePicker<T> TimePicker(
			View timePicker) {
		Class<T> tClass = (Class<T>) timePicker.getClass();
		return new VTimePicker<T>((T) timePicker);
	}

	public static <T extends TimePicker> VTimePicker<T> TimePicker(T timePicker) {
		return new VTimePicker<T>(timePicker);
	}

	public static <T extends TimePicker> VTimePicker<T> TimePicker(
			Class<T> timePickerClass, Integer id) {
		return new VTimePicker<T>(id);
	}

	public static VXTimePicker<TimePicker, VaporTimePicker<TimePicker, ?>> TimePicker(
			Integer... ids) {
		return new VXTimePicker<TimePicker, VaporTimePicker<TimePicker, ?>>(ids);
	}

	public static VXTimePicker<TimePicker, VaporTimePicker<TimePicker, ?>> TimePicker(
			Object... timePickers) {
		return new VXTimePicker<TimePicker, VaporTimePicker<TimePicker, ?>>(
				timePickers);
	}

	public static <T extends TimePicker> VXTimePicker<T, VaporTimePicker<T, ?>> TimePicker(
			Class<T> timePickerClass, Integer... ids) {
		return new VXTimePicker<T, VaporTimePicker<T, ?>>(ids);
	}

	public static <T extends ToggleButton> VToggleButton<T> ToggleButton(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VToggleButton<T>(t);
	}

	public static <T extends ToggleButton> VToggleButton<T> ToggleButton(
			View toggleButton) {
		Class<T> tClass = (Class<T>) toggleButton.getClass();
		return new VToggleButton<T>((T) toggleButton);
	}

	public static <T extends ToggleButton> VToggleButton<T> ToggleButton(
			T toggleButton) {
		return new VToggleButton<T>(toggleButton);
	}

	public static <T extends ToggleButton> VToggleButton<T> ToggleButton(
			Class<T> toggleButtonClass, Integer id) {
		return new VToggleButton<T>(id);
	}

	public static VXToggleButton<ToggleButton, VaporToggleButton<ToggleButton, ?>> ToggleButton(
			Integer... ids) {
		return new VXToggleButton<ToggleButton, VaporToggleButton<ToggleButton, ?>>(
				ids);
	}

	public static VXToggleButton<ToggleButton, VaporToggleButton<ToggleButton, ?>> ToggleButton(
			Object... toggleButtons) {
		return new VXToggleButton<ToggleButton, VaporToggleButton<ToggleButton, ?>>(
				toggleButtons);
	}

	public static <T extends ToggleButton> VXToggleButton<T, VaporToggleButton<T, ?>> ToggleButton(
			Class<T> toggleButtonClass, Integer... ids) {
		return new VXToggleButton<T, VaporToggleButton<T, ?>>(ids);
	}

	public static <T extends VideoView> VVideoView<T> VideoView(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VVideoView<T>(t);
	}

	public static <T extends VideoView> VVideoView<T> VideoView(View videoView) {
		Class<T> tClass = (Class<T>) videoView.getClass();
		return new VVideoView<T>((T) videoView);
	}

	public static <T extends VideoView> VVideoView<T> VideoView(T videoView) {
		return new VVideoView<T>(videoView);
	}

	public static <T extends VideoView> VVideoView<T> VideoView(
			Class<T> videoViewClass, Integer id) {
		return new VVideoView<T>(id);
	}

	public static VXVideoView<VideoView, VaporVideoView<VideoView, ?>> VideoView(
			Integer... ids) {
		return new VXVideoView<VideoView, VaporVideoView<VideoView, ?>>(ids);
	}

	public static VXVideoView<VideoView, VaporVideoView<VideoView, ?>> VideoView(
			Object... videoViews) {
		return new VXVideoView<VideoView, VaporVideoView<VideoView, ?>>(
				videoViews);
	}

	public static <T extends VideoView> VXVideoView<T, VaporVideoView<T, ?>> VideoView(
			Class<T> videoViewClass, Integer... ids) {
		return new VXVideoView<T, VaporVideoView<T, ?>>(ids);
	}

	public static <T extends ViewAnimator> VViewAnimator<T> ViewAnimator(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VViewAnimator<T>(t);
	}

	public static <T extends ViewAnimator> VViewAnimator<T> ViewAnimator(
			View viewAnimator) {
		Class<T> tClass = (Class<T>) viewAnimator.getClass();
		return new VViewAnimator<T>((T) viewAnimator);
	}

	public static <T extends ViewAnimator> VViewAnimator<T> ViewAnimator(
			T viewAnimator) {
		return new VViewAnimator<T>(viewAnimator);
	}

	public static <T extends ViewAnimator> VViewAnimator<T> ViewAnimator(
			Class<T> viewAnimatorClass, Integer id) {
		return new VViewAnimator<T>(id);
	}

	public static VXViewAnimator<ViewAnimator, VaporViewAnimator<ViewAnimator, ?>> ViewAnimator(
			Integer... ids) {
		return new VXViewAnimator<ViewAnimator, VaporViewAnimator<ViewAnimator, ?>>(
				ids);
	}

	public static VXViewAnimator<ViewAnimator, VaporViewAnimator<ViewAnimator, ?>> ViewAnimator(
			Object... viewAnimators) {
		return new VXViewAnimator<ViewAnimator, VaporViewAnimator<ViewAnimator, ?>>(
				viewAnimators);
	}

	public static <T extends ViewAnimator> VXViewAnimator<T, VaporViewAnimator<T, ?>> ViewAnimator(
			Class<T> viewAnimatorClass, Integer... ids) {
		return new VXViewAnimator<T, VaporViewAnimator<T, ?>>(ids);
	}

	public static <T extends ViewFlipper> VViewFlipper<T> ViewFlipper(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VViewFlipper<T>(t);
	}

	public static <T extends ViewFlipper> VViewFlipper<T> ViewFlipper(
			View viewFlipper) {
		Class<T> tClass = (Class<T>) viewFlipper.getClass();
		return new VViewFlipper<T>((T) viewFlipper);
	}

	public static <T extends ViewFlipper> VViewFlipper<T> ViewFlipper(
			T viewFlipper) {
		return new VViewFlipper<T>(viewFlipper);
	}

	public static <T extends ViewFlipper> VViewFlipper<T> ViewFlipper(
			Class<T> viewFlipperClass, Integer id) {
		return new VViewFlipper<T>(id);
	}

	public static VXViewFlipper<ViewFlipper, VaporViewFlipper<ViewFlipper, ?>> ViewFlipper(
			Integer... ids) {
		return new VXViewFlipper<ViewFlipper, VaporViewFlipper<ViewFlipper, ?>>(
				ids);
	}

	public static VXViewFlipper<ViewFlipper, VaporViewFlipper<ViewFlipper, ?>> ViewFlipper(
			Object... viewFlippers) {
		return new VXViewFlipper<ViewFlipper, VaporViewFlipper<ViewFlipper, ?>>(
				viewFlippers);
	}

	public static <T extends ViewFlipper> VXViewFlipper<T, VaporViewFlipper<T, ?>> ViewFlipper(
			Class<T> viewFlipperClass, Integer... ids) {
		return new VXViewFlipper<T, VaporViewFlipper<T, ?>>(ids);
	}

	public static <T extends ViewSwitcher> VViewSwitcher<T> ViewSwitcher(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VViewSwitcher<T>(t);
	}

	public static <T extends ViewSwitcher> VViewSwitcher<T> ViewSwitcher(
			View viewSwitcher) {
		Class<T> tClass = (Class<T>) viewSwitcher.getClass();
		return new VViewSwitcher<T>((T) viewSwitcher);
	}

	public static <T extends ViewSwitcher> VViewSwitcher<T> ViewSwitcher(
			T viewSwitcher) {
		return new VViewSwitcher<T>(viewSwitcher);
	}

	public static <T extends ViewSwitcher> VViewSwitcher<T> ViewSwitcher(
			Class<T> viewSwitcherClass, Integer id) {
		return new VViewSwitcher<T>(id);
	}

	public static VXViewSwitcher<ViewSwitcher, VaporViewSwitcher<ViewSwitcher, ?>> ViewSwitcher(
			Integer... ids) {
		return new VXViewSwitcher<ViewSwitcher, VaporViewSwitcher<ViewSwitcher, ?>>(
				ids);
	}

	public static VXViewSwitcher<ViewSwitcher, VaporViewSwitcher<ViewSwitcher, ?>> ViewSwitcher(
			Object... viewSwitchers) {
		return new VXViewSwitcher<ViewSwitcher, VaporViewSwitcher<ViewSwitcher, ?>>(
				viewSwitchers);
	}

	public static <T extends ViewSwitcher> VXViewSwitcher<T, VaporViewSwitcher<T, ?>> ViewSwitcher(
			Class<T> viewSwitcherClass, Integer... ids) {
		return new VXViewSwitcher<T, VaporViewSwitcher<T, ?>>(ids);
	}

	public static <T extends ZoomButton> VZoomButton<T> ZoomButton(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VZoomButton<T>(t);
	}

	public static <T extends ZoomButton> VZoomButton<T> ZoomButton(
			View zoomButton) {
		Class<T> tClass = (Class<T>) zoomButton.getClass();
		return new VZoomButton<T>((T) zoomButton);
	}

	public static <T extends ZoomButton> VZoomButton<T> ZoomButton(T zoomButton) {
		return new VZoomButton<T>(zoomButton);
	}

	public static <T extends ZoomButton> VZoomButton<T> ZoomButton(
			Class<T> zoomButtonClass, Integer id) {
		return new VZoomButton<T>(id);
	}

	public static VXZoomButton<ZoomButton, VaporZoomButton<ZoomButton, ?>> ZoomButton(
			Integer... ids) {
		return new VXZoomButton<ZoomButton, VaporZoomButton<ZoomButton, ?>>(ids);
	}

	public static VXZoomButton<ZoomButton, VaporZoomButton<ZoomButton, ?>> ZoomButton(
			Object... zoomButtons) {
		return new VXZoomButton<ZoomButton, VaporZoomButton<ZoomButton, ?>>(
				zoomButtons);
	}

	public static <T extends ZoomButton> VXZoomButton<T, VaporZoomButton<T, ?>> ZoomButton(
			Class<T> zoomButtonClass, Integer... ids) {
		return new VXZoomButton<T, VaporZoomButton<T, ?>>(ids);
	}

	public static <T extends ZoomControls> VZoomControls<T> ZoomControls(
			Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VZoomControls<T>(t);
	}

	public static <T extends ZoomControls> VZoomControls<T> ZoomControls(
			View zoomControls) {
		Class<T> tClass = (Class<T>) zoomControls.getClass();
		return new VZoomControls<T>((T) zoomControls);
	}

	public static <T extends ZoomControls> VZoomControls<T> ZoomControls(
			T zoomControls) {
		return new VZoomControls<T>(zoomControls);
	}

	public static <T extends ZoomControls> VZoomControls<T> ZoomControls(
			Class<T> zoomControlsClass, Integer id) {
		return new VZoomControls<T>(id);
	}

	public static VXZoomControls<ZoomControls, VaporZoomControls<ZoomControls, ?>> ZoomControls(
			Integer... ids) {
		return new VXZoomControls<ZoomControls, VaporZoomControls<ZoomControls, ?>>(
				ids);
	}

	public static VXZoomControls<ZoomControls, VaporZoomControls<ZoomControls, ?>> ZoomControls(
			Object... zoomControlss) {
		return new VXZoomControls<ZoomControls, VaporZoomControls<ZoomControls, ?>>(
				zoomControlss);
	}

	public static <T extends ZoomControls> VXZoomControls<T, VaporZoomControls<T, ?>> ZoomControls(
			Class<T> zoomControlsClass, Integer... ids) {
		return new VXZoomControls<T, VaporZoomControls<T, ?>>(ids);
	}

	public static <T extends FragmentTabHost> VFragmentTabHost<T> FragmentTabHost(Integer id) {
		T t = (T) $.act().findViewById(id);
		Class<T> tClass = (Class<T>) t.getClass();
		return new VFragmentTabHost<T>(t);
	}

	public static <T extends FragmentTabHost> VFragmentTabHost<T> FragmentTabHost(
			View fragmentTabHost) {
		Class<T> tClass = (Class<T>) fragmentTabHost.getClass();
		return new VFragmentTabHost<T>((T) fragmentTabHost);
	}

	public static <T extends FragmentTabHost> VFragmentTabHost<T> FragmentTabHost(
			T fragmentTabHost) {
		return new VFragmentTabHost<T>(fragmentTabHost);
	}

	public static <T extends FragmentTabHost> VFragmentTabHost<T> FragmentTabHost(
			Class<T> fragmentTabHostClass, Integer id) {
		return new VFragmentTabHost<T>(id);
	}

	public static VXFragmentTabHost<FragmentTabHost, VaporFragmentTabHost<FragmentTabHost, ?>> FragmentTabHost(
			Integer... ids) {
		return new VXFragmentTabHost<FragmentTabHost, VaporFragmentTabHost<FragmentTabHost, ?>>(
				ids);
	}

	public static VXFragmentTabHost<FragmentTabHost, VaporFragmentTabHost<FragmentTabHost, ?>> FragmentTabHost(
			Object... fragmentTabHosts) {
		return new VXFragmentTabHost<FragmentTabHost, VaporFragmentTabHost<FragmentTabHost, ?>>(
				fragmentTabHosts);
	}

	public static <T extends FragmentTabHost> VXFragmentTabHost<T, VaporFragmentTabHost<T, ?>> FragmentTabHost(
			Class<T> fragmentTabHostClass, Integer... ids) {
		return new VXFragmentTabHost<T, VaporFragmentTabHost<T, ?>>(ids);
	}
	
}