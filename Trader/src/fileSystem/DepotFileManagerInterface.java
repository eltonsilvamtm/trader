/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileSystem;

import enviroment.Depot;
import java.util.ArrayList;

/**
 *
 * @author elton
 */
public interface DepotFileManagerInterface {
    
    /**
     * reads the file and load it in memory
     */
    public void readFile();
    
    /**
     * updates the file whether when an entree is added or removed
     * @param depot 
     */
    public void updateFile(ArrayList<Depot> depot);
    
    /**
     * deletes an entree (line) of the file
     * @param depot 
     */
    public void deleteDepot(Depot depot);
    
}
