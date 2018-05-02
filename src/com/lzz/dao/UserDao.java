package com.lzz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lzz.bean.Feel;
import com.lzz.bean.Friendmap;
import com.lzz.bean.Message;
import com.lzz.bean.User;

public interface UserDao {
	//User
	@Select("select * from User where qq=#{0} and password=#{1}")
	public User loginss(String username,String password);
	@Insert("insert into User(userid,qq,username,password,headpic,sex,birthday,nowaddress,hometown,ismarry,state,lastvisit) values(#{userid},#{qq},#{username},#{password},#{headpic},#{sex},#{birthday},#{nowaddress},#{hometown},#{ismarry},#{state},#{lastvisit})")
	public int insertuser(User user);
	@Select("select * from User where qq=#{qq}")
	public User selectQq(String qq);
	@Select("select * from User where username=#{username}")
	public List<User> selectName(String username);
	@Select("select * from User where userid=#{userid}")
	public User selectId(Integer userid);
	@Select("select * from User")
	public List<User> selectAll();
	
	//file
	@Update("update User set headpic=#{headpic} where qq=#{qq}")
	public Boolean updateFile(User user);
	
	
	
	
}
 