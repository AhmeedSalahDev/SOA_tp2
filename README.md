# SOA_tp2 – CalculatriceWS (Jakarta XML Web Services)

TP2 du module **Architecture SOA et services web** (LSI3-GLSI, Faculté des Sciences de Sfax, 2025-2026).

Service web **SOAP** en Java qui expose quatre opérations : `somme`, `soustraction`, `multiplication` et `division`.
Le service est développé avec **Jakarta XML Web Services (JAX-WS)**, publié avec `Endpoint.publish` et testé avec **SoapUI**.

## Structure

```
SOA_tp2/
├── pom.xml                        dépendances jakarta.xml.ws-api, jaxws-rt, jakarta.jws-api
├── src/main/java/
│   ├── service/
│   │   ├── ICalculatrice.java     contrat (@WebService, @WebMethod, @WebParam, @WebResult)
│   │   ├── Calculatrice.java      classe métier (implémentation)
│   │   └── DivisionParZeroException.java   @WebFault -> SOAP Fault
│   └── serveur/
│       └── ServeurJWS.java        publication avec Endpoint.publish
└── requetes-soap/                 requêtes SOAP prêtes à coller dans SoapUI
```

## Lancer le service

Dans IntelliJ : clic droit sur `ServeurJWS` puis **Run 'ServeurJWS.main()'**.
En ligne de commande :

```bash
mvn compile exec:java
```

| Ressource | URL |
|---|---|
| Service | http://localhost:8085/CalculatriceWS |
| Contrat WSDL | http://localhost:8085/CalculatriceWS?wsdl |
| Schéma XSD | http://localhost:8085/CalculatriceWS?xsd=1 |

## Correspondance code Java → WSDL

| Code Java | WSDL / XSD |
|---|---|
| `@WebService(name="ICalculatrice")` sur l'interface | `<portType name="ICalculatrice">` |
| `@WebMethod(operationName="somme")` | `<operation name="somme">` + messages `somme` / `sommeResponse` |
| `@WebParam(name="a")`, `@WebParam(name="b")` | éléments `<a>` et `<b>` du type XSD `somme` |
| `@WebResult(name="resultat")` | élément `<resultat>` de `sommeResponse` |
| `serviceName`, `portName` sur `Calculatrice` | `<service name="CalculatriceService">`, `<port name="CalculatricePort">` |
| URL de `Endpoint.publish` | `<soap:address location="http://localhost:8085/CalculatriceWS"/>` |
| `@WebFault DivisionParZeroException` | `<fault name="DivisionParZeroException">` |

## Tester avec SoapUI

1. **File → New SOAP Project**, *Initial WSDL* : `http://localhost:8085/CalculatriceWS?wsdl`.
2. Ouvrir `CalculatricePortBinding → somme → Request 1`.
3. Remplacer les `?` par deux valeurs (ou coller `requetes-soap/somme.xml`).
4. Cliquer sur ▶ et lire la réponse.

Requête :

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:cal="http://calculatrice.soa.fss.tn/">
   <soapenv:Header/>
   <soapenv:Body>
      <cal:somme>
         <a>5</a>
         <b>3</b>
      </cal:somme>
   </soapenv:Body>
</soapenv:Envelope>
```

Réponse attendue :

```xml
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
   <S:Body>
      <ns2:sommeResponse xmlns:ns2="http://calculatrice.soa.fss.tn/">
         <resultat>8.0</resultat>
      </ns2:sommeResponse>
   </S:Body>
</S:Envelope>
```

`division` avec `b = 0` renvoie un **SOAP Fault** contenant le message
« Division par zéro impossible : b doit être différent de 0 ».

## Démarche suivie (consigne du TP)

1. Projet Maven et dépendances (Activité 1).
2. Classe métier avec **une seule opération** (`somme`) d'abord (Activité 2).
3. Annotations JAX-WS (Activité 3), publication et lecture du WSDL (Activité 4).
4. Test de `somme` dans SoapUI (Activité 5).
5. Ajout de `soustraction`, `multiplication`, `division` puis nouveaux tests.
