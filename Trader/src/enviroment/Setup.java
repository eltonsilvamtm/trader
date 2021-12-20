/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enviroment;

import java.util.ArrayList;
import traderController.NavigationManager;
import traderController.Utils;
import traderModel.Database;
import traderModel.TradingBot;


/**
 * This class will be in charge of creating and storing all objects in memory
 * It will create:
 * 3 Companies;
 * 50 Depots of each Company
 * @author elton
 */
public class Setup {
    
    private int depotsAmount = 50;
    
    private ArrayList<Depot> depotA = new ArrayList<>();
    private ArrayList<Depot> depotB = new ArrayList<>();
    private ArrayList<Depot> depotC = new ArrayList<>();
    private ArrayList<Company> companies = new ArrayList<>();
    
    private Depot myDepot;
    
    private Database database = Database.getInstance();
    
    public Setup(){
        CreateEnviroment();
    }

    private void CreateEnviroment() {
        
        Company companyA = new Company("BIG-A");
        Company companyB = new Company("BIG-B");
        Company companyC = new Company("BIG-C");
        
        companies.add(companyA);
        companies.add(companyB);
        companies.add(companyC);
        
        CreateDepots(companyA, companyB, companyC);
    }

    /**
     * method responsible for creating 50 depots for each company
     * all the depots will have a DEP-A(x) description
     * @param companyA
     * @param companyB
     * @param companyC 
     */
    private void CreateDepots(Company companyA, Company companyB, Company companyC) {
        
        for(int i = 1 ; i <= depotsAmount ; i++){
            String depotName = Utils.DEPA + i;
            myDepot = new DepotA(companyA, depotName, Utils.getCashAllowence(), 
                    Utils.getInternalStock(),Utils.getExternalStock(),
                    Utils.getExternalStock(), Utils.getProductPrice(), Utils.getProductDelivery());
            depotA.add(myDepot);
            
        }
        
        for(int i = 1 ; i <= depotsAmount ; i++){
            String depotName = Utils.DEPB + i;
            myDepot = new DepotB(companyB, depotName, Utils.getCashAllowence(), 
                    Utils.getInternalStock(),Utils.getExternalStock(), 
                    Utils.getExternalStock(), Utils.getProductPrice(), Utils.getProductDelivery());
            depotB.add(myDepot);
            
        }
        
        for(int i = 1 ; i <= depotsAmount ; i++){
            String depotName = Utils.DEPC + i;
            myDepot = new DepotC(companyC, depotName, Utils.getCashAllowence(), 
                    Utils.getInternalStock(),Utils.getExternalStock(),
                    Utils.getExternalStock(), Utils.getProductPrice(), Utils.getProductDelivery());
            depotC.add(myDepot);
            
        }
        
        database.setDepotAList(depotA); //Send the depots created to the database
        database.setDepotBList(depotB);
        database.setDepotCList(depotC);
        database.setCompanies(companies);
        
        TradingBot tradingBot = new TradingBot(); //starts the trading process
        
    }
    
}
