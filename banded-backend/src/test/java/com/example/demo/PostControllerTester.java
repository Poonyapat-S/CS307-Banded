package com.example.demo;

import com.javainuse.MainApplication;
import com.javainuse.classes.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes= MainApplication.class
)
@WebAppConfiguration
@AutoConfigureMockMvc(addFilters = false)
public class PostControllerTester {
    public PostControllerTester() {}

    @Autowired
    private MockMvc mvc;

    @Test
    public void what() {
        assert(true);
    }

    @Test
    @WithUserDetails("psinpany")
    public void testPost() throws Exception {
        User user = new User();
        user.setUserName("psinpany");
        Principal mockPrincipal = Mockito.mock(Principal.class);
        Mockito.when(mockPrincipal.getName()).thenReturn("psinpany");
        mvc.perform(get("/api/posts/timeline?count=0", user)).andExpect(status().isOk());
    }
}
