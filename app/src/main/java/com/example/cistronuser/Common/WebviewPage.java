package com.example.cistronuser.Common;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.cistronuser.R;

public class WebviewPage extends AppCompatActivity {

    WebView webview;
    String urladdress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_webview_page);


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.setCancelable(false);

        try {
        webview=findViewById(R.id.Webview);
        String web=getIntent().getStringExtra("pdf");

        webview.requestFocus();
        urladdress="https://cistronsystems.in/beta1/medicalattach/"+web;
            Log.e(TAG, "onCreate: "+urladdress );
        webview.setWebViewClient(new MyBrowser());
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);



        webview.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webview.getSettings().setAppCacheEnabled(true);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setSaveFormData(true);
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);
        webview.getSettings().setEnableSmoothTransition(true);



        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setJavaScriptEnabled(true);


            webview.loadUrl(urladdress);
        }catch (Exception e){
            Log.e(TAG, "onCreate: "+e.getMessage());
        }



        webview.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view, int progress) {

                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(urladdress));
                // Log.e(TAG, "shouldOverrideUrlLoading: "+Uri.parse(url) );
                startActivity(intent);
                if (progress < 100) {
                    progressDialog.show();
                }
                if (progress == 100) {
                    progressDialog.dismiss();
                }
            }
        });

    }



    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(urladdress));
           // Log.e(TAG, "shouldOverrideUrlLoading: "+Uri.parse(url) );
            startActivity(intent);
            return true;
        }
    }
}