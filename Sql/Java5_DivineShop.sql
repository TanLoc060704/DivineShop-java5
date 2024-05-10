use master
go

drop database if exists Java5_DivineShop
go

create database Java5_DivineShop
go

use Java5_DivineShop
go

create table [user]
(
    id          int primary key identity (1,1),
    username    varchar(63)  not null unique,
    email       varchar(63)  not null unique,
    is_admin    bit                   default 0,
    is_disable  bit                   default 0,
    fullname    nvarchar(63)          default 'fullname_val',
    gender      nvarchar(10)          default 'Nam',
    avatar      varchar(63)           default 'user.png',
    dob         date                  default getdate(),
    description nvarchar(300)         default 'description_val',
    job_title   nvarchar(63)          default 'Unemployed',
    role_id     int          not null default 3,
    role_name   nvarchar(63) not null default 'User',
    processed   bit                   default 0
);

CREATE TABLE Account
(
    user_id         int IDENTITY (1,1) PRIMARY KEY,
    username        nvarchar(63) NOT NULL unique,
    email           varchar(255) NOT NULL unique,
    hashed_password varchar(255) NOT NULL,
    is_disable      bit default 0,
    is_admin        bit
);