package com.mpec.quanlysinhvien.controller;

import com.mpec.quanlysinhvien.entities.SinhVien;
import com.mpec.quanlysinhvien.entities.SinhVienMonHoc;
import com.mpec.quanlysinhvien.service.SinhVienMonHocService;
import com.mpec.quanlysinhvien.service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sinh-vien-mon-hoc")
public class SinhVienMonHocController {
    @Autowired
    SinhVienMonHocService sinhVienMonHocService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAllSinhVienMonHoc(){

        Pageable pageable = PageRequest.of(0,10);
        Page<SinhVienMonHoc> sinhVienMonHocs = sinhVienMonHocService.findAll(pageable);
        return ResponseEntity.ok(sinhVienMonHocs);

    }
}
