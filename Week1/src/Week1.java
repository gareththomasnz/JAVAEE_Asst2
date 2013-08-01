

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Week1
 */
@WebServlet("/Week1")
public class Week1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Week1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = new PrintWriter(response.getWriter());
		response.setContentType("text/html");

		out.println("<html>");
		out.println("<table border=\"1\">");
		
		for(int i = 0; i< 5;i++)
		{
			out.println("<tr>");
			for(int j = 0; j < 5; j++)
			{
				out.println("<td>"+i+","+j+"</td>");
			}
			out.println("</tr>");
		}
		
		out.println("</table>");
		out.println("Hello World! this is my first Java servlet.");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
