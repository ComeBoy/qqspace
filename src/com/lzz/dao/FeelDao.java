package com.lzz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lzz.bean.Feel;

public interface FeelDao {
	//feel
		@Insert("insert into Feel (feelid,userid,feeltime,feelcontent,pic) values(#{feelid},#{userid},#{feeltime},#{feelcontent},#{pic})")
		public void insertFeel(Feel feel);
		@Select("select * from Feel where userid=#{userid}")
		public List<Feel> selectFeel(int id);
		@Select("select * from Feel")
		public List<Feel> selectFeels();
		@Delete("delete from Feel where feelid=#{feelid}")
		public void delectFeel(Integer id);
		@Update("update Feel set speak=#{speak} where feelid=#{feelid}")
		public  List<Feel> updateSpeak(Integer feelid);
}
