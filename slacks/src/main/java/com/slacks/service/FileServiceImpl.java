package com.slacks.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.slacks.dao.FileDAO;
import com.slacks.utils.FileBoardList;

@Service("fileService")
public class FileServiceImpl implements FileService{
	@Resource(name = "fileDAO")
	private FileDAO fileDAO;
	
	@Override
	public FileBoardList fileBoardList(FileBoardList fileBoardList) throws Exception{
		FileBoardList fileBoard = fileDAO.findBoardList(fileBoardList);
		
		fileBoardList.setSender(fileBoard.getSender());
		fileBoardList.setPath(fileBoard.getPath());
		fileBoardList.setSender(fileBoard.getSender());
		
		return fileBoardList;
	}
}
