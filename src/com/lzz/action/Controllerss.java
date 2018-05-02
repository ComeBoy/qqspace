package com.lzz.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lzz.bean.Feel;
import com.lzz.bean.Friendmap;
import com.lzz.bean.Message;
import com.lzz.bean.Speak;
import com.lzz.bean.User;
import com.lzz.dao.FeelDao;
import com.lzz.dao.MessageDao;
import com.lzz.dao.SpeakDao;
import com.lzz.dao.UserDao;

@Controller
@RequestMapping
public class Controllerss {

	@Autowired
	private UserDao userDao;
	@Autowired
	private FeelDao feeldDao;
	@Autowired
	private MessageDao messageDao;
	@Autowired
	private SpeakDao speakDao;

	@RequestMapping("/go")
	public String go() {
		return "login";
	}

	@RequestMapping("/zhucess")
	public String ceshi(Model model, HttpSession session) {
		String qq = (String) session.getAttribute("qq");
		User selectQq = userDao.selectQq(qq);
		model.addAttribute("user", selectQq);
		return "register";
	}
	// 登陆
	@RequestMapping("/login")
	public ModelAndView logins(User user,  HttpSession session) {
		ModelAndView mav=new ModelAndView();
		ModelAndView mav1=new ModelAndView();
		String qq = user.getQq();
		String password = user.getPassword();
		User loginss = userDao.loginss(qq, password);// 账号密码
		session.setAttribute("qq", qq);
		User user1 = userDao.selectQq(qq);
		session.setAttribute("user",user1);//通过登陆的qq插查询这个user
		mav.setViewName("redirect:/loginselect");
		mav1.setViewName("redirect:/go");
		if (loginss != null) {
			return mav;
		}
		return mav1;
	}
	//登陆后查询
	@RequestMapping("/loginselect")
	public ModelAndView loginsSelect(HttpSession session,Model model) {
		ModelAndView mav=new ModelAndView();
		String qq = (String) session.getAttribute("qq");
		User selectQq = userDao.selectQq(qq);// 根据qq查询这个qq的所有信息
		session.setAttribute("qqid", selectQq.getUserid());//session来存储登陆着qq的id
		List<Feel> selectFeel = feeldDao.selectFeel(selectQq.getUserid());// 根据登陆者的id查询登陆着有多少条说说
		session.setAttribute("feelLength", selectFeel.size());// 说说的条数
		String df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());// 设置日期格式
		session.setAttribute("datetime", df);
		model.addAttribute("datetime", df);
		model.addAttribute("user", selectQq);
		model.addAttribute("totalfeels", selectFeel.size());
		mav.setViewName("index");
		return mav;
	}

	// 注册
	@RequestMapping("/register")
	public String regist(User user) {
		String qq = (System.currentTimeMillis() + "").substring(3, 13);
		user.setQq(qq);
		user.setState("1");// 注册好之后状态为1（正常使用）
		String df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		user.setLastvisit(df);
		user.setHeadpic("defaultheadpic.jpg");
		userDao.insertuser(user);
		return "register_suc";
	}

	// 个人资料
	@RequestMapping("/grzl")
	public String selectUser(Model model, HttpSession session) {
		String qq = (String) session.getAttribute("qq");
		String df = (String) session.getAttribute("datetime");
		Integer feelLength = (Integer) session.getAttribute("feelLength");// 查询说说的条数
		model.addAttribute("totalfeels", feelLength);
		model.addAttribute("datetime", df);
		User selectQq = userDao.selectQq(qq);
		model.addAttribute("user", selectQq);
		return "index";
	}

	// 自己发表说说
	@RequestMapping("/speak")
	private ModelAndView addFeel(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String qq = (String) session.getAttribute("qq");
		User user = userDao.selectQq(qq);// 查询到qq主人的所有信息
		Feel feel = new Feel();
		feel.setFeelcontent(request.getParameter("feelcontent"));
		feel.setUserid(user.getUserid());
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = df.format(day);
		feel.setFeeltime(format);
		feel.setPic("logo.jpg");
		feeldDao.insertFeel(feel);// 添加
		mav.setViewName("redirect:/queryFeels");
		System.out.println("跳到查询所有页面了");
		return mav;
	}

	// 查询自己的说说
	@RequestMapping("/queryFeel")
	private ModelAndView queryFeel(HttpSession session, Model model) {
		ModelAndView mav = new ModelAndView();
		String qq = (String) session.getAttribute("qq");
		User user = userDao.selectQq(qq);
		List<Feel> selectFeel = feeldDao.selectFeel(user.getUserid());// 根据qq的id来查询Feel表的所有信息
		Collections.reverse(selectFeel);// 让说说倒叙显示
		mav.addObject("user", user);
		mav.addObject("feel", selectFeel);
		mav.setViewName("feelmy");
		return mav;
	}

	// 查询全部说说
	@RequestMapping("/queryFeels")
	private ModelAndView queryFeels(HttpSession session, Model model) {
		ModelAndView mav = new ModelAndView();
		String qq = (String) session.getAttribute("qq");
		User host = userDao.selectQq(qq);
		List<Feel> selectFeels = feeldDao.selectFeels();
		List<User> list = userDao.selectAll();
		Collections.reverse(selectFeels);// 让说说倒叙显示
		mav.addObject("userlist", list);
		mav.addObject("feel", selectFeels);
		mav.addObject("user", host);
		List<User> list1 = userDao.selectAll();
		List<Speak> selectSpeak = speakDao.selectSpeak();
		System.out.println(selectSpeak);
		mav.addObject("speak", selectSpeak);//查询speak表
		mav.addObject("userlist", list1);//查询所有用户表
		mav.setViewName("indexmy");
		return mav;
	}

	// 删除说说
	@RequestMapping(path = "delectFeel/{feelid}")
	public ModelAndView delectFeel(@PathVariable Integer feelid) {
		ModelAndView mav = new ModelAndView();
		feeldDao.delectFeel(feelid);
		mav.setViewName("redirect:/queryFeel");
		return mav;
	}

	// 跳转到修改头像页面
	@RequestMapping("/updateheadpic")
	public ModelAndView updateheadpic(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String qq = (String) session.getAttribute("qq");
		User user = userDao.selectQq(qq);// 查询到qq主人的所有信息
		mav.addObject("user", user);
		mav.setViewName("updateheadpic");
		return mav;
	}

	// 修改头像并跳转到index页面
	@RequestMapping("/update")
	private ModelAndView updateHeadpic(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String host = (String) session.getAttribute("qq");
		String path = request.getSession().getServletContext().getRealPath("res/headpic");
		String fileName = file.getOriginalFilename(); // 获得图片名字
		File targetFile = new File(path, fileName);//文件的名字和路径
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
			User user = new User();
			user.setQq(host);
			user.setHeadpic(fileName);
			if (userDao.updateFile(user)) {
				mav.addObject("modify_suc", "修改成功");
				User user1 = userDao.selectQq(host);
				session.setAttribute("user", user1);
				mav.setViewName("forward:/grzl");
			} else {
				mav.addObject("modify_fail", "修改失败。原因：内部服务器正在维护，请稍后再进行修改");
				mav.setViewName("updateheadpic"); // 若注册失败转到修改页面重新修改
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	// message
	@RequestMapping("/leaveMessage")
	public ModelAndView insertMessage(HttpServletRequest request, HttpSession session, Message message) {
		ModelAndView mav = new ModelAndView();
		String qq = (String) session.getAttribute("qq");
		User selectQq = userDao.selectQq(qq);// 拿到留言者的所有信息
		int receiveid = selectQq.getUserid();// 留言者的id
		message.setReceiveid(receiveid);// 设置留言者的id
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = df.format(day);
		message.setMessagetime(format);// 设置留言的时间
		String parameter = request.getParameter("content");
		message.setContent(parameter);// 设置留内容
		message.setPic("1");
		message.setParentmessageid(0);// 设置回复留言时的父留言id
		messageDao.insertMassage(message);
		mav.addObject("user", selectQq);
		mav.addObject("message", message);
		mav.setViewName("forward:/jumpMessage");
		return mav;
	}
	//查询留言
	@RequestMapping("/jumpMessage")
	public ModelAndView selectMessage(HttpSession session, Message message) {
		ModelAndView mav = new ModelAndView();
		String qq = (String) session.getAttribute("qq");
		User selectQq = userDao.selectQq(qq);// 查询这个qq的所有信息
		List<Message> selectMessage = messageDao.selectMessage();// 查询message表
		Collections.reverse(selectMessage);// 时间倒叙显示留言
		mav.addObject("user", selectQq);
		mav.addObject("message", selectMessage);
		mav.setViewName("message");
		return mav;
	}

	// 删除留言
	@RequestMapping(path = "delectMessage/{messageid}")
	public ModelAndView deleteMessage(@PathVariable Integer messageid) {
		ModelAndView mav = new ModelAndView();
		messageDao.deleteMessage(messageid);
		mav.setViewName("redirect:/jumpMessage");
		return mav;
	}
	// 一级评论说说
	@RequestMapping(path = "comment1/{feelid}")
	public ModelAndView speakFeel(HttpServletRequest request,HttpServletResponse response, Speak speak,HttpSession session,@PathVariable Integer feelid) throws UnsupportedEncodingException {
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = df.format(day);
		Integer qqid =(Integer) session.getAttribute("qqid");
		ModelAndView mav = new ModelAndView();
		String textsss = request.getParameter("textsss");
		speak.setContext(textsss);//页面来的评论内容
		speak.setTime(format);//存储时间
		speak.setUserid(qqid);//评论者的id
		speak.setPid(feelid);//pid
		System.out.println(speak);
		speakDao.insertSpeak(speak);//添加评论
		mav.setViewName("redirect:/queryFeels");
		return mav;
	}

}
