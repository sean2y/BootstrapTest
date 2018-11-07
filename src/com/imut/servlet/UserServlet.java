package com.imut.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imut.dao.UserDao;
import com.imut.dao.impl.UserDaoImpl;
import com.imut.domin.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		String type = request.getParameter("type");
		UserDao dao = new UserDaoImpl();
		if("list".equals(type)){
			List<User> list = dao.findAll();
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(response.getWriter(), list);
			
		}else if("add".equals(type)){
			String userName = request.getParameter("userName");
			String userSex = request.getParameter("userSex");
			int userAge = Integer.parseInt((request.getParameter("userAge")));
			String userNum = request.getParameter("userNum");
			User user = new User(userName, userSex, userAge, userNum);
			boolean flag = dao.add(user);
			ObjectMapper mapper = new ObjectMapper();
			if(flag == true){
				mapper.writeValue(response.getWriter(), "success");
			}else{
				mapper.writeValue(response.getWriter(), "error");
			}
		}else if("del".equals(type)){
			int userId = Integer.parseInt(request.getParameter("userId"));
			boolean flag = dao.del(userId);
			ObjectMapper mapper = new ObjectMapper();
			if(flag == true){
				mapper.writeValue(response.getWriter(), "success");
			}else{
				mapper.writeValue(response.getWriter(), "error");
			}
		}else if("find".equals(type)){
			int userId = Integer.parseInt(request.getParameter("userId"));
			User user = dao.findById(userId);
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(response.getWriter(), user);
		}else if("update".equals(type)){
			String userName = request.getParameter("userName");
			String userSex = request.getParameter("userSex");
			int userAge = Integer.parseInt((request.getParameter("userAge")));
			String userNum = request.getParameter("userNum");
			int userId = Integer.parseInt(request.getParameter("userId"));
			User user = new User(userId, userName, userSex, userAge, userNum);
			boolean flag = dao.update(user);
			ObjectMapper mapper = new ObjectMapper();
			if(flag == true){
				mapper.writeValue(response.getWriter(), user);
			}
		}
	
	}

}
