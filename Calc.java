import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
class Frame1 extends Frame
{
	MenuBar mb=new MenuBar();
	Menu m1=new Menu("File");
	Menu m2=new Menu("Edit");
	Menu m3=new Menu("Search");
	Menu m4=new Menu("Tools");
	Menu m5=new Menu("Help");
	MenuItem m11=new MenuItem("New");
	MenuItem m12=new MenuItem("Open");
	MenuItem m13=new MenuItem("Save");
	MenuItem m14=new MenuItem("Save As");
	MenuItem m15=new MenuItem("Page Setup");
	MenuItem m16=new MenuItem("Print");
	MenuItem m17=new MenuItem("Exit");
	TextArea t=new TextArea();
	Frame1(String title)
	{
		super(title);
		initialize();
	}
	//Frame1()
	//{
	//	initialize();
	//}
	void initialize()
	{
		setSize(500,400);
		add(t);
		t.setFont(new Font("Arial",0,18));
		addWindowListener(new WindowAdapter(){public void windowClosing(WindowEvent e){System.exit(0);}});
		mb.add(m1);mb.add(m2);mb.add(m3);
		mb.add(m4);mb.add(m5);
		setMenuBar(mb);
		m1.add(m11);
		m1.add(m12);
		m1.addSeparator();
		m1.add(m13);
		m1.add(m14);
		m1.addSeparator();
		m1.add(m15);
		m1.add(m16);
		m1.addSeparator();
		m1.add(m17);
		m17.setShortcut(new MenuShortcut('X'));
		m17.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){m17_Click(e);}});
		m12.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){m12_Click(e);}});
		m13.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){m13_Click(e);}});
		m14.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){m14_Click(e);}});
	}
	void m14_Click(ActionEvent e)
	{
		ColorDialog c=new ColorDialog(this);
		c.setVisible(true);
		Color clr=c.color;
		if(clr!=null)
		{
			t.setForeground(clr);
		}
	}
	void m12_Click(ActionEvent e)
	{
		try{
			FileDialog f=new FileDialog(this,"Open File As....");
			f.setVisible(true);
			String path=f.getDirectory()+f.getFile();
			FileInputStream fin=new FileInputStream(path);
			int size=fin.available();
			byte[] b=new byte[size];
			fin.read(b);
			String str=new String(b);
			t.setText(str);
		}catch(Exception ee){
			System.out.println(ee);
			}
	}
	void m13_Click(ActionEvent e)
	{
		try{
			FileDialog f=new FileDialog(this,"Save File As....",1);
			f.setVisible(true);
			String path=f.getDirectory()+f.getFile();
			FileWriter fw=new FileWriter(path);
			String s=t.getText();
			fw.write(s);
			fw.close();
		}catch(Exception ee){}
	}
	void m17_Click(ActionEvent e)
	{
		int a=JOptionPane.showConfirmDialog(this,"Are you sure?");
		if(a==0)
			System.exit(0);
	}
}
class ColorDialog extends Dialog
{
		Label l1=new Label();
		Scrollbar sc1=new Scrollbar(Scrollbar.HORIZONTAL,0,5,0,260);
		Scrollbar sc2=new Scrollbar(Scrollbar.HORIZONTAL,0,5,0,260);
		Scrollbar sc3=new Scrollbar(Scrollbar.HORIZONTAL,0,5,0,260);
		Color color;
		ColorDialog(Frame p)
		{
			super(p,true);
			setSize(500,500);
			setLayout(null);
			addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					color=l1.getBackground();
					dispose();
				}
			});
			sc1.setBounds(50,50,400,50);add(sc1);
			sc2.setBounds(50,120,400,50);add(sc2);
			sc3.setBounds(50,190,400,50);add(sc3);
			l1.setBounds(50,260,400,50);add(l1);
			l1.setBackground(Color.black);
			sc1.addAdjustmentListener(new AdjustmentListener(){public void adjustmentValueChanged(AdjustmentEvent e){sc1_scroll();}});
			sc2.addAdjustmentListener(new AdjustmentListener(){public void adjustmentValueChanged(AdjustmentEvent e){sc2_scroll();}});
			sc3.addAdjustmentListener(new AdjustmentListener(){public void adjustmentValueChanged(AdjustmentEvent e){sc3_scroll();}});
		}
		void sc1_scroll()
		{
			int a=sc1.getValue();
			sc1.setBackground(new Color(a,0,0));
			mix();
		}
		void sc2_scroll()
		{
			int a=sc2.getValue();
			sc2.setBackground(new Color(0,a,0));
			mix();
	}
	void sc3_scroll()
	{
			int a=sc3.getValue();
			sc3.setBackground(new Color(0,0,a));
			mix();
	}
	void mix()
	{
		int r=sc1.getValue();
		int g=sc2.getValue();
		int b=sc3.getValue();
		l1.setBackground(new Color(r,g,b));
	}
}
class p125
{
	public static void main(String[] ar)
	{
		Frame1 f=new Frame1("DFGFDGF");
		f.setVisible(true);
	}
}