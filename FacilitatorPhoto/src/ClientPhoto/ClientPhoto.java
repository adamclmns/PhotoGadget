/*
 * For Summer Seminar 2013 Facilitator Profile Picture
 */
package ClientPhoto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Adam Clemons 
 * EAST Initiative - July 3rd, 2013
 */
public class ClientPhoto {
    //<editor-fold defaultstate="collapsed" desc="All varriables here">
    //<editor-fold defaultstate="collapsed" desc="Hardcoded Configuration Varriables">
    static String DataPath = "C:\\ClientPicProg\\Datapath\\";
    static String InitialImagePath = "C:\\ClientPicProg\\picturefolder\\";
    static String FinalImagePath = "C:\\ClientPicProg\\FinalPicFolder\\";
    static File myImageFolder = new File(InitialImagePath);
    //public FileInputStream FileReader = null;
   //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Input/Output Objects declared">
    static Scanner ConsoleReader = new Scanner(System.in);
    static Scanner inStream = null;
    static File myClientDataFile = new File(DataPath+"ClientData.csv");
    static File mySessionDataFile = new File(DataPath+"SessionData.csv");
    static File myImageFile;
    //</editor-fold>
       
    //<editor-fold defaultstate="collapsed" desc="Configuration Varraibles - Accessed Directly by ReadClientData() and ReadSessionData()">
    static List<String> ClientName = new ArrayList<String>();
    static List<String> ClientIDNumber = new ArrayList<String>();
    static List<String> SessionName = new ArrayList<String>();
    static List<String> SessionNumber = new ArrayList<String>();
    //</editor-fold>
    
    static List<String> ImageFilesFound = new ArrayList<String>();
    static String CurrentFacilitatorNumber;
    static String CurrentFacilitatorName;
    static String CurrentBarcodeScan;
   //</editor-fold>
   
    public static void main(String[] args) {
        //Runs Configuration from CSV files
        //Will add a cleanup/update function later
        System.out.println("Starting... ");
        ReadClientData();
        
        //Loops until you close the program... 
        while(true){
        System.out.println("...Ready!");
        //Grabs the barcode from console
        CurrentBarcodeScan = GetBarcodeNumberConsole();
        
        //Grabs current facilitator name and Number via barcode scanned
        CurrentFacilitatorName = ClientName.get(SearchClientIDNumber(CurrentBarcodeScan));
        System.out.println("Facilitator Name: "+CurrentFacilitatorName);
        CurrentFacilitatorNumber= ClientIDNumber.get(SearchClientIDNumber(CurrentBarcodeScan));
        System.out.println("Facilitator Number: "+CurrentFacilitatorNumber);
        
        ListFilesInFolder();
        MoveRenameImage();
        }
    }
    //Hold on, it's about to get bumpy...
    static void ReadClientData() {
        String FileLine;
        String Number;
        String Name;
        
        try{
           //Opening BufferedReader (inStream) to read from file 
           inStream = new Scanner(myClientDataFile);
           //Checking for next line before reading 
           while(inStream.hasNext()){
                FileLine = inStream.nextLine();
                //reading line from csv, splitting, and appendign to Array list
                String[] parts = FileLine.split(",");
                Number = parts[0];
                Name = parts[1];
                //Appending to Array List
                ClientName.add(Name);
                ClientIDNumber.add(Number);
           }
           
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                inStream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        //End Reading from Facilitator number Config sheet           
    }//End ReadClientData
    
    static void ReadSessionData() {
        String FileLine;
        String Number;
        String Name;
        try{
           //Opening BufferedReader (inStream) to read from file 
           inStream = new Scanner(mySessionDataFile);
           //Checking for next line before reading 
           while(inStream.hasNext()){
                FileLine = inStream.nextLine();
                //reading line from csv, splitting, and appendign to Array list
                String[] parts = FileLine.split(",");
                Number = parts[0];
                Name = parts[1];
                //Appending to Array List
                SessionName.add(Name);
                SessionNumber.add(Number);
           } 
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                inStream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        //End Reading from Session number Config sheet
    }//End ReadSessionData
    
    static void ListFilesInFolder(){
        List<String> FilesFound = new ArrayList<String>();
        File[] ListOfFiles = myImageFolder.listFiles();
        for(int i=0; i<ListOfFiles.length;i++){
            if(ListOfFiles[i].isFile()){
                FilesFound.add(ListOfFiles[i].getName());
            }
        }
        ImageFilesFound = FilesFound;
    }//End ListFilesInFolder
    
    static void MoveRenameImage(){
       for(int i=0;i<ImageFilesFound.size();i++){
        try{
            //iterates through all files detected by ListFilesInFolder()
            File datpic = new File(InitialImagePath + ImageFilesFound.get(i));
            if(datpic.renameTo(new File(FinalImagePath+CurrentFacilitatorNumber+CurrentFacilitatorName+"-take-"+i+".jpg"))){
            System.out.println("Image Moved and Renamed Successfully!");
            } else{
                System.out.println("Error Moving/Renaming");
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
        }//End For Loop
    }//End MoveRenameImage
    static String GetBarcodeNumberConsole(){
        String BarcodeValue = ConsoleReader.nextLine();
        return BarcodeValue;
    }//End GetBarcodeNumberConsole
        
    static int SearchClientIDNumber(String CurrentScan){
        int index=0;
        for(int i=1;i<ClientIDNumber.size();i++){
            int FacilitatorNumberAsInt = Integer.parseInt(ClientIDNumber.get(i));
            int CurrentScanAsInt = Integer.parseInt(CurrentScan);
            if(FacilitatorNumberAsInt==CurrentScanAsInt){
                index=i;
            }
        }
        return index;
    }
    
    //NOT IMPLEMENTED
    static void SetPathVariable(){
        //Prompt for Image Path if not default
        //Console Version
        System.out.println("Default image path at C:\\ClientPicProg\\picturefolder\\");
    }//End SetPathVariable
    static void GetCurrentDataValues(int ind){
        CurrentFacilitatorNumber=ClientIDNumber.get(ind);
        CurrentFacilitatorName=ClientName.get(ind);
    }
}
