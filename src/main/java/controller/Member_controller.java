package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.MemberDAO;
import member.MemberDTO;

import java.io.IOException;

//http://localhost:8181/JSP_MY_PROJ/*.mm
@WebServlet ("*.mm")
public class Member_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Member_controller() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		// 한글이 깨지지 않도록 처리
		  
			request.setCharacterEncoding("UTF-8");
			System.out.println("mm 요청을 처리하는 controller 입니다.");
		    
			// URL : http://localhost:8181/JSP_MY_PROJ/*.mm	 (주소 전체)
			// URI : /JSP_MY_PROJ/*.mm  (서버 주소 뒤에 나오는 부분)
			
			String uri = request.getRequestURI();
			System.out.println(uri);
			
			String path = uri.substring(uri.lastIndexOf("/"));   // uri 의 마지막 "/" 뒤에 있는 것을 path 변수값으로 처리
			System.out.println(path);
	
	
			if ( path.equals("/login.mm")) {
				
			System.out.println("/login mm 요청 성공");
			
			// 1. client 에서 넘긴 파라미터 변수값을 받아서 변수에 저장
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			// 2. 넘겨받은 값을 MemberDTO에 저장	
				
			MemberDTO dto = new MemberDTO () ;
			
			dto.setId(id);
			dto.setPassword(password);
		
			
			// 3. UserDAO 의 login (dto)
			MemberDAO dao = new MemberDAO () ;
						
			// 리턴 받을 UsersDTO 선언
						 
			MemberDTO user = new MemberDTO () ;
						
			user = dao.login(dto);
			
			// user가 null 인  경우 : 인증실패 , 그렇지 않을 경우 인증 성공
						
			if ( user == null ) {
				
			System.out.println( "로그인 실패 ");
			response.sendRedirect("LoginForm.jsp");
			
			
			} else   {
				
			// 세션의 변수의 값을 할당하고 view 페이지로 전송
				
			System.out.println( "로그인 성공 ");	
			HttpSession session = request.getSession();
			
			session.setAttribute("id", user.getId());
			session.setAttribute("role", user.getRole());
				
			response.sendRedirect("LoginForm.jsp");
			
			}
			
			} else if ( path.equals("/logout.mm")) {
				
				System.out.println("logout.mm 요청 처리 ");
				
				// 1. 사용자 세션 변수의 모든 값을 삭제함
				HttpSession session = request.getSession();
				
				// invalidate(); : 세션 변수에 담긴 모든 변수의 값을 삭제 
				session.invalidate();
				
				// 뷰페이지로 이동 (처음페이지로 이동)
				
				response.sendRedirect("http://localhost:8181/JSP_MY_PROJ");
				
			
			
			}
			
			
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
