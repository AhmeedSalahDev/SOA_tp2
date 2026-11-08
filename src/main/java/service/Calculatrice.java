package service;

import jakarta.jws.WebService;

/**
 * Classe métier : implémente les opérations du service.
 * endpointInterface relie la classe au contrat ICalculatrice.
 */
@WebService(
        serviceName = "CalculatriceService",
        portName = "CalculatricePort",
        endpointInterface = "service.ICalculatrice",
        targetNamespace = "http://calculatrice.soa.fss.tn/")
public class Calculatrice implements ICalculatrice {

    @Override
    public double somme(double a, double b) {
        return a + b;
    }

    @Override
    public double soustraction(double a, double b) {
        return a - b;
    }

    @Override
    public double multiplication(double a, double b) {
        return a * b;
    }

    @Override
    public double division(double a, double b) throws DivisionParZeroException {
        if (b == 0) {
            // Transformée par JAX-WS en <soap:Fault> côté client
            throw new DivisionParZeroException("Division par zéro impossible (b = 0)");
        }
        return a / b;
    }
}
