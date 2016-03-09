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
		goods.add(new Item("ITEM000001", "可口可乐", "瓶", 3, 0, false, false));
		goods.add(new Item("ITEM000002", "羽毛球", "个", 1, 1, true, false));
		goods.add(new Item("ITEM000003", "苹果", "斤", 5.5, 2, false, true));
		goods.add(new Item("ITEM000004", "李赵宁", "吨", 2, 3, true, true));
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
			if (item.item.activity[Item.mai2zeng1]) {
				int num = item.count / 3;
				bill.addItem(item, item.item.price * (item.count - num), num
						* item.item.price, num);
			} else if (item.item.activity[Item.jiuwuzhe]) {
				bill.addItem(item, item.item.price * item.count * 0.95,
						item.item.price * item.count * 0.05, 0);
			} else {
				bill.addItem(item, item.item.price * item.count, 0, 0);
			}
		}

		bill.printBill();

	}

}
