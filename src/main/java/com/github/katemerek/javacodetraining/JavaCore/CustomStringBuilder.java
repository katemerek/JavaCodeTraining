package com.github.katemerek.javacodetraining.JavaCore;

import lombok.Data;

@Data
public class CustomStringBuilder {
    private StringBuilder str;

    public SnapshotStringBuilder save(StringBuilder str) {
        System.out.println("Ура, меня сохранили в неизменяемый объект! " + str);
        return new SnapshotStringBuilder(str);
    }

    public void undo(SnapshotStringBuilder ssb) {
        this.str = ssb.getSavedStr();
        System.out.println("Меня откатили до исходного состояния: " + str.toString());
    }

    public static void main(String[] args) {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        StringBuilder example = new StringBuilder();
        example.append("Hello");
        System.out.println(example);
        customStringBuilder.undo(customStringBuilder.save(example));
    }
}
