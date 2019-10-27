package com.haanhgs.sqlitedatabaseroomdemo.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class HomeFragment extends Fragment {

    private Context context;
    private Adapter adapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        adapter = new Adapter(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView rvHome = view.findViewById(R.id.rvHome);
        rvHome.setLayoutManager(new LinearLayoutManager(context));
        rvHome.setItemAnimator(new DefaultItemAnimator());
        rvHome.setAdapter(adapter);

        Model model = ViewModelProviders.of(this).get(Model.class);
        model.getAllPerson().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(List<Person> people) {
                adapter.setAllPerson(people);
            }
        });

        return view;
    }
}