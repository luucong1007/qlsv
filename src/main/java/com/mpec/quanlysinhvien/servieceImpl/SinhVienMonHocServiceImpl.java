package com.mpec.quanlysinhvien.servieceImpl;

import com.mpec.quanlysinhvien.entities.MonHoc;
import com.mpec.quanlysinhvien.entities.SinhVienMonHoc;
import com.mpec.quanlysinhvien.repository.MonHocRepo;
import com.mpec.quanlysinhvien.repository.SinhVienMonHocRepo;
import com.mpec.quanlysinhvien.service.SinhVienMonHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SinhVienMonHocServiceImpl implements SinhVienMonHocService {
    @Autowired
    SinhVienMonHocRepo sinhVienMonHocRepo;

    @Override
    public Page<SinhVienMonHoc> findAll(Pageable pageable) {
        try{
            return sinhVienMonHocRepo.findAll(pageable);

        }catch (Exception ex){
            return Page.empty();
        }
    }
}
