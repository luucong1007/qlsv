package com.mpec.quanlysinhvien.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "mon_hoc", schema = "quan_ly_sinh_vien")
public class MonHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "ten_mon_hoc")
    private String tenMonHoc;

    @Column(name = "ma_mon_hoc")
    private String maMonHoc;


    @Column(name = "so_tin_chi")
    private Float soTinChi;

    @Column(name = "xoa")
    private boolean xoa;

}
