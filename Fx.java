package struct;

/**
 * Class with static methods which can be used in other algorithms.
 */
public class Fx {
	/**
	 * Merge Sort for the array of integers
	 * @param Arr is the array to sort
	 */
	public static void mergeSort(int[] Arr) {
		int size = Arr.length;
		/* each element in areas[] represents the size of each sorted area from the beginning
		 * size of the array is 31+1 for 2^31 which is limit for positive 32 bit integers */
		int[] areas = new int[32];
		areas[0] = 0; // end of areas, never reached
		int pointer = 0; // current element
		int[] tempArray; // temporary array for merging two areas
		int tempId; // it's current element
		int areaId = 1; // current area
		int tmp; //variable for swapping the elements
		int leftId; // current element of a left merging area; it's borders are below
		int leftBegin;
		int leftEnd;
		int rightId; // current element of a left merging area; it's borders are below
		int rightBegin;
		int rightEnd;
		boolean leftHasTail; // variable that indicates if the left area has remainings when merging
		while (pointer < (size - 1)) {
			if (Arr[pointer] > Arr[pointer + 1]){
				tmp = Arr[pointer]; Arr[pointer] = Arr[pointer + 1]; Arr[pointer + 1] = tmp;
			}
			areas[areaId] = 2;
			// merging with equable previous areas
			while (areas[areaId] == areas[areaId - 1]){
				rightEnd = pointer + 1;
				leftEnd = (pointer + 1) - areas[areaId];
				rightBegin = (pointer + 2) - areas[areaId];
				leftBegin = (pointer + 2) - 2*areas[areaId];
				rightId = rightBegin;
				leftId = leftBegin;
				tempArray = new int[2*areas[areaId]];
				tempId = 0;
				leftHasTail = false;
				while (true) {
					if (Arr[leftId] < Arr[rightId]) {
						tempArray[tempId++] = Arr[leftId++];
						if (leftId > leftEnd) break;
					} else {
						tempArray[tempId++] = Arr[rightId++];
						if (rightId > rightEnd)
							{ leftHasTail = true; break; }
					}
				}
				if (leftHasTail)
					for (int i = leftId; i <= leftEnd; i++)
						Arr[i + areas[areaId]] = Arr[i];
				for (int i = 0; i < tempId; i++) Arr[leftBegin + i] = tempArray[i];
				areas[areaId - 1] = 2*areas[areaId];
				areas[areaId--] = 0;
			}	
			// merging end
			areaId++;
			pointer = pointer + 2;
		}
		if (pointer == (size - 1)) areas[areaId] = 1;
		else { areaId--; pointer--; }
		// merging with remeining previous areas
		while (areas[areaId] < areas[areaId - 1]) {
			rightEnd = pointer;
			leftEnd = pointer - areas[areaId];
			rightBegin = (pointer + 1) - areas[areaId];
			leftBegin = (pointer + 1) - (areas[areaId] + areas[areaId - 1]);
			rightId = rightBegin;
			leftId = leftBegin;
			tempArray = new int[areas[areaId] + areas[areaId - 1]];
			tempId = 0;
			leftHasTail = false;
			while (true) {
				if (Arr[leftId] < Arr[rightId]) {
					tempArray[tempId++] = Arr[leftId++];
					if (leftId > leftEnd) break;
				} else {
					tempArray[tempId++] = Arr[rightId++];
					if (rightId > rightEnd)
						{ leftHasTail = true; break; }
				}
			}
			if (leftHasTail)
				for (int i = leftEnd; i >= leftId; i--)
					Arr[i + areas[areaId]] = Arr[i];
			for (int i = 0; i < tempId; i++) Arr[leftBegin + i]=tempArray[i];
			areas[areaId - 1] = areas[areaId] + areas[areaId - 1];
			areas[areaId--] = 0;
		}	
		// merging end
	}

	/**
	 * Quick sort for the integer numbers within a narrow range beginning with zero.
	 * @param Arr array to sort
	 * @param cap the maximum element in the array (the right border of the range)
	 * @throws IllegalArgumentException if the array reaches beyond the specified border 
	 */
	public static void countSort(int[] Arr, int cap) throws IllegalArgumentException {
		try {
			int[] store = new int[cap + 1];
			for (int n : Arr) {
				store[n]++;
			}
			int k = 0;
			int i = 0;
			while (i < Arr.length) {
				for (int j = 0; j < store[k]; j++)
					Arr[i++] = k;
				k++;
			}
		} catch(IndexOutOfBoundsException e) {
			throw new IllegalArgumentException("given array is out of specified range", e);
		}
	}
}
