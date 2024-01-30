package com.chirkov.restApiRestaurantBussines.units;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AddErrorMessageFromMyException {

    public static String getErrorMessage(BindingResult bindingResult) {

        AtomicReference<StringBuilder> errorMessage = new AtomicReference<>(new StringBuilder());
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();

        for (FieldError error :
                fieldErrorList) {
            errorMessage.get()
                    .append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append(";");
        }
        return errorMessage.get().toString();
    }
}
