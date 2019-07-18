package com.dany.dany.main;
import com.dany.dany.entidades.Dispositivo;
import com.dany.dany.repositorio.DispositivoRepository;
import com.sun.javafx.collections.ChangeHelper;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.*;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.JodaTimeConverters;
import org.springframework.data.convert.Jsr310Converters;

import javax.swing.plaf.basic.BasicMenuUI;
import java.time.LocalDate;

@SpringComponent
@UIScope
public class EditorDispositivo extends VerticalLayout implements KeyNotifier {
    private final DispositivoRepository repo;

    private Dispositivo dispositivo;

    // TextField fechageneracion = new TextField("Fecha generacion");
    TextField temperatura = new TextField("Temperatura");
    TextField humedad = new TextField("Humedad");

    Button guardar = new Button("Guardar", VaadinIcon.CHECK.create());
    Button cancelar = new Button("Cancelar");
    Button eliminar = new Button("Eliminar", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(guardar, cancelar, eliminar);

    Binder<Dispositivo> binder = new Binder<>(Dispositivo.class);

    private ChangeHandler changeHandler;

    @Autowired
    public EditorDispositivo(DispositivoRepository repository) {
        this.repo = repository;

        add(temperatura, humedad, actions);

        // binder.forField(fechageneracion).withNullRepresentation("").withConverter(new DateToLongConverter().toString());
        binder.forField ( this.humedad )
                .withNullRepresentation ( "" )
                .withConverter (new StringToLongConverter(Long.valueOf(0L), "No pueden conversirse nada"))
                .bind (Dispositivo::getHumedad, Dispositivo::setHumedad);
        binder.forField ( this.temperatura )
                .withNullRepresentation ( "" )
                .withConverter (new StringToLongConverter(Long.valueOf(0L), "No pueden conversirse nada"))
                .bind (Dispositivo::getTemperatura, Dispositivo::setTemperatura);
        binder.bindInstanceFields(this);

        setSpacing(true);

        guardar.getElement().getThemeList().add("primary");
        eliminar.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> guardar());

        guardar.addClickListener(e -> guardar());
        eliminar.addClickListener(e -> eliminar());
        cancelar.addClickListener(e -> editarDispositivo(this.dispositivo));
        setVisible(false);
    }

    void eliminar() {
        repo.delete(this.dispositivo);
        changeHandler.onChange();
    }

    void guardar() {
        repo.save(this.dispositivo);
        changeHandler.onChange();
    }

    public interface  ChangeHandler {
        void onChange();
    }

    public final void editarDispositivo(Dispositivo d) {
        if(d == null) {
            setVisible(true);
            return;
        }
        final boolean persistencia = d.getIdDispositivo() != null;

        if(persistencia) {
            this.dispositivo = repo.findById(d.getIdDispositivo()).get();
        } else {
            this.dispositivo = d;
        }
        cancelar.setVisible(persistencia);
        binder.setBean(this.dispositivo);

        setVisible(true);

        temperatura.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }
}
