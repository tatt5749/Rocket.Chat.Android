package chat.rocket.android.helper;


import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class OkHttpHelper {
    private static OkHttpClient sHttpClientForWS;
    public static OkHttpClient getClientForWebSocket() {

        if (sHttpClientForWS==null) {
            sHttpClientForWS = new OkHttpClient.Builder()
                    .readTimeout(0, TimeUnit.NANOSECONDS)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();
        }
        return sHttpClientForWS;
    }
}
