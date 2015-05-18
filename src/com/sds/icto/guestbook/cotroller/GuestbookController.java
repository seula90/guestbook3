package com.sds.icto.guestbook.cotroller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.icto.guestbookDao.GuestBookDao;
import com.sds.icto.guestbookVo.GuestBookVo;

@Controller
public class GuestbookController {


	@Autowired
	GuestBookDao guestBookDao;

	@RequestMapping("/index")
	public String index(Model model) {
		List<GuestBookVo> list = guestBookDao.fetchList();
		model.addAttribute("list", list);
		return "/views/index.jsp";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@RequestParam("name") String name,
			@RequestParam("pass") String pwd,
			@RequestParam("content") String msg) {

		GuestBookVo vo = new GuestBookVo();
		vo.setName(name);
		vo.setPwd(pwd);
		vo.setMsg(msg);

		guestBookDao.insert(vo);
		return "redirect:/index";
	}
}

