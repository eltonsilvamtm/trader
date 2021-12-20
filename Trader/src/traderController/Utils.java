/*
 * To concentrate some useful information such as 
 * constants and methods that will be ued in many places
 */
package traderController;

/**
 *
 * @author elton
 */
public class Utils {
    
    private static int minProductPrice = 1;
    private static int maxProductPrice = 10;
    private static int minProductDelivery = 1;
    private static int maxProductDelivery = 10;
    private static int minCashAllowence = 50, maxCashAllowence = 100;
    private static int minInternalStock = 15, maxInternalStock = 50;
    private static int minExternalStock = 3, maxExternalStock = 40;
    
    public static final String BIGA = "BIG-A";
    public static final String BIGB = "BIG-B";
    public static final String BIGC = "BIG-C";
    public static final String DEPA = "DEP-A";
    public static final String DEPB = "DEP-B";
    public static final String DEPC = "DEP-C";
    
    /**
     * @return random number ranging 1 to 10
     */
    public static int getProductPrice(){
        return (int) Math.floor(Math.random()*(maxProductPrice-minProductPrice+1)+ minProductPrice);
    }
    
    /**
     * @return random number ranging 1 to 10
     */
    public static int getProductDelivery(){
        return (int) Math.floor(Math.random()*(maxProductDelivery-minProductDelivery+1)+minProductDelivery);
    }
    
    /**
     * @return random number ranging 50 to 100
     */
    public static int getCashAllowence(){
        return (int) Math.floor(Math.random()*(maxCashAllowence-minCashAllowence+1)+minCashAllowence);
    }
    
     /**
     * @return random number ranging 15 to 50
     */
    public static int getInternalStock(){
        return (int) Math.floor(Math.random()*(maxInternalStock-minInternalStock+1)+minInternalStock);
    }
    
     /**
     * @return random number ranging 3 to 40
     */
    public static int getExternalStock(){
        return (int) Math.floor(Math.random()*(maxExternalStock-minExternalStock+1)+minExternalStock);
    }
    
}
