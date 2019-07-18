package com.dany.dany.main;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.component.button.Button;

import java.awt.*;

@Push
@Route("")
public class MainLayout extends HorizontalLayout implements RouterLayout {
    Div contenidoDentro;

    public MainLayout() {
        VerticalLayout menu = crearMenu();

        menu.setWidth("30%");
        menu.setHeight("100%");

        contenidoDentro = new Div();
        contenidoDentro.setWidth("80%");
        contenidoDentro.setHeight("100%");

        contenidoDentro.add(new Label("Probando Papolo"));

        setSizeFull();
        add(menu, contenidoDentro);
    }

    private VerticalLayout crearMenu() {
        VerticalLayout v = new VerticalLayout();

        v.add(new Label("Administradora de dispositivos Dany"));
        v.add(new RouterLink("Vista CRUD", VistaCrud.class));
        // v.add(listBox);
        // v.add(new )

        return v;
    }
}
