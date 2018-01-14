package com.bwei.wsq.wsqusercenter.app;

import android.app.Application;
import android.graphics.Bitmap;

import com.bwei.wsq.wsqusercenter.R;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/**
 * Created by WSQ on 2018/1/13 0013.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        //配置磁盘缓存
        DiskCacheConfig diskSmallCacheConfig = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryPath(this.getCacheDir())//缓存图片基路径
                .setBaseDirectoryName(getString(R.string.app_name))//文件夹名
                .build();
        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(this)
                .setBitmapsConfig(Bitmap.Config.RGB_565)
                .setSmallImageDiskCacheConfig(diskSmallCacheConfig)
                .build();
    }
}
