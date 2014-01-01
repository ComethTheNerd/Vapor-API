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

package vapor.gesture;

import java.util.ArrayList;

import vapor.widget.VaporFrameLayout;
import android.gesture.Gesture;
import android.gesture.GestureOverlayView;
import android.gesture.GesturePoint;
import android.graphics.Path;

//Checked: 051220121244

/**
 * Fluent Vapor companion to GestureOverlayView, a class that provides a
 * transparent overlay for gesture input, that can be placed on top of other
 * widgets or contain other widgets.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from GestureOverlayView
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporGestureOverlayView<T extends GestureOverlayView, self extends VaporGestureOverlayView<T, self>>
		extends VaporFrameLayout<T, self> {

	public VaporGestureOverlayView(int id) {
		super(id);
	}

	public VaporGestureOverlayView(T gestureOverlayView) {
		super(gestureOverlayView);
	}

	public enum Listeners {
		GESTURE, GESTURING, GESTURE_PERF
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public float angleThreshold() {
		return view.getGestureStrokeAngleThreshold();
	}

	/**
	 * 
	 * 
	 * @return self
	 */
	public self angleThreshold(float strokeAngleThreshold) {
		view.setGestureStrokeAngleThreshold(strokeAngleThreshold);
		return (self) this;
	}

	/**
	 * 
	 * @return self
	 */
	public self cancel() {
		view.cancelGesture();
		return (self) this;
	}

	/**
	 * 
	 * @return self
	 */
	public self cancelClearAnim() {
		view.cancelClearAnimation();
		return (self) this;
	}

	/**
	 * 
	 * @param animated
	 *            whether to animate the clearing
	 * @return self
	 */
	public self clear(boolean animated) {
		view.clear(animated);
		return (self) this;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public int color() {
		return view.getGestureColor();
	}

	/**
	 * 
	 * 
	 * @return self
	 */
	public self color(int color) {
		view.setGestureColor(color);
		return (self) this;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public int colorUncertain() {
		return view.getUncertainGestureColor();
	}

	/**
	 * 
	 * 
	 * @return self
	 */
	public self colorUncertain(int color) {
		view.setUncertainGestureColor(color);
		return (self) this;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public boolean fades() {
		return view.isFadeEnabled();
	}

	/**
	 * 
	 * 
	 * @return self
	 */
	public self fades(boolean fadeEnabled) {
		view.setFadeEnabled(fadeEnabled);
		return (self) this;
	}

	/**
	 * 
	 * @return
	 */
	public long fadeOffset() {
		return view.getFadeOffset();
	}

	/**
	 * 
	 * 
	 * @return self
	 */
	public self fadeOffset(long fadeOffset) {
		view.setFadeOffset(fadeOffset);
		return (self) this;
	}

	/**
	 * 
	 * @return
	 */
	public Gesture gesture() {
		return view.getGesture();
	}

	/**
	 * 
	 * 
	 * @return self
	 */
	public self gesture(Gesture gesture) {
		view.setGesture(gesture);
		return (self) this;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public boolean gesturing() {
		return view.isGesturing();
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public boolean gestureViz() {
		return view.isGestureVisible();
	}

	/**
	 * 
	 * 
	 * @return self
	 */
	public self gestureViz(boolean visible) {
		view.setGestureVisible(visible);
		return (self) this;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public boolean intercept() {
		return view.isEventsInterceptionEnabled();
	}

	/**
	 * 
	 * 
	 * @return self
	 */
	public self intercept(boolean eventsInterceptionEnabled) {
		view.setEventsInterceptionEnabled(eventsInterceptionEnabled);
		return (self) this;
	}

	/**
	 * 
	 * @return self
	 */
	public self gesture(
			vapor.listeners.gesture.gestureoverlayview.$gesture gestureListener) {
		view.addOnGestureListener(gestureListener);
		return (self) this;
	}

	/**
	 * 
	 * @return self
	 */
	public self gesturePerf(
			vapor.listeners.gesture.gestureoverlayview.$gesturePerf gesturePerformedListener) {
		view.addOnGesturePerformedListener(gesturePerformedListener);
		return (self) this;
	}

	/**
	 * 
	 * @return self
	 */
	public self gesturing(
			vapor.listeners.gesture.gestureoverlayview.$gesturing gesturingListener) {
		view.addOnGesturingListener(gesturingListener);
		return (self) this;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public int orientation() {
		return view.getOrientation();
	}

	/**
	 * 
	 * 
	 * @return self
	 */
	public self orientation(int orientation) {
		view.setOrientation(orientation);
		return (self) this;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public Path path() {
		return view.getGesturePath();
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public Path path(Path path) {
		return view.getGesturePath(path);
	}

	/**
	 * 
	 * 
	 * @return self
	 */
	public self remove(
			vapor.listeners.gesture.gestureoverlayview.$gesture gestureListener) {
		view.removeOnGestureListener(gestureListener);
		return (self) this;
	}

	/**
	 * 
	 * 
	 * @return self
	 */
	public self remove(
			vapor.listeners.gesture.gestureoverlayview.$gesturePerf gesturePerformedListener) {
		view.removeOnGesturePerformedListener(gesturePerformedListener);
		return (self) this;
	}

	/**
	 * 
	 * 
	 * @return self
	 */
	public self remove(
			vapor.listeners.gesture.gestureoverlayview.$gesturing gesturingListener) {
		view.removeOnGesturingListener(gesturingListener);
		return (self) this;
	}

	/**
	 * Removes all listeners of the given type that are currently attached to
	 * this views
	 * 
	 * @param gestureListener
	 *            A Listeners enum type of gesture listener to remove
	 * @return self
	 */
	public self remove(Listeners gestureListener) {
		switch (gestureListener) {

		case GESTURE:
			view.removeAllOnGestureListeners();
			break;

		case GESTURING:
			view.removeAllOnGesturingListeners();
			break;

		case GESTURE_PERF:
			view.removeAllOnGesturePerformedListeners();
			break;
		}
		return (self) this;
	}

	/**
	 * 
	 * 
	 * @return self
	 */
	public self removeGesturingListeners() {
		view.removeAllOnGesturingListeners();
		return (self) this;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<GesturePoint> stroke() {
		return view.getCurrentStroke();
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public float lengthThreshold() {
		return view.getGestureStrokeLengthThreshold();
	}

	/**
	 * 
	 * 
	 * @return self
	 */
	public self lengthThreshold(float strokeLengthThreshold) {
		view.setGestureStrokeLengthThreshold(strokeLengthThreshold);
		return (self) this;
	}

	/**
	 * 
	 * 
	 * @return self
	 */
	public float squarenessThreshold() { // squareThreshold instead?
		return view.getGestureStrokeSquarenessTreshold();
	}

	/**
	 * 
	 * 
	 * @return self
	 */
	public self squarenessThreshold(float strokeSquarenessThreshold) {
		view.setGestureStrokeSquarenessTreshold(strokeSquarenessThreshold);
		return (self) this;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public int strokeType() {
		return view.getGestureStrokeType();
	}

	/**
	 * 
	 * 
	 * @return self
	 */
	public self strokeType(int gestureStrokeType) {
		view.setGestureStrokeType(gestureStrokeType);
		return (self) this;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public float strokeWidth() {
		return view.getGestureStrokeWidth();
	}

	/**
	 * 
	 * 
	 * @return self
	 */
	public self strokeWidth(float gestureStrokeWidth) {
		view.setGestureStrokeWidth(gestureStrokeWidth);
		return (self) this;
	}

}
