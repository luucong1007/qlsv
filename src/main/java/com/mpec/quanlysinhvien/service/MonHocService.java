package com.mpec.quanlysinhvien.service;

import com.mpec.quanlysinhvien.entities.MonHoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface MonHocService {
    Page<MonHoc> findAll(Pageable pageable);

//    Page<MonHoc> findByName(String tenMonHoc, Pageable pageable);

    Page<MonHoc> search(String text, Float tinChi, Pageable pageable);

    Optional<MonHoc> findById(int id, boolean xoa);

    Optional<MonHoc> save(MonHoc monHoc);

    Optional<MonHoc> update(MonHoc monHoc);

    Boolean delete(int id);
}
