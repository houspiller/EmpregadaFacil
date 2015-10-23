package br.com.ieb.posgraduacao.empregadafacil;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CalculaSalarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcula_salario);

    }

    public void calculaSalarioClick(View view){

        final AlertDialog.Builder msgDialog = new AlertDialog.Builder(this);
        msgDialog.setTitle("Resultados");
        msgDialog.setMessage(formaTextoComResultados());
        msgDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        msgDialog.show();
    }

    private String formaTextoComResultados(){

        StringBuffer Texto = new StringBuffer();

        Texto.append(getBaseContext().getString(R.string.inss_empregador)+"\n");
        Texto.append(getBaseContext().getString(R.string.inss_empregado)+"\n");
        Texto.append(getBaseContext().getString(R.string.desc_vale)+"\n");
        Texto.append(getBaseContext().getString(R.string.fgts)+"\n");
        Texto.append(this.getString(R.string.salario_liquido));


        return Texto.toString();
    }
}
