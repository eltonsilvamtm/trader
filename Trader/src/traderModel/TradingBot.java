/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traderModel;

import enviroment.Depot;
import java.util.ArrayList;
import traderController.NavigationManager;
import traderController.Utils;

/**
 * this class will be responsible for checking things that 
 * involve the decision making processes to perform the transactions
 * @author elton
 */
public class TradingBot {
    
    Database database = Database.getInstance(); //get database instance
    
    private ArrayList<Depot> depotA = new ArrayList<>();
    private ArrayList<Depot> depotB = new ArrayList<>();
    private ArrayList<Depot> depotC = new ArrayList<>();
    private ArrayList<Depot> swap = new ArrayList<>();
    
    private ArrayList<Depot> buyerSorted = new ArrayList<>();
    private ArrayList<Depot> seller1Sorted = new ArrayList<>();
    private ArrayList<Depot> seller2Sorted = new ArrayList<>();
        
        
    private ArrayList<Transaction> transactionsHistory = new ArrayList<>();
    
    private int buyForStock1 = 1, buyForStock2 = 2; //these are the buying codes (which will tell what product is gonna be bought) 
    private static int counter = 1; 
 
    /**
     *
     */
    public TradingBot(){
        
        LoadData(); //load the data from the database to this class
        
       RunBotManager(); //responsible for control the trading waves
        
    }
    
    private void LoadData(){
        
        this.depotA.addAll(database.getDepotAList());
        this.depotB.addAll(database.getDepotBList());
        this.depotC.addAll(database.getDepotCList());
        
    }
    
    /**
     * Bubble sort algorithm to rearrange the array according to trading system
     * @param depot
     * @return sorted array
     */
    private ArrayList<Depot> sortDepotLowExternalSotck1(ArrayList<Depot> depot){

       int MaxReference, MinReference;
		
	for(int i = 0; i < depot.size() ; i++) { // loop through the array one by one to check its neighbour
			
            for(int j = i + 1; j < depot.size(); j++) { //loop through the array to get the next depot
				
		MaxReference = depot.get(i).getExterternalStock1(); //saves the position '0' to compare
		MinReference = depot.get(j).getExterternalStock1(); //saves the position '1' to compare
				 
                    if(MaxReference > MinReference) { //if A is bigger than B both objects need to get swapped
					
                        swap.add(depot.get(i)); //saves the record on the temporary array
                        depot.set(i, depot.get(j)); //moves B where A was
                        depot.set(j, swap.get(0)); //moves A where B was
			swap.clear(); // clears the temporary array
					
                    }
            }
	}
        
        return depot;
    }
    
    /**
     * Bubble sort algorithm to rearrange the array according to trading system
     * @param depot
     * @return sorted array
     */
    private ArrayList<Depot> sortDepotLowExternalSotck2(ArrayList<Depot> depot){
        
        int MaxReference, MinReference;
		
	for(int i = 0; i < depot.size() ; i++) { // loop through the array one by one to check its neighbour
			
            for(int j = i + 1; j < depot.size(); j++) { //loop through the array to get the next depot
				
		MaxReference = depot.get(i).getExterternalStock2(); //saves the position 'i' to compare
		MinReference = depot.get(j).getExterternalStock2(); //saves the position 'j' to compare
				 
                    if(MaxReference > MinReference) { //if A is bigger than B both objects need to get swapped
					
                        swap.add(depot.get(i)); //saves the record on the temporary array
                        depot.set(i, depot.get(j)); //moves B where A was
                        depot.set(j, swap.get(0)); //moves A where B was
			swap.clear(); // clears the temporary array
					
                    }
            }
	}
        return depot;
    }
    
    /**
     * bubble sort algorithm to sort companies with the best prices
     * @param depot
     * @return sorted array
     */
    private ArrayList<Depot> sortCheapestPriceWithDelivery(ArrayList<Depot> depot){
        
        int MaxReference, MinReference;
		
	for(int i = 0; i < depot.size() ; i++) { // loop through the array one by one to check its neighbour
			
            for(int j = i + 1; j < depot.size(); j++) { //loop through the array to get the next depot
				
		MaxReference = depot.get(i).getCost() + depot.get(i).getDelivery(); //saves the position '0' to compare
		MinReference = depot.get(j).getCost() + depot.get(j).getDelivery(); //saves the position '1' to compare
				 
                    if(MaxReference > MinReference) { //if A is bigger than B both objects need to get swapped
					
                        swap.add(depot.get(i)); //saves the record on the temporary array
                        depot.set(i, depot.get(j)); //moves B where A was
                        depot.set(j, swap.get(0)); //moves A where B was
			swap.clear(); // clears the temporary array
					
                    }
            }
	}
        return depot;
    }
    
