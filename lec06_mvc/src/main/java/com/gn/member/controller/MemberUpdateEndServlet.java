package com.gn.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.gn.member.service.MemberService;
import com.gn.member.vo.Member;

@WebServlet(name = "memberUpdateEndServlet", urlPatterns = "/memberUpdateEnd")
public class MemberUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberUpdateEndServlet() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pw = request.getParameter("member_pw");
		String name = request.getParameter("member_name");
		String no = request.getParameter("member_no");
		// System.out.println(id + " " + pw + " " + name);

		// Service에 데이터 전달
		int result = new MemberService().updateMember(pw, name, no);
		JSONObject obj = new JSONObject();
		obj.put("res_code", "500");
		obj.put("res_msg", "수정 중 오류가 발생하였습니다.");
		if (result > 0) {
			obj.put("res_code", "200");
			obj.put("res_msg", "수정이 완료되었습니다.");

			Member updateMember = new MemberService().refreshMember(no);
			HttpSession session = request.getSession(false);
			session.setAttribute("member", updateMember);
			session.setMaxInactiveInterval(60 * 30);
		}
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(obj);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
