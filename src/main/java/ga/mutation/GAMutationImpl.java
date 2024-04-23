package ga.mutation;

import java.util.Random;
import ec.evolution.Individual;
import ec.mutation.Mutation;
import ga.algorithm.GAConstants;
import ga.evolution.IndividualGA;

public class GAMutationImpl implements Mutation {

  @Override
  public void mutate(Individual individual) {
    IndividualGA individualGA=(IndividualGA) individual;
    double[] values=individualGA.getValues();
    Random random=new Random();
    int pos=random.nextInt(values.length);
    double randomDouble=random.nextDouble();
    values[pos]=values[pos]+randomDouble;
  }

  @Override
  public Float getMutationProbability() {
    return GAConstants.GA_MUTATION_PROBABILITY;
  }
}
