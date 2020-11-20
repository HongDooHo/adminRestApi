package com.example.changeapi.service;

import com.example.changeapi.ifs.CrudInterface;
import com.example.changeapi.model.entity.Admin;
import com.example.changeapi.network.Header;
import com.example.changeapi.network.request.AdminRequest;
import com.example.changeapi.network.response.AdminResponse;
import com.example.changeapi.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminApiLogicService implements CrudInterface<AdminRequest, AdminResponse> {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Header<AdminResponse> create(Header<AdminRequest> request) {

        AdminRequest body = request.getData();

        Admin admin = Admin.builder()
                .adminEmail(body.getAdminEmail())
                .userName(body.getUserName())
                .userEmail(body.getUserEmail())
                .donationPrice(body.getDonationPrice())
                .donationPlace(body.getDonationPlace())
                .donationDate(body.getDonationDate())
//                .createdAt(LocalDateTime.now())
//                .createdBy("SERVER")
                .build();

        Admin newAdmin = adminRepository.save(admin);

        return Header.OK(response(newAdmin));
    }

    @Override
    public Header<AdminResponse> read(Long id) {

        return adminRepository.findById(id)
                .map(admin -> response(admin))
                .map(adminResponse -> Header.OK(adminResponse))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<AdminResponse> update(Header<AdminRequest> request) {

        AdminRequest body = request.getData();

        Optional<Admin> optional = adminRepository.findById(body.getId());

        return optional.map(admin -> {
            admin.setAdminEmail(body.getAdminEmail());
            admin.setUserEmail(body.getUserEmail());
            admin.setUserName(body.getUserName());
            admin.setDonationPrice(body.getDonationPrice());
            admin.setDonationPlace(body.getDonationPlace());
            admin.setDonationDate(body.getDonationDate());

            return admin;
        })
                .map(admin -> adminRepository.save(admin))
                .map(updateAdmin -> response(updateAdmin))
                .map(newAdmin -> Header.OK(newAdmin))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

        Optional<Admin> optional = adminRepository.findById(id);

        return optional.map(admin -> {
            adminRepository.delete(admin);

            return Header.OK();
        })
                .orElseGet(() -> Header.ERROR("딜리트 실패"));
    }

    private AdminResponse response(Admin admin) {
        AdminResponse adminResponse = AdminResponse.builder()
                .id(admin.getId())
                .adminEmail(admin.getAdminEmail())
                .userName(admin.getUserName())
                .userEmail(admin.getUserEmail())
                .donationPrice(admin.getDonationPrice())
                .donationPlace(admin.getDonationPlace())
                .donationDate(admin.getDonationDate())
                .createdAt(admin.getCreatedAt())
                .cratedBy(admin.getCreatedBy())
                .build();

        return adminResponse;
    }
}
