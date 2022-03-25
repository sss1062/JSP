package com.myspring.pro30.board.vo;

import com.myspring.pro30.board.dao.BoardDAO;
import com.myspring.pro30.board.dao.BoardDAOImpl;

public class PageVO {
	private final static int pageCount = 10;
	private int blockStartNum = 0;
	private int blockLastNum = 0;
	private int lastPageNum = 0;
	
	public int getBlockStartNum() {
		return blockStartNum;
	}
	public int getBlockLastNum() {
		return blockLastNum;
	}
	public int getLastPageNum() {
		return lastPageNum;
	}
	public void setBlockStartNum(int blockStartNum) {
		this.blockStartNum = blockStartNum;
	}
	public void setBlockLastNum(int blockLastNum) {
		this.blockLastNum = blockLastNum;
	}
	public void setLastPageNum(int lastPageNum) {
		this.lastPageNum = lastPageNum;
	}
	// block����
	// ���� �������� ���� block�� ���� ��ȣ, �� ��ȣ�� ���
	public void makeBlock(int curPage) {
		int blockNum = 0;
		
		blockNum = (int)Math.floor((curPage-1)/pageCount); // �۰ų� ���� ������ ��ȯ
		blockStartNum = (pageCount + blockNum) + 1;
		blockLastNum = blockStartNum + (pageCount - 1);
	}
	
	//�� �������� ������ ��ȣ
	public void makeLastPageNum() {
		BoardDAO dao = new BoardDAOImpl();
		int total = dao.totalCount();
		
		if( total % pageCount == 0) {
			lastPageNum = (int)Math.floor(total/pageCount);
		}else {
			lastPageNum = (int)Math.floor(total/pageCount) + 1;
		}
		
	}
}
