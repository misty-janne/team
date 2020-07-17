package kr.co.domain;

import java.io.Serializable;

public class BoardVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int bno;
	private String title;
	private String content;
	private String writer;
	private int viewCnt;
	private String regDate;
	private String updateDate;
	private int repCnt;
	private String[] files; //업로드 파일 전부
	
	//files 추가된 사용자 생성자	
	public BoardVO(int bno, String title, String content, String writer, int viewCnt, String regDate, String updateDate,
			int repCnt, String[] files) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.viewCnt = viewCnt;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.repCnt = repCnt;
		this.files = files;
	}
	
	//files get/set
	public String[] getFiles() {
		return files;
	}
	
	public void setFiles(String[] files) {
		this.files = files;
	}
	



	public BoardVO() {
		// TODO Auto-generated constructor stub
	}
	


	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer + ", viewCnt="
				+ viewCnt + ", regDate=" + regDate + ", updateDate=" + updateDate + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bno;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardVO other = (BoardVO) obj;
		if (bno != other.bno)
			return false;
		return true;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getRepCnt() {
		return repCnt;
	}
	
	public void setRepCnt(int repCnt) {
		this.repCnt = repCnt;
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

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BoardVO(int bno, String title, String content, String writer, int viewCnt, String regDate,
			String updateDate, int repCnt) {
		super();
		this.repCnt = repCnt;
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.viewCnt = viewCnt;
		this.regDate = regDate;
		this.updateDate = updateDate;
	}


}
