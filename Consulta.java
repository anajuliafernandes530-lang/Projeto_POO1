public class Consulta {
    public String cpfPaciente;
    public String nomeProfissional;
    public String especialidade;
    public String data;   // formato "DD/MM/AAAA"
    public String hora;   // formato "HH:MM"
    public String tipo;   // "inicial", "retorno", "avaliacao"
    public String status; // "agendada", "realizada", "cancelada", "remarcada"
    public String motivoCancelamento;
    public String observacoes;
    public String diagnostico;
    public String[] procedimentos; // tamanho fixo 10
    public int qtdProcedimentos;   // contadora
    public double valorCobrado;

    public Consulta() {
        procedimentos = new String[10];
        qtdProcedimentos = 0;
        status = "agendada";
        tipo = "inicial";
    }

    public Consulta(String pCpfPaciente, String pNomeProfissional, String pData, String pHora) {
        cpfPaciente = pCpfPaciente;
        nomeProfissional = pNomeProfissional;
        data = pData;
        hora = pHora;
        tipo = "inicial";
        status = "agendada";
        procedimentos = new String[10];
        qtdProcedimentos = 0;
    }

    public Consulta(String pCpfPaciente, String pNomeProfissional, String pData, String pHora, String pTipo) {
        cpfPaciente = pCpfPaciente;
        nomeProfissional = pNomeProfissional;
        data = pData;
        hora = pHora;
        tipo = pTipo;
        status = "agendada";
        procedimentos = new String[10];
        qtdProcedimentos = 0;
    }

    public Consulta(String pCpfPaciente, String pNomeProfissional, String pEspecialidade,
                    String pData, String pHora, String pTipo) {
        cpfPaciente = pCpfPaciente;
        nomeProfissional = pNomeProfissional;
        especialidade = pEspecialidade;
        data = pData;
        hora = pHora;
        tipo = pTipo;
        status = "agendada";
        procedimentos = new String[10];
        qtdProcedimentos = 0;
    }


    public void registrarAtendimento(String pObservacoes) {
        observacoes = pObservacoes;
        status = "realizada";
    }

    public void registrarAtendimento(String pObservacoes, String pDiagnostico) {
        observacoes = pObservacoes;
        diagnostico = pDiagnostico;
        status = "realizada";
    }

    public void registrarAtendimento(String pObservacoes, String pDiagnostico,
                                     String[] pProcedimentos, int qtd) {
        observacoes = pObservacoes;
        diagnostico = pDiagnostico;
        int i = 0;
        while (i < qtd && qtdProcedimentos < 10) {
            procedimentos[qtdProcedimentos] = pProcedimentos[i];
            qtdProcedimentos = qtdProcedimentos + 1;
            i = i + 1;
        }
        status = "realizada";
    }

    public boolean adicionarProcedimento(String procedimento) {
        if (qtdProcedimentos >= 10) return false;
        procedimentos[qtdProcedimentos] = procedimento;
        qtdProcedimentos = qtdProcedimentos + 1;
        return true;
    }

    public void cancelar() {
        status = "cancelada";
    }

    public void cancelar(String motivo) {
        status = "cancelada";
        motivoCancelamento = motivo;
    }

    public void exibir() {
        System.out.println("---------------------------------------------");
        System.out.println("Paciente CPF:  " + cpfPaciente);
        System.out.println("Profissional:  " + nomeProfissional);
        if (especialidade != null) System.out.println("Especialidade: " + especialidade);
        System.out.println("Data/Hora:     " + data + " " + hora);
        System.out.println("Tipo:          " + tipo);
        System.out.println("Status:        " + status);
        if (observacoes != null) System.out.println("Observacoes:   " + observacoes);
        if (diagnostico != null) System.out.println("Diagnostico:   " + diagnostico);
        if (qtdProcedimentos > 0) {
            System.out.println("Procedimentos:");
            int i = 0;
            while (i < qtdProcedimentos) {
                System.out.println("  - " + procedimentos[i]);
                i = i + 1;
            }
        }
        if (motivoCancelamento != null) {
            System.out.println("Motivo cancelamento: " + motivoCancelamento);
        }
    }
}
