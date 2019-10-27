package com.haanhgs.sqlitedatabaseroomdemo.ui.insert;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.haanhgs.sqlitedatabaseroomdemo.R;
import com.haanhgs.sqlitedatabaseroomdemo.model.Job;
import com.haanhgs.sqlitedatabaseroomdemo.model.Model;
import com.haanhgs.sqlitedatabaseroomdemo.model.Person;
import java.util.ArrayList;
import java.util.List;

public class InsertFragment extends Fragment {

    private Context context;
    private List<String> strings = new ArrayList<>();
    private List<Integer>jobIds = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private int spinnerPostitionSelected;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_insert, container, false);


        final EditText etInsertName = view.findViewById(R.id.etInsertName);
        final EditText etInsertAge = view.findViewById(R.id.etInsertAge);

        Button bnInsert = view.findViewById(R.id.bnInsert);

        //setup spinner
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, strings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Model model = ViewModelProviders.of(this).get(Model.class);
        model.getAllJob().observe(this, new Observer<List<Job>>() {
            @Override
            public void onChanged(List<Job> jobs) {
                for (int i = 0; i < jobs.size(); i++){
                    strings.add(jobs.get(i).getJobName());
                    jobIds.add(jobs.get(i).getJobId());
                }
                adapter.notifyDataSetChanged();
            }
        });
        Spinner spJob = view.findViewById(R.id.spJob);
        spJob.setAdapter(adapter);
        spJob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerPostitionSelected = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        bnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etInsertAge.getText())
                && !TextUtils.isEmpty(etInsertName.getText())){


                    Person person = new Person(0, etInsertName.getText().toString(),
                            jobIds.get(spinnerPostitionSelected),
                            Integer.parseInt(etInsertAge.getText().toString()));
                    model.insertPerson(person);
                }
            }
        });

        return view;
    }
}