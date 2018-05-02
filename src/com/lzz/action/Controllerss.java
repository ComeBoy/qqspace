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
	// ��½
	@RequestMapping("/login")
	public ModelAndView logins(User user,  HttpSession session) {
		ModelAndView mav=new ModelAndView();
		ModelAndView mav1=new ModelAndView();
		String qq = user.getQq();
		String password = user.getPassword();
		User loginss = userDao.loginss(qq, password);// �˺�����
		session.setAttribute("qq", qq);
		User user1 = userDao.selectQq(qq);
		session.setAttribute("user",user1);//ͨ����½��qq���ѯ���user
		mav.setViewName("redirect:/loginselect");
		mav1.setViewName("redirect:/go");
		if (loginss != null) {
			return mav;
		}
		return mav1;
	}
	//��½���ѯ
	@RequestMapping("/loginselect")
	public ModelAndView loginsSelect(HttpSession session,Model model) {
		ModelAndView mav=new ModelAndView();
		String qq = (String) session.getAttribute("qq");
		User selectQq = userDao.selectQq(qq);// ����qq��ѯ���qq��������Ϣ
		session.setAttribute("qqid", selectQq.getUserid());//session���洢��½��qq��id
		List<Feel> selectFeel = feeldDao.selectFeel(selectQq.getUserid());// ���ݵ�½�ߵ�id��ѯ��½���ж�����˵˵
		session.setAttribute("feelLength", selectFeel.size());// ˵˵������
		String df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());// �������ڸ�ʽ
		session.setAttribute("datetime", df);
		model.addAttribute("datetime", df);
		model.addAttribute("user", selectQq);
		model.addAttribute("totalfeels", selectFeel.size());
		mav.setViewName("index");
		return mav;
	}

	// ע��
	@RequestMapping("/register")
	public String regist(User user) {
		String qq = (System.currentTimeMillis() + "").substring(3, 13);
		user.setQq(qq);
		user.setState("1");// ע���֮��״̬Ϊ1������ʹ�ã�
		String df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		user.setLastvisit(df);
		user.setHeadpic("defaultheadpic.jpg");
		userDao.insertuser(user);
		return "register_suc";
	}

	// ��������
	@RequestMapping("/grzl")
	public String selectUser(Model model, HttpSession session) {
		String qq = (String) session.getAttribute("qq");
		String df = (String) session.getAttribute("datetime");
		Integer feelLength = (Integer) session.getAttribute("feelLength");// ��ѯ˵˵������
		model.addAttribute("totalfeels", feelLength);
		model.addAttribute("datetime", df);
		User selectQq = userDao.selectQq(qq);
		model.addAttribute("user", selectQq);
		return "index";
	}

	// �Լ�����˵˵
	@RequestMapping("/speak")
	private ModelAndView addFeel(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String qq = (String) session.getAttribute("qq");
		User user = userDao.selectQq(qq);// ��ѯ��qq���˵�������Ϣ
		Feel feel = new Feel();
		feel.setFeelcontent(request.getParameter("feelcontent"));
		feel.setUserid(user.getUserid());
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = df.format(day);
		feel.setFeeltime(format);
		feel.setPic("logo.jpg");
		feeldDao.insertFeel(feel);// ���
		mav.setViewName("redirect:/queryFeels");
		System.out.println("������ѯ����ҳ����");
		return mav;
	}

	// ��ѯ�Լ���˵˵
	@RequestMapping("/queryFeel")
	private ModelAndView queryFeel(HttpSession session, Model model) {
		ModelAndView mav = new ModelAndView();
		String qq = (String) session.getAttribute("qq");
		User user = userDao.selectQq(qq);
		List<Feel> selectFeel = feeldDao.selectFeel(user.getUserid());// ����qq��id����ѯFeel���������Ϣ
		Collections.reverse(selectFeel);// ��˵˵������ʾ
		mav.addObject("user", user);
		mav.addObject("feel", selectFeel);
		mav.setViewName("feelmy");
		return mav;
	}

	// ��ѯȫ��˵˵
	@RequestMapping("/queryFeels")
	private ModelAndView queryFeels(HttpSession session, Model model) {
		ModelAndView mav = new ModelAndView();
		String qq = (String) session.getAttribute("qq");
		User host = userDao.selectQq(qq);
		List<Feel> selectFeels = feeldDao.selectFeels();
		List<User> list = userDao.selectAll();
		Collections.reverse(selectFeels);// ��˵˵������ʾ
		mav.addObject("userlist", list);
		mav.addObject("feel", selectFeels);
		mav.addObject("user", host);
		List<User> list1 = userDao.selectAll();
		List<Speak> selectSpeak = speakDao.selectSpeak();
		System.out.println(selectSpeak);
		mav.addObject("speak", selectSpeak);//��ѯspeak��
		mav.addObject("userlist", list1);//��ѯ�����û���
		mav.setViewName("indexmy");
		return mav;
	}

	// ɾ��˵˵
	@RequestMapping(path = "delectFeel/{feelid}")
	public ModelAndView delectFeel(@PathVariable Integer feelid) {
		ModelAndView mav = new ModelAndView();
		feeldDao.delectFeel(feelid);
		mav.setViewName("redirect:/queryFeel");
		return mav;
	}

	// ��ת���޸�ͷ��ҳ��
	@RequestMapping("/updateheadpic")
	public ModelAndView updateheadpic(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String qq = (String) session.getAttribute("qq");
		User user = userDao.selectQq(qq);// ��ѯ��qq���˵�������Ϣ
		mav.addObject("user", user);
		mav.setViewName("updateheadpic");
		return mav;
	}

	// �޸�ͷ����ת��indexҳ��
	@RequestMapping("/update")
	private ModelAndView updateHeadpic(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String host = (String) session.getAttribute("qq");
		String path = request.getSession().getServletContext().getRealPath("res/headpic");
		String fileName = file.getOriginalFilename(); // ���ͼƬ����
		File targetFile = new File(path, fileName);//�ļ������ֺ�·��
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
			User user = new User();
			user.setQq(host);
			user.setHeadpic(fileName);
			if (userDao.updateFile(user)) {
				mav.addObject("modify_suc", "�޸ĳɹ�");
				User user1 = userDao.selectQq(host);
				session.setAttribute("user", user1);
				mav.setViewName("forward:/grzl");
			} else {
				mav.addObject("modify_fail", "�޸�ʧ�ܡ�ԭ���ڲ�����������ά�������Ժ��ٽ����޸�");
				mav.setViewName("updateheadpic"); // ��ע��ʧ��ת���޸�ҳ�������޸�
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
		User selectQq = userDao.selectQq(qq);// �õ������ߵ�������Ϣ
		int receiveid = selectQq.getUserid();// �����ߵ�id
		message.setReceiveid(receiveid);// ���������ߵ�id
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = df.format(day);
		message.setMessagetime(format);// �������Ե�ʱ��
		String parameter = request.getParameter("content");
		message.setContent(parameter);// ����������
		message.setPic("1");
		message.setParentmessageid(0);// ���ûظ�����ʱ�ĸ�����id
		messageDao.insertMassage(message);
		mav.addObject("user", selectQq);
		mav.addObject("message", message);
		mav.setViewName("forward:/jumpMessage");
		return mav;
	}
	//��ѯ����
	@RequestMapping("/jumpMessage")
	public ModelAndView selectMessage(HttpSession session, Message message) {
		ModelAndView mav = new ModelAndView();
		String qq = (String) session.getAttribute("qq");
		User selectQq = userDao.selectQq(qq);// ��ѯ���qq��������Ϣ
		List<Message> selectMessage = messageDao.selectMessage();// ��ѯmessage��
		Collections.reverse(selectMessage);// ʱ�䵹����ʾ����
		mav.addObject("user", selectQq);
		mav.addObject("message", selectMessage);
		mav.setViewName("message");
		return mav;
	}

	// ɾ������
	@RequestMapping(path = "delectMessage/{messageid}")
	public ModelAndView deleteMessage(@PathVariable Integer messageid) {
		ModelAndView mav = new ModelAndView();
		messageDao.deleteMessage(messageid);
		mav.setViewName("redirect:/jumpMessage");
		return mav;
	}
	// һ������˵˵
	@RequestMapping(path = "comment1/{feelid}")
	public ModelAndView speakFeel(HttpServletRequest request,HttpServletResponse response, Speak speak,HttpSession session,@PathVariable Integer feelid) throws UnsupportedEncodingException {
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = df.format(day);
		Integer qqid =(Integer) session.getAttribute("qqid");
		ModelAndView mav = new ModelAndView();
		String textsss = request.getParameter("textsss");
		speak.setContext(textsss);//ҳ��������������
		speak.setTime(format);//�洢ʱ��
		speak.setUserid(qqid);//�����ߵ�id
		speak.setPid(feelid);//pid
		System.out.println(speak);
		speakDao.insertSpeak(speak);//�������
		mav.setViewName("redirect:/queryFeels");
		return mav;
	}

}
