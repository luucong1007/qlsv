package com.mpec.quanlysinhvien.repository;

import com.mpec.quanlysinhvien.entities.MonHoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MonHocRepo extends JpaRepository<MonHoc, Integer> {

    @Query("from MonHoc mh where mh.xoa = false ")
    Page<MonHoc> findAll(Pageable pageable);

//    @Query("from MonHoc mh where mh.xoa =false and mh.tenMonHoc like concat('%', ?1, '%')")
//    Page<MonHoc> findByName(String  tenMonHoc, Pageable pageable);

//    @Query("from  MonHoc mh where mh.xoa = false  and mh.id = ?1 ")
//    Optional<MonHoc> findByID(int id);

    @Query(value = " from MonHoc monHoc where 1=1 and " +
            " (?1 is null or monHoc.tenMonHoc like concat('%',?1,'%')) " +
            " and (?2 is null or  monHoc.soTinChi = ?2) " +
            " and monHoc.xoa = false ")
    Page<MonHoc> search(String text,Float tinChi,Pageable pageable);



    @Query(value = "from MonHoc  mh where  mh.id = ?1 and mh.xoa=?2 ")
    Optional<MonHoc> findById(int id, boolean xoa);

    @Modifying
    @Transactional
    @Query(value = "update MonHoc mh set mh.xoa = true where mh.id = ?1")
    Integer delete(int id);

}
