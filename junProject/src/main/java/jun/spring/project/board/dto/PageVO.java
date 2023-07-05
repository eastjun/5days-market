package jun.spring.project.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageVO {
	
		private int startRow;   // 시작 행 번호
	    private int endRow;     // 끝 행 번호
	    private int total;      // 전체 게시글 수
	    private int currentPage; // 현재 페이지
	    private int totalPages; // 전체 페이지 수
	    private int startPage;  // 시작 페이지 번호
	    private int endPage;    // 끝 페이지 번호
	    private int displayPageSize = 10; // 페이지당 보여줄 페이지 수

	    public PageVO(int total, int currentPage) {
	        this.total = total;
	        this.currentPage = currentPage;
	        calculateRows();
	        calculatePages();
	    }

	    private void calculateRows() {
	        this.startRow = (currentPage - 1) * displayPageSize + 1;
	        this.endRow = startRow + displayPageSize - 1;
	    }

	    private void calculatePages() {
	        this.totalPages = (int) Math.ceil((double) total / displayPageSize);
	        int currentBlock = (int) Math.ceil((double) currentPage / displayPageSize);
	        this.startPage = (currentBlock - 1) * displayPageSize + 1;
	        this.endPage = startPage + displayPageSize - 1;

	        if (endPage > totalPages) {
	            this.endPage = totalPages;
	        }
	    }

}
