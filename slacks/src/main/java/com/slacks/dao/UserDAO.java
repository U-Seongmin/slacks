package com.slacks.dao;

import org.springframework.stereotype.Repository;

import com.slacks.common.dao.AbstactDAO;
import com.slacks.utils.AuthInfoPass;
import com.slacks.utils.FileDownload;
import com.slacks.utils.RegisterRequest;
import com.slacks.vo.UserVO;

@Repository("userDAO")
public class UserDAO extends AbstactDAO{
	public UserVO selectByEmail(String email) {
        return (UserVO)selectOne("user.selectByEmail", email);
    }
 
    public void insertUser(RegisterRequest regReq) {
        insert("user.register", regReq);
    }
    
    public UserVO selectByEmailFind(String email) {
        return (UserVO)selectOne("user.selectByEmailFind", email);
    }
    
    public void changePassword(AuthInfoPass infoPass) {
    	update("user.changePassword", infoPass);
    }

	public String findDirectory(FileDownload fileDownload) {
		String directory = select("user.findDirectory", );
		return directory;
	}
}
