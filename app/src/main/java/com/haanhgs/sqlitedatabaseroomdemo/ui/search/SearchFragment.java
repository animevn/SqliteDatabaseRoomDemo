package com.haanhgs.sqlitedatabaseroomdemo.ui.search;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.haanhgs.sqlitedatabaseroomdemo.Adapter;
import com.haanhgs.sqlitedatabaseroomdemo.R;
import com.haanhgs.sqlitedatabaseroomdemo.model.Model;
import com.haanhgs.sqlitedatabaseroomdemo.model.Person;
import java.util.List;

public class SearchFragment extends Fragment {

    private Context context;
    private Model model;
    private Adapter adapter;
    private EditText etSearch;
    private Button bnSearch;
    private RecyclerView rvSearch;



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        adapter = new Adapter(context);
    }

    private void initViews(View view){
        etSearch = view.findViewById(R.id.etSearchName);
        bnSearch = view.findViewById(R.id.bnSearch);
        rvSearch = view.findViewById(R.id.rvSearch);
        rvSearch.setLayoutManager(new LinearLayoutManager(context));
        rvSearch.setItemAnimator(new DefaultItemAnimator());
        rvSearch.setAdapter(adapter);
    }

    private void initModel(String name){
        model = ViewModelProviders.of(this).get(Model.class);
        adapter.setOwner(this);
        adapter.setModel(model);
        model.findPersonByName(name).observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(List<Person> people) {
                adapter.setAllPerson(people);
            }
        });
    }

    private void handleButtonClick(){
        bnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etSearch.getText())){
                    initModel(etSearch.getText().toString());
                }
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initViews(view);
        handleButtonClick();
        return view;
    }
}