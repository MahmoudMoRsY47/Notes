package com.example.notes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Notes> mNoteList = new ArrayList<>();

    private OnItemCliclListener mListener;
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Notes currentWord = mNoteList.get(position);
        holder.textViewWord.setText(currentWord.getTitle());
        holder.textViewMeaning.setText(currentWord.getNote());
    }

    public  void setWords(List<Notes> notes)
    {
        mNoteList = notes;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewWord;
        public TextView textViewMeaning;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewWord = itemView.findViewById(R.id.word_text_view);
            textViewMeaning = itemView.findViewById(R.id.meaning_text_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();

                    if(true)
                    {
                        mListener.onItemClick(mNoteList.get(index));
                    }
                }
            });
        }
    }

    public interface OnItemCliclListener
    {
        void onItemClick(Notes note);
    }

    public void OnItemClickListener(OnItemCliclListener listener)
    {
        mListener = listener;
    }

    public Notes getWordAt(int pos)
    {
        return mNoteList.get(pos);
    }
}