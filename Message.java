package proj;

public class Message {
	Type type;
	int x = -1;
	int y = -1;
	boolean Horz = false;
	
	public Message(Type type) {
		this.type = type;
	}
	
	public Message(Type type, int x, int y) {
		this.type = type;
		this.x= x;
		this.y = y;
	}
	
	public Message(Type type, int x, int y, boolean HV) {
		this.type = type;
		this.Horz = HV;
	}
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isVert() {
		assert type == Type.PLACE;
		return Horz;
	}

	public void setHorz(boolean Horz) {
		this.Horz = Horz;
	}
	
}
