package com.jy.libcustomview2;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.ArrayList;

/*
 * created by Cherry on 2019-12-09
 **/
public class SliderView extends View {

    private static final String  [] CHARS = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","#"};
    private Paint mPaint;
    private Paint mGridPaint;
    private ArrayList<Char> mChars;

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




    private boolean isSetWH = false;


    public SliderView(Context context) {
        this(context,null);
    }

    public SliderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SliderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SliderView);

        mTextSize = typedArray.getDimension(R.styleable.SliderView_sliderTextSize,dp2px(context, 15.0f));
        mMarginLeft = typedArray.getDimension(R.styleable.SliderView_sliderCharLeftMargin,dp2px(context, 12.0f));
        mMarginRight = typedArray.getDimension(R.styleable.SliderView_sliderCharRightMargin,dp2px(context, 12.0f));
        mMarginTop = typedArray.getDimension(R.styleable.SliderView_sliderCharTopMargin,dp2px(context, 12.0f));
        mMarginBottom = typedArray.getDimension(R.styleable.SliderView_sliderCharBottomMargin,dp2px(context, 12.0f));
        mTextColor = typedArray.getColor(R.styleable.SliderView_sliderTextColor, Color.BLACK);

        typedArray.recycle();


        init();


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);



        ViewGroup  viewGroup = (ViewGroup) getParent();
        if(viewGroup instanceof ConstraintLayout){
            if(!isSetWH){
                ConstraintLayout parent = (ConstraintLayout) viewGroup;
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(parent);
                constraintSet.constrainHeight(getId(), mHeight);
                constraintSet.constrainWidth(getId(), mWidth);
                constraintSet.applyTo(parent);
                isSetWH = true;
            }
        }else{
            setMeasuredDimension(MeasureSpec.makeMeasureSpec(mWidth, MeasureSpec.EXACTLY),MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY));
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(Char aChar : mChars){
            canvas.drawText(aChar.value, aChar.x, aChar.y, mPaint);
           // canvas.drawRect(aChar.rect, mGridPaint);
        }
    }




    private void init(){

        mPaint = new Paint();

        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.CENTER);

        mGridPaint = new Paint();
        mGridPaint.setStrokeWidth(3);
        mGridPaint.setStyle(Paint.Style.STROKE);

        mCharWidth =  mPaint.measureText("W"); // 计算文字的宽度

        mWidth = (int) (mMarginLeft + mCharWidth + mMarginRight);

        Paint.FontMetrics fontMetrics =  mPaint.getFontMetrics();
        mCharHeight = Math.min(mTextSize,Math.abs(fontMetrics.ascent));

        mGridHeight = (int) (mMarginTop + mCharHeight + mMarginBottom);

        mHeight = (int) (CHARS.length * (mCharHeight + mMarginBottom + mMarginTop));

        mChars = new ArrayList<>(CHARS.length);

        Char aChar;
        for(int i =0; i < CHARS.length;i++){
            aChar = new Char(CHARS[i],i);
            mChars.add(aChar);
        }


    }


    private class Char {
        String value;
        Rect rect  = new Rect();
        float y;
        float x;

        public Char(String value,int position) {
            this.value = value;
            rect.top = position * mGridHeight;
            rect.left = 0;
            rect.right = mWidth;
            rect.bottom = rect.top + mGridHeight;

            y = ((rect.height() /2) + (mCharHeight /2)) + rect.top;
            x = rect.width() /2;



            Log.d("Test", value + " x = " + x + " y = " + y);
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
