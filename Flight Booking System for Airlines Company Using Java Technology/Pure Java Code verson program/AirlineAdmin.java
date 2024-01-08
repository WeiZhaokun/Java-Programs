import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;


public class AirlineAdmin {
	
	private static Map<String, String> userCredentials;//用来存储用户名（键key）和相对应的密码（值value）Store the user name and the corresponding password.
	private static ArrayList<Flight> flights = new ArrayList<>(); // 将航班列表设置为静态变量 Static flight list
    
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initializeUserCredentials();// Initialize user credentials
        initializeFlights();// Initialize flights
        
        while(true) { // 添加一个循环，允许多次登录 Loop to allow multiple logins
        // 用户登录 User login
        System.out.println("Welcome to the Airline Booking System");
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        if (authenticateUser(username, password)) {
            
            switch (username) {
            case "Admin":
                runAdminFeatures(sc);  // 作为管理员运行		Admin operations
                break;
            case "Manager":
                runManagerFeatures(sc);  // 作为经理运行	Manager operations
                break;
            case "Customer":
                runCustomerFeatures(sc);  // 作为客户运行	Customer operations
                break;
            default:
                System.out.println("Invalid role.");
                break;
            }
        } else {
            System.out.println("Invalid credentials.");           
        }
        System.out.println("Do you want to continue? (yes/no)");
        String response = sc.nextLine();
        if(!response.equalsIgnoreCase("yes")) {
        	System.out.println("The program is end.");
            break; // 如果用户不想继续，则退出循环	Exit loop if user does not want to continue
        	}
        
        }//while brace
	   sc.close();
    }//main method brace
	
	
		// 初始化用户凭证	Initialize user credentials
		private static void initializeUserCredentials() {
	        userCredentials = new HashMap<>();
	        userCredentials.put("Admin", "password");
	        userCredentials.put("Manager", "password");
	        userCredentials.put("Customer", "password");
	    }
	 
		
	 	// 用户验证	Authenticate user
	    private static boolean authenticateUser(String username, String password) {
	        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
	        //如果返回值為真，證明賬號密碼正確
	    }
	    
	    
	    // 初始化航班列表	Initialize flights list
	 	private static void initializeFlights() {       
        // 添加一个初始航班 Add an initial flight (can be modified at will)
        Flight f1 = new Flight("Wenzhou", "ZhengZhou", "2023/10/23", 100, "Firstclass, Business, Economic");    
        Flight f2 = new Flight("Shantou", "Fuzhou", "2023/11/22", 120, "Firstclass, Business, Economic");        
        Flight f3 = new Flight("Shanghai", "Hongkong", "2023/12/21", 150, "Firstclass, Business, Economic");
        Flight f4 = new Flight("Los Angeles", "Phoenix", "2023/1/13", 20, "Firstclass, Business, Economic");
        flights.add(f1);
        flights.add(f2);
        flights.add(f3);
        flights.add(f4);
	 	}
	    
	    // ... 管理员功能代码，使用全局的 flights 列表 Admin features
	    private static void runAdminFeatures(Scanner sc) {
	   //(Scanner sc)Make sure the entire program uses the same Scanner to avoid unexpected logic problems
	    	
	          //  Display all flights
	    	 for(Flight f : flights) {
		            System.out.println(f);
		        }
		        System.out.println();//顯示所有航班	Show all flights
		        
	        // 添加新航班，設置航班信息，设置座位价格	Add new flights or set seat prices
	        System.out.println("Do you need to add new Flight, dear airline admin?");
	        System.out.println("Press 1 for yes, press 2 for no.");
	        String input = sc.nextLine();

	        if(input.equals("1")) {
	            System.out.println("How many flights do you want to input?");
	            int numFlights = sc.nextInt();
	            sc.nextLine();//Consume the newline character after nextInt()
	            	            	           
	            for(int i = 0; i < numFlights; i++) {
	                System.out.println("Enter the departure city:");
	                String origin = sc.nextLine();
	                

	                System.out.println("Enter the destination city:");
	                String destination = sc.nextLine();
	                

	                System.out.println("Enter the travel date (YYYY/MM/DD):");
	                String date = sc.nextLine();
	                
	                System.out.println("Enter the number of travelers:");
	                int people = sc.nextInt();
	                sc.nextLine(); // 消耗掉 nextInt() 后的换行符	Consume the newline character after nextInt()

	                System.out.println("Enter the seat type:");
	                String seats = sc.nextLine();
	                
	                
	                       
	                Flight newFlight = new Flight(origin, destination, date, people, seats);
	                flights.add(newFlight);
	                
	            }//for brace
	        }//if brace
	            
	        //Set seat prices for flights
	          setupSeatPrices(flights, sc);//設置座位價格
	        		       
	        // 再次显示所有航班	Show all flights again after edited
	        for(Flight f : flights) {
	            System.out.println(f);
	        }
	        System.out.println();	        	     
	    }	
	    
	    
	 
	    // Manager features
	    private static void runManagerFeatures(Scanner sc) {
	    	//SC Make sure the entire program uses the same Scanner to avoid unexpected logic problems
	        AirlineManager manager = new AirlineManager();
	        manager.viewAndApproveSeatPrices(flights, sc); 
	        // 直接使用全局的flights列表Use this local flights list directly, otherwise the newly created flights list data will disappear.
	    }
	    
	    
	 // Customer features
	    private static void runCustomerFeatures(Scanner sc) {
	    	//SC for same scanner
	        CustomerInterface customerInterface = new CustomerInterface(flights, sc);
	        //still use same local flights not create new flight.
	        customerInterface.bookFlight();
	    }
	    
	    //Both admin and manager will use this method
       // same reason for flights and SC             // Set seat prices features for admin.
	 private static void setupSeatPrices(ArrayList<Flight> flights, Scanner sc) { 
        for (Flight flight : flights) {
        	
        	// 设置头等舱的价格 Set price of firstclass
            System.out.println("Setting prices for flight: " + flight);
            System.out.println("Enter price for Firstclass seats:");
            double firstClassPrice = sc.nextDouble();            
            sc.nextLine(); // 消耗掉 nextDouble() 后的换行符	Consume the newline character after nextDouble()
            flight.setSeatPrice("Firstclass", firstClassPrice);
            
            // 设置商務艙舱的价格	Set the price of business class
            System.out.println("Enter price for Business seats:");
            double businessPrice = sc.nextDouble();
            sc.nextLine(); // 消耗掉 nextDouble() 后的换行符	Consume the newline character after nextDouble()
            flight.setSeatPrice("Business", businessPrice);
            
            //設置經濟艙價格	Set economic price
            System.out.println("Enter price for Economic seats:");
            double economicPrice = sc.nextDouble();
            sc.nextLine(); // 消耗掉 nextDouble() 后的换行符	Consume the newline character after nextDouble()
            flight.setSeatPrice("Economic", economicPrice);
            
            
            
        }
    }
}
/**
 * When using Scanner's nextDouble() method to read a double value from the console, nextDouble() will read and return the number, but will not read the newline character (\n) after the number. This newline character is generated when the user enters a number and then presses the Enter key.
If you do not call sc.nextLine() to consume the newline character, the next nextLine() call will read the newline character and immediately return an empty string instead of waiting for the user to enter new data. This can cause the program to appear to skip the user's next input.
Therefore, calling sc.nextLine() after each use of nextDouble() is to clear the newline characters in the input buffer, ensuring that the next call to nextLine() will work properly.
 * 
*/
