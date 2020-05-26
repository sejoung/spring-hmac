package com.github.sejoung.hmac.api.contorller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sejoung.hmac.api.dto.HmacTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestContorllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void 정상() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        HmacTest test = HmacTest.builder().data("테스트").build();

        this.mockMvc.perform(post("/api/test")
            .header("Signature", "6d6cd63284be4a47ba7aec4a3458939a95dcbdd5cd0438f23d7457099b4b917c")
            .content(objectMapper.writeValueAsString(test))
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print()).andExpect(status().isOk());
    }


    @Test
    public void Signature_is_null() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        HmacTest test = HmacTest.builder().data("테스트").build();

        this.mockMvc.perform(post("/api/test")
            .header("Signature", "")
            .content(objectMapper.writeValueAsString(test))
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    public void Signature_not_equals() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        HmacTest test = HmacTest.builder().data("테스트").build();

        this.mockMvc.perform(post("/api/test")
            .header("Signature", "1")
            .content(objectMapper.writeValueAsString(test))
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print()).andExpect(status().isForbidden());
    }
}