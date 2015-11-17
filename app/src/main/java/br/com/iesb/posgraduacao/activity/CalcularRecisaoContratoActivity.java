package br.com.iesb.posgraduacao.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;
import br.com.ieb.posgraduacao.activity.R;

import java.util.Calendar;

public class CalcularRecisaoContratoActivity extends AppCompatActivity {

    /**
     * This integer will uniquely define the dialog to be used for displaying date picker.
     */
    private static final int DATE_DIALOG_ID = 0;
    private TextView pDisplayDate;
    private DataInicioContrato dataInicioContrato;
    private Spinner spMotivoTerminoContrato;
    private TextView tvPossuiFeriasVencidas;
    private TextView tvCumpriuAvisoPrevio;
    private RadioGroup rgPossuiFeriasVencidas;
    private RadioGroup rgCumpriuAvisoPrevio;
    private Spinner spinnerDependentes;

    private DatePickerDialog.OnDateSetListener pDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    dataInicioContrato.ano = year;
                    dataInicioContrato.mes = monthOfYear;
                    dataInicioContrato.dia = dayOfMonth;
                    updateDisplay();
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
                        tvPossuiFeriasVencidas.setVisibility(View.GONE);
                        rgPossuiFeriasVencidas.setVisibility(View.GONE);
                        break;

                    case 3:
                        Log.d("Spinner", "Término de contrato de experiência");
                        tvPossuiFeriasVencidas.setVisibility(View.GONE);
                        rgPossuiFeriasVencidas.setVisibility(View.GONE);
                        tvCumpriuAvisoPrevio.setVisibility(View.GONE);
                        rgCumpriuAvisoPrevio.setVisibility(View.GONE);
                        break;

                    default:
                        Log.d("Spinner", "Default value!");
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
    }

    private void inicializarDataInicioContrato() {
        dataInicioContrato = new DataInicioContrato();
        pDisplayDate = (TextView) findViewById(R.id.tv_data_inicio_contrato);
        pDisplayDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        /** Obtém a data atual*/
        final Calendar cal = Calendar.getInstance();
        dataInicioContrato.ano = cal.get(Calendar.YEAR);
        dataInicioContrato.mes = cal.get(Calendar.MONTH);
        dataInicioContrato.dia = cal.get(Calendar.DAY_OF_MONTH);

        /** Atualiza a data no textview*/
        updateDisplay();
    }

    /**
     * Updates the date in the TextView
     */
    private void updateDisplay() {
        pDisplayDate.setText(
                new StringBuilder()
                        // O mês é baseado em 0, então adiciono + 1
                        .append(dataInicioContrato.dia).append("/")
                        .append(dataInicioContrato.mes + 1).append("/")
                        .append(dataInicioContrato.ano).append(" "));
    }

    /**
     * Create a new dialog for date picker
     */
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        pDateSetListener,
                        dataInicioContrato.ano, dataInicioContrato.mes, dataInicioContrato.dia);
        }
        return null;
    }

    public class DataInicioContrato {
        int dia;
        int mes;
        int ano;
    }

}
