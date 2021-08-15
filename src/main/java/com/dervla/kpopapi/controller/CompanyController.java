package com.dervla.kpopapi.controller;


import com.dervla.kpopapi.entity.Company;
import com.dervla.kpopapi.exception.ResourceNotFoundException;
import com.dervla.kpopapi.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/kpop-api/company")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/")
    public List<Company> getAllCompanies() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable(value = "id") Long companyId) throws ResourceNotFoundException {
        Company company =
                companyService
                        .findById(companyId)
                        .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + companyId));
        return ResponseEntity.ok().body(company);
    }

    @PostMapping("/")
    public Company createCompany(@Valid @RequestBody Company company) {
        return companyService.save(company);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable(value = "id") Long companyId,
                                                 @Valid @RequestBody Company companyDetails)
            throws ResourceNotFoundException {
        Company company =
                companyService
                        .findById(companyId)
                        .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + companyId));
        company.setName(companyDetails.getName());
        company.setCreationDate(companyDetails.getCreationDate());
        company.setNumberOfGroups(companyDetails.getNumberOfGroups());
        company.setNumberOfMembers(companyDetails.getNumberOfMembers());

        final Company updatedCompany = companyService.save(company);
        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCompany(@PathVariable(value = "id") Long companyId) throws Exception {
        Company company =
                companyService
                        .findById(companyId)
                        .orElseThrow(() -> new ResourceNotFoundException("Company not found on :: " + companyId));
        companyService.delete(company);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
