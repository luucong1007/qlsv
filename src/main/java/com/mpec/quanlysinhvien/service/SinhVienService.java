package com.mpec.quanlysinhvien.service;

import com.mpec.quanlysinhvien.entities.SinhVien;
import com.mpec.quanlysinhvien.dto.SinhVienDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SinhVienService {
    Page<SinhVien> findAll(Pageable pageable);

//    Page<SinhVien> findByName(String tenSinhVien, Pageable pageable);
//
//    Page<SinhVien> findByPhone(String soDienThoai, Pageable pageable);

    Page<SinhVien> search(String text, Pageable pageable);

    Optional<SinhVien> findById(int id, boolean xoa);

    Optional<SinhVien> save(SinhVien sinhVien);

    Optional<SinhVien> update(SinhVienDTO sinhVien);

    Boolean delete(int id);

}
