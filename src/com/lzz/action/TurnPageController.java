package com.lzz.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("")
public class TurnPageController {
	/*
	 * ������ҳ��
	 */
	@RequestMapping("/{page}")
	public  String showPage(@PathVariable String page){
		return page;
	}
}
