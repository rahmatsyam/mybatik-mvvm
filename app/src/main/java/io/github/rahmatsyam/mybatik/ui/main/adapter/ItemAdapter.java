package io.github.rahmatsyam.mybatik.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowMetrics;
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

public class ItemAdapter extends RecyclerView.Adapter<BaseViewHolder> implements OnItemClickListener {

    private List<ResultModel> resultModelList = new ArrayList<>();

    OnItemClickListener mListener;

    private static final int TYPE_HEADER = 0, TYPE_ITEM = 1;

    protected boolean data = false;

    public ItemAdapter() {
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
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

//        if (viewType == TYPE_HEADER) {
//            return new VHHeader(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header_item_batik, viewGroup, false));
//        } else if (viewType == TYPE_ITEM) {
//            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_batik, viewGroup, false));
//        } else return null;

        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_batik, viewGroup, false));


    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
//        if (isHeader(position)) {
//            return;
//        }

        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        return isHeader(position) ? TYPE_HEADER : TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return resultModelList.size();
    }

    public boolean isHeader(int position) {
        return position == 0;
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

    static class VHHeader extends BaseViewHolder {

        public VHHeader(View itemView) {
            super(itemView);
        }

        @Override
        protected void clear() {

        }
    }

}
