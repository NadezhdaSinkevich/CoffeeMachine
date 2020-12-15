package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.dao.dao.UserDao;
import com.dao.query.Query;
import com.data.User;

import org.apache.log4j.BasicConfigurator;



@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static User curUser = null;
	private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(Controller.class);
	@Resource(name="jdbc/coffeemachine")
	public static DataSource dataSource;
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		BasicConfigurator.configure(); 
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logic(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void Logic(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		try {
			
		String command= request.getParameter("command");
		switch(command){
		case "main":
			ShowMain(request,response);
			break;
		case "signin":
			SignIn(request,response);
			break;
		case "signinshowform":
			SignInShowForm(request,response);
			break;
		case "signupshowform":
			SignUpShowForm(request,response);
			break;
		case "signup":
			SignUp(request,response);
			break;
		case "showusers":
			ShowUsers(request,response);
			break;
		case "exit":
			Exit(request,response);
			break;
			default:
				ShowMain(request,response);
				break;
		}}catch(Exception e){
			logger.error("Problems handling request",e);
		}
		
	}
	
	private void Exit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		curUser=null;
		request.setAttribute("curUser", curUser);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/JSP/Main.jsp");
		requestDispatcher.forward(request, response);
	}
	
	private void ShowMain(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("curUser", curUser);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/JSP/Main.jsp");
		requestDispatcher.forward(request, response);
	}
	private void SignIn(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
		if (userDao.CheckUser(request)) {
			curUser= userDao.GetUser(request);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Controller?command=main");
			requestDispatcher.forward(request, response);
			
		}else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Controller?command=signinshowform");
			requestDispatcher.forward(request, response);
		}
	}
	private void SignInShowForm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/JSP/LogIn.jsp");
		requestDispatcher.forward(request, response);	 
	}
	private void SignUp(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
		userDao.AddUser(request);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Controller?command=signinshowform");
		requestDispatcher.forward(request, response);
	}
	private void SignUpShowForm(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/JSP/SignUp.jsp");
		requestDispatcher.forward(request, response);
	}
	private void ShowUsers(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDao();
		List<User> userList = userDao.getUsers(Query.GetAllUsers);
		request.setAttribute("users", userList);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/JSP/Users.jsp");
		requestDispatcher.forward(request, response);	 
	}


}
