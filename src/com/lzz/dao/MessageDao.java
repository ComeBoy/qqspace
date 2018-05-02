package com.lzz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.lzz.bean.Message;

public interface MessageDao {
	//message
		@Insert("insert into Message (messageid,receiveid,senderid,messagetime,content,parentmessageid,pic) values(#{messageid},#{receiveid},#{senderid},#{messagetime},#{content},#{parentmessageid},#{pic})")
		public void insertMassage(Message message);
		@Select("select * from Message")
		public List<Message> selectMessage();
		@Delete("delete from Message where messageid=#{messageid}")
		public void deleteMessage(Integer messageid);
}
