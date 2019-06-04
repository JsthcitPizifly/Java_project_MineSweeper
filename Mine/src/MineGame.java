import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.io.*;
public class MineGame extends JFrame implements ActionListener{
     JMenuBar bar;
     JMenu fileMenu;
     JMenuItem beginner,intermediate,advanced,mineRecording;
     MineArea mineArea=null;
     File reCording=new File("reCording.txt");
     Hashtable hashtable=null;
     ShowRecord showHeroRecord=null;
     MineGame(){
         mineArea=MineArea.getInstance(16,16,40,1);
         add(mineArea,BorderLayout.CENTER);
         bar=new JMenuBar();
         fileMenu=new JMenu("��Ϸ");
         beginner=new JMenuItem("����");
         intermediate=new JMenuItem("�м�");
         advanced=new JMenuItem("�߼�");
         mineRecording=new JMenuItem("���ְ�");
         fileMenu.add(beginner);
         fileMenu.add(intermediate);
         fileMenu.add(advanced);
         fileMenu.add(mineRecording);
         bar.add(fileMenu);
         setJMenuBar(bar);
         beginner.addActionListener(this);
         intermediate.addActionListener(this);
         advanced.addActionListener(this);
         mineRecording.addActionListener(this);
         hashtable=new Hashtable();
         hashtable.put("beginner","beginner#"+999+"#����"); 
         hashtable.put("intermediate","intermediate#"+999+"#����");
         hashtable.put("advanced","advanced#"+999+"#����");
         if(!reCording.exists()) {
            try{ FileOutputStream out=new FileOutputStream(reCording);
                 ObjectOutputStream objectOut=new ObjectOutputStream(out);
                 objectOut.writeObject(hashtable);
                 objectOut.close();
                 out.close();
            }
            catch(IOException e){}
        }
        showHeroRecord=new ShowRecord(this,hashtable);
        setBounds(100,100,280,380);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
     }
     public void actionPerformed(ActionEvent e){
        if(e.getSource()==beginner){
              mineArea.initMineArea(8,8,10,1);
              setBounds(100,100,200,280);  
        }
        if(e.getSource()==intermediate){
              mineArea.initMineArea(16,16,40,2);
              setBounds(100,100,280,380);
        }
        if(e.getSource()==advanced){
              mineArea.initMineArea(22,22,99,3);
              setBounds(100,100,350,390);
        }
        if(e.getSource()==mineRecording){ 
          if(showHeroRecord!=null)
           showHeroRecord.setVisible(true);
        }
        validate();
    }
    public static void main(String args[]){
        new MineGame();
    }
}
