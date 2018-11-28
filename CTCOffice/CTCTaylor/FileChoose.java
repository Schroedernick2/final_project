import java.util.scanner;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileChoose{

	JFileChooser myFileChooser = new JfileChooser();
	
	
	if (myFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
		java.io.File file = myFileChooser.getSelectedFile;
	}


}