package me.liujia95.googleplayer.protocol;

import me.liujia95.googleplayer.base.BaseProtocol;
import me.liujia95.googleplayer.bean.HomeBean;

/**
 * Created by Administrator on 2016/1/7 10:59.
 */
public class HomeProtocol extends BaseProtocol<HomeBean> {

    @Override
    protected String getInterfacePath() {
        return "home";
    }

//    @Override
//    protected HomeBean parseJson(String json) {
//        return new Gson().fromJson(json, HomeBean.class);
//    }


}
