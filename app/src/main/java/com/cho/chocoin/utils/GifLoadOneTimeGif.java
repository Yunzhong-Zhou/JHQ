package com.cho.chocoin.utils;

import android.content.Context;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by zyz on 2019/6/26.
 * description ：在Glide4.0之后,没法再直接获取GifDecoder对象了,原因是因为GlideDrawable不再提供这个方法了。
 * 我这里是采用反射的方法获取到GifDecoder变量
 * <p>
 * <p>
 * <p>
 * https://github.com/bumptech/glide
 * compile 'com.github.bumptech.glide:glide:4.8.0'
 * annotationProcessor 'com.github.bumptech.glide:compiler:4.8.0'
 * <p>
 * 同时,因为我采用的是反射,所以别忘了在你的proguard-rules.pro中加上Glide的反混淆规则,
 * #Glide
 * -keep class com.bumptech.glide.** {*;}
 */
public class GifLoadOneTimeGif {
    /**
     * Gif 加载 可以设置次数，监听播放完成回调
     *
     * @param context      上下文对象
     * @param resId        传入的gif地址，可以是网络，也可以是本地，（https://raw.githubusercontent.com/Jay-YaoJie/KotlinDialogs/master/diagram/test.gif）
     * @param gifImageView 要显示的imageView
     * @param loopCount    播放次数
     * @param gifListener  Gif播放完毕回调
     */
    public static void loadOneTimeGif(Context context, int resId, final GifImageView gifImageView, final int loopCount, final GifListener gifListener) {
        gifImageView.setImageResource(resId);
        GifDrawable gifDrawable = (GifDrawable) gifImageView.getDrawable();
        gifDrawable.start();
        gifDrawable.setLoopCount(loopCount);
        gifImageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (gifListener != null) {
                    gifListener.gifPlayComplete();
                }
            }
        }, gifDrawable.getDuration());

        /*Glide.with(context)
                .asGif()
                .load(model)
                .listener(new RequestListener<GifDrawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                try {
                    Field gifStateField = GifDrawable.class.getDeclaredField("state");
                    gifStateField.setAccessible(true);
                    Class gifStateClass = Class.forName("com.bumptech.glide.load.resource.gif.GifDrawable$GifState");
                    Field gifFrameLoaderField = gifStateClass.getDeclaredField("frameLoader");
                    gifFrameLoaderField.setAccessible(true);
                    Class gifFrameLoaderClass = Class.forName("com.bumptech.glide.load.resource.gif.GifFrameLoader");
                    Field gifDecoderField = gifFrameLoaderClass.getDeclaredField("gifDecoder");
                    gifDecoderField.setAccessible(true);
                    Class gifDecoderClass = Class.forName("com.bumptech.glide.gifdecoder.GifDecoder");
                    Object gifDecoder = gifDecoderField.get(gifFrameLoaderField.get(gifStateField.get(resource)));
                    Method getDelayMethod = gifDecoderClass.getDeclaredMethod("getDelay", int.class);
                    getDelayMethod.setAccessible(true);
                    ////设置播放次数
                    resource.setLoopCount(loopCount);
                    //获得总帧数
                    int count = resource.getFrameCount();
                    int delay = 4500;
                    for (int i = 0; i < count; i++) {
                        //计算每一帧所需要的时间进行累加
                        delay += (int) getDelayMethod.invoke(gifDecoder, i);
                    }
                    MyLogger.i(">>>>>>>>gif总帧数："+count);
                    MyLogger.i(">>>>>>>>gif图片时间："+delay);
                    imageView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (gifListener != null) {
                                gifListener.gifPlayComplete();
                            }
                        }
                    }, delay);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

                return false;
            }
        }).into(imageView);*/
    }

    /**
     * Gif播放完毕回调
     */
    public interface GifListener {
        void gifPlayComplete();
    }


}
