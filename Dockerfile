# Build Frontend
FROM node:18 AS frontend
WORKDIR /app
COPY coolreads/package*.json ./
RUN npm install
COPY coolreads ./
RUN npm run build

# Build Backend
FROM maven:3.9.7 AS backend
WORKDIR /app
COPY backend/coolreads/pom.xml ./
RUN mvn dependency:go-offline

COPY backend/coolreads ./
COPY --from=frontend /app/dist /app/src/main/resources/static
RUN mvn package -DskipTests=true

# Setup Spring boot settings
FROM openjdk:21-jdk
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY --from=backend /app/${JAR_FILE} app.jar

# Set environment variables for database connection
ENV DB_CONNECTION=postgresql
ENV DB_HOST=postgres
ENV DB_PORT=5432
ENV DB_DATABASE=cool
ENV DB_USERNAME=postgres
ENV DB_PASSWORD=postgres

# Set environment variables for cache database connection
ENV CACHE_HOST=redis
ENV CACHE_PORT=6379

# Set environment variables for JWT
ENV SECRET_KEY=3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b
ENV EXPIRATION_TIME=3600000

# CoolReads App Settings
ENV PROFILE=prod

# Mail
ENV COOLREADS_MAIL=no-reply@coolreads.com
ENV MAIL_HOST=sandbox.smtp.mailtrap.io
ENV MAIL_PORT=2525
ENV MAIL_USERNAME=48d43d55f99f02
ENV MAIL_PASSWORD=03900edb14f99c

# Expose port 8080
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
