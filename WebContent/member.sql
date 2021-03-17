create table mem_tbl_book(
	custno number(6) not null primary key,
	custname varchar2(20),
	joindate date,
	grade char(1) check(grade in('A','B','C')),
	address varchar2(60)
)

CREATE TABLE rent_tbl_book(
	custno number(6) not null,
	bookno number(4) not null,
	rentdate date,
	returndate date,
	primary key(custno,bookno)
)

SELECT * from MEM_TBL_BOOK;
SELECT * from MEM_TBL_BOOK where custno =  10001;


insert into mem_tbl_book values(10001,'박정희','2019-02-15','A','경기 분당구 서현동');
insert into mem_tbl_book values(10002,'최선한','2019-03-15','B','경기 군포시 산본동');
insert into mem_tbl_book values(10003,'김순애','2019-04-17','A','경기 군포시 산본동');
insert into mem_tbl_book values(10004,'최정현','2019-12-19','B','경기 분당구 정자동');
insert into mem_tbl_book values(10005,'김영림','2019-02-15','A','경기 분당구 정자동');
insert into mem_tbl_book values(10006,'박세영','2019-02-15','C','경기 용신시 용인동');

SELECT * from rent_tbl_book;

insert into rent_tbl_book values(10001,1234,'2019-02-15','2019-02-15');
insert into rent_tbl_book values(10001,1122,'2019-02-15','2019-02-16');
insert into rent_tbl_book values(10002,1234,'2019-03-15','2019-02-15');
insert into rent_tbl_book values(10003,1234,'2019-04-17','2019-02-15');
insert into rent_tbl_book values(10004,1122,'2019-12-19','2019-02-15');
insert into rent_tbl_book values(10005,1122,'2019-02-15','2019-02-15');
insert into rent_tbl_book values(10005,1113,'2019-02-15','2019-02-15');
insert into rent_tbl_book values(10005,1114,'2019-02-15','2019-02-15');
insert into rent_tbl_book values(10006,1113,'2019-02-15','2019-02-15');

SELECT MAX(custno) from mem_tbl_book