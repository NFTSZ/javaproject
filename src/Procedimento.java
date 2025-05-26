import java.time.LocalDate;

public class Procedimento {
    private String nome;
    private boolean realizado;
    private LocalDate dataRealizacao;

    public Procedimento(String nome) {
        this.nome = nome;
        this.realizado = false;
    }

    public void marcarRealizado(LocalDate data) {
        this.realizado = true;
        this.dataRealizacao = data;
    }

    // Getters
    public String getNome() { return nome; }
    public boolean isRealizado() { return realizado; }
    public LocalDate getDataRealizacao() { return dataRealizacao; }

    // Setters
    public void setRealizado(boolean realizado) { this.realizado = realizado; }
    public void setDataRealizacao(LocalDate dataRealizacao) { this.dataRealizacao = dataRealizacao; }
}