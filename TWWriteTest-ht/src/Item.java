import java.util.ArrayList;

public class Item {

	public String code;
	public String name;
	public String unit;
	public double price;
	public int type;
	public ArrayList<Activity> activityList;

	public Item(String code, String name, String unit, double price, int type) {
		this.code = code;
		this.name = name;
		this.unit = unit;
		this.price = price;
		this.type = type;
		activityList = new ArrayList<Activity>();
	}

	public void add(Activity activity) {
		activityList.add(activity);
	}

}
