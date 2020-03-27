package com.prodadimhaski.eastory2.rvadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.prodadimhaski.eastory2.R;
import com.prodadimhaski.eastory2.Room.entities.Question;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Question> questions;
    private List<Integer> checkedQuestions;

    public QuestionAdapter(Context context, List<Question> questions) {
        this.questions = questions;
        this.inflater = LayoutInflater.from(context);
        checkedQuestions = new ArrayList<>();
    }

    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuestionAdapter.ViewHolder holder, final int position) {
        Question question = questions.get(position);
        holder.nameView.setText(question.getQuestion());

        holder.checkBox.setChecked(false);
        for (Integer questionId: checkedQuestions) {
            if (question.getQuestion_id() == questionId) {
                holder.checkBox.setChecked(true);
                break;
            }
        }

        holder.checkBox.setOnClickListener(v -> {
            if (checkedQuestions.contains(question.getQuestion_id())) {
                checkedQuestions.remove(checkedQuestions.indexOf(question.getQuestion_id()));

            }
            else {
                checkedQuestions.add(question.getQuestion_id());
                System.out.println(question.getQuestion_id());
            }
        });

    }

    @Override
    public int getItemCount() {
        return questions == null ? 0 : questions.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView;
        final CheckBox checkBox;

        ViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.questionText);
            checkBox = view.findViewById(R.id.checkBox);
        }
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }


    public List<Integer> getCheckedQuestions() {
        return checkedQuestions;
    }
/*
    private int indexOf(List<Question> questions, Question question) {
        for (int i = 0; i < questions.size(); i++) {
            if (quest.equals(question)) {
                questions.remove(quest);
            }
        }
    }

 */
}