docker exec -it postgres psql -U postgres -d cool -c "
SELECT date_bin(
    '1 year', 
    insert_date,
    '2000-01-1'
    ) 
from personal_book
";

