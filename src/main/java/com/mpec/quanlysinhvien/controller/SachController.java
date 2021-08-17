package com.mpec.quanlysinhvien.controller;

import com.mpec.quanlysinhvien.dto.SachDTO;
import com.mpec.quanlysinhvien.entities.MonHoc;
import com.mpec.quanlysinhvien.entities.Sach;
import com.mpec.quanlysinhvien.entities.SinhVien;
import com.mpec.quanlysinhvien.service.SachService;
import com.mpec.quanlysinhvien.service.SinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/sach")
public class SachController {
    @Autowired
    SachService sachService;

    @Autowired
    SinhVienService sinhVienService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAllSach() {

        Pageable pageable = PageRequest.of(0, 10);
        Page<Sach> sachs = sachService.findAll(pageable);
        return ResponseEntity.ok(sachs);
    }

    //    @GetMapping("/search/{id}")
//    ResponseEntity<?> search(@PathVariable("id") String tenSach){
//        Pageable pageable = PageRequest.of(0,10);
//        Page<Sach> saches = sachService.search(tenSach,pageable);
//        return ResponseEntity.ok(saches);
//    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name = "text") String text,
                                    @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                    @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Sach> saches = sachService.search(text, pageable);
        return ResponseEntity.ok(saches);
    }

    @PostMapping("/find/{id}/{xoa}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") int id,
                                      @PathVariable(name = "xoa") boolean xoa) {
        Optional<Sach> sachOptional = sachService.findById(id, xoa);
        if (sachOptional.isPresent()) {
            return ResponseEntity.ok(sachOptional.get());
        } else {
            return ResponseEntity.ok(Optional.empty());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Sach sach,
                                  @RequestParam("sinh_vien_id") int sinhVienId,
                                  @RequestParam("xoa") Boolean xoa) {
        return sinhVienService.findById(sinhVienId, xoa)
                .map(sinhVien1 -> {
                    sach.setSinhVien(sinhVien1);
                    sachService.save(sach);
                    return ResponseEntity.ok("success");
                }).orElse(ResponseEntity.ok("false"));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam("sinh_vien_id") int sinhVienId,
                                    @RequestParam("xoa") Boolean xoa,
//                                    @RequestParam("ten_sach") String tenSach,
//                                    @RequestParam("ngay_muon")String ngayMuon,
//                                    @RequestParam("sach_id") int sachId){
                                    @RequestBody SachDTO s){
        return sachService.findById(s.getId(), xoa)
                .map(sach -> {
                    return  sinhVienService.findById(sinhVienId,xoa)
                                    .map(sinhVien -> {
                                        sach.setNgayMuon(s.getNgayMuon());
                                        sach.setTenSach(s.getTenSach());
                                        sach.setSinhVien(sinhVien);
                                        sachService.save(sach);
                                        return ResponseEntity.ok("success");
                                    }).orElse(ResponseEntity.ok("SinhVienID not found"));
                }).orElse(ResponseEntity.ok("false"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        return sachService.delete(id)
                ? ResponseEntity.ok("success") : ResponseEntity.ok("failed");
    }
}
