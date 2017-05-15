package com.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*   JDBC开发步骤：
 *    1、加载驱动程序
 *    2、建立到数据库连接
 *    3、编写SQL语句
 *    4、执行SQL语句
 *    5、处理结果集
 *    6、断开与数据库的连接
 *    
 * */

public class DataBase {
	private static final String driver="oracle.jdbc.driver.OracleDriver";//数据库全限定名(包名+类名)
	private static final String url="jdbc:oracle:thin:@127.0.0.1:1521:orcl";//数据库URL
	private static final String username="scott";  //数据库用户账号
	private static final String password="tiger";   //数据库用户密码
	protected Connection con=null;
	public void getConnect(){
		
		try {
			Class.forName(driver);
			//1.加载驱动,2.建立到数据库连接(url,username,password);
			con=DriverManager.getConnection(url,username,password);
			System.out.println(con);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		DataBase db=new DataBase();
		db.getConnect();
	}
	
	
}
