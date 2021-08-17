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
@Table(name = "sinh_vien_mon_hoc", schema = "quan_ly_sinh_vien")
public class SinhVienMonHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "sinh_vien_id")
    private int sinhVienId;

    @Column(name = "mon_hoc_id")
    private int monHocId;

    @Column(name = "ngay_tao")
    private String ngayTao;

    @Column(name = "xoa")
    private boolean xoa;
}
