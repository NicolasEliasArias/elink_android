package com.example.nicol.elink.Fragments;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.nicol.elink.Activitys.ActivityEmprendedor;
import com.example.nicol.elink.Activitys.ActivityInversor;
import com.example.nicol.elink.R;

public class AllProjectsFragment extends Fragment {

    private View rootview;
    private ActivityInversor context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.all_proyects_fragment, container,false);
        context = (ActivityInversor) getActivity();
        return rootview;
    }
}
