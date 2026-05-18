public class Paciente {
    public String nome;
    public String cpf;
    public int idade;
    public String telefone;
    public String convenio;
    public boolean ativo;
    public double multaPendente;

    public Paciente() {
        ativo = true;
        multaPendente = 0.0;
    }

    public Paciente(String pNome, String pCpf) {
        nome = pNome;
        cpf = pCpf;
        ativo = true;
        multaPendente = 0.0;
    }

    public Paciente(String pNome, String pCpf, int pIdade, String pTelefone) {
        nome = pNome;
        cpf = pCpf;
        idade = pIdade;
        telefone = pTelefone;
        ativo = true;
        multaPendente = 0.0;
    }

    public Paciente(String pNome, String pCpf, int pIdade, String pTelefone, String pConvenio) {
        nome = pNome;
        cpf = pCpf;
        idade = pIdade;
        telefone = pTelefone;
        convenio = pConvenio;
        ativo = true;
        multaPendente = 0.0;
    }

    public void complementar(int pIdade, String pTelefone) {
        idade = pIdade;
        telefone = pTelefone;
    }

    public void complementar(int pIdade, String pTelefone, String pConvenio) {
        idade = pIdade;
        telefone = pTelefone;
        convenio = pConvenio;
    }

    public void complementar(String pConvenio) {
        convenio = pConvenio;
    }

    public void desativar() {
        ativo = false;
    }

    public void exibir() {
        System.out.println("---------------------------------------------");
        System.out.println("Nome:      " + nome);
        System.out.println("CPF:       " + cpf);
        System.out.println("Idade:     " + (idade > 0 ? idade : "(nao informada)"));
        System.out.println("Telefone:  " + (telefone != null ? telefone : "(nao informado)"));
        System.out.println("Convenio:  " + (convenio != null ? convenio : "(sem convenio)"));
        System.out.println("Status:    " + (ativo ? "ATIVO" : "INATIVO"));
        if (multaPendente > 0) {
            System.out.println("Multa pendente: R$ " + multaPendente);
        }
    }
}
