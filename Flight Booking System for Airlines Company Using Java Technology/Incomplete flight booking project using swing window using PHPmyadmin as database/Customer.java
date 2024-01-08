import java.sql.Date;
import java.util.List;

import Booking.Booking;

public class Customer {

    private DBHelper dbHelper;

    public Customer(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    // 搜索航班
    public List<Flight> searchFlights(String origin, String destination, Date date) {
        // 调用 DBHelper 的方法来搜索航班
        return dbHelper.searchFlights(origin, destination, date);
    }

    // 选择航班
    public boolean selectFlight(int flightID, int customerID) {
        // 在这里，您可能需要检查航班是否可预订等逻辑
        // 之后，调用 DBHelper 的方法来插入预订
        return dbHelper.bookFlight(flightID, customerID);
    }

    // 选择座位
    public boolean selectSeat(int flightID, int seatID, int customerID) {
        // 首先，您应该检查座位是否可用
        if (dbHelper.isSeatAvailable(flightID, seatID)) {
            // 如果座位可用，预订座位
            return dbHelper.bookSeat(flightID, seatID, customerID);
        } else {
            // 如果座位不可用，返回 false
            return false;
        }
    }

    // 完成预订
    public Booking completeBooking(int customerID) {
        // 调用 DBHelper 的方法来完成预订并返回预订详情
        return dbHelper.completeBooking(customerID);
    }

    // 进行支付
    public boolean makePayment(int bookingID, double amount) {
        // 调用 DBHelper 的方法来处理支付
        return dbHelper.makePayment(bookingID, amount);
    }

   
}
