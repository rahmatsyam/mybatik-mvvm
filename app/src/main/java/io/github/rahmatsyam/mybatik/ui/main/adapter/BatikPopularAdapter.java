package io.github.rahmatsyam.mybatik.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.rahmatsyam.mybatik.R;
import io.github.rahmatsyam.mybatik.data.model.ResultModel;
import io.github.rahmatsyam.mybatik.util.ArrayHelper;
import io.github.rahmatsyam.mybatik.util.GlideCustom;
import io.github.rahmatsyam.mybatik.util.click.OnItemClickListener;

public class BatikPopularAdapter extends RecyclerView.Adapter<BaseViewHolder> implements OnItemClickListener {

    private List<ResultModel> resultModelList = new ArrayList<>();

    boolean data = false;

    OnItemClickListener mListener;

    public BatikPopularAdapter() {

    }

    public void setData(List<ResultModel> list, boolean limitData) {
        data = limitData;

        if (data) {
            resultModelList = ArrayHelper.filterLimitData(list);
        } else {
            resultModelList = list;
        }
        notifyDataSetChanged();

    }

    public void setClickItem(OnItemClickListener onItemClickListener) {
        mListener = onItemClickListener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_batik, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return resultModelList.size();
    }

    @Override
    public void OnItemClick(ResultModel resultModel) {

    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_batik_name)
        TextView tvBatikName;
        @BindView(R.id.tv_batik_detail)
        TextView tvDetailBatik;

        @BindView(R.id.iv_batik)
        ImageView ivBatik;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            super.onBind(position);

            ResultModel resultModel = resultModelList.get(position);

            tvBatikName.setText(resultModel.getNamaBatik());

            GlideCustom.setImageUrl(ivBatik, resultModel.getLinkBatik());

            itemView.setOnClickListener(v -> mListener.OnItemClick(resultModel));


        }


        @Override
        protected void clear() {

        }
    }
}
