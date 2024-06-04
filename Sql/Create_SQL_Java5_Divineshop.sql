USE master;
GO

ALTER DATABASE Java5_DivineShop SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
DROP DATABASE IF EXISTS Java5_DivineShop;
GO

CREATE DATABASE Java5_DivineShop;
GO

USE Java5_DivineShop;
GO

-- Tạo bảng Account
CREATE TABLE [Account] (
    id INT IDENTITY(1,1) PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    hash_password VARCHAR(255) NOT NULL,
    is_enabled BIT DEFAULT 1
);

-- Tạo bảng User
CREATE TABLE [User] (
    Sys_id_user INT IDENTITY(1,1) PRIMARY KEY,
    ten_dang_nhap NVARCHAR(255) NOT NULL UNIQUE,
    email NVARCHAR(255) NOT NULL UNIQUE,
    ho_va_ten NVARCHAR(255),
    so_du NVARCHAR(255),
    ngay_tham_gia DATE,
    anh_dai_dien NVARCHAR(MAX)
);

-- Tạo bảng Roles
CREATE TABLE [Roles] (
    username VARCHAR(50) NOT NULL,
    username_user NVARCHAR(255),
    role VARCHAR(50) NOT NULL,
    CONSTRAINT PK_authorities PRIMARY KEY (username, role, username_user),
    FOREIGN KEY (username) REFERENCES [Account] (username),
    FOREIGN KEY (username_user) REFERENCES [User] (ten_dang_nhap)
);


-- Tạo bảng Discount
CREATE TABLE [Discount] (
    Sys_id_discount INT IDENTITY(1,1) PRIMARY KEY,
    ten_giam_gia NVARCHAR(255),
    percent_giam_gia FLOAT,
    ngay_bat_dau DATE,
    ngay_ket_thuc DATE,
    mota NVARCHAR(255)
);

-- Tạo bảng Category
CREATE TABLE [Category] (
    Sys_id_category INT IDENTITY(1,1) PRIMARY KEY,
    ten_the_loai NVARCHAR(MAX)
);

-- Tạo bảng Product
CREATE TABLE [Product] (
    Sys_id_product INT IDENTITY(1,1) PRIMARY KEY,
    ma_san_pham NVARCHAR(255),
    ten_san_pham NVARCHAR(255),
    tinh_trang BIT,
    the_loai NVARCHAR(255),
    gia_san_pham FLOAT,
    percent_giam_gia FLOAT,
    anh_san_pham NVARCHAR(255),
    slug NVARCHAR(255) UNIQUE NOT NULL,
    danh_muc NVARCHAR(255),
    mota NVARCHAR(MAX),
    active_san_pham BIT DEFAULT 1,
    Sys_id_discount INT,
    soluong int,
    soluongmua int,
    soluotthich int
    FOREIGN KEY (Sys_id_discount) REFERENCES [Discount] (Sys_id_discount)
);

-- Tạo bảng CategoryDetail
CREATE TABLE [category_detail] (
    Sys_id_category INT NOT NULL,
    Sys_id_product INT NOT NULL,
    FOREIGN KEY (Sys_id_category) REFERENCES [Category] (Sys_id_category),
    FOREIGN KEY (Sys_id_product) REFERENCES [Product] (Sys_id_product),
    PRIMARY KEY (Sys_id_category, Sys_id_product)
);

-- Tạo bảng Order
CREATE TABLE [Order] (
    Sys_id_cart_payment INT IDENTITY(1,1) PRIMARY KEY,
    ma_don_hang NVARCHAR(255),
    ngay_lap_don DATE,
    trang_thai_thanh_toan BIT,
    tong_tien_san_pham FLOAT,
    tien_thanh_toan FLOAT,
    so_luong_mua INT,
    Sys_id_product INT,
    Sys_id_user INT,
    FOREIGN KEY (Sys_id_product) REFERENCES [Product] (Sys_id_product),
    FOREIGN KEY (Sys_id_user) REFERENCES [User] (Sys_id_user)
);
GO

-- Tạo trigger trg_InsertUserAfterAccountInsert
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
