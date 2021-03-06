package com.zczczy.leo.fuwuwangapp.activities;

import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.zczczy.leo.fuwuwangapp.R;
import com.zczczy.leo.fuwuwangapp.tools.AndroidTool;
import com.zczczy.leo.fuwuwangapp.viewgroup.MyTitleView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.WindowFeature;

/**
 * Created by leo on 2015/6/21.
 */
@EActivity(R.layout.web_view_layout)
@WindowFeature(value = {Window.FEATURE_NO_TITLE, Window.FEATURE_INDETERMINATE_PROGRESS})
public class BusinessInstituteActivity extends BaseActivity {


    @ViewById
    MyTitleView title;

    @ViewById
    WebView wv_web;


    WebSettings settings;

    @AfterViews
    void afterView() {

        title.setTitle("商学院");

        title.setListener(this);

        settings = wv_web.getSettings();

        settings.setJavaScriptEnabled(true);

        wv_web.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//优先使用缓存

        //wv_web.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存：

        wv_web.loadUrl("file:///android_asset/businessinstitute.html");

        //判断页面加载过程
        wv_web.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    // 网页加载完成
                    AndroidTool.dismissLoadDialog();
                } else {
                    AndroidTool.showLoadDialog(BusinessInstituteActivity.this);
                }
            }
        });

    }

    //改写物理按键——返回的逻辑
    //如果希望浏览的网页后退而不是退出浏览器，需要WebView覆盖URL加载，让它自动生成历史访问记录，那样就可以通过前进或后退访问已访问过的站点。
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(wv_web.canGoBack())
            {
                wv_web.goBack();//返回上一页面
                return true;
            }
            else
            {
                // System.exit(0);//退出程序
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
