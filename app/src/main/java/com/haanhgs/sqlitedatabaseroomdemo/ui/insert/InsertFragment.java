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
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import com.haanhgs.sqlitedatabaseroomdemo.R;
import com.haanhgs.sqlitedatabaseroomdemo.model.Job;
import com.haanhgs.sqlitedatabaseroomdemo.model.Model;
import com.haanhgs.sqlitedatabaseroomdemo.model.Person;
import com.haanhgs.sqlitedatabaseroomdemo.model.RoomRepo;
import java.util.ArrayList;
import java.util.List;

public class InsertFragment extends Fragment {

    private Context context;
    private final List<String> strings = new ArrayList<>();
    private final List<Integer>jobIds = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private int spinnerPostitionSelected;
    private Model model;
    private boolean isNew = true;
    private EditText etInsertName;
    private EditText etInsertAge;
    private Button bnInsert;
    private Spinner spJob;
    private Person currentPerson;
    private int personId;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        if (getArguments() != null){
            isNew = getArguments().getBoolean("isNew");
            personId = getArguments().getInt("ID");
        }
    }

    private void initViews(View view){
        etInsertName = view.findViewById(R.id.etInsertName);
        etInsertAge = view.findViewById(R.id.etInsertAge);
        bnInsert = view.findViewById(R.id.bnInsert);
        spJob = view.findViewById(R.id.spJob);
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, strings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (isNew){
            bnInsert.setText(R.string.insert);
        }else {
            bnInsert.setText(R.string.update);
        }
    }

    private void initModel(){
        model = ViewModelProviders.of(this).get(Model.class);
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
    }



    private void handleSpinner(){
        spJob.setAdapter(adapter);
        spJob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerPostitionSelected = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void updateSpinnerWithID(int jobId){
        model.findJobById(jobId).observe(this, new Observer<Job>() {
            @Override
            public void onChanged(Job job) {
                if (job != null){
                    spJob.setSelection(adapter.getPosition(job.getJobName()));
                }
            }
        });
    }

    private void updateCurrentViews(){
        model.findPersonById(personId).observe(this, new Observer<Person>() {
            @Override
            public void onChanged(Person person) {
                currentPerson = person;
                etInsertAge.setText(String.format("%s", currentPerson.getAge()));
                etInsertName.setText(currentPerson.getName());
                int jobId = currentPerson.getJobId();
                updateSpinnerWithID(currentPerson.getJobId());
            }
        });
    }

    private void updateViewsIfNotNew(){
        if (!isNew){
            updateCurrentViews();
        }
    }

    private static boolean isInteger(String string){
        try{
            return Integer.parseInt(string) > 0;
        }catch (NumberFormatException e){
            e.printStackTrace();
            return false;
        }
    }

    private void createNewRecord(){
        if (isNew){
            if (!TextUtils.isEmpty(etInsertAge.getText())
                    && !TextUtils.isEmpty(etInsertName.getText())
                    && isInteger(etInsertAge.getText().toString())){
                Person person = new Person(0, etInsertName.getText().toString(),
                        jobIds.get(spinnerPostitionSelected),
                        Integer.parseInt(etInsertAge.getText().toString()));
                model.insertPerson(person);
            } else if (!isInteger(etInsertAge.getText().toString())){
                Toast.makeText(context, "wrong age :D", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void updateRecord(){
        if (!isNew){
            if (!TextUtils.isEmpty(etInsertAge.getText())
                    && !TextUtils.isEmpty(etInsertName.getText())
                    && isInteger(etInsertAge.getText().toString())){
                currentPerson.setAge(Integer.parseInt(etInsertAge.getText().toString()));
                currentPerson.setName(etInsertName.getText().toString());
                currentPerson.setJobId(spJob.getSelectedItemPosition() + 1);
                model.updatePerson(currentPerson);
            } else if (!isInteger(etInsertAge.getText().toString())){
                Toast.makeText(context, "wrong age :D", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void handleButton(){
        bnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewRecord();
                updateRecord();
                RoomRepo.hideSoftKey(context, v);
                Navigation.findNavController(v).popBackStack();
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_insert, container, false);
        initViews(view);
        initModel();
        handleSpinner();
        updateViewsIfNotNew();
        handleButton();
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        isNew = true;
    }
}