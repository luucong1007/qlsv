package com.mpec.quanlysinhvien.service;

import com.mpec.quanlysinhvien.entities.MonHoc;
import com.mpec.quanlysinhvien.entities.SinhVien;
import com.mpec.quanlysinhvien.entities.SinhVienMonHoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface SinhVienMonHocService {
    Page<SinhVienMonHoc> findAll(Pageable pageable);
//    Page<MonHoc> findByName(String tenMonHoc, Pageable pageable);
}
