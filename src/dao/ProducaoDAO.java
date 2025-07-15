package dao;

import config.ConexaoMySQL;
import model.Funcionario;
import model.Producao;
import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProducaoDAO {

    private Connection conn = ConexaoMySQL.getConnection();
    ProdutoDAO produtoDAO = new ProdutoDAO();
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    ProducaoDAO producaoDAO = new ProducaoDAO();


    public ArrayList<Producao> Listar() {

        try {
            ArrayList<Producao> producao = new ArrayList<>();
            String sql = "SELECT * FROM producao;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id_producao");
                String nomeProduto = rs.getString("nome_produto");
                int idProduto = rs.getInt("id_produto");
                int idFuncionario = rs.getInt("id_funcionario");
                LocalDate data = LocalDate.parse(rs.getString("data_producao"));
                int quantidade = rs.getInt("quantidade");

                Produto producaoDoProduto = produtoDAO.buscaPorId(idProduto);
                Funcionario funcionario = funcionarioDAO.buscaPorId(idFuncionario);

                producao.add(new Producao(id, nomeProduto, producaoDoProduto, funcionario, data, quantidade));

            }
            return producao;

        } catch (SQLException erro) {
            System.out.println("Deu ruim em listar a producao." + erro.getMessage());
            return null;
        }
    }

    public Producao buscaPorId(Integer id) {
        try {
            String sql = "SELECT * FROM producao WHERE id_producao = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Integer idProducao = rs.getInt("id_producao");
                String nomeProduto = rs.getString("nome_produto");
                int idProduto = rs.getInt("id_produto");
                int idFuncionario = rs.getInt("id_funcionario");
                LocalDate data = LocalDate.parse(rs.getString("data_producao"));
                int quantidade = rs.getInt("quantidade");

                Produto producaoDoProduto = produtoDAO.buscaPorId(idProduto);
                Funcionario funcionario = funcionarioDAO.buscaPorId(idFuncionario);

                Producao producao = (new Producao(idProducao, nomeProduto, producaoDoProduto, funcionario, data, quantidade));
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao buscar a producao pelo ID" + erro);
        }
        return null;
    }

    public Boolean cadastrar(Producao producao) {
        try {
            String sql = "INSERT INTO producao (nome_produto, id_produto, id_funcionario, data_producao, quantidade ) VALUES( ? , ?, ? , ? , ? );";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, producao.getNomeProduto());
            ps.setInt(2, producao.getProduto().getIdProduto());
            ps.setInt(3, producao.getFuncionario().getIdFuncionario());
            ps.setString(4, producao.getDataProducao().toString());
            ps.setInt(5, producao.getQuantidade());

            int qtsDeLinha = ps.executeUpdate();

            if (qtsDeLinha > 0){
                return true;
            }
            return false;

        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar a produção" + erro.getMessage());
        }
        return false;
    }

    public Boolean atualizar (Producao producao){
        try {
            String sql = "UPDATE producao SET nome_produto = ?, id_funcionario = ? WHERE id_producao = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, producao.getNomeProduto());
            ps.setInt(2, producao.getFuncionario().getIdFuncionario());
            ps.setInt(3, producao.getProduto().getIdProduto());

            int qtdeAtualização = ps.executeUpdate();

            if (qtdeAtualização > 0){
                return true;

            }return false;

        } catch (SQLException erro){
            System.out.println("Erro ao atualizar a produção" + erro.getMessage());
        }
        return false;
    }

    public Boolean deletar (int idProducao){
        try {
            String sql = "DELETE FROM producao WHERE id_producao = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idProducao);

            Producao producaoRetornado = buscaPorId(idProducao);


            if (producaoRetornado != null){
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException erro){

            System.out.println("Erro ao deletar a producao" + erro.getMessage());
        }
        return false;

    }



    public Producao buscarPorId(Integer id) {
        return null;
    }

    public Boolean Cadastrar(Producao producao) {
        return false;
    }

    public Boolean Atualizar(Producao producao) {
        return false;
    }

    public Boolean Remover(Integer id) {
        return false;
    }


}
