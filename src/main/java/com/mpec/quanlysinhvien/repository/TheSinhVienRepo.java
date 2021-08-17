package com.mpec.quanlysinhvien.repository;

import com.mpec.quanlysinhvien.entities.MonHoc;
import com.mpec.quanlysinhvien.entities.Sach;
import com.mpec.quanlysinhvien.entities.SinhVien;
import com.mpec.quanlysinhvien.entities.TheSinhVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TheSinhVienRepo extends JpaRepository<TheSinhVien, Integer> {

    @Query("from TheSinhVien tsv where tsv.xoa = false ")
    Page<TheSinhVien> findAll(Pageable pageable);

//    @Query("from TheSinhVien s where s.xoa =false and s.maThe=?1 ")
//    Page<TheSinhVien> findByName(String  maThe, Pageable pageable);

    @Query("from TheSinhVien s where s.xoa =false and s.maThe like concat('%',?1,'%')")
    Page<TheSinhVien> search(String  maThe, Pageable pageable);

    @Query(value = "from TheSinhVien  s where  s.id = ?1 and s.xoa=?2 ")
    Optional<TheSinhVien> findById(int id, boolean xoa);


    @Modifying
    @Transactional
    @Query(value = "update TheSinhVien s set s.xoa = true where s.id = ?1")
    Integer delete(int id);
}
