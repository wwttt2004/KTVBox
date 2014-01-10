package com.yiqiding.ktvbox.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * BitmapUtils.java
 * com.yiqiding.ktvbox.utils
 * <p/>
 * Created by culm on 13-12-2.
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public class BitmapUtils {

    public static String bitmaptoString(Bitmap bitmap) {
        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }

    public static Bitmap compressImage(Bitmap image, int percent) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, percent, baos);
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
            options -= 10;
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        return bitmap;
    }


    public static boolean saveBitmap2file(Bitmap bmp, String filepath) {
        Bitmap.CompressFormat format = Bitmap.CompressFormat.PNG;
        Log.i("culm", "start save");
        int quality = 100;
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(filepath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bmp.compress(format, quality, stream);
    }

    public static Bitmap getimage(String srcPath, int width, int height) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = height;
        float ww = width;
        int be = 1;
        newOpts.inSampleSize = be;
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap, 50);
    }

    /**
     * bitmap draw text
     */
    public static Bitmap drawTextAtBitmap(Bitmap bitmap, String text) {

        int x = bitmap.getWidth();
        int y = bitmap.getHeight();

        Bitmap newbit = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(newbit);

        Paint paint = new Paint();

        canvas.drawBitmap(bitmap, 0, 0, paint);

        paint.setTextSize(20.0f);

        paint.setColor(Color.parseColor("#000000"));

        paint.setAntiAlias(true);

        paint.setStrokeWidth(10.0f);

        int x_t = 10;
        if (text.length() <= 3) {
            x_t = 16;
        }

        canvas.drawText(text, x_t, 30, paint);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();

        return newbit;
    }

    public static Bitmap stringtoBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public static Bitmap toConformBitmap(Bitmap background, Bitmap foreground) {
        if (background == null) {
            return null;
        }

        int bgWidth = background.getWidth();
        int bgHeight = background.getHeight();

        Bitmap newbmp = Bitmap.createBitmap(bgWidth, bgHeight, Bitmap.Config.ARGB_8888);
        Canvas cv = new Canvas(newbmp);
        cv.drawBitmap(background, 0, 0, null);
        cv.drawBitmap(foreground, 0, 0, null);
        cv.save(Canvas.ALL_SAVE_FLAG);
        cv.restore();
        return newbmp;
    }

    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    public static Bitmap createReflectedImage(View view) {
        // 图片与倒影间隔距离
        final int reflectionGap = 4;

        Bitmap originalBitmap = convertViewToBitmap(view);

        // 图片的宽度
        int width = originalBitmap.getWidth();
        // 图片的高度
        int height = originalBitmap.getHeight();

        Matrix matrix = new Matrix();
        // 图片缩放，x轴变为原来的1倍，y轴为-1倍,实现图片的反转
        matrix.preScale(1, -1);
        // 创建反转后的图片Bitmap对象，图片高是原图的一半。
        Bitmap reflectionBitmap = Bitmap.createBitmap(originalBitmap, 0,
                height / 2, width, height / 2, matrix, false);
        // 创建标准的Bitmap对象，宽和原图一致，高是原图的1.5倍。
        Bitmap withReflectionBitmap = Bitmap.createBitmap(width, (height
                + height / 2 + reflectionGap), Bitmap.Config.ARGB_8888);

        // 构造函数传入Bitmap对象，为了在图片上画图
        Canvas canvas = new Canvas(withReflectionBitmap);
        // 画倒影图片
        canvas.drawBitmap(reflectionBitmap, 0, 0, null);

        // 实现倒影效果
        Paint paint = new Paint();

        LinearGradient shader = new LinearGradient(0, originalBitmap.getHeight(),
                0, withReflectionBitmap.getHeight(), 0x70ffffff, 0x00ffffff,
                Shader.TileMode.MIRROR);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        // 覆盖效果
        canvas.drawRect(0, height, width, withReflectionBitmap.getHeight(), paint);

        return withReflectionBitmap;
    }

    public static Bitmap createReflectedImage(Bitmap originalBitmap) {
        // 图片与倒影间隔距离
        final int reflectionGap = 300;

        // 图片的宽度
        int width = originalBitmap.getWidth();
        // 图片的高度
        int height = originalBitmap.getHeight();

        Matrix matrix = new Matrix();
        // 图片缩放，x轴变为原来的1倍，y轴为-1倍,实现图片的反转
        matrix.preScale(1, -1);
        // 创建反转后的图片Bitmap对象，图片高是原图的一半。
        Bitmap reflectionBitmap = Bitmap.createBitmap(originalBitmap, 0,
                height / 2, width, height / 2, matrix, false);
        // 创建标准的Bitmap对象，宽和原图一致，高是原图的1.5倍。
        Bitmap withReflectionBitmap = Bitmap.createBitmap(width, (height
                + height / 2 + reflectionGap), Bitmap.Config.ARGB_8888);

        // 构造函数传入Bitmap对象，为了在图片上画图
        Canvas canvas = new Canvas(withReflectionBitmap);
        // 画原始图片
        // 画间隔矩形
        Paint defaultPaint = new Paint();
        defaultPaint.setColor(Color.TRANSPARENT);
        canvas.drawRect(0, height, width, height + reflectionGap, defaultPaint);

        // 画倒影图片
        canvas.drawBitmap(reflectionBitmap, 0, height + reflectionGap, null);

        // 实现倒影效果
        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, originalBitmap.getHeight(),
                0, withReflectionBitmap.getHeight(), 0xffffffff, 0x00ffffff,
                Shader.TileMode.MIRROR);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        // 覆盖效果
        canvas.drawRect(0, height, width, withReflectionBitmap.getHeight(), paint);

        return withReflectionBitmap;
    }


    public static Bitmap setAlpha(Bitmap sourceImg, int number) {
        int[] argb = new int[sourceImg.getWidth() * sourceImg.getHeight()];
        sourceImg.getPixels(argb, 0, sourceImg.getWidth(), 0, 0, sourceImg.getWidth(), sourceImg.getHeight());
        number = number * 255 / 100;
        double round = (double) number / (double) (argb.length);
        System.out.println(round + "  l=" + argb.length + " n=" + number);
        for (int i = 0; i < argb.length; i++) {
            if (number - i * round > 10) {
                argb[i] = ((int) (number - i * round) << 24) | (argb[i] & 0x00FFFFFF);
                continue;
            } else {
                argb[i] = (10 << 24) | (argb[i] & 0x00FFFFFF);
                continue;
            }

        }
        sourceImg = Bitmap.createBitmap(argb, sourceImg.getWidth(), sourceImg.getHeight(), Bitmap.Config.ARGB_8888);

        return sourceImg;
    }

    public static Bitmap createNewReflectedImage(View view) throws Exception {

        //originalImage=Bitmap.createBitmap(originalImage,0,originalImage.getHeight()/2,originalImage.getWidth(),originalImage.getHeight()/2);

        //Define a bitmap with the same size as the view
        Bitmap originalImage = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(originalImage);
        //Get the view's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.TRANSPARENT);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        //The gap we want between the reflection and the original image
        final int reflectionGap = 50;

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        int y= (int) (height*0.5);
        //This will not scale but will flip on the Y axis
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        //Create a Bitmap with the flip matrix applied to it.
        //We only want the bottom half of the image


        Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0, y, width, height-y, matrix, false);
        if (!originalImage.isRecycled())
            originalImage.recycle();

        //Create a new bitmap with same width but taller to fit reflection
        Bitmap bitmapWithReflection = Bitmap.createBitmap(width
                , (height + height / 2), Bitmap.Config.ARGB_8888);

        //Create a new Canvas with the bitmap that's big enough for
        //the image plus gap plus reflection
        canvas = new Canvas(bitmapWithReflection);
        //Draw in the original image
        //Draw in the gap
        Paint defaultPaint = new Paint();
        defaultPaint.setColor(Color.TRANSPARENT);
        canvas.drawRect(0, height, width, height + reflectionGap, defaultPaint);
        //Draw in the reflection
        canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

        //Create a shader that is a linear gradient that covers the reflection
        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, originalImage.getHeight(), 0,
                bitmapWithReflection.getHeight() + reflectionGap, 0xffffffff, 0x00ffffff,
                Shader.TileMode.CLAMP);
        //Set the paint to use this shader (linear gradient)
        paint.setShader(shader);
        //Set the Transfer mode to be porter duff and destination in
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        //Draw a rectangle using the paint with our linear gradient
        canvas.drawRect(0, height, width,
                bitmapWithReflection.getHeight() + reflectionGap, paint);

        return bitmapWithReflection;
    }

    public static Bitmap setShadow(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);
        Bitmap shadowImage = Bitmap.createBitmap(bitmap, 0, (int) (height * 0.6),
                width, (int) (height * 0.4), matrix, false);
        return shadowImage;
    }

    public static Bitmap setShadow(Bitmap bitmap, int curr_width) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);
        Bitmap shadowImage = Bitmap.createBitmap(bitmap, 0, (int) (height * 0.6),
                curr_width, (int) (height * 0.4), matrix, false);
        return shadowImage;
    }

    private Bitmap getViewBitmap(View view) {
        view.clearFocus();
        view.setPressed(false);

        boolean willNotCache = view.willNotCacheDrawing();
        view.setWillNotCacheDrawing(false);

        int color = view.getDrawingCacheBackgroundColor();
        view.setDrawingCacheBackgroundColor(0);

        if (color != 0) {
            view.destroyDrawingCache();
        }
        view.buildDrawingCache();
        Bitmap cacheBitmap = view.getDrawingCache();
        if (cacheBitmap == null) {
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

        view.destroyDrawingCache();
        view.setWillNotCacheDrawing(willNotCache);
        view.setDrawingCacheBackgroundColor(color);

        return bitmap;
    }

    public static Bitmap createBitmapReflectFromView(View v) {

        Bitmap originalImage = convertViewToBitmap(v);
        int reflectionGap = 5;
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        //这个matrix的作用只是翻转原图，给左倒影用
        Matrix matrix = new Matrix();//新建一个转换矩阵
        matrix.preScale(1, -1);    // 图片矩阵变换（从低部向顶部的倒影）  ，-1表示反方向
        // 截取原图下半部分
        Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0, height / 2, width, height / 2, matrix, false);
        // 创建倒影图片（高度为原图1/6）
        Bitmap bitmapWithReflection = Bitmap.createBitmap(width, 50, Bitmap.Config.ARGB_8888);
        // 绘制倒影图（原图 + 间距 + 倒影）  注意这里只是创建一个用来装倒影图的画布
        Canvas canvas = new Canvas(bitmapWithReflection);
        // 绘制原图        //这画笔的作用是绘制原图和倒影之间的间隔矩形
        Paint paint = new Paint();
        // 绘制倒影图
        canvas.drawBitmap(reflectionImage, 0, reflectionGap, null);

        //这支画笔的作用是再已有图上绘制阴影效果
        paint = new Paint();
        //阴影效果
        LinearGradient shader = new LinearGradient(0, originalImage.getHeight(), 0, bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff, 0x00ffffff, Shader.TileMode.CLAMP);
        paint.setShader(shader);// 线性渐变效果
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));// 倒影遮罩效果
        // 绘制倒影的阴影效果 ,如果没这句的话，效果是倒影出原图的一半，而没有阴影效果
        canvas.drawRect(0, height, width, bitmapWithReflection.getHeight() + reflectionGap, paint);

        return bitmapWithReflection;
    }

    public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.TRANSPARENT);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    public static Bitmap getBitmapFromView(View view, int width, int height) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }
}
