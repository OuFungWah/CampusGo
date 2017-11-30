package com.example.fungwah.campusgo.common;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.design.widget.FloatingActionButton;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fungwah.campusgo.R;

/**
 * Created by FungWah on 2017/10/24.
 */

public class FabAnimHelper implements View.OnTouchListener {

    private static final long ANIM_TIME = 300L;
    private boolean running = false;

    private FloatingActionButton addFAB;
    private RelativeLayout coverRl;
    private FloatingActionButton activityAddFAB;
    private FloatingActionButton courseAddFAB;
    private TextView courseTv;
    private TextView activityTv;

    private ObjectAnimator activityFabTranslationAppearAnim;
    private ObjectAnimator courseFabTranslationAppearAnim;
    private ObjectAnimator activityFabTranslationDisappearAnim;
    private ObjectAnimator courseFabTranslationDisappearAnim;

    private ObjectAnimator activityFabAlphaAppearAnim;
    private ObjectAnimator activityFabAlphaDisappearAnim;
    private ObjectAnimator courseFabAlphaAppearAnim;
    private ObjectAnimator courseFabAlphaDisappearAnim;

    private ObjectAnimator coverRlAlphaAppearAnim;
    private ObjectAnimator coverRlAlphaDisappearAnim;

    private ObjectAnimator addFabRotateRight;
    private ObjectAnimator addFabRotateLeft;

    private ObjectAnimator activityTvAppearAlphaAnim;
    private ObjectAnimator activityTvDisappearAlphaAnim;
    private ObjectAnimator courseTvAppearAlphaAnim;
    private ObjectAnimator courseTvDisappearAlphaAnim;

    /**
     * 构造函数
     *
     * @param parent
     */
    public FabAnimHelper(View parent) {
        this(parent, ANIM_TIME);
    }

    /**
     * 构造函数
     *
     * @param parent
     * @param animTime
     */
    public FabAnimHelper(View parent, long animTime) {
        coverRl = parent.findViewById(R.id.cover_rl);
        activityAddFAB = parent.findViewById(R.id.add_activity_fab);
        courseAddFAB = parent.findViewById(R.id.add_course_fab);
        activityTv = parent.findViewById(R.id.activity_fab_tv);
        courseTv = parent.findViewById(R.id.course_fab_tv);
        addFAB = parent.findViewById(R.id.add_fab);
        //初始只有一个FAB在外面可见
        inVisiableFab();
        //初始化各个动画效果
        initAnimator(animTime);
    }

    public void initFabClickListener(View.OnClickListener listener) {
        if (listener != null) {
            addFAB.setOnClickListener(listener);
            activityAddFAB.setOnClickListener(listener);
            courseAddFAB.setOnClickListener(listener);
        }
    }

