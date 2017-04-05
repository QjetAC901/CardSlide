package com.cgq.cardslide.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cgq.cardslide.R;
import com.cgq.cardslide.view.CoordinateView;

public class UnlockActivity extends AppCompatActivity {

    private Button btnDraw;
    private CoordinateView cv;


    private static final String TAG = "UnlockActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock);

        btnDraw = (Button) findViewById(R.id.btn_draw);
        cv = (CoordinateView) findViewById(R.id.coordinate);
        btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cv.isDraw()) {
                    cv.setDraw(false);
                } else {
                    cv.setDraw(true);
                }
            }
        });

        cv.setOnTouchListener(new CoordinateView.OnTouchListener() {
            @Override
            public void onDownTouch(float downX, float downY) {
                Log.e(TAG, "onTouchEvent:--------------开始");
            }

            @Override
            public void onUpTouch(float upX, float upY) {
                Log.e(TAG, "onTouchEvent:--------------结束");
            }
        });
    }
}
