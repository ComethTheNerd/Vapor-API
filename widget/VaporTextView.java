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

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import vapor.core.$;
import vapor.os.VaporBundle;
import vapor.view.VaporView;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Layout;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.text.method.MovementMethod;
import android.text.method.TransformationMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.ActionMode;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Fluent Vapor companion to TextView, which displays text to the user and
 * optionally allows them to edit it. A TextView is a complete text editor,
 * however the basic class is configured to not allow editing; see VaporEditText
 * for a subclass that configures the text view for editing
 * 
 * @author Darius H (darius@vapor-api.com)
 * @since 1.0
 * @param <T>
 *            A standard Android type derived from TextView
 * @param <self>
 *            A recursively defined type that provides information for fluent
 *            method invocation
 */
public class VaporTextView<T extends TextView, self extends VaporTextView<T, self>>
		extends VaporView<T, self> implements
		vapor.listeners.view.viewtreeobserver.$preDraw {

	public VaporTextView(int id) {
		super(id);
	}

	public VaporTextView(T textView) {
		super(textView);
	}

	/**
	 * Sets the text color, size, style, hint color, and highlight color from
	 * the specified TextAppearance resource.
	 * 
	 * @param context
	 * @param resId
	 * @return self
	 */
	public self appearance(Context context, int resId) {
		view.setTextAppearance(context, resId);
		return (self) this;
	}

	/**
	 * Convenience method: Append the specified text to the TextView's display
	 * buffer, upgrading it to BufferType.EDITABLE if it was not already
	 * editable.
	 * 
	 * @param text
	 * @return
	 */
	public final self append(CharSequence text) {
		view.append(text);
		return (self) this;
	}

	/**
	 * Convenience method: Append the specified text slice to the TextView's
	 * display buffer, upgrading it to BufferType.EDITABLE if it was not already
	 * editable.
	 * 
	 * @param text
	 * @param start
	 * @param end
	 * @return self
	 */
	public self append(CharSequence text, int start, int end) {
		view.append(text, start, end);
		return (self) this;
	}

	/**
	 * 
	 * @return self
	 */
	public self batchEdit() {
		view.beginBatchEdit();
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking beginBatchEdit(), called in
	 * response to a request to begin a batch of edit operations.
	 * 
	 * @return self
	 */
	public self beginBatchEdit() {
		view.onBeginBatchEdit();
		return (self) this;
	}

	/**
	 * Move the point, specified by the offset, into the view if it is needed.
	 * This has to be called after layout. Returns true if anything changed.
	 * 
	 * @param offset
	 * @return
	 */
	public boolean bringPoint(int offset) {
		return view.bringPointIntoView(offset);
	}

	/**
	 * Sets the properties of this field to transform input to ALL CAPS display.
	 * This may use a "small caps" formatting if available. This setting will be
	 * ignored if this field is editable or selectable. This call replaces the
	 * current transformation method. Disabling this will not necessarily
	 * restore the previous behavior from before this was enabled.
	 * 
	 * @param allCaps
	 * @return self
	 */
	public self caps(boolean allCaps) {
		view.setAllCaps(allCaps);
		return (self) this;
	}

	/**
	 * Use BaseInputConnection.removeComposingSpans() to remove any IME
	 * composing state from this text view.
	 * 
	 * @return self
	 */
	public self clear() {
		view.clearComposingText();
		return (self) this;
	}

	/**
	 * Returns whether the movement method will automatically be set to
	 * LinkMovementMethod if mask(int) has been set to nonzero and links are
	 * detected in text(char[], int, int). The default is true.
	 * 
	 * @return
	 */
	public final boolean clickableLinks() {
		return view.getLinksClickable();
	}

	/**
	 * Sets whether the movement method will automatically be set to
	 * LinkMovementMethod if setAutoLinkMask(int) has been set to nonzero and
	 * links are detected in text(char[], int, int). The default is true.
	 * 
	 * @param clickableLinks
	 * @return self
	 */
	public final self clickableLinks(boolean clickableLinks) {
		view.setLinksClickable(clickableLinks);
		return (self) this;
	}

	/**
	 * Returns the default color from the TextView_textColor attribute from the
	 * AttributeSet, if set, or the default color from the
	 * TextAppearance_textColor from the TextView_textAppearance attribute, if
	 * TextView_textColor was not set directly.
	 * 
	 * @param context
	 * @param attrs
	 * @param def
	 * @return
	 */
	public static int color(Context context, TypedArray attrs, int def) {
		return TextView.getTextColor(context, attrs, def);
	}

	/**
	 * Sets the text color.
	 * 
	 * @param color
	 * @return self
	 */
	public self color(int color) {
		view.setTextColor(color);
		return (self) this;
	}

	/**
	 * Sets the text color for all the states (normal, selected, focused) to be
	 * this color.
	 * 
	 * @param colors
	 * @return self
	 */
	public self color(ColorStateList colors) {
		view.setTextColor(colors);
		return (self) this;
	}

	/**
	 * Gets the text colors for the different states (normal, selected, focused)
	 * of the TextView
	 * 
	 * @return
	 */
	public final ColorStateList colors() {
		return view.getTextColors();
	}

	/**
	 * Returns the TextView_textColor attribute from the
	 * Resources.StyledAttributes, if set, or the TextAppearance_textColor from
	 * the TextView_textAppearance attribute, if TextView_textColor was not set
	 * directly.
	 * 
	 * @param context
	 * @param attrs
	 * @return
	 */
	public static ColorStateList colors(Context context, TypedArray attrs) {
		return TextView.getTextColors(context, attrs);
	}

	/**
	 * Fluent equivalent Vapor method for invoking
	 * onCommitCompletion(CompletionInfo), called by the framework in response
	 * to a text completion from the current input method, provided by it
	 * calling InputConnection.commitCompletion(). The default implementation
	 * does nothing; text views that are supporting auto-completion should
	 * override this to do their desired behavior.
	 * 
	 * @param completionInfo
	 *            The auto complete text the user has selected.
	 * @return self
	 */
	public self commitCompletion(CompletionInfo completionInfo) {
		view.onCommitCompletion(completionInfo);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking
	 * onCommitCorrection(CorrectionInfo), called by the framework in response
	 * to a text auto-correction (such as fixing a typo using a a dictionnary)
	 * from the current input method, provided by it calling
	 * commitCorrection(CorrectionInfo) InputConnection.commitCorrection()}. The
	 * default implementation flashes the background of the corrected word to
	 * provide feedback to the user.
	 * 
	 * @param correctionInfo
	 *            The auto correct info about the text that was corrected.
	 * @return self
	 */
	public self commitCorrection(CorrectionInfo correctionInfo) {
		view.onCommitCorrection(correctionInfo);
		return (self) this;
	}

	/**
	 * Returns drawables for the left, top, right, and bottom borders.
	 * 
	 * @return drawables for the left, top, right, and bottom borders.
	 */
	public Drawable[] compoundImgs() {
		return view.getCompoundDrawables();
	}

	/**
	 * Sets the Drawables (if any) to appear to the left of, above, to the right
	 * of, and below the text. Use null if you do not want a Drawable there. The
	 * Drawables must already have had bounds(Rect) called.
	 * 
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 * @return self
	 */
	public self compoundImgs(Drawable left, Drawable top, Drawable right,
			Drawable bottom) {
		view.setCompoundDrawables(left, top, right, bottom);
		return (self) this;
	}

	/**
	 * Sets the Drawables (if any) to appear to the left of, above, to the right
	 * of, and below the text. Use null if you do not want a Drawable there. The
	 * Drawables' bounds will be set to their intrinsic bounds.
	 * 
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 * @return self
	 */
	public self intrinsicCompoundImgs(Drawable left, Drawable top,
			Drawable right, Drawable bottom) {
		view.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
		return (self) this;
	}

	/**
	 * Sets the Drawables (if any) to appear to the left of, above, to the right
	 * of, and below the text. Use 0 if you do not want a Drawable there. The
	 * Drawables' bounds will be set to their intrinsic bounds.
	 * 
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 * @return self
	 */
	public self intrinsicCompoundImgs(int left, int top, int right, int bottom) {
		view.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
		return (self) this;
	}

	/**
	 * Returns the padding between the compound drawables and the text.
	 * 
	 * @return the padding between the compound drawables and the text
	 */
	public int compoundPad() {
		return view.getCompoundDrawablePadding();
	}

	/**
	 * Sets the size of the padding between the compound drawables and the text.
	 * 
	 * @param padding
	 * @return self
	 */
	public self compoundPad(int padding) {
		view.setCompoundDrawablePadding(padding);
		return (self) this;
	}

	/**
	 * Returns the bottom padding of the view, plus space for the bottom
	 * Drawable if any.
	 * 
	 * @return the bottom padding of the view, plus space for the bottom
	 *         Drawable if any.
	 */
	public int compoundPadBottom() {
		return view.getCompoundPaddingBottom();
	}

	/**
	 * Returns the left padding of the view, plus space for the left Drawable if
	 * any.
	 * 
	 * @return the left padding of the view, plus space for the left Drawable if
	 *         any.
	 */
	public int compoundPadLeft() {
		return view.getCompoundPaddingLeft();
	}

	/**
	 * Returns the right padding of the view, plus space for the right Drawable
	 * if any.
	 * 
	 * @return the right padding of the view, plus space for the right Drawable
	 *         if any.
	 */
	public int compoundPadRight() {
		return view.getCompoundPaddingRight();
	}

	/**
	 * Returns the top padding of the view, plus space for the top Drawable if
	 * any.
	 * 
	 * @return the top padding of the view, plus space for the top Drawable if
	 *         any.
	 */
	public int compoundPadTop() {
		return view.getCompoundPaddingTop();
	}

	/**
	 * 
	 * @return whether or not the cursor is visible (assuming this TextView is
	 *         editable)
	 */
	public boolean cursorViz() {
		return view.isCursorVisible();
	}

	/**
	 * Set whether the cursor is visible. The default is true. Note that this
	 * property only makes sense for editable VaporTextView.
	 * 
	 * @param cursorVisible
	 * @return self
	 */
	public self cursorViz(boolean cursorVisible) {
		view.setCursorVisible(cursorVisible);
		return (self) this;
	}

	/**
	 * Return the current color selected for normal text.
	 * 
	 * @return Returns the current text color.
	 */
	public final int currentColor() {
		return view.getCurrentTextColor();
	}

	/**
	 * Prints information about this view in the log output, with the tag
	 * VIEW_LOG_TAG. Each line in the output is preceded with an indentation
	 * defined by the depth.
	 * 
	 * @param depth
	 *            the indentation level
	 * @return self
	 */
	public self debug(int depth) {
		view.debug(depth);
		return (self) this;
	}

	/**
	 * Return the text the TextView is displaying as an Editable object. If the
	 * text is not editable, null is returned.
	 * 
	 * @return the text the TextView is displaying as an Editable object
	 */
	public Editable editableText() {
		return view.getEditableText();
	}

	/**
	 * Set a special listener to be called when an action is performed on the
	 * text view. This will be called when the enter key is pressed, or when an
	 * action supplied to the IME is selected by the user. Setting this means
	 * that the normal hard key event will not insert a newline into the text
	 * view, even if it is multi-line; holding down the ALT modifier will,
	 * however, allow the user to insert a newline character.
	 * 
	 * @param editorActionListener
	 * @return self
	 */
	public self edit(vapor.listeners.widget.textview.$edit editorActionListener) {
		view.setOnEditorActionListener(editorActionListener);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onEditorAction(int), called
	 * when an attached input method calls InputConnection.performEditorAction()
	 * for this text view. The default implementation will call your action
	 * listener supplied to
	 * setOnEditorActionListener(TextView.OnEditorActionListener), or perform a
	 * standard operation for EditorInfo.IME_ACTION_NEXT,
	 * EditorInfo.IME_ACTION_PREVIOUS, or EditorInfo.IME_ACTION_DONE.
	 * 
	 * For backwards compatibility, if no IME options have been set and the text
	 * view would not normally advance focus on enter, then the NEXT and DONE
	 * actions received here will be turned into an enter key down/up pair to go
	 * through the normal key handling.
	 * 
	 * @param actionCode
	 *            The code of the action being performed.
	 * @return self
	 */
	public self editAction(int actionCode) {
		view.onEditorAction(actionCode);
		return (self) this;
	}

	/**
	 * Sets the Factory used to create new Editables.
	 * 
	 * @param editableFactory
	 * @return self
	 */
	public final self editFactory(Editable.Factory editableFactory) {
		view.setEditableFactory(editableFactory);
		return (self) this;
	}

	/**
	 * Returns where, if anywhere, words that are longer than the view is wide
	 * should be ellipsized.
	 * 
	 * @return where, if anywhere, words that are longer than the view is wide
	 *         should be ellipsized.
	 */
	public TextUtils.TruncateAt ellipsize() {
		return view.getEllipsize();
	}

	/**
	 * Causes words in the text that are longer than the view is wide to be
	 * ellipsized instead of broken in the middle. You may also want to
	 * singleLine() or setHorizontallyScrolling(boolean) to constrain the text
	 * to a single line. Use null to turn off ellipsizing. If setMaxLines(int)
	 * has been used to set two or more lines, END and MARQUEE* are only
	 * supported (other ellipsizing types will not do anything).
	 * 
	 * @param ellipsis
	 * @return self
	 */
	public self ellipsize(TextUtils.TruncateAt ellipsis) {
		view.setEllipsize(ellipsis);
		return (self) this;
	}

	/**
	 * Makes the TextView exactly this many ems wide
	 * 
	 * @param ems
	 * @return self
	 */
	public self ems(int ems) {
		view.setEms(ems);
		return (self) this;
	}

	public self ems(int minEms, int maxEms) {
		minEms(minEms);
		maxEms(maxEms);
		return (self) this;
	}

	/**
	 * 
	 * @return self
	 */
	public self finishBatchEdit() {
		view.endBatchEdit();
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onEndBatchEdit(), called by
	 * the framework in response to a request to end a batch of edit operations
	 * through a call to link endBatchEdit().
	 * 
	 * @return
	 */
	public self endBatchEdit() {
		view.onEndBatchEdit();
		return (self) this;
	}

	/**
	 * Returns the error message that was set to be displayed with
	 * error(CharSequence), or null if no error was set or if it the error was
	 * cleared by the widget after user input.
	 * 
	 * @return the error message that was set to be displayed with
	 *         error(CharSequence), or null if no error was set or if it the
	 *         error was cleared by the widget after user input.
	 */
	public CharSequence error() {
		return view.getError();
	}

	/**
	 * Sets the right-hand compound drawable of the VaporTextView to the "error"
	 * icon and sets an error message that will be displayed in a popup when the
	 * VaporTextView has focus. The icon and error message will be reset to null
	 * when any key events cause changes to the VaporTextView's text. If the
	 * error is null, the error message and icon will be cleared.
	 * 
	 * @param error
	 * @return self
	 */
	public self error(CharSequence error) {
		view.setError(error);
		return (self) this;
	}

	/**
	 * Sets the right-hand compound drawable of the VaporTextView to the
	 * specified icon and sets an error message that will be displayed in a
	 * popup when the VaporTextView has focus. The icon and error message will
	 * be reset to null when any key events cause changes to the VaporTextView's
	 * text. The drawable must already have had setBounds(Rect) set on it. If
	 * the error is null, the error message will be cleared (and you should
	 * provide a null icon as well).
	 * 
	 * @param error
	 * @return self
	 */
	public self error(CharSequence error, Drawable icon) {
		view.setError(error, icon);
		return (self) this;
	}

	/**
	 * If this TextView contains editable content, extract a portion of it based
	 * on the information in request in to outText.
	 * 
	 * @param request
	 * @param outText
	 * @return Returns true if the text was successfully extracted, else false.
	 */
	public boolean extract(ExtractedTextRequest request, ExtractedText outText) {
		return view.extractText(request, outText);
	}

	/**
	 * Apply to this text view the given extracted text, as previously returned
	 * by extract(ExtractedTextRequest, ExtractedText).
	 * 
	 * @param extractedText
	 * @return self
	 */
	public self extract(ExtractedText extractedText) {
		view.setExtractedText(extractedText);
		return (self) this;
	}

	/**
	 * Retrieve the input extras currently associated with the text view, which
	 * can be viewed as well as modified.
	 * 
	 * @param create
	 *            If true, the extras will be created if they don't already
	 *            exist. Otherwise, null will be returned if none have been
	 *            created.
	 * @return
	 */
	public VaporBundle extras(boolean create) {
		return $.Bundle(view.getInputExtras(create));
	}

	/**
	 * Set the extra input data of the text, which is the
	 * TextBoxAttribute.extras Bundle that will be filled in when creating an
	 * input connection. The given integer is the resource ID of an XML resource
	 * holding an <input-extras> XML tree.
	 * 
	 * @param xmlResId
	 * @return self
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	public self extras(int xmlResId) throws XmlPullParserException, IOException {
		view.setInputExtras(xmlResId);
		return (self) this;
	}

	/**
	 * Returns the extended bottom padding of the view, including both the
	 * bottom Drawable if any and any extra space to keep more than maxLines of
	 * text from showing. It is only valid to call this after measuring.
	 * 
	 * @return the extended bottom padding of the view
	 */
	public int padBottomExt() {
		return view.getExtendedPaddingBottom();
	}

	/**
	 * Returns the extended top padding of the view, including both the top
	 * Drawable if any and any extra space to keep more than maxLines of text
	 * from showing. It is only valid to call this after measuring.
	 * 
	 * @return the extended top padding of the view
	 */
	public int padTopExt() {
		return view.getExtendedPaddingTop();
	}

	/**
	 * Returns the current list of input filters.
	 * 
	 * @return
	 */
	public InputFilter[] filters() {
		return view.getFilters();
	}

	/**
	 * Sets the list of input filters that will be used if the buffer is
	 * Editable. Has no effect otherwise.
	 * 
	 * @param filters
	 * @return self
	 */
	public self filters(InputFilter[] filters) {
		view.setFilters(filters);
		return (self) this;
	}

	/**
	 * Set the TextView so that when it takes focus, all the text is selected.
	 * 
	 * @param selectAllOnFocus
	 * @return self
	 */
	public self focusSelectAll(boolean selectAllOnFocus) {
		view.setSelectAllOnFocus(selectAllOnFocus);
		return (self) this;
	}

	/**
	 * 
	 * @return the current typeface and style in which the text is being
	 *         displayed.
	 */
	public Typeface font() {
		return view.getTypeface();
	}

	/**
	 * Sets the typeface and style in which the text should be displayed. Note
	 * that not all Typeface families actually have bold and italic variants, so
	 * you may need to use font(Typeface, int) to get the appearance that you
	 * actually want.
	 * 
	 * @param typeface
	 * @return self
	 */
	public self font(Typeface typeface) {
		view.setTypeface(typeface);
		return (self) this;
	}

	/**
	 * Sets the typeface and style in which the text should be displayed, and
	 * turns on the fake bold and italic bits in the Paint if the Typeface that
	 * you provided does not have all the bits in the style that you specified.
	 * 
	 * @param typeface
	 * @param style
	 * @return self
	 */
	public self font(Typeface typeface, int style) {
		view.setTypeface(typeface, style);
		return (self) this;
	}

	/**
	 * Gets whether the TextView includes extra top and bottom padding to make
	 * room for accents that go above the normal ascent and descent.
	 * 
	 * @return
	 */
	public boolean fontPadding() {
		return view.getIncludeFontPadding();
	}

	/**
	 * Set whether the TextView includes extra top and bottom padding to make
	 * room for accents that go above the normal ascent and descent. The default
	 * is true.
	 * 
	 * @param includePadding
	 * @return self
	 */
	public self fontPadding(boolean includePadding) {
		view.setIncludeFontPadding(includePadding);
		return (self) this;
	}

	/**
	 * 
	 * @return the size (in pixels) of the default text size in this TextView.
	 */
	public float fontSize() {
		return view.getTextSize();
	}

	/**
	 * Set the default text size to the given value, interpreted as
	 * "scaled pixel" units. This size is adjusted based on the current density
	 * and user font size preference.
	 * 
	 * @param size
	 * @return self
	 */
	public self fontSize(float size) {
		view.setTextSize(size);
		return (self) this;
	}

	/**
	 * Set the default text size to a given unit and value.
	 * 
	 * @param unit
	 *            The desired dimension unit.
	 * @param size
	 *            The desired size in the given units.
	 * @return
	 */
	public self fontSize(int unit, float size) {
		view.setTextSize(unit, size);
		return (self) this;
	}

	/**
	 * Return whether this text view is including its entire text contents in
	 * frozen icicles.
	 * 
	 * @return Returns true if text is included, false if it isn't.
	 */
	public boolean freezes() {
		return view.getFreezesText();
	}

	/**
	 * Control whether this text view saves its entire text contents when
	 * freezing to an icicle, in addition to dynamic state such as cursor
	 * position. By default this is false, not saving the text. Set to true if
	 * the text in the text view is not being saved somewhere else in persistent
	 * storage (such as in a content provider) so that if the view is later
	 * thawed the user will not lose their data.
	 * 
	 * @param freezesText
	 *            Controls whether a frozen icicle should include the entire
	 *            text data: true to include it, false to not.
	 * @return self
	 */
	public self freezes(boolean freezesText) {
		view.setFreezesText(freezesText);
		return (self) this;
	}

	/**
	 * Returns the horizontal and vertical alignment of this TextView.
	 * 
	 * @return A Gravity constant for placing an object within a potentially
	 *         larger container.
	 */
	public int gravity() {
		return view.getGravity();
	}

	/**
	 * Sets the horizontal alignment of the text and the vertical gravity that
	 * will be used when there is extra space in the TextView beyond what is
	 * required for the text itself.
	 * 
	 * @param gravity
	 * @return self
	 */
	public self gravity(int gravity) {
		view.setGravity(gravity);
		return (self) this;
	}

	/**
	 * Returns the color used to display the selection highlight
	 * 
	 * @return the color used to display the selection highlight
	 */
	public int hiColor() {
		return view.getHighlightColor();
	}

	/**
	 * Sets the color used to display the selection highlight.
	 * 
	 * @param color
	 * @return self
	 */
	public self hiColor(int color) {
		view.setHighlightColor(color);
		return (self) this;
	}

	/**
	 * Returns the hint that is displayed when the text of the TextView is
	 * empty.
	 * 
	 * @return the hint that is displayed when the text of the TextView is
	 *         empty.
	 */
	public CharSequence hint() {
		return view.getHint();
	}

	/**
	 * Sets the text to be displayed when the text of the VaporTextView is
	 * empty, from a resource.
	 * 
	 * @param resId
	 * @return self
	 */
	public final self hint(int resId) {
		view.setHint(resId);
		return (self) this;
	}

	/**
	 * Sets the text to be displayed when the text of the TextView is empty.
	 * Null means to use the normal empty text. The hint does not currently
	 * participate in determining the size of the view.
	 * 
	 * @param hint
	 * @return self
	 */
	public final self hint(CharSequence hint) {
		view.setHint(hint);
		return (self) this;
	}

	/**
	 * Return the current color selected to paint the hint text.
	 * 
	 * @return Returns the current hint text color.
	 */
	public final int hintColor() {
		return view.getCurrentHintTextColor();
	}

	/**
	 * Sets the color of the hint text for all the states (disabled, focussed,
	 * selected...) of this TextView.
	 * 
	 * @param color
	 * @return self
	 */
	public final self hintColor(int color) {
		view.setHintTextColor(color);
		return (self) this;
	}

	/**
	 * Sets the color of the hint text.
	 * 
	 * @param colors
	 * @return self
	 */
	public final self hintColor(ColorStateList colors) {
		view.setHintTextColor(colors);
		return (self) this;
	}

	/**
	 * Returns the color of the hint text, for the different states of this
	 * TextView.
	 * 
	 * @return the color of the hint text, for the different states of this
	 *         TextView.
	 */
	public final ColorStateList hintColors() {
		return view.getHintTextColors();
	}

	/**
	 * Sets whether the text should be allowed to be wider than the View is. If
	 * false, it will be wrapped to the width of the VaporView.
	 * 
	 * @param wrapText
	 * @return self
	 */
	public self wrap(boolean wrapText) {
		view.setHorizontallyScrolling(wrapText);
		return (self) this;
	}

	/**
	 * Get the type of the IME editor.
	 * 
	 * @return the type of the IME editor.
	 */
	public int ime() {
		return view.getImeOptions();
	}

	/**
	 * Change the editor type integer associated with the text view, which will
	 * be reported to an IME with imeOptions when it has focus.
	 * 
	 * @param imeOptions
	 * @return self
	 */
	public self ime(int imeOptions) {
		view.setImeOptions(imeOptions);
		return (self) this;
	}

	/**
	 * Get the IME action ID previous set with imeLabel(CharSequence, int).
	 * 
	 * @return the IME action ID previous set with imeLabel(CharSequence, int).
	 */
	public int imeId() {
		return view.getImeActionId();
	}

	/**
	 * Get the IME action label previous set with imeLabel(CharSequence, int).
	 * 
	 * @return the IME action label previous set with imeLabel(CharSequence,
	 *         int).
	 */
	public CharSequence imeLabel() {
		return view.getImeActionLabel();
	}

	/**
	 * Change the custom IME action associated with the text view, which will be
	 * reported to an IME with actionLabel and actionId when it has focus.
	 * 
	 * @param label
	 * @param actionId
	 * @return self
	 */
	public self imeLabel(CharSequence label, int actionId) {
		view.setImeActionLabel(label, actionId);
		return (self) this;
	}

	/**
	 * Get the private type of the content.
	 * 
	 * @return the private type of the content.
	 */
	public String privateIme() {
		return view.getPrivateImeOptions();
	}

	/**
	 * Set the private content type of the text, which is the
	 * EditorInfo.privateImeOptions field that will be filled in when creating
	 * an input connection.
	 * 
	 * @param type
	 * @return self
	 */
	public self privateIme(String type) {
		view.setPrivateImeOptions(type);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor for invoking onPrivateIMECommand(String,Bundle),
	 * used in response to a request to a private command from the current
	 * method, provided by it calling InputConnection.performPrivateCommand().
	 * 
	 * @param action
	 *            The action name of the command.
	 * @param data
	 *            Any additional data for the command. This may be null.
	 * @return Return true if you handled the command, else false.
	 */
	public boolean privateImeCommand(String action, VaporBundle data) {
		return view.onPrivateIMECommand(action, data.bundle());
	}

	/**
	 * 
	 * @return
	 */
	public boolean inputTarget() {
		return view.isInputMethodTarget();
	}

	/**
	 * Get the type of the editable content.
	 * 
	 * @return
	 */
	public int inputType() {
		return view.getInputType();
	}

	/**
	 * Set the type of the content with a constant as defined for inputType.
	 * This will take care of changing the key listener, by calling
	 * key(KeyListener), to match the given content type. If the given content
	 * type is TYPE_NULL then a soft keyboard will not be displayed for this
	 * text view. Note that the maximum number of displayed lines (see
	 * maxLines(int)) will be modified if you change the
	 * TYPE_TEXT_FLAG_MULTI_LINE flag of the input type.
	 * 
	 * @param inputType
	 * @return self
	 */
	public self inputType(int inputType) {
		view.setInputType(inputType);
		return (self) this;
	}

	/**
	 * Sets the key listener to be used with this TextView. This can be null to
	 * disallow user input. Note that this method has significant and subtle
	 * interactions with soft keyboards and other input method: see
	 * KeyListener.getContentType() for important details. Calling this method
	 * will replace the current content type of the text view with the content
	 * type returned by the key listener.
	 * 
	 * Be warned that if you want a TextView with a key listener or movement
	 * method not to be focusable, or if you want a TextView without a key
	 * listener or movement method to be focusable, you must call
	 * setFocusable(boolean) again after calling this to get the focusability
	 * back the way you want it.
	 * 
	 * @param keyListener
	 * @return self
	 */
	public self key(vapor.listeners.text.method.$key keyListener) {
		view.setKeyListener(keyListener);
		return (self) this;
	}

	/**
	 * 
	 * @return the current key listener for this TextView. This will frequently
	 *         be null for non-EditText TextViews.
	 */
	public final KeyListener keyListener() {
		return view.getKeyListener();
	}

	/**
	 * Returns the Layout that is currently being used to display the text. This
	 * can be null if the text or width has recently changes.
	 * 
	 * @return the Layout that is currently being used to display the text. This
	 *         can be null if the text or width has recently changes.
	 */
	public final Layout layout() {
		return view.getLayout();
	}

	/**
	 * Returns the length, in characters, of the text managed by this TextView
	 * 
	 * @return
	 */
	public int length() {
		return view.length();
	}

	/**
	 * Return the baseline for the specified line (0...lineCount() - 1)
	 * 
	 * @param line
	 *            which line to examine (0..getLineCount() - 1)
	 * @return the Y-coordinate of the baseline
	 */
	public int lineBounds(int line) {
		return lineBounds(line, null);
	}

	/**
	 * Return the baseline for the specified line (0...lineCount() - 1) If
	 * bounds is not null, return the top, left, right, bottom extents of the
	 * specified line in it. If the internal Layout has not been built, return 0
	 * and set bounds to (0, 0, 0, 0)
	 * 
	 * @param line
	 *            which line to examine (0..getLineCount() - 1)
	 * @param bounds
	 *            Optional. If not null, it returns the extent of the line
	 * @return the Y-coordinate of the baseline
	 */
	public int lineBounds(int line, Rect bounds) {
		return view.getLineBounds(line, bounds);
	}

	/**
	 * Return the number of lines of text, or 0 if the internal Layout has not
	 * been built.
	 * 
	 * @return the number of lines of text, or 0 if the internal Layout has not
	 *         been built.
	 */
	public int lines() {
		return view.getLineCount();
	}

	/**
	 * Makes the TextView exactly this many lines tall. Note that setting this
	 * value overrides any other (minimum / maximum) number of lines or height
	 * setting. A single line TextView will set this value to 1.
	 * 
	 * @param lines
	 * @return self
	 */
	public self lines(int lines) {
		view.setLines(lines);
		return (self) this;
	}

	/**
	 * Returns the height of one standard line in pixels. Note that markup
	 * within the text can cause individual lines to be taller or shorter than
	 * this height, and the layout may contain additional first- or last-line
	 * padding.
	 * 
	 * @return the height of one standard line in pixels. Note that markup
	 *         within the text can cause individual lines to be taller or
	 *         shorter than this height, and the layout may contain additional
	 *         first- or last-line padding.
	 */
	public int lineHeight() {
		return view.getLineHeight();
	}

	/**
	 * Set the range for the number of lines in this View
	 * 
	 * @param minLines
	 *            The minimum number of lines
	 * @param maxLines
	 *            The maximum number of lines
	 * @return self
	 */
	public self lines(int minLines, int maxLines) {
		minLines(minLines);
		maxLines(maxLines);
		return (self) this;
	}

	/**
	 * Sets line spacing for this TextView. Each line will have its height
	 * multiplied by mult and have add added to it.
	 * 
	 * @param add
	 * @param mult
	 * @return self
	 */
	public self lineSpace(float add, float mult) {
		view.setLineSpacing(add, mult);
		return (self) this;
	}

	/**
	 * Gets the line spacing extra space
	 * 
	 * @return the extra space that is added to the height of each lines of this
	 *         TextView.
	 */
	public float lineSpaceExtra() {
		return view.getLineSpacingExtra();
	}

	/**
	 * Gets the line spacing multiplier
	 * 
	 * @return the value by which each line's height is multiplied to get its
	 *         actual height.
	 */
	public float lineSpaceMultiplier() {
		return view.getLineSpacingMultiplier();
	}

	/**
	 * Turns all occurrences of the link types indicated in the mask into
	 * clickable links. If matches are found the movement method for the
	 * TextView is set to LinkMovementMethod.
	 * 
	 * @param mask
	 * @return self
	 */
	public self link(int mask) {
		Linkify.addLinks(view, mask);
		return (self) this;
	}

	/**
	 * Returns the list of colors used to paint the links in the text, for the
	 * different states of this TextView
	 * 
	 * @return the list of colors used to paint the links in the text, for the
	 *         different states of this TextView
	 */
	public ColorStateList linkColors() {
		return view.getLinkTextColors();
	}

	/**
	 * Sets the color of links in the text.
	 * 
	 * @param color
	 * @return self
	 */
	public final self linkColor(int color) {
		view.setLinkTextColor(color);
		return (self) this;
	}

	/**
	 * Sets the color of links in the text.
	 * 
	 * @param color
	 * @return self
	 */
	public final self linkColor(ColorStateList colors) {
		view.setLinkTextColor(colors);
		return (self) this;
	}

	/**
	 * Gets the number of times the marquee animation is repeated. Only
	 * meaningful if the TextView has marquee enabled.
	 * 
	 * @return the number of times the marquee animation is repeated. -1 if the
	 *         animation repeats indefinitely
	 */
	public int marqueeRepeatLimit() {
		return view.getMarqueeRepeatLimit();
	}

	/**
	 * Sets how many times to repeat the marquee animation. Only applied if the
	 * TextView has marquee enabled. Set to -1 to repeat indefinitely.
	 * 
	 * @param marqueeLimit
	 * @return
	 */
	public self marqueeRepeatLimit(int marqueeLimit) {
		view.setMarqueeRepeatLimit(marqueeLimit);
		return (self) this;
	}

	/**
	 * Gets the autolink mask of the text. See Linkify.ALL and peers for
	 * possible values.
	 * 
	 * @return the autolink mask of the text
	 */
	public final int mask() {
		return view.getAutoLinkMask();
	}

	/**
	 * Sets the autolink mask of the text. See Linkify.ALL and peers for
	 * possible values.
	 * 
	 * @param mask
	 * @return self
	 */
	public final self mask(int mask) {
		view.setAutoLinkMask(mask);
		return (self) this;
	}

	/**
	 * 
	 * @return the maximum width of the TextView, expressed in ems or -1 if the
	 *         maximum width was set in pixels instead (using maxWidth(int) or
	 *         width(int)).
	 */
	public int maxEms() {
		return view.getMaxEms();
	}

	/**
	 * Makes the TextView at most this many ems wide
	 * 
	 * @param maxEms
	 * @return self
	 */
	public self maxEms(int maxEms) {
		view.setMaxEms(maxEms);
		return (self) this;
	}

	/**
	 * 
	 * @return the maximum height of this TextView expressed in pixels, or -1 if
	 *         the maximum height was set in number of lines instead using or
	 *         lines(int).
	 */
	public int maxHeight() {
		return view.getMaxHeight();
	}

	/**
	 * Makes the TextView at most this many pixels tall. This option is mutually
	 * exclusive with the maxLines(int) method. Setting this value overrides any
	 * other (maximum) number of lines setting.
	 * 
	 * @param maxHeight
	 * @return self
	 */
	public self maxHeight(int maxHeight) {
		view.setMaxHeight(maxHeight);
		return (self) this;
	}

	/**
	 * Returns the maximum number of lines displayed in this TextView, or -1 if
	 * the maximum height was set in pixels instead using or height(int).
	 * 
	 * @return the maximum number of lines displayed in this TextView, or -1 if
	 *         the maximum height was set in pixels instead using or
	 *         height(int).
	 */
	public int maxLines() {
		return view.getMaxLines();
	}

	/**
	 * Makes the TextView at most this many lines tall. Setting this value
	 * overrides any other (maximum) height setting.
	 * 
	 * @param maxLines
	 * @return self
	 */
	public self maxLines(int maxLines) {
		view.setMaxLines(maxLines);
		return (self) this;
	}

	/**
	 * 
	 * @return the maximum width of the TextView, in pixels or -1 if the maximum
	 *         width was set in ems instead (using maxEms(int) or ems(int)).
	 */
	public int maxWidth() {
		return view.getMaxWidth();
	}

	/**
	 * Makes the TextView at most this many pixels wide
	 * 
	 * @param maxWidth
	 * @return self
	 */
	public self maxWidth(int maxWidth) {
		view.setMaxWidth(maxWidth);
		return (self) this;
	}

	/**
	 * Returns the minimum width of the TextView, expressed in ems or -1 if the
	 * minimum width was set in pixels instead (using minWidth(int) or
	 * width(int)).
	 * 
	 * @return the minimum width of the TextView, expressed in ems or -1 if the
	 *         minimum width was set in pixels instead (using minWidth(int) or
	 *         width(int)).
	 */
	public int minEms() {
		return view.getMinEms();
	}

	/**
	 * Makes the TextView at least this many ems wide
	 * 
	 * @param minEms
	 * @return self
	 */
	public self minEms(int minEms) {
		view.setMinEms(minEms);
		return (self) this;
	}

	@Override
	public int minHeight() {
		return view.getMinHeight();
	}

	@Override
	public self minHeight(int minHeight) {
		view.setMinHeight(minHeight);
		return (self) this;
	}

	/**
	 * 
	 * @return the minimum number of lines displayed in this TextView, or -1 if
	 *         the minimum height was set in pixels instead using or
	 *         height(int).
	 */
	public int minLines() {
		return view.getMinLines();
	}

	/**
	 * Makes the VaporTextView at least this many lines tall. Setting this value
	 * overrides any other (minimum) height setting. A single line TextView will
	 * set this value to 1.
	 * 
	 * @param minLines
	 * @return self
	 */
	public self minLines(int minLines) {
		view.setMinLines(minLines);
		return (self) this;
	}

	@Override
	public int minWidth() {
		return view.getMinWidth();
	}

	@Override
	public self minWidth(int minWidth) {
		view.setMinWidth(minWidth);
		return (self) this;
	}

	/**
	 * 
	 * @return the movement method being used for this TextView. This will
	 *         frequently be null for non-EditText TextViews.
	 */
	public final MovementMethod moveMethod() {
		return view.getMovementMethod();
	}

	/**
	 * Sets the movement method (arrow key handler) to be used for this
	 * VaporTextView. This can be null to disallow using the arrow keys to move
	 * the cursor or scroll the view.
	 * 
	 * Be warned that if you want a VaporTextView with a key listener or
	 * movement method not to be focusable, or if you want a TextView without a
	 * key listener or movement method to be focusable, you must call
	 * focusable(boolean) again after calling this to get the focusability back
	 * the way you want it.
	 * 
	 * @param movementMethod
	 * @return self
	 */
	public final self moveMethod(MovementMethod movementMethod) {
		view.setMovementMethod(movementMethod);
		return (self) this;
	}

	/**
	 * Move the cursor, if needed, so that it is at an offset that is visible to
	 * the user. This will not move the cursor if it represents more than one
	 * character (a selection range). This will only work if the VaporTextView
	 * contains spannable text; otherwise it will do nothing.
	 * 
	 * @return True if the cursor was actually moved, false otherwise.
	 */
	public boolean offsetCursor() {
		return view.moveCursorToVisibleOffset();
	}

	/**
	 * 
	 * @return the base paint used for the text. Please use this only to consult
	 *         the Paint's properties and not to change them.
	 */
	public TextPaint paint() {
		return view.getPaint();
	}

	/**
	 * 
	 * @return the flags on the Paint being used to display the text.
	 */
	public int paintFlags() {
		return view.getPaintFlags();
	}

	/**
	 * Sets flags on the Paint being used to display the text and reflows the
	 * text if they are different from the old flags.
	 * 
	 * @param flags
	 * @return self
	 */
	public self paintFlags(int flags) {
		view.setPaintFlags(flags);
		return (self) this;
	}

	/**
	 * Get the character offset closest to the specified absolute position. A
	 * typical use case is to pass the result of x() and y() to this method.
	 * 
	 * @param x
	 *            The horizontal absolute position of a point on screen
	 * @param y
	 *            The vertical absolute position of a point on screen
	 * @return the character offset for the character whose position is closest
	 *         to the specified position. Returns -1 if there is no layout.
	 */
	public int offsetPos(float x, float y) {
		return view.getOffsetForPosition(x, y);
	}

	/**
	 * Fluent equivalent Vapor method for invoking onPreDraw(), a method that is
	 * invoked when the view tree is about to be drawn. At this point, all views
	 * in the tree have been measured and given a frame. Clients can use this to
	 * adjust their scroll bounds or even to request a new layout before drawing
	 * occurs.
	 * 
	 * @return Return true to proceed with the current drawing pass, or false to
	 *         cancel.
	 */
	public boolean preDraw() {
		return view.onPreDraw();
	}

	/**
	 * Directly change the content type integer of the text view, without
	 * modifying any other state.
	 * 
	 * @param rawInputType
	 * @return self
	 */
	public self rawInputType(int rawInputType) {
		view.setRawInputType(rawInputType);
		return (self) this;
	}

	/**
	 * Removes the specified TextWatcher from the list of those whose methods
	 * are called whenever this TextView's text changes.
	 * 
	 * @param textWatcher
	 * @return self
	 */
	public self remove(TextWatcher textWatcher) {
		view.removeTextChangedListener(textWatcher);
		return (self) this;
	}

	/**
	 * @param scroller
	 * @return self
	 */
	public self scroller(Scroller scroller) {
		view.setScroller(scroller);
		return (self) this;
	}

	/**
	 * When a TextView is used to display a useful piece of information to the
	 * user (such as a contact's address), it should be made selectable, so that
	 * the user can select and copy this content. Use selectable(boolean) or the
	 * TextView_textIsSelectable XML attribute to make this TextView selectable
	 * (text is not selectable by default). Note that this method simply returns
	 * the state of this flag. Although this flag has to be set in order to
	 * select text in non-editable TextView, the content of an EditText can
	 * always be selected, independently of the value of this flag.
	 * 
	 * @return True if the text displayed in this TextView can be selected by
	 *         the user.
	 */
	public boolean selectableText() {
		return view.isTextSelectable();
	}

	/**
	 * Sets whether or not (default) the content of this view is selectable by
	 * the user.
	 * 
	 * @param textIsSelectable
	 *            Whether or not the content of this TextView should be
	 *            selectable.
	 * @return self
	 */
	public self selectableText(boolean textIsSelectable) {
		view.setTextIsSelectable(textIsSelectable);
		return (self) this;
	}

	/**
	 * Return true iff there is a selection inside this text view.
	 * 
	 * @return
	 */
	public boolean selection() {
		return view.hasSelection();
	}

	/**
	 * Retrieves the value set in selectionCallback(ActionMode.Callback).
	 * Default is null.
	 * 
	 * @return the value set in selectionCallback(ActionMode.Callback). Default
	 *         is null.
	 */
	public ActionMode.Callback selectionListener() {
		return view.getCustomSelectionActionModeCallback();
	}

	/**
	 * If provided, this ActionMode.Callback will be used to create the
	 * ActionMode when text selection is initiated in this View. The standard
	 * implementation populates the menu with a subset of Select All, Cut, Copy
	 * and Paste actions, depending on what this View supports. A custom
	 * implementation can add new entries in the default menu in its
	 * onPrepareActionMode(ActionMode, Menu) method. The default actions can
	 * also be removed from the menu using removeItem(int) and passing
	 * selectAll, cut, copy or paste ids as parameters. Returning false from
	 * onCreateActionMode(ActionMode, Menu) will prevent the action mode from
	 * being started. Action click events should be handled by the custom
	 * implementation of onActionItemClicked(ActionMode, MenuItem). Note that
	 * text selection mode is not started when a TextView receives focus and the
	 * selectAllOnFocus flag has been set. The content is highlighted in that
	 * case, to allow for quick replacement.
	 * 
	 * @param actionModeCallback
	 * @return self
	 */
	public self selection(ActionMode.Callback actionModeCallback) {
		view.setCustomSelectionActionModeCallback(actionModeCallback);
		return (self) this;
	}

	/**
	 * Convenience for getSelectionEnd(CharSequence).
	 * 
	 * @return
	 */
	public int selectionEnd() {
		return view.getSelectionEnd();
	}

	/**
	 * Convenience for getSelectionStart(CharSequence).
	 * 
	 * @return
	 */
	public int selectionStart() {
		return view.getSelectionStart();
	}

	/**
	 * 
	 * @return the color of the shadow layer
	 */
	public int shadowColor() {
		return view.getShadowColor();
	}

	/**
	 * 
	 * @return the horizontal offset of the shadow layer
	 */
	public float shadowDx() {
		return view.getShadowDx();
	}

	/**
	 * 
	 * @return the vertical offset of the shadow layer
	 */
	public float shadowDy() {
		return view.getShadowDy();
	}

	/**
	 * Gives the text a shadow of the specified radius and color, the specified
	 * distance from its normal position.
	 * 
	 * @param radius
	 * @param dx
	 * @param dy
	 * @param color
	 * @return
	 */
	public self shadowLayer(float radius, float dx, float dy, int color) {
		view.setShadowLayer(radius, dx, dy, color);
		return (self) this;
	}

	/**
	 * Gets the radius of the shadow layer.
	 * 
	 * @return the radius of the shadow layer. If 0, the shadow layer is not
	 *         visible
	 */
	public float shadowRadius() {
		return view.getShadowRadius();
	}

	/**
	 * Sets the properties of this field (lines, horizontally scrolling,
	 * transformation method) to be for a single-line input.
	 * 
	 * @return self
	 */
	public self singleLine() {
		view.setSingleLine();
		return (self) this;
	}

	/**
	 * If true, sets the properties of this field (number of lines, horizontally
	 * scrolling, transformation method) to be for a single-line input; if
	 * false, restores these to the default conditions. Note that the default
	 * conditions are not necessarily those that were in effect prior this
	 * method, and you may want to reset these properties to your custom values.
	 * 
	 * @param singleLine
	 * @return self
	 */
	public self singleLine(boolean singleLine) {
		view.setSingleLine(singleLine);
		return (self) this;
	}

	/**
	 * Sets the Factory used to create new Spannables.
	 * 
	 * @param spannableFactory
	 * @return self
	 */
	public final self spanFactory(Spannable.Factory spannableFactory) {
		view.setSpannableFactory(spannableFactory);
		return (self) this;
	}

	/**
	 * Like text(CharSequence), except that the cursor position (if any) is
	 * retained in the new text.
	 * 
	 * @param text
	 *            The new text to place in the text view.
	 * @return self
	 */
	public final self statefulText(CharSequence text) {
		view.setTextKeepState(text);
		return (self) this;
	}

	/**
	 * Like text(CharSequence, android.widget.TextView.BufferType), except that
	 * the cursor position (if any) is retained in the new text.
	 * 
	 * @param text
	 * @param bufferType
	 * @return self
	 */
	public final self statefulText(CharSequence text,
			TextView.BufferType bufferType) {

		view.setTextKeepState(text, bufferType);

		return (self) this;
	}

	/**
	 * Return whether or not suggestions are enabled on this TextView. The
	 * suggestions are generated by the IME or by the spell checker as the user
	 * types. This is done by adding SuggestionSpans to the text. When
	 * suggestions are enabled (default), this list of suggestions will be
	 * displayed when the user asks for them on these parts of the text. This
	 * value depends on the inputType of this TextView. The class of the input
	 * type must be TYPE_CLASS_TEXT. In addition, the type variation must be one
	 * of TYPE_TEXT_VARIATION_NORMAL, TYPE_TEXT_VARIATION_EMAIL_SUBJECT,
	 * TYPE_TEXT_VARIATION_LONG_MESSAGE, TYPE_TEXT_VARIATION_SHORT_MESSAGE or
	 * TYPE_TEXT_VARIATION_WEB_EDIT_TEXT. And finally, the
	 * TYPE_TEXT_FLAG_NO_SUGGESTIONS flag must not be set.
	 * 
	 * @return true if the suggestions popup window is enabled, based on the
	 *         inputType.
	 */
	public boolean suggests() {
		return view.isSuggestionsEnabled();
	}

	/**
	 * Return the text the TextView is displaying. If text(...) was called with
	 * an argument of BufferType.SPANNABLE or BufferType.EDITABLE, you can cast
	 * the return value from this method to Spannable or Editable, respectively.
	 * Note: The content of the return value should not be modified. If you want
	 * a modifiable one, you should make your own copy first.
	 * 
	 * @return the text the TextView is displaying
	 */
	public CharSequence text() {
		return view.getText();
	}

	/**
	 * Sets the string value of the VaporTextView. VaporTextView does not accept
	 * HTML-like formatting, which you can do with text strings in XML resource
	 * files. To style your strings, attach android.text.style.* objects to a
	 * SpannableString, or see the Available Resource Types documentation for an
	 * example of setting formatted text in the XML resource file.
	 * 
	 * @param text
	 * @return self
	 */
	public final self text(CharSequence text) {
		view.setText(text);
		return (self) this;
	}

	/**
	 * Sets the text that this TextView is to display (see
	 * setText(CharSequence)) and also sets whether it is stored in a
	 * styleable/spannable buffer and whether it is editable.
	 * 
	 * @param text
	 * @param bufferType
	 * @return
	 */
	public final self text(CharSequence text, TextView.BufferType bufferType) {
		view.setText(text, bufferType);
		return (self) this;
	}

	/**
	 * 
	 * @param resId
	 * @return self
	 */
	public final self text(int resId) {
		view.setText(resId);
		return (self) this;
	}

	/**
	 * Adds a TextWatcher to the list of those whose methods are called whenever
	 * this TextView's text changes.
	 * 
	 * @param textWatcher
	 * @return self
	 */
	public self text(vapor.listeners.text.$text textWatcher) {
		view.addTextChangedListener(textWatcher);
		return (self) this;
	}

	/**
	 * Sets the TextView to display the specified slice of the specified char
	 * array. You must promise that you will not change the contents of the
	 * array except for right before another call to text(), since the
	 * VaporTextView has no way to know that the text has changed and that it
	 * needs to invalidate and re-layout.
	 * 
	 * @param text
	 * @param start
	 * @param length
	 * @return self
	 */
	public final self text(char[] text, int start, int length) {
		view.setText(text, start, length);
		return (self) this;
	}

	/**
	 * 
	 * @param resId
	 * @param bufferType
	 * @return self
	 */
	public final self text(int resId, TextView.BufferType bufferType) {
		view.setText(resId, bufferType);
		return (self) this;
	}

	public self text(String text) {
		view.setText(text);
		return (self) this;
	}

	/**
	 * Fluent equivalent Vapor method for invoking onTextContextMenuItem(int),
	 * used in response to selecting a context menu option for the text view.
	 * Currently this will be one of selectAll, cut, copy or paste.
	 * 
	 * @param id
	 * @return true if the context menu item action was performed.
	 */
	public boolean textOption(int id) {
		return view.onTextContextMenuItem(id);
	}

	/**
	 * 
	 * @return the extent by which text is currently being stretched
	 *         horizontally. This will usually be 1.
	 */
	public float textScaleX() {
		return view.getTextScaleX();
	}

	/**
	 * Sets the extent by which text should be stretched horizontally.
	 * 
	 * @param size
	 * @return self
	 */
	public self textScaleX(float size) {
		view.setTextScaleX(size);
		return (self) this;
	}

	/**
	 * Returns the total bottom padding of the view, including the bottom
	 * Drawable if any, the extra space to keep more than maxLines from showing,
	 * and the vertical offset for gravity, if any.
	 * 
	 * @return
	 */
	public int totalPadBottom() {
		return view.getTotalPaddingBottom();
	}

	/**
	 * Returns the total left padding of the view, including the left Drawable
	 * if any.
	 * 
	 * @return
	 */
	public int totalPadLeft() {
		return view.getTotalPaddingLeft();
	}

	/**
	 * Returns the total right padding of the view, including the right Drawable
	 * if any.
	 * 
	 * @return
	 */
	public int totalPadRight() {
		return view.getTotalPaddingRight();
	}

	/**
	 * Returns the total top padding of the view, including the top Drawable if
	 * any, the extra space to keep more than maxLines from showing, and the
	 * vertical offset for gravity, if any.
	 * 
	 * @return
	 */
	public int totalPadTop() {
		return view.getTotalPaddingTop();
	}

	/**
	 * Returns true, only while processing a touch gesture, if the initial touch
	 * down event caused focus to move to the text view and as a result its
	 * selection changed. Only valid while processing the touch gesture of
	 * interest, in an editable text view.
	 * 
	 * @return true, only while processing a touch gesture, if the initial touch
	 *         down event caused focus to move to the text view and as a result
	 *         its selection changed.
	 */
	public boolean touchFocused() {
		return view.didTouchFocusSelect();
	}

	/**
	 * 
	 * @return the current transformation method for this TextView. This will
	 *         frequently be null except for single-line and password fields.
	 */
	public final TransformationMethod transformMethod() {
		return view.getTransformationMethod();
	}

	/**
	 * Sets the transformation that is applied to the text that this TextView is
	 * displaying.
	 * 
	 * @param transformationMethod
	 * @return self
	 */
	public final self transformMethod(TransformationMethod transformationMethod) {
		view.setTransformationMethod(transformationMethod);
		return (self) this;
	}

	/**
	 * Returns the list of URLSpans attached to the text (by Linkify or
	 * otherwise) if any. You can call getURL() on them to find where they link
	 * to or use getSpanStart(Object) and getSpanEnd(Object) to find the region
	 * of the text they are attached to.
	 * 
	 * @return
	 */
	public URLSpan[] urls() {
		return view.getUrls();
	}

	/* INTERFACE : OnPreDrawListener */
	/**
	 * NOTE: This method serves to satisfy the OnPreDrawListener interface, use
	 * the equivalent VAPOR FLUENT method preDraw() instead
	 */
	@Override
	public boolean onPreDraw() {
		return preDraw();
	}

}
