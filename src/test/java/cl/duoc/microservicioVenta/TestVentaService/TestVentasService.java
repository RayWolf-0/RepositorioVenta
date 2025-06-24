package cl.duoc.microservicioVenta.TestVentaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cl.duoc.microservicioVenta.model.Ventas;
import cl.duoc.microservicioVenta.repository.VentasRepository;
import cl.duoc.microservicioVenta.service.VentasService;

public class TestVentasService {

    @Mock
    private VentasRepository ventasrepository;

    @InjectMocks
    private VentasService ventasservice;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarTodo(){

        List<Ventas> lista = new ArrayList<>();

        Ventas Venta1 = new Ventas();
        Ventas Venta2 = new Ventas();

        Venta1.setFecha("01/01/2025");
        Venta1.setId_tienda(1);
        Venta1.setId_usuario(2);
        Venta1.setId_venta(1);
        Venta1.setTotal(2000);

        Venta2.setFecha("02/02/2025");
        Venta2.setId_tienda(2);
        Venta2.setId_usuario(3);
        Venta2.setId_venta(4);
        Venta2.setTotal(1000);;

        lista.add(Venta1);
        lista.add(Venta2);

        when(ventasrepository.findAll()).thenReturn(lista);

        List<Ventas> resultadoBusqueda = ventasservice.BuscarVentas();

        assertEquals(2, resultadoBusqueda.size());
        verify(ventasrepository, times(1)).findAll();
    }

    @Test
    public void testBuscarUnVenta(){
        Ventas Venta1 = new Ventas();
        Venta1.setFecha("01/01/2025");
        Venta1.setId_tienda(1);
        Venta1.setId_usuario(2);
        Venta1.setId_venta(1);
        Venta1.setTotal(2000);


        when(ventasrepository.findById(1)).thenReturn(Optional.of(Venta1));

        Ventas Ventabuscado = ventasservice.BuscarunaVenta(1);
        assertEquals(1, Ventabuscado.getId_venta());
        verify(ventasrepository, times(1)).findById(1);
    }

    @Test
    public void testGuardarInventario(){
        Ventas Venta1 = new Ventas();
        Venta1.setFecha("01/01/2025");
        Venta1.setId_tienda(1);
        Venta1.setId_usuario(2);
        Venta1.setId_venta(1);
        Venta1.setTotal(2000);

        when(ventasrepository.save(Venta1)).thenReturn(Venta1);

        Ventas ventaGuardada = ventasservice.GuardarVenta(Venta1);

        assertEquals(1, ventaGuardada.getId_venta());
        verify(ventasrepository, times(1)).save(Venta1);

    }

}
