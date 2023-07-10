public class Fee {
    public static void main(String[] args) {
        // 0 , 0 -> 101
        // 0 , 1 -> 102
        int[][] array = new int[20][2]; // 21층 2호
        // Q1. 가장 비싼 관리비를 낸 호수 와 가장 적게 관리비를 내 호수를 찾기
        // Q2. 관리비 중위값 그리고 중위값의 해당하는 호수 찾기
        // Q3. 관리비 평균값 그리고 평균값에 가장 가까운 호수 찾기
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[1].length; j++) {
                array[i][j] = (int) ((Math.random() * 50001) + 50000);
            }
        }
        // Q3. 관리비 평균값 그리고 평균값에 가장 가까운 호수 찾기
        int[] storeTotal = new int[40];
        int[] storePrice1 = new int[20];
        int[] storePrice2 = new int[20];
        for (int i = 0; i < array.length; i++) {
            storePrice1[i] = array[i][0];
        }
        for (int i = 0; i < array.length; i++) {
            storePrice2[i] = array[i][1];
        }
        for (int i = 0; i < array.length; i++) {
            storeTotal[i] = storePrice1[i];
        }
        for (int i = 0; i < array.length; i++) {
            storeTotal[i + 20] = storePrice2[i];
        }
        int totalValue = 0;
        for (int i = 0; i < storeTotal.length; i++) {
            totalValue += storeTotal[i];
        }

        int average = totalValue / storeTotal.length;
        System.out.println("Average Price: " + average);

        int vary = 500000; // 차이
        int target = -1;
        for (int i = 0; i < storeTotal.length; i++) {
            if (vary > Math.abs(storeTotal[i] - average)) {
                vary = Math.abs(storeTotal[i] - average);
                target = i;
            }
        }

        System.out.println("closest price: " + storeTotal[target]);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[1].length; j++) {
                if (array[i][j] == storeTotal[target]) {
                    System.out.println("closest door number :" + (i + 1) + "0" + (j + 1));
                }
            }
        }

        // Q2
        // 중위값 구하는기
        // 20개의 행 그리고 2개의 열
        // 40개
        // int[] storeTotal = new int[40];
        // int[] storePrice1 = new int[20];
        // int[] storePrice2 = new int[20];
        // for (int i = 0; i < array.length; i++){
        // storePrice1[i] = array[i][0];
        // }
        // for (int i=0; i <array.length; i++){
        // storePrice2[i] = array[i][1];
        // }
        // for (int i = 0; i< array.length; i++){
        // storeTotal[i] = storePrice1[i];
        // }
        // for(int i = 0; i<array.length; i++){
        // storeTotal[i+20]= storePrice2[i];
        // }

        for (int i = 0; i < storeTotal.length; i++) {
            for (int j = 0; j < storeTotal.length - 1; j++) {
                if (storeTotal[i] > storeTotal[j]) {
                    int temp = 0;
                    temp = storeTotal[j];
                    storeTotal[j] = storeTotal[i];
                    storeTotal[i] = temp;
                }
            }
        }
        int median = storeTotal[20];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[1].length; j++) {
                if (array[i][j] == median) {
                    System.out.println("가장 중위값 호수는 :" + (i + 1) + "0" + (j + 1));
                }
            }
        } // DONE

        // Q1
        // int maxFee = 0;
        // int minFee = 110000;
        // for (int i = 0; i < array.length; i++){
        // for (int j =0 ; j < array[1].length; j ++){
        // if (array[i][j] > maxFee){
        // maxFee = array[i][j];
        // }
        // if (array[i][j]< minFee){
        // minFee = array[i][j];
        // }
        // }
        // }
        // for (int i = 0; i < array.length; i++){
        // for (int j =0 ; j < array[1].length; j ++){
        // if (array[i][j] == maxFee){
        // System.out.println("가장 많이 낸 호수는 :"+(i+1)+"0"+(j+1));
        // }
        // if (array[i][j] == minFee){
        // System.out.println("가장 적게 낸 호수는 :"+(i+1)+"0"+(j+1));
        // }
        // }
        // }

        // System.out.println("가장 비싼 관리비:"+maxFee);
        // System.out.println("가장 적은 관리비:"+minFee);

        // for (int i = 0; i <array.length; i++){
        // for(int j = 0; j <array[0].length; j++){
        // System.out.print(array[i][j]+" ");
        // }
        // System.out.println();
        // }
    }
}
