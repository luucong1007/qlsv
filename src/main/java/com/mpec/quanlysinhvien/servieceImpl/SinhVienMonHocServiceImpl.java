package com.mpec.quanlysinhvien.servieceImpl;

import com.mpec.quanlysinhvien.entities.MonHoc;
import com.mpec.quanlysinhvien.entities.SinhVienMonHoc;
import com.mpec.quanlysinhvien.entities.TheSinhVien;
import com.mpec.quanlysinhvien.repository.MonHocRepo;
import com.mpec.quanlysinhvien.repository.SinhVienMonHocRepo;
import com.mpec.quanlysinhvien.service.SinhVienMonHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public Optional<SinhVienMonHoc> save(SinhVienMonHoc sinhVienMonHoc) {
        try {
            return Optional.ofNullable(sinhVienMonHocRepo.save(sinhVienMonHoc));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<SinhVienMonHoc> findByID(int id) {
        try {
            return sinhVienMonHocRepo.findById(id);

        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<SinhVienMonHoc> update(SinhVienMonHoc sinhVienMonHoc) {
        try{
            Optional<SinhVienMonHoc> sinhVienMonHocOptional = findByID(sinhVienMonHoc.getId());
            if(!sinhVienMonHocOptional.isPresent()){
                return Optional.empty();
            }
            SinhVienMonHoc tsv = sinhVienMonHocOptional.get();
            return Optional.ofNullable(sinhVienMonHocRepo.save(tsv));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
