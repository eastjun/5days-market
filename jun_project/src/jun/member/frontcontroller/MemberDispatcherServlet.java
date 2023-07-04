package jun.member.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jun.member.control.Controller;
import jun.member.controller.IdCheckController;
import jun.member.controller.LogOutController;
import jun.member.controller.LoginController;
import jun.member.controller.LoginViewController;
import jun.member.controller.MainviewController;
import jun.member.controller.MemberDeleteController;
import jun.member.controller.MemberDeleteViewController;
import jun.member.controller.MemberInfoViewController;
import jun.member.controller.MemberInsertController;
import jun.member.controller.MemberInsertViewController;
import jun.member.controller.MemberUpdateController;
import jun.member.controller.MypageViewController;
import jun.member.controller.OrderCheckViewController;
import jun.member.controller.CartViewController;
import jun.member.controller.GoodsDetailController;
import jun.member.controller.GoodsListController;
import jun.member.hander.MemberHandlerAdapter;

public class MemberDispatcherServlet extends HttpServlet implements Servlet{

	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(MemberDispatcherServlet.class);
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI =request.getRequestURI( );
		String contextPath =request.getContextPath( );
		String pathURL =requestURI.substring(contextPath.length( ));
		log.info("매핑명 조회" + pathURL);
		
		MemberHandlerAdapter memberHandlerAdapter = null;
		Controller controller =null;
		
		if (pathURL.equals("/MainView.do")) {
			controller = new MainviewController();
			memberHandlerAdapter = controller.execute(request, response);
			log.info("index 이동 확인 - "+memberHandlerAdapter);	
		}
		else if (pathURL.equals("/MemberLoginView.do")) {
			controller = new LoginViewController();
			memberHandlerAdapter = controller.execute(request, response);
			log.info("로그인 뷰 이동 확인 - "+memberHandlerAdapter);	
		}
		else if (pathURL.equals("/MemberLogin.do")) {
			controller = new LoginController();
			memberHandlerAdapter = controller.execute(request, response);
			log.info("로그인 확인 - "+memberHandlerAdapter);	
		}
		else if (pathURL.equals("/Logout.do")) {
			controller = new LogOutController();
			memberHandlerAdapter = controller.execute(request, response);
			log.info("로그아웃 확인 - "+memberHandlerAdapter);	
		}
		else if (pathURL.equals("/Sign-up.do")) {
			controller = new MemberInsertController();
			memberHandlerAdapter = controller.execute(request, response);
			log.info("회원가입 확인 - "+memberHandlerAdapter);	
		}
		else if (pathURL.equals("/Sign-upView.do")) {
			controller = new MemberInsertViewController();
			memberHandlerAdapter = controller.execute(request, response);
			log.info("회원가입 뷰 이동 확인 - "+memberHandlerAdapter);	
		}
		else if (pathURL.equals("/CheckID.do")) {
			controller = new IdCheckController();
			memberHandlerAdapter = controller.execute(request, response);
			log.info("아이디 중복체크 확인 - "+memberHandlerAdapter);	
		}
		else if (pathURL.equals("/MypageView.do")) {
			controller = new MypageViewController();
			memberHandlerAdapter = controller.execute(request, response);
			log.info("마이페이지 뷰 이동 확인 - "+memberHandlerAdapter);	
		}
		else if (pathURL.equals("/MemberInfoView.do")) {
			controller = new MemberInfoViewController();
			memberHandlerAdapter = controller.execute(request, response);
			log.info("회원정보 뷰 이동 - "+memberHandlerAdapter);	
		}
		else if (pathURL.equals("/MemberInfoUpdate.do")) {
			controller = new MemberUpdateController();
			memberHandlerAdapter = controller.execute(request, response);
			log.info("회원정보 수정 확인 - "+memberHandlerAdapter);	
		}
		else if (pathURL.equals("/MemberDeleteView.do")) {
			controller = new MemberDeleteViewController();
			memberHandlerAdapter = controller.execute(request, response);
			log.info("회원탈퇴 뷰 이동 - "+memberHandlerAdapter);	
		}
		else if (pathURL.equals("/MemberDelete.do")) {
			controller = new MemberDeleteController();
			memberHandlerAdapter = controller.execute(request, response);
			log.info("회원탈퇴 확인 - "+memberHandlerAdapter);	
		}
		else if (pathURL.equals("/OrderCheckView.do")) {
			controller = new OrderCheckViewController();
			memberHandlerAdapter = controller.execute(request, response);
			log.info("주문조회 뷰 이동 확인 - "+memberHandlerAdapter);	
		}
		else if (pathURL.equals("/GoodsListView.do")) {
			controller = new GoodsListController();
			memberHandlerAdapter = controller.execute(request, response);
			log.info("상품목록 뷰 이동 확인 - "+memberHandlerAdapter);	
		}
		else if (pathURL.equals("/GoodsDetailView.do")) {
			controller = new GoodsDetailController();
			memberHandlerAdapter = controller.execute(request, response);
			log.info("상품 상세보기 뷰 이동  - "+memberHandlerAdapter);	
		}
		else if (pathURL.equals("/CartView.do")) {
			controller = new CartViewController();
			memberHandlerAdapter = controller.execute(request, response);
			log.info("장바구니 뷰 이동  - "+memberHandlerAdapter);	
		}
		if (memberHandlerAdapter !=null) {
			if (memberHandlerAdapter.isRedirect()) {
				response.sendRedirect(memberHandlerAdapter.getPath());
				
			}
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(memberHandlerAdapter.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}
	
}
