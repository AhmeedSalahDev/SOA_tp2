package service;

import jakarta.xml.ws.WebFault;

/**
 * Exception métier : déclarée dans le WSDL comme un <fault>
 * et renvoyée au client sous forme de SOAP Fault.
 */
@WebFault(name = "DivisionParZero", targetNamespace = "http://calculatrice.soa.fss.tn/")
public class DivisionParZeroException extends Exception {

    public DivisionParZeroException(String message) {
        super(message);
    }
}
