package com.fdd.lms;

import com.fdd.lms.Model.Admin;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Lin.wang
 * @date 2018-05-17 19:34.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminLoginTests {
    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    @Test
    public void adminLoginTest() throws Exception {
        mockMvc.perform(post("/adminLogin").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("adminId", "admin")
                .param("adminPw","123;"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().stringValues("Location","/admin"));



        mockMvc.perform(post("/adminLogin").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("adminId", "adin")
                .param("adminPw","123"))
                .andExpect(status().isOk())
                .andExpect(view().name("errorInfo"));



//        Admin expectedAdmin = new Admin();
//        expectedAdmin.setAdminId("admin");
//        expectedAdmin.setAdminPw("123;");

        mockMvc.perform(get("/adminLogin"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminLoginForm"));



    }

}
