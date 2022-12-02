package com.khs.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.khs.exam.demo.vo.Member;

@Mapper
public interface MemberRepository {

	@Insert("""
			INSERT INTO `member`
			SET regDate = NOW(),
			updateDate = NOW(),
			loginId = #{loginId},
			loginPw = #{loginPw},
			`name` = #{name},
			nickname = #{nickname},
			cellphoneNum = #{cellphoneNum},
			email = #{email},
			`status` = "가입완료"
			""")
	void join(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);

	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

	@Select("""
			SELECT *
			FROM `member` AS M
			WHERE M.id = #{id}
			""")
	Member getMemberById(int id);

	@Select("""
			SELECT *
			FROM `member` AS M
			WHERE M.loginId = #{loginId}
			""")
	Member getMemberByLoginId(String loginId);

	@Select("""
			SELECT *
			FROM `member` AS M
			WHERE M.name = #{name}
			AND M.email = #{email}
			""")
	Member getMemberByNameAndEmail(String name, String email);

	@Update("""
			<script>
			UPDATE `member`
			<set>
			updateDate = NOW(),
			<if test="loginPw != null">
			loginPw = #{loginPw},
			</if>
			<if test="name != null">
			name = #{name},
			</if>
			<if test="nickname != null">
			nickname = #{nickname},
			</if>
			<if test="cellphoneNum != null">
			cellphoneNum = #{cellphoneNum},
			</if>
			<if test="email != null">
			email = #{email}
			</if>
			</set>
			WHERE id = #{id};
			</script>
			""")
	void modify(int id, String loginPw, String name, String nickname, String cellphoneNum, String email);

	@Select("""
			SELECT COUNT(id)
			FROM `member`
			WHERE id= #{id}
			""")
	int idCheck(String id);

	@Update("""
			<script>
			UPDATE `member`
			SET delStatus = 1,
			delDate = NOW(),
			`status` = '탈퇴'
			WHERE id = #{id}
			</script>
			""")
	void withdrawMember(int id);

	@Select("""
			<script>
			SELECT COUNT(*) AS cnt
			FROM `member` AS M
			WHERE 1
			<if test="authLevel != 0">
			AND M.authLevel = #{authLevel}
			</if>
			<if test="searchKeyword != ''">
			<choose>
			<when test="searchKeywordTypeCode == 'loginId'">
			AND M.loginId LIKE CONCAT('%', #{searchKeyword}, '%')
			</when>
			<when test="searchKeywordTypeCode == 'name'">
			AND M.name LIKE CONCAT('%', #{searchKeyword}, '%')
			</when>
			<when test="searchKeywordTypeCode == 'nickname'">
			AND M.nickname LIKE CONCAT('%', #{searchKeyword}, '%')
			</when>
			<otherwise>
			AND (
			M.loginId LIKE CONCAT('%', #{searchKeyword}, '%')
			OR M.name LIKE CONCAT('%', #{searchKeyword}, '%')
			OR M.nickname LIKE CONCAT('%', #{searchKeyword}, '%')
			)
			</otherwise>
			</choose>
			</if>
			</script>
			""")
	int getMembersCount(String authLevel, String searchKeywordTypeCode, String searchKeyword);

	@Select("""
			<script>
			SELECT M.*
			FROM `member` AS M
			WHERE 1
			<if test="authLevel != 0">
			AND M.authLevel = #{authLevel}
			</if>
			<if test="searchKeyword != ''">
			<choose>
			<when test="searchKeywordTypeCode == 'loginId'">
			AND M.loginId LIKE CONCAT('%', #{searchKeyword}, '%')
			</when>
			<when test="searchKeywordTypeCode == 'name'">
			AND M.name LIKE CONCAT('%', #{searchKeyword}, '%')
			</when>
			<when test="searchKeywordTypeCode == 'nickname'">
			AND M.nickname LIKE CONCAT('%', #{searchKeyword}, '%')
			</when>
			<otherwise>
			AND (
			M.loginId LIKE CONCAT('%', #{searchKeyword}, '%')
			OR M.name LIKE CONCAT('%', #{searchKeyword}, '%')
			OR M.nickname LIKE CONCAT('%', #{searchKeyword}, '%')
			)
			</otherwise>
			</choose>
			</if>
			ORDER BY M.id DESC
			<if test="limitTake != -1">
			LIMIT #{limitStart}, #{limitTake}
			</if>
			</script>
			""")
	List<Member> getForPrintMembers(String authLevel, String searchKeywordTypeCode, String searchKeyword,
			int limitStart, int limitTake);

	@Select("""
			<script>
			SELECT *
			FROM `member`
			WHERE `status` = '탈퇴'
			ORDER BY id DESC
			<if test="limitTake != -1">
			LIMIT #{limitStart}, #{limitTake}
			</if>
			</script>
			""")
	List<Member> getWithrawMemberByStatus(int limitStart, int limitTake);

	@Update("""
			<script>
			UPDATE `member`
			SET delStatus = 1,
			delDate = NOW(),
			`status` = '탈퇴'
			WHERE id = #{id}
			</script>
			""")
	void deleteMember(int id);

	@Select("""
			SELECT *
			FROM `member`
			WHERE delStatus = 1
			""")
	Member getMemberByDelstatus();

	@Update("""
			<script>
			UPDATE `member`
			SET updateDate = NOW(),
			delStatus = 0,
			delDate IS NULL
			WHERE delStatus = 1
			AND id = #{id}
			</script>
			""")
	void recoverMember(int id);

	@Select("""
			SELECT *
			FROM `member`
			WHERE `status` = '가입대기'
			""")
	Member getWaitingMemberByStatus();

	@Update("""
			UPDATE `member`
			SET `status` = '가입완료'
			WHERE id = #{id}
			""")
	void acceptMember(int id);

}