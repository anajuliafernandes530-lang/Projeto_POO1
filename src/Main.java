import java.util.Scanner;
public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static Clinica clinica = new Clinica();

    public static void main(String[] args) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println();
            System.out.println("===== CLINICA VIDAPLENA =====");
            System.out.println("1. Pacientes");
            System.out.println("2. Profissionais");
            System.out.println("3. Consultas");
            System.out.println("4. Pagamentos");
            System.out.println("5. Relatorios");
            System.out.println("0. Sair");
            opcao = lerInt("Opcao: ");
            if (opcao == 1) menuPacientes();
            else if (opcao == 2) menuProfissionais();
            else if (opcao == 3) menuConsultas();
            else if (opcao == 4) menuPagamentos();
            else if (opcao == 5) menuRelatorios();
            else if (opcao != 0) System.out.println("Opcao invalida.");
        }
        System.out.println("Sistema encerrado.");
    }

    // ============ HELPERS ============
    public static int lerInt(String msg) {
        System.out.print(msg);
        while (!sc.hasNextInt()) { sc.next(); System.out.print(msg); }
        int v = sc.nextInt(); sc.nextLine();
        return v;
    }
    public static double lerDouble(String msg) {
        System.out.print(msg);
        while (!sc.hasNextDouble()) { sc.next(); System.out.print(msg); }
        double v = sc.nextDouble(); sc.nextLine();
        return v;
    }
    public static String lerStr(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    // ============ PACIENTES ============
    public static void menuPacientes() {
        int op = -1;
        while (op != 0) {
            System.out.println();
            System.out.println("--- PACIENTES ---");
            System.out.println("1. Cadastrar (apenas nome e CPF)");
            System.out.println("2. Cadastrar (nome, CPF, idade, telefone)");
            System.out.println("3. Cadastrar (cadastro completo + convenio)");
            System.out.println("4. Complementar cadastro (idade e telefone)");
            System.out.println("5. Complementar cadastro (idade, telefone, convenio)");
            System.out.println("6. Desativar paciente");
            System.out.println("7. Buscar por CPF");
            System.out.println("8. Listar todos");
            System.out.println("0. Voltar");
            op = lerInt("Opcao: ");
            if (op == 1) {
                String n = lerStr("Nome: "); String c = lerStr("CPF: ");
                if (clinica.adicionarPaciente(new Paciente(n, c)))
                    System.out.println("Paciente cadastrado.");
                else System.out.println("CPF ja cadastrado ou limite atingido.");
            } else if (op == 2) {
                String n = lerStr("Nome: "); String c = lerStr("CPF: ");
                int i = lerInt("Idade: "); String t = lerStr("Telefone: ");
                if (clinica.adicionarPaciente(new Paciente(n, c, i, t)))
                    System.out.println("Paciente cadastrado.");
                else System.out.println("CPF ja cadastrado ou limite atingido.");
            } else if (op == 3) {
                String n = lerStr("Nome: "); String c = lerStr("CPF: ");
                int i = lerInt("Idade: "); String t = lerStr("Telefone: ");
                String co = lerStr("Convenio: ");
                if (clinica.adicionarPaciente(new Paciente(n, c, i, t, co)))
                    System.out.println("Paciente cadastrado.");
                else System.out.println("CPF ja cadastrado ou limite atingido.");
            } else if (op == 4) {
                Paciente p = clinica.buscarPaciente(lerStr("CPF: "));
                if (p == null) { System.out.println("Nao encontrado."); continue; }
                int i = lerInt("Idade: "); String t = lerStr("Telefone: ");
                p.complementar(i, t);
                System.out.println("Atualizado.");
            } else if (op == 5) {
                Paciente p = clinica.buscarPaciente(lerStr("CPF: "));
                if (p == null) { System.out.println("Nao encontrado."); continue; }
                int i = lerInt("Idade: "); String t = lerStr("Telefone: ");
                String co = lerStr("Convenio: ");
                p.complementar(i, t, co);
                System.out.println("Atualizado.");
            } else if (op == 6) {
                Paciente p = clinica.buscarPaciente(lerStr("CPF: "));
                if (p == null) { System.out.println("Nao encontrado."); continue; }
                p.desativar(); System.out.println("Paciente desativado.");
            } else if (op == 7) {
                Paciente p = clinica.buscarPaciente(lerStr("CPF: "));
                if (p == null) System.out.println("Nao encontrado."); else p.exibir();
            } else if (op == 8) {
                clinica.listarPacientes();
            } else if (op != 0) System.out.println("Opcao invalida.");
        }
    }

    // ============ PROFISSIONAIS ============
    public static void menuProfissionais() {
        int op = -1;
        while (op != 0) {
            System.out.println();
            System.out.println("--- PROFISSIONAIS ---");
            System.out.println("1. Cadastrar (nome e especialidade)");
            System.out.println("2. Cadastrar completo (com dias)");
            System.out.println("3. Atualizar (registro e valor)");
            System.out.println("4. Atualizar (registro, valor e dias)");
            System.out.println("5. Listar todos");
            System.out.println("6. Listar por especialidade");
            System.out.println("0. Voltar");
            op = lerInt("Opcao: ");
            if (op == 1) {
                String n = lerStr("Nome: ");
                String e = lerStr("Especialidade (clinica geral/fisioterapia/psicologia/nutricao): ");
                if (!clinica.especialidadeValida(e)) { System.out.println("Especialidade invalida."); continue; }
                if (clinica.adicionarProfissional(new Profissional(n, e)))
                    System.out.println("Cadastrado.");
                else System.out.println("Limite ou especialidade invalida.");
            } else if (op == 2) {
                String n = lerStr("Nome: ");
                String e = lerStr("Especialidade: ");
                if (!clinica.especialidadeValida(e)) { System.out.println("Especialidade invalida."); continue; }
                String r = lerStr("Registro: ");
                double v = lerDouble("Valor da consulta: ");
                int qtd = lerInt("Quantos dias atende? ");
                String[] dias = new String[7]; int i = 0;
                while (i < qtd && i < 7) { dias[i] = lerStr("Dia " + (i+1) + ": "); i = i + 1; }
                if (clinica.adicionarProfissional(new Profissional(n, e, r, v, dias, qtd)))
                    System.out.println("Cadastrado.");
                else System.out.println("Erro ao cadastrar.");
            } else if (op == 3) {
                Profissional p = clinica.buscarProfissional(lerStr("Nome do profissional: "));
                if (p == null) { System.out.println("Nao encontrado."); continue; }
                String r = lerStr("Registro: "); double v = lerDouble("Valor: ");
                p.atualizar(r, v); System.out.println("Atualizado.");
            } else if (op == 4) {
                Profissional p = clinica.buscarProfissional(lerStr("Nome do profissional: "));
                if (p == null) { System.out.println("Nao encontrado."); continue; }
                String r = lerStr("Registro: "); double v = lerDouble("Valor: ");
                int qtd = lerInt("Quantos dias atende? ");
                String[] dias = new String[7]; int i = 0;
                while (i < qtd && i < 7) { dias[i] = lerStr("Dia " + (i+1) + ": "); i = i + 1; }
                p.atualizar(r, v, dias, qtd); System.out.println("Atualizado.");
            } else if (op == 5) {
                clinica.listarProfissionais();
            } else if (op == 6) {
                clinica.listarProfissionais(lerStr("Especialidade: "));
            } else if (op != 0) System.out.println("Opcao invalida.");
        }
    }

    // ============ CONSULTAS ============
    public static void menuConsultas() {
        int op = -1;
        while (op != 0) {
            System.out.println();
            System.out.println("--- CONSULTAS ---");
            System.out.println("1. Agendar (paciente + profissional + tipo)");
            System.out.println("2. Agendar buscando profissional por especialidade");
            System.out.println("3. Registrar atendimento (so observacoes)");
            System.out.println("4. Registrar atendimento (obs + diagnostico)");
            System.out.println("5. Registrar atendimento (obs + diag + procedimentos)");
            System.out.println("6. Cancelar consulta");
            System.out.println("7. Remarcar consulta");
            System.out.println("8. Listar todas");
            System.out.println("9. Listar por CPF");
            System.out.println("0. Voltar");
            op = lerInt("Opcao: ");
            if (op == 1) agendarComProfissional();
            else if (op == 2) agendarPorEspecialidade();
            else if (op == 3) registrarAtendimento(1);
            else if (op == 4) registrarAtendimento(2);
            else if (op == 5) registrarAtendimento(3);
            else if (op == 6) cancelarConsulta();
            else if (op == 7) remarcarConsulta();
            else if (op == 8) clinica.listarConsultas();
            else if (op == 9) clinica.listarConsultas(lerStr("CPF: "));
            else if (op != 0) System.out.println("Opcao invalida.");
        }
    }

    public static void agendarComProfissional() {
        String cpf = lerStr("CPF do paciente: ");
        Paciente p = clinica.buscarPaciente(cpf);
        if (p == null) { System.out.println("Paciente nao encontrado."); return; }
        if (!p.ativo)  { System.out.println("Paciente INATIVO. Nao pode agendar."); return; }
        String nomeProf = lerStr("Nome do profissional: ");
        Profissional pr = clinica.buscarProfissional(nomeProf);
        if (pr == null) { System.out.println("Profissional nao encontrado."); return; }
        if (pr.valorConsulta <= 0) { System.out.println("Profissional sem valor de consulta. Bloqueado."); return; }
        String dia  = lerStr("Dia da semana (segunda/terca/...): ");
        String data = lerStr("Data (DD/MM/AAAA): ");
        String hora = lerStr("Hora (HH:MM): ");
        if (!pr.atendeNoDia(dia)) { System.out.println("Profissional nao atende nesse dia."); return; }
        if (clinica.horarioOcupado(pr.nome, data, hora)) {
            System.out.println("Horario ocupado.");
            String sug = clinica.sugerirHorario(pr.nome, data);
            if (sug != null) {
                System.out.println("Proximo horario livre: " + sug);
                String resp = lerStr("Aceita esse horario? (s/n): ");
                if (resp.equalsIgnoreCase("s")) hora = sug;
                else return;
            } else { System.out.println("Sem horarios livres."); return; }
        }
        String tipo = lerStr("Tipo (inicial/retorno/avaliacao) [enter = inicial]: ");
        Consulta c;
        if (tipo == null || tipo.trim().length() == 0)
            c = new Consulta(cpf, pr.nome, data, hora);
        else
            c = new Consulta(cpf, pr.nome, pr.especialidade, data, hora, tipo);
        clinica.adicionarConsulta(c);
        System.out.println("Consulta agendada.");
    }

    public static void agendarPorEspecialidade() {
        String cpf = lerStr("CPF do paciente: ");
        Paciente p = clinica.buscarPaciente(cpf);
        if (p == null || !p.ativo) { System.out.println("Paciente invalido/inativo."); return; }
        String esp  = lerStr("Especialidade: ");
        String dia  = lerStr("Dia da semana: ");
        String data = lerStr("Data (DD/MM/AAAA): ");
        String hora = lerStr("Hora (HH:MM): ");
        Profissional pr = clinica.buscarProfissionalDisponivel(esp, dia, data, hora);
        if (pr == null) { System.out.println("Nenhum profissional disponivel."); return; }
        Consulta c = new Consulta(cpf, pr.nome, esp, data, hora, "inicial");
        clinica.adicionarConsulta(c);
        System.out.println("Agendada com: " + pr.nome);
    }

    public static void registrarAtendimento(int modo) {
        String cpf = lerStr("CPF do paciente: ");
        String data = lerStr("Data da consulta (DD/MM/AAAA): ");
        String hora = lerStr("Hora (HH:MM): ");
        Consulta alvo = null; int i = 0;
        while (i < clinica.qtdConsultas) {
            Consulta c = clinica.consultas[i];
            if (c.cpfPaciente.equals(cpf) && c.data.equals(data) && c.hora.equals(hora)) { alvo = c; break; }
            i = i + 1;
        }
        if (alvo == null) { System.out.println("Consulta nao encontrada."); return; }
        if (!alvo.status.equals("agendada")) { System.out.println("Status atual: " + alvo.status + ". Nao aceita registro."); return; }
        String obs = lerStr("Observacoes: ");
        if (modo == 1) {
            alvo.registrarAtendimento(obs);
        } else if (modo == 2) {
            String diag = lerStr("Diagnostico: ");
            alvo.registrarAtendimento(obs, diag);
        } else {
            String diag = lerStr("Diagnostico: ");
            int forma = lerInt("Procedimentos: 1=um por vez, 2=todos de uma vez: ");
            if (forma == 1) {
                alvo.observacoes = obs; alvo.diagnostico = diag;
                String mais = "s";
                while (mais.equalsIgnoreCase("s") && alvo.qtdProcedimentos < 10) {
                    String pr = lerStr("Procedimento: ");
                    alvo.adicionarProcedimento(pr);
                    mais = lerStr("Adicionar outro? (s/n): ");
                }
                alvo.status = "realizada";
            } else {
                int qtd = lerInt("Quantos procedimentos? ");
                if (qtd > 10) qtd = 10;
                String[] arr = new String[10]; int k = 0;
                while (k < qtd) { arr[k] = lerStr("Procedimento " + (k+1) + ": "); k = k + 1; }
                alvo.registrarAtendimento(obs, diag, arr, qtd);
            }
        }
        System.out.println("===== RESUMO =====");
        alvo.exibir();
    }

    public static void cancelarConsulta() {
        String cpf  = lerStr("CPF: ");
        String data = lerStr("Data (DD/MM/AAAA): ");
        String hora = lerStr("Hora (HH:MM): ");
        String horaAtual = lerStr("Hora atual (HH:MM, mesmo dia): ");
        Consulta alvo = null; int i = 0;
        while (i < clinica.qtdConsultas) {
            Consulta c = clinica.consultas[i];
            if (c.cpfPaciente.equals(cpf) && c.data.equals(data) && c.hora.equals(hora)) { alvo = c; break; }
            i = i + 1;
        }
        if (alvo == null) { System.out.println("Nao encontrada."); return; }
        if (!alvo.status.equals("agendada")) { System.out.println("Status: " + alvo.status + ". Nao pode cancelar."); return; }

        if (hora.length() < 5 || horaAtual.length() < 5) {
             System.out.println("Hora invalida. Considerando sem multa automatica.");
        } else {
            int h1 = (hora.charAt(0) - '0') * 10 + (hora.charAt(1) - '0');
            int m1 = (hora.charAt(3) - '0') * 10 + (hora.charAt(4) - '0');
            int minConsulta = h1 * 60 + m1;

            int h2 = (horaAtual.charAt(0) - '0') * 10 + (horaAtual.charAt(1) - '0');
            int m2 = (horaAtual.charAt(3) - '0') * 10 + (horaAtual.charAt(4) - '0');
            int minAtual = h2 * 60 + m2;
            
            int diff = minConsulta - minAtual;
            if (diff >= 0 && diff < 120) {
                System.out.println("Faltam menos de 2h. Multa de R$ 50,00 aplicada.");
                Paciente p = clinica.buscarPaciente(cpf);
                if (p != null) p.multaPendente = p.multaPendente + 50.0;
            }
        }
        String motivo = lerStr("Motivo (enter para nenhum): ");
        if (motivo == null || motivo.trim().length() == 0) alvo.cancelar();
        else alvo.cancelar(motivo);
        System.out.println("Cancelada.");
    }

    public static void remarcarConsulta() {
        String cpf  = lerStr("CPF: ");
        String data = lerStr("Data atual (DD/MM/AAAA): ");
        String hora = lerStr("Hora atual (HH:MM): ");
        Consulta alvo = null; int i = 0;
        while (i < clinica.qtdConsultas) {
            Consulta c = clinica.consultas[i];
            if (c.cpfPaciente.equals(cpf) && c.data.equals(data) && c.hora.equals(hora)) { alvo = c; break; }
            i = i + 1;
        }
        if (alvo == null) { System.out.println("Nao encontrada."); return; }
        if (!alvo.status.equals("agendada")) { System.out.println("Status: " + alvo.status + "."); return; }
        Profissional pr = clinica.buscarProfissional(alvo.nomeProfissional);
        int modo = lerInt("1 = mudar so o horario, 2 = mudar data e horario: ");
        String novaData = data; String novaHora;
        if (modo == 2) novaData = lerStr("Nova data: ");
        novaHora = lerStr("Nova hora: ");
        String dia = lerStr("Dia da semana da nova data: ");
        if (pr != null && !pr.atendeNoDia(dia)) { System.out.println("Profissional nao atende nesse dia."); return; }
        if (clinica.horarioOcupado(alvo.nomeProfissional, novaData, novaHora)) { System.out.println("Horario ocupado."); return; }
        alvo.status = "remarcada";
        Consulta nova = new Consulta(alvo.cpfPaciente, alvo.nomeProfissional, alvo.especialidade, novaData, novaHora, alvo.tipo);
        clinica.adicionarConsulta(nova);
        System.out.println("Remarcada com sucesso.");
    }

    // ============ PAGAMENTOS ============
    public static void menuPagamentos() {
        int op = -1;
        while (op != 0) {
            System.out.println();
            System.out.println("--- PAGAMENTOS ---");
            System.out.println("1. Pagamento direto (informa valor)");
            System.out.println("2. Pagamento calculado (sem desconto)");
            System.out.println("3. Pagamento calculado (com desconto)");
            System.out.println("4. Pagamento calculado (desconto + multa pendente)");
            System.out.println("5. Listar pagamentos");
            System.out.println("0. Voltar");
            op = lerInt("Opcao: ");
            if (op == 1) {
                String cpf = lerStr("CPF: "); double v = lerDouble("Valor: ");
                String tipo = lerStr("Tipo (dinheiro/cartao/convenio): ");
                int parc = 1;
                if (tipo.equalsIgnoreCase("cartao")) parc = lerInt("Parcelas (1-3): ");
                Pagamento pg = new Pagamento(cpf, v, tipo, parc);
                if (!pg.validarParcelas()) { System.out.println("Parcelas invalidas."); continue; }
                clinica.adicionarPagamento(pg);
                pg.exibir();
            } else if (op >= 2 && op <= 4) {
                String cpf = lerStr("CPF: ");
                Paciente p = clinica.buscarPaciente(cpf);
                if (p == null) { System.out.println("Nao encontrado."); continue; }
                String nomeProf = lerStr("Nome do profissional: ");
                Profissional pr = clinica.buscarProfissional(nomeProf);
                if (pr == null) { System.out.println("Profissional nao encontrado."); continue; }
                String tipoCons = lerStr("Tipo da consulta (inicial/retorno/avaliacao): ");
                String tipoPag = lerStr("Tipo pagamento (dinheiro/cartao/convenio): ");
                int parc = 1;
                if (tipoPag.equalsIgnoreCase("cartao")) parc = lerInt("Parcelas (1-3): ");
                Pagamento pg = new Pagamento(cpf, pr.valorConsulta, tipoPag, parc);
                if (op == 2) pg.calcular(pr.valorConsulta);
                else if (op == 3) pg.calcular(pr.valorConsulta, tipoCons, p.convenio != null);
                else {
                    pg.calcular(pr.valorConsulta, tipoCons, p.convenio != null, p.multaPendente);
                    p.multaPendente = 0.0;
                }
                if (!pg.validarParcelas()) { System.out.println("Parcelas invalidas."); continue; }
                clinica.adicionarPagamento(pg);
                pg.exibir();
            } else if (op == 5) clinica.listarPagamentos();
            else if (op != 0) System.out.println("Opcao invalida.");
        }
    }

    // ============ RELATORIOS ============
    public static void menuRelatorios() {
        int op = -1;
        while (op != 0) {
            System.out.println();
            System.out.println("--- RELATORIOS ---");
            System.out.println("1. Geral (todas as consultas)");
            System.out.println("2. Por profissional");
            System.out.println("3. Por periodo (datas)");
            System.out.println("4. Resumo financeiro");
            System.out.println("0. Voltar");
            op = lerInt("Opcao: ");
            if (op == 1) clinica.listarConsultas();
            else if (op == 2) clinica.listarConsultas(lerStr("Nome do profissional: "), true);
            else if (op == 3) {
                String d1 = lerStr("Data inicio (DD/MM/AAAA): ");
                String d2 = lerStr("Data fim (DD/MM/AAAA): ");
                clinica.listarConsultas(d1, d2, true);
            } else if (op == 4) clinica.resumoFinanceiro();
            else if (op != 0) System.out.println("Opcao invalida.");
        }
    }
}
