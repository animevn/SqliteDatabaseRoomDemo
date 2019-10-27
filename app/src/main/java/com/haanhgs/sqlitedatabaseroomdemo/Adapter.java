package com.haanhgs.sqlitedatabaseroomdemo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.haanhgs.sqlitedatabaseroomdemo.model.Job;
import com.haanhgs.sqlitedatabaseroomdemo.model.Person;
import com.haanhgs.sqlitedatabaseroomdemo.model.RoomDB;
import com.haanhgs.sqlitedatabaseroomdemo.model.RoomRepo;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Person> allPerson;
    private Context context;

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
        RoomRepo.GetJobAsync async = new RoomRepo.GetJobAsync(
                RoomDB.init(context).personDao(),
                new RoomRepo.GetJobFromID() {
            @Override
            public void onPostExcecute(Job job) {
                if (job != null){
                    holder.tvJob.setText(job.getJobName());
                }else {
                    holder.tvJob.setText("");
                }
            }
        });
        async.execute(jobId);
    }

    @Override
    public int getItemCount() {
        return allPerson == null? 0 : allPerson.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvId;
        private TextView tvName;
        private TextView tvAge;
        private TextView tvJob;

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
