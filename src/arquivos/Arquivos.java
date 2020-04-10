package arquivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

public class Arquivos {
    private File diretorioProva;
    private File diretorioGabarito;
    private File diretorio;
    private File diretorioBoletimAlfabetico;
    
    public Arquivos(){
        this.diretorio = new File("matérias");
        diretorio.mkdir();
        
        this.diretorioProva = new File("matérias\\provas");
        diretorioProva.mkdir();
        
        this.diretorioGabarito = new File("matérias\\gabaritos");
        diretorioGabarito.mkdir();
        
        this.diretorioBoletimAlfabetico = new File("matérias\\BoletimAlfabetico");
        diretorioBoletimAlfabetico.mkdir();
    }
    
    public void visualizarProvas(){
        try{
            File afile[] = diretorioProva.listFiles();
            for(int i=0; i<afile.length; i++){
                File arquivo = afile[i];
                System.out.println("Nome do arquivo: " + arquivo.getName());
            }
        } catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public void visualizarGabaritos(){
        try{
            File afile[] = diretorioGabarito.listFiles();
            for(int i=0; i<afile.length; i++){
                File arquivo = afile[i];
                System.out.println("Nome do arquivo: " + arquivo.getName());
            }
        } catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }
    
    public boolean compararDisciplina(String disciplinaEscolhida){
        boolean eIgual = false;
        try{
            File afile[] = diretorioProva.listFiles();
            for(int i=0; i<afile.length; i++){
                File arquivo = afile[i];
                if(arquivo.getName().equals(disciplinaEscolhida + ".txt")){
                    eIgual = true;
                    return eIgual;
                }
            }
        } catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return eIgual;
    }
    
    public boolean compararGabarito(String gabaritoEscolhido){
        boolean eIgual = false;
        try{
            File afile[] = diretorioGabarito.listFiles();
            for(int i=0; i<afile.length; i++){
                File arquivo = afile[i];
                if(arquivo.getName().equals(gabaritoEscolhido + ".txt")){
                    eIgual = true;
                    return eIgual;
                }
            }
        } catch(Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return eIgual;
    }
    
    public void criarRespostas() {
        Scanner materiaIn = new Scanner(System.in);
        Scanner alunoIn = new Scanner(System.in);
        Scanner respostasIn = new Scanner(System.in);
        Scanner outroAluno = new Scanner(System.in);
        Scanner outraMateria = new Scanner(System.in);

        Integer novoAluno = null;
        Integer novaMateria = null;
        
        Integer i = 0;

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

                    while(i < 10){
                        System.out.println("Digite os V ou F:");
                        String respostas = respostasIn.next();
                        if(respostas.equals("V") || respostas.equals("F") || respostas.equals("v") || respostas.equals("f")){
                            bw.write(respostas);
                            i++;
                        } else {
                            System.out.println("Resposta inválida, responda apenas V ou F");
                        }
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
    }
    
    public void criarGabaritoDeRespostas(String disciplinaEscolhida){
        try{
        Scanner teclado = new Scanner(System.in);
        File arquivo = new File(diretorioGabarito, disciplinaEscolhida + ".txt");
        
        FileWriter escreverArquivo = new FileWriter(arquivo, true);
        BufferedWriter bw = new BufferedWriter(escreverArquivo);
        
        System.out.println("Crie o gabarito oficial da disciplina de " + disciplinaEscolhida );
        int i = 0;
        while(i < 10){
            System.out.println("Digite os V ou F:");
            String respostas = teclado.next();
            if(respostas.equals("V") || respostas.equals("F") || respostas.equals("v") || respostas.equals("f")){
                bw.write(respostas);
                i++;
            } else {
                System.out.println("Resposta inválida, responda apenas V ou F");
            }
        }
            bw.close();
            escreverArquivo.close();
            
            System.out.println("Gabarito gerado com sucesso!");
            System.out.println("O gabarito está localizado no endereço: " + arquivo.getAbsolutePath());
        }  catch (FileNotFoundException e){
            System.out.println("Erro: " + e);
            e.printStackTrace();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void compararProvaGabarito(String gabaritoEscolhido){
        String[] letraGabarito = null;
        String[] letraProva = null;
        int contadorDeQuestao = 0;
        
        try{
            File arquivoGabarito = new File(diretorioGabarito, gabaritoEscolhido + ".txt");
            FileReader frGabarito = new FileReader(arquivoGabarito);
            BufferedReader brGabarito = new BufferedReader(frGabarito);
            String linhaGabarito = brGabarito.readLine();
            letraGabarito = linhaGabarito.split("");
            
            File arquivoProva = new File(diretorioProva, gabaritoEscolhido + ".txt");
            FileReader frProva = new FileReader(arquivoProva);
            BufferedReader brProva = new BufferedReader(frProva);
            String linhaProva = brProva.readLine();
            letraProva = linhaProva.split("");
            
            while( linhaProva != null ){
            contadorDeQuestao = 0;
            for(int i=0; i<letraGabarito.length; i++){
                if(letraGabarito[i].equals(letraProva[i])){
                    contadorDeQuestao++;
                }
            }
            linhaProva = brProva.readLine();
            System.out.println(contadorDeQuestao);
            }
            
            brGabarito.close();
            brProva.close();
            frGabarito.close();
            frProva.close();
        } catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado");
        } catch (IOException e){
            System.out.println("Erro na leitura do arquivo");
        }
        
    }
}
