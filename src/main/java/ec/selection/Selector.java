package ec.selection;

import java.util.List;
import ec.evolution.Individual;
import ec.evolution.Population;

public interface Selector {

  List<Individual> select(Population population);

}
