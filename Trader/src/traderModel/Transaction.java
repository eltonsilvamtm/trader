/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traderModel;

import enviroment.Depot;
import java.util.ArrayList;

/**
 *
 * @author elton
 */
public class Transaction{
    
   
    protected static ArrayList<Transaction> transactionsHistory = new ArrayList<Transaction>();
    private static int id = 0;
    private Depot buyer, seller;
    private int buyerCode, quantitySold, transactionId;
    private String depotSellerName, depotBuyerName;
    
    /**
     * first constructor that will take care of performing the transactions
     * @param buyer
     * @param seller
     * @param buyerCode 
     */
    public Transaction(Depot buyer, Depot seller, int buyerCode){
        
        this.buyer = buyer;
        this.seller = seller;
        this.buyerCode = buyerCode;
        
        purchase();
        
    }
    
    /**
     * constructor that will be responsible for registering the transactions
     * @param transactionId
     * @param depotSellerName
     * @param quantitySold
     * @param depotBuyerName 
     */
    public Transaction(int transactionId, String depotSellerName, int quantitySold, String depotBuyerName){
        
        this.transactionId = transactionId;
        this.quantitySold = quantitySold;
        this.depotSellerName = depotSellerName;
        this.depotBuyerName = depotBuyerName;
        
    }
    
    public int getId(){
        return this.transactionId;
    }
    
    public int getQuantitySold(){
        return this.quantitySold;
    }
    
    public String getDepotSellerName(){
        return this.depotSellerName;
    }
    
    public String getDepotBuyerName(){
        return this.depotBuyerName;
    }
    
    public Depot getBuyer(){
        return this.buyer;
    }
    
    public Depot getSeller(){
        return this.seller;
    }
    
    private int createId(){
        return this.id;
    }
    
    /**
     * return whether the seller has enough stock
     * @return isSotckAvailable
     */
    private boolean checkSellerStock(){
        
        boolean isSotckAvailable = false;
        
        if(this.seller.getInternalStock() > 3){
            return true;
        }
        
        return isSotckAvailable;
    }
    
    /**
     * return whether the buyer has enough money or not
     * @return isBalanceAvailable
     */
    private boolean checkBalance(){
        
        boolean isBalanceAvailable = false;
        int balance = this.buyer.getCashAllowance() - (this.seller.getCost() + this.seller.getDelivery());
        if(balance > 0){
            return true;
        }
        
        return isBalanceAvailable;
    }
    
    /**
     * return whether there is space available to buy a product
     * @return isStorageAvailable
     */
    private boolean checkBuyerStorage1(){

        return this.buyer.getExterternalStock1() < 40;
    }
    
    /**
     * return whether there is space available to buy a product
     * @return isStorageAvailable
     */
    private boolean checkBuyerStorage2(){
        
        return this.buyer.getExterternalStock2() < 40;
    }
    
    /**
     * example if the buyer A wants to buy product B then code is 1, if the buyer A wants to buy C then the code is 2
     */
    private void purchase(){
        
        if(this.buyerCode == 1){
            buyerCode1();
        }
        
        if(this.buyerCode == 2){
            buyerCode2();
        }
        
    }

    /**
     * WILL ONLY PROCEED WITH THE TRANSACTION IF ALL THE CRITERIA IS MET
     * enough product to sell;
     * enough money to pay;
     * enough storage to store;
     */
    private void buyerCode1() {
        
        if(checkSellerStock() && checkBalance() && checkBuyerStorage1()){
            
            payment();
            tradeExternal1(createId());
            
        }
    }

    /**
     * WILL ONLY PROCEED WITH THE TRANSACTION IF ALL THE CRITERIA IS MET
     * enough product to sell;
     * enough money to pay;
     * enough storage to store;
     */
    private void buyerCode2() {
        
        if(checkSellerStock() && checkBalance() && checkBuyerStorage2()){
            
            payment();
            tradeExternal2(createId());
            
        }
    }
    
    
    private void tradeExternal1(int transactionId) {
        
        int stockAvailable = seller.getInternalStock() - 3;
        int stock = stockAvailable + buyer.getExterternalStock1();
        
        if(stock > 40){ //the purchase should be necessary to fill the stock of a depot, if not then it will just proceed with the rtansaction
            
            int stockLeftOver = stock-40;
            seller.setInternalStock(stockLeftOver + 3);
            buyer.setExternalStock1(stock - stockLeftOver); //the stock of external product 1 will be 40
            quantitySold = stockAvailable - stockLeftOver; //how many items the seller sold, it will be used to store the transaction
            
        }else{ //seller sold all his stock and it reached its minimum value
            seller.setInternalStock(3);
            buyer.setExternalStock1(stock);
            quantitySold = stockAvailable; //seller has sold all his items and is on his minimum stock
        }
        
        transactionsHistory.add(new Transaction(transactionId, this.seller.getDepotName(), quantitySold, this.buyer.getDepotName()));
        Transaction.id++;
        
    }
    
    private void tradeExternal2(int transactionId) {
        
        int stockAvailable = seller.getInternalStock() - 3;
        int stock = stockAvailable + buyer.getExterternalStock2();
        
        if(stock > 40){ //the purchase should be necessary to fill the stock of a depot, if not then it will just proceed with the rtansaction
            
            int stockLeftOver = stock-40;
            seller.setInternalStock(stockLeftOver + 3);
            buyer.setExternalStock2(stock - stockLeftOver); //the stock of external product 1 will be 40
            quantitySold = stockAvailable - stockLeftOver; //how many items the seller sold, it will be used to store the transaction
            
        }else{ //seller sold all his stock and it reached its minimum value
            seller.setInternalStock(3);
            buyer.setExternalStock2(stock);
            quantitySold = stockAvailable; //seller has sold all his items and is on his minimum stock
        }
        
        transactionsHistory.add(new Transaction(transactionId, this.seller.getDepotName(), quantitySold, this.buyer.getDepotName()));
        
        Transaction.id++;
    }

    /**
     * performs the payment of a transaction
     */
    private void payment() {
        
        int price = this.seller.getCost() + this.seller.getDelivery();
        this.buyer.setCashAllowance(this.buyer.getCashAllowance() - price);
        this.seller.setCashAllowance(seller.getCashAllowance() + price);
        
    }
    
    @Override
    public String toString() {
        
        return "ID " + this.transactionId + ": " + this.depotBuyerName + " has bought " + this.quantitySold + " items from " + this.depotSellerName;

    }
    
}

