package com.empoperate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.DataBase.DataBase;

public class EmpOperate extends DataBase{

	Statement st=null;
	ResultSet rs=null;
	
	    //录入员工信息方法
	public int addEmp(int empno,String ename,String job,int mgr,String hiredate,int sal,int comm,int deptno){
		//flag:0代表插入失败，1代表成功  ( 最终的值影响数据库的行数)
		int flag=0;
		//1.2、加载驱动，建立连接
		getConnect();
		//3、编写SQL语句
		String sql="insert into emp values("+empno+",'"+ename+"','"+job+"',"+mgr+",to_date('"+hiredate+"','yyyy-mm-dd'),"+sal+","+comm+","+deptno+")";
		System.out.println(sql);
		//4、创建实现执行SQL语句的对象Statement
		try {
			st=con.createStatement();
			//使用Statement对象将SQL发送到数据库中去执行
			flag=st.executeUpdate(sql);//将(增，删，改)的SQL发送给数据库执行
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(st!=null){
					st.close(); //销毁st对象
				}
				if(con!=null){
					con.close();//断开连接
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("插入成功");
		return flag;
	}
	
	//根据员工编号修改员工姓名
	public int updateEmp(int empno,String ename,String hiredate){
		//flag:0代表插入失败，1代表成功  ( 最终的值影响数据库的行数)
				int flag=0;
				//1.2、加载驱动，建立连接
				getConnect();
				//3、编写SQL语句
				String sql="update emp set ename='"+ename+"',hiredate=to_date('"+hiredate+"','yyyy-mm-dd') where empno="+empno;
				System.out.println(sql);
				//4、创建实现执行SQL语句的对象Statement
				try {
					st=con.createStatement();
					//使用Statement对象将SQL发送到数据库中去执行
					flag=st.executeUpdate(sql);//将(增，删，改)的SQL发送给数据库执行
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					try {
						if(st!=null){
							st.close(); //销毁st对象
						}
						if(con!=null){
							con.close();//断开连接
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("修改成功");
				return flag;
	}
	//根据员工编号删除员工姓名
		public int deleteEmp(int empno){
			int flag=0;
			getConnect();
			String sql="delete from emp where empno="+empno;
			System.out.println(sql);
			try {
				st=con.createStatement();
				flag=st.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					st.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("删除成功");
			return flag;
		}
		
	//查询员工信息
	public List getAllEmp(){
		List list=new ArrayList();
		getConnect();
		String sql="select * from emp";
			try {
				st=con.createStatement();
				rs=st.executeQuery(sql);
				Map<String,Object> map=null;
				//从结果集RS提取数据
				while(rs.next()){
					//先创建一个map来保存一行的数据
					map=new HashMap<String,Object>();
					map.put("empno", rs.getInt("empno"));
					map.put("ename", rs.getString("ename"));
					map.put("hiredate", rs.getDate("hiredate"));
					map.put("sal", rs.getInt("sal"));
					//将map存入list中
					list.add(map);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					st.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		return list;
	}
	//作业:使用Statement对象实现对DEPT表的CURD操作
	
	public static void main(String[] args) {
		EmpOperate eo=new EmpOperate();
		//eo.addEmp(9527, "哈哈明", "设计师", 7962, "2017-05-13", 3000, 1000, 20);
		//eo.updateEmp(9527, "老队长", "2008-05-12");
		//eo.deleteEmp(9527);
		List<Map<String,Object>> list=eo.getAllEmp();
		for (Map<String, Object> map : list) {
			System.out.println("员工编号:"+map.get("empno")+"员工姓名:"+map.get("ename")+"入职日期:"+map.get("hiredate")+"工资:"+map.get("sal"));
		}
		
	}
}
