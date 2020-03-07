package com.prodadimhaski.eastory2.New.TestConstructor.DataAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.prodadimhaski.eastory2.New.TestConstructor.FullListConstructor.Question;
import com.prodadimhaski.eastory2.R;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Question> questions;
    boolean[] checked;
    List<Integer> numberChecked = new ArrayList<Integer>();

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
    public void onBindViewHolder(QuestionAdapter.ViewHolder holder, final int position) {
        Question question = questions.get(position);
        holder.nameView.setText(question.getQuestion());
        holder.checkBox.setChecked(checked[position]);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked[position] = !checked[position];
                if(checked[position])numberChecked.add(position);
                else for (Integer s :numberChecked
                          ) {if(s==position) {numberChecked.remove(s);break;}
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return questions == null ? 0 : questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView;
        final CheckBox checkBox;
        ViewHolder(View view){
            super(view);
            nameView = view.findViewById(R.id.questionText);
            checkBox = view.findViewById(R.id.checkBox);
        }
    }

    public List<Integer> getNumberChecked() {
        return numberChecked;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        checked = new boolean[questions.size()];
        numberChecked = new ArrayList<Integer>();
    }
}