package controller;

public interface ValidationInterface {
	
	/**
	 * validates user's input and makes sure that the input is not empty 
	 * @return user's input
	 */
	public String inputReader();
	
	/**
	 * display 5 options to the user and waits until the user picks one
	 * @return user's option according to menu options
	 */
	public int validateMainMenu();
	
	/**
	 * display two options to the user and wait until the user picks one
	 * @return user's option according to the menu options
	 */
	public int validateSubMenu();
	
	/**
	 * validates the id that the user is inputing to the system
	 * @return id
	 */
	public int validateID();
	
	/**
	 * validates both reader and book id.
	 * in this particular case both ids need to match with 
	 * ids that are already saved in the system
	 * @return an array of ids
	 */
	public int[] validateBookAndReaderID();
	
	/**
	 * check whether the user wants to sort the 
	 * archive in a certain way or if it is just a normal search
	 * @param term that is being searched
	 */
	public void analyseInputBooks(String term);
	
	/**
	 * check whether the user wants to sort the 
	 * archive in a certain way or if it is just a normal search
	 * @param term that is being searched
	 */
	public void analyseInputReaders(String term);
	
	
	

}
