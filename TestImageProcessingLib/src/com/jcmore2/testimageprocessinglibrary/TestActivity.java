package com.jcmore2.testimageprocessinglibrary;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.jcmore2.imageprocessing.ImageProcessing;
import com.jcmore2.imageprocessing.filesystem.ImageFilesystemManager;
import com.jcmore2.imageprocessing.utils.Utils;
import com.jcmore2.testimageprocessinglibrary.ProcessingAsyncTask.ProcessingListener;

/**
 * 
 * @author jcmore2 jcmore2@gmail.com
 *
 */
public class TestActivity extends Activity {

	Bitmap bmOriginal;


	protected static final int LOADING_DIALOG = 0;
	
	public HorizontalListView listaDibujosFull;
	private ListEffectAdapter gridAdapter = null;
	private ImageView imagen;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		
		imagen = (ImageView) findViewById(R.id.imagen);

		bmOriginal = Utils.getBitmapFromDrawable(imagen.getDrawable());

		
		listaDibujosFull = (HorizontalListView) findViewById(R.id.listaDibujosFull);

		gridAdapter = new ListEffectAdapter(getApplicationContext(),
				bmOriginal);
		listaDibujosFull.setAdapter(gridAdapter);
		listaDibujosFull.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				Bitmap bm = Utils.getBitmapFromDrawable(((ImageView)((ViewGroup)v).getChildAt(1)).getDrawable());
				imagen.setImageBitmap(bm);

				final ProgressDialog pd = new ProgressDialog(getApplicationContext());
				pd.setMessage("Loading...");
				pd.show();
				new ProcessingAsyncTask(getApplicationContext(), bmOriginal, new ProcessingListener() {

					@Override
					public void onFinish(Bitmap bm) {
						// TODO Auto-generated method stub
						pd.dismiss();
						imagen.setImageBitmap(bm);
					}
				}).execute(ImageProcessing.effectList.get(position));
			}
		});

		findViewById(R.id.normal).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				imagen.setImageBitmap(bmOriginal);
			}
		});
		
		findViewById(R.id.file).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ImageFilesystemManager.pickImageFromGallery(TestActivity.this);
			}
		});

		findViewById(R.id.save).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ImageFilesystemManager.saveImageToExternalStorage(
						TestActivity.this,
						Utils.getBitmapFromDrawable(imagen.getDrawable()),
						"TestImageProcessing", "Carpeta", "imagen");

			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		bmOriginal = ImageFilesystemManager.onPickImageResult(this,
				requestCode, resultCode, data);


		imagen.setImageBitmap(bmOriginal);
		
		gridAdapter.updateBitmap(bmOriginal);
		gridAdapter.notifyDataSetChanged();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
		return true;
	}

}
