package com.jcmore2.imageprocessing;

import java.util.ArrayList;
import java.util.Random;

import com.jcmore2.imageprocessing.model.ImageProcessingModel;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;

/**
 * Class to manage the image processing and filters
 * 
 * @author jcmore2 jcmore2@gmail.com
 * 
 */
public class ImageProcessing {

	
	/**
	 * Constantes para identificar el nombre de los procesos
	 */
	public static final String NORMAL = "Normal";
	public static final String HIGHLIGHT = "Highlight";
	public static final String INVERT = "Invert";
	public static final String GREY = "Grey";
	public static final String GAMMA = "Gamma";
	public static final String COLOR_FILTER = "Color Filter";
	public static final String SEPIA = "Sepia";
	public static final String COLOR_DEPTH = "Color Depth";
	public static final String CONTRAST = "Contrast";
	public static final String ROTATE = "Rotate";
	public static final String BRIGHTNESS = "Brightness";
	public static final String BLUR = "Blur";
	public static final String SHARPEN = "Sharpen";
	public static final String MEAN = "Mean";
	public static final String  SMOOTH = "Smooth";
	public static final String EMBOSS = "Emboss";
	public static final String ENGRAVE = "Engrave";
	public static final String BOOST = "Boost";
	public static final String ROUND = "Round";
	public static final String MARK = "Mark";
	public static final String FLIP = "Flip";
	public static final String COLOR_REPLACE = "Color Replace";
	public static final String TINT = "Tint";
	public static final String FLEA = "Flea";
	public static final String BLACK = "Black";
	public static final String SNOW = "Snow";
	public static final String SHADDING = "Shadding";
	public static final String SATURATION = "Saturation";
	public static final String HUE = "Hue";
	public static final String REFLECTION = "Reflection";
	
	/**
	 * Constantes para identificar cada tipo de procesado
	 */
	public static final int PROCESSING_NORMAL = 0;
	public static final int PROCESSING_HIGHLIGHT = 1;
	public static final int PROCESSING_INVERT = 2;
	public static final int PROCESSING_GREY = 3;
	public static final int PROCESSING_GAMMA = 4;
	public static final int PROCESSING_COLOR_FILTER = 5;
	public static final int PROCESSING_SEPIA = 6;
	public static final int PROCESSING_COLOR_DEPTH = 7;
	public static final int PROCESSING_CONTRAST = 8;
	public static final int PROCESSING_ROTATE = 9;
	public static final int PROCESSING_BRIGHTNESS = 10;
	public static final int PROCESSING_GAUSSIAN_BLUR = 11;
	public static final int PROCESSING_SHARPEN = 12;
	public static final int PROCESSING_MEAN = 13;
	public static final int PROCESSING_SMOOTH = 14;
	public static final int PROCESSING_EMBOSS = 15;
	public static final int PROCESSING_ENGRAVE = 16;
	public static final int PROCESSING_BOOST = 17;
	public static final int PROCESSING_ROUND = 18;
	public static final int PROCESSING_MARK = 19;
	public static final int PROCESSING_FLIP = 20;
	public static final int PROCESSING_COLOR_REPLACE = 21;
	public static final int PROCESSING_TINT = 22;
	public static final int PROCESSING_FLEA = 23;
	public static final int PROCESSING_BLACK = 24;
	public static final int PROCESSING_SNOW = 25;
	public static final int PROCESSING_SHADDING = 26;
	public static final int PROCESSING_SATURATION = 27;
	public static final int PROCESSING_HUE = 28;
	public static final int PROCESSING_REFLECTION = 29;

	public static ArrayList<Integer> effectList = new ArrayList<Integer>();

	static {
		effectList.add(PROCESSING_NORMAL);
		effectList.add(PROCESSING_HIGHLIGHT);
		effectList.add(PROCESSING_INVERT);
		effectList.add(PROCESSING_GREY);
		effectList.add(PROCESSING_GAMMA);
		effectList.add(PROCESSING_COLOR_FILTER);
		effectList.add(PROCESSING_SEPIA);
		effectList.add(PROCESSING_COLOR_DEPTH);
		effectList.add(PROCESSING_CONTRAST);
		effectList.add(PROCESSING_ROTATE);
		effectList.add(PROCESSING_BRIGHTNESS);
		effectList.add(PROCESSING_GAUSSIAN_BLUR);
		effectList.add(PROCESSING_SHARPEN);
		effectList.add(PROCESSING_MEAN);
		effectList.add(PROCESSING_SMOOTH);
		effectList.add(PROCESSING_EMBOSS);
		effectList.add(PROCESSING_ENGRAVE);
		effectList.add(PROCESSING_BOOST);
		effectList.add(PROCESSING_ROUND);
		effectList.add(PROCESSING_MARK);
		effectList.add(PROCESSING_FLIP);
		effectList.add(PROCESSING_COLOR_REPLACE);
		effectList.add(PROCESSING_TINT);
		effectList.add(PROCESSING_FLEA);
		effectList.add(PROCESSING_BLACK);
		effectList.add(PROCESSING_SNOW);
		effectList.add(PROCESSING_SHADDING);
		effectList.add(PROCESSING_SATURATION);
		effectList.add(PROCESSING_HUE);
		effectList.add(PROCESSING_REFLECTION);

	}
	
	
	public static ArrayList<ImageProcessingModel> imageProcessedList = new ArrayList<ImageProcessingModel>();

