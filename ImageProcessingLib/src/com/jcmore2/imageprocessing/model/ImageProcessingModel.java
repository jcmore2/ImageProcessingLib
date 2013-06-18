package com.jcmore2.imageprocessing.model;

import android.graphics.Bitmap;

/**
 * Image Processing Model
 * @author jcmore2 jcmore2@gmail.com
 *
 */
public class ImageProcessingModel {
	
	public int process;
	public String processName;
	public Bitmap processedBitmap;
	
	public ImageProcessingModel(int process, String processName, Bitmap processedBitmap){
		this.process = process;
		this.processName = processName;
		this.processedBitmap = processedBitmap;
	}
	
	public ImageProcessingModel(){

	}
	
	public int getProcess() {
		return process;
	}
	public void setProcess(int process) {
		this.process = process;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public Bitmap getProcessedBitmap() {
		return processedBitmap;
	}
	public void setProcessedBitmap(Bitmap processedBitmap) {
		this.processedBitmap = processedBitmap;
	}

}
