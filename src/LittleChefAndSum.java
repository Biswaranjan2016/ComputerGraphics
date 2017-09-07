import java.util.Scanner;

class LittleChefAndSum {

	public static void main(String args[]){

		int t=0;
		int n=0;
		int arr[];

		LittleChefAndSum littleChefAndSum = new LittleChefAndSum();
		Scanner sc = new Scanner(System.in);
		t=sc.nextInt();

		while (t>0){
			n = sc.nextInt();
			arr = new int[n];
			n=0;
			while (n<arr.length){
				arr[n] = sc.nextInt();
				n++;
			}
			littleChefAndSum.sumCalculation2(arr);
			t--;
		}
	}

	private void sumCalculation2(int arr[]){
		int length = arr.length;
		int minIndex,maxIndex;
		int minIndexSum = 0,maxIndexSum = 0 ;
		int minIndexValRes = 99999999,minIndexRes = 0;

		for (int i = 0;i<length;i++){

			minIndex = i;
			maxIndex = i;

			minIndexSum = 0;
			maxIndexSum = 0;



			boolean minBool = true;
			boolean maxBool = true;

			while (minBool || maxBool){
				if (minIndex>0){
					minIndexSum += arr[minIndex];
					minIndex--;
				}else {
					minBool = false;
				}
				if (maxIndex<length){
					maxIndexSum += arr[maxIndex];
					maxIndex++;
				}else{
					maxBool = false;
				}
			}

			if (minIndexSum+maxIndexSum < minIndexValRes){
				if (minIndexValRes != minIndexSum+maxIndexSum){
					minIndexRes = i;
					minIndexValRes = minIndexSum+maxIndexSum;
				}

			}
		}
		System.out.println(minIndexRes+1);
	}
}
