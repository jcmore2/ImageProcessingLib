package com.jcmore2.testimageprocessinglibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.AsyncTask;

import com.jcmore2.imageprocessing.ImageProcessing;


/**
 * 
 * @author jcmore2 jcmore2@gmail.com
 *
 */
public class ProcessingAsyncTask extends AsyncTask<Integer, Void, Bitmap> {

	private final String TAG = "ProcessingAsyncTask";

	Bitmap _bitmap;
	Context _context;
	ProcessingListener _listener;

	public ProcessingAsyncTask(Context context, Bitmap b,
			ProcessingListener listener) {
		_context = context;
		_bitmap = b;
		_listener = listener;

	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected Bitmap doInBackground(Integer... params) {

		final int procesado = params[0];

		Bitmap result = null;

		switch (procesado) {
		case ImageProcessing.PROCESSING_NORMAL:
			result = ImageProcessing.applyNormalImage(_bitmap);
			break;
		case ImageProcessing.PROCESSING_HIGHLIGHT:
			result = ImageProcessing.applyHighlightImage(_bitmap);
			break;
		case ImageProcessing.PROCESSING_INVERT:
			result = ImageProcessing.applyInvert(_bitmap);
			break;
		case ImageProcessing.PROCESSING_GREY:
			result = ImageProcessing.applyGreyscale(_bitmap);
			break;
		case ImageProcessing.PROCESSING_GAMMA:
			result = ImageProcessing.applyGamma(_bitmap, 1.8, 1.8, 0.6);
			break;
		case ImageProcessing.PROCESSING_COLOR_FILTER:
			result = ImageProcessing.applyColorFilter(_bitmap, 150, 150, 150);
			break;
		case ImageProcessing.PROCESSING_SEPIA:
			result = ImageProcessing.applySepiaToningEffect(_bitmap, 5, 100,
					100, 0);
			break;
		case ImageProcessing.PROCESSING_COLOR_DEPTH:
			result = ImageProcessing.applyColorDepth(_bitmap, 64);
			break;
		case ImageProcessing.PROCESSING_CONTRAST:
			result = ImageProcessing.applyContrast(_bitmap, 20);
			break;
		case ImageProcessing.PROCESSING_ROTATE:
			result = ImageProcessing.applyRotate(_bitmap, 45);
			break;
		case ImageProcessing.PROCESSING_BRIGHTNESS:
			result = ImageProcessing.applyBrightness(_bitmap, 60);
			break;
		case ImageProcessing.PROCESSING_GAUSSIAN_BLUR:
			result = ImageProcessing.applyGaussianBlur(_bitmap);
			break;
		case ImageProcessing.PROCESSING_SHARPEN:
			result = ImageProcessing.applySharpen(_bitmap, 12);
			break;
		case ImageProcessing.PROCESSING_MEAN:
			result = ImageProcessing.applyMeanRemoval(_bitmap);
			break;
		case ImageProcessing.PROCESSING_SMOOTH:
			result = ImageProcessing.applySmooth(_bitmap, 5);
			break;
		case ImageProcessing.PROCESSING_EMBOSS:
			result = ImageProcessing.applyEmboss(_bitmap);
			break;
		case ImageProcessing.PROCESSING_ENGRAVE:
			result = ImageProcessing.applyEngrave(_bitmap);
			break;
		case ImageProcessing.PROCESSING_BOOST:
			result = ImageProcessing.applyBoost(_bitmap,
					ImageProcessing.BOOST_RED, 30);
			break;
		case ImageProcessing.PROCESSING_ROUND:
			result = ImageProcessing.applyRoundCorner(_bitmap, 25);
			break;
		case ImageProcessing.PROCESSING_MARK:
			result = ImageProcessing.applyMark(_bitmap, "watermark", new Point(
					40, 90), Color.BLUE, 100, 100, false);
			break;
		case ImageProcessing.PROCESSING_FLIP:
			result = ImageProcessing.applyFlip(_bitmap,
					ImageProcessing.FLIP_HORIZONTAL);
			break;
		case ImageProcessing.PROCESSING_COLOR_REPLACE:
			result = ImageProcessing.applyReplaceColor(_bitmap, Color.WHITE,
					Color.MAGENTA);
			break;
		case ImageProcessing.PROCESSING_TINT:
			result = ImageProcessing.applyTintImage(_bitmap, 20);
			break;
		case ImageProcessing.PROCESSING_FLEA:
			result = ImageProcessing.applyFleaEffect(_bitmap);
			break;
		case ImageProcessing.PROCESSING_BLACK:
			result = ImageProcessing.applyBlackFilter(_bitmap);
			break;
		case ImageProcessing.PROCESSING_SNOW:
			result = ImageProcessing.applySnowEffect(_bitmap);
			break;
		case ImageProcessing.PROCESSING_SHADDING:
			result = ImageProcessing.applyShadingFilter(_bitmap, Color.MAGENTA);
			break;
		case ImageProcessing.PROCESSING_SATURATION:
			result = ImageProcessing.applySaturationFilter(_bitmap, 40);
			break;
		case ImageProcessing.PROCESSING_HUE:
			result = ImageProcessing.applyHueFilter(_bitmap, 40);
			break;
		case ImageProcessing.PROCESSING_REFLECTION:
			result = ImageProcessing.applyReflection(_bitmap);
			break;

		default:
			break;
		}

		return result;

	}

	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);

		_listener.onFinish(result);
	}

	/**
	 * The ImageCallback interface defines a single method used to pass an image
	 * back to the calling object when it has been loaded.
	 */
	public static interface ProcessingListener {
		/**
		 * The onImageLoaded method is called by the ImageCache when an image
		 * has been loaded.
		 * 
		 * @param image
		 *            The requested image in the form of a Drawable object.
		 * @param url
		 *            The originally requested URL
		 */
		void onFinish(Bitmap bm);
	}

}
