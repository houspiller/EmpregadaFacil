package br.com.iesb.posgraduacao.control;
import br.com.iesb.posgraduacao.modelo.RecisaoModel;
import br.com.iesb.posgraduacao.modelo.RecisaoViewModel;
import org.joda.time.DateTime;
import org.joda.time.Years;

import java.text.DecimalFormat;
/**
 * Created by braiannunes on 11/17/15.
 */
public class CalculoRecisaoController {

    private static final int DATA_LIMITE_MES_TRABALHADO = 15;
    private static final double INSS = 0.08;
    private static final double UM_TERCO = 3;
    private static final double INSS_13 = 0.08;
    //    private final boolean POSSUI_FERIAS_VENCIDAS = true;
//    private final boolean CUMPRIU_AVISO_PREVIO = false;
//    private final boolean DISPENSA_SEM_JUSTA_CAUSA = true;
//    private final boolean DISPENSA_COM_JUSTA_CAUSA = false;
    // Padrão de input das telas;
//    private final double SALARIO = 1240;
    private final int QTD_DIA_MES = 30;
    private final int QTD_MESES_ANO = 12;
    //    private final String DATA_INICIO_TRABALHO = "01/01/2014";
//    private final String DATA_FINAL_TRABALHO = "21/11/2015";
//    private final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd/MM/yyyy");
    private final RecisaoViewModel recisaoViewModel;
    private int qtdDiasTrabalhados;
    private int qtdMesesTrabalhados;
    private int qtdAnosTrabalhados;
    private RecisaoModel recisaoModel = new RecisaoModel();
    private DecimalFormat decimalFormat = new DecimalFormat("R$#.00");

    public CalculoRecisaoController(RecisaoViewModel recisaoViewModel) {
        this.recisaoViewModel = recisaoViewModel;
    }

    public RecisaoModel realizar_calculo() {
        carregarDadosDeEntrada();

        recisaoModel.setSaldoDeSalario(calcular_saldo_salario());
        recisaoModel.setAvisoPrevio(calcular_aviso_previo());
        recisaoModel.set_13Proporcional(calcular_decimo_terceiro_proporcional());
        recisaoModel.set_13Indenizado(calcular_decimo_terceiro_indenizado());
        recisaoModel.setFeriasVencidas(calcular_ferias_vencidas());
        recisaoModel.setFeriasProporcionais(calcular_ferias_proporcionais());
        recisaoModel.setFeriasIndenizadas(calcular_ferias_indenizadas());
        recisaoModel.setUmTercoDeFeriasVencidas(cacular_um_terco_de_ferias_vencidas());
        recisaoModel.setUmTercoDeFeriasProporcionais(calcular_um_terco_de_ferias_proporcionais());
        recisaoModel.setUmTercoSobreFeriasIndenizadas(calcular_um_terco_sobre_ferias_indenizadas());
        recisaoModel.setSemJustaCausa(this.recisaoViewModel.isDispensaSemJustaCausa());
        recisaoModel.setTotalDeFerias(calcular_total_de_ferias(recisaoModel));
        recisaoModel.setTotal(calcular_total(recisaoModel));

        System.out.println(recisaoModel.toString());

        return recisaoModel;
    }

    private double calcular_um_terco_sobre_ferias_indenizadas() {
        if (this.recisaoViewModel.isDispensaSemJustaCausa() && !this.recisaoViewModel.isCumpriuAvisoPrevio()) {
            return calcular_ferias_indenizadas() / UM_TERCO;
        }

        return 0;
    }

    private double calcular_ferias_indenizadas() {
        if (this.recisaoViewModel.isDispensaSemJustaCausa() && !this.recisaoViewModel.isCumpriuAvisoPrevio()) {
            return this.recisaoViewModel.getUltimoSalario() / QTD_MESES_ANO;
        }

        return 0;
    }

    private double calcular_decimo_terceiro_indenizado() {
        if (this.recisaoViewModel.isDispensaSemJustaCausa() && !this.recisaoViewModel.isCumpriuAvisoPrevio()) {
            return calcular_ferias_indenizadas() / UM_TERCO;
        }

        return 0;
    }

