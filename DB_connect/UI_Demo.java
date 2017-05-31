
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;/*�ƥ�ϥέn���Jevent�M��*/
import javax.swing.JTextArea;
import javax.swing.JScrollPane;


class MyFrame extends JFrame
{
	public JPanel mainPanel, firstPanel, secondPanel;
	public secondPane Pane_two;
	private JTextField name,num;
	private JLabel title,username,password;
	private CardLayout cl;
	CalculatorRMIClient c = new CalculatorRMIClient();
	int i = 0;
	MyFrame()
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/*UI_Demo���غc���]�w���U����"X"�s��Y����������*/
		setBounds(100,100,250,250);/*�]�w�������y��(100,100),�j�p��450*300*/
		setTitle("yeah��ѫ�");
		setResizable(false);
		
		cl = new CardLayout();		
		mainPanel = new JPanel();
		mainPanel.setLayout(cl);
		add(mainPanel);
		//add(mainPanel, BorderLayout.CENTER);
		
		//Setting first panel.
		firstPanel = new JPanel();
		firstPanel.setLayout(new BorderLayout());
		
		JPanel loginInfo = new JPanel();
		loginInfo.setLayout(new BoxLayout(loginInfo, BoxLayout.Y_AXIS));
		JPanel panel = new JPanel(new FlowLayout());
		
		username = new JLabel("�ϥΪ̦W�١G");
		panel.add(username);	
		
		name = new JTextField();
		name.setColumns(10);
		name.setText("");
		panel.add(name);		
		loginInfo.add(panel);		
		
		panel = new JPanel(new FlowLayout());
		password = new JLabel("�K�X�G");
		panel.add(password);
		num = new JTextField();
		num.setColumns(10);
		num.setText("");
		panel.add(num);
		loginInfo.add(panel);
		firstPanel.add(loginInfo, BorderLayout.NORTH);		

		panel = new JPanel(new FlowLayout());
		JButton button = new JButton("�n�J");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{ 
				//Pane_two.setVisible(true);
				
				c.signIn(name.getText(),num.getText());
				cl.show(mainPanel, "2");
			}
		});
		panel.add(button);/*�N���sbutton1��icontentPane����*/
		
		JButton button1 = new JButton("���U");
		
		
		button1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{ 
				//Pane_two.setVisible(true);
				
				c.register(name.getText(),num.getText());
				cl.show(mainPanel, "2");
			}
		});
		panel.add(button1);
		
		
		
		firstPanel.add(panel, BorderLayout.SOUTH);
		mainPanel.add(firstPanel, "1");		
		
		secondPanel = new JPanel();
		mainPanel.add(new secondPane(), "2");
		cl.show(mainPanel, "1");
		setVisible(true);		
	}
}


class secondPane extends JPanel implements ActionListener
{
	public secondPane Pane_two;
	public JFrame BoardPane,chatPane,articlePane;
	
	String btn[] = {"�ݪO","��ѫ�","�Q�װ�"};
	JButton selec[] = new JButton[btn.length];
	secondPane()
	{
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		//setTitle("Yeah��ѫ�");
		setLayout(null);
		
		JPanel range = new JPanel();
		add(range);
		range.setBounds(150,20,100,120);
		setLayout(new FlowLayout(FlowLayout.LEFT,100,20));

		for(int i = 0; i < btn.length; i++)
		{
			selec[i] = new JButton(btn[i]);
			add(selec[i]);
			selec[i].addActionListener(this);
		}
		//setVisible(true);
		//System.out.println(selec[0]);
		
	}
	
	public void actionPerformed(ActionEvent event)
	{
		JButton button = (JButton)event.getSource();
		String text = button.getText();
		System.out.println();
		if(text.equals("�ݪO"))
		{
			BoardPane = new thirdJPanel();
			//System.out.println("123");
		}
		else if(text.equals("��ѫ�"))
		{
			chatPane = new thirdJPanel();
			//System.out.println("456");
		}
		else if(text.equals("�Q�װ�"))
		{
			articlePane = new ArticleFrame();
			//System.out.println("789");
		}
		else
		{
			//System.out.println("9999999");
		}
			
	}
	
