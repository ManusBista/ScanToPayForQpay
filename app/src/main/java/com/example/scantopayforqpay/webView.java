package com.example.scantopayforqpay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scantopayforqpay.Model.channelLogin;
import com.example.scantopayforqpay.Utils.Constants;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class webView extends AppCompatActivity {

    WebView webview;
    ArrayList<channelLogin> cls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        String uri = getIntent().getStringExtra("url");

        webview=findViewById(R.id.webview);

        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings()
                .setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setBuiltInZoomControls(true);
//        webview.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//
//            @Override
//            public void onPageFinished(WebView view, final String url) {
////                dialog.cancel();
//            }
//        });
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                //Required functionality here
//                view.loadUrl(url);
                return super.onJsAlert(view, url, message, result);
            }
        });

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith(url)) {
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                    StringRequest request = new StringRequest(Request.Method.POST, Constants.GET_CHANNELLOGIN_URL, response -> {
                        try {
//                    Log.e("err",response);
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString("status").equals("success")) {
                                JSONArray categoriesArray = jsonObject.getJSONArray("cls");
                                for (int i = 0; i < categoriesArray.length(); i++) {
                                    JSONObject object = categoriesArray.getJSONObject(i);
                                    channelLogin cl = new channelLogin(
                                            object.getString("appId"),
                                            object.getString("id"),
                                            object.getString("lat"),
                                            object.getString("lng"),
                                            object.getString("industryCode"),
                                            object.getString("industryReserve")

                                            );
                                    cls.add(cl);
                                }

                            } else {
//                        aa
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> {

                    });
                    queue.add(request);
                    Toast.makeText(webView.this, "ok", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    view.loadUrl(url);
                    Toast.makeText(webView.this, "not ok", Toast.LENGTH_SHORT).show();
                    return false;
                }
//                view.loadUrl(url);
//                return true;
            }
        });

        webview
                .loadUrl(uri);
    }


}

//https://unionpayintl.qcms.com/?industryCode=A0000058&industryReserve=groupMerchantId-200939_groupBrandId-200943_tableId-159fce7694d246b983e6bc0e3aac2529