    private void carregarDadosDeEntrada() {
        qtdAnosTrabalhados = Years.yearsBetween(this.recisaoViewModel.getDataInicioContrato(), this.recisaoViewModel.getDataTerminoContrato()).getYears();
        qtdDiasTrabalhados = this.recisaoViewModel.getDataTerminoContrato().getDayOfMonth();

        System.out.println("qtdAnosTrabalhados = " + qtdAnosTrabalhados);
        System.out.println("qtdDiasTrabalhados = " + qtdDiasTrabalhados);
    }

    private double calcular_total(RecisaoModel recisaoModel) {
        return recisaoModel.getSaldoDeSalario()
                + recisaoModel.getTotalDeFerias()
                + recisaoModel.getAvisoPrevio()
                + recisaoModel.get_13Proporcional();
    }

    private double calcular_total_de_ferias(RecisaoModel recisaoModel) {
        return recisaoModel.getFeriasVencidas()
                + recisaoModel.getUmTercoDeFeriasVencidas()
                + recisaoModel.getFeriasProporcionais()
                + recisaoModel.getUmTercoDeFeriasProporcionais()
                + recisaoModel.getFeriasIndenizadas()
                + recisaoModel.getUmTercoDeFeriasIndenizadas()
                + recisaoModel.getUmTercoSobreFeriasIndenizadas();
    }

    private double calcular_um_terco_de_ferias_proporcionais() {
        if (this.recisaoViewModel.isDispensaComJustaCausa()) {
            return 0;
        }
        return recisaoModel.getFeriasProporcionais() / UM_TERCO;
    }

    private double cacular_um_terco_de_ferias_vencidas() {
        if (this.recisaoViewModel.isPossuiFeriasVencidas()) {
            return recisaoModel.getFeriasVencidas() / UM_TERCO;
        }

        return 0;
    }

    private double calcular_aviso_previo() {
        if (this.recisaoViewModel.isCumpriuAvisoPrevio() || this.recisaoViewModel.isDispensaComJustaCausa()) {
            return 0;
        }

        if (this.recisaoViewModel.isDispensaSemJustaCausa()) {
            double totalAvisoPrevio = (this.recisaoViewModel.getUltimoSalario() * obterAvisoPrevioPorTempoDeServico()) / QTD_DIA_MES;
            return (totalAvisoPrevio - (totalAvisoPrevio * INSS));
        }

        return -this.recisaoViewModel.getUltimoSalario();
    }

    private double calcular_ferias_proporcionais() {
        if (this.recisaoViewModel.isDispensaComJustaCausa()) {
            return 0;
        }

        int qtdMesesTrabalhados;
        DateTime dataFinal = this.recisaoViewModel.getDataTerminoContrato();
        qtdMesesTrabalhados = dataFinal.minusMonths(1).getMonthOfYear();
        if (!possoCalcularProporcional(qtdMesesTrabalhados)) {
            return 0;
        }

        if (dataFinal.getDayOfMonth() >= DATA_LIMITE_MES_TRABALHADO) {
            qtdMesesTrabalhados += 1;
        }

        return (this.recisaoViewModel.getUltimoSalario() / QTD_MESES_ANO) * qtdMesesTrabalhados;

    }

    private double calcular_ferias_vencidas() {
        if (this.recisaoViewModel.isPossuiFeriasVencidas()) {
            return this.recisaoViewModel.getUltimoSalario();
        }

        return 0;
    }

    public double calcular_saldo_salario() {
        double saldoDeSalario;
        double descontoINSS;

        saldoDeSalario = (this.recisaoViewModel.getUltimoSalario() / QTD_DIA_MES) * qtdDiasTrabalhados;
        descontoINSS = saldoDeSalario * INSS;

        //TODO Calcular IRRF
        saldoDeSalario -= descontoINSS;

        return saldoDeSalario;
    }

