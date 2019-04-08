package com.example.user.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.example.article.domain.PatientDTO;
import com.example.user.service.PatientDAO;

@Repository("patientDAOImpl")
public class PatientDAOImpl implements PatientDAO {
	private final Logger log = LoggerFactory.getLogger(PatientDAOImpl.class);
	@Autowired
	@Qualifier("sqlSessionTemplate")
    private SqlSession sqlSession;
	
	@Override
	public List<PatientDTO> patientlist() throws Exception {
		List<PatientDTO> list = sqlSession.selectList("com.example.PatientMapper.selectPatientList");
		return list;
	}
}
