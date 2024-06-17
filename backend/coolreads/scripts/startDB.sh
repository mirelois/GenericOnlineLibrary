docker run --name postgres -e POSTGRES_PASSWORD=postgres \
-p 5432:5432 -d postgres:16

sleep 5

docker exec -it postgres psql -U postgres -c "CREATE DATABASE cool" 

docker exec -it postgres psql -U postgres -d cool -c "CREATE TABLE IF NOT EXISTS Genre (
    genre_type VARCHAR(255) UNIQUE NOT NULL PRIMARY KEY
);"

docker exec -it postgres psql -U postgres -d cool -c "CREATE TABLE IF NOT EXISTS AgeRange (
    id SERIAL PRIMARY KEY,
    ageClass VARCHAR(255) UNIQUE NOT NULL
);"

sleep 5

docker exec -it postgres psql -U postgres -d cool -c "INSERT INTO Genre (genre_type) VALUES
('scifi'),
('fantasy'),
('adventure'),
('mystery'),
('biography'),
('manga'),
('music'),
('sports'),
('romance')
ON CONFLICT (genre_type) DO NOTHING;";


docker exec -it postgres psql -U postgres -d cool -c "INSERT INTO AgeRange (ageClass) VALUES
('child'),
('teen'),
('young_adult'),
('adult'),
('elder')
ON CONFLICT (ageClass) DO NOTHING;";

