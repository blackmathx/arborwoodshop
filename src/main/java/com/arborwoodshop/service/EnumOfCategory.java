package com.arborwoodshop.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum EnumOfCategory {

    MEASURING_AND_MARKING_TOOLS("Measuring and Marking Tools"),
    CUTTING_TOOLS("Cutting Tools"),
    SHAPING_AND_CARVING_TOOLS("Shaping and Carving Tools"),
    JOINERY_TOOLS("Joinery Tools"),
    CLAMPING_AND_HOLDING_TOOLS("Clamping and Holding Tools"),
    DRILLING_AND_BORING_TOOLS("Drilling and Boring Tools"),
    SANDING_AND_SMOOTHING_TOOLS("Sanding and Smoothing Tools"),
    POWER_TOOLS("Power Tools"),
    FINISHING_TOOLS("Finishing Tools"),
    WORKHOLDING_ACCESSORIES("Workholding Accessories"),
    SHARPENING_TOOLS("Sharpening Tools"),
    SAFETY_EQUIPMENT("Safety Equipment");

    private final String displayName;

    private static final List<String> listOfCategoriesForDisplay;
    private static final List<String> listOfCategoriesForLinks;

    static {
        listOfCategoriesForDisplay = Stream.of(EnumOfCategory.values()).map(EnumOfCategory::getDisplayName).collect(Collectors.toList());
        listOfCategoriesForLinks = Stream.of(EnumOfCategory.values()).map(e -> e.name().toLowerCase()).collect(Collectors.toList());
    }

    private EnumOfCategory(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }

    public static List<String> getListOfCategoriesForLinks(){
        return listOfCategoriesForLinks;
    }

    public static List<String> getListOfCategoriesForDisplay(){
        return listOfCategoriesForDisplay;
    }
}
