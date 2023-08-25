package Graph;

import java.util.*;

public class PrintCashFlow {

	

	    public static void minimumCashFlow(float[][] graph, ArrayList<Person> persons) {
	        float[] amount = getAmount(graph);
	        minimumCashFlow(amount, persons);

	    }


	    
	    public static void minimumCashFlow(float[] amount, ArrayList<Person> persons) {

	        //@// TODO: 13/12/16  
	        int maximumCreditPerson = getMaxPerson(amount);
	        int maximumDebitPerson = getMinPerson(amount);

	        if (amount[maximumCreditPerson] == 0 && amount[maximumDebitPerson] == 0) {
	            return;
	        }

	        float min = Math.min(-amount[maximumDebitPerson], amount[maximumCreditPerson]);

	        amount[maximumCreditPerson] -= min;
	        amount[maximumDebitPerson] += min;
	        
	        System.out.println(persons.get(maximumDebitPerson).name + " pays " + min + " to " + persons.get(maximumCreditPerson).name);


//	        System.out.println("Person(" + maximumDebitPerson + ") pays " +
//	                min + " to Person(" + maximumCreditPerson + ")");

	        minimumCashFlow(amount,persons);
	    }

	    private static int getMinPerson(float[] amount) {
	        int minPerson = 0;
	        for (int i = 1; i < amount.length; i++) {
	            if (amount[i] < amount[minPerson]) {
	                minPerson = i;
	            }
	        }

	        return minPerson;
	    }

	    private static int getMaxPerson(float[] amount) {
	        int maxPerson = 0;
	        for (int i = 1; i < amount.length; i++) {
	            if (amount[i] > amount[maxPerson]) {
	                maxPerson = i;
	            }
	        }
	        return maxPerson;
	    }

	    private static float[] getAmount(float[][] graph) {
	        float amount[] = new float[graph.length];

	        for (int i = 0; i < graph.length; i++) {
	            for (int j = 0; j < graph.length; j++) {
	                /**
	                 * Here  graph[j][i] => incoming amount
	                 * and  graph[i][j] => outgoing amount
	                 */
	                amount[i] += graph[j][i] - graph[i][j];
	            }
	        }

	        return amount;
	    }
	}
