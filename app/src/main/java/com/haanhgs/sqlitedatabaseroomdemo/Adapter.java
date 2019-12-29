package com.haanhgs.sqlitedatabaseroomdemo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.haanhgs.sqlitedatabaseroomdemo.model.Job;
import com.haanhgs.sqlitedatabaseroomdemo.model.Model;
import com.haanhgs.sqlitedatabaseroomdemo.model.Person;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Person> allPerson;
    private final Context context;
    private Model model;
    private LifecycleOwner owner;

    public void setOwner(LifecycleOwner owner) {
        this.owner = owner;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Adapter(Context context){
        this.context = context;
    }

    public void setAllPerson(List<Person> allPerson) {
        this.allPerson = allPerson;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.fragment_home_recycler_item,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Person person = allPerson.get(position);
        holder.tvId.setText(String.format("%s", person.getPersonId()));
        holder.tvName.setText(person.getName());
        holder.tvAge.setText(String.format("%s", person.getAge()));

        int jobId = person.getJobId();
        model.findJobById(jobId).observe(owner, new Observer<Job>() {
            @Override
            public void onChanged(Job job) {
                if (job != null){
                    holder.tvJob.setText(job.getJobName());
                }else {
                    holder.tvJob.setText("");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return allPerson == null? 0 : allPerson.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tvId;
        private final TextView tvName;
        private final TextView tvAge;
        private final TextView tvJob;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvID);
            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvJob = itemView.findViewById(R.id.tvJob);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putInt("ID", allPerson.get(getAdapterPosition()).getPersonId());
            bundle.putBoolean("isNew", false);
            Navigation.findNavController(v).navigate(R.id.action_nav_home_to_mniInsert, bundle);
        }
    }

    public Person getPersonAtPosition(int position){
        return allPerson.get(position);
    }
}
