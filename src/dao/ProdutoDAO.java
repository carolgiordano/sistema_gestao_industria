package dao;

import config.ConexaoMySQL;
import model.Produto;
import model.Setor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAO {

    private Connection conn = ConexaoMySQL.getConnection();

    public ArrayList<Produto> Listar() {

        try {
            ArrayList<Produto> produtos = new ArrayList<>();
            String sql = "SELECT * FROM produtos;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id_produto");
                String nomeProduto = rs.getString("nome_produto");
                String descricao = rs.getString("descricao");

                produtos.add(new Produto(id, nomeProduto, descricao));


            }

            return produtos;

        } catch (SQLException erro) {
            System.out.println("Deu ruim em listar os produtos." + erro.getMessage());
            return null;
        }

    }

    public Produto buscaPorId(Integer id) {
        try {
            String sql = "SELECT * FROM produtos WHERE id_produto = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Integer idProduto = rs.getInt("id_produto");
                String nomeProduto = rs.getString("nome_produto");
                String descricao = rs.getString("descricao");

                Produto produto = new Produto(idProduto, nomeProduto, descricao);
                return produto;
            }


        } catch (SQLException erro) {
            System.out.println("Erro ao buscar o produto pelo ID" + erro);

        }

        return null;

    }

    public Boolean cadastrar(Produto produto) {
        try {
            String sql = "INSERT INTO produtos (nome_produto, descricao) VALUES( ? , ? );";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, produto.getNomeProduto());
            ps.setString(2, produto.getDescricao());

            int qtsDeLinha = ps.executeUpdate();

            if (qtsDeLinha > 0){
                return true;
            }
            return false;

        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar o produto" + erro.getMessage());


        }

        return false;
    }

    public Boolean atualizar (Produto produto){
        try {
            String sql = "UPDATE produtos SET nome_produto = ?, descricao = ? WHERE id_produto = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, produto.getNomeProduto());
            ps.setString(2, produto.getDescricao());
            ps.setInt(3, produto.getIdProduto());
            int qtdeAtualização = ps.executeUpdate();

            if (qtdeAtualização > 0){
                return true;

            }return false;

        } catch (SQLException erro){
            System.out.println("Erro ao atualizar o produto" + erro.getMessage());

        }

        return false;
    }

    public Boolean deletar (int idProduto){
        try {
            String sql = "DELETE FROM produtos WHERE id_produto = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idProduto);

            Produto produtoRetornado = buscaPorId(idProduto);

            if (produtoRetornado != null){
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException erro){
            System.out.println("Erro ao deletar o produto" + erro.getMessage());
        }
        return false;

    }

    public Produto buscarPorId(Integer id) {
        return null;
    }

    public Boolean Cadastrar(Produto produto) {
        return false;
    }

    public Boolean Atualizar(Produto produto) {
        return false;
    }

    public Boolean Remover(Integer id) {
        return false;
    }


}
