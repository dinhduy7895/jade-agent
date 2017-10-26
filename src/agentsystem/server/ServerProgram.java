package agentsystem.server;

import agentsystem.server.ui.ConfigureServerFrame;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class ServerProgram {

    private static jade.wrapper.AgentContainer mainContainer;

    public static jade.wrapper.AgentContainer getMainContainer() {
        return mainContainer;
    }

    public static void main(String[] args) throws StaleProxyException {
        ConfigureServerFrame configureServerFrame = new ConfigureServerFrame();
        configureServerFrame
                .setOnConfigurationChangedListener(new ConfigureServerFrame.OnConfigurationChangedListener() {

                    @Override
                    public void onConfigurationChanged(String savingImagesDirectory, int port) {

                        // Get a hold on JADE runtime
                        jade.core.Runtime rt = jade.core.Runtime.instance();

                        // Exit the JVM when there are no more containers around
                        rt.setCloseVM(true);
                        System.out.print("runtime created\n");

                        // Create a default profile
                        Profile profile = new ProfileImpl("localhost", port, null);
                        System.out.print("profile created\n");

                        System.out.println("Launching a whole in-process platform..." + profile);
                        mainContainer = rt.createMainContainer(profile);

                        AgentController rma;
                        try {
                            rma = mainContainer.createNewAgent("rma", "jade.tools.rma.rma", new Object[0]);
                            rma.start();
                            mainContainer.createNewAgent("ThanhBinh",
                                    "agentsystem.server.agent.ServerAgent",
                                    new Object[]{savingImagesDirectory}).start();
                            mainContainer.createNewAgent("disk-server",
                                    "agentsystem.server.agent.DiskAgent", new Object[]{}).start();
                            mainContainer.createNewAgent("chat-server",
                                    "agentsystem.server.agent.ChattingAgentServer", new Object[]{}).start();
                            mainContainer.createNewAgent("logout-server",
                                    "agentsystem.server.agent.LogoutAgent", new Object[]{}).start();
                            mainContainer.createNewAgent("shutdown-server",
                                    "agentsystem.server.agent.ShutdownPCAgent", new Object[]{})
                                    .start();
                            mainContainer.createNewAgent("send-message-server",
                                    "agentsystem.server.agent.SendMessageAgent",
                                    new Object[]{}).start();
                            mainContainer.createNewAgent("restart-server",
                                    "agentsystem.server.agent.RestartPCAgent", new Object[]{}).start();
                            mainContainer.createNewAgent("capture-server",
                                    "agentsystem.server.agent.CaptureAgent", new Object[]{}).start();
                        } catch (StaleProxyException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }
                });
        configureServerFrame.setVisible(true);
    }
}
