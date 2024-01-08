/**
 * 
 */

/**
 * @author 20276
 *
 */
import java.util.List;
import java.util.Scanner;

public class CustomerInterface {
    private List<Flight> availableFlights;// List to hold available flights
    private Scanner scanner;// Scanner to read user input

    	//Constructor to create a CustomerInterface instance.
    public CustomerInterface(List<Flight> flights, Scanner scanner) {
        this.availableFlights = flights;
        this.scanner = scanner;
    }
    
    //method for customers to book a flight
    public void bookFlight() {
    	boolean continueBooking = true;

        while (continueBooking) {        	       	   	
        System.out.println("Available flights:");
        
        // Display available flights
        for (int i = 0; i < availableFlights.size(); i++) {
            System.out.println((i + 1) + ": " + availableFlights.get(i));
        }
        
        //select a flight by input number
        System.out.println("Select a flight number:");
        while (!scanner.hasNextInt()) {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // 清除非法输入 Clear invalid input
            } else {
                System.out.println("No input available. Exiting.");
                return;
            }
            System.out.println("Please enter a valid flight number:");
        }
        int flightNumber = scanner.nextInt() - 1;
        scanner.nextLine();// Consume the newline character
        
     // Check for exit condition
        if (flightNumber == -1) {
            System.out.println("Exiting booking system.");
            break; // Exit the loop
        }
        
        
        // Check for valid flight number
        if (flightNumber < 0 || flightNumber >= availableFlights.size()) {
            System.out.println("Invalid flight number. Exiting.");
            continue;
        }
        
        // Get the selected flight
        Flight selectedFlight = availableFlights.get(flightNumber);
        
        
        // Display available seat types for the selected flight
        System.out.println("Available seat types: " + selectedFlight.getSeatPrices().keySet());
        System.out.println("Select a seat type:");
        String seatTypeInput = scanner.nextLine().trim(); // 获取输入并去除前后空格 Get input and trim spaces
        
        // Convert input to correct seat type format
        String seatType = convertSeatType(seatTypeInput);
        
        
        //purchase a seat function
        /**
         * purchaseSeat(seatType) 这个方法是 Flight 类的一部分，其主要目的是处理座位的购买请求。
         * 参数 seatType 指定了客户想要购买的座位类型，比如经济舱、商务舱或头等舱。
         * 首先检查指定座位类型是否可用。
			然后，如果座位可用，将其标记为已售出或保留。
			如果座位不可用，返回一个表示失败的值（比如 false）
			bookFlight 方法中，purchaseSeat 方法的调用用于确定客户选择的座位是否可购买。如果方法返回 true（即购买成功），则接下来的代码会显示座位的价格，并提示用户确认购买。
			如果返回 false（即购买失败），则显示“没有可用座位”的消息。
         */
        if (selectedFlight.purchaseSeat(seatType)) {
            double price = selectedFlight.getSeatPrice(seatType);
            System.out.println("Price for " + seatType + " is: " + price);
            System.out.println("Confirm booking? (yes/no)");
            String confirmation = scanner.nextLine();
            
         // Process payment and send confirmation email
            if (confirmation.equalsIgnoreCase("yes")) {
                System.out.println("Processing payment...");
                // Assume payment is processed successfully
                System.out.println("Payment successful!");
                sendConfirmationEmail(selectedFlight, seatType, price);
            } else {
                System.out.println("Booking cancelled.");
            }
        } else {
            System.out.println("Sorry, no seats available for the selected type.");
        	}
        
        
        //book another flight
        System.out.println("Do you want to book another flight? (yes/no)");
        String response = scanner.nextLine().trim();
        if (!response.equalsIgnoreCase("yes")) {
            continueBooking = false;// Stop booking if user does not want to continue
        }
        
        }
    }
    
    
    //Method to send confirmation email (dummy implementation).
    private void sendConfirmationEmail(Flight flight, String seatType, double price) {
        // Implement email sending logic
        System.out.println("Sending email confirmation for booking:");
        System.out.println("Flight: " + flight);
        System.out.println("Seat Type: " + seatType);
        System.out.println("Price: " + price);
    }
    
    //Method to convert seat type input to the correct format.
    private String convertSeatType(String input) {
        /** 根据 Flight 类中的定义转换座位类型字符串的格式	Convert the format of the seat type string as defined in the Flight class
         *	例如，将输入转换为首字母大写或全大写			For example, convert input to first letter or all caps
        *	这里假设座位类型是首字母大写					It is assumed here that the seat type is capitalized
        */ 
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    
}
