package com.mpec.quanlysinhvien.servieceImpl;

import com.mpec.quanlysinhvien.entities.SinhVien;
import com.mpec.quanlysinhvien.entities.SinhVienDTO;
import com.mpec.quanlysinhvien.repository.SinhVienRepo;
import com.mpec.quanlysinhvien.service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SinhVienServiceImpl implements SinhVienService {

    @Autowired
    SinhVienRepo sinhVienRepo;

    @Override
    public Page<SinhVien> findAll(Pageable pageable) {
       try{
           return sinhVienRepo.findAll(pageable);

       }catch (Exception ex){
           return Page.empty();
        }
    }

    @Override
    public Page<SinhVien> search(String text, Pageable pageable) {
        try{
            return sinhVienRepo.search(text,pageable);

        }catch (Exception ex){
            return Page.empty();
        }
    }

    @Override
    public Optional<SinhVien> findById(int id, boolean xoa) {
        try {
            return sinhVienRepo.findById(id, xoa);
        }catch (Exception ex){
            return Optional.empty();
        }
    }

    @Override
    public Optional<SinhVien> save(SinhVien sinhVien) {

        try {
            sinhVien.setXoa(false);
            return  Optional.ofNullable(sinhVienRepo.save(sinhVien));

        }catch (Exception e){
        return Optional.empty();
        }
    }

    @Override
    public Optional<SinhVien> update(SinhVienDTO sinhVien) {
        try{
            Optional<SinhVien> sinhVienOptional = findById(sinhVien.getId(), false);
            if(!sinhVienOptional.isPresent()){
                return Optional.empty();
            }
            SinhVien sv = sinhVienOptional.get();
            sv.setDiaChi(sinhVien.getDiaChi());
            sv.setSoDienThoai(sinhVien.getSoDienThoai());
            return Optional.ofNullable(sinhVienRepo.save(sv));
        }catch (Exception ex){
            return Optional.empty();
        }
    }

    @Override
    public Boolean delete(int id) {
        try{
            return sinhVienRepo.delete(id) >= 0 ;
        }catch (Exception ex){
            System.out.printf(ex.getMessage());
            return false;
        }
    }
}


//    @Override
//    public Page<SinhVien> findByName(String tenSinhVien, Pageable pageable) {
//        try{
//            return sinhVienRepo.findByName(tenSinhVien,pageable);
//
//        }catch (Exception ex){
//            return Page.empty();
//        }
//    }
//
//    @Override
//    public Page<SinhVien> findByPhone(String soDienThoai, Pageable pageable) {
//        try{
//            return sinhVienRepo.findByPhone(soDienThoai, pageable);
//
//        }catch (Exception ex){
//            return Page.empty();
//        }
//    }



