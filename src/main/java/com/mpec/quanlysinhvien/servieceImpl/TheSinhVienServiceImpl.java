package com.mpec.quanlysinhvien.servieceImpl;

import com.mpec.quanlysinhvien.entities.TheSinhVien;
import com.mpec.quanlysinhvien.repository.TheSinhVienRepo;
import com.mpec.quanlysinhvien.service.TheSinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TheSinhVienServiceImpl implements TheSinhVienService {
    @Autowired
    TheSinhVienRepo theSinhVienRepo;

    @Override
    public Page<TheSinhVien> findAll(Pageable pageable) {
        try{
            return theSinhVienRepo.findAll(pageable);

        }catch (Exception ex){
            return Page.empty();
        }
    }

    @Override
    public Page<TheSinhVien> search(String maThe, Pageable pageable) {
        try{
            return theSinhVienRepo.search(maThe,pageable);

        }catch (Exception ex){
            return Page.empty();
        }
    }

    @Override
    public Optional<TheSinhVien> findById(int id, boolean xoa) {
        try {
            return theSinhVienRepo.findById(id, xoa);

        } catch (Exception ex) {
            return Optional.empty();
        }
    }


    @Override
    public Optional<TheSinhVien> save(TheSinhVien theSinhVien) {
        try {
            return Optional.ofNullable(theSinhVienRepo.save(theSinhVien));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<TheSinhVien> update(TheSinhVien theSinhVien) {
        try{
            Optional<TheSinhVien> theSinhVienOptional = findById(theSinhVien.getId(), false);
            if(!theSinhVienOptional.isPresent()){
                return Optional.empty();
            }
            return Optional.ofNullable(theSinhVienRepo.save(theSinhVien));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public Boolean delete(int id) {
        try {
            return theSinhVienRepo.delete(id) >= 0;
        } catch (Exception ex) {
            return false;
        }
    }

//    @Override
//    public Page<TheSinhVien> findByName(String maThe, Pageable pageable) {
//        try{
//            return theSinhVienRepo.findByName(maThe,pageable);
//
//        }catch (Exception ex){
//            return Page.empty();
//        }
//    }
}
