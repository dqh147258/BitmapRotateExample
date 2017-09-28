package com.yxf.bitmaprotateexample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yxf.bitmaprotateexample.R;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    Bitmap src;
    ImageView imageView;
    Button button;
    float direction = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        src = BitmapFactory.decodeResource(getResources(), R.drawable.rotate);
        imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageBitmap(src);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                direction += 10f;
                Bitmap bitmap = rotateBitmap(src, direction);
                imageView.setImageBitmap(bitmap);
            }
        });
    }


    Bitmap rotateBitmap(Bitmap bitmap, float direction) {
        int d = bitmap.getWidth();
        int r = d / 2;
        float dir = Math.abs(direction) % 90;
        int l = (int) (2 * Math.sqrt(2) * r * Math.sin(Math.PI * (dir + 45) / 180f));
        Matrix matrix = new Matrix();
        matrix.postRotate(direction);
        Bitmap rotate = Bitmap.createBitmap(bitmap, 0, 0, d, d, matrix, true);
        Bitmap result = Bitmap.createBitmap(d, d, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Rect src = new Rect(l / 2 - r, l / 2 - r, r + l / 2, r + l / 2);
        RectF dst = new RectF(0, 0, d, d);
        canvas.drawBitmap(rotate, src, dst, null);
        canvas.setBitmap(null);
        return result;
    }
}
