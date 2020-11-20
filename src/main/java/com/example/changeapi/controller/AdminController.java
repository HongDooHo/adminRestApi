package com.example.changeapi.controller;

import com.example.changeapi.ifs.CrudInterface;
import com.example.changeapi.model.entity.Admin;
import com.example.changeapi.network.Header;
import com.example.changeapi.network.request.AdminRequest;
import com.example.changeapi.network.response.AdminResponse;
import com.example.changeapi.repository.AdminRepository;
import com.example.changeapi.service.AdminApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:8080")
public class AdminController implements CrudInterface<AdminRequest, AdminResponse> {

    @Autowired
    private AdminApiLogicService adminApiLogicService;

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/all")
    public List<Admin> findAll() {
        List<Admin> admins = new ArrayList<>();

        adminRepository.findAll().forEach(e -> admins.add(e));
        return admins;
    }

    @Override
    @PostMapping("")
    public Header<AdminResponse> create(@RequestBody Header<AdminRequest> request) {
        return adminApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<AdminResponse> read(@PathVariable(name = "id") Long id) {
        return adminApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<AdminResponse> update(@RequestBody Header<AdminRequest> request) {
        return adminApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return adminApiLogicService.delete(id);
    }
}
