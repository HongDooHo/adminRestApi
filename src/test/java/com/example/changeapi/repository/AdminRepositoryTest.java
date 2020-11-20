package com.example.changeapi.repository;

import com.example.changeapi.ChangeapiApplicationTests;
import com.example.changeapi.model.entity.Admin;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class AdminRepositoryTest extends ChangeapiApplicationTests {

    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void create() {
        Admin admin = Admin.builder()
                .userName("김정호")
                .userEmail("tt@naver.com")
                .donationPrice(2000)
                .donationPlace("서울시 강동구")
                .donationDate(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .adminEmail("test@naver.com")
                .createdBy("서버")
                .build();

        Admin newAdmin = adminRepository.save(admin);
        System.out.println(newAdmin);
    }
}