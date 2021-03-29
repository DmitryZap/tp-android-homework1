package ru.techpark.homework666.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.techpark.homework666.R;
import ru.techpark.homework666.kernel.entities.StateObject;

class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder> {
    private final FragmentActivity fa;
    private List<StateObject> stateObjects;

    StateAdapter(List<StateObject> stateObjects, FragmentActivity fa) {
        this.stateObjects = stateObjects;
        this.fa = fa;
    }

    @NonNull
    @Override
    public StateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StateAdapter.ViewHolder holder, int position) {
        holder.mIdView.setText(stateObjects.get(position).getNumber().toString());
        holder.mIdView.setTextColor(fa.getResources().getColor(stateObjects.get(position).specifyColor()));
        holder.mView.setOnClickListener(view1 -> fa.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, ContentFragment.newInstance(stateObjects.get(position).getNumber(), stateObjects.get(position).specifyColor()))
                .addToBackStack(null)
                .commit());
    }

    public void updateValues(List<StateObject> items) {
        stateObjects = items;
    }

    @Override
    public int getItemCount() {
        return stateObjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
        }
    }
}