package com.d2kindia.constraintlayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.AutoTransition;
import android.support.transition.TransitionManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private Context context;
    private List<ProductModel> productModelList;
    ConstraintSet mConstraintSet;

    public ProductsAdapter(Context context, List<ProductModel> productModelList) {
        this.context = context;
        this.productModelList = productModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_constraint, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final ProductModel model = productModelList.get(i);

        if(model.isSelected()){
            changeConstraints(viewHolder.mConstraintLayout);
        }else {
            restoreConstraints(viewHolder.mConstraintLayout);
        }
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(model.isSelected()){
                    productModelList.get(i).setSelected(false);
                    notifyDataSetChanged();
                }else {
                    productModelList.get(i).setSelected(true);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout, imageView;
        ConstraintLayout mConstraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            mConstraintLayout = itemView.findViewById(R.id.constraintLayout);

        }
    }

    private void restoreConstraints(ConstraintLayout mConstraintLayout) {
        mConstraintSet = new ConstraintSet();
        mConstraintSet.clone(mConstraintLayout);

        AutoTransition autoTransition = new AutoTransition();
        autoTransition.excludeChildren(R.id.recyclerView, true);
        TransitionManager.beginDelayedTransition(mConstraintLayout, autoTransition);

        mConstraintSet.constrainWidth(R.id.imageView, ConstraintSet.MATCH_CONSTRAINT);
        mConstraintSet.constrainWidth(R.id.linearLayout, ConstraintSet.MATCH_CONSTRAINT);

        mConstraintSet.connect(R.id.imageView,ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START);
        mConstraintSet.connect(R.id.imageView,ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM);
        mConstraintSet.connect(R.id.imageView,ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP);
        mConstraintSet.connect(R.id.imageView,ConstraintSet.END,R.id.linearLayout,ConstraintSet.START);

        mConstraintSet.connect(R.id.linearLayout,ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END);
        mConstraintSet.connect(R.id.linearLayout,ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM);
        mConstraintSet.connect(R.id.linearLayout,ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM);

        mConstraintSet.constrainPercentWidth(R.id.imageView, 0.35f);
        mConstraintSet.constrainPercentWidth(R.id.linearLayout, 0.65f);
        mConstraintSet.applyTo(mConstraintLayout);
    }

    private void changeConstraints(ConstraintLayout mConstraintLayout) {
        mConstraintSet = new ConstraintSet();
        mConstraintSet.clone(mConstraintLayout);

        AutoTransition autoTransition = new AutoTransition();
        autoTransition.excludeChildren(R.id.recyclerView, true);
        TransitionManager.beginDelayedTransition(mConstraintLayout, autoTransition);

        mConstraintSet.constrainWidth(R.id.imageView, ConstraintSet.MATCH_CONSTRAINT);
        mConstraintSet.constrainWidth(R.id.linearLayout, ConstraintSet.MATCH_CONSTRAINT);

        mConstraintSet.connect(R.id.imageView,ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START);
        mConstraintSet.connect(R.id.imageView,ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END);
        mConstraintSet.connect(R.id.imageView,ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP);

        mConstraintSet.connect(R.id.linearLayout,ConstraintSet.START,ConstraintSet.PARENT_ID,ConstraintSet.START);
        mConstraintSet.connect(R.id.linearLayout,ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END);
        mConstraintSet.connect(R.id.linearLayout,ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM);
        mConstraintSet.connect(R.id.linearLayout,ConstraintSet.TOP,R.id.imageView,ConstraintSet.BOTTOM);

        mConstraintSet.constrainPercentWidth(R.id.imageView, 1);
        mConstraintSet.constrainPercentWidth(R.id.linearLayout, 1);
        mConstraintSet.applyTo(mConstraintLayout);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
