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

public class TabAllFragment extends Fragment {

    private static final String TAG = "TabButtonAll";
    private Button allButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_all_fragment, container, false);

        allButton = view.findViewById(R.id.tabButtonAll);

        allButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getActivity(), "Tab all klickad", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return view;
    }
}
