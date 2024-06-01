use master
go

drop database if exists Java5_DivineShop
go

create database Java5_DivineShop
go

use Java5_DivineShop
go

Create table [Account](
    id              int IDENTITY (1,1) PRIMARY KEY,
    username        varchar(50) NOT NULL UNIQUE,
    email           varchar(255) NOT NULL UNIQUE,
    hash_password		varchar(255) NOT NULL,
    is_enabled			bit DEFAULT 1
)

Create table [User](
    Sys_id_user int IDENTITY (1,1) primary key,
    ten_dang_nhap nvarchar(255) NOT NULL UNIQUE,
    email nvarchar(255) NOT NULL UNIQUE,
    ho_va_ten nvarchar(255),
    so_du nvarchar(255),
    ngay_tham_gia date,
    anh_dai_dien nvarchar(max)
)



Create table [Roles](
    username varchar(50) NOT NULL,
    username_user nvarchar(255),
    role varchar(50) NOT NULL,
    CONSTRAINT PK_authorities PRIMARY KEY (username, role, username_user),
    FOREIGN KEY (username) REFERENCES account(username),
    FOREIGN KEY (username_user) REFERENCES [User](ten_dang_nhap)
)


Create table [Category](
    Sys_id_category int IDENTITY (1,1) primary key,
    ten_the_loai nvarchar(max)
)


Create table [Discount](
    Sys_id_discount int IDENTITY (1,1) primary key,
    ten_giam_gia nvarchar(255),
    percent_giam_gia float,
    ngay_bat_dau date,
    ngay_ket_thuc date,
    mota nvarchar(255)
)

Create table [Product](
    Sys_id_product int IDENTITY (1,1) primary key,
    ma_san_pham nvarchar(255),
    ten_san_pham nvarchar(255),
    tinh_trang bit,
    the_loai nvarchar(255),
    gia_san_pham float,
    percent_giam_gia float,
    anh_san_pham nvarchar(255),
    slug nvarchar(255),
    danh_muc nvarchar(255),
    mota nvarchar(max),
    active_san_pham bit default 1,
    Sys_id_discount int,
    FOREIGN KEY (Sys_id_discount) REFERENCES [Discount] (Sys_id_discount)
)

CREATE table [CategoryDetail](
    Sys_id_category int,
    Sys_id_product int,
    FOREIGN KEY (Sys_id_category) REFERENCES [Category] (Sys_id_category),
    FOREIGN KEY (Sys_id_product) REFERENCES [Product] (Sys_id_product),
)

Create table [Cart](
    Sys_id_cart int IDENTITY (1,1) primary key,
    Sys_id_user int UNIQUE,
    FOREIGN KEY (Sys_id_user) REFERENCES [User] (Sys_id_user)
)

Create table [Cart_payment](
    Sys_id_cart_payment int IDENTITY (1,1) primary key,
    ma_don_hang nvarchar(255),
    ngay_lap_don date,
    trang_thai_thanh_toan bit,
    tong_tien_san_pham float,
    tien_thanh_toan float,
    so_luong_mua int,
    Sys_id_cart int,
    Sys_id_product int,
    FOREIGN KEY (Sys_id_cart) REFERENCES [Cart] (Sys_id_cart),
    FOREIGN KEY (Sys_id_product) REFERENCES [Product] (Sys_id_product)
)




go
CREATE TRIGGER trg_InsertUserAfterAccountInsert
ON [Account]
AFTER INSERT
AS
BEGIN
    INSERT INTO [User] (ten_dang_nhap, email, ho_va_ten, so_du, ngay_tham_gia, anh_dai_dien)
    SELECT
        username AS ten_dang_nhap,
        email,
        NULL AS ho_va_ten,
        NULL AS so_du,
        GETDATE() AS ngay_tham_gia,
        NULL AS anh_dai_dien
    FROM
        inserted;
END;
GO