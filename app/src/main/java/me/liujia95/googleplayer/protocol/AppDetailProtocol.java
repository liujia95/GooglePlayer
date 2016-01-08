package me.liujia95.googleplayer.protocol;

import java.util.HashMap;
import java.util.Map;

import me.liujia95.googleplayer.base.BaseProtocol;
import me.liujia95.googleplayer.bean.AppInfoBean;

/**
 * Created by Administrator on 2016/1/8 16:25.
 */
public class AppDetailProtocol extends BaseProtocol<AppInfoBean> {

    private String mPackageName;

    public AppDetailProtocol(String packageName) {
        this.mPackageName = packageName;
    }

    @Override
    protected String getInterfacePath() {
        return "detail";
    }

    @Override
    protected Map<String, String> getParamters() {
        Map<String, String> map = new HashMap<>();
        map.put("packageName", mPackageName);
        return map;

    }

//    @Override
//    protected AppInfoBean parseJson(String json) {
//        return new Gson().fromJson(json, AppInfoBean.class);
//    }
}
