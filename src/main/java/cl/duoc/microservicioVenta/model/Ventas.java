package cl.duoc.microservicioVenta.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="VENTA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa una venta")
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VENTA")
    @Schema(description = "id de la venta")
    private int id_venta;

    @Column(name = "ID_USUARIO", nullable = false)
    @Schema(description = "id del usuario")
    private int id_usuario;

    @Column(name = "ID_TIENDA", nullable = false)
    @Schema(description = "id de la tienda")
    private int id_tienda;

    @Column(name= "FECHA", nullable = false)
    @JsonFormat(pattern = "dd-mm-yyyy")
    @Schema(description = "fecha de la venta")
    private String fecha;

    @Column(name= "TOTAL", nullable = false, length = 11)
    @Schema(description = "total de la venta")
    private int total;


}
