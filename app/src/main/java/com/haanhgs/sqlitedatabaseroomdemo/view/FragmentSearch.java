package com.haanhgs.sqlitedatabaseroomdemo.view;

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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.haanhgs.sqlitedatabaseroomdemo.R;
import com.haanhgs.sqlitedatabaseroomdemo.viewmodel.Model;
import com.haanhgs.sqlitedatabaseroomdemo.model.RoomRepo;
import com.haanhgs.sqlitedatabaseroomdemo.model.tables.PersonWithJob;
import java.util.List;

public class FragmentSearch extends Fragment {

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
        model = new ViewModelProvider(this).get(Model.class);
        model.getPersonByName(name).observe(this, new Observer<List<PersonWithJob>>() {
            @Override
            public void onChanged(List<PersonWithJob> people) {
                adapter.setAllPerson(people);
            }
        });
    }

    private void setupSwipe(){
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                model.deletePerson(adapter.getPersonAtPosition(position).getPersonId());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        helper.attachToRecyclerView(rvSearch);
    }

    private void handleButtonClick(){
        bnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etSearch.getText())){
                    initModel(etSearch.getText().toString());
                    setupSwipe();
                    RoomRepo.hideSoftKey(context, v);
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