package gp.evolution;

import gp.algorithm.GPConstants;
import gp.structure.DerivationTree;

public class IndividualGPFactory {

  public static IndividualGP createIndividualGP(DerivationTree derivationTree,String grammarName){
    IndividualGP individualGP=null;
    switch(grammarName){
      case GPConstants.GRAMMAR_RUN_NAME:
        individualGP=new IndividualRunGP(derivationTree);
        break;
      case GPConstants.GRAMMAR_SCANNED_NAME:
        individualGP=new IndividualScannedGP(derivationTree);
        break;
      case GPConstants.GRAMMAR_HIT_BULLET_NAME:
        individualGP=new IndividualHitBulletGP(derivationTree);
        break;
    }
    return individualGP;
  }

}
