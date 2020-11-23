package com.example.changeapi.repository;

import com.example.changeapi.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("SELECT SUM(a.donationPrice) FROM Admin a ")
    Integer selectTotal();

    @Query("SELECT a.donationPrice, a.donationDate FROM Admin a where a.donationDate=CURRENT_DATE")
    Integer todayTotal();
}
