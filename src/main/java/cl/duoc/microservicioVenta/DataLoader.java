package cl.duoc.microservicioVenta;

import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cl.duoc.microservicioVenta.model.Ventas;
import cl.duoc.microservicioVenta.service.VentasService;
import net.datafaker.Faker;

@Component
public class DataLoader implements CommandLineRunner{

    private final Faker faker = new Faker(new Locale("es", "cl"));
    private final Random random = new Random();

    @Autowired
    private VentasService VentasService;

    @Override
    public void run(String... arg) throws Exception{
        for (int i=0; i < 10; i++){
            Ventas nuevaventa = new Ventas();
            nuevaventa.setId_usuario(random.nextInt());
            nuevaventa.setFecha(faker.date().toString());

            VentasService.GuardarVenta(nuevaventa);
            System.out.println("Venta guardada: " + nuevaventa.getId_usuario());
        }
    }


}
