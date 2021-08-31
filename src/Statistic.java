import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Statistic {
	private JFrame frame;
	private JLabel background;
	private JButton back, ad, diary;
	private String ID;
	private BufferedImage img_bg, img_back, img_ad, img_diary, img_g1, img_g2, img_b1, img_b2, img_m, img_diary2;
	private String bgPath, backPath, adPath, diaryPath, g1Path, g2Path, b1Path, b2Path, mPath, diary2Path;
	private String server = "jdbc:mysql://140.119.19.73:9306/";
	private String database = "TG06?useUnicode=true&characterEncoding=UTF-8";
	private String url = server + database;
	private String username = "TG06";
	private String sqlpassword = "bMIEqf";
	private Connection conn = null;
	private double goodcount=0;
	private double badcount=0;

	public Statistic(String ID) throws SQLException {
		this.ID = ID;
		connection();
		CountMood();
		LoadMainFile();
		createFrame();
	}
	public void createFrame() throws SQLException {		
		if(badcount!=0) {
			if(goodcount/(goodcount+badcount)<0.25) {		
			setBackground(img_b1, b1Path);  
			}
		else if(goodcount/(goodcount+badcount)>=0.25&goodcount/(goodcount+badcount)<0.5) {
			setBackground(img_b2, b2Path);  
		}
		else if(goodcount/(goodcount+badcount)>=0.5&goodcount/(goodcount+badcount)<=0.75) {
			setBackground(img_g1, g1Path);  
		}
		else if(goodcount/(goodcount+badcount)>0.75) {
			setBackground(img_g2, g2Path);  
		}
	}
		else {
			if(goodcount!=0) {
				setBackground(img_g2, g2Path); 
			}
			else {
				setBackground(img_m, mPath); 
			}
		}
		
		background = new JLabel(new ImageIcon(img_bg));
		frame = new JFrame();

		createLabel();
		frame.getContentPane().add(background);
		frame.pack();
		frame.setTitle("Statistic");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(false);
		frame.setResizable(false);
	}

	public void createLabel() throws SQLException {
		back = new JButton(new ImageIcon(img_back));
		back.setActionCommand("Back");
		back.setBounds(600, 530, img_back.getWidth(), img_back.getHeight());
		back.setContentAreaFilled(false);
		back.setBorder(null);

		ad = new JButton(new ImageIcon(img_ad));
		ad.setActionCommand("Advice");
		ad.setBounds(159, 530, img_ad.getWidth(), img_ad.getHeight());
		ad.setContentAreaFilled(false);
		ad.setBorder(null);

		diary = new JButton(new ImageIcon(img_diary));
		diary.setActionCommand("Diary");
		diary.setBounds(380, 530, img_diary.getWidth(), img_diary.getHeight());
		diary.setContentAreaFilled(false);
		diary.setBorder(null);

		class ButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				String command = event.getActionCommand();
				if (command.equals("Back")) {
					frame.setVisible(false);
					try {
						new MainFrame(ID);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else if (command.equals("Advice")) {
					try {
						String webURL = "";
						if (getBGPath().equals(b1Path)) {
							int op = JOptionPane.showConfirmDialog(null, "人生並非總是如意，聽聽歌來沉靜心靈?", "即將跳轉網站", JOptionPane.YES_NO_OPTION);							
							if (op == JOptionPane.YES_OPTION) {
								webURL = "https://www.youtube.com/watch?v=KQS6DctTfp4&ab_channel=PositiveCalm--%E8%BA%AB%E5%BF%83%E9%9D%88%E5%81%A5%E5%BA%B7PositiveCalm--%E8%BA%AB%E5%BF%83%E9%9D%88%E5%81%A5%E5%BA%B7";
								java.awt.Desktop.getDesktop().browse(java.net.URI.create(webURL));
							} else if (op == JOptionPane.NO_OPTION) {
								
							}
							
						} else if (getBGPath().equals(b2Path)) {
                            int op = JOptionPane.showConfirmDialog(null, "讓鯊鯊幫你掃除煩惱?", "即將跳轉網站", JOptionPane.YES_NO_OPTION);							
							if (op == JOptionPane.YES_OPTION) {
								webURL = "https://www.youtube.com/watch?v=n3ri8v07aMc&ab_channel=%E3%81%86%E3%81%BE%E3%81%A1%E3%82%83%E3%82%93%E3%83%81%E3%83%A3%E3%83%B3%E3%83%8D%E3%83%AB%E3%81%86%E3%81%BE%E3%81%A1%E3%82%83%E3%82%93%E3%83%81%E3%83%A3%E3%83%B3%E3%83%8D%E3%83%AB";
								java.awt.Desktop.getDesktop().browse(java.net.URI.create(webURL));
							} else if (op == JOptionPane.NO_OPTION) {

							}
						} else if (getBGPath().equals(g2Path)) {
                            int op = JOptionPane.showConfirmDialog(null, "你真的很棒，想要掌聲鼓勵嗎?", "即將跳轉網站", JOptionPane.YES_NO_OPTION);							
							if (op == JOptionPane.YES_OPTION) {
								webURL = "https://www.youtube.com/watch?v=h52lR8x34CY&ab_channel=%E5%85%89%E9%A0%AD%E7%9A%84%E6%B3%A1%E6%8B%89%E9%BA%B5";
								java.awt.Desktop.getDesktop().browse(java.net.URI.create(webURL));
							} else if (op == JOptionPane.NO_OPTION) {
								
							}
						} else if(getBGPath().equals(g1Path)){
                            int op = JOptionPane.showConfirmDialog(null, "人生可以過得很精彩，想學學鯊鯊嗎?", "即將跳轉網站", JOptionPane.YES_NO_OPTION);						
							if (op == JOptionPane.YES_OPTION) {
								webURL = "https://www.youtube.com/watch?v=f9LYm1GRT0I&ab_channel=%D0%9C%D0%BD%D0%B5%D1%81%D0%BA%D1%83%D1%87%D0%BD%D0%BE%D0%9C%D0%BD%D0%B5%D1%81%D0%BA%D1%83%D1%87%D0%BD%D0%BE";
								java.awt.Desktop.getDesktop().browse(java.net.URI.create(webURL));
							} else if (op == JOptionPane.NO_OPTION) {

							}
						}else if(getBGPath().equals(mPath)){
                            int op = JOptionPane.showConfirmDialog(null, "希望可愛鯊鯊陪你嗎?", "即將跳轉網站", JOptionPane.YES_NO_OPTION);							
							if (op == JOptionPane.YES_OPTION) {
								webURL = "https://www.ikea.com.tw/zh/products/play/toys-for-small-children/blahaj-art-10373589";
								java.awt.Desktop.getDesktop().browse(java.net.URI.create(webURL));
							} else if (op == JOptionPane.NO_OPTION) {

							}
						}
						
				        
					} catch (java.lang.NullPointerException e) {
						e.printStackTrace();
					} catch (java.io.IOException e) {
				        System.out.println(e.getMessage());
				        javax.swing.JOptionPane.showMessageDialog(null, "網路連結錯誤 ");
				    }
				} else if (command.equals("Diary")) {
					Object[] options = {"Good","Bad","Cancel"};
					int op = JOptionPane.showOptionDialog(null,"請選擇想要撰寫的日記：","提示",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
                    if (op == 0) {
                    	new GoodMood(ID);
    					frame.setVisible(false);
					} else if (op == 1) {
						new BadMood(ID);
    					frame.setVisible(false);
					}
					else if (op == 2) {
						
					}
                   
					
				}
			}
		}
		ActionListener listener = new ButtonListener();
		back.addActionListener(listener);
		ad.addActionListener(listener);
		diary.addActionListener(listener);

		frame.add(back);
		frame.add(ad);
		frame.add(diary);
	}
   
	public void LoadMainFile() {
		backPath = "/back3.png";
		adPath = "/advice.png";
		diaryPath = "/diary.png";
		g1Path = "/g1.png";
		g2Path = "/g2.png";
		b1Path = "/b1.png";
		b2Path = "/b2.png";
		mPath = "/m.png";
		diary2Path = "/diary2.png";
		java.net.URL imgURL;
		try {
			imgURL = Statistic.class.getResource(backPath);
			img_back = ImageIO.read(imgURL);
			imgURL = Statistic.class.getResource(adPath);
			img_ad = ImageIO.read(imgURL);
			imgURL = Statistic.class.getResource(diaryPath);
			img_diary = ImageIO.read(imgURL);
			imgURL = Statistic.class.getResource(g1Path);
			img_g1 = ImageIO.read(imgURL);
			imgURL = Statistic.class.getResource(g2Path);
			img_g2 = ImageIO.read(imgURL);
			imgURL = Statistic.class.getResource(b1Path);
			img_b1 = ImageIO.read(imgURL);
			imgURL = Statistic.class.getResource(b2Path);
			img_b2 = ImageIO.read(imgURL);
			imgURL = Statistic.class.getResource(mPath);
			img_m = ImageIO.read(imgURL);
			imgURL = Statistic.class.getResource(diary2Path);
			img_diary2 = ImageIO.read(imgURL);
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, "載入圖檔錯誤: " + bgPath);
			img_bg = null;
		}
	}

	public void setBackground(BufferedImage image, String path) {
		img_bg = image;
		bgPath = path;
	}

	public String getBGPath() {
		String path = "";
		path = bgPath;
		return path;
	}
	
	public void open() {
		frame.setVisible(true);
	}
	public void CountMood() {		 
		 try {    	
		    	Statement stat = conn.createStatement();	
		    	Statement stat2 = conn.createStatement();
		        String query = String.format("SELECT COUNT(`GoodDiary`)FROM `Diary` WHERE `UserID`='%s' ",ID);  
		        String query2 = String.format("SELECT COUNT(`BadDiary`)FROM `Diary` WHERE `UserID`='%s' ",ID);  
		        boolean hasResultSet = stat.execute(query);	
		        boolean hasResultSet2 = stat2.execute(query2);
		        if (hasResultSet&&hasResultSet2) {
		        ResultSet result = stat.getResultSet();	
		        ResultSet result2 = stat2.getResultSet();	
		        goodcount=showResultSet(result);
		        badcount=showResultSet(result2);
		       
		        result.close();
		        result2.close();    
		    }
		    }
		    catch(SQLException e){
		    	System.out.println("錯誤訊息:" + e.getMessage());
		    	javax.swing.JOptionPane.showMessageDialog(null, "網路連結錯誤 ");
		    }		 
	 }
	 public  int showResultSet(ResultSet result) throws SQLException{
			int count=0;		
			while (result.next()) {	
				count=result.getInt(1);
				}	
			
			return count;
			
		}
	 public void connection() throws SQLException {
			try {
				conn= DriverManager.getConnection(url,username,sqlpassword);				
		     }
		     catch(SQLException e){
		     	System.out.println("錯誤訊息:" + e.getMessage());
		     	javax.swing.JOptionPane.showMessageDialog(null, "網路連結錯誤 ");	
		     }  
		}
}
