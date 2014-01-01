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

package vapor.appwidget;

import vapor.os.VaporBundle;
import vapor.widget.VaporFrameLayout;
import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Rect;
import android.widget.RemoteViews;

// Checked: 051220121236

/**
 * Fluent Vapor companion to AppWidgetHostView, a class that provides the glue
 * to show AppWidget views. This class offers automatic animation between
 * updates, and will try recycling old views for each incoming RemoteViews.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from AppWidgetHostView
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporAppWidgetHostView<T extends AppWidgetHostView, self extends VaporAppWidgetHostView<T, self>>
		extends VaporFrameLayout<T, VaporAppWidgetHostView<T, self>> {

	public VaporAppWidgetHostView(int id) {
		super(id);
	}

	public VaporAppWidgetHostView(T appWidgetHostView) {
		super(appWidgetHostView);
	}

	/**
	 * Process a set of RemoteViews coming in as an update from the AppWidget
	 * provider. Will animate into these new views as needed
	 * 
	 * @param remoteViews
	 * @return self
	 */
	public self updateWidget(RemoteViews remoteViews) {
		view.updateAppWidget(remoteViews);
		return (self) this;
	}

	/**
	 * Specify some extra information for the widget provider. Causes a callback
	 * to the AppWidgetProvider.
	 * 
	 * @param widgetOptions
	 *            The bundle of options information.
	 * @return self
	 */
	public self updateOptions(VaporBundle widgetOptions) {
		view.updateAppWidgetOptions(widgetOptions.bundle());
		return (self) this;
	}

	/**
	 * Provide guidance about the size of this widget to the AppWidgetManager.
	 * The widths and heights should correspond to the full area the
	 * AppWidgetHostView is given. Padding added by the framework will be
	 * accounted for automatically. This information gets embedded into the
	 * AppWidget options and causes a callback to the AppWidgetProvider.
	 * 
	 * @param widgetOptions
	 *            The bundle of options, in addition to the size information,
	 *            can be null.
	 * @param minWidth
	 *            The minimum width that the widget will be displayed at.
	 * @param minHeight
	 *            The maximum height that the widget will be displayed at.
	 * @param maxWidth
	 *            The maximum width that the widget will be displayed at.
	 * @param maxHeight
	 *            The maximum height that the widget will be displayed at.
	 * @return self
	 */
	public self updateSize(VaporBundle widgetOptions, int minWidth,
			int minHeight, int maxWidth, int maxHeight) {
		view.updateAppWidgetSize(widgetOptions.bundle(), minWidth, minHeight,
				maxWidth, maxHeight);
		return (self) this;
	}

	/**
	 * Set the AppWidget that will be displayed by this view. This method also
	 * adds default padding to widgets, as described in
	 * getDefaultPaddingForWidget(Context, ComponentName, Rect) and can be
	 * overridden in order to add custom padding.
	 * 
	 * @param appWidgetId
	 * @param info
	 * @return self
	 */
	public self widget(int appWidgetId, AppWidgetProviderInfo info) {
		view.setAppWidget(appWidgetId, info);
		return (self) this;
	}

	/**
	 * As of ICE_CREAM_SANDWICH padding is automatically applied to widgets
	 * targeting ICE_CREAM_SANDWICH and higher. The new widget design guidelines
	 * strongly recommend that widget developers do not add extra padding to
	 * their widgets. This will help achieve consistency among widgets. Note:
	 * this method is only needed by developers of AppWidgetHosts. The method is
	 * provided in order for the AppWidgetHost to account for the automatic
	 * padding when computing the number of cells to allocate to a particular
	 * widget.
	 * 
	 * @param context
	 *            the current context
	 * @param component
	 *            the component name of the widget
	 * @param padding
	 *            Rect in which to place the output, if null, a new Rect will be
	 *            allocated and returned
	 * @return default padding for this widget
	 */
	public static Rect widgetDefPad(Context context, ComponentName component,
			Rect padding) {
		return AppWidgetHostView.getDefaultPaddingForWidget(context, component,
				padding);
	}

	/**
	 * 
	 * @return
	 */
	public int widgetId() {
		return view.getAppWidgetId();
	}

	/**
	 * 
	 * @return
	 */
	public AppWidgetProviderInfo widgetInfo() {
		return view.getAppWidgetInfo();
	}

}
