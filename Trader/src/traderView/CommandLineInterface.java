/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traderView;

/**
 *
 * @author elton
 */
public class CommandLineInterface {
    
    public CommandLineInterface(){
    
    }

    public void Welcome() {
        
        System.out.println("Welcome to the trader bot! Please select a type of transaction report:");
        System.out.println("1: Full report     2: Company's report     3: Depot report");
    }
    
    public void ChooseCompany(){
        
        System.out.println("Please choose a Company:");
        System.out.println("1: BIG-A        2: BIG-B        3: BIG-C");
        
    }
    
    public void ChooseDepot(){
        
        System.out.println("Please enter the deposit number from 1 to 50:");
        
    }
    
    public void WrongNumber(){
        
        System.out.println("Please enter a number according to instructions.");
        
    }
    
}
