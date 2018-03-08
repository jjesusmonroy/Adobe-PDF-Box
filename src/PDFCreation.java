/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;

/**
 *
 * @author jjesusmonroy
 */
public class PDFCreation {
    
    public static void main(String [] args) throws IOException{
        /*PDDocument document = new PDDocument();
        
        for (int i=0;i<10; i++){
            PDPage blankPage = new PDPage();
            
            document.addPage(blankPage);
        }
        
        document.save("/home/jjesusmonroy/Documents/example.pdf");
        
        System.out.println("PDF created");
        
        document.close();*/
        String path = "/home/jjesusmonroy/Documents/example.pdf";
        //loadingFile(path);
        //removingPages(path, 3);
        documentProperties(path);
    }
    
    private static void loadingFile(String path){
        File file = new File(path);
        PDDocument document;
        try{
            document = PDDocument.load(file);
            System.out.println("PDF Loaded");
            
            document.addPage(new PDPage());
            
            document.save(path);
            
            document.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    private static void removingPages(String path,int pageToRemove){
        File file = new File(path);
        PDDocument document;
        
        try{
            document = PDDocument.load(file);
            int Pages = document.getNumberOfPages();
            System.out.println(Pages);
            if(pageToRemove<Pages)document.removePage(pageToRemove);
            
            document.save(path);
            
            document.close();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private static void documentProperties(String path){
        File file = new File(path);
        PDDocument document;
        
        try{
            document = PDDocument.load(file);
            PDDocumentInformation pdd = document.getDocumentInformation();
            pdd.setAuthor("jjesusmonroy");
            pdd.setCreator("Best Creator");
            pdd.setTitle("BEST PDF FILE IN THE WORLD");
            pdd.setSubject("Everything a world's pdf file have to containt");
            pdd.setKeywords("insert alot of keywords here please");
            Calendar date = new GregorianCalendar();
            date.set(2018, 3, 5);
            pdd.setCreationDate(date);
            date.set(2018,3,8);
            pdd.setModificationDate(date);
            
            document.save(path);
            System.out.println("propertes added succesfully");
            document.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        
             
    }
    
}
