package br.com.iesb.posgraduacao.modelo;
/**
 * Created by braiannunes on 11/2/15.
 */
public class FeriasModel {
    private double salarioBruto;
    private double valorMedioHoraExtra;
    private int dependentes;
    private int diasFerias;
    private boolean abonoPecuniario;
    private boolean adiantarDecimoTerceiro;

    public double getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(final double salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public double getValorMedioHoraExtra() {
        return valorMedioHoraExtra;
    }

    public void setValorMedioHoraExtra(final double valorMedioHoraExtra) {
        this.valorMedioHoraExtra = valorMedioHoraExtra;
    }

    public int getDependentes() {
        return dependentes;
    }

    public void setDependentes(final int dependentes) {
        this.dependentes = dependentes;
    }

    public int getDiasFerias() {
        return diasFerias;
    }

    public void setDiasFerias(final int diasFerias) {
        this.diasFerias = diasFerias;
    }

    public boolean isAbonoPecuniario() {
        return abonoPecuniario;
    }

    public void setAbonoPecuniario(final boolean abonoPecuniario) {
        this.abonoPecuniario = abonoPecuniario;
    }

    public boolean isAdiantarDecimoTerceiro() {
        return adiantarDecimoTerceiro;
    }

    public void setAdiantarDecimoTerceiro(final boolean adiantarDecimoTerceiro) {
        this.adiantarDecimoTerceiro = adiantarDecimoTerceiro;
    }
}
