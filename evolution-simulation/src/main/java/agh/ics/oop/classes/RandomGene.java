package agh.ics.oop.classes;

import agh.ics.oop.abstract_classes.AbstractGene;
import agh.ics.oop.classes.Animal;

public class RandomGene extends AbstractGene {
    public RandomGene(int length){
        super(length);
    }

    public RandomGene(Animal alfa, Animal beta){
        super(alfa, beta);
    }
    @Override
    public void mutateGene(){
        int mutationCount = r.nextInt(minMutationCount, maxMutationCount+1);
        char[] mutate = this.geneString.toCharArray();

        while (mutationCount > 0){
            int toChange = r.nextInt(this.geneLength);
            mutate[toChange] = (char) (r.nextInt(0, 8)  + '0');
            mutationCount-=1;
        }

        this.geneString = mutate.toString();

    }


}
