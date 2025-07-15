package menu;

import dao.ProducaoDAO;
import model.Producao;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

public class ProducaoMenu {

    private static ProducaoDAO dao = new ProducaoDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n--- MENU PRODUCAO ---");
            System.out.println("[1] Listar produção");
            System.out.println("[2] Buscar produção por ID");
            System.out.println("[3] Cadastrar produção");
            System.out.println("[4] Atualizar produção");
            System.out.println("[5] Remover produção");
            System.out.println("[0] Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    listarProcucao();
                    break;
                case 2:
                    buscarPorId();
                    break;
                case 3:
                    cadastrarProducao();
                    break;
                case 4:
                    atualizarProducao();
                    break;
                case 5:
                    removerProducao();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void listarProcucao() {
        ArrayList<Producao> producaos = dao.Listar();
        if (producaos != null && !producaos.isEmpty()) {
            for (Producao producao : producaos) {
                System.out.println(producao);
            }
        } else {
            System.out.println("Nenhuma produção encontrada.");
        }
    }

    private static void buscarPorId() {
        System.out.print("Digite o ID da produção: ");
        int id = scanner.nextInt();
        Producao producao = dao.buscarPorId(id);
        if (producao != null) {
            System.out.println(producao);
        } else {
            System.out.println("model.Produção não encontrado.");
        }
    }

    private static void cadastrarProducao() {
        System.out.print("Nome do produto produzido: ");
        String nome = scanner.nextLine();
        System.out.print("Data da produção: ");
        LocalDate dataProducao = LocalDate.parse(scanner.nextLine());
        System.out.print("Qual a quantidade da produção: ");
        Integer quantidade = scanner.nextInt();


        Producao producao = new Producao(null, nome, null, null, dataProducao, quantidade);
        if (dao.cadastrar(producao)) {
            System.out.println("model.Producao cadastrada com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar producao do produto.");
        }
    }

    private static void atualizarProducao() {
        System.out.print("ID da producao a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
        Producao producao = dao.buscarPorId(id);
        if (producao == null) {
            System.out.println("model.Produção não encontrada.");
            return;
        }
        System.out.print("Nova produção: ");
        String nome = scanner.nextLine();
        System.out.print("Nova data da produção: ");
        LocalDate dataProducao = LocalDate.parse(scanner.next());
        System.out.print("Quantidade da produção: ");
        Integer quantidade = scanner.nextInt();
        producao.setNomeProduto(nome);
        producao.setDataProducao(dataProducao);
        producao.setQuantidade(quantidade);

        if (dao.atualizar(producao)) {
            System.out.println("model.Producao atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar producao.");
        }
    }

    private static void removerProducao() {
        System.out.print("ID da producao a remover: ");
        int id = scanner.nextInt();
        if (dao.Remover(id)) {
            System.out.println("model.Produção removido com sucesso!");
        } else {
            System.out.println("Erro ao remover produção.");
        }




    }

}
