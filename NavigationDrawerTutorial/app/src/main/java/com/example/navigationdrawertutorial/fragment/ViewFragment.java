package com.example.navigationdrawertutorial.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.navigationdrawertutorial.R;
import com.example.navigationdrawertutorial.viewmodel.SharedViewModel;

public class ViewFragment extends Fragment {

    private TextView tvMessage;

    public ViewFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_fragment, container, false);

        tvMessage = view.findViewById(R.id.tv_showmessage);

        /*
        SharedPreferences sharedPref = getActivity().getSharedPreferences("Message", Context.MODE_PRIVATE);
        String message = sharedPref.getString("message",null);
        tvMessage.setText(message);
         */

        SharedViewModel model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        model.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.i("message: ", s);
                tvMessage.setText(s);
            }
        });

        return view;
    }
}
