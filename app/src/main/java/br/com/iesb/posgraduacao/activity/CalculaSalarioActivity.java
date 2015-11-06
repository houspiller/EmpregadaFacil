package br.com.iesb.posgraduacao.activity;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.ieb.posgraduacao.activity.R;
import br.com.iesb.posgraduacao.util.CalculoSalario;
import br.com.iesb.posgraduacao.util.PDFOperations;

public class CalculaSalarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcula_salario);
    }

    public void calculaSalarioClick(View view) {

        final PDFOperations pdfo = new PDFOperations();

        AlertDialog.Builder msgDialog = new AlertDialog.Builder(CalculaSalarioActivity.this);
        msgDialog.setTitle("Resultados");
        msgDialog.setMessage(formaTextoComResultados());

        msgDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //gerando o PDF
        msgDialog.setNegativeButton("Gerar PDF", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pdfo.write("salario", formaTextoComResultados());
                lerPDFGerado();
                dialog.dismiss();
            }
        });

        msgDialog.show();
    }

    //método que retorna o texto completo do relatório gerado
    private String formaTextoComResultados() {

        CalculoSalario calculosSalarioAux = new CalculoSalario();
        EditText etSalarioBruto = (EditText) findViewById(R.id.et_salario_bruto);

        StringBuffer Texto = new StringBuffer();

        Texto.append(this.getString(R.string.inss_empregador));
        Texto.append(" R$" + calculosSalarioAux.calcularINSS(CalculoSalario.EMPREGADOR, Float.parseFloat(etSalarioBruto.getText().toString())) + "\n");

        Texto.append(this.getString(R.string.inss_empregado));
        Texto.append(" R$" + calculosSalarioAux.calcularINSS(CalculoSalario.EMPREGADO, Float.parseFloat(etSalarioBruto.getText().toString())) + "\n");

        Texto.append(this.getString(R.string.desc_vale));
        Texto.append(" R$" + calculosSalarioAux.calcularDescontoValeTransporte(Float.parseFloat(etSalarioBruto.getText().toString())) + "\n");

        Texto.append(this.getString(R.string.fgts));
        Texto.append(" R$" + calculosSalarioAux.calcularFGTS(Float.parseFloat(etSalarioBruto.getText().toString())) + "\n");

        Texto.append(this.getString(R.string.salario_liquido));
        Texto.append(" R$" + calculosSalarioAux.calcularSalarioLiquido(Float.parseFloat(etSalarioBruto.getText().toString())) + "\n");

        return Texto.toString();
    }


    public void lerPDFGerado(){

        final PDFOperations pdfo = new PDFOperations();
        Intent intent = pdfo.openPDF("salario");

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No Application Available to View PDF", Toast.LENGTH_SHORT).show();
        }
    }

}
