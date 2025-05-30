import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SistemaOCI {
    private List<Paciente> pacientes = new ArrayList<>();
    private BaseOCI baseOCI = new BaseOCI();

    public void cadastrarPaciente(Scanner scanner) {
        try {
            System.out.println("\n=== CADASTRO DE PACIENTE ===");

            System.out.print("Nome do paciente: ");
            String nome = scanner.nextLine();
            TratadorExcecoes.validarNaoVazio("Nome", nome);

            System.out.print("CPF do paciente: ");
            String cpf = scanner.nextLine();
            TratadorExcecoes.validarCPF(cpf);

            System.out.print("Telefone pessoal: ");
            String telefonePessoal = scanner.nextLine();
            TratadorExcecoes.validarNaoVazio("Telefone pessoal", telefonePessoal);

            System.out.print("Nome do agente de saúde: ");
            String nomeAgente = scanner.nextLine();
            TratadorExcecoes.validarNaoVazio("Nome do agente de saúde", nomeAgente);

            System.out.print("Nome da UBS: ");
            String nomeUBS = scanner.nextLine();
            TratadorExcecoes.validarNaoVazio("Nome da UBS", nomeUBS);

            System.out.print("Telefone da UBS: ");
            String telefoneUBS = scanner.nextLine();
            TratadorExcecoes.validarNaoVazio("Telefone da UBS", telefoneUBS);

            Contato contato = new Contato(telefonePessoal, nomeAgente, telefoneUBS, nomeUBS);

            System.out.println("\n=== DADOS DA OCI ===");
            System.out.print("Número da OCI: ");
            String numeroOCI = scanner.nextLine();
            TratadorExcecoes.validarNaoVazio("Número da OCI", numeroOCI);

            System.out.print("Data de início da OCI (AAAA-MM-DD): ");
            String dataInicioStr = scanner.nextLine();
            LocalDate dataInicio = TratadorExcecoes.validarData(dataInicioStr);

            OCI oci = baseOCI.buscarOCI(numeroOCI, dataInicio);

            if (oci == null) {
                System.out.println("Número da OCI não encontrado.");
                return;
            }

            Paciente paciente = new Paciente(nome, cpf, contato, oci);
            pacientes.add(paciente);

            paciente.exibirResumoCadastro();

        } catch (IllegalArgumentException e) {
            System.out.println("Erro no cadastro: " + e.getMessage());
        }
    }

    public void listarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("\nNenhum paciente cadastrado.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("\n=== LISTA DE PACIENTES ===");
        System.out.printf("%-5s %-30s %-15s %-20s %-15s %-12s\n",
                "ID", "Nome", "CPF", "Tipo OCI", "Data Limite", "Status");

        for (Paciente paciente : pacientes) {
            System.out.printf("%-5d %-30s %-15s %-20s %-15s %-12s\n",
                    paciente.getId(),
                    paciente.getNome(),
                    paciente.getCpf(),
                    paciente.getOCI().getTipo(),
                    paciente.getOCI().getDataLimite().format(formatter),
                    paciente.getOCI().getStatus());
        }
    }

    public void exibirDetalhesPaciente(int id) {
        Paciente paciente = buscarPacientePorId(id);

        if (paciente != null) {
            paciente.exibirDadosCompletos();
        } else {
            System.out.println("Paciente não encontrado.");
        }
    }

    public void editarPaciente(int id, Scanner scanner) {
        Paciente paciente = buscarPacientePorId(id);

        if (paciente == null) {
            System.out.println("Paciente não encontrado.");
            return;
        }

        boolean editando = true;
        while (editando) {
            System.out.println("\nEditando paciente: " + paciente.getNome());
            System.out.println("=== DADOS ATUAIS ===");
            System.out.println("1. Nome: " + paciente.getNome());
            System.out.println("2. CPF: " + paciente.getCpf());
            System.out.println("=== CONTATO ===");
            System.out.println("3. Telefone Pessoal: " + paciente.getContato().getTelefonePessoal());
            System.out.println("4. Nome do Agente de Saúde: " + paciente.getContato().getNomeAgenteSaude());
            System.out.println("5. Nome da UBS: " + paciente.getContato().getNomeUBS());
            System.out.println("6. Telefone da UBS: " + paciente.getContato().getTelefoneUBS());
            System.out.println("=== OCI ===");
            System.out.println("7. Número da OCI: " + paciente.getOCI().getNumero());
            System.out.println("8. Data de Início: " + paciente.getOCI().getDataInicio());
            System.out.println("0. Finalizar edição");
            System.out.print("Escolha o campo que deseja editar: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("Novo nome: ");
                    paciente.setNome(scanner.nextLine());
                    System.out.println("Nome atualizado com sucesso.");
                    break;
                case 2:
                    System.out.print("Novo CPF: ");
                    paciente.setCpf(scanner.nextLine());
                    System.out.println("CPF atualizado com sucesso.");
                    break;
                case 3:
                    System.out.print("Novo telefone pessoal: ");
                    paciente.getContato().setTelefonePessoal(scanner.nextLine());
                    System.out.println("Telefone pessoal atualizado com sucesso.");
                    break;
                case 4:
                    System.out.print("Novo nome do agente de saúde: ");
                    paciente.getContato().setNomeAgenteSaude(scanner.nextLine());
                    System.out.println("Agente de saúde atualizado com sucesso.");
                    break;
                case 5:
                    System.out.print("Novo nome da UBS: ");
                    paciente.getContato().setNomeUBS(scanner.nextLine());
                    System.out.println("Nome da UBS atualizado com sucesso.");
                    break;
                case 6:
                    System.out.print("Novo telefone da UBS: ");
                    paciente.getContato().setTelefoneUBS(scanner.nextLine());
                    System.out.println("Telefone da UBS atualizado com sucesso.");
                    break;
                case 7:
                    System.out.print("Novo número da OCI: ");
                    String novoNumeroOCI = scanner.nextLine();
                    OCI ociExistente = baseOCI.buscarOCI(novoNumeroOCI, paciente.getOCI().getDataInicio());
                    if (ociExistente != null) {
                        paciente.setOCI(ociExistente);
                        System.out.println("OCI atualizada com sucesso.");
                    } else {
                        System.out.println("OCI não encontrada. Mantida a atual.");
                    }
                    break;
                case 8:
                    System.out.print("Nova data de início (AAAA-MM-DD): ");
                    LocalDate novaData = LocalDate.parse(scanner.nextLine());
                    OCI novaOCI = baseOCI.buscarOCI(paciente.getOCI().getNumero(), novaData);
                    if (novaOCI != null) {
                        paciente.setOCI(novaOCI);
                        System.out.println("Data de início atualizada com sucesso.");
                    } else {
                        System.out.println("Erro ao atualizar data. Mantida a atual.");
                    }
                    break;
                case 0:
                    editando = false;
                    System.out.println("Edição finalizada.");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public void atualizarProcedimentos(int id, Scanner scanner) {
        Paciente paciente = buscarPacientePorId(id);

        if (paciente == null) {
            System.out.println("Paciente não encontrado.");
            return;
        }

        System.out.println("\nAtualizando procedimentos para: " + paciente.getNome());
        System.out.println("OCI: " + paciente.getOCI().getDescricao());

        OCI oci = paciente.getOCI();
        List<Procedimento> procedimentos = oci.getProcedimentosObrigatorios();

        System.out.println("\nProcedimentos Obrigatórios:");
        for (int i = 0; i < procedimentos.size(); i++) {
            Procedimento p = procedimentos.get(i);
            System.out.printf("%d. %s - %s\n", i+1, p.getNome(), p.isRealizado() ? "Realizado" : "Pendente");
        }

        System.out.print("\nDigite o número do procedimento a ser atualizado (0 para cancelar): ");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        if (escolha == 0) {
            System.out.println("Operação cancelada.");
            return;
        }

        if (escolha < 1 || escolha > procedimentos.size()) {
            System.out.println("Número inválido.");
            return;
        }

        Procedimento procedimento = procedimentos.get(escolha - 1);

        if (procedimento.isRealizado()) {
            System.out.print("Deseja marcar como não realizado? (S/N): ");
            String resposta = scanner.nextLine();

            if (resposta.equalsIgnoreCase("S")) {
                procedimento.setRealizado(false);
                procedimento.setDataRealizacao(null);
                System.out.println("Procedimento marcado como não realizado.");
            }
        } else {
            System.out.print("Deseja marcar como realizado? (S/N): ");
            String resposta = scanner.nextLine();

            if (resposta.equalsIgnoreCase("S")) {
                System.out.print("Data de realização (AAAA-MM-DD): ");
                LocalDate data = LocalDate.parse(scanner.nextLine());
                procedimento.marcarRealizado(data);
                System.out.println("Procedimento atualizado com sucesso.");
            }
        }
    }

    public void cadastrarNovaOCI(Scanner scanner) {
        System.out.println("\n=== CADASTRO DE NOVA OCI ===");

        System.out.print("Número da OCI: ");
        String numero = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Tipo (Oncologia/Cardiologia/Ortopedia/Oftalmologia/Otorrinolaringologia): ");
        String tipo = scanner.nextLine();

        System.out.println("\nProcedimentos Obrigatórios (digite 'fim' para terminar):");
        List<String> procedimentosObrigatorios = new ArrayList<>();
        while (true) {
            System.out.print("Nome do procedimento: ");
            String procedimento = scanner.nextLine();
            if (procedimento.equalsIgnoreCase("fim")) break;
            procedimentosObrigatorios.add(procedimento);
        }

        baseOCI.adicionarOCI(numero, descricao, tipo, procedimentosObrigatorios);
        System.out.println("\nOCI cadastrada com sucesso!");
    }

    public void listarTodasOCIs() {
        List<OCI> ocis = baseOCI.listarTodasOCIs();

        if (ocis.isEmpty()) {
            System.out.println("\nNenhuma OCI cadastrada.");
            return;
        }

        System.out.println("\n=== LISTA DE TODAS AS OCIs ===");
        System.out.printf("%-20s %-50s %-20s %-10s %-10s\n",
                "Número", "Descrição", "Tipo", "Dias Limite", "Procedimentos");

        for (OCI oci : ocis) {
            System.out.printf("%-20s %-50s %-20s %-10d %-10d\n",
                    oci.getNumero(),
                    oci.getDescricao(),
                    oci.getTipo(),
                    oci.getDiasLimite(),
                    oci.getProcedimentosObrigatorios().size());
        }
    }

    public Paciente buscarPacientePorId(int id) {
        for (Paciente paciente : pacientes) {
            if (paciente.getId() == id) {
                return paciente;
            }
        }
        return null;
    }
}