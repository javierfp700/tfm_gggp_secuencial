package gp.results;

import ec.evolution.Individual;

public final class GPResults {
  private static GPResults instance;
  public Individual bestRunIndividual;
  public Individual bestScannedIndividual;
  public Individual bestHitBulletIndividual;

  private GPResults(){}

  public static GPResults getInstance(){
    if(instance==null){
      instance = new GPResults();
    }
    return instance;
  }

  public void setBestRunIndividual(Individual bestRunIndividual) {
    this.bestRunIndividual = bestRunIndividual;
  }

  public void setBestScannedIndividual(Individual bestScannedIndividual) {
    this.bestScannedIndividual = bestScannedIndividual;
  }

  public void setBestHitBulletIndividual(Individual bestHitBulletIndividual) {
    this.bestHitBulletIndividual = bestHitBulletIndividual;
  }

  public Individual getBestRunIndividual(){
    return bestRunIndividual;
  }

  public Individual getBestScannedIndividual(){
    return bestScannedIndividual;
  }

}
