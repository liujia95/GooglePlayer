package me.liujia95.googleplayer.bean;

import java.util.List;


public class AppInfoBean {

    public String des;
    public String downloadUrl;
    public String iconUrl;
    public long   id;
    public String name;
    public String packageName;
    public long   size;
    public float  stars;

    public String                author;
    public String                date;
    public String                downloadNum;
    public List<AppInfoSafeBean> safe;
    public List<String>          screen;
    public String                version;

    public class AppInfoSafeBean {
        public String safeDes;
        public int    safeDesColor;
        public String safeDesUrl;
        public String safeUrl;
    }
}
