package com.dervla.kpopapi.rest;


import com.dervla.kpopapi.dao.CompanyRepository;
import com.dervla.kpopapi.entity.Company;
import com.dervla.kpopapi.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kpop-api")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/company")
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable(value = "id") Long companyId) throws ResourceNotFoundException {
        Company company =
                companyRepository
                        .findById(companyId)
                        .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + companyId));
        return ResponseEntity.ok().body(company);
    }

    @PostMapping("/company")
    public Company createCompany(@Valid @RequestBody Company company) {
        return companyRepository.save(company);
    }

    @PutMapping("/company/{id}")
    public ResponseEntity<Company> updateCompany(
            @PathVariable(value = "id") Long companyId, @Valid @RequestBody Company companyDetails)
            throws ResourceNotFoundException {
        Company company =
                companyRepository
                        .findById(companyId)
                        .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + companyId));
        company.setName(companyDetails.getName());
        company.setCreationDate(companyDetails.getCreationDate());
        company.setNumberOfGroups(companyDetails.getNumberOfGroups());
        company.setNumberOfMembers(companyDetails.getNumberOfMembers());

        final Company updatedCompany = companyRepository.save(company);
        return ResponseEntity.ok(updatedCompany);
    }


    @DeleteMapping("/company/{id}")
    public Map<String, Boolean> deleteCompany(@PathVariable(value = "id") Long companyId) throws Exception {
        Company company =
                companyRepository
                        .findById(companyId)
                        .orElseThrow(() -> new ResourceNotFoundException("Company not found on :: " + companyId));
        companyRepository.delete(company);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
