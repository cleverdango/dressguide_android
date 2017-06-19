package cn.chengwenjun.dressguide.bean;

/**
 * Created by yazawanico on 2017/6/16.
 */

public class Collect {
    private int top;
    private int middle;
    private int bottom;
    private String email;

    public Collect(int top, int middle, int bottom, String email) {
        this.top = top;
        this.middle = middle;
        this.bottom = bottom;
        this.email = email;
    }

    public Collect() {
    }

    public int getTop() {
        return top;
    }

    public int getMiddle() {
        return middle;
    }

    public int getBottom() {
        return bottom;
    }

    public String getEmail() {
        return email;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public void setMiddle(int middle) {
        this.middle = middle;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
