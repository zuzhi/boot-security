package com.example.corespring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeControllerTests {

    @Test
    public void test200AndCorrectJsonContent() throws Exception {
        HomeController homeController = new HomeController();
        MockMvc mockMvc = standaloneSetup(homeController).build();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"msg\":\"hello, world\"}"));
    }

    @Test
    public void test406Error() throws Exception {
        HomeController homeController = new HomeController();
        MockMvc mockMvc = standaloneSetup(homeController).build();
        mockMvc.perform(get("/").accept(MediaType.APPLICATION_XML))
                .andExpect(status().isNotAcceptable());
    }
}
