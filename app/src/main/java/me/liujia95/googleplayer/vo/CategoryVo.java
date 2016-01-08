package me.liujia95.googleplayer.vo;

import java.util.List;

/**
 * Created by Administrator on 2016/1/8 11:18.
 *
 * @des Category对应服务器的Value Object
 */
public class CategoryVo {
    public List<CategoryInfos> infos;
    public String              title;

    public class CategoryInfos {
        public String name1;//休闲
        public String name2;//棋牌
        public String name3;//益智
        public String url1;//image/category_game_0.jpg
        public String url2;//image/category_game_1.jpg
        public String url3;//image/category_game_2.jpg
    }
}
