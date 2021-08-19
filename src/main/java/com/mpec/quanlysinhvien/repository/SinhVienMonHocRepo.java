package com.mpec.quanlysinhvien.repository;

import com.mpec.quanlysinhvien.entities.MonHoc;
import com.mpec.quanlysinhvien.entities.SinhVien;
import com.mpec.quanlysinhvien.entities.SinhVienMonHoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SinhVienMonHocRepo extends JpaRepository<SinhVienMonHoc, Integer> {

    @Query("from SinhVienMonHoc svmh where svmh.xoa = false ")
    Page<SinhVienMonHoc> findAll(Pageable pageable);

//    @Query("from Sach s where s.xoa =false and s.tenSach=?1 ")
//    Page<Sach> findByName(String  tenSach, Pageable pageable);

    @Query("from SinhVienMonHoc s where s.xoa=false  and s.id= ?1 ")
    Optional<SinhVienMonHoc> findByID(int id);

}
