package agentsystem.client.agent;

import agentsystem.client.utils.SystemManager;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ShutdownPCAgent extends MobileAgent {
	private static final long serialVersionUID = 1L;

	@Override
	protected void setup() {
		System.out.println(String.format("Hello! ShutdownPCAgent %s is ready.", getAID().getName()));
		addBehaviour(new ShutdownPC());
	}

	private class ShutdownPC extends CyclicBehaviour {
		private static final long serialVersionUID = 1L;

		@Override
		public void action() {
			ACLMessage msg = myAgent.receive();
			if (msg != null) {
				if (isNormalMessage(msg)) {
					SystemManager.shutdown();
				}
			} else {
				block();
			}
		}

	}
}
