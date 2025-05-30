import java.time.LocalDate;
import java.util.*;

public class BaseOCI {
    private static Map<String, OCI> mapaOCI = new HashMap<>();

    static {
        // Oncologia (30 dias)
        mapaOCI.put("09.01.01.001-4", new OCI("09.01.01.001-4",
                "AVALIAÇÃO DIAGNÓSTICA INICIAL DE CÂNCER DE MAMA", "Oncologia",
                List.of("Consulta especializada", "Mamografia")));

        mapaOCI.put("09.01.01.008-1", new OCI("09.01.01.008-1",
                "AVALIAÇÃO DIAGNÓSTICA DE CÂNCER COLORRETAL", "Oncologia",
                List.of("Consulta especializada", "Colonoscopia")));

        // Cardiologia (60 dias)
        mapaOCI.put("03.01.01.005-2", new OCI("03.01.01.005-2",
                "AVALIAÇÃO CARDIOVASCULAR COMPLETA", "Cardiologia",
                List.of("Consulta com cardiologista", "Eletrocardiograma")));

        // Ortopedia (60 dias)
        mapaOCI.put("04.01.01.003-9", new OCI("04.01.01.003-9",
                "AVALIAÇÃO ORTOPÉDICA PARA DORES CRÔNICAS", "Ortopedia",
                List.of("Consulta com ortopedista", "Radiografia")));

        // Oftalmologia (60 dias)
        mapaOCI.put("05.01.01.007-6", new OCI("05.01.01.007-6",
                "AVALIAÇÃO OFTALMOLÓGICA COMPLETA", "Oftalmologia",
                List.of("Consulta com oftalmologista", "Exame de refração")));

        // Otorrinolaringologia (60 dias)
        mapaOCI.put("06.01.01.002-8", new OCI("06.01.01.002-8",
                "AVALIAÇÃO OTORRINOLARINGOLÓGICA PARA DISTÚRBIOS AUDITIVOS", "Otorrinolaringologia",
                List.of("Consulta com otorrinolaringologista", "Audiometria")));
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