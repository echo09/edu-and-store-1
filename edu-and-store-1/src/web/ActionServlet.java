package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import entity.User;

public class ActionServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;character=utf-8");
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));

		if("/login".equals(action)){
			//�ȱȽ���֤���Ƿ���ȷ
			HttpSession session = request.getSession();
			//�û��ύ����֤��
			String number1 = request.getParameter("number");
			//session���������֤
			String number2 = (String) session.getAttribute("number");
			if(!number1.equalsIgnoreCase(number2)){
				//��֤�����
				request.setAttribute("number_error", "��֤�����");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			
			//��ȡ�û���������
			String username= request.getParameter("username");

			String pwd = request.getParameter("pwd");
			//�����û��������룬��ѯ���ݿ����Ƿ��ж�Ӧ�ļ�¼
			UserDao dao = new UserDao();
			try {
				User user = dao.findByUsername(username);
				System.out.println(user);
				if(user!=null&&user.getPwd().equals(pwd)){
					//��¼�ɹ�����һЩ���ݰ󶨵�session�����ϡ�
					
					session.setAttribute("user", user);
					
					//�з��������ļ�¼����¼�ɹ�
					response.sendRedirect("success.jsp");
				}else{
					//��¼ʧ��,���û���Ӧ��ʾ
					request.setAttribute("login_failed", "�û������������");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		}

	}

}
