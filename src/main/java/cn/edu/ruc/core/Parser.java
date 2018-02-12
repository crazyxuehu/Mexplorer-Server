package cn.edu.ruc.core;

import cn.edu.ruc.domain.Entity;
import cn.edu.ruc.domain.Feature;
import cn.edu.ruc.domain.Relation;

import java.util.ArrayList;
import java.util.List;


public class Parser {
    //important: pay attention to the strings such ""

    public static Entity encodeEntity(String entityString) {
        return DataUtil.getEntity2Id(entityString) == -1 ? null : new Entity(DataUtil.getEntity2Id(entityString), entityString);
    }

    public static Entity encodeEntity(String entityString, double score) {
        return DataUtil.getEntity2Id(entityString) == -1 ? null : new Entity(DataUtil.getEntity2Id(entityString), entityString, score);
    }

    public static List<Entity> encodeEntityList(List<String> entityStringList) {
        List<Entity> entityList = new ArrayList<>();

        for(String entityString : entityStringList) {
            String[] tokens = entityString.split("_");
            Entity entity = Parser.encodeEntity(tokens[0], Double.parseDouble(tokens[1]));

            if(entity != null) {
                entityList.add(entity);
            }
        }

        return entityList;
    }

    public static Relation encodeRelation(String relationString, int direction) {
        return DataUtil.getRelation2Id(relationString) == -1 ? null : new Relation(DataUtil.getRelation2Id(relationString), direction, relationString);
    }

    public static Relation encodeRelation(String relationString, int direction, double score) {
        return DataUtil.getRelation2Id(relationString) == -1 ? null : new Relation(DataUtil.getRelation2Id(relationString), direction, relationString, score);
    }

    public static Feature encodeFeature(String featureString, double score) {
        String[] tokens = featureString.split("##");
        String entityString = tokens[0];
        String relationString = tokens[1];
        int relationDirection = (tokens.length == 3) ? Integer.parseInt(tokens[2]) : -1;

        Entity entity = encodeEntity(entityString, score);
        Relation relation = encodeRelation(relationString, relationDirection, score);

        return entity != null && relation != null ? new Feature(entity, relation, score) : null;
    }

    public static List<Feature> encodeFeatureList(List<String> featureStringList) {
        List<Feature> featureList = new ArrayList<>();

        for(String featureString : featureStringList) {
            String[] tokens = featureString.split("_");
            Feature feature = Parser.encodeFeature(tokens[0], Double.parseDouble(tokens[1]));

            if(feature != null) {
                featureList.add(feature);
            }
        }

        return featureList;
    }

    //decode
    public static void decodeEntity(Entity entity){
        if(entity != null) {
            entity.setName(DataUtil.getId2Entity(entity.getId()));
        }
    }

    public static void decodeRelation(Relation relation){
        if(relation != null) {
            relation.setName(DataUtil.getId2Relation(relation.getId()));
        }
    }

    public static void decodeFeature(Feature feature){
        if(feature != null) {
            decodeEntity(feature.getEntity());
            decodeRelation(feature.getRelation());
        }
    }


    public static void richEntity(Entity entity){
        if(entity != null) {
            entity.setDescription(DataUtil.getDescription(entity.getId()));
            entity.setImage(DataUtil.getImage(entity.getId()));
        }
    }
}