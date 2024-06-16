docker run --name postgres -e POSTGRES_PASSWORD=postgres \
-p 5432:5432 -d postgres:16

sleep 10

docker exec -it postgres psql -U postgres -c "CREATE DATABASE cool" 

docker exec -it postgres psql -U postgres -d cool -c "CREATE TABLE IF NOT EXISTS Genre (
    genre_type VARCHAR(255) UNIQUE NOT NULL PRIMARY KEY
);"

docker exec -it postgres psql -U postgres -d cool -c "CREATE TABLE IF NOT EXISTS AgeRange (
    id SERIAL PRIMARY KEY,
    ageClass VARCHAR(255) UNIQUE NOT NULL
);"

