package agentsystem.client;

import agentsystem.client.ui.ConfigureClientFrame;
import agentsystem.client.utils.HostInformations;

import jade.core.ProfileImpl;
import jade.wrapper.StaleProxyException;

public class ClientProgram {

    private static jade.wrapper.AgentContainer agentContainer;

    public static jade.wrapper.AgentContainer getAgentContainer() {
        return agentContainer;
    }

    public static void main(String[] args) throws StaleProxyException {
        ConfigureClientFrame configureClientFrame = new ConfigureClientFrame();
        configureClientFrame
                .setOnConfigurationChangedListener(new ConfigureClientFrame.OnConfigurationChangedListener() {

                    @Override
                    public void onConfigurationChanged(String address, int port) {
                        // Get a hold on JADE runtime
                        jade.core.Runtime rt = jade.core.Runtime.instance();

                        // Exit the JVM when there are no more containers around
                        rt.setCloseVM(true);
                        System.out.print("runtime created\n");

                        // now set the default Profile to start a container
                        ProfileImpl pContainer = new ProfileImpl(address, port,
                                null);
                        System.out.println("Launching the agent container ..."
                                + pContainer);

                        agentContainer = rt.createAgentContainer(pContainer);
                        System.out
                                .println("Launching the agent container after ..."
                                        + pContainer);

                        System.out.println("containers created");
                    }
                });
        configureClientFrame.setVisible(true);
    }

}
