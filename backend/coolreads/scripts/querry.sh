docker exec -it postgres psql -U postgres -d cool -c "
       with ages as (
        select bookshelf.name, book_isbn, customer.username, extract('YEAR' from AGE(CURRENT_DATE, customer.birth_date)) as age
        from personal_book
        inner join bookshelf_personalbook bp on personal_book.id = bp.personalbook_id
        inner join bookshelf on bookshelf.id = bp.bookshelf_id
        inner join customer on bookshelf.customer_username = customer.username
        where bookshelf.name = 'already_read' and book_isbn = '9781673363609')
        select ageclass as className, amount
        from agerange
        inner join (
            select width_bucket(age, array[0,13,18,31,61]) as bucket, count(*) as amount
            from ages
            group by bucket
        )
        on agerange.id=bucket

";

