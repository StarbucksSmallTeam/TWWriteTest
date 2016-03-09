public class Activity {
	public static int CODE_NULL = -1;
	public static int CODE_MAISONG = 0;
	public static int CODE_DAZHE = 1;

	int type;
	int weight;

	// 如果是满送
	int num1, num2;

	// 如果是打折
	double dazhe;

	public Activity(int type, int weight) {
		this.type = type;
		this.weight = weight;
	}

}
