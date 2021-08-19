package com.mpec.quanlysinhvien.controller;

import com.mpec.quanlysinhvien.entities.SinhVien;
import com.mpec.quanlysinhvien.entities.SinhVienMonHoc;
import com.mpec.quanlysinhvien.service.MonHocService;
import com.mpec.quanlysinhvien.service.SinhVienMonHocService;
import com.mpec.quanlysinhvien.service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sinh-vien-mon-hoc")
public class SinhVienMonHocController {
    @Autowired
    SinhVienMonHocService sinhVienMonHocService;

    @Autowired
    SinhVienService sinhVienService;

    @Autowired
    MonHocService monHocService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAllSinhVienMonHoc(){

        Pageable pageable = PageRequest.of(0,10);
        Page<SinhVienMonHoc> sinhVienMonHocs = sinhVienMonHocService.findAll(pageable);
        return ResponseEntity.ok(sinhVienMonHocs);
    }


    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody SinhVienMonHoc sinhVienMonHoc,
    @RequestParam("sinh_vien_id") int sinhVienId,
    @RequestParam("xoa")boolean xoa,
    @RequestParam("mon_hoc_id") int monHocId){
        return sinhVienService.findById(sinhVienId, xoa)
                .map(sinhVien -> {
                    sinhVienMonHoc.setSinhVien(sinhVien);
                    return monHocService.findById(monHocId, xoa)
                            .map(monHoc -> {
                                sinhVienMonHoc.setMonHoc(monHoc);
                                sinhVienMonHocService.save(sinhVienMonHoc);
                                return ResponseEntity.ok("success");
                            }).orElse(ResponseEntity.ok("mon hoc id not found"));
                }).orElse(ResponseEntity.ok("sinh vien id not found"));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam("sinh_vien_mon_hoc_id")int sinhVienMonHocId,
                                    @RequestParam("sinh_vien_id")int sinhVienId,
                                    @RequestParam("mon_hoc_id")int monHocId,
                                    @RequestParam("ngay_tao")String ngayTao,
                                    @RequestParam("xoa")boolean xoa){
        return sinhVienMonHocService.findByID(sinhVienMonHocId)
                .map(sinhVienMonHoc -> {
                    return sinhVienService.findById(sinhVienId, xoa)
                            .map(sinhVien -> {
                                return monHocService.findById(monHocId, xoa)
                                                .map(monHoc -> {
                                                    sinhVienMonHoc.setMonHoc(monHoc);
                                                    sinhVienMonHoc.setSinhVien(sinhVien);
                                                    sinhVienMonHoc.setNgayTao(ngayTao);
                                                    sinhVienMonHoc.setXoa(xoa);
                                                    sinhVienMonHocService.save(sinhVienMonHoc);
                                                    return ResponseEntity.ok("success");
                            }).orElse(ResponseEntity.ok("mon hoc id not found"));
                }).orElse(ResponseEntity.ok("sinh vien id not found"));
        }).orElse(ResponseEntity.ok("sinh vien mon hoc id not found"));
    }

}
