import dao.ProdutoDAO;
import dao.RelatorioDAO;
import file.EscritaArquivo;
import menu.FuncionarioMenu;
import menu.ProducaoMenu;
import menu.ProdutoMenu;
import menu.SetorMenu;
import model.Produto;
import model.Relatorio;

import java.util.ArrayList;
import java.util.Scanner;

public class AppIndustria {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("===== Menu Principal =====");
            System.out.println("[1] Produção");
            System.out.println("[2] Funcionario");
            System.out.println("[3] Setor");
            System.out.println("[4] Produto");
            System.out.println("[5] Relatório");
            System.out.println("[0] Sair");
            System.out.print("Opção: ");
            Integer opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    ProducaoMenu.main(args);
                    break;
                case 2:
                    FuncionarioMenu.main(args);
                    break;
                case 3:
                    SetorMenu.main(args);
                    break;
                case 4:
                    ProdutoMenu.main(args);
                    break;
                case 5:
                    Relatorio relatorio = new Relatorio(new RelatorioDAO());
                    System.out.println(relatorio.gerarRelatorioGeral());
                    EscritaArquivo escritaArquivo = new EscritaArquivo();
                    escritaArquivo.salvarCSV(relatorio.gerarRelatorioGeral());
                    break;
                case 0:
                    System.out.println("Programa finalizado com sucesso.");
                    return;
                default:
                    System.out.println("Opção inválida!");

            }


//PRODUTO
//        ProdutoDAO produtoDAO = new ProdutoDAO();
//        ArrayList<Produto> produtos = produtoDAO.Listar();
//
//        for (Produto produto: produtos){
//            System.out.println(produto);
//        }
//
//        System.out.println(" + " .repeat(100));
//
//        Produto produto = produtoDAO.buscaPorId(5);
//        System.out.println(produto);
//
//        System.out.println(" + " .repeat(100));
//
//        Produto novoProduto = new Produto("CPU", "Carcaça");
//        Boolean foiCadastrado = produtoDAO.cadastrar(novoProduto);
//        System.out.println("Foi cadastrado ? " + foiCadastrado);
//
//        System.out.println(" + " .repeat(100));
//
//
//        model.Setor atualizarSetor = new model.Setor();
//        atualizarSetor.setIdSetor(6);
//        atualizarSetor.setNomeSetor("SETOR 6");
//        atualizarSetor.setResponsavel("Sandro Lopes");
//
//        Boolean foiAtualizado = setorDAO.atualizar(atualizarSetor);
//        System.out.println("Foi atualizado ? " + foiAtualizado);
//
//        System.out.println(" + " .repeat(100));
//
//
//        model.Setor deletarSetor = new model.Setor();
//        deletarSetor.setIdSetor(6);
//        deletarSetor.setNomeSetor("SETOR 6");
//        deletarSetor.setResponsavel("Sandro Lopes");
//
//        Boolean foiDeletado = setorDAO.deletar(6);
//        System.out.println("Foi deletado ? " + foiDeletado);
//
//        System.out.println(" + " .repeat(100));


//SETOR
//        dao.SetorDAO setorDAO = new dao.SetorDAO();
//
//        ArrayList<model.Setor> setores = setorDAO.Listar();
//
//        for (model.Setor setor: setores){
//            System.out.println(setor);
//        }
//
//
//        System.out.println(" + " .repeat(100));
//
//        model.Setor setor = setorDAO.buscaPorId(5);
//        System.out.println(setor);
//
//        System.out.println(" + " .repeat(100));
//
//        model.Setor novoSetor = new model.Setor();
//        novoSetor.setIdSetor(6);
//        novoSetor.setNomeSetor("SETOR 6");
//        novoSetor.setResponsavel("Sandro Lopes");
//
//
//        Boolean foiCadastrado = setorDAO.cadastrar(novoSetor);
//        System.out.println("Foi cadastrado ? " + foiCadastrado);
//
//        System.out.println(" + " .repeat(100));
//
//
//        model.Setor atualizarSetor = new model.Setor();
//        atualizarSetor.setIdSetor(6);
//        atualizarSetor.setNomeSetor("SETOR 6");
//        atualizarSetor.setResponsavel("Sandro Lopes");
//
//        Boolean foiAtualizado = setorDAO.atualizar(atualizarSetor);
//        System.out.println("Foi atualizado ? " + foiAtualizado);
//
//        System.out.println(" + " .repeat(100));
//
//
//        model.Setor deletarSetor = new model.Setor();
//        deletarSetor.setIdSetor(6);
//        deletarSetor.setNomeSetor("SETOR 6");
//        deletarSetor.setResponsavel("Sandro Lopes");
//
//        Boolean foiDeletado = setorDAO.deletar(6);
//        System.out.println("Foi deletado ? " + foiDeletado);
//
//        System.out.println(" + " .repeat(100));


        }

    }
}
