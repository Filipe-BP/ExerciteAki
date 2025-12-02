package br.ucs.poo.exerciteaki;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.ucs.poo.exerciteaki.utils.Utils;

public class Principal {

	private static Scanner scanner = new Scanner(System.in);
	private static int contadorId = 1;
	private static Academia academia;

	public static void main(String[] args) throws Exception {
		inicializarAcademia();

		boolean ativo = true;
		while (ativo) {
			System.out.println("\n=== MENU PRINCIPAL ===");
			System.out.println("1. Cadastrar usuário");
			System.out.println("2. Login");
			System.out.println("3. Sair");
			System.out.print("Escolha uma opção: ");

			try {
				int opcao = scanner.nextInt();
				scanner.nextLine();

				switch (opcao) {
				case 1:
					cadastrarUsuario();
					break;
				case 2:
					realizarLogin();
					break;
				case 3: {
					ativo = false;
					System.out.println("Encerrando...");
					break;
				}
				default:
					System.out.println("Opção inválida. Escolha 1, 2 ou 3.");
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("Opção inválida. Por favor, digite apenas o numero).");
				scanner.nextLine();
			}

		}
	}

	private static void inicializarAcademia() {
		System.out.println("-------- Bem-vindo(a) ao ExerciteAki --------");

		boolean criaDadosDeTeste = !Storage.arquivoExiste();
		if (criaDadosDeTeste) {
			preencherSistemaComDadosDeTeste();
		} else {
			academia = Storage.carregarDados();
		}
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
		case 1: {
				System.out.print("Data de nascimento (dd/MM/yyyy): ");
				Date nascimento = parseDate(scanner.nextLine());
	
				System.out.print("Altura (em metros): ");
				float altura = scanner.nextFloat();
				scanner.nextLine();
	
				novoUsuario = new Aluno(login, senha, false, id, academia, nome, email, telefone, nascimento, altura);
				academia.adicionarAluno((Aluno) novoUsuario);
				break;
			}
		case 2: {
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
				break;
			}
		case 3: {
				novoUsuario = new Administrador(login, senha, true, id, nome, email, telefone, academia);
				academia.setAdministrador((Administrador) novoUsuario);
				break;
			}
		default: System.out.println("Tipo inválido.");
		}

		if (novoUsuario != null) {
			System.out.println("Usuário cadastrado com sucesso!");
		}
	}

	private static void realizarLogin() {
		System.out.print("\nLogin: ");
		String login = scanner.nextLine();

		System.out.print("Senha: ");
		String senha = scanner.nextLine();

		try {
			Object usuario = academia.getPessoa(login, senha);
			Pessoa pessoa = (Pessoa) usuario;
			academia.login(login, senha); // Assume que academia.login() seta usuarioLogado

			System.out.println("Login bem-sucedido!");
			System.out.println("Bem-vindo, " + pessoa.getNome());

			if (usuario instanceof Aluno) {
				/*
				 * if (aluno.getFrequenciaPendente() != null) { System.out.
				 * println(" Aviso: Você já tem uma entrada pendente. Faça logout para registrar a saída anterior."
				 * ); } else { aluno.registrarEntrada(); String horaEntrada =
				 * LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
				 * System.out.println(" Entrada registrada! Hora: " + horaEntrada); }FIXME
				 */
			}

			if (usuario instanceof Administrador) {
				menuAdministrador((Administrador) usuario);
			} else if (usuario instanceof Instrutor) {
				menuInstrutor((Instrutor) usuario);
			} else if (usuario instanceof Aluno) {
				menuAluno((Aluno) usuario);
			}

		} catch (RuntimeException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	private static void menuAdministrador(Administrador admin) {
		System.out.println("Tipo de usuário: Administrador");
		boolean logado = true;
		while (logado) {
			System.out.println("\n=== MENU ADMINSTRADOR ===");
			System.out.println("1. Gerenciar Horários");
			System.out.println("2. Gerenciar Aparelhos");
			System.out.println("3. Dados da academia");
			System.out.println("4. Alterar Dados da Academia");
			System.out.println("5. EXCLUIR ACADEMIA ");
			System.out.println("6. Horários de Funcionamento");
			System.out.println("7. Consultar Aparelhos");
			System.out.println("8. Consultar Alunos");
			System.out.println("9. Sair (Logout)");

			System.out.print("Escolha uma opção: ");
			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1: gerenciarHorarios(admin);
				break;
			case 2: gerenciarAparelhos(admin);
				break;
			case 3: consultarAcademia(admin);
				break;
			case 4: alterarAcademia(admin);
				break;
			case 5: removerAcademia(admin);
				break;
			case 6: listarHorarios();
				break;
			case 7: consultarAparelhoPersonalizado();
				break;
			case 8: consultarAlunoPersonalizado();
				break;
			case 9: {
					logado = false;
					academia.logout();
					System.out.println("Logout realizado.");
				}
				break;
			default: System.out.println("Opção inválida.");
			}
		}
	}

	private static void menuInstrutor(Instrutor instrutor) {
		System.out.println("Tipo de usuário: Instrutor");
		boolean logado = true;
		while (logado) {
			System.out.println("\n=== MENU INSTRUTORES ===");
			System.out.println("1. Gerenciar Treinos de Alunos (Add/Alt/Rem)");
			System.out.println("2. Listar Alunos e Consultar");
			System.out.println("3. Consultar dados da academia");
			System.out.println("4. Listar Horários");
			System.out.println("5. Consultar Aparelho");
			System.out.println("6. Gerenciar evolução de alunos");
			System.out.println("7. Sair (Logout)");

			System.out.print("Escolha uma opção: ");
			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1: gerenciarTreinos(instrutor);
				break;
			case 2: consultarAlunoPersonalizado();
				break;
			case 3: consultarAcademia(instrutor);
				break;
			case 4: listarHorarios();
				break;
			case 5: consultarAparelhoPersonalizado();
				break;
			case 6: gerenciarEvolucaoAluno(instrutor);
				break;
			case 7: {
					logado = false;
					academia.logout();
					System.out.println("Logout realizado.");
				}
				break;
			default: System.out.println("Opção inválida.");
			}
		}
	}

	private static void menuAluno(Aluno aluno) {
		System.out.println("Tipo de usuário: Aluno");
		boolean logado = true;
		while (logado) {
			System.out.println("\n   MENU ALUNOS   ");
			System.out.println("1. Imprimir Treino do Dia");
			System.out.println("2. Consultar dados da academia");
			System.out.println("3. Listar Horários");
			System.out.println("4. Consultar Aparelhos");
			System.out.println("5. Frequencia");
			System.out.println("6. Evolução Pessoal");
			System.out.println("7. Sair (Logout)");

			System.out.print("Escolha uma opção: ");
			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1: consultarTreinoDoDia(aluno);
				break;
			case 2: consultarAcademia(aluno);
				break;
			case 3: listarHorarios();
				break;
			case 4: consultarAparelhoPersonalizado();
				break;
			case 5: menuFrequencia(aluno);
				break;
			case 6: menuEvolucao(aluno);
				break;
			case 7: {
					academia.registrarSaida();
					logado = false;
					academia.logout();
					System.out.println("Logout realizado.");
				}
				break;
			default: System.out.println("Opção inválida.");
			}
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

	private static void menuFrequencia(Aluno aluno) {
		boolean voltar = false;
		while (!voltar) {
			System.out.println("\n=============================================");
			System.out.println("1. Visualizar Histórico de Frequência (Tela)");
			System.out.println("2. Gerar Relatório de Frequência (Arquivo TXT)");
			System.out.println("3. Voltar ao Menu Principal do Aluno");
			System.out.print("Escolha uma opção: ");

			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
			case 1: visualizarFrequencia(aluno);
				break;
			case 2: gerarRelatorioFrequencia(aluno);
				break;
			case 3: voltar = true;
				break;
			default: System.out.println("Opção inválida. Tente novamente.");
			}
		}
	}

