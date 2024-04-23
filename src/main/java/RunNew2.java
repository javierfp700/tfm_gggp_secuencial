import java.util.ArrayList;
import java.util.List;

public class RunNew2 {

  public static void main(String[] args) {
    String cad="400#####analytics.noValidEngineName#####Engine name prueba.gt is not valid because it contains not allowed characters";
    String[] res=cad.split("#####");
    for(String r:res){
      System.out.println(r);
    }
  }

}
