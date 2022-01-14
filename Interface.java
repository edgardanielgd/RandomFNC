package fnc;
import org.netbeans.lib.awtextra.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
public class Interface extends javax.swing.JDialog {
    public Interface(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        sc = null;
        initComponents();
    }
    @SuppressWarnings("unchecked")
    private void initComponents() {
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new AbsoluteLayout()); 
        getContentPane().setBackground(new java.awt.Color(55,197, 211));
        
        setBounds(new Rectangle(50, 50, 500, 500));
        
        jLabel2 = new JLabel("Forma Normal Conjuntiva");
        jLabel2.setBackground(new Color(255,255,255));
        jLabel2.setOpaque(true);
        
        jLabel1 = new JLabel();
        
        jLabel3 = new JLabel();
        jLabel3.setBackground(new Color(255,255,255));
        jLabel3.setOpaque(true);
        
        jLabel4 = new JLabel();
        jLabel4.setBackground(new Color(255,255,255));
        jLabel4.setOpaque(true);
        
        jText1 = new JTextField();
        
        jButton1 = new JButton("Aceptar");
        jButton2 = new JButton("Imprimir en consola");
        
        jTable1 = new JTable();
        
        jScrollPane1 = new JScrollPane();
        
        jLabel2.setFont(new Font("Sans Serif", 1, 30));
        jLabel2.setForeground(new Color(255,0,0));
        getContentPane().add(jLabel2,
                new AbsoluteConstraints(50,5, 440, 50));
        
        jLabel3.setText("Digite el número de variables booleanas");
        jLabel3.setFont(new Font("Sans Serif", 1, 20));
        jLabel3.setForeground(new Color(255,0,0));
        getContentPane().add(jLabel3,
                new AbsoluteConstraints(10,60, 400, 50));
        
        jText1.setFont(new Font("Sans Serif", 1, 16));
        getContentPane().add(jText1,
                new AbsoluteConstraints(10,110, 50, 50));
        
        jButton1.setFont(new Font("Sans Serif", 1, 16));
        getContentPane().add(jButton1,
                new AbsoluteConstraints(210,110, 200, 50));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        
        jLabel4.setText("Visualice una FNC aleatoria");
        jLabel4.setFont(new Font("Sans Serif", 1, 16));
        jLabel4.setForeground(new Color(255,0,0));
        getContentPane().add(jLabel4,
                new AbsoluteConstraints(10,170, 240, 50));
        
        jTable1.setFont(new Font("Sans Serif", 1, 26));
        jTable1.setForeground(new Color(0,0,0));
        jScrollPane1.setViewportView( jTable1 );
        getContentPane().add(jScrollPane1,
                new AbsoluteConstraints(50,230, 400, 200));
        
        jButton2.setFont(new Font("Sans Serif", 1, 16));
        getContentPane().add(jButton2,
                new AbsoluteConstraints(50,450, 200, 50));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed2(evt);
            }
        });
        
        jLabel1.setIcon(new ImageIcon(Interface.class.getResource("/fnc/Images/13-1.jpg"))); // NOI18N
        getContentPane().add(jLabel1,
                new AbsoluteConstraints(0,0, 500, 500));

        
        
        pack();
        
    }
    
    private void jButton1ActionPerformed(ActionEvent evt) {
        String data = jText1.getText();
        Integer value = null;
        try{
            value = Integer.parseInt(data);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Digite valores validos");
            return;
        }
        
        sc = FormaNormalConjuntiva.getRandomFNC(value);
        
        DefaultTableModel modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column)
                {
                    return false;//This causes all cells to be not editable
                }
        };
        for( int i = 0; i < value; i ++){
            modelo.addColumn(String.valueOf(i));
        }
        
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        
        int pos = 0;
        for( Disjunction dis : sc.getArr()){
            String[] arr = new String[value];
            Boolean[] bin = dis.binary_expression;
            for(int i = 0; i < value; i ++){
                arr[i] =( ( i == 0 ) ? "( " : "+ " )+
                        ( (bin[i]) ? "X\u0305" : "X" ) + String.valueOf(i)+
                        ( (i == value - 1) ? " )" : "" );
            }
            modelo.addRow( arr );
        }
        
        jTable1.setModel(modelo);
        
        jTable1.setRowHeight(50);
        
    }
    
    private void jButton1ActionPerformed2(ActionEvent evt) {
        if( sc != null ){
            System.out.println( sc );
        }
    }
    public static void main(String args[]) {
        Interface inter = new Interface(null, false);
        inter.setVisible(true);   
    }
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    
    private JTextField jText1;
    
    private JButton jButton1;
    private JButton jButton2;
    
    private JTable jTable1;
    
    private JScrollPane jScrollPane1;
    
    private SingleConjuntion sc;
}
