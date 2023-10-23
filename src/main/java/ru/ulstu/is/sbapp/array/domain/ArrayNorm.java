package ru.ulstu.is.sbapp.array.domain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Arrays;
import java.util.Random;


public class ArrayNorm implements Array {
    private final Logger log = LoggerFactory.getLogger(ArrayNorm.class);

    @Override
    public String runArray(int row, int column) {
        int [][] array=new int[row][column];

		Random rand =new Random();
		for(int i=0; i<array.length; i++){
			for(int j=0; j<column; j++){
				array[i][j]=rand.nextInt(100-10+1);
			}
		}
		String res ="";
		for(int i=0; i<array.length; i++){
			res+=Arrays.toString(array[i]);
		}
		return res;
    }

    public void init() {
        log.info("ArrayNorm.init()");
    }

    public void destroy() {
        log.info("ArrayNorm.destroy()");
    }
    
}
