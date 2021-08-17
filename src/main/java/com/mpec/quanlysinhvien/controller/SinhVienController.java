package com.mpec.quanlysinhvien.controller;

import com.mpec.quanlysinhvien.entities.MonHoc;
import com.mpec.quanlysinhvien.entities.SinhVien;
import com.mpec.quanlysinhvien.entities.SinhVienDTO;
import com.mpec.quanlysinhvien.repository.SinhVienRepo;
import com.mpec.quanlysinhvien.service.SinhVienService;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController // lấy dl trả về
@RequestMapping("/api/v1/sinh-vien")
public class SinhVienController {
    @Autowired
    SinhVienService sinhVienService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAllSinhVien(){

        Pageable pageable = PageRequest.of(0,10);
        Page<SinhVien> sinhViens = sinhVienService.findAll(pageable);
        return ResponseEntity.ok(sinhViens);

    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name = "text") String text,
                                    @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                    @RequestParam(name = "size", defaultValue = "10", required = false) Integer size){
        Pageable pageable =PageRequest.of(page -1, size);
        Page<SinhVien> sinhViens = sinhVienService.search(text,pageable);
        return ResponseEntity.ok(sinhViens);
    }

    @PostMapping("/find/{id}/{xoa}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") int id,
                                      @PathVariable(name = "xoa") boolean xoa){
        Optional<SinhVien> sinhVienOptional = sinhVienService.findById(id, xoa);
        if(sinhVienOptional.isPresent()){
            return ResponseEntity.ok(sinhVienOptional.get());
        }else {
            return ResponseEntity.ok(Optional.empty());
        }
    }

    @PostMapping("/save")
    public SinhVien save(@RequestBody SinhVien sv){
        Optional<SinhVien> sinhVien = sinhVienService.save(sv);
        if(sinhVien.isPresent()){
            return sinhVien.get();
        }else {
            return null;
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody SinhVienDTO sv){
        Optional<SinhVien> sinhVien = sinhVienService.update(sv);
        if(sinhVien.isPresent()){
            return ResponseEntity.ok("success");
        }else {
            return ResponseEntity.ok("failed");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        return sinhVienService.delete(id)
                ? ResponseEntity.ok("success") : ResponseEntity.ok("failed");
    }





//    @RequestMapping("/search/name/{name}")
//    ResponseEntity<?> findBySinhVien(@PathVariable("name") String tenSinhVien){
//
//        Pageable pageable = PageRequest.of(0,10);
//        Page<SinhVien> sinhViens = sinhVienService.findByName(tenSinhVien,pageable);
//        return ResponseEntity.ok(sinhViens);
//    }
//
//    @RequestMapping("/search/sdt/{sdt}")
//    ResponseEntity<?> findBySoDienThoai(@PathVariable("sdt") String soDienThoai){
//
//        Pageable pageable = PageRequest.of(0,10);
//        Page<SinhVien> sinhViens = sinhVienService.findByPhone(soDienThoai,pageable);
//        return ResponseEntity.ok(sinhViens);
//    }


}
