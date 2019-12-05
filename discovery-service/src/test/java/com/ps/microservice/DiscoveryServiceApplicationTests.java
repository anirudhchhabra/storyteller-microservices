package com.ps.microservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ps.microservice.DiscoveryServiceApplication;

@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(SpringRunner.class)
@SpringBootTest(classes = DiscoveryServiceApplication.class)
public class DiscoveryServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

}
