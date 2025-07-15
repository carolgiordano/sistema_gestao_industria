package dao;

import config.ConexaoMySQL;
import model.Setor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SetorDAO {

    private Connection conn = ConexaoMySQL.getConnection();

    public ArrayList<Setor> Listar() {

        try {
            ArrayList<Setor> setores = new ArrayList<>();
            String sql = "SELECT * FROM setores;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id_setor");
                String nome = rs.getString("nome_setor");
                String responsavel = rs.getString("responsavel");

                setores.add(new Setor(id, nome, responsavel));


            }

            return setores;

        } catch (SQLException erro) {
            System.out.println("Deu ruim em listar os clientes." + erro.getMessage());
            return null;
        }

    }

    public Setor buscaPorId(Integer id) {
        try {
            String sql = "SELECT * FROM setores WHERE id_setor = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Integer idSetor = rs.getInt("id_setor");
                String nomeSetor = rs.getString("nome_setor");
                String responsavel = rs.getString("responsavel");

                Setor setor = new Setor(idSetor, nomeSetor, responsavel);
                return setor;
            }


        } catch (SQLException erro) {
            System.out.println("Erro ao buscar setor pelo ID" + erro);

        }

        return null;

    }

    public Boolean cadastrar(Setor setor) {
        try {
            String sql = "INSERT INTO setores (nome_setor, responsavel) VALUES( ? , ?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, setor.getNomeSetor());
            ps.setString(2, setor.getResponsavel());
            int qtsDeLinha = ps.executeUpdate();

            if (qtsDeLinha > 0){
                return true;
            }
            return false;

        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar setor" + erro.getMessage());


        }

        return false;
    }

    public Boolean atualizar (Setor setor){
        try {
            String sql = "UPDATE setores SET nome_setor = ?, responsavel = ? WHERE id_setor = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, setor.getNomeSetor());
            ps.setString(2, setor.getResponsavel());
            ps.setInt(3, setor.getIdSetor());
            int qtdeAtualização = ps.executeUpdate();

            if (qtdeAtualização > 0){
                return true;

            }return false;




        } catch (SQLException erro){
            System.out.println("Erro ao atualizar o setor" + erro.getMessage());

        }

        return false;
    }

    public Boolean deletar (int idSetor){
        try {
            String sql = "DELETE FROM setores WHERE id_setor = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idSetor);

            Setor setorRetornado = buscaPorId(idSetor);


            if (setorRetornado != null){
                ps.executeUpdate();
                return true;

            }

        } catch (SQLException erro){

            System.out.println("Erro ao deletar o setor" + erro.getMessage());
        }

        return false;

    }



    public Setor buscarPorId(Integer id) {
        return null;
    }

    public Boolean Cadastrar(Setor setor) {
        return false;
    }

    public Boolean Atualizar(Setor setor) {
        return false;
    }

    public Boolean Remover(Integer id) {
        return false;
    }


}
