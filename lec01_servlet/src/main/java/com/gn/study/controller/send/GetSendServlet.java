package com.gn.study.controller.send;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/joinMember")
public class GetSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetSendServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 1. text 타입
		String userName = request.getParameter("userName");
		// 2. radio 타입
		String userGender = request.getParameter("userGender");
		// 3. number 타입
		int userAge = Integer.parseInt(request.getParameter("userAge"));
		// 4. checkbox 타입
		String[] hobby = request.getParameterValues("hobby");

		System.out.println("이름 : " + userName);
		// System.out.println("성별 : " + userGender);
		// 성별 정보가 1 -> 남성, 그 외 여성
		System.out.print("성별 : ");
		if ("1".equals(userGender)) {
			System.out.println("남성");
		} else {
			System.out.println("여성");
		}
		System.out.println("나이 : " + userAge);
		// System.out.println("취미 : " + hobby);
		// 취미 정보 -> ,로 연결해서 야구,농구,축구
		// 취미 정보 없으면 취미 : 없음
		System.out.print("취미 : ");
		if (hobby == null) {
			System.out.println("없음");
		} else {
			for (int i = 0; i < hobby.length; i++) {
				switch (hobby[i]) {
				case "1":
					if (hobby.length == 1) {
						System.out.print("야구");
					} else {
						System.out.print("야구,");
					}
					break;
				case "2":
					if ("2".equals(hobby[hobby.length - 1])) {
						System.out.print("농구");
					} else {
						System.out.print("농구,");
					}
					break;
				case "3":
					System.out.print("축구");
					break;
				}
			}
		}

		// 1. 출력 할 문서 형태 선언
		response.setContentType("text/html; charset=UTF-8");
		// 2. 터널(스트림)
		PrintWriter out = response.getWriter();
		// 3. 스트림을 통해 HTML 구문을 한줄씩 출력
		out.println("<html lang='en'>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
		out.println("<title>회원가입 결과화면</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>" + userName + "님, 환영합니다</h1>");
		out.println("<h2>앞으로도 자주 와주실꺼죠?</h2>");
		out.println("<a href='/'>홈페이지로 이동</a>");
		out.println("</body>");
		out.println("</html>");
		// 4. 터널 문닫기
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
