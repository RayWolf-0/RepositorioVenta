package cl.duoc.microservicioVenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.microservicioVenta.model.Ventas;

public interface VentasRepository extends JpaRepository <Ventas, Integer>{

    Ventas save(int ventaguardar);

}
