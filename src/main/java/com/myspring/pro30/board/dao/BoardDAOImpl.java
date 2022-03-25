package com.myspring.pro30.board.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.pro30.board.vo.ArticleVO;
import com.myspring.pro30.board.vo.ImageVO;


@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	private SqlSession sqlSession;

	
	
	@Override
	public List<ArticleVO> selectAllArticles(Map<String, Integer> pagingMap) {
		List<ArticleVO> articlesList = sqlSession.selectList("mapper.board.selectAllArticles",pagingMap);
		
		return articlesList;
	}


//	@Override
//	public List<ArticleVO> selectAllArticlesList() throws DataAccessException {
//		List<ArticleVO> articlesList = articlesList = sqlSession.selectList("mapper.board.selectAllArticlesList");
//		return articlesList;
//	}

	
	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		int articleNO = selectNewArticleNO();
		articleMap.put("articleNO", articleNO);
		sqlSession.insert("mapper.board.insertNewArticle",articleMap);
		return articleNO;
	}
    
	//다중 파일 업로드
	
	@Override
	public void insertNewImage(Map articleMap) throws DataAccessException {
		List<ImageVO> imageFileList = (ArrayList)articleMap.get("imageFileList");

			int articleNO = (Integer)articleMap.get("articleNO");
			int imageFileNO = selectNewImageFileNO();
			for(ImageVO imageVO : imageFileList){
				imageVO.setImageFileNO(++imageFileNO);
				imageVO.setArticleNO(articleNO);
			}
			
			sqlSession.insert("mapper.board.insertNewImage",imageFileList);	
		
	}
	
	@Override
	public ArticleVO selectArticle(int articleNO) throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectArticle", articleNO);
	}
	// 한개 이미지 수정
//	@Override
//	public void updateArticle(Map articleMap) throws DataAccessException {
//		sqlSession.update("mapper.board.updateArticle", articleMap);
//	}

	@Override
	public void updateArticle(Map articleMap) throws DataAccessException {
		sqlSession.update("mapper.board.updateArticle", articleMap);
		sqlSession.update("mapper.board.updateImageList", articleMap);
	}

	@Override
	public void deleteArticle(int articleNO) throws DataAccessException {
		sqlSession.delete("mapper.board.deleteArticle", articleNO);
		
	}
	
	@Override
	public List selectImageFileList(int articleNO) throws DataAccessException {
		List<ImageVO> imageFileList = null;
		imageFileList = sqlSession.selectList("mapper.board.selectImageFileList",articleNO);
		return imageFileList;
	}
	
	private int selectNewArticleNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectNewArticleNO");
	}
	
	private int selectNewImageFileNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectNewImageFileNO");
	}
	
	public int totalCount() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.total");
	}
	
	public void testInsert(int i) throws DataAccessException {
		sqlSession.insert("mapper.board.testInsert",i);
	}
	// 조회수 올리는 메소드 쿼리 매핑
	@Override
	public void readCountUp(int articleNO) throws DataAccessException {
		 sqlSession.update("mapper.board.readCountUp",articleNO);;
		
	}
	
	
}