	static {
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_NORMAL, NORMAL, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_HIGHLIGHT, HIGHLIGHT, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_INVERT, INVERT, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_GREY, GREY, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_GAMMA, GAMMA, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_COLOR_FILTER, COLOR_FILTER, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_SEPIA, SEPIA, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_COLOR_DEPTH, COLOR_DEPTH, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_CONTRAST, CONTRAST, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_ROTATE, ROTATE, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_BRIGHTNESS, BRIGHTNESS, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_GAUSSIAN_BLUR, BLUR, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_SHARPEN, SHARPEN, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_MEAN, MEAN, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_SMOOTH, SMOOTH, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_EMBOSS, EMBOSS, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_ENGRAVE, ENGRAVE, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_BOOST, BOOST, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_ROUND, ROUND, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_MARK, MARK, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_FLIP, FLIP, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_COLOR_REPLACE, COLOR_REPLACE, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_TINT, TINT, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_FLEA, FLEA, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_BLACK, BLACK, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_SNOW, SNOW, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_SHADDING, SHADDING, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_SATURATION, SATURATION, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_HUE, HUE, null));
		imageProcessedList.add(new ImageProcessingModel(PROCESSING_REFLECTION, REFLECTION, null));

	}

	
	/**
	 * Devuelve la misma imagen
	 * 
	 * @param src
	 * @return
	 */
	public static Bitmap applyNormalImage(Bitmap bm) {
		// return out final image
		return bm;
	}
	
	
	/**
	 * Crea un aura en torno a la imagen
	 * 
	 * @param src
	 * @return
	 */
	public static Bitmap applyHighlightImage(Bitmap src) {
		// create new bitmap, which will be painted and becomes result image
		Bitmap bmOut = Bitmap.createBitmap(src.getWidth() + 96,
				src.getHeight() + 96, Bitmap.Config.ARGB_8888);
		// setup canvas for painting
		Canvas canvas = new Canvas(bmOut);
		// setup default color
		canvas.drawColor(0, PorterDuff.Mode.CLEAR);

		// create a blur paint for capturing alpha
		Paint ptBlur = new Paint();
		ptBlur.setMaskFilter(new BlurMaskFilter(15, Blur.NORMAL));
		int[] offsetXY = new int[2];
		// capture alpha into a bitmap
		Bitmap bmAlpha = src.extractAlpha(ptBlur, offsetXY);
		// create a color paint
		Paint ptAlphaColor = new Paint();
		ptAlphaColor.setColor(0xFFFFFFFF);
		// paint color for captured alpha region (bitmap)
		canvas.drawBitmap(bmAlpha, offsetXY[0], offsetXY[1], ptAlphaColor);
		// free memory
		bmAlpha.recycle();

		// paint the image source
		canvas.drawBitmap(src, 0, 0, null);

		// return out final image
		return bmOut;
	}

	/**
	 * Invierte el color de la imagen
	 * 
	 * @param src
	 * @return
	 */
	public static Bitmap applyInvert(Bitmap src) {
		// create new bitmap with the same settings as source bitmap
		Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(),
				src.getConfig());
		// color info
		int A, R, G, B;
		int pixelColor;
		// image size
		int height = src.getHeight();
		int width = src.getWidth();

		// scan through every pixel
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// get one pixel
				pixelColor = src.getPixel(x, y);
				// saving alpha channel
				A = Color.alpha(pixelColor);
				// inverting byte for each R/G/B channel
				R = 255 - Color.red(pixelColor);
				G = 255 - Color.green(pixelColor);
				B = 255 - Color.blue(pixelColor);
				// set newly-inverted pixel to output image
				bmOut.setPixel(x, y, Color.argb(A, R, G, B));
			}
		}

		// return final bitmap
		return bmOut;
	}

	/**
	 * Convierte la imagen a grises
	 * 
	 * @param src
	 * @return
	 */
	public static Bitmap applyGreyscale(Bitmap src) {
		// constant factors
		final double GS_RED = 0.299;
		final double GS_GREEN = 0.587;
		final double GS_BLUE = 0.114;

		// create output bitmap
		Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(),
				src.getConfig());
		// pixel information
		int A, R, G, B;
		int pixel;

		// get image size
		int width = src.getWidth();
		int height = src.getHeight();

		// scan through every single pixel
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				// get one pixel color
				pixel = src.getPixel(x, y);
				// retrieve color of all channels
				A = Color.alpha(pixel);
				R = Color.red(pixel);
				G = Color.green(pixel);
				B = Color.blue(pixel);
				// take conversion up to one single value
				R = G = B = (int) (GS_RED * R + GS_GREEN * G + GS_BLUE * B);
				// set new pixel color to output bitmap
				bmOut.setPixel(x, y, Color.argb(A, R, G, B));
			}
		}

		// return final image
		return bmOut;
	}

	/**
	 * Modifica el gamma de los colores de la imagen
	 * 
	 * @param src
	 * @param red
	 *            (0.6, 1.8)
	 * @param green
	 *            (0.6, 1.8)
	 * @param blue
	 *            (0.6, 1.8)
	 * @return
	 */
	public static Bitmap applyGamma(Bitmap src, double red, double green,
			double blue) {
		// create output image
		Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(),
				src.getConfig());
		// get image size
		int width = src.getWidth();
		int height = src.getHeight();
		// color information
		int A, R, G, B;
		int pixel;
		// constant value curve
		final int MAX_SIZE = 256;
		final double MAX_VALUE_DBL = 255.0;
		final int MAX_VALUE_INT = 255;
		final double REVERSE = 1.0;

		// gamma arrays
		int[] gammaR = new int[MAX_SIZE];
		int[] gammaG = new int[MAX_SIZE];
		int[] gammaB = new int[MAX_SIZE];

		// setting values for every gamma channels
		for (int i = 0; i < MAX_SIZE; ++i) {
			gammaR[i] = (int) Math.min(
					MAX_VALUE_INT,
					(int) ((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE
							/ red)) + 0.5));
			gammaG[i] = (int) Math.min(
					MAX_VALUE_INT,
					(int) ((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE
							/ green)) + 0.5));
			gammaB[i] = (int) Math.min(
					MAX_VALUE_INT,
					(int) ((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE
							/ blue)) + 0.5));
		}

		// apply gamma table
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				// get pixel color
				pixel = src.getPixel(x, y);
				A = Color.alpha(pixel);
				// look up gamma
				R = gammaR[Color.red(pixel)];
				G = gammaG[Color.green(pixel)];
				B = gammaB[Color.blue(pixel)];
				// set new color to output bitmap
				bmOut.setPixel(x, y, Color.argb(A, R, G, B));
			}
		}

		// return final image
		return bmOut;
	}

	/**
	 * Filtrar la imagen por color
	 * 
	 * @param src
	 * @param red
	 *            (100) (0) (0) (150)
	 * @param green
	 *            (0) (100) (0) (150)
	 * @param blue
	 *            (0) (0) (100) (150)
	 * @return
	 */
	public static Bitmap applyColorFilter(Bitmap src, double red, double green,
			double blue) {
		// image size
		int width = src.getWidth();
		int height = src.getHeight();
		// create output bitmap
		Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
		// color information
		int A, R, G, B;
		int pixel;

		// scan through all pixels
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				// get pixel color
				pixel = src.getPixel(x, y);
				// apply filtering on each channel R, G, B
				A = Color.alpha(pixel);
				R = (int) (Color.red(pixel) * red);
				G = (int) (Color.green(pixel) * green);
				B = (int) (Color.blue(pixel) * blue);
				// set new color pixel to output bitmap
				bmOut.setPixel(x, y, Color.argb(A, R, G, B));
			}
		}

		// return final image
		return bmOut;
	}

	/**
	 * Imagen en tono sepia
	 * 
	 * @param src
	 * @param depth
	 * @param red
	 * @param green
	 * @param blue
	 * @return
	 */
	public static Bitmap applySepiaToningEffect(Bitmap src, int depth,
			double red, double green, double blue) {
		// image size
		int width = src.getWidth();
		int height = src.getHeight();
		// create output bitmap
		Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
		// constant grayscale
		final double GS_RED = 0.3;
		final double GS_GREEN = 0.59;
		final double GS_BLUE = 0.11;
		// color information
		int A, R, G, B;
		int pixel;

		// scan through all pixels
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				// get pixel color
				pixel = src.getPixel(x, y);
				// get color on each channel
				A = Color.alpha(pixel);
				R = Color.red(pixel);
				G = Color.green(pixel);
				B = Color.blue(pixel);
				// apply grayscale sample
				B = G = R = (int) (GS_RED * R + GS_GREEN * G + GS_BLUE * B);

				// apply intensity level for sepid-toning on each channel
				R += (depth * red);
				if (R > 255) {
					R = 255;
				}

				G += (depth * green);
				if (G > 255) {
					G = 255;
				}

				B += (depth * blue);
				if (B > 255) {
					B = 255;
				}

				// set new pixel color to output image
				bmOut.setPixel(x, y, Color.argb(A, R, G, B));
			}
		}

		// return final image
		return bmOut;
	}

	/**
	 * Imagen con menos pixeles de profundidad
	 * 
	 * @param src
	 * @param bitOffset
	 *            (32,64,128)
	 * @return
	 */
	public static Bitmap applyColorDepth(Bitmap src, int bitOffset) {
		// get image size
		int width = src.getWidth();
		int height = src.getHeight();
		// create output bitmap
		Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
		// color information
		int A, R, G, B;
		int pixel;

		// scan through all pixels
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				// get pixel color
				pixel = src.getPixel(x, y);
				A = Color.alpha(pixel);
				R = Color.red(pixel);
				G = Color.green(pixel);
				B = Color.blue(pixel);

				// round-off color offset
				R = ((R + (bitOffset / 2))
						- ((R + (bitOffset / 2)) % bitOffset) - 1);
				if (R < 0) {
					R = 0;
				}
				G = ((G + (bitOffset / 2))
						- ((G + (bitOffset / 2)) % bitOffset) - 1);
				if (G < 0) {
					G = 0;
				}
				B = ((B + (bitOffset / 2))
						- ((B + (bitOffset / 2)) % bitOffset) - 1);
				if (B < 0) {
					B = 0;
				}

				// set pixel color to output bitmap
				bmOut.setPixel(x, y, Color.argb(A, R, G, B));
			}
		}

		// return final image
		return bmOut;
	}

	/**
	 * Imagen con contraste a negro
	 * 
	 * @param src
	 * @param value
	 *            (50,100)
	 * @return
	 */
	public static Bitmap applyContrast(Bitmap src, double value) {
		// image size
		int width = src.getWidth();
		int height = src.getHeight();
		// create output bitmap
		Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
		// color information
		int A, R, G, B;
		int pixel;
		// get contrast value
		double contrast = Math.pow((100 + value) / 100, 2);

		// scan through all pixels
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				// get pixel color
				pixel = src.getPixel(x, y);
				A = Color.alpha(pixel);
				// apply filter contrast for every channel R, G, B
				R = Color.red(pixel);
				R = (int) (((((R / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
				if (R < 0) {
					R = 0;
				} else if (R > 255) {
					R = 255;
				}

				G = Color.red(pixel);
				G = (int) (((((G / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
				if (G < 0) {
					G = 0;
				} else if (G > 255) {
					G = 255;
				}

				B = Color.red(pixel);
				B = (int) (((((B / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
				if (B < 0) {
					B = 0;
				} else if (B > 255) {
					B = 255;
				}

				// set new pixel color to output bitmap
				bmOut.setPixel(x, y, Color.argb(A, R, G, B));
			}
		}

		// return final image
		return bmOut;
	}

	/**
	 * Imagen rotada
	 * 
	 * @param src
	 * @param degree
	 *            (45, 90,180)
	 * @return
	 */
	public static Bitmap applyRotate(Bitmap src, float degree) {
		// create new matrix
		Matrix matrix = new Matrix();
		// setup rotation degree
		matrix.postRotate(degree);

		// return new bitmap rotated using matrix
		return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(),
				matrix, true);
	}

	/**
	 * Imagen con mas (valores positivos) o menos brillo (valores negativos)
	 * 
	 * @param src
	 * @param value
	 *            (40, -60)
	 * @return
	 */
	public static Bitmap applyBrightness(Bitmap src, int value) {
		// image size
		int width = src.getWidth();
		int height = src.getHeight();
		// create output bitmap
		Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
		// color information
		int A, R, G, B;
		int pixel;

		// scan through all pixels
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				// get pixel color
				pixel = src.getPixel(x, y);
				A = Color.alpha(pixel);
				R = Color.red(pixel);
				G = Color.green(pixel);
				B = Color.blue(pixel);

				// increase/decrease each channel
				R += value;
				if (R > 255) {
					R = 255;
				} else if (R < 0) {
					R = 0;
				}

				G += value;
				if (G > 255) {
					G = 255;
				} else if (G < 0) {
					G = 0;
				}

				B += value;
				if (B > 255) {
					B = 255;
				} else if (B < 0) {
					B = 0;
				}

				// apply new pixel color to output bitmap
				bmOut.setPixel(x, y, Color.argb(A, R, G, B));
			}
		}

		// return final image
		return bmOut;
	}

	/**
	 * Aplica una funcion Gausiana a la imagen
	 * 
	 * @param src
	 * @return
	 */
	public static Bitmap applyGaussianBlur(Bitmap src) {
		double[][] GaussianBlurConfig = new double[][] { { 1, 2, 1 },
				{ 2, 4, 2 }, { 1, 2, 1 } };
		ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
		convMatrix.applyConfig(GaussianBlurConfig);
		convMatrix.Factor = 16;
		convMatrix.Offset = 0;
		return ConvolutionMatrix.computeConvolution3x3(src, convMatrix);
	}

	/**
	 * Imagen con los bordes mas afilados
	 * 
	 * @param src
	 * @param weight
	 * @return
	 */
	public static Bitmap applySharpen(Bitmap src, double weight) {
		double[][] SharpConfig = new double[][] { { 0, -2, 0 },
				{ -2, weight, -2 }, { 0, -2, 0 } };
		ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
		convMatrix.applyConfig(SharpConfig);
		convMatrix.Factor = weight - 8;
		return ConvolutionMatrix.computeConvolution3x3(src, convMatrix);
	}

	/**
	 * Imagen con la Media eliminada
	 * 
	 * @param src
	 * @return
	 */
	public static Bitmap applyMeanRemoval(Bitmap src) {
		double[][] MeanRemovalConfig = new double[][] { { -1, -1, -1 },
				{ -1, 9, -1 }, { -1, -1, -1 } };
		ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
		convMatrix.applyConfig(MeanRemovalConfig);
		convMatrix.Factor = 1;
		convMatrix.Offset = 0;
		return ConvolutionMatrix.computeConvolution3x3(src, convMatrix);
	}

	/**
	 * Imagen suavizada
	 * 
	 * @param src
	 * @param value
	 *            (13)
	 * @return
	 */
	public static Bitmap applySmooth(Bitmap src, double value) {
		ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
		convMatrix.setAll(1);
		convMatrix.Matrix[1][1] = value;
		convMatrix.Factor = value + 8;
		convMatrix.Offset = 1;
		return ConvolutionMatrix.computeConvolution3x3(src, convMatrix);
	}

	/**
	 * Imagen realzada
	 * 
	 * @param src
	 * @return
	 */
	public static Bitmap applyEmboss(Bitmap src) {
		double[][] EmbossConfig = new double[][] { { -1, 0, -1 }, { 0, 4, 0 },
				{ -1, 0, -1 } };
		ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
		convMatrix.applyConfig(EmbossConfig);
		convMatrix.Factor = 1;
		convMatrix.Offset = 127;
		return ConvolutionMatrix.computeConvolution3x3(src, convMatrix);
	}

	/**
	 * Imagen en gris con bordes marcados
	 * 
	 * @param src
	 * @return
	 */
	public static Bitmap applyEngrave(Bitmap src) {
		ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
		convMatrix.setAll(0);
		convMatrix.Matrix[0][0] = -2;
		convMatrix.Matrix[1][1] = 2;
		convMatrix.Factor = 1;
		convMatrix.Offset = 95;
		return ConvolutionMatrix.computeConvolution3x3(src, convMatrix);
	}

	// type definition
	public static final int BOOST_RED = 1;
	public static final int BOOST_GREEN = 2;
	public static final int BOOST_BLUE = 3;

	/**
	 * Imagen con los colores marcados
	 * 
	 * @param src
	 * @param type
	 *            (1-rojo, 2-verde, 3-azul)
	 * @param percent
	 * @return
	 */
	public static Bitmap applyBoost(Bitmap src, int type, float percent) {
		int width = src.getWidth();
		int height = src.getHeight();
		Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());

		int A, R, G, B;
		int pixel;

		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				pixel = src.getPixel(x, y);
				A = Color.alpha(pixel);
				R = Color.red(pixel);
				G = Color.green(pixel);
				B = Color.blue(pixel);
				if (type == 1) {
					R = (int) (R * (1 + percent));
					if (R > 255)
						R = 255;
				} else if (type == 2) {
					G = (int) (G * (1 + percent));
					if (G > 255)
						G = 255;
				} else if (type == 3) {
					B = (int) (B * (1 + percent));
					if (B > 255)
						B = 255;
				}
				bmOut.setPixel(x, y, Color.argb(A, R, G, B));
			}
		}
		return bmOut;
	}

	/**
	 * Imagen con bordes redondeados
	 * 
	 * @param src
	 * @param round
	 *            (5,10,...)
	 * @return
	 */
	public static Bitmap applyRoundCorner(Bitmap src, float round) {
		// image size
		int width = src.getWidth();
		int height = src.getHeight();
		// create bitmap output
		Bitmap result = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		// set canvas for painting
		Canvas canvas = new Canvas(result);
		canvas.drawARGB(0, 0, 0, 0);

		// config paint
		final Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);

		// config rectangle for embedding
		final Rect rect = new Rect(0, 0, width, height);
		final RectF rectF = new RectF(rect);

		// draw rect to canvas
		canvas.drawRoundRect(rectF, round, round, paint);

		// create Xfer mode
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		// draw source image to canvas
		canvas.drawBitmap(src, rect, rect, paint);

		// return final image
		return result;
	}

	/**
	 * Imagen con una marca de agua
	 * 
	 * @param src
	 * @param watermark
	 *            (Texto de la marca)
	 * @param location
	 *            (Localizacion)
	 * @param color
	 * @param alpha
	 * @param size
	 * @param underline
	 * @return
	 */
	public static Bitmap applyMark(Bitmap src, String watermark,
			Point location, int color, int alpha, int size, boolean underline) {
		int w = src.getWidth();
		int h = src.getHeight();
		Bitmap result = Bitmap.createBitmap(w, h, src.getConfig());

		Canvas canvas = new Canvas(result);
		canvas.drawBitmap(src, 0, 0, null);

		Paint paint = new Paint();
		paint.setColor(color);
		paint.setAlpha(alpha);
		paint.setTextSize(size);
		paint.setAntiAlias(true);
		paint.setUnderlineText(underline);
		canvas.drawText(watermark, location.x, location.y, paint);

		return result;
	}

	// type definition
	public static final int FLIP_VERTICAL = 1;
	public static final int FLIP_HORIZONTAL = 2;

	/**
	 * Imagen en modo espejo (vertical horizontal)
	 * 
	 * @param src
	 * @param type
	 * @return
	 */
	public static Bitmap applyFlip(Bitmap src, int type) {
		// create new matrix for transformation
		Matrix matrix = new Matrix();
		// if vertical
		if (type == FLIP_VERTICAL) {
			// y = y * -1
			matrix.preScale(1.0f, -1.0f);
		}
		// if horizonal
		else if (type == FLIP_HORIZONTAL) {
			// x = x * -1
			matrix.preScale(-1.0f, 1.0f);
			// unknown type
		} else {
			return null;
		}

		// return transformed image
		return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(),
				matrix, true);
	}

	/**
	 * Imagen con los colores reemplazados
	 * 
	 * @param bitmap
	 * @param fromColor
	 * @param targetColor
	 * @return
	 */
	public static Bitmap applyReplaceColor(Bitmap bitmap, int fromColor,
			int targetColor) {
		if (bitmap == null) {
			return null;
		}

		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		int[] pixels = new int[width * height];
		bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

		for (int x = 0; x < pixels.length; ++x) {
			pixels[x] = (pixels[x] == fromColor) ? targetColor : pixels[x];
		}

		Bitmap newImage = Bitmap
				.createBitmap(width, height, bitmap.getConfig());
		newImage.setPixels(pixels, 0, width, 0, 0, width, height);

		return newImage;
	}

	private static final double PI = 3.14159d;
	private static final double FULL_CIRCLE_DEGREE = 360d;
	private static final double HALF_CIRCLE_DEGREE = 180d;
	private static final double RANGE = 256d;

	/**
	 * Imagen tintada
	 * 
	 * @param src
	 * @param degree
	 * @return
	 */
	public static Bitmap applyTintImage(Bitmap src, int degree) {

		int width = src.getWidth();
		int height = src.getHeight();

		int[] pix = new int[width * height];
		src.getPixels(pix, 0, width, 0, 0, width, height);

		int RY, GY, BY, RYY, GYY, BYY, R, G, B, Y;
		double angle = (PI * (double) degree) / HALF_CIRCLE_DEGREE;

		int S = (int) (RANGE * Math.sin(angle));
		int C = (int) (RANGE * Math.cos(angle));

		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++) {
				int index = y * width + x;
				int r = (pix[index] >> 16) & 0xff;
				int g = (pix[index] >> 8) & 0xff;
				int b = pix[index] & 0xff;
				RY = (70 * r - 59 * g - 11 * b) / 100;
				GY = (-30 * r + 41 * g - 11 * b) / 100;
				BY = (-30 * r - 59 * g + 89 * b) / 100;
				Y = (30 * r + 59 * g + 11 * b) / 100;
				RYY = (S * BY + C * RY) / 256;
				BYY = (C * BY - S * RY) / 256;
				GYY = (-51 * RYY - 19 * BYY) / 100;
				R = Y + RYY;
				R = (R < 0) ? 0 : ((R > 255) ? 255 : R);
				G = Y + GYY;
				G = (G < 0) ? 0 : ((G > 255) ? 255 : G);
				B = Y + BYY;
				B = (B < 0) ? 0 : ((B > 255) ? 255 : B);
				pix[index] = 0xff000000 | (R << 16) | (G << 8) | B;
			}

		Bitmap outBitmap = Bitmap.createBitmap(width, height, src.getConfig());
		outBitmap.setPixels(pix, 0, width, 0, 0, width, height);

		pix = null;

		return outBitmap;
	}

	private static final int COLOR_MIN = 0x00;
	private static final int COLOR_MAX = 0xFF;

	/**
	 * Imagen con efecto granulado
	 * 
	 * @param source
	 * @return
	 */
	public static Bitmap applyFleaEffect(Bitmap source) {
		// get image size
		int width = source.getWidth();
		int height = source.getHeight();
		int[] pixels = new int[width * height];
		// get pixel array from source
		source.getPixels(pixels, 0, width, 0, 0, width, height);
		// a random object
		Random random = new Random();

		int index = 0;
		// iteration through pixels
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				// get current index in 2D-matrix
				index = y * width + x;
				// get random color
				int randColor = Color.rgb(random.nextInt(COLOR_MAX),
						random.nextInt(COLOR_MAX), random.nextInt(COLOR_MAX));
				// OR
				pixels[index] |= randColor;
			}
		}
		// output bitmap
		Bitmap bmOut = Bitmap.createBitmap(width, height, source.getConfig());
		bmOut.setPixels(pixels, 0, width, 0, 0, width, height);
		return bmOut;
	}

	/**
	 * Imagen con efecto granulado negro
	 * 
	 * @param source
	 * @return
	 */
	public static Bitmap applyBlackFilter(Bitmap source) {
		// get image size
		int width = source.getWidth();
		int height = source.getHeight();
		int[] pixels = new int[width * height];
		// get pixel array from source
		source.getPixels(pixels, 0, width, 0, 0, width, height);
		// random object
		Random random = new Random();

		int R, G, B, index = 0, thresHold = 0;
		// iteration through pixels
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				// get current index in 2D-matrix
				index = y * width + x;
				// get color
				R = Color.red(pixels[index]);
				G = Color.green(pixels[index]);
				B = Color.blue(pixels[index]);
				// generate threshold
				thresHold = random.nextInt(COLOR_MAX);
				if (R < thresHold && G < thresHold && B < thresHold) {
					pixels[index] = Color.rgb(COLOR_MIN, COLOR_MIN, COLOR_MIN);
				}
			}
		}
		// output bitmap
		Bitmap bmOut = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bmOut.setPixels(pixels, 0, width, 0, 0, width, height);
		return bmOut;
	}

	/**
	 * Imagen con efecto nieve
	 * 
	 * @param source
	 * @return
	 */
	public static Bitmap applySnowEffect(Bitmap source) {
		// get image size
		int width = source.getWidth();
		int height = source.getHeight();
		int[] pixels = new int[width * height];
		// get pixel array from source
		source.getPixels(pixels, 0, width, 0, 0, width, height);
		// random object
		Random random = new Random();

		int R, G, B, index = 0, thresHold = 50;
		// iteration through pixels
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				// get current index in 2D-matrix
				index = y * width + x;
				// get color
				R = Color.red(pixels[index]);
				G = Color.green(pixels[index]);
				B = Color.blue(pixels[index]);
				// generate threshold
				thresHold = random.nextInt(COLOR_MAX);
				if (R > thresHold && G > thresHold && B > thresHold) {
					pixels[index] = Color.rgb(COLOR_MAX, COLOR_MAX, COLOR_MAX);
				}
			}
		}
		// output bitmap
		Bitmap bmOut = Bitmap
				.createBitmap(width, height, Bitmap.Config.RGB_565);
		bmOut.setPixels(pixels, 0, width, 0, 0, width, height);
		return bmOut;
	}

	/**
	 * Imagen con sombreado del color especificado
	 * 
	 * @param source
	 * @param shadingColor
	 * @return
	 */
	public static Bitmap applyShadingFilter(Bitmap source, int shadingColor) {
		// get image size
		int width = source.getWidth();
		int height = source.getHeight();
		int[] pixels = new int[width * height];
		// get pixel array from source
		source.getPixels(pixels, 0, width, 0, 0, width, height);

		int index = 0;
		// iteration through pixels
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				// get current index in 2D-matrix
				index = y * width + x;
				// AND
				pixels[index] &= shadingColor;
			}
		}
		// output bitmap
		Bitmap bmOut = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bmOut.setPixels(pixels, 0, width, 0, 0, width, height);
		return bmOut;
	}

	/**
	 * Imagen con un filtro de saturacion
	 * 
	 * @param source
	 * @param level
	 *            (3,9...)
	 * @return
	 */
	public static Bitmap applySaturationFilter(Bitmap source, int level) {
		// get image size
		int width = source.getWidth();
		int height = source.getHeight();
		int[] pixels = new int[width * height];
		float[] HSV = new float[3];
		// get pixel array from source
		source.getPixels(pixels, 0, width, 0, 0, width, height);

		int index = 0;
		// iteration through pixels
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				// get current index in 2D-matrix
				index = y * width + x;
				// convert to HSV
				Color.colorToHSV(pixels[index], HSV);
				// increase Saturation level
				HSV[1] *= level;
				HSV[1] = (float) Math.max(0.0, Math.min(HSV[1], 1.0));
				// take color back
				pixels[index] |= Color.HSVToColor(HSV);
			}
		}
		// output bitmap
		Bitmap bmOut = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bmOut.setPixels(pixels, 0, width, 0, 0, width, height);
		return bmOut;
	}

	/**
	 * Imagen con un filtro de saturacion mas pronunciado
	 * 
	 * @param source
	 * @param level
	 *            (3,9...)
	 * @return
	 */
	public static Bitmap applyHueFilter(Bitmap source, int level) {
		// get image size
		int width = source.getWidth();
		int height = source.getHeight();
		int[] pixels = new int[width * height];
		float[] HSV = new float[3];
		// get pixel array from source
		source.getPixels(pixels, 0, width, 0, 0, width, height);

		int index = 0;
		// iteration through pixels
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				// get current index in 2D-matrix
				index = y * width + x;
				// convert to HSV
				Color.colorToHSV(pixels[index], HSV);
				// increase Saturation level
				HSV[0] *= level;
				HSV[0] = (float) Math.max(0.0, Math.min(HSV[0], 360.0));
				// take color back
				pixels[index] |= Color.HSVToColor(HSV);
			}
		}
		// output bitmap
		Bitmap bmOut = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bmOut.setPixels(pixels, 0, width, 0, 0, width, height);
		return bmOut;
	}

	/**
	 * Imagen con reflejo
	 * 
	 * @param originalImage
	 * @return
	 */
	public static Bitmap applyReflection(Bitmap originalImage) {
		// gap space between original and reflected
		final int reflectionGap = 4;
		// get image size
		int width = originalImage.getWidth();
		int height = originalImage.getHeight();

		// this will not scale but will flip on the Y axis
		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);

		// create a Bitmap with the flip matrix applied to it.
		// we only want the bottom half of the image
		Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
				height / 2, width, height / 2, matrix, false);

		// create a new bitmap with same width but taller to fit reflection
		Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
				(height + height / 2), Config.ARGB_8888);

		// create a new Canvas with the bitmap that's big enough for
		// the image plus gap plus reflection
		Canvas canvas = new Canvas(bitmapWithReflection);
		// draw in the original image
		canvas.drawBitmap(originalImage, 0, 0, null);
		// draw in the gap
		Paint defaultPaint = new Paint();
		canvas.drawRect(0, height, width, height + reflectionGap, defaultPaint);
		// draw in the reflection
		canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

		// create a shader that is a linear gradient that covers the reflection
		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0,
				originalImage.getHeight(), 0, bitmapWithReflection.getHeight()
						+ reflectionGap, 0x70ffffff, 0x00ffffff, TileMode.CLAMP);
		// set the paint to use this shader (linear gradient)
		paint.setShader(shader);
		// set the Transfer mode to be porter duff and destination in
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		// draw a rectangle using the paint with our linear gradient
		canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
				+ reflectionGap, paint);

		return bitmapWithReflection;
	}

	/**
	 * Le aplica una mascara a un bitmap para hacer transparente todo lo que no
	 * sea negro
	 * 
	 * @param bitmap
	 *            Bitmap al que aplicar la mascara
	 * @return bitmap con la mascara aplicada
	 */
	public static Bitmap applyTransparency(Bitmap bitmap) {

		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		Bitmap result = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		result.eraseColor(Color.BLACK);

		Canvas c = new Canvas(result);

		Bitmap alpha = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		int[] alphaPix = new int[width * height];
		bitmap.getPixels(alphaPix, 0, width, 0, 0, width, height);

		int count = width * height;
		for (int i = 0; i < count; ++i) {
			// Usamos el canal rojo como alpha
			alphaPix[i] = alphaPix[i] << 8; // Realizamos desplazamiento a la
											// izquierda con signo
		}

		alpha.setPixels(alphaPix, 0, width, 0, 0, width, height);

		Paint alphaP = new Paint();
		alphaP.setAntiAlias(true);
		alphaP.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));

		c.drawBitmap(alpha, 0, 0, alphaP);

		bitmap.recycle();
		alpha.recycle();

		return result;

	}

}
