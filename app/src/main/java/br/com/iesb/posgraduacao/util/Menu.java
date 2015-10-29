package br.com.iesb.posgraduacao.util;
import br.com.ieb.posgraduacao.activity.*;
import br.com.iesb.posgraduacao.activity.CalculaSalarioActivity;
import br.com.iesb.posgraduacao.activity.Calcular13Activity;
import br.com.iesb.posgraduacao.activity.CalcularFeriasActivity;
import br.com.iesb.posgraduacao.activity.CalcularRecisaoContratoActivity;
/**
 * Created by braiannunes on 10/26/15.
 */
public enum Menu {
    CALCULAR_SALARIO("Calcular salário", R.drawable.wallet, CalculaSalarioActivity.class),
    CALCULAR_13("Calcular 13º", R.drawable.calculator, Calcular13Activity.class),
    CALCULAR_FERIAS("Calcular férias", R.drawable.vacation, CalcularFeriasActivity.class),
    RECISAO_CONTRATO("Recisão de contrato", R.drawable.contract, CalcularRecisaoContratoActivity.class);

    private String texto;
    private int id;
    private Class activity;

    Menu(final String texto, final int id, Class activity) {
        this.texto = texto;
        this.id = id;
        this.activity = activity;
    }

    public static Class getActivity(int id) {
        for (Menu menu : values()) {
            if (menu.getId() == id) {
                return menu.activity;
            }
        }
        throw new IllegalArgumentException("Activity não localizada!");
    }

    public String getTexto() {
        return texto;
    }

    public int getId() {
        return id;
    }
}
