package dev.frydae.commoncore.utils.java;

import java.util.ArrayList;

public class DistinctCaselessList extends ArrayList<String> {
    @Override
    public boolean contains(Object o) {
        String string = (String) o;

        return super.contains(string.toLowerCase());
    }

    @Override
    public boolean add(String s) {
        return !contains(s) && super.add(s.toLowerCase());
    }

    @Override
    public DistinctCaselessList clone() {
        return (DistinctCaselessList) super.clone();
    }
}
