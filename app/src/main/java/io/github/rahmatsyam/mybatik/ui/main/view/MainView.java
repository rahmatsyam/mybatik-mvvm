package io.github.rahmatsyam.mybatik.ui.main.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;

import io.github.rahmatsyam.mybatik.R;
import io.github.rahmatsyam.mybatik.data.model.ResultModel;
import io.github.rahmatsyam.mybatik.data.repo.RXBus;
import io.github.rahmatsyam.mybatik.ui.BatikDetailActivity;
import io.github.rahmatsyam.mybatik.ui.main.adapter.BatikPopularAdapter;
import io.github.rahmatsyam.mybatik.ui.main.adapter.ItemAdapter;
import io.github.rahmatsyam.mybatik.ui.main.baseview.BaseView;
import io.github.rahmatsyam.mybatik.ui.main.dialogfragment.BottomDialogFragment;
import io.github.rahmatsyam.mybatik.ui.main.viewmodel.MainViewModel;
import io.github.rahmatsyam.mybatik.util.click.OnItemClickListener;
import timber.log.Timber;

public class MainView extends BaseView {
    @BindView(R.id.rv_batik)
    RecyclerView rvBatik;

    @BindView(R.id.rv_batik_popular)
    RecyclerView rvBatikPopular;

    @BindView(R.id.rl_batik_header)
    RelativeLayout rlBatikHeader;

    @BindView(R.id.rl_popular_header)
    RelativeLayout rlPopularHeader;

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

        batikPopularAdapter.setClickItem(resultModel -> {

            BottomDialogFragment bottomDialogFragment = new BottomDialogFragment();
            bottomDialogFragment.show(getSupportFragmentManager(), BottomDialogFragment.TAG);
            RXBus.getBehaviorSubject().onNext(resultModel);

        });

    }

    @Override
    protected void initData() {

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.getDataList().observe(this, resultModels -> itemAdapter.setData(resultModels, true));

        mainViewModel.getDataBatikPopular().observe(this, resultModels -> batikPopularAdapter.setData(resultModels, true));

    }

    private void setRvBatik() {

        rlBatikHeader.removeAllViews();
        View view = getLayoutInflater().inflate(R.layout.header_item_batik, rlBatikHeader, false);
        TextView tvBatik = view.findViewById(R.id.tv_batik);
        TextView tvSeeAll = view.findViewById(R.id.tv_see_all);
        tvBatik.setText("Batik");
        tvSeeAll.setOnClickListener(v -> setDataIntent(0));

        rlBatikHeader.addView(view);

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

        rlPopularHeader.removeAllViews();
        View view = getLayoutInflater().inflate(R.layout.header_item_batik, rlPopularHeader, false);
        TextView tvBatik = view.findViewById(R.id.tv_batik);
        TextView tvSeeAll = view.findViewById(R.id.tv_see_all);
        tvBatik.setText("Popular Batik");
        tvSeeAll.setOnClickListener(v -> {
            Timber.d("Clicked");
            setDataIntent(1);
        });

        rlPopularHeader.addView(view);

        batikPopularAdapter = new BatikPopularAdapter();

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);

        rvBatikPopular.setLayoutManager(layoutManager);
        rvBatikPopular.setHasFixedSize(true);
        rvBatikPopular.setAdapter(batikPopularAdapter);

    }

    private void setDataIntent(int type){
        Intent intent = new Intent(getActivity(),ShowAllItemActivity.class);
        if (type == 0){
            intent.putExtra("TYPE", false);
        } else {
            intent.putExtra("TYPE", true);
        }
        startActivity(intent);
    }

}