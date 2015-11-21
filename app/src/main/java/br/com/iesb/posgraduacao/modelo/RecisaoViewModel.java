package br.com.iesb.posgraduacao.modelo;
import org.joda.time.DateTime;
/**
 * Created by braiannunes on 11/21/15.
 */
public class RecisaoViewModel {

    private double ultimoSalario;
    private DateTime dataInicioContrato;
    private DateTime dataTerminoContrato;
    private boolean dispensaSemJustaCausa;
    private boolean dispensaComJustaCausa;
    private boolean possuiFeriasVencidas;
    private boolean cumpriuAvisoPrevio;

    public double getUltimoSalario() {
        return ultimoSalario;
    }

    public void setUltimoSalario(final double ultimoSalario) {
        this.ultimoSalario = ultimoSalario;
    }

    public DateTime getDataInicioContrato() {
        return dataInicioContrato;
    }

    public void setDataInicioContrato(final DateTime dataInicioContrato) {
        this.dataInicioContrato = dataInicioContrato;
    }

    public DateTime getDataTerminoContrato() {
        return dataTerminoContrato;
    }

    public void setDataTerminoContrato(final DateTime dataTerminoContrato) {
        this.dataTerminoContrato = dataTerminoContrato;
    }

    public boolean isDispensaSemJustaCausa() {
        return dispensaSemJustaCausa;
    }

    public void setDispensaSemJustaCausa(final boolean dispensaSemJustaCausa) {
        this.dispensaSemJustaCausa = dispensaSemJustaCausa;
    }

    public boolean isDispensaComJustaCausa() {
        return dispensaComJustaCausa;
    }

    public void setDispensaComJustaCausa(final boolean dispensaComJustaCausa) {
        this.dispensaComJustaCausa = dispensaComJustaCausa;
    }

    public boolean isPossuiFeriasVencidas() {
        return possuiFeriasVencidas;
    }

    public void setPossuiFeriasVencidas(final boolean possuiFeriasVencidas) {
        this.possuiFeriasVencidas = possuiFeriasVencidas;
    }

    public boolean isCumpriuAvisoPrevio() {
        return cumpriuAvisoPrevio;
    }

    public void setCumpriuAvisoPrevio(final boolean cumpriuAvisoPrevio) {
        this.cumpriuAvisoPrevio = cumpriuAvisoPrevio;
    }
}
