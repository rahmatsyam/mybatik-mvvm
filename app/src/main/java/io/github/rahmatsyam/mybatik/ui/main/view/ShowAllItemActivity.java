package io.github.rahmatsyam.mybatik.ui.main.view;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import butterknife.BindView;
import io.github.rahmatsyam.mybatik.R;
import io.github.rahmatsyam.mybatik.data.model.ResultModel;
import io.github.rahmatsyam.mybatik.ui.main.adapter.ItemAdapter;
import io.github.rahmatsyam.mybatik.ui.main.baseview.BaseView;
import io.github.rahmatsyam.mybatik.ui.main.viewmodel.MainViewModel;
import io.github.rahmatsyam.mybatik.util.click.OnItemClickListener;

public class ShowAllItemActivity extends BaseView {

    @BindView(R.id.rv_batik)
    RecyclerView rvBatik;

    ItemAdapter itemAdapter;

    protected MainViewModel mainViewModel;


    @Override
    protected Context getActivity() {
        return this;
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_show_all_item;
    }

    @Override
    protected void initData() {
        boolean check = getIntent().getBooleanExtra("TYPE", false);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        if (!check) mainViewModel.getDataList().observe(this, resultModels -> itemAdapter.setData(resultModels, false));
        else mainViewModel.getDataBatikPopular().observe(this, resultModels -> itemAdapter.setData(resultModels, false));
    }

    @Override
    protected void initViews() {
        setRvBatik();
    }

    @Override
    protected void initEvents() {
        itemAdapter.setClickItem(new OnItemClickListener() {
            @Override
            public void OnItemClick(ResultModel resultModel) {

            }
        });
    }

    private void setRvBatik() {

        itemAdapter = new ItemAdapter();

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        rvBatik.setLayoutManager(layoutManager);

        rvBatik.setHasFixedSize(true);
        rvBatik.setAdapter(itemAdapter);

    }

}