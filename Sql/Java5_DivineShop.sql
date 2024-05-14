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

CREATE TABLE account
(
    id              int IDENTITY (1,1) PRIMARY KEY,
    username        varchar(50) NOT NULL UNIQUE,
    email           varchar(255) NOT NULL UNIQUE,
    hash_password		varchar(255) NOT NULL,
    is_enabled			bit DEFAULT 1
);
go
CREATE TABLE roles
(
    username varchar(50) NOT NULL,
    role varchar(50) NOT NULL,
    CONSTRAINT PK_authorities PRIMARY KEY (username, role),
    FOREIGN KEY (username) REFERENCES account(username)
);

-- Chèn dữ liệu vào bảng users
INSERT INTO account (username, email, hash_password, is_enabled)
VALUES
('john', 'john@gmail.com', '{bcrypt}$2y$10$sjnOuHMt7OQTXTODsPj6beOkyuNIVcI82kKosANwxXG.e9NuhgXlW', 1),
('mery', 'mery@gmail.com', '{bcrypt}$2y$10$sjnOuHMt7OQTXTODsPj6beOkyuNIVcI82kKosANwxXG.e9NuhgXlW', 1),
('susan', 'susan@gmail.com','{bcrypt}$2y$10$sjnOuHMt7OQTXTODsPj6beOkyuNIVcI82kKosANwxXG.e9NuhgXlW', 1);

-- Chèn dữ liệu vào bảng authorities
INSERT INTO roles (username, role)
VALUES 
('john', 'ROLE_EMPLOYEE'),
('mery', 'ROLE_EMPLOYEE'),
('mery', 'ROLE_MANAGER'),
('susan', 'ROLE_EMPLOYEE'),
('susan', 'ROLE_MANAGER'),
('susan', 'ROLE_ADMIN');

select * from account where username = 'susan'
select username,role from roles where username = 'susan'
