package com.mpec.quanlysinhvien.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "the_sinh_vien", schema = "quan_ly_sinh_vien")
public class TheSinhVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne // quan hệ 1 sinh viên mượn nhiều sách many sách--1 SV
    @JoinColumn(name = "sinh_vien_id") // liên kết với sinh vien id ở bảng sinh vien
    private SinhVien sinhVien;


    @Column(name = "ma_the")
    private String maThe;

    @Column(name = "xoa")
    private boolean xoa;
}