    /**
     * 初始化动画
     */
    protected void initAnimator(long animTime) {
        activityFabTranslationAppearAnim = ObjectAnimator.ofFloat(activityAddFAB, "translationY", 40, 0).setDuration(animTime);
        activityFabTranslationDisappearAnim = ObjectAnimator.ofFloat(activityAddFAB, "translationY", 0, 40).setDuration(animTime);
        courseFabTranslationAppearAnim = ObjectAnimator.ofFloat(courseAddFAB, "translationY", 80, 0).setDuration(animTime);
        courseFabTranslationDisappearAnim = ObjectAnimator.ofFloat(courseAddFAB, "translationY", 0, 80).setDuration(animTime);
        addFabRotateRight = ObjectAnimator.ofFloat(addFAB, "rotation", 0, 45).setDuration(animTime);
        addFabRotateLeft = ObjectAnimator.ofFloat(addFAB, "rotation", 45, 0).setDuration(animTime);
        activityFabAlphaAppearAnim = ObjectAnimator.ofFloat(activityAddFAB, "alpha", 0, 1).setDuration(animTime);
        activityFabAlphaDisappearAnim = ObjectAnimator.ofFloat(activityAddFAB, "alpha", 1, 0).setDuration(animTime);
        courseFabAlphaAppearAnim = ObjectAnimator.ofFloat(courseAddFAB, "alpha", 0, 1).setDuration(animTime);
        courseFabAlphaDisappearAnim = ObjectAnimator.ofFloat(courseAddFAB, "alpha", 1, 0).setDuration(animTime);
        activityTvAppearAlphaAnim = ObjectAnimator.ofFloat(activityTv, "alpha", 0, 1).setDuration(animTime);
        activityTvDisappearAlphaAnim = ObjectAnimator.ofFloat(activityTv, "alpha", 1, 0).setDuration(animTime);
        courseTvAppearAlphaAnim = ObjectAnimator.ofFloat(courseTv, "alpha", 0, 1).setDuration(animTime);
        courseTvDisappearAlphaAnim = ObjectAnimator.ofFloat(courseTv, "alpha", 1, 0).setDuration(animTime);
        coverRlAlphaAppearAnim = ObjectAnimator.ofFloat(coverRl, "alpha", 0, 0.5F).setDuration(animTime);
        coverRlAlphaDisappearAnim = ObjectAnimator.ofFloat(coverRl, "alpha", 0.5F, 0).setDuration(animTime);
        activityFabAlphaAppearAnim.addListener(new AppearListener());
        activityFabAlphaDisappearAnim.addListener(new DisappearListener());
    }

    public void startAnimation() {
        if (!isRunning()) {
            if (coverRl.getVisibility() == View.INVISIBLE) {
                appearAnim();
            } else {
                disappearAnim();
            }
        }
    }

    protected void appearAnim() {
        activityTvAppearAlphaAnim.start();
        courseTvAppearAlphaAnim.start();
        activityFabAlphaAppearAnim.start();
        courseFabAlphaAppearAnim.start();
        activityFabTranslationAppearAnim.start();
        courseFabTranslationAppearAnim.start();
        courseFabAlphaAppearAnim.start();
        coverRlAlphaAppearAnim.start();
        addFabRotateRight.start();
    }

    protected void disappearAnim() {
        activityTvDisappearAlphaAnim.start();
        courseTvDisappearAlphaAnim.start();
        activityFabAlphaDisappearAnim.start();
        courseFabAlphaDisappearAnim.start();
        activityFabTranslationDisappearAnim.start();
        courseFabTranslationDisappearAnim.start();
        coverRlAlphaDisappearAnim.start();
        addFabRotateLeft.start();
    }

    protected void visiableFab() {
        coverRl.setOnTouchListener(this);
        coverRl.setVisibility(View.VISIBLE);
        activityAddFAB.setVisibility(View.VISIBLE);
        courseAddFAB.setVisibility(View.VISIBLE);
        activityTv.setVisibility(View.VISIBLE);
        courseTv.setVisibility(View.VISIBLE);
    }

    protected void inVisiableFab() {
        coverRl.setOnTouchListener(null);
        coverRl.setVisibility(View.INVISIBLE);
        activityAddFAB.setVisibility(View.INVISIBLE);
        courseAddFAB.setVisibility(View.INVISIBLE);
        activityTv.setVisibility(View.INVISIBLE);
        courseTv.setVisibility(View.INVISIBLE);
    }

    public boolean isFabShowing() {
        return coverRl.getVisibility() == View.VISIBLE;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }

    private class AppearListener implements Animator.AnimatorListener {
        @Override
        public void onAnimationStart(Animator animator) {
            running = true;
            visiableFab();
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            running = false;
        }

        @Override
        public void onAnimationCancel(Animator animator) {

        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }
    }

    private class DisappearListener implements Animator.AnimatorListener {
        @Override
        public void onAnimationStart(Animator animator) {
            running = true;
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            running = false;
            inVisiableFab();
        }

        @Override
        public void onAnimationCancel(Animator animator) {

        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }
    }
}
