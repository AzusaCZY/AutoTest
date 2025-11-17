import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.sql.*;

public class JDBCTest {

    // 数据库连接配置
    private static final String DB_URL = "jdbc:mysql://localhost:3306/webemp";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";

    /**
     * 根据用户名和密码查询用户信息
     */
    @Test
    @Parameters({"username","password"})
    public void findUserByUsernameAndPassword(String username, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;
        System.out.println("开始查询用户信息..." + username + password);
        try {
            // 1. 获取数据库连接
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 2. 编写SQL语句
            String sql = "SELECT * FROM User WHERE username = ? AND password = ?";

            // 3. 创建PreparedStatement对象
            pstmt = conn.prepareStatement(sql);

            // 4. 设置参数
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            // 5. 执行查询
            rs = pstmt.executeQuery();

            // 6. 处理结果集
            if (rs.next()) {
                user = new User();
                user.setId((int) rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                // 设置其他属性...
                System.out.println("用户信息：" + user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 7. 关闭资源
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //return user;
    }
}

