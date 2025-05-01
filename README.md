# Spring Boot Application

## How to Run

To run the application, execute the following command in the terminal:

```bash
./mvnw clean spring-boot:run
```

# Configuración de Lombok en Maven

Este proyecto usa Lombok para generar automáticamente constructores, getters, loggers, etc.
Para que funcione correctamente incluso fuera del IDE (como en la terminal o en CI/CD), es necesario decirle a Maven que procese las anotaciones de Lombok.

Para eso, se configura el plugin maven-compiler-plugin en el pom.xml con lo siguiente:

Se indica el JDK usado (source y target).

Se agrega Lombok como annotation processor.

Esto evita errores de compilación como constructores faltantes o variables log no reconocidas.

Para eso, se configura el plugin maven-compiler-plugin en el pom.xml así:

```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-compiler-plugin</artifactId>
  <version>3.10.1</version>
  <configuration>
    <source>17</source>
    <target>17</target>
    <annotationProcessorPaths>
      <path>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-configuration-processor</artifactId>
        <version>3.4.5</version>
      </path>
      <path>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.38</version>
      </path>
    </annotationProcessorPaths>
  </configuration>
</plugin>
```
