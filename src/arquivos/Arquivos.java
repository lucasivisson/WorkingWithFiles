package arquivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Arquivos {
    private File diretorioProva;
    private File diretorioGabarito;
    private File diretorio;
    private File diretorioBoletimAlfabetico;
    private File diretorioBoletimDecrescente;
    
    public Arquivos(){
        this.diretorio = new File("matérias");
        diretorio.mkdir();
        
        this.diretorioProva = new File("matérias\\provas");
        diretorioProva.mkdir();
        
        this.diretorioGabarito = new File("matérias\\gabaritos");
        diretorioGabarito.mkdir();
        
        this.diretorioBoletimAlfabetico = new File("matérias\\BoletimAlfabetico");
        diretorioBoletimAlfabetico.mkdir();
        
        this.diretorioBoletimDecrescente = new File("matérias\\BoletimDecrescente");
        diretorioBoletimDecrescente.mkdir();
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

                    i = 0;
                    
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
    
    public void compararProvaGabaritoAlfabetico(String gabaritoEscolhido){
        ArrayList<String> nomes = new ArrayList<String>();
        int contadorDeV = 0;
        int contadorDeF = 0;
        
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

            File arquivo = new File(diretorioBoletimAlfabetico, gabaritoEscolhido + ".txt");
            FileWriter escreverArquivo = new FileWriter(arquivo, true);
            BufferedWriter bw = new BufferedWriter(escreverArquivo);

            while( linhaProva != null ) {
                contadorDeQuestao = 0;

                String[] dados = linhaProva.split("  ");

                for(int i = 0; i < letraGabarito.length; i++) {
                    letraProva = linhaProva.split("");
                    if(letraGabarito[i].equals(letraProva[i])){
                        contadorDeQuestao++;
                    }
                    if (letraProva[i].equals("v") || letraProva[i].equals("V")) {
                        contadorDeV++;
                    } else if (letraProva[i].equals("f") || letraProva[i].equals("F")) {
                        contadorDeF++;
                    }
                }

                if (contadorDeV == 10 || contadorDeF == 10) {
                    contadorDeQuestao = 0;
                }

                contadorDeF = 0;
                contadorDeV = 0;
                linhaProva = brProva.readLine();

                Integer.toString(contadorDeQuestao);

                nomes.add("Nome do Aluno: " + dados[1] + " / " + "Nota: " + contadorDeQuestao);
            }

            Collections.sort(nomes);
            for(int i = 0; i < nomes.size(); i++) {
                bw.write(nomes.get(i));
                bw.newLine();
            }

            brGabarito.close();
            brProva.close();
            frGabarito.close();
            frProva.close();
            bw.close();

        } catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado");
        } catch (IOException e){
            System.out.println("Erro na leitura do arquivo");
        }
    }
    public void compararProvaGabaritoDecrescente(String gabaritoEscolhido){
        ArrayList<String> notas = new ArrayList<String>();
        ArrayList<String> notasDez = new ArrayList<String>();

        String[] letraGabarito = null;
        String[] letraProva = null;
        int contadorDeQuestao = 0;

        Integer contadorDeV = 0;
        Integer contadorDeF = 0;

        Double somaDeNotas = 0.0;
        Double media = 0.0;
        Double quantidadeDeNotas = 0.0;

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

            File arquivo = new File(diretorioBoletimDecrescente, gabaritoEscolhido + ".txt");
            FileWriter escreverArquivo = new FileWriter(arquivo, true);
            BufferedWriter bw = new BufferedWriter(escreverArquivo);

            while( linhaProva != null ) {
                contadorDeQuestao = 0;

                String[] dados = linhaProva.split("  ");

                for (int i = 0; i < letraGabarito.length; i++) {
                    letraProva = linhaProva.split("");
                    if (letraGabarito[i].equals(letraProva[i])) {
                        contadorDeQuestao++;
                    }

                    if (letraProva[i].equals("v") || letraProva[i].equals("V")) {
                        contadorDeV++;
                    } else if (letraProva[i].equals("f") || letraProva[i].equals("F")) {
                        contadorDeF++;
                    }
                }

                if (contadorDeV == 10 || contadorDeF == 10) {
                    contadorDeQuestao = 0;
                }

                contadorDeF = 0;
                contadorDeV = 0;

                linhaProva = brProva.readLine();

                Integer.toString(contadorDeQuestao);

                if(Integer.toString(contadorDeQuestao).equals("10")) {
                    notasDez.add("Notas: " + contadorDeQuestao + " / " + "Nome do Aluno: " + dados[1]);
                } else {
                    notas.add("Notas: " + contadorDeQuestao + " / " + "Nome do Aluno: " + dados[1]);
                }

                somaDeNotas += contadorDeQuestao;
                quantidadeDeNotas++;
            }

            Collections.sort(notas);
            Collections.reverse(notas);

            for(int i = 0; i < notasDez.size(); i++) {
                bw.write(notasDez.get(i));
                bw.newLine();
            }
            for(int i = 0; i < notas.size(); i++) {
                bw.write(notas.get(i));
                bw.newLine();
            }

            media = somaDeNotas / quantidadeDeNotas;
            bw.write("Média da Turma: " + media);
            bw.newLine();

            brGabarito.close();
            brProva.close();
            frGabarito.close();
            frProva.close();
            bw.close();

        } catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado");
        } catch (IOException e){
            System.out.println("Erro na leitura do arquivo");
        }
    }
    
    public void mostrarBoletimOrdemAlfabetica(String gabaritoEscolhido){
        try{
        File arquivoGabarito = new File(diretorioBoletimAlfabetico, gabaritoEscolhido + ".txt");
        FileReader frGabarito = new FileReader(arquivoGabarito);
        BufferedReader brGabarito = new BufferedReader(frGabarito);
        String linha = brGabarito.readLine();
        
        while(linha != null){
            System.out.println(linha);
            linha = brGabarito.readLine();
        }
        
        } catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado");
        } catch (IOException e){
            System.out.println("Erro na leitura do arquivo");
        }
    }
    public void mostrarBoletimOrdemDecrescente(String gabaritoEscolhido){
        try{
        File arquivoGabarito = new File(diretorioBoletimDecrescente, gabaritoEscolhido + ".txt");
        FileReader frGabarito = new FileReader(arquivoGabarito);
        BufferedReader brGabarito = new BufferedReader(frGabarito);
        String linha = brGabarito.readLine();
        
        while(linha != null){
            System.out.println(linha);
            linha = brGabarito.readLine();
        }
        
        } catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado");
        } catch (IOException e){
            System.out.println("Erro na leitura do arquivo");
        }
    }
}
