create database library;
use library;

create table book (
ID bigint PRIMARY KEY AUTO_INCREMENT,
title varchar(150) NOT NULL,
authors varchar(250) NOT NULL,
acquisition DATE NOT NULL,
pages SMALLINT,
year SMALLINT NOT NULL,
edition TINYINT, 
price DECIMAL(10,2)
);

desc book;