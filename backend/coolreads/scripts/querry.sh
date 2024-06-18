docker exec -it postgres psql -U postgres -d cool -c "
with ages as (
        select customer.username, extract('YEAR' from AGE(CURRENT_DATE, customer.birth_date)) as age
        from personal_book
        inner join bookshelf on bookshelf_id = bookshelf.id
        inner join customer on customer.username = bookshelf.customer_username
        where book_isbn = '1' and bookshelf.name = 'currently_reading')
        select ageClass as className, amount
        from ageRange
        inner join (
            select width_bucket(age, array[0,13,18,31,61]) as bucket, count(*) as amount
            from ages
            group by bucket
        )
        on ageRange.id=bucket;
";

