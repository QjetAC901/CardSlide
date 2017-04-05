package com.cgq.cardslide.ui;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cgq.cardslide.R;
import com.cgq.cardslide.bean.Xiaohua;
import com.cgq.cardslide.helper.DialogUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsoupActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    private List<Xiaohua> datas;

    private ListView lv;
    private XHAdapter adapter;

    private Handler mhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup);
        dialog = DialogUtils.createProgressDialog(this,"正在加载.....");
        dialog.show();

        mhandler = new Handler();
        datas = new ArrayList<>();
        adapter = new XHAdapter();
        lv = (ListView) findViewById(R.id.jsoup_lv);
        lv.setAdapter(adapter);
        new DataAsyncTask().execute();
    }


    private class DataAsyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                for (int k = 0; k < 5; k++) {
                    Document doc = Jsoup.connect("http://www.qiushibaike.com/imgrank/page/" + k + "/").get();
                    Elements elsTitles = doc.select("a[id=highlight]");//找到类型位置（热门、热图、新鲜、投稿）
                    Elements els = doc.select("a.contentHerf");//找到内容

                    Log.e("糗事百科类型:",elsTitles.text() );

                    Elements elsUsers = doc.select("div[class=author clearfix]");//找到用户信息

                    for (int i = 0; i < els.size(); i++) {
                        Xiaohua xh = new Xiaohua(elsTitles.text());
                        Elements eltUser = elsUsers.get(i).select("a[rel=nofollow] img");
                        if(!eltUser.isEmpty()){
                            xh.setUserName(eltUser.attr("src"));
                            xh.setUserPic(eltUser.attr("alt"));
                        }

                        Element el = els.get(i);
                        String href = el.attr("href");

                        Document doc_detail = Jsoup.connect("http://www.qiushibaike.com" + href).get();
                        Elements els_detail = doc_detail.select(".content");
                        Log.e("3.內容", els_detail.text());
                        xh.setContent(els_detail.text());

                        Elements els_pic = doc_detail.select(".thumb img[src$=jpg]");
                        if (!els_pic.isEmpty()) {
                            xh.setImage(els_pic.attr("src"));

                        } else {
                            xh.setImage("null");
                        }
                        datas.add(xh);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            adapter.notifyDataSetChanged();
        }
    }


    private class XHAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Xiaohua getItem(int i) {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final ViewHolder holder;
            if (view == null){
                holder = new ViewHolder();
                view = LayoutInflater.from(JsoupActivity.this).inflate(R.layout.item_lv_jsoup,null);
                holder.tvType = (TextView) view.findViewById(R.id.tv_type);
                holder.tvContent = (TextView) view.findViewById(R.id.tv_content);
                holder.ivPic = (ImageView) view.findViewById(R.id.iv_pic);
                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }
            final Xiaohua xh = datas.get(i);
            holder.tvType.setText(xh.getType());
            holder.tvContent.setText(xh.getContent());
            if (!xh.getImage().equals("null")){
                holder.ivPic.setVisibility(View.VISIBLE);
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(xh.getImage());
                            final Bitmap bm = BitmapFactory.decodeStream(url.openStream());
                            mhandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    holder.ivPic.setImageBitmap(bm);
                                }
                            });


                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }

            return view;
        }

        private class ViewHolder{
            private TextView tvType,tvContent;
            private ImageView ivPic;
        }
    }
}
