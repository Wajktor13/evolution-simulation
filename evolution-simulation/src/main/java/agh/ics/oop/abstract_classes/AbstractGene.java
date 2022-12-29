package agh.ics.oop.abstract_classes;

import agh.ics.oop.classes.Animal;
import agh.ics.oop.interfaces.IGene;

import java.util.Random;

public abstract class AbstractGene implements IGene {
    public Random r = new Random();
    protected int geneLength;
    protected String geneString = "";
    protected int nextUseId = 0;
    protected int minMutationCount = 0;
    protected int maxMutationCount = 0;

    public AbstractGene(int geneLength){
        this.geneLength = geneLength;
        generateGene();
    }

    public AbstractGene(Animal alfa, Animal beta){
        this.geneLength = alfa.getAnimalGene().getGeneLength();
        this.geneString = getChildGene(alfa, beta);
    }

    @Override
    public void generateGene(){
        for (int i = 0; i < geneLength; i++){
            geneString += r.nextInt(0, 8);
        }
    }

    @Override
    public int getGeneLength(){
        return this.geneLength;
    }

    public int getNextOrientation(){
        int toReturn = geneString.charAt(nextUseId) - '0';
        updateNextUseId();
        return toReturn;
    }

    private String getChildGene(Animal alfa, Animal beta) {
        int e1 = alfa.getEnergy();
        int e2 = beta.getEnergy();
        String g1 = alfa.getAnimalGene().toString();
        String g2 = beta.getAnimalGene().toString();

        int totalEnergy = e1 + e2;

        boolean right = r.nextBoolean();
        int subId = (int) ((e1 * 100 / totalEnergy * geneLength) / 100);

        String toReturn = "";
         //kinda working not really sure
        if (right){
            if (e1 > e2) {
                toReturn += new StringBuilder(g2.substring(0, subId)).reverse().toString();
                toReturn += new StringBuilder(g1.substring(subId, geneLength)).reverse().toString();
            } else {
                toReturn += new StringBuilder(g2.substring(geneLength - subId, geneLength)).reverse().toString();
                toReturn += new StringBuilder(g1.substring(0, geneLength - subId)).reverse().toString();
            }
        }
        else {
            if (e1 > e2){
                toReturn += g1.substring(0, subId);
                toReturn += g2.substring(subId, geneLength);
            } else {
                toReturn += g2.substring(0, geneLength - subId);
                toReturn += g1.substring(geneLength - subId, geneLength);
            }
        }
        return toReturn;
    }
    @Override
    public void updateNextUseId(){
        nextUseId = (nextUseId + 1)%geneLength;
    }

    @Override
    public String toString(){
        return geneString;
    }

    @Override
    public void mutateGene(){

    }
    @Override
    public void updateMutationCount(int minMutationCount, int maxMutationCount){
        this.minMutationCount = minMutationCount;
        this.maxMutationCount = maxMutationCount;
    }

    @Override
    public int getMinMutationCount(){
        return this.minMutationCount;
    }

    @Override
    public int getMaxMutationCount(){
        return this.maxMutationCount;
    }
}
