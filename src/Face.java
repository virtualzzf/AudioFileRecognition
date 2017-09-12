import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class Face extends javax.swing.JFrame {

	public Face() {
		initComponents();
	}

	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		textArea = new javax.swing.JTextArea();
		loadFile = new javax.swing.JButton();
		translate = new javax.swing.JButton();
		setLocation(400,200);
		
		setTitle("音频转写");
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		textArea.setColumns(20);
		textArea.setRows(5);
		jScrollPane1.setViewportView(textArea);

		loadFile.setText("\u4e0a\u4f20\u6587\u4ef6");
		loadFile.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.out.println("上传文件");
				
				JFileChooser chooseFile=new JFileChooser();  
				chooseFile.showDialog(new JLabel(), "选择文件");   
		        System.out.println(chooseFile.getSelectedFile().getAbsolutePath()); 
		        filePath=chooseFile.getSelectedFile().getAbsolutePath();
			}
		});

		translate.setText("\u8f6c\u5199");
		translate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				textArea.setText("");
				AudioToText attInstance=new AudioToText(filePath);
				textArea.setText(attInstance.convert());
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		jScrollPane1,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		376,
																		Short.MAX_VALUE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(60, 60,
																		60)
																.addComponent(
																		loadFile)
																.addGap(99, 99,
																		99)
																.addComponent(
																		translate,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		87,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGap(29, 29, 29)
								.addComponent(jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										146,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										41, Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														translate,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														34, Short.MAX_VALUE)
												.addComponent(
														loadFile,
														javax.swing.GroupLayout.Alignment.TRAILING,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														34,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(50, 50, 50)));

		pack();
	}

	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Face().setVisible(true);
			}
		});
	}

	private javax.swing.JButton loadFile;
	private javax.swing.JButton translate;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea textArea;
	private String filePath;

}