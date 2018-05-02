package com.lzz.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lzz.bean.Friendmap;
import com.lzz.bean.User;
import com.lzz.dao.FriendShipDao;
import com.lzz.dao.UserDao;


@Controller
@RequestMapping("")
public class FriendShipController {
	@Autowired
	private UserDao userDao;

	@Autowired
	private FriendShipDao friendShip;

	@RequestMapping("/showfriend")
	private ModelAndView showfriend(ModelAndView mav, HttpSession session, HttpServletRequest request) {
		User host = (User) session.getAttribute("user");
		session.setAttribute("user", host);
		List<User> qingqiu = new ArrayList<User>();
		List<User> friend = new ArrayList<User>();
		List<User> lahei = new ArrayList<User>();
		List<Friendmap> statu_0_1 = friendShip.queryFriendByToOK(host.getUserid(), 0);// ��ȷ�Ϻ���
		if (statu_0_1 != null) {
			for (int i = 0; i < statu_0_1.size(); i++) {
				User user = userDao.selectId(statu_0_1.get(i).getUserid());//
				qingqiu.add(user);
			}
		}
		List<Friendmap> statu_0 = friendShip.queryFriendByOK(host.getUserid(), 0);// ��ȷ�Ϻ���
		if (statu_0.size() != 0) {
			for (int i = 0; i < statu_0.size(); i++) {
				User user = userDao.selectId(statu_0.get(i).getFriendid());
				qingqiu.add(user);
			}
		}
		List<Friendmap> statu_1 = friendShip.queryFriendByOK(host.getUserid(), 1);// �ҵĺ���
		if (statu_1.size() != 0) {
			for (int i = 0; i < statu_1.size(); i++) {
				User user = userDao.selectId(statu_1.get(i).getFriendid());
				friend.add(user);
			}
		}

		List<Friendmap> statu_1_1 = friendShip.queryFriendByToOK(host.getUserid(), 1);// �ҵĺ���
		System.err.println(statu_1_1.size());
		if (statu_1_1.size() != 0) {
			for (int i = 0; i < statu_1_1.size(); i++) {
				User user = userDao.selectId(statu_1_1.get(i).getUserid());
				friend.add(user);
			}
		}

		List<Friendmap> statu_2 = friendShip.queryFriendByOK(host.getUserid(), -1);// ������
		if (statu_2.size() != 0) {
			for (int i = 0; i < statu_2.size(); i++) {
				User user = userDao.selectId(statu_2.get(i).getFriendid());
				lahei.add(user);
			}
		}

		List<Friendmap> statu_2_1 = friendShip.queryFriendByToOK(host.getUserid(), -1);// ������
		if (statu_2_1.size() != 0) {
			for (int i = 0; i < statu_2_1.size(); i++) {
				User user = userDao.selectId(statu_2_1.get(i).getUserid());
				lahei.add(user);
			}
		}

		mav.addObject("qingqiu", qingqiu);
		mav.addObject("friend", friend);
		mav.addObject("lahei", lahei);
		mav.setViewName("forward:/showfriends");
		return mav;
	}
	@RequestMapping("/findFriend")
	private ModelAndView findFriend(ModelAndView mav, HttpServletRequest request) {
		List<User> list = new ArrayList<User>();
		String selectmethod = request.getParameter("selectmethod");
		String selectcontent = request.getParameter("selectcontent");
		if (selectmethod.equals("qq")) {
			User user = userDao.selectQq(selectcontent);
			list.add(user);
			System.out.println(list);
		} else {
			list = userDao.selectName(selectcontent);
		}
		if (list.size() <= 0) {
			mav.addObject("resultisnull", "û�в�ѯ�����û���");
		}
		mav.addObject("users", list);
		mav.setViewName("forward:/showfriends");
		return mav;
	}
	@RequestMapping("/askforFriend")
	private ModelAndView askforFriend(ModelAndView mav, HttpServletRequest request, HttpSession session) {
		User host = (User) session.getAttribute("user");
		int friendid = Integer.parseInt(request.getParameter("friendid"));// ҳ���ȡ����id
		User user = userDao.selectId(friendid);// ��ѯ������Ϣ
		Friendmap ship = new Friendmap();// ���ѹ�ϵ
		ship.setUserid(host.getUserid());
		ship.setFriendid(user.getUserid());
		ship.setState(0);// ��״̬��Ϊ0,��ʾ����ȴ�
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = df.format(day);
		ship.setAddtime(format);// ��������ʱ�䡣���ﲻΪ��ӳɹ�ʱ�䡣����Է�ȷ�ϣ������ʱ���Ϊ���ʱ��
		List<Friendmap> friendList = friendShip.queryByAll();
		if (friendid == host.getUserid()) {
			mav.addObject("askfor_fail", "��������Լ�Ϊ����");
		}
		boolean bool = true;
		for (int i = 0; i < friendList.size(); i++) {
			if (friendList.get(i).getUserid() == ship.getUserid() && friendList.get(i).getFriendid() == ship.getFriendid()) {
				if (friendList.get(i).getState() == 0) {
					mav.addObject("askfor_suc", "�������,��ȴ� " + user.getUsername() + "ͬ��");
					bool = false;
				} else if (friendList.get(i).getState() == 2) {
					mav.addObject("askfor_fail", "�������������еĳ�Ա���������������лָ�Ϊ�������ѣ�");
					bool = false;
				} else if (friendList.get(i).getState() == 1) {
					mav.addObject("askfor_fail", user.getUsername() + "�Ѿ�Ϊ����,�뵽�ҵĺ������ѯ");
					bool = false;
				}
			}
		}
		if (bool) {
			friendShip.addFriend(ship);// ��Ӻ��ѹ�ϵ
			mav.addObject("askfor_suc", "����ɹ�����ȴ� " + user.getUsername() + " ����");
		}
		mav.addObject("other", "operainforback.jsp");
		mav.setViewName("forward:/showfriends");
		return mav;
	}
	//
	@RequestMapping("/showinfor")
	private ModelAndView showinfor(ModelAndView mav, HttpSession session, HttpServletRequest request) {
		User host = (User) session.getAttribute("user");// ��ǰ��¼�û�
		int friendid = Integer.parseInt(request.getParameter("friendid"));// �����û�id
		User user = userDao.selectId(friendid);// �õ�ҳ�������û���Ϣid����û�
		Friendmap ship = friendShip.queryFriendByStatu(friendid, host.getUserid());
		if (ship == null) {
			Friendmap ship1 = friendShip.queryFriendByStatu(host.getUserid(), friendid);
			if (ship1.getState() != 0) {
				mav.addObject("ship", ship1);
			}
		} else {
			mav.addObject("ship", ship);
		}
		mav.addObject("fuser", user);
		mav.addObject("other", "friendinfo.jsp");
		mav.setViewName("forward:/showfriend");
		return mav;
	}
	
