package com.dany.dany.main;

import com.dany.dany.entidades.*;
import com.dany.dany.repositorio.UsuarioRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@SpringComponent
@UIScope
public class EditorUsuario extends VerticalLayout implements KeyNotifier {
    private final UsuarioRepository repo;

    private Usuario usuario;

    TextField txtApellido = new TextField("Apellido");
    TextField txtNombre = new TextField("Nombre");
    TextField txtUsuario = new TextField("Usuario");
    TextField txtEmail = new TextField("Email");
    PasswordField txtContraisenia = new PasswordField("Contraseña");


    Button guardar = new Button("Guardar", VaadinIcon.CHECK.create());
    Button cancelar = new Button("Cancelar");
    Button eliminar = new Button("Eliminar", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(guardar, cancelar, eliminar);

    Binder<Usuario> binder = new Binder<>(Usuario.class);

    private ChangeHandler changeHandler;

    @Autowired
    public EditorUsuario(UsuarioRepository repository) {
        this.repo = repository;

        add(txtNombre, txtApellido, txtEmail, txtUsuario, txtContraisenia, actions);

        binder.forField(this.txtNombre)
                .withNullRepresentation("")
                .bind(Usuario::getNombre, Usuario::setNombre);
        binder.forField(this.txtApellido)
                .withNullRepresentation("")
                .bind(Usuario::getApellido, Usuario::setApellido);
        binder.forField(this.txtEmail)
                .withNullRepresentation("")
                .bind(Usuario::getEmail, Usuario::setEmail);
        binder.forField(this.txtUsuario)
                .withNullRepresentation("")
                .bind(Usuario::getUsuario, Usuario::setUsuario);
        binder.forField(this.txtContraisenia)
                .withNullRepresentation("")
                .bind(Usuario::getContraisena, Usuario::setContraisena);

        binder.bindInstanceFields(this);

        setSpacing(true);

        guardar.getElement().getThemeList().add("primary");
        eliminar.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> guardar());

        guardar.addClickListener(e -> guardar());
        eliminar.addClickListener(e -> eliminar());
        cancelar.addClickListener(e -> editarUsuario(this.usuario));
        setVisible(false);
    }

    void eliminar() {
        repo.delete(this.usuario);
        changeHandler.onChange();
    }

    void guardar() {
        repo.save(this.usuario);
        changeHandler.onChange();
    }

    public interface  ChangeHandler {
        void onChange();
    }

    public final void editarUsuario(Usuario u) {
        if(u == null) {
            setVisible(true);
            return;
        }
        final boolean persistecia = u.getUsuario() != null;

        if(persistecia) {
            this.usuario = repo.findById(u.getUsuario()).get();
        } else {
            this.usuario = u;
        }
        cancelar.setVisible(persistecia);
        binder.setBean(this.usuario);

        setVisible(true);

        txtNombre.focus();
    }

    public void setChangeHandler(ChangeHandler h) { changeHandler = h; }
}
