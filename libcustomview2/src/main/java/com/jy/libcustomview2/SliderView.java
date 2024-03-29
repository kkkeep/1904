package com.jy.libcustomview2;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.ArrayList;

/*
 * created by Cherry on 2019-12-09
 **/
public class SliderView extends View {

    private static final String[] CHARS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};

    private WindowManager mWindowManager;
    private TextView mPopView;
    private WindowManager.LayoutParams mWindowParams;

    private Paint mPaint;
    private Paint mStrokePaint;
    private Paint mBgPaint;
    private Paint mSelectedCharBgPaint;
    private ArrayList<Char> mChars;

    private Char mPreChar;

    private RectF mFillRect;
    private RectF mStrokeRect;

    private float mTextSize;

    private float mCharWidth;
    private float mCharHeight;
    private float mMarginLeft; // 文字左边边距
    private float mMarginRight; // 文字右边边距
    private float mMarginTop; // 文字顶边边距
    private float mMarginBottom; // 文字底边边距

    private int mWidth;
    private int mHeight;
    private int mTextColor;
    private int mGridHeight;
    private int mLocationInAppY;// 我们滑动条在 app 窗口（非屏幕）中的位置
    private float mStrokeWidth;


    private boolean isSetWH = false;

    private boolean isShowPopView;


    public SliderView(Context context) {
        this(context, null);
    }

    public SliderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SliderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SliderView);

        mTextSize = typedArray.getDimension(R.styleable.SliderView_sliderTextSize, dp2px(context, 15.0f));
        mMarginLeft = typedArray.getDimension(R.styleable.SliderView_sliderCharLeftMargin, dp2px(context, 12.0f));
        mMarginRight = typedArray.getDimension(R.styleable.SliderView_sliderCharRightMargin, dp2px(context, 12.0f));
        mMarginTop = typedArray.getDimension(R.styleable.SliderView_sliderCharTopMargin, dp2px(context, 12.0f));
        mMarginBottom = typedArray.getDimension(R.styleable.SliderView_sliderCharBottomMargin, dp2px(context, 12.0f));
        mTextColor = typedArray.getColor(R.styleable.SliderView_sliderTextColor, Color.BLACK);

        typedArray.recycle();


        init();


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup instanceof ConstraintLayout) {
            if (!isSetWH) {
                ConstraintLayout parent = (ConstraintLayout) viewGroup;
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(parent);
                constraintSet.constrainHeight(getId(), mHeight);
                constraintSet.constrainWidth(getId(), mWidth);
                constraintSet.applyTo(parent);
                isSetWH = true;
            }
        } else {
            setMeasuredDimension(MeasureSpec.makeMeasureSpec(mWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY));
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }


    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawRoundRect(mStrokeRect, mStrokeRect.width() / 2, mStrokeRect.width() / 2, mStrokePaint);
        canvas.drawRoundRect(mFillRect, mFillRect.width() / 2, mFillRect.width() / 2, mBgPaint);



        for (Char aChar : mChars) {
            if (aChar.isSelect) {
                canvas.drawOval(aChar.getSelectRectF(), mSelectedCharBgPaint);
            }
            canvas.drawText(aChar.value, aChar.x, aChar.y, mPaint);

        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.d("Test", "touch " + event.getAction() + " x = " + event.getX() + " y = " + event.getY());
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN: {
                Char value = getValue(event.getX(), event.getY());
                if (mPreChar != null) {
                    mPreChar.isSelect = false;
                }
                mPreChar = value;

                if (value != null) {
                    showOrUpdatePopView(value);
                    invalidate();
                }
                break;
            }

            case MotionEvent.ACTION_MOVE: {

                Char value = getValue(event.getX(), event.getY());
                if (value != null && value != mPreChar) {
                    if (mPreChar != null) {
                        mPreChar.isSelect = false;
                    }
                    mPreChar = value;
                    showOrUpdatePopView(value);
                    invalidate();
                }

                break;
            }

            case MotionEvent.ACTION_UP: {

                if (mPreChar != null) {
                    mPreChar.isSelect = false;
                    mPreChar = null;
                }
                getHandler().removeCallbacks(removePopViewTask);
                getHandler().postDelayed(removePopViewTask, 200);
                invalidate();

                break;
            }

        }

        return true;
    }

    private void showOrUpdatePopView(Char aChar) {

        Log.d("Test", "show");
        if (mWindowManager == null) {
            mWindowManager = ((Activity) getContext()).getWindowManager();

            mWindowParams = new WindowManager.LayoutParams();
            mWindowParams.type = WindowManager.LayoutParams.TYPE_APPLICATION;     // 提示类型,重要
            mWindowParams.format = PixelFormat.RGBA_8888;
            mWindowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE; // 不能抢占聚焦点
            mWindowParams.flags = mWindowParams.flags | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS; // 排版不受限制
            mWindowParams.gravity = Gravity.LEFT | Gravity.TOP;   //调整悬浮窗口至左上角

            int w = dp2px(getContext(), 60);


            mWindowParams.width = w;
            mWindowParams.height = w;

            mWindowParams.x = getLeft() - 2 * w;
        }

        if (mPopView == null) {
            mPopView = new TextView(getContext());
            mPopView.setBackgroundColor(Color.BLACK);
            mPopView.setAlpha(0.5f);
            mPopView.setGravity(Gravity.CENTER);
            mPopView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40);
            mPopView.setTextColor(Color.WHITE);
        }


        if (!isShowPopView) {
            mWindowManager.addView(mPopView, mWindowParams);
            isShowPopView = true;
        }


        mPopView.setText(aChar.value);

        mWindowParams.y = (int) aChar.y + mLocationInAppY - (mWindowParams.width / 2);

        mWindowManager.updateViewLayout(mPopView, mWindowParams);

    }


    private void removePopView() {
        Log.d("Test", "remove");
        if (isShowPopView) {
            mWindowManager.removeView(mPopView);
            isShowPopView = false;
        }
    }


    private Runnable removePopViewTask = new Runnable() {
        @Override
        public void run() {
            removePopView();
        }
    };

    /**
     * 获取 down 和 move 的点在 27个格子中对应的 字母
     *
     * @param x
     * @param y
     * @return
     */
    private Char getValue(float x, float y) {
        Char value = null;
        for (Char c : mChars) {
            if (c.rect.contains(x, y)) {
                value = c;
                c.isSelect = true;
                break;
            } else {
                c.isSelect = false;

            }
        }
        return value;
    }

    private void init() {

        mPaint = new Paint();

        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.CENTER);


        mStrokeWidth = 1.5f;
        mStrokePaint = new Paint();
        mStrokePaint.setAntiAlias(true);
        mStrokePaint.setStrokeWidth(mStrokeWidth);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setColor(Color.BLACK);


        mBgPaint = new Paint();
        mBgPaint.setStrokeWidth(1);
        mBgPaint.setAntiAlias(true);
        mBgPaint.setStyle(Paint.Style.FILL);
        mBgPaint.setColor(Color.WHITE);

        mSelectedCharBgPaint = new Paint();
        mSelectedCharBgPaint.setStrokeWidth(1);
        mSelectedCharBgPaint.setAntiAlias(true);
        mSelectedCharBgPaint.setStyle(Paint.Style.FILL);
        mSelectedCharBgPaint.setColor(Color.RED);


        mCharWidth = mPaint.measureText("W"); // 计算文字的宽度

        mWidth = (int) (mMarginLeft + mCharWidth + mMarginRight);

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();


        mCharHeight = Math.min(mTextSize, Math.abs(fontMetrics.ascent));

        mGridHeight = (int) (mMarginTop + mCharHeight + mMarginBottom);

        mHeight = (int) (CHARS.length * mGridHeight);

        Log.d("Test", "------ height " + mHeight);
        mChars = new ArrayList<>(CHARS.length);

        mStrokeRect = new RectF();

        mStrokeRect.left = mStrokeWidth / 2;
        mStrokeRect.top = mStrokeWidth / 2;
        mStrokeRect.right = mWidth - mStrokeWidth / 2;
        mStrokeRect.bottom = mHeight - mStrokeWidth / 2;

        mFillRect = new RectF();
        mFillRect.left = mStrokeRect.left + mStrokeWidth / 2;
        mFillRect.top = mStrokeRect.top + mStrokeWidth / 2;
        mFillRect.right = mStrokeRect.right - mStrokeWidth / 2;
        mFillRect.bottom = mStrokeRect.bottom - mStrokeWidth / 2;

        //mFillRect = mStrokeRect;

        Char aChar;
        for (int i = 0; i < CHARS.length; i++) {
            aChar = new Char(CHARS[i], i);
            mChars.add(aChar);
        }


        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                int[] location = new int[2];
                getLocationInWindow(location);
                mLocationInAppY = location[1] - getStatusBarHeight();
                getViewTreeObserver().removeOnPreDrawListener(this);

                return false;
            }
        });

    }

    /**
     * 获取状态栏的高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        Rect rectangle = new Rect();
        ((Activity) getContext()).getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);
        return rectangle.top;
    }

    private class Char {
        String value;
        RectF rect = new RectF();
        float y;
        float x;
        RectF select;
        int position;
        boolean isSelect;

        public Char(String value, int position) {
            this.value = value;
            this.position = position;
            rect.top = position * mGridHeight;
            rect.left = 0;
            rect.right = mWidth;
            rect.bottom = rect.top + mGridHeight;

            y = ((rect.height() / 2) + (mCharHeight / 2)) + rect.top;
            x = rect.width() / 2;

            Log.d("Test", value + " x = " + x + " y = " + rect.bottom);
        }

        public RectF getSelectRectF() {
            if (select == null) {
                int offset = (int) Math.min(mStrokeWidth * 4, rect.width() / 10);

                int bottom = (int) (rect.bottom - offset + mCharHeight * 0.20);

                if (position == CHARS.length - 1) {
                    bottom = (int) (rect.bottom - offset);
                }

                select = new RectF(rect.left + offset, rect.top + offset, rect.right - offset, bottom);

            }

            return select;
        }


    }


    private static int dp2px(Context context, float dp) {

        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    private String getModeName(int mode) {
        if (mode == MeasureSpec.UNSPECIFIED) {
            return " MeasureSpec.UNSPECIFIED";
        } else if (mode == MeasureSpec.AT_MOST) {
            return " MeasureSpec.AT_MOST";
        } else {
            return " MeasureSpec.EXACTLY";
        }
    }
}
