/*
 * For Summer Seminar 2013 Facilitator Profile Picture
 */
package ClientPhoto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Adam Clemons 
 * EAST Initiative - July 3rd, 2013
 */
public class ClientPhoto {
    Session session = new Session();
    void main(String[] args) {
        //Runs Configuration from CSV files
        //Will add a cleanup/update function later
        System.out.println("Starting... ");
        ReadClientData();
        
        //Loops until you close the program... 
        while(true){
        System.out.println("...Ready!");
        //Grabs the barcode from console
        session.CurrentBarcodeScan = GetBarcodeNumberConsole();
        
        //Grabs current facilitator name and Number via barcode scanned
        session.CurrentFacilitatorName = session.ClientName.get(SearchClientIDNumber(session.CurrentBarcodeScan));
        System.out.println("Facilitator Name: "+session.CurrentFacilitatorName);
        session.CurrentFacilitatorNumber= session.ClientIDNumber.get(SearchClientIDNumber(session.CurrentBarcodeScan));
        System.out.println("Facilitator Number: "+session.CurrentFacilitatorNumber);
        
        ListFilesInFolder();
        MoveRenameImage();
        }
    }
    //Hold on, it's about to get bumpy...
    void ReadClientData() {
        String FileLine;
        String Number;
        String Name;
        
        try{
           //Opening BufferedReader (inStream) to read from file 
           session.inStream = new Scanner(session.myClientDataFile);
           //Checking for next line before reading 
           while(session.inStream.hasNext()){
                FileLine = session.inStream.nextLine();
                //reading line from csv, splitting, and appendign to Array list
                String[] parts = FileLine.split(",");
                Number = parts[0];
                Name = parts[1];
                //Appending to Array List
                session.ClientName.add(Name);
                session.ClientIDNumber.add(Number);
           }
           
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                session.inStream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        //End Reading from Facilitator number Config sheet           
    }//End ReadClientData
    
    void ReadSessionData() {
        String FileLine;
        String Number;
        String Name;
        try{
           //Opening BufferedReader (inStream) to read from file 
           session.inStream = new Scanner(session.mySessionDataFile);
           //Checking for next line before reading 
           while(session.inStream.hasNext()){
                FileLine = session.inStream.nextLine();
                //reading line from csv, splitting, and appendign to Array list
                String[] parts = FileLine.split(",");
                Number = parts[0];
                Name = parts[1];
                //Appending to Array List
                session.SessionName.add(Name);
                session.SessionNumber.add(Number);
           } 
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                session.inStream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        //End Reading from Session number Config sheet
    }//End ReadSessionData
    
    void ListFilesInFolder(){
        List<String> FilesFound = new ArrayList<String>();
        File[] ListOfFiles = session.myImageFolder.listFiles();
        for (File ListOfFile : ListOfFiles) {
            if (ListOfFile.isFile()) {
                FilesFound.add(ListOfFile.getName());
            }
        }
        session.ImageFilesFound = FilesFound;
    }//End ListFilesInFolder
    
    void SaveSession(Session target, String filename) throws FileNotFoundException, IOException{
        FileOutputStream saveFile = new FileOutputStream(filename);
        ObjectOutputStream save = new ObjectOutputStream(saveFile);
        save.writeObject(target);
        save.close();
    }
    
    Session LoadSession(Session target, String filename) throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream loadFile = new FileInputStream(filename);
        ObjectInputStream load = new ObjectInputStream(loadFile);
        target = (Session) load.readObject();
        load.close();
        return target;
    }
    
    void MoveRenameImage(){
       for(int i=0;i<session.ImageFilesFound.size();i++){
        try{
            //iterates through all files detected by ListFilesInFolder()
            File datpic = new File(session.InitialImagePath + session.ImageFilesFound.get(i));
            if(datpic.renameTo(new File(session.FinalImagePath+session.CurrentFacilitatorNumber+session.CurrentFacilitatorName+"-take-"+i+".jpg"))){
            System.out.println("Image Moved and Renamed Successfully!");
            } else{
                System.out.println("Error Moving/Renaming");
            }
            
        } catch(Exception e){
            e.printStackTrace();
        }
        }//End For Loop
    }//End MoveRenameImage
    String GetBarcodeNumberConsole(){
        String BarcodeValue = session.ConsoleReader.nextLine();
        return BarcodeValue;
    }//End GetBarcodeNumberConsole
        
    int SearchClientIDNumber(String CurrentScan){
        int index=0;
        for(int i=1;i<session.ClientIDNumber.size();i++){
            int FacilitatorNumberAsInt = Integer.parseInt(session.ClientIDNumber.get(i));
            int CurrentScanAsInt = Integer.parseInt(CurrentScan);
            if(FacilitatorNumberAsInt==CurrentScanAsInt){
                index=i;
            }
        }
        return index;
    }
    
    void GetCurrentDataValues(int ind){
        session.CurrentFacilitatorNumber=session.ClientIDNumber.get(ind);
        session.CurrentFacilitatorName=session.ClientName.get(ind);
    }
}
