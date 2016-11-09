package com.example.kunal.to_dolist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment {

    TextView textView;
    public PageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_page_layout,container,false);
        textView=(TextView)view.findViewById(R.id.pagetext);
        Bundle bundle=getArguments();
        String message=Integer.toString(bundle.getInt("count"));
        textView.setText("this is a "+message+"swipe page");
        return view;
    }

}
