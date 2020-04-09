package arquivos;

import java.util.Scanner;
import java.io.*;

public class Main {
    
    public static void main(String[] args) {
        Scanner materiaIn = new Scanner(System.in);
        Scanner alunoIn = new Scanner(System.in);
        Scanner respostasIn = new Scanner(System.in);
        Scanner outroAluno = new Scanner(System.in);
        Scanner outraMateria = new Scanner(System.in);
        Scanner escolhaMenu = new Scanner(System.in);

        Integer novoAluno = null;
        Integer novaMateria = null;
        
        File diretorio = new File("matérias");
        diretorio.mkdir();
        
        File diretorioProva = new File("matérias\\provas");
        diretorioProva.mkdir();
        
        File diretorioGabarito = new File("matérias\\gabaritos");
        diretorioGabarito.mkdir();
        
        int escolha;
        do{
        System.out.println("1 - Fazer a prova da disciplina");
        System.out.println("2 - Gerar gabarito oficial da disciplina");
        System.out.println("3 - Lista de acertos por aluno em ordem alfabética");
        System.out.println("4 - Lista de acertos por aluno em ordem decrescente de notas com média geral.");
        System.out.println("5 - Sair");
        escolha = escolhaMenu.nextInt();
        
        switch(escolha){
            case 1 :
            try {
                do {
                    System.out.println("Digite o nome da matéria: ");
                    String materia = materiaIn.next();

                    File arquivo = new File(diretorioProva, materia + ".txt");

                    FileWriter escreverArquivo = new FileWriter(arquivo, true);
                    BufferedWriter bw = new BufferedWriter(escreverArquivo);

                    do {
                        System.out.println("Digite o nome do aluno: ");
                        String nome = alunoIn.nextLine();

                        for(int i = 0; i < 10; i++) {
                            System.out.println("Digite os V ou F:");
                            String respostas = respostasIn.next();

                            bw.write(respostas);
                        }
                        bw.write("  " + nome);

                        System.out.println("Deseja cadastrar as respostas de outro aluno?");
                        System.out.println("1: SIM / 2: NÃO");
                        novoAluno = outroAluno.nextInt();

                        bw.newLine();

                    } while (novoAluno == 1);

                    bw.close();
                    escreverArquivo.close();

                    System.out.println("Deseja cadastrar outra matéria?");
                    System.out.println("1: SIM / 2: NÃO");
                    novaMateria = outraMateria.nextInt();

                } while (novaMateria == 1);

            }  catch (FileNotFoundException e){
            e.printStackTrace();
            
            } catch (IOException e) {
            e.printStackTrace();
            }
                break;
                
            case 2 :
                System.out.println("Listas de todas as provas feitas pelos alunos.");
                Arquivos a = new Arquivos();
                a.visualizarArquivos(diretorioProva);
                
                System.out.println("Digite o nome de uma disciplina para gerar o gabarito oficial:");
                String disciplinaEscolhida = escolhaMenu.next();
                boolean resultadoDaComparacao = a.compararDisciplina(disciplinaEscolhida);
                
                if(resultadoDaComparacao == true){
                    a.criarGabaritoDeRespostas(disciplinaEscolhida);
                }else{
                    System.out.println("Disciplina não encontrada!");
                }
                break;
            
            default:
                System.out.println("Escolha não existente, tente novamente!");
            }
        }while(escolha != 5);
    } 
}

    
