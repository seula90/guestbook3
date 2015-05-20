package com.sds.icto.guestbookDao;



import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.sds.icto.guestbookVo.GuestBookVo;
		
@Repository
public class GuestBookDao {
	
	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;
	
	public void insert(GuestBookVo vo) {
		sqlMapClientTemplate.insert("guestbook.insert",vo);
	}
	
	public void delete(GuestBookVo vo) {
		sqlMapClientTemplate.delete("guestbook.delete",vo);
	}

	public List<GuestBookVo> fetchList() {
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		
		list = sqlMapClientTemplate.queryForList("guestbook.list");
		
		return list;
	}
}
