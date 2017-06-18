package cn.chengwenjun.dressgudie;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectInfoActivity extends ListActivity {
    private ListView listView;
    //todo:sp获取数据判断email对应的collect
    protected static final String PREFS_NAME = "myPrefsFile";
    private SharedPreferences sp;
    private boolean remeberUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_info);
//        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.activity_collect_info,
//                new String[]{"top", "middle", "bottom"},
//                new int[]{R.id.tv_top, R.id.tv_middle, R.id.tv_button});
//        setListAdapter(adapter);
    }

//    private List<Map<String, Object>> getData() {
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("top", "G1");
//        map.put("middle", "google 1");
//        map.put("bottom", "");
//        list.add(map);
//
//        map = new HashMap<String, Object>();
//        map.put("top", "G2");
//        map.put("middle", "google 2");
//        map.put("bottom", "2");
//        list.add(map);
//
//        map = new HashMap<String, Object>();
//        map.put("top", "G3");
//        map.put("middle", "google 3");
//        map.put("bottom", "3");
//        list.add(map);
//
//        return list;
//    }
}

