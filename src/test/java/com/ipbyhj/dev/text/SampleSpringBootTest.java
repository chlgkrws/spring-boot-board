package com.ipbyhj.dev.text;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class SampleSpringBootTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void hello() throws Exception {
    	MultiValueMap<String, String> info = new LinkedMultiValueMap<>();

    	info.add("name", "테스트");
    	info.add("id", "chlgkrws");

        mockMvc.perform(get("/hello")
        		.params(info))
                .andExpect(status().isOk())
                .andExpect(content().string("hello saelobi"))
                .andDo(print());
    }
    
    
}

