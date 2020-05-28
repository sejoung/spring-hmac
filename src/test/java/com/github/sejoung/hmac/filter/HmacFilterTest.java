package com.github.sejoung.hmac.filter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
public class HmacFilterTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void 정상() throws Exception {

        this.mockMvc.perform(get("/actuator/health")
            .header("Signature",
                "6d6cd63284be4a47ba7aec4a3458939a95dcbdd5cd0438f23d7457099b4b917c")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void Signature_is_null() throws Exception {
        this.mockMvc.perform(get("/actuator/health")
            .header("Signature",
                "")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    public void Signature_not_equals() throws Exception {
        this.mockMvc.perform(get("/actuator/health")
            .header("Signature",
                "1")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print()).andExpect(status().isForbidden());
    }

}