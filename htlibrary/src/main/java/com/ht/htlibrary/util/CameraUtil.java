package com.ht.htlibrary.util;

import android.hardware.Camera;
import android.util.Log;

/**
 * Created by Administrator on 2017/8/24 0024.
 */

public class CameraUtil {
	/**
	 * 返回前置Camera对象
	 * @param camIsOpen 之前是否打开了Camera
	 * @return
	 */
	public static Camera openFrontFacingCameraGingerbread(boolean camIsOpen){
		int cameraCount;
		Camera cam = null;

		Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
		cameraCount = Camera.getNumberOfCameras();

		for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
			Camera.getCameraInfo(camIdx, cameraInfo);
			if (!camIsOpen) {
				try {
					cam = Camera.open(camIdx);
					camIsOpen = true;
				} catch (RuntimeException e) {
					Log.e("", "Camera failed to open: " + e.getLocalizedMessage());
				}
			}
		}
		if (cam == null) {
			cam = Camera.open(Camera.getNumberOfCameras() - 1);
		}
		return cam;
	}
}
