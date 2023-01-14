package leibniz;

import jdk.incubator.vector.DoubleVector;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorSpecies;
import jdk.incubator.vector.VectorMask;
import jdk.incubator.vector.VectorShuffle;


public class JL {
  public static double PiOrig(int init_rounds) {
    final int rounds = init_rounds+2;
    double pi = 1.0;
    double x = 1;

    for (int i = 2; i < rounds; i++) {
      x *= -1;
      pi += (x / (2 * i - 1));
    }
    return pi * 4;
  }


  public static double PiVec(int rounds) {
    final VectorSpecies<Double> species = DoubleVector.SPECIES_PREFERRED;
    final int vecLen = species.length();
    final int nVecGroups = rounds / vecLen;

    final DoubleVector divConstants = DoubleVector.fromArray(species, new double[]{1.0, 3.0, 5.0, 7.0, 9.0, 11.0, 13.0, 15.0},0);
    final DoubleVector aones = DoubleVector.fromArray(species, new double[]{1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0},0);
    final DoubleVector ones = DoubleVector.broadcast(species, 1.0);
    DoubleVector sumTarget = DoubleVector.broadcast(species, 0.0);
    final int dvecLen = vecLen*2;


    for(int vidx = 0; vidx < nVecGroups; ++vidx) {
      sumTarget = sumTarget.add(ones.div(divConstants.add(dvecLen*vidx).mul(aones)));
    }

    double pi = sumTarget.reduceLanes(VectorOperators.ADD);
    for(int idx = nVecGroups * vecLen; idx < rounds; ++idx) {
      final double x = 1.0 - (2.0 * (idx & 0x1));
      pi += (x / (1 + (2*idx)));
    }
    
    return pi * 4;
  }

}
