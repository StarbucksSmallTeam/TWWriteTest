public class Item {

	public static int mai2zeng1 = 0;
	public static int jiuwuzhe = 1;

	public boolean[] activity = new boolean[2];
	public String code;
	public String name;
	public String unit;
	public double price;
	public int type;

	public Item(String code, String name, String unit, double price, int type,
			boolean ismai2zeng1, boolean isjiuwuzhe) {
		this.code = code;
		this.name = name;
		this.unit = unit;
		this.price = price;
		this.type = type;
		activity[mai2zeng1] = ismai2zeng1;
		activity[jiuwuzhe] = isjiuwuzhe;
	}

}
