package com.game.myloardinganim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.game.CustomView.ShaderView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ShaderView view;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view=(ShaderView) findViewById(R.id.main_);
        button=(Button) findViewById(R.id.main_bt);
        button.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_bt:{
                this.view.start_animation();
                break;
            }
        }
    }
}
