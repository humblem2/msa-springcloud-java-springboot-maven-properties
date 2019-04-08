package com.example.user.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.article.domain.PatientDTO;
import com.example.user.service.PatientDAO;
import com.example.user.service.PatientService;

@Service("patientServiceImpl")
public class PatientServiceImpl implements PatientService{
	private final Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);
	@Autowired
	@Qualifier("patientDAOImpl")
	private PatientDAO patientDAO;
	
	@Override
	public List<PatientDTO> patientlist() throws Exception {
		return patientDAO.patientlist();
	}
}
