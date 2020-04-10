package arquivos;

import java.util.Scanner;
import java.io.*;

public class Main {
    
    public static void main(String[] args) {
        Scanner escolhaMenu = new Scanner(System.in);
        
        File diretorio = new File("matérias");
        diretorio.mkdir();
        
        File diretorioProva = new File("matérias\\provas");
        diretorioProva.mkdir();
        
        File diretorioGabarito = new File("matérias\\gabaritos");
        diretorioGabarito.mkdir();
        
        Arquivos a = new Arquivos();
        
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

                a.criarRespostas();
                break;
                
            case 2 :
                System.out.println("Listas de todas as provas feitas pelos alunos.");
                a.visualizarProvas();
                
                System.out.println("Digite o nome de uma disciplina para gerar o gabarito oficial:");
                String disciplinaEscolhida = escolhaMenu.next();
                boolean resultadoDaComparacao = a.compararDisciplina(disciplinaEscolhida);
                
                if(resultadoDaComparacao == true){
                    a.criarGabaritoDeRespostas(disciplinaEscolhida);
                }else{
                    System.out.println("Disciplina não encontrada!");
                }
                break;
            
            case 3:
                System.out.println("Listas de todas os gabaritos feitos.");
                a.visualizarGabaritos();
                
                System.out.println("Digite o nome do gabarito para gerar o boletim da disciplina:");
                String gabaritoEscolhido = escolhaMenu.next();
                boolean resultadoDaComp = a.compararGabarito(gabaritoEscolhido);
                
                if(resultadoDaComp == true){
                    
                } else {
                    System.out.println("Impossível gerar boletim, gabarito não encontrado!");
                }           
                
                break;
            
            default:
                System.out.println("Escolha não existente, tente novamente!");
            }
        }while(escolha != 5);
    } 
}

    
