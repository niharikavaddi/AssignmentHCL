package Pack;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Display")
public class Display extends HttpServlet {
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter out= response.getWriter();
		Connection con=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		String url="jdbc:mysql://127.0.0.1:3306/test?autoReconnect=true&useSSL=false",user="niharika",password="abcdefgh";
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
	    con=DriverManager.getConnection(url,user,password);
	    psmt=con.prepareStatement("select amount from accountdetails where accID=23456782");
	    rs=psmt.executeQuery();
	    if(rs.next())
	    out.println("ACCOUNT BALANCE:"+rs.getString(1));
	    psmt=con.prepareStatement("select * from transactions order by id desc limit 5;");
	    rs=psmt.executeQuery();
	    int i=1;
	    while(rs.next())
		{
	    	out.println(i+". "+rs.getString(1));
	    	i++;
		}
	    rs.close();
	    psmt.close();
		con.close();
		}
		catch(Exception e)
		{
			out.println(e.toString());
		}

	}
}
