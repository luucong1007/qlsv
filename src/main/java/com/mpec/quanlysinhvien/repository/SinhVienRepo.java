package com.mpec.quanlysinhvien.repository;

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

@Repository // giao tiep voi database lấy dữ liệu
public interface SinhVienRepo extends JpaRepository<SinhVien, Integer> {

    @Query("from SinhVien sv where sv.xoa = false ")
    Page<SinhVien> findAll(Pageable pageable);


//    @Query("from SinhVien sv where sv.xoa =false and sv.tenSinhVien=?1")
//    Page<SinhVien> findByName(@Param("tenSinhVien") String tenSinhVien, Pageable pageable);
//
//    @Query("from SinhVien sv where sv.xoa =false and sv.soDienThoai=?1 ")
//    Page<SinhVien> findByPhone(String soDienThoai, Pageable pageable);

    // query ... like concat

    @Query(value = "from SinhVien sv where (" +
            " sv.tenSinhVien like concat('%', ?1, '%') " +
            " or sv.maSinhVien like concat('%', ?1, '%') " +
            ")" +
            " and sv.xoa = false ")
    Page<SinhVien> search(String text, Pageable pageable);

    @Query(value = "from SinhVien  sv where  sv.id = ?1 and sv.xoa=?2 ")
    Optional<SinhVien> findById(int id, boolean xoa);


    @Modifying
    @Transactional
    @Query(value = "update SinhVien sv set sv.xoa = true where sv.id = ?1")
    Integer delete(int id);

}
