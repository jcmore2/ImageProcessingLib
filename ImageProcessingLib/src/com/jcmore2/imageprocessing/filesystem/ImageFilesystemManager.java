package com.jcmore2.imageprocessing.filesystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.util.Log;

import com.jcmore2.imageprocessing.utils.Utils;

/**
 * This class manage filesystem to save to SD, pick from gallery and save in gallery.
 * @author jcmore2 jcmore2@gmail.com
 *
 */
public class ImageFilesystemManager {

	private static int RESULT_LOAD_IMAGE = 1;

	public static void pickImageFromGallery(Activity activity) {
		Intent i = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

		activity.startActivityForResult(i, RESULT_LOAD_IMAGE);

	}
	
	public static void pickImageFromGallery(Fragment fragment) {
		Intent i = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

		fragment.startActivityForResult(i, RESULT_LOAD_IMAGE);

	}

	public static Bitmap onPickImageResult(Activity activity, int requestCode,
			int resultCode, Intent data) {

		Bitmap bitmap = null;
		if (requestCode == RESULT_LOAD_IMAGE
				&& resultCode == Activity.RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = activity.getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();

			bitmap = BitmapFactory.decodeFile(picturePath);

		}
		return bitmap;
	}

	private final static String APP_PATH_SD_CARD = "/App_foo/";
	private final static String APP_THUMBNAIL_PATH_SD_CARD = "foo";

	public static File saveImageToExternalStorage(Context context,
			Bitmap image, String parentPath, String childPath, String filename) {

		String fullPath = "";
		if (parentPath != null && !parentPath.equalsIgnoreCase("")
				&& childPath != null && !childPath.equalsIgnoreCase("")) {
			fullPath = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/" + parentPath + "/" + childPath;
		} else {
			fullPath = Environment.getExternalStorageDirectory()
					.getAbsolutePath()
					+ APP_PATH_SD_CARD
					+ APP_THUMBNAIL_PATH_SD_CARD;
		}

		try {
			File dir = new File(fullPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			if (filename == null || filename.equalsIgnoreCase(""))
				filename = "imagen" + new Date().getTime();

			OutputStream fOut = null;
			File file = new File(fullPath, filename + ".jpg");
			file.createNewFile();
			fOut = new FileOutputStream(file);

			// 100 means no compression, the lower you go, the stronger the
			// compression
			image.compress(Bitmap.CompressFormat.JPEG, 90, fOut);
			fOut.flush();
			fOut.close();

			saveMediaEntry(context, file.getAbsolutePath());;

			return file;

		} catch (Exception e) {
			Log.e("saveToExternalStorage()", e.getMessage());
			return null;
		}
	}

	public static boolean saveImageToInternalStorage(Context context,
			Bitmap image, String filename) {

		if (filename == null || filename.equalsIgnoreCase(""))
			filename = "imagen" + new Date().getTime();
		try {
			// Use the compress method on the Bitmap object to write image to
			// the OutputStream
			FileOutputStream fos = context.openFileOutput(filename + ".jpg",
					Context.MODE_PRIVATE);

			// Writing the bitmap to the output stream
			image.compress(Bitmap.CompressFormat.JPEG, 90, fos);
			fos.close();

			return true;
		} catch (Exception e) {
			Log.e("saveToInternalStorage()", e.getMessage());
			return false;
		}
	}

	public static Bitmap getImageFromExternalStorage(Context context, String parentPath,
			String childPath, String filename) {

		String fullPath = "";
		if (parentPath != null && !parentPath.equalsIgnoreCase("")
				&& childPath != null && !childPath.equalsIgnoreCase("")) {
			fullPath = Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/" + parentPath + "/" + childPath;
		} else {
			fullPath = Environment.getExternalStorageDirectory()
					.getAbsolutePath()
					+ APP_PATH_SD_CARD
					+ APP_THUMBNAIL_PATH_SD_CARD;
		}
		Bitmap thumbnail = null;

		// Look for the file on the external storage
		try {
			if (Utils.isSdReadable() == true) {
				thumbnail = BitmapFactory.decodeFile(fullPath + "/" + filename);
			}
		} catch (Exception e) {
			Log.e("getThumbnail() on external storage", e.getMessage());
		}

		// If no file on external storage, look in internal storage
		if (thumbnail == null) {
			try {
				File filePath = context.getFileStreamPath(filename);
				FileInputStream fi = new FileInputStream(filePath);
				thumbnail = BitmapFactory.decodeStream(fi);
			} catch (Exception ex) {
				Log.e("getThumbnail() on internal storage", ex.getMessage());
			}
		}
		return thumbnail;
	}
	
	
	/**
	 * Copia una imagen a la galeria de fotos
	 * @param imagePath Ruta a la imagen
	 * @return la url creada
	 */
	public static Uri saveMediaEntry(Context context, String imagePath) {
		
		File imageFile = new File(imagePath);
		
	    ContentValues v = new ContentValues();
	    v.put(Images.Media.TITLE,  imageFile.getName());
	    v.put(Images.Media.DISPLAY_NAME, imageFile.getName());
	    v.put(Images.Media.MIME_TYPE, "image/jpeg");

	 
	    File f = new File(imagePath) ;
	    File parent = f.getParentFile() ;
	    String path = parent.toString().toLowerCase() ;
	    String name = parent.getName().toLowerCase() ;
	    v.put(Images.ImageColumns.BUCKET_ID, path.hashCode());
	    v.put(Images.ImageColumns.BUCKET_DISPLAY_NAME, name);
	    v.put(Images.Media.SIZE,f.length()) ;
	    f = null ;

	    v.put("_data",imagePath) ;
	    ContentResolver c = context.getContentResolver() ;
	    Uri uri= null;
	    try{
	    uri = c.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, v);
	    }catch (Exception e) {
			// TODO: handle exception
	    	Log.e("Utils", "Error al guardar la foto " + e.getMessage());
		}
	    return uri;
	}
	

	
	
	public static Uri getFileContentUri(Context context, File imageFile) {
	    String filePath = imageFile.getAbsolutePath();
	    Cursor cursor = context.getContentResolver().query(
	            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
	            new String[] { MediaStore.Video.Media._ID },
	            MediaStore.Video.Media.DATA + "=? ",
	            new String[] { filePath }, null);

	    if (cursor != null && cursor.moveToFirst()) {
	        int id = cursor.getInt(cursor
	                .getColumnIndex(MediaStore.MediaColumns._ID));
	        Uri baseUri = Uri.parse("content://media/external/video/media");
	        return Uri.withAppendedPath(baseUri, "" + id);
	    } else {
	        if (imageFile.exists()) {
	            ContentValues values = new ContentValues();
	            values.put(MediaStore.Video.Media.DATA, filePath);
	            return context.getContentResolver().insert(
	                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values);
	        } else {
	            return null;
	        }
	    }
	}
	
	public static boolean deleteFileContentUri(Context context, Uri uri) {

		if(context.getContentResolver().delete(
	    		uri, null,null)>0){
			return true;
		}else{
			return false;
		}

	}

}
