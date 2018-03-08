/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 *
 * @author jjesusmonroy
 */
public class PDFCreation {
    
    public static void main(String [] args) throws IOException{
        PDDocument document = new PDDocument();
        
        document.save("/home/jjesusmonroy/Documents/example.pdf");
        
        System.out.println("PDF created");
        
        document.close();
    }
    
}
