package com.mpec.quanlysinhvien.repository;

import com.mpec.quanlysinhvien.entities.MonHoc;
import com.mpec.quanlysinhvien.entities.Sach;
import com.mpec.quanlysinhvien.entities.SinhVien;
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
public interface SachRepo extends JpaRepository<Sach, Integer> {

    @Query("from Sach s where s.xoa = false ")
    Page<Sach> findAll(Pageable pageable);

//    @Query("from Sach s where s.xoa =false and s.tenSach like concat('%',?1,'%')")
//    Page<Sach> findByName(String  tenSach, Pageable pageable);

    @Query("from Sach s where s.xoa =false and s.tenSach like concat('%',?1,'%')")
    Page<Sach> search(String  tenSach, Pageable pageable);

    @Query(value = "from Sach  s where  s.id = ?1 and s.xoa=?2 ")
    Optional<Sach> findById(int id, boolean xoa);


    @Modifying
    @Transactional
    @Query(value = "update Sach s set s.xoa = true where s.id = ?1")
    Integer delete(int id);
}
