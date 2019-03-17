import java.math.BigInteger;

public class RedBlackTree {
	public static final int RED = 1;
	public static final int BLACK = 0;
	private RedBlackNode root;
	private RedBlackNode sentinel;

	public RedBlackTree() {
		sentinel = new RedBlackNode(null, null, BLACK, null, null, null);
		root = new RedBlackNode(null, null, BLACK, sentinel, sentinel, sentinel);

	}

	public BigInteger value(String v) {
		RedBlackNode x = this.root;
		RedBlackNode y = this.sentinel;
		while (x != this.sentinel) {
			y = x;
			if (x.getKey().compareTo(v) < 0) {
				x = x.getRc();
			} else if (x.getKey().compareTo(v) > 0) {
				x = x.getLc();
			} else if (x.getKey().compareTo(v) == 0) {
				return x.getValue();
			}
		}
		return y.getValue();

	}

	public boolean contains(String v) {
		RedBlackNode r = this.root;
		if(r.getKey() == null) {
			return false;
		}
		while (r != sentinel) {
			if (r.getKey().compareTo(v) == 0) {
				return true;
			} else if (r.getKey().compareTo(v) < 0) {
				r = r.getRc();
			} else if (r.getKey().compareTo(v) > 0) {
				r = r.getLc();
			}
		}
		return false;
	}

	public void insert(String key, BigInteger value) {
		RedBlackNode newNode = new RedBlackNode(key, value, RED, sentinel, sentinel, sentinel);
		RedBlackNode x = this.root;
		RedBlackNode y = this.sentinel;
		if (root.getKey() == null) {
			root.setKeyValue(key, value);
			this.RBInsertFixup(newNode);
		} else {
			while (x != this.sentinel) {
				y = x;
				if (x.getKey().compareTo(key) < 0) {
					x = x.getRc();
				} else if (x.getKey().compareTo(key) > 0) {
					x = x.getLc();
				} else if (x.getKey().compareTo(key) == 0) {
					x.setKeyValue(key, value);
					return;
				}
			}
			if (key.compareTo(y.getKey()) < 0) {
				y.setLc(newNode);
				newNode.setP(y);
			} else {
				y.setRc(newNode);
				newNode.setP(y);
			}
			this.RBInsertFixup(newNode);
		}
	}

	public void leftRotate(RedBlackNode x) {
		RedBlackNode y = x.getRc();
		x.setRc(y.getLc());
		y.getLc().setP(x);
		y.setP(x.getP());
		if (x.getP() == this.sentinel) {
			this.root = y;
		} else if (x == x.getP().getLc()) {
			x.getP().setLc(y);
		} else {
			x.getP().setRc(y);
		}
		y.setLc(x);
		x.setP(y);

	}

	public void RBInsertFixup(RedBlackNode z) {
		while (z.getP().getColor() == RED) {
			if (z.getP() == z.getP().getP().getLc()) {
				RedBlackNode y = z.getP().getP().getRc();
				if (y.getColor() == RED) {
					z.getP().setColor(BLACK);
					y.setColor(BLACK);
					z.getP().getP().setColor(RED);
					z = z.getP().getP();
				} else {
					if (z == z.getP().getRc()) {
						z = z.getP();
						this.leftRotate(z);
					}
					z.getP().setColor(BLACK);
					z.getP().getP().setColor(RED);
					this.rightRotate(z.getP().getP());
				}
			} else {
				RedBlackNode y = z.getP().getP().getLc();
				if (y.getColor() == RED) {
					z.getP().setColor(BLACK);
					y.setColor(BLACK);
					z.getP().getP().setColor(RED);
					z = z.getP().getP();

				} else {
					if (z == z.getP().getLc()) {
						z = z.getP();
						this.rightRotate(z);
					}
					z.getP().setColor(BLACK);
					z.getP().getP().setColor(RED);
					this.leftRotate(z.getP().getP());
				}
			}
		}
		this.root.setColor(BLACK);

	}

	public void rightRotate(RedBlackNode x) {
		RedBlackNode y = x.getLc();
		x.setLc(y.getRc());
		y.getRc().setP(x);
		y.setP(x.getP());
		if (x.getP() == this.sentinel) {
			this.root = y;
		} else if (x == x.getP().getLc()) {
			x.getP().setLc(y);
		} else {
			x.getP().setRc(y);
		}
		y.setRc(x);
		x.setP(y);

	}
	public static void main(String[] args) { 
		BigInteger a = new BigInteger("789632145") ;
		BigInteger b = new BigInteger("4567337337");
		BigInteger c = new BigInteger("123456");
		
		RedBlackTree testTree = new RedBlackTree();
		testTree.insert("a", a);
		testTree.insert("b", b);
		testTree.insert("c", c);
		System.out.println(testTree.value("a"));
		System.out.println(testTree.value("b"));
		System.out.println(testTree.value("c"));
		testTree.insert("a", c);
		System.out.println(testTree.value("a"));
	}

}
