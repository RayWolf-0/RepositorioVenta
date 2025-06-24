package cl.duoc.microservicioVenta.model;

import com.fasterxml.jackson.annotation.JsonFormat;

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

public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VENTA")
    private int id_venta;

    @Column(name = "ID_USUARIO", nullable = false)
    private int id_usuario;

    @Column(name = "ID_TIENDA", nullable = false)
    private int id_tienda;

    @Column(name= "FECHA", nullable = false)
    @JsonFormat(pattern = "dd-mm-yyyy")
    private String fecha;

    @Column(name= "TOTAL", nullable = false, length = 11)
    private int total;


}
