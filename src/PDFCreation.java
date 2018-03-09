/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionJavaScript;
import org.apache.pdfbox.text.PDFTextStripper;

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
        //String path = "/home/jjesusmonroy/Documents/example.pdf";
        String imgPath = "/home/jjesusmonroy/Documents/tec-png.png";
        //loadingFile(path);
        //removingPages(path, 3);
        //documentProperties(path);
        //insertingContent(path, 4);
        //readingTextFromPDF(path);
        //insertingImage(path, 6, imgPath);
        //encryptingPDF(path, "1234");
        //String path = "/home/jjesusmonroy/Documents/anotherpdf.pdf";
        //creatingPDF(path);
        //addingJS(path);
        //addingPage(path);
        //addingPage(path);
        //addingPage(path);
        //addingPage(path);
        //insertingContent(path, 3);
        //addingJS(path);
        
        String path = "/home/jjesusmonroy/Documents/otherpdf.pdf";
        //creatingPDF(path);
        //addingPage(path);
        //insertingContent(path, 0);
        //addingJS(path);
        addingPage(path);
        splittingPDF(path);
    }
    
    private static void creatingPDF(String path){
        PDDocument document = new PDDocument();
        
        try{document.save(path);
        System.out.println("PDFCreated");
        document.close();}catch(IOException e){}
        
    }
    
    private static void addingPage(String path){
        File file = new File(path);
        try{
            PDDocument document = PDDocument.load(file);
            PDPage blankPage = new PDPage();
            document.addPage(blankPage);
            document.save(path);
            System.out.println("1 page added");
            document.close();
        }catch(IOException e){}
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
        }      
    }
    
    private static void insertingContent(String path, int pageToModified){
        //This method only works adding 1 line
        File file = new File(path);
        PDDocument document;
        
        try{document = PDDocument.load(file);
        
        PDPage page = document.getPage(pageToModified);
            try (PDPageContentStream contentStream = new 
        PDPageContentStream(document,page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.COURIER, 14);
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(25, 500);
                contentStream.showText("Addindg text to our pdf file at 25,500");
                contentStream.newLine();  // Adding a new line like \n
                contentStream.showText("1 more line because why not");
                contentStream.endText();
                System.out.println("Information added");
            }
        
        document.save(path);
        document.close();
        
        }catch(IOException e){
        }
    }
    
    private static void readingTextFromPDF(String path){
        File file = new File(path);
        try(PDDocument document = PDDocument.load(file)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text=pdfStripper.getText(document);
            System.out.println(text);
        }catch(IOException e){
        }
    }
    private static void insertingImage(String path,int page,String imgPath){
        File file = new File(path);
        try(PDDocument document = PDDocument.load(file)) {
        PDPage pge = document.getPage(page);
        PDImageXObject pdImage = PDImageXObject.createFromFile(imgPath,document);
        
        PDPageContentStream contentStream = new PDPageContentStream(document, pge);
        contentStream.drawImage(pdImage, 70,250);
        contentStream.close();
        System.out.println("Image inserted");
        document.save(path);
        }catch(IOException e){}
    }
    
    private static void encryptingPDF(String path, String password){
        File file = new File(path);
        try(PDDocument document = PDDocument.load(file)) {
        AccessPermission ap = new AccessPermission();
        
        StandardProtectionPolicy spp = new StandardProtectionPolicy(password,password,ap);
        
        spp.setEncryptionKeyLength(128);
        
        spp.setPermissions(ap);
        
        document.protect(spp);
        
        System.out.println("Document encrypted");
        
        document.save(path);
        
        }catch(IOException e){}        
    }
    
    private static void addingJS(String path){
        File file = new File(path);
        try(PDDocument document = PDDocument.load(file)) {
        String js = "app.alert( {cMsg: 'this is an example', nIcon: 3,"
         + " nType: 0, cTitle: 'PDFBox Javascript example’} );";
        PDActionJavaScript pda = new PDActionJavaScript(js);
        
        document.getDocumentCatalog().setOpenAction(pda);
        
        document.save(path);
        System.out.println("js added succesfully");
        
}
        catch(IOException e){
        }        
    }
    private static void splittingPDF(String path){
        File file = new File(path);
        try(PDDocument document = PDDocument.load(file)) {
            Splitter splitter = new Splitter();
            List<PDDocument> pages = splitter.split(document);
            Iterator<PDDocument> iterator = pages.listIterator();

            int i = 1;
            while(iterator.hasNext()){
                PDDocument pd = iterator.next();
                pd.save("/home/jjesusmonroy/Documents/otherpage"+ i++ +".pdf");
            }
                System.out.println("Multiple PDF's created");
        }catch(IOException e){
        }
    }
    
}
