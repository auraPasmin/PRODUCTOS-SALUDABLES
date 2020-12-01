package vistas;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChatCliente extends JFrame{
    
    private boolean continuar = true;
	
	/**
	 * Create the application.
	 */
	public ChatCliente(String cliente, String direccion) {
            setResizable(false);
            nameCliente = cliente;
            direccionIP = direccion;
            initialize();
            txtMensaje.setEditable(true);
            btnSend.setEnabled(true);
            btnSend.setFocusable(true);
	}

	private void initialize() {
		setTitle( "Cliente" );
		setBounds(100, 100, 450, 337);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		menu = new JPanel();
		menu.setBackground(Color.WHITE);
		menu.setBorder(UIManager.getBorder("CheckBox.border"));
		menu.setBounds(2, 0, 434, 32);
		getContentPane().add(menu);
		menu.setLayout(null);
		
		icono = new JLabel(nameCliente);
		icono.setBackground(Color.WHITE);
		icono.setBounds(10, 0, 30, 30);
		menu.add(icono);
		icono.setIcon(new ImageIcon(getClass().getResource("/recurso/user.png")));
		
		lblNombreUsuario = new JLabel(nameCliente);
		lblNombreUsuario.setForeground(Color.RED);
		lblNombreUsuario.setBounds(50, 11, 60, 14);
		menu.add(lblNombreUsuario);
		
		
		textArea = new JTextArea();
                 //textArea.setBounds(10, 43, 424, 223);
                //getContentPane().add(textArea);
                scroll = new JScrollPane(textArea);
                scroll.setBounds(2, 31, 432, 247);
                getContentPane().add(scroll);
        
		txtMensaje = new JTextField();
		txtMensaje.setBounds(2, 277, 344, 22);
		getContentPane().add(txtMensaje);
		txtMensaje.setColumns(10);
		
		btnSend = new JButton("enviar");
		btnSend.setBackground(Color.GREEN);
		btnSend.setBounds(345, 276, 94, 23);
		getContentPane().add(btnSend);
		

		setVisible(true);
	}
	

	
//	@Override
//	public void actionPerformed(ActionEvent event) {
//		mensajeaEnviar = txtMensaje.getText();
//		sendMessages(mensajeaEnviar);
//		txtMensaje.setText("");
//		
//		if(event.getSource() == "enviar") {
//			mensajeaEnviar = txtMensaje.getText();
//			sendMessages(mensajeaEnviar);
//			txtMensaje.setText("");
//		}
//	}
//        
//        public void guardarChat() {
//            
//            int opcion = JOptionPane.showConfirmDialog(null, "¡¿Deseas guardar registro de la conversación?!",
//                    "Mensaje", JOptionPane.OK_CANCEL_OPTION);
//            
//            if(opcion == JOptionPane.OK_OPTION) {
//                try {
//                    
//                    JFileChooser selectFile = new JFileChooser();
//                    selectFile.showSaveDialog(this);
//                    File archivo = selectFile.getSelectedFile();
//                
//                    if(archivo != null) {
//                        FileWriter writeToFile = new FileWriter(archivo + ".txt");
//                        //System.out.print(mensajeToSave);
//                        writeToFile.write(mensajeToSave);
//                        writeToFile.close();
//                    }
//                }
//                catch(IOException e) {
//                    JOptionPane.showMessageDialog(null, "No se pudo guardar su archivo", "Error", JOptionPane.WARNING_MESSAGE);
//                }
//                finally {
//                    dispose();
//                }
//            }
//            else
//                dispose();
//        }
        public JTextArea getText(){
            return textArea;
        }
        public String getMessage(){
            return txtMensaje.getText();
        }
	public void addListenerbtn(ActionListener al){
            btnSend.addActionListener(al);
        }
	private JTextField txtMensaje;
	private JTextArea textArea;
	private JLabel lblNombreUsuario;
	private JPanel menu;
	private JLabel icono;
	private JButton btnSend;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	private String mensajeaEnviar = "";
	private String nameCliente;
	private String direccionIP;
	private Socket cliente;
	private JScrollPane scroll;
        private String mensajeToSave = "";
}

