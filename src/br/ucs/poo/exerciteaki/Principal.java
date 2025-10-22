package br.ucs.poo.exerciteaki;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Principal {

    private static Scanner scanner = new Scanner(System.in);
    private static int contadorId = 1;
    private static Academia academia;

    public static void main(String[] args) {
        inicializarAcademia();

        boolean ativo = true;
        while (ativo) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Login");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarUsuario();
                case 2 -> realizarLogin();
                case 3 -> {
                    ativo = false;
                    System.out.println("Encerrando...");
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void inicializarAcademia() {
        System.out.println("---Cadastro da Academia---");
        System.out.print("Login do administrador padrão: ");
        String login = scanner.nextLine();

        System.out.print("Senha do administrador padrão: ");
        String senha = scanner.nextLine();
        
        inicializarAcademiaComValoresPadrao();
//        System.out.print("Nome da academia: ");
//        String nome = scanner.nextLine();
//
//        System.out.print("Telefone: ");
//        String telefone = scanner.nextLine();
//
//        System.out.print("Website: ");
//        String website = scanner.nextLine();
//
//        System.out.println("=== Endereço da academia ===");
//        System.out.print("ID: ");
//        int idEndereco = scanner.nextInt();
//        scanner.nextLine();
//
//        System.out.print("Logradouro: ");
//        String logradouro = scanner.nextLine();
//
//        System.out.print("Número: ");
//        String numero = scanner.nextLine();
//
//        System.out.print("Complemento: ");
//        String complemento = scanner.nextLine();
//
//        System.out.print("Bairro: ");
//        String bairro = scanner.nextLine();
//
//        System.out.print("CEP: ");
//        String cep = scanner.nextLine();
//
//        System.out.print("Cidade: ");
//        String cidade = scanner.nextLine();
//
//        System.out.print("Estado: ");
//        String estado = scanner.nextLine();

//        Endereco endereco = new Endereco(idEndereco, logradouro, numero, complemento, bairro, cep, cidade, estado);
        Pessoa admin = new Administrador(login, senha, true, 0, "Admin Padrão", "admin@academia.com", null, null);
//
//        academia = new Academia(login, senha, 1, nome, telefone, website, endereco, admin);
//        Storage.addAcademia(academia);
        Storage.addPessoa(admin);
        academia.login("admin", "1234");
        
        preencherSistemaComDadosDeTeste();
        System.out.println("Academia cadastrada com sucesso.");
        
    }

    private static void cadastrarUsuario() {
        System.out.println("\n=== CADASTRO DE USUÁRIO ===");
        System.out.println("1. Aluno");
        System.out.println("2. Instrutor");
        System.out.println("3. Administrador");
        System.out.print("Escolha o tipo: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Login: ");
        String login = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        int id = contadorId++;

        Pessoa novoUsuario = null;

        switch (tipo) {
            case 1 -> {
                System.out.print("Data de nascimento (dd/MM/yyyy): ");
                Date nascimento = parseDate(scanner.nextLine());

                System.out.print("Altura (em metros): ");
                float altura = scanner.nextFloat();
                scanner.nextLine();

                novoUsuario = new Aluno(login, senha, false, id, academia, nome, email, telefone, nascimento, altura);
                academia.adicionarAluno((Aluno) novoUsuario);
            }
            case 2 -> {
                System.out.print("ID da formação: ");
                int formacaoId = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Nome da formação: ");
                String formacaoNome = scanner.nextLine();

                System.out.print("Descrição da formação: ");
                String formacaoDescricao = scanner.nextLine();

                Formacao formacao = new Formacao(formacaoId, formacaoNome, formacaoDescricao);

                novoUsuario = new Instrutor(login, senha, false, id, nome, email, telefone, formacao, academia);
                academia.adicionarInstrutor((Instrutor) novoUsuario);
            }
            case 3 -> {
                novoUsuario = new Administrador(login, senha, true, id, nome, email, telefone, academia);
                academia.setAdministrador(novoUsuario);
            }
            default -> System.out.println("Tipo inválido.");
        }

        if (novoUsuario != null) {
            Storage.addPessoa(novoUsuario);
            System.out.println("Usuário cadastrado com sucesso!");
        }
    }

    private static void realizarLogin() {
        System.out.print("\nLogin: ");
        String login = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        try {
        	Pessoa usuario = (Pessoa) Storage.findUsuario(login, senha);
            academia.login(login, senha);

            System.out.println("Login bem-sucedido!");
            System.out.println("Bem-vindo, " + usuario.getNome());

            if (usuario instanceof Administrador) {
                System.out.println("Tipo de usuário: Administrador");
            } else if (usuario instanceof Instrutor) {
                System.out.println("Tipo de usuário: Instrutor");
            } else if (usuario instanceof Aluno) {
                System.out.println("Tipo de usuário: Aluno");
            }

            boolean logado = true;
            while (logado) {
                System.out.println("\n=== MENU PRINCIPAL ===");
                System.out.println("1. Consultar dados da academia");
                System.out.println("2. Alterar academia");
                System.out.println("3. Remover academia");
                System.out.println("4. Listar horarios");
                System.out.println("5. Gerenciar horários"); 
                System.out.println("6. Gerenciar aparelhos"); 
                System.out.println("7. Listar todos aparelhos");
                System.out.println("8. Descricao e funcao do aparelho");
                System.out.println("9. Gerenciar treinos de alunos"); 
                System.out.println("10. Imprimir treino");
                System.out.println("11. Sair");
                
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> consultarAcademia(usuario);
                    case 2 -> alterarAcademia(usuario);
                    case 3 -> removerAcademia(usuario);
                    case 4 -> listarHorarios(); 
                    case 5 -> gerenciarHorarios(usuario); //add,alt,rem horarios
                    case 6 -> gerenciarAparelhos(usuario);
                    case 7 -> listarAparelhos();
                    case 8 -> verDescricaoAparelho();
                    case 9 -> gerenciarTreinos(usuario);
                    case 10 -> escolherTreinoDoDia(usuario);
                    case 11 -> {
                        logado = false;
                        System.out.println("Logout realizado.");
                    }
                    default -> System.out.println("Opção inválida.");
                }
            }

        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static Date parseDate(String dataStr) {
        try {
            return new SimpleDateFormat("dd/mm/yyyy").parse(dataStr);
        } catch (ParseException e) {
            System.out.println("Data inválida. Usando data atual.");
            return new Date();
        }
    }
    
    private static void consultarAcademia(Pessoa usuario) {
        System.out.println("   DADOS DA ACADEMIA    ");
        System.out.println(academia.exibirDadosPublicos());
    }

    private static void alterarAcademia(Usuario usuario) {
        if (usuario.isAdministrador()) {
            System.out.println("\n   ALTERAR DADOS DA ACADEMIA    ");

            System.out.print("Novo nome: ");
            String nome = scanner.nextLine();
            academia.setNome(nome);

            System.out.print("Novo telefone: ");
            String telefone = scanner.nextLine();
            academia.setTelefone(telefone);

            System.out.print("Novo website: ");
            String website = scanner.nextLine();
            academia.setWebsite(website);

            System.out.println("Dados da academia atualizados com sucesso.");
        } else {
            System.out.println("Acesso negado. Apenas administradores podem alterar os dados da academia.");
        }
    }

    private static void removerAcademia(Pessoa usuario) {
        if (usuario instanceof Administrador admin) {
        	Storage.removePessoa(usuario); 
            System.out.println("Academia removida. Encerrando sistema.");
            System.exit(0);
        } else {
            System.out.println("Acesso negado. Apenas administradores podem remover a academia.");
        }
    }
    
    private static void gerenciarHorarios(Pessoa usuario) {
        if (!(usuario instanceof Administrador)) {
            System.out.println("Acesso negado. Apenas administradores podem gerenciar horários.");
            return;
        }

        boolean gerenciando = true;
        while (gerenciando) {
            System.out.println("\n    GERENCIAR HORÁRIOS    ");
            System.out.println("1. Adicionar horário");
            System.out.println("2. Alterar horário");
            System.out.println("3. Remover horário");
            System.out.println("4. Voltar");

            System.out.print("Escolha uma opção: ");
            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> adicionarHorario(usuario);
                case 2 -> alterarHorario(usuario);
                case 3 -> removerHorario(usuario);
                case 4 -> gerenciando = false;
                default -> System.out.println("Opção inválida.");
            }
        }
    }
    
    private static void adicionarHorario(Pessoa usuario) {
        System.out.print("Dia da semana: ");
        String dia = scanner.nextLine();

        System.out.print("Horário de abertura (HH:mm): ");
        String aberturaStr = scanner.nextLine();

        System.out.print("Horário de fechamento (HH:mm): ");
        String fechamentoStr = scanner.nextLine();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date abertura = sdf.parse(aberturaStr);
            Date fechamento = sdf.parse(fechamentoStr);

            Horario horario = new Horario(dia, abertura, fechamento, academia);
            academia.adicionarHorario(horario);

            System.out.println("Horário adicionado com sucesso.");
        } catch (ParseException e) {
            System.out.println("Formato de horário inválido. Use HH:mm (ex: 08:30).");
        }
    }
    	
    private static void alterarHorario(Pessoa usuario) {
        List<Horario> horarios = academia.getHorarios();
        if (horarios.isEmpty()) {
            System.out.println("Nenhum horário cadastrado.");
            return;
        }

        System.out.println("\n=== HORÁRIOS CADASTRADOS ===");
        for (int i = 0; i < horarios.size(); i++) {
            System.out.println(i + " - " + horarios.get(i));
        }

        System.out.print("Escolha o índice do horário a alterar: ");
        int index;
        try {
            index = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Índice inválido.");
            return;
        }

        if (index < 0 || index >= horarios.size()) {
            System.out.println("Índice fora do intervalo.");
            return;
        }

        System.out.print("Novo dia da semana: ");
        String dia = scanner.nextLine();

        System.out.print("Novo horário de abertura (HH:mm): ");
        String inicio = scanner.nextLine();

        System.out.print("Novo horário de fechamento (HH:mm): ");
        String fim = scanner.nextLine();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date abertura = sdf.parse(inicio);
            Date fechamento = sdf.parse(fim);

            Horario novoHorario = new Horario(dia, abertura, fechamento, academia);
            academia.alterarHorario(index, novoHorario);
            System.out.println("Horário alterado com sucesso.");
        } catch (ParseException e) {
            System.out.println("Formato de horário inválido. Use HH:mm (ex: 08:30).");
        }
    }
    
    private static void removerHorario(Pessoa usuario) {
        List<Horario> horarios = academia.getHorarios();
        if (horarios.isEmpty()) {
            System.out.println("Nenhum horário cadastrado.");
            return;
        }

        for (int i = 0; i < horarios.size(); i++) {
            System.out.println(i + " - " + horarios.get(i));
        }

        System.out.print("Escolha o índice do horário a remover: ");
        int index = Integer.parseInt(scanner.nextLine());

        if (index < 0 || index >= horarios.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Horario horario = horarios.get(index);
        academia.removerHorario(horario);
    }
    
    private static void listarHorarios() {
        List<Horario> horarios = academia.getHorarios();
        if (horarios.isEmpty()) {
            System.out.println("Nenhum horário cadastrado.");
        } else {
            System.out.println("\n=== HORÁRIOS DA ACADEMIA ===");
            for (int i = 0; i < horarios.size(); i++) {
                System.out.println(i + " - " + horarios.get(i));
            }
        }
    }
    
    private static void gerenciarAparelhos(Pessoa usuario) {
        if (usuario instanceof Aluno) {
            System.out.println("Acesso negado. Alunos não podem gerenciar aparelhos.");
            return;
        }

        boolean gerenciando = true;
        while (gerenciando) {
            System.out.println("\n=== GERENCIAR APARELHOS ===");
            System.out.println("1. Cadastrar aparelho");
            System.out.println("2. Alterar aparelho");
            System.out.println("3. Remover aparelho");
            System.out.println("4. Voltar");

            System.out.print("Escolha uma opção: ");
            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> cadastrarAparelho(usuario);
                case 2 -> alterarAparelho(usuario);
                case 3 -> removerAparelho(usuario);
                case 4 -> gerenciando = false;
                default -> System.out.println("Opção inválida.");
            }
        }
    }
    
    
    private static void cadastrarAparelho(Pessoa usuario) {
        if (usuario instanceof Aluno) {
            System.out.println("Acesso negado. Alunos não podem cadastrar aparelhos.");
            return;
        }

        System.out.print("Nome do aparelho: ");
        String nome = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Função: ");
        String funcao = scanner.nextLine();

        int id = academia.getAparelhos().size(); 

        Aparelho aparelho = new Aparelho(id, nome, descricao, funcao, academia);
        academia.adicionarAparelho(aparelho);

        System.out.println("Aparelho cadastrado com sucesso.");
    }
    
    private static void alterarAparelho(Pessoa usuario) {
        if (usuario instanceof Aluno) {
            System.out.println("Acesso negado. Alunos não podem alterar aparelhos.");
            return;
        }

        List<Aparelho> aparelhos = academia.getAparelhos();
        if (aparelhos.isEmpty()) {
            System.out.println("Nenhum aparelho cadastrado.");
            return;
        }

        for (int i = 0; i < aparelhos.size(); i++) {
            System.out.println(i + " - " + aparelhos.get(i));
        }

        System.out.print("Escolha o índice do aparelho a alterar: ");
        int index = Integer.parseInt(scanner.nextLine());

        if (index < 0 || index >= aparelhos.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        System.out.print("Novo nome: ");
        String novoNome = scanner.nextLine();

        System.out.print("Nova descrição: ");
        String novaDescricao = scanner.nextLine();

        System.out.print("Nova função: ");
        String novaFuncao = scanner.nextLine();

        int id = aparelhos.get(index).getId(); // mantém o mesmo ID

        Aparelho novo = new Aparelho(id, novoNome, novaDescricao, novaFuncao, academia);
        academia.alterarAparelho(index, novo);

        System.out.println("Aparelho alterado com sucesso.");
    }
    
    private static void removerAparelho(Pessoa usuario) {
        if (usuario instanceof Aluno) {
            System.out.println("Acesso negado. Alunos não podem remover aparelhos.");
            return;
        }

        List<Aparelho> aparelhos = academia.getAparelhos();
        if (aparelhos.isEmpty()) {
            System.out.println("Nenhum aparelho cadastrado.");
            return;
        }

        System.out.println("\n=== APARELHOS CADASTRADOS ===");
        for (Aparelho a : aparelhos) {
            System.out.println("ID: " + a.getId() + " | Nome: " + a.getNome());
        }

        System.out.print("Digite o ID do aparelho a remover: ");
        int idBusca = Integer.parseInt(scanner.nextLine());

        Aparelho aparelhoRemovido = null;
        for (Aparelho a : aparelhos) {
            if (a.getId() == idBusca) {
                aparelhoRemovido = a;
                break;
            }
        }

        if (aparelhoRemovido == null) {
            System.out.println("Nenhum aparelho encontrado com esse ID.");
            return;
        }

        academia.removerAparelho(aparelhoRemovido);
        System.out.println("Aparelho removido com sucesso.");
    }
    
    private static void listarAparelhos() {
        List<Aparelho> aparelhos = academia.getAparelhos();
        if (aparelhos.isEmpty()) {
            System.out.println("Nenhum aparelho cadastrado.");
        } else {
            System.out.println("\n=== LISTA DE APARELHOS ===");
            for (Aparelho a : aparelhos) {
                System.out.println("ID: " + a.getId() + " | Nome: " + a.getNome());
            }
        }
    }
    
    private static void verDescricaoAparelho() {
        List<Aparelho> aparelhos = academia.getAparelhos();
        if (aparelhos.isEmpty()) {
            System.out.println("Nenhum aparelho cadastrado.");
            return;
        }

        System.out.println("\nBuscar descrição por:");
        System.out.println("1. Nome");
        System.out.println("2. ID");
        System.out.print("Escolha uma opção: ");
        int escolha = Integer.parseInt(scanner.nextLine());

        switch (escolha) {
            case 1 -> {
                System.out.print("Digite o nome do aparelho: ");
                String nomeBusca = scanner.nextLine().toLowerCase();
                boolean encontrado = false;
                for (Aparelho a : aparelhos) {
                    if (a.getNome().toLowerCase().contains(nomeBusca)) {
                    	System.out.println("\n-----------------------------");
                    	System.out.println("Nome: " + a.getNome());
                    	System.out.println("Descrição: " + a.getDescricao());
                    	System.out.println("Função: " + a.getFuncao());
                    	System.out.println("-----------------------------");
                        encontrado = true;
                    }
                }
                if (!encontrado) System.out.println("Nenhum aparelho encontrado com esse nome.");
            }
            case 2 -> {
                System.out.print("Digite o ID do aparelho: ");
                int idBusca = Integer.parseInt(scanner.nextLine());
                boolean encontrado = false;
                for (Aparelho a : aparelhos) {
                    if (a.getId() == idBusca) {
                    	System.out.println("\n-----------------------------");
                    	System.out.println("Nome: " + a.getNome());
                    	System.out.println("Descrição: " + a.getDescricao());
                    	System.out.println("Função: " + a.getFuncao());
                    	System.out.println("-----------------------------");
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) System.out.println("Nenhum aparelho encontrado com esse ID.");
            }
            default -> System.out.println("Opção inválida.");
        }
    }
    
    private static void gerenciarTreinos(Pessoa usuario) {
        if (!(usuario instanceof Instrutor instrutor)) {
            System.out.println("Apenas instrutores podem gerenciar treinos.");
            return;
        }

        boolean gerenciando = true;
        while (gerenciando) {
            System.out.println("\n=== GERENCIAR TREINOS ===");
            System.out.println("1. Adicionar treino para aluno");
            System.out.println("2. Alterar treino de aluno");
            System.out.println("3. Remover treino de aluno");
            System.out.println("4. Voltar");

            System.out.print("Escolha uma opção: ");
            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> adicionarTreinoParaAluno(instrutor);
                case 2 -> alterarTreinoDeAluno(instrutor);
                case 3 -> removerTreinoDeAluno(instrutor);
                case 4 -> gerenciando = false;
                default -> System.out.println("Opção inválida.");
            }
        }
    }
    
    private static void adicionarTreinoParaAluno(Instrutor instrutor) {
        List<Aluno> alunos = academia.getAlunos();
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        System.out.println("\n=== ALUNOS DISPONÍVEIS ===");
        for (int i = 0; i < alunos.size(); i++) {
            System.out.println(i + " - " + alunos.get(i).getNome());
        }

        System.out.print("Escolha o índice do aluno: ");
        int indexAluno = Integer.parseInt(scanner.nextLine());
        if (indexAluno < 0 || indexAluno >= alunos.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Aluno aluno = alunos.get(indexAluno);

        System.out.print("Nome do treino: ");
        String nomeTreino = scanner.nextLine();

        System.out.print("Dia da semana (1=Segunda, ..., 7=Domingo): ");
        int diaSemana = Integer.parseInt(scanner.nextLine());

        int idTreino = aluno.getTreinos().size();
        Treino treino = new Treino(idTreino, diaSemana, nomeTreino, aluno);

        boolean adicionando = true;
        int ordem = 1;
        while (adicionando) {
            List<Aparelho> aparelhos = academia.getAparelhos();
            if (aparelhos.isEmpty()) {
                System.out.println("Nenhum aparelho disponível.");
                break;
            }

            System.out.println("\n=== APARELHOS DISPONÍVEIS ===");
            for (int i = 0; i < aparelhos.size(); i++) {
                System.out.println(i + " - " + aparelhos.get(i).getNome());
            }

            System.out.print("Escolha o índice do aparelho: ");
            int indexAparelho = Integer.parseInt(scanner.nextLine());
            if (indexAparelho < 0 || indexAparelho >= aparelhos.size()) {
                System.out.println("Índice inválido.");
                continue;
            }

            Aparelho aparelho = aparelhos.get(indexAparelho);

            System.out.print("Carga (kg): ");
            float carga = Float.parseFloat(scanner.nextLine());

            System.out.print("Repetições: ");
            int repeticoes = Integer.parseInt(scanner.nextLine());

            Exercicio exercicio = new Exercicio(ordem++, carga, repeticoes, aparelho);
            treino.adicionarExercicio(exercicio);

            System.out.print("Adicionar outro exercício? (s/n): ");
            String resposta = scanner.nextLine().toLowerCase();
            if (!resposta.equals("s")) {
                adicionando = false;
            }
        }

        aluno.adicionarTreino(treino);
        instrutor.adicionarTreino(treino);
        System.out.println("Treino adicionado com sucesso para " + aluno.getNome());
    }
    
    private static void alterarTreinoDeAluno(Instrutor instrutor) {
        List<Aluno> alunos = academia.getAlunos();
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        System.out.println("\n=== ALUNOS DISPONÍVEIS ===");
        for (int i = 0; i < alunos.size(); i++) {
            System.out.println(i + " - " + alunos.get(i).getNome());
        }

        System.out.print("Escolha o índice do aluno: ");
        int indexAluno = Integer.parseInt(scanner.nextLine());
        if (indexAluno < 0 || indexAluno >= alunos.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Aluno aluno = alunos.get(indexAluno);
        List<Treino> treinos = aluno.getTreinos();
        if (treinos.isEmpty()) {
            System.out.println("Esse aluno não possui treinos.");
            return;
        }

        System.out.println("\n=== TREINOS DO ALUNO ===");
        for (int i = 0; i < treinos.size(); i++) {
            System.out.println(i + " - " + treinos.get(i).getNome());
        }

        System.out.print("Escolha o índice do treino a alterar: ");
        int indexTreino = Integer.parseInt(scanner.nextLine());
        if (indexTreino < 0 || indexTreino >= treinos.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Treino treino = treinos.get(indexTreino);

        System.out.print("Novo nome do treino: ");
        String novoNome = scanner.nextLine();
        treino.setNome(novoNome);

        System.out.print("Novo dia da semana: ");
        int novoDia = Integer.parseInt(scanner.nextLine());
        treino.setDiaSemana(novoDia);

        System.out.println("Treino alterado com sucesso.");
    }
    
    private static void removerTreinoDeAluno(Instrutor instrutor) {
        List<Aluno> alunos = academia.getAlunos();
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        System.out.println("\n=== ALUNOS DISPONÍVEIS ===");
        for (int i = 0; i < alunos.size(); i++) {
            System.out.println(i + " - " + alunos.get(i).getNome());
        }

        System.out.print("Escolha o índice do aluno: ");
        int indexAluno = Integer.parseInt(scanner.nextLine());
        if (indexAluno < 0 || indexAluno >= alunos.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Aluno aluno = alunos.get(indexAluno);
        List<Treino> treinos = aluno.getTreinos();
        if (treinos.isEmpty()) {
            System.out.println("Esse aluno não possui treinos.");
            return;
        }

        System.out.println("\n=== TREINOS DO ALUNO ===");
        for (int i = 0; i < treinos.size(); i++) {
            System.out.println(i + " - " + treinos.get(i).getNome());
        }

        System.out.print("Escolha o índice do treino a remover: ");
        int indexTreino = Integer.parseInt(scanner.nextLine());
        if (indexTreino < 0 || indexTreino >= treinos.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Treino treinoRemovido = treinos.get(indexTreino);
        aluno.removerTreino(treinoRemovido);
        instrutor.removerTreino(treinoRemovido);
        System.out.println("Treino removido com sucesso.");
    }
    
    
    private static void escolherTreinoDoDia(Pessoa usuario) {
        if (!(usuario instanceof Aluno aluno)) {
            System.out.println("Apenas alunos podem escolher treinos.");
            return;
        }

        System.out.print("Informe o dia da semana (1=Segunda, ..., 7=Domingo): ");
        int dia = Integer.parseInt(scanner.nextLine());

        List<Treino> treinosDoDia = new ArrayList<>();
        for (Treino t : aluno.getTreinos()) {
            if (t.getDiaSemana() == dia) {
                treinosDoDia.add(t); // referência direta
            }
        }

        if (treinosDoDia.isEmpty()) {
            System.out.println("Você não possui treinos cadastrados para esse dia.");
            return;
        }

        System.out.println("\n=== TREINOS DISPONÍVEIS PARA O DIA " + dia + " ===");
        for (int i = 0; i < treinosDoDia.size(); i++) {
            Treino t = treinosDoDia.get(i);
            System.out.println(i + " - " + t.getNome() + " (ID: " + t.getId() + ")");
        }

        System.out.print("Escolha o índice do treino que deseja visualizar: ");
        int escolha = Integer.parseInt(scanner.nextLine());
        if (escolha < 0 || escolha >= treinosDoDia.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Treino treinoSelecionado = treinosDoDia.get(escolha); // referência direta

        System.out.println("\n=== TREINO SELECIONADO ===");
        System.out.println("Nome: " + treinoSelecionado.getNome());
        System.out.println("ID: " + treinoSelecionado.getId());
        System.out.println("Dia da semana: " + treinoSelecionado.getDiaSemana());
        System.out.println("Exercícios:");
        for (Exercicio e : treinoSelecionado.getExercicios()) {
            System.out.println("→ " + e.getOrdem() + ". " + e.getAparelho().getNome() +
                               " | Carga: " + e.getCarga() + "kg | Repetições: " + e.getNumeroRepeticoes());
        }
    }
    
    private static void inicializarAcademiaComValoresPadrao() {
        Endereco enderecoPadrao = new Endereco(
            999, 
            "Rua da Programação", 
            "101", 
            "Bloco A", 
            "Bairro Teste", 
            "99999-999", 
            "Cidade Padrão", 
            "TS" // TS para Teste
        );

        academia = new Academia(
            "admin", 
            "1234", 
            1, // ID da Academia
            "Academia Giga Fitness", 
            "(51) 3333-4444", 
            "www.gigafitness.com.br", 
            enderecoPadrao, 
            null
        );

        Storage.addAcademia(academia);
    }
    
    private static void preencherSistemaComDadosDeTeste() {
        System.out.println("... Adicionando 10 registros de Endereços e Pessoas de Teste.");
        
        Academia academia = Storage.findAcademia(1);
        Instrutor instrutor1 = new Instrutor("instrutor10", "senha123", false, 10, "Carlos Santos", "carlos.s@gym.com", "(51) 9876-1234", null, academia);
        Instrutor instrutor2 = new Instrutor("instrutor11", "senha123", false, 11, "Ana Maria Silva", "ana.m@gym.com", "(51) 9876-5678", null, academia);

        Aluno aluno1 = new Aluno("aluno12", "senha123", false, 12, academia, "João Pereira", "joao.p@teste.com", "(51) 9912-3456", new Date(), 1.75f);
        Aluno aluno2 = new Aluno("aluno13", "senha123", false, 13, academia, "Maria Oliveira", "maria.o@teste.com", "(51) 9912-4567", new Date(), 1.63f);
        Aluno aluno3 = new Aluno("aluno14", "senha123", false, 14, academia, "Pedro Souza", "pedro.s@teste.com", "(51) 9912-5678", new Date(), 1.88f);
        Aluno aluno4 = new Aluno("aluno15", "senha123", false, 15, academia, "Julia Lima", "julia.l@teste.com", "(51) 9912-6789", new Date(), 1.70f);
        Aluno aluno5 = new Aluno("aluno16", "senha123", false, 16, academia, "Lucas Rocha", "lucas.r@teste.com", "(51) 9912-7890", new Date(), 1.82f);
        Aluno aluno6 = new Aluno("aluno17", "senha123", false, 17, academia, "Mariana Alves", "mariana.a@teste.com", "(51) 9912-8901", new Date(), 1.68f);
        Aluno aluno7 = new Aluno("aluno18", "senha123", false, 18, academia, "Felipe Costa", "felipe.c@teste.com", "(51) 9912-9012", new Date(), 1.77f);
        Aluno aluno8 = new Aluno("aluno19", "senha123", false, 19, academia, "Larissa Melo", "larissa.m@teste.com", "(51) 9912-0123", new Date(), 1.60f);
        Aluno aluno9 = new Aluno("aluno20", "senha123", false, 20, academia, "Rafael Gomes", "rafael.g@teste.com", "(51) 9912-1234", new Date(), 1.85f);

        List<Aluno> alunos = List.of(aluno1, aluno2, aluno3, aluno4, aluno5, aluno6,aluno7,aluno8,aluno9);
        academia.adicionarAlunos(alunos);
        
        List<Instrutor> instrutores = List.of(instrutor1, instrutor2);
        academia.adicionarInstrutores(instrutores);
        
        Storage.addPessoa(instrutor1);
        Storage.addPessoa(instrutor2);
        Storage.addPessoa(aluno1);
        Storage.addPessoa(aluno2);
        Storage.addPessoa(aluno3);
        Storage.addPessoa(aluno4);
        Storage.addPessoa(aluno5);
        Storage.addPessoa(aluno6);
        Storage.addPessoa(aluno7);
        Storage.addPessoa(aluno8);
        Storage.addPessoa(aluno9);
	}
}