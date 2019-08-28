package com.dany.dany.main;

import com.dany.dany.entidades.Usuario;
import com.dany.dany.repositorio.UsuarioRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Push
@Route("")
public class MainLayout extends HorizontalLayout implements RouterLayout {
    Div contenidoDentro;

    @Autowired
    UsuarioRepository usuarioRepository;

//    @Autowired
//    BCryptPasswordEncoder encoder;


    LoginForm component;
    VerticalLayout menu;
    Usuario user;

    public MainLayout() {
        menu = crearMenu();

        menu.setWidth("30%");
        menu.setHeight("100%");

        contenidoDentro = new Div();
        contenidoDentro.setWidth("70%");
        contenidoDentro.setHeight("100%");
        setSizeFull();
        add(menu, contenidoDentro);

        /*component.addLoginListener(e -> {
           try {
               // ResponseEntity<?> response =
               UI.getCurrent().getPage().executeJavaScript("window.location.replace('/dashboard');");
               UI.getCurrent().navigat  e(Dashboard.class);
           } catch (Exception ex) {
               component.setError(true);
           }
        });
        //String jwt = (String) VaadinSession.getCurrent().getAttribute("JSESSIONID");
        String jwt = (String) VaadinSession.getCurrent().getSession().getId();
        System.out.println(jwt);*/


    }

    private VerticalLayout crearMenu() {
        VerticalLayout v = new VerticalLayout();

        v.add(new Label("Administradora de dispositivos Dany"));
        v.add(new RouterLink("Dashboard", Dashboard.class));
        v.add(new RouterLink("Dispositivos", VistaCrud.class));
        v.add(new RouterLink("Usuarios", VistaUsuario.class));

        return v;
    }



//    private boolean authenticate(AbstractLogin.LoginEvent e) {
//        Usuario user = usuarioRepository.findByUsuario(e.getUsername());
//        //if(encoder.matches(e.getPassword(), user.getContraisena()))
//        if(user.getContraisena() == e.getPassword())
//        {
//            this.user = user;
//            return true;
//        }
//
//
//        return false;
//    }

}
