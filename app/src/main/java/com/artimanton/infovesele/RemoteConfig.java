package com.artimanton.infovesele;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class RemoteConfig extends AppCompatActivity {
    private FirebaseRemoteConfig remoteConfig;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_config);


        remoteConfig = FirebaseRemoteConfig.getInstance();

        textView = (TextView) findViewById(R.id.tvWelcome);

        FirebaseRemoteConfigSettings settings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(true)
                .build();

        remoteConfig.setConfigSettings(settings);
        remoteConfig.setDefaults(R.xml.remote_config_default);


        findViewById(R.id.btGetData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

    }

    private void getData(){
        remoteConfig.fetch(0).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete( Task<Void> task) {
                if(task.isComplete()){
                    Toast.makeText(RemoteConfig.this, "Успех", Toast.LENGTH_SHORT).show();
                    remoteConfig.activateFetched();
                }else
                    Toast.makeText(RemoteConfig.this, "Неудача", Toast.LENGTH_SHORT).show();
                showText();
            }

        });
    }

    private void showText(){
        textView.setText(remoteConfig.getString("welcome_message"));
    }
}
