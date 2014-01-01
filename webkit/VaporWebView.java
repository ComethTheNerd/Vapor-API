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

package vapor.webkit;

import java.util.Map;

import vapor.os.VaporBundle;
import vapor.widget.VaporAbsoluteLayout;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.net.http.SslCertificate;
import android.os.Message;
import android.webkit.ValueCallback;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Fluent Vapor companion to WebView, a view that displays web pages. This class
 * is the basis upon which you can roll your own web browser or simply display
 * some online content within your Activity.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from WebView
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporWebView<T extends WebView, self extends VaporWebView<T, self>>
		extends VaporAbsoluteLayout<T, self> {
	/*
	 * DEPRECATED INTERFACES implements
	 * vapor.listeners.view.viewgroup.$hierarchy,
	 * vapor.listeners.view.viewtreeobserver.$focus
	 */

	final String SCHEME_GEO = "geo:0,0?q=", SCHEME_MAILTO = "mailto:",
			SCHEME_TEL = "tel:";

	public VaporWebView(int id) {
		super(id);
	}

	public VaporWebView(T webView) {
		super(webView);
	}

	/**
	 * Retrieves the HTTP authentication username and password for a given host
	 * and realm pair
	 * 
	 * @param host
	 *            the host for which the credentials apply
	 * @param realm
	 *            the realm for which the credentials apply
	 * @return String[] if found. String[0] is username, which can be null and
	 *         String[1] is password. Return null if it can't find anything.
	 */
	public String[] auth(String host, String realm) {
		return view.getHttpAuthUsernamePassword(host, realm);
	}

	/**
	 * Sets the HTTP authentication credentials for a given host and realm.
	 * 
	 * @param host
	 *            the host for the credentials
	 * @param realm
	 *            the realm for the credentials
	 * @param username
	 *            the username for the password. If it is null, it means
	 *            password can't be saved.
	 * @param password
	 *            the password
	 * @return self
	 */
	public self auth(String host, String realm, String username, String password) {
		view.setHttpAuthUsernamePassword(host, realm, username, password);
		return (self) this;
	}

	/**
	 * Sets the chrome handler. This is an implementation of WebChromeClient for
	 * use in handling JavaScript dialogs, favicons, titles, and the progress.
	 * This will replace the current handler.
	 * 
	 * @param client
	 *            an implementation of WebChromeClient
	 * @return self
	 */
	public self chrome(WebChromeClient client) {
		view.setWebChromeClient(client);
		return (self) this;
	}

	/**
	 * Clears this WebView so that onDraw() will draw nothing but white
	 * background, and onMeasure() will return 0 if MeasureSpec is not
	 * MeasureSpec.EXACTLY.
	 * 
	 * @return self
	 */
	public self clear() {
		view.clearView();
		return (self) this;
	}

	/**
	 * Clears the resource cache. Note that the cache is per-application, so
	 * this will clear the cache for all WebViews used.
	 * 
	 * @param includeDiskFiles
	 *            if false, only the RAM cache is cleared
	 * @return self
	 */
	public self clearCache(boolean includeDiskFiles) {
		view.clearCache(includeDiskFiles);
		return (self) this;
	}

	/**
	 * Makes sure that clearing the form data removes the adapter from the
	 * currently focused textfield if there is one.
	 * 
	 * @return self
	 */
	public self clearForm() {
		view.clearFormData();
		return (self) this;
	}

	/**
	 * Tells this WebView to clear its internal back/forward list.
	 * 
	 * @return self
	 */
	public self clearHistory() {
		view.clearHistory();
		return (self) this;
	}

	/**
	 * Clears the highlighting surrounding text matches created by
	 * findAll(String) or findAllAsync(String).
	 * 
	 * @return self
	 */
	public self clearMatches() {
		view.clearMatches();
		return (self) this;
	}

	/**
	 * Clears the SSL preferences table stored in response to proceeding with
	 * SSL certificate errors.
	 * 
	 * @return self
	 */
	public self clearSslPrefs() {
		view.clearSslPreferences();
		return (self) this;
	}

	/**
	 * Gets the height of the HTML content.
	 * 
	 * @return the height of the HTML content
	 */
	public int contentHeight() {
		return view.getContentHeight();
	}

	/**
	 * Destroys the internal state of this WebView. This method should be called
	 * after this WebView has been removed from the view system. No other
	 * methods may be called on this WebView after destroy.
	 * 
	 * @return self
	 */
	public self destroy() {
		view.destroy();
		return (self) this;
	}

	/**
	 * Scrolls the contents of this WebView down by half the page size.
	 * 
	 * @param pageBottom
	 *            true to jump to bottom of page
	 * @return true if the page was scrolled
	 */
	public boolean pgDown(boolean pageBottom) {
		return view.pageDown(pageBottom);
	}

	/**
	 * Registers the interface to be used when content can not be handled by the
	 * rendering engine, and should be downloaded instead. This will replace the
	 * current handler.
	 * 
	 * @param listener
	 *            an implementation of DownloadListener
	 * @return self
	 */
	public self download(vapor.listeners.webkit.$download downloadListener) {
		view.setDownloadListener(downloadListener);
		return (self) this;
	}

	/**
	 * Gets the favicon for the current page. This is the favicon of the current
	 * page until WebViewClient.onReceivedIcon is called.
	 * 
	 * @return the favicon for the current page
	 */
	public Bitmap favicon() {
		return view.getFavicon();
	}

	/**
	 * Finds all instances of find on the page and highlights them,
	 * asynchronously. Notifies any registered WebView.FindListener. Successive
	 * calls to this or findAll(String) will cancel any pending searches.
	 * 
	 * @param find
	 *            the string to find.
	 * @return self
	 */
	public self findAsync(String find) {
		view.findAllAsync(find);
		return (self) this;
	}

	/**
	 * Registers the listener to be notified as find-on-page operations
	 * progress. This will replace the current listener.
	 * 
	 * @param findListener
	 *            an implementation of WebView.FindListener
	 * @return self
	 */
	public self find(vapor.listeners.webkit.webview.$find findListener) {
		view.setFindListener(findListener);
		return (self) this;
	}

	/**
	 * Gets the first substring consisting of the address of a physical
	 * location.
	 * 
	 * @param addr
	 *            the string to search for addresses
	 * @return the address, or if no address is found, null
	 */
	public static String findAddress(String addr) {
		return WebView.findAddress(addr);
	}

	/**
	 * Starts an ActionMode for finding text in this WebView. Only works if this
	 * WebView is attached to the view system.
	 * 
	 * @param text
	 *            if non-null, will be the initial text to search for.
	 *            Otherwise, the last String searched for in this WebView will
	 *            be used to start.
	 * @param showIme
	 *            if true, show the IME, assuming the user will begin typing. If
	 *            false and text is non-null, perform a find all.
	 * @return true if the find dialog is shown, false otherwise
	 */
	public boolean findDialog(String text, boolean showIme) {
		return view.showFindDialog(text, showIme);
	}

	/**
	 * Highlights and scrolls to the next match found by find(String), wrapping
	 * around page boundaries as necessary. Notifies any registered
	 * WebView.FindListener. If find(String) has not been called yet, or if
	 * clearMatches() has been called since the last find operation, this
	 * function does nothing.
	 * 
	 * @param forward
	 *            the direction to search
	 * @return self
	 */
	public self findNext(boolean forward) {
		view.findNext(forward);
		return (self) this;
	}

	/**
	 * 
	 * @param velocityX
	 * @param velocityY
	 * @return self
	 */
	public self fling(int velocityX, int velocityY) {
		view.flingScroll(velocityX, velocityY);
		return (self) this;
	}

	/**
	 * Informs this WebView that memory is low so that it can free any available
	 * memory.
	 * 
	 * @return self
	 */
	public self freeMem() {
		view.freeMemory();
		return (self) this;
	}

	/**
	 * Gets the WebBackForwardList for this WebView. This contains the
	 * back/forward list for use in querying each item in the history stack.
	 * This is a copy of the private WebBackForwardList so it contains only a
	 * snapshot of the current state. Multiple calls to this method may return
	 * different objects. The object returned from this method will not be
	 * updated to reflect any new state.
	 * 
	 * @return the WebBackForwardList for this WebView
	 */
	public WebBackForwardList sessionList() {
		return view.copyBackForwardList();
	}

	/**
	 * Gets a HitTestResult based on the current cursor node. If a HTML::a tag
	 * is found and the anchor has a non-JavaScript URL, the HitTestResult type
	 * is set to SRC_ANCHOR_TYPE and the URL is set in the "extra" field. If the
	 * anchor does not have a URL or if it is a JavaScript URL, the type will be
	 * UNKNOWN_TYPE and the URL has to be retrieved through
	 * requestFocusNodeHref(Message) asynchronously. If a HTML::img tag is found,
	 * the HitTestResult type is set to IMAGE_TYPE and the URL is set in the
	 * "extra" field. A type of SRC_IMAGE_ANCHOR_TYPE indicates an anchor with a
	 * URL that has an image as a child node. If a phone number is found, the
	 * HitTestResult type is set to PHONE_TYPE and the phone number is set in the
	 * "extra" field of HitTestResult. If a vaporMap address is found, the
	 * HitTestResult type is set to GEO_TYPE and the address is set in the
	 * "extra" field of HitTestResult. If an email address is found, the
	 * HitTestResult type is set to EMAIL_TYPE and the email is set in the
	 * "extra" field of HitTestResult. Otherwise, HitTestResult type is set to
	 * UNKNOWN_TYPE.
	 * 
	 * @return a HitTestResult based on the current cursor node
	 */
	public WebView.HitTestResult hitTest() {
		return view.getHitTestResult();
	}

	/**
	 * Queries the document to see if it contains any image references. The
	 * message object will be dispatched with arg1 being set to 1 if images were
	 * found and 0 if the document does not reference any images.
	 * 
	 * @param response
	 *            the message that will be dispatched with the result
	 * @return self
	 */
	public self imgs(Message response) {
		view.documentHasImages(response);
		return (self) this;
	}

	/**
	 * Gets whether private browsing is enabled in this WebView.
	 * 
	 * @return self
	 */
	public boolean incognito() {
		return view.isPrivateBrowsingEnabled();
	}

	/**
	 * Injects the supplied Java object into this WebView. The object is
	 * injected into the JavaScript context of the main frame, using the
	 * supplied name. This allows the Java object's public methods to be accessed
	 * from JavaScript.
	 * 
	 * @param object
	 *            the Java object to inject into this WebView's JavaScript
	 *            context. Null values are ignored.
	 * @param interfaceName
	 *            the name used to expose the object in JavaScript
	 * @return self
	 */
	public self js(Object object, String interfaceName) {
		view.addJavascriptInterface(object, interfaceName);
		return (self) this;
	}

	/**
	 * Loads the given URL.
	 * 
	 * @param url
	 *            the URL of the resource to load
	 * @return self
	 */
	public self load(String url) {
		view.loadUrl(url);
		return (self) this;
	}

	/**
	 * Loads the given URL with the specified additional HTTP headers.
	 * 
	 * @param url
	 *            the URL of the resource to load
	 * @param additionalHttpHeaders
	 *            the additional headers to be used in the HTTP request for this
	 *            URL, specified as a vaporMap from name to value. Note that if
	 *            this vaporMap contains any of the headers that are set by
	 *            default by this WebView, such as those controlling caching,
	 *            accept types or the User-Agent, their values may be overriden
	 *            by this WebView's defaults.
	 * @return self
	 */
	public self load(String url, Map<String, String> additionalHttpHeaders) {
		view.loadUrl(url, additionalHttpHeaders);
		return (self) this;
	}

	/**
	 * Loads the given data into this WebView using a 'data' scheme URL.
	 * 
	 * @param data
	 *            a String of data in the given encoding
	 * @param mimeType
	 *            the MIME type of the data, e.g. 'text/html'
	 * @param encoding
	 *            the encoding of the data
	 * @return self
	 */
	public self load(String data, String mimeType, String encoding) {
		view.loadData(data, mimeType, encoding);
		return (self) this;
	}

	/**
	 * Loads the given data into this WebView, using baseUrl as the base URL for
	 * the content. The base URL is used both to resolve relative URLs and when
	 * applying JavaScript's same origin policy. The historyUrl is used for the
	 * history entry.
	 * 
	 * @param baseUrl
	 *            the URL to use as the page's base URL. If null defaults to
	 *            'about:blank'.
	 * @param data
	 *            a String of data in the given encoding
	 * @param mimeType
	 *            the MIMEType of the data, e.g. 'text/html'. If null, defaults
	 *            to 'text/html'.
	 * @param encoding
	 *            the encoding of the data
	 * @param historyUrl
	 *            the URL to use as the history entry. If null defaults to
	 *            'about:blank'.
	 * @return self
	 */
	public self load(String baseUrl, String data, String mimeType,
			String encoding, String historyUrl) {
		view.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
		return (self) this;
	}

	/**
	 * 
	 * @param setMap
	 * @return
	 * 
	 *         public self mapTrackballToArrows(boolean setMap){
	 *         view.setMapTrackballToArrowKeys(setMap); return (self)this; }
	 */

	/**
	 * Goes to the history item that is the number of steps away from the
	 * current item. Steps is negative if backward and positive if forward.
	 * 
	 * @param steps
	 *            the number of steps to take back or forward in the back
	 *            forward list
	 * @return self
	 */
	public self nav(int steps) {
		view.goBackOrForward(steps);
		return (self) this;
	}

	/**
	 * Goes back in the history of this WebView.
	 * 
	 * @return self
	 */
	public self navBack() {
		view.goBack();
		return (self) this;
	}

	/**
	 * Goes forward in the history of this WebView.
	 * 
	 * @return self
	 */
	public self navForward() {
		view.goForward();
		return (self) this;
	}

	/**
	 * Gets whether this WebView has a forward history item.
	 * 
	 * @param steps
	 * @return true iff this Webview has a forward history item
	 */
	public boolean navable(int steps) {
		return view.canGoBackOrForward(steps);
	}

	/**
	 * Gets whether this WebView has a back history item.
	 * 
	 * @return true iff this WebView has a back history item
	 */
	public boolean navableBack() {
		return view.canGoBack();
	}

	/**
	 * Gets whether this WebView has a forward history item.
	 * 
	 * @return true iff this Webview has a forward history item
	 */
	public boolean navableForward() {
		return view.canGoForward();
	}

	/**
	 * Informs WebView of the network state. This is used to set the JavaScript
	 * property window.navigator.isOnline and generates the online/offline event
	 * as specified in HTML5, sec. 5.7.7
	 * 
	 * @param networkAvailable
	 *            a boolean indicating if network is available
	 * @return self
	 */
	public self network(boolean networkAvailable) {
		view.setNetworkAvailable(networkAvailable);
		return (self) this;
	}

	/**
	 * Gets the original URL for the current page. This is not always the same
	 * as the URL passed to WebViewClient.onPageStarted because although the
	 * load for that URL has begun, the current page may not have changed. Also,
	 * there may have been redirects resulting in a different URL to that
	 * originally requested.
	 * 
	 * @return the URL that was originally requested for the current page
	 */
	public String origUrl() {
		return view.getOriginalUrl();
	}

	/**
	 * Gets whether horizontal scrollbar has overlay style.
	 * 
	 * @return true if horizontal scrollbar has overlay style
	 */
	public boolean xScrollOverlays() {
		return view.overlayHorizontalScrollbar();
	}

	/**
	 * Specifies whether the horizontal scrollbar has overlay style.
	 * 
	 * @param overlayEnabled
	 *            true if horizontal scrollbar should have overlay style
	 * @return self
	 */
	public self xScrollOverlays(boolean overlayEnabled) {
		view.setHorizontalScrollbarOverlay(overlayEnabled);
		return (self) this;
	}

	/**
	 * Gets whether vertical scrollbar has overlay style.
	 * 
	 * @return true if vertical scrollbar has overlay style
	 */
	public boolean yScrollOverlays() {
		return view.overlayVerticalScrollbar();
	}

	/**
	 * Specifies whether the vertical scrollbar has overlay style
	 * 
	 * @param overlayEnabled
	 *            true if vertical scrollbar should have overlay style
	 * @return self
	 */
	public self yScrollOverlays(boolean overlayEnabled) {
		view.setVerticalScrollbarOverlay(overlayEnabled);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onPause(), used to pause any
	 * extra processing associated with this WebView and its associated DOM,
	 * plugins, JavaScript etc. For example, if this WebView is taken offscreen,
	 * this could be called to reduce unnecessary CPU or network traffic
	 * 
	 * @return self
	 */
	public self pause() {
		view.onPause();
		return (self) this;
	}

	/**
	 * Pauses all layout, parsing, and JavaScript timers for all WebViews. This
	 * is a global requests, not restricted to just this WebView. This can be
	 * useful if the application has been paused.
	 * 
	 * @return self
	 */
	public self pauseTimers() {
		view.pauseTimers();
		return (self) this;
	}

	/**
	 * Loads the URL with postData using "POST" method into this WebView. If url
	 * is not a network URL, it will be loaded with load(String) instead.
	 * 
	 * @param url
	 *            the URL of the resource to load
	 * @param postData
	 *            the data will be passed to "POST" request
	 * @return self
	 */
	public self post(String url, byte[] postData) {
		view.postUrl(url, postData);
		return (self) this;
	}

	/**
	 * Gets the progress for the current page.
	 * 
	 * @return the progress for the current page between 0 and 100
	 */
	public int progress() {
		return view.getProgress();
	}

	/**
	 * Reloads the current URL.
	 * 
	 * @return self
	 */
	public self reload() {
		view.reload();
		return (self) this;
	}

	/**
	 * Removes a previously injected Java object from this WebView. Note that
	 * the removal will not be reflected in JavaScript until the page is next
	 * (re)loaded. See js(Object, String).
	 * 
	 * @param interfaceName
	 *            the name used to expose the object in JavaScript
	 * @return self
	 */
	public self removeJs(String interfaceName) {
		view.removeJavascriptInterface(interfaceName);
		return (self) this;
	}

	/**
	 * Requests the anchor or image element URL at the last tapped point. If
	 * hrefMsg is null, this method returns immediately and does not dispatch
	 * hrefMsg to its target. If the tapped point hits an image, an anchor, or an
	 * image in an anchor, the message associates strings in named keys in its
	 * data. The value paired with the key may be an empty string.
	 * 
	 * @param hrefMsg
	 *            the message to be dispatched with the result of the request.
	 *            The message data contains three keys. "url" returns the
	 *            anchor's href attribute. "title" returns the anchor's text.
	 *            "src" returns the image's src attribute.
	 * @return self
	 */
	public self reqFocusHref(Message hrefMsg) {
		view.requestFocusNodeHref(hrefMsg);
		return (self) this;
	}

	/**
	 * Requests the URL of the image last touched by the user. msg will be sent
	 * to its target with a String representing the URL as its object.
	 * 
	 * @param msg
	 *            the message to be dispatched with the result of the request as
	 *            the data member with "url" as key. The result can be null.
	 * @return self
	 */
	public self reqImgRef(Message msg) {
		view.requestImageRef(msg);
		return (self) this;
	}

	/**
	 * Restores the state of this WebView from the given vaporMap used in
	 * onRestoreInstanceState(Bundle). This method should be called to restore
	 * the state of this WebView before using the object. If it is called after
	 * this WebView has had a chance to build state (load pages, create a
	 * back/forward list, etc.) there may be undesirable side-effects. Please
	 * note that this method no longer restores the display data for this
	 * WebView.
	 * 
	 * @param inState
	 *            the incoming Bundle of state
	 * @return the restored back/forward list or null if restoreState failed
	 */
	public WebBackForwardList restore(VaporBundle inState) {
		return view.restoreState(inState.bundle());
	}

	/**
	 * Fluent equivalent Vapor method for invoking onResume(), used to resume a
	 * WebView after a previous call to pause().
	 * 
	 * @return self
	 */
	public self resume() {
		view.onResume();
		return (self) this;
	}

	/**
	 * Resumes all layout, parsing, and JavaScript timers for all WebViews. This
	 * will resume dispatching all timers.
	 * 
	 * @return self
	 */
	public self resumeTimers() {
		view.resumeTimers();
		return (self) this;
	}

	/**
	 * Saves the state of this WebView used in onSaveInstanceState(Bundle).
	 * Please note that this method no longer stores the display data for this
	 * WebView.
	 * 
	 * @param outState
	 *            the Bundle to store this WebView's state
	 * @return the same copy of the back/forward list used to save the state. If
	 *         saveState fails, the returned list will be null.
	 */
	public WebBackForwardList save(VaporBundle outState) {
		return view.saveState(outState.bundle());
	}

	/**
	 * Saves the current view as a web archive.
	 * 
	 * @param webArchiveFilename
	 *            the filename where the archive should be placed
	 * @return self
	 */
	public self save(String webArchiveFilename) {
		view.saveWebArchive(webArchiveFilename);
		return (self) this;
	}

	/**
	 * Saves the current view as a web archive.
	 * 
	 * @param webArchiveBaseName
	 *            the filename where the archive should be placed
	 * @param autoName
	 *            if false, takes basename to be a file. If true, basename is
	 *            assumed to be a directory in which a filename will be chosen
	 *            according to the URL of the current page.
	 * @param callBack
	 *            called after the web archive has been saved. The parameter for
	 *            onReceiveValue will either be the filename under which the
	 *            file was saved, or null if saving the file failed.
	 * @return self
	 */
	public self save(String webArchiveBaseName, boolean autoName,
			ValueCallback<String> callBack) {
		view.saveWebArchive(webArchiveBaseName, autoName, callBack);
		return (self) this;
	}

	/**
	 * Saves the username and password for a particular host in this WebView's
	 * internal database.
	 * 
	 * @param host
	 *            the host that required the credentials
	 * @param username
	 *            the username for the given host
	 * @param password
	 *            the password for the given host
	 * @return self
	 */
	public self saveAuth(String host, String username, String password) {
		view.savePassword(host, username, password);
		return (self) this;
	}

	/**
	 * Sets the initial scale for this WebView. 0 means default. If
	 * getUseWideViewPort() is true, it zooms out all the way. Otherwise it
	 * starts with 100%. If initial scale is greater than 0, WebView starts with
	 * this value as initial scale. Please note that unlike the scale properties
	 * in the viewport meta tag, this method doesn't take the screen density into
	 * account.
	 * 
	 * @param initialScaleInPercent
	 *            the initial scale in percent
	 * @return self
	 */
	public self scaleInit(int initialScaleInPercent) {
		view.setInitialScale(initialScaleInPercent);
		return (self) this;
	}

	/**
	 * Gets the WebSettings object used to control the settings for this
	 * WebView.
	 * 
	 * @return a WebSettings object that can be used to control this WebView's
	 *         settings
	 */
	public WebSettings settings() {
		return view.getSettings();
	}

	/**
	 * Invokes the graphical zoom picker widget for this WebView. This will
	 * result in the zoom widget appearing on the screen to control the zoom
	 * level of this WebView.
	 * 
	 * @return self
	 */
	public self zoomer() {
		view.invokeZoomPicker();
		return (self) this;
	}

	/**
	 * Gets a new picture that captures the current display of this WebView.
	 * This is a copy of the display, and will be unaffected if this WebView
	 * later loads a different URL.
	 * 
	 * @return a picture containing the current contents of this WebView. Note
	 *         this picture is of the entire document, and is not restricted to
	 *         the bounds of the view.
	 */
	public Picture snapShot() {
		return view.capturePicture();
	}

	/**
	 * Gets the SSL certificate for the main top-level page or null if there is
	 * no certificate (the site is not secure).
	 * 
	 * @return the SSL certificate for the main top-level page
	 */
	public SslCertificate ssl() {
		return view.getCertificate();
	}

	/**
	 * Stops the current load.
	 * 
	 * @return self
	 */
	public self stop() {
		view.stopLoading();
		return (self) this;
	}

	/**
	 * Gets the title for the current page. This is the title of the current
	 * page until WebViewClient.onReceivedTitle is called.
	 * 
	 * @return the title for the current page
	 */
	public String title() {
		return view.getTitle();
	}

	/**
	 * Scrolls the contents of this WebView up by half the view size.
	 * 
	 * @param pageTop
	 *            true to jump to the top of the page
	 * @return true if the page was scrolled
	 */
	public boolean pgUp(boolean pageTop) {
		return view.pageUp(pageTop);
	}

	/**
	 * Gets the URL for the current page. This is not always the same as the URL
	 * passed to WebViewClient.onPageStarted because although the load for that
	 * URL has begun, the current page may not have changed.
	 * 
	 * @return the URL for the current page
	 */
	public String url() {
		return view.getUrl();
	}

	/**
	 * Sets the WebViewClient that will receive various notifications and
	 * requests. This will replace the current handler.
	 * 
	 * @param client
	 *            an implementation of WebViewClient
	 * @return self
	 */
	public self webClient(WebViewClient client) {
		view.setWebViewClient(client);
		return (self) this;
	}

	/**
	 * Performs zoom in in this WebView.
	 * 
	 * @return true if zoom in succeeds, false if no zoom changes
	 */
	public boolean zoomIn() {
		return view.zoomIn();
	}

	/**
	 * Performs zoom out in this WebView.
	 * 
	 * @return true if zoom out succeeds, false if no zoom changes
	 */
	public boolean zoomOut() {
		return view.zoomOut();
	}
}
