package com.dany.dany.main;

import com.dany.dany.entidades.Usuario;
import com.dany.dany.repositorio.UsuarioRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;


@Route(value = "VistaUsuario", layout = MainLayout.class)
public class VistaUsuario extends VerticalLayout {
    private final UsuarioRepository repo;
    final Grid<Usuario> grid;
    private final EditorUsuario editor;
    private final Button addNewBtn;


    public VistaUsuario(UsuarioRepository usuarioRepository, EditorUsuario editorUsuario) {
        this.repo = usuarioRepository;
        this.editor = editorUsuario;
        this.grid = new Grid<>(Usuario.class);
        this.addNewBtn = new Button("Nuevo Usuario", VaadinIcon.PLUS.create());


        HorizontalLayout actions = new HorizontalLayout(addNewBtn);
        add(actions, grid, editor);

        this.setWidth("100%");
        grid.setColumns("usuario", "nombre", "apellido", "email");

        grid.getColumnByKey("usuario").setWidth("30px").setFlexGrow(0);

        grid.asSingleSelect().addValueChangeListener(e -> {
           editor.editarUsuario(e.getValue());
        });

        addNewBtn.addClickListener(e -> editor.editarUsuario(new Usuario()));
        editor.setChangeHandler(() -> {
            editorUsuario.setVisible(false);
            listarUsuarios();
        });
        listarUsuarios();
    }

    private void listarUsuarios() {
        grid.setItems(repo.findAll());

    }
}
