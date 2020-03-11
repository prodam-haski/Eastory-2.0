package com.prodadimhaski.eastory2.New.TestConstructor.DataAdapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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

    private LayoutInflater inflater;
    private List<String> listOfTests;

    public ListOfTestsAdapter(Context context, List<String> list) {
        this.listOfTests = list;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public ListOfTestsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_list_of_test, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListOfTestsAdapter.ViewHolder holder, final int position) {
        String name = listOfTests.get(position);
        holder.testName.setText(name);
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

