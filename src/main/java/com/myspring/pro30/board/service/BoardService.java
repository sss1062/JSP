package com.myspring.pro30.board.service;

import java.util.List;
import java.util.Map;

import com.myspring.pro30.board.vo.ArticleVO;

public interface BoardService {
	public Map listArticles(Map pagingMap) throws Exception;
//	public List<ArticleVO> listArticles1() throws Exception;
	public int addNewArticle(Map articleMap) throws Exception;
//	public ArticleVO viewArticle(int articleNO) throws Exception;
	public Map viewArticle(int articleNO) throws Exception;
	public void modArticle(Map articleMap) throws Exception;
	public void removeArticle(int articleNO) throws Exception;
	public void count(int articleNO) throws Exception; // ��ȸ�� �ø��� �޼ҵ�
	public void test(int i) throws Exception;
}
