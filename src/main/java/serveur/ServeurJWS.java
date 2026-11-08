package serveur;

import jakarta.xml.ws.Endpoint;
import service.Calculatrice;

/**
 * Publie le service CalculatriceWS avec le serveur HTTP intégré de JAX-WS.
 * WSDL : http://localhost:8085/CalculatriceWS?wsdl
 */
public class ServeurJWS {

    public static final String URL = "http://localhost:8085/CalculatriceWS";

    public static void main(String[] args) {
        Endpoint endpoint = Endpoint.publish(URL, new Calculatrice());

        System.out.println("Service publié : " + endpoint.isPublished());
        System.out.println("Adresse        : " + URL);
        System.out.println("Contrat WSDL   : " + URL + "?wsdl");
        System.out.println("Schéma XSD     : " + URL + "?xsd=1");
        System.out.println("Laissez cette fenêtre ouverte pendant les tests SoapUI.");
    }
}
