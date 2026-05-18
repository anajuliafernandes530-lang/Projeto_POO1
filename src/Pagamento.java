public class Pagamento {
    public String cpfPaciente;
    public String nomeProfissional;
    public String data;
    public String tipoPagamento; // "dinheiro", "cartao", "convenio"
    public double valorBase;
    public double desconto;
    public double multa;
    public double valorFinal;
    public int parcelas;


    public Pagamento() {
        parcelas = 1;
    }

    public Pagamento(String pCpfPaciente, double pValor, String pTipoPagamento) {
        cpfPaciente = pCpfPaciente;
        valorBase = pValor;
        valorFinal = pValor;
        tipoPagamento = pTipoPagamento;
        parcelas = 1;
    }

    public Pagamento(String pCpfPaciente, double pValor, String pTipoPagamento, int pParcelas) {
        cpfPaciente = pCpfPaciente;
        valorBase = pValor;
        valorFinal = pValor;
        tipoPagamento = pTipoPagamento;
        parcelas = pParcelas;
    }

    public Pagamento(String pCpfPaciente, String pNomeProfissional, String pData,
                     double pValor, String pTipoPagamento, int pParcelas) {
        cpfPaciente = pCpfPaciente;
        nomeProfissional = pNomeProfissional;
        data = pData;
        valorBase = pValor;
        valorFinal = pValor;
        tipoPagamento = pTipoPagamento;
        parcelas = pParcelas;
    }


    public double calcular(double pValorBase) {
        valorBase = pValorBase;
        valorFinal = pValorBase;
        if (valorFinal < 0) valorFinal = 0;
        return valorFinal;
    }

    //DESCONTO
    public double calcular(double pValorBase, String tipoConsulta, boolean temConvenio) {
        valorBase = pValorBase;
        double total = pValorBase;
        if (tipoConsulta != null && tipoConsulta.equalsIgnoreCase("retorno")) {
            total = total - (pValorBase * 0.20);
        }
        if (temConvenio) {
            total = total - (pValorBase * 0.40);
        }
        desconto = pValorBase - total;
        valorFinal = total;
        if (valorFinal < 0) valorFinal = 0;
        return valorFinal;
    }

    // Com desconto e multa pendente
    public double calcular(double pValorBase, String tipoConsulta, boolean temConvenio, double multaPendente) {
        double v = calcular(pValorBase, tipoConsulta, temConvenio);
        multa = multaPendente;
        valorFinal = v + multaPendente;
        if (valorFinal < 0) valorFinal = 0;
        return valorFinal;
    }

    public boolean validarParcelas() {
        if (parcelas < 1) return false;
        if (parcelas > 1 && !"cartao".equalsIgnoreCase(tipoPagamento)) return false;
        if (parcelas > 3) return false;
        return true;
    }

    public double valorParcela() {
        if (parcelas <= 0) return valorFinal;
        return valorFinal / parcelas;
    }

    public void exibir() {
        System.out.println("---------------------------------------------");
        System.out.println("Paciente CPF:  " + cpfPaciente);
        if (nomeProfissional != null) System.out.println("Profissional:  " + nomeProfissional);
        if (data != null)             System.out.println("Data:          " + data);
        System.out.println("Valor base:    R$ " + valorBase);
        if (desconto > 0) System.out.println("Desconto:      R$ " + desconto);
        if (multa > 0)    System.out.println("Multa:         R$ " + multa);
        System.out.println("Valor final:   R$ " + valorFinal);
        System.out.println("Tipo:          " + tipoPagamento);
        System.out.println("Parcelas:      " + parcelas + "x de R$ " + valorParcela());
    }
}
