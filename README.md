# Microservicio y Dockerfile
## Ejecutar aplicación Spring
```
./mvnw spring-boot:run
```

# Dockerfile

## Crear .JAR
```
./mvnw clean install -DskipTests
```

## Construir imagen
```
docker build -t usuario/app .
```

## Subir imagen a docker hub

Se debe iniciar sesión a tráves de la aplicación de Docker o vía comando

```
docker login
usuario:
contraseña:
```

```
docker push usuario/app
```

