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
		List<Friendmap> statu_0_1 = friendShip.queryFriendByToOK(host.getUserid(), 0);// 被确认好友
		if (statu_0_1 != null) {
			for (int i = 0; i < statu_0_1.size(); i++) {
				User user = userDao.selectId(statu_0_1.get(i).getUserid());//
				qingqiu.add(user);
			}
		}
		List<Friendmap> statu_0 = friendShip.queryFriendByOK(host.getUserid(), 0);// 待确认好友
		if (statu_0.size() != 0) {
			for (int i = 0; i < statu_0.size(); i++) {
				User user = userDao.selectId(statu_0.get(i).getFriendid());
				qingqiu.add(user);
			}
		}
		List<Friendmap> statu_1 = friendShip.queryFriendByOK(host.getUserid(), 1);// 我的好友
		if (statu_1.size() != 0) {
			for (int i = 0; i < statu_1.size(); i++) {
				User user = userDao.selectId(statu_1.get(i).getFriendid());
				friend.add(user);
			}
		}

		List<Friendmap> statu_1_1 = friendShip.queryFriendByToOK(host.getUserid(), 1);// 我的好友
		System.err.println(statu_1_1.size());
		if (statu_1_1.size() != 0) {
			for (int i = 0; i < statu_1_1.size(); i++) {
				User user = userDao.selectId(statu_1_1.get(i).getUserid());
				friend.add(user);
			}
		}

		List<Friendmap> statu_2 = friendShip.queryFriendByOK(host.getUserid(), -1);// 黑名单
		if (statu_2.size() != 0) {
			for (int i = 0; i < statu_2.size(); i++) {
				User user = userDao.selectId(statu_2.get(i).getFriendid());
				lahei.add(user);
			}
		}

		List<Friendmap> statu_2_1 = friendShip.queryFriendByToOK(host.getUserid(), -1);// 黑名单
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
			mav.addObject("resultisnull", "没有查询到此用户！");
		}
		mav.addObject("users", list);
		mav.setViewName("forward:/showfriends");
		return mav;
	}
	@RequestMapping("/askforFriend")
	private ModelAndView askforFriend(ModelAndView mav, HttpServletRequest request, HttpSession session) {
		User host = (User) session.getAttribute("user");
		int friendid = Integer.parseInt(request.getParameter("friendid"));// 页面获取好友id
		User user = userDao.selectId(friendid);// 查询好友信息
		Friendmap ship = new Friendmap();// 好友关系
		ship.setUserid(host.getUserid());
		ship.setFriendid(user.getUserid());
		ship.setState(0);// 将状态置为0,表示请求等待
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = df.format(day);
		ship.setAddtime(format);// 设置请求时间。这里不为添加成功时间。如果对方确认，则更新时间后为添加时间
		List<Friendmap> friendList = friendShip.queryByAll();
		if (friendid == host.getUserid()) {
			mav.addObject("askfor_fail", "不能添加自己为好友");
		}
		boolean bool = true;
		for (int i = 0; i < friendList.size(); i++) {
			if (friendList.get(i).getUserid() == ship.getUserid() && friendList.get(i).getFriendid() == ship.getFriendid()) {
				if (friendList.get(i).getState() == 0) {
					mav.addObject("askfor_suc", "你请求过,请等待 " + user.getUsername() + "同意");
					bool = false;
				} else if (friendList.get(i).getState() == 2) {
					mav.addObject("askfor_fail", "他是您黑名单中的成员，请您到黑名单中恢复为正常好友！");
					bool = false;
				} else if (friendList.get(i).getState() == 1) {
					mav.addObject("askfor_fail", user.getUsername() + "已经为好友,请到我的好友里查询");
					bool = false;
				}
			}
		}
		if (bool) {
			friendShip.addFriend(ship);// 添加好友关系
			mav.addObject("askfor_suc", "请求成功，请等待 " + user.getUsername() + " 接受");
		}
		mav.addObject("other", "operainforback.jsp");
		mav.setViewName("forward:/showfriends");
		return mav;
	}
	//
	@RequestMapping("/showinfor")
	private ModelAndView showinfor(ModelAndView mav, HttpSession session, HttpServletRequest request) {
		User host = (User) session.getAttribute("user");// 当前登录用户
		int friendid = Integer.parseInt(request.getParameter("friendid"));// 请求用户id
		User user = userDao.selectId(friendid);// 拿到页面请求用户信息id查该用户
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
		User host = (User) session.getAttribute("user");// 当前登录用户
		int friendid = Integer.parseInt(request.getParameter("fusermapid")); // 请求用户id
		User user = userDao.selectId(friendid);// 请求用户信息
		Friendmap ship = friendShip.queryFriendByStatu(friendid, host.getUserid());// 获取好友映射对中自己为被请求者身份的好友映射对
		if (ship != null) {
			ship.setState(1);
			int i = friendShip.updateStatu(ship);
			if (i != 0) {
				mav.addObject("agree_suc", "已成功接受" + user.getUsername() + "的请求！");
			} else {
				mav.addObject("agree_fail", "服务器正在维护，请稍后！");
			}
		} else {
			mav.addObject("agree_fail", "服务器正在维护，请稍后！");
		}
		mav.addObject("other", "operainforback.jsp");
		mav.setViewName("forward:/showfriend");
		return mav;
	}
	@RequestMapping("/outfuser")
	private ModelAndView outfuser(HttpSession session, ModelAndView mav, HttpServletRequest request) {
		User host = (User) session.getAttribute("user");// 当前登录用户
		int fusermapfid = Integer.parseInt(request.getParameter("fusermapfid")); // 请求用户id
		int fusermapuid = Integer.parseInt(request.getParameter("fusermapuid")); // 请求用户id
		int friendid = 0;
		if (host.getUserid() != fusermapfid) {
			friendid = fusermapfid;
		}
		if (host.getUserid() != fusermapuid) {
			friendid = fusermapuid;
		}
		String p = request.getParameter("p");
		boolean bool = true;
		User user = userDao.selectId(friendid);// 请求用户信息
		Friendmap ship = friendShip.queryFriendByStatu(friendid, host.getUserid()); // 获取好友映射对中自己为被请求者身份的好友映射对
		Friendmap ship_1 = friendShip.queryFriendByStatu(host.getUserid(), friendid); // 获取好友映射对中自己为被请求者身份的好友映射对
		if (ship != null) {
			int i = friendShip.deleteFriendMap(ship);// 删除操作
			if (i != 0) {
				if (p.equals("0")) {
					mav.addObject("refuse_suc", "您拒绝了 " + user.getUsername() + " 的请求！");
					bool = false;
				} else if (p.equals("1")) {
					mav.addObject("refuse_suc", "您删除了 " + user.getUsername() + " 好友！");
					bool = false;
				}
			} else {
				mav.addObject("refuse_fail", "服务器正在维护，请稍后！"); // 如果删除失败，返回友好提示信息
			}
		}
		if (ship_1 != null) {
			int i = friendShip.deleteFriendMap(ship_1);// 删除操作
			if (i != 0) {
				if (p.equals("0")) {
					mav.addObject("refuse_suc", "您拒绝了 " + user.getUsername() + " 的请求！");
					bool = false;
				} else if (p.equals("1")) {
					mav.addObject("refuse_suc", "您删除了 " + user.getUsername() + " 好友！");
					bool = false;
				}
			} else {
				mav.addObject("refuse_fail", "服务器正在维护，请稍后！"); // 如果删除失败，返回友好提示信息
			}
		}
		if (bool) {
			mav.addObject("refuse_fail", "服务器正在维护，请稍后！");
		}
		mav.addObject("other", "operainforback.jsp");
		mav.setViewName("forward:/showfriend");
		return mav;
	}
	@RequestMapping("/blacfuser")
	private ModelAndView blackfuser(ModelAndView mav, HttpServletRequest request, HttpSession session) {
		User host = (User) session.getAttribute("user");// 当前登录用户
		int fusermapfid = Integer.parseInt(request.getParameter("fusermapfid")); // 请求用户id
		int fusermapuid = Integer.parseInt(request.getParameter("fusermapuid")); // 请求用户id
		int friendid = 0;
		if (host.getUserid() != fusermapfid) {
			friendid = fusermapfid;
		}
		if (host.getUserid() != fusermapuid) {
			friendid = fusermapuid;
		}
		User user = userDao.selectId(friendid);// 请求用户信息
		boolean bool = true;
		Friendmap ship = friendShip.queryFriendByStatu(friendid, host.getUserid()); // 获取好友映射对中自己为被请求者身份的好友映射对
		Friendmap ship_1 = friendShip.queryFriendByStatu(host.getUserid(), friendid);
		if (ship != null) {
			ship.setState(-1);
			int i = friendShip.updateStatu(ship);
			if (i != 0) {
				mav.addObject("black_suc", "您拉黑了" + user.getUsername() + "！");
				bool = false;
			} else {
				mav.addObject("black_fail", "服务器正在维护，请稍后！");
			}
		}
		if (ship_1 != null) {
			ship_1.setState(-1);
			int i = friendShip.updateStatu(ship_1);
			if (i != 0) {
				mav.addObject("black_suc", "您拉黑了" + user.getUsername() + "！");
				bool = false;
			} else {
				mav.addObject("black_fail", "服务器正在维护，请稍后！");
			}
		}
		if (bool) {
			mav.addObject("black_fail", "服务器正在维护，请稍后！");
		}
		mav.setViewName("forward:/showfriend");
		return mav;
	}
	@RequestMapping("/hffuser")
	private ModelAndView hffuser(ModelAndView mav, HttpServletRequest request, HttpSession session) {
		User host = (User) session.getAttribute("user");// 当前登录用户
		int fusermapfid = Integer.parseInt(request.getParameter("fusermapfid")); // 请求用户id
		int fusermapuid = Integer.parseInt(request.getParameter("fusermapuid")); // 请求用户id
		int friendid = 0;
		if (host.getUserid() != fusermapfid) {
			friendid = fusermapfid;
		}
		if (host.getUserid() != fusermapuid) {
			friendid = fusermapuid;
		}
		User user = userDao.selectId(friendid);// 请求用户信息
		boolean bool = true;
		Friendmap ship = friendShip.queryFriendByStatu(friendid, host.getUserid()); // 获取好友映射对中自己为被请求者身份的好友映射对
		Friendmap ship_1 = friendShip.queryFriendByStatu(host.getUserid(), friendid);
		if (ship != null) {
			ship.setState(1);
			int i = friendShip.updateStatu(ship);
			if (i != 0) {
				mav.addObject("black_suc", "您恢复了" + user.getUsername() + "！");
				bool = false;
			} else {
				mav.addObject("black_fail", "服务器正在维护，请稍后！");
			}
		}
		if (ship_1 != null) {
			ship_1.setState(1);
			int i = friendShip.updateStatu(ship_1);
			if (i != 0) {
				mav.addObject("black_suc", "您恢复了" + user.getUsername() + "！");
				bool = false;
			} else {
				mav.addObject("black_fail", "服务器正在维护，请稍后！");
			}
		}
		if (bool) {
			mav.addObject("black_fail", "服务器正在维护，请稍后！");
		}
		mav.setViewName("forward:/showfriend");
		return mav;
	}
}
