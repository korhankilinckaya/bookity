package com.bookity.test;

import com.bookity.controller.ServiceController;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceController.class, loader = AnnotationConfigContextLoader.class)
public class ServiceControllerTest {

}
