package com.asynctaskcoffee.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.asynctaskcoffee.audiorecorder.uikit.VoiceSenderDialog;
import com.asynctaskcoffee.audiorecorder.worker.AudioRecordListener;
import com.asynctaskcoffee.audiorecorder.worker.MediaPlayListener;
import com.asynctaskcoffee.audiorecorder.worker.Player;
import com.asynctaskcoffee.helper.DateUtils;
import com.asynctaskcoffee.ui.R;
import com.asynctaskcoffee.ui.db.table.Chat;

import java.io.ByteArrayOutputStream;

public class ChatMainActivity extends DbBaseActivity implements AudioRecordListener, MediaPlayListener {

    private Player player;
    private CardView lay_extra;
    private EditText et_message;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportActionBar().setTitle("Self Chat");
        setUpRv();
        new getAllchat(this).execute();

        lay_extra = findViewById(R.id.lay_extra);
        et_message = findViewById(R.id.et_message);

        findViewById(R.id.btn_photo_lib).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(intent, 3);
                lay_extra.setVisibility(View.GONE);
            }
        });

        findViewById(R.id.btn_loc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lay_extra.setVisibility(View.GONE);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        findViewById(R.id.add_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lay_extra.getVisibility() == View.VISIBLE)
                    lay_extra.setVisibility(View.GONE);
                else
                    lay_extra.setVisibility(View.VISIBLE);
            }
        });
    }

    private Uri img;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3) {
            if (resultCode == RESULT_OK && data != null) {
                sendImage(data.getData().toString());
            }
        }
    }

    Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path =
                MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "TitreF", null);
        return Uri.parse(path);
    }

    public void openDialog(View view) {
        VoiceSenderDialog dialog = new VoiceSenderDialog(this);
        dialog.setBeepEnabled(true);
        dialog.show(getSupportFragmentManager(), "AUDIO");
    }

    @Override
    public void onAudioReady(@Nullable String audioUri) {
        Log.i("ChatMainActivity", audioUri);
        player = new Player(this);
        player.injectMedia(audioUri);
        Chat chat = new Chat();
        chat.setMessage("C'est un message audio");
        chat.setMessageType("audio");
        chat.setPath(audioUri.toString());
        chat.setTime(DateUtils.getCurrentDate(DateUtils.HOUR_FORMAT_1));

        new addChatToDb(this).execute(chat);
    }

    @Override
    public void onRecordFailed(@Nullable String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReadyForRecord() {

    }

    @Override
    public void onStartMedia() {

    }

    @Override
    public void onStopMedia() {

    }

    String playAudioId = "";

    @Override
    public void onItemClick(View view, Chat chat) {
        if (chat.getMessageType().equals("audio")) {
            if (playAudioId != String.valueOf(chat.getMessageId())) {
                playAudioId = String.valueOf(chat.getMessageId());
                player = new Player(this);
                player.injectMedia(chat.getPath());
                getAdapter().updateChat();
            }
            if (player.getPlayer().isPlaying()) {
                player.stopPlaying();
                ((ImageView) view).setImageResource(R.drawable.ic_play);
                chat.setPlaying(false);
            } else {
                player.startPlaying();
                ((ImageView) view).setImageResource(R.drawable.ic_pause);
                chat.setPlaying(true);
            }


        }
    }

    //message
    public void sendMsg(View view) {
        if (et_message.getText().toString().toString().isEmpty()) {
            Toast.makeText(this, "message vide", Toast.LENGTH_SHORT).show();
            return;
        }
        Chat chat = new Chat();
        chat.setMessage(et_message.getText().toString());
        chat.setMessageType("text");
        chat.setPath("");
        chat.setTime(DateUtils.getCurrentDate(DateUtils.HOUR_FORMAT_1));

        new addChatToDb(this).execute(chat);
        et_message.setText("");
    }


    //image
    void sendImage(String path) {
        Chat chat = new Chat();
        chat.setMessage("");
        chat.setMessageType("image");
        chat.setPath(path);
        chat.setTime(DateUtils.getCurrentDate(DateUtils.HOUR_FORMAT_1));

        new addChatToDb(this).execute(chat);
        et_message.setText("");
    }
}
