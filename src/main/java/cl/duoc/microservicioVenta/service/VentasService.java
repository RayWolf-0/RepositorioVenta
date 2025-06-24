package cl.duoc.microservicioVenta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.microservicioVenta.model.Ventas;
import cl.duoc.microservicioVenta.repository.VentasRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional

public class VentasService {

    @Autowired
    private VentasRepository ventasrepository;

    public List<Ventas> BuscarVentas(){
        return ventasrepository.findAll();
    }

    public Ventas BuscarunaVenta(int id_venta){
        return ventasrepository.findById(id_venta).get();
    }

    public Ventas GuardarVenta(Ventas ventaguardar){
        return ventasrepository.save(ventaguardar);
    }



}
