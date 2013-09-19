/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.alexletov.fs;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Alex
 */
@ManagedBean
@SessionScoped
public class LoginBean {
    @EJB
    private AuthenticateBean authBean;
    private boolean logged = false;
    private boolean loginError = false;
    private String login;
    private String password;
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }
    
    public boolean isLogged() {
        return logged;
    }
    public void doLogin() {
        loginError = false;
        logged = authBean.doLogin(login, password);
        if (!logged) {
            loginError = true;
        }
    }
    
    public void validateName(FacesContext context, UIComponent component,
            Object value) {
        String l = (String) value;
        if (l == null || l.length() < 5) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Login is too small", "Login is too small"));
        }
    }
    public void validatePassword(FacesContext context, UIComponent component,
            Object value) {
        String p = (String)value;
        if (p == null || p.length() < 5) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Password is too small", "Password is too small"));
        }
    }

    public boolean isLoginError() {
        return loginError;
    }

    public void setLoginError(boolean loginError) {
        this.loginError = loginError;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}