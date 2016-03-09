import java.text.DecimalFormat;
import java.util.ArrayList;

public class Bill {

	ArrayList<BillItem> billItem;
	double totalMoney;
	double totalSave;
	boolean ismai2zengyi;

	public Bill() {
		billItem = new ArrayList<Bill.BillItem>();
		totalMoney = 0;
		totalSave = 0;
		ismai2zengyi = false;
	}

	void addItem(BuysItem item, double sumMoney, double save, int mai2zeng1) {
		billItem.add(new BillItem(item, sumMoney, save, mai2zeng1));
		totalMoney += sumMoney;
		totalSave += save;
		if (mai2zeng1 > 0)
			ismai2zengyi = true;
	}

	void printBill() {

		DecimalFormat df = new DecimalFormat("#.00");

		System.out.println("***<没钱赚商店>购物清单***");
		for (int i = 0; i < billItem.size(); i++) {
			BillItem item = billItem.get(i);
			System.out.print("名称：" + item.buysItem.item.name + "，数量："
					+ item.buysItem.count + item.buysItem.item.unit + "，单价："
					+ df.format(item.buysItem.item.price) + "(元)，小计："
					+ df.format(item.sumMoney) + "(元)");
			if (item.save > 0) {
				System.out.print("，节省" + df.format(item.save) + "(元)");
			}
			System.out.println();
		}
		System.out.println("----------------------");

		if (ismai2zengyi) {
			System.out.println("买二赠一商品：");
			for (int i = 0; i < billItem.size(); i++) {
				BillItem item = billItem.get(i);
				if (item.mai2zeng1 > 0) {
					System.out.println("名称：" + item.buysItem.item.name + "，数量："
							+ item.mai2zeng1 + item.buysItem.item.unit);
				}
			}
			System.out.println("----------------------");
		}

		System.out.println("总计：" + df.format(totalMoney) + "(元)");
		System.out.println("节省：" + df.format(totalSave) + "(元)");
		System.out.println("**********************");

	}

	class BillItem {
		BuysItem buysItem;
		double sumMoney;
		double save;
		int mai2zeng1;

		BillItem(BuysItem item, double sumMoney, double save, int mai2zeng1) {
			buysItem = item;
			this.sumMoney = sumMoney;
			this.save = save;
			this.mai2zeng1 = mai2zeng1;
		}

	}

}
