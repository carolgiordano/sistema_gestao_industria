package model;

import java.time.LocalDate;

public class Producao {

    private Integer idProducao;
    private String nomeProduto;
    private Produto produto;
    private Funcionario funcionario;
    private LocalDate dataProducao;
    private Integer quantidade;

    @Override
    public String toString() {
        return "{\n" +
                "  \"idProducao\": " + idProducao + ",\n" +
                "  \"nomeProduto\": \"" + nomeProduto + "\",\n" +
                "  \"produto\": " + produto.toString() + ",\n" +
                "  \"funcionario\": " + funcionario.toString() + ",\n" +
                "  \"dataProducao\": \"" + dataProducao + "\",\n" +
                "  \"quantidade\": " + quantidade + "\n" +
                "}";
    }

    public Producao() {
    }

    public Producao(Integer idProducao, String nomeProduto, Produto produto,
                    Funcionario funcionario, LocalDate dataProducao, Integer quantidade) {
        this.idProducao = idProducao;
        this.nomeProduto = nomeProduto;
        this.produto = produto;
        this.funcionario = funcionario;
        this.dataProducao = dataProducao;
        this.quantidade = quantidade;
    }

    public Integer getIdProducao() {

        return idProducao;
    }

    public void setIdProducao(Integer idProducao) {

        this.idProducao = idProducao;
    }

    public String getNomeProduto() {

        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {

        this.nomeProduto = nomeProduto;
    }

    public Produto getProduto() {

        return produto;
    }

    public void setProduto(Produto produto) {

        this.produto = produto;
    }

    public Funcionario getFuncionario() {

        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {

        this.funcionario = funcionario;
    }

    public LocalDate getDataProducao() {

        return dataProducao;
    }

    public void setDataProducao(LocalDate dataProducao) {

        this.dataProducao = dataProducao;
    }

    public Integer getQuantidade() {

        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {

        this.quantidade = quantidade;
    }
}
