package POJO;

public class ServiciosPOJO {
    int idServicio;
    String tipoServicio;
    double costo;
    String descripcion;

    public ServiciosPOJO() {
    }

    public ServiciosPOJO(int idServicio, String tipoServicio, double costo, String descripcion) {
        this.idServicio = idServicio;
        this.tipoServicio = tipoServicio;
        this.costo = costo;
        this.descripcion = descripcion;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return tipoServicio;
    }
}
