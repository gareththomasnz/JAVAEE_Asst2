create table book (title varchar(50), author varchar(50),  genre varchar(50));

create table book (title varchar(50), author varchar(50),  genre varchar(50));

create table book(book_id int primary key, title varchar(50), author varchar(50),  genre varchar(50), isbn varchar(50), blurb varchar(250));

insert into Book (BOOK_ID,TITLE, AUTHOR, GENRE, ISBN, BLURB) 
values (1,'Gone with the wind','Margaret Mitchell', 'Classic','1451635621','blah blah blah'); 

insert into Book (BOOK_ID,TITLE, AUTHOR, GENRE, ISBN, BLURB) 
values (2,'A Clock Work Orange','Anthony Burgess','Dystopian Novella','0393312836','blah blah blah'); 

insert into Book (BOOK_ID,TITLE, AUTHOR, GENRE, ISBN, BLURB) 
values (3,'2001: A Space Odyssey','Arthur C Clarke','Science Fiction','0451457994','blah blah blah'); 

insert into Book (BOOK_ID,TITLE, AUTHOR, GENRE, ISBN, BLURB) 
values (4,'Make Room! Make Room!','Harry Harrison', 'Science Fiction','0765318857','blah blah blah'); 

insert into Book (BOOK_ID,TITLE, AUTHOR, GENRE, ISBN, BLURB) 
values (5,'The Catcher in the Rye','JD Salinger', 'General','0316769177','blah blah blah');

select * from book;