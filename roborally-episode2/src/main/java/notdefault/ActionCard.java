package notdefault;

public class ActionCard {
    private ActionType actionType;
    private boolean movementCard = false;
    private int magnitude = 0;

    public ActionCard() {
	init();
    }
    
    public void init() {
	double val = Math.random();
	
	if(val < 0.30) {
	    actionType = ActionType.Move_1;
	    setMagnitude(1);
	} else if(val < 0.50) {
	    actionType = ActionType.Move_2;
	    setMagnitude(2);
	} else if(val < 0.55) {
	    actionType = ActionType.Move_3;
	    setMagnitude(3);
	} else if(val < 0.75) {
	    actionType = ActionType.TurnLeft;
	} else if(val < 0.95) {
	    actionType = ActionType.TurnRight;
	} else {
	    actionType = ActionType.UTurn;
	}
	actionType.setActionType(actionType);
	setMovement();
    }
    
    public void setMovement() {
	String x = actionType.getActionType();
	
	if(x.contains("Move")) {
	    movementCard = true;
	}
    }

    
    public boolean isMovement() {
	return movementCard;
    }

    public int getMagnitude() {
	return magnitude;
    }

    public void setMagnitude(int magnitude) {
	this.magnitude = magnitude;
    }
    
    
}

