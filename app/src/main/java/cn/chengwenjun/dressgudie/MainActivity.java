package cn.chengwenjun.dressgudie;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.List;

import cn.chengwenjun.dressgudie.Dao.CollectDao;
import cn.chengwenjun.dressgudie.bean.*;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter topSectionsPagerAdapter;
    private SectionsPagerAdapter mainSectionsPagerAdapter;
    private SectionsPagerAdapter bottomSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager topViewPager;
    private ViewPager mainViewPager;
    private ViewPager bottomViewPager;

    Intent intent;
    protected static final String PREFS_NAME = "myPrefsFile";
    private SharedPreferences sp;
    private boolean remeberUser;
    private CollectDao collectDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        topSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), Dress.getAllTop());
        mainSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), Dress.getAllMiddle());
        bottomSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), Dress.getAllBottom());
        // Set up the ViewPager with the sections adapter.
        topViewPager = (ViewPager) findViewById(R.id.cn_top);
        topViewPager.setAdapter(topSectionsPagerAdapter);


        mainViewPager = (ViewPager) findViewById(R.id.cn_main);
        mainViewPager.setAdapter(mainSectionsPagerAdapter);


        bottomViewPager = (ViewPager) findViewById(R.id.cn_bottom);
        bottomViewPager.setAdapter(bottomSectionsPagerAdapter);

        collectDao = new CollectDao(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                remeberUser = sp.getBoolean("remeber", false);
                if (remeberUser) {
                    //todo:添加收藏的功能
                    Collect collect = new Collect();
                    List<Collect> collectList = new ArrayList<Collect>();
                    collect.setTop(topViewPager.getCurrentItem());
                    collect.setMiddle(mainViewPager.getCurrentItem());
                    collect.setBottom(bottomViewPager.getCurrentItem());
                    collect.setEmail(new String(sp.getString("email", "")));
                    collectDao.insert(collect);
                    //collectList.add(collect);

                    Snackbar.make(view, "添加收藏成功，请到我的收藏中查找", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    Snackbar.make(view, "请先登录", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        sp = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        remeberUser = sp.getBoolean("remeber", false);
        if (remeberUser) {
            getMenuInflater().inflate(R.menu.menu_logined, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_main, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.it_login:
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.it_register:
                intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.it_exit:
                //退出登陆
                sp = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("email", "");
                editor.putString("password", "");
                editor.putBoolean("remeber", false);
                editor.commit();
                finish();
                break;
            case R.id.it_userInfo:
                intent = new Intent(MainActivity.this, UserInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.it_collectInfo:
                intent = new Intent(MainActivity.this, CollectInfoActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        private Dress dress;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(Dress dress) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            fragment.dress = dress;
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);//inflater用这个来把xml的内容转成view
            // 获取文字部分
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(dress.title);
//            textView.setText(getString(R.string.section_format, dress.title));
            //获取图片部分
            ImageView iv_main = (ImageView) rootView.findViewById(R.id.iv_main);
            iv_main.setImageResource(dress.resourceId);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Dress> dressList;

        public SectionsPagerAdapter(FragmentManager fm, ArrayList<Dress> dressList) {
            super(fm);
            this.dressList = dressList;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            Dress dress = dressList.get(position);
            return PlaceholderFragment.newInstance(dress);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return dressList.size();
        }


    }
}
