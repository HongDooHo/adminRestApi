package com.example.changeapi.repository;

import com.example.changeapi.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("SELECT SUM(a.donationPrice) FROM Admin a ")
    Integer selectTotal();
}
