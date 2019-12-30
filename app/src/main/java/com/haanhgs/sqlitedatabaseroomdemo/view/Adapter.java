package com.haanhgs.sqlitedatabaseroomdemo.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.haanhgs.sqlitedatabaseroomdemo.R;
import com.haanhgs.sqlitedatabaseroomdemo.model.tables.PersonWithJob;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<PersonWithJob> allPerson;
    private final Context context;

    public Adapter(Context context){
        this.context = context;
    }

    public void setAllPerson(List<PersonWithJob> allPerson) {
        this.allPerson = allPerson;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.recycler_item,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        PersonWithJob person = allPerson.get(position);
        holder.tvId.setText(String.format("%s", person.getPersonId()));
        holder.tvName.setText(person.getName());
        holder.tvAge.setText(String.format("%s", person.getAge()));
        holder.tvJob.setText(person.getJobName());
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

    public PersonWithJob getPersonAtPosition(int position){
        return allPerson.get(position);
    }
}
