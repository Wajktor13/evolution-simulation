package agh.ics.oop;

import java.util.Random;

public class Gene {
    Random r = new Random();
    private int geneLength;
    private String geneString = "";

    private int nextUseId = 0;
    public Gene(int length){
        this.geneLength = length;
        generateGene();
    }

    private void generateGene(){
        for (int i = 0; i < geneLength; i++){
            geneString += r.nextInt(0, 8);
        }
    }
    public int getNextOrientation(){
        int toReturn = geneString.charAt(nextUseId) - '0';
        updateNextUseId();
        return toReturn;
    }

    private void updateNextUseId(){
        nextUseId = (nextUseId + 1)%geneLength;
    }

    public String getGene(){
        return geneString;
    }
}
