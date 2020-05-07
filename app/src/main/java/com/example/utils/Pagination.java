package com.example.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class Pagination extends RecyclerView.OnScrollListener {

    private GridLayoutManager layoutManager;

    protected Pagination(GridLayoutManager layoutManager){
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItem = layoutManager.getChildCount();
        int titalItemCount = layoutManager.getItemCount();
        int firdtVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

        if (!isLoginding()&& !isLoginpage()){
            if ((visibleItem+firdtVisibleItemPosition)>=titalItemCount && firdtVisibleItemPosition>=0 && titalItemCount >= getTiralPegers()){
                logMoreItem();
            }
        }
    }

    protected abstract void logMoreItem();

    protected abstract int getTiralPegers();

    protected abstract boolean isLoginpage();

    protected abstract boolean isLoginding();
}
