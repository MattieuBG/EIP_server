# API EschoolBag

## Module - SharedLibrary

Le jar se trouve dans le dossiers Lib. Pour génèrer le jar (jar avec les dependances) créer une régle 

```maven
clean compile assembly:single
```

## Module - Server

- Créer une régle pour clean et install le module serveur

```maven
clean install
```

- Créer également un tomcat avec le war explode dans l'onglet deployement

- Possibilité  de tester la connection à mongo et l'insertion via MongoConnectionTest.java (/server/src/test)

## Module - ClientLibrary

