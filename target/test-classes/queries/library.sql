select count(id)
from users; -- actual

select count(distinct id)
from users; -- expected

select id,name,author from books
where name = 'Clean Code' and author='Robert C.Martin'
order by id desc;
