package com.jcmore2.testimageprocessinglibrary;

import java.util.ArrayList;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jcmore2.imageprocessing.ImageProcessing;
import com.jcmore2.imageprocessing.model.ImageProcessingModel;
import com.jcmore2.testimageprocessinglibrary.ProcessingAsyncTask.ProcessingListener;

/**
 * 
 * @author jcmore2 jcmore2@gmail.com
 *
 */
public class ListEffectAdapter extends BaseAdapter {

	private Context context;

	private Bitmap bitmap;
	public ViewHolder mHolder;
	public View mConvertView;

	private ArrayList<ImageProcessingModel> listaEfectos;
	private ProcessingAsyncTask processingAsyncTask;

	public ListEffectAdapter(Context context, Bitmap bitmap) {
		super();
		this.context = context;
		this.bitmap = bitmap;
		listaEfectos = ImageProcessing.imageProcessedList;
	}

	public int getCount() {
		return listaEfectos.size();
	}

	public Object getItem(int position) {
		return listaEfectos.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	static class ViewHolder {

		ImageView imageViewEffect;
		TextView textViewEffect;
		ProgressBar pb;
	}

	public void cancel() {

		processingAsyncTask.cancel(true);
	}

	public int getItemViewType(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;

		final ImageProcessingModel imagenProcesada = listaEfectos.get(position);

		if (convertView == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(R.layout.effect_view, null);

			convertView.setDrawingCacheEnabled(true);

			holder = new ViewHolder();
			holder.pb = (ProgressBar) convertView.findViewById(R.id.progBar);
			holder.imageViewEffect = (ImageView) convertView
					.findViewById(R.id.image_effect);
			holder.textViewEffect = (TextView) convertView
					.findViewById(R.id.name_effect);
			
			convertView.setTag(holder);
			mConvertView = convertView;
		} else {
			convertView.setDrawingCacheEnabled(true);
			holder = (ViewHolder) convertView.getTag();
		}


		holder.imageViewEffect.setImageBitmap(null);
		holder.textViewEffect.setText(imagenProcesada.getProcessName());

		Bitmap bitmapScaled = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
		
		processingAsyncTask = new ProcessingAsyncTask(context, bitmapScaled, new ProcessingListener() {

				@Override
				public void onFinish(Bitmap bm) {
					// TODO Auto-generated method stub
					imagenProcesada.setProcessedBitmap(bm);
					holder.pb.setVisibility(View.GONE);
					holder.imageViewEffect.setImageBitmap(bm);
				}
			});
		processingAsyncTask.execute(imagenProcesada.getProcess());

		mHolder = holder;

		return convertView;
	}

	public int getViewTypeCount() {
		return 1;
	}

	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public void registerDataSetObserver(DataSetObserver arg0) {
		// TODO Auto-generated method stub

	}

	public void unregisterDataSetObserver(DataSetObserver arg0) {
		// TODO Auto-generated method stub

	}

	public boolean areAllItemsEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isEnabled(int arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	public void updateBitmap(Bitmap bm) {

		this.bitmap = bm;
	}

}
