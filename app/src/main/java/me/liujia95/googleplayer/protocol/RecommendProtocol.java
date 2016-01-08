package me.liujia95.googleplayer.protocol;

import java.util.List;

import me.liujia95.googleplayer.base.BaseProtocol;

/**
 * Created by Administrator on 2016/1/8 15:11.
 */
public class RecommendProtocol extends BaseProtocol<List<String>> {

    @Override
    protected String getInterfacePath() {
        return "recommend";
    }

//    @Override
//    protected List<String> parseJson(String json) {
//        Gson gson = new Gson();
//        return gson.fromJson(json, new TypeToken<List<String>>() {
//        }.getType());
//    }
}
