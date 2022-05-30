package rest.company.a123;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PanelGlowny extends AppCompatActivity implements View.OnClickListener {
    private Button button,button2,button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_glowny);
        
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){


            case R.id.button2:
                startActivity(new Intent(this,Dodawanie.class ));
                break;

            case R.id.button4:
                startActivity(new Intent(this,LISTA.class));
                break;
        }
    }}