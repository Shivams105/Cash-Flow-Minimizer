package Graph;

import java.util.*;

public class splitwise {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("WELCOME TO SPLITWISE WORLD");
		System.out.println("Enter the number of persons in the group");
		int personCount = scanner.nextInt();
		//Person[] persons = new Person[personCount];
		HashMap<String, Integer> personsMap = new HashMap<>();
		ArrayList<Person> persons = new ArrayList<>();
		for(int i=0; i<personCount; i++) {
			System.out.println("Enter name and age of person"+(i+1));
			String name = scanner.next();
			int age = scanner.nextInt();
			//float amount = scanner.nextInt();
			Person person = new Person(name,age,0);
			//persons[i] = person;
			personsMap.put(name, i);
			persons.add(person);
		}
		System.out.println("Type new to start a new transaction");
		String start = scanner.next();
		float[][] graph = new float[personCount][personCount];
	
		while(start.equals("new")) {
			System.out.println("Enter the name of payer");
			String self = scanner.next();
			int selfIndex = personsMap.get(self);
			System.out.println("Enter the amount");
			float amt = scanner.nextInt();
			System.out.println("Number of person to be splitted including yourself");
			int count = scanner.nextInt();
			float divAmt = amt/count;
			if(count == personCount) {
				for(int i=0; i<count; i++) {
					graph[i][selfIndex] += divAmt;
				}
			}else {
				System.out.println("Enter the name of persons");
				for(int i=0; i<count; i++) {
					String debtor = scanner.next();
					if(personsMap.containsKey(debtor)) {
						int debtorIndex = personsMap.get(debtor);
						graph[debtorIndex][selfIndex] += divAmt;
					}else {
						System.out.println("Person not in group");
					}
					
				}
			}
			System.out.println("Type \"new\" for next trasaction or type \"settle\" to settle");
			start = scanner.next();
		}
		for(int i=0; i<personCount; i++) {
			for(int j=0; j<personCount; j++) {
				System.out.print(graph[i][j] + " ");
			}
			System.out.println("");
		}
		
		PrintCashFlow m = new PrintCashFlow();
        m.minimumCashFlow(graph,persons);

	}

}
