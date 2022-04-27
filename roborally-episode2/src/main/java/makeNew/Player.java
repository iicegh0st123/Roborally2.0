package makeNew;

import java.util.ArrayList;

public class Player {

    private String name;

    private Robot robot;

    private boolean inGame;
    private boolean acidStop = false;

    private ArrayList<ActionCard> actionCards;
    private ArrayList<ActionCard> hand;

    private Board board;

    public Player(String s) {
	setName(s);
	robot = new Robot();
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Robot getRobot() {
	return robot;
    }

    public void makeActionCards() {
	actionCards = new ArrayList<ActionCard>();

	for (int i = 0; i < 9; i++) {
	    actionCards.add(new ActionCard());
	}
    }

    public void execHand(int i) {
	if(!acidStop) {
	    execCard(hand.get(i)); 
	}
    }

    public void execCard(ActionCard actionCard) {
	if(validMove(actionCard)) {
	    move(actionCard);
	    
	    obstacleInteract();

	} else {
	    rotate(actionCard);
	}
    }

    public boolean validMove(ActionCard actionCard) {
	boolean valid = false;

	if(actionCard.isMovement()) {
	    if(robot.getDir() == 1) {
		if(robot.getCoor()[0]+actionCard.getMagnitude() < board.getCols()-1) {
		    valid = true;
		}
	    } else if(robot.getDir() == 2) {
		if(robot.getCoor()[1]+actionCard.getMagnitude() < board.getRows()-1) {
		    valid = true;
		}
	    } else if(robot.getDir() == 3) {
		if(0 <= robot.getCoor()[0]-actionCard.getMagnitude()) {
		    valid = true;
		}
	    } else if(robot.getDir() == 0) {
		if(0 <= robot.getCoor()[1]-actionCard.getMagnitude()) {
		    valid = true;
		}
	    }
	} else {
	    valid = true;
	}

	return valid;
    }

    public void move(ActionCard actionCard) {
	if(robot.getDir() == 1) {
	    robot.setCoor(new int[] {robot.getCoor()[0]+actionCard.getMagnitude(),robot.getCoor()[1]});
	} else if(robot.getDir() == 2) {
	    robot.setCoor(new int[] {robot.getCoor()[0],robot.getCoor()[1]+actionCard.getMagnitude()});
	} else if(robot.getDir() == 3) {
	    robot.setCoor(new int[] {robot.getCoor()[0]-actionCard.getMagnitude(),robot.getCoor()[1]});
	} else if(robot.getDir() == 0) {
	    robot.setCoor(new int[] {robot.getCoor()[0],robot.getCoor()[1]-actionCard.getMagnitude()});
	}
    }

    public void rotate(ActionCard actionCard) {
	if(actionCard.getCardType().equals(CardType.TurnL)) {
	    robot.setDir((robot.getDir()-1)%4);
	} else if(actionCard.getCardType().equals(CardType.TurnR)) {
	    robot.setDir((robot.getDir()+1)%4);
	} else if(actionCard.getCardType().equals(CardType.UTurn)) {
	    robot.setDir((robot.getDir()+2)%4);
	}
    }

    private void obstacleInteract() {
	Obstacle obstacle;

	obstacle = board.getTile(robot.getCoor()).getObstacle();

	robot.takeDamage(obstacle.getDamage());

	if(obstacle.getType().equals("Barrel")) {
	    rotate(new ActionCard(CardType.UTurn));
	    move(new ActionCard(CardType.Move1));
	    rotate(new ActionCard(CardType.UTurn));
	}

	if(obstacle.getType().equals("GearR")) {
	    rotate(new ActionCard(CardType.TurnR));
	}

	if(obstacle.getType().equals("GearL")) {
	    rotate(new ActionCard(CardType.TurnL));
	}

	if(obstacle.getType().equals("Conveyor1")) {
	    move(new ActionCard(CardType.Move1));
	}

	if(obstacle.getType().equals("Conveyor2")) {
	    move(new ActionCard(CardType.Move2));
	}

	if(obstacle.getType().equals("Conveyor3")) {
	    move(new ActionCard(CardType.Move3));
	}

	if(obstacle.getType().equals("Acid")) {
	    acidStop = true;
	}
    }

    public boolean inGame() {
	return this.inGame;
    }

    public void setGameOver(boolean b) {
	inGame = b;
    }

    public void setBoard(Board board) {
	this.board = board;
    }
    
    public void noAcid() {
	acidStop = false;
    }

    public ArrayList<ActionCard> getActionCards() {
	return actionCards;
    }

    public void setHand(ArrayList<ActionCard> hand2) {
	hand = hand2;	
    }

    public ArrayList<ActionCard> getHand() {
	return hand;
    }

    public void makeRobot(Robot robot2) {
	robot = robot2;
    }

}