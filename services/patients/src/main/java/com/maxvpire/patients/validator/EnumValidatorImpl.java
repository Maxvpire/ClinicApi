package com.maxvpire.patients.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {
    private final EnumValidator annotation;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;

        Object[] enumValues = this.annotation.enumClass().getEnumConstants();

        if (enumValues != null) {
            for (Object enumVal : enumValues) {
                if (value.equals(enumVal.toString())) {
                    return true;
                }
            }
        }

        return false;
    }
}
