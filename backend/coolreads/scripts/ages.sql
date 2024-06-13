with ages as (
    select
        extract('YEAR' from AGE(CURRENT_DATE, birth_date)) as age
            from customer)
    select ageClass, amount from ageRange
    inner join (
    select
        width_bucket(age, array[0,13,18,31,61]) as bucket, count(*) as amount
        from ages
        group by bucket
    ) on ageRange.id=bucket;



with ages as (
    select
        extract('YEAR' from AGE(CURRENT_DATE, pb.customer.birth_date)) as age
            from PersonalBook pb where pb.bookshelf.name = :bookshelf and pb.book.isbn = :isbn
    )
    select ageClass, amount from ageRange
    inner join (
    select
        width_bucket(age, array[0,13,18,31,61]) as bucket, count(*) as amount
        from ages
        group by bucket
    ) on ageRange.id=bucket;
