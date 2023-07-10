public class For {

    public static void main(String[] args) {

        /*
        * 
        **
        ***
        ****
        *****
        */

        // for(int i = 1; i < 6 ; i++){
        // for (int j= 0 ; j < i; j++){
        // System.out.print("*");
        // }
        // System.out.println();
        // }

        // for (int i = 1; i<4; i++){
        // if (i >= 4){

        // }
        // for (int j= 0 ; j < i; j++){
        // System.out.print("*");
        // }
        // System.out.println();
        // }
        /*  
        
         * 
         **
         ***
         **
         *
         
         */

        for (int i = 1; i < 8; i++) {
            if (i < 5) {
                for (int j = 0; j < i; j++) {
                    System.out.print("*");
                }
            } else {
                for (int j = 8 - i; j > 0; j--) {
                    System.out.print("*");
                }
            }
            System.out.println();
        }

    }

}
