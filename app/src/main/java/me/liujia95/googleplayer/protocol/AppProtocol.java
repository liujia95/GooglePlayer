package me.liujia95.googleplayer.protocol;

import java.util.List;

import me.liujia95.googleplayer.base.BaseProtocol;
import me.liujia95.googleplayer.bean.AppInfoBean;

/**
 * Created by Administrator on 2016/1/7 13:31.
 */
public class AppProtocol extends BaseProtocol<List<AppInfoBean>> {
    @Override
    protected String getInterfacePath() {
        return "app";
    }

//    @Override
//    protected List<AppInfoBean> parseJson(String json) {
//        Gson gson = new Gson();
//        Type type = new TypeToken<List<AppInfoBean>>() {
//        }.getType();
//        return gson.fromJson(json, type);
//    }
}
