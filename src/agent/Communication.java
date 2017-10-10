/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

import jade.core.AID;
import jade.core.Agent;
import jade.core.PlatformID;
import jade.core.behaviours.TickerBehaviour;
@SuppressWarnings("serial")
public class Communication extends Agent {

    @Override
    protected void setup() {
        super.setup();
        addBehaviour(new MyTickerBehaviour(this, 1000));
        System.out.println("Hello World. I am an agent!");
        System.out.println("My LocalName: " + getAID().getLocalName());
        System.out.println("My Name: " + getAID().getName());
        System.out.println("My Address: " + getAID().getAddressesArray()[0]);
    }

    private class MyTickerBehaviour extends TickerBehaviour {

        Agent agent;
        // long interval;      
        int counter;

        public MyTickerBehaviour(Agent agent, long interval) {
            super(agent, interval);
            this.agent = agent;
            // this.interval = interval;       
        }

        @Override
        protected void onTick() {
            if (counter == 3) {
                // move out    
                AID remoteAMS = new AID("ams at PC-de-zitouni", AID.ISGUID);
                remoteAMS.addAddresses("http://192.168.1.15:7778/acc");
                PlatformID destination = new PlatformID(remoteAMS);
                agent.doMove(destination);
            }
            if (counter == 10) {
                // move back     
                AID remoteAMS = new AID("ams at MED-PC", AID.ISGUID);
                remoteAMS.addAddresses("http://192.168.1.6:7778/acc");
                PlatformID destination = new PlatformID(remoteAMS);
                agent.doMove(destination);
            }
            if (counter < 15) {
                System.out.println(counter++);
            } else {
                agent.doDelete();
            }
        }
    }
}
