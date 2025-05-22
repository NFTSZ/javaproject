import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaOCI sistema = new SistemaOCI();
        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("\n=== SISTEMA DE MONITORAMENTO DE OCI ===");
            System.out.println("1. Cadastrar novo paciente");
            System.out.println("2. Listar todos os pacientes");
            System.out.println("3. Exibir detalhes de um paciente");
            System.out.println("4. Editar paciente");
            System.out.println("5. Atualizar procedimentos de um paciente");
            System.out.println("6. Cadastrar nova OCI");
            System.out.println("7. Listar todas as OCIs disponíveis");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    sistema.cadastrarPaciente(scanner);
                    break;
                case 2:
                    sistema.listarPacientes();
                    break;
                case 3:
                    System.out.print("Digite o ID do paciente: ");
                    int idDetalhes = scanner.nextInt();
                    scanner.nextLine();
                    sistema.exibirDetalhesPaciente(idDetalhes);
                    break;
                case 4:
                    System.out.print("Digite o ID do paciente que deseja editar: ");
                    int idEditar = scanner.nextInt();
                    scanner.nextLine();
                    sistema.editarPaciente(idEditar, scanner);
                    break;
                case 5:
                    System.out.print("Digite o ID do paciente: ");
                    int idProcedimentos = scanner.nextInt();
                    scanner.nextLine();
                    sistema.atualizarProcedimentos(idProcedimentos, scanner);
                    break;
                case 6:
                    sistema.cadastrarNovaOCI(scanner);
                    break;
                case 7:
                    sistema.listarTodasOCIs();
                    break;
                case 0:
                    executando = false;
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }
}