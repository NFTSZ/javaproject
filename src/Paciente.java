import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Paciente {
    private static int proximoId = 1;

    private int id;
    private String nome;
    private String cpf;
    private Contato contato;
    private OCI oci;
    private LocalDate dataCadastro;

    public Paciente(String nome, String cpf, Contato contato, OCI oci) {
        this.id = proximoId++;
        this.nome = nome;
        this.cpf = cpf;
        this.contato = contato;
        this.oci = oci;
        this.dataCadastro = LocalDate.now();
    }

    public void exibirResumoCadastro() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("\n=== CADASTRO REALIZADO COM SUCESSO ===");
        System.out.println("ID do Paciente: " + id);
        System.out.println("Data de Cadastro: " + dataCadastro.format(formatter));
        System.out.println("\nProcedimentos Obrigatórios:");
        for (Procedimento p : oci.getProcedimentosObrigatorios()) {
            System.out.println(" - " + p.getNome());
        }
    }

    public void exibirDadosCompletos() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("\n=== DADOS COMPLETOS DO PACIENTE ===");
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("\n=== CONTATO ===");
        System.out.println("Telefone Pessoal: " + contato.getTelefonePessoal());
        System.out.println("Agente de Saúde: " + contato.getNomeAgenteSaude());
        System.out.println("UBS: " + contato.getNomeUBS());
        System.out.println("Telefone da UBS: " + contato.getTelefoneUBS());
        System.out.println("\n=== DADOS DA OCI ===");
        System.out.println("Número: " + oci.getNumero());
        System.out.println("Descrição: " + oci.getDescricao());
        System.out.println("Tipo: " + oci.getTipo());
        System.out.println("Data de Início: " + oci.getDataInicio().format(formatter));
        System.out.println("Data Limite: " + oci.getDataLimite().format(formatter));
        System.out.println("Dias para conclusão: " + oci.getDiasLimite());
        System.out.println("Status: " + oci.getStatus());
        System.out.println("\nProcedimentos Obrigatórios:");
        for (Procedimento p : oci.getProcedimentosObrigatorios()) {
            System.out.print(" - " + p.getNome());
            if (p.isRealizado()) {
                System.out.println(" (Realizado em: " + p.getDataRealizacao().format(formatter) + ")");
            } else {
                System.out.println(" (Pendente)");
            }
        }
        System.out.println("\nData de Cadastro: " + dataCadastro.format(formatter));
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public Contato getContato() { return contato; }
    public OCI getOCI() { return oci; }
    public LocalDate getDataCadastro() { return dataCadastro; }

    public void setNome(String nome) { this.nome = nome; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setContato(Contato contato) { this.contato = contato; }
    public void setOCI(OCI oci) { this.oci = oci; }
}