package com.karrel.colloc;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.view.View;

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