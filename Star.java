import java.util.ArrayList;

public class Star {
    public static class Monkey {
        int height;
        int weight;
    }

    public static class Human extends Monkey {

        public void walk() {
            System.out.println("walk");
        }

        public void speak() {
            System.out.println("speak");
        }
    }

    public static class RemoteController {
        int chennel;
        int volume;
        int maxChennel;
        int maxVolume;
        String status; // 현재 외부입력 hdmi 1 hdmi 2 hdmi 3
        ArrayList<String> options; // hdmi 1 hdmi 2 hdmi 3
        // constructor -> 생성자고 하며 클래스랑 이름이 동일해야함 -> 이 클래스가 생성될떄 단 한번만 동작하는 함수
        // RemoteController() {
        // this.chennel = 0;
        // this.volume = 0;
        // }

        RemoteController(int chennel, int volume, int totalOptions) {
            this.volume = 0;
            this.chennel = 0;
            this.maxChennel = chennel;
            this.maxVolume = volume;
            this.status = "hdmi1";
            this.options = new ArrayList<String>();
            for (int i = 1; i < totalOptions + 1; i++) {
                this.options.add("hdmi" + i);
            }
        }

        public void volumeUp() {
            if (this.volume >= this.maxVolume) {
                System.out.println("MaxVolume");
            } else {
                this.volume += 1;
            }
            System.out.println("Current volume: " + this.volume);
        }

        public void volumeDown() {
            if (this.volume == 0) {
                this.volume = 0;
            } else {
                this.volume -= 1;
            }
            System.out.println("Current volume: " + this.volume);
        }

        public void chennelUp() {
            if (this.chennel >= this.maxChennel) {
                this.chennel = 0;
            } else {
                this.chennel += 1;
            }

            System.out.println("Current chennel: " + this.chennel);
        }

        public void chenelDown() {
            if (this.chennel == 0) {
                this.chennel = 0;
            } else {
                this.chennel -= 1;
            }
            System.out.println("Current chennel: " + this.chennel);
        }

        public void showOptions() {
            for (int i = 0; i < this.options.size(); i++) {
                System.out.print(this.options.get(i) + " ");
            }
        }

        public int[] saveData() {
            int[] savedData = new int[2];
            savedData[0] = this.chennel;
            savedData[1] = this.volume;
            return savedData;
        }

        public void loadData(int[] lastData) {
            this.chennel = lastData[0];
            this.volume = lastData[1];
        }

        public static void main(String[] args) {

            // Human human = new Human();
            // human.walk();
            // human.speak();
            RemoteController remote = new RemoteController(3, 4, 3);

            // 외부 입력을 눌렀을떄
            remote.showOptions();
            // 오프 버튼 누른다
            int[] lastData = new int[2];
            // 전원 끄기 버튼을 누르면 몸 안에 아주 작은 메모리가 있어요 -> 우리가 흔히 데이터 베이스 -> 마지막 상태를 저장하는거에요
            lastData = remote.saveData();
            // DB = lastData
            // remote.loadData(DB);
            // RemoteController remote2 = new RemoteController(lastData[0],lastData[1],3);

            // for(int i =0 ; i < 5; i++){
            // remote.volumeUp();
            // remote.chennelUp();
            // }

            // for(int i =0; i< 5; i++){
            // for( int j = 0; j <i ; j++){
            // if (j == 0){
            // System.out.print("*");
            // }
            // if (j >0 &&j == i-1) {
            // System.out.print("*");
            // }
            // System.out.print(" ");
            // }
            // System.out.println();
            // }
            // 0 <= Math.random * 10 < 10
            // int(3.3) = 3

            int[][] array = new int[20][2]; // 21층 2호 
            // Q1. 가장 비싼 관리비를 낸 호수 와 가장 적게 관리비를 내 호수를 찾기
            // Q2. 관리비 중위값 그리고 중위값의 해당하는 호수 찾기
            // Q3. 관리비 평균값 그리고 평균값에 가장 가까운 호수 찾기
            for (int i = 0; i < array.length; i++){
            for (int j =0 ; j < array[1].length; j ++){
            array[i][j] = (int)(Math.random()*100);
            }
            }

            for (int i = 0; i <array.length; i++){
            for(int j = 0; j <array[0].length; j++){
            System.out.print(array[i][j]+" ");
            }
            System.out.println();
            }

            // for (int i = 0; i < array.length; i++) {
            // for (int j = 0; j < array.length - 1; j++) {
            // if (array[j] > array[j + 1]) {
            // int temp = 0;
            // temp = array[j];
            // array[j] = array[j + 1];
            // array[j + 1] = temp;
            // }
            // }
            // }

            // for (int i = 0; i < array.length; i++) {
            // System.out.println(array[i]);
            // }
        }

    }
}
