package com.github.katemerek.javacodetraining.JavaCore;

public class SnapshotStringBuilder {
    private final StringBuilder savedStr;

    public SnapshotStringBuilder(StringBuilder str) {
        this.savedStr = str;
    }

    public StringBuilder getSavedStr() {
        System.out.println("О нет, меня хотят извлечь! На моем месте должен был быть объект из истории изменений");
        return savedStr;
    }


}
