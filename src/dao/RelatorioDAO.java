package dao;

import config.ConexaoMySQL;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAO {
    private static final Connection conn = ConexaoMySQL.getConnection();

    // 1. Listar funcionários por setor
    public List<Funcionario> listarFuncionariosPorSetor(int idSetor) {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios WHERE id_setor = ?; ";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idSetor);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setIdFuncionario(rs.getInt("id_funcionario"));
                f.setNome(rs.getString("nome"));
                lista.add(f);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar funcionários por setor: " + e.getMessage());
        }
        return lista;
    }

    // 2. Produções por data (data_producao é varchar)
    public List<Producao> listarProducaoPorData(String data) {
        List<Producao> lista = new ArrayList<>();
        String sql = "SELECT * FROM producaos WHERE data_producao = ? ;";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, data); // String direto
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Producao p = new Producao();
                p.setIdProducao(rs.getInt("id_producao"));
                p.setDataProducao(LocalDate.parse(rs.getString("data_producao"))); // usar String
                p.setQuantidade(rs.getInt("quantidade"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produção por data: " + e.getMessage());
        }
        return lista;
    }

    // 3. Produtos produzidos por funcionário
    public List<Produto> listarProdutosPorFuncionario(int idFuncionario) {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT DISTINCT p.* FROM producao pr " +
                "INNER JOIN produtos p ON pr.id_produtos = p.id_produtos " +
                "WHERE pr.id_funcionario = ? ;";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idFuncionario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto prod = new Produto();
                prod.setIdProduto(rs.getInt("id_produtos"));
                prod.setNomeProduto(rs.getString("nome_produto"));
                prod.setDescricao(rs.getString("descricao"));
                lista.add(prod);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos por funcionário: " + e.getMessage());
        }
        return lista;
    }

    // 4. Funcionários, setores e produções
    public List<String> listarFuncionariosSetoresProducoes() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT f.nome, s.nome_setor, p.quantidade, p.data_producao " +
                "FROM funcionarios f " +
                "INNER JOIN setores s ON f.id_setor = s.id_setor " +
                "INNER JOIN producao p ON f.id_funcionario = p.id_funcionario ; ";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String linha = rs.getString("nome") + " | " +
                        rs.getString("nome_setor") + " | " +
                        rs.getInt("quantidade") + " unidades em " +
                        rs.getString("data_producao");
                lista.add(linha);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar funcionários + setores + produções: " + e.getMessage());
        }
        return lista;
    }

    // 5. Detalhes da produção em data específica
    public List<String> listarDetalhesProducaoPorData(String data) {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT f.nome, s.nome_setor, p.quantidade " +
                "FROM producao p " +
                "INNER JOIN funcionarios f ON p.id_funcionario = f.id_funcionario " +
                "INNER JOIN setores s ON f.id_setor = s.id_setor " +
                "WHERE p.data_producao = ? ;";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, data);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String linha = "Funcionário: " + rs.getString("nome") +
                        " | Setor: " + rs.getString("nome_setor") +
                        " | Quantidade: " + rs.getInt("quantidade");
                lista.add(linha);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar detalhes da produção: " + e.getMessage());
        }
        return lista;
    }

//    private ArrayList<Relatorio> funcionarioPorSetor(int id_setor){
//
//        try {
//            ArrayList<Relatorio> relatorios = new ArrayList<>();
//            String sql = "SELECT * FROM funcionarios WHERE id_setor = ? ;";
//
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1,id_setor);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Integer id = rs.getInt("id_funcionario");
//                String nome = rs.getString("nome");
//                String cargo = rs.getString("cargo");
//                int idSetor = rs.getInt("id_setor");
//
//                Setor setorFunc = new Setor(idSetor);
//
//                relatorios.add(new Relatorio(id, nome, cargo, setorFunc));
//            }
//
//            return relatorios;
//
//        } catch (SQLException e) {
//            System.out.println("Erro de SQL" + e.getMessage());
//        }
//
//
//        return null;
//    }

}
