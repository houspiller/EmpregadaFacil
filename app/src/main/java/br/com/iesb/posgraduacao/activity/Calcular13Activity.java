package br.com.iesb.posgraduacao.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.com.ieb.posgraduacao.activity.R;
import br.com.iesb.posgraduacao.util.CalculoSalario;


public class Calcular13Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular13);
    }

    public void calcular13Salario(View view){

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

    //método que retorna o texto completo do relatório gerado
    private String formaTextoComResultados() {

        CalculoSalario calculosSalarioAux = new CalculoSalario();
        EditText etSalarioBruto = (EditText) findViewById(R.id.et_salario_bruto);

        StringBuffer Texto = new StringBuffer();

        Texto.append(this.getString(R.string.valor_13) + "\n");

        Texto.append(this.getString(R.string.primeira_parcela_13));
        Texto.append(" R$" + calculosSalarioAux.calcularPrimeiraParcela(Float.parseFloat(etSalarioBruto.getText().toString())) + "\n");

        Texto.append(this.getString(R.string.segunda_parcela_13));
        Texto.append(" R$" + calculosSalarioAux.calcularSegundaParcela(Float.parseFloat(etSalarioBruto.getText().toString())) + "\n");

        Texto.append(this.getString(R.string.inss_empregado));
        Texto.append(" R$" + calculosSalarioAux.calcularINSS(CalculoSalario.EMPREGADO, Float.parseFloat(etSalarioBruto.getText().toString())) + "\n");

        return Texto.toString();
    }
}
