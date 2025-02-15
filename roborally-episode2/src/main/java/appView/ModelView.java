package appView;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import appControl.ModelControl;

public class ModelView extends JFrame {
    
    private ModelControl model;
    private BoardView boardView;
    private CardView cardView;
    private JLabel command;

    public ModelView(ModelControl model) {
	this.model = model;
	initGUI();
    }

    private void initGUI() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setTitle("ROBORALLY 2.0");
	setSize(new Dimension(900, 800));
	setLayout(new FlowLayout(FlowLayout.CENTER));
	
	boardView = new BoardView(model.getBoard());
	add(boardView);
	
	command = new JLabel();
	add(command);
	
	cardView = new CardView();
	add(cardView);
	
	
    }
    
    public CardView accessCards() {
	return cardView;
    }

    public CardView cardView() {
	return cardView;
    }
    
    public JLabel getCommand() {
	return command;
    }

}
