package com.example.xuqiang.app_framework.View.Test.test_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.libcore.activity.ActivityManager;
import com.example.libcore.application.RootApplication;
import com.example.libcore.toast.T;
import com.example.libcore_ui.activity.BaseActivity;
import com.example.xuqiang.app_framework.R;

/**
 * Description: 测试类B
 * Created by xuqiang on 2018/1/9 0009.
 */

public class ActivityB extends BaseActivity implements View.OnClickListener{
    private Button btn_go_to_activity;
    private Button btn_finish_top_activity;
    private Button btn_finish_first_A_activity;
    private Button btn_finish_all_A_activity;
    private Button btn_return_to_home_page;
    private Button btn_close;

    private TextView tv_all_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    protected void initView() {
        setContentView(R.layout.activity_test_activity_b);
        btn_go_to_activity = $(R.id.btn_go_to_activity);
        btn_finish_top_activity = $(R.id.btn_finish_top_activity);
        btn_finish_first_A_activity = $(R.id.btn_finish_first_A_activity);
        btn_finish_all_A_activity = $(R.id.btn_finish_all_A_activity);
        btn_return_to_home_page = $(R.id.btn_return_to_home_page);
        btn_close = $(R.id.btn_close);

        btn_go_to_activity.setOnClickListener(this);
        btn_finish_top_activity.setOnClickListener(this);
        btn_finish_first_A_activity.setOnClickListener(this);
        btn_finish_all_A_activity.setOnClickListener(this);
        btn_return_to_home_page.setOnClickListener(this);
        btn_close.setOnClickListener(this);

        tv_all_info = $(R.id.tv_all_info);
        addNavigationOnBottom((ViewGroup)$(R.id.fl_navigation));
    }

    protected void initData() {
    }

    private void showStackInfo(){
        T.getInstance().setGravity(Gravity.TOP).setyOffset(10).setyOffset(10).showShort(RootApplication.getInstance().toString());
        StringBuilder sb = new StringBuilder();
        sb.append("activityManager:   ").append(ActivityManager.getInstance().getStackInfo()).append("\n");
        tv_all_info.setText(sb.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_go_to_activity:
                startActivity(new Intent(this, ActivityA.class));
                break;
            case R.id.btn_finish_top_activity:
                ActivityManager.getInstance().finishActivity();
                break;
            case R.id.btn_finish_first_A_activity:
                ActivityManager.getInstance().finishLastActivity(ActivityA.class);
                break;
            case R.id.btn_finish_all_A_activity:
                ActivityManager.getInstance().finishAllActivity(ActivityA.class);
                break;
            case R.id.btn_return_to_home_page:
                ActivityManager.getInstance().finishAfterActivity(ActivityTestHomePage.class);
                break;
            case R.id.btn_close:
                ActivityManager.getInstance().finishAllActivityAndClose();
                break;
        }
        showStackInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showStackInfo();
    }
}
