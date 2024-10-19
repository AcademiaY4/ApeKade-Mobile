package com.app.apekade.Utils.Validation.Validator;

import android.widget.EditText;
import android.widget.Spinner;

import com.app.apekade.Utils.Validation.Schema.SignUpForm;
import com.app.apekade.Utils.Validation.ValidationResult;

public class SignUpValidator {
    private SignUpForm signUpForm;

    public SignUpValidator(SignUpForm signUpForm) {
        this.signUpForm = signUpForm;
    }

    public ValidationResult validateFirstName() {
        return signUpForm.validateFirstName();
    }
    public ValidationResult validateLastName() {
        return signUpForm.validateLastName();
    }

    public ValidationResult validateEmail() {
        return signUpForm.validateEmail();
    }

    public ValidationResult validatePassword() {
        return signUpForm.validatePassword();
    }

    public ValidationResult validateTelephone() {
        return signUpForm.validateTelephone();
    }

    public ValidationResult validateAge() {
        return signUpForm.validateAge();
    }

    public ValidationResult validateCity() {
        return signUpForm.validateCity();
    }

    public boolean isValid(String firstName, String lastName, String email, String password, String telephone, int age,
                           String district, String province, String city,
                           EditText firstNameField, EditText lastNameField, EditText emailField, EditText passwordField,
                           EditText telephoneField, EditText ageField, EditText cityField) {
        boolean isValid = true;

        // Validate First Name
        ValidationResult firstNameValidation = validateFirstName();
        if (firstNameValidation instanceof ValidationResult.Invalid) {
            firstNameField.setError(((ValidationResult.Invalid) firstNameValidation).getErrorMsg());
            isValid = false;
        } else if (firstNameValidation instanceof ValidationResult.Empty) {
            firstNameField.setError(((ValidationResult.Empty) firstNameValidation).getErrorMsg());
            isValid = false;
        }

        // Validate Last Name
        ValidationResult lastNameValidation = validateLastName();
        if (lastNameValidation instanceof ValidationResult.Invalid) {
            lastNameField.setError(((ValidationResult.Invalid) lastNameValidation).getErrorMsg());
            isValid = false;
        } else if (lastNameValidation instanceof ValidationResult.Empty) {
            lastNameField.setError(((ValidationResult.Empty) lastNameValidation).getErrorMsg());
            isValid = false;
        }

        // Validate Email
        ValidationResult emailValidation = validateEmail();
        if (emailValidation instanceof ValidationResult.Invalid) {
            emailField.setError(((ValidationResult.Invalid) emailValidation).getErrorMsg());
            isValid = false;
        } else if (emailValidation instanceof ValidationResult.Empty) {
            emailField.setError(((ValidationResult.Empty) emailValidation).getErrorMsg());
            isValid = false;
        }

        // Validate Password
        ValidationResult passwordValidation = validatePassword();
        if (passwordValidation instanceof ValidationResult.Invalid) {
            passwordField.setError(((ValidationResult.Invalid) passwordValidation).getErrorMsg());
            isValid = false;
        } else if (passwordValidation instanceof ValidationResult.Empty) {
            passwordField.setError(((ValidationResult.Empty) passwordValidation).getErrorMsg());
            isValid = false;
        }

        // Validate Telephone
        ValidationResult telephoneValidation = validateTelephone();
        if (telephoneValidation instanceof ValidationResult.Invalid) {
            telephoneField.setError(((ValidationResult.Invalid) telephoneValidation).getErrorMsg());
            isValid = false;
        } else if (telephoneValidation instanceof ValidationResult.Empty) {
            telephoneField.setError(((ValidationResult.Empty) telephoneValidation).getErrorMsg());
            isValid = false;
        }

        // Validate Age
        ValidationResult ageValidation = validateAge();
        if (ageValidation instanceof ValidationResult.Invalid) {
            ageField.setError(((ValidationResult.Invalid) ageValidation).getErrorMsg());
            isValid = false;
        }else if(ageValidation instanceof ValidationResult.Empty){
            ageField.setError(((ValidationResult.Empty) ageValidation).getErrorMsg());
            isValid = false;
        }

        // Validate City
        ValidationResult cityValidation = validateCity();
        if (cityValidation instanceof ValidationResult.Invalid) {
            cityField.setError(((ValidationResult.Invalid) cityValidation).getErrorMsg());
            isValid = false;
        } else if (cityValidation instanceof ValidationResult.Empty) {
            cityField.setError(((ValidationResult.Empty) cityValidation).getErrorMsg());
            isValid = false;
        }

        return isValid;
    }
}
