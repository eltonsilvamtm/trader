/*
 *
 */
package enviroment;

/**
 *
 * @author elton
 */
public abstract class Depot {
 
    //a depot already starts with the minimum stock quantity required
    protected int cashAllowance = 50, internalStock = 15, externalStock1 = 3, externalStock2 = 3;
    protected int cost = 1, delivery = 1;
    protected Company companyName;
    protected String depotName;
    
    public int getCashAllowance(){
        return this.cashAllowance;
    }
    
    public int getInternalStock(){
        return this.internalStock;
    }
    
    public int getExterternalStock1(){
        return this.externalStock1;
    }
    
    public int getExterternalStock2(){
        return this.externalStock2;
    }
    
    public int getCost(){
        return this.cost;
    }
    
    public int getDelivery(){
        return this.delivery;
    }
    
    public String getCompanyName(){
        return this.companyName.getName();
    }
    
    public String getDepotName(){
        return this.depotName;
    }
    
    public void setInternalStock(int internalStock){
        
        if(internalStock < 15)internalStock = 15;
        else if(internalStock > 50)internalStock = 50;
        
        this.internalStock = internalStock;
    }
    
    public void setExternalStock1(int externalStock1){
        
        if(externalStock1 < 3)externalStock1 = 3;
        else if(externalStock1 > 40)externalStock1 = 40;
        
        this.externalStock1 = externalStock1;
    }
    
    public void setExternalStock2(int externalStock2){
        
        if(externalStock2 < 3)externalStock2 = 3;
        else if(externalStock2 > 40)externalStock2 = 40;
        
        this.externalStock2 = externalStock2;
    }
    
    public void setCashAllowance(int cashAllowence){
        
        if(cashAllowence <0) cashAllowence = 0;
        else if(cashAllowence < 50)cashAllowence = 50;
        else if(cashAllowence > 100)cashAllowence = 100;
        
        this.cashAllowance = cashAllowence;
    }
    
    public void setCost(int cost){
        
        if(cost < 1)cost = 1;
        else if(cost > 10)cost = 10;
        
        this.cost = cost;
    }
    
    public void setDelivery(int delivery){
        
        if(delivery < 1)delivery = 1;
        else if(delivery > 10)delivery = 10;
        
        this.delivery = delivery;
    }
    
    @Override
    public String toString() {
        
        return "Depot name:" + this.depotName + "\n"
                + "Cash Allowence:" + this.cashAllowance + "\n"
                + "Internal Stock:" + this.internalStock + "\n"
                + "External Stock 1: " + this.externalStock1 + "\n"
                + "External Stock 2: " + this.externalStock2 + "\n";
        
    }
    
    
}