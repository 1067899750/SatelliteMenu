package com.diudiu.satellitemenu.lockPattern.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.diudiu.satellitemenu.R;

import java.util.ArrayList;
import java.util.List;

public class LockPatternView extends View {

    //选中点的数量
    private int pointSize = 5;
    //9个点
    private Point[][] points = new Point[3][3];
    private boolean isInit, isSelected, isFinish;
    private float width, height, offsetsX, offssetsY;
    private int mPointRadius;

    private Bitmap pointNormal, pointPressed, pointError, linePressed, lineError;

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    /**
     * 按下的点的集合
     */
    private List<Point> pointList = new ArrayList<>();
    /**
     * 移动的X和Y
     */
    private float movingX, movingY;

    /**
     * 点
     */
    public static class Point {
        //正常
        public static int STATE_NORMAL = 0;
        //选中
        public static int STATE_PRESSED = 1;
        //错误
        public static int STATE_ERROR = 2;
        public float x, y;
        public int index = 0, state = 0;

        public Point() {
        }

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    public LockPatternView(Context context) {
        this(context, null);
    }

    public LockPatternView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LockPatternView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInit) {
            initPoints();
        }
        points2Canvas(canvas);
    }

    /**
     * 将点绘制到画布
     *
     * @param canvas
     */
    private void points2Canvas(Canvas canvas) {
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                Point point = points[i][j];
                if (point.state == Point.STATE_NORMAL) {
                    canvas.drawBitmap(pointNormal, point.x - mPointRadius, point.y - mPointRadius, paint);
                } else if (point.state == Point.STATE_PRESSED) {
                    canvas.drawBitmap(pointPressed, point.x - mPointRadius, point.y - mPointRadius, paint);
                } else {
                    canvas.drawBitmap(pointError, point.x - mPointRadius, point.y - mPointRadius, paint);
                }
            }
        }
    }

    /**
     * 初始化点
     */
    private void initPoints() {
//1.获取布局宽高
        width = getWidth();
        height = getHeight();

        //横屏和竖屏
        if (width > height) {
            offsetsX = (width - height) / 2;
            width = height;
        } else {
            offssetsY = (height - width) / 2;
            height = width;
        }

        //图片资源
        pointNormal = BitmapFactory.decodeResource(getResources(), R.drawable.point_normal);
        pointPressed = BitmapFactory.decodeResource(getResources(), R.drawable.point_pressed);
        pointError = BitmapFactory.decodeResource(getResources(), R.drawable.point_error);
        linePressed = BitmapFactory.decodeResource(getResources(), R.drawable.line_pressed);
        lineError = BitmapFactory.decodeResource(getResources(), R.drawable.line_error);

        points[0][0] = new Point((offsetsX + width / 4), (offssetsY + width / 4));
        points[0][1] = new Point((offsetsX + width / 2), (offssetsY + width / 4));
        points[0][2] = new Point((offsetsX + width - width / 4), (offssetsY + width / 4));

        points[1][0] = new Point((offsetsX + width / 4), (offssetsY + width / 2));
        points[1][1] = new Point((offsetsX + width / 2), (offssetsY + width / 2));
        points[1][2] = new Point((offsetsX + width - width / 4), (offssetsY + width / 2));

        points[2][0] = new Point((offsetsX + width / 4), (offssetsY + width - width / 4));
        points[2][1] = new Point((offsetsX + width / 2), (offssetsY + width - width / 4));
        points[2][2] = new Point((offsetsX + width - width / 4), (offssetsY + width - width / 4));

        mPointRadius = pointNormal.getWidth() / 2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        movingX = event.getX();
        movingY = event.getY();

        Point point = null;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                resetPoint();
                point = checkSelectPoint();
                if (point != null) {
                    isSelected = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isSelected) {
                    point = checkSelectPoint();
                }
                break;
            case MotionEvent.ACTION_UP:
                isFinish = true;
                isSelected = false;
                break;
        }
        //选中重复检查
        if (!isFinish && isSelected && point != null) {

        }
        //绘制结束
        if (isFinish) {
            if (pointList.size() == 1) {//绘制不成立
                pointList.clear();
            } else if (pointList.size() < pointSize && pointList.size() > 2) {//绘制错误

            }
        }
        postInvalidate();
        return true;
    }

    /**
     * 重置
     */
    public void resetPoint() {

    }

    /**
     * 绘制错误
     */
    public void errPoint() {
        for (Point point : pointList) {
            point.state = Point.STATE_ERROR;
        }
    }

    /**
     * 检查点是否选中
     *
     * @return
     */
    private Point checkSelectPoint() {
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                Point point = points[i][j];
                if (with(point.x, point.y, mPointRadius, movingX, movingY)) {
                    return point;
                }
            }
        }
        return null;
    }

    /**
     * 是否重合
     *
     * @param poinX   参考点的X
     * @param pointY  参考点的Y
     * @param r       圆的半径
     * @param movingX 移动点的X
     * @param movingY 移动点的Y
     * @return 是否重合
     */
    private static boolean with(float poinX, float pointY, float r, float movingX, float movingY) {
        return Math.sqrt((poinX - movingX) * (poinX - movingX) + (pointY - movingY) * (pointY - movingY)) < r;
    }
}
