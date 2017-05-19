package com.user.Operate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.DataBase.DataBase;

/*
 * PreparedStatement : 通常用来执行带参数的复杂的SQL语句
 * 优点 : 
 * 		1、可以将SQL语句发送到数据库中进行预编译
 * （预编译：将SQL在数据库中编译后存储于数据库缓冲池中，当再次执行该SQL时，将省略编译的过程，直接从缓冲池中取出该SQL进行操作），从而提高程序的执行效率
 *      
 *      2、使用占位符替代了繁琐的SQL拼接操作，从而也可以避免因SQL拼接操作带来的SQL注入问题
 * */

public class UserOperate extends DataBase{

	
	PreparedStatement ps=null;
	Statement st=null;
	ResultSet rs=null;
	//插入用户登录信息
	
	public void addUser(int id,String username,String password){
		
		getConnect();
		String sql="insert into userlogin values(?,?,?)";//使用占位符
		
		//将SQL发送到数据库中进行预编译
		//执行SQL语句
		int flag;
		try {
			ps=con.prepareStatement(sql);
			//需要给占位符赋值（索引，值）
			ps.setInt(1, id);
			ps.setString(2, username);
			ps.setString(3, password);
			//用ps执行SQL语句
			flag = ps.executeUpdate();
			System.out.println("影响的行数为"+flag);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	//实现用户登录
	public int CheckLogin(String username,String password){
		String regexp="\\w+";
		int flag=0;//登录成功标志
		if(!username.matches(regexp)){
			return 0;
		}
		
		getConnect();
		
		try {
			String sql="select * from userlogin where username='"+username+"' and password='"+password+"'";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			
			while(rs.next()){
				flag=1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	//用Prepared实现用户登录
	public int CheckLogin1(String username,String password){
		int flag=0;
		getConnect();
		String sql="select *　from userlogin where username=? and password=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setObject(1, username);
			ps.setObject(2, password);
			rs=ps.executeQuery();
			while(rs.next()){
				flag=1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	public static void main(String[] args) {
		
		UserOperate uo=new UserOperate();
		//uo.addUser(1, "admin", "123456");
		
		String username="admin";
		String password="123456";
		int f=uo.CheckLogin1(username, password);
		if(f==1){
			System.out.println("登录成功");
		}else{
			System.out.println("登录失败");
		}
		
	}
}
