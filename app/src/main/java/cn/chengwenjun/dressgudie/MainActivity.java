package cn.chengwenjun.dressgudie;

import android.content.Intent;
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
import android.widget.TextView;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;

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
    private SectionsPagerAdapter buttomSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager topViewPager;

    private ViewPager mainViewPager;

    private ViewPager buttomViewPager;

    Intent intent;
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
        buttomSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), Dress.getAllButtom());
        // Set up the ViewPager with the sections adapter.
        topViewPager = (ViewPager) findViewById(R.id.cn_top);
        topViewPager.setAdapter(topSectionsPagerAdapter);


        mainViewPager = (ViewPager) findViewById(R.id.cn_main);
        mainViewPager.setAdapter(mainSectionsPagerAdapter);

        buttomViewPager = (ViewPager) findViewById(R.id.cn_buttom);
        buttomViewPager.setAdapter(buttomSectionsPagerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        //private int resourceId = 0;
        //private ArrayList<Dress> dressList;
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
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(dress.title);
            textView.setText(getString(R.string.section_format, dress.title));
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

//        @Override
//        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "水手服";
//                case 1:
//                    return "西式马甲";
//                case 2:
//                    return "测试";
//            }
//            return null;
//        }

    }
}
