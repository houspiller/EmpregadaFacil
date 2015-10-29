package br.com.ieb.posgraduacao.empregadafacil;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DecimalFormat;

import br.com.ieb.posgraduacao.empregadafacil.Utils.calculosSalario;

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

    //método que retorna o texto completo do relatório gerado
    private String formaTextoComResultados(){


        calculosSalario calculosSalarioAux = new calculosSalario();
        EditText etSalarioBruto = (EditText) findViewById(R.id.et_salario_bruto);

        StringBuffer Texto = new StringBuffer();

        Texto.append(this.getString(R.string.inss_empregador));
        Texto.append(" R$"+calculosSalarioAux.calcularINSS(calculosSalario.EMPREGADOR, Float.parseFloat(etSalarioBruto.getText().toString()))+"\n");

        Texto.append(this.getString(R.string.inss_empregado));
        Texto.append(" R$"+calculosSalarioAux.calcularINSS(calculosSalario.EMPREGADO, Float.parseFloat(etSalarioBruto.getText().toString()))+"\n");

        Texto.append(this.getString(R.string.desc_vale));
        Texto.append(" R$"+calculosSalarioAux.calcularDescontoValeTransporte(Float.parseFloat(etSalarioBruto.getText().toString()))+"\n");

        Texto.append(this.getString(R.string.fgts));
        Texto.append(" R$"+calculosSalarioAux.calcularFGTS(Float.parseFloat(etSalarioBruto.getText().toString()))+"\n");

        Texto.append(this.getString(R.string.salario_liquido));
        Texto.append(" R$"+calculosSalarioAux.calcularSalarioLiquido(Float.parseFloat(etSalarioBruto.getText().toString()))+"\n");


        return Texto.toString();
    }

}
