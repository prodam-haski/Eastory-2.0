package com.prodadimhaski.eastory2.rvadapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.prodadimhaski.eastory2.R;

import java.util.List;

public class ListOfTestsAdapter extends RecyclerView.Adapter<ListOfTestsAdapter.ViewHolder> {

    private List<String> listOfTests;

    public ListOfTestsAdapter(List<String> list) {
        this.listOfTests = list;
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

