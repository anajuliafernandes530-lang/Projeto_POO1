// Classe Profissional - sem herança, tudo público.
public class Profissional {
    public String nome;
    public String especialidade; // "clinica geral", "fisioterapia", "psicologia", "nutricao"
    public String registro;
    public double valorConsulta;
    public String[] diasAtendimento; // array com tamanho fixo
    public int qtdDias;              // variavel contadora

    // ---------- SOBRECARGA DE CONSTRUTORES (4) ----------
    public Profissional() {
        diasAtendimento = new String[7];
        qtdDias = 0;
    }

    public Profissional(String pNome, String pEspecialidade) {
        nome = pNome;
        especialidade = pEspecialidade;
        diasAtendimento = new String[7];
        qtdDias = 0;
    }

    public Profissional(String pNome, String pEspecialidade, String pRegistro, double pValorConsulta) {
        nome = pNome;
        especialidade = pEspecialidade;
        registro = pRegistro;
        valorConsulta = pValorConsulta;
        diasAtendimento = new String[7];
        qtdDias = 0;
    }

    public Profissional(String pNome, String pEspecialidade, String pRegistro,
                        double pValorConsulta, String[] dias, int qtd) {
        nome = pNome;
        especialidade = pEspecialidade;
        registro = pRegistro;
        valorConsulta = pValorConsulta;
        diasAtendimento = new String[7];
        qtdDias = 0;
        int i = 0;
        while (i < qtd && i < 7) {
            diasAtendimento[qtdDias] = dias[i];
            qtdDias = qtdDias + 1;
            i = i + 1;
        }
    }

    // ---------- SOBRECARGA DE MÉTODOS ----------
    public void atualizar(String pRegistro, double pValorConsulta) {
        registro = pRegistro;
        valorConsulta = pValorConsulta;
    }

    public void atualizar(String pRegistro, double pValorConsulta, String[] dias, int qtd) {
        registro = pRegistro;
        valorConsulta = pValorConsulta;
        qtdDias = 0;
        int i = 0;
        while (i < qtd && i < 7) {
            diasAtendimento[qtdDias] = dias[i];
            qtdDias = qtdDias + 1;
            i = i + 1;
        }
    }

    public boolean atendeNoDia(String dia) {
        int i = 0;
        while (i < qtdDias) {
            if (diasAtendimento[i].equalsIgnoreCase(dia)) {
                return true;
            }
            i = i + 1;
        }
        return false;
    }

    public boolean atendeNoDia(String dia, boolean exigirValor) {
        if (exigirValor && valorConsulta <= 0) {
            return false;
        }
        return atendeNoDia(dia);
    }

    public void exibir() {
        System.out.println("---------------------------------------------");
        System.out.println("Nome:          " + nome);
        System.out.println("Especialidade: " + especialidade);
        System.out.println("Registro:      " + (registro != null ? registro : "(nao informado)"));
        System.out.println("Valor:         " + (valorConsulta > 0 ? "R$ " + valorConsulta : "(nao definido)"));
        System.out.print("Dias:          ");
        if (qtdDias == 0) {
            System.out.println("(nao definidos)");
        } else {
            int i = 0;
            while (i < qtdDias) {
                System.out.print(diasAtendimento[i]);
                if (i < qtdDias - 1) System.out.print(", ");
                i = i + 1;
            }
            System.out.println();
        }
    }
}
