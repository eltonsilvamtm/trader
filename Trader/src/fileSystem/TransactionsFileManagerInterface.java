/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileSystem;

import java.util.ArrayList;
import traderModel.Transaction;

/**
 *
 * @author elton
 */
public interface TransactionsFileManagerInterface {
    
    /**
     * reads the file and load it in memory
     */
    public void readFile();
    
    /**
     * updates the file whether when an entree is added or removed
     * @param transaction 
     */
    public void updateFile(ArrayList<Transaction> transaction);
    
    /**
     * deletes an entree (line) of the file
     * @param transaction 
     */
    public void deleteTransaction(Transaction transaction);
    
}
