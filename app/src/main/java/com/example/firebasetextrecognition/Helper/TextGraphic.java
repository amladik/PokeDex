package com.example.firebasetextrecognition.Helper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.text.FirebaseVisionText;

public class TextGraphic extends GraphicOverlay.Graphic{
    private static final int Text_Color = Color.YELLOW;
    private static final float Text_Size = 34.0f;
    private static final float Stroke_Width = 4.0f;

    private final Paint rectPaint, textPaint;
    private final FirebaseVisionText.Element text;

    public TextGraphic(GraphicOverlay overlay,FirebaseVisionText.Element text) {
        super(overlay);

        this.text = text;


        rectPaint = new Paint();
        rectPaint.setColor(Color.YELLOW);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(Stroke_Width);


        textPaint = new Paint();
        textPaint.setColor(Text_Color);
        textPaint.setTextSize(Text_Size);

        postInvalidate();

    }


    @Override
    public void draw(Canvas canvas) {
        if(textPaint == null)
        {
            throw new IllegalStateException("Attempting to draw a null text");

        }

        RectF rect = new RectF(text.getBoundingBox());

        rect.left = translateX(rect.left);
        rect.top = translateX(rect.top);
        rect.right = translateX(rect.right);
        rect.bottom = translateX(rect.bottom);
       // canvas.drawRect(rect,rectPaint);


      //  canvas.drawText(text.getText(), rect.right, rect.bottom,textPaint);


    }
}
