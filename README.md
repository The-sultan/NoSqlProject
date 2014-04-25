NoSqlProject
============

Proyecto de "Tecnicas avanzadas en gestion de sistemas de informacion" con bases de datos no relacionales.


Requisitos

0. Java SDK
1. Maven (http://maven.apache.org/download.cgi)

Para poder utilizar el proyecto seguir los siguientes pasos

1. Desde el directorio raiz (donde se encuentra el pom.xml) ejecutar en la linea de comando:

<pre><code>mvn clean install<pre><code>

Con esto se descargan las dependencias del proyecto, compila y además ejecuta los tests.

2. Además el proyecto tiene configurado un plugin de maven para usar jetty, para poder inicializar el servidor ejecutar:

<pre><code>mvn jetty:run<pre><code>


Se puede utilizar cualquier IDE. Notar que si se desea buildear desde la ide y no desde una terminal, tener en cuenta que el proyecto es maven (netbeans soporta por defecto proyectos maven, eclipse tiene un plugin). 

