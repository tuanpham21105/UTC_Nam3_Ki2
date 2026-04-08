-- ==============================
-- Create Database
-- ==============================
CREATE DATABASE DuLieu;
GO

USE DuLieu;
GO

-- ==============================
-- Create Table: tblChatLieu
-- ==============================
CREATE TABLE tblChatLieu (
    MaCL NVARCHAR(10) PRIMARY KEY,
    TenCL NVARCHAR(100) NOT NULL
);
GO

-- ==============================
-- Create Table: tblSanPham
-- ==============================
CREATE TABLE tblSanPham (
    MaSP NVARCHAR(10) PRIMARY KEY,
    TenSP NVARCHAR(100) NOT NULL,
    MaCL NVARCHAR(10) NOT NULL,
    MoTa NVARCHAR(255),
    GiaNhap DECIMAL(10,2),
    GiaBan DECIMAL(10,2),
    SoLuong INT,
    FOREIGN KEY (MaCL) REFERENCES tblChatLieu(MaCL)
);
GO

-- ==============================
-- Insert Data into tblChatLieu
-- ==============================
INSERT INTO tblChatLieu (MaCL, TenCL)
VALUES 
('CL01', N'Nhựa'),
('CL02', N'Kim loại'),
('CL03', N'Gỗ');
GO

-- ==============================
-- Insert Data into tblSanPham
-- ==============================
INSERT INTO tblSanPham (MaSP, TenSP, MaCL, MoTa, GiaNhap, GiaBan, SoLuong)
VALUES
('SP01', N'Ghế nhựa', 'CL01', N'Ghế làm từ nhựa cao cấp', 50000, 80000, 100),
('SP02', N'Bàn kim loại', 'CL02', N'Bàn sắt chắc chắn', 200000, 300000, 50),
('SP03', N'Tủ gỗ', 'CL03', N'Tủ gỗ tự nhiên', 500000, 750000, 20),
('SP04', N'Ghế gỗ', 'CL03', N'Ghế làm từ gỗ cao cấp', 50000, 80000, 0);
GO