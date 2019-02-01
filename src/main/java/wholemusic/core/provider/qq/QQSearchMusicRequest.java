package wholemusic.core.provider.qq;

import com.alibaba.fastjson.JSONObject;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import wholemusic.core.api.BaseRequest;
import wholemusic.core.config.Constants;
import wholemusic.core.model.Song;

import java.io.IOException;
import java.util.List;

/**
 * Created by haohua on 2018/2/11.
 */
class QQSearchMusicRequest extends BaseRequest<List<? extends Song>> {
    private final String mKeyword;
    private static final int PAGE_SIZE = Constants.PAGE_SIZE;
    private final int mPage;

    public QQSearchMusicRequest(String keyword, int page) {
        mKeyword = keyword;
        mPage = page;
    }

    @Override
    protected Request buildRequest() {
        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://c.y.qq.com/soso/fcgi-bin/search_for_qq_cp").newBuilder();
        urlBuilder.addQueryParameter("w", mKeyword);
        urlBuilder.addQueryParameter("p", String.valueOf(mPage));
        urlBuilder.addQueryParameter("n", String.valueOf(PAGE_SIZE));
        urlBuilder.addQueryParameter("format", "json");
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(urlBuilder.build());
        requestBuilder.addHeader("User-Agent", QQMusicApi.USER_AGENT);
        requestBuilder.addHeader(Constants.REFERER, "http://m.y.qq.com");
        requestBuilder.get();
        final Request request = requestBuilder.build();
        return request;
    }

    @Override
    protected List<QQSong> parseResult(Response response) throws IOException {
        String data = responseBodyToString(response);
        JSONObject json = JSONObject.parseObject(data);
        List<QQSong> list = json.getJSONObject("data").getJSONObject("song").getJSONArray("list")
                .toJavaList(QQSong.class);
        return list;
    }
}
