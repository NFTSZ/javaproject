import java.time.LocalDate;
import java.util.*;

public class BaseOCI {
    private static Map<String, OCI> mapaOCI = new HashMap<>();

    static {
        // Oncologia (30 dias)
        mapaOCI.put("09.01.01.001-4", new OCI("09.01.01.001-4",
                "AVALIAÇÃO DIAGNÓSTICA INICIAL DE CÂNCER DE MAMA", "Oncologia",
                List.of("Consulta especializada", "Mamografia")));

        // ... (adicionar outras OCIs conforme necessário)
    }

    public static OCI buscarOCI(String numero, LocalDate dataInicio) {
        OCI ociBase = mapaOCI.get(numero);
        if (ociBase != null) {
            OCI novaOCI = new OCI(ociBase.getNumero(), ociBase.getDescricao(), ociBase.getTipo(),
                    new ArrayList<>(ociBase.getProcedimentosObrigatorios()
                            .stream().map(Procedimento::getNome).toList()));
            novaOCI.definirDataInicio(dataInicio);
            return novaOCI;
        }
        return null;
    }

    public static List<OCI> listarTodasOCIs() {
        return new ArrayList<>(mapaOCI.values());
    }

    public static void adicionarOCI(String numero, String descricao, String tipo,
                                    List<String> procedimentosObrigatorios) {
        mapaOCI.put(numero, new OCI(numero, descricao, tipo, procedimentosObrigatorios));
    }
}