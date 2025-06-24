package cl.duoc.microservicioVenta.Assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import cl.duoc.microservicioVenta.controller.VentasController;
import cl.duoc.microservicioVenta.model.Ventas;

@Component
public class VentaAssemblerModel implements RepresentationModelAssembler<Ventas, EntityModel<Ventas>>{

    private final VentasController ventasController;

    VentaAssemblerModel(VentasController ventasController) {
        this.ventasController = ventasController;
    }

    @Override
    public EntityModel<Ventas> toModel(Ventas v){
        return EntityModel.of(
            v,
            linkTo(methodOn(VentasController.class).BuscarunaVenta(v.getId_venta())).withRel("Listar-Venta-Buscada"),
            linkTo(methodOn(VentasController.class).ListarVentas()).withRel("Todas-Las-Ventas")
        );
    }
}
