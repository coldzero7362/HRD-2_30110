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

@WebServlet("/MemberList.do")
public class memberSelectController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}
	public void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		memberDAO instance = memberDAO.getInstance();
		ArrayList<memberVO> list = instance.selectMember();
		try {
			req.setAttribute("list", list);
			RequestDispatcher rd = req.getRequestDispatcher("selectMember.jsp");
			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("컨트롤러 오류");
		}
	}
}
