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

package vapor.opengl;

import vapor.view.VaporSurfaceView;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.EGLConfigChooser;
import android.opengl.GLSurfaceView.EGLContextFactory;
import android.opengl.GLSurfaceView.EGLWindowSurfaceFactory;
import android.opengl.GLSurfaceView.GLWrapper;
import android.view.SurfaceHolder;

// Checked: 051220121435

/**
 * Fluent Vapor companion to GLSurfaceView, an implementation of SurfaceView
 * that uses the dedicated surface for displaying OpenGL rendering.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from GLSurfaceView
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporGLSurfaceView<T extends GLSurfaceView, self extends VaporGLSurfaceView<T, self>>
		extends VaporSurfaceView<T, self> implements SurfaceHolder.Callback {

	public VaporGLSurfaceView(int id) {
		super(id);
	}

	public VaporGLSurfaceView(T glSurfaceView) {
		super(glSurfaceView);
	}

	/**
	 * Get the current value of the debug flags.
	 * 
	 * @return the current value of the debug flags.
	 */
	public int debugFlags() {
		return view.getDebugFlags();
	}

	/**
	 * Set the debug flags to a new value. The value is constructed by
	 * OR-together zero or more of the DEBUG_CHECK_* constants. The debug flags
	 * take effect whenever a surface is created. The default value is zero.
	 * 
	 * @param debugFlags
	 *            the new debug flags
	 * @return self
	 */
	public self debugFlags(int debugFlags) {
		view.setDebugFlags(debugFlags);
		return (self) this;
	}

	/**
	 * Install a custom EGLConfigChooser.
	 * 
	 * @param configChooser
	 * @return self
	 */
	public self config(EGLConfigChooser configChooser) {
		view.setEGLConfigChooser(configChooser);
		return (self) this;
	}

	/**
	 * Install a config chooser which will choose a config as close to 16-bit
	 * RGB as possible, with or without an optional depth buffer as close to
	 * 16-bits as possible.
	 * 
	 * @param needDepth
	 * @return self
	 */
	public self config(boolean needDepth) {
		view.setEGLConfigChooser(needDepth);
		return (self) this;
	}

	/**
	 * Install a config chooser which will choose a config with at least the
	 * specified depthSize and stencilSize, and exactly the specified redSize,
	 * greenSize, blueSize and alphaSize.
	 * 
	 * @param redSize
	 * @param greenSize
	 * @param blueSize
	 * @param alphaSize
	 * @param depthSize
	 * @param stencilSize
	 * @return self
	 */
	public self config(int redSize, int greenSize, int blueSize, int alphaSize,
			int depthSize, int stencilSize) {
		view.setEGLConfigChooser(redSize, greenSize, blueSize, alphaSize,
				depthSize, stencilSize);
		return (self) this;
	}

	/**
	 * Install a custom EGLContextFactory.
	 * 
	 * @param eglContextFactory
	 * @return self
	 */
	public self factory(EGLContextFactory eglContextFactory) {
		view.setEGLContextFactory(eglContextFactory);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onPause(), that informs the
	 * view that the activity is paused. The owner of this view must call this
	 * method when the activity is paused. Calling this method will pause the
	 * rendering thread. Must not be called before a renderer has been set.
	 * 
	 * @return self
	 */
	public self pause() {
		view.onPause();
		return (self) this;
	}

	/**
	 * 
	 * @return true if the EGL context will be preserved when paused
	 */
	public boolean preserve() {
		return view.getPreserveEGLContextOnPause();
	}

	/**
	 * Control whether the EGL context is preserved when the GLSurfaceView is
	 * paused and resumed.
	 * 
	 * @param preserveEGLContextOnPause
	 *            preserve the EGL context when paused
	 * @return self
	 */
	public self preserve(boolean preserveEGLContextOnPause) {
		view.setPreserveEGLContextOnPause(preserveEGLContextOnPause);
		return (self) this;
	}

	/**
	 * Queue a runnable to be run on the GL rendering thread. This can be used
	 * to communicate with the Renderer on the rendering thread. Must not be
	 * called before a renderer has been set.
	 * 
	 * @param runnable
	 *            the runnable to be run on the GL rendering thread.
	 * @return self
	 */
	public self queue(Runnable runnable) {
		view.queueEvent(runnable);
		return (self) this;
	}

	/**
	 * Get the current rendering mode. May be called from any thread. Must not
	 * be called before a renderer has been set.
	 * 
	 * @return the current rendering mode.
	 */
	public int renderMode() {
		return view.getRenderMode();
	}

	/**
	 * Set the renderer associated with this view. Also starts the thread that
	 * will call the renderer, which in turn causes the rendering to start.
	 * 
	 * @param renderer
	 *            the renderer to use to perform OpenGL drawing.
	 * @return self
	 */
	public self renderer(GLSurfaceView.Renderer renderer) {
		view.setRenderer(renderer);
		return (self) this;
	}

	/**
	 * Set the rendering mode. When renderMode is RENDERMODE_CONTINUOUSLY, the
	 * renderer is called repeatedly to re-render the scene.
	 * 
	 * @param renderMode
	 *            one of the RENDERMODE_X constants
	 * @return self
	 */
	public self renderMode(int renderMode) {
		view.setRenderMode(renderMode);
		return (self) this;
	}

	/**
	 * Request that the renderer render a frame. This method is typically used
	 * when the render mode has been set to RENDERMODE_WHEN_DIRTY, so that
	 * frames are only rendered on demand. May be called from any thread. Must
	 * not be called before a renderer has been set.
	 * 
	 * @return self
	 */
	public self reqRender() {
		view.requestRender();
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onResume(), that informs the
	 * view that the activity is resumed. The owner of this view must call this
	 * method when the activity is resumed. Calling this method will recreate
	 * the OpenGL display and resume the rendering thread. Must not be called
	 * before a renderer has been set.
	 * 
	 * @return self
	 */
	public self resume() {
		view.onResume();
		return (self) this;
	}

	/**
	 * Equivalent fluent Vapor method for invoking
	 * surfaceChanged(SurfaceHolder,int,int,int), part of the
	 * SurfaceHolder.Callback interface and not normally called or subclassed by
	 * clients of GLSurfaceView.
	 * 
	 * @param surfaceHolder
	 *            The SurfaceHolder whose surface has changed.
	 * @param format
	 *            The new PixelFormat of the surface.
	 * @param width
	 *            The new width of the surface.
	 * @param height
	 *            The new height of the surface.
	 * @return self
	 */
	public self changed(SurfaceHolder surfaceHolder, int format, int width,
			int height) {
		view.surfaceChanged(surfaceHolder, format, width, height);
		return (self) this;
	}

	/**
	 * Equivalent fluent Vapor method for invoking
	 * surfaceCreated(SurfaceHolder), part of the SurfaceHolder.Callback
	 * interface and not normally called or subclassed by clients of
	 * GLSurfaceView.
	 * 
	 * @param surfaceHolder
	 *            The SurfaceHolder whose surface is being created.
	 * @return self
	 */
	public self created(SurfaceHolder surfaceHolder) {
		view.surfaceCreated(surfaceHolder);
		return (self) this;
	}

	/**
	 * Equivalent fluent Vapor method for invoking
	 * surfaceDestroy(SurfaceHolder), part of the SurfaceHolder.Callback
	 * interface and not normally called or subclassed by clients of
	 * GLSurfaceView.
	 * 
	 * @param surfaceHolder
	 *            The SurfaceHolder whose surface is being destroyed.
	 * @return self
	 */
	public self destroyed(SurfaceHolder surfaceHolder) {
		view.surfaceDestroyed(surfaceHolder);
		return (self) this;
	}

	/**
	 * Inform the default EGLContextFactory and default EGLConfigChooser which
	 * EGLContext client version to pick.
	 * 
	 * @param eglContextClientVersion
	 *            The EGLContext client version to choose. Use 2 for OpenGL ES
	 *            2.0
	 * @return self
	 */
	public self version(int eglContextClientVersion) {
		view.setEGLContextClientVersion(eglContextClientVersion);
		return (self) this;
	}

	/**
	 * Install a custom EGLWindowSurfaceFactory.
	 * 
	 * @param eglWindowSurfaceFactory
	 * @return self
	 */
	public self winSurfaceFactory(
			EGLWindowSurfaceFactory eglWindowSurfaceFactory) {
		view.setEGLWindowSurfaceFactory(eglWindowSurfaceFactory);
		return (self) this;
	}

	/**
	 * Set the glWrapper. If the glWrapper is not null, its wrap(GL) method is
	 * called whenever a surface is created. A GLWrapper can be used to wrap the
	 * GL object that's passed to the renderer.
	 * 
	 * @param glWrapper
	 *            the new GLWrapper
	 * @return self
	 */
	public self wrapper(GLWrapper glWrapper) {
		view.setGLWrapper(glWrapper);
		return (self) this;
	}

	/* INTERFACE : SurfaceHolder.Callback */
	/**
	 * NOTE: This method serves to satisfy the SurfaceHolder.Callback interface,
	 * use the equivalent VAPOR FLUENT method changed(SurfaceHolder,int,int,int)
	 * instead
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		changed(holder, format, width, height);
	}

	/**
	 * NOTE: This method serves to satisfy the SurfaceHolder.Callback interface,
	 * use the equivalent VAPOR FLUENT method created(SurfaceHolder) instead
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		created(holder);
	}

	/**
	 * NOTE: This method serves to satisfy the SurfaceHolder.Callback interface,
	 * use the equivalent VAPOR FLUENT method destroyed(SurfaceHolder) instead
	 */
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		destroyed(holder);
	}

}
