package ru.job4j.mapa;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        int count = 0;
        double rsl = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                rsl += subject.score();
                count++;
            }
        }
        return rsl / count;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> list = new ArrayList<>();
        int count = 0;
        double score = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                score += subject.score();
                count++;
            }
            list.add(new Label(pupil.name(), score / count));
            count = 0;
            score = 0;
        }
        return list;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        Map<String, Double> map = new LinkedHashMap<>();
        List<Label> labelList = new ArrayList<>();
        loadMap(map, pupils);
        for (Map.Entry<String, Double> value : map.entrySet()) {
            labelList.add(new Label(value.getKey(), value.getValue() / pupils.size()));
        }
        return labelList;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> list = new ArrayList<>();
        double score = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                score += subject.score();
            }
            list.add(new Label(pupil.name(), score));
            score = 0;
        }
        list.sort(Label::compareTo);
        return list.get(list.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        Map<String, Double> map = new LinkedHashMap<>();
        List<Label> labelList = new ArrayList<>();
        loadMap(map, pupils);
        System.out.println(map);
        for (Map.Entry<String, Double> value : map.entrySet()) {
            labelList.add(new Label(value.getKey(), value.getValue()));
        }
        labelList.sort(Label::compareTo);
        return labelList.get(labelList.size() - 1);
    }

    private static void loadMap(Map<String, Double> map, List<Pupil> pupils) {
        double sum = 0;
        for (int i = 0; i < pupils.size(); i++) {
            for (int j = 0; j < pupils.get(i).subjects().size(); j++) {
                sum += pupils.get(j).subjects().get(i).score();
            }
            map.put(pupils.get(i).subjects().get(i).name(), sum);
            sum = 0;
        }
    }
}
