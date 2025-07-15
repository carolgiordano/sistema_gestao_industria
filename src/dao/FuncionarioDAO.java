package dao;

import config.ConexaoMySQL;
import model.Funcionario;
import model.Setor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuncionarioDAO {

    private Connection conn = ConexaoMySQL.getConnection();
    SetorDAO setorDAO = new SetorDAO();

    public ArrayList<Funcionario> Listar() {

        try {
            ArrayList<Funcionario> funcionarios = new ArrayList<>();
            String sql = "SELECT * FROM funcionarios;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id_funcionario");
                String nomeFuncionario = rs.getString("nome");
                String cargo = rs.getString("cargo");
                int setorFuncionario = rs.getInt("id_setor");

                Setor setorDoFuncionario = new Setor(setorFuncionario);

                funcionarios.add(new Funcionario(id, nomeFuncionario, cargo, setorDoFuncionario));

            }
            return funcionarios;

        } catch (SQLException erro) {
            System.out.println("Deu ruim em listar os funcionarios." + erro.getMessage());
            return null;
        }

    }

    public Funcionario buscaPorId(Integer id) {
        try {
            String sql = "SELECT * FROM funcionarios WHERE id_funcionario = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Integer idFuncionario = rs.getInt("id_funcionario");
                String nomeFuncionario = rs.getString("nome_funcionario");
                String cargo = rs.getString("cargo");
                int setorFuncionario = rs.getInt("id_setor");

                Setor setorDoFuncionario = setorDAO.buscaPorId(setorFuncionario);

                Funcionario funcionario = new Funcionario(idFuncionario, nomeFuncionario, cargo, setorDoFuncionario);
                return funcionario;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao buscar setor pelo ID" + erro);
        }
        return null;
    }

    public Boolean cadastrar(Funcionario funcionario) {
        try {
            String sql = "INSERT INTO funcionarios (nome, cargo, id_setor) VALUES( ? , ?, ? );";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCargo());
            ps.setInt(3,funcionario.getSetor().getIdSetor());


            int qtsDeLinha = ps.executeUpdate();

            if (qtsDeLinha > 0){
                return true;
            }
            return false;

        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar o funcionario" + erro.getMessage());
        }
        return false;
    }

    public Boolean atualizar (Funcionario funcionario){
        try {
            String sql = "UPDATE funcionarios SET nome = ?, cargo = ? WHERE id_setor = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCargo());
            ps.setInt(3,funcionario.getSetor().getIdSetor());
            int qtdeAtualização = ps.executeUpdate();

            if (qtdeAtualização > 0){
                return true;

            }return false;

        } catch (SQLException erro){
            System.out.println("Erro ao atualizar o funcionario" + erro.getMessage());
        }
        return false;
    }

    public Boolean deletar (int idFuncionario){
        try {
            String sql = "DELETE FROM funcionarios WHERE id_funcionario = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idFuncionario);

            Setor funcionarioRetornado = buscaPorId(idFuncionario).getSetor();


            if (funcionarioRetornado != null){
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException erro){

            System.out.println("Erro ao deletar o funcionario" + erro.getMessage());
        }
        return false;

    }



    public Funcionario buscarPorId(Integer id) {
        return null;
    }

    public Boolean Cadastrar(Funcionario funcionario) {
        return false;
    }

    public Boolean Atualizar(Funcionario funcionario) {
        return false;
    }

    public Boolean Remover(Integer id) {
        return false;
    }


}
