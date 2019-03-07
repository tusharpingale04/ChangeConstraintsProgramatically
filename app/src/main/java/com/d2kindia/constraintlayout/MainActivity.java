package com.d2kindia.constraintlayout;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout imageView;
    LinearLayout linearLayout;
    ConstraintLayout mConstraintLayout;
    ConstraintSet mConstraintSet;
    boolean isClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        linearLayout = findViewById(R.id.linearLayout);
        mConstraintLayout = findViewById(R.id.constraintLayout);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isClicked){
                    isClicked = true;
                    changeConstraints();
                }else{
                    isClicked = false;
                    restoreConstraints();
                }

            }
        });
    }

    private void restoreConstraints() {
        mConstraintSet = new ConstraintSet();
        mConstraintSet.clone(mConstraintLayout);

        TransitionManager.beginDelayedTransition(mConstraintLayout);
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

    private void changeConstraints() {
        mConstraintSet = new ConstraintSet();
        mConstraintSet.clone(mConstraintLayout);

        TransitionManager.beginDelayedTransition(mConstraintLayout);
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
}
