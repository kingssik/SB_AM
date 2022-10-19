package com.khs.exam.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.khs.exam.demo.vo.Board;

@Mapper
public interface BoardRepository {
	@Select("""
			SELECT *
			FROM board AS B
			WHERE B.id = #{id}
			AND B.delStatus = 0
			""")
	public Board getBoardById(int id);

}
