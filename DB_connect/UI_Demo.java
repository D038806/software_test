
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;/*事件使用要載入event套件*/
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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/*UI_Demo的建構式設定按下視窗"X"鈕後即為關閉視窗*/
		setBounds(100,100,250,250);/*設定視窗為座標(100,100),大小為450*300*/
		setTitle("yeah聊天室");
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
		
		username = new JLabel("使用者名稱：");
		panel.add(username);	
		
		name = new JTextField();
		name.setColumns(10);
		name.setText("");
		panel.add(name);		
		loginInfo.add(panel);		
		
		panel = new JPanel(new FlowLayout());
		password = new JLabel("密碼：");
		panel.add(password);
		num = new JTextField();
		num.setColumns(10);
		num.setText("");
		panel.add(num);
		loginInfo.add(panel);
		firstPanel.add(loginInfo, BorderLayout.NORTH);		

		panel = new JPanel(new FlowLayout());
		JButton button = new JButton("登入");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{ 
				//Pane_two.setVisible(true);
				
				c.signIn(name.getText(),num.getText());
				cl.show(mainPanel, "2");
			}
		});
		panel.add(button);/*將按鈕button1放進contentPane物件中*/
		
		JButton button1 = new JButton("註冊");
		
		
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
	
	String btn[] = {"看板","聊天室","討論區"};
	JButton selec[] = new JButton[btn.length];
	secondPane()
	{
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		//setTitle("Yeah聊天室");
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
		if(text.equals("看板"))
		{
			BoardPane = new thirdJPanel();
			//System.out.println("123");
		}
		else if(text.equals("聊天室"))
		{
			chatPane = new thirdJPanel();
			//System.out.println("456");
		}
		else if(text.equals("討論區"))
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
		String box[] = {"新手教學","常見問題","Q:斷線頻率","Q:手機APP","Q:人數限制","個人資料?","Q:中英文切換?","Q:背景更改","離線留言?"};
		JButton cho[] = new JButton[box.length];
		CalculatorRMIClient c = new CalculatorRMIClient();
		thirdJPanel()
		{
//			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/*UI_Demo的建構式設定按下視窗"X"鈕後即為關閉視窗*/
//			setBounds(100,100,450,300);/*設定視窗為座標(100,100),大小為450*300*/
//			setTitle("看板");
//			BoardPane = new JPanel();
//			setContentPane(BoardPane);
//			setLayout(null);
			
//--------------------------------------------------------------------------------------------------------			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/*UI_Demo的建構式設定按下視窗"X"鈕後即為關閉視窗*/
			setBounds(100,100,560,400);/*設定視窗為座標(100,100),大小為450*300*/
			setTitle("聊天室");
			
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
			
			topic = new JLabel("Yeah聊天室");
			topic.setBounds(50,10,200,100);
			add(topic);
			person = new JLabel("對方IP位置");
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
			
			
			
			JButton give = new JButton("傳送");
			give.setBounds(475,305,60,25);
			give.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{	 
					c.chatroom(saywords.getText(),person.getText(),"AAAA");
					content.setText(saywords.getText());
					JOptionPane.showMessageDialog(null, "傳送成功！");
			}
			});
			add(give);
//------------------------------------------------------------------------------------------			
//			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			setBounds(100,100,469,300);
//			setTitle("討論區");
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
//			JButton  add = new JButton("新增主題");
//			add.addActionListener(new ActionListener()
//			{
//				public void actionPerformed(ActionEvent e)
//				{	 
//					addPane = new AddPanel();
//				}
//			});
//			JButton  reply = new JButton("回復");
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
		String box[] = {"新手教學","常見問題","Q:斷線頻率","Q:手機APP","Q:人數限制","個人資料?","Q:中英文切換?","Q:背景更改","離線留言?"};
		JButton cho[] = new JButton[box.length];
		private JPanel articlePane;
		public AddPanel addPane;
		public ReplyPanel replyPane;
		
		public ArticleFrame(){
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100,100,469,300);
			setTitle("討論區");
			articlePane = new JPanel();
			setContentPane(articlePane);
			articlePane.setLayout(null);

			articlePane.setLayout(new GridLayout(3,2));
			for(int p = 0; p < box.length; p++)
			{
				cho[p] = new JButton(box[p]);
				add(cho[p]);
			}
			
			JButton  add = new JButton("新增主題");
			add.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{	 
					addPane = new AddPanel();
				}
			});
			JButton  reply = new JButton("回復");
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/*UI_Demo的建構式設定按下視窗"X"鈕後即為關閉視窗*/
		setBounds(100,100,450,300);/*設定視窗為座標(100,100),大小為450*300*/
		setTitle("新增主題");
		JPanel  addPane = new JPanel();
		setContentPane(addPane);
		setLayout(null);
		
		title = new JLabel("主題名稱：");
		title.setBounds(30,20,80,100);
		add(title);
		
		context = new JLabel("內容：");
		context.setBounds(30,60,60,100);
		add(context);
		
		tab1 = new JTextField();
		tab1.setColumns(10);
		tab1.setText("Q:....");
		tab1.setBounds(120,50,100,30);
		add(tab1);
		
		tab2 = new JTextField();
		tab2.setColumns(10);
		tab2.setText("有關:....");
		tab2.setBounds(120,100,300,100);
		add(tab2);
		
		JButton send = new JButton("送出");
		send.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	 
				c.subject(tab1.getText(),tab2.getText());
				JOptionPane.showMessageDialog(null, "新增成功！");
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
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);/*UI_Demo的建構式設定按下視窗"X"鈕後即為關閉視窗*/
			setBounds(100,100,450,300);/*設定視窗為座標(100,100),大小為450*300*/
			setTitle("回復");
			JPanel  replyPane = new JPanel();
			setContentPane(replyPane);
			setLayout(null);
			
			
			
			title2 = new JLabel("回復文章：");
			title2.setBounds(30,20,80,100);
			add(title2);
			
			context2 = new JLabel("內容：");
			context2.setBounds(30,60,60,100);
			add(context2);
			
			tab1 = new JTextField();
			tab1.setColumns(10);
			tab1.setText("RE:....");
			tab1.setBounds(120,50,100,30);
			add(tab1);
			
			tab2 = new JTextField();
			tab2.setColumns(10);
			tab2.setText("有關:....");
			tab2.setBounds(120,100,300,100);
			add(tab2);
			
			JButton Re_send = new JButton("送出");
			Re_send.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{	 
					JOptionPane.showMessageDialog(null, "回復成功！");
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
