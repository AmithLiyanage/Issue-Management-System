package com.nvoi.Issuemanagementsystem;

import com.nvoi.Issuemanagementsystem.controller.IssueController;
import com.nvoi.Issuemanagementsystem.repository.EventRepository;
import com.nvoi.Issuemanagementsystem.repository.IssueRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class IssueManagementSystemApplicationTests {

	private IssueController issueController;

	@Test
	void contextLoads() {
	}
	
//	@Test
//	void getOpenIssues(){
//		List res = issueController.getOpenIssues("pqrs@gmail.com");
//		for (Object o:res ) {
//			System.out.println("o = " + o);
//		}
//	}

}
