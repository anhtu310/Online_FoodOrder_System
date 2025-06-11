package com.example.learningenglish;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.learningenglish.data.model.Vocab;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VocabAdapter extends RecyclerView.Adapter<VocabAdapter.VocabViewHolder> {
    private List<Vocab> vocabList;
    private Context context;

    public VocabAdapter(Context context, List<Vocab> vocabList) {
        this.context = context;
        this.vocabList = vocabList;
    }

        @NonNull
        @Override
        public VocabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.vocab_layout, parent, false);
            return new VocabAdapter.VocabViewHolder(view);
        }


    @Override
    public void onBindViewHolder(@NonNull VocabViewHolder holder, int position) {
        Vocab vocab = vocabList.get(position);
        holder.wordTextView.setText(vocab.getWord());
        holder.meaningTextView.setText(vocab.getMeaning());
        holder.pronounciationTextView.setText(vocab.getPronounciation());

        Picasso.get()
                .load(vocab.getImageUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return vocabList.size();
    }

    public static class VocabViewHolder extends RecyclerView.ViewHolder {

        TextView wordTextView, meaningTextView, pronounciationTextView;

        ImageView imageView;
        Button buttonViewTopic, buttonDeleteTopic;
        public VocabViewHolder(@NonNull View itemView) {
            super(itemView);

            wordTextView = itemView.findViewById(R.id.vocabTextViewName);
            meaningTextView = itemView.findViewById(R.id.vocabTextViewMeaning);
            pronounciationTextView = itemView.findViewById(R.id.vocabTextViewPronounce);
            imageView = itemView.findViewById(R.id.vocabImageView);
            buttonViewTopic = itemView.findViewById(R.id.buttonEditVocab);
            buttonDeleteTopic = itemView.findViewById(R.id.buttonDeleteVocab);
        }
    }
}
