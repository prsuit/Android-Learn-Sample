package com.prsuit.androidlearnsample.algorithm;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

/**
 * @Description:
 * @Author: sh
 * @Date: 2021/12/5
 */
public class LruUse {

    //并没有一个指定的缓存大小可以满足所有的应用程序，分析程序内存的使用情况。
    //一个太小的缓存空间，有可能造成图片频繁地被释放和重新加载，这并没有好处。
    // 而一个太大的缓存空间，则有可能还是会引起 java.lang.OutOfMemory 的异常。
    LruCache<String, Bitmap> mMemoryCache;
   // 当LruCache中存储图片的总大小达到容量上限的时候，会自动把最近最少使用的图片从缓存中移除。
    public void onCreate() {
        //获取可用内存最大值
        int maxMemory = (int) (Runtime.getRuntime().maxMemory());
        //使用最大可用内存的1/8作为缓存大小
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                //重写此方法返回每个条目大小,即每张图片的大小
                return bitmap.getByteCount();
            }
        };
    }

    public void addBitmapToCache(String key, Bitmap bitmap) {
        if (getBitmapFromCache(key) == null)
            mMemoryCache.put(key, bitmap);
    }

    public Bitmap getBitmapFromCache(String key) {
        return mMemoryCache.get(key);
    }

    //BitmapFactory这个类提供了多个解析方法(
    // decodeByteArray,decodeStream, decodeFile, decodeResource等)用于创建Bitmap对象
    // 提供了一个可选的BitmapFactory.Options参数，
    // 将这个参数的inJustDecodeBounds属性设置为true就可以让解析方法禁止为bitmap分配内存，
    // 返回值也不再是一个Bitmap对象，而是null。虽然Bitmap是null了，
    // 但是BitmapFactory.Options的outWidth、outHeight和outMimeType属性都会被赋值。
    // 这个技巧让我们可以在加载图片之前就获取到图片的长宽值和MIME类型，从而根据情况对图片进行压缩。

    public static Bitmap decodeSampleBitmap(String pathName, int reqWidth, int reqHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        //第一次解析inJustDecodeBounds设置true来获取图片大小，禁止为bitmap分配内存，
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName,options);
        //计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options,reqWidth,reqHeight);
        //使用获取到的inSampleSize再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(pathName,options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int inSampleSize = 1;
        //图片原宽高
        int width = options.outWidth;
        int height = options.outHeight;
        if (height> reqHeight || width > reqWidth){
            //计算原宽高和目标宽/高的比率
            int heightRatio = Math.round((float) height / (float) reqHeight);//小数点进行四舍五入
            int widthRatio = Math.round((float) width / (float) reqWidth);
            //取其中最小的比率，保证最终图片的宽高一定大于等于目标的宽高
            inSampleSize = Math.min(heightRatio, widthRatio);
        }
        return inSampleSize;
    }

    public static void main(String[] args) {
        decodeSampleBitmap("filePath",100,100);
    }


}
