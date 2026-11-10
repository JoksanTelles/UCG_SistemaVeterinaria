package POJO;

import java.sql.Date;

public class CitasPOJO {
    int idCita;
    Date fecha;
    String hora;
    String motivo;
    int idMascota;
    int idServicio;

    public CitasPOJO() {
    }

    public CitasPOJO(int idCita, Date fecha, String hora, String motivo, int idMascota, int idServicio) {
        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.idMascota = idMascota;
        this.idServicio = idServicio;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }
    
    @Override
    public String toString() {
        return "#" + idCita + " - " + motivo + " (" + fecha + ", " + hora + ")";
    }
}
