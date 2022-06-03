# build frontend
FROM node:16-alpine AS frontend
RUN npm install -g pnpm
WORKDIR /frontend
COPY frontend/. .
RUN pnpm install && pnpm build

# build servlet and create war file
# @see https://github.com/carlossg/docker-maven/issues/281
FROM maven@sha256:93696354d3a7556ee08145a8975110abacc8966ec3b74699c1c7cee556c28442 AS warfile
WORKDIR /usr/src/cost-warden
COPY pom.xml .
# Download deps
RUN mvn -B dependency:resolve dependency:resolve-plugins
# Build application
COPY src ./src
RUN rm -rf ./src/main/webapp/index.jsp
COPY --from=frontend /frontend/public/. ./src/main/webapp/
RUN mvn package

FROM tomcat:9.0-jre8-alpine
# Install tools
RUN apk add --no-cache socat \
    && rm -rf /var/cache/apk/*
# ADD tomcat/catalina.sh $CATALINA_HOME/bin/
WORKDIR /usr/local/tomcat/bin
COPY run.sh run.sh
RUN chmod +x run.sh
# Copy war file
RUN rm -rf /usr/local/tomcat/webapps/ROOT
COPY --from=warfile /usr/src/cost-warden/target/cost-warden-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
# Expose ports
ENV JPDA_ADDRESS="8000"
ENV JPDA_TRANSPORT="dt_socket"
EXPOSE 8080
WORKDIR /usr/local/tomcat/bin
CMD ["run.sh"]
