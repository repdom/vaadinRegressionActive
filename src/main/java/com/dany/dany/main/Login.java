package com.dany.dany.main;

import com.dany.dany.entidades.Usuario;
import com.dany.dany.repositorio.UsuarioRepository;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.*;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;

import java.util.Optional;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

@Route(value = "log")
public class Login extends LoginForm {

    @Autowired
    private UsuarioRepository usuarioRepository;
    //LoginForm component;
    public  Login(){
//        this.addLoginListener(e -> {
//            boolean isAuthenticated = authenticate(e);
//            if (isAuthenticated) {
//               // navigateToMainPage();
//                UI.getCurrent().navigate(Dashboard.class);
//            } else {
//                this.setError(true);
//            }
//        });
    }

//    private boolean authenticate(LoginEvent e) {
//        Optional<Usuario> user = usuarioRepository.findByUsuarioAndContraisena(e.getUsername(), e.getPassword());
//        if(user.isPresent())
//            return true;
//
//        return false;
//    }

//    @Override
//    public Registration addLoginListener(ComponentEventListener<LoginEvent> listener) {
//
//        return onLogin(listener); super.addLoginListener(OnLogin(listener);
//    }

//    private void onLogin(LoginEvent loginEvent) {
//        final String username = loginEvent.getUsername();
//        final String password = loginEvent.getPassword();
//
//        try {
//            Notification.show("Login successful");
//
//        } catch (Exception e) {
//            final String message = "User '" + username + "' login failed";
//            Notification.show(message + e.getMessage());
//        }
//    }


}
