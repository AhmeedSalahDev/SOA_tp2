package service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

/**
 * Contrat du service web : seules les signatures des opérations.
 * C'est à partir de cette interface que JAX-WS génère le portType du WSDL.
 */
@WebService(
        name = "ICalculatrice",
        targetNamespace = "http://calculatrice.soa.fss.tn/")
public interface ICalculatrice {

    @WebMethod(operationName = "somme")
    @WebResult(name = "resultat")
    double somme(@WebParam(name = "a") double a,
                 @WebParam(name = "b") double b);

    @WebMethod(operationName = "soustraction")
    @WebResult(name = "resultat")
    double soustraction(@WebParam(name = "a") double a,
                        @WebParam(name = "b") double b);

    @WebMethod(operationName = "multiplication")
    @WebResult(name = "resultat")
    double multiplication(@WebParam(name = "a") double a,
                          @WebParam(name = "b") double b);

    @WebMethod(operationName = "division")
    @WebResult(name = "resultat")
    double division(@WebParam(name = "a") double a,
                    @WebParam(name = "b") double b) throws DivisionParZeroException;
}
