package vo;

public class Member {
	private String memberId;
	private String memberPw;
	@Override
	public String toString() {
		return "Member [memberId=" + this.memberId + ", memberPw=" + this.memberPw + "]";
	}
	public String getMemberId() {
		return memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	
	
	

}
