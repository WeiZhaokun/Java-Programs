/**
 * 
 */

/**
 * @author 20276
 *
 */
import java.util.Date;
//这个类主要用于表示和封装与航班相关的数据，例如航班ID (flightID)、出发地 (origin)、目的地 (destination) 和日期 (date)。
//它定义了航班数据的结构和如何访问这些数据，但并不包含数据库交互的逻辑。
//例如，DBHelper 类中的 addFlight(Flight flight) 方法可能会接收一个 Flight 对象，然后使用该对象的属性来构造 SQL 语句，并执行数据库操作。
//这样，Flight 类与 DBHelper 类一起协作，实现了数据的表示和数据库操作的分离
public class Flight {
    private int flightID;
    private String origin;
    private String destination;
    private Date date;
    
    public Flight() {
        // 初始化代码...
    }
    
    // 构造器
    public Flight(int flightID, String origin, String destination, Date date) {
        this.flightID = flightID;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
    }

    // Getter 和 Setter 方法
    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
