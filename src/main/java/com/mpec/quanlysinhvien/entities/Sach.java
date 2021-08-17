package com.mpec.quanlysinhvien.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Where(clause = "xoa = false") // truyền mặc định bằng false
@Builder
@Table(name = "sach", schema = "quan_ly_sinh_vien")
public class Sach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ten_sach")
    private String tenSach;

    @Column(name = "ngay_muon")
    private String ngayMuon;

    @ManyToOne                              // quan hệ 1 sinh viên mượn nhiều sách many sách--1 SV
    @JoinColumn(name = "sinh_vien_id")      // liên kết với sinh vien id ở bảng sinh vien
    private SinhVien sinhVien;

    @Column(name = "xoa")
    private boolean xoa;
}
