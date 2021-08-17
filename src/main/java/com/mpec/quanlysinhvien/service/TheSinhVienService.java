package com.mpec.quanlysinhvien.service;

import com.mpec.quanlysinhvien.dto.TheSinhVienDTO;
import com.mpec.quanlysinhvien.entities.MonHoc;
import com.mpec.quanlysinhvien.entities.Sach;
import com.mpec.quanlysinhvien.entities.SinhVien;
import com.mpec.quanlysinhvien.entities.TheSinhVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TheSinhVienService {
    Page<TheSinhVien> findAll(Pageable pageable);

//    Page<TheSinhVien> findByName(String maThe, Pageable pageable);

    Page<TheSinhVien> search(String maThe, Pageable pageable);

    Optional<TheSinhVien> findById(int id, boolean xoa);

    Optional<TheSinhVien> save(TheSinhVien theSinhVien);

    Optional<TheSinhVien> update(TheSinhVienDTO theSinhVien);

    Boolean delete(int id);

}
