package com.examly.springapp;

import java.util.*;

public class CricketerAgeComparator implements Comparator<Cricketer> {
    @Override
    public int compare(Cricketer cricketer1, Cricketer cricketer2) {
        return Integer.compare(cricketer1.getAge(), cricketer2.getAge());
    }
}
