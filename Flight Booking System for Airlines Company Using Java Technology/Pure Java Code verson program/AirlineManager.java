/**
 * 
 */

/**
 * @author 20276
 *
 */

import java.util.List;
import java.util.Scanner;

public class AirlineManager {
    // 管理员可以查看和批准座位价格变更	Manager can view and approve seat price changes
	//This method allows the manager to view and approve or update seat prices for each flight.
    public void viewAndApproveSeatPrices(List<Flight> flights, Scanner scanner) {
    	
    	// Loop through each flight in the provided list of flights.
        for (Flight flight : flights) {
        	
        	 // Show flight.
            System.out.println(flight);
            System.out.println("Dear Manager, you can enter new prices for the following seat types or press Enter to approve:");
         // Iterate over each seat type and its current price.
            for (String seatType : flight.getSeatPrices().keySet()) {
            	
            	// Display the current price of the seat type.
                System.out.println("Current price for " + seatType + ": " + flight.getSeatPrice(seatType));
                System.out.println("Enter new price for " + seatType + ":");
                //enter new price
                String input = scanner.nextLine();
                
                
             // Check if input is not empty, indicating a new price is entered.
                if (!input.isEmpty()) {
                	
                	// Parse the input to a double and set the new price for the seat type.
                    double newPrice = Double.parseDouble(input);
                    flight.setSeatPrice(seatType, newPrice);
                    
                    //show the new price.
                    System.out.println("New price for " + seatType + " set to " + newPrice);
                }
            }
        }
        
    }
}
