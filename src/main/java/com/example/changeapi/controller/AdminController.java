package com.example.changeapi.controller;

import com.example.changeapi.ifs.CrudInterface;
import com.example.changeapi.network.Header;
import com.example.changeapi.network.request.AdminRequest;
import com.example.changeapi.network.response.AdminResponse;
import com.example.changeapi.service.AdminApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController implements CrudInterface<AdminRequest, AdminResponse> {

    @Autowired
    private AdminApiLogicService adminApiLogicService;

    @GetMapping("/dd")
    public String test() {
        return "helo";
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
