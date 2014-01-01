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

package vapor.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;

// Checked: 061220122142

// onSurfaceTextureAvailable(android.graphics.SurfaceTexture, int, int)

/**
 * Fluent Vapor companion to TextureView, used to display a content stream. Such
 * a content stream can for instance be a video or an OpenGL scene. The content
 * stream can come from the application's process as well as a remote process.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from TextureView
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporTextureView<T extends TextureView, self extends VaporTextureView<T, self>>
		extends VaporView<T, self> {

	public VaporTextureView(int id) {
		super(id);
	}

	public VaporTextureView(T textureView) {
		super(textureView);
	}

	/**
	 * Returns true if the SurfaceTexture associated with this TextureView is
	 * available for rendering. When this method returns true, texture() returns
	 * a valid surface texture.
	 * 
	 * @return true if the SurfaceTexture associated with this TextureView is
	 *         available for rendering
	 */
	public boolean available() {
		return view.isAvailable();
	}

	/**
	 * Returns a Bitmap representation of the content of the associated surface
	 * texture. If the surface texture is not available, this method returns
	 * null.
	 * 
	 * The bitmap returned by this method uses the ARGB_8888 pixel format and
	 * its dimensions are the same as this view's
	 * 
	 * @return A valid ARGB_8888 bitmap, or null if the surface texture is not
	 *         available or the width <= 0 or the height <= 0
	 */
	public Bitmap bitmap() {
		return view.getBitmap();
	}

	/**
	 * Returns a Bitmap representation of the content of the associated surface
	 * texture. If the surface texture is not available, this method returns
	 * null.
	 * 
	 * The bitmap returned by this method uses the ARGB_8888 pixel format.
	 * 
	 * @param width
	 *            The width of the bitmap to create
	 * @param height
	 *            The height of the bitmap to create
	 * @return A valid ARGB_8888 bitmap, or null if the surface texture is not
	 *         available or width is <= 0 or height is <= 0
	 */
	public Bitmap bitmap(int width, int height) {
		return view.getBitmap(width, height);
	}

	/**
	 * Copies the content of this view's surface texture into the specified
	 * bitmap. If the surface texture is not available, the copy is not
	 * executed. The content of the surface texture will be scaled to fit exactly
	 * inside the specified bitmap.
	 * 
	 * @param bitmap
	 *            The bitmap to copy the content of the surface texture into,
	 *            cannot be null, all configurations are supported
	 * @return The bitmap specified as a parameter
	 */
	public Bitmap bitmap(Bitmap bitmap) {
		return view.getBitmap(bitmap);
	}

	/**
	 * Start editing the pixels in the surface. The returned Canvas can be used
	 * to draw into the surface's bitmap. A null is returned if the surface has
	 * not been created or otherwise cannot be edited. You will usually need to
	 * implement onSurfaceTextureAvailable(android.graphics.SurfaceTexture, int,
	 * int) to find out when the Surface is available for use.
	 * 
	 * @return A Canvas used to draw into the surface.
	 */
	public Canvas lock() {
		return view.lockCanvas();
	}

	/**
	 * Just like lock() but allows specification of a dirty rectangle. Every
	 * pixel within that rectangle must be written; however pixels outside the
	 * dirty rectangle will be preserved by the next call to lock().
	 * 
	 * @param dirty
	 *            Area of the surface that will be modified.
	 * @return A Canvas used to draw into the surface.
	 */
	public Canvas lock(Rect dirty) {
		return view.lockCanvas(dirty);
	}

	/**
	 * Indicates whether the content of this TextureView is opaque. The content
	 * is assumed to be opaque by default.
	 * 
	 * @param isOpaque
	 *            True if the content of this TextureView is opaque, false
	 *            otherwise
	 * @return self
	 */
	public self opaque(boolean isOpaque) {
		view.setOpaque(isOpaque);
		return (self) this;
	}

	/**
	 * Returns the SurfaceTexture used by this view. This method may return null
	 * if the view is not attached to a window or if the surface texture has not
	 * been initialized yet.
	 * 
	 * @return
	 */
	public SurfaceTexture texture() {
		return view.getSurfaceTexture();
	}

	/**
	 * Set the SurfaceTexture for this view to use. If a SurfaceTexture is
	 * already being used by this view, it is immediately released and not be
	 * usable any more. The onSurfaceTextureDestroyed(SurfaceTexture) callback is
	 * not called for the previous SurfaceTexture. Similarly, the
	 * onSurfaceTextureAvailable(SurfaceTexture, int, int) callback is not called
	 * for the SurfaceTexture passed to setSurfaceTexture. The SurfaceTexture
	 * object must be detached from all OpenGL ES contexts prior to calling this
	 * method.
	 * 
	 * @param surfaceTexture
	 *            The SurfaceTexture that the view should use.
	 * @return self
	 */
	public self texture(SurfaceTexture surfaceTexture) {
		view.setSurfaceTexture(surfaceTexture);
		return (self) this;
	}

	/**
	 * Sets the TextureView.SurfaceTextureListener used to listen to surface
	 * texture events.
	 * 
	 * @param surfaceTextureListener
	 * @return self
	 */
	public self texture(
			vapor.listeners.view.textureview.$texture surfaceTextureListener) {
		view.setSurfaceTextureListener(surfaceTextureListener);
		return (self) this;
	}

	/**
	 * Returns the TextureView.SurfaceTextureListener currently associated with
	 * this texture view.
	 * 
	 * @return
	 */
	public SurfaceTextureListener textureListener() {
		return view.getSurfaceTextureListener();
	}

	/**
	 * Returns the transform associated with this texture view.
	 * 
	 * @param transform
	 *            The Matrix in which to copy the current transform. Can be
	 *            null.
	 * @return The specified matrix if not null or a new Matrix instance
	 *         otherwise.
	 */
	public Matrix transform(Matrix transform) {
		return view.getTransform(transform);
	}

	/**
	 * Sets the transform to associate with this texture view. The specified
	 * transform applies to the underlying surface texture and does not affect
	 * the size or position of the view itself, only of its content.
	 * 
	 * Some transforms might prevent the content from drawing all the pixels
	 * contained within this view's bounds. In such situations, make sure this
	 * texture view is not marked opaque.
	 * 
	 * @param transform
	 *            The transform to apply to the content of this view.
	 * @return self
	 */
	public self setTransform(Matrix transform) {
		view.setTransform(transform);
		return (self) this;
	}

	/**
	 * Finish editing pixels in the surface. After this call, the surface's
	 * current pixels will be shown on the screen, but its content is lost, in
	 * particular there is no guarantee that the content of the Surface will
	 * remain unchanged when lock() is called again.
	 * 
	 * @param canvas
	 *            The Canvas previously returned by lock()
	 * @return self
	 */
	public self unlock(Canvas canvas) {
		view.unlockCanvasAndPost(canvas);
		return (self) this;
	}
}
