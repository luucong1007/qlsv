package com.mpec.quanlysinhvien.controller;

import com.mpec.quanlysinhvien.dto.TheSinhVienDTO;
import com.mpec.quanlysinhvien.entities.Sach;
import com.mpec.quanlysinhvien.entities.SinhVien;
import com.mpec.quanlysinhvien.entities.TheSinhVien;
import com.mpec.quanlysinhvien.service.SinhVienService;
import com.mpec.quanlysinhvien.service.TheSinhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/the-sinh-vien")
public class TheSinhVienController {
    @Autowired
    TheSinhVienService theSinhVienService;

    @Autowired
    SinhVienService sinhVienService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAllTheSinhVien(){

        Pageable pageable = PageRequest.of(0,10);
        Page<TheSinhVien> theSinhViens = theSinhVienService.findAll(pageable);
        return ResponseEntity.ok(theSinhViens);
    }
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(name = "text") String text,
                                    @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                                    @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<TheSinhVien> theSinhViens = theSinhVienService.search(text, pageable);
        return ResponseEntity.ok(theSinhViens);
    }

    @PostMapping("/find/{id}/{xoa}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") int id,
                                      @PathVariable(name = "xoa") boolean xoa) {
        Optional<TheSinhVien> theSinhVienOptional = theSinhVienService.findById(id, xoa);
        if (theSinhVienOptional.isPresent()) {
            return ResponseEntity.ok(theSinhVienOptional.get());
        } else {
            return ResponseEntity.ok(Optional.empty());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody TheSinhVien theSinhVien,
                                  @RequestParam("sinh_vien_id") int sinhVienId,
                                  @RequestParam("xoa") Boolean xoa) {
        return sinhVienService.findById(sinhVienId, xoa)
                .map(sinhVien1 -> {
                    theSinhVien.setSinhVien(sinhVien1);
                    theSinhVienService.save(theSinhVien);
                    return ResponseEntity.ok("success");
                }).orElse(ResponseEntity.ok("false"));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam("sinh_vien_id") int sinhVienId,
                                    @RequestParam("xoa") Boolean xoa,
//                                    @RequestParam("ma_the") String maThe,
//                                    @RequestParam("the_id") int theId){
                                    @RequestBody TheSinhVienDTO theSinhVienDTO){
        return theSinhVienService.findById(theSinhVienDTO.getId(), xoa)
                .map(theSinhVien -> {
                    return  sinhVienService.findById(sinhVienId,xoa)
                            .map(sinhVien -> {
                                theSinhVien.setMaThe(theSinhVienDTO.getMaThe());
                                theSinhVien.setSinhVien(sinhVien);
                                theSinhVienService.save(theSinhVien);
                                return ResponseEntity.ok("success");
                            }).orElse(ResponseEntity.ok("SinhVienID not found"));
                }).orElse(ResponseEntity.ok("false"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        return theSinhVienService.delete(id)
                ? ResponseEntity.ok("success") : ResponseEntity.ok("failed");
    }

//    @RequestMapping("/search/{id}")
//    ResponseEntity<?> findByTheSinhVien (@PathVariable("id") String maThe){
//
//        Pageable pageable = PageRequest.of(0,10);
//        Page<TheSinhVien> theSinhViens = theSinhVienService.findByName(maThe,pageable);
//        return ResponseEntity.ok(theSinhViens);
//    }
}
