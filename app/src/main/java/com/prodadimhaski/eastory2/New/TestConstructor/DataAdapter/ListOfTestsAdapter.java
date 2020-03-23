package com.prodadimhaski.eastory2.New.TestConstructor.DataAdapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.prodadimhaski.eastory2.Eastory2.OldVersion.DBManager.DatabaseHelper;
import com.prodadimhaski.eastory2.New.TestConstructor.FullListConstructor.Question;
import com.prodadimhaski.eastory2.R;

import java.util.ArrayList;
import java.util.List;

public class ListOfTestsAdapter extends RecyclerView.Adapter<ListOfTestsAdapter.ViewHolder> {

    private List<Test> listOfTests;

    public ListOfTestsAdapter(Context context, List<Test> list) {
        this.listOfTests = list;
    }

    @Override
    public ListOfTestsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_of_test, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListOfTestsAdapter.ViewHolder holder, final int position) {
        final Test test = listOfTests.get(position);
        holder.testName.setText(test.getTestName());
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

