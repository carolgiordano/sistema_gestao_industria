package menu;

import dao.FuncionarioDAO;
import model.Funcionario;

import java.util.Scanner;
import java.util.ArrayList;

public class FuncionarioMenu {
    private static FuncionarioDAO dao = new FuncionarioDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n--- MENU FUNCIONARIO ---");
            System.out.println("[1] Listar funcionarios");
            System.out.println("[2] Buscar funcionario por ID");
            System.out.println("[3] Cadastrar funcionario");
            System.out.println("[4] Atualizar funcionario");
            System.out.println("[5] Remover funcionario");
            System.out.println("[0] Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    listarFuncionarios();
                    break;
                case 2:
                    buscarPorId();
                    break;
                case 3:
                    cadastrarFuncionario();
                    break;
                case 4:
                    atualizarFuncionario();
                    break;
                case 5:
                    removerFuncionario();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void listarFuncionarios() {
        ArrayList<Funcionario> funcionarios = dao.Listar();
        if (funcionarios != null && !funcionarios.isEmpty()) {
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario);
            }
        } else {
            System.out.println("Nenhum funcionario encontrado.");
        }
    }

    private static void buscarPorId() {
        System.out.print("Digite o ID do funcionario: ");
        int id = scanner.nextInt();
        Funcionario funcionario = dao.buscarPorId(id);
        if (funcionario != null) {
            System.out.println(funcionario);
        } else {
            System.out.println("model.Funcionario não encontrado.");
        }
    }

    private static void cadastrarFuncionario() {
        System.out.print("Nome do funcionario: ");
        String nome = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();

        Funcionario funcionario = new Funcionario(null, nome, cargo, null);

        if (dao.cadastrar(funcionario)) {
            System.out.println("model.Funcionario cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar funcionario.");
        }
    }

    private static void atualizarFuncionario() {
        System.out.print("ID do funcionario a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
        Funcionario funcionario = dao.buscarPorId(id);
        if (funcionario == null) {
            System.out.println("model.Funcionario não encontrado.");
            return;
        }
        System.out.print("Novo nome do funcionario: ");
        String nome = scanner.nextLine();
        System.out.print("Nome do cargo: ");
        String cargo = scanner.nextLine();
        funcionario.setNome(nome);
        funcionario.setCargo(cargo);
        if (dao.atualizar(funcionario)) {
            System.out.println("model.Funcionario atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar o funcionario.");
        }
    }

    private static void removerFuncionario() {
        System.out.print("ID do funcionario a remover: ");
        int id = scanner.nextInt();
        if (dao.Remover(id)) {
            System.out.println("model.Funcionario removido com sucesso!");
        } else {
            System.out.println("Erro ao remover funcionario.");
        }




    }
}
