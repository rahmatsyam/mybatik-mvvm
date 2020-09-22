package io.github.rahmatsyam.mybatik.ui.main.baseview;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(initLayout());

        ButterKnife.bind(this);

        initData();
        initViews();
        initEvents();

    }

    protected abstract Context getActivity();

    protected abstract int initLayout();

    protected abstract void initData();

    protected abstract void initViews();

    protected abstract void initEvents();



}
