package com.github.sejoung.hmac.api.contorller;

import com.github.sejoung.hmac.api.dto.HmacTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/")
public class TestContorller {

    @RequestMapping(value = "/test", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void test(@RequestBody HmacTest test) {
        log.info("test = {}", test);
    }

}
