import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaintJFrame extends JFrame implements ActionListener {

	JButton jb1 = new JButton("线");
	JButton jb2 = new JButton("矩形");
	JButton jb3 = new JButton("圆");
	JButton jb4 = new JButton("颜色");
	JButton jb5 = new JButton("保存");

	Color selectColor = Color.black;// 画笔的颜色是什么?

	MyJPanel jp = new MyJPanel(this);// 绘画区域

	public void actionPerformed(ActionEvent e) {
		if (jb1 == e.getSource()) {// 线
			jp.setType("线");
		} else if (jb2 == e.getSource()) {// 矩形
			jp.setType("矩形");
		} else if (jb3 == e.getSource()) {// 圆
			jp.setType("圆");
		} else if (jb4 == e.getSource()) {// 颜色

			// 这里弹出一个颜色的选择器 用他来选择颜色
			JColorChooser jc = new JColorChooser();
			// 打开颜色选择器 并得到颜色选择器选择的颜色
			selectColor = jc.showDialog(this, "请选择颜色", selectColor);
			// 把选好的颜色设置在按钮背景上
			jb4.setBackground(selectColor);

		} else if (jb5 == e.getSource()) {// 保存

			JFileChooser jf = new JFileChooser();
			jf.showSaveDialog(this);
			jp.save(jf.getSelectedFile());

		}

	}

	// 构造器
	public PaintJFrame() {

		this.getContentPane().setBackground(Color.BLACK);

		jp.setBackground(Color.white);// 设置这个画板的底色 为白色
		jp.setBounds(65, 10, 515, 550);

		// 把绘画板 添加到窗口中
		this.add(jp);

		// 把按钮关联事件 注册事件
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb5.addActionListener(this);

		// 设置按钮的位置
		jb1.setBounds(1, 10, 60, 30);
		jb2.setBounds(1, 40, 60, 30);
		jb3.setBounds(1, 70, 60, 30);
		jb4.setBounds(1, 100, 60, 30);
		jb5.setBounds(1, 130, 60, 30);

		// 添加按钮到窗口之中
		this.add(jb1);
		this.add(jb2);
		this.add(jb3);
		this.add(jb4);
		this.add(jb5);
		jb4.setBackground(selectColor);

		this.setLayout(null);// 没有布局 我们自己定义组件的摆放方式
		this.setTitle("学号：xxxxx 		姓名：xxxx ");// 设置窗口的标题
		this.setSize(600, 600);// 设置窗口的大小
		this.setVisible(true); // 显示窗口

	}

	public static void main(String[] args) {
		new PaintJFrame();
	}
	public class MyJPanel extends JPanel implements MouseListener {
		public class Info {
			String type = "";
			int x;
			int y;
			int x1;
			int y1;
			Color c;

		}

		private PaintJFrame jf = null;

		MyJPanel(PaintJFrame jf) {
			this.jf = jf;
			// 把事件关联到这个面板上 注册事件
			this.addMouseListener(this);
		}

		private String type;

		public void setType(String type) {
			this.type = type;
		}

		int x, y;

		// 鼠标的按下
		public void mousePressed(MouseEvent e) {
			x = e.getX();
			y = e.getY();
		}

		Vector<Info> infos = new Vector<Info>();

		public void paint(Graphics g) {// 重绘
			super.paint(g);

			for (Info i : infos) {

				g.setColor(i.c);
				if (i.type.equalsIgnoreCase("线")) {
					g.drawLine(i.x, i.y, i.x1, i.y1);
				} else if (i.type.equalsIgnoreCase("矩形")) {
					g.drawRect(i.x, i.y, i.x1, i.y1);
				} else if (i.type.equalsIgnoreCase("圆")) {
					g.drawOval(i.x, i.y, i.x1, i.y1);

				}
			}

		}

		public void save(File f) {

			// 在内存中创建了一个照片
			BufferedImage image = new BufferedImage(this.getWidth(),
					this.getHeight(), BufferedImage.TYPE_INT_RGB);

			Graphics g = image.getGraphics();
			// 给照片设置背景颜色
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth() + 1, this.getHeight() + 1);

			for (Info i : infos) {
				g.setColor(i.c);
				if (i.type.equalsIgnoreCase("线")) {
					g.drawLine(i.x, i.y, i.x1, i.y1);
				} else if (i.type.equalsIgnoreCase("矩形")) {
					g.drawRect(i.x, i.y, i.x1, i.y1);
				} else if (i.type.equalsIgnoreCase("圆")) {
					g.drawOval(i.x, i.y, i.x1, i.y1);

				}
			}

			try {
				ImageIO.write(image, "jpeg", f);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

		// 鼠标的松开
		public void mouseReleased(MouseEvent e) {
			int x1 = e.getX();
			int y1 = e.getY();
			Graphics2D g = (Graphics2D) this.getGraphics();
			g.setColor(jf.selectColor);
			Info info = new Info();
			info.c = jf.selectColor;
			info.type = type;
			if (type.equalsIgnoreCase("线")) {
				g.drawLine(x, y, x1, y1);
				info.x = x;
				info.y = y;
				info.x1 = x1;
				info.y1 = y1;
			} else if (type.equalsIgnoreCase("矩形")) {
				g.drawRect(x, y, x1 - x, y1 - y);
				info.x = x;
				info.y = y;
				info.x1 = x1 - x;
				info.y1 = y1 - y;
			} else if (type.equalsIgnoreCase("圆")) {
				g.drawOval(x, y, x1 - x, y1 - y);
				info.x = x;
				info.y = y;
				info.x1 = x1 - x;
				info.y1 = y1 - y;
			}
			infos.add(info);

		}

		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

}