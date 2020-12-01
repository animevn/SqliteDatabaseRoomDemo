package com.haanhgs.sqlitedatabaseroomdemo.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.haanhgs.sqlitedatabaseroomdemo.model.tables.PersonWithJob;
import java.util.List;

public class FragmentHome extends Fragment {

    private Context context;
    private Adapter adapter;
    private RecyclerView rvHome;
    private Model model;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        adapter = new Adapter(context);
    }

    private void initViews(View view){
        rvHome = view.findViewById(R.id.rvHome);
        rvHome.setLayoutManager(new LinearLayoutManager(context));
        rvHome.setItemAnimator(new DefaultItemAnimator());
        rvHome.setAdapter(adapter);
    }

    private void initModel(){
        model = new ViewModelProvider(this).get(Model.class);
        model.getAllPerson().observe(this, new Observer<List<PersonWithJob>>() {
            @Override
            public void onChanged(List<PersonWithJob> people) {
                adapter.setAllPerson(people);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void setupSwipe(){
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
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
        helper.attachToRecyclerView(rvHome);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(view);
        initModel();
        setupSwipe();
        return view;
    }
}