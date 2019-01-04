package com.diudiu.satellitemenu.satellite.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.diudiu.satellitemenu.R;

public class ArcMenu extends ViewGroup {

    private final int POS_LEFT_TOP = 0;
    private final int POS_LEFT_BOTTOM = 1;
    private final int POS_RIGHT_TOP = 2;
    private final int POS_RIGHT_BOTTOM = 3;

    private int mRadius;
    private Position mPosition = Position.RIGHT_BOTOTM;
    /**
     * 菜单的状态
     */
    private Status mCurrentStatus = Status.CLOSE;
    /**
     * 菜单的按钮
     */
    private View mCButton;

    private OnMunuItemClickListener onMunuItemClickListener;

    public enum Status {
        OPEN, CLOSE
    }

    /**
     * 菜单的位置枚举类
     */
    public enum Position {
        LEFT_TOP, LEFT_BOTTOM, RIGHT_TOP, RIGHT_BOTOTM
    }

    /**
     * 菜单的点击监听
     */
    public interface OnMunuItemClickListener {
        void onClick(View view, int pos);
    }

    public void setOnMunuItemClickListener(OnMunuItemClickListener onMunuItemClickListener) {
        this.onMunuItemClickListener = onMunuItemClickListener;
    }


    public ArcMenu(Context context) {
        this(context, null);
    }

    public ArcMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());

        //获取自定义属性的值
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ArcMenu, defStyleAttr, 0);

        int pos = a.getInt(R.styleable.ArcMenu_position, POS_RIGHT_BOTTOM);
        switch (pos) {
            case POS_LEFT_TOP:
                mPosition = Position.LEFT_TOP;
                break;
            case POS_LEFT_BOTTOM:
                mPosition = Position.LEFT_BOTTOM;
                break;
            case POS_RIGHT_TOP:
                mPosition = Position.RIGHT_TOP;
                break;
            case POS_RIGHT_BOTTOM:
                mPosition = Position.RIGHT_BOTOTM;
                break;
        }

        mRadius = (int) a.getDimension(R.styleable.ArcMenu_radius, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()));
        Log.e("TAG", "position=" + mPosition + ",radius=" + mRadius);
        a.recycle();
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
