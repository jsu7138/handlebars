package study.handlebars.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public final class BindingResultUtil {
    public static ConcurrentHashMap toConcurrentHashMap(BindingResult bindingResult){
        bindingResult.getFieldErrors().forEach(error -> {
            System.err.println("error.getCode() = " + error.getDefaultMessage());
            System.err.println("error.getCode() = " + error.getField());
        });
        return bindingResult.getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,   // x -> x.getField()
                        FieldError::getDefaultMessage,
                        (x,y)->x,
                        ConcurrentHashMap::new
                ));
    }
}
