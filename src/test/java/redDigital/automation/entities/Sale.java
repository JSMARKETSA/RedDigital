package redDigital.automation.entities;


import java.sql.Timestamp;

public class Sale  {

    private Timestamp fecha;
    private String origen;
    private String destino;
    private String confirmacion;
    private String monto;
    private String carrier;
    private String operacion;
    private String medio;
    private String idTerminal;
    private String tipo;
    private String montoValor;

    public Sale() {
        
    }
    
    public Sale(Timestamp fecha, String origen, String destino, String confirmacion, String monto, String carrier, String operacion, String medio, String idTerminal, String tipo, String montoValor) {
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        this.confirmacion = confirmacion;
        this.monto = monto;
        this.carrier = carrier;
        this.operacion = operacion;
        this.medio = medio;
        this.idTerminal = idTerminal;
        this.tipo = tipo;
        this.montoValor = montoValor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMontoValor() {
        return montoValor;
    }

    public void setMontoValor(String montoValor) {
        this.montoValor = montoValor;
    }



    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }
    
    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getMedio() {
        return medio;
    }

    public void setMedio(String medio) {
        this.medio = medio;
    }

    public String getIdTerminal() {
        return idTerminal;
    }

    public void setIdTerminal(String idTerminal) {
        this.idTerminal = idTerminal;
    }

    
    
    @Override
    public String toString() {
        return "Sale{" +
                "fecha=" + fecha +
                '}';
    }
}
