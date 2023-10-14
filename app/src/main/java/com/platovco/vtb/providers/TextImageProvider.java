package com.platovco.vtb.providers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.platovco.vtb.R;
import com.yandex.runtime.image.ImageProvider;

public class TextImageProvider extends ImageProvider {
    private static final float FONT_SIZE = 20;
    private static final float MARGIN_SIZE = 3;
    private static final float STROKE_SIZE = 5;

    @Override
    public String getId() {
        return "text_" + text;
    }

    private final String text;
    private final Context context;

    @Override
    public Bitmap getImage() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(metrics);
        Paint textPaint = new Paint();
        textPaint.setTextSize(FONT_SIZE * metrics.density);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.WHITE);
        textPaint.setAntiAlias(true);
        float widthF = textPaint.measureText(text);
        Paint.FontMetrics textMetrics = textPaint.getFontMetrics();
        float heightF = Math.abs(textMetrics.bottom) + Math.abs(textMetrics.top);
        float textRadius = (float) Math.sqrt(widthF * widthF + heightF * heightF) / 2;
        float internalRadius = textRadius + MARGIN_SIZE * metrics.density;
        float externalRadius = internalRadius + STROKE_SIZE * metrics.density;
        int width = (int) (2 * externalRadius + 0.5);
        Bitmap bitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint backgroundPaint = new Paint();
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setColor(Color.WHITE);
        canvas.drawCircle((float) width / 2, (float) width / 2, externalRadius, backgroundPaint);
        backgroundPaint.setColor(context.getResources().getColor(R.color.colorPrimary));
        canvas.drawCircle((float) width / 2, (float) width / 2, internalRadius, backgroundPaint);
        canvas.drawText(
                text,
                (float) width / 2,
                (float) width / 2 - (textMetrics.ascent + textMetrics.descent) / 2,
                textPaint);
        return bitmap;
    }

    public TextImageProvider(Context context, String text) {
        this.context = context;
        int number = Integer.parseInt(text);
        if (number >= 10) this.text = "10+";
        else if (number >= 5) this.text = "5+";
        else this.text = text;

    }

}