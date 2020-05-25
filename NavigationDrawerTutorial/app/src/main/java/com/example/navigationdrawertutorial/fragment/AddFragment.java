package com.example.navigationdrawertutorial.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.navigationdrawertutorial.R;
import com.example.navigationdrawertutorial.viewmodel.SharedViewModel;

public class AddFragment extends Fragment {

    private EditText etMessage;
    private Button button;
    private SharedViewModel model;

    public AddFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_fragment, container, false);
        etMessage = view.findViewById(R.id.et_message);
        button = view.findViewById(R.id.btn);
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = etMessage.getText().toString().trim();
                if (!message.isEmpty()) {
                    /*
                    SharedPreferences sharedPref = getActivity().getSharedPreferences("Message", Context.MODE_PRIVATE);
                    SharedPreferences.Editor spEditor = sharedPref.edit();
                    spEditor.putString("message", message);
                    spEditor.apply();
                     */
                    model.setMessage(message);
                }
            }
        });

        return view;
    }
}
