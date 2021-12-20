/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import traderModel.Transaction;


/**
 *
 * @author elton
 */
public class TransactionsFileManager implements TransactionsFileManagerInterface{
    
    private File transactionsFile = new File("transactions.txt");
    private Transaction myTransaction;
    private ArrayList<Transaction> transactions = new ArrayList<>(); 
    
    private BufferedReader readDocument;
    private BufferedWriter writeDocument;
    
    
    public TransactionsFileManager(){
     
    }
    
    
    public ArrayList<Transaction> getTransactions(){
        return this.transactions;
    }
    
    @Override
    public void readFile() {
        
        String documentLine = "";
        String[] data;
        String companyName, depotName;
        int internalStock, externalStock1, externalStock2;
        
        try {
            readDocument = new BufferedReader(new FileReader(transactionsFile));
            documentLine = readDocument.readLine(); //reads the first line
                        
			while(documentLine != null) { //reads the whole file line by line
				
				data = documentLine.split(",");
                                
                                companyName = data[0];
                                depotName = data[1];
				internalStock = Integer.parseInt(data[2]); //parsing a string to an integer
                                externalStock1 = Integer.parseInt(data[3]); //parsing a string to an integer
                                externalStock2 = Integer.parseInt(data[4]); //parsing a string to an integer
				
			//	transactions.add(new Transaction(companyName, depotName, internalStock, externalStock1, externalStock2)); //storing the reader objects into an array 
				documentLine = readDocument.readLine(); //reads the rest of the file
			
			}
			
			readDocument.close();
                        
        } catch (IOException ex) {
            System.out.println("file not read properly");
        }
    }

    
    @Override
    public void updateFile(ArrayList<Transaction> transaction) {
		
        
		try {
		
                    writeDocument = new BufferedWriter(new FileWriter(transactionsFile, true));
			
			//loop through the array in order to write all the depots into the file
			for(int i = 0; i < transaction.size(); i++) {
				
				//writes all the depot information to the file
				writeDocument.write(transaction.get(i).getId() + "," + transaction.get(i).getDepotBuyerName() + "," + 
                                        transaction.get(i).getDepotSellerName() + "," + transaction.get(i).getQuantitySold());
                                        
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
    public void deleteTransaction(Transaction transaction) {
        
        for(int i=0;i<transactions.size();i++){
            if(transactions.get(i) == transaction){
               transactions.remove(i);
               updateFile(transactions);
            }
        }
        
    }
    
    
    
    public void createFile() {

                    try {
			
                    boolean doesFileExist = transactionsFile.createNewFile();
                    if(doesFileExist == false) {
                        
                      transactionsFile.delete();
                      transactionsFile.createNewFile(); 

                    }
		} catch (IOException e) {	
                    System.out.println("failed deleting the file");
                  }

    }
    
}