	@RequestMapping("/agreefuser")
	private ModelAndView agreefuser(ModelAndView mav, HttpSession session, HttpServletRequest request) {
		User host = (User) session.getAttribute("user");// ��ǰ��¼�û�
		int friendid = Integer.parseInt(request.getParameter("fusermapid")); // �����û�id
		User user = userDao.selectId(friendid);// �����û���Ϣ
		Friendmap ship = friendShip.queryFriendByStatu(friendid, host.getUserid());// ��ȡ����ӳ������Լ�Ϊ����������ݵĺ���ӳ���
		if (ship != null) {
			ship.setState(1);
			int i = friendShip.updateStatu(ship);
			if (i != 0) {
				mav.addObject("agree_suc", "�ѳɹ�����" + user.getUsername() + "������");
			} else {
				mav.addObject("agree_fail", "����������ά�������Ժ�");
			}
		} else {
			mav.addObject("agree_fail", "����������ά�������Ժ�");
		}
		mav.addObject("other", "operainforback.jsp");
		mav.setViewName("forward:/showfriend");
		return mav;
	}
	@RequestMapping("/outfuser")
	private ModelAndView outfuser(HttpSession session, ModelAndView mav, HttpServletRequest request) {
		User host = (User) session.getAttribute("user");// ��ǰ��¼�û�
		int fusermapfid = Integer.parseInt(request.getParameter("fusermapfid")); // �����û�id
		int fusermapuid = Integer.parseInt(request.getParameter("fusermapuid")); // �����û�id
		int friendid = 0;
		if (host.getUserid() != fusermapfid) {
			friendid = fusermapfid;
		}
		if (host.getUserid() != fusermapuid) {
			friendid = fusermapuid;
		}
		String p = request.getParameter("p");
		boolean bool = true;
		User user = userDao.selectId(friendid);// �����û���Ϣ
		Friendmap ship = friendShip.queryFriendByStatu(friendid, host.getUserid()); // ��ȡ����ӳ������Լ�Ϊ����������ݵĺ���ӳ���
		Friendmap ship_1 = friendShip.queryFriendByStatu(host.getUserid(), friendid); // ��ȡ����ӳ������Լ�Ϊ����������ݵĺ���ӳ���
		if (ship != null) {
			int i = friendShip.deleteFriendMap(ship);// ɾ������
			if (i != 0) {
				if (p.equals("0")) {
					mav.addObject("refuse_suc", "���ܾ��� " + user.getUsername() + " ������");
					bool = false;
				} else if (p.equals("1")) {
					mav.addObject("refuse_suc", "��ɾ���� " + user.getUsername() + " ���ѣ�");
					bool = false;
				}
			} else {
				mav.addObject("refuse_fail", "����������ά�������Ժ�"); // ���ɾ��ʧ�ܣ������Ѻ���ʾ��Ϣ
			}
		}
		if (ship_1 != null) {
			int i = friendShip.deleteFriendMap(ship_1);// ɾ������
			if (i != 0) {
				if (p.equals("0")) {
					mav.addObject("refuse_suc", "���ܾ��� " + user.getUsername() + " ������");
					bool = false;
				} else if (p.equals("1")) {
					mav.addObject("refuse_suc", "��ɾ���� " + user.getUsername() + " ���ѣ�");
					bool = false;
				}
			} else {
				mav.addObject("refuse_fail", "����������ά�������Ժ�"); // ���ɾ��ʧ�ܣ������Ѻ���ʾ��Ϣ
			}
		}
		if (bool) {
			mav.addObject("refuse_fail", "����������ά�������Ժ�");
		}
		mav.addObject("other", "operainforback.jsp");
		mav.setViewName("forward:/showfriend");
		return mav;
	}
	@RequestMapping("/blacfuser")
	private ModelAndView blackfuser(ModelAndView mav, HttpServletRequest request, HttpSession session) {
		User host = (User) session.getAttribute("user");// ��ǰ��¼�û�
		int fusermapfid = Integer.parseInt(request.getParameter("fusermapfid")); // �����û�id
		int fusermapuid = Integer.parseInt(request.getParameter("fusermapuid")); // �����û�id
		int friendid = 0;
		if (host.getUserid() != fusermapfid) {
			friendid = fusermapfid;
		}
		if (host.getUserid() != fusermapuid) {
			friendid = fusermapuid;
		}
		User user = userDao.selectId(friendid);// �����û���Ϣ
		boolean bool = true;
		Friendmap ship = friendShip.queryFriendByStatu(friendid, host.getUserid()); // ��ȡ����ӳ������Լ�Ϊ����������ݵĺ���ӳ���
		Friendmap ship_1 = friendShip.queryFriendByStatu(host.getUserid(), friendid);
		if (ship != null) {
			ship.setState(-1);
			int i = friendShip.updateStatu(ship);
			if (i != 0) {
				mav.addObject("black_suc", "��������" + user.getUsername() + "��");
				bool = false;
			} else {
				mav.addObject("black_fail", "����������ά�������Ժ�");
			}
		}
		if (ship_1 != null) {
			ship_1.setState(-1);
			int i = friendShip.updateStatu(ship_1);
			if (i != 0) {
				mav.addObject("black_suc", "��������" + user.getUsername() + "��");
				bool = false;
			} else {
				mav.addObject("black_fail", "����������ά�������Ժ�");
			}
		}
		if (bool) {
			mav.addObject("black_fail", "����������ά�������Ժ�");
		}
		mav.setViewName("forward:/showfriend");
		return mav;
	}
	@RequestMapping("/hffuser")
	private ModelAndView hffuser(ModelAndView mav, HttpServletRequest request, HttpSession session) {
		User host = (User) session.getAttribute("user");// ��ǰ��¼�û�
		int fusermapfid = Integer.parseInt(request.getParameter("fusermapfid")); // �����û�id
		int fusermapuid = Integer.parseInt(request.getParameter("fusermapuid")); // �����û�id
		int friendid = 0;
		if (host.getUserid() != fusermapfid) {
			friendid = fusermapfid;
		}
		if (host.getUserid() != fusermapuid) {
			friendid = fusermapuid;
		}
		User user = userDao.selectId(friendid);// �����û���Ϣ
		boolean bool = true;
		Friendmap ship = friendShip.queryFriendByStatu(friendid, host.getUserid()); // ��ȡ����ӳ������Լ�Ϊ����������ݵĺ���ӳ���
		Friendmap ship_1 = friendShip.queryFriendByStatu(host.getUserid(), friendid);
		if (ship != null) {
			ship.setState(1);
			int i = friendShip.updateStatu(ship);
			if (i != 0) {
				mav.addObject("black_suc", "���ָ���" + user.getUsername() + "��");
				bool = false;
			} else {
				mav.addObject("black_fail", "����������ά�������Ժ�");
			}
		}
		if (ship_1 != null) {
			ship_1.setState(1);
			int i = friendShip.updateStatu(ship_1);
			if (i != 0) {
				mav.addObject("black_suc", "���ָ���" + user.getUsername() + "��");
				bool = false;
			} else {
				mav.addObject("black_fail", "����������ά�������Ժ�");
			}
		}
		if (bool) {
			mav.addObject("black_fail", "����������ά�������Ժ�");
		}
		mav.setViewName("forward:/showfriend");
		return mav;
	}
}
