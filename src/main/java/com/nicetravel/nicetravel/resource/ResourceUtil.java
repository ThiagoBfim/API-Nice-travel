package com.nicetravel.nicetravel.resource;

import com.nicetravel.nicetravel.exceptions.EmptyValueException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

public class ResourceUtil {

    public static void validateValue(Object object, String nameVariable) {
        String messageError = String.format("The parameter of '%s'", nameVariable);
        if (object == null) {
            throw new EmptyValueException(messageError + " must have value.");
        }
        if (object instanceof String && StringUtils.isEmpty(object)) {
            throw new EmptyValueException(messageError + " must have value.");
        }
        if (object instanceof Collection && CollectionUtils.isEmpty((Collection<?>) object)) {
            throw new EmptyValueException(messageError + " must have at least one element.");
        }
    }
}
