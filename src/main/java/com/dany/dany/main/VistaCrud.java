package com.dany.dany.main;

import com.dany.dany.entidades.Dispositivo;
import com.dany.dany.repositorio.DispositivoRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "VistaCrud", layout = MainLayout.class)
public class VistaCrud extends VerticalLayout {
    private final DispositivoRepository repo;
    final Grid<Dispositivo> grid;
    private final EditorDispositivo editor;
    private final Button addNewBtn;

    public VistaCrud(DispositivoRepository dispositivoRepository, EditorDispositivo editor) {
        this.repo = dispositivoRepository;
        this.editor = editor;
        this.grid = new Grid<>(Dispositivo.class);
        this.addNewBtn = new Button("Nuevo Dispositivo", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(addNewBtn);
        add(actions, grid, editor);
        this.setWidth("100%");
        grid.setHeight("100px");
        grid.setColumns("idDispositivo", "nombre", "tiempoDeAlarma");
        grid.getColumnByKey("idDispositivo").setWidth("30px").setFlexGrow(0);
        // add(grid);
        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editarDispositivo(e.getValue());
        });

        addNewBtn.addClickListener(e -> editor.editarDispositivo(new Dispositivo()));
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listarDispositivos();
        });
        listarDispositivos();
    }

    private void listarDispositivos() {
        grid.setItems(repo.findAll());

        /*if (StringUtils.isEmpty()) {
            grid.setItems(repo.findAll());
        }
        else {
            grid.setItems(repo.findAll());
        }*/
    }
}
