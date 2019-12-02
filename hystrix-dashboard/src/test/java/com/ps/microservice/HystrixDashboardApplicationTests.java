package com.ps.microservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ps.microservice.HystrixDashboardApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HystrixDashboardApplication.class)
public class HystrixDashboardApplicationTests {

	@Test
	public void contextLoads() {
	}

}
