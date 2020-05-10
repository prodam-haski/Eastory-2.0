package com.prodadimhaski.eastory2.rvadapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prodadimhaski.eastory2.R;
import com.prodadimhaski.eastory2.serverUtils.NetworkService;
import com.prodadimhaski.eastory2.serverUtils.POJO.ResultDTO;
import com.prodadimhaski.eastory2.serverUtils.POJO.TopicOTD;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<ResultDTO> resultDTOS;
    private Context context;


    public ResultAdapter (Context context, List<ResultDTO> resultDTOS) {
        this.resultDTOS = resultDTOS;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }


    @NonNull
    @Override
    public ResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.result_item, parent, false);
        return new ResultAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResultDTO result = resultDTOS.get(position);
        NetworkService.getInstance()
                .getJSONApi()
                .getTopicById(result.getTopicId())
                .enqueue(new Callback<TopicOTD>() {
                    @Override
                    public void onResponse(@NonNull Call<TopicOTD> call, @NonNull Response<TopicOTD> response) {
                        TopicOTD topicOTD = response.body();
                        holder.testName.setText(topicOTD.getTopicText());

                    }

                    @Override
                    public void onFailure(@NonNull Call<TopicOTD> call, @NonNull Throwable t) {
                        System.out.println("fail");
                    }
                });

        holder.studentName.setText(result.getName());
        holder.scoreField.setText(result.getRightAnswers().toString());

    }

    @Override
    public int getItemCount() {
        return resultDTOS == null ? 0 : resultDTOS.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView testName;
        TextView studentName;
        TextView scoreField;

        ViewHolder(View view) {
            super(view);
            testName = view.findViewById(R.id.nameOfTest);
            studentName = view.findViewById(R.id.nameOfStudent);
            scoreField = view.findViewById(R.id.scoreField);
        }
    }

}