    /**
     * engine that performs all the transactions
     * @param depotBuyer
     * @param depotSeller1
     * @param depotSeller2 
     */
    private void RunBot(ArrayList<Depot> depotBuyer, ArrayList<Depot> depotSeller1, ArrayList<Depot> depotSeller2){
        
        buyerSorted.clear(); //clean the arrays from previous transactions
        seller1Sorted.clear();
        
        buyerSorted.addAll(sortDepotLowExternalSotck1(depotBuyer)); //sorts all depots according to its lowest stock of non-native products
        seller1Sorted.addAll(sortCheapestPriceWithDelivery(depotSeller1)); //sort by cheapest price + delivery
        
        for(int i=0 ; i < depotBuyer.size(); i++){ //trades A&B, B&A, C&A
        
            //starts a new transaction with code 1 (meaning that buyer will buy external stock 1)
        Transaction trade1 = new Transaction( buyerSorted.get(i), seller1Sorted.get(i), buyForStock1 ); 
        buyerSorted.set(i, trade1.getBuyer()); //returns the depot after performing the trade
        seller1Sorted.set(i, trade1.getSeller()); //returns the depot after performing the trade
        
        }
        
        returnArrays(buyerSorted, seller1Sorted); //update oficial arrays with the new changes
        
        
        depotBuyer.clear();
        depotBuyer.addAll(buyerSorted); //returning array with the changes from the first loop
        buyerSorted.clear();
        seller1Sorted.clear();
        
        
        buyerSorted.addAll(sortDepotLowExternalSotck2(depotBuyer)); //sorting the same buyer but for the second non-native product
        seller2Sorted.addAll(sortCheapestPriceWithDelivery(depotSeller2)); //sort by cheapest price + delivery
        
        for(int i = 0; i < depotBuyer.size() ; i++){ //trades A&C, B&C, C&B
        
            //starts a new transaction with code 2 (meaning that buyer will buy external stock 2)
        Transaction trade2 = new Transaction( buyerSorted.get(i), seller2Sorted.get(i), buyForStock2 ); 
        buyerSorted.set(i, trade2.getBuyer()); //returns the depot after performing the trade
        seller2Sorted.set(i, trade2.getSeller()); //returns the depot after performing the trade
        
        }
        
        returnArrays(buyerSorted, seller2Sorted); //update oficial arrays with the new changes
        
        seller2Sorted.clear();
        
    }

    /**
     * updates the arrays that suffered alterations during the transaction process
     * @param depotBuyer
     * @param depotSeller
     */
    private void returnArrays(ArrayList<Depot> depotBuyer, ArrayList<Depot> depotSeller) {

        String companyNameBuyer = depotBuyer.get(0).getCompanyName(); //retrieving the first name on the array to check where they belong
        String companyNameSeller = depotSeller.get(0).getCompanyName();
        
        if(companyNameBuyer.equals(Utils.BIGA) && companyNameSeller.equals(Utils.BIGB)){ //if company A is buying and company B is selling
            
            UpdateDepotA(depotBuyer);
            UpdateDepotB(depotSeller);
        
        }
        
        if(companyNameBuyer.equals(Utils.BIGA) && companyNameSeller.equals(Utils.BIGC)){ //if company A is buying and company C is selling
            
            UpdateDepotA(depotBuyer);
            UpdateDepotC(depotSeller);
        
        }
        
        if(companyNameBuyer.equals(Utils.BIGB) && companyNameSeller.equals(Utils.BIGC)){ //if company B is buying and company C is selling
            
            UpdateDepotB(depotBuyer);
            UpdateDepotC(depotSeller);

        }
        
        if(companyNameBuyer.equals(Utils.BIGB) && companyNameSeller.equals(Utils.BIGA)){ //if company B is buying and company C is selling
            
            UpdateDepotB(depotBuyer);
            UpdateDepotA(depotSeller);
        
        }
        
        if(companyNameBuyer.equals(Utils.BIGC) && companyNameSeller.equals(Utils.BIGA)){ //if company C is buying and company A is selling
            
            UpdateDepotC(depotBuyer);
            UpdateDepotA(depotSeller);
        
        }
        
        if(companyNameBuyer.equals(Utils.BIGC) && companyNameSeller.equals(Utils.BIGB)){ //if company C is buying and company B is selling
            
            UpdateDepotC(depotBuyer);
            UpdateDepotB(depotSeller);
        
        }

    }

    private void UpdateDatabase() {
        
        //SAVE THE depot ARRAYS ON THE DATABASE
        database.setDepotAList(depotA);
        database.setDepotBList(depotB);
        database.setDepotCList(depotC);
        
        //save the transactions history on the database
        database.setTransactionsList(Transaction.transactionsHistory);
        
        //writes the txt files with all the information
        database.WriteFiles();
        
    }

    private void DisplayMenu() {
        
        UpdateDatabase();
        
        NavigationManager navigationManager = new NavigationManager(); //prompt the first menu to the use after performing all the transactions on all the depots
    
    }

    private void RunBotManager() {

        while(counter < 4){
          if(counter == 1){
              
              counter++; //counter keeps track of which wave the system is at
              
              RunBot(depotA, depotB, depotC); //starts the trading process (first wave, A trades with B and C)
              
          } 
          if(counter == 2){ //run second wave of transactions
              
              counter++;
              
              RunBot(depotB, depotC, depotA); //second wave of the trading, B will trade with C and A
              
          }

          if(counter == 3){ //run third and final wave of transactions
              
              counter++;
              
              RunBot(depotC, depotB, depotA); //third and final wave of trades, C trades with B and A
              
          }
        }
        
        DisplayMenu();
        
    }
    
    private void UpdateDepotA(ArrayList<Depot> depot){
        this.depotA.clear(); //copies the full array after the trading to the global variable
        this.depotA.addAll(depot);
    }
    
    private void UpdateDepotB(ArrayList<Depot> depot){
        this.depotB.clear(); //copies the full array after the trading to the global variable
        this.depotB.addAll(depot);
    }
    
    private void UpdateDepotC(ArrayList<Depot> depot){
        this.depotC.clear(); //copies the full array after the trading to the global variable
        this.depotC.addAll(depot);
    }
    
}


