package io.github.rahmatsyam.mybatik.util;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class CharacterItemDecoration extends RecyclerView.ItemDecoration {

    private int offSet;

    public CharacterItemDecoration(int offSet) {
        this.offSet = offSet;

    }

    @Override
    public void getItemOffsets(@NotNull Rect outRect, View view, @NotNull RecyclerView parent, @NotNull RecyclerView.State state) {

        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();

        if (layoutParams.getSpanIndex() % 2 == 0) {
            outRect.top = offSet;
            outRect.left = offSet;
            outRect.right = offSet / 2;
        } else {
            outRect.top = offSet;
            outRect.right = offSet;
            outRect.left = offSet / 2;

        }

    }


}
