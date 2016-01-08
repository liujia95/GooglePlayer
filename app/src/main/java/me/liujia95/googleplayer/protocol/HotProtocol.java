package me.liujia95.googleplayer.protocol;

import java.util.List;

import me.liujia95.googleplayer.base.BaseProtocol;

/**
 * Created by Administrator on 2016/1/8 9:48.
 */
public class HotProtocol extends BaseProtocol<List<String>> {

    @Override
    protected String getInterfacePath() {
        return "hot";
    }

//    @Override
//    protected List<String> parseJson(String json) {
//        Type type = new TypeToken<List<String>>() {
//        }.getType();
//        return new Gson().fromJson(json,type);
//    }
}
