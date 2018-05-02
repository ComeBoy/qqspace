package com.lzz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lzz.bean.Friendmap;

public interface FriendShipDao {
	@Insert("insert into Friendmap(friendmapid,userid,friendid,state,addtime) values(#{friendmapid},#{userid},#{friendid},#{state},#{addtime})")
	void addFriend(Friendmap firendShip);

	@Select("select * from Friendmap")
	List<Friendmap> queryByAll();

	@Select("select * from Friendmap where userid = #{0} and state = #{1}")
	List<Friendmap> queryFriendByOK(Integer uid, Integer statu);

	@Select("select * from Friendmap where friendid = #{0} and state = #{1}")
	List<Friendmap> queryFriendByToOK(Integer uid, Integer statu);

	@Select("select * from Friendmap where userid = #{0} and friendid = #{1}")
	Friendmap queryFriendByStatu(Integer uid, Integer fid);

	@Update("update Friendmap set state = #{state} where friendmapid = #{friendmapid}")
	int updateStatu(Friendmap ship);

	@Delete("delete from Friendmap where friendmapid = #{friendmapid}")
	int deleteFriendMap(Friendmap ship);

}
