package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.memberDAO;
import book.memberVO;

@WebServlet("/MemberUpdate.do")
public class memberUpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req,resp);
	}
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		req.setCharacterEncoding("utf-8");
		
		memberDAO instance = memberDAO.getInstance();
		
		int custNo = Integer.parseInt(req.getParameter("custno"));
		String custName = req.getParameter("custName");
		String joindate = req.getParameter("joinDate");
		String grade = req.getParameter("grade");
		String address = req.getParameter("address");
		
		java.sql.Date date = java.sql.Date.valueOf(joindate);
	
		memberVO vo = new memberVO();
		vo.setCustno(custNo);
		vo.setCustname(custName);
		vo.setJoindate(date);
		vo.setGrade(grade);
		vo.setAddress(address);
		
		boolean rst = instance.updateMember(vo);
		
		try {
			if(rst) {
				String good = "good";
				req.setAttribute("good", good);
				RequestDispatcher rd = req.getRequestDispatcher("updateMember.jsp");
				rd.forward(req, resp);
			}else {
				String bad = "bad";
				req.setAttribute("bad", bad);
				RequestDispatcher rd = req.getRequestDispatcher("updateMember.jsp");
				rd.forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