	private static void visualizarFrequencia(Aluno aluno) {
		System.out.println("\n=== HISTÓRICO DE FREQUÊNCIA DE " + aluno.getNome().toUpperCase() + " ===");
		List<Frequencia> historico = aluno.getFrequencias();

		if (historico.isEmpty()) {
			System.out.println("Nenhum registro de frequência encontrado.");
			return;
		}

		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.printf("%-12s | %-10s | %-10s%n", "DATA", "ENTRADA", "SAÍDA");
		System.out.println("---------------------------------");

		for (Frequencia f : historico) {
			String data = f.getData().format(dateFormatter);
			String entrada = f.getDataHoraEntrada().format(timeFormatter);
			String saida = f.getDataHoraSaida() != null ? f.getDataHoraSaida().format(timeFormatter) : "PENDENTE";

			System.out.printf("%-12s | %-10s | %-10s%n", data, entrada, saida);
		}
	}

	private static void menuEvolucao(Aluno aluno) {
		boolean ativo = true;
		while (ativo) {
			System.out.println("\n==========================================");
			System.out.println("1. Visualizar Histórico de Evolução (Tela)");
			System.out.println("2. Gerar Relatório de Evolução (Arquivo TXT)");
			System.out.println("3. Voltar");

			System.out.print("Escolha uma opção: ");
			int opcao;
			try {
				opcao = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida.");
				continue;
			}

			switch (opcao) {
			case 1: visualizarHistoricoEvolucao(aluno);
				break;
			case 2: gerarRelatorioEvolucao(aluno);
				break;
			case 3: ativo = false;
				break;
			default: System.out.println("Opção inválida.");
			}
		}
	}

	private static void visualizarHistoricoEvolucao(Aluno aluno) {
		List<Evolucao> evolucoes = aluno.getEvolucoes();

		if (evolucoes.isEmpty()) {
			System.out.println("Nenhuma evolução registrada.");
			return;
		}

		System.out.println("\n=== HISTÓRICO DE EVOLUÇÃO ===");
		for (Evolucao e : evolucoes) {
			System.out.println("Data: " + e.getData() + " | Peso: " + e.getPeso() + "kg" + " | Massa muscular: "
					+ e.getMassaMuscular() + "%");
		}
	}

