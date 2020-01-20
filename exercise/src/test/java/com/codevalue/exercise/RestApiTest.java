package com.codevalue.exercise;

import org.apache.log4j.BasicConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class RestApiTest extends TestCase {

	protected MockMvc mvc; // we also can use RestController which is popular and convenient
	
	@Autowired
	WebApplicationContext webApplicationContext;

	 
	
	protected void setUp() throws Exception {
		super.setUp();
		BasicConfigurator.configure();
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}



	@Test
	public void testAddWater() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		String uri = "/api/addWater/2/100";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
}
