package com.capston.demo.repository;

import com.capston.demo.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findAll();
    List<File> findAllByUploadDate(LocalDateTime uploadDate);
    List<File> findAllByUploadDateBetween(LocalDateTime startDay, LocalDateTime endDay);

    @Query(value =
            "SELECT "+
                    "SUM(file.price) AS totalPrice " +
                    "FROM t_file file"
//                    "GROUP BY file.price"
            , nativeQuery = true
    )
    Optional<Integer> findFirstByOrderByPrice();
//    Optional<File> findFirstByOrderByPrice();
}
