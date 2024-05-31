
package com.api.games.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.springframework.web.multipart.MultipartFile;


public class UploadUtil {

    public static boolean uploadImg(MultipartFile imagem){
        boolean upload = false;
        
        if(!imagem.isEmpty()){
            String nomeArquivo = imagem.getOriginalFilename();
            try{
                // criando o diretório
                String uploadDir = "/home/diego/cursos/senac/modulo 3/projeto integrador/etapa7-8/games/src/main/resources/static/imgs/";
                File dir = new File(uploadDir);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                
                //salvar arquivo no diretório
                File arquivo = new File(dir.getAbsolutePath() + File.separator + nomeArquivo);
                BufferedOutputStream st = new BufferedOutputStream(new FileOutputStream(arquivo));
                
                st.write(imagem.getBytes());
                st.close();
                
                System.out.println("Diretório: " + dir.getAbsolutePath());
                System.out.println("Arquivo " + arquivo + "salvo com sucesso");
            }
            catch(Exception e){
                System.out.println("Falha no carregamento do arquivo " + nomeArquivo + "=>" + e.getMessage());
            }
        }
        else{
            System.out.println("Arquivo vazio!");
        }       
        
        return upload;
    }
}
