package me.liujia95.googleplayer.protocol;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import me.liujia95.googleplayer.base.BaseProtocol;
import me.liujia95.googleplayer.bean.CategoryBean;
import me.liujia95.googleplayer.vo.CategoryVo;

/**
 * Created by Administrator on 2016/1/8 11:17.
 */
public class CategoryProtocol extends BaseProtocol<List<CategoryBean>> {

    @Override
    protected String getInterfacePath() {
        return "category";
    }

    @Override
    protected List<CategoryBean> parseJson(String json) {

        Gson gson = new Gson();
        List<CategoryVo> voDatas = gson.fromJson(json, new TypeToken<List<CategoryVo>>() {
        }.getType());

        List<CategoryBean> mDatas = new ArrayList<>();

        for (int i = 0; i < voDatas.size(); i++) {
            CategoryBean titleBean = new CategoryBean();
            CategoryVo vo = voDatas.get(i);
            titleBean.title = vo.title;
            titleBean.type = CategoryBean.TYPE_TITLE;
            mDatas.add(titleBean);

            for (int j = 0; j < vo.infos.size(); j++) {
                CategoryBean itemBean = new CategoryBean();
                List<CategoryVo.CategoryInfos> infos = vo.infos;
                itemBean.type = CategoryBean.TYPE_ITEM;
                itemBean.icon1 = infos.get(j).url1;
                itemBean.icon2 = infos.get(j).url2;
                itemBean.icon3 = infos.get(j).url3;

                itemBean.name1 = infos.get(j).name1;
                itemBean.name2 = infos.get(j).name2;
                itemBean.name3 = infos.get(j).name3;

                mDatas.add(itemBean);
            }
        }

        return mDatas;
    }
}
