package ru.touchit.util;

import org.springframework.validation.BindingResult;

public class BindingResultParser {
    public static String parseBindingResultError(BindingResult bindingResult) {
        String errorMessage = "";

        for (int i = 0; i < bindingResult.getErrorCount(); i++) {
            errorMessage += (i + 1) + ". " + bindingResult.getFieldErrors().get(i).getField() + ": "
                    + bindingResult.getAllErrors().get(i).getDefaultMessage() + ".";
            if (i != bindingResult.getErrorCount()-1) {
                errorMessage += " ";
            }
        }

        return errorMessage;
    }
}