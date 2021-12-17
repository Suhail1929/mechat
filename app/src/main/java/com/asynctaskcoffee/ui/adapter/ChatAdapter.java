package com.asynctaskcoffee.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.asynctaskcoffee.ui.R;
import com.asynctaskcoffee.ui.db.table.Chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private List<Chat> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public ChatAdapter(Context context,
                       List<Chat> data,
                       Intent intent) {
        this.mData = data;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.chat_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chat booking = mData.get(position);
        holder.setData(booking);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    // permet de capturer les événements de clics
    public void setClickListener(ItemClickListener itemClickListener) {
        mClickListener = itemClickListener;
    }

    public void updateChat(List<Chat> newList) {
        mData = newList;
        Collections.reverse(mData);
        notifyDataSetChanged();
    }

    public void updateChat() {
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_message;
        LinearLayout lay_audio;
        CardView lay_card;
        ImageView img_play_pause;
        ImageView img_chat;
        TextView tv_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_message = itemView.findViewById(R.id.tv_message);
            lay_audio = itemView.findViewById(R.id.lay_audio);
            lay_card = itemView.findViewById(R.id.lay_card);
            img_play_pause = itemView.findViewById(R.id.img_play_pause);
            img_chat = itemView.findViewById(R.id.img_chat);
            tv_time = itemView.findViewById(R.id.tv_time);

            img_play_pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mClickListener != null) {
                        mClickListener.onItemClick(view, mData.get(getAdapterPosition()));
                    }
                }
            });
        }

        void setData(Chat chat) {
            tv_message.setVisibility(View.GONE);
            lay_audio.setVisibility(View.GONE);
            lay_card.setVisibility(View.GONE);

            switch (chat.getMessageType()) {
                case "text":
                    tv_message.setVisibility(View.VISIBLE);
                    tv_message.setText(chat.getMessage());
                    break;
                case "audio":
                    lay_audio.setVisibility(View.VISIBLE);
                    if (chat.isPlaying()) {
                        img_play_pause.setImageResource(R.drawable.ic_pause);
                    } else {
                        img_play_pause.setImageResource(R.drawable.ic_play);
                    }
                    break;
                case "image":
                    lay_card.setVisibility(View.VISIBLE);
                    img_chat.setImageURI(Uri.parse(chat.getPath()));
                    break;
            }
            tv_time.setText(chat.getTime());
        }
    }


    public interface ItemClickListener {
        public void onItemClick(View view, Chat chat);
    }

}
