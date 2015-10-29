package br.com.iesb.posgraduacao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import br.com.ieb.posgraduacao.activity.R;
import br.com.iesb.posgraduacao.util.Menu;

public class MainActivity extends AppCompatActivity {
    private static final String[] menuNames = {Menu.CALCULAR_SALARIO.getTexto(), Menu.CALCULAR_13.getTexto(),
            Menu.CALCULAR_FERIAS.getTexto(), Menu.RECISAO_CONTRATO.getTexto()};

    private static final int[] menuImg = {Menu.CALCULAR_SALARIO.getId(), Menu.CALCULAR_13.getId(),
            Menu.CALCULAR_FERIAS.getId(), Menu.RECISAO_CONTRATO.getId()};

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_grid);
        gridView = (GridView) findViewById(R.id.gridViewPrincipal);
        gridView.setAdapter(new MenuAdapter(this, menuNames, menuImg));

    }

    public void irCalculaSalarioActivity(View view) {
        Intent intentGoCalculaSalarioActivity = new Intent(this, CalculaSalarioActivity.class);
        startActivity(intentGoCalculaSalarioActivity);
    }
}
