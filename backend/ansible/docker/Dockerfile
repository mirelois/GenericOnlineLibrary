FROM ubuntu:22.04
ARG DEBIAN_FRONTEND=noninteractive

# Define default values for the Database Environment Variables
ENV DB_CONNECTION mysql
ENV DB_HOST laraveldb
ENV DB_DATABASE laravel
ENV DB_USERNAME myuser
ENV DB_PASSWORD password

# Define default values for Mail Environment Variables
ENV MAIL_MAILER smtp
ENV MAIL_HOST sandbox.smtp.mailtrap.io
ENV MAIL_PORT 2525
ENV MAIL_USERNAME Inbox-Name
ENV MAIL_PASSWORD null
ENV MAIL_ENCRYPTION tls


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

# Change Mail env
RUN sed -i "s/MAIL_MAILER=smtp/MAIL_MAILER=${MAIL_MAILER}/g" .env
RUN sed -i "s/MAIL_HOST=127.0.0.1/MAIL_HOST=${MAIL_HOST}/g" .env
RUN sed -i "s/MAIL_PORT=2525/MAIL_PORT=${MAIL_PORT}/g" .env
RUN sed -i "s/MAIL_USERNAME=Inbox-Name/MAIL_USERNAME=${MAIL_USERNAME}/g" .env
Run sed -i "s/MAIL_PASSWORD=null/MAIL_PASSWORD=${MAIL_PASSWORD}/g" .env
RUN sed -i "s/MAIL_ENCRYPTION=null/MAIL_ENCRYPTION=${MAIL_ENCRYPTION}/g" .env

# Change Github Oath env
RUN sed -i "s/GITHUB_ID=/GITHUB_ID=${GTIHUB_ID}/g" .env
RUN sed -i "s/GITHUB_SECRET=/GITHUB_SECRET=${GITHUB_SECRET}/g" .env
RUN sed -i "s|GITHUB_URL=http://laravel.io.test/auth/github|GITHUB_URL=${GITHUB_URL}|g" .env


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
