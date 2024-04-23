import ga.evolution.IndividualGA;

public class Run7 {

  public static void main(String[] args) {
    double[] values=new double[]{1.0,2.0};
    IndividualGA individualGA=new IndividualGA(values,null);
    values=individualGA.getValues();
    values[0]=4.0;
    System.out.println(individualGA.getValues()[0]);
  }

}
