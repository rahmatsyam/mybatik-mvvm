package io.github.rahmatsyam.mybatik.ui.main.dialogfragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.github.rahmatsyam.mybatik.R;
import io.github.rahmatsyam.mybatik.data.model.ResultModel;
import io.github.rahmatsyam.mybatik.data.repo.RXBus;
import io.github.rahmatsyam.mybatik.util.GlideCustom;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import timber.log.Timber;

public class BottomDialogFragment extends BottomSheetDialogFragment {

    public static final String TAG = "ActionBottomDialog";

    private Disposable disposable;

    private Unbinder unbinder;

    @BindView(R.id.iv_batik)
    ImageView ivBatik;

    @BindView(R.id.txt_batik_name)
    TextView tvBatikName;
    @BindView(R.id.txt_batik_detail)
    TextView tvBatikDetail;

    public BottomDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        disposable = RXBus.getBehaviorSubject().subscribeWith(new DisposableObserver<ResultModel>() {
            @Override
            public void onNext(@NonNull ResultModel resultModel) {
                Timber.d(resultModel.getNamaBatik());
                setView(resultModel);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

    private void setView(ResultModel resultModel) {
        tvBatikName.setText(resultModel.getNamaBatik());
        tvBatikDetail.setText(resultModel.getMaknaBatik());
        GlideCustom.setImageUrl(ivBatik, resultModel.getLinkBatik());
    }

    @Override
    public int getTheme() {
        return R.style.BottomSheetMenuTheme;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposable.dispose();
        unbinder.unbind();
    }
}