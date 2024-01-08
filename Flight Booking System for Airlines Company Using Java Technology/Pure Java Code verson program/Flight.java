import java.util.HashMap;
import java.util.Map;

/**
 * 
 */



public class Flight {

    private String departureCity;  // 出发地 Departure
    private String destinationCity; // 目的地城市	destination city
    private String travelDate; // 旅行日期	travel date
    private int numberOfTravelers; // 出行人数	Number of travelers
    private String seatType; // 座位类型	Seats type
    
    private Map<String, Double> seatPrices;	// A map to store seat types and their prices
    // 枚举座位类型：头等舱，商务舱，经济舱
    private Map<String, Integer> seatCapacities;// A map to store seat types and their capacities
    private Map<String, Integer> seatsRemaining;// A map to store seat types and their remaining seats

    // Constructor to create a Flight object.
    public Flight(String departureCity, String destinationCity, String travelDate, int numberOfTravelers, String seatType) {
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.travelDate = travelDate;
        this.numberOfTravelers = numberOfTravelers;
        this.seatType = seatType;
        this.seatPrices = new HashMap<>();
        this.seatCapacities = new HashMap<>();
        this.seatsRemaining = new HashMap<>();
        initializeSeatCapacities();
    }
    
    //Initializes the seat capacities based on the number of travelers.
    public void initializeSeatCapacities() {
        // 根据给定的百分比设置座位容量	Setting seat capacities as a percentage of total travelers
        int firstClassCapacity = (int) Math.ceil(numberOfTravelers * 0.05); // 头等舱容量	First class capacity
        int businessClassCapacity = (int) Math.ceil(numberOfTravelers * 0.25); // 商务舱容量	Business class capacity
        int economyClassCapacity = (int) Math.ceil(numberOfTravelers * 0.70); // 经济舱容量		Economy class capacity

        // 设置座位容量
        setSeatCapacity("Firstclass", firstClassCapacity);
        setSeatCapacity("Business", businessClassCapacity);
        setSeatCapacity("Economic", economyClassCapacity);
    }
    


    // Getter and Setter methods  
    public Map<String, Double> getSeatPrices() {
        return seatPrices;
    }
    
    public void setSeatPrice(String seatType, double price) {
        seatPrices.put(seatType, price);
    }

    public Double getSeatPrice(String seatType) {
        return seatPrices.get(seatType);
    }
    
    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    public int getNumberOfTravelers() {
        return numberOfTravelers;
    }

    public void setNumberOfTravelers(int numberOfTravelers) {
        this.numberOfTravelers = numberOfTravelers;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }
 // 添加座位容量的方法
    public void setSeatCapacity(String seatType, int capacity) {
        seatCapacities.put(seatType, capacity);
        seatsRemaining.put(seatType, capacity); // 初始情況下，剩餘座位等於總座位
    }
    
    
    
    
 // 購買座位的方法	Sets the capacity for a given seat type.
    public boolean purchaseSeat(String seatType) {
        if (seatsRemaining.getOrDefault(seatType, 0) > 0) {
            seatsRemaining.put(seatType, seatsRemaining.get(seatType) - 1);
            return true;
        }
        return false;
    }

    //  Override toString() for easy printing of TravelBooking details
    @Override//Returns a string representation of the flight details.
    public String toString() {
        return "TravelBooking{ " +
                "Departure: '" + departureCity + '\'' +
                ",   Destination: '" + destinationCity + '\'' +
                ",   TravelDate: " + travelDate +
                ",   Travelers: " + numberOfTravelers +
                ",   Seats Type: " + seatType + '\'' +
                ",   Seat Prices: "+ seatPrices + '}';
    }
}

/**
 * 
 * Flight flight = new Flight("New York", "London", "2023/01/01", 200, "Economy");
 * 上面的代码会打印出 Flight 对象的字符串表示，这是通过调用 flight 对象的 toString() 方法得到的。
 */



