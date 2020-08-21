package com.fellows.cursospring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class CursospringApplicationTests {

	@Test
	void contextLoads() {		
		Assert.notNull(new CursospringApplication(), "error");
	}

}
