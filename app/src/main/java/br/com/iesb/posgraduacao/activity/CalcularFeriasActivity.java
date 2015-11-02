package br.com.iesb.posgraduacao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import br.com.ieb.posgraduacao.activity.R;

import java.util.ArrayList;
import java.util.List;

public class CalcularFeriasActivity extends AppCompatActivity {

    private Spinner spinnerDependentes;
    private Spinner spinnerFerias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_ferias);
        carregarSpinners();
    }

    private void carregarSpinners() {
        carregarSpinnerDependentes();
        carregarSpinnerFerias();
    }

    private void carregarSpinnerFerias() {
        spinnerFerias = (Spinner) findViewById(R.id.spinner_dias_ferias);
        ArrayList<String> listDiasFerias = new ArrayList<String>();
        for (int i = 10; i <= 30; i++) {
            listDiasFerias.add(String.valueOf(i));
        }

        ArrayAdapter<String> adapterDependente = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listDiasFerias);
        this.spinnerFerias.setAdapter(adapterDependente);
    }

    private void carregarSpinnerDependentes() {
        spinnerDependentes = (Spinner) findViewById(R.id.spinner_dependentes);
        List<String> listDependente = new ArrayList<String>() {{
            add("00");
            add("01");
            add("02");
            add("03");
            add("04");
            add("05");
            add("06");
            add("07");
            add("08");
            add("09");
            add("10");
        }};

        ArrayAdapter<String> adapterDependente = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listDependente);
        this.spinnerDependentes.setAdapter(adapterDependente);
    }

}
