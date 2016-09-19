/*
   Copyright 2016 Narrative Nights Inc. All Rights Reserved.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.examples.singamnist;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Handwritten digit detector.
 * <p/>
 * Created by miyoshi on 16/01/17.
 */
public class DigitDetector {
	static {
		System.loadLibrary("singa_mnist");
	}

	public native String stringFromJNI();

	public native String init(AssetManager mgr);

	//public native int labelFromJNI();
	public native int labelFromJNI(int[] pixels, String path);

	/**
	 * draw pixels
	 */
	/*
	public native int detectDigit(int[] pixels);
    */
	public boolean setup(Context context, String filename) {

		AssetManager assetManager = context.getAssets();

		InputStream in = null;
		OutputStream out = null;
		try {
			in = assetManager.open(filename);

			String newFileName = "/data/data/" + context.getPackageName() + "/" + filename;
			out = new FileOutputStream(newFileName);

      		byte[] buffer = new byte[1024*4];
			int len;
			while ((len = in.read(buffer)) != -1) {
				//String text = new String(buffer, "UTF-8");
				//Log.v("singa-libs", text);
				out.write(buffer, 0, len);
			}
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;

			return true;

		} catch (Exception e) {
			Log.e("tag", e.getMessage());
			return false;
		}

		/*
		AssetManager assetManager = context.getAssets();

		// model from beginner tutorial
		//int ret = init(assetManager, "file:///android_asset/beginner-graph.pb");

		// model from expert tutorial
		int ret = init(assetManager, "file:///android_asset/expert-graph.pb");

		return ret >= 0;
		*/
	}
}
