/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enviroment;

/**
 *
 * @author elton
 */
public class DepotC extends Depot implements DepotFactoryInterface{
    
     public DepotC(Company companyName, String depotName, int cashAllowance, int internalStock, int externalStock1, int externalStock2, int cost, int delivery){
        
        this.companyName = companyName;
        this.cashAllowance = cashAllowance;
        this.depotName = depotName;
        this.internalStock = internalStock;
        this.externalStock1 = externalStock1;
        this.externalStock2 = externalStock2;
        this.cost = cost;
        this.delivery = delivery;
        
    }

    @Override
    public Depot createDepot() {
        return new DepotC(companyName, depotName, cashAllowance, internalStock, externalStock1, externalStock2, cost, delivery);
    }
    
}
