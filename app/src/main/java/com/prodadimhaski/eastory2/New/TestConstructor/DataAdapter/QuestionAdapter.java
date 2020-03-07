package com.prodadimhaski.eastory2.New.TestConstructor.DataAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.prodadimhaski.eastory2.New.TestConstructor.FullListConstructor.Question;
import com.prodadimhaski.eastory2.R;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Question> questions;

    public QuestionAdapter(Context context, List<Question> foodItems) {
        this.questions = foodItems;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuestionAdapter.ViewHolder holder, int position) {
        Question question = questions.get(position);
        holder.nameView.setText(question.getQuestion());
    }

    @Override
    public int getItemCount() {
        return questions == null ? 0 : questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView;
        ViewHolder(View view){
            super(view);
            nameView = view.findViewById(R.id.questionText);

        }
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}