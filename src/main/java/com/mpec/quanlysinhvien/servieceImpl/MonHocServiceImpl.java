package com.mpec.quanlysinhvien.servieceImpl;

import com.mpec.quanlysinhvien.entities.MonHoc;
import com.mpec.quanlysinhvien.entities.SinhVien;
import com.mpec.quanlysinhvien.entities.SinhVienDTO;
import com.mpec.quanlysinhvien.repository.MonHocRepo;
import com.mpec.quanlysinhvien.service.MonHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // xử lý + validate
public class MonHocServiceImpl implements MonHocService {
    @Autowired
    MonHocRepo monHocRepo;

    @Override
    public Page<MonHoc> findAll(Pageable pageable) {
        try{
            return monHocRepo.findAll(pageable);

        }catch (Exception ex){
            return Page.empty();
        }
    }

    @Override
    public Page<MonHoc> search(String text, Float tinChi, Pageable pageable) {
        try {
            return monHocRepo.search(text, tinChi, pageable);
        }
        catch (Exception ex){

            return Page.empty();
        }
    }

    @Override
    public Optional<MonHoc> findById(int id, boolean xoa) {
        try {
            return monHocRepo.findById(id,xoa);
        }
        catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<MonHoc> save(MonHoc monHoc) {
        try {
            monHoc.setXoa(false);
            return Optional.ofNullable(monHocRepo.save(monHoc));
        }catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<MonHoc> update(MonHoc monHoc) {
        try{
            Optional<MonHoc> monHocOptional = findById(monHoc.getId(), false);
            if(!monHocOptional.isPresent()){
                return Optional.empty();
            }
            MonHoc mh = monHocOptional.get();
            mh.setTenMonHoc(monHoc.getTenMonHoc());
            mh.setMaMonHoc(monHoc.getMaMonHoc());
            mh.setSoTinChi(monHoc.getSoTinChi());
            return Optional.ofNullable(monHocRepo.save(mh));
        }catch (Exception ex){
            return Optional.empty();
        }
    }

    @Override
    public Boolean delete(int id) {
        try{
            return monHocRepo.delete(id) >= 0 ;
        }catch (Exception ex){
            return false;
        }
    }


//    @Override
//    public Page<MonHoc> findByName(String tenMonHoc,Pageable pageable) {
//        try{
//            return monHocRepo.findByName(tenMonHoc,pageable);
//
//        }catch (Exception ex){
//            return Page.empty();
//        }
//    }
//


}
