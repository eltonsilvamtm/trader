/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileSystem;

import enviroment.Depot;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author elton
 */
public class DepotFileManager implements DepotFileManagerInterface{
   
    private File depotsFile = new File("depots.txt");
    private Depot myDepot;
    private ArrayList<Depot> depots = new ArrayList<>(); 
    private BufferedReader readDocument;
    private BufferedWriter writeDocument;
    
    
    public DepotFileManager(){
       
    }
    
    
    public ArrayList<Depot> getDepots(){
        return this.depots;
    }
    
    @Override
    public void readFile() {
        
        String documentLine = "";
        String[] data;
        String companyName, depotName;
        int internalStock, externalStock1, externalStock2;
        
        try {
            readDocument = new BufferedReader(new FileReader(depotsFile));
            documentLine = readDocument.readLine(); //reads the first line
                        
			while(documentLine != null) { //reads the whole file line by line
				
				data = documentLine.split(",");
                                
                                companyName = data[0];
                                depotName = data[1];
				internalStock = Integer.parseInt(data[2]); //parsing a string to an integer
                                externalStock1 = Integer.parseInt(data[3]); //parsing a string to an integer
                                externalStock2 = Integer.parseInt(data[4]); //parsing a string to an integer
				
				depots.add(myDepot(companyName, depotName, internalStock, externalStock1, externalStock2)); //storing the reader objects into an array 
				documentLine = readDocument.readLine(); //reads the rest of the file
			
			}
			
			readDocument.close();
                        
        } catch (IOException ex) {
            System.out.println("file not read properly");
        }
    }

    
    @Override
    public void updateFile(ArrayList<Depot> depot) {
        
        //boolean isFileClear = false;
			
		try {
		
                    writeDocument = new BufferedWriter(new FileWriter(depotsFile, true));
			
			//loop through the array in order to write all the depots into the file
			for(int i = 0; i < depot.size(); i++) {
				
				//writes all the depot information to the file
				writeDocument.write(depot.get(i).getCompanyName() + "," + depot.get(i).getDepotName() 
                                        + "," + depot.get(i).getInternalStock() + "," + depot.get(i).getExterternalStock1()
                                        + "," + depot.get(i).getExterternalStock2());
				//jumps to the next line
				writeDocument.newLine();
			
			}
			
			writeDocument.flush();
			writeDocument.close();
	
		} catch (IOException e) {
		    System.out.println("cannot write to the file");;
                  }

    }

    
    @Override
    public void deleteDepot(Depot depot) {
        
        for(int i=0;i<depots.size();i++){
            if(depots.get(i) == depot){
               depots.remove(i);
               updateFile(depots);
            }
        }
        
    }
    
    
    
    public void createFile() {

                    try {
			
                    boolean doesFileExist = depotsFile.createNewFile();
                    
                    if(doesFileExist == false) {
                        
                      depotsFile.delete();
                      depotsFile.createNewFile(); 

                    }
		} catch (IOException e) {	
                    System.out.println("failed deleting the file");
                  }

    }
    
    
    private Depot myDepot(String companyName, String depotName, int internalStock, int externalStock1, int externalStock2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
