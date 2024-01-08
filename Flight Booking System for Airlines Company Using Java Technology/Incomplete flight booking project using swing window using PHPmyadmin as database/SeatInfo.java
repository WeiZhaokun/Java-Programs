/**
 * 
 */

/**
 * @author 20276
 *
 */
public class SeatInfo {
    private int seatID;
    private String type;
    private String features;

    // 无参构造函数
    public SeatInfo() {
        // 初始化代码...
    }

    // 带参数的构造函数
    public SeatInfo(int seatID, String type, String features) {
        this.seatID = seatID;
        this.type = type;
        this.features = features;
    }

    // Getter 和 Setter 方法
    public int getSeatID() {
        return seatID;
    }

    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    // 如果需要，您还可以添加toString、equals和hashCode方法
}

