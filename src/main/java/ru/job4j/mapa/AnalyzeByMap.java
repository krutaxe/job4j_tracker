package ru.job4j.mapa;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        int count = 0;
        double rsl = 0;
        for (int i = 0; i < pupils.size(); i++) {
            for (int j = 0; j < pupils.get(i).subjects().size(); j++) {
                rsl += pupils.get(i).subjects().get(j).score();
                count++;
            }
        }
        rsl = rsl / count;
        return rsl;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> list = new ArrayList<>();
        int count = 0;
        double score = 0;
        for (int i = 0; i < pupils.size(); i++) {
            for (int j = 0; j < pupils.get(i).subjects().size(); j++) {
                score += pupils.get(i).subjects().get(j).score();
                count++;
                if (count == pupils.get(i).subjects().size()) {
                    list.add(new Label(pupils.get(i).name(), score / count));
                    count = 0;
                    score = 0;
                }
            }
        }
        return list;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        double sumScore = 0;
        Map<String, Double> map = new LinkedHashMap<>();
        List<Label> labelList = new ArrayList<>();
        for (int i = 0; i < pupils.size(); i++) {
            for (int j = 0; j < pupils.get(i).subjects().size(); j++) {
                sumScore += pupils.get(j).subjects().get(i).score();
            }
            map.put(pupils.get(i).subjects().get(i).name(), sumScore);
            sumScore = 0;
        }
        for (Map.Entry<String, Double> value : map.entrySet()) {
            labelList.add(new Label(value.getKey(), value.getValue() / pupils.size()));
        }
        return labelList;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> list = new ArrayList<>();
        int count = 0;
        double score = 0;
        for (int i = 0; i < pupils.size(); i++) {
            for (int j = 0; j < pupils.get(i).subjects().size(); j++) {
                score += pupils.get(i).subjects().get(j).score();
            }
            list.add(new Label(pupils.get(i).name(), score));
            score = 0;
        }
        list.sort(Label::compareTo);
        return list.get(list.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        double sumScore = 0;
        Map<String, Double> map = new LinkedHashMap<>();
        List<Label> labelList = new ArrayList<>();
        for (int i = 0; i < pupils.size(); i++) {
            for (int j = 0; j < pupils.get(i).subjects().size(); j++) {
                sumScore += pupils.get(j).subjects().get(i).score();
            }
            map.put(pupils.get(i).subjects().get(i).name(), sumScore);
            sumScore = 0;
        }
        for (Map.Entry<String, Double> value : map.entrySet()) {
            labelList.add(new Label(value.getKey(), value.getValue()));
        }
        labelList.sort(Label::compareTo);
        return labelList.get(labelList.size() - 1);
    }
}
