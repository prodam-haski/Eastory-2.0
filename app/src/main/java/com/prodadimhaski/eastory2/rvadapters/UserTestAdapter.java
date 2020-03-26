package com.prodadimhaski.eastory2.rvadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prodadimhaski.eastory2.R;
import com.prodadimhaski.eastory2.Room.entities.Question;

import java.util.List;

public class UserTestAdapter extends RecyclerView.Adapter<UserTestAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Question> questions;

    public UserTestAdapter(Context context, List<Question> questions) {
        this.questions = questions;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public UserTestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.question_item, parent, false);
        return new UserTestAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question question = questions.get(position);
        holder.nameView.setText(question.getQuestion());
    }

    @Override
    public int getItemCount() {
        return questions == null ? 0 : questions.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameView;

        ViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.question_text);
        }
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
