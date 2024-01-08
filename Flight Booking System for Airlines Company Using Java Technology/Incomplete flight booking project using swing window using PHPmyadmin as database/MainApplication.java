import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApplication {

    public static void main(String[] args) {
        // 创建 JFrame 实例
        JFrame frame = new JFrame("Flight Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        // 创建面板
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        // 设置界面可见
        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // 创建 Admin 按钮
        JButton adminButton = new JButton("Admin");
        adminButton.setBounds(10, 20, 80, 25);
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 创建一个新的JFrame作为弹出窗口
                JFrame adminFrame = new JFrame("Admin Panel");
                adminFrame.setSize(400, 500);
                adminFrame.setLayout(new FlowLayout());

                // 创建文本框和标签
                JTextField flightIDField = new JTextField(20);
                JTextField originField = new JTextField(20);
                JTextField destinationField = new JTextField(20);
                JTextField dateField = new JTextField(20); // 注意：实际应用中应该使用日期选择器
                JTextField priceField = new JTextField(20);

                // 添加组件到adminFrame
                adminFrame.add(new JLabel("Flight ID:"));
                adminFrame.add(flightIDField);

                adminFrame.add(new JLabel("Origin:"));
                adminFrame.add(originField);

                adminFrame.add(new JLabel("Destination:"));
                adminFrame.add(destinationField);

                adminFrame.add(new JLabel("Date (yyyy-mm-dd):"));
                adminFrame.add(dateField);

                adminFrame.add(new JLabel("Price:"));
                adminFrame.add(priceField);

                // 创建保存按钮和其事件监听器
                JButton saveButton = new JButton("Save");
                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	
                    	// 在方法内部声明和初始化变量
                    	 // 初始化为空字符串
                    	String flightID = flightIDField.getText();
                    	String origin = originField.getText();
                    	

                    	String destination = destinationField.getText();
                    	String date = destinationField.getText();
                    	String price = destinationField.getText();
                        // 这里应当有代码来处理保存逻辑，比如验证输入，更新数据库等
                        System.out.println("Flight ID: " + flightIDField.getText());
                        System.out.println("Origin: " + originField.getText());
                        System.out.println("Destination: " + destinationField.getText());
                        System.out.println("Date: " + dateField.getText());
                        System.out.println("Price: " + priceField.getText());
                        
                        // 调用 DBHelper 中的方法来保存数据到数据库
                        boolean success = DBHelper.saveFlightInfo(flightID, origin, destination, date, price);
                        if (success) {
                            JOptionPane.showMessageDialog(null, "航班信息已保存！");
                        } else {
                            JOptionPane.showMessageDialog(null, "保存失败，请检查输入！");
                        }
                    }
                });
                adminFrame.add(saveButton);

                // 设置窗口可见
                adminFrame.setVisible(true);
            }
        });
        
        
        
        

        panel.add(adminButton);

        // 创建 Manager 按钮
        JButton managerButton = new JButton("Manager");
        managerButton.setBounds(100, 20, 100, 25);
        managerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 添加 Manager 功能实现
                System.out.println("Manager button clicked");
            }
        });
        panel.add(managerButton);

        // 创建 Customer 按钮
        JButton customerButton = new JButton("Customer");
        customerButton.setBounds(210, 20, 100, 25);
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: 添加 Customer 功能实现
                System.out.println("Customer button clicked");
            }
        });
        panel.add(customerButton);
    }
}
