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

import vapor.view.VaporView;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

/**
 * Fluent Vapor companion to ImageView, a view that displays an arbitrary image,
 * such as an icon. The ImageView class can load images from various sources
 * (such as resources or content providers), takes care of computing its
 * measurement from the image so that it can be used in any layout manager, and
 * provides various display options such as scaling and tinting.
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from ImageView
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporImageView<T extends ImageView, self extends VaporImageView<T, self>>
		extends VaporView<T, self> {

	public VaporImageView(int id) {
		super(id);
	}

	public VaporImageView(T imageView) {
		super(imageView);
	}

	/**
	 * True when ImageView is adjusting its bounds to preserve the aspect ratio
	 * of its drawable
	 * 
	 * @return whether to adjust the bounds of this view to preserve the
	 *         original aspect ratio of the drawable
	 */
	public boolean adjustBounds() {
		return view.getAdjustViewBounds();
	}

	/**
	 * Set this to true if you want the ImageView to adjust its bounds to
	 * preserve the aspect ratio of its drawable.
	 * 
	 * @param adjustViewBounds
	 *            Whether to adjust the bounds of this view to presrve the
	 *            original aspect ratio of the drawable
	 * @return self
	 */
	public self adjustBounds(boolean adjustViewBounds) {
		view.setAdjustViewBounds(adjustViewBounds);
		return (self) this;
	}

	/**
	 * Returns the alpha that will be applied to the drawable of this ImageView.
	 * 
	 * @return the alpha that will be applied to the drawable of this ImageView
	 */
	@Override
	public float alpha() {
		return view.getImageAlpha(); // intentional override from View, as:
										// http://developer.android.com/reference/android/widget/ImageView.html#setAlpha(int)
	}

	@Override
	public self alpha(float imageAlpha) {
		view.setImageAlpha((int) imageAlpha); // intentional override from View,
												// as:
												// http://developer.android.com/reference/android/widget/ImageView.html#setAlpha(int)
		return (self) this;
	}

	/**
	 * Set the offset of the widget's text baseline from the widget's top
	 * boundary. This value is overridden by the baselineAlignBottom(boolean)
	 * property.
	 * 
	 * @param baseline
	 * @return self
	 */
	public self baseline(int baseline) {
		view.setBaseline(baseline);
		return (self) this;
	}

	/**
	 * Return whether this view's baseline will be considered the bottom of the
	 * view.
	 * 
	 * @return true if this view's baseline will be considered the bottom of the
	 *         view
	 */
	public boolean baselineAlignBottom() {
		return view.getBaselineAlignBottom();
	}

	/**
	 * Set whether to set the baseline of this view to the bottom of the view.
	 * Setting this value overrides any calls to baseline(int).
	 * 
	 * @param aligned
	 *            If true, the image view will be baseline aligned with based on
	 *            its bottom edge.
	 * @return self
	 */
	public self baselineAlignBottom(boolean aligned) {
		view.setBaselineAlignBottom(aligned);
		return (self) this;
	}

	/**
	 * 
	 * @return self
	 */
	public final self clearFilter() {
		view.clearColorFilter();
		return (self) this;
	}

	/**
	 * Return whether this ImageView crops to padding.
	 * 
	 * @return whether this ImageView crops to padding
	 */
	public boolean cropToPad() {
		return view.getCropToPadding();
	}

	/**
	 * Sets whether this ImageView will crop to padding.
	 * 
	 * @param cropToPadding
	 *            whether this ImageView will crop to padding
	 * @return self
	 */
	public self cropToPad(boolean cropToPadding) {
		view.setCropToPadding(cropToPadding);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onCreateDrawableState(int),
	 * which generates the new Drawable state for this view. This is called by
	 * the view system when the cached Drawable state is determined to be
	 * invalid.
	 * 
	 * @param extraSpace
	 *            if non-zero, this is the number of extra entries you would
	 *            like in the returned array in which you can place your own
	 *            states.
	 * @return Returns an array holding the current Drawable state of the view.
	 */
	public int[] drawState(int extraSpace) {
		return view.onCreateDrawableState(extraSpace);
	}

	/**
	 * Returns the active color filter for this ImageView.
	 * 
	 * @return the active color filter for this ImageView+
	 */
	public ColorFilter filter() {
		return view.getColorFilter();
	}

	/**
	 * Set a tinting option for the image. Assumes SRC_ATOP blending mode.
	 * 
	 * @param color
	 *            Color tint to apply.
	 * @return self
	 */
	public final self filter(int color) {
		view.setColorFilter(color);
		return (self) this;
	}

	/**
	 * Apply an arbitrary colorfilter to the image.
	 * 
	 * @param colorFilter
	 *            the colorfilter to apply (may be null)
	 * @return self
	 */
	public self filter(ColorFilter colorFilter) {
		view.setColorFilter(colorFilter);
		return (self) this;
	}

	/**
	 * Set a tinting option for the image.
	 * 
	 * @param color
	 *            Color tint to apply.
	 * @param porterDuffMode
	 *            How to apply the color. The standard mode is SRC_ATOP
	 * @return self
	 */
	public final self filter(int color, PorterDuff.Mode porterDuffMode) {
		view.setColorFilter(color, porterDuffMode);
		return (self) this;
	}

	/**
	 * Return the view's drawable, or null if no drawable has been assigned.
	 * 
	 * @return the view's drawable, or null if no drawable has been assigned.
	 */
	public Drawable img() {
		return view.getDrawable();
	}

	/**
	 * Sets a drawable as the content of this ImageView.
	 * 
	 * @param resId
	 *            the resource identifier of the the drawable
	 * @return self
	 */
	public self img(int resId) {
		view.setImageResource(resId);
		return (self) this;
	}

	/**
	 * Sets a Bitmap as the content of this ImageView.
	 * 
	 * @param bitmap
	 *            The bitmap to set
	 * @return self
	 */
	public self img(Bitmap bitmap) {
		view.setImageBitmap(bitmap);
		return (self) this;
	}

	/**
	 * Sets a drawable as the content of this ImageView.
	 * 
	 * @param drawable
	 *            The drawable to set
	 * @return self
	 */
	public self img(Drawable drawable) {
		view.setImageDrawable(drawable);
		return (self) this;
	}

	/**
	 * Sets the content of this ImageView to the specified Uri.
	 * 
	 * @param imageUri
	 *            The Uri of an image
	 * @return self
	 */
	public self img(Uri imageUri) {
		view.setImageURI(imageUri);
		return (self) this;
	}

	/**
	 * Sets the image level, when it is constructed from a LevelListDrawable.
	 * 
	 * @param imageLevel
	 *            The new level for the image.
	 * @return self
	 */
	public self imgLevel(int imageLevel) {
		view.setImageLevel(imageLevel);
		return (self) this;
	}

	/**
	 * Return the view's optional matrix. This is applied to the view's drawable
	 * when it is drawn. If there is not matrix, this method will return null.
	 * Do not change this matrix in place. If you want a different matrix applied
	 * to the drawable, be sure to call setImageMatrix().
	 * 
	 * @return the view's optional matrix
	 */
	public Matrix imgMatrix() {
		return view.getImageMatrix();
	}

	/**
	 * 
	 * @param imageMatrix
	 * @return self
	 */
	public self imgMatrix(Matrix imageMatrix) {
		view.setImageMatrix(imageMatrix);
		return (self) this;
	}

	/**
	 * 
	 * @param imageState
	 * @param merge
	 * @return self
	 */
	public self imgState(int[] imageState, boolean merge) {
		view.setImageState(imageState, merge);
		return (self) this;
	}

	/**
	 * The maximum height of this view.
	 * 
	 * @return The maximum height of this view
	 */
	public int maxHeight() {
		return view.getMaxHeight();
	}

	/**
	 * An optional argument to supply a maximum height for this view. Only valid
	 * if adjustBounds(boolean) has been set to true.
	 * 
	 * @param maxHeight
	 *            maximum height for this view
	 * @return self
	 */
	public self maxHeight(int maxHeight) {
		view.setMaxHeight(maxHeight);
		return (self) this;
	}

	/**
	 * The maximum width of this view.
	 * 
	 * @return The maximum width of this view
	 */
	public int maxWidth() {
		return view.getMaxWidth();
	}

	/**
	 * An optional argument to supply a maximum width for this view. Only valid
	 * if adjustBounds(boolean) has been set to true.
	 * 
	 * @param maximum
	 *            width for this view
	 * @return self
	 */
	public self maxWidth(int maxWidth) {
		view.setMaxWidth(maxWidth);
		return (self) this;
	}

	/**
	 * Return the current scale type in use by this ImageView.
	 * 
	 * @return the current scale type in use by this ImageView
	 */
	public ImageView.ScaleType scaleType() {
		return view.getScaleType();
	}

	/**
	 * Controls how the image should be resized or moved to match the size of
	 * this ImageView.
	 * 
	 * @param scaleType
	 *            The desired scaling mode.
	 * @return self
	 */
	public self scaleType(ImageView.ScaleType scaleType) {
		view.setScaleType(scaleType);
		return (self) this;
	}

}
