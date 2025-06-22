package cl.duoc.microservicioVenta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.microservicioVenta.model.Ventas;
import cl.duoc.microservicioVenta.service.VentasService;

@RestController
@RequestMapping("/api/v1/Ventas")

public class VentasController {

    @Autowired
    private VentasService ventasservice;

    @GetMapping
    public ResponseEntity<?> ListarVentas(){
        List<Ventas> Ventas = ventasservice.BuscarVentas();
        if (Ventas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay ventas"); 
        } else {
            return ResponseEntity.ok(Ventas);
            
        }
    }

    @GetMapping("/{id_venta}")
    public ResponseEntity<?> BuscarunaVenta(@PathVariable int id_venta){
        try {
            Ventas ventabuscada = ventasservice.BuscarunaVenta(id_venta);
            return ResponseEntity.ok(ventabuscada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta no encontrada");
        }
    }

    @PostMapping("/{id_venta}")
    public ResponseEntity<?> GuardarVenta(@RequestBody int ventaguardar){
        try {
            Ventas registrarventa = ventasservice.GuardarVenta(ventaguardar);
            return ResponseEntity.ok(registrarventa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Venta no guardada");
        }
    }

}
