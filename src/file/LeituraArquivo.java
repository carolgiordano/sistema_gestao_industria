package file;

import java.io.*;

public class LeituraArquivo {
    public static void main(String[] args) {

        String caminho = "C:\\Users\\senai\\Downloads\\Industria\\src\\file\\arquivo.csv";

        File arquivo = new File(caminho);

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            double soma = 0.0;
            String linha;
            br.readLine();

            while ((linha = br.readLine()) != null){
                String [] posicoes = linha.split(",");
                Integer id = Integer.parseInt(posicoes[0]);
                String nome = posicoes[1];
                String responsavel = posicoes[2];
                soma += id;

                System.out.println(id + nome + responsavel);
            }
            System.out.println("SOMA: " + soma);

        } catch (FileNotFoundException erro){
            System.out.println("Erro ao buscar o arquivo.");

        } catch (IOException erroExcecao){
            System.out.printf("Erro ao abrir o arquivo.");
        }




    }
}
