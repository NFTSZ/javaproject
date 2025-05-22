import java.time.LocalDate;

public class Contato {
    private String telefonePessoal;
    private String nomeAgenteSaude;
    private String telefoneUBS;
    private String nomeUBS;

    public Contato(String telefonePessoal, String nomeAgenteSaude, String telefoneUBS, String nomeUBS) {
        this.telefonePessoal = telefonePessoal;
        this.nomeAgenteSaude = nomeAgenteSaude;
        this.telefoneUBS = telefoneUBS;
        this.nomeUBS = nomeUBS;
    }

    // Getters
    public String getTelefonePessoal() { return telefonePessoal; }
    public String getNomeAgenteSaude() { return nomeAgenteSaude; }
    public String getTelefoneUBS() { return telefoneUBS; }
    public String getNomeUBS() { return nomeUBS; }

    // Setters
    public void setTelefonePessoal(String telefonePessoal) { this.telefonePessoal = telefonePessoal; }
    public void setNomeAgenteSaude(String nomeAgenteSaude) { this.nomeAgenteSaude = nomeAgenteSaude; }
    public void setTelefoneUBS(String telefoneUBS) { this.telefoneUBS = telefoneUBS; }
    public void setNomeUBS(String nomeUBS) { this.nomeUBS = nomeUBS; }
}