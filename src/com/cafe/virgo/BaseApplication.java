package com.cafe.virgo;

import java.util.concurrent.TimeUnit;

import android.app.Application;
import android.graphics.Bitmap;

import com.cafe.virgo.enity.ScrnSize;
import com.cafe.virgo.util.DisplayUtil;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.squareup.okhttp.OkHttpClient;

/**
 * @author cafe_dou
 */

public class BaseApplication extends Application {

	private static BaseApplication instance;
	public static OkHttpClient client;//okhttp网络访问请求
	public ScrnSize scrn;
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		scrn = DisplayUtil.getScreenWidth(instance);
		initImageLoder();
	}
	
	public static BaseApplication getInstence(){
		return instance;
	}
	public static OkHttpClient getOkHttpClient() {
		if (client != null) {
			return client;
		}else{
			try {
				client = new OkHttpClient();
				client.setConnectTimeout(10, TimeUnit.SECONDS);//超时10s
				client.setReadTimeout(10, TimeUnit.SECONDS);
				client.setWriteTimeout(10, TimeUnit.SECONDS);
				return client;
			} catch (Exception e) {
				throw new IllegalStateException("client not initialized");
			}
		}
	}
	private void initImageLoder(){
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.imageScaleType(ImageScaleType.IN_SAMPLE_INT) 
		.bitmapConfig(Bitmap.Config.RGB_565)// 防止内存溢出的，图片太多就这这个。还有其他设置
		//		.showImageOnLoading(R.drawable.ic_launcher)   //加载中
		//		.showImageForEmptyUri(R.drawable.kedou)       //加载为空
		//		.showImageOnFail(R.drawable.k2k2k2k)          //加载失败
		.displayer(new RoundedBitmapDisplayer(0))	  //圆角图片弧度为5
		.build();
		@SuppressWarnings("deprecation")
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
		.memoryCacheExtraOptions(scrn.ScrnW_px, scrn.ScrnH_px)// 缓存在内存的图片的宽和高度--等同屏幕
		.diskCacheExtraOptions(scrn.ScrnW_px, scrn.ScrnH_px, null)
		.memoryCache(new WeakMemoryCache()) 
		.memoryCacheSize(2 * 1024 * 1024) //缓存到内存的最大数据
		.discCacheSize(50 * 1024 * 1024)  //缓存到文件的最大数据
		.discCacheFileCount(1000)            //文件数量
		.threadPriority(Thread.NORM_PRIORITY - 2)
		.denyCacheImageMultipleSizesInMemory()
		.discCacheFileNameGenerator(new Md5FileNameGenerator())
		.tasksProcessingOrder(QueueProcessingType.LIFO)
		.defaultDisplayImageOptions(defaultOptions)
		.build(); 
		ImageLoader.getInstance().init(config);
	}
}
