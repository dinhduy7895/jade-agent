package agent;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.SwingUtilities;

import misc.Logger;

import util.F.Tuple;
import util.Utils;

public class AgentCamera extends Agent {

    private Agent agent = this;

    @Override
    protected void setup() {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Logger.info(agent, "Creating Agent Camera...");
                
            }
        });

    }

   

    public class ShutdownAgent extends OneShotBehaviour {

        @Override
        public void action() {
            agent.doDelete();
        }

    }
}
