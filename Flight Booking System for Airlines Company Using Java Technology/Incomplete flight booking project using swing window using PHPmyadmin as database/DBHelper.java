/**
 * 
 */

/**
 * @author 20276
 *
 */
import java.sql.Connection;		// 导入 Java SQL 包
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import Booking.Booking;

import java.sql.PreparedStatement;

//数据库连接信息
public class DBHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/flights?useSSL=false";// false，表示不使用 SSL 加密    localhost:3306/ - 这指定了数据库服务器的位置和本地端口号
    private static final String USER = "root";//數據庫賬號
    private static final String PASSWORD = ""; // 或者您的 MySQL 密码

    //  静态块 加载 JDBC 驱动
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 获取数据库连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    
    

    // 示例：添加一个新的航班到数据库
    public void addFlight(Flight flight) {
        // 使用 Connection 和 SQL 语句来添加一个航班
    	 String sql = "INSERT INTO Flights (FlightID, Origin, Destination, Date) VALUES (?, ?, ?, ?)";
         try (Connection conn = getConnection();
              PreparedStatement pstmt = conn.prepareStatement(sql)) {
             pstmt.setInt(1, flight.getFlightID());
             pstmt.setString(2, flight.getOrigin());
             pstmt.setString(3, flight.getDestination());
             pstmt.setDate(4, new java.sql.Date(flight.getDate().getTime()));
             pstmt.executeUpdate();
         } catch (SQLException e) {
             e.printStackTrace();
         }
    }

    // 示例：获取所有航班的列表
    public List<Flight> getAllFlights() {
    	 List<Flight> flights = new ArrayList<>();
         String sql = "SELECT * FROM Flights";
         try (Connection conn = getConnection();
              PreparedStatement pstmt = conn.prepareStatement(sql);
              ResultSet rs = pstmt.executeQuery()) {
             while (rs.next()) {
                 Flight flight = new Flight();
                 flight.setFlightID(rs.getInt("FlightID"));
                 flight.setOrigin(rs.getString("Origin"));
                 flight.setDestination(rs.getString("Destination"));
                 flight.setDate(rs.getDate("Date"));
                 flights.add(flight);
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return flights;
    }
    
    
 // 更新座位价格
    public void setSeatPrice(int flightID, int seatTypeID, double price) {
        String sql = "UPDATE FlightSeatPrices SET Price = ? WHERE FlightID = ? AND SeatTypeID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, price);
            pstmt.setInt(2, flightID);
            pstmt.setInt(3, seatTypeID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 // 更新航班信息
    public void updateFlight(int flightID, String newOrigin, String newDestination, Date newDate) {
        String sql = "UPDATE Flights SET Origin = ?, Destination = ?, Date = ? WHERE FlightID = ?";

        try (Connection conn = DBHelper.getConnection(); // 使用类名直接调用静态方法
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newOrigin);
            pstmt.setString(2, newDestination);
            pstmt.setDate(3, new java.sql.Date(newDate.getTime()));
            pstmt.setInt(4, flightID);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //刪除航班
    public void deleteFlight(int flightID) {
        String sql = "DELETE FROM Flights WHERE FlightID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, flightID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //刪除座位
    public void deleteSeatType(int seatTypeID) {
        String sql = "DELETE FROM SeatTypes WHERE SeatTypeID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, seatTypeID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 // 添加座位类型
    public void addSeatType(String typeName, String features) {
    	 String sql = "INSERT INTO SeatTypes (Type, Features) VALUES (?, ?)";
    	    try (Connection conn = DBHelper.getConnection();
    	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
    	        pstmt.setString(1, typeName);
    	        pstmt.setString(2, features);
    	        pstmt.executeUpdate();
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    }
    
    //  更新座位类型特征
    public void updateSeatTypeFeatures(int seatTypeID, String newFeatures) {
    	String sql = "UPDATE SeatTypes SET Features = ? WHERE SeatTypeID = ?";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newFeatures);
            pstmt.setInt(2, seatTypeID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




 // 搜索航班
    public List<Flight> searchFlights(String origin, String destination, Date date) {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM Flights WHERE Origin = ? AND Destination = ? AND Date = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, origin);
            pstmt.setString(2, destination);
            pstmt.setDate(3, date);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();
                // Assume Flight class has a constructor or setters to initialize its fields
                flight.setFlightID(rs.getInt("FlightID"));
                flight.setOrigin(rs.getString("Origin"));
                flight.setDestination(rs.getString("Destination"));
                flight.setDate(rs.getDate("Date"));
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }




 // 预订航班
    public boolean bookFlight(int flightID, int customerID) {
        String sql = "INSERT INTO BookedFlights (FlightID, CustomerID) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, flightID);
            pstmt.setInt(2, customerID);
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    
	//見擦汗作爲是否可用
    public boolean isSeatAvailable(int flightID, int seatID) {
        String sql = "SELECT * FROM Seats WHERE FlightID = ? AND SeatID = ? AND IsBooked = 0";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, flightID);
            pstmt.setInt(2, seatID);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();  // If there's a result, the seat is available
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    
    public boolean bookSeat(int flightID, int seatID, int customerID) {
        // First, check if the seat is available
        if (!isSeatAvailable(flightID, seatID)) {
            return false; // Seat is not available
        }
        // If the seat is available, proceed to book it
        String sql = "UPDATE Seats SET IsBooked = 1, CustomerID = ? WHERE FlightID = ? AND SeatID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerID);
            pstmt.setInt(2, flightID);
            pstmt.setInt(3, seatID);
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




    public Booking completeBooking(int customerID) {
        Booking booking = null;
        // The SQL query needs to interact with several tables, this is a simplification
        String sql = "INSERT INTO Bookings (CustomerID, FlightID, SeatID, Status, BookingDate) VALUES (?, ?, ?, 'Booked', ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Assuming you have a way to determine the FlightID and SeatID
            int flightID = 1; // This should be determined by your application logic
            int seatID = 25; // This should be determined by your application logic
            
            pstmt.setInt(1, customerID);
            pstmt.setInt(2, flightID);
            pstmt.setInt(3, seatID);
            pstmt.setDate(4, new Date(System.currentTimeMillis()));
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int bookingID = generatedKeys.getInt(1);
                        booking = new Booking(bookingID, customerID, flightID, seatID, "Booked", new Date(System.currentTimeMillis()));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }





	
    public boolean makePayment(int bookingID, double amount) {
        // This method would likely involve updating a booking record with payment details
        // and possibly interacting with an external payment service.
        // For now, here's a basic implementation to update a Booking table:
        String sql = "UPDATE Bookings SET AmountPaid = ?, IsPaid = 1 WHERE BookingID = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, amount);
            pstmt.setInt(2, bookingID);
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




	/**
	 * @param flightID
	 * @param origin
	 * @param destination
	 * @param date
	 * @param price
	 * @return
	 */
    public static boolean saveFlightInfo(String flightID, String origin, String destination, String date, String price) {
        try {
            System.out.println("Flight ID: " + flightID);
            System.out.println("Origin: " + origin);
            System.out.println("Destination: " + destination);
            System.out.println("Date: " + date);
            System.out.println("Price: " + price);
            
            
            // 检查日期是否为空
            

            // 使用 SimpleDateFormat 解析日期
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                System.out.println("日期格式不正确，应为 yyyy-MM-dd");
                return false;
            }
            
            java.util.Date parsedDate = dateFormat.parse(date);
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

            // 连接数据库并插入航班信息
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flights?useSSL=false", "root", "")) {
                String sql = "INSERT INTO flights (flight_id, origin, destination, flight_date, price) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, flightID);
                preparedStatement.setString(2, origin);
                preparedStatement.setString(3, destination);
                preparedStatement.setDate(4, sqlDate);
                preparedStatement.setString(5, price);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("航班信息已保存成功");
                    return true;
                } else {
                    System.out.println("保存失败，请检查输入或数据库连接");
                    return false;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("数据库连接或查询失败");
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("日期格式不正确");
            return false;
        }
    }



    }
