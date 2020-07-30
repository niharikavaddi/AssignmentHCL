package Pack;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.CallableStatement;
@WebServlet("/Transfer")
public class Transfer extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
	PrintWriter out=response.getWriter();
	try
	{
	PreparedStatement psmt=null;
	ResultSet rs=null;
	String url="jdbc:mysql://127.0.0.1:3306/test?autoReconnect=true&useSSL=false",user="niharika",password="abcdefgh";
    Connection con=DriverManager.getConnection(url,user,password);
    CallableStatement call= con.prepareCall("{call transact(?,?)}");
    call.setInt(1,23456782);
    call.setInt(2,23456783);
	call.execute();
	RequestDispatcher rd=request.getRequestDispatcher("Display");
	rd.forward(request, response);
    call.close();
	con.close();
}
catch(Exception e)
{
	System.out.println(e);
}
	}
}
