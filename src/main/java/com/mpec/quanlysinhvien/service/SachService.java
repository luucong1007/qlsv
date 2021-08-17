package com.mpec.quanlysinhvien.service;

import com.mpec.quanlysinhvien.dto.SachDTO;
import com.mpec.quanlysinhvien.entities.MonHoc;
import com.mpec.quanlysinhvien.entities.Sach;
import com.mpec.quanlysinhvien.entities.SinhVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SachService {
    Page<Sach> findAll(Pageable pageable);

//    Page<Sach> findByName(String tenSach, Pageable pageable);
//
//    Optional<Sach> findById(int id, boolean xoa);
    Page<Sach> search(String tenSach, Pageable pageable);

    Optional<Sach> findById(int id, boolean xoa);

    Optional<Sach> save(Sach monHoc);

    Optional<Sach> update(SachDTO monHoc);

    Boolean delete(int id);


}
