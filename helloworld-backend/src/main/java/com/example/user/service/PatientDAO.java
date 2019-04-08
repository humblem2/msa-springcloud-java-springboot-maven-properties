package com.example.user.service;

import java.util.List;

import com.example.article.domain.PatientDTO;

public interface PatientDAO {
	// SELECT LIST
	public List<PatientDTO> patientlist() throws Exception;
	
	// SELECT ONE
	//public BoardDTO boardOne(int board_seq) throws Exception;
	
	// UPDATE ONE
	//public int boardUpdate(BoardDTO boardDTO) throws Exception;
	
	// DELETE ONE
	//public int boardDelete(BoardDTO boardDTO) throws Exception;
	
	// INSERT ONE
	//public int boardAdd(BoardDTO boardDTO) throws Exception;
	
	// UPDATE HIT COUNT
	//public int hitCntUpdate(BoardDTO boardDTO) throws Exception;
	
	// 게시물 전체 목록
	//public List<BoardDTO> listAll(int start, int end, String searchOption, String keyword) throws Exception;
	
	// 게시물 전체 개수
	//public int countArticle(String searchOption, String keyword) throws Exception;
}
