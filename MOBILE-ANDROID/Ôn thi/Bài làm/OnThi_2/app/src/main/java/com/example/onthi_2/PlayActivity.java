package com.example.onthi_2;

import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// Khai bao activity trong Android Manifest
public class PlayActivity extends AppCompatActivity {

    Button playBtn;
    Button stopBtn;
    boolean play;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.play_ringtone_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.playMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        playBtn = findViewById(R.id.play);
        stopBtn = findViewById(R.id.buttonStop);

        Uri ringtoneUri = RingtoneManager.getDefaultUri(
                RingtoneManager.TYPE_RINGTONE
        );

        mediaPlayer = MediaPlayer.create(this, ringtoneUri);

        mediaPlayer.setLooping(true);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play = true;
                mediaPlayer.start();
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (play) {
                    play = false;
                    mediaPlayer.pause();
                }
                else {
                    play = false;
                    setResult(200);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
