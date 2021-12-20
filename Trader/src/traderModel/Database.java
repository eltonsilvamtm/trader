/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traderModel;

import enviroment.Company;
import enviroment.Depot;
import fileSystem.DepotFileManager;
import fileSystem.TransactionsFileManager;
import java.util.ArrayList;
import traderController.Utils;

/**
 *  class to store all the information across the whole system.
 *  it will store all the transactions and all the depots information.
 * this class will be singleton once the system needs only one database instance
 * it will also be the main point of communication between the file system and the data
 * @author elton
 */
public class Database {
    
    public static Database database;
    
    private static ArrayList<Depot> depotA = new ArrayList<>();
    private static ArrayList<Depot> depotB = new ArrayList<>();
    private static ArrayList<Depot> depotC = new ArrayList<>();
    
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    
    private static ArrayList<Company> companies = new ArrayList<>();
    
    private DepotFileManager DepotsFile = new DepotFileManager();
    private TransactionsFileManager TransactionsFile = new TransactionsFileManager();
    private Depot depot;
    
    private Database(){
        
    }
    
    public static synchronized Database getInstance(){
        
        if(database == null){
           database = new Database();
        }
        
        return database;
        
    }
    
    public ArrayList<Depot> getDepotAList(){
        
        return depotA;
    }
    
    public ArrayList<Depot> getDepotBList(){
        return depotB;
    }
    
    public ArrayList<Depot> getDepotCList(){
        return depotC;
    }
    
    public ArrayList<Transaction> getTransactionsList(){
        return transactions;
    }
    
    public Depot getDepot(int companyCode, int depotCode){
        
        
        String depotName;
        
        if(companyCode == 1){
            
            depotName = Utils.DEPA + depotCode;
            
            for(int i = 0 ; i < depotA.size() ; i ++){
                if(depotA.get(i).getDepotName().equals(depotName)){
                    System.out.println(depotA.get(i).toString());
                    return depotA.get(i);  
                }
            }
            
        }
        
        if(companyCode == 2){
            
            depotName = Utils.DEPB + depotCode;
            
            for(int i = 0 ; i < depotB.size() ; i ++){
                if(depotB.get(i).getDepotName().equals(depotName)){
                   depot = depotB.get(i); 
                  return depot;  
                }
            }
            
        }
        
        if(companyCode == 3){
            
            depotName = Utils.DEPC + depotCode;
            
            for(int i = 0 ; i < depotC.size() ; i ++){
                if(depotC.get(i).getDepotName().equals(depotName)){
                    depot = depotC.get(i);
                    return depot;  
                }
            }
            
        }
        
        return depot;
    }
    
    public void setDepotAList(ArrayList<Depot> depotA){
        Database.depotA.clear();
        Database.depotA.addAll(depotA);
    }
    
    public void setDepotBList(ArrayList<Depot> depotB){
        Database.depotB.clear();
        Database.depotB.addAll(depotB);
    }
    
    public void setDepotCList(ArrayList<Depot> depotC){
        Database.depotC.clear();
        Database.depotC.addAll(depotC);
    }
    
    public void setTransactionsList(ArrayList<Transaction> transactions){
        
        Database.transactions.addAll(transactions);
    }
    
    public void setCompanies(ArrayList<Company> companies) {
        Database.companies.addAll(companies);
    }
    
    public void WriteFiles(){
        
        DepotsFile.createFile();
        DepotsFile.updateFile(depotA);
        DepotsFile.updateFile(depotB);
        DepotsFile.updateFile(depotC);
        
        TransactionsFile.updateFile(transactions);
    }

    
    

    
    
    
    
}
