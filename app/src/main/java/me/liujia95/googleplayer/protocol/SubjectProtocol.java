package me.liujia95.googleplayer.protocol;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import me.liujia95.googleplayer.base.BaseProtocol;
import me.liujia95.googleplayer.bean.SubjectBean;

/**
 * Created by Administrator on 2016/1/7 18:10.
 */
public class SubjectProtocol extends BaseProtocol<List<SubjectBean>> {


    @Override
    protected String getInterfacePath() {
        return "subject";
    }

    @Override
    protected List<SubjectBean> parseJson(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<SubjectBean>>() {
        }.getType();
        return gson.fromJson(json, type);
    }
}
