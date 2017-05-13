package com.example.android.restful.utils;

/** * Created by Paulo Vila Nova on 2017-05-13.
 *
 * context.getCacheDir();
 * It's looking for the image in the cache directory. This is a directory that's managed automatically
 * by the Android framework. As long as you have available space on your device, images stored there
 * will be saved. But if the device gets low on storage, it can eliminate those images as needed.
 * Once I have the location of the file, it's a simple matter to go get the related bitmap image.
 * If the file exists, I read it into memory using bitmap factory dot decode stream, and return
 * it from this method. Otherwise down here, I return null.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.android.restful.model.DataItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageCacheManager {

    public static Bitmap getBitmap(Context context, DataItem dataItem) {

        String fileName = context.getCacheDir() + "/" + dataItem.getImage();
        File file = new File(fileName);
        if (file.exists()) {
            try {
                return BitmapFactory.decodeStream(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    public static void putBitmap(Context context, DataItem dataItem, Bitmap bitmap) {
        String fileName = context.getCacheDir() + "/" + dataItem.getImage();
        File file = new File(fileName);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}