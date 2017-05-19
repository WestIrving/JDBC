package com.empoperate;

import java.sql.CallableStatement;
import java.sql.SQLException;

import com.DataBase.DataBase;


/*
 * 在JDBC中使用CallabeStament对象调用存储过程
 * 
 * */

public class TestProcedure extends DataBase{
	CallableStatement cs=null;
	//调用存储过程:  实现根据员工编号修改员工姓名
	
	public void callProcedurel(int empno,String ename){
		
		int flag=0;
		
		getConnect();
		
		String sql="{call pr_demo(?,?)}"; // {call 存储过程名称}
		try {
			cs=con.prepareCall(sql);
			cs.setObject(1, empno);
			cs.setObject(2, ename);
			flag=cs.executeUpdate();
			System.out.println("影响的行数为:"+flag);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				cs.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//调用存储过程:  实现根据员工编号查询员工姓名
public void callProcedure2(int empno){
			
		getConnect();
		String sql="{call re_demo2(?,?)}";
		try {
			cs=con.prepareCall(sql);
			cs.setObject(1, empno);
			//如果占位符属于传出参数,需要为占位符注册数据类型
			cs.registerOutParameter(2, java.sql.Types.VARCHAR);
			cs.executeUpdate();
			Object obj=cs.getObject(2);
			System.out.println("员工姓名:"+obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				cs.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
	//作业:使用存储过程，查询员工表的全部信息，并在存储过程进行输出
	
	public static void main(String[] args) {
		
		TestProcedure tp=new TestProcedure();
		tp.callProcedure2(1002);
		
		
	}

}
