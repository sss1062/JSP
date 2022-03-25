package com.myspring.pro30.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.pro30.board.vo.ArticleVO;


public interface BoardDAO {
//	public List<ArticleVO> selectAllArticlesList() throws DataAccessException;
	public int insertNewArticle(Map articleMap) throws DataAccessException;
	public void insertNewImage(Map articleMap) throws DataAccessException;
	
	public ArticleVO selectArticle(int articleNO) throws DataAccessException;
	public void updateArticle(Map articleMap) throws DataAccessException;
	public void deleteArticle(int articleNO) throws DataAccessException;
	public List selectImageFileList(int articleNO) throws DataAccessException;
	public int totalCount() throws DataAccessException;
	public void readCountUp(int articleNO) throws DataAccessException; // 조회수 올리는 메소드 쿼리 매핑
	public List<ArticleVO> selectAllArticles(Map<String, Integer> pagingMap);
	public void testInsert(int i) throws DataAccessException;
}
