package kr.or.ctw.common.vo;

import java.util.List;

import kr.or.ddit.commons.ibatis.Alias;

@Alias("pagingVO")
public class pagingVO<T> {	
		private String searchType;	// 검색 조건
		private String searchWord;	// 검색 단어
		private int screenSize;		// 페이지에 표시될 게시글의 갯수
		private int blockSize;		// 페이지 블럭 갯수
		private int currentPage;	// 현재 페이지
		private int totalRecords;	// 전체 게시글 수
		private int totalPages;		// 전체 페이지
		private int startRow;		// 페이지 시작번호
		private int endRow;			// 페이지 끝번호
		private int startPage;		// 페이지 블럭의 시작번호
		private int endPage;		// 페이지 블럭의 끝번호
		private String pagingHTML;	// HTML문 처리
		private List<T> dataList ;	// 가져올 데이터의 정보
		
		public pagingVO(){
			super();
		}
		public pagingVO(int screenSize, int blockSize){
			super();
			// 생성자로 처음 표시될 게시글의 갯수와 페이지 블럭의 갯수를 parameter값으로 넘겨받는다.
			// 페이지에 뿌려질 게시글 갯수
			this.screenSize = screenSize;
			// 밑에 페이지 번호 표시 갯수
			this.blockSize = blockSize;	
		}
		public int getTotalReords(){
			// DB에서 전체 데이터 갯수를 받아온다.
			return totalRecords;
		}
		public void setTotalRecords(int totalRecords){
			this.totalRecords = totalRecords;
			
			totalPages = (int)Math.ceil(totalRecords/(double)screenSize);
			
			endRow = currentPage*screenSize;
			startRow = endRow - (screenSize-1);
			
			startPage = (currentPage-1)/blockSize * blockSize+1;
			endPage = startPage +(blockSize-1);
			
			StringBuffer html = new StringBuffer("");
			
			if(startPage>1){
				html.append("<a class='numPrev' href='javascript:goPage("+(startPage-blockSize)+")'>"+"<img src='${pageContext.request.contextPath }/resources/common/image/leftArrow.png' width='20px'></a>&nbsp;");
				
			}
			for(int i =startPage; i <=(endPage>totalPages?totalPages:endPage); i++){
				if(i==currentPage){
					html.append("<span class='cp'>"+i+"</span>&nbsp;");
				}else{
					html.append("<a class='numBox' href='javaScript:goPage("+i+")'>"+i+"</a>&nbsp;");
				}
				
			}
			if(endPage<totalPages){
				html.append("<a class='numNext' href='javascript:goPage("+(endPage+1)+")'>"+"<img src='${pageContext.request.contextPath }/resources/common/image/rightArrow.png' width='20px'></a>");
			}
			
			pagingHTML = html.toString();
		}
		
		public String getSearchType() {
			return searchType;
		}
		public void setSearchType(String searchType) {
			this.searchType = searchType;
		}
		public String getSearchWord() {
			return searchWord;
		}
		public void setSearchWord(String searchWord) {
			this.searchWord = searchWord;
		}
		public int getScreenSize() {
			return screenSize;
		}
		public void setScreenSize(int screenSize) {
			this.screenSize = screenSize;
		}
		public int getBlockSize() {
			return blockSize;
		}
		public void setBlockSize(int blockSize) {
			this.blockSize = blockSize;
		}
		public int getTotalPages() {
			return totalPages;
		}
		public void setTotalPages(int totalPages) {
			this.totalPages = totalPages;
		}
		public int getCurrentPage() {
			return currentPage;
		}
		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}
		public int getStartRow() {
			return startRow;
		}
		public void setStartRow(int startRow) {
			this.startRow = startRow;
		}
		public int getEndRow() {
			return endRow;
		}
		public void setEndRow(int endRow) {
			this.endRow = endRow;
		}
		public int getStartPage() {
			return startPage;
		}
		public void setStartPage(int startPage) {
			this.startPage = startPage;
		}
		public int getEndPage() {
			return endPage;
		}
		public void setEndPage(int endPage) {
			this.endPage = endPage;
		}
		public String getPagingHTML() {
			return pagingHTML;
		}
		public void setPagingHTML(String pagingHTML) {
			this.pagingHTML = pagingHTML;
		}
		public List<T> getDataList() {
			return dataList;
		}
		public void setDataList(List<T> detaList) {
			// 게시글 정보를 받아온다.
			this.dataList = detaList;
		}
}
