with ages as (
    select 
        extract('YEAR' from AGE(CURRENT_DATE, birth_date)) as age
            from customer as age
        )
    select
        width_bucket(age, array[13,18,31,61]) as bucket, count(*)
        from ages
        group by width_bucket;
