package hu.prf.messaging.util;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Named @RequestScoped
public class Credentials {
    private String email;
    private String password;

    @NotNull @Length(min=3, max=25)
    public String getEmail() { return email; }
    public void setEmail(String username) { this.email = username; }
    
    @NotNull @Length(min=6, max=20)
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