    //TODO
    /*
    * O cálculo do 13 é feito a partir de janeiro e não do tempo de trabalho (férias)
    * */
    public double calcular_decimo_terceiro_proporcional() {
        if (this.recisaoViewModel.isDispensaComJustaCausa()) {
            return 0;
        }

        double decimoTerceiroProporcional;
        double descontoInss;

        DateTime dataFinal = this.recisaoViewModel.getDataTerminoContrato();

        // Identificar ultimo mês trabalhado
        qtdMesesTrabalhados = dataFinal.minusMonths(1).getMonthOfYear();
        if (!possoCalcularProporcional(qtdMesesTrabalhados)) {
            return 0;
        }

        if (dataFinal.getDayOfMonth() >= DATA_LIMITE_MES_TRABALHADO) {
            qtdMesesTrabalhados += 1;
        }

        decimoTerceiroProporcional = (this.recisaoViewModel.getUltimoSalario() / QTD_MESES_ANO) * qtdMesesTrabalhados;

        // Desconto INSS sobre as férias
        descontoInss = decimoTerceiroProporcional * INSS_13;
        decimoTerceiroProporcional -= descontoInss;

//        System.out.println("decimoTerceiroProporcional = " + decimoTerceiroProporcional);

        return decimoTerceiroProporcional;
    }

    private boolean possoCalcularProporcional(int qtdMesesTrabalhados) {
        if (qtdMesesTrabalhados == QTD_MESES_ANO) {
            return false;
        }
        return true;
    }

    // A cada ano completo soma-se 3
    private int obterAvisoPrevioPorTempoDeServico() {
        int totalPontosPorTempoServico = 0;
        for (int i = 0; i < qtdAnosTrabalhados; i++) {
            totalPontosPorTempoServico += 3;
        }

        return QTD_DIA_MES + totalPontosPorTempoServico;
    }

    public String formatarTexto(final RecisaoModel recisaoModel) {
        StringBuffer sb = new StringBuffer();

        // Qtd dias trabalhados
        sb.append("Qtd dias trabalhados.: ");
        sb.append(qtdDiasTrabalhados);
        sb.append("\n");

        // Qtd anos trabalhados
        sb.append("Qtd anos trabalhados: ");
        sb.append(qtdAnosTrabalhados);
        sb.append("\n");

        // Saldo de salário
        sb.append("\n");
        sb.append("Saldo de salário............: ");
        sb.append(decimalFormat.format(recisaoModel.getSaldoDeSalario()));
        sb.append("\n");

        // Décimo terceiro proporcional
        sb.append("13º proporcional...........: ");
        sb.append(decimalFormat.format(recisaoModel.get_13Proporcional()));
        sb.append("\n");

        // Férias vencidas
        sb.append("Férias vencidas.............: ");
        sb.append(decimalFormat.format(recisaoModel.getFeriasVencidas()));
        sb.append("\n");

        // Um terço férias vencidas
        sb.append("1/3 férias vencidas.......: ");
        sb.append(decimalFormat.format(recisaoModel.getUmTercoDeFeriasVencidas()));
        sb.append("\n");

        // Férias proporcionais
        sb.append("Férias proporcionais.....: ");
        sb.append(decimalFormat.format(recisaoModel.getFeriasProporcionais()));
        sb.append("\n");

        // Um terço férias proporcionais
        sb.append("1/3 férias proporcionais: ");
        sb.append(decimalFormat.format(recisaoModel.getUmTercoDeFeriasProporcionais()));
        sb.append("\n");

        if (this.recisaoViewModel.isDispensaSemJustaCausa() && !this.recisaoViewModel.isCumpriuAvisoPrevio()) {
            // Férias indenizadas
            sb.append("Férias indenizadas.........: ");
            sb.append(decimalFormat.format(recisaoModel.getFeriasIndenizadas()));
            sb.append("\n");

            // 1/3 férias indenizadas
            sb.append("1/3 férias indenizadas....: ");
            sb.append(decimalFormat.format(recisaoModel.getUmTercoSobreFeriasIndenizadas()));
            sb.append("\n");
        }

        // Total de férias
        sb.append("\n");
        sb.append("Total de férias..: ");
        sb.append(decimalFormat.format(recisaoModel.getTotalDeFerias()));
        sb.append("\n");

        // Total
        sb.append("Total da recisão: ");
        sb.append(decimalFormat.format(recisaoModel.getTotal()));
        sb.append("\n");

        return sb.toString();
    }
}
