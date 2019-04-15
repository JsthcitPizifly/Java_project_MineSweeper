import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class ShowRecord extends JDialog implements ActionListener{
     File file=new File("���ְ�.txt");
     String name=null;
     Hashtable hashtable=null;
     JButton show,reRecord;
     JLabel labelBeginner[],labelIntermediate[],labelAdvanced[];
     public ShowRecord(JFrame frame,Hashtable h) {
          setTitle("ɨ�׻��ְ�");
          hashtable=h;
          setBounds(100,100,320,185);
          setResizable(false);
          setVisible(false);
          setModal(true); 
          labelBeginner=new JLabel[3];
          labelIntermediate=new JLabel[3];
          labelAdvanced=new JLabel[3];
          for(int i=0;i<3;i++) {
              labelBeginner[i]=new JLabel();
              labelBeginner[i].setBorder(null);
              labelIntermediate[i]=new JLabel();
              labelIntermediate[i].setBorder(null);
              labelAdvanced[i]=new JLabel();
              labelAdvanced[i].setBorder(null);
          } 
          labelBeginner[0].setText("����");
          labelBeginner[1].setText(""+999); 
          labelBeginner[1].setText("����");  
          labelIntermediate[0].setText("�м�");
          labelIntermediate[1].setText(""+999); 
          labelIntermediate[1].setText("����");
          labelAdvanced[0].setText("�߼�");
          labelAdvanced[1].setText(""+999); 
          labelAdvanced[1].setText("����"); 
          JPanel pCenter=new JPanel();
          pCenter.setLayout(new GridLayout(3,3)); 
          for(int i=0;i<3;i++)
             pCenter.add(labelBeginner[i]);
          for(int i=0;i<3;i++)
             pCenter.add(labelIntermediate[i]);
          for(int i=0;i<3;i++)
             pCenter.add(labelAdvanced[i]);
          pCenter.setBorder(BorderFactory.createTitledBorder("mineRecording"));           
          show=new JButton("��ʾ�ɼ�");
          reRecord=new JButton("���¼Ƿ�");
          show.addActionListener(this);
          reRecord.addActionListener(this);
          JPanel pSouth=new JPanel();
          pSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
          pSouth.add(reRecord);
          pSouth.add(show);
          add(pCenter,BorderLayout.CENTER);
          add(pSouth,BorderLayout.SOUTH) ;
     }
     public void readAndShow(){
         try{ FileInputStream in=new FileInputStream(file);
              ObjectInputStream object_in=new ObjectInputStream(in);
              hashtable=(Hashtable)object_in.readObject();
              object_in.close();
              in.close(); 
              String temp=(String)hashtable.get("����");
              StringTokenizer fenxi=new StringTokenizer(temp,"#");
              labelBeginner[0].setText(fenxi.nextToken());
              labelBeginner[1].setText(fenxi.nextToken());
              labelBeginner[2].setText(fenxi.nextToken());  
              temp=(String)hashtable.get("�м�");
              fenxi=new StringTokenizer(temp,"#");
              labelIntermediate[0].setText(fenxi.nextToken());
              labelIntermediate[1].setText(fenxi.nextToken());
              labelIntermediate[2].setText(fenxi.nextToken());  
              temp=(String)hashtable.get("�߼�");
              fenxi=new StringTokenizer(temp,"#");
              labelAdvanced[0].setText(fenxi.nextToken());
              labelAdvanced[1].setText(fenxi.nextToken());
              labelAdvanced[2].setText(fenxi.nextToken()); 
         }
         catch(Exception e){}
     }
     public void actionPerformed(ActionEvent e) {
         if(e.getSource()==reRecord) {
            hashtable.put("����","����#"+999+"#����");
            labelBeginner[0].setText("����");
            labelBeginner[1].setText(""+999);
            labelBeginner[2].setText("����"); 
            hashtable.put("�м�","�м�#"+999+"#����");
            labelIntermediate[0].setText("����");
            labelIntermediate[1].setText(""+999);
            labelIntermediate[2].setText("����");
            hashtable.put("�߼�","�߼�#"+999+"#����");
            labelAdvanced[0].setText("����");
            labelAdvanced[1].setText(""+999);
            labelAdvanced[2].setText("����");
            try{ FileOutputStream out=new FileOutputStream(file);
                 ObjectOutputStream object_out=new ObjectOutputStream(out);
                 object_out.writeObject(hashtable);
                 object_out.close();
                 out.close();
            }
            catch(IOException event){}
            setVisible(false);
     }
     if(e.getSource()==show){
        readAndShow();
     }  
  }
}
