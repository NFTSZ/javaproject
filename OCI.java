import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class OCI {
    private String numero;
    private String descricao;
    private String tipo;
    private LocalDate dataInicio;
    private LocalDate dataLimite;
    private List<Procedimento> procedimentosObrigatorios;
    private int diasLimite;

    public OCI(String numero, String descricao, String tipo, List<String> nomesProcedimentosObrigatorios) {
        this.numero = numero;
        this.descricao = descricao;
        this.tipo = tipo;
        this.procedimentosObrigatorios = new ArrayList<>();

        for (String nome : nomesProcedimentosObrigatorios) {
            procedimentosObrigatorios.add(new Procedimento(nome));
        }

        this.diasLimite = "Oncologia".equalsIgnoreCase(tipo) ? 30 : 60;
    }

    public void definirDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
        this.dataLimite = dataInicio.plusDays(diasLimite);
    }

    // Getters
    public String getNumero() { return numero; }
    public String getDescricao() { return descricao; }
    public String getTipo() { return tipo; }
    public LocalDate getDataInicio() { return dataInicio; }
    public LocalDate getDataLimite() { return dataLimite; }
    public List<Procedimento> getProcedimentosObrigatorios() { return procedimentosObrigatorios; }
    public int getDiasLimite() { return diasLimite; }

    public boolean todosProcedimentosObrigatoriosConcluidos() {
        return procedimentosObrigatorios.stream().allMatch(Procedimento::isRealizado);
    }

    public String getStatus() {
        if (todosProcedimentosObrigatoriosConcluidos()) {
            return "Concluído";
        } else if (LocalDate.now().isAfter(dataLimite)) {
            return "Atrasado";
        } else {
            return "Em andamento";
        }
    }
}