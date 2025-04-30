package cloud.model.boundary;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UnitEmployeeBoundary {

    @Email
    @NotBlank
    private String email;

    public UnitEmployeeBoundary() {

    }

    public UnitEmployeeBoundary(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
