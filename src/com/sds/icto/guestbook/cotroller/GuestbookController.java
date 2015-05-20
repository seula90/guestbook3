package com.sds.icto.guestbook.cotroller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.icto.guestbookDao.GuestBookDao;
import com.sds.icto.guestbookVo.GuestBookVo;

@Controller
public class GuestbookController {


	@Autowired
	GuestBookDao guestBookDao;

	@RequestMapping(value={"","/","/list","/index"})
	public String index(Model model) {
		List<GuestBookVo> list = guestBookDao.fetchList();
		
		model.addAttribute("list", list);
				
		return "index";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute GuestBookVo vo) {
		
		guestBookDao.insert(vo);
		return "redirect:/index";
	}
	
	@RequestMapping(value={"/delete/{no}"}, method=RequestMethod.GET)
	public String deleteForm(@PathVariable Long no, Model model){
		model.addAttribute("no",no);
		return "deleteform";
	}
	
	@RequestMapping(value={"/delete"}, method=RequestMethod.POST)
	public String delete(@RequestParam("no") Long no,
			@RequestParam("pass") String pwd){
		
		GuestBookVo vo = new GuestBookVo();
		vo.setNo(no);
		vo.setPwd(pwd);
		
		guestBookDao.delete(vo);
		
		return "redirect:/index";
	}
	
	
}

