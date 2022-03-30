package com.example.duanmotnhom12.Translate;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.duanmotnhom12.R;

public class TranslateAnimation_nav implements View.OnTouchListener {

    private GestureDetector gestureDetector;

    public TranslateAnimation_nav(Context context, View view) {
        gestureDetector = new GestureDetector(context, new SimlpeGestureDetector(view));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public class SimlpeGestureDetector extends GestureDetector.SimpleOnGestureListener {

        private View view;
        private boolean isfn = true;

        public SimlpeGestureDetector(View view) {
            this.view = view;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (distanceY > 0) {
                hidenView();
            } else {
                showView();
            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        private void hidenView() {
            if (view == null || view.getVisibility() == View.GONE) {
                return;
            }
            Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.view_in);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    view.setVisibility(View.VISIBLE);
                    isfn = false;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(View.GONE);
                    isfn = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            if (isfn) {
                view.startAnimation(animation);
            }
        }

        private void showView() {
            if (view == null || view.getVisibility() == View.VISIBLE) {
                return;
            }
            Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.view_up);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    view.setVisibility(View.VISIBLE);
                    isfn = false;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isfn = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            if (isfn) {
                view.startAnimation(animation);
            }
        }
    }
}