	class  thirdJPanel extends JFrame
	{
		public JPanel BoardPane,chatPane;
		private JLabel topic,person;
		private JTextField ip_num,saywords,toName;
		String box[] = {"�s��о�","�`�����D","Q:�_�u�W�v","Q:���APP","Q:�H�ƭ���","�ӤH���?","Q:���^�����?","Q:�I�����","���u�d��?"};
		JButton cho[] = new JButton[box.length];
		CalculatorRMIClient c = new CalculatorRMIClient();
		thirdJPanel()
		{
//			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/*UI_Demo���غc���]�w���U����"X"�s��Y����������*/
//			setBounds(100,100,450,300);/*�]�w�������y��(100,100),�j�p��450*300*/
//			setTitle("�ݪO");
//			BoardPane = new JPanel();
//			setContentPane(BoardPane);
//			setLayout(null);
			
//--------------------------------------------------------------------------------------------------------			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/*UI_Demo���غc���]�w���U����"X"�s��Y����������*/
			setBounds(100,100,560,400);/*�]�w�������y��(100,100),�j�p��450*300*/
			setTitle("��ѫ�");
			
			chatPane = new JPanel();
			chatPane.setBounds(150, 220, 100, 120);
			
			JTextArea content = new JTextArea(100,10);
			JScrollPane g = new JScrollPane(content);
			setContentPane(chatPane);
			setLayout(null);
			g.setBounds(280,10,250,280);
			chatPane.add(g);
//			JPanel half1 = new JPanel();
//			half1.setBounds(80,10,80,80);
//			half1.setBackground(Color.yellow);
//			setContentPane(half1);
//			setLayout(null);
			
			topic = new JLabel("Yeah��ѫ�");
			topic.setBounds(50,10,200,100);
			add(topic);
			person = new JLabel("���IP��m");
			person.setBounds(10,60,200,100);
			add(person);
			
			ip_num = new JTextField();
			ip_num.setColumns(10);
			ip_num.setText("");
			ip_num.setBounds(80,100,120,20);
			add(ip_num);
			
//			toName = new JTextField();
//			toName.setColumns(10);
//			toName.setText("");
//			toName.setBounds(80,60,120,20);
//			add(toName);
			
			
			saywords = new JTextField();
			saywords.setColumns(10);
			saywords.setText("");
			saywords.setBounds(280,300,195,35);
			add(saywords);
			
			
			
			JButton give = new JButton("�ǰe");
			give.setBounds(475,305,60,25);
			give.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{	 
					c.chatroom(saywords.getText(),person.getText(),"AAAA");
					content.setText(saywords.getText());
					JOptionPane.showMessageDialog(null, "�ǰe���\�I");
			}
			});
			add(give);
//------------------------------------------------------------------------------------------			
//			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			setBounds(100,100,469,300);
//			setTitle("�Q�װ�");
//			articlePane = new JPanel();
//			setContentPane(articlePane);
//			articlePane.setLayout(null);
//
//			articlePane.setLayout(new GridLayout(3,2));
//			for(int p = 0; p < box.length; p++)
//			{
//				cho[p] = new JButton(box[p]);
//				add(cho[p]);
//			}
//			
//			JButton  add = new JButton("�s�W�D�D");
//			add.addActionListener(new ActionListener()
//			{
//				public void actionPerformed(ActionEvent e)
//				{	 
//					addPane = new AddPanel();
//				}
//			});
//			JButton  reply = new JButton("�^�_");
//			reply.addActionListener(new ActionListener()
//			{
//				public void actionPerformed(ActionEvent e)
//				{	 
//					replyPane = new ReplyPanel();
//				}
//			});
//			add.setBounds(400,70,100,45); 
//			add(add); 
//			reply.setBounds(500,200,100,30);
//			add(reply);
			setVisible(true);
		}
	
	}
	
	class ArticleFrame extends JFrame{
		String box[] = {"�s��о�","�`�����D","Q:�_�u�W�v","Q:���APP","Q:�H�ƭ���","�ӤH���?","Q:���^�����?","Q:�I�����","���u�d��?"};
		JButton cho[] = new JButton[box.length];
		private JPanel articlePane;
		public AddPanel addPane;
		public ReplyPanel replyPane;
		
		public ArticleFrame(){
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100,100,469,300);
			setTitle("�Q�װ�");
			articlePane = new JPanel();
			setContentPane(articlePane);
			articlePane.setLayout(null);

			articlePane.setLayout(new GridLayout(3,2));
			for(int p = 0; p < box.length; p++)
			{
				cho[p] = new JButton(box[p]);
				add(cho[p]);
			}
			
			JButton  add = new JButton("�s�W�D�D");
			add.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{	 
					addPane = new AddPanel();
				}
			});
			JButton  reply = new JButton("�^�_");
			reply.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{	 
					replyPane = new ReplyPanel();
				}
			});
			add.setBounds(400,70,100,45); 
			add(add); 
			reply.setBounds(500,200,100,30);
			add(reply);
			setVisible(true);
		}
	}
	
	
	class AddPanel extends JFrame
	{
		private JLabel title,context;
		private JTextField tab1,tab2;
		CalculatorRMIClient c = new CalculatorRMIClient();
		private CardLayout cl= new CardLayout();
		
		AddPanel()
		{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/*UI_Demo���غc���]�w���U����"X"�s��Y����������*/
		setBounds(100,100,450,300);/*�]�w�������y��(100,100),�j�p��450*300*/
		setTitle("�s�W�D�D");
		JPanel  addPane = new JPanel();
		setContentPane(addPane);
		setLayout(null);
		
		title = new JLabel("�D�D�W�١G");
		title.setBounds(30,20,80,100);
		add(title);
		
		context = new JLabel("���e�G");
		context.setBounds(30,60,60,100);
		add(context);
		
		tab1 = new JTextField();
		tab1.setColumns(10);
		tab1.setText("Q:....");
		tab1.setBounds(120,50,100,30);
		add(tab1);
		
		tab2 = new JTextField();
		tab2.setColumns(10);
		tab2.setText("����:....");
		tab2.setBounds(120,100,300,100);
		add(tab2);
		
		JButton send = new JButton("�e�X");
		send.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	 
				c.subject(tab1.getText(),tab2.getText());
				JOptionPane.showMessageDialog(null, "�s�W���\�I");
//				cl.show(ArticleFrame, "3");
		}
		});
		send.setBounds(300,220,80,35); 
		add(send); 
		
		setVisible(true);
		}
		
	}
	class ReplyPanel extends JFrame
	{
		private JLabel title2,context2;
		private JTextField tab1,tab2;
		
		ReplyPanel()
		{
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/*UI_Demo���غc���]�w���U����"X"�s��Y����������*/
			setBounds(100,100,450,300);/*�]�w�������y��(100,100),�j�p��450*300*/
			setTitle("�^�_");
			JPanel  replyPane = new JPanel();
			setContentPane(replyPane);
			setLayout(null);
			
			
			
			title2 = new JLabel("�^�_�峹�G");
			title2.setBounds(30,20,80,100);
			add(title2);
			
			context2 = new JLabel("���e�G");
			context2.setBounds(30,60,60,100);
			add(context2);
			
			tab1 = new JTextField();
			tab1.setColumns(10);
			tab1.setText("RE:....");
			tab1.setBounds(120,50,100,30);
			add(tab1);
			
			tab2 = new JTextField();
			tab2.setColumns(10);
			tab2.setText("����:....");
			tab2.setBounds(120,100,300,100);
			add(tab2);
			
			JButton Re_send = new JButton("�e�X");
			Re_send.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{	 
					JOptionPane.showMessageDialog(null, "�^�_���\�I");
			}
			});
			Re_send.setBounds(300,220,80,35); 
			add(Re_send); 
			setVisible(true);
		}
		
	}
	
}
	

public class UI_Demo
{
	public static void main(String[] args)
	{
		MyFrame f = new MyFrame(); 
		
	}

}
