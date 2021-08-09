package com.nike.dto;

import java.io.File;
import java.sql.Date;

public class BoardDTO {
	private int boardNumber; //게시판 글번호
	private String title; //게시판 글 제목
	private String content; //게시판 글 내용
	private String writer; //게시판 글 등록한사람
	private Date date;
	private int hitcount; //게시판 조회수
	private int ref;//그룹번호(몇번글에대한 댓글인지)
	private int step; //(그룹내에서 순서) ==> 최근글일수록 위로올라오게한다.
	private int level; //들여쓰기
	private String file;
	private String filePath;

	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getBoardNumber() {
		return boardNumber;
	}
	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	@Override
	public String toString() {
		return "BoardDTO [boardNumber=" + boardNumber + ", title=" + title + ", content=" + content + ", writer="
				+ writer + ", date=" + date + ", hitcount=" + hitcount + ", ref=" + ref + ", step=" + step + ", indent="
				+ level +", file="+file +"]";
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getHitcount() {
		return hitcount;
	}
	public void setHitcount(int hitcount) {
		this.hitcount = hitcount;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
