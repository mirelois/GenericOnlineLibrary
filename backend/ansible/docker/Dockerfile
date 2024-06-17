FROM ubuntu:22.04
ARG DEBIAN_FRONTEND=noninteractive

# Define default values for the Database Environment Variables
ENV DB_CONNECTION mysql
ENV DB_HOST laraveldb
ENV DB_DATABASE laravel
ENV DB_USERNAME myuser
ENV DB_PASSWORD password

# Define default values for the start Environment Variables
ENV MIGRATE true
ENV BUILD true
ENV SEED false

# Update and Install software-properties-common package
RUN apt update && \
    apt install -y software-properties-common
    
# Install laravelio packages
RUN add-apt-repository ppa:ondrej/php && apt update && apt install -y \
    php8.2 \
    php8.2-curl \
    php8.2-xml \
    php8.2-mysql \
    mysql-client \
    composer \
    npm

# Clone Laravel.io repository
RUN git clone https://github.com/laravelio/laravel.io
WORKDIR laravel.io

# Create Laravel.io configuration file
RUN cp .env.example .env
RUN sed -i "s/DB_DATABASE=laravel/DB_CONNECTION=${DB_CONNECTION}\nDB_DATABASE=${DB_DATABASE}/g" .env
RUN sed -i "s/DB_USERNAME=root/DB_HOST=${DB_HOST}\nDB_USERNAME=${DB_USERNAME}/g" .env
RUN sed -i "s/DB_PASSWORD=password/DB_PASSWORD=${DB_PASSWORD}/g" .env

# Install Laravel.io composer
RUN composer install

# Install Laravel.io npm
RUN npm install

# Generate key Laravel.io
RUN php artisan key:generate

# Copy start script
COPY ./start.sh .

# Add execution permission to start script
RUN chmod +x start.sh

# Expose port 8000
EXPOSE 8000

# Start Laravel.io Server
CMD ./start.sh
