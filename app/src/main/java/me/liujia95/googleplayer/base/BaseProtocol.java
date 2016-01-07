package me.liujia95.googleplayer.base;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import me.liujia95.googleplayer.utils.Constants;
import me.liujia95.googleplayer.utils.IOUtils;
import me.liujia95.googleplayer.utils.LogUtils;

/**
 * Created by Administrator on 2016/1/7 11:14.
 */
public abstract class BaseProtocol<T> {

    private static final String TAG = "BaseProtocol";


    public T loadData(int index) throws Exception {

        T t = getDataFromLocal(index);

        if (t != null) {
            LogUtils.d("去本地加载数据");
            return t;
        }

        return getDataFromNet(index);

    }

    /**
     * 去本地访问数据
     *
     * @param index
     */
    private T getDataFromLocal(int index) throws Exception {
        File file = new File(Constants.CACHE_DIR, getInterfacePath() + index);
        if (!file.exists()) {
            //如果文件不存在，说明还没有缓存
            return null;
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            //读取的第一行是时间戳
            String time = reader.readLine();
            if (Long.valueOf(time) + Constants.CACHE_TIME > System.currentTimeMillis()) {
                //如果还没超时，读本地的数据
                String json = reader.readLine();
                return parseJson(json);
            } else {
                //如果超时，去网络读取数据
                return null;
            }

        } finally {
            IOUtils.close(reader);
        }
    }

    /**
     * 去网络访问数据
     *
     * @param index
     * @return
     * @throws HttpException
     * @throws IOException
     */
    private T getDataFromNet(int index) throws Exception {
        HttpUtils httpUtils = new HttpUtils();
        String url = Constants.BASE_SERVER + getInterfacePath();

        RequestParams params = new RequestParams();
        params.addQueryStringParameter("index", "" + index);
        ResponseStream responseStream = httpUtils.sendSync(HttpRequest.HttpMethod.GET, url, params);
        String json = responseStream.readString();

        //写数据到本地
        writeJson(index, json);

        //解析json
        return parseJson(json);
    }

    /**
     * 写数据到本地
     *
     * @param index
     * @param json
     */
    private void writeJson(int index, String json) throws Exception {
        File file = new File(Constants.CACHE_DIR, getInterfacePath() + index);

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            //写时间戳
            writer.write(System.currentTimeMillis() + "");
            writer.newLine();
            //写数据
            writer.write(json);

        } finally {
            IOUtils.close(writer);
        }
    }

    protected abstract String getInterfacePath();

    protected abstract T parseJson(String json);

}
