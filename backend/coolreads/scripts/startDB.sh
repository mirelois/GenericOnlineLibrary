docker run --name postgres -e POSTGRES_PASSWORD=postgres \
-p 5432:5432 -d postgres:16

sleep 10

docker exec -it postgres psql -U postgres -c "CREATE DATABASE cool" 
