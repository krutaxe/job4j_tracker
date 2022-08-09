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
        Map<String, Integer> map = new LinkedHashMap<>();
        List<Label> labelList = new ArrayList<>();
        loadMap(map, pupils);
        for (Map.Entry<String, Integer> value : map.entrySet()) {
            labelList.add(new Label(value.getKey(), (double) value.getValue() / pupils.size()));
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
        Map<String, Integer> map = new LinkedHashMap<>();
        List<Label> labelList = new ArrayList<>();
        loadMap(map, pupils);
        for (Map.Entry<String, Integer> value : map.entrySet()) {
            labelList.add(new Label(value.getKey(), value.getValue()));
        }
        labelList.sort(Label::compareTo);
        return labelList.get(labelList.size() - 1);
    }

    private static void loadMap(Map<String, Integer> map, List<Pupil> pupils) {
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                map.merge(subject.name(), subject.score(), Integer::sum);
            }
        }
    }
}
