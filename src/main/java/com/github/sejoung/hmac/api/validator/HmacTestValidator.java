package com.github.sejoung.hmac.api.validator;

import com.github.sejoung.hmac.api.dto.HmacTest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
public class HmacTestValidator implements ConstraintValidator<HmacTestCheck, HmacTest> {

    @Override
    public void initialize(
        HmacTestCheck constraintAnnotation) {

        log.debug("initialize {}", constraintAnnotation.message());

    }

    @Override
    public boolean isValid(HmacTest hmacTest,
        ConstraintValidatorContext cxt) {
        log.debug("isValid {}", hmacTest);

        if (StringUtils.isEmpty(hmacTest.getData())) {
            cxt.disableDefaultConstraintViolation();
            cxt.buildConstraintViolationWithTemplate("data is Empty")
                .addConstraintViolation();
            return false;
        }

        return true;
    }
}
