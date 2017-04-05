package com.cgq.cardslide.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cgq.cardslide.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_rr://人人美剧卡片滑动
                intent.setClass(this, RrCardActivity.class);
                intent.putExtra("Mode", 0);
                break;
            case R.id.btn_tt://探探卡片滑动
                intent.setClass(this, RrCardActivity.class);
                intent.putExtra("Mode", 1);
                break;
            case R.id.btn_unlock://可移动坐标系
                intent.setClass(this, UnlockActivity.class);
                break;
            case R.id.btn_custom_recycler://自定义RecycleView
                intent.setClass(this, RecyclerViewTestActivity.class);
                break;
            case R.id.btn_free_view://可随意拖动的悬浮窗
                intent.setClass(this, FreeViewActivity.class);
                intent.putExtra("Mode", 2);
                break;
            case R.id.btn_slide_code://仿斗鱼滑动验证码
                intent.setClass(this, FreeViewActivity.class);
                intent.putExtra("Mode", 3);
                break;
            case R.id.btn_code://二维码案例
                intent.setClass(this, CodeActivity.class);
                break;
            case R.id.btn_nested://ListView上方布局大小根据他的滑动动态变化
                intent.setClass(this, NestedScrollActivity.class);
                break;
            case R.id.btn_jsoup://使用Jsoup网络爬取数据
                intent.setClass(this, JsoupActivity.class);
                break;
            case R.id.btn_WX_shooting:
                intent.setClass(this, ShootingActivity.class);
                startActivityForResult(intent, 600);
                return;
        }
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 600) {
                Bundle data = intent.getBundleExtra("data");
                String type = data.getString("DataType");
                String dataPath = data.getString("path");
                Log.e(TAG, "data type is :" + type + "::::::::data path is :" + dataPath);
            }

        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "cancel shoot！", Toast.LENGTH_SHORT).show();
        }
    }
}
