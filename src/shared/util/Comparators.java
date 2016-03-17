package shared.util;

// Java Imports
import java.util.Comparator;

// Other Imports
import shared.model.Organism;
import shared.model.SpeciesType;

/**
 * The Comparators class provides comparators to sort different lists.
 */
public class Comparators {

//    public static Comparator<Organism> GroupSizeComparatorASC = new Comparator<Organism>() {
//        @Override
//        public int compare(Organism o1, Organism o2) {
//            return Integer.valueOf(o1.getGroupSize()).compareTo(Integer.valueOf(o2.getGroupSize()));
//        }
//    };
//
//    public static Comparator<Organism> GroupSizeComparatorDESC = new Comparator<Organism>() {
//        @Override
//        public int compare(Organism o1, Organism o2) {
//            return Integer.valueOf(o2.getGroupSize()).compareTo(Integer.valueOf(o1.getGroupSize()));
//        }
//    };

    public static Comparator<SpeciesType> SpeciesNameComparator = new Comparator<SpeciesType>() {
        @Override
        public int compare(SpeciesType o1, SpeciesType o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    };
}
