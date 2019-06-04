package com.slacks.dao;

import org.springframework.stereotype.Repository;

import com.slacks.common.dao.AbstractDAO;
import com.slacks.utils.FileBoardList;

@Repository("fileDAO")
public class FileDAO extends AbstractDAO{
	public FileBoardList findBoardList(FileBoardList fileBoardList) {
		return fileBoardList = (FileBoardList) selectOne("user.findList", fileBoardList);
	}
}
