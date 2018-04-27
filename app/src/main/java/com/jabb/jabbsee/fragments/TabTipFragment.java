package com.jabb.jabbsee.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.jabb.jabbsee.R;

public class TabTipFragment extends Fragment {

    private static final String TAG = "TabButtonTip";
    private Button tipButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_tip_fragment, container, false);

        tipButton = view.findViewById(R.id.tabButtonTip);

        tipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getActivity(), "Tab tip klickad", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return view;
    }
}
