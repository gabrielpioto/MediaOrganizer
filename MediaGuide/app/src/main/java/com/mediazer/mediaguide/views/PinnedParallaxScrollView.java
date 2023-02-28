package com.mediazer.mediaguide.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.mediazer.mediaguide.R;

/**
 * Created by gabriel on 03/06/15.
 */
public class PinnedParallaxScrollView extends ScrollView {

    private static final float DEFAULT_PARALLAX_FACTOR = 1.5F;
    private static final float DEFAULT_ALPHA_FACTOR = -1F;
    private static final int DEFAULT_TOP_POSITION = 0;

    private float parallaxFactor = DEFAULT_PARALLAX_FACTOR;
    private float alphaFactor = DEFAULT_ALPHA_FACTOR;
    private int topPosition = DEFAULT_TOP_POSITION;

    private View pinnedView;
    private View parallaxView;
    private View pinnedPlaceHolder;
    private FrameLayout frame;

    private OnScrollListener mOnScrollListener;

    public PinnedParallaxScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public PinnedParallaxScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.PinnedParallaxScroll);
        this.parallaxFactor = typeArray.getFloat(R.styleable.PinnedParallaxScroll_parallax_factor, DEFAULT_PARALLAX_FACTOR);
        this.alphaFactor = typeArray.getFloat(R.styleable.PinnedParallaxScroll_alpha_factor, DEFAULT_ALPHA_FACTOR);
        //this.topPosition = typeArray.getInt(R.styleable.PinnedParallaxScroll_pinned_top_position, DEFAULT_TOP_POSITION);
        this.topPosition = typeArray.getDimensionPixelSize(R.styleable.PinnedParallaxScroll_pinned_top_position, DEFAULT_TOP_POSITION);
        typeArray.recycle();

        frame = new FrameLayout(context);
        pinnedPlaceHolder = new View(context) {
            @Override
            protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                this.setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), pinnedView.getMeasuredHeight());
            }
        };
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        findViews();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed)
            pinnedView.setTranslationY(pinnedPlaceHolder.getTop());
    }

    private void findViews() {
        if (getChildCount() > 0 && getChildAt(0) instanceof ViewGroup) {

            ViewGroup viewsHolder = (ViewGroup) getChildAt(0);
            parallaxView = viewsHolder.getChildAt(0);
            pinnedView = viewsHolder.getChildAt(1);

            viewsHolder.removeView(pinnedView);
            viewsHolder.addView(pinnedPlaceHolder, 1);
            removeView(viewsHolder);

            frame.addView(viewsHolder);
            frame.addView(pinnedView);
            addView(frame);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        parallaxView.setTranslationY((float) t / parallaxFactor);
        if (alphaFactor != DEFAULT_ALPHA_FACTOR) {
            float fixedAlpha = (t <= 0) ? 1 : (100 / ((float) t * alphaFactor));
            parallaxView.setAlpha(fixedAlpha);
        }
        pinnedView.setTranslationY(Math.max(pinnedPlaceHolder.getTop(), t + topPosition));

        if (mOnScrollListener != null) {
            mOnScrollListener.onScroll(this, t);
        }
    }

    public void setOnScrollListener(OnScrollListener listener) {
        mOnScrollListener = listener;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }

    public interface OnScrollListener {
        void onScroll(ScrollView who, int scrollY);
    }

}