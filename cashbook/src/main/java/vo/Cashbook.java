package vo;

public class Cashbook {
	private int cashbookNo;
	private String cashDate;
	private String kind;
	private int cash;
	private String memo;
	private String updateDate;
	private String CreateDate;
	
	@Override
	public String toString() {
		return "Cashbook [cashbookNo=" + this.cashbookNo + ", cashDate=" + this.cashDate + ", kind=" + this.kind + ", cash=" + this.cash
				+ ", memo=" + this.memo + ", updateDate=" + this.updateDate + ", CreateDate=" + this.CreateDate + "]";
	}
	public int getCashbookNo() {
		return cashbookNo;
	}
	public String getCashDate() {
		return cashDate;
	}
	public String getKind() {
		return kind;
	}
	public int getCash() {
		return cash;
	}
	public String getMemo() {
		return memo;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public String getCreateDate() {
		return CreateDate;
	}
	public void setCashbookNo(int cashbookNo) {
		this.cashbookNo = cashbookNo;
	}
	public void setCashDate(String cashDate) {
		this.cashDate = cashDate;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}
	
	
}
