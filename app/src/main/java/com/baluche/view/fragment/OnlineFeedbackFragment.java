package com.baluche.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baluche.R;

import org.jetbrains.annotations.Nullable;

public class OnlineFeedbackFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_online_feedback, container, false);
        return v;
    }
}
