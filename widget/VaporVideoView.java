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

import vapor.view.VaporSurfaceView;
import android.net.Uri;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Fluent Vapor companion to VideoView, that displays a video file. The
 * VideoView class can load images from various sources (such as resources or
 * content providers), takes care of computing its measurement from the video so
 * that it can be used in any layout manager, and provides various display
 * options such as scaling and tinting.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from VideoView
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporVideoView<T extends VideoView, self extends VaporVideoView<T, self>>
		extends VaporSurfaceView<T, self> implements
		MediaController.MediaPlayerControl {

	public VaporVideoView(int id) {
		super(id);
	}

	public VaporVideoView(T videoView) {
		super(videoView);
	}

	/**
	 * 
	 * @return
	 */
	public int bufferPercentage() {
		return view.getBufferPercentage();
	}

	/**
	 * Register a callback to be invoked when the end of a media file has been
	 * reached during playback.
	 * 
	 * @param completionListener
	 *            The callback that will be run
	 * @return self
	 */
	public self completion(
			vapor.listeners.media.mediaplayer.$completion completionListener) {
		view.setOnCompletionListener(completionListener);
		return (self) this;
	}

	/**
	 * 
	 * @param mediaController
	 * @return self
	 */
	public self controller(MediaController mediaController) {
		view.setMediaController(mediaController);
		return (self) this;
	}

	public int duration() {
		return view.getDuration();
	}

	/**
	 * Register a callback to be invoked when an error occurs during playback or
	 * setup. If no listener is specified, or if the listener returned false,
	 * VideoView will inform the user of any errors.
	 * 
	 * @param errorListener
	 *            The callback that will be run
	 * @return self
	 */
	public self error(vapor.listeners.media.mediaplayer.$error errorListener) {
		view.setOnErrorListener(errorListener);
		return (self) this;
	}

	/**
	 * Register a callback to be invoked when an informational event occurs
	 * during playback or setup.
	 * 
	 * @param infoListener
	 *            The callback that will be run
	 * @return self
	 */
	public self info(vapor.listeners.media.mediaplayer.$info infoListener) {
		view.setOnInfoListener(infoListener);
		return (self) this;
	}

	/**
	 * Register a callback to be invoked when the media file is loaded and ready
	 * to go.
	 * 
	 * @param preparedListener
	 *            The callback that will be run
	 * @return self
	 */
	public self prepared(
			vapor.listeners.media.mediaplayer.$prepared preparedListener) {
		view.setOnPreparedListener(preparedListener);
		return (self) this;
	}

	/**
	 * 
	 * @return self
	 */
	public self path(String videoPath) {
		view.setVideoPath(videoPath);
		return (self) this;
	}

	/**
	 * 
	 * @return self
	 */
	public self paused(boolean paused) {
		if (paused)
			view.pause();
		else
			view.resume();
		return (self) this;
	}

	/**
	 * 
	 * @return
	 */
	public boolean pauseable() {
		return view.canPause();
	}

	/**
	 * 
	 * @return self
	 */
	public self playing(boolean playing) {
		if (playing)
			view.start();
		else
			view.stopPlayback();
		return (self) this;
	}

	/**
	 * 
	 * @return
	 */
	public boolean playing() {
		return view.isPlaying();
	}

	/**
	 * 
	 * @param desiredSize
	 * @param measureSpec
	 * @return
	 */
	public int resolveAdj(int desiredSize, int measureSpec) {
		return view.resolveAdjustedSize(desiredSize, measureSpec);
	}

	/**
	 * 
	 * @return self
	 */
	public self resume() {
		view.resume();
		return (self) this;
	}

	/**
	 * 
	 * @return the current position of the video
	 */
	public int seek() {
		return view.getCurrentPosition();
	}

	/**
	 * 
	 * @param milliseconds
	 * @return self
	 */
	public self seek(int milliseconds) {
		view.seekTo(milliseconds);
		return (self) this;
	}

	/**
	 * 
	 * @return
	 */
	public boolean seekableBackward() {
		return view.canSeekBackward();
	}

	/**
	 * 
	 * @return
	 */
	public boolean seekableForward() {
		return view.canSeekForward();
	}

	/**
	 * 
	 * @return self
	 */
	public self suspend() {
		view.suspend();
		return (self) this;
	}

	/**
	 * 
	 * @return self
	 */
	public self uri(Uri videoUri) {
		view.setVideoURI(videoUri);
		return (self) this;
	}

	/* INTERFACE : MediaPlayerControl */
	/**
	 * NOTE: This method serves to satisfy the MediaPlayerControl interface, use
	 * the equivalent VAPOR FLUENT method pauseable() instead
	 */
	@Override
	public boolean canPause() {
		return pauseable();
	}

	/**
	 * NOTE: This method serves to satisfy the MediaPlayerControl interface, use
	 * the equivalent VAPOR FLUENT method seekableBackward() instead
	 */
	@Override
	public boolean canSeekBackward() {
		return seekableBackward();
	}

	/**
	 * NOTE: This method serves to satisfy the MediaPlayerControl interface, use
	 * the equivalent VAPOR FLUENT method seekableForward() instead
	 */
	@Override
	public boolean canSeekForward() {
		return seekableForward();
	}

	/**
	 * NOTE: This method serves to satisfy the MediaPlayerControl interface, use
	 * the equivalent VAPOR FLUENT method bufferPercentage() instead
	 */
	@Override
	public int getBufferPercentage() {
		return bufferPercentage();
	}

	/**
	 * NOTE: This method serves to satisfy the MediaPlayerControl interface, use
	 * the equivalent VAPOR FLUENT method seek() instead
	 */
	@Override
	public int getCurrentPosition() {
		return seek();
	}

	/**
	 * NOTE: This method serves to satisfy the MediaPlayerControl interface, use
	 * the equivalent VAPOR FLUENT method duration() instead
	 */
	@Override
	public int getDuration() {
		return duration();
	}

	/**
	 * NOTE: This method serves to satisfy the MediaPlayerControl interface, use
	 * the equivalent VAPOR FLUENT method playing() instead
	 */
	@Override
	public boolean isPlaying() {
		return playing();
	}

	/**
	 * NOTE: This method serves to satisfy the MediaPlayerControl interface, use
	 * the equivalent VAPOR FLUENT method seek(int) instead
	 */
	@Override
	public void seekTo(int milliseconds) {
		seek(milliseconds);
	}

	/**
	 * NOTE: This method serves to satisfy the MediaPlayerControl interface, use
	 * the equivalent VAPOR FLUENT method play(true) instead
	 */
	@Override
	public void start() {
		playing(true);
	}

	/**
	 * NOTE: This method serves to satisfy the MediaPlayerControl interface, use
	 * the equivalent VAPOR FLUENT method paused(true) instead
	 */
	@Override
	public void pause() {
		paused(true);
	}
}
