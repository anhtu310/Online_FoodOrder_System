package com.example.learningenglish;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningenglish.R;
import com.example.learningenglish.data.model.Topic;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {
    private List<Topic> topicList;
    private Context context;

    public TopicAdapter(Context context, List<Topic> topicList) {
        this.context = context;
        this.topicList = topicList;
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.topic_layout, parent, false);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        Topic topic = topicList.get(position);
        holder.topicTextViewName.setText(topic.getName());

        holder.buttonViewTopic.setOnClickListener(v -> {
            Intent intent = new Intent(context, VocabManager.class);
            intent.putExtra("topic_id", topic.getId());
            context.startActivity(intent);
        });
// xoa topic
        holder.buttonDeleteTopic.setOnClickListener(v -> {
            int topicId = topic.getId();  // Lấy id của topic hiện tại

            // Tìm vị trí của item có id này
            int positionToRemove = -1;
            for (int i = 0; i < topicList.size(); i++) {
                if (topicList.get(i).getId() == topicId) {
                    positionToRemove = i;
                    break;
                }
            }

            // Nếu tìm thấy thì xóa
            if (positionToRemove != -1) {
                topicList.remove(positionToRemove);
                notifyItemRemoved(positionToRemove);
                Toast.makeText(context, "Đã xóa Topic có ID: " + topicId, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    public static class TopicViewHolder extends RecyclerView.ViewHolder {

        TextView topicTextViewName;
        Button buttonViewTopic, buttonDeleteTopic;
        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);

            topicTextViewName = itemView.findViewById(R.id.topicTextViewName);
            buttonViewTopic = itemView.findViewById(R.id.buttonViewTopic);
            buttonDeleteTopic = itemView.findViewById(R.id.buttonDeleteTopic);
        }
    }
}
