package agh.ics.oop.interfaces;

public interface IGene {
    void generateGene();

    int getNextOrientation();
    void updateNextUseId();
    void mutateGene();
    void updateMutationCount(int minMutationCount, int maxMutationCount);

    int getGeneLength();

    int getMinMutationCount();

    int getMaxMutationCount();
}
