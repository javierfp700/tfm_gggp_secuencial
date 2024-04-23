package ec.replacement;

import java.util.List;
import ec.evolution.Individual;
import ec.evolution.Population;

public interface Replacement {

  void replace(Population population, List<Individual> mutatedIndividuals);

}
