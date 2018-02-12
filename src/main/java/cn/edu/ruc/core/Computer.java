package cn.edu.ruc.core;

public class Computer {
    //compute score in the embedding space
    public static double getEmbeddingScore(int entityId1, int entityId2) {
        double[] entityVector1 = DataUtil.getEntity2Vector(entityId1);
        double[] entityVector2 = DataUtil.getEntity2Vector(entityId2);

        double sum=0;
        for(int i = 0 ; i < DataUtil.Dimension; i ++) {
            sum += Math.pow(entityVector1[i] - entityVector2[i], 2);
        }

        return (2 - Math.sqrt(sum))/ 2;
    }

    public static double getEmbeddingsScore(int entityId1, int relationId, int direction, int entityId2){
        double[] entityVector1 = DataUtil.getEntity2Vector(entityId1);
        double[] relationVector = DataUtil.getRelation2Vector(relationId);
        double[] entityVector2 = DataUtil.getEntity2Vector(entityId2);

        double sum=0;
        for(int i = 0 ; i < DataUtil.Dimension; i ++) {
            sum += Math.pow(entityVector1[i] - (direction * relationVector[i] + entityVector2[i]), 2);
        }

        return (3 - Math.sqrt(sum))/ 3;
    }
}