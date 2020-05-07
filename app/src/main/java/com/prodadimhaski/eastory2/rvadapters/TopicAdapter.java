package com.prodadimhaski.eastory2.rvadapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prodadimhaski.eastory2.R;
import com.prodadimhaski.eastory2.Room.entities.Question;
import com.prodadimhaski.eastory2.Room.entities.Topic;
import com.prodadimhaski.eastory2.serverUtils.NetworkService;
import com.prodadimhaski.eastory2.serverUtils.POJO.TestOTD;
import com.prodadimhaski.eastory2.serverUtils.POJO.TopicOTD;
import com.prodadimhaski.eastory2.ui.TestFromServerActivity;
import com.prodadimhaski.eastory2.ui.TestWindow;
import com.prodadimhaski.eastory2.ui.TestsFromServer;
import com.prodadimhaski.eastory2.ui.UserTests;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.prodadimhaski.eastory2.rvadapters.ListOfTestsAdapter.ITEM_POSITION;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<TopicOTD> topicOTDS;
    private Context context;

    public TopicAdapter(Context context, List<TopicOTD> topicOTDS) {
        this.topicOTDS = topicOTDS;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public TopicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.question_item, parent, false);
        return new TopicAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TopicOTD topic = topicOTDS.get(position);
        holder.nameView.setText(topic.getTopicText());
        holder.nameView.setOnClickListener(v -> {
            Intent intent = new Intent(context, TestFromServerActivity.class);
            intent.putExtra(ITEM_POSITION, topic.getId());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            
        });
    }

    @Override
    public int getItemCount() {
        return topicOTDS == null ? 0 : topicOTDS.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameView;

        ViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.question_text);
        }
    }

    public void setQuestions(List<TopicOTD> questions) {
        this.topicOTDS = topicOTDS;
    }
}