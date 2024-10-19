package com.app.apekade.Utils.Validation.Validator;

import android.widget.EditText;

import com.app.apekade.Utils.Validation.Schema.SignInForm;
import com.app.apekade.Utils.Validation.ValidationResult;

public class SignInValidator {
    private SignInForm signInForm;

    public SignInValidator(SignInForm signInForm) {
        this.signInForm = signInForm;
    }

    public ValidationResult validateEmail(String email) {
        return signInForm.validateEmail(email);
    }

    public ValidationResult validatePassword(String password) {
        return signInForm.validatePassword(password);
    }

    public boolean isValid(String email, String password, EditText emailField, EditText passwordField) {
        boolean isValid = true;

        ValidationResult emailValidation = validateEmail(email);
        ValidationResult passwordValidation = validatePassword(password);

        if (emailValidation instanceof ValidationResult.Invalid) {
            emailField.setError(((ValidationResult.Invalid) emailValidation).getErrorMsg());
            isValid = false;
        } else if (emailValidation instanceof ValidationResult.Empty) {
            emailField.setError(((ValidationResult.Empty) emailValidation).getErrorMsg());
            isValid = false;
        }

        if (passwordValidation instanceof ValidationResult.Invalid) {
            passwordField.setError(((ValidationResult.Invalid) passwordValidation).getErrorMsg());
            isValid = false;
        } else if (passwordValidation instanceof ValidationResult.Empty) {
            passwordField.setError(((ValidationResult.Empty) passwordValidation).getErrorMsg());
            isValid = false;
        }

        return isValid;
    }
}