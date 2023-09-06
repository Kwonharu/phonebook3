package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PersonDao;
import com.javaex.vo.PersonVo;


@WebServlet("/PhonebookController")
public class PhonebookController extends HttpServlet {
	
	//필드
	private static final long serialVersionUID = 1L;
       
	//생성자
	
	//메서드 gs
	
	//메소드 일반
	//	get방식으로 요청이 들어왔을 때 실행되는 메소드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 action의 값을 꺼내온다	업무구분
		String action = request.getParameter("action");
		
		if("list".equals(action)) {	//NullPoint 방지
			System.out.println("action == list");
			//리스트 //////////////////////////////////////////////////////////////////
			//1.dao를 통해 전체 리스트데이터 가져오기
			PersonDao personDao = new PersonDao();
			List<PersonVo> personList = personDao.personSelect("");
			
			//System.out.println(personList);
			
			//리스트 화면(html) + data 응답을 해야한다
			//request data를 넣어둔다
			request.setAttribute("personList", personList);
			
			//list.jsp에게 시킨다		=> 포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp"); //jsp파일 위치를 입력
			rd.forward(request, response);
			//foward : 대충 jsp한테 던지는 것
			////////////////////////////////////////////////////////////////////////////
			
		}else if("wform".equals(action)){
			System.out.println("action == wform");
			//등록폼 //////////////////////////////////////////////////////////////////
			
			//writeForm.jsp에게 시킨다		=> 포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/writeForm.jsp"); //jsp파일 위치를 입력
			rd.forward(request, response);
			
		}else if("insert".equals(action)){
			System.out.println("action == insert");
			//저장할 때
			
			//파라미터 꺼내기
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			//파라미터 값 1개로 묶기
			PersonVo personVo = new PersonVo();
			personVo.setName(name);
			personVo.setHp(hp);
			personVo.setCompany(company);
			
			//System.out.println(personVo);
			
			//dao를 이용해서 테이터 저장하기
			PersonDao personDao = new PersonDao();
			int count = personDao.personInsert(personVo);
			System.out.println(count);
			
			//리스트로 출력
			response.sendRedirect("/phonebook3/PhonebookController?action=list");
				
		}else if("delete".equals(action)){
			System.out.println("action == delete");
			//삭제
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			PersonDao personDao = new PersonDao();
			int count = personDao.personDelete(id);
			System.out.println(count);
			
			response.sendRedirect("/phonebook3/PhonebookController?action=list");
		
		}else if("updateform".equals(action)){
			System.out.println("action == updateform");
			//수정폼
			
			PersonDao personDao = new PersonDao();
			PersonVo personVo = personDao.getPerson(Integer.parseInt(request.getParameter("id")));
			
			request.setAttribute("personVo", personVo);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/updateForm.jsp"); //jsp파일 위치를 입력
			rd.forward(request, response);
		
		}else if("update".equals(action)){
			System.out.println("action == update");
			//수정
			
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			String personId = request.getParameter("id");
			
			PersonVo personVo = new PersonVo(Integer.parseInt(personId), name, hp, company);
		
			PersonDao personDao = new PersonDao();
			int count = personDao.personUpdate(personVo);
			System.out.println(count);

			response.sendRedirect("/phonebook3/PhonebookController?action=list");
			
		}else {
			System.out.println("뭉탱이");
		}
	}

	//forward : 내부에서 일어남 (파일 경로 작성)
	//redirect : 주소치고 엔터 (인터넷 주소 입력)
	
	//	post방식으로 요청이 들어왔을 때 실행되는 메소드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}





