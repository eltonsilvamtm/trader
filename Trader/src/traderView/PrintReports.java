/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traderView;

import enviroment.Depot;
import java.util.ArrayList;
import traderController.Utils;
import traderModel.Transaction;

/**
 *
 * @author elton
 */
public class PrintReports {

    public PrintReports() {
        
    }
    
    public void printFullReport(ArrayList<Transaction> transactions){
        
        for(int i = 0; i < transactions.size(); i++){
            
                System.out.println(transactions.get(i).toString());
                
        }
    }

    public void printCompanyAReport(ArrayList<Depot> depotA, ArrayList<Transaction> transactions) {
        
        System.out.println("Please find below the company " + Utils.BIGA + " report: ");
        System.out.println("");
        for(int i = 0; i < depotA.size() ; i++){
            
            System.out.println(depotA.get(i).toString());
            
            for(int j = 0; j < transactions.size(); j++){
            
                if(transactions.get(j).getDepotBuyerName().equals(depotA.get(i).getDepotName())){
                System.out.println(transactions.get(j).toString());
                
                }
            }
            System.out.println("");
        }
        
        
    }

    public void printCompanyBReport(ArrayList<Depot> depotB, ArrayList<Transaction> transactions) {
        
        System.out.println("Please find below the company " + Utils.BIGB + " report: ");
        System.out.println("");
        
        for(int i = 0; i < depotB.size() ; i++){
            
            System.out.println(depotB.get(i).toString());
            
            for(int j = 0; j < transactions.size(); j++){
            
                if(transactions.get(j).getDepotBuyerName().equals(depotB.get(i).getDepotName())){
                System.out.println(transactions.get(j).toString());
                
                }
            }
        }
    }

    public void printCompanyCReport(ArrayList<Depot> depotC, ArrayList<Transaction> transactions) {
        
        System.out.println("Please find below the company " + Utils.BIGC + " report: ");
        System.out.println("");
        
        for(int i = 0; i < depotC.size() ; i++){
            
            System.out.println(depotC.get(i).toString());
            
            for(int j = 0; j < transactions.size(); j++){
            
                if(transactions.get(j).getDepotBuyerName().equals(depotC.get(i).getDepotName())){
                System.out.println(transactions.get(j).toString());
                }
            }
            System.out.println("");
        }
    }

    /**
     * will print all the transactions of a depot as well as its final stock situation
     * @param depot
     * @param transactions 
     */
    public void printDepotReport(Depot depot, ArrayList<Transaction> transactions) { 
        
       
        
        String depotName = depot.getDepotName();
        
        System.out.println("Report of the depot " + depotName + ":");

        System.out.println(depot.toString());

        for(int i = 0; i < transactions.size(); i++){
              if(transactions.get(i).getDepotBuyerName().equals(depotName)){
                System.out.println(transactions.get(i).toString());
                  
              }
        }
        System.out.println("");
    }
    
}
