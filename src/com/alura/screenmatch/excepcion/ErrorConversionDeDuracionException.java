package com.alura.screenmatch.excepcion;

public class ErrorConversionDeDuracionException extends RuntimeException {
    private String mensaje;

    public ErrorConversionDeDuracionException(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage(){
        return this.mensaje;
    }

}
