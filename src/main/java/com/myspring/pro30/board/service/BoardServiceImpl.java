package com.myspring.pro30.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.pro30.board.dao.BoardDAO;
import com.myspring.pro30.board.vo.ArticleVO;
import com.myspring.pro30.board.vo.ImageVO;


@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl  implements BoardService{
	@Autowired
	BoardDAO boardDAO;
	
	
	
	@Override
	public Map listArticles(Map pagingMap) throws Exception {
		Map articlesMap = new HashMap();
		List<ArticleVO> articlesList = boardDAO.selectAllArticles(pagingMap);
		int total = boardDAO.totalCount();
		articlesMap.put("articlesList", articlesList);
		articlesMap.put("total", total);
		System.out.println(total);
		return articlesMap;
	}


//	public List<ArticleVO> listArticles1() throws Exception{
//		List<ArticleVO> articlesList =  boardDAO.selectAllArticlesList();
//		int total = boardDAO.totalCount();
//        return articlesList;
//	}

	
//	//단일 이미지 추가하기
//	@Override
//	public int addNewArticle(Map articleMap) throws Exception{
//		return boardDAO.insertNewArticle(articleMap);
//	}
	
	 //다중 이미지 추가하기
	
	@Override
	public int addNewArticle(Map articleMap) throws Exception{
		int articleNO = boardDAO.insertNewArticle(articleMap);
		articleMap.put("articleNO", articleNO);
		boardDAO.insertNewImage(articleMap);
		return articleNO;
	}
	
	
	//다중 파일 보이기
	@Override
	public Map viewArticle(int articleNO) throws Exception {
		Map articleMap = new HashMap();
		ArticleVO articleVO = boardDAO.selectArticle(articleNO);
		List<ImageVO> imageFileList = boardDAO.selectImageFileList(articleNO);
		articleMap.put("article", articleVO);
		articleMap.put("imageFileList", imageFileList);
		return articleMap;
	}
   
	
	
	 //단일 파일 보이기
//	@Override
//	public ArticleVO viewArticle(int articleNO) throws Exception {
//		ArticleVO articleVO = boardDAO.selectArticle(articleNO);
//		return articleVO;
//	}
	
	
	@Override
	public void modArticle(Map articleMap) throws Exception {
		boardDAO.updateArticle(articleMap);
	}
	
	@Override
	public void removeArticle(int articleNO) throws Exception {
		boardDAO.deleteArticle(articleNO);
	}

	// 조회수 올리는 메소드
	@Override
	public void count(int articleNO) throws Exception {
		boardDAO.readCountUp(articleNO);
	}
	
	public void test(int i) throws Exception {
		boardDAO.testInsert(i);
	}

	
}
