package com.bank.cps.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class EnumValueValidator implements ConstraintValidator<EnumValue, String> {
    private Class<? extends Enum<?>> enumClass;
    public void initialize(EnumValue annotation) { this.enumClass = annotation.enumClass(); }
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return Arrays.stream(enumClass.getEnumConstants()).anyMatch(e -> e.name().equals(value));
    }
}
