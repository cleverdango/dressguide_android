package cn.chengwenjun.dressguide;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;


import java.util.ArrayList;

import cn.chengwenjun.dressguide.Dao.CollectDao;
import cn.chengwenjun.dressguide.bean.*;

public class MyCollectActivity extends AppCompatActivity {
    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;
    private SharedPreferences sp;
    protected static final String PREFS_NAME = "myPrefsFile";
    private ListView listView;
    private Collect collect;
    private ArrayList<Collect> collectList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);

        sp = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String email = sp.getString("email", "");

        CollectDao collectDao = new CollectDao(this);
        collectList = collectDao.queryAllByEmail(email);
        //todo:从list取出collect
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), collectList);
        viewPager = (ViewPager) findViewById(R.id.vp_collect);
        viewPager.setAdapter(sectionsPagerAdapter);

    }


    public static class PlaceholderFragment extends Fragment {

        private Collect collect;

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(Collect collect) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            fragment.collect = collect;
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_collect, container, false);//inflater用这个来把xml的内容转成view

            ImageView iv_collect_top = (ImageView) rootView.findViewById(R.id.iv_collect_top);
            ImageView iv_collect_middle = (ImageView) rootView.findViewById(R.id.iv_collect_middle);
            ImageView iv_collect_bottom = (ImageView) rootView.findViewById(R.id.iv_collect_bottom);
            int top = collect.getTop();
            int middle = collect.getMiddle();
            int bottom = collect.getBottom();
            ArrayList<Dress> topList = new ArrayList<Dress>(Dress.getAllTop());
            ArrayList<Dress> middleList = new ArrayList<Dress>(Dress.getAllMiddle());
            ArrayList<Dress> bottomList = new ArrayList<Dress>(Dress.getAllBottom());

            iv_collect_top.setImageResource(topList.get(top).resourceId);
            iv_collect_middle.setImageResource(middleList.get(middle).resourceId);
            iv_collect_bottom.setImageResource(bottomList.get(bottom).resourceId);
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Collect> collectList;
        //todo:获取收藏夹

        public SectionsPagerAdapter(FragmentManager fm, ArrayList<Collect> collectList) {
            super(fm);
            this.collectList = collectList;
            //todo:改collectList
        }

        @Override
        public Fragment getItem(int position) {
            Collect collect = collectList.get(position);
            return PlaceholderFragment.newInstance(collect);
        }

        @Override
        public int getCount() {

            return collectList.size();
        }


    }

}



