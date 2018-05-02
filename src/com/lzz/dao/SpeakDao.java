package com.lzz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.lzz.bean.Speak;


public interface SpeakDao {
	@Insert("insert into Speak (speakid,context,time,userid,fid,pid) values(#{speakid},#{context},#{time},#{userid},#{fid},#{pid})")
	public void insertSpeak(Speak speak);
	@Select("select * from Speak")
	public List<Speak> selectSpeak();
}
