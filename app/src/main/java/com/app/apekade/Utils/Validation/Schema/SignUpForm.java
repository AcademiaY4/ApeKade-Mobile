package com.app.apekade.Utils.Validation.Schema;

import com.app.apekade.Helpers.CheckIsDistrict;
import com.app.apekade.Helpers.CheckIsProvince;
import com.app.apekade.Model.Enum.District;
import com.app.apekade.Model.Enum.Province;
import com.app.apekade.Utils.Validation.ValidationResult;

public class SignUpForm {
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String telephone;
    public int age;
    public String district;
    public String province;
    public String city;

    public SignUpForm(String firstName,String lastName, String email, String password, String telephone,
                        int age, String district, String province, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.age = age;
        this.district = district;
        this.province = province;
        this.city = city;
    }

    // Validation for FirstName
    public ValidationResult validateFirstName() {
        if (firstName.isEmpty()) {
            return new ValidationResult.Empty("First name is required.");
        } else {
            return ValidationResult.Valid.getInstance();
        }
    }
    // Validation for LastName
    public ValidationResult validateLastName() {
        if (firstName.isEmpty()) {
            return new ValidationResult.Empty("Last name is required.");
        } else {
            return ValidationResult.Valid.getInstance();
        }
    }

    // Validation for Email
    public ValidationResult validateEmail() {
        String regex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        if (email.isEmpty()) {
            return new ValidationResult.Empty("A valid email is required.");
        } else if (!email.matches(regex)) {
            return new ValidationResult.Invalid("Enter a valid email.");
        } else {
            return ValidationResult.Valid.getInstance();
        }
    }

    // Validation for Password
    public ValidationResult validatePassword() {
        String passwordPattern = "^.{6,}$"; // Minimum 6 characters
        if (password.isEmpty()) {
            return new ValidationResult.Empty("Password is required.");
        } else if (!password.matches(passwordPattern)) {
            return new ValidationResult.Invalid("Password must be at least 6 characters.");
        } else {
            return ValidationResult.Valid.getInstance();
        }
    }

    // Validation for Telephone
    public ValidationResult validateTelephone() {
        String telephonePattern = "^\\d{9}$"; // Matches exactly 9 digits
        if (telephone.isEmpty()) {
            return new ValidationResult.Empty("Telephone number is required.");
        } else if (!telephone.matches(telephonePattern)) {
            return new ValidationResult.Invalid("Telephone must be in the format +94XXXXXXXXX.");
        } else {
            return ValidationResult.Valid.getInstance();
        }
    }

    // Validation for Age
    public ValidationResult validateAge() {
        if (age < 14 || age > 100) {
            return new ValidationResult.Invalid("Age must be between 14 and 100.");
        } else {
            return ValidationResult.Valid.getInstance();
        }
    }

    // Validation for District
    public ValidationResult validateDistrict() {
        if (district == null) {
            return new ValidationResult.Empty("District is required.");
        } else if (!CheckIsDistrict.isValidDistrict(District.valueOf(district))) {
            return new ValidationResult.Invalid("Invalid district.");
        }else {
            return ValidationResult.Valid.getInstance();
        }
    }

    // Validation for Province
    public ValidationResult validateProvince() {
        if (province == null) {
            return new ValidationResult.Empty("Province is required.");
        }else if (!CheckIsProvince.isValidProvince(Province.valueOf(province))) {
            return new ValidationResult.Invalid("Invalid province.");
        } else {
            return ValidationResult.Valid.getInstance();
        }
    }

    // Validation for City
    public ValidationResult validateCity() {
        if (city.isEmpty()) {
            return new ValidationResult.Empty("City is required.");
        } else {
            return ValidationResult.Valid.getInstance();
        }
    }
}
