# API EschoolBag

## Run

- Créer une régle Maven pour clean et install le module serveur

```maven
clean install
```

- Créer également un Tomcat avec le war explode dans l'onglet deployement

- Possibilité  de tester la connection à mongo et l'insertion via MongoConnectionTest.java (/server/src/test)

## Paramétrage de Logback 


- server/main/resources/logback.xml
Actuellement, il y a deux appender/types de sortie, une sous fichier et l'autre dans la console.
La console print tous les messages peut importe leurs niveaux.
Le fichier le stock que les messages d'un level "ERROR"