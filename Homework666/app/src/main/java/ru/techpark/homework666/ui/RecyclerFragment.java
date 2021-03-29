package ru.techpark.homework666.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ru.techpark.homework666.R;
import ru.techpark.homework666.kernel.api.StateApi;

public class RecyclerFragment extends Fragment {
    private int mColumnCount = 3;

    public RecyclerFragment() {
        super(R.layout.fragment_recycler);
    }

    private RecyclerView mRecyclerView;
    private FloatingActionButton mAddButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            mColumnCount = 3;
        } else {
            mColumnCount = 4;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        mRecyclerView = view.findViewById(R.id.items_recycler);
        mAddButton = view.findViewById(R.id.add_item);

        initRecycler();
//        recoverState(savedInstanceState);
        initAddButton();

        return view;
    }

//    private void recoverState(@Nullable Bundle savedInstanceState){
//        if(savedInstanceState != null){
//            mRecyclerView.scrollToPosition(savedInstanceState.getInt("scrollPosition", 0));
//        }
//    }

    private void initRecycler(){
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), mColumnCount));
        StateAdapter adapter = new StateAdapter(StateApi.getInstance().getStates(), getActivity());
        mRecyclerView.setAdapter(adapter);
        StateApi.getInstance().addCallback((Object... objects) -> {
            adapter.updateValues(StateApi.getInstance().getStates());
            adapter.notifyDataSetChanged();
            mRecyclerView.smoothScrollToPosition(StateApi.getInstance().getStates().size() - 1);
        });
    }

    private void initAddButton(){
        mAddButton.setOnClickListener(v -> {
            StateApi.getInstance().addState();
        });
    }

//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt("scrollPosition", ((GridLayoutManager) mRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition());
//    }
}
