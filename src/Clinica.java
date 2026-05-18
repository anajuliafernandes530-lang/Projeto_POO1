public class Clinica {
    public Paciente[] pacientes = new Paciente[100];
    public int qtdPacientes = 0;

    public Profissional[] profissionais = new Profissional[50];
    public int qtdProfissionais = 0;

    public Consulta[] consultas = new Consulta[500];
    public int qtdConsultas = 0;

    public Pagamento[] pagamentos = new Pagamento[500];
    public int qtdPagamentos = 0;

    public Clinica() {}

    public Clinica(int pCapPac) {
        pacientes = new Paciente[pCapPac];
    }

    public Clinica(int pCapPac, int pCapProf) {
        pacientes     = new Paciente[pCapPac];
        profissionais = new Profissional[pCapProf];
    }

    public Clinica(int pCapPac, int pCapProf, int pCapCons, int pCapPag) {
        pacientes     = new Paciente[pCapPac];
        profissionais = new Profissional[pCapProf];
        consultas     = new Consulta[pCapCons];
        pagamentos    = new Pagamento[pCapPag];
    }

    //PACIENTES
    public Paciente buscarPaciente(String cpf) {
        int i = 0;
        while (i < qtdPacientes) {
            if (pacientes[i].cpf.equals(cpf)) return pacientes[i];
            i = i + 1;
        }
        return null;
    }

    public boolean adicionarPaciente(Paciente p) {
        if (qtdPacientes >= pacientes.length) return false;
        if (buscarPaciente(p.cpf) != null) return false;
        pacientes[qtdPacientes] = p;
        qtdPacientes = qtdPacientes + 1;
        return true;
    }

    public void listarPacientes() {
        if (qtdPacientes == 0) { System.out.println("Nenhum paciente cadastrado."); return; }
        int i = 0;
        while (i < qtdPacientes) { pacientes[i].exibir(); i = i + 1; }
    }

    // ---------- PROFISSIONAIS ----------
    public Profissional buscarProfissional(String nome) {
        int i = 0;
        while (i < qtdProfissionais) {
            if (profissionais[i].nome.equalsIgnoreCase(nome)) return profissionais[i];
            i = i + 1;
        }
        return null;
    }

    public boolean adicionarProfissional(Profissional p) {
        if (qtdProfissionais >= profissionais.length) return false;
        if (!especialidadeValida(p.especialidade)) return false;
        profissionais[qtdProfissionais] = p;
        qtdProfissionais = qtdProfissionais + 1;
        return true;
    }

    public boolean especialidadeValida(String esp) {
        if (esp == null) return false;
        return esp.equalsIgnoreCase("clinica geral")
            || esp.equalsIgnoreCase("fisioterapia")
            || esp.equalsIgnoreCase("psicologia")
            || esp.equalsIgnoreCase("nutricao");
    }

    public void listarProfissionais() {
        if (qtdProfissionais == 0) { System.out.println("Nenhum profissional cadastrado."); return; }
        int i = 0;
        while (i < qtdProfissionais) { profissionais[i].exibir(); i = i + 1; }
    }


    public void listarProfissionais(String especialidade) {
        int i = 0; int achou = 0;
        while (i < qtdProfissionais) {
            if (profissionais[i].especialidade.equalsIgnoreCase(especialidade)) {
                profissionais[i].exibir();
                achou = achou + 1;
            }
            i = i + 1;
        }
        if (achou == 0) System.out.println("Nenhum profissional dessa especialidade.");
    }

    public Profissional buscarProfissionalDisponivel(String especialidade, String diaSemana, String data, String hora) {
        int i = 0;
        while (i < qtdProfissionais) {
            Profissional p = profissionais[i];
            if (p.especialidade.equalsIgnoreCase(especialidade)
                && p.valorConsulta > 0
                && p.atendeNoDia(diaSemana)
                && !horarioOcupado(p.nome, data, hora)) {
                return p;
            }
            i = i + 1;
        }
        return null;
    }

    public String diaToData(String s) { return s; }

    //CONSULTA
    public boolean horarioOcupado(String nomeProfissional, String data, String hora) {
        int i = 0;
        while (i < qtdConsultas) {
            Consulta c = consultas[i];
            if (c.nomeProfissional.equalsIgnoreCase(nomeProfissional)
                && c.data.equals(data) && c.hora.equals(hora)
                && c.status.equals("agendada")) {
                return true;
            }
            i = i + 1;
        }
        return false;
    }

    public String sugerirHorario(String nomeProfissional, String data) {
        // de hora em hora das 08h ate 18h
        int h = 8;
        while (h <= 18) {
            String hora;
            if (h < 10) hora = "0" + h + ":00";
            else        hora = h + ":00";
            if (!horarioOcupado(nomeProfissional, data, hora)) return hora;
            h = h + 1;
        }
        return null;
    }

    public boolean adicionarConsulta(Consulta c) {
        if (qtdConsultas >= consultas.length) return false;
        consultas[qtdConsultas] = c;
        qtdConsultas = qtdConsultas + 1;
        return true;
    }

    public void listarConsultas() {
        if (qtdConsultas == 0) { System.out.println("Nenhuma consulta agendada."); return; }
        int i = 0;
        while (i < qtdConsultas) { consultas[i].exibir(); i = i + 1; }
    }


    public void listarConsultas(String cpfPaciente) {
        int i = 0; int achou = 0;
        while (i < qtdConsultas) {
            if (consultas[i].cpfPaciente.equals(cpfPaciente)) {
                consultas[i].exibir();
                achou = achou + 1;
            }
            i = i + 1;
        }
        if (achou == 0) System.out.println("Nenhuma consulta para esse CPF.");
    }


    public void listarConsultas(String nomeProfissional, boolean porProfissional) {
        int i = 0; int achou = 0;
        while (i < qtdConsultas) {
            if (consultas[i].nomeProfissional.equalsIgnoreCase(nomeProfissional)) {
                consultas[i].exibir();
                achou = achou + 1;
            }
            i = i + 1;
        }
        if (achou == 0) System.out.println("Nenhuma consulta para esse profissional.");
    }


    public void listarConsultas(String dataInicio, String dataFim, boolean porPeriodo) {
        int i = 0; int achou = 0;
        int ini = paraNumero(dataInicio);
        int fim = paraNumero(dataFim);
        while (i < qtdConsultas) {
            int d = paraNumero(consultas[i].data);
            if (d >= ini && d <= fim) {
                consultas[i].exibir();
                achou = achou + 1;
            }
            i = i + 1;
        }
        if (achou == 0) System.out.println("Nenhuma consulta nesse periodo.");
    }

    public int paraNumero(String data) {
        // "DD/MM/AAAA" -> AAAAMMDD
        if (data == null || data.length() != 10) return 0;
        String dd = data.substring(0,2);
        String mm = data.substring(3,5);
        String aa = data.substring(6,10);
        String completo = aa + mm + dd;
        int result = 0;
        int i = 0;
        while (i < completo.length()) {
            result = result * 10 + (completo.charAt(i) - '0');
            i = i + 1;
        }
        return result;
    }

    //PAGAMENTOS
    public boolean adicionarPagamento(Pagamento p) {
        if (qtdPagamentos >= pagamentos.length) return false;
        pagamentos[qtdPagamentos] = p;
        qtdPagamentos = qtdPagamentos + 1;
        return true;
    }

    public void listarPagamentos() {
        if (qtdPagamentos == 0) { System.out.println("Nenhum pagamento registrado."); return; }
        int i = 0;
        while (i < qtdPagamentos) { pagamentos[i].exibir(); i = i + 1; }
    }

    //RELATORIO FINANCEIRO
    public void resumoFinanceiro() {
        int realizadas = 0; int canceladas = 0;
        double totalFaturado = 0; double totalMultas = 0;
        int i = 0;
        while (i < qtdConsultas) {
            if (consultas[i].status.equals("realizada"))  realizadas = realizadas + 1;
            if (consultas[i].status.equals("cancelada"))  canceladas = canceladas + 1;
            i = i + 1;
        }
        i = 0;
        while (i < qtdPagamentos) {
            totalFaturado = totalFaturado + pagamentos[i].valorFinal;
            totalMultas   = totalMultas   + pagamentos[i].multa;
            i = i + 1;
        }
        System.out.println("===== RESUMO FINANCEIRO =====");
        System.out.println("Atendimentos realizados: " + realizadas);
        System.out.println("Cancelamentos:           " + canceladas);
        System.out.println("Total faturado:          R$ " + totalFaturado);
        System.out.println("Total em multas:         R$ " + totalMultas);
    }
}
