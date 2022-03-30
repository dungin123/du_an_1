package com.example.duanmotnhom12.Translate;

import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class AnimationUlti {
    public static final int ANIMATION_DU = 1000;
    public static boolean isAnimationStart;

    public static void transalateAnimation(final ImageView imageView, final View viewStart, View viewEnd,
                                           final Animation.AnimationListener animationListener) {

        viewStart.setDrawingCacheEnabled(true);
        Bitmap bitmap = viewStart.getDrawingCache();
        if (bitmap == null) {
            return;
        }
        Bitmap bitmap_ = Bitmap.createBitmap(bitmap);
        viewStart.setDrawingCacheEnabled(false);

        imageView.setImageBitmap(bitmap_);

        float startViewWidthCenter = viewStart.getWidth() / 7;
        float startViewHeightCenter = viewStart.getHeight() / 2;

        float endViewWidthCenter = viewStart.getWidth() * 0.40f;
        float endViewHeightCenter = viewStart.getHeight() / 2f;

        final int[] startPos = new int[2];
        viewStart.getLocationOnScreen(startPos);

        final int[] endPos = new int[2];
        viewEnd.getLocationOnScreen(endPos);

        float fromX = startPos[0];
        float toX = endPos[0] + endViewWidthCenter - startViewWidthCenter;
        float fromY = startPos[1]-startViewHeightCenter ;
        float toY = endPos[1] - endViewHeightCenter + startViewHeightCenter;


        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setInterpolator(new AccelerateInterpolator());

        int animationDru = 200;

        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.5f, 1.0f,
                1.5f, startViewWidthCenter, startViewHeightCenter);
        scaleAnimation.setDuration(animationDru);

        //fromX 1.0f , fromY 1.0f (Zoom động rộng của icon)
        //ToX 1.5f , ToY 1.0f (Zoom động rộng của icon)

        TranslateAnimation translateAnimation = new TranslateAnimation(fromX, toX, fromY, toY);
        translateAnimation.setStartOffset(animationDru);
        translateAnimation.setDuration(ANIMATION_DU);

        ScaleAnimation scaleAnimation_ = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, toX, toY);
        scaleAnimation_.setStartOffset(animationDru);
        scaleAnimation_.setDuration(ANIMATION_DU);

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(scaleAnimation_);

        if (isAnimationStart) {
            imageView.clearAnimation();
            if (animationListener != null) {
                animationListener.onAnimationEnd(null);
            }
        }
        imageView.startAnimation(animationSet);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isAnimationStart = true;
                imageView.setVisibility(View.VISIBLE);
                viewStart.setVisibility(View.INVISIBLE);
                if (animationListener != null) {
                    animationListener.onAnimationStart(animation);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.setVisibility(View.GONE);
                viewStart.setVisibility(View.VISIBLE);
                if (animationListener != null) {
                    animationListener.onAnimationEnd(animation);
                }
                isAnimationStart = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                if (animationListener != null) {
                    animationListener.onAnimationRepeat(animation);
                }
            }
        });
    }

}
