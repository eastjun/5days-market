package jun.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jun.member.dto.MemberDTO;
import jun.member.service.MemberService;

public class MemberDAO implements MemberService{

	private static Log log = LogFactory.getLog(MemberDAO.class);
	
	@Override
	public ArrayList<MemberDTO> memberSelectAll() {
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet =null;
		ArrayList<MemberDTO> arrayList = new ArrayList<MemberDTO>();
		try {
			Context context = new InitialContext( );
			DataSource dataSource =(DataSource) context.lookup("java:comp/env/jdbc");
			connection =dataSource.getConnection( );
			
			String sql ="select uniqueid, userid, password, nickname, phonenum, email, birthday from member ";
			log.info("SQL 확인 -" + sql);
			preparedStatement = connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next( )) {
			MemberDTO memberDTO =new MemberDTO( );
			memberDTO.setUniqueId(resultSet.getInt("uniqueid"));
			memberDTO.setUserid(resultSet.getString("userid"));
			memberDTO.setPassword(resultSet.getString("password"));
			memberDTO.setNickname(resultSet.getString("nickname"));
			memberDTO.setPhonenum(resultSet.getString("phonenum"));
			memberDTO.setEmail(resultSet.getString("email"));
			memberDTO.setBirthday(resultSet.getString("birthday"));
			arrayList.add(memberDTO);
			}
			resultSet.getRow( );
			if(resultSet.getRow( )== 0) {
				log.info("등록된 아이디가 없습니다.");
			}
			
		} catch (Exception e) {
			log.info("전체 아이디 조회 실패 -" + e);
		}
		finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return arrayList;
	}

	@Override
	public MemberDTO memberSelect(MemberDTO memberDTO) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext( );
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection( );
			// 쿼리문 작성
			String sql = "select userid, nickname, password, phonenum, email, birthday from member ";
			sql += " where userid = ? ";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberDTO.getUserid());
			
			resultSet = preparedStatement.executeQuery( );
			
			if(resultSet.next( )) {
				log.info("아이디 확인 - " + resultSet.getString("userid"));
				memberDTO.setUserid(resultSet.getString("userid"));
				memberDTO.setNickname(resultSet.getString("nickname"));
				memberDTO.setPassword(resultSet.getString("password"));
				memberDTO.setPhonenum(resultSet.getString("phonenum"));
				memberDTO.setEmail(resultSet.getString("email"));
				memberDTO.setBirthday(resultSet.getString("birthday"));
			}
		} catch(Exception e) {
			log.info("개별 회원 조회 실패 - " + e);
		} finally {
			try {
				resultSet.close( );
				preparedStatement.close( );
				connection.close( );
			} catch(Exception e) {
				e.printStackTrace( );
			}
		}
		return memberDTO;
	}

	@Override
	public MemberDTO memberInsert(MemberDTO memberDTO) {
		
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		try {
			Context context =new InitialContext( );
			DataSource dataSource =(DataSource) context.lookup("java:comp/env/jdbc");
			connection =dataSource.getConnection( );
			String sql ="insert into member (uniqueid, userid, password, nickname, phonenum, email, birthday ) ";
			sql += "values (?,?,?,?,?,?,to_date(?, 'yyyy-mm-dd') ) ";
			log.info("SQL 확인" + sql);
			preparedStatement =connection.prepareStatement(sql);
			preparedStatement.setInt(1, memberDTO.getUniqueId());
			preparedStatement.setString(2, memberDTO.getUserid());
			preparedStatement.setString(3, memberDTO.getPassword());
			preparedStatement.setString(4, memberDTO.getNickname());
			preparedStatement.setString(5, memberDTO.getPhonenum());
			preparedStatement.setString(6, memberDTO.getEmail());
			preparedStatement.setString(7, memberDTO.getBirthday());
			
			int count = preparedStatement.executeUpdate();
			if (count>0) {
				connection.commit();
				log.info("커밋완료.");
				
			}
			else {
				connection.rollback();
				log.info("롤백 되었습니다.");
			}
			
		} catch (Exception e) {
			log.info("회원가입 DB실패 -" +e );
		}finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return memberDTO;
	}

	@Override
	public MemberDTO memberUpdate(MemberDTO memberDTO) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		log.info("업데이트 정보 - " + memberDTO);
		try {
			Context context = new InitialContext( );
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection( );
			String sql = "update member set password=?, nickname=?, phonenum=?, email=? ";
			sql += "where userid = ?";
			log.info("SQL - " + sql);
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, memberDTO.getPassword());
			preparedStatement.setString(2, memberDTO.getNickname());
			preparedStatement.setString(3, memberDTO.getPhonenum());
			preparedStatement.setString(4, memberDTO.getEmail());	
			preparedStatement.setString(5, memberDTO.getUserid());
			
			int count = preparedStatement.executeUpdate( );
			log.info("수정 데이터 확인 - " + memberDTO);
			if(count > 0) {
				connection.commit( );
				log.info("커밋되었습니다.");
			} else {
				connection.rollback( );
				log.info("롤백되었습니다.");
			}
		} catch(Exception e) {
			log.info("회원 정보 수정 실패 - " + e);
		} finally {
			try {
				connection.close( );
				preparedStatement.close( );
			} catch(Exception e) {
				e.printStackTrace( );
			}
		}
		return memberDTO;
	}

	@Override
	public MemberDTO memberDelete(String id) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext( );
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection( );
			String sql = "delete from member ";
			sql += " where userid = ? ";
			log.info("SQL - " + sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			int count = preparedStatement.executeUpdate( );
			if(count > 0) {
				connection.commit( );
				log.info("커밋되었습니다.");
			} else {
				connection.rollback( );
				log.info("롤백되었습니다.");
			}
		} catch(Exception e) {
			log.info("회원 삭제 실패 - " + e);
		} finally {
			try {
				connection.close( );
				preparedStatement.close( );
			} catch(Exception e) {
				e.printStackTrace( );
			}
		}
		return null;
	}

	@Override
	public MemberDTO findById(String id) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		MemberDTO memberDTO = new MemberDTO();
		try {
			Context context = new InitialContext( );
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection( );
			// 쿼리문 작성
			String sql = "select userid from member ";
			sql += " where userid = ? ";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			
			resultSet = preparedStatement.executeQuery( );
			if(resultSet.next( )) {
				log.info("아이디 확인 - " + resultSet.getString("userid"));
				memberDTO.setUserid(resultSet.getString("userid"));
				
			}
		} catch(Exception e) {
			log.info("개별 회원 조회 실패 - " + e);
		} finally {
			try {
				resultSet.close( );
				preparedStatement.close( );
				connection.close( );
			} catch(Exception e) {
				e.printStackTrace( );
			}
		}
		return memberDTO;
	
	}

}
