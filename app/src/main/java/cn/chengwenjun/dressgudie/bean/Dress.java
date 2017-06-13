package cn.chengwenjun.dressgudie.bean;

import java.util.ArrayList;

import cn.chengwenjun.dressgudie.R;

/**
 * Created by yazawanico on 2017/6/11.
 */

public class Dress {
    public String title;
    public int resourceId;

    public Dress(String title, int resourceId) {
        this.title = title;
        this.resourceId = resourceId;
    }

    public static ArrayList<Dress> getAllTop() {
        ArrayList<Dress> list = new ArrayList<Dress>();
        list.add(new Dress("基础款水手服",R.drawable.top1));
        list.add(new Dress("白三本水手服",R.drawable.top2));
        list.add(new Dress("西式水手服",R.drawable.top3));
        list.add(new Dress("西式巧克力格马甲",R.drawable.top4));
        list.add(new Dress("西式水格马甲",R.drawable.top5));
        list.add(new Dress("白二本",R.drawable.top6));
        return list;
    }

    public static ArrayList<Dress> getAllMiddle() {
        ArrayList<Dress> list = new ArrayList<Dress>();
        list.add(new Dress("绀裙",R.drawable.middle1));
        list.add(new Dress("巧克力格裙",R.drawable.middle2));
        list.add(new Dress("水格裙",R.drawable.middle3));
        list.add(new Dress("蓝裙",R.drawable.middle4));
        return list;
    }
    public static ArrayList<Dress> getAllButtom() {
        ArrayList<Dress> list = new ArrayList<Dress>();
        list.add(new Dress("棕色搭扣款制服鞋",R.drawable.buttom1));
        list.add(new Dress("黑色基础款制服鞋",R.drawable.buttom2));
        return list;
    }
}
