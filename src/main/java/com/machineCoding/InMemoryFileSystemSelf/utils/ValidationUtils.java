package com.machineCoding.InMemoryFileSystemSelf.utils;

import com.machineCoding.InMemoryFileSystemSelf.exceptions.ValidationException;

public class ValidationUtils {

    public static void ensureTrue(Boolean bool, String message) {
        if(!bool)
            throw new ValidationException(message);
    }

    public static void ensureFalse(Boolean bool, String message) {
        ensureTrue(!bool, message);
    }
}
