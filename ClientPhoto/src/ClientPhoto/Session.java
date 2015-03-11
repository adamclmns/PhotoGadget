/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientPhoto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author AdamClmns
 */
public class Session {
    
    String DataPath = "C:\\ClientPicProg\\Datapath\\";
    String InitialImagePath = "C:\\ClientPicProg\\picturefolder\\";
    String FinalImagePath = "C:\\ClientPicProg\\FinalPicFolder\\";

    File myImageFolder = new File(InitialImagePath);

    Scanner ConsoleReader = new Scanner(System.in);
    Scanner inStream = null;
    File myClientDataFile = new File(this.DataPath+"ClientData.csv");
    File mySessionDataFile = new File(this.DataPath+"SessionData.csv");
    File myImageFile;

    List<String> ClientName = new ArrayList<String>();
    List<String> ClientIDNumber = new ArrayList<String>();
    List<String> SessionName = new ArrayList<String>();
    List<String> SessionNumber = new ArrayList<String>();


    List<String> ImageFilesFound = new ArrayList<String>();
    String CurrentFacilitatorNumber;
    String CurrentFacilitatorName;
    String CurrentBarcodeScan;
    
    public void Session(){
    
    }
   
    public String getDataPath() {
        return DataPath;
    }

    public void setDataPath(String DataPath) {
        this.DataPath = DataPath;
    }

    public String getInitialImagePath() {
        return InitialImagePath;
    }

    public void setInitialImagePath(String InitialImagePath) {
        this.InitialImagePath = InitialImagePath;
    }

    public String getFinalImagePath() {
        return FinalImagePath;
    }

    public void setFinalImagePath(String FinalImagePath) {
        this.FinalImagePath = FinalImagePath;
    }

    public File getMyImageFolder() {
        return myImageFolder;
    }

    public void setMyImageFolder(File myImageFolder) {
        this.myImageFolder = myImageFolder;
    }

    public Scanner getConsoleReader() {
        return ConsoleReader;
    }

    public void setConsoleReader(Scanner ConsoleReader) {
        this.ConsoleReader = ConsoleReader;
    }

    public Scanner getInStream() {
        return inStream;
    }

    public void setInStream(Scanner inStream) {
        this.inStream = inStream;
    }

    public File getMyClientDataFile() {
        return myClientDataFile;
    }

    public void setMyClientDataFile(File myClientDataFile) {
        this.myClientDataFile = myClientDataFile;
    }

    public File getMySessionDataFile() {
        return mySessionDataFile;
    }

    public void setMySessionDataFile(File mySessionDataFile) {
        this.mySessionDataFile = mySessionDataFile;
    }

    public File getMyImageFile() {
        return myImageFile;
    }

    public void setMyImageFile(File myImageFile) {
        this.myImageFile = myImageFile;
    }

    public List<String> getClientName() {
        return ClientName;
    }

    public void setClientName(List<String> ClientName) {
        this.ClientName = ClientName;
    }

    public List<String> getClientIDNumber() {
        return ClientIDNumber;
    }

    public void setClientIDNumber(List<String> ClientIDNumber) {
        this.ClientIDNumber = ClientIDNumber;
    }

    public List<String> getSessionName() {
        return SessionName;
    }

    public void setSessionName(List<String> SessionName) {
        this.SessionName = SessionName;
    }

    public List<String> getSessionNumber() {
        return SessionNumber;
    }

    public void setSessionNumber(List<String> SessionNumber) {
        this.SessionNumber = SessionNumber;
    }

    public List<String> getImageFilesFound() {
        return ImageFilesFound;
    }

    public void setImageFilesFound(List<String> ImageFilesFound) {
        this.ImageFilesFound = ImageFilesFound;
    }

    public String getCurrentFacilitatorNumber() {
        return CurrentFacilitatorNumber;
    }

    public void setCurrentFacilitatorNumber(String CurrentFacilitatorNumber) {
        this.CurrentFacilitatorNumber = CurrentFacilitatorNumber;
    }

    public String getCurrentFacilitatorName() {
        return CurrentFacilitatorName;
    }

    public void setCurrentFacilitatorName(String CurrentFacilitatorName) {
        this.CurrentFacilitatorName = CurrentFacilitatorName;
    }

    public String getCurrentBarcodeScan() {
        return CurrentBarcodeScan;
    }

    public void setCurrentBarcodeScan(String CurrentBarcodeScan) {
        this.CurrentBarcodeScan = CurrentBarcodeScan;
    }
    
}
