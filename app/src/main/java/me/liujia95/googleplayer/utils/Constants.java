package me.liujia95.googleplayer.utils;

/**
 * Created by Administrator on 2016/1/6 19:42.
 */
public interface Constants {
    String BASE_SERVER = "http://10.0.3.2:8080/GooglePlayServer/";
    String BASE_IMAGE  = BASE_SERVER + "image?name=";

    int    PAGER_SIZE = 20;//一页的数据
    int    CACHE_TIME = 30 * 1000;//缓存的保存时长
    String CACHE_DIR  = FileUtils.getDir("json");//缓存的根目录

}
