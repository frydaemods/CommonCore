package dev.frydae.beguild.utils.java;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class DistinctCaselessList extends ArrayList<String> {
    @Override
    public boolean contains(Object o) {
        String string = (String) o;

        return super.contains(string.toLowerCase());
    }

    public boolean containsAll(String... strings) {
        return Arrays.stream(strings).allMatch(this::contains);
    }

    @Override
    public boolean add(String s) {
        return !contains(s) && super.add(s.toLowerCase());
    }

    @CanIgnoreReturnValue
    public boolean addAll(String... strings) {
        return addAll(List.of(strings));
    }

    @Override
    public boolean addAll(Collection<? extends String> strings) {
        strings.forEach(this::add);

        return true;
    }

    @Override
    public DistinctCaselessList clone() {
        return (DistinctCaselessList) super.clone();
    }

    /**
     * Returns a list of all the strings in this list that are not in the provided list
     *
     * @param list the list to compare against
     * @return a list of strings
     */
    public DistinctCaselessList getMissing(DistinctCaselessList list) {
        DistinctCaselessList clone = clone();
        clone.removeAll(list);
        return clone;
    }
}
