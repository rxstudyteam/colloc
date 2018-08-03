package com.karrel.colloc;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class JavaTestClass {
    private void test(){
        List<String> itemList = new ArrayList<>();

        if(itemList.get(0) instanceof String){

        }
    }
}

@SuppressLint("ValidFragment")
class FragmentNeo extends Fragment{
    public FragmentNeo(View view){

    }
}


class CustomView extends ViewGroup {
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}