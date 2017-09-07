public class ThreadExample implements Runnable {
	public void run(){
		for (int i = 0;i<10; i++){
			try{
				Thread.sleep(700);
				System.out.println("i:\t"+i);
			}catch (Exception e){

			}
		}
	}
	public static void main(String args[]){
		ThreadExample threadExample = new ThreadExample();
		Thread thread = new Thread(threadExample);
		thread.start();
	}
}
