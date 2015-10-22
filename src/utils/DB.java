package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DB{
	
	String driver;
    // URL指向要访问的数据库名scutcs
	String url;
    // MySQL配置时的用户�?
	String user; 
    // MySQL配置时的密码
	String password;
	public ResultSet rs;
	Connection conn;
    
	public DB() throws ClassNotFoundException, SQLException{
		
		// 驱动程序
	    driver = "com.mysql.jdbc.Driver";
	
	    // URL指向要访问的数据库名scutcs
	    url = "jdbc:mysql://192.168.0.202:3550/dpf";
	    //url = "jdbc:mysql://192.168.1.103:3550/dpf_temp";
	
	    // MySQL配置时的用户名
	    user = "zhangyunsong"; 
	
	    // MySQL配置时的密码
	    password = "4ibWgK79ht";
	    
	    // 加载驱动程序
	     Class.forName(driver);
	
	     // 连接数据库
	     this.conn = DriverManager.getConnection(url, user, password);
	
	     if(!conn.isClosed()) ;
	      //System.out.println("Succeeded connecting to the Database!");
		
	}

	protected void finalize() throws SQLException{
		this.rs.close();
		this.conn.close();
	}

	public void RunSqlCmd(String cmdstr){
		try { 	
		     // statement用来执行SQL语句
		     Statement statement = conn.createStatement();
		
		     // 要执行的SQL语句
		     String sql = cmdstr;
		
		     // 结果�?
		     this.rs = statement.executeQuery(sql);
				
		    } catch(SQLException e) {
				
		     e.printStackTrace();
				
		    } catch(Exception e) {
				
		     e.printStackTrace();
				
		    } 
			
	}

	public void RunInsert(String cmdstr){
		try { 	
		     // statement用来执行SQL语句
		     Statement statement = conn.createStatement();
		
		     // 要执行的SQL语句
		     String sql = cmdstr;
		
		     statement.executeUpdate(sql);
				
		    } catch(SQLException e) {
				
		     e.printStackTrace();
				
		    } catch(Exception e) {
				
		     e.printStackTrace();
				
		    } 
			
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException{
		DB newdb= new DB();
		 System.out.println("-----------------");
	     System.out.println("执行结果如下�?�?:");
	     System.out.println("-----------------");
	     System.out.println(" user_mobile" + "\t" + " GROUP_CONCAT(DISTINCT CAST(rid AS CHAR))");
	     System.out.println("-----------------");
	
	     String name = null;
	
	     newdb.RunSqlCmd("SELECT user_mobile,GROUP_CONCAT(DISTINCT CAST(rid AS CHAR)) from dw_orders GROUP BY user_mobile HAVING count(DISTINCT rid)>1");
	    
	     while(newdb.rs.next()) {
	
	      // 选择sname这列数据
	      name = newdb.rs.getString("GROUP_CONCAT(DISTINCT CAST(rid AS CHAR))");
	
	      // 首先使用ISO-8859-1字符集将name解码为字节序列并将结果存储新的字节数组中�?
	      // 然后使用GB2312字符集解码指定的字节数组
	      //name = new String(name.getBytes("ISO-8859-1"),"GB2312");
	
	      // 输出结果
	      System.out.println(newdb.rs.getString("user_mobile") + "\t" + name);
	     }
	
	
	     
			
	    
	}

	
}