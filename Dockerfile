# Usa una imagen con Tomcat y Java
FROM tomcat:9.0-jdk17

# Elimina aplicaciones por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia tu WAR a la carpeta de Tomcat (renombra a ROOT.war para acceder en la raíz)
COPY target/admin-service-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/admin-service-0.0.1-SNAPSHOT.war

# Puerto expuesto por Tomcat
EXPOSE 8080

# Comando para iniciar Tomcat
CMD ["catalina.sh", "run"]