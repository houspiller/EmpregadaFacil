package br.com.iesb.posgraduacao.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import br.com.ieb.posgraduacao.activity.R;
import br.com.iesb.posgraduacao.control.CalculoFeriasController;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CalcularFeriasActivity extends AppCompatActivity {

    private static final DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance(new Locale("pt", "BR"));

    private Spinner spinnerDependentes;
    private Spinner spinnerFerias;
    private double salarioBruto;
    private double valorMedioHE;
    private int quantidadeDependentes;
    private int quantidadeDiasFerias;
    private boolean abonoPecuniario;
    private boolean adiantar13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_ferias);
        carregarSpinners();
    }

    public void calculaSalarioClick(View view) {

        final AlertDialog.Builder msgDialog = new AlertDialog.Builder(this);
        msgDialog.setTitle("Resultado");
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
        obterValoresDeEntrada();
        CalculoFeriasController calculoFerias = new CalculoFeriasController();

        StringBuffer texto = new StringBuffer();

        // Valor férias
        texto.append(this.getString(R.string.resultado_valor_ferias));
        double valorFerias = calculoFerias.calcularValorFeriasSemDesconto(salarioBruto, quantidadeDiasFerias);
        texto.append(" R$" + valorFerias);
        texto.append("\n");

        // 1/3 férias....................:R$ 400,00
        texto.append(this.getString(R.string.resultado_13_ferias));
        double _13Ferias = calculoFerias.calcular13Ferias(valorFerias);
        texto.append(" R$" + _13Ferias);
        texto.append("\n");

//        Abono pecuniário........:R$ 0,00
        texto.append(this.getString(R.string.resultado_abono_pecuniario));
        double abonoPecuniario = calculoFerias.calcularAbonoPecuniario(salarioBruto, quantidadeDiasFerias, this.abonoPecuniario);
        texto.append(" R$" + abonoPecuniario);
        texto.append("\n");

//        1/3 abono pecuniário..:R$ 0,00
        texto.append(this.getString(R.string.resultado_13_abono_pecuniario));
        double umTercoAbonoPecuniario = calculoFerias.calcularUmTercoAbonoPecuniario(abonoPecuniario);
        texto.append(" R$" + umTercoAbonoPecuniario);
        texto.append("\n");

//        Adiantamento 13º.......:R$ 0,00
        texto.append(this.getString(R.string.resultado_adiantamento_13));
        double adiantamento13 = calculoFerias.calcularAdiantamento13(salarioBruto);
        texto.append(" R$" + adiantamento13);
        texto.append("\n");

//        Total de verbas...........:R$ 1.600,00
        texto.append("\n");
        texto.append(this.getString(R.string.resultado_total_verbas));
        double totalVerbas = valorFerias + _13Ferias + abonoPecuniario + umTercoAbonoPecuniario + adiantamento13;
        texto.append(" R$" + totalVerbas);
        texto.append("\n");

//
//        Descontos:
//        INSS.......................................: R$144,00

//        Dependentes...........(Qtd)..0.....: R$ 0,00 (O valor da dedução mensal é R$ 189,59 por dependente)
//        Imposto de renda (IRPF).............: R$ 0,00
//        Total de descontos......................:R$ 144,00

        return texto.toString();
    }

    private void obterValoresDeEntrada() {
        final EditText edSalarioBruto = (EditText) findViewById(R.id.et_salario_bruto);
        final EditText edMediaHE = (EditText) findViewById(R.id.et_horas_extras);

        this.salarioBruto = converterDouble(edSalarioBruto.getText().toString());
        this.valorMedioHE = converterDouble((edMediaHE.getText().toString()));
        this.quantidadeDependentes = Integer.valueOf(String.valueOf(this.spinnerDependentes.getSelectedItem()));
        this.quantidadeDiasFerias = Integer.valueOf(String.valueOf(this.spinnerFerias.getSelectedItem()));

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group_abono_pecuniario);

        this.abonoPecuniario = ((RadioButton) findViewById(R.id.rb_abono_pecuniario_sim)).isChecked() ? true : false;
        this.adiantar13 = ((RadioButton) findViewById(R.id.rb_adiantamento_13_sim)).isChecked() ? true : false;
    }

    private void carregarSpinners() {
        carregarSpinnerDependentes();
        carregarSpinnerFerias();
    }

    private void carregarSpinnerFerias() {
        spinnerFerias = (Spinner) findViewById(R.id.spinner_dias_ferias);
        ArrayList<String> listDiasFerias = new ArrayList<String>();
        for (int i = 30; i >= 10; i--) {
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

    private double converterDouble(String strDouble) {
        if (strDouble != null && !strDouble.trim().isEmpty()) {
            return Double.parseDouble(strDouble);
        }

        return 0;
    }
}
