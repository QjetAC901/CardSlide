package com.cgq.cardslide.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cgq.all_library.utils.CheckPermissionsUtil;
import com.cgq.all_library.utils.FileUtil;
import com.cgq.all_library.weight.JCameraView;
import com.cgq.cardslide.R;

public class ShootingActivity extends AppCompatActivity {

    private JCameraView mJCameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shooting);
        //隐藏状态栏操作
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        //隐藏标题栏
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //Andriod6.0 running permission
        CheckPermissionsUtil checkPermissionsUtil = new CheckPermissionsUtil(this);
        checkPermissionsUtil.requestAllPermission(this);

        mJCameraView = (JCameraView) findViewById(R.id.cameraview);
        //设置是否自动对焦
        mJCameraView.setAutoFoucs(false);
        //设置视频保存路径（如果不设置默认为Environment.getExternalStorageDirectory().getPath()）
        mJCameraView.setSaveVideoPath(Environment.getExternalStorageDirectory().getPath());
        mJCameraView.setCameraViewListener(new JCameraView.CameraViewListener() {
            @Override
            public void quit() {
                setResult(RESULT_CANCELED);
                ShootingActivity.this.finish();
            }

            @Override
            public void captureSuccess(Bitmap bitmap) {
                String jpgPath = FileUtil.saveBitmap(bitmap);
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                bundle.putString("DataType", "image");
                bundle.putString("path", jpgPath);
                intent.putExtra("data", bundle);
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public void recordSuccess(String url) {
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                bundle.putString("DataType", "video");
                bundle.putString("path", url);
                intent.putExtra("data", bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mJCameraView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mJCameraView.onPause();
    }
}
