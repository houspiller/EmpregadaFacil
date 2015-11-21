package br.com.iesb.posgraduacao.modelo;
/**
 * Created by braiannunes on 11/18/15.
 */
public class RecisaoModel {

    public static final double INSS_SALARIO = 8;
    public static final double IRRF_SALARIO = 8;

    private double saldoDeSalario;
    private double avisoPrevio;
    private double _13Proporcional;
    private double _13Indenizado;
    private double feriasVencidas;
    private double feriasProporcionais;
    private double feriasIndenizadas;
    private double umTercoDeFeriasVencidas;
    private double umTercoDeFeriasProporcionais;
    private double umTercoDeFeriasIndenizadas;
    private double umTercoSobreFeriasIndenizadas;
    private double irrf13Salario;
    private boolean semJustaCausa;
    private double totalDeFerias;
    private double total;

    public double getSaldoDeSalario() {
        return saldoDeSalario;
    }

    public void setSaldoDeSalario(double saldoDeSalario) {
        this.saldoDeSalario = saldoDeSalario;
    }

    public double getAvisoPrevio() {
        return avisoPrevio;
    }

    public void setAvisoPrevio(double avisoPrevio) {
        this.avisoPrevio = avisoPrevio;
    }

    public double get_13Proporcional() {
        return _13Proporcional;
    }

    public void set_13Proporcional(double _13Proporcional) {
        this._13Proporcional = _13Proporcional;
    }

    public double getFeriasVencidas() {
        return feriasVencidas;
    }

    public void setFeriasVencidas(double feriasVencidas) {
        this.feriasVencidas = feriasVencidas;
    }

    public double getFeriasProporcionais() {
        return feriasProporcionais;
    }

    public void setFeriasProporcionais(double feriasProporcionais) {
        this.feriasProporcionais = feriasProporcionais;
    }

    public double getUmTercoDeFeriasVencidas() {
        return umTercoDeFeriasVencidas;
    }

    public void setUmTercoDeFeriasVencidas(double umTercoDeFeriasVencidas) {
        this.umTercoDeFeriasVencidas = umTercoDeFeriasVencidas;
    }

    public double getUmTercoDeFeriasProporcionais() {
        return umTercoDeFeriasProporcionais;
    }

    public void setUmTercoDeFeriasProporcionais(double umTercoDeFeriasProporcionais) {
        this.umTercoDeFeriasProporcionais = umTercoDeFeriasProporcionais;
    }

    public double getIrrf13Salario() {
        return irrf13Salario;
    }

    public void setIrrf13Salario(double irrf13Salario) {
        this.irrf13Salario = irrf13Salario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotalDeFerias() {
        return totalDeFerias;
    }

    public void setTotalDeFerias(double totalDeFerias) {
        this.totalDeFerias = totalDeFerias;
    }

    public double get_13Indenizado() {
        return _13Indenizado;
    }

    public void set_13Indenizado(double _13Indenizado) {
        this._13Indenizado = _13Indenizado;
    }

    public double getUmTercoDeFeriasIndenizadas() {
        return umTercoDeFeriasIndenizadas;
    }

    public void setUmTercoDeFeriasIndenizadas(double umTercoDeFeriasIndenizadas) {
        this.umTercoDeFeriasIndenizadas = umTercoDeFeriasIndenizadas;
    }

    public double getFeriasIndenizadas() {
        return feriasIndenizadas;
    }

    public void setFeriasIndenizadas(double feriasIndenizadas) {
        this.feriasIndenizadas = feriasIndenizadas;
    }

    public double getUmTercoSobreFeriasIndenizadas() {
        return umTercoSobreFeriasIndenizadas;
    }

    public void setUmTercoSobreFeriasIndenizadas(double umTercoSobreFeriasIndenizadas) {
        this.umTercoSobreFeriasIndenizadas = umTercoSobreFeriasIndenizadas;
    }

    public String isSemJustaCausa() {
        if (semJustaCausa) {
            return "Obs.: Além do valor da rescisão, o empregado tem direito à multa de 40% sobre o valor do fundo de garantia.";
        }
        return "";
    }

    public void setSemJustaCausa(boolean semJustaCausa) {
        this.semJustaCausa = semJustaCausa;
    }

    @Override
    public String toString() {
        return "RecisaoModel{" +
                "  \nsaldoDeSalario=" + saldoDeSalario +
                ", \navisoPrevio=" + avisoPrevio +
                ", \n_13Proporcional=" + _13Proporcional +
                ", \nferiasVencidas=" + feriasVencidas +
                ", \nferiasProporcionais=" + feriasProporcionais +
                ", \numTercoDeFeriasVencidas=" + umTercoDeFeriasVencidas +
                ", \numTercoDeFeriasProporcionais=" + umTercoDeFeriasProporcionais +
                ", \numTercoFeriasIndenizadas=" + umTercoSobreFeriasIndenizadas +
                ", \nsemJustaCausa=" + isSemJustaCausa() +
                ", \ntotalDeFerias=" + totalDeFerias +
                ", \ntotal=" + total +
                '}';
    }

}
