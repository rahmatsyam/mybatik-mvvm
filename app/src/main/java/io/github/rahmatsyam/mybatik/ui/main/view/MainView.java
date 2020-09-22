package io.github.rahmatsyam.mybatik.ui.main.view;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;

import io.github.rahmatsyam.mybatik.R;
import io.github.rahmatsyam.mybatik.data.model.ResultModel;
import io.github.rahmatsyam.mybatik.ui.BatikDetailActivity;
import io.github.rahmatsyam.mybatik.ui.main.adapter.BatikPopularAdapter;
import io.github.rahmatsyam.mybatik.ui.main.adapter.ItemAdapter;
import io.github.rahmatsyam.mybatik.ui.main.baseview.BaseView;
import io.github.rahmatsyam.mybatik.ui.main.viewmodel.MainViewModel;
import io.github.rahmatsyam.mybatik.util.click.OnItemClickListener;

public class MainView extends BaseView {
    @BindView(R.id.rv_batik)
    RecyclerView rvBatik;

    @BindView(R.id.rv_batik_popular)
    RecyclerView rvBatikPopular;

    ItemAdapter itemAdapter;

    BatikPopularAdapter batikPopularAdapter;

    protected MainViewModel mainViewModel;

    @Override
    protected Context getActivity() {
        return this;
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        setRvBatik();
        setRecyclerBatikPopular();

    }

    @Override
    protected void initEvents() {
        itemAdapter.setClickItem(resultModel -> startActivity(new Intent(getActivity(), BatikDetailActivity.class)));

        batikPopularAdapter.setClickItem(new OnItemClickListener() {
            @Override
            public void OnItemClick(ResultModel resultModel) {

            }
        });

    }

    @Override
    protected void initData() {
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.getDataList().observe(this, resultModels -> itemAdapter.setData(resultModels, true));

        mainViewModel.getDataBatikPopular().observe(this, resultModels -> batikPopularAdapter.setData(resultModels, false));

    }

    private void setRvBatik() {

        itemAdapter = new ItemAdapter();

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return itemAdapter.isHeader(position) ? layoutManager.getSpanCount() : 1;
//            }
//        });

//        rvBatik.addItemDecoration(new CharacterItemDecoration(25));

        rvBatik.setLayoutManager(layoutManager);

        rvBatik.setHasFixedSize(true);
        rvBatik.setAdapter(itemAdapter);

    }

    private void setRecyclerBatikPopular() {

        batikPopularAdapter = new BatikPopularAdapter();

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);

        rvBatikPopular.setLayoutManager(layoutManager);
        rvBatikPopular.setHasFixedSize(true);
        rvBatikPopular.setAdapter(batikPopularAdapter);

    }

}