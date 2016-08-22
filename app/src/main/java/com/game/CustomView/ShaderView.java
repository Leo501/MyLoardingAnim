package com.game.CustomView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2016/8/19.
 */
public class ShaderView extends View {
    private int colorArray[];
    private float position[];
    private int start_x,end_x,start_y,end_y;
    private float change_x_1, change_y_1,change_x_2,change_y_2;
    private int view_start_x,view_end_x,view_start_y,view_end_y;
    private static final String TAG = "ShaderView";
    public ShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        colorArray =new int[]{0x00ffe6ff,0x8fffe6ff,0xafffffff};
        position=new float[]{0.3f,0.3f,0.4f};
        view_end_y=15;
        view_start_y=10;
        view_start_x=100;
        view_end_x=100;
    }

    public ShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        change_x_1 =getWidth();
        change_y_1 =getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        start_x=getWidth()/4;end_x=getWidth()*3/4;
        start_y=getHeight()/6;end_y=getHeight()*5/6;
        //改变X轴，Y轴定长，如果做动画的话，只变Y轴。
        ShaderPara para=new ShaderPara(start_x,view_start_y,start_x, change_y_1,0,35);
        creatShaderVertical(para,colorArray,canvas);
        //同上
        ShaderPara para2=new ShaderPara(end_x-70,change_y_2,end_x-70, getHeight() -view_end_y,0,35);
        creatShaderVertical(para2,colorArray,canvas);
        //改变Y轴，X轴不变，如果做动画的话，只变X轴。
        ShaderPara para1=new ShaderPara(view_start_x,start_y, change_x_1,start_y,35,0);
        creatShaderHorizontal(para1,colorArray,canvas);
        //同上
        ShaderPara para3=new ShaderPara(change_x_2,end_y-70, getWidth() -view_end_x,getWidth()-70,35,0);
        creatShaderHorizontal(para3,colorArray,canvas);

    }
    public void start_animation(){

        ValueAnimator animator_x=ValueAnimator.ofFloat(view_start_x,getWidth()-view_start_x,view_start_x);
        animator_x.setDuration(3000);
        animator_x.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                change_x_1 = (float) valueAnimator.getAnimatedValue();
                Log.d(TAG, "onAnimationUpdate: "+ change_x_1);
                invalidate();
            }
        });

        ValueAnimator animator_end_x=ValueAnimator.ofFloat(getWidth()-view_end_x,view_end_x,getWidth()-view_end_x);
        animator_end_x.setDuration(3000);
        animator_end_x.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                change_x_2=(float)valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        ValueAnimator animator_y=ValueAnimator.ofFloat(view_start_y,getHeight()-view_end_y,view_start_y);
        animator_y.setDuration(3000);
        animator_y.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                change_y_1 =(float)valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        ValueAnimator animator_end_y=ValueAnimator.ofFloat(getHeight()-view_end_y,view_start_y,getHeight()-view_end_y);
        animator_end_y.setDuration(3000);
        animator_end_y.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                change_y_2=(float)valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator_x.start();
        animator_end_x.start();
        animator_y.start();
        animator_end_y.start();
    }
    private void creatShaderVertical(ShaderPara para,int [] colorArray,Canvas canvas){
        Paint paint=setPaint(colorArray[1],0, Paint.Style.FILL);
        Shader shader=new LinearGradient(para.start_x,para.start_y,para.start_x+para.width_x,para.start_y,colorArray,null, Shader.TileMode.MIRROR);
        RectF rectF=new RectF(para.start_x,para.start_y,para.start_x+para.width_x*2,para.end_y);
        paint.setShader(shader);
        canvas.drawRoundRect(rectF,0,0,paint);

        Paint paint1=setPaint(colorArray[1],0, Paint.Style.FILL);
        Shader shader1=new LinearGradient(para.start_x+para.width_x*4/7,para.start_y,para.start_x+para.width_x,para.start_y,colorArray,null, Shader.TileMode.MIRROR);
        paint1.setShader(shader1);
        RectF rectF1=new RectF(para.start_x+para.width_x*4/7,para.start_y,para.start_x+para.width_x*2-para.width_x*4/7,para.end_y);
        canvas.drawRoundRect(rectF1,0,0,paint1);
    }
    private void creatShaderHorizontal(ShaderPara para,int [] colorArray,Canvas canvas){

        Paint paint=setPaint(colorArray[1],0, Paint.Style.FILL);
        Shader shader=new LinearGradient(para.start_x,para.start_y,para.start_x,para.start_y+para.width_y,colorArray,null, Shader.TileMode.MIRROR);
        RectF rectF=new RectF(para.start_x,para.start_y,para.end_x,para.start_y+para.width_y*2);
        paint.setShader(shader);
        canvas.drawRoundRect(rectF,0,0,paint);

        Paint paint1=setPaint(colorArray[1],0, Paint.Style.FILL);
        Shader shader1=new LinearGradient(para.start_x,para.start_y+para.width_y*4/7,para.start_x,para.start_y+para.width_y,colorArray,null, Shader.TileMode.MIRROR);
        paint1.setShader(shader1);
        RectF rectF1=new RectF(para.start_x,para.start_y+para.width_y*4/7,para.end_x,para.start_y+para.width_y*2-para.width_y*4/7);
        canvas.drawRoundRect(rectF1,0,0,paint1);
    }
    private Paint setPaint(int color, int lineWidth, Paint.Style style){
        Paint paint=new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeWidth(lineWidth);
        paint.setStyle(style);
//        paint.setStrokeCap(Paint.Cap.ROUND); //设置笔刷的样式 Paint.Cap.Round ,Cap.SQUARE等分别为圆形、方形
//        paint.setStrokeJoin(Paint.Join.ROUND);//画笔接洽点类型 如影响矩形但角的外轮廓
        return paint;
    }
    private Paint creatPaint(int paintColor, int textSize, Paint.Style style, int lineWidth) {
        Paint paint = new Paint();
        paint.setColor(paintColor);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(lineWidth);
        paint.setDither(true);
        paint.setTextSize(textSize);
        paint.setStyle(style);
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        paint.setStrokeJoin(Paint.Join.ROUND);
        return paint;
    }
    class ShaderPara{
        float start_x;
        float start_y;
        float end_x;
        float end_y;
        float width_y;
        float width_x;

        public ShaderPara(float start_x, float start_y, float end_x, float end_y, float width_y,float width_x) {
            this.start_x = start_x;
            this.start_y = start_y;
            this.end_x = end_x;
            this.end_y = end_y;
            this.width_y = width_y;
            this.width_x = width_x;
        }

        public void setStart_x(float start_x) {
            this.start_x = start_x;
        }

        public void setStart_y(float start_y) {
            this.start_y = start_y;
        }

        public void setEnd_x(float end_x) {
            this.end_x = end_x;
        }

        public void setEnd_y(float end_y) {
            this.end_y = end_y;
        }

        public void setWidth_y(float width_y) {
            this.width_y = width_y;
        }

        public void setWidth_x(float width_x) {
            this.width_x = width_x;
        }

        public float getStart_x() {
            return start_x;
        }

        public float getStart_y() {
            return start_y;
        }

        public float getEnd_x() {
            return end_x;
        }

        public float getEnd_y() {
            return end_y;
        }

        public float getWidth_y() {
            return width_y;
        }
    }
}
