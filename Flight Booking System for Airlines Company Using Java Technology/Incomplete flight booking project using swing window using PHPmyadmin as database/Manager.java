import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 
 */

/**
 * @author 20276
 *
 */

public class Manager {
	
    // 构造函数，初始化 DBHelper
    public Manager(DBHelper dbHelper) {
    	// 这里可以初始化 dbHelper 实例，但似乎缺少相应的成员变量和赋值语句。
    }
    
    // 审批价格变动
    public boolean approvePriceChange(int priceID, boolean isApproved) {
        String sql = "UPDATE PriceChanges SET IsApproved = ? WHERE PriceID = ?";
        
        try (Connection conn = DBHelper.getConnection();	// 获取数据库连接
             PreparedStatement pstmt = conn.prepareStatement(sql)) {// 准备 SQL 语句

            pstmt.setBoolean(1, isApproved);// 设置审批结果
            pstmt.setInt(2, priceID);// 设置价格ID

            int rowsUpdated = pstmt.executeUpdate();// 执行更新操作
            return rowsUpdated > 0; // 返回是否更新成功 如果更新了一行或更多行，返回 true
        } catch (SQLException e) {
            e.printStackTrace();// 打印异常信息
            return false;// 如果发生异常，返回 false
        }
    }

    // 查看管理员添加或编辑的座位列表
    public List<SeatInfo> viewEditedSeats() {
        List<SeatInfo> editedSeats = new ArrayList<>();
        String sql = "SELECT * FROM Seats WHERE IsEdited = TRUE";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                SeatInfo seat = new SeatInfo(); // 对于结果集中的每条记录，创建一个新的 SeatInfo 对象
                seat.setSeatID(rs.getInt("SeatID"));
                seat.setType(rs.getString("Type"));
                seat.setFeatures(rs.getString("Features"));
                // 设置其他可能的字段

                editedSeats.add(seat); // 将新创建的 SeatInfo 对象添加到列表中
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return editedSeats; // 返回包含所有已编辑座位的列表
    }


   
}
