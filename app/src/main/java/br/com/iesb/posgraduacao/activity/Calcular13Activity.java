package br.com.iesb.posgraduacao.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        //segundo dialog com as opções de 1ª e 2ª parcela
        final AlertDialog.Builder msgDialog2 = new AlertDialog.Builder(this);

        msgDialog.setTitle("Resultados");
        msgDialog.setMessage(formaTextoComResultados());

        //abre as informações com os resultados
        msgDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        //abre outra caixa de diálogo para a geracao dos recibos.
        msgDialog.setNegativeButton("Gerar Recibo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                msgDialog2.setTitle("Recibos");
                msgDialog2.setItems(new CharSequence[]
                                {"Gerar 1ª Parcela", "Gerar 2ª Parcela"},
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                switch (which) {
                                    case 0:
                                        gerarPDF1Parcela();
                                        Toast.makeText(getBaseContext(), "Gerou 1ª Parcela", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 1:
                                        gerarPDF2Parcela();
                                        Toast.makeText(getBaseContext(), "Gerou 2ª Parcela", Toast.LENGTH_SHORT).show();
                                        break;
                                }

                            }
                        });
                msgDialog2.create().show();
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

    public void gerarPDF1Parcela(){
        //TODO a fazer
    }

    public void gerarPDF2Parcela(){
        //TODO a fazer
    }
}
