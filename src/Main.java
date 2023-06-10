import MetaHeurstique.BSOAgent;
import MetaHeurstique.WDPProblem;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        WDPProblem problem = new WDPProblem("Benchmarks/wdp_lau_goh/lau_goh/group05_b1500_g1500/in601.txt");
        //   WDPProblem problem = new WDPProblem(100);    204
        jade.core.Runtime runtime = jade.core.Runtime.instance();
        jade.wrapper.AgentContainer container = runtime.createMainContainer(new ProfileImpl());
        try {
            AgentController agentController = container.createNewAgent("BSO", BSOAgent.class.getName(), new Object[]{problem});
            agentController.start();
        } catch (Exception e) {
            e.printStackTrace();
        }


   //     Bee bee = Bee.BeeInit(problem);

  //      System.out.println(bee.dance);

    }

}