	private static void gerarRelatorioEvolucao(Aluno aluno) {
		List<Evolucao> evolucoes = aluno.getEvolucoes();

		if (evolucoes.isEmpty()) {
			System.out.println("Nenhuma evolução registrada.");
			return;
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("evolucao_" + aluno.getNome() + ".txt"))) {

			writer.write("Relatório de Evolução - Aluno: " + aluno.getNome());
			writer.newLine();
			writer.write("==============================");
			writer.newLine();

			for (Evolucao e : evolucoes) {
				writer.write("Data: " + e.getData() + " | Peso: " + e.getPeso() + "kg" + " | Massa muscular: "
						+ e.getMassaMuscular() + "%");
				writer.newLine();
			}

			System.out.println("Relatório de evolução exportado com sucesso para arquivo TXT!");
		} catch (IOException ex) {
			System.out.println("Erro ao gravar relatório: " + ex.getMessage());
		}
	}

	private static String getRelatorioFrequenciaText(Aluno aluno) {
		StringBuilder sb = new StringBuilder();
		sb.append("=== RELATÓRIO DE FREQUÊNCIA DE ").append(aluno.getNome().toUpperCase()).append(" ===\n");
		sb.append("Gerado em: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
				.append("\n\n");

		List<Frequencia> historico = aluno.getFrequencias();

		if (historico.isEmpty()) {
			sb.append("Nenhum registro de frequência encontrado.");
			return sb.toString();
		}

		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		int totalComparecimentos = 0;
		Duration duracaoTotal = Duration.ZERO;

		sb.append("--- DETALHES DOS REGISTROS ---\n");
		sb.append(String.format("%-12s | %-10s | %-10s | %-10s%n", "DATA", "ENTRADA", "SAÍDA", "DURAÇÃO"));
		sb.append("------------------------------------------\n");

		for (Frequencia f : historico) {
			String data = f.getData().format(dateFormatter);
			String entrada = f.getDataHoraEntrada().format(timeFormatter);
			String duracaoStr;

			if (f.getDataHoraSaida() != null) {
				Duration duracao = Duration.between(f.getDataHoraEntrada(), f.getDataHoraSaida());
				duracaoTotal = duracaoTotal.plus(duracao);
				totalComparecimentos++;

				long horas = duracao.toHours();
				long minutos = duracao.toMinutes() % 60;
				duracaoStr = String.format("%02dh %02dm", horas, minutos);

				String saida = f.getDataHoraSaida().format(timeFormatter);
				sb.append(String.format("%-12s | %-10s | %-10s | %-10s%n", data, entrada, saida, duracaoStr));

			} else {
				String saida = "PENDENTE";
				duracaoStr = "N/A";
				sb.append(String.format("%-12s | %-10s | %-10s | %-10s%n", data, entrada, saida, duracaoStr));
			}
		}

		sb.append("\n=== RESUMO GERAL ===\n");

		long totalHoras = duracaoTotal.toHours();
		long totalMinutos = duracaoTotal.toMinutes() % 60;

		sb.append("Total de Comparecimentos (Com Saída): ").append(totalComparecimentos).append(" vezes\n");
		sb.append("Total de Permanência na Academia: ")
				.append(String.format("%d horas e %d minutos", totalHoras, totalMinutos)).append("\n");

		return sb.toString();
	}

	private static void gerarRelatorioFrequencia(Aluno aluno) {
		String relatorio = getRelatorioFrequenciaText(aluno);

		String nomeAlunoLimpo = aluno.getNome().replaceAll("\\s+", "_");
		String nomeArquivo = "Relatorio_Frequencia_" + nomeAlunoLimpo + "_" + LocalDate.now() + ".txt";

		try (PrintWriter writer = new PrintWriter(new FileWriter(nomeArquivo))) {
			writer.print(relatorio);
			System.out.println("\n Relatório gerado com sucesso!");
			System.out.println("Arquivo salvo como: " + nomeArquivo);
			System.out.println("Você pode encontrá-lo na pasta raiz do projeto.");
		} catch (IOException e) {
			System.err.println("\n Erro ao escrever o arquivo de relatório: " + e.getMessage());
		}
	}

	private static void consultarAcademia(Pessoa usuario) {
		System.out.println("\n   DADOS DA ACADEMIA    ");
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
		if (usuario instanceof Administrador) {
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
			case 1: adicionarHorario(usuario);
				break;
			case 2: alterarHorario(usuario);
				break;
			case 3: removerHorario(usuario);
				break;
			case 4: gerenciando = false;
				break;
			default: System.out.println("Opção inválida.");
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

			int opcao;
			try {
				opcao = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Digite um número.");
				continue; // volta para o início do while
			}

			switch (opcao) {
			case 1: cadastrarAparelho(usuario);
				break;
			case 2: alterarAparelho(usuario);
				break;
			case 3: removerAparelho(usuario);
				break;
			case 4: gerenciando = false;
				break;
			default: System.out.println("Opção inválida.");
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

		Aparelho aparelho = null;
		boolean continuar = true;

		while (continuar && aparelho == null) {
			System.out.println("\n=== SELEÇÃO DE APARELHO ===");
			System.out.println("1. Pesquisar por ID");
			System.out.println("2. Pesquisar por Nome");
			System.out.println("0. Cancelar");
			System.out.print("Escolha: ");

			int opcao;
			if (scanner.hasNextInt()) {
				opcao = scanner.nextInt();
				scanner.nextLine();
			} else {
				System.out.println("Opção inválida.");
				scanner.nextLine();
				continue;
			}

			switch (opcao) {
			case 1: aparelho = selecionarAparelhoPorId();
				break;
			case 2: aparelho = selecionarAparelhoPorNome();
				break;
			case 0: {
				System.out.println("Operação cancelada.");
				return;
			}
			default: System.out.println("Opção inválida.");
			}
		}

		if (aparelho == null) {
			System.out.println("Nenhum aparelho selecionado.");
			return;
		}

		System.out.print("Novo nome (atual: " + aparelho.getNome() + "): ");
		String novoNome = scanner.nextLine();

		System.out.print("Nova descrição (atual: " + aparelho.getDescricao() + "): ");
		String novaDescricao = scanner.nextLine();

		System.out.print("Nova função (atual: " + aparelho.getFuncao() + "): ");
		String novaFuncao = scanner.nextLine();

		int id = aparelho.getId();

		Aparelho novo = new Aparelho(id, novoNome, novaDescricao, novaFuncao, academia);
		academia.alterarAparelhoPorId(id, novo);

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

		Aparelho aparelho = null;
		boolean continuar = true;

		while (continuar && aparelho == null) {
			System.out.println("\n=== SELEÇÃO DE APARELHO PARA REMOÇÃO ===");
			System.out.println("1. Pesquisar por ID");
			System.out.println("2. Pesquisar por Nome");
			System.out.println("0. Cancelar");
			System.out.print("Escolha: ");

			int opcao;
			if (scanner.hasNextInt()) {
				opcao = scanner.nextInt();
				scanner.nextLine();
			} else {
				System.out.println("Opção inválida.");
				scanner.nextLine();
				continue;
			}

			switch (opcao) {
			case 1: {
					System.out.print("Digite o ID do aparelho: ");
					if (scanner.hasNextInt()) {
						int id = scanner.nextInt();
						scanner.nextLine();
						aparelho = academia.buscarAparelhoPorId(id);
						if (aparelho == null) {
							System.out.println("Aparelho com ID " + id + " não encontrado.");
						}
					} else {
						System.out.println("ID inválido.");
						scanner.nextLine();
					}
				}
				break;
			case 2: {
					System.out.print("Digite parte ou o nome completo do aparelho: ");
					String nomeQuery = scanner.nextLine();
					List<Aparelho> resultados = academia.buscarAparelhosPorNome(nomeQuery);
	
					if (!resultados.isEmpty()) {
						System.out.println("\n--- APARELHOS ENCONTRADOS ---");
						for (int i = 0; i < resultados.size(); i++) {
							Aparelho a = resultados.get(i);
							System.out.println(i + " - ID: " + a.getId() + ", Nome: " + a.getNome());
						}
						System.out.print("Escolha o índice: ");
						int idx = Integer.parseInt(scanner.nextLine());
						if (idx >= 0 && idx < resultados.size()) {
							aparelho = resultados.get(idx);
						} else {
							System.out.println("Índice inválido.");
						}
					} else {
						System.out.println("Nenhum aparelho encontrado.");
					}
				}
			break;
			case 0: {
				System.out.println("Operação cancelada.");
				return;
			}
			default: System.out.println("Opção inválida.");
			}
		}

		if (aparelho == null) {
			System.out.println("Nenhum aparelho selecionado.");
			return;
		}

		academia.removerAparelho(aparelho);
		System.out.println("Aparelho removido com sucesso: " + aparelho.getNome());
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

	private static void listarAlunos() {
		List<Aluno> alunos = academia.getAlunos();
		if (alunos == null || alunos.isEmpty()) {
			System.out.println("Nenhum aluno cadastrado.");
			return;
		}
		System.out.println("\n=== LISTA DE ALUNOS ===");
		for (int i = 0; i < alunos.size(); i++) {
			Aluno a = alunos.get(i);
			System.out.println(i + " - ID: " + a.getId() + " | Nome: " + a.getNome() + " | Email: " + a.getEmail()
					+ " | Tel: " + a.getTelefone());
		}
	}

	private static void consultarAluno() {
		List<Aluno> alunos = academia.getAlunos();
		if (Utils.isEmpty(alunos)) {
			System.out.println("Nenhum aluno cadastrado.");
			return;
		}

		System.out.println("\nBuscar aluno por:");
		System.out.println("1. Nome");
		System.out.println("2. ID");
		System.out.print("Escolha uma opção: ");
		String escolha = scanner.nextLine();

		List<Aluno> resultados = new ArrayList<>();

		switch (escolha) {
		case "1": {
			System.out.print("Digite parte do nome: ");
			String termo = scanner.nextLine().toLowerCase();
			for (Aluno a : alunos) {
				if (a.getNome() != null && a.getNome().toLowerCase().contains(termo)) {
					resultados.add(a);
					break;
				}
			}
		}
		case "2": {
			try {
				System.out.print("Digite o ID: ");
				int id = Integer.parseInt(scanner.nextLine());
				for (Aluno a : alunos) {
					if (a.getId() == id) {
						resultados.add(a);
						break;
					}
				}
			} catch (NumberFormatException e) {
				System.out.println("ID inválido.");
				//return;
			}
		}
		default: {
			System.out.println("Opção inválida.");
			//return;
			}
		}
		
		if (resultados.isEmpty()) {
			System.out.println("Nenhum aluno encontrado.");
			return;
		}

		System.out.println("\n=== RESULTADOS ===");
		for (Aluno a : resultados) {
			System.out.println("------------------------------");
			System.out.println("ID: " + a.getId());
			System.out.println("Nome: " + a.getNome());
			System.out.println("Email: " + a.getEmail());
			System.out.println("Telefone: " + a.getTelefone());
			System.out.println("Altura: " + a.getAltura());
			System.out.println("Qtd. Treinos: " + (a.getTreinos() != null ? a.getTreinos().size() : 0));
			if (a.getTreinos() != null && !a.getTreinos().isEmpty()) {
				System.out.println("Treinos:");
				for (Treino t : a.getTreinos()) {
					System.out.println(" - [" + t.getId() + "] " + t.getNome() + " | Dia: " + t.getDiaSemana());
				}
			}
		}
		System.out.println("------------------------------");
	}

	private static void consultarAlunoPersonalizado() {
		boolean continuarConsultando = true;

		while (continuarConsultando) {
			System.out.println("\n=== CONSULTA DE ALUNOS ===");
			System.out.println("1. Pesquisar por ID");
			System.out.println("2. Pesquisar por Nome");
			System.out.println("0. Voltar ao Menu Anterior");
			System.out.print("Escolha o tipo de pesquisa: ");

			int tipoPesquisa;
			if (scanner.hasNextInt()) {
				tipoPesquisa = scanner.nextInt();
				scanner.nextLine();
			} else {
				System.out.println("Opção inválida. Digite um número.");
				scanner.nextLine();
				continue;
			}

			switch (tipoPesquisa) {
			case 1: pesquisarPorId();
				break;
			case 2: pesquisarPorNome();
				break;
			case 0: {
					continuarConsultando = false;
					System.out.println("Voltando ao menu principal...");
				}
			break;
			default: System.out.println("Opção de pesquisa inválida.");
			}
		}
	}

	private static void pesquisarPorId() {
		System.out.print("Digite o ID do aluno: ");
		if (scanner.hasNextInt()) {
			int id = scanner.nextInt();
			scanner.nextLine(); // Limpa buffer

			Aluno aluno = academia.buscarAlunoPorId(id);

			if (aluno != null) {
				System.out.println("\n--- ALUNO ENCONTRADO ---");
				System.out.println(
						"ID: " + aluno.getId() + ", Nome: " + aluno.getNome() + ", Email: " + aluno.getEmail());
			} else {
				System.out.println("Aluno com ID " + id + " não encontrado.");
			}
		} else {
			System.out.println("ID inválido. Por favor, digite um número.");
			scanner.nextLine();
		}
	}

	private static void pesquisarPorNome() {
		System.out.print("Digite parte ou o nome completo do aluno: ");
		String nomeQuery = scanner.nextLine();

		List<Aluno> resultados = academia.buscarAlunosPorNome(nomeQuery);

		if (!resultados.isEmpty()) {
			System.out.println("\n--- ALUNOS ENCONTRADOS (" + resultados.size() + ") ---");
			for (Aluno aluno : resultados) {
				System.out.println(
						"ID: " + aluno.getId() + ", Nome: " + aluno.getNome() + ", Email: " + aluno.getEmail());
			}
		} else {
			System.out.println("Nenhum aluno encontrado com o nome '" + nomeQuery + "'.");
		}
	}

	private static void pesquisarAparelhoPorId() {
		System.out.print("Digite o ID do aparelho: ");
		if (scanner.hasNextInt()) {
			int id = scanner.nextInt();
			scanner.nextLine();

			Aparelho aparelho = academia.buscarAparelhoPorId(id);
			if (aparelho != null) {
				System.out.println("\n--- APARELHO ENCONTRADO ---");
				System.out.println(aparelho.toString());

			} else {
				System.out.println("Aparelho com ID " + id + " não encontrado.");
			}
		} else {
			System.out.println("ID inválido. Por favor, digite um número.");
			scanner.nextLine();
		}
	}

	private static void pesquisarAparelhoPorNome() {
		System.out.print("Digite parte ou o nome completo do aparelho: ");
		String nomeQuery = scanner.nextLine();

		List<Aparelho> resultados = academia.buscarAparelhosPorNome(nomeQuery);

		if (!resultados.isEmpty()) {
			System.out.println("\n--- APARELHOS ENCONTRADOS (" + resultados.size() + ") ---");
			for (Aparelho aparelho : resultados) {
				System.out.println("---");
				System.out.println(aparelho.toString());
			}
			System.out.println("---");
		} else {
			System.out.println("Nenhum aparelho encontrado com o nome '" + nomeQuery + "'.");
		}
	}

	private static void consultarAparelhoPersonalizado() {
		boolean continuarConsultando = true;

		while (continuarConsultando) {
			System.out.println("\n=== CONSULTA DE APARELHOS ===");
			System.out.println("1. Pesquisar por ID");
			System.out.println("2. Pesquisar por Nome");
			System.out.println("0. Voltar ao Menu Anterior");
			System.out.print("Escolha o tipo de pesquisa: ");

			int tipoPesquisa;
			if (scanner.hasNextInt()) {
				tipoPesquisa = scanner.nextInt();
				scanner.nextLine();
			} else {
				System.out.println("Opção inválida. Digite um número.");
				scanner.nextLine();
				continue;
			}

			switch (tipoPesquisa) {
			case 1: pesquisarAparelhoPorId();
				break;
			case 2: pesquisarAparelhoPorNome();
				break;
			case 0: {
					continuarConsultando = false;
					System.out.println("Voltando ao menu anterior...");
				}
			break;
			default: System.out.println("Opção de pesquisa inválida.");
			}
		}
	}

	private static Aluno selecionarAlunoPorId() {
		System.out.print("Digite o ID do aluno: ");
		if (scanner.hasNextInt()) {
			int id = scanner.nextInt();
			scanner.nextLine();
			Aluno aluno = academia.buscarAlunoPorId(id);
			if (aluno != null) {
				System.out.println("Aluno encontrado: " + aluno.getNome());
				return aluno;
			} else {
				System.out.println("Aluno com ID " + id + " não encontrado.");
			}
		} else {
			System.out.println("ID inválido.");
			scanner.nextLine();
		}
		return null;
	}

	private static Aluno selecionarAlunoPorNome() {
		System.out.print("Digite parte ou o nome completo do aluno: ");
		String nomeQuery = scanner.nextLine();
		List<Aluno> resultados = academia.buscarAlunosPorNome(nomeQuery);

		if (!resultados.isEmpty()) {
			System.out.println("\n--- ALUNOS ENCONTRADOS ---");
			for (int i = 0; i < resultados.size(); i++) {
				Aluno a = resultados.get(i);
				System.out.println(i + " - ID: " + a.getId() + ", Nome: " + a.getNome());
			}
			System.out.print("Escolha o índice: ");
			int idx = Integer.parseInt(scanner.nextLine());
			if (idx >= 0 && idx < resultados.size()) {
				return resultados.get(idx);
			} else {
				System.out.println("Índice inválido.");
			}
		} else {
			System.out.println("Nenhum aluno encontrado.");
		}
		return null;
	}

	private static Aparelho selecionarAparelhoPorId() {
		System.out.print("Digite o ID do aparelho: ");
		if (scanner.hasNextInt()) {
			int id = scanner.nextInt();
			scanner.nextLine();
			for (Aparelho a : academia.getAparelhos()) {
				if (a.getId() == id) {
					System.out.println("Aparelho encontrado: " + a.getNome());
					return a;
				}
			}
			System.out.println("Nenhum aparelho encontrado com ID " + id);
		} else {
			System.out.println("ID inválido.");
			scanner.nextLine();
		}
		return null;
	}

	private static Aparelho selecionarAparelhoPorNome() {
		System.out.print("Digite parte ou o nome completo do aparelho: ");
		String nomeQuery = scanner.nextLine().toLowerCase();
		List<Aparelho> resultados = new ArrayList<>();
		for (Aparelho a : academia.getAparelhos()) {
			if (a.getNome().toLowerCase().contains(nomeQuery)) {
				resultados.add(a);
			}
		}

		if (!resultados.isEmpty()) {
			System.out.println("\n--- APARELHOS ENCONTRADOS ---");
			for (int i = 0; i < resultados.size(); i++) {
				Aparelho a = resultados.get(i);
				System.out.println(i + " - ID: " + a.getId() + ", Nome: " + a.getNome());
			}
			System.out.print("Escolha o índice: ");
			int idx = Integer.parseInt(scanner.nextLine());
			if (idx >= 0 && idx < resultados.size()) {
				return resultados.get(idx);
			} else {
				System.out.println("Índice inválido.");
			}
		} else {
			System.out.println("Nenhum aparelho encontrado.");
		}
		return null;
	}

	private static void gerenciarEvolucaoAluno(Pessoa usuario) {
		if (!(usuario instanceof Instrutor)) {
			System.out.println("Acesso negado. Apenas instrutores podem gerenciar evolução dos alunos.");
			return;
		}

		Aluno aluno = null;
		boolean continuar = true;

		while (continuar && aluno == null) {
			System.out.println("\n=== SELEÇÃO DE ALUNO PARA EVOLUÇÃO ===");
			System.out.println("1. Pesquisar por ID");
			System.out.println("2. Pesquisar por Nome");
			System.out.println("0. Cancelar");
			System.out.print("Escolha: ");

			int opcao;
			if (scanner.hasNextInt()) {
				opcao = scanner.nextInt();
				scanner.nextLine();
			} else {
				System.out.println("Opção inválida.");
				scanner.nextLine();
				continue;
			}

			switch (opcao) {
			case 1: aluno = selecionarAlunoPorId();
				break;
			case 2: aluno = selecionarAlunoPorNome();
				break;
			case 0: {
				System.out.println("Operação cancelada.");
				return;
			}
			default: System.out.println("Opção inválida.");
			}
		}

		if (aluno == null) {
			System.out.println("Nenhum aluno selecionado.");
			return;
		}

		boolean gerenciando = true;
		while (gerenciando) {
			System.out.println("\n=== GERENCIAR EVOLUÇÃO DO ALUNO ===");
			System.out.println("Aluno: " + aluno.getNome());
			System.out.println("1. Registrar nova evolução");
			System.out.println("2. Listar evoluções");
			System.out.println("3. Alterar evolução");
			System.out.println("4. Remover evolução");
			System.out.println("5. Voltar");
			System.out.print("Escolha: ");

			int opcao;
			try {
				opcao = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida.");
				continue;
			}

			switch (opcao) {
			case 1: {
					System.out.print("Data da avaliação (DD/MM/AAAA): ");
					String entradaData = scanner.nextLine();
	
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
					LocalDate data = LocalDate.parse(entradaData, formatter);
	
					System.out.print("Peso (kg): ");
					Float peso = Float.parseFloat(scanner.nextLine());
	
					System.out.print("Percentual de massa muscular (%): ");
					Float massaMuscular = Float.parseFloat(scanner.nextLine());
	
					Evolucao evolucao = new Evolucao(data, peso, massaMuscular, aluno);
					aluno.adicionarEvolucao(evolucao);
	
					System.out.println("Evolução registrada com sucesso!");
				}
				break;
			case 2: {
					List<Evolucao> evolucoes = aluno.getEvolucoes();
					if (evolucoes.isEmpty()) {
						System.out.println("Nenhuma evolução registrada para este aluno.");
					} else {
						System.out.println("\n=== HISTÓRICO DE EVOLUÇÃO ===");
						for (int i = 0; i < evolucoes.size(); i++) {
							Evolucao e = evolucoes.get(i);
							System.out.println(i + " - Data: " + e.getData() + " | Peso: " + e.getPeso() + "kg"
									+ " | Massa Muscular: " + e.getMassaMuscular() + "%");
						}
					}
				}
				break;
			case 3: {
					List<Evolucao> evolucoes = aluno.getEvolucoes();
					if (evolucoes.isEmpty()) {
						System.out.println("Nenhuma evolução registrada.");
					} else {
						System.out.println("\n=== EVOLUÇÕES ===");
						for (int i = 0; i < evolucoes.size(); i++) {
							Evolucao e = evolucoes.get(i);
							// Mostra a data formatada em DD/MM/AAAA
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
							String dataFormatada = e.getData().format(formatter);
	
							System.out.println(i + " - Data: " + dataFormatada + " | Peso: " + e.getPeso() + "kg"
									+ " | Massa Muscular: " + e.getMassaMuscular() + "%");
						}
	
						System.out.print("Escolha o índice da evolução a alterar: ");
						int idx;
						try {
							idx = Integer.parseInt(scanner.nextLine());
						} catch (NumberFormatException ex) {
							System.out.println("Entrada inválida. Digite um número válido.");
							return; // volta para o menu de evolução, não para o menu inicial
						}
	
						if (idx >= 0 && idx < evolucoes.size()) {
							try {
								System.out.print("Nova data (DD/MM/AAAA): ");
								String entradaData = scanner.nextLine();
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
								LocalDate novaData = LocalDate.parse(entradaData, formatter);
	
								System.out.print("Novo peso (kg): ");
								Float novoPeso = Float.parseFloat(scanner.nextLine());
	
								System.out.print("Nova massa muscular (%): ");
								Float novaMassa = Float.parseFloat(scanner.nextLine());
	
								Evolucao novaEvolucao = new Evolucao(novaData, novoPeso, novaMassa, aluno);
								aluno.alterarEvolucao(idx, novaEvolucao);
	
								System.out.println("Evolução alterada com sucesso!");
							} catch (Exception ex) {
								System.out.println("Entrada inválida. Tente novamente.");
								// não sai do menu, apenas retorna para o loop de evolução
							}
						} else {
							System.out.println("Índice inválido.");
						}
					}
				}
				break;
			case 4: {
					List<Evolucao> evolucoes = aluno.getEvolucoes();
					if (evolucoes.isEmpty()) {
						System.out.println("Nenhuma evolução registrada.");
					} else {
						System.out.println("\n=== EVOLUÇÕES ===");
						for (int i = 0; i < evolucoes.size(); i++) {
							Evolucao e = evolucoes.get(i);
							System.out.println(i + " - Data: " + e.getData() + " | Peso: " + e.getPeso() + "kg"
									+ " | Massa Muscular: " + e.getMassaMuscular() + "%");
						}
						System.out.print("Escolha o índice da evolução a remover: ");
						int idx = Integer.parseInt(scanner.nextLine());
						if (idx >= 0 && idx < evolucoes.size()) {
							Evolucao removida = evolucoes.get(idx);
							aluno.removerEvolucao(removida);
							System.out.println("Evolução removida com sucesso!");
						} else {
							System.out.println("Índice inválido.");
						}
					}
				}
				break;
			case 5: gerenciando = false;
				break;
			default: System.out.println("Opção inválida.");
			}
		}
	}

	private static void gerenciarTreinos(Pessoa usuario) {
		if (!(usuario instanceof Instrutor)) {
			System.out.println("Apenas instrutores podem gerenciar treinos.");
			return;
		}

		boolean gerenciando = true;
		while (gerenciando) {
			Instrutor instrutor = (Instrutor) usuario;
			System.out.println("\n==== TREINOS =====");
			System.out.println("1. Adicionar treino para aluno");
			System.out.println("2. Alterar treino de aluno");
			System.out.println("3. Remover treino de aluno");
			System.out.println("4. Voltar");

			System.out.print("Escolha uma opção: ");
			int opcao = Integer.parseInt(scanner.nextLine());

			switch (opcao) {
			case 1: adicionarTreinoParaAluno(instrutor);
				break;
			case 2: alterarTreinoDeAluno(instrutor);
				break;
			case 3: removerTreinoDeAluno(instrutor);
				break;
			case 4: gerenciando = false;
				break;
			default: System.out.println("Opção inválida.");
			}
		}
	}

	private static List<Exercicio> criarNovaListaDeExercicios() {
		List<Aparelho> aparelhosDisponiveis = academia.getAparelhos();
		List<Exercicio> novaListaExercicios = new ArrayList<>();

		if (aparelhosDisponiveis.isEmpty()) {
			System.out.println("Não há aparelhos cadastrados na academia. Não é possível adicionar exercícios.");
			return novaListaExercicios;
		}

		int ordem = 1;
		System.out.println("\n==== CRIAÇÃO/ALTERAÇÃO DE EXERCÍCIOS ===");

		while (true) {
			System.out.print("\nDeseja adicionar um novo exercício? (s/n): ");
			String continuar = scanner.nextLine().trim();
			if (!continuar.equalsIgnoreCase("s")) {
				break;
			}

			System.out.println("\n=== APARELHOS DISPONÍVEIS ===");
			for (int i = 0; i < aparelhosDisponiveis.size(); i++) {
				Aparelho a = aparelhosDisponiveis.get(i);
				System.out.printf("[%d] ID: %d | Nome: %s%n", i, a.getId(), a.getNome());
			}
			System.out.print("Escolha o ÍNDICE do Aparelho (ou digite 'f' para cancelar): ");
			String inputIndex = scanner.nextLine().trim();
			if (inputIndex.equalsIgnoreCase("f"))
				continue;

			try {
				int index = Integer.parseInt(inputIndex);
				if (index < 0 || index >= aparelhosDisponiveis.size()) {
					System.out.println("Índice inválido.");
					continue;
				}

				Aparelho aparelhoSelecionado = aparelhosDisponiveis.get(index);

				System.out.print("Carga (kg): ");
				Float carga = Float.parseFloat(scanner.nextLine());

				System.out.print("Número de Repetições: ");
				Integer repeticoes = Integer.parseInt(scanner.nextLine());

				Exercicio novoExercicio = new Exercicio(ordem++, carga, repeticoes, aparelhoSelecionado);
				novaListaExercicios.add(novoExercicio);

				System.out.println(
						"Exercício '" + aparelhoSelecionado.getNome() + "' adicionado com Carga: " + carga + "kg.");

			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Tente novamente.");
			}
		}

		return novaListaExercicios;
	}

	private static void alterarExercicioIndividualmente(Exercicio exercicio) {
		System.out.println("\n=== ALTERANDO EXERCÍCIO: " + exercicio.getAparelho().getNome() + " ===");

		System.out.print("Digite a nova carga (kg): ");
		String inputCarga = scanner.nextLine();
		if (!inputCarga.trim().isEmpty()) {
			try {
				exercicio.setCarga(Float.parseFloat(inputCarga));
				System.out.println("Carga alterada com sucesso.");
			} catch (NumberFormatException e) {
				System.out.println("Carga inválida. Valor anterior mantido.");
			}
		} else {
			System.out.println("Carga mantida.");
		}

		System.out.print("Novo Número de Repetições: ");
		String inputReps = scanner.nextLine();
		if (!inputReps.trim().isEmpty()) {
			try {
				exercicio.setNumeroRepeticoes(Integer.parseInt(inputReps));
				System.out.println("Repetições alteradas com sucesso.");
			} catch (NumberFormatException e) {
				System.out.println("Repetições inválidas. Valor anterior mantido.");
			}
		} else {
			System.out.println("Repetições mantidas.");
		}

	}

	private static Aluno selecionarAlunoParaAlteracao() {
		if (academia.getAlunos().isEmpty()) {
			System.out.println("Nenhum aluno cadastrado na academia.");
			return null;
		}

		System.out.println("\n--- BUSCA DE ALUNO ---");
		System.out.print("Digite o ID ou o Nome do aluno: ");
		String termoBusca = scanner.nextLine().trim();

		try {
			int idBusca = Integer.parseInt(termoBusca);

			Aluno alunoPorId = academia.buscarAlunoPorId(idBusca);
			if (alunoPorId != null) {
				System.out.println("✅ Aluno encontrado por ID: " + alunoPorId.getNome());
				return alunoPorId;
			}
		} catch (NumberFormatException e) {

		}

		List<Aluno> resultados = academia.buscarAlunosPorNome(termoBusca);

		if (resultados.isEmpty()) {
			System.out.println("Nenhum aluno encontrado com o termo: " + termoBusca);
			return null;
		}

		if (resultados.size() == 1) {
			System.out.println("✅ Aluno encontrado: " + resultados.get(0).getNome());
			return resultados.get(0);
		}

		System.out.println("\nForam encontrados " + resultados.size() + " alunos. Escolha pelo índice:");
		for (int i = 0; i < resultados.size(); i++) {
			System.out.printf("[%d] ID: %d | Nome: %s%n", i, resultados.get(i).getId(), resultados.get(i).getNome());
		}

		System.out.print("Escolha o índice do aluno desejado: ");
		try {
			int indexSelecionado = Integer.parseInt(scanner.nextLine());
			if (indexSelecionado >= 0 && indexSelecionado < resultados.size()) {
				return resultados.get(indexSelecionado);
			} else {
				System.out.println("Índice inválido.");
				return null;
			}
		} catch (NumberFormatException e) {
			System.out.println("Entrada inválida.");
			return null;
		}
	}

	private static void adicionarTreinoParaAluno(Instrutor instrutor) {
		Aluno aluno = null;
		boolean continuar = true;

		while (continuar && aluno == null) {
			System.out.println("\n=== SELEÇÃO DE ALUNO ===");
			System.out.println("1. Pesquisar por ID");
			System.out.println("2. Pesquisar por Nome");
			System.out.println("0. Cancelar");
			System.out.print("Escolha: ");

			int opcao;
			if (scanner.hasNextInt()) {
				opcao = scanner.nextInt();
				scanner.nextLine();
			} else {
				System.out.println("Opção inválida.");
				scanner.nextLine();
				continue;
			}

			switch (opcao) {
			case 1: aluno = selecionarAlunoPorId();
				break;
			case 2: aluno = selecionarAlunoPorNome();
				break;
			case 0: {
				System.out.println("Operação cancelada.");
				return;
			}
			default: System.out.println("Opção inválida.");
			}
		}

		if (aluno == null) {
			System.out.println("Nenhum aluno selecionado.");
			return;
		}

		System.out.print("Nome do treino: ");
		String nomeTreino = scanner.nextLine();

		System.out.print(
				"Dia da semana: \n1. Segunda\n2. Terça\n3. Quarta\n4. Quinta\n5. Sexta\n6. Sabado\n7. Domingo: ");
		int diaSemana = Integer.parseInt(scanner.nextLine());

		List<Treino> treinosExistentes = aluno.getTreinos();
		try {
			if (treinosExistentes != null && treinosExistentes.stream().anyMatch(t -> t.getDiaSemana() == diaSemana)) {
				throw new IllegalStateException("Aluno já possui um treino cadastrado para este dia da semana.");
			}
		} catch (IllegalStateException e) {
			System.out.println(e.getMessage());
			return;
		}

		int idTreino = treinosExistentes.size();
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
		Aluno aluno = null;
		boolean continuar = true;

		while (continuar && aluno == null) {
			System.out.println("\n=== SELEÇÃO DE ALUNO PARA ALTERAÇÃO ===");
			System.out.println("1. Pesquisar por ID");
			System.out.println("2. Pesquisar por Nome");
			System.out.println("0. Cancelar");
			System.out.print("Escolha: ");

			int opcao;
			if (scanner.hasNextInt()) {
				opcao = scanner.nextInt();
				scanner.nextLine();
			} else {
				System.out.println("Opção inválida.");
				scanner.nextLine();
				continue;
			}

			switch (opcao) {
			case 1: aluno = selecionarAlunoPorId();
				break;
			case 2: aluno = selecionarAlunoPorNome();
				break;
			case 0: {
				System.out.println("Operação cancelada.");
				return;
			}
			default: System.out.println("Opção inválida.");
			}
		}

		if (aluno == null) {
			System.out.println("Nenhum aluno selecionado.");
			return;
		}

		// --- TREINOS DO ALUNO ---
		List<Treino> treinos = aluno.getTreinos();
		if (treinos.isEmpty()) {
			System.out.println("Esse aluno não possui treinos.");
			return;
		}

		System.out.println("\n=== TREINOS DO ALUNO ===");
		for (int i = 0; i < treinos.size(); i++) {
			Treino t = treinos.get(i);
			String exerciciosAtuais = (t.getExercicios() != null && !t.getExercicios().isEmpty())
					? "(" + t.getExercicios().size() + " exercícios)"
					: "(Nenhum exercício)";
			System.out.printf("[%d] %s %s%n", i, t.getNome(), exerciciosAtuais);
		}

		System.out.print("Escolha o índice do treino a alterar: ");
		int indexTreino = Integer.parseInt(scanner.nextLine());
		if (indexTreino < 0 || indexTreino >= treinos.size()) {
			System.out.println("Índice inválido.");
			return;
		}

		Treino treino = treinos.get(indexTreino);

		System.out.print("Novo nome do treino (atual: " + treino.getNome() + "): ");
		String novoNome = scanner.nextLine();
		treino.setNome(novoNome);

		System.out.print("Novo dia da semana (atual: " + treino.getDiaSemana()
				+ "):\n1. Segunda\n2. Terça\n3. Quarta\n4. Quinta\n5. Sexta\n6. Sabado\n7. Domingo: ");
		int novoDia = Integer.parseInt(scanner.nextLine());
		treino.setDiaSemana(novoDia);

		List<Exercicio> listaExercicios = treino.getExercicios();

		if (listaExercicios.isEmpty()) {
			System.out.println("\nO treino não possui exercícios. Adicione um.");
			System.out.print("Deseja ADICIONAR uma lista de exercícios agora? (s/n): ");
			if (scanner.nextLine().equalsIgnoreCase("s")) {
				List<Exercicio> novosExercicios = criarNovaListaDeExercicios();
				treino.setExercicios(novosExercicios);
			}
		} else {
			boolean alterandoExercicios = true;
			while (alterandoExercicios) {
				System.out.println("\n--- OPÇÕES DE EXERCÍCIOS PARA " + aluno.getNome() + " / TREINO: "
						+ treino.getNome() + " ---");

				for (int i = 0; i < listaExercicios.size(); i++) {
					Exercicio e = listaExercicios.get(i);
					System.out.printf("[%d] %s - Carga: %.1fkg, Reps: %d%n", i, e.getAparelho().getNome(), e.getCarga(),
							e.getNumeroRepeticoes());
				}

				System.out.println("\nEscolha a ação:");
				System.out.println("1. Alterar um exercício existente (por índice)");
				System.out.println("2. Adicionar novos exercícios (anexar ao fim)");
				System.out.println("3. Voltar (Finalizar Alteração de Exercícios)");
				System.out.print("Opção: ");

				try {
					int opcaoExercicio = Integer.parseInt(scanner.nextLine());

					switch (opcaoExercicio) {
					case 1: {
							System.out.print("Digite o ÍNDICE do exercício que deseja alterar: ");
							int indexAlterar = Integer.parseInt(scanner.nextLine());
	
							if (indexAlterar >= 0 && indexAlterar < listaExercicios.size()) {
								alterarExercicioIndividualmente(listaExercicios.get(indexAlterar));
							} else {
								System.out.println("Índice de exercício inválido.");
							}
						}
						break;
					case 2: {
							List<Exercicio> novosExercicios = criarNovaListaDeExercicios();
							listaExercicios.addAll(novosExercicios);
							System.out.println("Novos exercícios adicionados ao treino.");
						}
						break;
					case 3: alterandoExercicios = false;
						break;
					default: System.out.println("Opção inválida.");
					}
				} catch (NumberFormatException e) {
					System.out.println("Entrada inválida. Digite um número.");
				}
			}
		}

		System.out.println("\nTreino alterado com sucesso!");
	}

	private static void removerTreinoDeAluno(Instrutor instrutor) {
		Aluno aluno = null;
		boolean continuar = true;

		while (continuar && aluno == null) {
			System.out.println("\n=== SELEÇÃO DE ALUNO PARA REMOÇÃO DE TREINO ===");
			System.out.println("1. Pesquisar por ID");
			System.out.println("2. Pesquisar por Nome");
			System.out.println("0. Cancelar");
			System.out.print("Escolha: ");

			int opcao;
			if (scanner.hasNextInt()) {
				opcao = scanner.nextInt();
				scanner.nextLine();
			} else {
				System.out.println("Opção inválida.");
				scanner.nextLine();
				continue;
			}

			switch (opcao) {
			case 1: aluno = selecionarAlunoPorId();
				break;
			case 2: aluno = selecionarAlunoPorNome();
				break;
			case 0: {
				System.out.println("Operação cancelada.");
				return;
			}
			default: System.out.println("Opção inválida.");
			}
		}

		if (aluno == null) {
			System.out.println("Nenhum aluno selecionado.");
			return;
		}

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
		System.out.println("Treino removido com sucesso para " + aluno.getNome());
	}

	private static void escolherTreinoDoDia(Pessoa usuario) {
		if (!(usuario instanceof Aluno)) {
			System.out.println("Apenas alunos podem escolher treinos.");
			return;
		}

		System.out.print(
				"Informe o dia da semana: \n1. Segunda\n2. Terça\n3. Quarta\n4. Quinta\n5. Sexta\n6. Sabado\n7. Domingo): ");
		
		int dia = Integer.parseInt(scanner.nextLine());
		Aluno aluno = (Aluno) usuario;
		List<Treino> treinosDoDia = new ArrayList<Treino>();
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
			System.out.println("" + e.getOrdem() + ". " + e.getAparelho().getNome() + " | Carga: " + e.getCarga()
					+ "kg | Repetições: " + e.getNumeroRepeticoes());
		}
	}

	private static void consultarTreinoDoDia(Aluno aluno) {
		List<Treino> treinos = aluno.getTreinos();
		if (treinos.isEmpty()) {
			System.out.println("Nenhum treino cadastrado para você.");
			return;
		}

		// Lista de dias da semana como opções
		String[] diasSemana = { "segunda", "terça", "quarta", "quinta", "sexta", "sábado", "domingo" };

		System.out.println("\n=== CONSULTAR TREINO DO DIA ===");
		for (int i = 0; i < diasSemana.length; i++) {
			System.out.println(i + " - " + diasSemana[i]);
		}

		System.out.print("Escolha o índice do dia da semana: ");
		int idxDia;
		try {
			idxDia = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Entrada inválida.");
			return;
		}

		if (idxDia < 0 || idxDia >= diasSemana.length) {
			System.out.println("Índice inválido.");
			return;
		}

		List<Treino> treinosDoDia = new ArrayList<>();
		for (Treino t : treinos) {
			if (t.getDiaSemana() == idxDia) {
				treinosDoDia.add(t);
			}
		}

		if (treinosDoDia.isEmpty()) {
			System.out.println("Nenhum treino encontrado para " + diasSemana[idxDia]);
			return;
		}

		System.out.println("\n=== TREINOS DISPONÍVEIS NA " + diasSemana[idxDia].toUpperCase() + " ===");
		for (int i = 0; i < treinosDoDia.size(); i++) {
			Treino t = treinosDoDia.get(i);
			System.out.println(i + " - " + t.getNome());
		}

		System.out.print("Escolha o índice do treino para gerar arquivo TXT: ");
		int idxTreino;
		try {
			idxTreino = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Entrada inválida.");
			return;
		}

		if (idxTreino < 0 || idxTreino >= treinosDoDia.size()) {
			System.out.println("Índice inválido.");
			return;
		}

		Treino treinoSelecionado = treinosDoDia.get(idxTreino);

		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter("treino_" + aluno.getNome() + "_" + diasSemana[idxDia] + ".txt"))) {

			writer.write("Treino do dia - Aluno: " + aluno.getNome());
			writer.newLine();
			writer.write("Dia da semana: " + diasSemana[treinoSelecionado.getDiaSemana()]);
			writer.newLine();
			writer.write("Nome do treino: " + treinoSelecionado.getNome());
			writer.newLine();
			writer.write("Exercícios:");
			writer.newLine();

			// 👉 É aqui que entra o for
			for (Exercicio exercicio : treinoSelecionado.getExercicios()) {
				writer.write("- Exercício " + exercicio.getOrdem() + ": " + exercicio.getAparelho().toStringSimples()
						+ " | Carga: " + exercicio.getCarga() + "kg" + " | Repetições: "
						+ exercicio.getNumeroRepeticoes());
				writer.newLine();
			}

			System.out.println("Treino exportado com sucesso para arquivo TXT!");
		} catch (IOException e) {
			System.out.println("Erro ao gravar treino em arquivo: " + e.getMessage());
		}

	}

	private static void preencherSistemaComDadosDeTeste() {
		inicializarAcademiaComValoresPadrao();

		Administrador admin = new Administrador(Academia.USER_DEFAULT, Academia.PWD_DEFAULT, true, 0, "Admin Padrão",
				"admin@academia.com", null, academia);
		
		academia.setAdministrador(admin);
		academia.login(admin.getLogin(), admin.getPassword());

		Instrutor instrutor1 = new Instrutor("instrutor10", "senha123", false, 10, "Carlos Santos", "carlos.s@gym.com",
				"(51) 9876-1234", null, academia);
		Instrutor instrutor2 = new Instrutor("instrutor11", "senha123", false, 11, "Ana Maria Silva", "ana.m@gym.com",
				"(51) 9876-5678", null, academia);

		Aluno aluno1 = new Aluno("aluno12", "senha123", false, 12, academia, "João Pereira", "joao.p@teste.com",
				"(51) 9912-3456", new Date(), 1.75f);
		Aluno aluno2 = new Aluno("aluno13", "senha123", false, 13, academia, "Maria Oliveira", "maria.o@teste.com",
				"(51) 9912-4567", new Date(), 1.63f);
		Aluno aluno3 = new Aluno("aluno14", "senha123", false, 14, academia, "Pedro Souza", "pedro.s@teste.com",
				"(51) 9912-5678", new Date(), 1.88f);
		Aluno aluno4 = new Aluno("aluno15", "senha123", false, 15, academia, "Julia Lima", "julia.l@teste.com",
				"(51) 9912-6789", new Date(), 1.70f);
		Aluno aluno5 = new Aluno("aluno16", "senha123", false, 16, academia, "Lucas Rocha", "lucas.r@teste.com",
				"(51) 9912-7890", new Date(), 1.82f);
		Aluno aluno6 = new Aluno("aluno17", "senha123", false, 17, academia, "Mariana Alves", "mariana.a@teste.com",
				"(51) 9912-8901", new Date(), 1.68f);
		Aluno aluno7 = new Aluno("aluno18", "senha123", false, 18, academia, "Felipe Costa", "felipe.c@teste.com",
				"(51) 9912-9012", new Date(), 1.77f);
		Aluno aluno8 = new Aluno("aluno19", "senha123", false, 19, academia, "Larissa Melo", "larissa.m@teste.com",
				"(51) 9912-0123", new Date(), 1.60f);
		Aluno aluno9 = new Aluno("aluno20", "senha123", false, 20, academia, "Rafael Gomes", "rafael.g@teste.com",
				"(51) 9912-1234", new Date(), 1.85f);

		List<Aluno> alunos = List.of(aluno1, aluno2, aluno3, aluno4, aluno5, aluno6, aluno7, aluno8, aluno9);
		academia.adicionarAlunos(alunos);

		List<Instrutor> instrutores = List.of(instrutor1, instrutor2);
		academia.adicionarInstrutores(instrutores);

		Storage.salvarDados(academia);

		academia.logout();

		System.out.println("Academia cadastrada com sucesso.");
	}

	private static void inicializarAcademiaComValoresPadrao() {
		Endereco enderecoPadrao = new Endereco(999, "Rua da Programação", "101", "Bloco A", "Bairro Teste", "99999-999",
				"Cidade Padrão", "TS");

		academia = new Academia("admin", "1234", 1, // ID da Academia
				"Academia Giga Fitness", "(51) 3333-4444", "www.gigafitness.com.br", enderecoPadrao);
	}

}