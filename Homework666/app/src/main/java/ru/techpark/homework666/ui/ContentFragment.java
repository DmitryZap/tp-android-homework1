package ru.techpark.homework666.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.techpark.homework666.R;

public class ContentFragment extends Fragment {

    private static final String ARG_NUMBER = "number";
    private static final String ARG_COLOR = "color";

    private int color = android.R.color.holo_blue_dark;
    private Integer number = 1;

    private View mView;

    public static ContentFragment newInstance(int number, int color) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_NUMBER, number);
        args.putInt(ARG_COLOR, color);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            number = getArguments().getInt(ARG_NUMBER);
            color = getArguments().getInt(ARG_COLOR);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_content, container, false);

        // TODO: Испрвить фрагмент
        Log.d("ContentFragment", number.toString() + " " + color);
        TextView mContentText = mView.findViewById(R.id.content_text);
        mContentText.setText(number.toString());
        mContentText.setTextColor(getResources().getColor(color));
        return mView;
    }
}
