import controller.SystemController;

//시작:11:20(수)  22:00(토)  15:00(일)   19:00(일)
//종료:15:20(수)  02:00(토)  16:00(일)	  20:40(일)
//소요:10:40:00

public class Main {
	public static void main(String[] args) {
		SystemController.getInstance().run();
	}
}