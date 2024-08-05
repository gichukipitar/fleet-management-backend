package com.fleet.managament.utils;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class UtilFunctions {
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private static boolean isValidPhoneNumber(String phoneNumber) {

        //pattern for phone number
        String phoneRegex = "^(\\+\\d{1,2})?[-.\\s]?\\(?\\d{1,4}\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$";

        //create a pattern object
        Pattern pattern = Pattern.compile(phoneRegex);

        //create a matcher object
        Matcher matcher = pattern.matcher(phoneNumber);

        //check if the given phone number matches the pattern
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        //Defining a valid email pattern
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        //create a pattern object
        Pattern pattern = Pattern.compile(emailRegex);

        //create a matcher object
        Matcher matcher = pattern.matcher(email);

        //check if the email address matches the pattern
        return matcher.matches();
    }

    private static <T> List<ErrorMessage> validateNotNull(Object obj) {

        List<ErrorMessage> errors = new ArrayList<>();

        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);

        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
            errors.add(
                    ErrorMessage.builder()
                            .field(constraintViolation.getPropertyPath().toString())
                            .message(constraintViolation.getMessage())
                            .build());

            log.error("{}-{}", constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        }
        return errors;
    }
}
