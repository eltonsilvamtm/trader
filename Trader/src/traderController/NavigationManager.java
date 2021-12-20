
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traderController;

import enviroment.Depot;
import traderModel.Database;
import traderView.CommandLineInterface;
import traderView.PrintReports;

/**
 * class to organize all the menu navigation structure of the application
 * @author elton
 */
public class NavigationManager {
    
    
    Database database = Database.getInstance();
    PrintReports printReport = new PrintReports();
    CommandLineInterface CLI = new CommandLineInterface();
    Validation validate = new Validation();
    private int optionchosen, companyNumber, depotNumber;
    
    public NavigationManager(){
        MainMenu();
    }

    public void MainMenu() {
       
       CLI.Welcome();
       
       optionchosen = validate.validateMainMenu();
        
       MainMenuNavigation(optionchosen);
       
    }
    
    private void MainMenuNavigation(int option) {
		
		switch(option) {
		  
			case 1:
				printReport.printFullReport(database.getTransactionsList());//display a full report with all transactions
                                MainMenu(); //returns to the initial menu
			case 2:
				CompanyReportMenu(); //user has to pick a company
			case 3:
				DepotReportMenu();//user picks a company to generate a depot report
		}
	
    }
    
    public void CompanyReportMenu(){
        
        CLI.ChooseCompany();
        
        optionchosen = validate.validateMainMenu();
        			
        CompanyReportNavigation(optionchosen);
        
    }
    
    private void CompanyReportNavigation(int option) {
		
	if(option == 1){
            printReport.printCompanyAReport(database.getDepotAList(), database.getTransactionsList());//displays all transactions for company A
            MainMenu(); //returns to the initial menu
        }
        
        if(option == 2){
            
            printReport.printCompanyBReport(database.getDepotBList(), database.getTransactionsList());//displays all transactions for company B
            MainMenu(); //returns to the initial menu
            
        }
        
        if(option == 3){
            
            printReport.printCompanyCReport(database.getDepotCList(), database.getTransactionsList());//displays all transactions for company C
            MainMenu(); //returns to the initial menu
            
        }
	
	
    }
    
    public void DepotReportMenu(){
        
        CLI.ChooseCompany();
        companyNumber = validate.validateMainMenu();
        
        CLI.ChooseDepot();
        depotNumber = validate.validateDepotNumber();
        
        Depot depot = database.getDepot(companyNumber, depotNumber);
        
        printReport.printDepotReport(depot, database.getTransactionsList());
        
        MainMenu(); //returns to the main menu
    }
    
    
    
    
}
