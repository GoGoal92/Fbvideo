package gogoal.myfb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import gogoal.R;
import gogoal.myfb.utils.Mytoast;

/**
 * Created by Go Goal on 5/26/2017.
 */

public class webviewdownloader extends AppCompatActivity {


    WebView wb;
    ProgressBar pg;
    String url, clas;
    static AppCompatActivity ac;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mywebview);
        ac = this;

        getSupportActionBar().setTitle("Download");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        url = getIntent().getExtras().getString("url");
        clas = getIntent().getExtras().getString("cla");
        wb = (WebView) findViewById(R.id.mywebview);
        pg = (ProgressBar) findViewById(R.id.pg);

        //wb.requestFocus();
        //wb.requestFocusFromTouch();

        wb.setWebViewClient(new WebViewClient() {


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pg.setVisibility(View.GONE);
                wb.setVisibility(View.VISIBLE);

                wb.loadUrl("javascript:(function() { "
                        + "var el = document.querySelectorAll('div[data-sigil]');"
                        + "for(var i=0;i<el.length; i++)"
                        + "{"
                        + "var sigil = el[i].dataset.sigil;"
                        + "if(sigil.indexOf('inlineVideo') > -1){"
                        + "delete el[i].dataset.sigil;"
                        + "var jsonData = JSON.parse(el[i].dataset.store);"
                        + "el[i].setAttribute('onClick', 'webviewdownloader.processVideo(\"'+jsonData['src']+'\");');"
                        + "}" + "}" + "})()");


            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);

                wb.loadUrl("javascript:(function prepareVideo() { "
                        + "var el = document.querySelectorAll('div[data-sigil]');"
                        + "for(var i=0;i<el.length; i++)"
                        + "{"
                        + "var sigil = el[i].dataset.sigil;"
                        + "if(sigil.indexOf('inlineVideo') > -1){"
                        + "delete el[i].dataset.sigil;"
                        + "console.log(i);"
                        + "var jsonData = JSON.parse(el[i].dataset.store);"
                        + "el[i].setAttribute('onClick', 'webviewdownloader.processVideo(\"'+jsonData['src']+'\",\"'+jsonData['videoID']+'\");');"
                        + "}" + "}" + "})()");
                wb.loadUrl("javascript:( window.onload=prepareVideo;"
                        + ")()");


            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                pg.setVisibility(View.GONE);
                if (errorCode == -2) {
                    Mytoast.show(ac, "Networl fail");
                    ac.finish();
                }

            }
        });


        wb.addJavascriptInterface(ac, "webviewdownloader");
        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        CookieSyncManager.getInstance().startSync();

        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setDomStorageEnabled(true);
        wb.loadUrl(url);


    }


    @JavascriptInterface
    public void processVideo(final String vidData, final String vidID) {

        Intent newintent = new Intent(ac, FakeDialog.class);
        newintent.putExtra("url", vidData);
        newintent.putExtra("type", clas);
        startActivity(newintent);
        overridePendingTransition(0, 0);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                ac.overridePendingTransition(0, 0);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public static void finishactivity() {
        ac.finish();
    }
}
