import java.math.BigInteger;

public class RedBlackNode {
	public static final int RED = 1;
	public static final int BLACK = 0;
	private String key;
	private BigInteger value;
	private int color;
	private RedBlackNode p;
	private RedBlackNode lc;
	private RedBlackNode rc;

	public RedBlackNode(String key, BigInteger value, int color, RedBlackNode p, RedBlackNode lc,
			RedBlackNode rc) {
		this.key = key;
		this.value = value;
		this.color = color;
		this.p = p;
		this.lc = lc;
		this.rc = rc;
	}

	public int getColor() {
		return this.color;
	}

	public BigInteger getValue() {

		return  this.value;
	}
	public String getKey() {

		return  this.key;
	}


	public RedBlackNode getLc() {
		return this.lc;
	}

	public RedBlackNode getP() {
		return this.p;
	}

	public RedBlackNode getRc() {
		return this.rc;
	}

	public void setColor(int color) {
		this.color = color;

	}

	public void setKeyValue(String key, BigInteger value) {
		this.key = key;
		this.value = value;
	}

	public void setLc(RedBlackNode lc) {
		this.lc = lc;

	}

	public void setP(RedBlackNode p) {
		this.p = p;
	}

	public void setRc(RedBlackNode rc) {
		this.rc = rc;

	}

}
