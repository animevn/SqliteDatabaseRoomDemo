package com.haanhgs.sqlitedatabaseroomdemo.ui.delete;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import com.haanhgs.sqlitedatabaseroomdemo.R;
import com.haanhgs.sqlitedatabaseroomdemo.model.Model;

public class DeleteFragment extends Fragment {

    private Model model;
    private EditText etDelete;
    private Button bnDelete;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    private void initViews(View view){
        etDelete = view.findViewById(R.id.etDeleteName);
        bnDelete = view.findViewById(R.id.bnDelete);
        model = ViewModelProviders.of(this).get(Model.class);
    }

    private void handleButton(){
        bnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etDelete.getText())){
                    model.deletePersonByName(etDelete.getText().toString());
                    Navigation.findNavController(v).popBackStack();
                }
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete, container, false);
        initViews(view);
        handleButton();
        return view;
    }
}