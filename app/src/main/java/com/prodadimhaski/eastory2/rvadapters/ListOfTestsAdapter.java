package com.prodadimhaski.eastory2.rvadapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.prodadimhaski.eastory2.R;
import com.prodadimhaski.eastory2.ui.UserTests;

import java.util.List;

public class ListOfTestsAdapter extends RecyclerView.Adapter<ListOfTestsAdapter.ViewHolder> {

    private List<String> listOfTests;
    private Context context;

    public static final String ITEM_POSITION = "itemPostion";

    public ListOfTestsAdapter(List<String> list, Context context) {
        this.listOfTests = list;
        this.context = context;
    }

    @Override
    public ListOfTestsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_of_test, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListOfTestsAdapter.ViewHolder holder, final int position) {
        final String test = listOfTests.get(position);
        holder.testName.setText(test);
        holder.testName.setOnClickListener(v -> {
            Intent intent = new Intent(context, UserTests.class);
            intent.putExtra(ITEM_POSITION, position);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listOfTests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView testName;

        ViewHolder(View view) {
            super(view);
            testName = view.findViewById(R.id.testName);
        }
    }
}

