FROM postgres:15.2-alpine
MAINTAINER ITAcademy

ADD /tables/ddl/*.sql /docker-entrypoint-initdb.d/
ADD /product-service/ddl/*.sql /docker-entrypoint-initdb.d/
ADD /user-service/ddl/*.sql /docker-entrypoint-initdb.d/
ADD /audit-service/ddl/*.sql /docker-entrypoint-initdb.d/
ADD /mail-service/ddl/*.sql /docker-entrypoint-initdb.d/
