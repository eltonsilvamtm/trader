package traderController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;



/**
 * this class takes care of the entire validation section of this program
 * it checks all inputs if they are empty, or if there is non matching characters on the inputs
 * @author elton
 *
 */
public class Validation{
	
	
	public String inputReader() {
		
                boolean validate = false;
                String input = "";
                
		BufferedReader InputReader = new BufferedReader(new InputStreamReader(System.in)); //reads the keyboard
		do {
		try {
			input = InputReader.readLine().trim(); //eliminate unnecessary spaces
                        validate = !input.isEmpty();
		} catch (IOException e) {
                    System.out.println("not able to read line");
		}
		}while(validate == false); //run this loop while the input is empty
		          
		return input;
	}
	
	
	public int validateMainMenu() {
		
		boolean validate = false;
		String choice;
		int number = 0;
		
		do{ //it forces to run the loop the first time
			
			choice = inputReader(); //looks for empty or white spaces entries
			validate = choice.matches("^([1-3])$"); //must enter a number that has only one digit
			                             
			if(validate == false) { //if the user entered the right format then it checks if it corresponds to one of the actions
                            
                            System.out.println("please enter a number according instructions.");
                            
                        }else{
                            
                            number = Integer.parseInt(choice); //parsing string to integer
                            
                            if(number <1 || number > 3) { //must be smaller than 3 to be validated
				
                                validate = false;
                                System.out.println("please enter a number according instructions.");
                                
                            }
			}
		
		}while(validate == false); //runs this loop until the user enters a correct format 
			
		return number;
	}
	
	public int validateDepotNumber() {
		
		boolean validate = false;
		String choice;
		int number = 0;
		
		do { //it forces to run the loop the first time
			
			choice = inputReader(); //checks for empty or white spaces entries
			validate = choice.matches("^[0-5]?[0-9]$"); //must match a two digit typing format
			
			if(validate == false) { //if the user entered the right format then it checks if it corresponds to one of the actions
                            
                            System.out.println("please enter a number according instructions.");
                            
                        }else{
                            
                            number = Integer.parseInt(choice); //parsing string to integer
                            
                            if(number < 1 || number > 50) { //must be smaller than 3 to be validated
				
                                validate = false;
                                
                                System.out.println("please enter a number according instructions.");
                                
                            }
			}
		
			
		}while(validate == false); //runs this loop until the user enters a correct format 
		
		return number;
	}
	

}
