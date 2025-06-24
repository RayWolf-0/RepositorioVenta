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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/Ventas")

public class VentasController {

    @Autowired
    private VentasService ventasservice;

    //Endpoint para listar las ventas
    @GetMapping
    @Operation(summary = "Listado de ventas", description = "Operación que lista las ventas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se listó correctamente las ventas",
            content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Ventas.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro nada en las ventas",
                content = @Content(mediaType = "application/json",
                schema = @Schema(type = "string", example = "no se encuentran Datos")))
    })
    public ResponseEntity<?> ListarVentas(){
        List<Ventas> Ventas = ventasservice.BuscarVentas();
        if (Ventas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay ventas"); 
        } else {
            return ResponseEntity.ok(Ventas);
            
        }
    }

    //Endpoint para buscar una venta
    @GetMapping("/{id_venta}")
    @Operation(summary = "Endpoint que busca una venta", description = "Operación que busca y lista una venta")
    @Parameters(value = {
        @Parameter(name = "idItem", description = "Id del item que se va a buscar", in = ParameterIn.PATH, required = true)
    })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se lista correctamente El Item",
            content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Ventas.class))),
        @ApiResponse(responseCode = "404", description = "No se encuentran venta",
                content = @Content(mediaType = "application/json",
                schema = @Schema(type = "string", example = "No se encuentran ventas")))
    })
    public ResponseEntity<?> BuscarunaVenta(@PathVariable int id_venta){
        try {
            Ventas ventabuscada = ventasservice.BuscarunaVenta(id_venta);
            return ResponseEntity.ok(ventabuscada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta no encontrada");
        }
    }

    //Endpoint para guardar una venta
    @PostMapping("/{id_venta}")
    @Operation(summary = "Endpoint que registra una venta", description = "Endpoint que registra una venta", 
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto venta que se va a registrar", required = true,
    content = @Content(schema = @Schema(implementation = Ventas.class))
    ))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Indica que se registro correctamente la venta", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ventas.class))),
        @ApiResponse(responseCode = "500", description = "Indica que no se logro registrar la venta",
        content = @Content(schema = @Schema(type = "String", example = "No se puede registar la venta")))
    })
    public ResponseEntity<?> GuardarVenta(@RequestBody Ventas ventaguardar){
        try {
            Ventas registrarventa = ventasservice.GuardarVenta(ventaguardar);
            return ResponseEntity.ok(registrarventa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Venta no guardada");
        }
    }


}
