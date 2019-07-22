package com.dany.dany.main;

import com.dany.dany.entidades.Entradas;
import com.dany.dany.services.DispositivoEntradasServices;
import com.dany.dany.services.DispositivoService;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.AxisType;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.DataProviderSeries;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;


@Route(value ="dashboard", layout = MainLayout.class)
public class Dashboard extends Div {

    DataProvider<Entradas, Void> dataProvider;


    public Dashboard(@Autowired DispositivoService dispositivoService, @Autowired DispositivoEntradasServices dispositivoEntradasServices) {
        VerticalLayout layout = new VerticalLayout();

        // MenuView menuView = new MenuView("dashboard");
       //  menuView.getStyle().set("margin-bottom", "20px");
        Select<String> select = new Select<>();
        select.setItems(dispositivoService.getAllDispositivosCodigoYNombre());
        select.setPlaceholder("Dispositivos");
        select.setLabel("Seleccionar dispositivo");

        Chart chart = new Chart(ChartType.SPLINE);

        Configuration configuration = chart.getConfiguration();
        configuration.getTitle().setText("Temperatura y humedad vs Tiempo");
        configuration.getxAxis().setType(AxisType.DATETIME);
        configuration.getxAxis().setTitle("Tiempo");
        configuration.getyAxis().setTitle("Valores");

        dataProvider = DataProvider.fromCallbacks(
                query -> {
                   int offset = query.getOffset();
                   int limit = query.getLimit();
                   if(select.getValue() == null) {
                       return new ArrayList<Entradas>().stream();
                   } else {
                       Long codigo = Long.parseLong(select.getValue().split(" ")[0]);
                       Long dispositivoCodigo = dispositivoService.find(codigo).getIdDispositivo();

                       return dispositivoEntradasServices.getAllEntradas(dispositivoService.find(codigo)).stream();
                   }
           },
            query -> {
               Long dispositivoCodigo = dispositivoService.find(Long.parseLong(select.getValue().split(" ")[0])).getIdDispositivo();
                return Math.toIntExact(dispositivoEntradasServices.getAllEntradas(dispositivoService.find(dispositivoCodigo)).stream().count());
            }
        );
        select.addValueChangeListener(e -> {
           dataProvider.refreshAll();
        });
        DataProviderSeries<Entradas> tempData = new DataProviderSeries<>(dataProvider, Entradas::getTemperatura);
        tempData.setId("Temperatura");
        tempData.setName("Temperatura");
        DataProviderSeries<Entradas> humData = new DataProviderSeries<>(dataProvider, Entradas::getHumedad);
        humData.setId("Humedad");
        humData.setName("Humedad");

        configuration.setSeries(tempData, humData);

        layout.add(select, chart);
        layout.setPadding(true);
        layout.setMargin(true);
        layout.setSpacing(true);
        add(layout);
    }
}
