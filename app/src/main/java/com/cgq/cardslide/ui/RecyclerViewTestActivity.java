package com.cgq.cardslide.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cgq.cardslide.R;
import com.cgq.cardslide.adapter.HomeAdapter;
import com.cgq.cardslide.helper.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewTestActivity extends AppCompatActivity {

    private List<String> mData;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter adapter;

    private HomeAdapter hAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclyer_view_test);
        initData();

        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        adapter = new RecyclerViewAdapter(mData, this);
        hAdapter = new HomeAdapter(this, mData);
        hAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecyclerViewTestActivity.this, position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(RecyclerViewTestActivity.this, position + " long click", Toast.LENGTH_SHORT).show();
            }
        });

        mRecyclerView.setAdapter(hAdapter);
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private void initData() {
        mData = new ArrayList<>();
//        for (int i = 'A'; i < 'z'; i++) {
//            mData.add("" + (char) i);
//        }
        mData.add("http://imgs.ebrun.com/resources/2016_03/2016_03_25/201603259771458878793312_origin.jpg");
        mData.add("http://p14.go007.com/2014_11_02_05/a03541088cce31b8_1.jpg");
        mData.add("http://imgs.ebrun.com/resources/2016_03/2016_03_25/201603259771458878793312_origin.jpg");
        mData.add("http://p14.go007.com/2014_11_02_05/a03541088cce31b8_1.jpg");
        mData.add("http://imgs.ebrun.com/resources/2016_03/2016_03_25/201603259771458878793312_origin.jpg");
        mData.add("http://p14.go007.com/2014_11_02_05/a03541088cce31b8_1.jpg");
        mData.add("http://imgs.ebrun.com/resources/2016_04/2016_04_12/201604124411460430531500.jpg");
        mData.add("http://news.k618.cn/tech/201604/W020160407281077548026.jpg");
        mData.add("http://imgs.ebrun.com/resources/2016_03/2016_03_25/201603259771458878793312_origin.jpg");
        mData.add("http://news.k618.cn/tech/201604/W020160407281077548026.jpg");
        mData.add("http://imgs.ebrun.com/resources/2016_04/2016_04_12/201604124411460430531500.jpg");
        mData.add("http://news.k618.cn/tech/201604/W020160407281077548026.jpg");
        mData.add("http://www.kejik.com/image/1460343965520.jpg");
        mData.add("http://cn.chinadaily.com.cn/img/attachement/jpg/site1/20160318/eca86bd77be61855f1b81c.jpg");
        mData.add("http://imgs.ebrun.com/resources/2016_04/2016_04_12/201604124411460430531500.jpg");
        mData.add("http://cn.chinadaily.com.cn/img/attachement/jpg/site1/20160318/eca86bd77be61855f1b81c.jpg");
        mData.add("http://imgs.ebrun.com/resources/2016_04/2016_04_12/201604124411460430531500.jpg");
        mData.add("http://imgs.ebrun.com/resources/2016_04/2016_04_24/201604244971461460826484_origin.jpeg");
        mData.add("http://imgs.ebrun.com/resources/2016_04/2016_04_12/201604124411460430531500.jpg");
        mData.add("http://www.lnmoto.cn/bbs/data/attachment/forum/201408/12/074018gshshia3is1cw3sg.jpg");
        mData.add("http://imgs.ebrun.com/resources/2016_04/2016_04_12/201604124411460430531500.jpg");
        mData.add("http://www.lnmoto.cn/bbs/data/attachment/forum/201408/12/074018gshshia3is1cw3sg.jpg");
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

        private List<String> datas;
        private Context context;


        public RecyclerViewAdapter(List<String> datas, Context context) {
            if (datas != null) {
                this.datas = datas;
            } else {
                this.datas = new ArrayList<>();
            }
            this.context = context;
        }

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false);
            RecyclerViewHolder holder = new RecyclerViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {
            holder.tv.setText(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_num);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_action_add:
                hAdapter.addData(1);
                break;
            case R.id.id_action_delete:
                hAdapter.removeData(1);
                break;
            case R.id.id_action_gridview:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
                break;
            case R.id.id_action_listview:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.id_action_horizontalGridView:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));
                break;
        }
        return true;
    }
}
