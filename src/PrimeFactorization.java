import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PrimeFactorization {
	public List<Integer> primeArray(int number){
		boolean primeBoolArray[] = new boolean[number];

		//Loop to pre-make the array for intended operation
		for (int i = 2;i<number;i++){
			primeBoolArray[i] = true;
		}

		//Loop to evaluate the primes by index number
		for (int i = 2;i<Math.sqrt(number);i++){
			for (int j = 2;j*i<number;j++){
				primeBoolArray[i*j] = false;
			}
		}

		//PREPARE THE INTEGER ARRAY
		List<Integer> primeList = new ArrayList<>();

		//Check each index. If the element at that index is true then
		//add the index number to the list.
		for(int i = 2;i<number;i++){
			if (primeBoolArray[i]){
				primeList.add(i);
			}
		}

		return primeList;
	}

	//Method to check a number is perfect square or not.
	private boolean isPerfectSqure(int number){
		double result = Math.sqrt(number);
		double result2 = Math.ceil(result);
		double result3 = result - result2;
		if (result3 == 0){
			return true;
		}else{
			return false;
		}
	}

	//Check a number is perfect cube or not
	private boolean isPerfectCube(int number){
		double result = Math.pow(number,1/3);
		double result2 = Math.ceil(result);
		double result3 = result - result2;
		if (result3 == 0){
			return true;
		}else{
			return false;
		}
	}


	public static void main(String args[]){
		//Object of the class PrimeFactorization
		PrimeFactorization primeFactorization = new PrimeFactorization();

		int numberForFactorization = 100;

		Scanner sc = new Scanner(System.in);
		numberForFactorization = sc.nextInt();

		//List to accommodate the returned list of primes
		List<Integer> primes;
		//List for keeping the factors during the computation
		List<Integer> factors = new ArrayList<>();



		primes = primeFactorization.primeArray(numberForFactorization+1);
		Iterator<Integer> itr = primes.iterator();
		int prime = 0;
		prime = itr.next();

		//Loop to get the factors of the provided number.
		while (numberForFactorization>=2){

			if (numberForFactorization%prime == 0){
				numberForFactorization/=prime;
				factors.add(prime);
			}else {
				if (itr.hasNext()){
					prime = itr.next();
				}
			}

		}

		//Iterate over the factors list
		Iterator<Integer> itrFactors = factors.iterator();
		while (itrFactors.hasNext()){
			System.out.print(itrFactors.next()+"\t");
		}
	}
}
