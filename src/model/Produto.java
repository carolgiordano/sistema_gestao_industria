package model;

public class Produto {

    private Integer idProduto;
    private String nomeProduto;
    private String descricao;

    @Override
    public String toString() {
        return "{\n" +
                "  \"idProduto\": " + idProduto + ",\n" +
                "  \"nomeProduto\": \"" + nomeProduto + "\",\n" +
                "  \"descricao\": \"" + descricao + "\"\n" +
                "}";
    }

    public Produto() {
    }

    public Produto(String nomeProduto, String descricao) {
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
    }

    public Produto(Integer idProduto, String nomeProduto, String descricao) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
    }

    public Integer getIdProduto() {

        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {

        this.idProduto = idProduto;
    }

    public String getNomeProduto() {

        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {

        this.nomeProduto = nomeProduto;
    }

    public String getDescricao() {

        return descricao;
    }

    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }
}
