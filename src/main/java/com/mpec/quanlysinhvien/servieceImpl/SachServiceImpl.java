package com.mpec.quanlysinhvien.servieceImpl;

import com.mpec.quanlysinhvien.dto.SachDTO;
import com.mpec.quanlysinhvien.entities.MonHoc;
import com.mpec.quanlysinhvien.entities.Sach;
import com.mpec.quanlysinhvien.entities.SinhVien;
import com.mpec.quanlysinhvien.repository.MonHocRepo;
import com.mpec.quanlysinhvien.repository.SachRepo;
import com.mpec.quanlysinhvien.service.SachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SachServiceImpl implements SachService {
    @Autowired
    SachRepo sachRepo;

    @Override
    public Page<Sach> findAll(Pageable pageable) {
        try {
            return sachRepo.findAll(pageable);

        } catch (Exception ex) {
            return Page.empty();
        }
    }

    @Override
    public Page<Sach> search(String tenSach, Pageable pageable) {
        try {
            return sachRepo.search(tenSach, pageable);

        } catch (Exception ex) {
            return Page.empty();
        }
    }

    @Override
    public Optional<Sach> findById(int id, boolean xoa) {
        try {
            return sachRepo.findById(id, xoa);

        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Sach> save(Sach sach) {
        try {
            return Optional.ofNullable(sachRepo.save(sach));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Sach> update(SachDTO sach) {
        try{
            Optional<Sach> sachOptional = findById(sach.getId(), false);
            if(!sachOptional.isPresent()){
                return Optional.empty();
            }
            Sach s = sachOptional.get();
//            s.setTenSach(sach.getTenSach());
//            s.setNgayMuon(sach.getNgayMuon());
            return Optional.ofNullable(sachRepo.save(s));

        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public Boolean delete(int id) {
        try {
            return sachRepo.delete(id) >= 0;
        } catch (Exception ex) {
            return false;
        }
    }

//    @Override
//    public Page<Sach> findByName(String tenSach, Pageable pageable) {
//        try{
//            return sachRepo.findByName(tenSach,pageable);
//
//        }catch (Exception ex){
//            return Page.empty();
//        }
//    }
//
//    @Override
//    public Optional<Sach> findById(int id, boolean xoa) {
//        try{
//           return sachRepo.findById(id,xoa);
//
//        }catch (Exception ex){
//            return Optional.empty();
//        }
//    }
//

}
