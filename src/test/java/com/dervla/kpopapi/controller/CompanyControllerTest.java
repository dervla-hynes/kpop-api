package com.dervla.kpopapi.controller;

import com.dervla.kpopapi.entity.Company;
import com.dervla.kpopapi.exception.ResourceNotFoundException;
import com.dervla.kpopapi.repository.CompanyRepository;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest
class CompanyControllerTest {

	@Autowired
	CompanyController companyController;

	@MockBean
	CompanyRepository companyRepository;

	@Test()
	void getRequestReturnsAllCompanies() {
		when(companyRepository.findAll()).thenReturn(createSampleCompanies());
		List<Company> companies = companyController.getAllCompanies();
		assertThat(companies.size()).isEqualTo(2);
	}

	@Test()
	void getRequestReturnsOneCompany() throws ResourceNotFoundException {
		when(companyRepository.findById(1L)).thenReturn(createSampleCompany());
		ResponseEntity<Company> response = companyController.getCompanyById(1L);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getName()).isEqualTo("JYP");
	}

	private Optional<Company> createSampleCompany() {
		Company companyOne = Company.builder()
				.id(1L)
				.name("JYP")
				.creationDate(LocalDate.of(1990, 1, 1))
				.numberOfGroups(5)
				.numberOfMembers(20)
				.build();
		return Optional.of(companyOne);
	}

	private List<Company> createSampleCompanies() {
		Company companyOne = Company.builder()
				.id(1L)
				.name("JYP")
				.creationDate(LocalDate.of(1990, 1, 1))
				.numberOfGroups(5)
				.numberOfMembers(20)
				.build();
		Company companyTwo = Company.builder()
				.id(2L)
				.name("BigHit")
				.creationDate(LocalDate.of(1990, 2, 2))
				.numberOfGroups(1)
				.numberOfMembers(7)
				.build();
		return ImmutableList.of(companyOne, companyTwo);
	}

}
