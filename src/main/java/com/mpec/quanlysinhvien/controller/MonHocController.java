package com.mpec.quanlysinhvien.controller;

import com.mpec.quanlysinhvien.dto.MonHocDTO;
import com.mpec.quanlysinhvien.entities.MonHoc;
import com.mpec.quanlysinhvien.service.MonHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/mon-hoc")
public class MonHocController {
    @Autowired
    MonHocService monHocService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAllMonHoc() {

        Pageable pageable = PageRequest.of(0, 10);
        Page<MonHoc> monHocs = monHocService.findAll(pageable);
        return ResponseEntity.ok(monHocs);
    }

    //    @RequestMapping("/search/{id}") // có thể truyền nhiều tham số /{id}/{xoa}
//    @RequestMapping("/search")
//    ResponseEntity<?> findById(@RequestParam("tenMonHoc") String tenMonHoc,
//                               @RequestParam("page") Integer page,  //có thể dùng default và require
//                               @RequestParam("size") Integer size) {
//
////        ResponseEntity<?> findByMonHoc (@PathVariable("id")  String tenMonHoc){ //gọn hơn
//
//        Pageable pageable = PageRequest.of(page - 1, size);
//        Page<MonHoc> monHocs = monHocService.search(tenMonHoc, pageable);
//        return ResponseEntity.ok(monHocs);
//    }

    @GetMapping("/search")
    ResponseEntity<?> search(@RequestParam (name = "text", required = false, defaultValue = "") String text,
                             @RequestParam(name = "tinChi", required = false) Float tinChi,
                             @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
                             @RequestParam(name = "size", defaultValue = "10", required = false) Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        Page<MonHoc> monHocs = monHocService.search(text,tinChi,pageable);
        return ResponseEntity.ok(monHocs);
    }

    @PostMapping("/find/{id}/{xoa}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") int id,
                                      @PathVariable(name = "xoa") boolean xoa){
        Optional<MonHoc> monHocOptional = monHocService.findById(id, xoa);
        if(monHocOptional.isPresent()){
            return ResponseEntity.ok(monHocOptional.get());
        }else {
            return ResponseEntity.ok(Optional.empty());
        }
    }

    @PostMapping("/save")
    public MonHoc save(@RequestBody MonHoc mh){
        Optional<MonHoc> monHoc = monHocService.save(mh);
        if(monHoc.isPresent()){
            return monHoc.get();
        }else {
            return null;
        }

    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody MonHocDTO mh){
        Optional<MonHoc> monHoc = monHocService.update(mh);
        if(monHoc.isPresent()){
            return ResponseEntity.ok("success");
        }else {
            return ResponseEntity.ok("failed");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        return monHocService.delete(id)
                ? ResponseEntity.ok("success") : ResponseEntity.ok("failed");
    }



}
