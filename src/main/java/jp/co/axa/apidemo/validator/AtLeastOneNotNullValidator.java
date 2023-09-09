package jp.co.axa.apidemo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

/**
 * Validator for the custom constraint {@link AtLeastOneNotNull}.
 *
 * This validator checks if at least one field in the validated object is not null.
 */
public class AtLeastOneNotNullValidator implements ConstraintValidator<AtLeastOneNotNull, Object> {

    /**
     * Validates the object to ensure that at least one field is not null.
     *
     * @param value   The object to be validated.
     * @param context The validation context.
     * @return true if at least one field is not null, false otherwise.
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // Null object cannot be valid
        }
        
        Class<?> clazz = value.getClass();

        // Use reflection to get all fields of the class
        Field[] fields = clazz.getDeclaredFields();

        // Iterate through all fields
        for (Field field : fields) {
            try {
                // Make the field accessible (even if it's private)
                field.setAccessible(true);

                // Check if the field is not null
                if (field.get(value) != null) {
                    return true; // At least one field is not null, so it's valid
                }
            } catch (IllegalAccessException e) {
                // Handle any reflection-related exceptions here
                e.printStackTrace();
                return false;
            }
        }
        
        return false; // No field was found that is not null, so it's invalid
    }
}
