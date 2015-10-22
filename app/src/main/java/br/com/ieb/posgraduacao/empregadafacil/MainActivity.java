package br.com.ieb.posgraduacao.empregadafacil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void irCalculaSalarioActivity(View view){
        Intent intentGoCalculaSalarioActivity = new Intent(this, CalculaSalarioActivity.class);
        startActivity(intentGoCalculaSalarioActivity);
    }
}
