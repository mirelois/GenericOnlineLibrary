#!/usr/bin/env bash

docker exec -it postgres psql -U postgres -d cool -c "
SELECT date_trunc('day', insert_date) as bucket , count(*)
FROM personal_book
GROUP BY bucket
LIMIT 100
;
";
