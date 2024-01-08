import java.sql.Date;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;



/**
 * 
 */

/**
 * @author 20276
 *
 */
public class Admin {

    private DBHelper dbHelper; // // 声明一个 DBHelper 类的实例，用于数据库操作

    // 构造函数，初始化 DBHelper
    public Admin(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    // 添加航班
    public void addFlight(int flightID, String origin, String destination, Date date) {
        // 创建一个 Flight 对象
        Flight newFlight = new Flight(flightID, origin, destination, date);
        // 使用 DBHelper 添加航班到数据库
        dbHelper.addFlight(newFlight);
    }

    // 设置座位价格
    public void setSeatPrice(int flightID, int seatTypeID, double price) {
        // 使用 DBHelper 设置座位价格
        dbHelper.setSeatPrice(flightID, seatTypeID, price);
    }

    // 從數據庫中删除航班
    public void deleteFlight(int flightID) {
        // 使用 DBHelper 删除航班
        dbHelper.deleteFlight(flightID);
    }

    
 // 更新航班信息
    public void updateFlight(int flightID, String newOrigin, String newDestination, Date newDate) {
        // 编写 SQL 更新语句
        String sql = "UPDATE Flights SET Origin = ?, Destination = ?, Date = ? WHERE FlightID = ?";//sql 更新語句

        try (Connection conn = DBHelper.getConnection();	// 获取数据库连接
             PreparedStatement pstmt = conn.prepareStatement(sql)) {	// 准备 SQL 语句

            // 设置 SQL 命令参数
            pstmt.setString(1, newOrigin);
            pstmt.setString(2, newDestination);
            pstmt.setDate(3, new java.sql.Date(newDate.getTime()));
            pstmt.setInt(4, flightID);

            // 执行更新
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();	// 打印异常信息
        }
    }
    
    //添加座位類型
    public void addSeatType(String typeName, String features) {
        dbHelper.addSeatType(typeName, features);
    }

    //更新作爲類型特徵
    public void updateSeatTypeFeatures(int seatTypeID, String newFeatures) {
        dbHelper.updateSeatTypeFeatures(seatTypeID, newFeatures);
    }
    


}

