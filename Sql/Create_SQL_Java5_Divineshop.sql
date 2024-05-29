use master
go

drop database if exists Java5_DivineShop
go

create database Java5_DivineShop
go

use Java5_DivineShop
go

Create table [User](
    Sys_id_user int IDENTITY (1,1) primary key,
    ten_dang_nhap nvarchar(max),
    email nvarchar(max),
    ho_va_ten nvarchar(max),
    so_du nvarchar(max),
    ngay_tham_gia date,
    anh_dai_dien nvarchar(max)
)


Create table [Account](
    id              int IDENTITY (1,1) PRIMARY KEY,
    username        varchar(50) NOT NULL UNIQUE,
    email           varchar(255) NOT NULL UNIQUE,
    hash_password		varchar(255) NOT NULL,
    is_enabled			bit DEFAULT 1
)


Create table [Roles](
    username varchar(50) NOT NULL,
    role varchar(50) NOT NULL,
    CONSTRAINT PK_authorities PRIMARY KEY (username, role),
    FOREIGN KEY (username) REFERENCES account(username)
)

Create table [Product](
    Sys_id_product int IDENTITY (1,1) primary key,
    ma_san_pham nvarchar(max),
    ten_san_pham nvarchar(max),
    tinh_trang bit,
    the_loai nvarchar(max),
    gia_san_pham nvarchar(max),
    percent_giam_gia nvarchar(max),
    anh_san_pham nvarchar(max),
    slug nvarchar(max),
    danh_muc nvarchar(max),
    mota nvarchar(max),
    active_san_pham bit default 1
)

Create table [Cart](
    Sys_id_cart int IDENTITY (1,1) primary key,
    gia nvarchar(max),
    anh_dai_dien nvarchar(max),
    so_luong nvarchar(max),
    tien_thanh_toan nvarchar(max),
    percent_giam_gia nvarchar(max),
    tinh_trang bit,
    tong_tien_thanh_toan nvarchar(max)
)

Create table [Cart_payment](
    Sys_id_cart_payment int IDENTITY (1,1) primary key,
    ma_don_hang nvarchar(max),
    ngay_lap_don date,
    trang_thai_thanh_toan bit,
    ten_nguoi_mua nvarchar(max),
    tong_tien_san_pham nvarchar(max),
    anh_san_pham nvarchar(max),
    so_luong_mua nvarchar(max)
)

Create table [Category](
    Sys_id_category int IDENTITY (1,1) primary key,
    ten_the_loai nvarchar(max)
)

Create table [Discount](
    Sys_id_discount int IDENTITY (1,1) primary key,
    ten_giam_gia nvarchar(max),
    percent_giam_gia nvarchar(max),
    ngay_bat_dau date,
    ngay_ket_thuc date,
    mota nvarchar(max)
)
