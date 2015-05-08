create table 
if not exists Book(
	book_id int primary key,
	title varchar(50),
	author varchar(50),
	genre varchar(50),
	isbn varchar(50),
	blurb varchar(250)
);