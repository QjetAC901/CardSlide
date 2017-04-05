package com.cgq.cardslide;

import android.support.v7.app.AppCompatActivity;
//
//
///**
// * 侧滑栏   和  FragmentTransaction切换方式
// */
public class ViewPagerActivity extends AppCompatActivity{
//
//    private static final String TAG = "ViewPagerActivity";
//
//    private ViewPager vp;
//    private List<Fragment> fragments;
//    private FragmentAdapter fragmentAdapter;
//
//    private Button btnHome, btnGuide, btnTalk, btnCenter;
//    private Button[] btnTabs;
//    private int currentIndex;
//
//    //侧滑栏控件
//    private NavigationView navigationView;
//    //侧滑栏的父级容器
//    private DrawerLayout drawerLayout_main;
//    //侧滑栏的头布局
//    private View headerView;
//    private ActionBar actionBar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_pager);
//        vp = (ViewPager) findViewById(R.id.vp_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
//        toolbar.setTitle(getString(R.string.app_name));
//
//        setSupportActionBar(toolbar);
//        StatusBarCompat.compat(this);
//
//        fragments = new ArrayList<>();
//        fragments.add(new HomeFragment());
//        fragments.add(new GuideFragment());
//        fragments.add(new TalkFragment());
//        fragments.add(new CenterFragment());
//        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
//        vp.setAdapter(fragmentAdapter);
//        vp.addOnPageChangeListener(this);
//
//        btnHome = (Button) findViewById(R.id.btn_home);
//        btnGuide = (Button) findViewById(R.id.btn_guide);
//        btnTalk = (Button) findViewById(R.id.btn_talk);
//        btnCenter = (Button) findViewById(R.id.btn_center);
//        btnHome.setSelected(true);
//        btnHome.setOnClickListener(this);
//        btnGuide.setOnClickListener(this);
//        btnTalk.setOnClickListener(this);
//        btnCenter.setOnClickListener(this);
//        btnTabs = new Button[]{btnHome, btnGuide, btnTalk, btnCenter};
//
//        drawerLayout_main = (DrawerLayout) findViewById(R.id.dl);
//        navigationView = (NavigationView) findViewById(R.id.id_nv_menu);
//        headerView = navigationView.getHeaderView(0);
//        actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_dashboard);
//        }
//        drawerLayout_main.openDrawer(navigationView);
//        headerView.setOnClickListener(this);
//        navigationView.setNavigationItemSelectedListener(this);
//    }
//
//    @Override
//    public void onClick(View view) {
//        btnTabs[currentIndex].setSelected(false);
//        switch (view.getId()) {
//            case R.id.btn_home:
//                if (currentIndex != 0) {
//                    currentIndex = 0;
//                }
//                break;
//            case R.id.btn_guide:
//                if (currentIndex != 1) {
//                    currentIndex = 1;
//                }
//                break;
//            case R.id.btn_talk:
//                if (currentIndex != 2) {
//                    currentIndex = 2;
//                }
//                break;
//            case R.id.btn_center:
//                if (currentIndex != 3) {
//                    currentIndex = 3;
//                }
//                break;
//        }
//        vp.setCurrentItem(currentIndex);
//        btnTabs[currentIndex].setSelected(true);
//    }
//
//
//
//    // fragment的adapter
//    private class FragmentAdapter extends FragmentPagerAdapter {
//
//        public FragmentAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return fragments.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return fragments.size();
//        }
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home://标题栏左上角的menu键  弹出侧滑栏
//                drawerLayout_main.openDrawer(navigationView);
//                break;
//        }
//        return true;
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.nav_home:
//                Log.i(TAG, "onNavigationItemSelected: ---------->home");
//                break;
//            case R.id.nav_messages:
//                Log.i(TAG, "onNavigationItemSelected: -------->messages");
//                break;
//            case R.id.nav_friends:
//                Log.i(TAG, "onNavigationItemSelected: --------->friends");
//                break;
//            case R.id.nav_discussion:
//                Log.i(TAG, "onNavigationItemSelected: ---------->discussion");
//                break;
//            case R.id.menu2_discussion:
//                Log.i(TAG, "onNavigationItemSelected: ---------->menu2_discussion");
//                break;
//            case R.id.menu2_forum:
//                Log.i(TAG, "onNavigationItemSelected: ----------->menu2_forum");
//                break;
//        }
//        Log.i(TAG, "onNavigationItemSelected: ItemId:" + item.getItemId());
//        return true;
//    }
//
//
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//        btnTabs[currentIndex].setSelected(false);
//        switch (position) {
//            case 0:
//                currentIndex = 0;
//                break;
//            case 1:
//                currentIndex = 1;
//                break;
//            case 2:
//                currentIndex = 2;
//                break;
//            case 3:
//                currentIndex = 3;
//                break;
//        }
//        btnTabs[currentIndex].setSelected(true);
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//
//    }
//
//
////    getSupportFragmentManager().beginTransaction().add(R.id.content_fragment, fragments[currentTabIndex]).commit();
////-----------------------------一下是切换的方法---------------------------------
////  FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
////    if (currentTabIndex != index) {
////        if (currentTabIndex < index) {
////            ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
////        } else {
////            ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out);
////        }
////        ft.hide(fragments[currentTabIndex]);
////        if (!fragments[index].isAdded()) {
////            ft.add(R.id.content_fragment, fragments[index]);
////        }
////        ft.show(fragments[index]).commit();
////        btnTabs[currentTabIndex].setSelected(false);
////        btnTabs[index].setSelected(true);
////        currentTabIndex = index;
}
