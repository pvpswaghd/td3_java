package helpers;

import java.util.ArrayList;

public class Utils {
    public static int[][] ArraylistTo2Dint(ArrayList<Integer> list, int xSize, int ySize) {
        int[][] newArr = new int[ySize][xSize];
        for(int j = 0; j < newArr.length; j++) {
            for (int i = 0; i < newArr[j].length;i++) {
                int index = j*ySize + i;
                newArr[j][i] = list.get(index);
            }
        }
        return newArr;
    }

    public static int[] TwoDto1DintArr(int[][] twoArr) {
        int[] oneArr = new int[twoArr.length * twoArr[0].length];
        for(int j = 0; j < twoArr.length; j++) {
            for (int i = 0; i < twoArr[j].length;i++) {
                int index = j * twoArr.length + i;
                oneArr[index] = twoArr[j][i];
            }
        }
        return oneArr;
    }
}
