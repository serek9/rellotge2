package com.example.dam.rellotge2;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.icu.util.Calendar;
import android.util.AttributeSet;
import android.view.View;
import java.util.GregorianCalendar;
public class Rellotge extends View{

    private Paint boleta;
    private Paint hores, minuts, segons;

    public Rellotge(Context context) {this(context, null, 0);}
    public Rellotge(Context context, AttributeSet attrs) {this(context, attrs, 0);}
    public Rellotge(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        boleta = new Paint();
        boleta.setColor(Color.GRAY);
        hores = new Paint();
        hores.setColor(Color.BLACK);
        hores.setStrokeWidth(25f);
        hores.setStrokeCap(Paint.Cap.ROUND);
        minuts = new Paint();
        minuts.setColor(Color.BLACK);
        minuts.setStrokeWidth(15f);
        segons = new Paint();
        segons.setColor(Color.BLACK);
        segons.setStrokeWidth(10f);
    }

    @Override public void onMeasure(int widthSpec, int heightSpec){
        int w = MeasureSpec.getSize(widthSpec);
        int h = MeasureSpec.getSize(heightSpec);
        int size = Math.min(w,h);
        setMeasuredDimension(size, size);
    }

    @Override public void onDraw(Canvas canvas){
        int size = this.getWidth() / 2;
        canvas.translate(size, size);
        for (int i=0; i<360; i += 30){
            canvas.save();
            canvas.rotate(i);
            canvas.drawCircle(0, -0.95f*size, 0.05f*size, boleta);
            canvas.restore();
        }
        GregorianCalendar ara = new GregorianCalendar();
        int h = ara.get(Calendar.HOUR);
        int m = ara.get(Calendar.MINUTE);
        int s = ara.get(Calendar.SECOND);
        canvas.save();
        canvas.rotate(h*30);
        canvas.drawLine(0, 0, 0, -0.6f*size, hores);
        canvas.restore();

        canvas.save();
        canvas.rotate(m*6);
        canvas.drawLine(0, 0, 0, -0.85f*size, minuts);
        canvas.restore();

        canvas.save();
        canvas.rotate(s*6);
        canvas.drawLine(0, 0, 0, -1.0f*size, segons);
        canvas.restore();
        this.postInvalidateDelayed(100);
    }

}
