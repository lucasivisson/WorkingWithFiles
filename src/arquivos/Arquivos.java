package arquivos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Arquivos {
    private File diretorioProva;
    private File diretorioGabarito;
    
    public Arquivos(){
        this.diretorioProva = new File("matérias\\provas");
        diretorioProva.mkdir();
        
        this.diretorioGabarito = new File("matérias\\gabaritos");
        diretorioGabarito.mkdir();
    }
    
    public void visualizarArquivos(File diretorio){
        try{
            File afile[] = diretorio.listFiles();
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
    
    public void criarGabaritoDeRespostas(String disciplinaEscolhida){
        Scanner teclado = new Scanner(System.in);
        try{
        File arquivo = new File(diretorioGabarito, disciplinaEscolhida + "Gabarito.txt");
        
        FileWriter escreverArquivo = new FileWriter(arquivo, true);
        BufferedWriter bw = new BufferedWriter(escreverArquivo);
        
        System.out.println("Crie o gabarito oficial da disciplina de " + disciplinaEscolhida );
        for(int i = 0; i < 10; i++) {
            System.out.println("Digite os V ou F:");
            String respostas = teclado.next();

            bw.write(respostas);
        }
            System.out.println("Gabarito gerado com sucesso!");
            System.out.println("O gabarito está localizado no endereço: " + arquivo.getAbsolutePath());
        }  catch (FileNotFoundException e){
            e.printStackTrace();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
