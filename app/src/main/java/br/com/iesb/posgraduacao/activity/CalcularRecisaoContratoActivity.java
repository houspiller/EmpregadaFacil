package br.com.iesb.posgraduacao.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;
import br.com.ieb.posgraduacao.activity.R;
import br.com.iesb.posgraduacao.control.CalculoRecisaoController;

import java.util.Calendar;

public class CalcularRecisaoContratoActivity extends AppCompatActivity {

    private static final int DATA_INICIO_CONTRADO_ID = 0;
    private static final int DATA_FIM_CONTRADO_ID = 1;
    private TextView tvDataInicioContrato;
    private TextView tvDataFimContrato;
    private DataInicioContrato dataInicioContrato;
    private DataFimContrato dataFimContrato;
    private Spinner spMotivoTerminoContrato;
    private TextView tvPossuiFeriasVencidas;
    private TextView tvCumpriuAvisoPrevio;
    private RadioGroup rgPossuiFeriasVencidas;
    private RadioGroup rgCumpriuAvisoPrevio;
    private Spinner spinnerDependentes;
    private CalculoRecisaoController controller;

    private DatePickerDialog.OnDateSetListener pDataInicioContratoListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    dataInicioContrato.ano = year;
                    dataInicioContrato.mes = monthOfYear;
                    dataInicioContrato.dia = dayOfMonth;
                    tvDataInicioContrato.setText(new StringBuilder()
                            // O mês é baseado em 0, então adiciono + 1
                            .append(dataInicioContrato.dia).append("/")
                            .append(dataInicioContrato.mes + 1).append("/")
                            .append(dataInicioContrato.ano).append(" "));
                }
            };

    private DatePickerDialog.OnDateSetListener pDataFimContratoListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    dataFimContrato.ano = year;
                    dataFimContrato.mes = monthOfYear;
                    dataFimContrato.dia = dayOfMonth;
                    tvDataFimContrato.setText(new StringBuilder()
                            // O mês é baseado em 0, então adiciono + 1
                            .append(dataFimContrato.dia).append("/")
                            .append(dataFimContrato.mes + 1).append("/")
                            .append(dataFimContrato.ano).append(" "));
                }
            };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_recisao_contrato);

        obterCampos();
        inicializarDatas();
        inicializarSpinnerMotivoTerminoContrato();
        inicializarSpinnerDependente();
    }

    private void inicializarSpinnerDependente() {
        spinnerDependentes = (Spinner) findViewById(R.id.spinner_dependentes);
        ArrayAdapter<CharSequence> adapterDependente = ArrayAdapter.createFromResource(this, R.array.dependente_array, android.R.layout.simple_spinner_item);
        adapterDependente.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerDependentes.setAdapter(adapterDependente);
    }

    private void obterCampos() {
        tvCumpriuAvisoPrevio = (TextView) findViewById(R.id.tv_aviso_previo);
        tvPossuiFeriasVencidas = (TextView) findViewById(R.id.tv_ferias_vencidas);
        rgCumpriuAvisoPrevio = (RadioGroup) findViewById(R.id.radio_group_aviso_previo);
        rgPossuiFeriasVencidas = (RadioGroup) findViewById(R.id.radio_group_ferias_vencidas);
    }

    private void inicializarSpinnerMotivoTerminoContrato() {
        spMotivoTerminoContrato = (Spinner) findViewById(R.id.sp_motivo_termino_contrato);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.motivo_termino_trabalho_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spMotivoTerminoContrato.setAdapter(adapter);
        spMotivoTerminoContrato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view, final int position, final long id) {
                switch (position) {
                    case 0:
                        Log.d("Spinner", "Pedido de demissão");
                        tvPossuiFeriasVencidas.setVisibility(View.VISIBLE);
                        rgPossuiFeriasVencidas.setVisibility(View.VISIBLE);
                        tvCumpriuAvisoPrevio.setVisibility(View.VISIBLE);
                        rgCumpriuAvisoPrevio.setVisibility(View.VISIBLE);
                        break;

                    case 1:
                        Log.d("Spinner", "Dispensa sem justa causa");
                        tvPossuiFeriasVencidas.setVisibility(View.VISIBLE);
                        rgPossuiFeriasVencidas.setVisibility(View.VISIBLE);
                        tvCumpriuAvisoPrevio.setVisibility(View.VISIBLE);
                        rgCumpriuAvisoPrevio.setVisibility(View.VISIBLE);
                        break;

                    case 2:
                        Log.d("Spinner", "Dispensa com justa causa");
                        tvPossuiFeriasVencidas.setVisibility(View.VISIBLE);
                        rgPossuiFeriasVencidas.setVisibility(View.VISIBLE);
                        tvCumpriuAvisoPrevio.setVisibility(View.GONE);
                        rgCumpriuAvisoPrevio.setVisibility(View.GONE);
                        break;

                    case 3:
                        Log.d("Spinner", "Término de contrato de experiência");
                        tvPossuiFeriasVencidas.setVisibility(View.GONE);
                        rgPossuiFeriasVencidas.setVisibility(View.GONE);
                        tvCumpriuAvisoPrevio.setVisibility(View.GONE);
                        rgCumpriuAvisoPrevio.setVisibility(View.GONE);
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {
            }
        });
    }

    private void inicializarDatas() {
        inicializarDataInicioContrato();
        inicializarDataFimContrato();
    }

    private void inicializarDataInicioContrato() {
        dataInicioContrato = new DataInicioContrato();
        tvDataInicioContrato = (TextView) findViewById(R.id.tv_data_inicio_contrato);
        tvDataInicioContrato.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATA_INICIO_CONTRADO_ID);
            }
        });

        /** Obtém a data atual*/
        final Calendar cal = Calendar.getInstance();
        dataInicioContrato.ano = cal.get(Calendar.YEAR);
        dataInicioContrato.mes = cal.get(Calendar.MONTH);
        dataInicioContrato.dia = cal.get(Calendar.DAY_OF_MONTH);

        atualizarCampoDataInicioContrato();
    }

    private void inicializarDataFimContrato() {
        dataFimContrato = new DataFimContrato();
        tvDataFimContrato = (TextView) findViewById(R.id.tv_data_termino_contrato);
        tvDataFimContrato.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATA_FIM_CONTRADO_ID);
            }
        });

        /** Obtém a data atual*/
        final Calendar cal = Calendar.getInstance();
        dataFimContrato.ano = cal.get(Calendar.YEAR);
        dataFimContrato.mes = cal.get(Calendar.MONTH);
        dataFimContrato.dia = cal.get(Calendar.DAY_OF_MONTH);

        atualizarCampoDataFimContrato();
    }

    private void atualizarCampoDataInicioContrato() {
        tvDataInicioContrato.setText(
                new StringBuilder()
                        // O mês é baseado em 0, então adiciono + 1
                        .append(dataInicioContrato.dia).append("/")
                        .append(dataInicioContrato.mes + 1).append("/")
                        .append(dataInicioContrato.ano).append(" "));
    }

    private void atualizarCampoDataFimContrato() {
        tvDataFimContrato.setText(
                new StringBuilder()
                        // O mês é baseado em 0, então adiciono + 1
                        .append(dataFimContrato.dia).append("/")
                        .append(dataFimContrato.mes + 1).append("/")
                        .append(dataFimContrato.ano).append(" "));
    }

    /**
     * Create a new dialog for date picker
     */
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATA_INICIO_CONTRADO_ID:
                return new DatePickerDialog(this, pDataInicioContratoListener,
                        dataInicioContrato.ano, dataInicioContrato.mes, dataInicioContrato.dia);

            case DATA_FIM_CONTRADO_ID:
                return new DatePickerDialog(this, pDataFimContratoListener,
                        dataFimContrato.ano, dataFimContrato.mes, dataFimContrato.dia);
        }
        return null;
    }

    public class DataInicioContrato {
        int dia;
        int mes;
        int ano;
    }

    public class DataFimContrato {
        int dia;
        int mes;
        int ano;
    }
}
