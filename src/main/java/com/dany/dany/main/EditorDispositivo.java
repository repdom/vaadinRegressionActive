package com.dany.dany.main;
import com.dany.dany.entidades.Dispositivo;
import com.dany.dany.repositorio.DispositivoRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class EditorDispositivo extends VerticalLayout implements KeyNotifier {
    private final DispositivoRepository repo;

    private Dispositivo dispositivo;

    // TextField fechageneracion = new TextField("Fecha generacion");
    TextField nombre = new TextField("Nombre");
    TextField tiempoAlerta = new TextField("Tiempo de alerta");

    Button guardar = new Button("Guardar", VaadinIcon.CHECK.create());
    Button cancelar = new Button("Cancelar");
    Button eliminar = new Button("Eliminar", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(guardar, cancelar, eliminar);

    Binder<Dispositivo> binder = new Binder<>(Dispositivo.class);

    private ChangeHandler changeHandler;

    @Autowired
    public EditorDispositivo(DispositivoRepository repository) {
        this.repo = repository;

        add(nombre, tiempoAlerta, actions);

        // binder.forField(fechageneracion).withNullRepresentation("").withConverter(new DateToLongConverter().toString());
        binder.forField ( this.tiempoAlerta)
                .withNullRepresentation ( "" )
                .withConverter (new StringToLongConverter(Long.valueOf(0L), "No pueden conversirse nada"))
                .bind (Dispositivo::getTiempoDeAlarma, Dispositivo::setTiempoDeAlarma);
        binder.forField ( this.nombre)
                .withNullRepresentation ( "" )
                .bind (Dispositivo::getNombre, Dispositivo::setNombre);
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

        nombre.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }
}
