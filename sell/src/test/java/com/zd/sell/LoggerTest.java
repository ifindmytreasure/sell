package com.zd.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by Unruly Wind on 2019/4/4/004.
 *
 * @author BlueMelancholy
 * @desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {
	@Test
	public void test(){
		String name = "imooc";
		String password = "123456";
		log.debug("debug...");
		log.info("name: " + name + " ,password: " + password);
		log.info("name: {}, password: {}", name, password);
		log.error("error...");
		log.warn("warn...");
		log.info("test");
	}
}
