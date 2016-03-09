import java.util.ArrayList;

import org.json.JSONObject;

public class Shop {

	ArrayList<Item> goods;
	ArrayList<BuysItem> buys;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSONObject jsn = new JSONObject();
		jsn.put("item0", "ITEM000001");
		jsn.put("item1", "ITEM000001");
		jsn.put("item2", "ITEM000001");
		jsn.put("item3", "ITEM000002");
		jsn.put("item4", "ITEM000002");
		jsn.put("item5", "ITEM000002");
		jsn.put("item6", "ITEM000002");
		jsn.put("item7", "ITEM000003-2");
		jsn.put("item8", "ITEM000003");
		jsn.put("item9", "ITEM000003");
		jsn.put("item10", "ITEM000003");
		jsn.put("item11", "ITEM000004");
		jsn.put("item12", "ITEM000004-3");
		jsn.put("item13", "ITEM000004");
		jsn.put("item14", "ITEM000004");

		Shop shop = new Shop();
		shop.receiveData(jsn);
	}

	public Shop() {
		goods = new ArrayList<Item>();
		buys = new ArrayList<BuysItem>();
		initGoods();
	}

	void initGoods() {
		// 随便填充数据
		Activity mansong = new Activity(Activity.CODE_MAISONG, 1);
		mansong.num1 = 3;
		mansong.num2 = 1;

		Activity dazhe = new Activity(Activity.CODE_DAZHE, 2);
		dazhe.dazhe = 0.95;

		goods.add(new Item("ITEM000001", "可口可乐", "瓶", 3, 0));
		goods.add(new Item("ITEM000002", "羽毛球", "个", 1, 1));
		goods.add(new Item("ITEM000003", "苹果", "斤", 5.5, 2));
		goods.add(new Item("ITEM000004", "李赵宁", "吨", 2, 3));

		goods.get(1).add(mansong);
		goods.get(2).add(dazhe);
		goods.get(3).add(mansong);
		goods.get(3).add(dazhe);

	}

	void receiveData(JSONObject jsn) {
		for (int i = 0; i < jsn.length(); i++) {
			String code = jsn.getString("item" + i);
			int count = 1;
			if (code.length() > 10) {
				count = Integer.parseInt(code.substring(11, code.length()));
				code = code.substring(0, 10);
			}
			for (int j = 0; j < goods.size(); j++) {
				if (code.equals(goods.get(j).code)) {
					add(j, count);
					break;
				}
			}
		}

		calculate();

	}

	void add(int position, int count) {
		Item item = goods.get(position);
		for (int i = 0; i < buys.size(); i++) {
			if (buys.get(i).item.code.equals(item.code)) {
				buys.get(i).count += count;
				return;
			}
		}
		buys.add(new BuysItem(goods.get(position), count));
	}

	void calculate() {

		Bill bill = new Bill();

		for (int i = 0; i < buys.size(); i++) {
			BuysItem item = buys.get(i);
			Activity activity = findActivity(item.item);
			if (activity == null) {
				bill.addItem(item, item.item.price * item.count, 0, 0);
			} else if (activity.type == Activity.CODE_MAISONG) {
				int num = item.count / activity.num1 * activity.num2;
				bill.addItem(item, item.item.price * (item.count - num), num
						* item.item.price, num);
			} else if (activity.type == Activity.CODE_DAZHE) {
				bill.addItem(item, item.item.price * item.count
						* activity.dazhe, item.item.price * item.count
						* (1 - activity.dazhe), 0);
			} else {
				return;
			}
		}

		bill.printBill();

	}

	Activity findActivity(Item item) {

		int tmp = Integer.MAX_VALUE;
		Activity activity = null;

		for (int i = 0; i < item.activityList.size(); i++) {
			if (item.activityList.get(i).weight < tmp) {
				tmp = item.activityList.get(i).weight;
				activity = item.activityList.get(i);
			}
		}

		return activity;
	}

}
