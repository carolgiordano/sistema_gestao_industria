package model;

public class Setor {

    // Atributos
    private Integer idSetor;
    private String nomeSetor;
    private String responsavel;

    // Construtor padrão
    public Setor() {
    }

    public Setor(Integer idSetor) {
        this.idSetor = idSetor;
    }

    // Construtor com parâmetros
    public Setor(Integer idSetor, String nomeSetor, String responsavel) {
        this.idSetor = idSetor;
        this.nomeSetor = nomeSetor;
        this.responsavel = responsavel;
    }

    // Getters e Setters
    public Integer getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(Integer idSetor) {
        this.idSetor = idSetor;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    // Representação JSON da classe
    @Override
    public String toString() {
        return "{\n" +
                "  \"idSetor\": " + idSetor + ",\n" +
                "  \"nomeSetor\": \"" + nomeSetor + "\",\n" +
                "  \"responsavel\": \"" + responsavel + "\"\n" +
                "}";
    }
}
