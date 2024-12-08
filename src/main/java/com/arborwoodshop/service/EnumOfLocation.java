package com.arborwoodshop.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Enums to switch between stored name and display name for the locations. Includes enums for State abbreviations.
 *
 *
 * -------- DEV NOTES --------------------------------------
 * Usage
 *      String s = "Bloomington";
 *		EnumOfLocation e = EnumOfLocation.stringToEnum(s);
 *		System.out.println(e);
 *		System.out.println(e.getDisplayName());
 *
 * Duplicate cities in the CL list of cities:
 *      Bloomington (Indiana, Illinois),
 *      Jackson (Mississippi, Tennessee),
 *      Lafayette (California, Louisiana),
 *      Richmond (Indiana, Virginia),
 *      Columbus (Georgia, Ohio)
 *
 */
public enum EnumOfLocation {

    // INDIANA
    IN("Indiana"), // State listed first
    BLOOMINGTON("Bloomington"),
    EVANSVILLE("Evansville"),
    FORT_WAYNE("Fort Wayne"),
    INDIANAPOLIS("Indianapolis"),
    KOKOMO("Kokomo"),
    LAFAYETTE_WEST_LAFAYETTE("Lafayette/West Lafayette"),
    MUNCIE_ANDERSON("Muncie/Anderson"),
    RICHMOND("Richmond"),
    SOUTH_BEND_MICHIANA("South Bend/Michiana"),
    TERRE_HAUTE("Terre Haute"),

    // MICHIGAN
    MI("Michigan"),
    ANN_ARBOR("Ann Arbor"),
    BATTLE_CREEK("Battle Creek"),
    CENTRAL_MICHIGAN("Central Michigan"),
    DETROIT_METRO("Detroit Metro"),
    FLINT("Flint"),
    GRAND_RAPIDS("Grand Rapids"),
    HOLLAND("Holland"),
    JACKSON("Jackson"),
    KALAMAZOO("Kalamazoo"),
    LANSING("Lansing"),
    MONROE("Monroe"),
    MUSKEGON("Muskegon"),
    NORTHERN_MICHIGAN("Northern Michigan"),
    PORT_HURON("Port Huron");

    private final String displayName;

    private EnumOfLocation(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }

}
