package com.pra;

import java.awt.Font;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.pra.controller.BackupController;
import com.pra.controller.LogInController;
import com.pra.controller.UserController;
import com.pra.repositories.SampleInRepository;

@SpringBootApplication
public class ApplicationLoader implements CommandLineRunner {

	@Autowired
	LogInController loginControl;
	@Autowired
	BackupController backupControl;

	@Autowired
	UserController userControl;
	@Autowired
	SampleInRepository sampleInRepo;

	public static void main(String[] args) throws Exception {

		new LoadingScreen(6).setVisible(true);

		setDefaultFont(new Font("Arial", Font.BOLD, 14));

		try {
			new SpringApplicationBuilder(ApplicationLoader.class).headless(false).run(args);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Problem while loading application, please check out database and other resources");
			System.exit(0);
		}

		// System.out.println(args.length);
		// SpringApplication.run(ApplicationLoader.class);

	}

	// Put your logic here.
	public void run(String... args) throws Exception {

		// System.out.println("hello world");
		// User user = new User("admin", "");
		// userControl.create(user);
		// for (int i = 0; i < 1e1; i++)
		// sampleInRepo.save(SampleIn.builder().refId(i).date(LocalDate.now().plusDays(i))
		// .detailReport("SELECT * FROM")
		// .cost(i * Math.random())
		// .price(i * Math.random())
		// .build());

		if (BackupController.BACKUP_PATH.equals(BackupController.BACKUP_FILE_NAME))
			backupControl.openWindow();
		else
			loginControl.openWindow();
	}

	public static void setDefaultFont(Font font) {
		// UIManager.getLookAndFeelDefaults().put("defaultFont", font);

		for (Map.Entry<Object, Object> entry : UIManager.getDefaults().entrySet()) {
			Object key = entry.getKey();
			Object value = javax.swing.UIManager.get(key);
			if (value != null && value instanceof javax.swing.plaf.FontUIResource) {
				javax.swing.plaf.FontUIResource fr = (javax.swing.plaf.FontUIResource) value;
				javax.swing.plaf.FontUIResource f = new javax.swing.plaf.FontUIResource(fr.getFamily(), fr.getStyle(),
						20);
				javax.swing.UIManager.put(key, f);
			}
		}

		// UIManager.put("Button.font", font);
		// UIManager.put("ToggleButton.font", font);
		// UIManager.put("RadioButton.font", font);
		// UIManager.put("CheckBox.font", font);
		// UIManager.put("ColorChooser.font", font);
		// UIManager.put("ComboBox.font", font);
		// UIManager.put("Label.font", font);
		// UIManager.put("List.font", font);
		// UIManager.put("MenuBar.font", font);
		// UIManager.put("MenuItem.font", font);
		// UIManager.put("RadioButtonMenuItem.font", font);
		// UIManager.put("CheckBoxMenuItem.font", font);
		// UIManager.put("Menu.font", font);
		// UIManager.put("PopupMenu.font", font);
		// UIManager.put("OptionPane.font", font);
		// UIManager.put("Panel.font", font);
		// UIManager.put("ProgressBar.font", font);
		// UIManager.put("ScrollPane.font", font);
		// UIManager.put("Viewport.font", font);
		// UIManager.put("TabbedPane.font", font);
		// UIManager.put("Table.font", font);
		// UIManager.put("TableHeader.font", font);
		// UIManager.put("TextField.font", font);
		// UIManager.put("PasswordField.font", font);
		// UIManager.put("TextArea.font", font);
		// UIManager.put("TextPane.font", font);
		// UIManager.put("EditorPane.font", font);
		// UIManager.put("TitledBorder.font", font);
		// UIManager.put("ToolBar.font", font);
		// UIManager.put("ToolTip.font", font);
		// UIManager.put("Tree.font", font);
	}

}
